package com.sown.outerrim.handlers;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.event.FMLServerStoppingEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import net.minecraft.block.Block;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.*;

public class RegionRegenHandler {

    private static class BlockSnapshot {
        int x, y, z;
        Block block;
        int meta;

        BlockSnapshot(int x, int y, int z, Block block, int meta) {
            this.x = x;
            this.y = y;
            this.z = z;
            this.block = block;
            this.meta = meta;
        }
    }

    private static class ScheduledRegion {
        String name;
        int delayTicks;
        int ticksRemaining;

        ScheduledRegion(String name, int delayTicks) {
            this.name = name;
            this.delayTicks = delayTicks;
            this.ticksRemaining = delayTicks;
        }

        NBTTagCompound toNBT() {
            NBTTagCompound tag = new NBTTagCompound();
            tag.setString("name", name);
            tag.setInteger("delay", delayTicks);
            return tag;
        }

        static ScheduledRegion fromNBT(NBTTagCompound tag) {
            return new ScheduledRegion(tag.getString("name"), tag.getInteger("delay"));
        }
    }
    
    public static void initRegionSystem(MinecraftServer server) {
        for (World world : server.worldServers) {
            if (world == null) continue;

            File regionDir = getRegionSaveDir(world);
            if (!regionDir.exists()) regionDir.mkdirs();

            loadScheduledRegions(world);
        }
    }

    private static final Map<String, ChunkCoordinates[]> selections = new HashMap<>();
    private static final Queue<BlockSnapshot> regenQueue = new LinkedList<>();
    private static int regenTicksRemaining = 0;
    private static int regenInterval = 1;
    private static final List<ScheduledRegion> scheduledRegions = new ArrayList<>();
    private static World regenWorld = null;
    private static final Map<Integer, List<ScheduledRegion>> scheduledRegionsByDim = new HashMap<>();

    public RegionRegenHandler() {
        MinecraftForge.EVENT_BUS.register(this);
        FMLCommonHandler.instance().bus().register(this); // <-- THIS LINE WAS MISSING AND IS REQUIRED
    }

    private static File getRegionSaveDir(World world) {
        int dimId = world.provider.dimensionId;
        File base = dimId == 0 ? world.getSaveHandler().getWorldDirectory()
                               : new File(world.getSaveHandler().getWorldDirectory(), "DIM" + dimId);
        File dir = new File(base, "saved_regions");
        if (!dir.exists()) dir.mkdirs();
        return dir;
    }

    private static File getScheduledFile(World world) {
        return new File(getRegionSaveDir(world), "scheduled_regions.dat");
    }

    @SubscribeEvent
    public void onInteract(PlayerInteractEvent event) {
        if (!(event.entityPlayer instanceof EntityPlayer) || !event.entityPlayer.canCommandSenderUseCommand(4, "*")) return;

        ItemStack held = event.entityPlayer.getHeldItem();
        if (held == null || held.getItem() != Items.wooden_shovel) return;

        ChunkCoordinates pos = new ChunkCoordinates(event.x, event.y, event.z);
        String name = event.entityPlayer.getCommandSenderName();

        if (event.action == PlayerInteractEvent.Action.RIGHT_CLICK_BLOCK) {
            selections.computeIfAbsent(name, k -> new ChunkCoordinates[2])[1] = pos;
            event.entityPlayer.addChatMessage(new ChatComponentText(EnumChatFormatting.GREEN + "Second position set: " + pos));
        } else if (event.action == PlayerInteractEvent.Action.LEFT_CLICK_BLOCK) {
            // Save the block before it's broken
            Block block = event.world.getBlock(event.x, event.y, event.z);
            int meta = event.world.getBlockMetadata(event.x, event.y, event.z);

            selections.computeIfAbsent(name, k -> new ChunkCoordinates[2])[0] = pos;
            event.entityPlayer.addChatMessage(new ChatComponentText(EnumChatFormatting.GREEN + "First position set: " + pos));

            // Cancel the block break and immediately restore the block
            event.setCanceled(true); // Prevents block break
            event.world.scheduleBlockUpdate(event.x, event.y, event.z, block, 1); // Optional, for visuals

            event.world.setBlock(event.x, event.y, event.z, block, meta, 3);
            event.world.markBlockForUpdate(event.x, event.y, event.z);
        }
    }

    @SubscribeEvent
    public void onWorldTick(TickEvent.ServerTickEvent event) {
        // Process ongoing region regeneration
        if (regenTicksRemaining > 0 && !regenQueue.isEmpty()) {
            regenTicksRemaining--;

            if (regenWorld == null || regenWorld.isRemote) {
                System.out.println("[RegionRegen] Regen world is null or client-side, skipping tick.");
                return;
            }

            // System.out.println("[RegionRegen] Tick - Remaining: " + regenTicksRemaining + ", Queue size: " + regenQueue.size());

            int blocksToPlace = Math.max(1, regenQueue.size() / Math.max(1, regenTicksRemaining));

            for (int i = 0; i < blocksToPlace && !regenQueue.isEmpty(); i++) {
                BlockSnapshot snap = regenQueue.poll();

                if (snap == null || snap.block == null) {
                    // System.out.println("[RegionRegen] Skipped null block at " + (snap != null ? snap.x + "," + snap.y + "," + snap.z : "null snap"));
                    continue;
                }

                if (snap.meta < 0 || snap.meta > 15) {
                    System.out.println("[RegionRegen] Warning: Invalid metadata " + snap.meta + " at " + snap.x + "," + snap.y + "," + snap.z);
                }

                // System.out.println("[RegionRegen] Attempting to place " + snap.block.getUnlocalizedName() + " at " + snap.x + "," + snap.y + "," + snap.z + " (meta " + snap.meta + ")");

                boolean success = regenWorld.setBlock(snap.x, snap.y, snap.z, snap.block, snap.meta, 3);

                if (!success) {
                    // System.out.println("[RegionRegen] Failed to place block at " + snap.x + "," + snap.y + "," + snap.z);
                }
            }
        }

        // Process scheduled region regeneration
        for (World world : MinecraftServer.getServer().worldServers) {
            if (world == null || world.isRemote) continue;

            int dimId = world.provider.dimensionId;
            List<ScheduledRegion> scheduled = scheduledRegionsByDim.get(dimId);
            if (scheduled == null) continue;

            Iterator<ScheduledRegion> iter = scheduled.iterator();
            while (iter.hasNext()) {
                ScheduledRegion region = iter.next();
                region.ticksRemaining--;

                if (region.ticksRemaining <= 0 && regenQueue.isEmpty()) {
                    File file = new File(getRegionSaveDir(world), region.name + ".dat");
                    if (!file.exists()) {
                        System.out.println("[RegionRegen] Scheduled region '" + region.name + "' not found in DIM" + dimId);
                        continue;
                    }

                    try {
                        NBTTagCompound tag = CompressedStreamTools.readCompressed(new FileInputStream(file));
                        int count = tag.getInteger("Count");

                        Map<Integer, List<BlockSnapshot>> layers = new TreeMap<>();
                        List<BlockSnapshot> delayed = new ArrayList<>();

                        for (int i = 0; i < count; i++) {
                            NBTTagCompound b = tag.getCompoundTag("b" + i);
                            int x = b.getInteger("x");
                            int y = b.getInteger("y");
                            int z = b.getInteger("z");
                            Block block = (Block) Block.blockRegistry.getObject(b.getString("block"));
                            int meta = b.getInteger("meta");
                            BlockSnapshot snap = new BlockSnapshot(x, y, z, block, meta);
                            if (isDelayedBlock(block)) delayed.add(snap);
                            else layers.computeIfAbsent(y, k -> new ArrayList<>()).add(snap);
                        }

                        regenQueue.clear();
                        regenWorld = world;
                        Random rand = new Random();

                        for (List<BlockSnapshot> layer : layers.values()) {
                            Collections.shuffle(layer, rand);
                            regenQueue.addAll(layer);
                        }
                        Collections.shuffle(delayed, rand);
                        regenQueue.addAll(delayed);

                        regenTicksRemaining = 100; // Default duration for scheduled regeneration
                        region.ticksRemaining = region.delayTicks;

                        System.out.println("[RegionRegen] Auto-regenerating region '" + region.name + "' in DIM" + dimId);
                    } catch (Exception e) {
                        System.err.println("[RegionRegen] Error loading scheduled region '" + region.name + "': " + e.getMessage());
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    private static boolean isDelayedBlock(Block block) {
        if (block == null) return false;
        String name = block.getUnlocalizedName().toLowerCase();
        return name.contains("torch")
            || name.contains("sign")
            || name.contains("button")
            || name.contains("ladder")
            || name.contains("lever")
            || name.contains("skull");
    }

    public static class CommandSaveRegion extends CommandBase {
        @Override
        public String getCommandName() {
            return "saveregion";
        }

        @Override
        public String getCommandUsage(ICommandSender sender) {
            return "/saveregion <name>";
        }

        @Override
        public void processCommand(ICommandSender sender, String[] args) {
            if (!(sender instanceof EntityPlayer)) return;
            EntityPlayer player = (EntityPlayer) sender;
            String name = sender.getCommandSenderName();

            if (!selections.containsKey(name) || selections.get(name)[0] == null || selections.get(name)[1] == null) {
                sender.addChatMessage(new ChatComponentText(EnumChatFormatting.RED + "Both positions must be set."));
                return;
            }

            if (args.length != 1) {
                sender.addChatMessage(new ChatComponentText(EnumChatFormatting.RED + "Usage: /saveregion <name>"));
                return;
            }

            String regionName = args[0];
            World world = sender.getEntityWorld();
            int dimId = world.provider.dimensionId;
            File dimDir = dimId == 0
                    ? world.getSaveHandler().getWorldDirectory()
                    : new File(world.getSaveHandler().getWorldDirectory(), "DIM" + dimId);
            File saveDir = new File(dimDir, "saved_regions");
            if (!saveDir.exists()) saveDir.mkdirs();

            ChunkCoordinates[] corners = selections.get(name);
            int minX = Math.min(corners[0].posX, corners[1].posX);
            int maxX = Math.max(corners[0].posX, corners[1].posX);
            int minY = Math.min(corners[0].posY, corners[1].posY);
            int maxY = Math.max(corners[0].posY, corners[1].posY);
            int minZ = Math.min(corners[0].posZ, corners[1].posZ);
            int maxZ = Math.max(corners[0].posZ, corners[1].posZ);

            try {
                NBTTagCompound tag = new NBTTagCompound();
                tag.setInteger("dim", dimId);

                int i = 0;
                for (int x = minX; x <= maxX; x++) {
                    for (int y = minY; y <= maxY; y++) {
                        for (int z = minZ; z <= maxZ; z++) {
                            Block block = world.getBlock(x, y, z);
                            int meta = world.getBlockMetadata(x, y, z);

                            NBTTagCompound entry = new NBTTagCompound();
                            entry.setInteger("x", x);
                            entry.setInteger("y", y);
                            entry.setInteger("z", z);
                            entry.setString("block", Block.blockRegistry.getNameForObject(block));
                            entry.setInteger("meta", meta);
                            tag.setTag("b" + (i++), entry);
                        }
                    }
                }

                tag.setInteger("Count", i);
                File file = new File(saveDir, regionName + ".dat");
                CompressedStreamTools.writeCompressed(tag, new FileOutputStream(file));
                sender.addChatMessage(new ChatComponentText(EnumChatFormatting.GREEN + "Region '" + regionName + "' saved with " + i + " blocks."));
            } catch (Exception e) {
                sender.addChatMessage(new ChatComponentText(EnumChatFormatting.RED + "Failed to save region: " + e.getMessage()));
                e.printStackTrace();
            }
        }

        @Override
        public int getRequiredPermissionLevel() {
            return 2;
        }
    }

    public static void saveScheduledRegions(World world) {
        try {
            List<ScheduledRegion> scheduledRegions = scheduledRegionsByDim.get(world.provider.dimensionId);
            if (scheduledRegions == null) return;

            NBTTagList list = new NBTTagList();
            for (ScheduledRegion region : scheduledRegions) {
                list.appendTag(region.toNBT());
            }

            NBTTagCompound wrapper = new NBTTagCompound();
            wrapper.setTag("regions", list);
            CompressedStreamTools.writeCompressed(wrapper, new FileOutputStream(getScheduledFile(world)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void loadScheduledRegions(World world) {
        File file = getScheduledFile(world);
        if (!file.exists()) return;

        try {
            NBTTagCompound wrapper = CompressedStreamTools.readCompressed(new FileInputStream(file));
            NBTTagList list = wrapper.getTagList("regions", 10);
            List<ScheduledRegion> scheduledRegions = new ArrayList<>();
            for (int i = 0; i < list.tagCount(); i++) {
                scheduledRegions.add(ScheduledRegion.fromNBT(list.getCompoundTagAt(i)));
            }
            scheduledRegionsByDim.put(world.provider.dimensionId, scheduledRegions);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Mod.EventHandler
    public static void onServerStarting() {
        for (World world : MinecraftServer.getServer().worldServers) {
            loadScheduledRegions(world);
        }
    }

    public static void onServerStopping() {
        for (World world : MinecraftServer.getServer().worldServers) {
            saveScheduledRegions(world);
        }
    }

    public static class CommandRegenRegion extends CommandBase {
        @Override
        public String getCommandName() {
            return "regenregion";
        }

        @Override
        public String getCommandUsage(ICommandSender sender) {
            return "/regenregion <name> [ticks] [dim]";
        }

        @Override
        public void processCommand(ICommandSender sender, String[] args) {
            if (args.length < 1) {
                sender.addChatMessage(new ChatComponentText(EnumChatFormatting.RED + "Usage: /regenregion <name> [ticks] [dim]"));
                return;
            }

            String regionName = args[0];
            int duration = args.length >= 2 ? parseInt(sender, args[1]) : 100;
            int dimId = args.length >= 3 ? parseInt(sender, args[2]) : sender.getEntityWorld().provider.dimensionId;

            World world = MinecraftServer.getServer().worldServerForDimension(dimId);
            if (world == null) {
                sender.addChatMessage(new ChatComponentText(EnumChatFormatting.RED + "Invalid dimension ID."));
                return;
            }

            File saveDir = getRegionSaveDir(world);
            File file = new File(saveDir, regionName + ".dat");

            if (!file.exists()) {
                sender.addChatMessage(new ChatComponentText(EnumChatFormatting.RED + "Region file not found in dimension " + dimId + "."));
                return;
            }

            try {
                NBTTagCompound tag = CompressedStreamTools.readCompressed(new FileInputStream(file));
                int fileDim = tag.getInteger("dim");
                if (fileDim != dimId) {
                    sender.addChatMessage(new ChatComponentText(EnumChatFormatting.RED + "Region belongs to dimension " + fileDim + ", not specified " + dimId + "."));
                    return;
                }

                int count = tag.getInteger("Count");
                Map<Integer, List<BlockSnapshot>> layers = new TreeMap<>();
                List<BlockSnapshot> delayedBlocks = new ArrayList<>();

                for (int i = 0; i < count; i++) {
                    NBTTagCompound b = tag.getCompoundTag("b" + i);
                    int x = b.getInteger("x");
                    int y = b.getInteger("y");
                    int z = b.getInteger("z");
                    Block block = (Block) Block.blockRegistry.getObject(b.getString("block"));
                    int meta = b.getInteger("meta");

                    BlockSnapshot snap = new BlockSnapshot(x, y, z, block, meta);
                    if (isDelayedBlock(block)) {
                        delayedBlocks.add(snap);
                    } else {
                        layers.computeIfAbsent(y, k -> new ArrayList<>()).add(snap);
                    }
                }

                regenQueue.clear();
                regenWorld = world;
                Random rand = new Random();

                for (List<BlockSnapshot> layer : layers.values()) {
                    Collections.shuffle(layer, rand);
                    regenQueue.addAll(layer);
                }
                Collections.shuffle(delayedBlocks, rand);
                regenQueue.addAll(delayedBlocks);

                regenTicksRemaining = duration;

                sender.addChatMessage(new ChatComponentText(EnumChatFormatting.GREEN + "Regenerating region '" + regionName + "' in dimension " + dimId + " over " + duration + " ticks."));
            } catch (Exception e) {
                sender.addChatMessage(new ChatComponentText(EnumChatFormatting.RED + "Failed to load region: " + e.getMessage()));
                e.printStackTrace();
            }
        }

        private static boolean isDelayedBlock(Block block) {
            if (block == null) return false;
            String name = block.getUnlocalizedName().toLowerCase();
            return name.contains("torch")
                || name.contains("sign")
                || name.contains("button")
                || name.contains("ladder")
                || name.contains("lever")
                || name.contains("skull");
        }

        @Override
        public int getRequiredPermissionLevel() {
            return 2;
        }
    }

    public static class CommandListRegions extends CommandBase {
        @Override
        public String getCommandName() {
            return "listregions";
        }

        @Override
        public String getCommandUsage(ICommandSender sender) {
            return "/listregions";
        }

        @Override
        public void processCommand(ICommandSender sender, String[] args) {
            boolean foundAny = false;

            for (World world : MinecraftServer.getServer().worldServers) {
                if (world == null) continue;

                int dimId = world.provider.dimensionId;
                File saveDir = getRegionSaveDir(world);
                String[] files = saveDir.list((dir, name) -> name.endsWith(".dat"));

                if (files == null || files.length == 0) continue;

                foundAny = true;
                sender.addChatMessage(new ChatComponentText(EnumChatFormatting.GREEN + "Saved Regions in Dimension " + dimId + ":"));
                for (String file : files) {
                    sender.addChatMessage(new ChatComponentText(EnumChatFormatting.WHITE + "- " + file.replace(".dat", "")));
                }
            }

            if (!foundAny) {
                sender.addChatMessage(new ChatComponentText(EnumChatFormatting.RED + "No saved regions found in any dimension."));
            }
        }

        @Override
        public int getRequiredPermissionLevel() {
            return 2;
        }
    }

    public static class CommandDeleteRegion extends CommandBase {
        @Override
        public String getCommandName() {
            return "deleteregion";
        }

        @Override
        public String getCommandUsage(ICommandSender sender) {
            return "/deleteregion <name> [dim]";
        }

        @Override
        public void processCommand(ICommandSender sender, String[] args) {
            if (args.length < 1) {
                sender.addChatMessage(new ChatComponentText(EnumChatFormatting.RED + "Usage: /deleteregion <name> [dim]"));
                return;
            }

            String name = args[0];
            int dimId = args.length >= 2 ? parseInt(sender, args[1]) : sender.getEntityWorld().provider.dimensionId;
            World world = MinecraftServer.getServer().worldServerForDimension(dimId);
            if (world == null) {
                sender.addChatMessage(new ChatComponentText(EnumChatFormatting.RED + "Invalid dimension ID."));
                return;
            }

            File saveDir = getRegionSaveDir(world);
            File file = new File(saveDir, name + ".dat");

            if (file.exists() && file.delete()) {
                // Remove scheduled region if it exists
                List<ScheduledRegion> list = scheduledRegionsByDim.get(dimId);
                if (list != null && list.removeIf(r -> r.name.equals(name))) {
                    saveScheduledRegions(world);
                    sender.addChatMessage(new ChatComponentText(EnumChatFormatting.YELLOW + "Removed scheduled regen for '" + name + "'."));
                }

                sender.addChatMessage(new ChatComponentText(EnumChatFormatting.GREEN + "Region '" + name + "' deleted from dimension " + dimId + "."));
            } else {
                sender.addChatMessage(new ChatComponentText(EnumChatFormatting.RED + "Region not found or could not be deleted."));
            }
        }

        @Override
        public int getRequiredPermissionLevel() {
            return 2;
        }
    }

    public static class CommandViewRegion extends CommandBase {
        @Override
        public String getCommandName() {
            return "viewregion";
        }

        @Override
        public String getCommandUsage(ICommandSender sender) {
            return "/viewregion <name> [dim]";
        }

        @Override
        public void processCommand(ICommandSender sender, String[] args) {
            if (args.length < 1 || args.length > 2) {
                sender.addChatMessage(new ChatComponentText(EnumChatFormatting.RED + "Usage: /viewregion <name> [dim]"));
                return;
            }

            String name = args[0];
            int dimId = args.length >= 2 ? parseInt(sender, args[1]) : sender.getEntityWorld().provider.dimensionId;

            World world = MinecraftServer.getServer().worldServerForDimension(dimId);
            if (world == null) {
                sender.addChatMessage(new ChatComponentText(EnumChatFormatting.RED + "Invalid dimension ID."));
                return;
            }

            File saveDir = getRegionSaveDir(world);
            File file = new File(saveDir, name + ".dat");

            if (!file.exists()) {
                sender.addChatMessage(new ChatComponentText(EnumChatFormatting.RED + "Region '" + name + "' not found in dimension " + dimId + "."));
                return;
            }

            try {
                NBTTagCompound tag = CompressedStreamTools.readCompressed(new FileInputStream(file));
                int count = tag.getInteger("Count");

                if (count == 0) {
                    sender.addChatMessage(new ChatComponentText(EnumChatFormatting.YELLOW + "Region '" + name + "' is empty."));
                    return;
                }

                NBTTagCompound first = tag.getCompoundTag("b0");
                NBTTagCompound last = tag.getCompoundTag("b" + (count - 1));

                sender.addChatMessage(new ChatComponentText(EnumChatFormatting.GREEN + String.format(
                        "Region '%s' in dimension %d: %s%d blocks from (%d, %d, %d) to (%d, %d, %d)",
                        name,
                        dimId,
                        EnumChatFormatting.WHITE,
                        count,
                        first.getInteger("x"), first.getInteger("y"), first.getInteger("z"),
                        last.getInteger("x"), last.getInteger("y"), last.getInteger("z")
                )));
            } catch (Exception e) {
                sender.addChatMessage(new ChatComponentText(EnumChatFormatting.RED + "Failed to read region: " + e.getMessage()));
                e.printStackTrace();
            }
        }

        @Override
        public int getRequiredPermissionLevel() {
            return 2;
        }
    }

    public static class CommandScheduleRegion extends CommandBase {
        @Override
        public String getCommandName() {
            return "scheduleregion";
        }

        @Override
        public String getCommandUsage(ICommandSender sender) {
            return "/scheduleregion <name> <delayTicks> [dim]";
        }

        @Override
        public void processCommand(ICommandSender sender, String[] args) {
            if (args.length < 2) {
                sender.addChatMessage(new ChatComponentText(EnumChatFormatting.RED + "Usage: /scheduleregion <name> <delayTicks> [dim]"));
                return;
            }

            String name = args[0];
            int delay = parseInt(sender, args[1]);
            int dimId = args.length >= 3 ? parseInt(sender, args[2]) : sender.getEntityWorld().provider.dimensionId;

            World world = MinecraftServer.getServer().worldServerForDimension(dimId);
            if (world == null) {
                sender.addChatMessage(new ChatComponentText(EnumChatFormatting.RED + "Invalid dimension ID."));
                return;
            }

            File saveDir = getRegionSaveDir(world);
            File file = new File(saveDir, name + ".dat");
            if (!file.exists()) {
                sender.addChatMessage(new ChatComponentText(EnumChatFormatting.RED + "Region not found in dimension " + dimId + "."));
                return;
            }

            List<ScheduledRegion> list = scheduledRegionsByDim.computeIfAbsent(dimId, k -> new ArrayList<>());
            list.removeIf(r -> r.name.equals(name)); // override existing
            list.add(new ScheduledRegion(name, delay));
            saveScheduledRegions(world);

            sender.addChatMessage(new ChatComponentText(EnumChatFormatting.GREEN + "Scheduled region '" + name + "' to regenerate every " + delay + " ticks in dimension " + dimId + "."));
        }

        @Override
        public int getRequiredPermissionLevel() {
            return 2;
        }
    }
}

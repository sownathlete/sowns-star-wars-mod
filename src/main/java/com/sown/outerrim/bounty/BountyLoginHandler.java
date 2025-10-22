package com.sown.outerrim.bounty;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.*;

public class BountyLoginHandler {

    private static final Map<UUID, List<ItemStack>> pendingHeads = new HashMap<>();
    private static final File SAVE_FILE = new File("pending_heads.dat");

    public static void queueHead(UUID uuid, ItemStack head) {
        pendingHeads.computeIfAbsent(uuid, k -> new ArrayList<>()).add(head);
        saveToDisk();
    }

    public static void handleLogin(EntityPlayerMP player) {
        UUID uuid = player.getUniqueID();
        
     // Sown intro
        if (uuid.toString().equals("5135dae1-7a0a-4915-85e1-c5a923e2d98a")) {
            for (Object obj : MinecraftServer.getServer().getConfigurationManager().playerEntityList) {
                EntityPlayerMP onlinePlayer = (EntityPlayerMP) obj;
                onlinePlayer.playerNetServerHandler.sendPacket(
                    new net.minecraft.network.play.server.S29PacketSoundEffect(
                        "outerrim:music.hero.anakin.intro",
                        onlinePlayer.posX, onlinePlayer.posY, onlinePlayer.posZ,
                        10000.0F,
                        1.0F
                    )
                );
            }
        }
        
        // ZugVam intro
        else if (uuid.toString().equals("e2e62bf7-1f56-4a7a-81c0-e2528b0bffcd")) {
            for (Object obj : MinecraftServer.getServer().getConfigurationManager().playerEntityList) {
                EntityPlayerMP onlinePlayer = (EntityPlayerMP) obj;
                onlinePlayer.playerNetServerHandler.sendPacket(
                    new net.minecraft.network.play.server.S29PacketSoundEffect(
                        "outerrim:music.hero.dooku.intro",
                        onlinePlayer.posX, onlinePlayer.posY, onlinePlayer.posZ,
                        10000.0F,
                        1.0F
                    )
                );
            }
        }
        
        // 711Hisroyal intro
        else if (uuid.toString().equals("44952a43-1177-4b80-9129-e2447a519f16")) {
            for (Object obj : MinecraftServer.getServer().getConfigurationManager().playerEntityList) {
                EntityPlayerMP onlinePlayer = (EntityPlayerMP) obj;
                onlinePlayer.playerNetServerHandler.sendPacket(
                    new net.minecraft.network.play.server.S29PacketSoundEffect(
                        "outerrim:music.hero.obi_wan.intro",
                        onlinePlayer.posX, onlinePlayer.posY, onlinePlayer.posZ,
                        10000.0F,
                        1.0F
                    )
                );
            }
        }
        
        // Im_Vengeance intro
        else if (uuid.toString().equals("b71432bc-a2a7-4829-b206-57cc10dd6cd0")) {
            for (Object obj : MinecraftServer.getServer().getConfigurationManager().playerEntityList) {
                EntityPlayerMP onlinePlayer = (EntityPlayerMP) obj;
                onlinePlayer.playerNetServerHandler.sendPacket(
                    new net.minecraft.network.play.server.S29PacketSoundEffect(
                        "outerrim:music.hero.luke.intro",
                        onlinePlayer.posX, onlinePlayer.posY, onlinePlayer.posZ,
                        10000.0F,
                        1.0F
                    )
                );
            }
        }

        // NoahClone67 intro
        else if (uuid.toString().equals("818f215d-4651-4d55-a355-4f71eaab95d3")) {
            for (Object obj : MinecraftServer.getServer().getConfigurationManager().playerEntityList) {
                EntityPlayerMP onlinePlayer = (EntityPlayerMP) obj;
                onlinePlayer.playerNetServerHandler.sendPacket(
                    new net.minecraft.network.play.server.S29PacketSoundEffect(
                        "outerrim:music.hero.boba_fett.intro",
                        onlinePlayer.posX, onlinePlayer.posY, onlinePlayer.posZ,
                        10000.0F,
                        1.0F
                    )
                );
            }
        }

        if (pendingHeads.containsKey(uuid)) {
            List<ItemStack> heads = pendingHeads.remove(uuid);
            int given = 0;
            int dropped = 0;

            for (ItemStack head : heads) {
                boolean added = player.inventory.addItemStackToInventory(head);
                if (added) given++;
                else {
                    player.dropPlayerItemWithRandomChoice(head, false);
                    dropped++;
                }
            }

            if (given > 0) {
                player.addChatMessage(new ChatComponentText(EnumChatFormatting.GREEN + "You received " + given + " bounty trophy item(s)."));
            }
            if (dropped > 0) {
                player.addChatMessage(new ChatComponentText(EnumChatFormatting.YELLOW.toString() + dropped + " bounty trophy item(s) were dropped at your feet because your inventory was full."));
            }

            saveToDisk(); // Update after removal
        }

        if (!BountyManager.getActiveBounties().isEmpty()) {
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    MinecraftServer.getServer().getCommandManager().executeCommand(player, "bounties");
                }
            }, 2000); // 2 seconds delay
        }
    }

    @SubscribeEvent
    public void onPlayerLogin(PlayerLoggedInEvent event) {
        if (event.player instanceof EntityPlayerMP) {
            handleLogin((EntityPlayerMP) event.player);
        }
    }

    public static void saveToDisk() {
        try {
            NBTTagCompound compound = new NBTTagCompound();
            for (Map.Entry<UUID, List<ItemStack>> entry : pendingHeads.entrySet()) {
                NBTTagList itemList = new NBTTagList();
                for (ItemStack stack : entry.getValue()) {
                    NBTTagCompound itemTag = new NBTTagCompound();
                    stack.writeToNBT(itemTag);
                    itemList.appendTag(itemTag);
                }
                compound.setTag(entry.getKey().toString(), itemList);
            }
            CompressedStreamTools.writeCompressed(compound, new FileOutputStream(SAVE_FILE));
        } catch (Exception e) {
            System.err.println("[BountyLoginHandler] Failed to save pending heads:");
            e.printStackTrace();
        }
    }

    public static void loadFromDisk() {
        if (!SAVE_FILE.exists()) return;

        try {
            NBTTagCompound compound = CompressedStreamTools.readCompressed(new FileInputStream(SAVE_FILE));
            for (Object keyObj : compound.func_150296_c()) {
                String key = (String) keyObj;                UUID uuid = UUID.fromString(key);
                NBTTagList itemList = compound.getTagList(key, 10);
                List<ItemStack> stacks = new ArrayList<>();
                for (int i = 0; i < itemList.tagCount(); i++) {
                    ItemStack stack = ItemStack.loadItemStackFromNBT(itemList.getCompoundTagAt(i));
                    if (stack != null) stacks.add(stack);
                }
                if (!stacks.isEmpty()) {
                    pendingHeads.put(uuid, stacks);
                }
            }
        } catch (Exception e) {
            System.err.println("[BountyLoginHandler] Failed to load pending heads:");
            e.printStackTrace();
        }
    }
}

package com.sown.outerrim.commands;

import com.sown.outerrim.registry.ItemRegister;
import com.sown.outerrim.OuterRim;
import com.sown.outerrim.items.ItemCustomRecord;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.play.server.S29PacketSoundEffect;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;

import java.util.*;

public class CommandMusic extends CommandBase {
    private static final float RANGE = 100.0F;
    private static final String PREFIX = "outerrim:records.";

    private static final List<String> SUBCOMMANDS = Arrays.asList("list", "play", "stop");
    private static final List<String> KEYWORDS    = Arrays.asList("all", "self", "nearby");

    private static final Map<String, String> CUSTOM_NAMES = new HashMap<>();
    static {
        CUSTOM_NAMES.put("anakin_vs_obi_wan", "Anakin vs. Obi-Wan");
        CUSTOM_NAMES.put("niamos",           "Niamos!");
    }

    @Override public String getCommandName() { return "music"; }
    @Override public List<String> getCommandAliases() { return Collections.singletonList("msc"); }
    @Override public String getCommandUsage(ICommandSender s) {
        return "/music <list|play|stop> [track] [all|self|nearby|user1,user2...]";
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) {
        if (args.length == 0) throw new WrongUsageException(getCommandUsage(sender));
        String sub = args[0].toLowerCase(Locale.ROOT);

        switch (sub) {
            case "list": handleList(sender); break;
            case "play": handlePlay(sender, args); break;
            case "stop": handleStop(sender, args); break;
            default: throw new WrongUsageException(getCommandUsage(sender));
        }
    }

    private void handleList(ICommandSender sender) {
        List<String> recs = new ArrayList<>();
        for (Map.Entry<String, net.minecraft.item.Item> e : ItemRegister.getRegisteredItems().entrySet()) {
            if (e.getValue() instanceof ItemCustomRecord) recs.add(e.getKey());
        }
        Collections.sort(recs);

        if (recs.isEmpty()) {
            sender.addChatMessage(new ChatComponentText(
                EnumChatFormatting.RED + "No tracks registered."
            ));
            return;
        }

        sender.addChatMessage(new ChatComponentText(
            EnumChatFormatting.GOLD + "Available tracks:"
        ));
        for (String key : recs) {
            String display = CUSTOM_NAMES.getOrDefault(key, prettyName(key));
            sender.addChatMessage(new ChatComponentText(
                EnumChatFormatting.GOLD + "- " + display
            ));
        }
    }

    private void handlePlay(ICommandSender sender, String[] args) {
        if (args.length < 2) throw new WrongUsageException("Usage: /music play <track_key> [targets]");

        String key = args[1].toLowerCase(Locale.ROOT);
        if (!(ItemRegister.getRegisteredItems().get(key) instanceof ItemCustomRecord)) {
            sender.addChatMessage(new ChatComponentText(
                EnumChatFormatting.RED + "Unknown track: " + key
            ));
            return;
        }

        String sound = PREFIX + key;
        List<EntityPlayerMP> players = resolveTargets(sender, args, 2);
        for (EntityPlayerMP p : players) {
            p.playerNetServerHandler.sendPacket(new S29PacketSoundEffect(sound, p.posX, p.posY, p.posZ, 10000F, 1F));
        }
        int c = players.size();
        sender.addChatMessage(new ChatComponentText(
            EnumChatFormatting.GREEN +
            "Played \"" + CUSTOM_NAMES.getOrDefault(key, prettyName(key)) + "\" to " + c + " " + (c==1?"player":"players")
        ));
    }

    private void handleStop(ICommandSender sender, String[] args) {
        List<EntityPlayerMP> players = resolveTargets(sender, args, 1);

        for (EntityPlayerMP p : players) {
            p.playerNetServerHandler.sendPacket(new S29PacketSoundEffect(
                "records.stop", p.posX, p.posY, p.posZ, 10000F, 1F
            ));
        }

        int c = players.size();
        sender.addChatMessage(new ChatComponentText(
            EnumChatFormatting.YELLOW +
            "Stopped music for " + c + " " + (c==1?"player":"players")
        ));
    }

    private List<EntityPlayerMP> resolveTargets(ICommandSender sender, String[] args, int idx) {
        EntityPlayerMP exec = getCommandSenderAsPlayer(sender);
        World w = exec.worldObj;
        Set<EntityPlayerMP> set = new LinkedHashSet<>();

        if (args.length > idx) {
            String t = args[idx].toLowerCase(Locale.ROOT);
            if ("all".equals(t)) {
                for (Object o : w.playerEntities) if (o instanceof EntityPlayerMP) set.add((EntityPlayerMP)o);
            } else if ("self".equals(t)) {
                set.add(exec);
            } else if ("nearby".equals(t)) {
                for (Object o : w.playerEntities) if (o instanceof EntityPlayerMP) {
                    EntityPlayerMP p = (EntityPlayerMP)o;
                    if (p.dimension==exec.dimension && p.getDistanceToEntity(exec)<=RANGE) set.add(p);
                }
            } else {
                for (String name : args[idx].split(",")) {
                    EntityPlayerMP p = MinecraftServer.getServer()
                        .getConfigurationManager().func_152612_a(name.trim());
                    if (p!=null) set.add(p);
                }
            }
        } else {
            for (Object o : w.playerEntities) if (o instanceof EntityPlayerMP) {
                EntityPlayerMP p = (EntityPlayerMP)o;
                if (p.dimension==exec.dimension && p.getDistanceToEntity(exec)<=RANGE) set.add(p);
            }
        }

        return new ArrayList<>(set);
    }

    private String prettyName(String raw) {
        String spaced = raw.replace("_vs_", " vs. ").replace("_", " ");
        String[] parts = spaced.split(" ");
        for (int i = 0; i < parts.length; i++) {
            if (parts[i].length() > 0) {
                parts[i] = parts[i].substring(0,1).toUpperCase()
                         + parts[i].substring(1).toLowerCase();
            }
        }
        return String.join(" ", parts);
    }

    @Override
    public List<String> addTabCompletionOptions(ICommandSender sender, String[] args) {
        if (args.length==1) {
            return getListOfStringsMatchingLastWord(args, SUBCOMMANDS.toArray(new String[0]));
        }
        if (args.length==2 && "play".equalsIgnoreCase(args[0])) {
            Set<String> recs = new TreeSet<>();
            for (Map.Entry<String, net.minecraft.item.Item> e : ItemRegister.getRegisteredItems().entrySet()) {
                if (e.getValue() instanceof ItemCustomRecord) recs.add(e.getKey());
            }
            return getListOfStringsMatchingLastWord(args, recs.toArray(new String[0]));
        }
        if (args.length>=2 && ("play".equalsIgnoreCase(args[0])||"stop".equalsIgnoreCase(args[0]))) {
            if (args.length==2) {
                return getListOfStringsMatchingLastWord(args, KEYWORDS.toArray(new String[0]));
            } else {
                List<String> opts = new ArrayList<>(KEYWORDS);
                opts.addAll(Arrays.asList(MinecraftServer.getServer().getAllUsernames()));
                return getListOfStringsMatchingLastWord(args, opts.toArray(new String[0]));
            }
        }
        return Collections.emptyList();
    }

    @Override public boolean canCommandSenderUseCommand(ICommandSender sender){return true;}
    @Override public int compareTo(Object o){return 0;}
}

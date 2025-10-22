package com.sown.outerrim.commands;

import com.sown.util.structures.StructureLoader;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;

import java.util.*;

public class CommandScanStructure implements ICommand {
    private static final Map<String, int[]> posMap = new HashMap<>();

    @Override
    public String getCommandName() {
        return "scanstructure";
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "/scanstructure pos1 | pos2 | save <ClassName>";
    }

    @Override
    public List<String> getCommandAliases() {
        return Collections.singletonList("sscan");
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) {
        if (!(sender instanceof EntityPlayer)) return;
        EntityPlayer player = (EntityPlayer) sender;
        String key = player.getUniqueID().toString();
        World world = player.worldObj;

        if (args.length < 1) {
            sender.addChatMessage(new ChatComponentText("Usage: /scanstructure pos1 | pos2 | save <ClassName>"));
            return;
        }

        switch (args[0].toLowerCase(Locale.ROOT)) {
            case "pos1":
                posMap.put(key + "_1", new int[]{(int)player.posX, (int)player.posY, (int)player.posZ});
                sender.addChatMessage(new ChatComponentText("Position 1 set."));
                break;

            case "pos2":
                posMap.put(key + "_2", new int[]{(int)player.posX, (int)player.posY, (int)player.posZ});
                sender.addChatMessage(new ChatComponentText("Position 2 set."));
                break;

            case "save":
                if (args.length < 2) {
                    sender.addChatMessage(new ChatComponentText("Usage: /scanstructure save <ClassName>"));
                    return;
                }
                int[] p1 = posMap.get(key + "_1");
                int[] p2 = posMap.get(key + "_2");
                if (p1 == null || p2 == null) {
                    sender.addChatMessage(new ChatComponentText("You must set both pos1 and pos2 first."));
                    return;
                }
                String className = args[1];
                StructureLoader.init();
                StructureLoader.saveStructureAsClass(
                    world,
                    p1[0], p1[1], p1[2],
                    p2[0], p2[1], p2[2],
                    className,
                    "com.sown.outerrim.generated"
                );
                sender.addChatMessage(new ChatComponentText(
                    "Structure class generated: " +
                    "config/outerrim_structures_code/com/sown/outerrim/generated/" +
                    className + ".java"
                ));
                break;

            default:
                sender.addChatMessage(new ChatComponentText("Unknown subcommand."));
        }
    }

    @Override
    public boolean canCommandSenderUseCommand(ICommandSender sender) {
        return true;
    }

    @Override
    public List<String> addTabCompletionOptions(ICommandSender sender, String[] args) {
        return null;
    }

    @Override
    public boolean isUsernameIndex(String[] args, int index) {
        return false;
    }

    @Override
    public int compareTo(Object o) {
        return 0;
    }
}

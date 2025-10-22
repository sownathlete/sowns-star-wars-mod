package com.sown.outerrim.bounty;

import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.EnumChatFormatting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CommandRemoveBounty implements ICommand {

    @Override
    public String getCommandName() {
        return "removebounty";
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "/removebounty <player>";
    }

    @Override
    public List getCommandAliases() {
        return Arrays.asList("cancelbounty", "revokebounty");
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) {
        if (!(sender instanceof EntityPlayerMP)) return;

        EntityPlayerMP player = (EntityPlayerMP) sender;

        if (args.length != 1) {
            ChatComponentText msg = new ChatComponentText("Usage: /removebounty <player>");
            msg.setChatStyle(new ChatStyle().setColor(EnumChatFormatting.RED));
            player.addChatMessage(msg);
            return;
        }

        String targetName = args[0];
        BountyManager.removeBounty(player, targetName);
    }

    @Override
    public boolean canCommandSenderUseCommand(ICommandSender sender) {
        return true;
    }

    @Override
    public List<String> addTabCompletionOptions(ICommandSender sender, String[] args) {
        if (args.length == 1) {
            return Arrays.asList(MinecraftServer.getServer().getAllUsernames());
        }
        return null;
    }

    @Override
    public boolean isUsernameIndex(String[] args, int index) {
        return index == 0;
    }

    @Override
    public int compareTo(Object o) {
        return 0;
    }
}

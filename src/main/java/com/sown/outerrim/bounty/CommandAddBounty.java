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

public class CommandAddBounty implements ICommand {

    @Override
    public String getCommandName() {
        return "addbounty";
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "/addbounty <player> <amount>";
    }

    @Override
    public List getCommandAliases() {
        return new ArrayList<>();
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) {
        if (!(sender instanceof EntityPlayerMP)) return;
        EntityPlayerMP player = (EntityPlayerMP) sender;

        if (args.length != 2) {
            ChatComponentText msg = new ChatComponentText("Usage: /addbounty <player> <amount>");
            msg.setChatStyle(new ChatStyle().setColor(EnumChatFormatting.RED));
            player.addChatMessage(msg);
            return;
        }

        String targetName = args[0];
        int amount;
        try {
            amount = Integer.parseInt(args[1]);
        } catch (NumberFormatException e) {
            ChatComponentText msg = new ChatComponentText("Amount must be a number.");
            msg.setChatStyle(new ChatStyle().setColor(EnumChatFormatting.RED));
            player.addChatMessage(msg);
            return;
        }

        if (amount <= 0) {
            ChatComponentText msg = new ChatComponentText("Amount must be positive.");
            msg.setChatStyle(new ChatStyle().setColor(EnumChatFormatting.RED));
            player.addChatMessage(msg);
            return;
        }

        BountyManager.increaseBounty(player, targetName, amount);
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

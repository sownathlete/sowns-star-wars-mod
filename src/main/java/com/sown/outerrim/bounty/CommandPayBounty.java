package com.sown.outerrim.bounty;

import com.sown.outerrim.bounty.BountyManager;
import com.sown.outerrim.bounty.BountyManager.Bounty;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;

import java.util.Collections;
import java.util.List;

public class CommandPayBounty extends CommandBase {

    @Override
    public String getCommandName() {
        return "paybounty";
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "/paybounty - Pay off the bounty on yourself.";
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) {
        if (!(sender instanceof EntityPlayerMP)) return;

        EntityPlayerMP player = (EntityPlayerMP) sender;
        Bounty bounty = BountyManager.getBounty(player);

        if (bounty == null) {
            sendError(player, "You do not have a bounty on your head.");
            return;
        }

        int amount = bounty.getAmount();
        boolean success = BountyManager.removeCreditsFromInventory(player, amount);

        if (!success) {
            sendError(player, "You don't have enough credits to pay off your bounty (" + amount + ").");
            return;
        }

        BountyManager.clearBounty(player);
        sendSuccess(player, "You have paid off your bounty of " + amount + " credits.");
        BountyManager.broadcast(player.getCommandSenderName() + " has paid off their bounty.", EnumChatFormatting.YELLOW);
    }

    @Override
    public boolean canCommandSenderUseCommand(ICommandSender sender) {
        return true;
    }

    @Override
    public List<String> addTabCompletionOptions(ICommandSender sender, String[] args) {
        return Collections.emptyList();
    }

    private void sendError(EntityPlayerMP player, String msg) {
        ChatComponentText chat = new ChatComponentText(msg);
        chat.getChatStyle().setColor(EnumChatFormatting.RED);
        player.addChatMessage(chat);
    }

    private void sendSuccess(EntityPlayerMP player, String msg) {
        ChatComponentText chat = new ChatComponentText(msg);
        chat.getChatStyle().setColor(EnumChatFormatting.GREEN);
        player.addChatMessage(chat);
    }
}

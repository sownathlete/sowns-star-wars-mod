package com.sown.outerrim.commands;

import com.sown.outerrim.OuterRim;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.PlayerNotFoundException;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;

import java.util.List;

public class CommandUnfreeze extends CommandBase {

    @Override
    public String getCommandName() {
        return "unfreeze";
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "/unfreeze <player>";
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) {
        if (args.length != 1) {
            ChatComponentText errorMessage = new ChatComponentText(
                "Invalid arguments. Usage: " + getCommandUsage(sender)
            );
            errorMessage.getChatStyle().setColor(EnumChatFormatting.RED);
            sender.addChatMessage(errorMessage);
            return;
        }

        EntityPlayer targetPlayer;
        try {
            targetPlayer = getPlayer(sender, args[0]);
        } catch (PlayerNotFoundException e) {
            ChatComponentText errorMessage = new ChatComponentText(
                "Player not found: " + args[0]
            );
            errorMessage.getChatStyle().setColor(EnumChatFormatting.RED);
            sender.addChatMessage(errorMessage);
            return;
        }

        OuterRim.frozenPlayers.remove(targetPlayer.getUniqueID());

        targetPlayer.capabilities.disableDamage = false;
        targetPlayer.capabilities.allowEdit   = true;
        targetPlayer.capabilities.isFlying    = false;
        targetPlayer.noClip                   = false;

        targetPlayer.clearActivePotions();
        targetPlayer.addPotionEffect(new PotionEffect(
            Potion.blindness.getId(),
            30 * 60 * 20,
            0,
            false
        ));

        ChatComponentText successMessage = new ChatComponentText(
            "Player " + args[0] + " has been unfrozen."
        );
        successMessage.getChatStyle().setColor(EnumChatFormatting.GREEN);
        sender.addChatMessage(successMessage);
    }

    @Override
    public int getRequiredPermissionLevel() {
        return 2;
    }

    @Override
    public List<String> addTabCompletionOptions(ICommandSender sender, String[] args) {
        if (args.length == 1) {
            return getListOfStringsMatchingLastWord(args, MinecraftServer.getServer().getAllUsernames());
        }
        return null;
    }
}

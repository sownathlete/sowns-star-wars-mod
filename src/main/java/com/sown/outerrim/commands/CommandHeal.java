package com.sown.outerrim.commands;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.PotionEffect;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;

public class CommandHeal extends CommandBase {

    @Override
    public String getCommandName() {
        return "heal";
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "/heal - Fully restores health, hunger, and removes all effects (singleplayer only).";
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) {
        if (!MinecraftServer.getServer().isSinglePlayer()) {
            ChatComponentText errorMessage = new ChatComponentText("This command is only available in singleplayer.");
            errorMessage.getChatStyle().setColor(EnumChatFormatting.RED);
            sender.addChatMessage(errorMessage);
            return;
        }

        if (!(sender instanceof EntityPlayer)) {
            ChatComponentText errorMessage = new ChatComponentText("This command can only be used by a player.");
            errorMessage.getChatStyle().setColor(EnumChatFormatting.RED);
            sender.addChatMessage(errorMessage);
            return;
        }

        EntityPlayer player = (EntityPlayer) sender;

        player.setHealth(player.getMaxHealth());
        player.getFoodStats().addStats(20, 20.0F);

        for (Object effectObj : player.getActivePotionEffects()) {
            if (effectObj instanceof PotionEffect) {
                PotionEffect effect = (PotionEffect) effectObj;
                player.removePotionEffect(effect.getPotionID());
            }
        }

        ChatComponentText successMessage = new ChatComponentText("You have been fully healed and cleansed of all effects.");
        successMessage.getChatStyle().setColor(EnumChatFormatting.GREEN);
        sender.addChatMessage(successMessage);
    }

    @Override
    public int getRequiredPermissionLevel() {
        return 2;
    }
}

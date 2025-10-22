package com.sown.outerrim.commands;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;

import java.lang.reflect.Field;

public class CommandVanish extends CommandBase {

    @Override
    public String getCommandName() {
        return "v";
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "/v - Toggles complete invisibility with no particles (singleplayer only).";
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) {
        if (!(sender instanceof EntityPlayer)) {
            ChatComponentText errorMessage = new ChatComponentText("This command can only be used by a player.");
            errorMessage.getChatStyle().setColor(EnumChatFormatting.RED);
            sender.addChatMessage(errorMessage);
            return;
        }

        EntityPlayer player = (EntityPlayer) sender;

        if (player.isPotionActive(Potion.invisibility)) {
            player.removePotionEffect(Potion.invisibility.getId());
            ChatComponentText successMessage = new ChatComponentText("Invisibility toggled OFF.");
            successMessage.getChatStyle().setColor(EnumChatFormatting.GREEN);
            sender.addChatMessage(successMessage);
        } else {
            PotionEffect invisibility = new PotionEffect(Potion.invisibility.getId(), Integer.MAX_VALUE, 0);
            player.addPotionEffect(invisibility);

            try {
                Field f = PotionEffect.class.getDeclaredField("showParticles");
                f.setAccessible(true);
                f.setBoolean(invisibility, false);
            } catch (Exception e) {
                e.printStackTrace();
            }

            ChatComponentText successMessage = new ChatComponentText("Invisibility toggled ON.");
            successMessage.getChatStyle().setColor(EnumChatFormatting.GREEN);
            sender.addChatMessage(successMessage);
        }
    }

    @Override
    public int getRequiredPermissionLevel() {
        return 2;
    }
}

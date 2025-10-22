package com.sown.outerrim.commands;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;

public class CommandFly extends CommandBase {

    @Override
    public String getCommandName() {
        return "fly";
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "/fly - Toggles the ability to fly in survival mode (singleplayer only).";
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

        if (player.capabilities.allowFlying) {
            player.capabilities.allowFlying = false;
            player.capabilities.isFlying = false;
            player.sendPlayerAbilities();
            ChatComponentText successMessage = new ChatComponentText("Fly mode disabled.");
            successMessage.getChatStyle().setColor(EnumChatFormatting.GREEN);
            player.addChatMessage(successMessage);
        } else {
            player.capabilities.allowFlying = true;
            player.sendPlayerAbilities();
            ChatComponentText successMessage = new ChatComponentText("Fly mode enabled.");
            successMessage.getChatStyle().setColor(EnumChatFormatting.GREEN);
            player.addChatMessage(successMessage);
        }
    }

    @Override
    public int getRequiredPermissionLevel() {
        return 2;
    }
}

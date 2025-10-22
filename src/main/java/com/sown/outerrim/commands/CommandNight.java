package com.sown.outerrim.commands;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;

public class CommandNight extends CommandBase {

    @Override
    public String getCommandName() {
        return "night";
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "/night - Sets the time to nighttime (singleplayer only).";
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) {
        if (!MinecraftServer.getServer().isSinglePlayer()) {
            ChatComponentText errorMessage = new ChatComponentText("This command is only available in singleplayer.");
            errorMessage.getChatStyle().setColor(EnumChatFormatting.RED);
            sender.addChatMessage(errorMessage);
            return;
        }

        sender.getEntityWorld().setWorldTime(13000);

        ChatComponentText successMessage = new ChatComponentText("Time set to night.");
        successMessage.getChatStyle().setColor(EnumChatFormatting.GREEN);
        sender.addChatMessage(successMessage);
    }

    @Override
    public int getRequiredPermissionLevel() {
        return 2;
    }
}

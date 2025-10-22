package com.sown.outerrim.commands;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;

public class CommandDay extends CommandBase {

    @Override
    public String getCommandName() {
        return "day";
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "/day - Sets the time to daytime in the current dimension.";
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) {
        if (!MinecraftServer.getServer().isSinglePlayer() && !MinecraftServer.getServer().isDedicatedServer()) {
            ChatComponentText errorMessage = new ChatComponentText("This command is only available in singleplayer or with server permissions.");
            errorMessage.getChatStyle().setColor(EnumChatFormatting.RED);
            sender.addChatMessage(errorMessage);
            return;
        }

        World world = sender.getEntityWorld();
        if (world != null) {
            world.setWorldTime(1000); // Set time to daytime in the current dimension
            ChatComponentText successMessage = new ChatComponentText("Time set to day.");
            successMessage.getChatStyle().setColor(EnumChatFormatting.GREEN);
            sender.addChatMessage(successMessage);
        } else {
            ChatComponentText errorMessage = new ChatComponentText("Error: Could not determine current dimension.");
            errorMessage.getChatStyle().setColor(EnumChatFormatting.RED);
            sender.addChatMessage(errorMessage);
        }
    }

    @Override
    public int getRequiredPermissionLevel() {
        return 2; // Requires operator or cheats enabled
    }
}

package com.sown.outerrim.commands;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.WorldSettings;

public class CommandGMC extends CommandBase {

    @Override
    public String getCommandName() {
        return "gmc";
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "/gmc - Change your game mode to Creative (singleplayer only)";
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) {
        if (!MinecraftServer.getServer().isSinglePlayer()) {
            ChatComponentText errorMessage = new ChatComponentText("This command is only available in singleplayer.");
            errorMessage.getChatStyle().setColor(EnumChatFormatting.RED);
            sender.addChatMessage(errorMessage);
            return;
        }

        if (!(sender instanceof EntityPlayerMP)) {
            ChatComponentText errorMessage = new ChatComponentText("This command can only be used by a player.");
            errorMessage.getChatStyle().setColor(EnumChatFormatting.RED);
            sender.addChatMessage(errorMessage);
            return;
        }

        EntityPlayerMP player = (EntityPlayerMP) sender;
        player.setGameType(WorldSettings.GameType.CREATIVE);
    }

    @Override
    public int getRequiredPermissionLevel() {
        return 2;
    }
}

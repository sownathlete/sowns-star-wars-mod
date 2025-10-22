package com.sown.outerrim.commands;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.WorldSettings;

import java.util.List;

public class CommandGameMode extends CommandBase {

    @Override
    public String getCommandName() {
        return "gm";
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "/gm [c|s|1|0] or /gmc, /gms - Change your game mode (singleplayer only)";
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

        String command = getCommandName();
        if (command.equals("gmc")) {
            setGameMode(player, WorldSettings.GameType.CREATIVE);
            return;
        } else if (command.equals("gms")) {
            setGameMode(player, WorldSettings.GameType.SURVIVAL);
            return;
        }

        if (args.length == 0) {
            ChatComponentText usageMessage = new ChatComponentText("Usage: " + this.getCommandUsage(sender));
            usageMessage.getChatStyle().setColor(EnumChatFormatting.RED);
            sender.addChatMessage(usageMessage);
            return;
        }

        String mode = args[0];
        if (mode.equalsIgnoreCase("c") || mode.equals("1")) {
            setGameMode(player, WorldSettings.GameType.CREATIVE);
        } else if (mode.equalsIgnoreCase("s") || mode.equals("0")) {
            setGameMode(player, WorldSettings.GameType.SURVIVAL);
        } else {
            ChatComponentText errorMessage = new ChatComponentText("Invalid game mode. Use 'c', 's', '1', or '0'.");
            errorMessage.getChatStyle().setColor(EnumChatFormatting.RED);
            sender.addChatMessage(errorMessage);
        }
    }

    private void setGameMode(EntityPlayerMP player, WorldSettings.GameType gameType) {
        player.setGameType(gameType);
    }

    @Override
    public int getRequiredPermissionLevel() {
        return 2;
    }

    @Override
    public List<String> addTabCompletionOptions(ICommandSender sender, String[] args) {
        if (args.length == 1) {
            return getListOfStringsMatchingLastWord(args, "c", "s", "1", "0");
        }
        return null;
    }
}

package com.sown.outerrim.commands;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;

public class CommandSpeed extends CommandBase {

    private static final float DEFAULT_WALK_SPEED = 0.1F;
    private static final float DEFAULT_FLY_SPEED = 0.05F;

    @Override
    public String getCommandName() {
        return "speed";
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "/speed <1-10|reset> - Adjusts player speed (singleplayer only).";
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) {
        if (!MinecraftServer.getServer().isSinglePlayer()) {
            sendMessage(sender, EnumChatFormatting.RED, "This command is only available in singleplayer.");
            return;
        }

        if (!(sender instanceof EntityPlayer)) {
            sendMessage(sender, EnumChatFormatting.RED, "This command can only be used by a player.");
            return;
        }

        EntityPlayer player = (EntityPlayer) sender;

        if (args.length != 1) {
            sendMessage(player, EnumChatFormatting.RED, "Usage: /speed <1-10|reset>");
            return;
        }

        if (args[0].equalsIgnoreCase("reset")) {
            player.capabilities.setPlayerWalkSpeed(DEFAULT_WALK_SPEED);
            player.capabilities.setFlySpeed(DEFAULT_FLY_SPEED);
            player.sendPlayerAbilities();
            sendMessage(player, EnumChatFormatting.GREEN, "Speed reset to default.");
            return;
        }

        try {
            int speedLevel = Integer.parseInt(args[0]);
            if (speedLevel < 1 || speedLevel > 10) {
                sendMessage(player, EnumChatFormatting.RED, "Speed must be between 1 and 10.");
                return;
            }

            float walkSpeed = DEFAULT_WALK_SPEED + (speedLevel - 1) * 0.015F;
            float flySpeed = DEFAULT_FLY_SPEED + (speedLevel - 1) * 0.01F;

            player.capabilities.setPlayerWalkSpeed(walkSpeed);
            player.capabilities.setFlySpeed(flySpeed);
            player.sendPlayerAbilities();

            sendMessage(player, EnumChatFormatting.GREEN,
                "Speed set to " + speedLevel + (player.capabilities.isFlying ? " (fly mode)" : " (walk mode)") + ".");
        } catch (NumberFormatException e) {
            sendMessage(player, EnumChatFormatting.RED, "Invalid input! Use a number between 1 and 10 or 'reset'.");
        }
    }

    private void sendMessage(ICommandSender sender, EnumChatFormatting color, String message) {
        ChatComponentText msg = new ChatComponentText(message);
        msg.getChatStyle().setColor(color);
        sender.addChatMessage(msg);
    }

    @Override
    public int getRequiredPermissionLevel() {
        return 2;
    }
}

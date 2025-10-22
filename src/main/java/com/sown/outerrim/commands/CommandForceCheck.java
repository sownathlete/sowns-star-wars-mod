package com.sown.outerrim.commands;

import java.util.Random;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.WorldServer;
import com.sown.outerrim.world.TransferDim;

public class CommandForceCheck extends CommandBase {

    @Override
    public String getCommandName() {
        return "force_check";
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return EnumChatFormatting.RED + "/force_check <player> <yes_x> <yes_y> <yes_z> <yes_dim> <no_x> <no_y> <no_z> <no_dim>";
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) {
        if (args.length != 9) {
            throw new WrongUsageException(EnumChatFormatting.RED + "Usage: " + getCommandUsage(sender));
        }

        EntityPlayerMP player = getPlayer(sender, args[0]);
        if (player == null) {
            sender.addChatMessage(new ChatComponentText(EnumChatFormatting.RED + "Player not found."));
            return;
        }

        int yesX = parseInt(sender, args[1]);
        int yesY = parseInt(sender, args[2]);
        int yesZ = parseInt(sender, args[3]);
        int yesDim = parseInt(sender, args[4]);

        int noX = parseInt(sender, args[5]);
        int noY = parseInt(sender, args[6]);
        int noZ = parseInt(sender, args[7]);
        int noDim = parseInt(sender, args[8]);

        Random rand = new Random();
        boolean isForceSensitive = rand.nextInt(10) < 1;

        int targetX = isForceSensitive ? yesX : noX;
        int targetY = isForceSensitive ? yesY : noY;
        int targetZ = isForceSensitive ? yesZ : noZ;
        int targetDim = isForceSensitive ? yesDim : noDim;

        MinecraftServer server = MinecraftServer.getServer();
        WorldServer targetWorld = server.worldServerForDimension(targetDim);
        if (targetWorld == null) {
            sender.addChatMessage(new ChatComponentText(EnumChatFormatting.RED + "Invalid dimension ID: " + targetDim));
            return;
        }

        if (player.dimension != targetDim) {
            new TransferDim(targetWorld).teleport(player);
        }

        player.setPositionAndUpdate(targetX + 0.5, targetY, targetZ + 0.5);
        targetWorld.updateEntityWithOptionalForce(player, false);

        if (isForceSensitive) {
            sender.addChatMessage(new ChatComponentText(EnumChatFormatting.AQUA + player.getCommandSenderName() +
                    EnumChatFormatting.YELLOW + " has awakened to the Force!" +
                    EnumChatFormatting.GREEN + " Teleported to (" + targetX + ", " + targetY + ", " + targetZ + ") in dimension " + targetDim + "."));
            
            player.addChatMessage(new ChatComponentText(EnumChatFormatting.BOLD + "" + EnumChatFormatting.AQUA + "You feel the power of the Force flowing through you!"));
        } else {
            sender.addChatMessage(new ChatComponentText(EnumChatFormatting.GRAY + player.getCommandSenderName() +
                    EnumChatFormatting.RED + " is not Force-sensitive." +
                    EnumChatFormatting.GREEN + " Teleported to (" + targetX + ", " + targetY + ", " + targetZ + ") in dimension " + targetDim + "."));
            
            player.addChatMessage(new ChatComponentText(EnumChatFormatting.BOLD + "" + EnumChatFormatting.GRAY + "You feel nothing..."));
        }
    }

    @Override
    public int getRequiredPermissionLevel() {
        return 2;
    }
}

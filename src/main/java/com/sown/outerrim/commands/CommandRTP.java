package com.sown.outerrim.commands;

import java.util.List;
import java.util.Random;

import com.sown.outerrim.world.TransferDim;
import net.minecraft.block.Block;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.WorldServer;

public class CommandRTP extends CommandBase {

    @Override
    public String getCommandName() {
        return "rtp";
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return EnumChatFormatting.RED + "Usage: /rtp [player] [x] [z] [radius] [dimension]";
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) {
        EntityPlayerMP player;
        int centerX = 0, centerZ = 0, radius = 1000, targetDim;
        int argOffset = 0;

        if (args.length > 0 && getPlayer(sender, args[0]) != null) {
            player = getPlayer(sender, args[0]);
            argOffset = 1;
        } else {
            if (!(sender instanceof EntityPlayerMP)) {
                sender.addChatMessage(new ChatComponentText(EnumChatFormatting.RED + "Console must specify a player."));
                return;
            }
            player = (EntityPlayerMP) sender;
        }

        centerX = (args.length > argOffset) ? parseIntBounded(sender, args[argOffset], Integer.MIN_VALUE, Integer.MAX_VALUE) : 0;
        centerZ = (args.length > argOffset + 1) ? parseIntBounded(sender, args[argOffset + 1], Integer.MIN_VALUE, Integer.MAX_VALUE) : 0;
        radius = (args.length > argOffset + 2) ? parseIntBounded(sender, args[argOffset + 2], 1, Integer.MAX_VALUE) : 1000;
        targetDim = (args.length > argOffset + 3) ? parseInt(sender, args[argOffset + 3]) : player.dimension;

        MinecraftServer server = MinecraftServer.getServer();
        WorldServer targetWorld = server.worldServerForDimension(targetDim);
        if (targetWorld == null) {
            sender.addChatMessage(new ChatComponentText(EnumChatFormatting.RED + "Invalid dimension ID!"));
            return;
        }

        Random rand = new Random();
        int tries = 0;
        boolean foundSafeLocation = false;
        int x = 0, y = 0, z = 0;

        while (tries < 20) {
            x = centerX + rand.nextInt(radius * 2) - radius;
            z = centerZ + rand.nextInt(radius * 2) - radius;
            y = targetWorld.getTopSolidOrLiquidBlock(x, z) - 1;

            Block block = targetWorld.getBlock(x, y, z);
            if (!block.getMaterial().isLiquid()) {
                foundSafeLocation = true;
                break;
            }
            tries++;
        }

        if (!foundSafeLocation) {
            sender.addChatMessage(new ChatComponentText(EnumChatFormatting.RED + "Failed to find a safe teleport location."));
            return;
        }

        if (player.dimension != targetDim) {
            new TransferDim(targetWorld).teleport(player);
        }

        player.setPositionAndUpdate(x + 0.5, y + 1, z + 0.5);
        targetWorld.updateEntityWithOptionalForce(player, false);

        sender.addChatMessage(new ChatComponentText(EnumChatFormatting.GREEN + "Teleported " 
                + EnumChatFormatting.YELLOW + player.getCommandSenderName() 
                + EnumChatFormatting.GREEN + " to " 
                + EnumChatFormatting.AQUA + x + ", " + y + ", " + z 
                + EnumChatFormatting.GREEN + " in dimension " + EnumChatFormatting.DARK_PURPLE + targetDim));
    }

    @Override
    public List<String> addTabCompletionOptions(ICommandSender sender, String[] args) {
        if (args.length == 1) {
            return getListOfStringsMatchingLastWord(args, MinecraftServer.getServer().getAllUsernames());
        }
        return null;
    }

    @Override
    public int getRequiredPermissionLevel() {
        return 2;
    }
}

package com.sown.outerrim.commands;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IChatComponent;
import net.minecraft.init.Items;
import net.minecraft.server.MinecraftServer;

import java.util.ArrayList;
import java.util.List;

public class CommandSkull extends CommandBase {

    @Override
    public String getCommandName() {
        return "skull";
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "/skull <playerName> - Gives you the skull of the specified player (singleplayer only).";
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) {
        if (!(sender instanceof EntityPlayerMP)) {
            sender.addChatMessage(new ChatComponentText("This command can only be used by a player."));
            return;
        }

        if (args.length != 1) {
            ChatComponentText errorMessage = new ChatComponentText("Usage: " + getCommandUsage(sender));
            errorMessage.getChatStyle().setColor(EnumChatFormatting.RED);
            sender.addChatMessage(errorMessage);
            return;
        }

        EntityPlayerMP player = (EntityPlayerMP) sender;
        String targetPlayerName = args[0];

        ItemStack playerSkull = new ItemStack(Items.skull, 1, 3);
        NBTTagCompound skullTag = new NBTTagCompound();

        skullTag.setString("SkullOwner", targetPlayerName);
        playerSkull.setTagCompound(skullTag);

        NBTTagCompound displayTag = new NBTTagCompound();
        displayTag.setString("Name", targetPlayerName + "'s Head");
        playerSkull.getTagCompound().setTag("display", displayTag);

        if (!player.inventory.addItemStackToInventory(playerSkull)) {
            sender.addChatMessage(new ChatComponentText("Your inventory is full. The item could not be added."));
        } else {
            sender.addChatMessage(new ChatComponentText("You received the skull of player: " + targetPlayerName));
        }
    }


    @Override
    public int getRequiredPermissionLevel() {
        return 2;
    }

    @Override
    public List<String> addTabCompletionOptions(ICommandSender sender, String[] args) {
        if (args.length == 1) {
            return getListOfStringsMatchingLastWord(args, MinecraftServer.getServer().getAllUsernames());
        }
        return null;
    }
}

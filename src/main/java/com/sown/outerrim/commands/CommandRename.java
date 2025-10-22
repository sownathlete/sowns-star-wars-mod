package com.sown.outerrim.commands;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.init.Items;

import java.util.ArrayList;
import java.util.List;

public class CommandRename extends CommandBase {

    @Override
    public String getCommandName() {
        return "rename";
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
    	return "/rename <new name>, /rename reset, /rename lore <text>, /rename lore reset";
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) {
        if (!(sender instanceof EntityPlayerMP)) {
            sender.addChatMessage(new ChatComponentText(EnumChatFormatting.RED + "This command can only be used by a player."));
            return;
        }

        EntityPlayerMP player = (EntityPlayerMP) sender;
        ItemStack heldItem = player.getHeldItem();

        if (heldItem == null) {
            sender.addChatMessage(new ChatComponentText(EnumChatFormatting.RED + "You must be holding an item to rename it."));
            return;
        }

        if (args.length < 1) {
            throw new WrongUsageException(getCommandUsage(sender));
        }

        String commandType = args[0].toLowerCase();

        if (commandType.equals("lore")) {
            handleLoreCommand(sender, heldItem, args);
        } else {
            handleRenameCommand(sender, heldItem, args);
        }
    }

    private void handleRenameCommand(ICommandSender sender, ItemStack item, String[] args) {
        String inputName = String.join(" ", args).trim();

        if (inputName.equalsIgnoreCase("reset")) {
            if (item.hasTagCompound() && item.getTagCompound().hasKey("display")) {
                item.getTagCompound().removeTag("display");
            }
            sender.addChatMessage(new ChatComponentText(EnumChatFormatting.GREEN + "Item name restored to default."));
            return;
        }

        String newName = parseQuotedText(inputName);
        newName = "&r" + newName;
        newName = applyColorCodes(newName);

        if (!item.hasTagCompound()) {
            item.setTagCompound(new NBTTagCompound());
        }

        NBTTagCompound displayTag = new NBTTagCompound();
        displayTag.setString("Name", newName);
        item.getTagCompound().setTag("display", displayTag);

        if (item.getItem() == Items.spawn_egg) {
            setSpawnEggName(item, newName);
        }

        sender.addChatMessage(new ChatComponentText(EnumChatFormatting.GREEN + "Item renamed to: " + newName));
    }

    private void handleLoreCommand(ICommandSender sender, ItemStack item, String[] args) {
        if (args.length < 2) {
            throw new WrongUsageException(getCommandUsage(sender));
        }

        if (!item.hasTagCompound()) {
            item.setTagCompound(new NBTTagCompound());
        }

        NBTTagCompound displayTag = item.getTagCompound().getCompoundTag("display");
        item.getTagCompound().setTag("display", displayTag);

        if (args[1].equalsIgnoreCase("reset")) {
            if (displayTag.hasKey("Lore")) {
                displayTag.removeTag("Lore");
            }
            sender.addChatMessage(new ChatComponentText(EnumChatFormatting.GREEN + "Item description removed."));
            return;
        }

        NBTTagList loreList = new NBTTagList();
        for (int i = 1; i < args.length; i++) {
            String loreLine = parseQuotedText(args[i]);
            loreLine = applyColorCodes(loreLine);
            loreList.appendTag(new NBTTagString(loreLine));
        }

        displayTag.setTag("Lore", loreList);
        sender.addChatMessage(new ChatComponentText(EnumChatFormatting.GREEN + "Item description updated."));
    }

    private String parseQuotedText(String input) {
        if (input.startsWith("\"") && input.endsWith("\"") && input.length() > 1) {
            input = input.substring(1, input.length() - 1);
        }
        return input.replaceAll("\"\"", "\"");
    }

    private String applyColorCodes(String input) {
        return input.replaceAll("&([0-9a-fklmnor])", "\u00A7$1");
    }

    private void setSpawnEggName(ItemStack spawnEgg, String newName) {
        NBTTagCompound entityTag = new NBTTagCompound();
        entityTag.setString("CustomName", newName);

        if (!spawnEgg.hasTagCompound()) {
            spawnEgg.setTagCompound(new NBTTagCompound());
        }

        spawnEgg.getTagCompound().setTag("EntityTag", entityTag);
    }

    @Override
    public int getRequiredPermissionLevel() {
        return 2;
    }

    @Override
    public List<String> addTabCompletionOptions(ICommandSender sender, String[] args) {
        return null;
    }
}

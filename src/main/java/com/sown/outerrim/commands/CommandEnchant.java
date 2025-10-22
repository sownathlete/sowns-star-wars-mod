package com.sown.outerrim.commands;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import java.util.*;

public class CommandEnchant extends CommandBase {

    private static final Map<String, Enchantment> enchantmentMap = new HashMap<>();

    static {
        for (Enchantment enchant : Enchantment.enchantmentsList) {
            if (enchant != null) {
                enchantmentMap.put(enchant.getTranslatedName(1).replace(" ", "").toLowerCase(), enchant);
            }
        }
    }

    @Override
    public String getCommandName() {
        return "enchant";
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "/enchant <enchantment> <level>, /enchant [max|remove|clear]";
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) {
        if (!(sender instanceof EntityPlayerMP)) {
            sender.addChatMessage(new ChatComponentText(EnumChatFormatting.RED + "Only players can use this command."));
            return;
        }

        EntityPlayerMP player = (EntityPlayerMP) sender;
        ItemStack item = player.getHeldItem();

        if (item == null) {
            player.addChatMessage(new ChatComponentText(EnumChatFormatting.RED + "You must be holding an item to enchant."));
            return;
        }

        if (args.length == 0) {
            player.addChatMessage(new ChatComponentText(EnumChatFormatting.RED + getCommandUsage(sender)));
            return;
        }

        if (args[0].equalsIgnoreCase("clear")) {
            clearEnchantments(item);
            player.addChatMessage(new ChatComponentText(EnumChatFormatting.GREEN + "All enchantments have been removed."));
            return;
        }

        if (args[0].equalsIgnoreCase("max")) {
            applyMaxEnchantments(item, player);
            return;
        }

        if (args[0].equalsIgnoreCase("remove")) {
            if (args.length < 2) {
                player.addChatMessage(new ChatComponentText(EnumChatFormatting.RED + "Usage: /enchant remove <enchantment>"));
                return;
            }
            String enchantmentKey = formatEnchantmentKey(args[1]);
            if (!enchantmentMap.containsKey(enchantmentKey)) {
                player.addChatMessage(new ChatComponentText(EnumChatFormatting.RED + "Invalid enchantment name."));
                return;
            }
            removeEnchantment(item, enchantmentMap.get(enchantmentKey));
            player.addChatMessage(new ChatComponentText(EnumChatFormatting.GREEN + "Removed " + args[1] + " from item."));
            return;
        }

        if (args.length < 2 || !isNumeric(args[1])) {
            player.addChatMessage(new ChatComponentText(EnumChatFormatting.RED + getCommandUsage(sender)));
            return;
        }

        String enchantmentKey = formatEnchantmentKey(args[0]);
        if (!enchantmentMap.containsKey(enchantmentKey)) {
            player.addChatMessage(new ChatComponentText(EnumChatFormatting.RED + "Invalid enchantment name."));
            return;
        }

        Enchantment enchantment = enchantmentMap.get(enchantmentKey);
        int level = Math.min(Integer.parseInt(args[1]), 10); // Cap certain enchants at level 10

        applyEnchantment(item, enchantment, level, player);
    }

    private void applyEnchantment(ItemStack item, Enchantment enchantment, int level, EntityPlayerMP player) {
        NBTTagCompound tag = item.hasTagCompound() ? item.getTagCompound() : new NBTTagCompound();
        NBTTagList enchantments = tag.hasKey("ench") ? tag.getTagList("ench", 10) : new NBTTagList();

        for (int i = 0; i < enchantments.tagCount(); i++) {
            NBTTagCompound enchantTag = enchantments.getCompoundTagAt(i);
            if (enchantTag.getShort("id") == enchantment.effectId) {
                enchantTag.setShort("lvl", (short) level);
                player.addChatMessage(new ChatComponentText(EnumChatFormatting.GREEN + "Updated " + enchantment.getTranslatedName(level) + " to level " + level + "."));
                tag.setTag("ench", enchantments);
                item.setTagCompound(tag);
                return;
            }
        }

        NBTTagCompound newEnchant = new NBTTagCompound();
        newEnchant.setShort("id", (short) enchantment.effectId);
        newEnchant.setShort("lvl", (short) level);
        enchantments.appendTag(newEnchant);

        tag.setTag("ench", enchantments);
        item.setTagCompound(tag);

        player.addChatMessage(new ChatComponentText(EnumChatFormatting.GREEN + "Added " + enchantment.getTranslatedName(level) + " (Lvl " + level + ")."));
    }

    private void applyMaxEnchantments(ItemStack item, EntityPlayerMP player) {
        for (Enchantment enchantment : enchantmentMap.values()) {
            applyEnchantment(item, enchantment, 10, player);
        }
        player.addChatMessage(new ChatComponentText(EnumChatFormatting.GREEN + "Item fully maxed with enchantments."));
    }

    private void removeEnchantment(ItemStack item, Enchantment enchantment) {
        NBTTagCompound tag = item.getTagCompound();
        if (tag == null || !tag.hasKey("ench")) return;

        NBTTagList enchantments = tag.getTagList("ench", 10);
        NBTTagList newEnchantments = new NBTTagList();

        for (int i = 0; i < enchantments.tagCount(); i++) {
            NBTTagCompound enchantTag = enchantments.getCompoundTagAt(i);
            if (enchantTag.getShort("id") != enchantment.effectId) {
                newEnchantments.appendTag(enchantTag);
            }
        }

        if (newEnchantments.tagCount() == 0) {
            tag.removeTag("ench");
        } else {
            tag.setTag("ench", newEnchantments);
        }

        item.setTagCompound(tag);
    }

    private void clearEnchantments(ItemStack item) {
        NBTTagCompound tag = item.getTagCompound();
        if (tag != null) {
            tag.removeTag("ench");
            item.setTagCompound(tag);
        }
    }

    private String formatEnchantmentKey(String input) {
        return input.replace(" ", "").toLowerCase();
    }

    private boolean isNumeric(String str) {
        return str.matches("\\d+");
    }
}

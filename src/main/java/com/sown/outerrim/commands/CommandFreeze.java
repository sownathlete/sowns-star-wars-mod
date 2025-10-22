package com.sown.outerrim.commands;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.PlayerNotFoundException;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

import com.sown.outerrim.OuterRim;
import com.sown.outerrim.blocks.BlockCarbonite;
import com.sown.outerrim.tileentities.TileEntityCarbonite;
import com.sown.outerrim.registry.BlockRegister;
import net.minecraft.server.MinecraftServer;

import java.util.List;

public class CommandFreeze extends CommandBase {
    @Override
    public String getCommandName() {
        return "freeze";
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "/freeze <player>";
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) {
        if (args.length != 1) {
            ChatComponentText errorMessage = new ChatComponentText(
                "Invalid arguments. Usage: " + getCommandUsage(sender)
            );
            errorMessage.getChatStyle().setColor(EnumChatFormatting.RED);
            sender.addChatMessage(errorMessage);
            return;
        }

        EntityPlayer targetPlayer;
        try {
            targetPlayer = getPlayer(sender, args[0]);
        } catch (PlayerNotFoundException e) {
            ChatComponentText errorMessage = new ChatComponentText(
                "Player not found: " + args[0]
            );
            errorMessage.getChatStyle().setColor(EnumChatFormatting.RED);
            sender.addChatMessage(errorMessage);
            return;
        }

        OuterRim.frozenPlayers.add(targetPlayer.getUniqueID());

        targetPlayer.capabilities.disableDamage = true;
        targetPlayer.capabilities.allowEdit   = false;
        targetPlayer.noClip                   = true;
        targetPlayer.motionX = targetPlayer.motionY = targetPlayer.motionZ = 0;

        targetPlayer.addPotionEffect(new PotionEffect(
            Potion.digSlowdown.getId(), 72000, 255, false));
        targetPlayer.addPotionEffect(new PotionEffect(
            Potion.moveSlowdown.getId(),  72000, 255, false));
        targetPlayer.addPotionEffect(new PotionEffect(
            Potion.blindness.getId(),     72000, 255, false));
        targetPlayer.addPotionEffect(new PotionEffect(
            Potion.invisibility.getId(),  72000, 255, false));

        ItemStack carboniteItem = new ItemStack(
            BlockRegister.getRegisteredBlock("carbonite_block")
        );
        carboniteItem.setStackDisplayName(
            "Frozen " + targetPlayer.getDisplayName()
        );
        ((EntityPlayer) sender).inventory.addItemStackToInventory(carboniteItem);

        ChatComponentText successMessage = new ChatComponentText(
            "Player " + args[0] + " has been frozen."
        );
        successMessage.getChatStyle().setColor(EnumChatFormatting.GREEN);
        sender.addChatMessage(successMessage);

        World world = sender.getEntityWorld();
        int x = MathHelper.floor_double(targetPlayer.posX);
        int y = MathHelper.floor_double(targetPlayer.posY);
        int z = MathHelper.floor_double(targetPlayer.posZ);

        if (world.getBlock(x, y, z) instanceof BlockCarbonite) {
            TileEntity tileEntity = world.getTileEntity(x, y, z);
            if (tileEntity instanceof TileEntityCarbonite) {
                TileEntityCarbonite carboniteTile = (TileEntityCarbonite) tileEntity;
                carboniteTile.setFrozenPlayerName(targetPlayer.getDisplayName());
                carboniteTile.markDirty();
                world.markBlockForUpdate(x, y, z);
            }
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

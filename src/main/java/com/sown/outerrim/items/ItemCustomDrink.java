package com.sown.outerrim.items;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class ItemCustomDrink extends ItemFood {
    private boolean removesPotionEffects = false;
    private List<PotionEffect> effects = new ArrayList<>();
    private Item containerItem = Items.bucket;

    public ItemCustomDrink(int hunger, float saturation) {
        super(hunger, saturation, false);
        this.maxStackSize = 1;
    }

    public Item getContainerItem() {
        return this.containerItem;
    }

    public ItemCustomDrink setContainerItem(Item containerItem) {
        this.containerItem = containerItem;
        return this;
    }

    @Override
    public EnumAction getItemUseAction(ItemStack stack) {
        return EnumAction.drink;
    }

    public ItemCustomDrink addPotionEffect(PotionEffect effect) {
        this.effects.add(effect);
        return this;
    }

    public ItemCustomDrink setRemovesPotionEffects(boolean removesPotionEffects) {
        this.removesPotionEffects = removesPotionEffects;
        return this;
    }

    @Override
    public ItemStack onEaten(ItemStack stack, World world, EntityPlayer player) {
        if (!world.isRemote) {
            player.getFoodStats().addStats(this.func_150905_g(stack), this.func_150906_h(stack)); // Hunger and saturation

            if (this.removesPotionEffects) {
                // Create a list to store potion effect IDs to remove after iteration
                List<Integer> effectsToRemove = new ArrayList<>();
                
                for (Object obj : player.getActivePotionEffects()) {
                    PotionEffect effect = (PotionEffect) obj;
                    effectsToRemove.add(effect.getPotionID());
                }
                
                // Now, remove potion effects outside the loop
                for (Integer potionID : effectsToRemove) {
                    player.removePotionEffect(potionID);
                }
            } else {
                for (PotionEffect effect : this.effects) {
                    player.addPotionEffect(new PotionEffect(effect));
                }
            }
        }

        if (!player.capabilities.isCreativeMode) {
            --stack.stackSize;
            if (stack.stackSize <= 0) {
                return new ItemStack(this.getContainerItem());
            } else {
                player.inventory.addItemStackToInventory(new ItemStack(this.getContainerItem()));
            }
        }

        return stack;
    }

    @Override
    public ItemStack onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer player) {
        player.setItemInUse(itemStackIn, this.getMaxItemUseDuration(itemStackIn));
        return itemStackIn;
    }

    @Override
    public int getMaxItemUseDuration(ItemStack stack) {
        return 32;
    }
}

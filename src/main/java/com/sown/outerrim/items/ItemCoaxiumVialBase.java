package com.sown.outerrim.items;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public abstract class ItemCoaxiumVialBase extends Item {
    private static final String TAG_DIM = "CoaxiumDimension";
    private static final String TAG_VOL = "CoaxiumVolatility";
    private final int maxVol;

    public ItemCoaxiumVialBase(String name, int maxVolatility) {
        this.setUnlocalizedName("outerrim." + name);
        this.setTextureName("outerrim:" + name);
        this.setMaxStackSize(1);
        this.maxVol = maxVolatility;
    }

    protected void initTags(ItemStack stack, int initialVol, int dim) {
        NBTTagCompound tag = stack.getTagCompound();
        if (tag == null) {
            tag = new NBTTagCompound();
            stack.setTagCompound(tag);
            tag.setInteger(TAG_VOL, initialVol);
            tag.setInteger(TAG_DIM, dim);
        }
    }

    @Override
    public void onUpdate(ItemStack stack, World world, Entity entity, int slot, boolean held) {
        if (world.isRemote) return;

        // only players carry vials
        if (!(entity instanceof EntityPlayer)) return;  
        EntityPlayer player = (EntityPlayer) entity;

        NBTTagCompound tag = stack.getTagCompound();
        if (tag == null) return;

        int vol = tag.getInteger(TAG_VOL);
        int dim = tag.getInteger(TAG_DIM);
        int currentDim = world.provider.dimensionId;

        // dimension-change explosion
        if (currentDim != dim) {
            world.createExplosion(null,
                                  player.posX, player.posY, player.posZ,
                                  2.0F, true);
            player.inventory.setInventorySlotContents(slot, null);
            return;
        }

        // volatility tick
        vol = Math.min(vol + 1, maxVol);
        tag.setInteger(TAG_VOL, vol);
        if (vol >= maxVol) {
            // max-instability explosion
            world.createExplosion(null,
                                  player.posX, player.posY, player.posZ,
                                  4.0F, true);
            player.inventory.setInventorySlotContents(slot, null);
        }
    }
}

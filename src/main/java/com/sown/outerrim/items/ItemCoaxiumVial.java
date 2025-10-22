package com.sown.outerrim.items;

import com.sown.outerrim.OuterRim;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemCoaxiumVial extends ItemCoaxiumVialBase {
    public ItemCoaxiumVial() {
        super("coaxiumVial", 200);  // maxVol
        this.setCreativeTab(OuterRim.tabMisc);
    }

    @Override
    public void onCreated(ItemStack stack, World world, EntityPlayer player) {
        initTags(stack, 0, world.provider.dimensionId);
    }
    
    @Override
    public int getItemStackLimit(ItemStack stack) {
        return 1;
    }
}
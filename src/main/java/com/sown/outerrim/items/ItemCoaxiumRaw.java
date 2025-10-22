package com.sown.outerrim.items;

import com.sown.outerrim.OuterRim;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class ItemCoaxiumRaw extends Item {
    public ItemCoaxiumRaw() {
        this.setUnlocalizedName("outerrim.coaxiumRaw");
        this.setTextureName("outerrim:coaxiumRaw");
        this.setCreativeTab(OuterRim.tabMaterials);
        this.setMaxStackSize(64);
    }
}
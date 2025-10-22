package com.sown.outerrim.items;

import com.sown.outerrim.OuterRim;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class ItemEmptyVial extends Item {
    public ItemEmptyVial() {
        this.setUnlocalizedName("outerrim.coaxiumVialEmpty");
        this.setTextureName("outerrim:coaxiumVialEmpty");
        this.setCreativeTab(OuterRim.tabMisc);
        this.setMaxStackSize(16);
    }
}
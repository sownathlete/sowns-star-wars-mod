package com.sown.outerrim.items;

import com.sown.outerrim.OuterRim;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class ItemCustom extends Item {
    public String name;

    public ItemCustom(String name) {
        this.name = name;
        this.setUnlocalizedName("outerrim." + this.name);
        this.setTextureName("outerrim:" + this.name);
    }
}
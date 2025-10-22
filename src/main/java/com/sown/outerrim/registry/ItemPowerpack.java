/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.item.Item
 */
package com.sown.outerrim.registry;

import com.sown.outerrim.OuterRim;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class ItemPowerpack
extends Item {
    public String name = "powerpack";

    public ItemPowerpack() {
        this.setUnlocalizedName("outerrim." + this.name);
        this.setTextureName("outerrim:" + this.name);
        this.setCreativeTab(OuterRim.tabCombat);
    }
}


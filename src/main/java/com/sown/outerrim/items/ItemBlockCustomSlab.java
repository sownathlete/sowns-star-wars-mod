/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockSlab
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemSlab
 *  net.minecraft.item.ItemStack
 */
package com.sown.outerrim.items;

import com.sown.outerrim.OuterRim;
import com.sown.outerrim.blocks.BlockCustomSlab;
import com.sown.outerrim.registry.BlockRegister;

import net.minecraft.block.Block;
import net.minecraft.block.BlockSlab;
import net.minecraft.item.Item;
import net.minecraft.item.ItemSlab;
import net.minecraft.item.ItemStack;

public class ItemBlockCustomSlab extends ItemSlab {
	public ItemBlockCustomSlab(net.minecraft.block.Block block, com.sown.outerrim.blocks.BlockCustomSlab singleSlab, com.sown.outerrim.blocks.BlockCustomSlab doubleSlab, Boolean isDouble) {
	    super(block, singleSlab, doubleSlab, isDouble);
	    this.setMaxDamage(0);
	    this.setHasSubtypes(true);
	}

    public String getUnlocalizedName(ItemStack itemStack) {
        return super.getUnlocalizedName();
    }

    public int getMetadata(int meta) {
        return meta;
    }
}

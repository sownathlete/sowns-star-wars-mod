package com.sown.outerrim.items;

import com.sown.outerrim.blocks.BlockCustomGrassPlant;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemBlockGrassPlant extends ItemBlock {

    public ItemBlockGrassPlant(Block block) {
        super(block);
        this.setMaxDamage(0);
        this.setHasSubtypes(false);
    }

    // Inventory tint like vanilla grass
    @Override
    public int getColorFromItemStack(ItemStack stack, int pass) {
        return net.minecraft.world.ColorizerGrass.getGrassColor(0.5D, 1.0D);
    }

    @Override
    public String getItemStackDisplayName(ItemStack stack) {
        return super.getItemStackDisplayName(stack);
    }
}

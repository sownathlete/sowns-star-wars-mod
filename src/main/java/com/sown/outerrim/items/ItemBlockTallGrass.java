package com.sown.outerrim.items;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemBlockTallGrass extends ItemBlock {

    public ItemBlockTallGrass(Block block) {
        super(block);
        this.setMaxDamage(0);
        this.setHasSubtypes(false);
    }

    // always place the bottom half
    @Override
    public int getMetadata(int meta) {
        return 0;
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

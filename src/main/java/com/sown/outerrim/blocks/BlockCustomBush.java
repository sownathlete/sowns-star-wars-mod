package com.sown.outerrim.blocks;

import net.minecraft.block.BlockBush;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

public class BlockCustomBush extends BlockBush {
    public BlockCustomBush(String name) {
        super(Material.vine);
        this.setBlockName(name);
        this.setBlockTextureName("outerrim:" + name);
        this.setCreativeTab(CreativeTabs.tabDecorations);
        this.setHardness(0.0F);
        this.setStepSound(soundTypeGrass);
    }
}

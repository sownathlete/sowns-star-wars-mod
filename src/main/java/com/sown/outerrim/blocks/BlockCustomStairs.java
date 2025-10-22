package com.sown.outerrim.blocks;

import com.sown.outerrim.OuterRim;

import net.minecraft.block.Block;
import net.minecraft.block.BlockStairs;

public class BlockCustomStairs extends BlockStairs {

    public BlockCustomStairs(Block mainBlock) {
        super(mainBlock, 0);
        String name = mainBlock.getUnlocalizedName().substring(5);
        setBlockName(name + "_stairs");
        setBlockTextureName("outerrim:" + name);
        setHardness(mainBlock.getBlockHardness(null, 0, 0, 0));
        setStepSound(mainBlock.stepSound);
        this.setCreativeTab(OuterRim.tabBlock);
    }
}
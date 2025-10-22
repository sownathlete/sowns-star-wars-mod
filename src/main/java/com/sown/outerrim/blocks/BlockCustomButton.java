package com.sown.outerrim.blocks;

import com.sown.outerrim.OuterRim;

import net.minecraft.block.Block;
import net.minecraft.block.BlockButton;
import net.minecraft.util.IIcon;

public class BlockCustomButton extends BlockButton {
    private final Block baseBlock;

    public BlockCustomButton(String name, Block baseBlock, float hardness, String toolType, int harvestLevel, SoundType stepSound, boolean isMultiSided) {
        super(false);
        this.baseBlock = baseBlock;
        this.setBlockName(name);
        this.setHardness(hardness);
        this.setHarvestLevel(toolType, harvestLevel);
        this.setStepSound(stepSound);
        this.setCreativeTab(OuterRim.tabUtil);
    }

    @Override
    public IIcon getIcon(int side, int meta) {
        return baseBlock.getIcon(side, meta);
    }
}

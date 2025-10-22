package com.sown.outerrim.blocks;

import com.sown.outerrim.OuterRim;
import net.minecraft.block.Block;
import net.minecraft.block.BlockPressurePlate;
import net.minecraft.block.material.Material;
import net.minecraft.util.IIcon;

public class BlockCustomPressurePlate extends BlockPressurePlate {
    private final Block baseBlock;

    public BlockCustomPressurePlate(String name, Block baseBlock, float hardness, String toolType, int harvestLevel, SoundType stepSound, boolean isMultiSided) {
        super(name, baseBlock.getMaterial(), BlockPressurePlate.Sensitivity.everything);

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

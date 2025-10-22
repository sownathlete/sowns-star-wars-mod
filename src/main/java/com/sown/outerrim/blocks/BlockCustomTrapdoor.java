package com.sown.outerrim.blocks;

import com.sown.outerrim.OuterRim;
import net.minecraft.block.Block;
import net.minecraft.block.BlockTrapDoor;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;

public class BlockCustomTrapdoor extends BlockTrapDoor {

    private Block baseBlock;
    private IIcon icon;

    public BlockCustomTrapdoor(String name, Block baseBlock, float hardness, String toolType, int harvestLevel, SoundType stepSound, boolean isMultiSided) {
        super(Material.rock);
        this.baseBlock = baseBlock;
        this.setBlockName(name);
        this.setHardness(hardness);
        this.setHarvestLevel(toolType, harvestLevel);
        this.setStepSound(stepSound);
        this.setCreativeTab(OuterRim.tabUtil);
    }

    @Override
    public void registerBlockIcons(IIconRegister reg) {
    }

    @Override
    public IIcon getIcon(int side, int meta) {
        return this.baseBlock.getIcon(side, meta);
    }
}

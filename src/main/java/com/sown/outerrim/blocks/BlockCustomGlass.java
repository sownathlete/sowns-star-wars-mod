package com.sown.outerrim.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockCustomGlass extends BlockCustomSolid {
    public BlockCustomGlass(String name, Material material, float hardness, String harvestTool, int harvestLevel, Block.SoundType stepSound, boolean isMultiSided) {
        super(name, material, hardness, harvestTool, harvestLevel, stepSound, isMultiSided);
    }

    @Override
    public boolean isOpaqueCube() {
        return false;
    }

    @Override
    public int getRenderBlockPass() {
        return 1;
    }

    @Override
    public boolean renderAsNormalBlock() {
        return false;
    }

    @Override
    public boolean shouldSideBeRendered(IBlockAccess world, int x, int y, int z, int side) {
        Block adjacentBlock = world.getBlock(x, y, z);
        return adjacentBlock == this ? false : super.shouldSideBeRendered(world, x, y, z, side);
    }
}

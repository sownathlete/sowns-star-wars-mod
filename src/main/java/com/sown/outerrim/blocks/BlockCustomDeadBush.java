package com.sown.outerrim.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockDeadBush;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.world.World;

public class BlockCustomDeadBush extends BlockDeadBush {
    public BlockCustomDeadBush(String name) {
        super();
        this.setBlockName(name);
        this.setBlockTextureName("outerrim:" + name);
        this.setCreativeTab(CreativeTabs.tabDecorations);
        this.setHardness(0.0F);
        this.setStepSound(soundTypeGrass);
    }

    @Override
    protected boolean canPlaceBlockOn(Block block) {
        if (block != null && block.getUnlocalizedName() != null) {
            String blockName = block.getUnlocalizedName().toLowerCase();
            return blockName.contains("sand") || blockName.contains("dirt") || blockName.contains("grass");
        }
        return false;
    }

    @Override
    public boolean canBlockStay(World world, int x, int y, int z) {
        Block blockBelow = world.getBlock(x, y - 1, z);
        return this.canPlaceBlockOn(blockBelow);
    }
}

package com.sown.outerrim.dimension.bestine;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;

public class WorldGenSmallShrub extends WorldGenAbstractTree {
    public WorldGenSmallShrub() {
        super(false);
    }

    @Override
    public boolean generate(World world, Random random, int x, int y, int z) {
        while (world.isAirBlock(x, y, z) && y > 2) {
            --y;
        }
        Block block = world.getBlock(x, y, z);
        if (block != Blocks.grass && block != Blocks.dirt) { // Only generate on grass or dirt
            return false;
        }

        // Place the jungle log block
        world.setBlock(x, y + 1, z, Blocks.log, 3, 2); // Jungle log
        
        // Place birch leaves on top and all four sides
        int leafMeta = 2 | 4; // Birch leaves, persistent (no decay)
        world.setBlock(x, y + 2, z, Blocks.leaves, leafMeta, 2); // Top leaf
        world.setBlock(x + 1, y + 1, z, Blocks.leaves, leafMeta, 2); // East
        world.setBlock(x - 1, y + 1, z, Blocks.leaves, leafMeta, 2); // West
        world.setBlock(x, y + 1, z + 1, Blocks.leaves, leafMeta, 2); // South
        world.setBlock(x, y + 1, z - 1, Blocks.leaves, leafMeta, 2); // North
        
        return true;
    }
}

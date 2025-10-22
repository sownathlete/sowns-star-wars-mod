package com.sown.outerrim.world.gen;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;

import com.sown.outerrim.registry.BlockRegister;

public class WorldGenJaporIvoryTree extends WorldGenAbstractTree {
    
    private final Block log = BlockRegister.getRegisteredBlock("japorIvoryLog");
    private final Block leaves = BlockRegister.getRegisteredBlock("japorIvoryLeaves");

    public WorldGenJaporIvoryTree() {
        super(false);
    }

    @Override
    public boolean generate(World world, Random rand, int x, int y, int z) {
        int height = rand.nextInt(4) + 7; // Tree height between 7-10 blocks
        
        // Check if there's enough space
        for (int yPos = y; yPos <= y + height + 1; yPos++) {
            if (!world.isAirBlock(x, yPos, z)) {
                return false;
            }
        }

        // Generate trunk
        for (int i = 0; i < height; i++) {
            world.setBlock(x, y + i, z, log);
        }

        // Generate leaves in a random canopy shape
        for (int dx = -2; dx <= 2; dx++) {
            for (int dz = -2; dz <= 2; dz++) {
                for (int dy = height - 2; dy <= height; dy++) {
                    if (rand.nextBoolean()) { // Adds randomness to leaf placement
                        world.setBlock(x + dx, y + dy, z + dz, leaves);
                    }
                }
            }
        }
        
        return true;
    }
}

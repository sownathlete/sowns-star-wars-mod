package com.sown.outerrim.world.gen;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;

import com.sown.outerrim.registry.BlockRegister;

public class WorldGenDarkTree extends WorldGenAbstractTree {
    
    private final Block log = BlockRegister.getRegisteredBlock("darkTreeLog");
    private final Block leaves = BlockRegister.getRegisteredBlock("darkTreeLeaves");

    public WorldGenDarkTree() {
        super(false);
    }

    @Override
    public boolean generate(World world, Random rand, int x, int y, int z) {
        int height = rand.nextInt(3) + 8; // Tree height between 8-10 blocks

        // Check if there's enough space
        for (int yPos = y; yPos <= y + height + 1; yPos++) {
            if (!world.isAirBlock(x, yPos, z)) {
                return false;
            }
        }

        // Generate 2x2 trunk base
        for (int dx = 0; dx < 2; dx++) {
            for (int dz = 0; dz < 2; dz++) {
                for (int i = 0; i < height; i++) {
                    world.setBlock(x + dx, y + i, z + dz, log);
                }
            }
        }

        // Generate gnarly branches extending outward
        int branchHeight = height - 3;
        for (int i = 0; i < 4; i++) {
            int bx = x + (rand.nextBoolean() ? 2 : -2);
            int bz = z + (rand.nextBoolean() ? 2 : -2);
            int by = y + branchHeight + rand.nextInt(2);
            
            world.setBlock(bx, by, bz, log);
            generateLeafCluster(world, bx, by, bz);
        }

        // Generate wide, thick canopy
        for (int dx = -3; dx <= 3; dx++) {
            for (int dz = -3; dz <= 3; dz++) {
                for (int dy = branchHeight - 1; dy <= branchHeight + 2; dy++) {
                    if (rand.nextBoolean()) {
                        world.setBlock(x + dx, y + dy, z + dz, leaves);
                    }
                }
            }
        }
        
        return true;
    }

    private void generateLeafCluster(World world, int x, int y, int z) {
        for (int dx = -1; dx <= 1; dx++) {
            for (int dz = -1; dz <= 1; dz++) {
                for (int dy = 0; dy <= 2; dy++) {
                    world.setBlock(x + dx, y + dy, z + dz, leaves);
                }
            }
        }
    }
}

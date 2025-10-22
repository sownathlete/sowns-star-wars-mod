package com.sown.outerrim.dimension.dathomir;

import java.util.Random;

import com.sown.outerrim.registry.BlockRegister;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenDarkTree extends WorldGenerator {

    private final Block log = BlockRegister.getRegisteredBlock("darkTreeLog");
    private final Block leaves = BlockRegister.getRegisteredBlock("darkTreeLeaves");

    @Override
    public boolean generate(World world, Random rand, int x, int y, int z) {
        Block ground = world.getBlock(x, y - 1, z);

        // Only generate on Dathomir dirt, soul sand, or Dathomir slate
        if (ground != BlockRegister.getRegisteredBlock("dathomirDirt") &&
            ground != BlockRegister.getRegisteredBlock("dathomirSlate") &&
            ground != Blocks.soul_sand) {
            return false;
        }

        int height = 4 + rand.nextInt(3); // height 4–6

        // Check space above
        for (int i = 0; i <= height + 1; i++) {
            Block block = world.getBlock(x, y + i, z);
            if (!world.isAirBlock(x, y + i, z) && !block.isReplaceable(world, x, y + i, z)) {
                return false;
            }
        }

        // Trunk
        for (int i = 0; i < height; i++) {
            world.setBlock(x, y + i, z, log, 0, 2);
        }

        // Leaves (simple blob)
        int leafRadius = 2;
        for (int dx = -leafRadius; dx <= leafRadius; dx++) {
            for (int dz = -leafRadius; dz <= leafRadius; dz++) {
                for (int dy = -1; dy <= 2; dy++) {
                    int dist = Math.abs(dx) + Math.abs(dz) + Math.abs(dy);
                    if (dist <= 3 && world.isAirBlock(x + dx, y + height + dy, z + dz)) {
                        world.setBlock(x + dx, y + height + dy, z + dz, leaves, 0, 2);
                    }
                }
            }
        }

        return true;
    }
}
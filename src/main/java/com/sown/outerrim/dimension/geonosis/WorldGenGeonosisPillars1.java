package com.sown.outerrim.dimension.geonosis;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenGeonosisPillars1 extends WorldGenerator {
    private final Block block1, block2;

    public WorldGenGeonosisPillars1(Block block1, Block block2) {
        this.block1 = block1;
        this.block2 = block2;
    }

    @Override
    public boolean generate(World world, Random rand, int x, int y, int z) {
        int height = rand.nextInt(10) + 20; // change these values for desired min and max heights
        int radius = rand.nextInt(2) + 1; // change these for desired min and max radii

        // additional variable to keep track of the rings
        int ringCounter = 0;

        float chance = rand.nextFloat();
        if(chance < 0.05) {
            height *= 5;
            radius *= 5;
        } else if(chance < 0.15) { // 0.05 (previous chance) + 0.10 (current chance) = 0.15
            height *= 3;
            radius *= 3;
        } else if(chance < 0.45) { // 0.15 (previous total chance) + 0.30 (current chance) = 0.45
            height *= 2;
            radius *= 2;
        }

        for (int i = 0; i < height; i++) {
            for (int dx = -radius; dx <= radius; dx++) {
                for (int dz = -radius; dz <= radius; dz++) {
                    if (dx * dx + dz * dz <= radius * radius) { // equation for a circle
                        Block currentBlock = rand.nextFloat() < 0.5 ? this.block1 : this.block2;
                        world.setBlock(x + dx, y + i, z + dz, currentBlock);

                        // only do depth check if we're at the base of the pillar
                        if (i == 0) {
                            int j = y + i - 1;
                            while(world.isAirBlock(x + dx, j, z + dz) || world.getBlock(x + dx, j, z + dz) == Blocks.lava) {
                                world.setBlock(x + dx, j, z + dz, this.block1);
                                j--;
                            }
                        }
                    }
                }
            }

            // every 5 blocks high, make a ring around the pillar, increase ringCounter
            if (i % 5 == 0 && i != 0) {
                ringCounter++;
            }

            if (ringCounter == 1) {
                for (int dx = -radius - 1; dx <= radius + 1; dx++) {
                    for (int dz = -radius - 1; dz <= radius + 1; dz++) {
                        if (dx * dx + dz * dz <= (radius + 1) * (radius + 1)) { // equation for a larger circle
                            world.setBlock(x + dx, y + i, z + dz, this.block1); // rings are always block1
                        }
                    }
                }
            }

            // when ringCounter reaches 2, decrease the radius and reset ringCounter
            if (ringCounter == 2 && radius > 0) {
                radius--;
                ringCounter = 0;
            }
        }

        return true;
    }
}
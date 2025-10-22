package com.sown.outerrim.dimension.geonosis;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenGeonosisPillars extends WorldGenerator {
    private final Block block1, block2, block3;

    public WorldGenGeonosisPillars(Block block1, Block block2, Block block3) {
        this.block1 = block1;
        this.block2 = block2;
        this.block3 = block3;
    }

    @Override
    public boolean generate(World world, Random rand, int x, int y, int z) {
        int height = rand.nextInt(10) + 20;
        int radius = rand.nextInt(2) + 1;
        int originalRadius = radius;

        int ringCounter = 0;

        float chance = rand.nextFloat();
        if(chance < 0.05) {
            height *= 5;
            radius *= 5;
        } else if(chance < 0.15) {
            height *= 3;
            radius *= 3;
        } else if(chance < 0.45) {
            height *= 2;
            radius *= 2;
        }

        originalRadius = radius;

        for (int i = 0; i < height; i++) {
            for (int dx = -radius; dx <= radius; dx++) {
                for (int dz = -radius; dz <= radius; dz++) {
                    if (dx * dx + dz * dz <= radius * radius) {
                        if(world.isAirBlock(x + dx, y + i - 1, z + dz)) {
                            world.setBlock(x + dx, y + i - 1, z + dz, this.block1);
                        }

                        Block currentBlock = rand.nextFloat() < 0.5 ? this.block1 : this.block2;
                        world.setBlock(x + dx, y + i, z + dz, currentBlock);
                    }
                }
            }

            if (i % 5 == 0 && i != 0) {
                for (int dx = -radius - 1; dx <= radius + 1; dx++) {
                    for (int dz = -radius - 1; dz <= radius + 1; dz++) {
                        if (dx * dx + dz * dz <= (radius + 1) * (radius + 1)) {
                            world.setBlock(x + dx, y + i, z + dz, this.block1);
                        }
                    }
                }
                ringCounter++;
            }

            if ((ringCounter == 5 && originalRadius == 5 && radius > 0) || (ringCounter == 2 && radius > 0 && originalRadius != 5)) {
                radius--;
                ringCounter = 0;
            }
        }

        // Adding additional 3 layers of block3 on top of the pillar
        for (int i = height; i < height + 3; i++) {
            for (int dx = -radius; dx <= radius; dx++) {
                for (int dz = -radius; dz <= radius; dz++) {
                    if (dx * dx + dz * dz <= radius * radius) {
                        world.setBlock(x + dx, y + i, z + dz, this.block3);
                    }
                }
            }
        }

        return true;
    }
}

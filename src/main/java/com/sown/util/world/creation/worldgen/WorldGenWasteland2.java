/*
 * Decompiled with CFR 0.148.
 *
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.world.World
 */
package com.sown.util.world.creation.worldgen;

import java.util.Random;

import com.sown.util.world.creation.ORBiome;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

public class WorldGenWasteland2
extends WorldGeneratorOR {
    @Override
    public boolean generate(World world, Random random, int x, int y, int z) {
        while (world.isAirBlock(x, y, z) && y > 2) {
            --y;
        }
        Block block = world.getBlock(x, y, z);
        if (block != Blocks.dirt)
            return false;
        for (int var7 = -2; var7 <= 2; ++var7) {
            for (int var8 = -2; var8 <= 2; ++var8) {
                if (!world.isAirBlock(x + var7, y - 1, z + var8) || !world.isAirBlock(x + var7, y - 2, z + var8) || world.isAirBlock(x + var7, y, z + var8)) {
                    continue;
                }
                return false;
            }
        }
        world.setBlock(x, y, z, Blocks.dirt);
        world.setBlock(x - 1, y, z, Blocks.dirt);
        world.setBlock(x + 1, y, z, Blocks.dirt);
        world.setBlock(x, y, z - 1, Blocks.dirt);
        world.setBlock(x, y, z + 1, Blocks.dirt);
        world.setBlock(x - 1, y, z - 1, Blocks.dirt);
        world.setBlock(x + 1, y, z + 1, Blocks.dirt);
        world.setBlock(x - 1, y, z + 1, Blocks.dirt);
        world.setBlock(x + 1, y, z - 1, Blocks.dirt);
        world.setBlock(x - 2, y, z - 1, Blocks.dirt);
        world.setBlock(x - 2, y, z, Blocks.dirt);
        world.setBlock(x - 2, y, z + 1, Blocks.dirt);
        world.setBlock(x + 2, y, z - 1, Blocks.dirt);
        world.setBlock(x + 2, y, z, Blocks.dirt);
        world.setBlock(x + 2, y, z + 1, Blocks.dirt);
        world.setBlock(x - 1, y, z - 2, Blocks.dirt);
        world.setBlock(x, y, z - 2, Blocks.dirt);
        world.setBlock(x + 1, y, z - 2, Blocks.dirt);
        world.setBlock(x - 1, y, z + 2, Blocks.dirt);
        world.setBlock(x, y, z + 2, Blocks.dirt);
        world.setBlock(x + 1, y, z + 2, Blocks.dirt);
        world.setBlock(x, y + 1, z, Blocks.dirt);
        world.setBlock(x - 1, y + 1, z, Blocks.dirt);
        world.setBlock(x + 1, y + 1, z, Blocks.dirt);
        world.setBlock(x, y + 1, z - 1, Blocks.dirt);
        world.setBlock(x, y + 1, z + 1, Blocks.dirt);
        world.setBlock(x - 1, y + 1, z - 1, Blocks.dirt);
        world.setBlock(x + 1, y + 1, z + 1, Blocks.dirt);
        world.setBlock(x - 1, y + 1, z + 1, Blocks.dirt);
        world.setBlock(x + 1, y + 1, z - 1, Blocks.dirt);
        world.setBlock(x - 2, y + 1, z - 1, Blocks.dirt);
        world.setBlock(x - 2, y + 1, z, Blocks.dirt);
        world.setBlock(x - 2, y + 1, z + 1, Blocks.dirt);
        world.setBlock(x + 2, y + 1, z - 1, Blocks.dirt);
        world.setBlock(x + 2, y + 1, z, Blocks.dirt);
        world.setBlock(x + 2, y + 1, z + 1, Blocks.dirt);
        world.setBlock(x - 1, y + 1, z - 2, Blocks.dirt);
        world.setBlock(x, y + 1, z - 2, Blocks.dirt);
        world.setBlock(x + 1, y + 1, z - 2, Blocks.dirt);
        world.setBlock(x - 1, y + 1, z + 2, Blocks.dirt);
        world.setBlock(x, y + 1, z + 2, Blocks.dirt);
        world.setBlock(x + 1, y + 1, z + 2, Blocks.dirt);
        world.setBlock(x - 1, y + 2, z, Blocks.dirt);
        world.setBlock(x + 1, y + 2, z, Blocks.dirt);
        world.setBlock(x, y + 2, z - 1, Blocks.dirt);
        world.setBlock(x, y + 2, z + 1, Blocks.dirt);
        world.setBlock(x - 1, y + 2, z - 1, Blocks.dirt);
        world.setBlock(x + 1, y + 2, z + 1, Blocks.dirt);
        world.setBlock(x - 1, y + 2, z + 1, Blocks.dirt);
        world.setBlock(x + 1, y + 2, z - 1, Blocks.dirt);
        world.setBlock(x - 2, y + 2, z - 1, Blocks.dirt);
        world.setBlock(x - 2, y + 2, z, Blocks.dirt);
        world.setBlock(x - 2, y + 2, z + 1, Blocks.dirt);
        world.setBlock(x + 2, y + 2, z - 1, Blocks.dirt);
        world.setBlock(x + 2, y + 2, z, Blocks.dirt);
        world.setBlock(x + 2, y + 2, z + 1, Blocks.dirt);
        world.setBlock(x - 1, y + 2, z - 2, Blocks.dirt);
        world.setBlock(x, y + 2, z - 2, Blocks.dirt);
        world.setBlock(x + 1, y + 2, z - 2, Blocks.dirt);
        world.setBlock(x - 1, y + 2, z + 2, Blocks.dirt);
        world.setBlock(x, y + 2, z + 2, Blocks.dirt);
        world.setBlock(x + 1, y + 2, z + 2, Blocks.dirt);
        world.setBlock(x, y + 2, z, Blocks.dirt);
        world.setBlock(x - 1, y + 3, z, Blocks.dirt);
        world.setBlock(x + 1, y + 3, z, Blocks.dirt);
        world.setBlock(x, y + 3, z - 1, Blocks.dirt);
        world.setBlock(x, y + 3, z + 1, Blocks.dirt);
        world.setBlock(x - 1, y + 3, z - 1, Blocks.dirt);
        world.setBlock(x + 1, y + 3, z + 1, Blocks.dirt);
        world.setBlock(x - 1, y + 3, z + 1, Blocks.dirt);
        world.setBlock(x + 1, y + 3, z - 1, Blocks.dirt);
        world.setBlock(x, y + 3, z, Blocks.dirt);
        world.setBlock(x - 1, y + 4, z, Blocks.dirt);
        world.setBlock(x + 1, y + 4, z, Blocks.dirt);
        world.setBlock(x, y + 4, z - 1, Blocks.dirt);
        world.setBlock(x, y + 4, z + 1, Blocks.dirt);
        world.setBlock(x - 1, y + 4, z - 1, Blocks.dirt);
        world.setBlock(x + 1, y + 4, z + 1, Blocks.dirt);
        world.setBlock(x - 1, y + 4, z + 1, Blocks.dirt);
        world.setBlock(x + 1, y + 4, z - 1, Blocks.dirt);
        world.setBlock(x, y + 4, z, Blocks.dirt);
        world.setBlock(x - 1, y + 5, z, Blocks.dirt);
        world.setBlock(x + 1, y + 5, z, Blocks.dirt);
        world.setBlock(x, y + 5, z - 1, Blocks.dirt);
        world.setBlock(x, y + 5, z + 1, Blocks.dirt);
        world.setBlock(x - 1, y + 5, z - 1, Blocks.dirt);
        world.setBlock(x + 1, y + 5, z + 1, Blocks.dirt);
        world.setBlock(x - 1, y + 5, z + 1, Blocks.dirt);
        world.setBlock(x + 1, y + 5, z - 1, Blocks.dirt);
        world.setBlock(x, y + 5, z, Blocks.dirt);
        world.setBlock(x - 1, y + 6, z, Blocks.dirt);
        world.setBlock(x + 1, y + 6, z, Blocks.dirt);
        world.setBlock(x, y + 6, z - 1, Blocks.dirt);
        world.setBlock(x, y + 6, z + 1, Blocks.dirt);
        world.setBlock(x, y + 6, z, Blocks.dirt);
        world.setBlock(x - 1, y + 7, z, Blocks.dirt);
        world.setBlock(x + 1, y + 7, z, Blocks.dirt);
        world.setBlock(x, y + 7, z - 1, Blocks.dirt);
        world.setBlock(x, y + 7, z + 1, Blocks.dirt);
        world.setBlock(x, y + 7, z, Blocks.dirt);
        world.setBlock(x - 1, y + 8, z, Blocks.dirt);
        world.setBlock(x + 1, y + 8, z, Blocks.dirt);
        world.setBlock(x, y + 8, z - 1, Blocks.dirt);
        world.setBlock(x, y + 8, z + 1, Blocks.dirt);
        world.setBlock(x, y + 8, z, Blocks.dirt);
        world.setBlock(x, y + 9, z, Blocks.dirt);
        world.setBlock(x, y + 10, z, Blocks.dirt);
        world.setBlock(x, y + 11, z, Blocks.dirt);
        world.setBlock(x, y + 12, z, Blocks.dirt);
        return true;
    }

    @Override
    public void setupGeneration(World world, Random random, ORBiome biome, String featureName, int x, int z) {
        for (int i = 0; i < (Integer)biome.theBiomeDecorator.orFeatures.getFeature(featureName); ++i) {
            int randX = x + random.nextInt(16) + 8;
            int randZ = z + random.nextInt(16) + 8;
            int randY = random.nextInt(world.getHeightValue(randX, randZ) * 2);
            this.generate(world, random, randX, randY, randZ);
        }
    }
}


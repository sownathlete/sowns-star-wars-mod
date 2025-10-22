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

public class WorldGenWasteland3
extends WorldGeneratorOR {
    @Override
    public boolean generate(World world, Random random, int x, int y, int z) {
        while (world.isAirBlock(x, y, z) && y > 2) {
            --y;
        }
        Block var6 = world.getBlock(x, y, z);
        Block var95 = world.getBlock(x - 1, y, z);
        Block var96 = world.getBlock(x + 1, y, z);
        Block var97 = world.getBlock(x, y, z - 1);
        Block var98 = world.getBlock(x, y, z + 1);
        if (var6 != Blocks.dirt || var95 != Blocks.dirt || var96 != Blocks.dirt || var97 != Blocks.dirt || var98 != Blocks.dirt)
            return false;
        for (int var7 = -2; var7 <= 2; ++var7) {
            for (int var8 = -2; var8 <= 2; ++var8) {
                if (!world.isAirBlock(x + var7, y - 1, z + var8) || !world.isAirBlock(x + var7, y - 2, z + var8)) {
                    continue;
                }
                return false;
            }
        }
        int var999 = random.nextInt(2);
        if (var999 == 0) {
            world.setBlock(x, y, z, Blocks.dirt);
            world.setBlock(x - 1, y, z, Blocks.dirt);
            world.setBlock(x + 1, y, z, Blocks.dirt);
            world.setBlock(x, y, z - 1, Blocks.dirt);
            world.setBlock(x, y, z + 1, Blocks.dirt);
            this.setBlockAndNotifyAdequately(world, x, y + 1, z, Blocks.dirt, 0);
            this.setBlockAndNotifyAdequately(world, x + 1, y + 1, z, Blocks.dirt, 0);
            this.setBlockAndNotifyAdequately(world, x - 1, y + 1, z, Blocks.dirt, 0);
            this.setBlockAndNotifyAdequately(world, x, y + 1, z + 1, Blocks.dirt, 0);
            this.setBlockAndNotifyAdequately(world, x, y + 1, z - 1, Blocks.dirt, 0);
            this.setBlockAndNotifyAdequately(world, x, y + 2, z, Blocks.dirt, 0);
            return true;
        }
        if (var999 == 1) {
            world.setBlock(x, y, z, Blocks.dirt);
            this.setBlockAndNotifyAdequately(world, x, y + 1, z, Blocks.dirt, 0);
            return true;
        }
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


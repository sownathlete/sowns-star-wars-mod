/*
 * Decompiled with CFR 0.148.
 *
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.init.Blocks
 *  net.minecraft.item.ItemStack
 *  net.minecraft.world.World
 */
package com.sown.util.world.creation.worldgen;

import java.util.Random;

import com.sown.util.world.creation.ORBiome;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class WorldGenWaterReeds
extends WorldGeneratorOR {
    @Override
    public boolean generate(World world, Random random, int x, int y, int z) {
        for (int var6 = 0; var6 < 128; ++var6) {
            int k1;
            int j1;
            int i1 = x + random.nextInt(8) - random.nextInt(8);
            if (!world.isAirBlock(i1, j1 = y + random.nextInt(2) - random.nextInt(2), k1 = z + random.nextInt(8) - random.nextInt(8)) || !Blocks.reeds.canReplace(world, i1, j1, k1, 0, new ItemStack(Blocks.reeds, 1, 14))) {
                continue;
            }
            for (int i = 4; i > -4; --i) {
                if (world.getBlock(i1 - i, j1 - 1, k1 - i) == Blocks.water) {
                    continue;
                }
                world.setBlock(i1, j1, k1, Blocks.reeds, 14, 2);
            }
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


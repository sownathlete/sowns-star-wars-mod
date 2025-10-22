/*
 * Decompiled with CFR 0.148.
 *
 * Could not load the following classes:
 *  net.minecraft.block.Block
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

public class WorldGenRiverCane
extends WorldGeneratorOR {
    @Override
    public boolean generate(World world, Random par2Random, int x, int y, int z) {
        for (int l = 0; l < 5; ++l) {
            int k1;
            int j1;
            int i1 = x + par2Random.nextInt(8) - par2Random.nextInt(8);
            if (!world.isAirBlock(i1, j1 = y, k1 = z + par2Random.nextInt(8) - par2Random.nextInt(8))) {
                continue;
            }
            int l1 = 1 + par2Random.nextInt(par2Random.nextInt(3) + 1);
            for (int i2 = 0; i2 < l1; ++i2) {
                if (!Blocks.reeds.canReplace(world, i1, j1, k1, 0, new ItemStack(Blocks.reeds, 1, 8))) {
                    continue;
                }
                world.setBlock(i1, j1 + i2, k1, Blocks.reeds, 8, 2);
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


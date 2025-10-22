/*
 * Decompiled with CFR 0.148.
 *
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.init.Blocks
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 */
package com.sown.util.world.creation.worldgen;

import java.util.Random;

import com.sown.util.world.creation.ORBiome;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class WorldGenOvergrownNetherrack
extends WorldGeneratorOR {
    @Override
    public boolean generate(World world, Random random, int x, int y, int z) {
        Block block;
        while (((block = world.getBlock(x, y, z)).isLeaves(world, x, y, z) || block.isAir(world, x, y, z)) && --y > 0) {
        }
        for (int var7 = 0; var7 < 128; ++var7) {
            int randX = x + random.nextInt(8) - random.nextInt(8);
            int randY = y + random.nextInt(4) - random.nextInt(4);
            int randZ = z + random.nextInt(8) - random.nextInt(8);
            int var999 = random.nextInt(5);
            if (!world.isAirBlock(randX, randY, randZ) || world.getBlock(randX, randY - 1, randZ) != Blocks.netherrack) {
                continue;
            }
            world.setBlock(randX, randY - 1, randZ, Blocks.netherrack, 0, 2);
            if (var999 == 0) {
                world.setBlock(randX, randY, randZ, Blocks.tallgrass, 2, 2);
                continue;
            }
            if (var999 == 1) {
                world.setBlock(randX, randY, randZ, Blocks.tallgrass, 10, 2);
                continue;
            }
            if (var999 == 2) {
                world.setBlock(randX, randY, randZ, Blocks.tallgrass, 11, 2);
                continue;
            }
            world.setBlock(randX, randY, randZ, Blocks.tallgrass, 2, 2);
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


/*
 * Decompiled with CFR 0.148.
 *
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockLiquid
 *  net.minecraft.init.Blocks
 *  net.minecraft.world.World
 */
package com.sown.util.world.creation.worldgen;

import java.util.Random;

import com.sown.outerrim.OuterRim;
import com.sown.outerrim.registry.BlockRegister;
import com.sown.util.world.creation.ORBiome;

import net.minecraft.block.Block;
import net.minecraft.block.BlockLiquid;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

public class WorldGenLavaSpout
extends WorldGeneratorOR {
    @Override
    public boolean generate(World world, Random random, int x, int y, int z) {
        while (world.isAirBlock(x, y, z) && y > 2) {
            --y;
        }
        Block block = world.getBlock(x, y, z);
        if (block != BlockRegister.getRegisteredBlock("ashBlack"))
            return false;
        for (int var7 = -2; var7 <= 2; ++var7) {
            for (int var8 = -2; var8 <= 2; ++var8) {
                if (!world.isAirBlock(x + var7, y - 1, z + var8) || !world.isAirBlock(x + var7, y - 2, z + var8)) {
                    continue;
                }
                return false;
            }
        }
        world.setBlock(x, y - 1, z, Blocks.flowing_lava);
        world.setBlock(x, y, z, Blocks.flowing_lava);
        world.setBlock(x, y + 1, z, Blocks.flowing_lava);
        world.setBlock(x - 1, y + 1, z, Blocks.flowing_lava);
        world.setBlock(x + 1, y + 1, z, Blocks.flowing_lava);
        world.setBlock(x, y + 1, z - 1, Blocks.flowing_lava);
        world.setBlock(x, y + 1, z + 1, Blocks.flowing_lava);
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


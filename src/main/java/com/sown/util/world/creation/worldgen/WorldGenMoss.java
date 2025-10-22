/*
 * Decompiled with CFR 0.148.
 *
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.util.Direction
 *  net.minecraft.util.Facing
 *  net.minecraft.world.World
 */
package com.sown.util.world.creation.worldgen;

import java.util.Random;

import com.sown.util.world.creation.ORBiome;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.Direction;
import net.minecraft.util.Facing;
import net.minecraft.world.World;

public class WorldGenMoss
extends WorldGeneratorOR {
    @Override
    public boolean generate(World world, Random random, int x, int y, int z) {
        int var6 = x;
        int var7 = z;
        while (y < 80) {
            if (world.isAirBlock(x, y, z)) {
                for (int var8 = 2; var8 <= 5; ++var8) {
                    if (!Blocks.vine.canPlaceBlockOnSide(world, x, y, z, var8)) {
                        continue;
                    }
                    int var999 = random.nextInt(4);
                    if (var999 == 0) {
                        world.setBlock(x, y, z, Blocks.vine, 1 << Direction.facingToDirection[Facing.oppositeSide[var8]], 2);
                    }
                    break;
                }
            } else {
                x = var6 + random.nextInt(4) - random.nextInt(4);
                z = var7 + random.nextInt(4) - random.nextInt(4);
            }
            ++y;
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


/*
 * Decompiled with CFR 0.148.
 *
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.init.Blocks
 *  net.minecraft.world.World
 */
package com.sown.util.world.creation.worldgen;

import java.util.Random;

import com.sown.util.world.creation.ORBiome;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

public class WorldGenLongVine
extends WorldGeneratorOR {
    private Block vine;
    private int baseHeight;
    private int randHeight;

    public WorldGenLongVine(Block vine, int baseHeight, int randHeight) {
        this.vine = vine;
        this.baseHeight = baseHeight;
        this.randHeight = randHeight;
    }

    @Override
    public boolean generate(World world, Random rand, int x, int y, int z) {
        int iy;
        while (world.isAirBlock(x, y, z) && y < 254) {
            ++y;
        }
        Block block = world.getBlock(x, y, z);
        if (block != Blocks.netherrack)
            return false;
        for (iy = 1; iy <= rand.nextInt(this.randHeight) + this.baseHeight && world.isAirBlock(x - 1, y - iy, z); ++iy) {
            world.setBlock(x - 1, y - iy, z, this.vine, 8, 2);
        }
        for (iy = 1; iy <= rand.nextInt(this.randHeight) + this.baseHeight && world.isAirBlock(x + 1, y - iy, z); ++iy) {
            world.setBlock(x + 1, y - iy, z, this.vine, 2, 2);
        }
        for (iy = 1; iy <= rand.nextInt(this.randHeight) + this.baseHeight && world.isAirBlock(x, y - iy, z - 1); ++iy) {
            world.setBlock(x, y - iy, z - 1, this.vine, 1, 2);
        }
        for (iy = 1; iy <= rand.nextInt(this.randHeight) + this.baseHeight && world.isAirBlock(x, y - iy, z + 1); ++iy) {
            world.setBlock(x, y - iy, z + 1, this.vine, 4, 2);
        }
        return true;
    }

    @Override
    public void setupGeneration(World world, Random random, ORBiome biome, String featureName, int x, int z) {
        for (int i = 0; i < (Integer)biome.theBiomeDecorator.orFeatures.getFeature(featureName); ++i) {
            int randX = x + random.nextInt(16) + 8;
            int randZ = z + random.nextInt(16) + 8;
            int randY = random.nextInt(256);
            this.generate(world, random, randX, randY, randZ);
        }
    }
}


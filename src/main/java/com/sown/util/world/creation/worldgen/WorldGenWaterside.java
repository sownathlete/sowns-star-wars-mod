/*
 * Decompiled with CFR 0.148.
 *
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.material.Material
 *  net.minecraft.world.World
 */
package com.sown.util.world.creation.worldgen;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import com.sown.util.world.creation.ORBiome;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenWaterside
extends WorldGenerator {
    private List sideBlocks;
    private Block watersideBlock;
    private int radius;

    public WorldGenWaterside(Block watersideBlock, int radius, Block ... sideBlocks) {
        super(true);
        this.watersideBlock = watersideBlock;
        this.radius = radius;
        this.sideBlocks = Arrays.asList(sideBlocks);
    }

    @Override
    public boolean generate(World world, Random random, int x, int y, int z) {
        if (world.getBlock(x, y, z).getMaterial() != Material.water)
            return false;
        int l = random.nextInt(this.radius - 2) + 2;
        int b0 = 2;
        for (int i1 = x - l; i1 <= x + l; ++i1) {
            for (int j1 = z - l; j1 <= z + l; ++j1) {
                int k1 = i1 - x;
                int l1 = j1 - z;
                if (k1 * k1 + l1 * l1 > l * l) {
                    continue;
                }
                for (int i2 = y - b0; i2 <= y + b0; ++i2) {
                    Block block = world.getBlock(i1, i2, j1);
                    if (!this.sideBlocks.contains(block)) {
                        continue;
                    }
                    world.setBlock(i1, i2, j1, this.watersideBlock, 0, 2);
                }
            }
        }
        return true;
    }

    public void setupGeneration(World world, Random random, ORBiome biome, String featureName, int x, int z) {
        for (int i = 0; i < (Integer)biome.theBiomeDecorator.orFeatures.getFeature(featureName); ++i) {
            int randX = x + random.nextInt(16) + 8;
            int randZ = z + random.nextInt(16) + 8;
            this.generate(world, random, randX, world.getTopSolidOrLiquidBlock(randX, randZ), randZ);
        }
    }
}


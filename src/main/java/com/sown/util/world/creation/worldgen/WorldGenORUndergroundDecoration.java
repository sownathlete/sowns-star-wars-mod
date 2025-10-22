/*
 * Decompiled with CFR 0.148.
 *
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.item.ItemStack
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 */
package com.sown.util.world.creation.worldgen;

import java.util.Random;

import com.sown.util.world.creation.ORBiome;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class WorldGenORUndergroundDecoration
extends WorldGeneratorOR {
    private Block tallGrass;
    private int tallGrassMetadata;

    public WorldGenORUndergroundDecoration(Block p_i45466_1_, int p_i45466_2_) {
        this.tallGrass = p_i45466_1_;
        this.tallGrassMetadata = p_i45466_2_;
    }

    @Override
    public boolean generate(World world, Random random, int x, int y, int z) {
        Block block;
        while (((block = world.getBlock(x, y, z)).isLeaves(world, x, y, z) || block.isAir(world, x, y, z)) && --y > 0) {
        }
        for (int l = 0; l < 128; ++l) {
            int k1;
            int j1;
            int i1 = x + random.nextInt(8) - random.nextInt(8);
            if (!world.isAirBlock(i1, j1 = y + random.nextInt(4) - random.nextInt(4), k1 = z + random.nextInt(8) - random.nextInt(8)) || !this.tallGrass.canReplace(world, i1, j1, k1, 0, new ItemStack(this.tallGrass, 1, this.tallGrassMetadata))) {
                continue;
            }
            world.setBlock(i1, j1, k1, this.tallGrass, this.tallGrassMetadata, 2);
        }
        return true;
    }

    @Override
    public void setupGeneration(World world, Random random, ORBiome biome, String featureName, int x, int z) {
        for (int i = 0; i < (Integer)biome.theBiomeDecorator.orFeatures.getFeature(featureName); ++i) {
            int randX = x + random.nextInt(16) + 8;
            int randZ = z + random.nextInt(16) + 8;
            int randY = random.nextInt(60);
            this.generate(world, random, randX, randY, randZ);
        }
    }
}


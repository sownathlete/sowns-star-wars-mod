/*
 * Decompiled with CFR 0.148.
 *
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockGrass
 *  net.minecraft.init.Blocks
 *  net.minecraft.item.ItemStack
 *  net.minecraft.world.World
 *  net.minecraft.world.WorldProvider
 */
package com.sown.util.world.creation.worldgen;

import java.util.Random;

import com.sown.util.world.creation.ORBiome;

import net.minecraft.block.Block;
import net.minecraft.block.BlockGrass;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraft.world.WorldProvider;

public class WorldGenORFlora
extends WorldGeneratorOR {
    public Block flora;
    public int floraMeta;
    private int groupCount = 64;

    public WorldGenORFlora() {
    }

    public WorldGenORFlora(Block flora, int floraMeta) {
        this.flora = flora;
        this.floraMeta = floraMeta;
    }

    public WorldGenORFlora(Block flora, int floraMeta, int groupCount) {
        this.flora = flora;
        this.floraMeta = floraMeta;
        this.groupCount = groupCount;
    }

    @Override
    public boolean generate(World world, Random random, int x, int y, int z) {
        for (int l = 0; l < this.groupCount; ++l) {
            int k1;
            int j1;
            int i1 = x + random.nextInt(8) - random.nextInt(8);
            if (!world.isAirBlock(i1, j1 = y + random.nextInt(4) - random.nextInt(4), k1 = z + random.nextInt(8) - random.nextInt(8)) || world.provider.hasNoSky && j1 >= 255 || (this.flora == Blocks.web ? world.getBlock(i1, j1 + 1, k1) != Blocks.grass : !this.flora.canReplace(world, i1, j1, k1, 0, new ItemStack(this.flora, 1, this.floraMeta)))) {
                continue;
            }
            world.setBlock(i1, j1, k1, this.flora, this.floraMeta, 2);
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


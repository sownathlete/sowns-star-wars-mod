/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockGrass
 *  net.minecraft.block.BlockTallGrass
 *  net.minecraft.init.Blocks
 *  net.minecraft.world.World
 *  net.minecraft.world.biome.BiomeGenBase
 *  net.minecraft.world.biome.BiomeGenBase$Height
 *  net.minecraft.world.gen.feature.WorldGenAbstractTree
 */
package com.sown.outerrim.dimension.rakataprime;

import java.util.HashMap;
import java.util.Random;

import com.sown.outerrim.dimension.bestine.WorldGenPalmTreeA1;
import com.sown.outerrim.dimension.bestine.WorldGenSmallShrub;
import com.sown.util.world.creation.ORSubBiome;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockGrass;
import net.minecraft.block.BlockTallGrass;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;

public class BiomeGenRakataPrime
extends ORSubBiome {
    private static final BiomeGenBase.Height biomeHeight = new BiomeGenBase.Height(-0.3f, 0.3f);

    public BiomeGenRakataPrime(int biomeID) {
        super(biomeID);
        this.zoom = 0.5;
        this.threshold = 0.5;
        this.setHeight(biomeHeight);
        this.setColor(7712283);
        this.setTemperatureRainfall(0.5f, 0.9f);
        this.topBlock = Blocks.sand;
        this.fillerBlock = Blocks.dirt;
        this.spawnableCreatureList.clear();
        this.spawnableMonsterList.clear();
    }

    public WorldGenAbstractTree genBigTreeChance(Random random) {
        if (random.nextInt(4) == 0) {
            return new WorldGenPalmTreeA1(); // Less frequent
        } else {
            return new WorldGenSmallShrub(); // Small shrub spawns more often
        }
    }

    @Override
    public void decorate(World world, Random random, int chunkX, int chunkZ) {
        super.decorate(world, random, chunkX, chunkZ);

        int totalPlants = (6 + random.nextInt(5)) / 4; // Reduce the total trees by 3/4

        for (int i = 0; i < totalPlants; i++) {
            int x = chunkX + random.nextInt(16);
            int z = chunkZ + random.nextInt(16);

            // Find the highest solid block at this (x, z) coordinate
            int y = world.getTopSolidOrLiquidBlock(x, z) - 1;

            Block block = world.getBlock(x, y, z);

            // Ensure we are placing only on grass
            if (block == Blocks.grass) {
                if (random.nextInt(4) == 0) {
                    new WorldGenPalmTreeA1().generate(world, random, x, y + 1, z);
                } else {
                    new WorldGenSmallShrub().generate(world, random, x, y + 1, z); // Small shrubs spawn more often
                }
            }
        }
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public int getWaterColorMultiplier() {
        return 2263264; // Hex: #2288B0
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public int getBiomeGrassColor(int x, int y, int z) {
        return 6326627; // Hex: #87A75E
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getBiomeFoliageColor(int x, int y, int z) {
        return 8880702; // Hex: #87A75E
    }

}


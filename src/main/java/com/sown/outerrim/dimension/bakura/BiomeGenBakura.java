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
package com.sown.outerrim.dimension.bakura;

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
import net.minecraft.world.gen.feature.WorldGenTrees;

public class BiomeGenBakura
extends ORSubBiome {
    private static final BiomeGenBase.Height biomeHeight = new BiomeGenBase.Height(-0.3f, 0.4f);

    public BiomeGenBakura(int biomeID) {
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
        if (random.nextBoolean()) {
            return new WorldGenPalmTreeA1(); // 50% chance for a palm tree
        } else {
            return new WorldGenTrees(false); // 50% chance for a normal vanilla tree (oak)
        }
    }

    @Override
    public void decorate(World world, Random random, int chunkX, int chunkZ) {
        super.decorate(world, random, chunkX, chunkZ);

        int totalTrees = 6 + random.nextInt(5); // Randomize the number of trees per chunk
        boolean[][] treePositions = new boolean[16][16]; // Track tree positions to avoid overwriting

        for (int i = 0; i < totalTrees; i++) {
            int x = chunkX + random.nextInt(16);
            int z = chunkZ + random.nextInt(16);
            int y = Math.max(world.getTopSolidOrLiquidBlock(x, z) - 1, 0); // Prevent negative Y

            // Ensure valid chunk & position
            if (!world.blockExists(x, y, z)) continue;  
            if (y < 0 || y > 255) continue;            

            Block block = world.getBlock(x, y, z);
            if (block == Blocks.grass) {
                if (random.nextInt(4) == 0) {
                    new WorldGenPalmTreeA1().generate(world, random, x, y + 1, z);
                } else {
                    new WorldGenTrees(false).generate(world, random, x, y + 1, z);
                }
                treePositions[Math.abs(x % 16)][Math.abs(z % 16)] = true; // Fix negative array index
            }
        }

        // Vegetation pass
        for (int dx = 0; dx < 16; dx++) {
            for (int dz = 0; dz < 16; dz++) {
                int x = chunkX + dx;
                int z = chunkZ + dz;
                int y = Math.max(world.getTopSolidOrLiquidBlock(x, z) - 1, 0); // Prevent negative Y

                if (!world.blockExists(x, y, z)) continue; 
                if (y < 0 || y > 255) continue;            

                if (world.getBlock(x, y, z) == Blocks.grass && !treePositions[dx][dz]) {
                    int chance = random.nextInt(100);
                    if (chance < 70) {
                        world.setBlock(x, y + 1, z, Blocks.tallgrass, 1, 2);
                    } else if (chance < 80) {
                        world.setBlock(x, y + 1, z, Blocks.double_plant, 2, 2);
                        world.setBlock(x, y + 2, z, Blocks.double_plant, 8, 2);
                    } else if (chance < 90) {
                        world.setBlock(x, y + 1, z, Blocks.red_flower, 0, 2);
                    } else {
                        world.setBlock(x, y + 1, z, Blocks.yellow_flower, 0, 2);
                    }
                }
            }
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getBiomeGrassColor(int x, int y, int z) {
        return 8829318;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getBiomeFoliageColor(int x, int y, int z) {
        return 6727262;
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public int getWaterColorMultiplier() {
        return 6928612;
    }

}


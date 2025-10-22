package com.sown.outerrim.dimension.bestine;

import java.util.HashMap;
import java.util.Random;

import com.sown.util.world.creation.ORSubBiome;

import net.minecraft.block.Block;
import net.minecraft.block.BlockGrass;
import net.minecraft.block.BlockTallGrass;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;

public class BiomeGenBestine extends ORSubBiome {
    private static final BiomeGenBase.Height biomeHeight = new BiomeGenBase.Height(-0.2f, 0.2f);

    public BiomeGenBestine(int biomeID) {
        super(biomeID);
        this.zoom = 0.5;
        this.threshold = 0.5;
        this.setHeight(biomeHeight);
        this.setColor(7169874);
        this.waterColorMultiplier = 6204903;
        this.setTemperatureRainfall(0.5f, 0.9f);
        this.topBlock = Blocks.sand;
        this.fillerBlock = Blocks.stone;
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

        int totalPlants = 6 + random.nextInt(5); // Reduce overall tree spawn rate

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

    public int getBiomeFoliageColor(int x, int y, int z) {
        return 11256166;
    }

    public int getBiomeGrassColor(int par1, int par2, int par3) {
        return 11256166;
    }
}

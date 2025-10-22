package com.sown.outerrim.dimension.ilum;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Arrays;
import java.util.Random;

import com.sown.outerrim.registry.BlockRegister;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.NoiseGeneratorPerlin;

public class BiomeGenIlumMountains extends BiomeGenBase {
    private byte[] noiseValues;
    private long seed;
    private NoiseGeneratorPerlin terrainNoise;
    private NoiseGeneratorPerlin snowHeightNoise;

    public BiomeGenIlumMountains(int biomeID) {
        super(biomeID);

        // General biome settings
        this.enableSnow = true; // Snowy environment
        this.setTemperatureRainfall(-0.5F, 0.5F); // Cold and snowy
        this.setHeight(new BiomeGenBase.Height(2.0F, 1.2F)); // Tall and rugged mountains
        this.spawnableCreatureList.clear(); // No passive mobs
        this.spawnableMonsterList.clear(); // No monsters

        // Terrain and block settings
        this.topBlock = Blocks.snow; // Snow on the peaks
        this.fillerBlock = BlockRegister.getRegisteredBlock("ilumRock"); // Ilum rock as filler block
    }

    @Override
    public void decorate(World world, Random random, int chunkX, int chunkZ) {
        super.decorate(world, random, chunkX, chunkZ);

        for (int x = chunkX; x < chunkX + 16; x++) {
            for (int z = chunkZ; z < chunkZ + 16; z++) {
                int y = world.getTopSolidOrLiquidBlock(x, z);

                // Check if the top block is snow and the block below is ilumRock
                if (world.getBlock(x, y - 1, z) == BlockRegister.getRegisteredBlock("ilumRock") &&
                    world.getBlock(x, y, z) == Blocks.snow_layer) {
                    // Replace ilumRock with ilumRockSnowy
                    world.setBlock(x, y - 1, z, BlockRegister.getRegisteredBlock("ilumRockSnowy"), 0, 2);
                }
            }
        }
    }

    @Override
    public void genTerrainBlocks(World world, Random random, Block[] blocks, byte[] meta, int chunkX, int chunkZ, double noiseVal) {
        if (this.noiseValues == null || this.seed != world.getSeed()) {
            generateNoise(world.getSeed());
        }

        this.seed = world.getSeed();
        int x = chunkX & 15;
        int z = chunkZ & 15;
        int blockHeight = blocks.length / 256;
        double snowVariation = this.snowHeightNoise.func_151601_a(chunkX * 0.05, chunkZ * 0.05); // Smooth snow variation

        for (int y = 255; y >= 0; --y) {
            int index = (z * 16 + x) * blockHeight + y;

            if (y <= random.nextInt(5)) {
                blocks[index] = Blocks.bedrock; // Bedrock layer
            } else {
                Block currentBlock = blocks[index];

                if (currentBlock != null && currentBlock.getMaterial() != Material.air) {
                    // Add snow and Ilum rock based on height
                    if (y > 200) {
                        if (random.nextDouble() < 0.3) { // Randomly raise snow layers
                            for (int layer = 0; layer < 3; layer++) {
                                if (y - layer >= 0 && blocks[index - layer * blockHeight] == null) {
                                    blocks[index - layer * blockHeight] = Blocks.snow;
                                }
                            }
                        } else {
                            blocks[index] = Blocks.snow; // Single layer for lower randomness
                        }
                    } else if (y > 120) {
                        blocks[index] = BlockRegister.getRegisteredBlock("ilumRock"); // Rock at mid-levels
                    } else {
                        blocks[index] = BlockRegister.getRegisteredBlock("ilumRock"); // Rock at the base
                    }
                }
            }
        }
    }

    public void generateNoise(long seed) {
        this.noiseValues = new byte[64];
        Arrays.fill(this.noiseValues, (byte) 16);
        Random random = new Random(seed);
        this.terrainNoise = new NoiseGeneratorPerlin(random, 1);
        this.snowHeightNoise = new NoiseGeneratorPerlin(random, 4); // Additional noise for snow height variation
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getBiomeFoliageColor(int x, int y, int z) {
        return 12632256; // A frosty, pale gray color
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getSkyColorByTemp(float temp) {
        return 12306124; // A cold, pale blue sky
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getBiomeGrassColor(int x, int y, int z) {
        return 12632256; // Frosty, snowy grass color
    }
}

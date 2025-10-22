package com.sown.outerrim.dimension.felucia;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Arrays;
import java.util.Random;

import com.sown.outerrim.registry.BlockRegister;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.NoiseGeneratorPerlin;
import net.minecraft.world.gen.feature.WorldGenBigMushroom;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;

public class BiomeGenFeluciaHighlands extends BiomeGenBase {
    private byte[] noiseValues;
    private long seed;
    private NoiseGeneratorPerlin rubbleNoise;

    public BiomeGenFeluciaHighlands(int biomeID) {
        super(biomeID);

        // General biome settings
        this.setTemperatureRainfall(1.5F, 0.9F);
        this.enableSnow = false;
        this.spawnableCreatureList.clear();

        // Terrain and block settings
        this.topBlock = Blocks.dirt;
        this.field_150604_aj = 2;
        this.fillerBlock = Blocks.dirt;

        this.theBiomeDecorator.treesPerChunk = -999; // No trees by default
        this.theBiomeDecorator.deadBushPerChunk = 15;
        this.theBiomeDecorator.cactiPerChunk = 5;
        this.theBiomeDecorator.flowersPerChunk = 0;

        // Spawn settings
        this.spawnableCreatureList.clear();
        this.spawnableMonsterList.clear();
    }

    @Override
    public WorldGenAbstractTree func_150567_a(Random random) {
        return this.worldGeneratorTrees; // Default tree generator (rare usage here)
    }

    @Override
    public void decorate(World world, Random random, int chunkX, int chunkZ) {
        super.decorate(world, random, chunkX, chunkZ);

        // Generate big mushrooms
        for (int i = 0; i < 4; i++) {
            int x = chunkX + random.nextInt(16);
            int z = chunkZ + random.nextInt(16);
            int y = world.getTopSolidOrLiquidBlock(x, z);
            if (world.getBlock(x, y - 1, z) == Blocks.grass || world.getBlock(x, y - 1, z) == Blocks.dirt) {
                WorldGenBigMushroom mushroomGen = new WorldGenBigMushroom(random.nextBoolean() ? 1 : 0);
                mushroomGen.generate(world, random, x, y, z);
            }
        }
    }


    @Override
    public void genTerrainBlocks(World world, Random random, Block[] blocks, byte[] meta, int chunkX, int chunkZ, double noiseVal) {
        if (this.noiseValues == null || this.seed != world.getSeed()) {
            generateNoise(world.getSeed());
        }

        this.seed = world.getSeed();
        int blockHeight = blocks.length / 256; // Height of the chunk (usually 256)

        // Iterate through the chunk horizontally
        for (int x = 0; x < 16; x++) {
            for (int z = 0; z < 16; z++) {
                boolean topmostModified = false; // Track whether the top block has been modified

                // Iterate vertically from top to bottom
                for (int y = 255; y >= 0; --y) {
                    int index = (z * 16 + x) * blockHeight + y; // Calculate index in the block array
                    Block currentBlock = blocks[index];

                    if (!topmostModified && currentBlock != null && currentBlock.getMaterial() != Material.air) {
                        // Modify the topmost solid block
                        int chance = random.nextInt(10);
                        if (chance < 6) {
                            blocks[index] = Blocks.dirt; // Podzol
                            meta[index] = 2; // Podzol metadata
                        } else if (chance == 6) {
                            blocks[index] = Blocks.grass; // Grass
                        } else if (chance == 7) {
                            blocks[index] = Blocks.dirt; // Podzol
                            meta[index] = 2; // Podzol metadata
                        } else {
                            blocks[index] = BlockRegister.getRegisteredBlock("mud"); // Mud
                        }
                        topmostModified = true; // Mark the topmost block as modified
                    }

                    // Stop processing this column after modifying the topmost block
                    if (topmostModified) {
                        break;
                    }
                }
            }
        }
    }

    public void generateNoise(long seed) {
        this.noiseValues = new byte[64];
        Arrays.fill(this.noiseValues, (byte) 16);
        Random random = new Random(seed);
        this.rubbleNoise = new NoiseGeneratorPerlin(random, 1);
    }
    
    @SideOnly(value = Side.CLIENT)
    public Vec3 getFogColor(float par1, float par2) {
        float f2 = MathHelper.cos(par1 * (float) Math.PI * 2.0f) * 2.0f + 0.5f;
        if (f2 < 0.0f) {
            f2 = 0.0f;
        }
        if (f2 > 1.0f) {
            f2 = 1.0f;
        }

        // Decimal color 13342303 -> RGB(203, 230, 255)
        float r = 203 / 255.0f; // Normalize to [0, 1]
        float g = 230 / 255.0f; // Normalize to [0, 1]
        float b = 255 / 255.0f; // Normalize to [0, 1]

        r *= f2 * 0.94f + 0.06f;
        g *= f2 * 0.94f + 0.06f;
        b *= f2 * 0.94f + 0.06f;

        return Vec3.createVectorHelper(r, g, b);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getWaterColorMultiplier() {
        return 0x3DBB3D; // Green water color
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getBiomeFoliageColor(int x, int y, int z) {
        return 8225876; // Dull junkyard foliage (dry and desaturated)
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getSkyColorByTemp(float temp) {
        return 13342303; // Grayish sky for a wasteland feel
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getBiomeGrassColor(int x, int y, int z) {
        return 8747848; // Similar to foliage, indicating dryness
    }
}

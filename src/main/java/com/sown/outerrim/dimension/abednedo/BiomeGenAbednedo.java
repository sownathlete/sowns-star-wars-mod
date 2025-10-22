package com.sown.outerrim.dimension.abednedo;

import java.util.Random;

import com.sown.util.world.creation.ORBiomeDecorator;
import com.sown.util.world.creation.ORSubBiome;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenTrees;
import net.minecraft.world.gen.feature.WorldGenerator;

public class BiomeGenAbednedo extends ORSubBiome {
    private ORBiomeDecorator customBiomeDecorator;
    private static final BiomeGenBase.Height biomeHeight = new BiomeGenBase.Height(0.1f, 0.3f);
    private final WorldGenerator treeGen = new WorldGenTrees(false, 5, 0, 0, false); // Corrected constructor

    public BiomeGenAbednedo(int biomeID) {
        super(biomeID);

        // General properties
        this.setHeight(biomeHeight);
        this.setDisableRain(); // Dry climate
        this.setTemperatureRainfall(2.0f, 0.0f); // Hot and dry
        this.setColor(8411441); // Brownish-green sky color in decimal

        // Top and filler blocks
        this.topBlock = Blocks.grass; // Surface layer
        this.fillerBlock = Blocks.dirt; // Underground filler

        // Clear spawning
        this.spawnableCreatureList.clear();
        this.spawnableMonsterList.clear();
        this.spawnableWaterCreatureList.clear();

        // Adjust biome decorator
        this.customBiomeDecorator = this.theBiomeDecorator;
        this.customBiomeDecorator.treesPerChunk = 2; // Sparse trees
        this.customBiomeDecorator.deadBushPerChunk = 4; // Add dead bushes
        this.customBiomeDecorator.generateLakes = true; // Allow lakes
    }

    @Override
    public void decorate(World world, Random random, int chunkX, int chunkZ) {
        super.decorate(world, random, chunkX, chunkZ);
        generateTrees(world, random, chunkX, chunkZ);
    }

    private void generateTrees(World world, Random random, int chunkX, int chunkZ) {
        for (int i = 0; i < 2; i++) { // Number of trees per chunk
            int x = chunkX + random.nextInt(16);
            int z = chunkZ + random.nextInt(16);
            int y = world.getHeightValue(x, z); // Find the ground height
            if (world.getBlock(x, y - 1, z) == Blocks.grass) { // Ensure it's grass
                treeGen.generate(world, random, x, y, z); // Generate the tree
            }
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getSkyColorByTemp(float temp) {
        return 10469743; // Brownish-green sky
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getWaterColorMultiplier() {
        return 65280; // Green water
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getBiomeFoliageColor(int x, int y, int z) {
        return 9668198; // Dull brownish foliage
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getBiomeGrassColor(int x, int y, int z) {
        return 9668198; // Matches dull brownish grass
    }
}

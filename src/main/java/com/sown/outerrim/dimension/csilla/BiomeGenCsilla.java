package com.sown.outerrim.dimension.csilla;

import java.util.Random;

import com.sown.util.world.creation.ORBiomeDecorator;
import com.sown.util.world.creation.ORSubBiome;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;

public class BiomeGenCsilla extends ORSubBiome {
    private ORBiomeDecorator customBiomeDecorator;
    private static final BiomeGenBase.Height biomeHeight = new BiomeGenBase.Height(0.1f, 0.2f);

    public BiomeGenCsilla(int biomeID) {
        super(biomeID);

        // General properties
        this.setHeight(biomeHeight);
        this.setEnableSnow();
        this.setTemperatureRainfall(0.0f, 0.0f); // Freezing and dry
        this.setColor(8388736); // Light blue-gray in decimal for glaciers

        // Top and filler blocks
        this.topBlock = Blocks.snow; // Glacier surface
        this.fillerBlock = Blocks.packed_ice; // Glacier depth

        // Clear spawning
        this.spawnableCreatureList.clear();
        this.spawnableMonsterList.clear();
        this.spawnableWaterCreatureList.clear();

        // Adjust biome decorator
        this.customBiomeDecorator = this.theBiomeDecorator;
        this.customBiomeDecorator.treesPerChunk = -999;
        this.customBiomeDecorator.deadBushPerChunk = 0;
        this.customBiomeDecorator.generateLakes = false;
    }

    @Override
    public void decorate(World world, Random random, int chunkX, int chunkZ) {
        super.decorate(world, random, chunkX, chunkZ);
        replaceWaterWithIce(world, chunkX, chunkZ);
        generateCaveDecorations(world, random, chunkX, chunkZ);
    }

    private void replaceWaterWithIce(World world, int chunkX, int chunkZ) {
        for (int x = chunkX; x < chunkX + 16; x++) {
            for (int z = chunkZ; z < chunkZ + 16; z++) {
                for (int y = 0; y < 256; y++) {
                    Block currentBlock = world.getBlock(x, y, z);
                    if (currentBlock == Blocks.water || currentBlock == Blocks.flowing_water) {
                        world.setBlock(x, y, z, Blocks.ice); // Replace water with ice
                    }
                }
            }
        }
    }

    private void generateCaveDecorations(World world, Random random, int chunkX, int chunkZ) {
        for (int i = 0; i < 5; i++) { // Generate stalactites and stalagmites in caves
            int x = chunkX + random.nextInt(16);
            int z = chunkZ + random.nextInt(16);
            int y = random.nextInt(40) + 10; // Restrict to cave depths
            if (world.getBlock(x, y, z) == Blocks.air && world.getBlock(x, y + 1, z) == Blocks.stone) {
                world.setBlock(x, y, z, Blocks.packed_ice);
            }
            if (world.getBlock(x, y - 1, z) == Blocks.stone) {
                world.setBlock(x, y - 1, z, Blocks.ice);
            }
        }
    }

    @SideOnly(Side.CLIENT)
    public int getSkyColorByTemp(float temp) {
        return 49087; // Cold blue in decimal
    }

    @SideOnly(Side.CLIENT)
    public int getWaterColorMultiplier() {
        return 49087; // Light blue water color
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getBiomeFoliageColor(int x, int y, int z) {
        return 49087; // No foliage, but matches the cold theme
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getBiomeGrassColor(int x, int y, int z) {
        return 49087; // Matches the icy blue theme
    }
}

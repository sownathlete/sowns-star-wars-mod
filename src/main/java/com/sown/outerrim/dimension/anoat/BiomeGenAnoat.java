package com.sown.outerrim.dimension.anoat;

import java.util.Random;

import com.sown.util.world.creation.ORBiomeDecorator;
import com.sown.util.world.creation.ORSubBiome;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import com.sown.outerrim.registry.FluidRegister;
import com.sown.outerrim.registry.BlockRegister;
import com.sown.outerrim.fluids.BlockFluidCustom;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.fluids.Fluid;

public class BiomeGenAnoat extends ORSubBiome {
    private ORBiomeDecorator customBiomeDecorator;
    private static final BiomeGenBase.Height biomeHeight = new BiomeGenBase.Height(0.2f, 0.1f);

    public BiomeGenAnoat(int biomeID) {
        super(biomeID);

        // General properties
        this.setHeight(biomeHeight);
        this.setDisableRain();
        this.setTemperatureRainfall(2.0f, 0.0f); // Super hot and dry
        this.setColor(14323558); // Polluted brown-gray sky in decimal

        // Top and filler blocks
        this.topBlock = Blocks.hardened_clay;
        this.fillerBlock = Blocks.stone;

        // Clear spawning
        this.spawnableCreatureList.clear();
        this.spawnableMonsterList.clear();
        this.spawnableWaterCreatureList.clear();

        // Adjust biome decorator
        this.customBiomeDecorator = this.theBiomeDecorator;
        this.customBiomeDecorator.treesPerChunk = -999;
        this.customBiomeDecorator.deadBushPerChunk = 2;
        this.customBiomeDecorator.generateLakes = false;
    }

    @Override
    public void decorate(World world, Random random, int chunkX, int chunkZ) {
        super.decorate(world, random, chunkX, chunkZ);
        replaceWaterWithAcid(world, chunkX, chunkZ);
    }

    private void replaceWaterWithAcid(World world, int chunkX, int chunkZ) {
        for (int x = chunkX; x < chunkX + 16; x++) {
            for (int z = chunkZ; z < chunkZ + 16; z++) {
                for (int y = 0; y < 256; y++) {
                    if (world.getBlock(x, y, z) == Blocks.water || world.getBlock(x, y, z) == Blocks.flowing_water) {
                        world.setBlock(x, y, z, Blocks.air);
                    }
                }
            }
        }
    }

    @SideOnly(Side.CLIENT)
    public int getSkyColorByTemp(float temp) {
        return 14323558; // Polluted sky
    }

    @SideOnly(Side.CLIENT)
    public int getWaterColorMultiplier() {
        return 14323558; // Polluted water
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getBiomeFoliageColor(int x, int y, int z) {
        return 14323558; // Polluted foliage
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getBiomeGrassColor(int x, int y, int z) {
        return 14323558; // Polluted grass
    }
}

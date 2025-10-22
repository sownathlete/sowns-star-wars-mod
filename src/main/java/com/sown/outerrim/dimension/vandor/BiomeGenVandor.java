package com.sown.outerrim.dimension.vandor;

import java.util.Random;

import com.sown.outerrim.registry.BlockRegister;
import com.sown.util.world.creation.ORBiomeDecorator;
import com.sown.util.world.creation.ORSubBiome;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;

public class BiomeGenVandor extends ORSubBiome {
    private ORBiomeDecorator customBiomeDecorator;
    private static final BiomeGenBase.Height biomeHeight = new BiomeGenBase.Height(0.6f, 0.7f);

    public BiomeGenVandor(int biomeID) {
        super(biomeID);

        this.setHeight(biomeHeight);
        this.setEnableSnow();
        this.setTemperatureRainfall(0.0f, 0.2f);
        this.setColor(8388736);

        this.topBlock = BlockRegister.getRegisteredBlock("snowyStone");
        this.fillerBlock = Blocks.stone;

        this.spawnableCreatureList.clear();
        this.spawnableMonsterList.clear();
        this.spawnableWaterCreatureList.clear();

        this.customBiomeDecorator = this.theBiomeDecorator;
        this.customBiomeDecorator.treesPerChunk = 1; // Sparse trees
        this.customBiomeDecorator.deadBushPerChunk = 0;
        this.customBiomeDecorator.generateLakes = false;
    }

    @Override
    public void decorate(World world, Random random, int chunkX, int chunkZ) {
        super.decorate(world, random, chunkX, chunkZ);
        replaceWaterWithIce(world, chunkX, chunkZ);
    }

    private void replaceWaterWithIce(World world, int chunkX, int chunkZ) {
        for (int x = chunkX; x < chunkX + 16; x++) {
            for (int z = chunkZ; z < chunkZ + 16; z++) {
                for (int y = 0; y < 256; y++) {
                    Block currentBlock = world.getBlock(x, y, z);
                    if (currentBlock == Blocks.water || currentBlock == Blocks.flowing_water) {
                        world.setBlock(x, y, z, Blocks.ice);
                    }
                }
            }
        }
    }

    @SideOnly(Side.CLIENT)
    public int getSkyColorByTemp(float temp) {
        return 8562892;
    }

    @SideOnly(Side.CLIENT)
    public int getWaterColorMultiplier() {
        return 4808311;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getBiomeFoliageColor(int x, int y, int z) {
        return 9343637;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getBiomeGrassColor(int x, int y, int z) {
        return 9343637;
    }
}

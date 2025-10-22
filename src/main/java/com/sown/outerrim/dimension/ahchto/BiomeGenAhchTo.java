package com.sown.outerrim.dimension.ahchto;

import java.util.Random;

import com.sown.outerrim.fluids.BlockFluidCustom;
import com.sown.outerrim.registry.FluidRegister;
import com.sown.util.world.creation.ORBiomeDecorator;
import com.sown.util.world.creation.OROverworldBiomeDecorator;
import com.sown.util.world.creation.ORSubBiome;
import com.sown.util.world.creation.OverworldBiomeFeatures;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenTrees;
import net.minecraft.world.gen.feature.WorldGenerator;

public class BiomeGenAhchTo extends ORSubBiome {
    private ORBiomeDecorator customBiomeDecorator;
    private static final BiomeGenBase.Height biomeHeight = new BiomeGenBase.Height(-0.2f, 0.5f); // Tall rocky islands

    public BiomeGenAhchTo(int biomeID) {
        super(biomeID);

        // General properties
        this.setHeight(biomeHeight);
        this.setTemperatureRainfall(0.8f, 0.4f); // Mild and humid
        this.setColor(5642560); // Lush green tone

        // Top and filler blocks
        this.topBlock = Blocks.grass; // Surface layer
        this.fillerBlock = Blocks.stone; // Rocky core

        // Clear spawning lists
        this.spawnableCreatureList.clear();
        this.spawnableMonsterList.clear();

        // Adjust biome decorator
        this.customBiomeDecorator = this.theBiomeDecorator;
        this.customBiomeDecorator.grassPerChunk = 5; // Patches of grass
        this.customBiomeDecorator.flowersPerChunk = 2; // Sparse flowers
        this.customBiomeDecorator.treesPerChunk = -999; // Sparse flowers
    }

    @Override
    public void decorate(World world, Random random, int chunkX, int chunkZ) {
        super.decorate(world, random, chunkX, chunkZ);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getSkyColorByTemp(float temp) {
        return 7842047; // Calm light blue sky
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getBiomeFoliageColor(int x, int y, int z) {
        return 6722345; // Lush green foliage
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getBiomeGrassColor(int x, int y, int z) {
        return 6722345; // Matches lush green grass
    }
}

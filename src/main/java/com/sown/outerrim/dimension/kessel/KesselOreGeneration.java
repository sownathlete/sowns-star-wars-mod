package com.sown.outerrim.dimension.kessel;

import java.util.Random;

import com.sown.outerrim.OuterRimResources;
import com.sown.outerrim.registry.BlockRegister;

import cpw.mods.fml.common.IWorldGenerator;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;

public class KesselOreGeneration implements IWorldGenerator {

    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator,
            IChunkProvider chunkProvider) {
        int dimensionId = world.provider.dimensionId;

        // Dynamically get the Kessel dimension ID
        int kesselDimId = OuterRimResources.ConfigOptions.getDimId("kessel");

        if (dimensionId == 1) {
            generateEnd(world, random, chunkX, chunkZ);
        } else if (dimensionId == 0) {
            generateOverworld(world, random, chunkX, chunkZ);
        } else if (dimensionId == -1) {
            generateNether(world, random, chunkX, chunkZ);
        } else if (dimensionId == kesselDimId) {
            generateKessel(world, random, chunkX, chunkZ);
        }
    }

    public void generateEnd(World world, Random rand, int x, int z) {

    }

    public void generateOverworld(World world, Random rand, int x, int z) {

    }

    public void generateKessel(World world, Random rand, int x, int z) {
        // Coaxium ore (Rare but large veins)
        generateOre(BlockRegister.getRegisteredBlock("coaxiumOre1"), world, rand, x, z, 8, 10, 3, 5, 30, BlockRegister.getRegisteredBlock("kesselRock1"));
        
        // Coal ore (More common, higher range)
        generateOre(BlockRegister.getRegisteredBlock("kesselCoalOre"), world, rand, x, z, 4, 16, 50, 0, 100, BlockRegister.getRegisteredBlock("kesselRock1"));
        
        // Iron ore (Common, slightly higher range)
        generateOre(BlockRegister.getRegisteredBlock("kesselIronOre"), world, rand, x, z, 4, 12, 38, 0, 90, BlockRegister.getRegisteredBlock("kesselRock1"));
        
        // Gold ore (Less common, middle depth)
        generateOre(BlockRegister.getRegisteredBlock("kesselGoldOre"), world, rand, x, z, 2, 8, 13, 0, 50, BlockRegister.getRegisteredBlock("kesselRock1"));
        
        // Diamond ore (Rare, limited depth)
        generateOre(BlockRegister.getRegisteredBlock("kesselDiamondOre"), world, rand, x, z, 1, 6, 8, 0, 40, BlockRegister.getRegisteredBlock("kesselRock1"));
        
        // Quartz ore (Adjusted for your settings)
        generateOre(BlockRegister.getRegisteredBlock("kesselQuartzOre"), world, rand, x, z, 4, 14, 25, 0, 80, BlockRegister.getRegisteredBlock("kesselRock1"));
        
        // Lapis Lazuli ore (Uncommon, deeper)
        generateOre(BlockRegister.getRegisteredBlock("kesselLapisOre"), world, rand, x, z, 3, 7, 10, 0, 60, BlockRegister.getRegisteredBlock("kesselRock1"));
        
        // Emerald ore (Very rare, very deep)
        generateOre(BlockRegister.getRegisteredBlock("kesselEmeraldOre"), world, rand, x, z, 2, 4, 3, 0, 30, BlockRegister.getRegisteredBlock("kesselRock1"));
        
        // Redstone ore (Common, deeper)
        generateOre(BlockRegister.getRegisteredBlock("kesselRedstoneOre"), world, rand, x, z, 5, 10, 25, 0, 60, BlockRegister.getRegisteredBlock("kesselRock1"));

        generateOre(BlockRegister.getRegisteredBlock("coaxiumOre2"), world, rand, x, z, 8, 10, 3, 5, 30, Blocks.stone);
    }

    public void generateNether(World world, Random rand, int x, int z) {

    }

    public void generateOre(Block block, World world, Random random, int chunkX, int chunkZ, int minVeinSize,
            int maxVeinSize, int chance, int minY, int maxY, Block generateIn) {
        int veinSize = minVeinSize + random.nextInt(maxVeinSize - minVeinSize);
        int heightRange = maxY - minY;
        WorldGenMinable gen = new WorldGenMinable(block, veinSize, generateIn);
        for (int i = 0; i < chance; i++) {
            int xRand = chunkX * 16 + random.nextInt(16);
            int yRand = random.nextInt(heightRange) + minY;
            int zRand = chunkZ * 16 + random.nextInt(16);
            gen.generate(world, random, xRand, yRand, zRand);
        }
    }
}

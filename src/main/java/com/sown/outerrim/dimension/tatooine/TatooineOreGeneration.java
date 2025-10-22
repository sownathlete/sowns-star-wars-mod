package com.sown.outerrim.dimension.tatooine;

import java.util.Random;
import com.sown.outerrim.OuterRimResources;
import com.sown.outerrim.registry.BlockRegister;
import cpw.mods.fml.common.IWorldGenerator;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;

public class TatooineOreGeneration implements IWorldGenerator {

    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
        int dimensionId = world.provider.dimensionId;

        // Get Tatooine dimension ID
        int tatooineDimId = OuterRimResources.ConfigOptions.getDimId("tatooine");

        if (dimensionId == tatooineDimId) {
            generateTatooine(world, random, chunkX, chunkZ);
        }
    }

    public void generateTatooine(World world, Random rand, int x, int z) {
        // Reduced spawn chances compared to Kessel
        generateOre(BlockRegister.getRegisteredBlock("tatooineCoalOre"), world, rand, x, z, 3, 10, 15, 0, 80, BlockRegister.getRegisteredBlock("tatooineRock"));
        generateOre(BlockRegister.getRegisteredBlock("tatooineIronOre"), world, rand, x, z, 3, 8, 12, 0, 70, BlockRegister.getRegisteredBlock("tatooineRock"));
        generateOre(BlockRegister.getRegisteredBlock("tatooineGoldOre"), world, rand, x, z, 2, 6, 8, 0, 40, BlockRegister.getRegisteredBlock("tatooineRock"));
        generateOre(BlockRegister.getRegisteredBlock("tatooineDiamondOre"), world, rand, x, z, 1, 4, 3, 5, 35, BlockRegister.getRegisteredBlock("tatooineRock"));
        generateOre(BlockRegister.getRegisteredBlock("tatooineQuartzOre"), world, rand, x, z, 3, 10, 7, 0, 60, BlockRegister.getRegisteredBlock("tatooineRock"));
        generateOre(BlockRegister.getRegisteredBlock("tatooineLapisOre"), world, rand, x, z, 2, 5, 5, 10, 50, BlockRegister.getRegisteredBlock("tatooineRock"));
        generateOre(BlockRegister.getRegisteredBlock("tatooineEmeraldOre"), world, rand, x, z, 1, 3, 2, 5, 30, BlockRegister.getRegisteredBlock("tatooineRock"));
        generateOre(BlockRegister.getRegisteredBlock("tatooineRedstoneOre"), world, rand, x, z, 2, 6, 10, 5, 50, BlockRegister.getRegisteredBlock("tatooineRock"));
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

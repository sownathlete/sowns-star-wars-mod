package com.sown.outerrim.dimension.ilum;

import java.util.Random;

import com.sown.outerrim.OuterRimResources;
import com.sown.outerrim.registry.BlockRegister;
import com.sown.outerrim.worldgen.WorldGenCustomCrystalCaves;

import cpw.mods.fml.common.IWorldGenerator;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;

public class IlumOreGeneration implements IWorldGenerator {

    private WorldGenCustomCrystalCaves crystalCaveGen = new WorldGenCustomCrystalCaves();

    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator,
                         IChunkProvider chunkProvider) {
        int dimensionId = world.provider.dimensionId;

        // Check for Ilum dimension
        if (dimensionId == OuterRimResources.ConfigOptions.getDimId("ilum")) {
            generateIlum(world, random, chunkX, chunkZ);
        }
    }

    public void generateIlum(World world, Random rand, int chunkX, int chunkZ) {
        if (Loader.isModLoaded("legends")) {
            Block kyberCrystalBlock = GameRegistry.findBlock("legends", "kyberCrystalBlock");
            Block ilumRock = BlockRegister.getRegisteredBlock("ilumRock");

            if (kyberCrystalBlock != null && ilumRock != null) {
                generateCrystalCaves(world, rand, chunkX, chunkZ, kyberCrystalBlock, ilumRock);
            }
        }
        generateOre(BlockRegister.getRegisteredBlock("ilumCoalOre"), world, rand, chunkX, chunkZ, 4, 16, 40, 0, 100, BlockRegister.getRegisteredBlock("ilumRock"));
        generateOre(BlockRegister.getRegisteredBlock("ilumIronOre"), world, rand, chunkX, chunkZ, 4, 12, 30, 0, 90, BlockRegister.getRegisteredBlock("ilumRock"));
        generateOre(BlockRegister.getRegisteredBlock("ilumGoldOre"), world, rand, chunkX, chunkZ, 2, 8, 10, 0, 50, BlockRegister.getRegisteredBlock("ilumRock"));
        generateOre(BlockRegister.getRegisteredBlock("ilumDiamondOre"), world, rand, chunkX, chunkZ, 1, 6, 6, 0, 40, BlockRegister.getRegisteredBlock("ilumRock"));
        generateOre(BlockRegister.getRegisteredBlock("ilumQuartzOre"), world, rand, chunkX, chunkZ, 4, 14, 20, 0, 80, BlockRegister.getRegisteredBlock("ilumRock"));
        generateOre(BlockRegister.getRegisteredBlock("ilumLapisOre"), world, rand, chunkX, chunkZ, 3, 7, 8, 0, 60, BlockRegister.getRegisteredBlock("ilumRock"));
        generateOre(BlockRegister.getRegisteredBlock("ilumEmeraldOre"), world, rand, chunkX, chunkZ, 2, 4, 2, 0, 30, BlockRegister.getRegisteredBlock("ilumRock"));
        generateOre(BlockRegister.getRegisteredBlock("ilumRedstoneOre"), world, rand, chunkX, chunkZ, 5, 10, 20, 0, 60, BlockRegister.getRegisteredBlock("ilumRock"));
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

    private void generateCrystalCaves(World world, Random random, int chunkX, int chunkZ, Block kyberCrystalBlock, Block ilumRock) {
        int[] validColors = generateValidColors();

        // Adjust spawn attempts for a reasonable density
        int spawnAttempts = 100;

        for (int i = 0; i < spawnAttempts; i++) {
            int xRand = chunkX * 16 + random.nextInt(16);
            int yRand = random.nextInt(60) + 5; // Between minHeight and maxHeight
            int zRand = chunkZ * 16 + random.nextInt(16);

            if (isValidPlacement(world, xRand, yRand, zRand, ilumRock)) {
                int randomColorID = validColors[random.nextInt(validColors.length)];

                // Place the Kyber Crystal block
                world.setBlock(xRand, yRand, zRand, kyberCrystalBlock, 0, 2);

                // Use reflection to set the colorID on the TileEntity
                TileEntity tileEntity = world.getTileEntity(xRand, yRand, zRand);
                if (tileEntity != null) {
                    try {
                        Class<?> tileEntityClass = tileEntity.getClass();
                        java.lang.reflect.Method setColorMethod = tileEntityClass.getMethod("setColor", int.class);
                        setColorMethod.invoke(tileEntity, randomColorID);
                    } catch (Exception e) {
                        System.err.println("Failed to set colorID for Kyber Crystal: " + e.getMessage());
                    }
                }
            }
        }
    }
    
    private boolean isValidPlacement(World world, int x, int y, int z, Block ilumRock) {
        // Ensure the block below is the specified block and the current position is air
        Block blockBelow = world.getBlock(x, y - 1, z);
        boolean isRockBelow = blockBelow != null && blockBelow.equals(ilumRock);
        boolean isAirBlock = world.isAirBlock(x, y, z);

        return isRockBelow && isAirBlock;
    }

    private int[] generateValidColors() {
        return new int[]{
                0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, // blue (20)
                1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, // blue_dark (20)
                2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, // blue_light (20)
                3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, // blue_arctic (50)
                4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, // green (20)
                5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, // green_lime (20)
                6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, // green_mint (20)
                7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, // green_viridian (20)
                8, 8, 8, 8, 8, // yellow (5)
                9, 9, 9, 9, 9, // yellow_bloom (5)
                10, 10, 10, 10, 10, // yellow_amber (5)
                11, 11, 11, 11, 11  // yellow_canary (5)
//            12, // red
//            13, // red_blood
//            14, // red_dark
//            15, // red_acolyte
//            16, // orange
//            17, // orange_reserved
//            18, // purple
//            19, // purple_derelict
//            20, // purple_amethyst
//            21, // gold
//            22, // bronze
//            23, // silver
//            24, // white
//            25, // grey
//            26, // cyan
//            27, // magenta
//            28, // pink
//            29, // indigo
//            30, // blue_cracked
//            31, // blue_dark_cracked
//            32, // blue_light_cracked
//            33, // blue_arctic_cracked
//            34, // green_cracked
//            35, // green_lime_cracked
//            36, // green_mint_cracked
//            37, // green_viridian_cracked
//            38, // yellow_cracked
//            39, // yellow_bloom_cracked
//            40, // yellow_amber_cracked
//            41, // yellow_canary_cracked
//            42, // red_cracked
//            43, // red_blood_cracked
//            44, // red_dark_cracked
//            45, // red_acolyte_cracked
//            46, // orange_cracked
//            47, // orange_reserved_cracked
//            48, // purple_cracked
//            49, // purple_derelict_cracked
//            50, // purple_amethyst_cracked
//            51, // gold_cracked
//            52, // bronze_cracked
//            53, // silver_cracked
//            54, // white_cracked
//            55, // grey_cracked
//            56, // cyan_cracked
//            57, // magenta_cracked
//            58, // pink_cracked
//            59, // indigo_cracked
//            60  // executioner
        };
    }
}

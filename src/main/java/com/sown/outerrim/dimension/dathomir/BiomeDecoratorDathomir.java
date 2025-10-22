package com.sown.outerrim.dimension.dathomir;

import java.util.Random;

import com.sown.outerrim.registry.BlockRegister;
import com.sown.outerrim.world.gen.WorldGenDarkTree;
import com.sown.util.world.creation.OROverworldBiomeDecorator;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.terraingen.DecorateBiomeEvent;

public class BiomeDecoratorDathomir extends OROverworldBiomeDecorator {
    public int darkTreesPerChunk = 0;
    public int lakesPerChunk = 0;
    public int grassPerChunk = 0;
    public int reedsPerChunk = 0;
    public int mushroomsPerChunk = 0;
    public int deadBushPerChunk = 0;
    public int clayPerChunk = 0;
    public int waterlilyPerChunk = 0;

    @Override
    public void decorateChunk(World world, Random rand, BiomeGenBase biome, int chunkX, int chunkZ) {
        super.decorateChunk(world, rand, biome, chunkX, chunkZ);

        int xStart = chunkX << 4;
        int zStart = chunkZ << 4;

        MinecraftForge.EVENT_BUS.post(new DecorateBiomeEvent.Pre(world, rand, xStart, zStart));

        // Dark Trees
        for (int i = 0; i < darkTreesPerChunk; i++) {
            int x = xStart + rand.nextInt(16);
            int z = zStart + rand.nextInt(16);
            int y = world.getHeightValue(x, z);

            if (y > 60 && y < 100) {
                new WorldGenDarkTreeBrute().generate(world, rand, x, y, z);
            }
        }

        // Lakes
        for (int i = 0; i < lakesPerChunk; i++) {
            if (rand.nextInt(10) == 0) {
                int x = xStart + rand.nextInt(16);
                int z = zStart + rand.nextInt(16);
                int y = getSafeGroundY(world, x, z);
                if (y > 60 && y < 80) {
                    world.setBlock(x, y, z, Blocks.water);
                }
            }
        }

        // Reeds
        for (int i = 0; i < reedsPerChunk; i++) {
            int x = xStart + rand.nextInt(16);
            int z = zStart + rand.nextInt(16);
            int y = getSafeGroundY(world, x, z);
            for (int h = 0; h < 3; h++) {
                world.setBlock(x, y + h, z, Blocks.reeds);
            }
        }

        // Mushrooms
        for (int i = 0; i < mushroomsPerChunk; i++) {
            int x = xStart + rand.nextInt(16);
            int z = zStart + rand.nextInt(16);
            int y = getSafeGroundY(world, x, z);
            world.setBlock(x, y, z, rand.nextBoolean() ? Blocks.brown_mushroom : Blocks.red_mushroom);
        }

        // Grass
        for (int i = 0; i < grassPerChunk; i++) {
            int x = xStart + rand.nextInt(16);
            int z = zStart + rand.nextInt(16);
            int y = getSafeGroundY(world, x, z);
            world.setBlock(x, y, z, Blocks.tallgrass, 1, 2); // 1 = grass
        }

        // Dead Bushes
        for (int i = 0; i < deadBushPerChunk; i++) {
            int x = xStart + rand.nextInt(16);
            int z = zStart + rand.nextInt(16);
            int y = getSafeGroundY(world, x, z);
            world.setBlock(x, y, z, Blocks.deadbush);
        }

        // Clay (swamp-style)
        for (int i = 0; i < clayPerChunk; i++) {
            int x = xStart + rand.nextInt(16);
            int z = zStart + rand.nextInt(16);
            int y = world.getTopSolidOrLiquidBlock(x, z);
            world.setBlock(x, y - 1, z, Blocks.clay);
        }

        // Water Lilies
        for (int i = 0; i < waterlilyPerChunk; i++) {
            int x = xStart + rand.nextInt(16);
            int z = zStart + rand.nextInt(16);
            int y = getSafeGroundY(world, x, z);
            if (world.getBlock(x, y - 1, z) == Blocks.water && world.isAirBlock(x, y, z)) {
                world.setBlock(x, y, z, Blocks.waterlily);
            }
        }

        MinecraftForge.EVENT_BUS.post(new DecorateBiomeEvent.Post(world, rand, xStart, zStart));
    }
    
    private int getSafeGroundY(World world, int x, int z) {
        for (int y = 255; y > 0; y--) {
            Block block = world.getBlock(x, y - 1, z);
            if (block == BlockRegister.getRegisteredBlock("dathomirDirt") ||
                block == BlockRegister.getRegisteredBlock("dathomirSlate") ||
                block == Blocks.soul_sand) {
                return y;
            }
        }
        return -1;
    }

}

/*
 * Decompiled with CFR 0.148.
 *
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.material.Material
 *  net.minecraft.world.World
 *  net.minecraft.world.gen.feature.WorldGenerator
 */
package com.sown.util.world.creation.worldgen;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.fluids.Fluid;

public class WorldGenHelper {
    public static void generateStructure(WorldGenerator worldGen, World world, Random rand, int x, int y, int z) {
        y = world.getTopSolidOrLiquidBlock(x += 8, z += 8);
        worldGen.generate(world, rand, x, y, z);
    }

    public static void generateStructureWithRandom(WorldGenerator worldGen, World world, Random rand, int x, int y, int z, int randomAmount) {
        WorldGenHelper.generateStructure(worldGen, world, rand, rand.nextInt(randomAmount), 0, rand.nextInt(randomAmount));
    }

    public static void generateLake(World world, Random rand, int x, int y, int z, Block fluid, Block block) {
        y = world.getTopSolidOrLiquidBlock(x += 8, z += 8) - 2;
        new WorldGenOuterRimLake(fluid).generate(world, rand, x, y, z, block);
    }
    
    public static void generateLake(World world, Random random, int x, int y, int z, Fluid fluid, Block block) {
        // Insert the implementation of the method here.
        // You can use the fluid parameter to generate the lake with the specified fluid.
    }

    public static boolean checkValidSpawn(World world, int x, int y, int z, int size) {
        if (!world.checkChunksExist(x, y, z, x + size, y + size, z + size))
            return false;
        int i = y;
        while (i > 5 && world.isAirBlock(x, y, z) || world.getBlock(x, y, z).getMaterial().isLiquid()) {
            --y;
        }
        if (y <= 4)
            return false;
        for (i = -size; i <= size; ++i) {
            for (int j = -size; j <= size; ++j) {
                if ((!world.isAirBlock(x, y - 1, z) || !world.isAirBlock(x, y - 2, z)) && (!world.getBlock(x, y - 1, z).getMaterial().isLiquid() || !world.getBlock(x, y - 2, z).getMaterial().isLiquid())) {
                    continue;
                }
                return false;
            }
        }
        return true;
    }
}


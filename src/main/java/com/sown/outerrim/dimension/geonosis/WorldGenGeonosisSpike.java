/*
 * Decompiled with CFR 0.148.
 *
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.init.Blocks
 *  net.minecraft.util.Direction
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 *  net.minecraft.world.gen.feature.WorldGenAbstractTree
 *  net.minecraftforge.common.IPlantable
 *  net.minecraftforge.common.util.ForgeDirection
 */
package com.sown.outerrim.dimension.geonosis;

import java.util.Random;

import com.sown.outerrim.OuterRim;
import com.sown.outerrim.registry.BlockRegister;

import net.minecraft.block.Block;
import net.minecraft.block.BlockSapling;
import net.minecraft.init.Blocks;
import net.minecraft.util.Direction;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.util.ForgeDirection;

public class WorldGenGeonosisSpike
extends WorldGenerator {
    @Override
    public boolean generate(World world, Random rand, int x, int y, int z) {
        while (world.isAirBlock(x, y, z) && y > 2) {
            --y;
        }
        for (int var7 = -2; var7 <= 2; ++var7) {
            for (int var8 = -2; var8 <= 2; ++var8) {
                if (!world.isAirBlock(x + var7, y - 1, z + var8) || !world.isAirBlock(x + var7, y - 2, z + var8)) {
                    continue;
                }
                return false;
            }
        }
        return this.generatreStructure(world, rand, x, y, z);
    }

    public boolean generatreStructure(World world, Random rand, int x, int y, int z) {
        world.setBlock(x, y, z, BlockRegister.getRegisteredBlock("geonosisRock"));
        world.setBlock(x - 1, y, z, BlockRegister.getRegisteredBlock("geonosisRock"));
        world.setBlock(x + 1, y, z, BlockRegister.getRegisteredBlock("geonosisRock"));
        world.setBlock(x, y, z - 1, BlockRegister.getRegisteredBlock("geonosisRock"));
        world.setBlock(x, y, z + 1, BlockRegister.getRegisteredBlock("geonosisRock"));
        world.setBlock(x - 1, y, z - 1, BlockRegister.getRegisteredBlock("geonosisRock"));
        world.setBlock(x + 1, y, z + 1, BlockRegister.getRegisteredBlock("geonosisRock"));
        world.setBlock(x - 1, y, z + 1, BlockRegister.getRegisteredBlock("geonosisRock"));
        world.setBlock(x + 1, y, z - 1, BlockRegister.getRegisteredBlock("geonosisRock"));
        world.setBlock(x - 2, y, z - 1, BlockRegister.getRegisteredBlock("geonosisRock"));
        world.setBlock(x - 2, y, z, BlockRegister.getRegisteredBlock("geonosisRock"));
        world.setBlock(x - 2, y, z + 1, BlockRegister.getRegisteredBlock("geonosisRock"));
        world.setBlock(x + 2, y, z - 1, BlockRegister.getRegisteredBlock("geonosisRock"));
        world.setBlock(x + 2, y, z, BlockRegister.getRegisteredBlock("geonosisRock"));
        world.setBlock(x + 2, y, z + 1, BlockRegister.getRegisteredBlock("geonosisRock"));
        world.setBlock(x - 1, y, z - 2, BlockRegister.getRegisteredBlock("geonosisRock"));
        world.setBlock(x, y, z - 2, BlockRegister.getRegisteredBlock("geonosisRock"));
        world.setBlock(x + 1, y, z - 2, BlockRegister.getRegisteredBlock("geonosisRock"));
        world.setBlock(x - 1, y, z + 2, BlockRegister.getRegisteredBlock("geonosisRock"));
        world.setBlock(x, y, z + 2, BlockRegister.getRegisteredBlock("geonosisRock"));
        world.setBlock(x + 1, y, z + 2, BlockRegister.getRegisteredBlock("geonosisRock"));
        world.setBlock(x, y + 1, z, BlockRegister.getRegisteredBlock("geonosisRock"));
        world.setBlock(x - 1, y + 1, z, BlockRegister.getRegisteredBlock("geonosisRock"));
        world.setBlock(x + 1, y + 1, z, BlockRegister.getRegisteredBlock("geonosisRock"));
        world.setBlock(x, y + 1, z - 1, BlockRegister.getRegisteredBlock("geonosisRock"));
        world.setBlock(x, y + 1, z + 1, BlockRegister.getRegisteredBlock("geonosisRock"));
        world.setBlock(x - 1, y + 1, z - 1, BlockRegister.getRegisteredBlock("geonosisRock"));
        world.setBlock(x + 1, y + 1, z + 1, BlockRegister.getRegisteredBlock("geonosisRock"));
        world.setBlock(x - 1, y + 1, z + 1, BlockRegister.getRegisteredBlock("geonosisRock"));
        world.setBlock(x + 1, y + 1, z - 1, BlockRegister.getRegisteredBlock("geonosisRock"));
        world.setBlock(x - 2, y + 1, z - 1, BlockRegister.getRegisteredBlock("geonosisRock"));
        world.setBlock(x - 2, y + 1, z, BlockRegister.getRegisteredBlock("geonosisRock"));
        world.setBlock(x - 2, y + 1, z + 1, BlockRegister.getRegisteredBlock("geonosisRock"));
        world.setBlock(x + 2, y + 1, z - 1, BlockRegister.getRegisteredBlock("geonosisRock"));
        world.setBlock(x + 2, y + 1, z, BlockRegister.getRegisteredBlock("geonosisRock"));
        world.setBlock(x + 2, y + 1, z + 1, BlockRegister.getRegisteredBlock("geonosisRock"));
        world.setBlock(x - 1, y + 1, z - 2, BlockRegister.getRegisteredBlock("geonosisRock"));
        world.setBlock(x, y + 1, z - 2, BlockRegister.getRegisteredBlock("geonosisRock"));
        world.setBlock(x + 1, y + 1, z - 2, BlockRegister.getRegisteredBlock("geonosisRock"));
        world.setBlock(x - 1, y + 1, z + 2, BlockRegister.getRegisteredBlock("geonosisRock"));
        world.setBlock(x, y + 1, z + 2, BlockRegister.getRegisteredBlock("geonosisRock"));
        world.setBlock(x + 1, y + 1, z + 2, BlockRegister.getRegisteredBlock("geonosisRock"));
        world.setBlock(x - 1, y + 2, z, BlockRegister.getRegisteredBlock("geonosisRock"));
        world.setBlock(x + 1, y + 2, z, BlockRegister.getRegisteredBlock("geonosisRock"));
        world.setBlock(x, y + 2, z - 1, BlockRegister.getRegisteredBlock("geonosisRock"));
        world.setBlock(x, y + 2, z + 1, BlockRegister.getRegisteredBlock("geonosisRock"));
        world.setBlock(x - 1, y + 2, z - 1, BlockRegister.getRegisteredBlock("geonosisRock"));
        world.setBlock(x + 1, y + 2, z + 1, BlockRegister.getRegisteredBlock("geonosisRock"));
        world.setBlock(x - 1, y + 2, z + 1, BlockRegister.getRegisteredBlock("geonosisRock"));
        world.setBlock(x + 1, y + 2, z - 1, BlockRegister.getRegisteredBlock("geonosisRock"));
        world.setBlock(x, y + 2, z, BlockRegister.getRegisteredBlock("geonosisRock"));
        world.setBlock(x - 1, y + 3, z, BlockRegister.getRegisteredBlock("geonosisRock"));
        world.setBlock(x + 1, y + 3, z, BlockRegister.getRegisteredBlock("geonosisRock"));
        world.setBlock(x, y + 3, z - 1, BlockRegister.getRegisteredBlock("geonosisRock"));
        world.setBlock(x, y + 3, z + 1, BlockRegister.getRegisteredBlock("geonosisRock"));
        world.setBlock(x - 1, y + 3, z - 1, BlockRegister.getRegisteredBlock("geonosisRock"));
        world.setBlock(x + 1, y + 3, z + 1, BlockRegister.getRegisteredBlock("geonosisRock"));
        world.setBlock(x - 1, y + 3, z + 1, BlockRegister.getRegisteredBlock("geonosisRock"));
        world.setBlock(x + 1, y + 3, z - 1, BlockRegister.getRegisteredBlock("geonosisRock"));
        world.setBlock(x, y + 3, z, BlockRegister.getRegisteredBlock("geonosisRock"));
        world.setBlock(x - 1, y + 4, z, BlockRegister.getRegisteredBlock("geonosisRock"));
        world.setBlock(x + 1, y + 4, z, BlockRegister.getRegisteredBlock("geonosisRock"));
        world.setBlock(x, y + 4, z - 1, BlockRegister.getRegisteredBlock("geonosisRock"));
        world.setBlock(x, y + 4, z + 1, BlockRegister.getRegisteredBlock("geonosisRock"));
        world.setBlock(x, y + 4, z, BlockRegister.getRegisteredBlock("geonosisRock"));
        world.setBlock(x - 1, y + 5, z, BlockRegister.getRegisteredBlock("geonosisRock"));
        world.setBlock(x + 1, y + 5, z, BlockRegister.getRegisteredBlock("geonosisRock"));
        world.setBlock(x, y + 5, z - 1, BlockRegister.getRegisteredBlock("geonosisRock"));
        world.setBlock(x, y + 5, z + 1, BlockRegister.getRegisteredBlock("geonosisRock"));
        world.setBlock(x, y + 5, z, BlockRegister.getRegisteredBlock("geonosisRock"));
        world.setBlock(x, y + 6, z, BlockRegister.getRegisteredBlock("geonosisRock"));
        world.setBlock(x, y + 7, z, BlockRegister.getRegisteredBlock("geonosisRock"));
        world.setBlock(x, y + 8, z, BlockRegister.getRegisteredBlock("geonosisRock"));
        return true;
    }
}


/*
 * Decompiled with CFR 0.148.
 *
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.world.World
 *  net.minecraft.world.gen.feature.WorldGenerator
 */
package com.sown.outerrim.dimension.tatooine;

import java.util.Random;

import com.sown.outerrim.registry.BlockRegister;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenDeadBush extends WorldGenerator {
    @Override
    public boolean generate(World world, Random random, int x, int y, int z) {
        // Move down until we hit the ground or the lowest possible height
        while (world.isAirBlock(x, y, z) && y > 2) {
            --y;
        }

        // Get the block at the current position
        Block block = world.getBlock(x, y, z);

        // Check if the block name contains "sand" (case insensitive)
        if (block != null && block.getUnlocalizedName().toLowerCase().contains("sand")) {
            // Place the dead_bush block above the sand block
            world.setBlock(x, y + 1, z, BlockRegister.getRegisteredBlock("dead_bush"), 0, 2);
            return true;
        }

        return false;
    }
}

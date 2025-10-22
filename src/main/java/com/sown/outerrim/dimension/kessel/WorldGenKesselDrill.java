package com.sown.outerrim.dimension.kessel;

import com.sown.outerrim.registry.BlockRegister;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.Random;

public class WorldGenKesselDrill extends WorldGenerator {

    @Override
    public boolean generate(World world, Random rand, int x, int y, int z) {
        int width = 10;  // Replace with actual width
        int length = 10; // Replace with actual length

        if (!canPlaceOnSolidGround(world, x, y, z, width, length)) {
            return false;
        }
        
        place(world, x + 1, y + 0, z + 1, Blocks.netherrack, 0);
        place(world, x + 1, y + 0, z + 2, Blocks.stone, 0);
        place(world, x + 1, y + 0, z + 3, Blocks.netherrack, 0);
        place(world, x + 1, y + 1, z + 1, Blocks.cobblestone_wall, 0);
        place(world, x + 1, y + 1, z + 2, Blocks.stone, 0);
        place(world, x + 1, y + 1, z + 3, Blocks.cobblestone_wall, 0);
        place(world, x + 1, y + 2, z + 1, Blocks.dark_oak_stairs, 4);
        place(world, x + 1, y + 2, z + 2, Blocks.dark_oak_stairs, 4);
        place(world, x + 1, y + 2, z + 3, Blocks.dark_oak_stairs, 7);
        place(world, x + 1, y + 3, z + 2, Blocks.stone_stairs, 4);
        place(world, x + 1, y + 4, z + 2, Blocks.stone, 0);
        place(world, x + 1, y + 5, z + 2, Blocks.cobblestone, 0);
        place(world, x + 1, y + 6, z + 2, Blocks.cobblestone, 0);
        place(world, x + 1, y + 7, z + 2, Blocks.stone, 0);
        place(world, x + 1, y + 8, z + 2, Blocks.stone_stairs, 0);
        place(world, x + 2, y + 0, z + 1, Blocks.stone, 0);
        place(world, x + 2, y + 0, z + 2, Blocks.stone, 0);
        place(world, x + 2, y + 0, z + 3, Blocks.stone, 0);
        place(world, x + 2, y + 1, z + 0, Blocks.nether_brick_fence, 0);
        place(world, x + 2, y + 1, z + 1, Blocks.stone, 0);
        place(world, x + 2, y + 1, z + 2, Blocks.stone, 0);
        place(world, x + 2, y + 1, z + 3, Blocks.stone, 0);
        place(world, x + 2, y + 1, z + 4, Blocks.nether_brick_fence, 0);
        place(world, x + 2, y + 2, z + 0, Blocks.nether_brick_fence, 0);
        place(world, x + 2, y + 2, z + 1, Blocks.dark_oak_stairs, 6);
        place(world, x + 2, y + 2, z + 2, Blocks.stone, 0);
        place(world, x + 2, y + 2, z + 3, Blocks.dark_oak_stairs, 7);
        place(world, x + 2, y + 2, z + 4, Blocks.nether_brick_fence, 0);
        place(world, x + 2, y + 3, z + 0, Blocks.nether_brick_fence, 0);
        place(world, x + 2, y + 3, z + 1, Blocks.stone_stairs, 6);
        place(world, x + 2, y + 3, z + 2, Blocks.stonebrick, 0);
        place(world, x + 2, y + 3, z + 3, Blocks.stone_stairs, 7);
        place(world, x + 2, y + 3, z + 4, Blocks.nether_brick_fence, 0);
        place(world, x + 2, y + 4, z + 1, Blocks.stone, 0);
        place(world, x + 2, y + 4, z + 3, Blocks.stone, 0);
        place(world, x + 2, y + 4, z + 4, Blocks.nether_brick_fence, 0);
        place(world, x + 2, y + 5, z + 1, Blocks.cobblestone, 0);
        place(world, x + 2, y + 5, z + 3, Blocks.cobblestone, 0);
        place(world, x + 2, y + 5, z + 4, Blocks.nether_brick_fence, 0);
        place(world, x + 2, y + 6, z + 1, Blocks.cobblestone, 0);
        place(world, x + 2, y + 6, z + 3, Blocks.cobblestone, 0);
        place(world, x + 2, y + 6, z + 4, Blocks.nether_brick_fence, 0);
        place(world, x + 2, y + 7, z + 1, Blocks.stone, 0);
        place(world, x + 2, y + 7, z + 3, Blocks.stone, 0);
        place(world, x + 2, y + 7, z + 4, Blocks.nether_brick_fence, 0);
        place(world, x + 2, y + 8, z + 1, Blocks.stone_stairs, 2);
        place(world, x + 2, y + 8, z + 2, Blocks.cobblestone, 0);
        place(world, x + 2, y + 8, z + 3, Blocks.stone_stairs, 3);
        place(world, x + 2, y + 9, z + 1, Blocks.nether_brick_fence, 0);
        place(world, x + 3, y + 0, z + 1, Blocks.netherrack, 0);
        place(world, x + 3, y + 0, z + 2, Blocks.stone, 0);
        place(world, x + 3, y + 0, z + 3, Blocks.netherrack, 0);
        place(world, x + 3, y + 1, z + 1, Blocks.cobblestone_wall, 0);
        place(world, x + 3, y + 1, z + 2, Blocks.stone, 0);
        place(world, x + 3, y + 1, z + 3, Blocks.cobblestone_wall, 0);
        place(world, x + 3, y + 2, z + 1, Blocks.dark_oak_stairs, 6);
        place(world, x + 3, y + 2, z + 2, Blocks.dark_oak_stairs, 5);
        place(world, x + 3, y + 2, z + 3, Blocks.dark_oak_stairs, 5);
        place(world, x + 3, y + 3, z + 0, Blocks.nether_brick_fence, 0);
        place(world, x + 3, y + 3, z + 1, Blocks.nether_brick_fence, 0);
        place(world, x + 3, y + 3, z + 2, Blocks.stone_stairs, 5);
        place(world, x + 3, y + 4, z + 1, Blocks.nether_brick_fence, 0);
        place(world, x + 3, y + 4, z + 2, Blocks.stone, 0);
        place(world, x + 3, y + 5, z + 1, Blocks.nether_brick_fence, 0);
        place(world, x + 3, y + 5, z + 2, Blocks.cobblestone, 0);
        place(world, x + 3, y + 6, z + 1, Blocks.nether_brick_fence, 0);
        place(world, x + 3, y + 6, z + 2, Blocks.cobblestone, 0);
        place(world, x + 3, y + 7, z + 1, Blocks.nether_brick_fence, 0);
        place(world, x + 3, y + 7, z + 2, Blocks.stone, 0);
        place(world, x + 3, y + 8, z + 1, Blocks.nether_brick_fence, 0);
        place(world, x + 3, y + 8, z + 2, Blocks.stone_stairs, 1);
        place(world, x + 3, y + 9, z + 1, Blocks.nether_brick_fence, 0);

        return true;
    }

    private void place(World world, int x, int y, int z, Block block, int meta) {
        if (block != null) {
            world.setBlock(x, y, z, block, meta, 2);
        } else {
            System.err.println("[Outer Rim] Tried to place null block at ( + x + , + y + , + z + )");
        }
    }

    private boolean canPlaceOnSolidGround(World world, int x, int y, int z, int width, int length) {
        for (int dx = 0; dx < width; dx++) {
            for (int dz = 0; dz < length; dz++) {
                Block below = world.getBlock(x + dx, y - 1, z + dz);
                if (below == null || below.isAir(world, x + dx, y - 1, z + dz)) {
                    return false;
                }
            }
        }
        return true;
    }
}

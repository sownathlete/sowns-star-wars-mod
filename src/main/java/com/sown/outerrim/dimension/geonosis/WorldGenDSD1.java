package com.sown.outerrim.dimension.geonosis;

import com.sown.outerrim.registry.BlockRegister;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.Random;

public class WorldGenDSD1 extends WorldGenerator {

    @Override
    public boolean generate(World world, Random rand, int x, int y, int z) {
        int width = 10;  // Replace with actual width
        int length = 10; // Replace with actual length

        if (!canPlaceOnSolidGround(world, x, y, z, width, length)) {
            return false;
        }

        // paste structure placement lines here
        place(world, x + 0, y + 0, z + 0, Blocks.stone_slab, 5);
        place(world, x + 0, y + 0, z + 10, Blocks.stone_slab, 5);
        place(world, x + 1, y + 0, z + 1, Blocks.stone, 6);
        place(world, x + 1, y + 0, z + 9, Blocks.stone, 6);
        place(world, x + 1, y + 1, z + 1, Blocks.cobblestone_wall, 0);
        place(world, x + 1, y + 1, z + 9, Blocks.cobblestone_wall, 0);
        place(world, x + 1, y + 2, z + 1, Blocks.stone, 6);
        place(world, x + 1, y + 2, z + 9, Blocks.stone, 6);
        place(world, x + 1, y + 3, z + 1, Blocks.stone_slab, 0);
        place(world, x + 1, y + 3, z + 9, Blocks.stone_slab, 0);
        place(world, x + 2, y + 2, z + 2, Blocks.double_stone_slab, 0);
        place(world, x + 2, y + 2, z + 8, Blocks.double_stone_slab, 0);
        place(world, x + 3, y + 1, z + 3, Blocks.stone_slab, 8);
        place(world, x + 3, y + 1, z + 7, Blocks.stone_slab, 8);
        place(world, x + 3, y + 2, z + 3, Blocks.stone_slab, 0);
        place(world, x + 3, y + 2, z + 5, Blocks.stone_slab, 13);
        place(world, x + 3, y + 2, z + 7, Blocks.stone_slab, 0);
        place(world, x + 3, y + 3, z + 4, Blocks.stone_brick_stairs, 0);
        place(world, x + 3, y + 3, z + 5, Blocks.stone, 6);
        place(world, x + 3, y + 3, z + 6, Blocks.stone_brick_stairs, 0);
        place(world, x + 3, y + 4, z + 5, Blocks.stone_slab, 5);
        place(world, x + 4, y + 1, z + 4, Blocks.stone_slab, 13);
        place(world, x + 4, y + 1, z + 5, Blocks.double_stone_slab, 5);
        place(world, x + 4, y + 1, z + 6, Blocks.stone_slab, 13);
        place(world, x + 4, y + 2, z + 4, Blocks.nether_brick, 0);
        place(world, x + 4, y + 2, z + 5, Blocks.nether_brick, 0);
        place(world, x + 4, y + 2, z + 6, Blocks.nether_brick, 0);
        place(world, x + 4, y + 3, z + 3, Blocks.stone_brick_stairs, 2);
        place(world, x + 4, y + 3, z + 4, Blocks.double_stone_slab, 5);
        place(world, x + 4, y + 3, z + 5, Blocks.stained_hardened_clay, 9);
        place(world, x + 4, y + 3, z + 6, Blocks.double_stone_slab, 5);
        place(world, x + 4, y + 3, z + 7, Blocks.stone_brick_stairs, 3);
        place(world, x + 4, y + 4, z + 4, Blocks.stone_brick_stairs, 0);
        place(world, x + 4, y + 4, z + 5, Blocks.stone, 6);
        place(world, x + 4, y + 4, z + 6, Blocks.stone_brick_stairs, 0);
        place(world, x + 4, y + 5, z + 5, Blocks.nether_brick_fence, 0);
        place(world, x + 4, y + 6, z + 5, Blocks.nether_brick_fence, 0);
        place(world, x + 4, y + 7, z + 5, Blocks.nether_brick_fence, 0);
        place(world, x + 5, y + 0, z + 5, Blocks.stone_slab, 13);
        place(world, x + 5, y + 1, z + 4, Blocks.double_stone_slab, 5);
        place(world, x + 5, y + 1, z + 5, Blocks.double_stone_slab, 5);
        place(world, x + 5, y + 1, z + 6, Blocks.double_stone_slab, 5);
        place(world, x + 5, y + 2, z + 3, Blocks.stone_slab, 13);
        place(world, x + 5, y + 2, z + 4, Blocks.nether_brick, 0);
        place(world, x + 5, y + 2, z + 5, Blocks.nether_brick, 0);
        place(world, x + 5, y + 2, z + 6, Blocks.nether_brick, 0);
        place(world, x + 5, y + 2, z + 7, Blocks.stone_slab, 13);
        place(world, x + 5, y + 3, z + 3, Blocks.stone, 6);
        place(world, x + 5, y + 3, z + 4, Blocks.stained_hardened_clay, 9);
        place(world, x + 5, y + 3, z + 5, Blocks.stained_hardened_clay, 9);
        place(world, x + 5, y + 3, z + 6, Blocks.stained_hardened_clay, 9);
        place(world, x + 5, y + 3, z + 7, Blocks.stone, 6);
        place(world, x + 5, y + 4, z + 3, Blocks.stone_slab, 5);
        place(world, x + 5, y + 4, z + 4, Blocks.stone, 6);
        place(world, x + 5, y + 4, z + 5, Blocks.stained_hardened_clay, 9);
        place(world, x + 5, y + 4, z + 6, Blocks.stone, 6);
        place(world, x + 5, y + 4, z + 7, Blocks.stone_slab, 5);
        place(world, x + 5, y + 5, z + 5, Blocks.stone_slab, 5);
        place(world, x + 6, y + 1, z + 4, Blocks.stone_slab, 13);
        place(world, x + 6, y + 1, z + 5, Blocks.double_stone_slab, 5);
        place(world, x + 6, y + 1, z + 6, Blocks.stone_slab, 13);
        place(world, x + 6, y + 2, z + 4, Blocks.nether_brick, 0);
        place(world, x + 6, y + 2, z + 5, Blocks.nether_brick, 0);
        place(world, x + 6, y + 2, z + 6, Blocks.nether_brick, 0);
        place(world, x + 6, y + 3, z + 3, Blocks.stone_brick_stairs, 2);
        place(world, x + 6, y + 3, z + 4, Blocks.double_stone_slab, 5);
        place(world, x + 6, y + 3, z + 5, Blocks.stained_hardened_clay, 9);
        place(world, x + 6, y + 3, z + 6, Blocks.double_stone_slab, 5);
        place(world, x + 6, y + 3, z + 7, Blocks.stone_brick_stairs, 3);
        place(world, x + 6, y + 4, z + 4, Blocks.redstone_block, 0);
        place(world, x + 6, y + 4, z + 5, Blocks.stone, 6);
        place(world, x + 6, y + 4, z + 6, Blocks.redstone_block, 0);
        place(world, x + 7, y + 1, z + 3, Blocks.stone_slab, 8);
        place(world, x + 7, y + 1, z + 7, Blocks.stone_slab, 8);
        place(world, x + 7, y + 2, z + 3, Blocks.stone_slab, 0);
        place(world, x + 7, y + 2, z + 5, Blocks.stone_slab, 13);
        place(world, x + 7, y + 2, z + 7, Blocks.stone_slab, 0);
        place(world, x + 7, y + 3, z + 4, Blocks.stone_brick_stairs, 1);
        place(world, x + 7, y + 3, z + 5, Blocks.stonebrick, 3);
        place(world, x + 7, y + 3, z + 6, Blocks.stone_brick_stairs, 1);
        place(world, x + 7, y + 4, z + 5, Blocks.stone_slab, 5);
        place(world, x + 8, y + 2, z + 2, Blocks.double_stone_slab, 0);
        place(world, x + 8, y + 2, z + 8, Blocks.double_stone_slab, 0);
        place(world, x + 8, y + 3, z + 5, Blocks.stonebrick, 3);
        place(world, x + 9, y + 0, z + 1, Blocks.stone, 6);
        place(world, x + 9, y + 0, z + 9, Blocks.stone, 6);
        place(world, x + 9, y + 1, z + 1, Blocks.cobblestone_wall, 0);
        place(world, x + 9, y + 1, z + 9, Blocks.cobblestone_wall, 0);
        place(world, x + 9, y + 2, z + 1, Blocks.stone, 6);
        place(world, x + 9, y + 2, z + 9, Blocks.stone, 6);
        place(world, x + 9, y + 3, z + 1, Blocks.stone_slab, 0);
        place(world, x + 9, y + 3, z + 5, Blocks.cobblestone_wall, 0);
        place(world, x + 9, y + 3, z + 9, Blocks.stone_slab, 0);
        place(world, x + 10, y + 0, z + 0, Blocks.stone_slab, 5);
        place(world, x + 10, y + 0, z + 10, Blocks.stone_slab, 5);
        place(world, x + 10, y + 3, z + 5, Blocks.cobblestone_wall, 0);
        place(world, x + 11, y + 3, z + 5, Blocks.cobblestone_wall, 0);

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

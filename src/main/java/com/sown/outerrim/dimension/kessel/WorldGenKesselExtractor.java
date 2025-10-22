package com.sown.outerrim.dimension.kessel;

import com.sown.outerrim.registry.BlockRegister;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.Random;

public class WorldGenKesselExtractor extends WorldGenerator {

    @Override
    public boolean generate(World world, Random rand, int x, int y, int z) {
        int width = 10;  // Replace with actual width
        int length = 10; // Replace with actual length

        if (!canPlaceOnSolidGround(world, x, y, z, width, length)) {
            return false;
        }

        place(world, x + 0, y + 0, z + 1, Blocks.double_stone_slab, 0);
        place(world, x + 0, y + 0, z + 2, Blocks.double_stone_slab, 0);
        place(world, x + 0, y + 0, z + 3, Blocks.double_stone_slab, 0);
        place(world, x + 0, y + 1, z + 1, Blocks.cobblestone_wall, 0);
        place(world, x + 0, y + 1, z + 2, BlockRegister.getRegisteredBlock("kesselMachineryPanel"), 0);
        place(world, x + 0, y + 1, z + 3, Blocks.cobblestone_wall, 0);
        place(world, x + 0, y + 2, z + 1, Blocks.cobblestone_wall, 0);
        place(world, x + 0, y + 2, z + 2, Blocks.iron_bars, 0);
        place(world, x + 0, y + 2, z + 3, Blocks.cobblestone_wall, 0);
        place(world, x + 0, y + 3, z + 1, Blocks.cobblestone_wall, 0);
        place(world, x + 0, y + 3, z + 2, BlockRegister.getRegisteredBlock("kesselMachineryPanel"), 0);
        place(world, x + 0, y + 3, z + 3, Blocks.cobblestone_wall, 0);
        place(world, x + 0, y + 4, z + 1, BlockRegister.getRegisteredBlock("kesselMachineryPanel"), 0);
        place(world, x + 0, y + 4, z + 2, BlockRegister.getRegisteredBlock("kesselMachineryPanel"), 0);
        place(world, x + 0, y + 4, z + 3, BlockRegister.getRegisteredBlock("kesselMachineryPanel"), 0);
        place(world, x + 0, y + 5, z + 2, BlockRegister.getRegisteredBlock("kesselMachineryPanel"), 0);
        place(world, x + 1, y + 0, z + 0, Blocks.double_stone_slab, 0);
        place(world, x + 1, y + 0, z + 1, Blocks.stained_hardened_clay, 4);
        place(world, x + 1, y + 0, z + 2, Blocks.stained_hardened_clay, 4);
        place(world, x + 1, y + 0, z + 3, Blocks.stained_hardened_clay, 4);
        place(world, x + 1, y + 0, z + 4, Blocks.double_stone_slab, 0);
        place(world, x + 1, y + 1, z + 0, Blocks.cobblestone_wall, 0);
        place(world, x + 1, y + 1, z + 1, BlockRegister.getRegisteredBlock("kesselMachineryPanel"), 0);
        place(world, x + 1, y + 1, z + 2, BlockRegister.getRegisteredBlock("kesselMachineryPanel"), 0);
        place(world, x + 1, y + 1, z + 3, BlockRegister.getRegisteredBlock("kesselMachineryPanel"), 0);
        place(world, x + 1, y + 1, z + 4, Blocks.cobblestone_wall, 0);
        place(world, x + 1, y + 2, z + 0, Blocks.cobblestone_wall, 0);
        place(world, x + 1, y + 2, z + 1, Blocks.iron_bars, 0);
        place(world, x + 1, y + 2, z + 2, Blocks.stone_slab, 5);
        place(world, x + 1, y + 2, z + 3, Blocks.iron_bars, 0);
        place(world, x + 1, y + 2, z + 4, Blocks.cobblestone_wall, 0);
        place(world, x + 1, y + 3, z + 0, Blocks.cobblestone_wall, 0);
        place(world, x + 1, y + 3, z + 1, BlockRegister.getRegisteredBlock("kesselMachineryPanel"), 0);
        place(world, x + 1, y + 3, z + 2, BlockRegister.getRegisteredBlock("kesselMachineryPanel"), 0);
        place(world, x + 1, y + 3, z + 3, BlockRegister.getRegisteredBlock("kesselMachineryPanel"), 0);
        place(world, x + 1, y + 3, z + 4, Blocks.cobblestone_wall, 0);
        place(world, x + 1, y + 4, z + 0, BlockRegister.getRegisteredBlock("kesselMachineryPanel"), 0);
        place(world, x + 1, y + 4, z + 1, BlockRegister.getRegisteredBlock("kesselMachineryPanel"), 0);
        place(world, x + 1, y + 4, z + 2, BlockRegister.getRegisteredBlock("kesselMachineryPanel"), 0);
        place(world, x + 1, y + 4, z + 3, BlockRegister.getRegisteredBlock("kesselMachineryPanel"), 0);
        place(world, x + 1, y + 4, z + 4, BlockRegister.getRegisteredBlock("kesselMachineryPanel"), 0);
        place(world, x + 1, y + 5, z + 1, BlockRegister.getRegisteredBlock("kesselMachineryPanel"), 0);
        place(world, x + 1, y + 5, z + 2, BlockRegister.getRegisteredBlock("kesselMachineryPanel"), 0);
        place(world, x + 1, y + 5, z + 3, BlockRegister.getRegisteredBlock("kesselMachineryPanel"), 0);
        place(world, x + 1, y + 6, z + 2, BlockRegister.getRegisteredBlock("kesselMachineryGlow2"), 4);
        place(world, x + 1, y + 7, z + 2, BlockRegister.getRegisteredBlock("kesselMachineryPanel"), 0);
        place(world, x + 2, y + 0, z + 0, Blocks.double_stone_slab, 0);
        place(world, x + 2, y + 0, z + 1, Blocks.stained_hardened_clay, 4);
        place(world, x + 2, y + 0, z + 2, Blocks.stained_hardened_clay, 4);
        place(world, x + 2, y + 0, z + 3, Blocks.stained_hardened_clay, 4);
        place(world, x + 2, y + 0, z + 4, Blocks.double_stone_slab, 0);
        place(world, x + 2, y + 1, z + 0, Blocks.stained_hardened_clay, 4);
        place(world, x + 2, y + 1, z + 1, Blocks.stained_hardened_clay, 4);
        place(world, x + 2, y + 1, z + 2, BlockRegister.getRegisteredBlock("kesselMachineryPanel"), 0);
        place(world, x + 2, y + 1, z + 3, BlockRegister.getRegisteredBlock("kesselMachineryPanel"), 0);
        place(world, x + 2, y + 1, z + 4, BlockRegister.getRegisteredBlock("kesselMachineryPanel"), 0);
        place(world, x + 2, y + 2, z + 0, Blocks.iron_bars, 0);
        place(world, x + 2, y + 2, z + 1, Blocks.stone_slab, 5);
        place(world, x + 2, y + 2, z + 2, BlockRegister.getRegisteredBlock("kesselMachineryGlow2"), 5);
        place(world, x + 2, y + 2, z + 3, Blocks.stone_slab, 5);
        place(world, x + 2, y + 2, z + 4, Blocks.iron_bars, 0);
        place(world, x + 2, y + 3, z + 0, BlockRegister.getRegisteredBlock("kesselMachineryPanel"), 0);
        place(world, x + 2, y + 3, z + 1, BlockRegister.getRegisteredBlock("kesselMachineryPanel"), 0);
        place(world, x + 2, y + 3, z + 2, BlockRegister.getRegisteredBlock("kesselMachineryPanel"), 0);
        place(world, x + 2, y + 3, z + 3, BlockRegister.getRegisteredBlock("kesselMachineryPanel"), 0);
        place(world, x + 2, y + 3, z + 4, BlockRegister.getRegisteredBlock("kesselMachineryPanel"), 0);
        place(world, x + 2, y + 4, z + 0, BlockRegister.getRegisteredBlock("kesselMachineryPanel"), 0);
        place(world, x + 2, y + 4, z + 1, BlockRegister.getRegisteredBlock("kesselMachineryPanel"), 0);
        place(world, x + 2, y + 4, z + 2, BlockRegister.getRegisteredBlock("kesselMachineryPanel"), 0);
        place(world, x + 2, y + 4, z + 3, BlockRegister.getRegisteredBlock("kesselMachineryPanel"), 0);
        place(world, x + 2, y + 4, z + 4, BlockRegister.getRegisteredBlock("kesselMachineryPanel"), 0);
        place(world, x + 2, y + 5, z + 0, BlockRegister.getRegisteredBlock("kesselMachineryPanel"), 0);
        place(world, x + 2, y + 5, z + 1, BlockRegister.getRegisteredBlock("kesselMachineryPanel"), 0);
        place(world, x + 2, y + 5, z + 2, BlockRegister.getRegisteredBlock("kesselMachineryPanel"), 0);
        place(world, x + 2, y + 5, z + 3, BlockRegister.getRegisteredBlock("kesselMachineryPanel"), 0);
        place(world, x + 2, y + 5, z + 4, BlockRegister.getRegisteredBlock("kesselMachineryPanel"), 0);
        place(world, x + 2, y + 6, z + 1, BlockRegister.getRegisteredBlock("kesselMachineryGlow2"), 4);
        place(world, x + 2, y + 6, z + 2, BlockRegister.getRegisteredBlock("kesselMachineryPanel"), 0);
        place(world, x + 2, y + 6, z + 3, BlockRegister.getRegisteredBlock("kesselMachineryGlow2"), 5);
        place(world, x + 2, y + 7, z + 1, BlockRegister.getRegisteredBlock("kesselMachineryPanel"), 0);
        place(world, x + 2, y + 7, z + 2, BlockRegister.getRegisteredBlock("kesselMachineryPanel"), 0);
        place(world, x + 2, y + 7, z + 3, BlockRegister.getRegisteredBlock("kesselMachineryPanel"), 0);
        place(world, x + 3, y + 0, z + 0, Blocks.double_stone_slab, 0);
        place(world, x + 3, y + 0, z + 1, Blocks.stained_hardened_clay, 4);
        place(world, x + 3, y + 0, z + 2, Blocks.stained_hardened_clay, 4);
        place(world, x + 3, y + 0, z + 3, Blocks.stained_hardened_clay, 4);
        place(world, x + 3, y + 0, z + 4, Blocks.double_stone_slab, 0);
        place(world, x + 3, y + 1, z + 0, Blocks.cobblestone_wall, 0);
        place(world, x + 3, y + 1, z + 1, Blocks.stained_hardened_clay, 4);
        place(world, x + 3, y + 1, z + 2, Blocks.stained_hardened_clay, 4);
        place(world, x + 3, y + 1, z + 3, BlockRegister.getRegisteredBlock("kesselMachineryPanel"), 0);
        place(world, x + 3, y + 1, z + 4, Blocks.cobblestone_wall, 0);
        place(world, x + 3, y + 2, z + 0, Blocks.cobblestone_wall, 0);
        place(world, x + 3, y + 2, z + 1, Blocks.iron_bars, 0);
        place(world, x + 3, y + 2, z + 2, Blocks.stone_slab, 5);
        place(world, x + 3, y + 2, z + 3, Blocks.iron_bars, 0);
        place(world, x + 3, y + 2, z + 4, Blocks.cobblestone_wall, 0);
        place(world, x + 3, y + 3, z + 0, Blocks.cobblestone_wall, 0);
        place(world, x + 3, y + 3, z + 1, BlockRegister.getRegisteredBlock("kesselMachineryPanel"), 0);
        place(world, x + 3, y + 3, z + 2, BlockRegister.getRegisteredBlock("kesselMachineryPanel"), 0);
        place(world, x + 3, y + 3, z + 3, BlockRegister.getRegisteredBlock("kesselMachineryPanel"), 0);
        place(world, x + 3, y + 3, z + 4, Blocks.cobblestone_wall, 0);
        place(world, x + 3, y + 4, z + 0, BlockRegister.getRegisteredBlock("kesselMachineryPanel"), 0);
        place(world, x + 3, y + 4, z + 1, BlockRegister.getRegisteredBlock("kesselMachineryPanel"), 0);
        place(world, x + 3, y + 4, z + 2, BlockRegister.getRegisteredBlock("kesselMachineryPanel"), 0);
        place(world, x + 3, y + 4, z + 3, BlockRegister.getRegisteredBlock("kesselMachineryPanel"), 0);
        place(world, x + 3, y + 4, z + 4, BlockRegister.getRegisteredBlock("kesselMachineryPanel"), 0);
        place(world, x + 3, y + 5, z + 1, BlockRegister.getRegisteredBlock("kesselMachineryPanel"), 0);
        place(world, x + 3, y + 5, z + 2, BlockRegister.getRegisteredBlock("kesselMachineryPanel"), 0);
        place(world, x + 3, y + 5, z + 3, BlockRegister.getRegisteredBlock("kesselMachineryPanel"), 0);
        place(world, x + 3, y + 6, z + 2, BlockRegister.getRegisteredBlock("kesselMachineryGlow2"), 5);
        place(world, x + 3, y + 7, z + 2, BlockRegister.getRegisteredBlock("kesselMachineryPanel"), 0);
        place(world, x + 4, y + 0, z + 1, Blocks.double_stone_slab, 0);
        place(world, x + 4, y + 0, z + 2, Blocks.double_stone_slab, 0);
        place(world, x + 4, y + 0, z + 3, Blocks.double_stone_slab, 0);
        place(world, x + 4, y + 1, z + 1, Blocks.cobblestone_wall, 0);
        place(world, x + 4, y + 1, z + 2, Blocks.stained_hardened_clay, 4);
        place(world, x + 4, y + 1, z + 3, Blocks.cobblestone_wall, 0);
        place(world, x + 4, y + 2, z + 1, Blocks.cobblestone_wall, 0);
        place(world, x + 4, y + 2, z + 2, Blocks.iron_bars, 0);
        place(world, x + 4, y + 2, z + 3, Blocks.cobblestone_wall, 0);
        place(world, x + 4, y + 3, z + 1, Blocks.cobblestone_wall, 0);
        place(world, x + 4, y + 3, z + 2, BlockRegister.getRegisteredBlock("kesselMachineryPanel"), 0);
        place(world, x + 4, y + 3, z + 3, Blocks.cobblestone_wall, 0);
        place(world, x + 4, y + 4, z + 1, BlockRegister.getRegisteredBlock("kesselMachineryPanel"), 0);
        place(world, x + 4, y + 4, z + 2, BlockRegister.getRegisteredBlock("kesselMachineryPanel"), 0);
        place(world, x + 4, y + 4, z + 3, BlockRegister.getRegisteredBlock("kesselMachineryPanel"), 0);
        place(world, x + 4, y + 5, z + 2, BlockRegister.getRegisteredBlock("kesselMachineryPanel"), 0);

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

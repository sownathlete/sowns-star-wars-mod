package com.sown.outerrim.dimension.felucia;

import com.sown.outerrim.registry.BlockRegister;

import cpw.mods.fml.common.Loader;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.Random;

public class WorldGenFeluciaPlant4 extends WorldGenerator {

    @Override
    public boolean generate(World world, Random rand, int x, int y, int z) {
        int width = 10;
        int length = 10;

        if (!canPlaceOnSolidGround(world, x, y, z, width, length)) {
            return false;
        }
        
        Block nether_brick_wall = getBlockByString("etfuturum:nether_brick_wall");
        Block mossy_cobblestone_stairs = getBlockByString("etfuturum:mossy_cobblestone_stairs");

        place(world, x + 0, y + 3, z + 1, nether_brick_wall, 0);
        place(world, x + 0, y + 4, z + 1, Blocks.mossy_cobblestone, 0);
        place(world, x + 0, y + 4, z + 2, Blocks.cobblestone_wall, 1);
        place(world, x + 0, y + 5, z + 0, Blocks.cobblestone_wall, 1);
        place(world, x + 0, y + 5, z + 1, Blocks.mossy_cobblestone, 0);
        place(world, x + 0, y + 5, z + 2, Blocks.cobblestone_wall, 1);
        place(world, x + 0, y + 6, z + 1, Blocks.mossy_cobblestone, 0);
        place(world, x + 0, y + 7, z + 1, mossy_cobblestone_stairs, 0);
        place(world, x + 1, y + 0, z + 1, nether_brick_wall, 0);
        place(world, x + 1, y + 1, z + 1, nether_brick_wall, 0);
        place(world, x + 1, y + 2, z + 1, Blocks.stained_hardened_clay, 12);
        place(world, x + 1, y + 3, z + 0, nether_brick_wall, 0);
        place(world, x + 1, y + 3, z + 1, Blocks.stained_hardened_clay, 12);
        place(world, x + 1, y + 3, z + 2, nether_brick_wall, 0);
        place(world, x + 1, y + 4, z + 0, Blocks.mossy_cobblestone, 0);
        place(world, x + 1, y + 4, z + 2, Blocks.stained_hardened_clay, 12);
        place(world, x + 1, y + 5, z + 0, Blocks.mossy_cobblestone, 0);
        place(world, x + 1, y + 5, z + 2, Blocks.mossy_cobblestone, 0);
        place(world, x + 1, y + 6, z + 0, Blocks.mossy_cobblestone, 0);
        place(world, x + 1, y + 6, z + 2, Blocks.mossy_cobblestone, 0);
        place(world, x + 1, y + 7, z + 0, Blocks.mossy_cobblestone, 0);
        place(world, x + 1, y + 7, z + 2, Blocks.mossy_cobblestone, 0);
        place(world, x + 1, y + 8, z + 0, Blocks.cobblestone_wall, 1);
        place(world, x + 1, y + 8, z + 1, Blocks.mossy_cobblestone, 0);
        place(world, x + 1, y + 8, z + 2, mossy_cobblestone_stairs, 3);
        place(world, x + 1, y + 9, z + 1, Blocks.mossy_cobblestone, 0);
        place(world, x + 1, y + 10, z + 1, Blocks.skull, 1);
        place(world, x + 2, y + 3, z + 1, nether_brick_wall, 0);
        place(world, x + 2, y + 4, z + 1, Blocks.stained_hardened_clay, 12);
        place(world, x + 2, y + 5, z + 0, Blocks.cobblestone_wall, 1);
        place(world, x + 2, y + 5, z + 1, Blocks.stained_hardened_clay, 12);
        place(world, x + 2, y + 5, z + 2, nether_brick_wall, 0);
        place(world, x + 2, y + 6, z + 0, Blocks.cobblestone_wall, 1);
        place(world, x + 2, y + 6, z + 1, Blocks.mossy_cobblestone, 0);
        place(world, x + 2, y + 7, z + 1, mossy_cobblestone_stairs, 1);

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

    private Block getBlockByString(String modidAndName) {
        String[] split = modidAndName.split(":");
        if (split.length != 2) return null;

        String modid = split[0];
        String blockName = split[1];

        if (!Loader.isModLoaded(modid)) return null;

        Object raw = Block.blockRegistry.getObject(modid + ":" + blockName);
        return raw instanceof Block ? (Block) raw : null;
    }
}

package com.sown.outerrim.dimension.felucia;

import com.sown.outerrim.registry.BlockRegister;

import cpw.mods.fml.common.Loader;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.Random;

public class WorldGenFeluciaPlantB3 extends WorldGenerator {

	@Override
	public boolean generate(World world, Random rand, int x, int y, int z) {
		int width = 10;
		int length = 10;

		if (!canPlaceOnSolidGround(world, x, y, z, width, length)) {
			return false;
		}

		// --- Et Futurum Block Definitions ---
		Block blackstone_wall = getBlockByString("etfuturum:blackstone_wall");
		Block fence_acacia = getBlockByString("etfuturum:fence_acacia");
		Block red_sandstone_wall = getBlockByString("etfuturum:red_sandstone_wall");
		Block blackstone = getBlockByString("etfuturum:blackstone");
		Block blackstone_stairs = getBlockByString("etfuturum:blackstone_stairs");
		Block fence_gate_acacia = getBlockByString("etfuturum:fence_gate_acacia");
		Block stone_slab = getBlockByString("etfuturum:stone_slab");

// --- Structure Placement ---
		place(world, x + 0, y + 0, z + 0, blackstone_wall, 0);
		place(world, x + 0, y + 0, z + 1, blackstone_wall, 0);
		place(world, x + 0, y + 1, z + 1, fence_acacia, 0);
		place(world, x + 0, y + 4, z + 1, red_sandstone_wall, 0);
		place(world, x + 0, y + 5, z + 1, Blocks.stained_hardened_clay, 1);
		place(world, x + 0, y + 6, z + 1, red_sandstone_wall, 0);
		place(world, x + 0, y + 7, z + 1, Blocks.carpet, 13);
		place(world, x + 1, y + 0, z + 0, blackstone, 0);
		place(world, x + 1, y + 0, z + 1, blackstone, 0);
		place(world, x + 1, y + 0, z + 2, blackstone_stairs, 3);
		place(world, x + 1, y + 1, z + 0, blackstone_wall, 0);
		place(world, x + 1, y + 1, z + 1, blackstone, 0);
		place(world, x + 1, y + 2, z + 1, Blocks.stained_hardened_clay, 1);
		place(world, x + 1, y + 3, z + 1, Blocks.stained_hardened_clay, 1);
		place(world, x + 1, y + 4, z + 1, Blocks.sand, 1);
		place(world, x + 1, y + 5, z + 1, fence_gate_acacia, 5);
		place(world, x + 1, y + 7, z + 1, stone_slab, 1);
		place(world, x + 2, y + 0, z + 0, blackstone_stairs, 1);
		place(world, x + 2, y + 0, z + 1, blackstone, 0);
		place(world, x + 2, y + 0, z + 2, blackstone_wall, 0);
		place(world, x + 2, y + 1, z + 1, blackstone_wall, 0);
		place(world, x + 2, y + 6, z + 1, stone_slab, 9);
		place(world, x + 2, y + 7, z + 1, Blocks.carpet, 13);
		place(world, x + 3, y + 0, z + 1, blackstone_wall, 0);

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
// 1) refuse to even look at blocks in unloaded chunks
		if (!world.checkChunksExist(x, y - 1, z, x + width - 1, y - 1, z + length - 1)) {
			return false;
		}

// 2) now its safe to query getBlock()
		for (int dx = 0; dx < width; dx++) {
			for (int dz = 0; dz < length; dz++) {
				Block below = world.getBlock(x + dx, y - 1, z + dz);
				if (below == null || below.isAir(world, x + dx, y - 1, z + dz) || below.getMaterial().isLiquid()
						|| !below.getMaterial().isSolid()) {
					return false;
				}
			}
		}

		return true;
	}

	private Block getBlockByString(String modidAndName) {
		String[] split = modidAndName.split(":");
		if (split.length != 2)
			return null;

		String modid = split[0];
		String blockName = split[1];

		if (!Loader.isModLoaded(modid))
			return null;

		Object raw = Block.blockRegistry.getObject(modid + ":" + blockName);
		return raw instanceof Block ? (Block) raw : null;
	}
}

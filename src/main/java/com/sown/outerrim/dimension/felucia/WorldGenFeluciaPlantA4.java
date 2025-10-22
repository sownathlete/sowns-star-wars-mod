package com.sown.outerrim.dimension.felucia;

import com.sown.outerrim.registry.BlockRegister;

import cpw.mods.fml.common.Loader;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.Random;

public class WorldGenFeluciaPlantA4 extends WorldGenerator {

	@Override
	public boolean generate(World world, Random rand, int x, int y, int z) {
		int width = 10;
		int length = 10;

		if (!canPlaceOnSolidGround(world, x, y, z, width, length)) {
			return false;
		}

		Block fence_gate_acacia = getBlockByString("etfuturum:fence_gate_acacia");
		Block red_sandstone_wall = getBlockByString("etfuturum:red_sandstone_wall");
		Block stone_slab = getBlockByString("etfuturum:stone_slab");
		Block trapdoor_acacia = getBlockByString("etfuturum:trapdoor_acacia");

		place(world, x + 0, y + 0, z + 0, Blocks.stained_hardened_clay, 1);
		place(world, x + 0, y + 1, z + 0, Blocks.stained_hardened_clay, 1);
		place(world, x + 0, y + 2, z + 0, red_sandstone_wall, 0);
		place(world, x + 0, y + 2, z + 1, fence_gate_acacia, 3);
		place(world, x + 0, y + 3, z + 0, trapdoor_acacia, 8);
		place(world, x + 0, y + 3, z + 1, red_sandstone_wall, 0);
		place(world, x + 0, y + 4, z + 0, Blocks.carpet, 13);
		place(world, x + 0, y + 4, z + 1, stone_slab, 1);

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

package com.sown.outerrim.dimension.felucia;

import com.sown.outerrim.registry.BlockRegister;

import cpw.mods.fml.common.Loader;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.Random;

public class WorldGenFeluciaFlower4 extends WorldGenerator {

	@Override
	public boolean generate(World world, Random rand, int x, int y, int z) {
		int width = 10;
		int length = 10;

		if (!canPlaceOnSolidGround(world, x, y, z, width, length)) {
			return false;
		}

		Block prismarine = getBlockByString("etfuturum:prismarine");
		Block prismarine_slab = getBlockByString("etfuturum:prismarine_slab");
		Block prismarine_stairs = getBlockByString("etfuturum:prismarine_stairs");
		Block prismarine_wall = getBlockByString("etfuturum:prismarine_wall");

		Block red_sandstone = getBlockByString("etfuturum:red_sandstone");
		Block red_sandstone_slab = getBlockByString("etfuturum:red_sandstone_slab");
		Block red_sandstone_stairs = getBlockByString("etfuturum:red_sandstone_stairs");
		Block red_sandstone_wall = getBlockByString("etfuturum:red_sandstone_wall");

		Block smooth_red_sandstone_slab = getBlockByString("etfuturum:smooth_red_sandstone_slab");
		Block smooth_red_sandstone_stairs = getBlockByString("etfuturum:smooth_red_sandstone_stairs");

		Block stone_wall = getBlockByString("etfuturum:stone_wall");

		Block sea_lantern = getBlockByString("etfuturum:sea_lantern");
		Block coarse_dirt = getBlockByString("etfuturum:coarse_dirt");
		Block double_plant = getBlockByString("etfuturum:double_plant"); // if applicable

		Block fence_acacia = getBlockByString("etfuturum:fence_acacia");
		Block fence_birch = getBlockByString("etfuturum:fence_birch");
		Block fence_dark_oak = getBlockByString("etfuturum:fence_dark_oak");
		Block fence_jungle = getBlockByString("etfuturum:fence_jungle");
		Block fence_spruce = getBlockByString("etfuturum:fence_spruce");

		Block fence_gate_acacia = getBlockByString("etfuturum:fence_gate_acacia");
		Block fence_gate_birch = getBlockByString("etfuturum:fence_gate_birch");
		Block fence_gate_dark_oak = getBlockByString("etfuturum:fence_gate_dark_oak");
		Block fence_gate_jungle = getBlockByString("etfuturum:fence_gate_jungle");
		Block fence_gate_spruce = getBlockByString("etfuturum:fence_gate_spruce");

		Block door_acacia = getBlockByString("etfuturum:door_acacia");
		Block door_dark_oak = getBlockByString("etfuturum:door_dark_oak");

		Block trapdoor_acacia = getBlockByString("etfuturum:trapdoor_acacia");

		place(world, x + 0, y + 14, z + 3, prismarine_slab, 0);
		place(world, x + 0, y + 14, z + 4, prismarine_slab, 0);
		place(world, x + 0, y + 14, z + 5, prismarine_slab, 0);
		place(world, x + 0, y + 14, z + 6, prismarine_slab, 0);
		place(world, x + 0, y + 14, z + 7, prismarine_slab, 0);
		place(world, x + 1, y + 13, z + 4, prismarine_stairs, 4);
		place(world, x + 1, y + 13, z + 5, prismarine_stairs, 4);
		place(world, x + 1, y + 13, z + 6, prismarine_stairs, 4);
		place(world, x + 1, y + 14, z + 1, prismarine_slab, 0);
		place(world, x + 1, y + 14, z + 2, prismarine_slab, 0);
		place(world, x + 1, y + 14, z + 3, prismarine_slab, 0);
		place(world, x + 1, y + 14, z + 4, Blocks.carpet, 7);
		place(world, x + 1, y + 14, z + 5, Blocks.carpet, 7);
		place(world, x + 1, y + 14, z + 6, Blocks.carpet, 7);
		place(world, x + 1, y + 14, z + 7, prismarine_slab, 0);
		place(world, x + 1, y + 14, z + 8, prismarine_slab, 0);
		place(world, x + 1, y + 14, z + 9, prismarine_slab, 0);
		place(world, x + 2, y + 10, z + 4, prismarine_stairs, 4);
		place(world, x + 2, y + 10, z + 5, prismarine_stairs, 4);
		place(world, x + 2, y + 10, z + 6, prismarine_stairs, 4);
		place(world, x + 2, y + 11, z + 4, Blocks.wool, 9);
		place(world, x + 2, y + 11, z + 5, Blocks.wool, 9);
		place(world, x + 2, y + 11, z + 6, Blocks.wool, 9);
		place(world, x + 2, y + 12, z + 3, prismarine_stairs, 4);
		place(world, x + 2, y + 12, z + 4, Blocks.wool, 9);
		place(world, x + 2, y + 12, z + 5, Blocks.wool, 9);
		place(world, x + 2, y + 12, z + 6, Blocks.wool, 9);
		place(world, x + 2, y + 12, z + 7, prismarine_stairs, 4);
		place(world, x + 2, y + 13, z + 2, fence_dark_oak, 0);
		place(world, x + 2, y + 13, z + 3, Blocks.wool, 9);
		place(world, x + 2, y + 13, z + 7, Blocks.wool, 9);
		place(world, x + 2, y + 13, z + 8, fence_dark_oak, 0);
		place(world, x + 2, y + 14, z + 1, prismarine_slab, 0);
		place(world, x + 2, y + 14, z + 2, prismarine_slab, 0);
		place(world, x + 2, y + 14, z + 3, Blocks.carpet, 7);
		place(world, x + 2, y + 14, z + 7, Blocks.carpet, 7);
		place(world, x + 2, y + 14, z + 8, prismarine_slab, 0);
		place(world, x + 2, y + 14, z + 9, prismarine_slab, 0);
		place(world, x + 3, y + 7, z + 5, prismarine_stairs, 4);
		place(world, x + 3, y + 8, z + 4, prismarine_stairs, 4);
		place(world, x + 3, y + 8, z + 5, Blocks.wool, 9);
		place(world, x + 3, y + 8, z + 6, prismarine_stairs, 4);
		place(world, x + 3, y + 9, z + 3, fence_dark_oak, 0);
		place(world, x + 3, y + 9, z + 4, Blocks.wool, 9);
		place(world, x + 3, y + 9, z + 5, Blocks.wool, 9);
		place(world, x + 3, y + 9, z + 6, Blocks.wool, 9);
		place(world, x + 3, y + 9, z + 7, fence_dark_oak, 0);
		place(world, x + 3, y + 10, z + 3, Blocks.wool, 9);
		place(world, x + 3, y + 10, z + 7, Blocks.wool, 9);
		place(world, x + 3, y + 11, z + 3, Blocks.wool, 9);
		place(world, x + 3, y + 11, z + 7, Blocks.wool, 9);
		place(world, x + 3, y + 12, z + 2, prismarine_stairs, 6);
		place(world, x + 3, y + 12, z + 8, prismarine_stairs, 7);
		place(world, x + 3, y + 13, z + 2, Blocks.wool, 9);
		place(world, x + 3, y + 13, z + 8, Blocks.wool, 9);
		place(world, x + 3, y + 14, z + 0, prismarine_slab, 0);
		place(world, x + 3, y + 14, z + 1, prismarine_slab, 0);
		place(world, x + 3, y + 14, z + 2, Blocks.carpet, 7);
		place(world, x + 3, y + 14, z + 8, Blocks.carpet, 7);
		place(world, x + 3, y + 14, z + 9, prismarine_slab, 0);
		place(world, x + 3, y + 14, z + 10, prismarine_slab, 0);
		place(world, x + 4, y + 0, z + 5, Blocks.dark_oak_stairs, 0);
		place(world, x + 4, y + 0, z + 6, Blocks.wooden_slab, 5);
		place(world, x + 4, y + 4, z + 5, fence_dark_oak, 0);
		place(world, x + 4, y + 5, z + 4, fence_dark_oak, 0);
		place(world, x + 4, y + 5, z + 5, Blocks.wool, 9);
		place(world, x + 4, y + 5, z + 6, fence_dark_oak, 0);
		place(world, x + 4, y + 6, z + 4, prismarine_wall, 0);
		place(world, x + 4, y + 6, z + 5, Blocks.wool, 9);
		place(world, x + 4, y + 6, z + 6, prismarine_wall, 0);
		place(world, x + 4, y + 7, z + 4, Blocks.wool, 9);
		place(world, x + 4, y + 7, z + 5, Blocks.wool, 9);
		place(world, x + 4, y + 7, z + 6, Blocks.wool, 9);
		place(world, x + 4, y + 8, z + 3, prismarine_stairs, 6);
		place(world, x + 4, y + 8, z + 7, prismarine_stairs, 7);
		place(world, x + 4, y + 9, z + 3, Blocks.wool, 9);
		place(world, x + 4, y + 9, z + 7, Blocks.wool, 9);
		place(world, x + 4, y + 10, z + 2, prismarine_stairs, 6);
		place(world, x + 4, y + 10, z + 8, prismarine_stairs, 7);
		place(world, x + 4, y + 11, z + 2, Blocks.wool, 9);
		place(world, x + 4, y + 11, z + 8, Blocks.wool, 9);
		place(world, x + 4, y + 12, z + 2, Blocks.wool, 9);
		place(world, x + 4, y + 12, z + 8, Blocks.wool, 9);
		place(world, x + 4, y + 13, z + 1, prismarine_stairs, 6);
		place(world, x + 4, y + 13, z + 9, prismarine_stairs, 7);
		place(world, x + 4, y + 14, z + 0, prismarine_slab, 0);
		place(world, x + 4, y + 14, z + 1, Blocks.carpet, 7);
		place(world, x + 4, y + 14, z + 9, Blocks.carpet, 7);
		place(world, x + 4, y + 14, z + 10, prismarine_slab, 0);
		place(world, x + 4, y + 17, z + 5, smooth_red_sandstone_stairs, 4);
		place(world, x + 5, y + 0, z + 4, Blocks.dark_oak_stairs, 2);
		place(world, x + 5, y + 0, z + 5, Blocks.stained_hardened_clay, 12);
		place(world, x + 5, y + 0, z + 6, Blocks.stained_hardened_clay, 12);
		place(world, x + 5, y + 1, z + 5, Blocks.stained_hardened_clay, 12);
		place(world, x + 5, y + 1, z + 6, fence_dark_oak, 0);
		place(world, x + 5, y + 2, z + 5, Blocks.stained_hardened_clay, 12);
		place(world, x + 5, y + 3, z + 5, Blocks.stained_hardened_clay, 12);
		place(world, x + 5, y + 4, z + 4, fence_dark_oak, 0);
		place(world, x + 5, y + 4, z + 5, Blocks.stained_hardened_clay, 12);
		place(world, x + 5, y + 4, z + 6, fence_dark_oak, 0);
		place(world, x + 5, y + 5, z + 4, Blocks.wool, 9);
		place(world, x + 5, y + 5, z + 5, Blocks.wool, 9);
		place(world, x + 5, y + 5, z + 6, Blocks.wool, 9);
		place(world, x + 5, y + 6, z + 4, Blocks.wool, 9);
		place(world, x + 5, y + 6, z + 5, Blocks.wool, 9);
		place(world, x + 5, y + 6, z + 6, Blocks.wool, 9);
		place(world, x + 5, y + 7, z + 3, prismarine_stairs, 6);
		place(world, x + 5, y + 7, z + 4, Blocks.wool, 9);
		place(world, x + 5, y + 7, z + 5, Blocks.wool, 9);
		place(world, x + 5, y + 7, z + 6, Blocks.wool, 9);
		place(world, x + 5, y + 7, z + 7, prismarine_stairs, 7);
		place(world, x + 5, y + 8, z + 3, Blocks.wool, 9);
		place(world, x + 5, y + 8, z + 5, stone_wall, 2);
		place(world, x + 5, y + 8, z + 7, Blocks.wool, 9);
		place(world, x + 5, y + 9, z + 3, Blocks.wool, 9);
		place(world, x + 5, y + 9, z + 5, stone_wall, 2);
		place(world, x + 5, y + 9, z + 7, Blocks.wool, 9);
		place(world, x + 5, y + 10, z + 2, prismarine_stairs, 6);
		place(world, x + 5, y + 10, z + 5, fence_birch, 0);
		place(world, x + 5, y + 10, z + 8, prismarine_stairs, 7);
		place(world, x + 5, y + 11, z + 2, Blocks.wool, 9);
		place(world, x + 5, y + 11, z + 5, fence_birch, 0);
		place(world, x + 5, y + 11, z + 8, Blocks.wool, 9);
		place(world, x + 5, y + 12, z + 2, Blocks.wool, 9);
		place(world, x + 5, y + 12, z + 5, fence_birch, 0);
		place(world, x + 5, y + 12, z + 8, Blocks.wool, 9);
		place(world, x + 5, y + 13, z + 1, prismarine_stairs, 6);
		place(world, x + 5, y + 13, z + 5, fence_birch, 0);
		place(world, x + 5, y + 13, z + 9, prismarine_stairs, 7);
		place(world, x + 5, y + 14, z + 0, prismarine_slab, 0);
		place(world, x + 5, y + 14, z + 1, Blocks.carpet, 7);
		place(world, x + 5, y + 14, z + 5, fence_birch, 0);
		place(world, x + 5, y + 14, z + 9, Blocks.carpet, 7);
		place(world, x + 5, y + 14, z + 10, prismarine_slab, 0);
		place(world, x + 5, y + 15, z + 5, fence_acacia, 0);
		place(world, x + 5, y + 16, z + 5, red_sandstone_wall, 0);
		place(world, x + 5, y + 17, z + 4, smooth_red_sandstone_slab, 0);
		place(world, x + 5, y + 17, z + 5, smooth_red_sandstone_slab, 0);
		place(world, x + 5, y + 17, z + 6, trapdoor_acacia, 1);
		place(world, x + 6, y + 0, z + 5, Blocks.stained_hardened_clay, 12);
		place(world, x + 6, y + 0, z + 6, fence_dark_oak, 0);
		place(world, x + 6, y + 1, z + 5, Blocks.wooden_slab, 5);
		place(world, x + 6, y + 4, z + 5, fence_dark_oak, 0);
		place(world, x + 6, y + 5, z + 4, fence_dark_oak, 0);
		place(world, x + 6, y + 5, z + 5, Blocks.wool, 9);
		place(world, x + 6, y + 5, z + 6, fence_dark_oak, 0);
		place(world, x + 6, y + 6, z + 4, prismarine_wall, 0);
		place(world, x + 6, y + 6, z + 5, Blocks.wool, 9);
		place(world, x + 6, y + 6, z + 6, prismarine_wall, 0);
		place(world, x + 6, y + 7, z + 4, Blocks.wool, 9);
		place(world, x + 6, y + 7, z + 5, Blocks.wool, 9);
		place(world, x + 6, y + 7, z + 6, Blocks.wool, 9);
		place(world, x + 6, y + 8, z + 3, prismarine_stairs, 6);
		place(world, x + 6, y + 8, z + 7, prismarine_stairs, 7);
		place(world, x + 6, y + 9, z + 3, Blocks.wool, 9);
		place(world, x + 6, y + 9, z + 7, Blocks.wool, 9);
		place(world, x + 6, y + 10, z + 2, prismarine_stairs, 6);
		place(world, x + 6, y + 10, z + 8, prismarine_stairs, 7);
		place(world, x + 6, y + 11, z + 2, Blocks.wool, 9);
		place(world, x + 6, y + 11, z + 8, Blocks.wool, 9);
		place(world, x + 6, y + 12, z + 2, Blocks.wool, 9);
		place(world, x + 6, y + 12, z + 8, Blocks.wool, 9);
		place(world, x + 6, y + 13, z + 1, prismarine_stairs, 6);
		place(world, x + 6, y + 13, z + 9, prismarine_stairs, 7);
		place(world, x + 6, y + 14, z + 0, prismarine_slab, 0);
		place(world, x + 6, y + 14, z + 1, Blocks.carpet, 7);
		place(world, x + 6, y + 14, z + 9, Blocks.carpet, 7);
		place(world, x + 6, y + 14, z + 10, prismarine_slab, 0);
		place(world, x + 6, y + 16, z + 4, trapdoor_acacia, 8);
		place(world, x + 6, y + 16, z + 5, smooth_red_sandstone_slab, 8);
		place(world, x + 6, y + 16, z + 6, smooth_red_sandstone_slab, 8);
		place(world, x + 6, y + 17, z + 5, Blocks.lever, 14);
		place(world, x + 7, y + 7, z + 5, prismarine_stairs, 5);
		place(world, x + 7, y + 8, z + 4, prismarine_stairs, 5);
		place(world, x + 7, y + 8, z + 5, Blocks.wool, 9);
		place(world, x + 7, y + 8, z + 6, prismarine_stairs, 5);
		place(world, x + 7, y + 9, z + 3, fence_dark_oak, 0);
		place(world, x + 7, y + 9, z + 4, Blocks.wool, 9);
		place(world, x + 7, y + 9, z + 5, Blocks.wool, 9);
		place(world, x + 7, y + 9, z + 6, Blocks.wool, 9);
		place(world, x + 7, y + 9, z + 7, fence_dark_oak, 0);
		place(world, x + 7, y + 10, z + 3, Blocks.wool, 9);
		place(world, x + 7, y + 10, z + 7, Blocks.wool, 9);
		place(world, x + 7, y + 11, z + 3, Blocks.wool, 9);
		place(world, x + 7, y + 11, z + 7, Blocks.wool, 9);
		place(world, x + 7, y + 12, z + 2, prismarine_stairs, 6);
		place(world, x + 7, y + 12, z + 8, prismarine_stairs, 7);
		place(world, x + 7, y + 13, z + 2, Blocks.wool, 9);
		place(world, x + 7, y + 13, z + 8, Blocks.wool, 9);
		place(world, x + 7, y + 14, z + 0, prismarine_slab, 0);
		place(world, x + 7, y + 14, z + 1, prismarine_slab, 0);
		place(world, x + 7, y + 14, z + 2, Blocks.carpet, 7);
		place(world, x + 7, y + 14, z + 8, Blocks.carpet, 7);
		place(world, x + 7, y + 14, z + 9, prismarine_slab, 0);
		place(world, x + 7, y + 14, z + 10, prismarine_slab, 0);
		place(world, x + 7, y + 16, z + 5, smooth_red_sandstone_slab, 0);
		place(world, x + 8, y + 10, z + 4, prismarine_stairs, 5);
		place(world, x + 8, y + 10, z + 5, prismarine_stairs, 5);
		place(world, x + 8, y + 10, z + 6, prismarine_stairs, 5);
		place(world, x + 8, y + 11, z + 4, Blocks.wool, 9);
		place(world, x + 8, y + 11, z + 5, Blocks.wool, 9);
		place(world, x + 8, y + 11, z + 6, Blocks.wool, 9);
		place(world, x + 8, y + 12, z + 3, prismarine_stairs, 5);
		place(world, x + 8, y + 12, z + 4, Blocks.wool, 9);
		place(world, x + 8, y + 12, z + 5, Blocks.wool, 9);
		place(world, x + 8, y + 12, z + 6, Blocks.wool, 9);
		place(world, x + 8, y + 12, z + 7, prismarine_stairs, 5);
		place(world, x + 8, y + 13, z + 2, fence_dark_oak, 0);
		place(world, x + 8, y + 13, z + 3, Blocks.wool, 9);
		place(world, x + 8, y + 13, z + 7, Blocks.wool, 9);
		place(world, x + 8, y + 13, z + 8, fence_dark_oak, 0);
		place(world, x + 8, y + 14, z + 1, prismarine_slab, 0);
		place(world, x + 8, y + 14, z + 2, prismarine_slab, 0);
		place(world, x + 8, y + 14, z + 3, Blocks.carpet, 7);
		place(world, x + 8, y + 14, z + 7, Blocks.carpet, 7);
		place(world, x + 8, y + 14, z + 8, prismarine_slab, 0);
		place(world, x + 8, y + 14, z + 9, prismarine_slab, 0);
		place(world, x + 9, y + 13, z + 4, prismarine_stairs, 5);
		place(world, x + 9, y + 13, z + 5, prismarine_stairs, 5);
		place(world, x + 9, y + 13, z + 6, prismarine_stairs, 5);
		place(world, x + 9, y + 14, z + 1, prismarine_slab, 0);
		place(world, x + 9, y + 14, z + 2, prismarine_slab, 0);
		place(world, x + 9, y + 14, z + 3, prismarine_slab, 0);
		place(world, x + 9, y + 14, z + 4, Blocks.carpet, 7);
		place(world, x + 9, y + 14, z + 5, Blocks.carpet, 7);
		place(world, x + 9, y + 14, z + 6, Blocks.carpet, 7);
		place(world, x + 9, y + 14, z + 7, prismarine_slab, 0);
		place(world, x + 9, y + 14, z + 8, prismarine_slab, 0);
		place(world, x + 9, y + 14, z + 9, prismarine_slab, 0);
		place(world, x + 10, y + 14, z + 3, prismarine_slab, 0);
		place(world, x + 10, y + 14, z + 4, prismarine_slab, 0);
		place(world, x + 10, y + 14, z + 5, prismarine_slab, 0);
		place(world, x + 10, y + 14, z + 6, prismarine_slab, 0);
		place(world, x + 10, y + 14, z + 7, prismarine_slab, 0);

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

// 2) now it’s safe to query getBlock()
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

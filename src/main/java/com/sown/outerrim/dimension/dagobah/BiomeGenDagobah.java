package com.sown.outerrim.dimension.dagobah;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;

public class BiomeGenDagobah extends BiomeGenBase {
	private static final String __OBFID = "CL_00000185";

	protected BiomeGenDagobah(int id) {
		super(id);
		this.theBiomeDecorator.treesPerChunk = 25;
		this.theBiomeDecorator.flowersPerChunk = 1;
		this.theBiomeDecorator.deadBushPerChunk = 1;
		this.theBiomeDecorator.mushroomsPerChunk = 8;
		this.theBiomeDecorator.reedsPerChunk = 10;
		this.theBiomeDecorator.clayPerChunk = 1;
		this.theBiomeDecorator.waterlilyPerChunk = 4;
		this.theBiomeDecorator.sandPerChunk2 = 0;
		this.theBiomeDecorator.sandPerChunk = 0;
		this.theBiomeDecorator.grassPerChunk = 15;
		this.waterColorMultiplier = 14745518;
		this.spawnableMonsterList.clear();
		this.spawnableCreatureList.clear();
		this.flowers.clear();
		this.spawnableMonsterList.add(new SpawnListEntry(EntitySpider.class, 100, 2, 4));
	}

	public WorldGenAbstractTree func_150567_a(Random rand) {
		return this.worldGeneratorSwamp;
	}

	public void genTerrainBlocks(World world, Random rand, Block[] blocks, byte[] meta, int x, int z, double noise) {
		double d1 = plantNoise.func_151601_a(x * 0.25D, z * 0.25D);

		if (d1 > 0.0D) {
			int k = x & 15;
			int l = z & 15;
			int i1 = blocks.length / 256;

			for (int j1 = 255; j1 >= 0; --j1) {
				int k1 = (l * 16 + k) * i1 + j1;

				if (blocks[k1] == null || blocks[k1].getMaterial() != Material.air) {
					if (j1 == 62 && blocks[k1] != Blocks.water) {
						blocks[k1] = Blocks.water;

						if (d1 < 0.12D) {
							blocks[k1 + 1] = Blocks.waterlily;
						}
					}
					break;
				}
			}
		}

		this.genBiomeTerrain(world, rand, blocks, meta, x, z, noise);
	}

	@SideOnly(Side.CLIENT)
	public int getBiomeGrassColor(int x, int y, int z) {
		double d0 = plantNoise.func_151601_a(x * 0.0225D, z * 0.0225D);
		return d0 < -0.1D ? 4877114 : 5995595;
	}

	@SideOnly(Side.CLIENT)
	public int getBiomeFoliageColor(int x, int y, int z) {
		return 4020016;
	}

	@SideOnly(Side.CLIENT)
	public int getSkyColorByTemp(float temp) {
		return (23 << 16) | (42 << 8) | 40;
	}

	@Override
	public int getWaterColorMultiplier() {
		return 3818807;
	}
}

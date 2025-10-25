package com.sown.outerrim.dimension.dagobah;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.passive.EntityBat;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenBigTree;
import net.minecraft.world.gen.feature.WorldGenCanopyTree;
import net.minecraft.world.gen.feature.WorldGenSwamp;
import net.minecraft.world.gen.feature.WorldGenTrees;

public class BiomeGenDagobah extends BiomeGenBase {

	private static final WorldGenSwamp SWAMP_TREE = new WorldGenSwamp();
	private static final WorldGenCanopyTree DARK_OAK_TREE = new WorldGenCanopyTree(false);
	private static final WorldGenBigTree HUGE_DARK_OAK = new WorldGenBigTree(false);
	private static final WorldGenTrees SMALL_TREE = new WorldGenTrees(false);

	protected BiomeGenDagobah(int id) {
		super(id);

		this.theBiomeDecorator.treesPerChunk = 28;
		this.theBiomeDecorator.grassPerChunk = 12;
		this.theBiomeDecorator.flowersPerChunk = 1;
		this.theBiomeDecorator.deadBushPerChunk = 2;
		this.theBiomeDecorator.mushroomsPerChunk = 10;
		this.theBiomeDecorator.reedsPerChunk = 8;
		this.theBiomeDecorator.clayPerChunk = 2;
		this.theBiomeDecorator.waterlilyPerChunk = 4;
		this.theBiomeDecorator.sandPerChunk = 0;
		this.theBiomeDecorator.sandPerChunk2 = 0;

		this.waterColorMultiplier = 3818807;
		this.setColor(0x324F28);
		this.setTemperatureRainfall(0.8F, 0.9F);
		this.enableRain = true;

		this.spawnableMonsterList.clear();
		this.spawnableCreatureList.clear();
		this.spawnableWaterCreatureList.clear();
		this.spawnableCaveCreatureList.clear();
		this.flowers.clear();

		this.spawnableCreatureList.add(new SpawnListEntry(EntitySpider.class, 120, 3, 6));
		this.spawnableCreatureList.add(new SpawnListEntry(EntityBat.class, 15, 1, 4));
		this.spawnableCaveCreatureList.add(new SpawnListEntry(EntityBat.class, 15, 1, 4));
		this.spawnableMonsterList.add(new SpawnListEntry(EntityBat.class, 15, 1, 4));
		this.setBiomeName("Dagobah");
	}

	@Override
	public WorldGenAbstractTree func_150567_a(Random rand) {
		int chance = rand.nextInt(20);

		if (chance < 8) {
			return SWAMP_TREE;
		} else if (chance < 14) {
			return DARK_OAK_TREE;
		} else if (chance < 16) {
			return HUGE_DARK_OAK;
		} else {
			return SMALL_TREE;
		}
	}

	@Override
	public void genTerrainBlocks(World world, Random rand, Block[] blocks, byte[] meta, int x, int z, double noise) {
		double d1 = this.plantNoise.func_151601_a(x * 0.25D, z * 0.25D);

		if (d1 > 0.0D) {
			int k = x & 15;
			int l = z & 15;
			int i1 = blocks.length / 256;

			for (int j1 = 255; j1 >= 0; --j1) {
				int k1 = (l * 16 + k) * i1 + j1;

				if (blocks[k1] != null && blocks[k1].getMaterial() != Material.air) {
					if (j1 == 62 && blocks[k1] != Blocks.water) {
						blocks[k1] = Blocks.water;
						if (d1 < 0.12D && rand.nextFloat() < 0.3F) {
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
	@Override
	public int getBiomeGrassColor(int x, int y, int z) {
		double d0 = this.plantNoise.func_151601_a(x * 0.0225D, z * 0.0225D);
		return d0 < -0.1D ? 0x3D4F33 : 0x546E44;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public int getBiomeFoliageColor(int x, int y, int z) {
		return 0x385C2E;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public int getSkyColorByTemp(float temp) {
		return (23 << 16) | (42 << 8) | 40;
	}

	@Override
	public int getWaterColorMultiplier() {
		return 3818807;
	}
}

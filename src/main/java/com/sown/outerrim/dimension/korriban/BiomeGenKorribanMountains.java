package com.sown.outerrim.dimension.korriban;

import com.sown.util.world.creation.OROverworldBiome;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.registry.GameRegistry;
import java.util.Arrays;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.NoiseGeneratorPerlin;

public class BiomeGenKorribanMountains extends OROverworldBiome {
	private byte[] field_150621_aC;
	private long field_150622_aD;
	private NoiseGeneratorPerlin field_150623_aE;
	private NoiseGeneratorPerlin field_150624_aF;
	private NoiseGeneratorPerlin field_150625_aG;
	private boolean field_150626_aH;
	private boolean field_150620_aI;
	protected Block stoneBlock;
	protected byte topMeta;
	protected byte fillerMeta;
	protected byte stoneMeta;

	public BiomeGenKorribanMountains(int id) {
		super(id);
		this.setHeight(new BiomeGenBase.Height(2.0f, 0.5f));
		this.setColor(16750899);
		this.setDisableRain();

		if (Loader.isModLoaded("legends")) {
			this.topBlock = GameRegistry.findBlock("legends", "dynamicLegendsBlock1");
			this.fillerBlock = GameRegistry.findBlock("legends", "dynamicLegendsBlock1");
			this.stoneBlock = GameRegistry.findBlock("legends", "dynamicLegendsBlock1");
		} else {
			this.topBlock = Blocks.sand;
			this.fillerBlock = Blocks.sand;
			this.stoneBlock = Blocks.sand;
			this.field_150604_aj = 1;
		}

		this.setBiomeName("Korriban Mountains");
		this.theBiomeDecorator.treesPerChunk = -999;
		this.theBiomeDecorator.deadBushPerChunk = 0;
		this.theBiomeDecorator.reedsPerChunk = 0;
		this.theBiomeDecorator.cactiPerChunk = 0;
		this.theBiomeDecorator.flowersPerChunk = 0;
		this.spawnableMonsterList.clear();
		this.spawnableCreatureList.clear();
		this.spawnableWaterCreatureList.clear();
		this.spawnableCaveCreatureList.clear();
	}

	@Override
	public void decorate(World par1World, Random par2Random, int par3, int par4) {
		super.decorate(par1World, par2Random, par3, par4);
	}

	@Override
	public void generateBiomeTerrain(World world, Random rand, Block[] block, byte[] meta, int x, int z,
			double stoneNoise) {
		Block topBlock = this.topBlock;
		byte topMeta = this.topMeta;
		Block fillerBlock = this.fillerBlock;
		byte fillerMeta = this.fillerMeta;
		int currentFillerDepth = -1;
		int maxFillerDepth = (int) (stoneNoise / 3.0 + 3.0 + rand.nextDouble() * 0.25);
		int maskX = x & 0xF;
		int maskZ = z & 0xF;
		int worldHeight = block.length / 256;
		int seaLevel = 32;
		for (int y = 255; y >= 0; --y) {
			int index = (maskZ * 16 + maskX) * worldHeight + y;
			if (y <= 0 + rand.nextInt(5)) {
				block[index] = Blocks.bedrock;
				continue;
			}
			Block currentBlock = block[index];
			if (currentBlock == null || currentBlock.getMaterial() == Material.air || currentBlock != Blocks.stone)
				continue;
			if (this.stoneBlock != null) {
				block[index] = this.stoneBlock;
				meta[index] = this.stoneMeta;
			}
			if (currentFillerDepth == -1) {
				if (maxFillerDepth <= 0) {
					topBlock = null;
					topMeta = 0;
					fillerBlock = Blocks.wool;
					fillerMeta = 1;
				} else if (y >= seaLevel - 5 && y <= seaLevel) {
					topBlock = this.topBlock;
					topMeta = this.topMeta;
					fillerBlock = this.fillerBlock;
					fillerMeta = 0;
				}
				if (y < seaLevel - 1 && (topBlock == null || topBlock.getMaterial() == Material.air)) {
					if (this.getFloatTemperature(x, y, z) < 0.15f) {
						topBlock = Blocks.ice;
						topMeta = 0;
					} else {
						topBlock = Blocks.water;
						topMeta = 0;
					}
				}
				currentFillerDepth = maxFillerDepth;
				if (y >= seaLevel - 2) {
					block[index] = topBlock;
					meta[index] = topMeta;
					continue;
				}
				if (y < seaLevel - 8 - maxFillerDepth) {
					topBlock = null;
					fillerBlock = Blocks.glass;
					fillerMeta = 1;
					block[index] = Blocks.gravel;
					continue;
				}
				block[index] = fillerBlock;
				meta[index] = fillerMeta;
				continue;
			}
			if (currentFillerDepth <= 0)
				continue;
			block[index] = fillerBlock;
			meta[index] = fillerMeta;
			if (--currentFillerDepth != 0 || fillerBlock != Blocks.sand)
				continue;
			currentFillerDepth = rand.nextInt(4) + Math.max(0, y - (seaLevel - 1));
			if (Loader.isModLoaded("legends")) {
				fillerBlock = GameRegistry.findBlock("legends", "dynamicLegendsBlock1");
			} else {
				fillerBlock = Blocks.sand;
				fillerMeta = 1;
			}
		}
	}

	@Override
	public void genTerrainBlocks(World par1World, Random par2Random, Block[] par3ArrayOfBlock, byte[] par4ArrayOfByte,
			int par5, int par6, double par7) {
		int l;
		int k;
		if (this.field_150621_aC == null || this.field_150622_aD != par1World.getSeed()) {
			this.func_150619_a(par1World.getSeed());
		}
		if (this.field_150623_aE == null || this.field_150624_aF == null
				|| this.field_150622_aD != par1World.getSeed()) {
			Random random1 = new Random(this.field_150622_aD);
			this.field_150623_aE = new NoiseGeneratorPerlin(random1, 4);
			this.field_150624_aF = new NoiseGeneratorPerlin(random1, 1);
		}
		this.field_150622_aD = par1World.getSeed();
		double d5 = 0.0;
		if (this.field_150626_aH) {
			k = (par5 & 0xFFFFFFF0) + (par6 & 0xF);
			l = (par6 & 0xFFFFFFF0) + (par5 & 0xF);
			double d1 = Math.min(Math.abs(par7),
					this.field_150623_aE.func_151601_a((double) k * 0.25, (double) l * 0.25));
			if (d1 > 0.0) {
				d5 = d1 * d1 * 2.5;
				double d2 = 0.001953125;
				double d3 = Math.abs(this.field_150624_aF.func_151601_a((double) k * d2, (double) l * d2));
				double d4 = Math.ceil(d3 * 50.0) + 14.0;
				if (d5 > d4) {
					d5 = d4;
				}
				d5 += 64.0;
			}
		}
		k = par5 & 0xF;
		l = par6 & 0xF;
		boolean flag = true;
		Block block = Blocks.sand;
		Block block2 = this.fillerBlock;
		int i1 = (int) (par7 / 3.0 + 3.0 + par2Random.nextDouble() * 0.25);
		boolean flag1 = Math.cos(par7 / 3.0 * 3.141592653589793) > 0.0;
		int j1 = -1;
		boolean flag2 = false;
		int k1 = par3ArrayOfBlock.length / 256;
		for (int l1 = 255; l1 >= 0; --l1) {
			int i2 = (l * 16 + k) * k1 + l1;
			if ((par3ArrayOfBlock[i2] == null || par3ArrayOfBlock[i2].getMaterial() == Material.air) && l1 < (int) d5) {
				par3ArrayOfBlock[i2] = Blocks.stone;
			}
			if (l1 <= 0 + par2Random.nextInt(5)) {
				par3ArrayOfBlock[i2] = Blocks.bedrock;
				continue;
			}
			Block block1 = par3ArrayOfBlock[i2];
			if (block1 != null && block1.getMaterial() != Material.air) {
				int b0;
				if (block1 != Blocks.stone)
					continue;
				if (j1 == -1) {
					flag2 = false;
					if (i1 <= 0) {
						block = null;
						block2 = Blocks.stone;
					} else if (l1 >= 59 && l1 <= 64) {
						if (Loader.isModLoaded("legends")) {
							block = GameRegistry.findBlock("legends", "dynamicLegendsBlock1");
						} else {
							block = Blocks.sand;
						}
						block2 = this.fillerBlock;
					}
					if (l1 < 63 && (block == null || block.getMaterial() == Material.air)) {
						block = Blocks.water;
					}
					j1 = i1 + Math.max(0, l1 - 63);
					if (l1 >= 62) {
						if (this.field_150620_aI && l1 > 86 + i1 * 2) {
							if (flag1) {
								par3ArrayOfBlock[i2] = Blocks.dirt;
								par4ArrayOfByte[i2] = 1;
								continue;
							}
							par3ArrayOfBlock[i2] = Blocks.grass;
							continue;
						}
						if (l1 > 66 + i1) {
							b0 = 16;
							if (l1 >= 64 && l1 <= 127) {
								if (!flag1) {
									b0 = this.func_150618_d(par5, l1, par6);
								}
							} else {
								b0 = 1;
							}
							if (b0 < 16) {
								if (Loader.isModLoaded("legends")) {
									par3ArrayOfBlock[i2] = GameRegistry.findBlock("legends", "dynamicLegendsBlock1");
								} else {
									par3ArrayOfBlock[i2] = Blocks.sand;
									par4ArrayOfByte[i2] = 1;
								}
								continue;
							}
							if (Loader.isModLoaded("legends")) {
								par3ArrayOfBlock[i2] = GameRegistry.findBlock("legends", "dynamicLegendsBlock1");
							} else {
								par3ArrayOfBlock[i2] = Blocks.sand;
								par4ArrayOfByte[i2] = 1;
							}
							continue;
						}
						par3ArrayOfBlock[i2] = this.topBlock;
						par4ArrayOfByte[i2] = (byte) this.field_150604_aj;
						flag2 = true;
						continue;
					}
					par3ArrayOfBlock[i2] = block2;
					if (block2 != Blocks.sand)
						continue;
					par4ArrayOfByte[i2] = 1;
					continue;
				}
				if (j1 <= 0)
					continue;
				--j1;
				if (flag2) {
					if (Loader.isModLoaded("legends")) {
						par3ArrayOfBlock[i2] = GameRegistry.findBlock("legends", "dynamicLegendsBlock1");
					} else {
						par3ArrayOfBlock[i2] = Blocks.sand;
						par4ArrayOfByte[i2] = 1;
					}
					continue;
				}
				b0 = this.func_150618_d(par5, l1, par6);
				if (b0 < 16) {
					if (Loader.isModLoaded("legends")) {
						par3ArrayOfBlock[i2] = GameRegistry.findBlock("legends", "dynamicLegendsBlock1");
					} else {
						par3ArrayOfBlock[i2] = Blocks.sand;
						par4ArrayOfByte[i2] = 1;
					}
					continue;
				}
				if (Loader.isModLoaded("legends")) {
					par3ArrayOfBlock[i2] = GameRegistry.findBlock("legends", "dynamicLegendsBlock1");
				} else {
					par3ArrayOfBlock[i2] = Blocks.sand;
					par4ArrayOfByte[i2] = 1;
				}
				continue;
			}
			j1 = -1;
		}
	}

	public void func_150619_a(long par1) {
		int l;
		int i1;
		int j;
		int j1;
		int k;
		int k1;
		this.field_150621_aC = new byte[64];
		Arrays.fill(this.field_150621_aC, (byte) 16);
		Random random = new Random(par1);
		this.field_150625_aG = new NoiseGeneratorPerlin(random, 1);
		for (j = 0; j < 64; ++j) {
			if ((j += random.nextInt(5) + 1) >= 64)
				continue;
			this.field_150621_aC[j] = 1;
		}
		j = random.nextInt(4) + 2;
		for (k = 0; k < j; ++k) {
			l = random.nextInt(3) + 1;
			i1 = random.nextInt(64);
			for (j1 = 0; i1 + j1 < 64 && j1 < l; ++j1) {
				this.field_150621_aC[i1 + j1] = 2;
			}
		}
		k = random.nextInt(4) + 2;
		for (l = 0; l < k; ++l) {
			i1 = random.nextInt(3) + 2;
			j1 = random.nextInt(64);
			for (k1 = 0; j1 + k1 < 64 && k1 < i1; ++k1) {
				this.field_150621_aC[j1 + k1] = 1;
			}
		}
		l = random.nextInt(4) + 2;
		for (i1 = 0; i1 < l; ++i1) {
			j1 = random.nextInt(3) + 1;
			k1 = random.nextInt(64);
			for (int l1 = 0; k1 + l1 < 64 && l1 < j1; ++l1) {
				this.field_150621_aC[k1 + l1] = 2;
			}
		}
		i1 = random.nextInt(3) + 3;
		j1 = 0;
		for (k1 = 0; k1 < i1; ++k1) {
			int b0 = 1;
			for (int i2 = 0; (j1 += random.nextInt(16) + 4) + i2 < 64 && i2 < b0; ++i2) {
				this.field_150621_aC[j1 + i2] = 1;
				if (j1 + i2 > 1 && random.nextBoolean()) {
					this.field_150621_aC[j1 + i2 - 1] = 2;
				}
				if (j1 + i2 >= 63 || !random.nextBoolean())
					continue;
				this.field_150621_aC[j1 + i2 + 1] = 0;
			}
		}
	}

	public byte func_150618_d(int par1, int par2, int par3) {
		int l = (int) Math.round(
				this.field_150625_aG.func_151601_a((double) par1 * 1.0 / 512.0, (double) par1 * 1.0 / 512.0) * 2.0);
		return this.field_150621_aC[(par2 + l + 64) % 64];
	}
}

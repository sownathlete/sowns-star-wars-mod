package com.sown.outerrim.dimension.corfai;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;

public class GenLayerCorfaiBiomes extends GenLayer {
	protected BiomeGenBase[] commonBiomes = new BiomeGenBase[] { BiomeGenBase.plains, BiomeGenBase.forest,
			BiomeGenBase.birchForest, BiomeGenBase.forestHills, BiomeGenBase.extremeHills };

	protected BiomeGenBase[] rareBiomes = new BiomeGenBase[] { BiomeGenBase.megaTaiga, BiomeGenBase.iceMountains };

	public GenLayerCorfaiBiomes(long seed, GenLayer parent) {
		super(seed);
		this.parent = parent;
	}

	public GenLayerCorfaiBiomes(long seed) {
		super(seed);
	}

	@Override
	public int[] getInts(int x, int z, int width, int depth) {
		int[] dest = IntCache.getIntCache(width * depth);
		for (int dz = 0; dz < depth; dz++) {
			for (int dx = 0; dx < width; dx++) {
				initChunkSeed(dx + x, dz + z);
				if (nextInt(120) == 0)
					dest[dx + dz * width] = rareBiomes[nextInt(rareBiomes.length)].biomeID;
				else
					dest[dx + dz * width] = commonBiomes[nextInt(commonBiomes.length)].biomeID;
			}
		}
		return dest;
	}
}

package com.sown.outerrim.dimension.bothawui;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;

public class GenLayerBothawuiBiomes extends GenLayer {

	protected BiomeGenBase[] commonBiomes = new BiomeGenBase[] { BiomeGenBase.plains, BiomeGenBase.forest,
			BiomeGenBase.savanna, BiomeGenBase.extremeHills, BiomeGenBase.taiga };

	protected BiomeGenBase[] rareBiomes = new BiomeGenBase[] { BiomeGenBase.icePlains, BiomeGenBase.iceMountains };

	public GenLayerBothawuiBiomes(long seed, GenLayer parent) {
		super(seed);
		this.parent = parent;
	}

	public GenLayerBothawuiBiomes(long seed) {
		super(seed);
	}

	@Override
	public int[] getInts(int x, int z, int width, int depth) {
		int[] dest = IntCache.getIntCache(width * depth);
		for (int dz = 0; dz < depth; dz++) {
			for (int dx = 0; dx < width; dx++) {
				initChunkSeed(dx + x, dz + z);
				if (nextInt(100) < 5)
					dest[dx + dz * width] = rareBiomes[nextInt(rareBiomes.length)].biomeID;
				else
					dest[dx + dz * width] = commonBiomes[nextInt(commonBiomes.length)].biomeID;
			}
		}
		return dest;
	}
}

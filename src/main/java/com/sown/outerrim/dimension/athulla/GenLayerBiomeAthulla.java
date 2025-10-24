package com.sown.outerrim.dimension.athulla;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.layer.*;

public class GenLayerBiomeAthulla extends GenLayer {
	public GenLayerBiomeAthulla(long seed) {
		super(seed);
	}

	@Override
	public int[] getInts(int areaX, int areaY, int areaWidth, int areaHeight) {
		int[] biomes = IntCache.getIntCache(areaWidth * areaHeight);

		for (int y = 0; y < areaHeight; y++) {
			for (int x = 0; x < areaWidth; x++) {
				initChunkSeed(x + areaX, y + areaY);
				int n = nextInt(100);

				if (n < 50)
					biomes[x + y * areaWidth] = BiomeGenBase.jungle.biomeID;
				else if (n < 75)
					biomes[x + y * areaWidth] = BiomeGenBase.forest.biomeID;
				else if (n < 95)
					biomes[x + y * areaWidth] = BiomeGenBase.swampland.biomeID;
				else if (n < 99)
					biomes[x + y * areaWidth] = BiomeGenBase.plains.biomeID;
				else
					biomes[x + y * areaWidth] = BiomeGenBase.extremeHills.biomeID;
			}
		}
		return biomes;
	}

	public static GenLayer[] makeTheWorld(long seed) {
		GenLayer biomes = new GenLayerAthullaBiomes(1L);
		GenLayer gen = new GenLayerZoom(1000L, biomes);
		for (int i = 1; i <= 5; i++)
			gen = new GenLayerZoom(1000L + i, gen);

		GenLayer rivers = new GenLayerRiverInit(1000L, gen);
		for (int i = 0; i <= 6; i++)
			rivers = new GenLayerZoom(1000L + i, rivers);
		rivers = new GenLayerRiver(1L, rivers);
		rivers = new GenLayerSmooth(1000L, rivers);

		gen = new GenLayerRiverMix(1000L, gen, rivers);
		GenLayerVoronoiZoom v = new GenLayerVoronoiZoom(10L, gen);
		gen.initWorldGenSeed(seed);
		v.initWorldGenSeed(seed);
		return new GenLayer[] { gen, v, rivers };
	}
}

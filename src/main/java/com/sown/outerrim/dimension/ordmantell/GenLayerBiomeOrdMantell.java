package com.sown.outerrim.dimension.ordmantell;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.layer.*;

public class GenLayerBiomeOrdMantell extends GenLayer {

	public GenLayerBiomeOrdMantell(long seed) {
		super(seed);
	}

	@Override
	public int[] getInts(int areaX, int areaY, int areaWidth, int areaHeight) {
		int[] biomes = IntCache.getIntCache(areaWidth * areaHeight);
		for (int y = 0; y < areaHeight; y++) {
			for (int x = 0; x < areaWidth; x++) {
				initChunkSeed(x + areaX, y + areaY);
				int n = nextInt(100);

				if (n < 25)
					biomes[x + y * areaWidth] = BiomeGenBase.desertHills.biomeID;
				else if (n < 45)
					biomes[x + y * areaWidth] = BiomeGenBase.mesaPlateau.biomeID;
				else if (n < 60)
					biomes[x + y * areaWidth] = BiomeGenBase.savannaPlateau.biomeID;
				else if (n < 80)
					biomes[x + y * areaWidth] = BiomeGenBase.plains.biomeID;
				else if (n < 90)
					biomes[x + y * areaWidth] = BiomeGenBase.extremeHills.biomeID;
				else
					biomes[x + y * areaWidth] = BiomeGenBase.forestHills.biomeID;
			}
		}
		return biomes;
	}

	public static GenLayer[] makeTheWorld(long seed) {
		GenLayer biomes = new GenLayerOrdMantellBiomes(1L);
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

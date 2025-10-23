package com.sown.outerrim.dimension.corfai;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.layer.*;

public class GenLayerBiomeCorfai extends GenLayer {

	public GenLayerBiomeCorfai(long seed) {
		super(seed);
	}

	@Override
	public int[] getInts(int areaX, int areaY, int areaWidth, int areaHeight) {
		int[] biomes = IntCache.getIntCache(areaWidth * areaHeight);
		for (int y = 0; y < areaHeight; y++) {
			for (int x = 0; x < areaWidth; x++) {
				initChunkSeed(x + areaX, y + areaY);
				int n = nextInt(100);
				if (n < 20)
					biomes[x + y * areaWidth] = BiomeGenBase.deepOcean.biomeID;
				else if (n < 45)
					biomes[x + y * areaWidth] = BiomeGenBase.plains.biomeID;
				else if (n < 70)
					biomes[x + y * areaWidth] = BiomeGenBase.forest.biomeID;
				else if (n < 85)
					biomes[x + y * areaWidth] = BiomeGenBase.forestHills.biomeID;
				else
					biomes[x + y * areaWidth] = BiomeGenBase.birchForest.biomeID;
			}
		}
		return biomes;
	}

	public static GenLayer[] makeTheWorld(long seed) {
		GenLayer biomes = new GenLayerCorfaiBiomes(1L);
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

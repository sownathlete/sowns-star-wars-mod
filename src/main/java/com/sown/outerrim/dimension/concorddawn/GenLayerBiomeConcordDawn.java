package com.sown.outerrim.dimension.concorddawn;

import net.minecraft.world.gen.layer.*;
import net.minecraft.world.biome.BiomeGenBase;

public class GenLayerBiomeConcordDawn extends GenLayer {

	public GenLayerBiomeConcordDawn(long seed) {
		super(seed);
	}

	@Override
	public int[] getInts(int areaX, int areaY, int areaWidth, int areaHeight) {
		int[] biomes = IntCache.getIntCache(areaWidth * areaHeight);
		for (int y = 0; y < areaHeight; y++) {
			for (int x = 0; x < areaWidth; x++) {
				initChunkSeed(x + areaX, y + areaY);
				int n = nextInt(100);
				if (n < 5)
					biomes[x + y * areaWidth] = BiomeGenBase.river.biomeID;
				else if (n < 25)
					biomes[x + y * areaWidth] = BiomeGenBase.savanna.biomeID;
				else if (n < 45)
					biomes[x + y * areaWidth] = BiomeGenBase.savannaPlateau.biomeID;
				else if (n < 70)
					biomes[x + y * areaWidth] = BiomeGenBase.extremeHills.biomeID;
				else if (n < 85)
					biomes[x + y * areaWidth] = BiomeGenBase.plains.biomeID;
				else if (n < 95)
					biomes[x + y * areaWidth] = BiomeGenBase.coldTaiga.biomeID;
				else
					biomes[x + y * areaWidth] = BiomeGenBase.icePlains.biomeID;
			}
		}
		return biomes;
	}

	public static GenLayer[] makeTheWorld(long seed) {
		GenLayer biomes = new GenLayerConcordDawnBiomes(1L);
		GenLayer gen = new GenLayerZoom(1000L, biomes);
		for (int i = 0; i < 6; i++)
			gen = new GenLayerZoom(1000L + i, gen);
		GenLayer rivers = new GenLayerRiverInit(1000L, gen);
		for (int i = 0; i < 7; i++)
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

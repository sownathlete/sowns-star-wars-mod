package com.sown.outerrim.dimension.bothawui;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.layer.*;

public class GenLayerBiomeBothawui extends GenLayer {

	public GenLayerBiomeBothawui(long seed) {
		super(seed);
	}

	@Override
	public int[] getInts(int areaX, int areaY, int areaWidth, int areaHeight) {
		int[] biomes = IntCache.getIntCache(areaWidth * areaHeight);
		for (int y = 0; y < areaHeight; y++) {
			for (int x = 0; x < areaWidth; x++) {
				initChunkSeed(x + areaX, y + areaY);
				int n = nextInt(100);
				if (n < 6)
					biomes[x + y * areaWidth] = BiomeGenBase.river.biomeID;
				else if (n < 25)
					biomes[x + y * areaWidth] = BiomeGenBase.plains.biomeID;
				else if (n < 45)
					biomes[x + y * areaWidth] = BiomeGenBase.forest.biomeID;
				else if (n < 65)
					biomes[x + y * areaWidth] = BiomeGenBase.extremeHills.biomeID;
				else if (n < 80)
					biomes[x + y * areaWidth] = BiomeGenBase.taiga.biomeID;
				else if (n < 90)
					biomes[x + y * areaWidth] = BiomeGenBase.icePlains.biomeID;
				else
					biomes[x + y * areaWidth] = BiomeGenBase.iceMountains.biomeID;
			}
		}
		return biomes;
	}

	public static GenLayer[] makeTheWorld(long seed) {
		GenLayer biomes = new GenLayerBothawuiBiomes(1L);
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

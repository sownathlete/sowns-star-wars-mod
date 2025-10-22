package com.sown.outerrim.dimension.tython;

import com.sown.outerrim.OuterRimResources;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.GenLayerVoronoiZoom;
import net.minecraft.world.gen.layer.GenLayerZoom;
import net.minecraft.world.gen.layer.IntCache;

public class GenLayerTython extends GenLayer {
    public GenLayerTython(long seed) {
        super(seed);
    }

    @Override
    public int[] getInts(int areaX, int areaY, int areaWidth, int areaHeight) {
        int[] biomes = IntCache.getIntCache(areaWidth * areaHeight);

        for (int y = 0; y < areaHeight; y++) {
            for (int x = 0; x < areaWidth; x++) {
                initChunkSeed((long)(x + areaX), (long)(y + areaY));

                // 50/50 chance between the two Tython biomes
                if (nextInt(2) == 0) {
                    biomes[x + y * areaWidth] = OuterRimResources.ConfigOptions.biomeTythonForestId;  // ilum biome
                } else {
                    biomes[x + y * areaWidth] = OuterRimResources.ConfigOptions.biomeTythonMountainsId;  // ilumPlateau biome
                }
            }
        }

        return biomes;
    }

    public static GenLayer[] makeTheWorld(long seed) {
        GenLayer biomes = new GenLayerTythonBiomes(1L);
        biomes = new GenLayerZoom(1000L, (GenLayer)biomes);
        biomes = new GenLayerZoom(1001L, (GenLayer)biomes);
        biomes = new GenLayerZoom(1002L, (GenLayer)biomes);
        biomes = new GenLayerZoom(1003L, (GenLayer)biomes);
        GenLayerVoronoiZoom genlayervoronoizoom = new GenLayerVoronoiZoom(10L, (GenLayer)biomes);
        biomes.initWorldGenSeed(seed);
        genlayervoronoizoom.initWorldGenSeed(seed);
        return new GenLayer[]{biomes, genlayervoronoizoom};
    }
}

package com.sown.outerrim.dimension.ahchto;

import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.GenLayerRiver;
import net.minecraft.world.gen.layer.GenLayerRiverInit;
import net.minecraft.world.gen.layer.GenLayerRiverMix;
import net.minecraft.world.gen.layer.GenLayerSmooth;
import net.minecraft.world.gen.layer.GenLayerVoronoiZoom;
import net.minecraft.world.gen.layer.GenLayerZoom;
import net.minecraft.world.gen.layer.IntCache;

import com.sown.outerrim.OuterRimResources;

import net.minecraft.world.biome.BiomeGenBase;

public class GenLayerBiomeAhchTo extends GenLayer {

    public GenLayerBiomeAhchTo(long seed) {
        super(seed);
    }

    @Override
    public int[] getInts(int areaX, int areaY, int areaWidth, int areaHeight) {
        int[] biomes = IntCache.getIntCache(areaWidth * areaHeight);

        for (int y = 0; y < areaHeight; y++) {
            for (int x = 0; x < areaWidth; x++) {
                initChunkSeed((long) (x + areaX), (long) (y + areaY));

                // 50% chance for each biome
                if (nextInt(100) < 50) { // 50% chance
                    biomes[x + y * areaWidth] = OuterRimResources.ConfigOptions.biomeAhchToId;
                } else { // 50% chance
                    biomes[x + y * areaWidth] = BiomeGenBase.deepOcean.biomeID; // Using the ocean biome ID
                }
            }
        }

        return biomes;
    }

    
    public static GenLayer[] makeTheWorld(long seed) {
        GenLayer biomes = new GenLayerAhchToBiomes(1L);

        // Initial zoom layers
        GenLayer genLayer = new GenLayerZoom(1000L, biomes);
        genLayer = new GenLayerZoom(1001L, genLayer);
        genLayer = new GenLayerZoom(1002L, genLayer);
        genLayer = new GenLayerZoom(1003L, genLayer);

        // Further zoom layers
        genLayer = new GenLayerZoom(1004L, genLayer);
        genLayer = new GenLayerZoom(1005L, genLayer);

        GenLayerVoronoiZoom genLayerVoronoiZoom = new GenLayerVoronoiZoom(10L, genLayer);

        genLayer.initWorldGenSeed(seed);
        genLayerVoronoiZoom.initWorldGenSeed(seed);

        return new GenLayer[] { genLayer, genLayerVoronoiZoom };
    }
}
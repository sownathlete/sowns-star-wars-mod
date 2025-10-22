package com.sown.outerrim.dimension.bespin;

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

public class GenLayerBiomeBespin extends GenLayer {

    public GenLayerBiomeBespin(long seed) {
        super(seed);
    }

    @Override
    public int[] getInts(int areaX, int areaY, int areaWidth, int areaHeight) {
        int[] biomes = IntCache.getIntCache(areaWidth * areaHeight);

        for (int y = 0; y < areaHeight; y++) {
            for (int x = 0; x < areaWidth; x++) {
                initChunkSeed((long)(x + areaX), (long)(y + areaY));
                
                int nextInt = nextInt(100); // Working with 100 for easier percentage calculations
                
                if (nextInt < 30) {  // 30% chance
                    biomes[x + y * areaWidth] = OuterRimResources.ConfigOptions.biomeBespinId;
                } else if (nextInt < 65) {  // 35% chance
                    biomes[x + y * areaWidth] = OuterRimResources.ConfigOptions.biomeBespinId;
                } else if (nextInt < 88) {  // 23% chance
                    biomes[x + y * areaWidth] = OuterRimResources.ConfigOptions.biomeBespinId;
                } else if (nextInt < 99) {  // 11% chance
                    biomes[x + y * areaWidth] = OuterRimResources.ConfigOptions.biomeBespinId;
                } else {  // 1% chance
                    biomes[x + y * areaWidth] = OuterRimResources.ConfigOptions.biomeBespinId;
                }
            }
        }

        return biomes;
    }
    
    public static GenLayer[] makeTheWorld(long seed) {
        GenLayer biomes = new GenLayerBespinBiomes(1L);

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
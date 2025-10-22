package com.sown.outerrim.dimension.dantooine;

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

public class GenLayerBiomeDantooine extends GenLayer {

    public GenLayerBiomeDantooine(long seed) {
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
                    biomes[x + y * areaWidth] = BiomeGenBase.deepOcean.biomeID;
                } else if (nextInt < 75) {  // 35% chance
                    biomes[x + y * areaWidth] = BiomeGenBase.savanna.biomeID;
                } else if (nextInt < 88) {  // 23% chance
                    biomes[x + y * areaWidth] = BiomeGenBase.savannaPlateau.biomeID;
                } else if (nextInt < 99) {  // 11% chance
                    biomes[x + y * areaWidth] = BiomeGenBase.plains.biomeID;
                } else {  // 1% chance
                    biomes[x + y * areaWidth] = BiomeGenBase.plains.biomeID;
                }
            }
        }

        return biomes;
    }
    
    public static GenLayer[] makeTheWorld(long seed) {
        GenLayer biomes = new GenLayerDantooineBiomes(1L);

        // Initial zoom layers
        GenLayer genLayer = new GenLayerZoom(1000L, biomes);
        genLayer = new GenLayerZoom(1001L, genLayer);
        genLayer = new GenLayerZoom(1002L, genLayer);
        genLayer = new GenLayerZoom(1003L, genLayer);

        // Further zoom layers
        genLayer = new GenLayerZoom(1004L, genLayer);
        genLayer = new GenLayerZoom(1005L, genLayer);

        // River layers
        GenLayer rivers = new GenLayerRiverInit(1000L, genLayer);
        rivers = new GenLayerZoom(1000L, rivers);
        rivers = new GenLayerZoom(1001L, rivers);
        rivers = new GenLayerZoom(1002L, rivers);
        rivers = new GenLayerZoom(1003L, rivers);
        rivers = new GenLayerZoom(1004L, rivers);
        rivers = new GenLayerZoom(1005L, rivers);
        rivers = new GenLayerZoom(1006L, rivers);
        rivers = new GenLayerRiver(1L, rivers);
        rivers = new GenLayerSmooth(1000L, rivers);

        // Combine rivers with biome map
        genLayer = new GenLayerRiverMix(1000L, genLayer, rivers);

        GenLayerVoronoiZoom genLayerVoronoiZoom = new GenLayerVoronoiZoom(10L, genLayer);

        genLayer.initWorldGenSeed(seed);
        genLayerVoronoiZoom.initWorldGenSeed(seed);

        return new GenLayer[] { genLayer, genLayerVoronoiZoom, rivers };
    }
}
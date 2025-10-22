package com.sown.outerrim.dimension.endor;

import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.GenLayerRiver;
import net.minecraft.world.gen.layer.GenLayerRiverInit;
import net.minecraft.world.gen.layer.GenLayerRiverMix;
import net.minecraft.world.gen.layer.GenLayerSmooth;
import net.minecraft.world.gen.layer.GenLayerVoronoiZoom;
import net.minecraft.world.gen.layer.GenLayerZoom;
import net.minecraft.world.gen.layer.IntCache;

import com.sown.outerrim.OuterRimResources;

public class GenLayerBiomeEndor extends GenLayer {

    public GenLayerBiomeEndor(long seed) {
        super(seed);
    }

    @Override
    public int[] getInts(int areaX, int areaY, int areaWidth, int areaHeight) {
        int[] biomes = IntCache.getIntCache(areaWidth * areaHeight);

        for (int y = 0; y < areaHeight; y++) {
            for (int x = 0; x < areaWidth; x++) {
                initChunkSeed((long)(x + areaX), (long)(y + areaY));
                int nextInt = nextInt(100);

                if (nextInt < 50) {
                    biomes[x + y * areaWidth] = OuterRimResources.ConfigOptions.biomeEndorMegaTaigaId;
                } else if (nextInt < 90) {
                    biomes[x + y * areaWidth] = OuterRimResources.ConfigOptions.biomeEndorMegaTaigaHillsId;
                } else {
                    biomes[x + y * areaWidth] = OuterRimResources.ConfigOptions.biomeEndorTaigaId;
                }
            }
        }

        return biomes;
    }

    public static GenLayer[] makeTheWorld(long seed) {
        // 1) Base biome layer
        GenLayer biomes = new GenLayerBiomeEndor(1L);

        // 2) Zoom base layer 6 times
        GenLayer gen = new GenLayerZoom(1000L, biomes);
        gen = new GenLayerZoom(1001L, gen);
        gen = new GenLayerZoom(1002L, gen);
        gen = new GenLayerZoom(1003L, gen);
        gen = new GenLayerZoom(1004L, gen);
        gen = new GenLayerZoom(1005L, gen);

        // 3) Build river layer from the same zoomed source
        GenLayer rivers = new GenLayerRiverInit(2000L, gen);
        rivers = new GenLayerZoom(2001L, rivers);
        rivers = new GenLayerZoom(2002L, rivers);
        rivers = new GenLayerZoom(2003L, rivers);
        rivers = new GenLayerZoom(2004L, rivers);
        rivers = new GenLayerZoom(2005L, rivers);
        rivers = new GenLayerZoom(2006L, rivers);
        rivers = new GenLayerRiver(1L, rivers);
        rivers = new GenLayerSmooth(1000L, rivers);

        // 4) Mix biomes and rivers
        GenLayer mixed = new GenLayerRiverMix(4000L, gen, rivers);

        // 5) **Additional smoothing to prevent river fragmentation**
        mixed = new GenLayerSmooth(5000L, mixed);

        // 6) Final Voronoi zoom for biome edges
        GenLayerVoronoiZoom voronoi = new GenLayerVoronoiZoom(10L, mixed);

        // 7) Initialize seeds
        mixed.initWorldGenSeed(seed);
        voronoi.initWorldGenSeed(seed);

        return new GenLayer[] { mixed, voronoi, rivers };
    }
}

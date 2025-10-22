package com.sown.outerrim.dimension.dathomir;

import net.minecraft.world.gen.layer.*;

public class GenLayerBiomeDathomir extends GenLayer {

    public GenLayerBiomeDathomir(long seed) {
        super(seed);
    }

    public static GenLayer[] makeTheWorld(long seed) {
        GenLayer base = new GenLayerDathomirBiomes(seed);

        // Apply biome zooms to make bigger patches
        GenLayer zoomed = new GenLayerZoom(1000L, base);
        zoomed = new GenLayerZoom(1001L, zoomed);
        zoomed = new GenLayerZoom(1002L, zoomed);
        zoomed = new GenLayerZoom(1003L, zoomed);
        zoomed = new GenLayerZoom(1004L, zoomed);

        // Optional: Voronoi for nice borders
        GenLayerVoronoiZoom voronoi = new GenLayerVoronoiZoom(10L, zoomed);

        zoomed.initWorldGenSeed(seed);
        voronoi.initWorldGenSeed(seed);

        return new GenLayer[] { zoomed, voronoi };
    }

    @Override
    public int[] getInts(int x, int z, int width, int depth) {
        return null; // Not used directly
    }
}

package com.sown.outerrim.dimension.niamos;

import com.sown.outerrim.OuterRimResources;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.layer.*;

/**
 * Assigns Niamos or Ocean, then smooths and adds real beaches (no deep ocean, no rivers).
 */
public class GenLayerBiomeNiamos extends GenLayer {
    public GenLayerBiomeNiamos(long seed) {
        super(seed);
    }

    @Override
    public int[] getInts(int startX, int startZ, int width, int height) {
        int[] out = IntCache.getIntCache(width * height);

        for (int dz = 0; dz < height; dz++) {
            for (int dx = 0; dx < width; dx++) {
                initChunkSeed(dx + startX, dz + startZ);
                int r = nextInt(100);
                if (r < 20) {
                    // 20% Niamos
                    out[dx + dz * width] = OuterRimResources.ConfigOptions.biomeNiamosId;
                } else {
                    // 80% Ocean
                    out[dx + dz * width] = BiomeGenBase.ocean.biomeID;
                }
            }
        }

        return out;
    }

    /**
     * Build the layer stack:
     *   [0] smoothed land/ocean map with beaches,
     *   [1] final Voronoi layer for biome borders.
     */
    public static GenLayer[] makeTheWorld(long seed) {
        // 1) start with raw Niamos vs Ocean
        GenLayer base = new GenLayerBiomeNiamos(seed);

        // 2) zoom out to create big patches
        base = new GenLayerZoom(1000L, base);
        base = new GenLayerZoom(1001L, base);
        base = new GenLayerZoom(1002L, base);
        base = new GenLayerZoom(1003L, base);

        // 3) run the shore maker multiple times for a thicker band of beaches
        base = new GenLayerShore(100L, base);
        base = new GenLayerShore(101L, base);  // second pass
        base = new GenLayerShore(102L, base);  // third pass if you really want it chunky

        // 4) smooth and blend a bit more
        base = new GenLayerSmooth(1000L, base);
        base = new GenLayerZoom(1004L, base);
        base = new GenLayerSmooth(1001L, base);

        // 5) Voronoi for crisp borders
        GenLayer voronoi = new GenLayerVoronoiZoom(10L, base);

        base.initWorldGenSeed(seed);
        voronoi.initWorldGenSeed(seed);
        return new GenLayer[]{ base, voronoi };
    }

}

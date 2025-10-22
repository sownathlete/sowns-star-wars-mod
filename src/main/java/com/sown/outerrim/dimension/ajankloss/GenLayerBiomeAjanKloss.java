package com.sown.outerrim.dimension.ajankloss;

import com.sown.outerrim.OuterRimResources;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.GenLayerRiverInit;
import net.minecraft.world.gen.layer.GenLayerZoom;
import net.minecraft.world.gen.layer.GenLayerRiver;
import net.minecraft.world.gen.layer.GenLayerSmooth;
import net.minecraft.world.gen.layer.GenLayerRiverMix;
import net.minecraft.world.gen.layer.GenLayerVoronoiZoom;
import net.minecraft.world.gen.layer.IntCache;

public class GenLayerBiomeAjanKloss {

    /**
     * Builds a pure Ajan Kloss world with very sparse, thin rivers.
     */
    public static GenLayer[] makeTheWorld(long seed) {
        // 1) base layer: entire world is Ajan Kloss biome
        GenLayer base = new GenLayerConstantAjanKloss(1L);

        // 2) river init on that base
        GenLayer river = new GenLayerRiverInit(2L, base);

        // 3) zoom many times to spread out seeds before carving
        river = new GenLayerZoom(3L, river);
        river = new GenLayerZoom(4L, river);
        river = new GenLayerZoom(5L, river);
        river = new GenLayerZoom(6L, river);
        river = new GenLayerZoom(7L, river);
        river = new GenLayerZoom(8L, river);

        // 4) carve rivers along those stretched seeds
        river = new GenLayerRiver(9L, river);

        // 5) smooth river edges for continuity
        river = new GenLayerSmooth(10L, river);

        // 6) mix rivers into the jungle world (river replaces jungle where it occurs)
        GenLayer finalMix = new GenLayerRiverMix(11L, base, river);

        // 7) final Voronoi zoom for soft borders
        GenLayer voronoi = new GenLayerVoronoiZoom(12L, finalMix);

        // seed all layers
        base.initWorldGenSeed(seed);
        river.initWorldGenSeed(seed);
        finalMix.initWorldGenSeed(seed);
        voronoi.initWorldGenSeed(seed);

        // return in Minecraft order: biome+rivers, voronoi, rivers
        return new GenLayer[] { finalMix, voronoi, river };
    }

    // ---------------------------------------------------------------------
    // layer that outputs only the Ajan Kloss biome everywhere
    private static class GenLayerConstantAjanKloss extends GenLayer {
        public GenLayerConstantAjanKloss(long seed) {
            super(seed);
        }

        @Override
        public int[] getInts(int x, int z, int width, int height) {
            int total = width * height;
            int[] out = IntCache.getIntCache(total);
            int id = OuterRimResources.ConfigOptions.biomeAjanKlossId;
            for (int i = 0; i < total; i++) {
                out[i] = id;
            }
            return out;
        }
    }
}

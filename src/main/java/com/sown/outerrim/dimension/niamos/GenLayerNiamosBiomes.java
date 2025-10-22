package com.sown.outerrim.dimension.niamos;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;

public class GenLayerNiamosBiomes extends GenLayer {
    private final BiomeGenBase[] common = {
        BiomeGenBase.ocean,
        BiomeGenBase.ocean,
        BiomeGenBase.ocean
    };
    private final BiomeGenBase[] rare = {
        NiamosProvider.niamos
    };

    public GenLayerNiamosBiomes(long seed, GenLayer parent) {
        super(seed);
        this.parent = parent;
    }

    @Override
    public int[] getInts(int x, int z, int width, int depth) {
        int[] out = IntCache.getIntCache(width * depth);

        for (int dz = 0; dz < depth; dz++) {
            for (int dx = 0; dx < width; dx++) {
                this.initChunkSeed(dx + x, dz + z);
                if (this.nextInt(100) < 1) {
                    out[dx + dz * width] = rare[ this.nextInt(rare.length) ].biomeID;
                } else {
                    out[dx + dz * width] = common[ this.nextInt(common.length) ].biomeID;
                }
            }
        }

        return out;
    }
}

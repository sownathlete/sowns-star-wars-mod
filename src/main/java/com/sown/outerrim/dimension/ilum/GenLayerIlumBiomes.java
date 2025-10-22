package com.sown.outerrim.dimension.ilum;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;

public class GenLayerIlumBiomes extends GenLayer {
    protected BiomeGenBase[] allBiomes = new BiomeGenBase[]{
        IlumBiomes.ilumMountains, 
        IlumBiomes.ilum
    };

    public GenLayerIlumBiomes(long seed, GenLayer genlayer) {
        super(seed);
        this.parent = genlayer;
    }

    public GenLayerIlumBiomes(long seed) {
        super(seed);
    }

    @Override
    public int[] getInts(int x, int z, int width, int depth) {
        int[] dest = IntCache.getIntCache(width * depth);
        for (int dz = 0; dz < depth; ++dz) {
            for (int dx = 0; dx < width; ++dx) {
                this.initChunkSeed(dx + x, dz + z);
                dest[dx + dz * width] = this.allBiomes[this.nextInt(this.allBiomes.length)].biomeID;
            }
        }
        return dest;
    }
}
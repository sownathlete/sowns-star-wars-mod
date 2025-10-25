package com.sown.outerrim.dimension.kashyyyk;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;

public class GenLayerKashyyykBiomes
extends GenLayer {
    protected BiomeGenBase[] commonBiomes = new BiomeGenBase[]{BiomeGenBase.deepOcean, BiomeGenBase.deepOcean, BiomeGenBase.deepOcean, BiomeGenBase.ocean, KashyyykProvider.kashyyyk, KashyyykProvider.kashyyyk, KashyyykProvider.kashyyyk, BiomeGenBase.deepOcean};
    protected BiomeGenBase[] rareBiomes = new BiomeGenBase[]{BiomeGenBase.ocean};

    public GenLayerKashyyykBiomes(long seed, GenLayer genlayer) {
        super(seed);
        this.parent = genlayer;
    }

    public GenLayerKashyyykBiomes(long seed) {
        super(seed);
    }

    @Override
    public int[] getInts(int x, int z, int width, int depth) {
        int[] dest = IntCache.getIntCache(width * depth);
        for (int dz = 0; dz < depth; ++dz) {
            for (int dx = 0; dx < width; ++dx) {
                this.initChunkSeed(dx + x, dz + z);
                dest[dx + dz * width] = this.nextInt(150) == 0 ? this.rareBiomes[this.nextInt(this.rareBiomes.length)].biomeID : this.commonBiomes[this.nextInt(this.commonBiomes.length)].biomeID;
            }
        }
        return dest;
    }
}


/*
 * Decompiled with CFR 0.148.
 *
 * Could not load the following classes:
 *  net.minecraft.world.biome.BiomeGenBase
 *  net.minecraft.world.gen.layer.GenLayer
 *  net.minecraft.world.gen.layer.IntCache
 */
package com.sown.outerrim.dimension.alderaan;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;

public class GenLayerAlderaanBiomes
extends GenLayer {
    protected BiomeGenBase[] commonBiomes = new BiomeGenBase[]{BiomeGenBase.deepOcean, BiomeGenBase.deepOcean, BiomeGenBase.deepOcean, BiomeGenBase.forest, BiomeGenBase.forestHills, BiomeGenBase.plains, BiomeGenBase.extremeHills, BiomeGenBase.forest, BiomeGenBase.forestHills};
    protected BiomeGenBase[] rareBiomes = new BiomeGenBase[]{BiomeGenBase.forestHills};

    public GenLayerAlderaanBiomes(long seed, GenLayer genlayer) {
        super(seed);
        this.parent = genlayer;
    }

    public GenLayerAlderaanBiomes(long seed) {
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


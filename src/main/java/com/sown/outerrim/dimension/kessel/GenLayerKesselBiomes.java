/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.biome.BiomeGenBase
 *  net.minecraft.world.gen.layer.GenLayer
 *  net.minecraft.world.gen.layer.IntCache
 */
package com.sown.outerrim.dimension.kessel;

import com.sown.outerrim.dimension.kessel.KesselBiomes;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;

public class GenLayerKesselBiomes
extends GenLayer {
    protected BiomeGenBase[] commonBiomes = new BiomeGenBase[]{KesselBiomes.kesselMountains, KesselBiomes.kesselRock2, KesselBiomes.kesselRock1};
    protected BiomeGenBase[] rareBiomes = new BiomeGenBase[]{KesselBiomes.kesselDirt1, KesselBiomes.kesselDirt2, KesselBiomes.kesselMud};

    public GenLayerKesselBiomes(long seed, GenLayer genlayer) {
        super(seed);
        this.parent = genlayer;
    }

    public GenLayerKesselBiomes(long seed) {
        super(seed);
    }

    public int[] getInts(int x, int z, int width, int depth) {
        int[] dest = IntCache.getIntCache((int)(width * depth));
        for (int dz = 0; dz < depth; ++dz) {
            for (int dx = 0; dx < width; ++dx) {
                this.initChunkSeed((long)(dx + x), (long)(dz + z));
                dest[dx + dz * width] = this.nextInt(150) == 0 ? this.rareBiomes[this.nextInt((int)this.rareBiomes.length)].biomeID : this.commonBiomes[this.nextInt((int)this.commonBiomes.length)].biomeID;
            }
        }
        return dest;
    }
}


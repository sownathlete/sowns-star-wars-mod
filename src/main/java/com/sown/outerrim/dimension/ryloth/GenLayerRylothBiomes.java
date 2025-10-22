package com.sown.outerrim.dimension.ryloth;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;

public class GenLayerRylothBiomes extends GenLayer {
    protected BiomeGenBase[] allBiomes = new BiomeGenBase[]{
        RylothBiomes.rylothPlateau, 
        RylothBiomes.ryloth, 
        RylothBiomes.rylothPlateau
    };

    public GenLayerRylothBiomes(long seed, GenLayer genlayer) {
        super(seed);
        this.parent = genlayer;
    }

    public GenLayerRylothBiomes(long seed) {
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
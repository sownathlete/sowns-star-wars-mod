package com.sown.outerrim.dimension.dathomir;

import com.sown.outerrim.OuterRimResources;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;

public class GenLayerDathomirBiomes extends GenLayer {

	protected BiomeGenBase[] commonBiomes = new BiomeGenBase[] {
		    // ~17.5% Forest
		    BiomeGenBase.getBiome(OuterRimResources.ConfigOptions.biomeDathomirForestId),
		    BiomeGenBase.getBiome(OuterRimResources.ConfigOptions.biomeDathomirForestId),

		    // ~17.5% Swamp
		    BiomeGenBase.getBiome(OuterRimResources.ConfigOptions.biomeDathomirSwampId),
		    BiomeGenBase.getBiome(OuterRimResources.ConfigOptions.biomeDathomirSwampId),

		    // ~65% Cliffs
		    BiomeGenBase.getBiome(OuterRimResources.ConfigOptions.biomeDathomirCliffsId),
		    BiomeGenBase.getBiome(OuterRimResources.ConfigOptions.biomeDathomirCliffsId),
		    BiomeGenBase.getBiome(OuterRimResources.ConfigOptions.biomeDathomirCliffsId),
		    BiomeGenBase.getBiome(OuterRimResources.ConfigOptions.biomeDathomirCliffsId),
		    BiomeGenBase.getBiome(OuterRimResources.ConfigOptions.biomeDathomirCliffsId),
		    BiomeGenBase.getBiome(OuterRimResources.ConfigOptions.biomeDathomirCliffsId)
		};

    public GenLayerDathomirBiomes(long seed) {
        super(seed);
    }

    @Override
    public int[] getInts(int x, int z, int width, int depth) {
        int[] dest = IntCache.getIntCache(width * depth);
        for (int dz = 0; dz < depth; ++dz) {
            for (int dx = 0; dx < width; ++dx) {
                this.initChunkSeed(dx + x, dz + z);
                BiomeGenBase chosen = commonBiomes[this.nextInt(commonBiomes.length)];
                dest[dx + dz * width] = chosen.biomeID;
            }
        }
        return dest;
    }
}

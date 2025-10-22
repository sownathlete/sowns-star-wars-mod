package com.sown.outerrim.dimension.yavin4;

import java.util.Random;

import com.sown.outerrim.dimension.BiomeChunkProviderGeneric;
import com.sown.outerrim.dimension.yavin4.BiomeGenYavin4;

import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.IChunkProvider;

/**
 * Custom chunk provider for Yavin 4, ensuring trees spawn during populate() to prevent leaf decay.
 */
public class BiomeChunkProviderYavin4 extends BiomeChunkProviderGeneric {
    private final World world;

    public BiomeChunkProviderYavin4(World world, long seed, boolean mapFeaturesEnabled) {
        super(world, seed, mapFeaturesEnabled);
        this.world = world;
    }

    /**
     * Populate is called after terrain and structures, before random ticks.
     * We override to inject tree spawning early so leaves won't decay.
     */
    @Override
    public void populate(IChunkProvider provider, int chunkX, int chunkZ) {
        // 1) Run standard population (ores, villages, structures, etc.)
        super.populate(provider, chunkX, chunkZ);

        // 2) Compute world coords for chunk origin
        int worldX = chunkX * 16;
        int worldZ = chunkZ * 16;

        // 3) Get the biome at center of chunk
        BiomeGenBase biome = world.getBiomeGenForCoords(worldX + 8, worldZ + 8);
        if (biome instanceof BiomeGenYavin4) {
            BiomeGenYavin4 y4 = (BiomeGenYavin4) biome;
            // Seed a fresh random so tree placement is consistent per-chunk
            Random treeRand = new Random(world.getSeed() + worldX + worldZ);
            // Spawn trees before any random tick decay
            y4.spawnTrees(world, treeRand, worldX, worldZ);
        }
    }
}

package com.sown.outerrim.dimension.abednedo;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeCache;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.WorldChunkManager;

import com.sown.util.world.creation.DimensionHelperChunkMgr;

public class WorldChunkManagerAbednedo extends DimensionHelperChunkMgr {
    private final BiomeCache biomeCache = new BiomeCache(this);
    private final List<BiomeGenBase> biomesToSpawn = new ArrayList<BiomeGenBase>();

    protected WorldChunkManagerAbednedo() {
        this.biomesToSpawn.add(AbednedoProvider.abednedo);
    }

    public WorldChunkManagerAbednedo(long seed) { this(); }
    public WorldChunkManagerAbednedo(World world) { this(world.getSeed()); }

    @Override
    public BiomeGenBase getBiome() { return AbednedoProvider.abednedo; }

    @Override
    public List getBiomesToSpawnIn() { return this.biomesToSpawn; }

    @Override
    public BiomeGenBase getBiomeGenAt(int x, int z) {
        BiomeGenBase b = this.biomeCache.getBiomeGenAt(x, z);
        return b == null ? AbednedoProvider.abednedo : b;
    }

    @Override
    public float getTemperatureAtHeight(float t, int y) { return t; }

    @Override
    public BiomeGenBase[] loadBlockGeneratorData(BiomeGenBase[] a, int x, int z, int w, int h) {
        return this.getBiomeGenAt(a, x, z, w, h, true);
    }

    @Override
    public void cleanupCache() { this.biomeCache.cleanupCache(); }
}

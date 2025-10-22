package com.sown.outerrim.dimension.utapau;

import java.util.ArrayList;
import java.util.List;

import com.sown.util.world.creation.DimensionHelperChunkMgr;

import net.minecraft.world.ChunkPosition;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeCache;
import net.minecraft.world.biome.BiomeGenBase;

public class WorldChunkManagerUtapau extends DimensionHelperChunkMgr {
    private BiomeCache biomeCache = new BiomeCache(this);
    private List<BiomeGenBase> biomesToSpawn = new ArrayList<BiomeGenBase>();

    protected WorldChunkManagerUtapau() {
        this.biomesToSpawn.add(UtapauProvider.utapau);
    }

    public WorldChunkManagerUtapau(long seed) {
        this();
    }

    public WorldChunkManagerUtapau(World world) {
        this(world.getSeed());
    }

    @Override
    public BiomeGenBase getBiome() {
        return UtapauProvider.utapau;
    }

    @Override
    public List getBiomesToSpawnIn() {
        return this.biomesToSpawn;
    }

    public boolean canSpawnLightningBolt() {
        return true;
    }

    public boolean getEnableSnow() {
        return false;
    }

    @Override
    public BiomeGenBase getBiomeGenAt(int x, int z) {
        BiomeGenBase biome = this.biomeCache.getBiomeGenAt(x, z);
        if (biome == null)
            return UtapauProvider.utapau;
        return biome;
    }

    @Override
    public float getTemperatureAtHeight(float par1, int par2) {
        return par1;
    }

    @Override
    public BiomeGenBase[] loadBlockGeneratorData(BiomeGenBase[] list, int x, int z, int width, int length) {
        return this.getBiomeGenAt(list, x, z, width, length, true);
    }

    @Override
    public void cleanupCache() {
        this.biomeCache.cleanupCache();
    }
}

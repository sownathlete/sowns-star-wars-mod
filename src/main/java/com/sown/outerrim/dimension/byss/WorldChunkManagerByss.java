package com.sown.outerrim.dimension.byss;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.sown.util.world.creation.DimensionHelperChunkMgr;

import net.minecraft.world.ChunkPosition;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeCache;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.WorldChunkManager;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;

public class WorldChunkManagerByss
extends DimensionHelperChunkMgr {
    private BiomeCache biomeCache = new BiomeCache(this);
    private List<BiomeGenBase> biomesToSpawn = new ArrayList<BiomeGenBase>();

    protected WorldChunkManagerByss() {
        this.biomesToSpawn.add(ByssProvider.byss);
    }

    public WorldChunkManagerByss(long seed) {
        this();
    }

    public WorldChunkManagerByss(World world) {
        this(world.getSeed());
    }

    @Override
    public BiomeGenBase getBiome() {
        return ByssProvider.byss;
    }

    @Override
    public List getBiomesToSpawnIn() {
        return this.biomesToSpawn;
    }
    
    public boolean canSpawnLightningBolt() {
        return true; // This makes it so that lightning can strike in the biome
    }

    public boolean getEnableSnow() {
        return false; // This ensures it rains instead of snowing
    }

    @Override
    public BiomeGenBase getBiomeGenAt(int par1, int par2) {
        BiomeGenBase biome = this.biomeCache.getBiomeGenAt(par1, par2);
        if (biome == null)
            return ByssProvider.byss;
        return biome;
    }

    @Override
    public float getTemperatureAtHeight(float par1, int par2) {
        return par1;
    }

    @Override
    public BiomeGenBase[] loadBlockGeneratorData(BiomeGenBase[] par1ArrayOfBiomeGenBase, int par2, int par3, int par4, int par5) {
        return this.getBiomeGenAt(par1ArrayOfBiomeGenBase, par2, par3, par4, par5, true);
    }

    @Override
    public void cleanupCache() {
        this.biomeCache.cleanupCache();
    }
}


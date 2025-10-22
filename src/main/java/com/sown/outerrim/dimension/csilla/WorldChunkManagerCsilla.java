/*
 * Decompiled with CFR 0.148.
 *
 * Could not load the following classes:
 *  micdoodle8.mods.galacticraft.api.prefab.world.gen.WorldChunkManagerSpace
 *  net.minecraft.world.ChunkPosition
 *  net.minecraft.world.World
 *  net.minecraft.world.biome.BiomeCache
 *  net.minecraft.world.biome.BiomeGenBase
 *  net.minecraft.world.biome.WorldChunkManager
 *  net.minecraft.world.gen.layer.GenLayer
 *  net.minecraft.world.gen.layer.IntCache
 */
package com.sown.outerrim.dimension.csilla;

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

public class WorldChunkManagerCsilla
extends DimensionHelperChunkMgr {
    private BiomeCache biomeCache = new BiomeCache(this);
    private List<BiomeGenBase> biomesToSpawn = new ArrayList<BiomeGenBase>();

    protected WorldChunkManagerCsilla() {
        this.biomesToSpawn.add(CsillaProvider.csilla);
    }

    public WorldChunkManagerCsilla(long seed) {
        this();
    }

    public WorldChunkManagerCsilla(World world) {
        this(world.getSeed());
    }

    @Override
    public BiomeGenBase getBiome() {
        return CsillaProvider.csilla;
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
            return CsillaProvider.csilla;
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


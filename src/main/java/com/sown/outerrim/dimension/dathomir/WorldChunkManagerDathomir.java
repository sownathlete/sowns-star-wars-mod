package com.sown.outerrim.dimension.dathomir;

import com.sown.util.world.creation.DimensionHelperChunkMgr;
import com.sown.outerrim.OuterRimResources;

import net.minecraft.world.ChunkPosition;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeCache;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.WorldChunkManager;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class WorldChunkManagerDathomir extends DimensionHelperChunkMgr {
    private GenLayer unzoomedBiomes;
    private GenLayer zoomedBiomes;
    private BiomeCache biomeCache = new BiomeCache(this);
    private List<BiomeGenBase> biomesToSpawn = new ArrayList<>();

    protected WorldChunkManagerDathomir() {
        biomesToSpawn.add(BiomeGenBase.getBiome(OuterRimResources.ConfigOptions.biomeDathomirForestId));
        biomesToSpawn.add(BiomeGenBase.getBiome(OuterRimResources.ConfigOptions.biomeDathomirSwampId));
        biomesToSpawn.add(BiomeGenBase.getBiome(OuterRimResources.ConfigOptions.biomeDathomirCliffsId));
    }

    public WorldChunkManagerDathomir(long seed) {
        this();
        GenLayer[] layers = GenLayerBiomeDathomir.makeTheWorld(seed);
        this.unzoomedBiomes = layers[0];
        this.zoomedBiomes = layers[1];
    }

    public WorldChunkManagerDathomir(World world) {
        this(world.getSeed());
    }

    @Override
    public BiomeGenBase getBiome() {
        return BiomeGenBase.getBiome(OuterRimResources.ConfigOptions.biomeDathomirForestId);
    }

    @Override
    public List getBiomesToSpawnIn() {
        return this.biomesToSpawn;
    }

    @Override
    public BiomeGenBase getBiomeGenAt(int x, int z) {
        BiomeGenBase biome = this.biomeCache.getBiomeGenAt(x, z);
        if (biome == null)
            return BiomeGenBase.getBiome(OuterRimResources.ConfigOptions.biomeDathomirForestId);
        return biome;
    }

    @Override
    public float[] getRainfall(float[] list, int x, int z, int width, int depth) {
        IntCache.resetIntCache();
        int[] ids = this.zoomedBiomes.getInts(x, z, width, depth);
        if (list == null || list.length < width * depth)
            list = new float[width * depth];

        for (int i = 0; i < width * depth; ++i) {
            float rain = BiomeGenBase.getBiome(ids[i]).getIntRainfall() / 65536.0F;
            list[i] = Math.min(1.0F, rain);
        }

        return list;
    }

    @Override
    public float getTemperatureAtHeight(float temp, int y) {
        return temp;
    }

    @Override
    public BiomeGenBase[] getBiomesForGeneration(BiomeGenBase[] out, int x, int z, int width, int length) {
        int[] ints = this.unzoomedBiomes.getInts(x, z, width, length);
        if (out == null || out.length < width * length)
            out = new BiomeGenBase[width * length];

        for (int i = 0; i < width * length; ++i)
            out[i] = BiomeGenBase.getBiome(ints[i]);

        return out;
    }

    @Override
    public BiomeGenBase[] loadBlockGeneratorData(BiomeGenBase[] out, int x, int z, int width, int length) {
        return this.getBiomeGenAt(out, x, z, width, length, true);
    }

    @Override
    public BiomeGenBase[] getBiomeGenAt(BiomeGenBase[] out, int x, int z, int width, int length, boolean useCache) {
        int[] ints = this.zoomedBiomes.getInts(x, z, width, length);

        if (out == null || out.length < width * length)
            out = new BiomeGenBase[width * length];

        if (useCache && width == 16 && length == 16 && (x & 0xF) == 0 && (z & 0xF) == 0) {
            BiomeGenBase[] cached = this.biomeCache.getCachedBiomes(x, z);
            System.arraycopy(cached, 0, out, 0, width * length);
            return out;
        }

        for (int i = 0; i < width * length; ++i) {
            out[i] = BiomeGenBase.getBiome(ints[i]);
        }

        return out;
    }

    @Override
    public boolean areBiomesViable(int x, int z, int radius, List biomes) {
        int i = (x - radius) >> 2;
        int j = (z - radius) >> 2;
        int k = (x + radius) >> 2;
        int l = (z + radius) >> 2;
        int w = k - i + 1;
        int h = l - j + 1;
        int[] ids = this.unzoomedBiomes.getInts(i, j, w, h);

        for (int id : ids) {
            BiomeGenBase biome = BiomeGenBase.getBiome(id);
            if (!biomes.contains(biome)) return false;
        }

        return true;
    }

    @Override
    public ChunkPosition findBiomePosition(int x, int z, int radius, List allowed, Random rand) {
        int i = (x - radius) >> 2;
        int j = (z - radius) >> 2;
        int k = (x + radius) >> 2;
        int l = (z + radius) >> 2;
        int w = k - i + 1;
        int h = l - j + 1;
        int[] ids = this.unzoomedBiomes.getInts(i, j, w, h);
        ChunkPosition result = null;
        int attempts = 0;

        for (int index = 0; index < ids.length; index++) {
            int bx = i + index % w << 2;
            int bz = j + index / w << 2;
            BiomeGenBase biome = BiomeGenBase.getBiome(ids[index]);

            if (allowed.contains(biome) && (result == null || rand.nextInt(++attempts) == 0)) {
                result = new ChunkPosition(bx, 0, bz);
            }
        }

        return result;
    }

    @Override
    public void cleanupCache() {
        this.biomeCache.cleanupCache();
    }
}

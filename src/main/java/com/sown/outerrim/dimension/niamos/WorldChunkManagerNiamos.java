/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.ChunkPosition
 *  net.minecraft.world.World
 *  net.minecraft.world.biome.BiomeCache
 *  net.minecraft.world.biome.BiomeGenBase
 *  net.minecraft.world.biome.WorldChunkManager
 *  net.minecraft.world.gen.layer.GenLayer
 *  net.minecraft.world.gen.layer.IntCache
 */
package com.sown.outerrim.dimension.niamos;

import com.sown.outerrim.dimension.tatooine.TatooineBiomes;
import com.sown.util.world.creation.DimensionHelperChunkMgr;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import net.minecraft.world.ChunkPosition;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeCache;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.WorldChunkManager;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;

public class WorldChunkManagerNiamos
extends DimensionHelperChunkMgr {
    private GenLayer unzoomedBiomes;
    private GenLayer zoomedBiomes;
    private BiomeCache biomeCache = new BiomeCache((WorldChunkManager)this);
    private List<BiomeGenBase> biomesToSpawn = new ArrayList<BiomeGenBase>();

    protected WorldChunkManagerNiamos() {
        this.biomesToSpawn.add(NiamosProvider.niamos);
    }

    public WorldChunkManagerNiamos(long seed) {
        this();
        GenLayer[] agenlayer = GenLayerBiomeNiamos.makeTheWorld(seed);
        this.unzoomedBiomes = agenlayer[0];
        this.zoomedBiomes = agenlayer[1];
    }

    public WorldChunkManagerNiamos(World world) {
        this(world.getSeed());
    }

    @Override
    public BiomeGenBase getBiome() {
        return NiamosProvider.niamos;
    }

    @Override
    public List getBiomesToSpawnIn() {
        return this.biomesToSpawn;
    }

    @Override
    public BiomeGenBase getBiomeGenAt(int par1, int par2) {
        BiomeGenBase biome = this.biomeCache.getBiomeGenAt(par1, par2);
        if (biome == null)
            return NiamosProvider.niamos;
        return biome;
    }

    @Override
    public float[] getRainfall(float[] par1, int x, int z, int width, int depth) {
        IntCache.resetIntCache();
        int[] aint = this.zoomedBiomes.getInts(x, z, width, depth);
        if (par1 == null || par1.length < width * depth) {
            par1 = new float[width * depth];
        }
        for (int i1 = 0; i1 < width * depth; ++i1) {
            float f = BiomeGenBase.getBiome(aint[i1]).getIntRainfall() / 65536.0f;
            if (f > 1.0f) {
                f = 1.0f;
            }
            par1[i1] = f;
        }
        return par1;
    }

    @Override
    public float getTemperatureAtHeight(float par1, int par2) {
        return par1;
    }

    @Override
    public BiomeGenBase[] getBiomesForGeneration(BiomeGenBase[] par1ArrayOfBiomeGenBase, int x, int z, int length, int width) {
        int[] arrayOfInts = this.unzoomedBiomes.getInts(x, z, length, width);
        if (par1ArrayOfBiomeGenBase == null || par1ArrayOfBiomeGenBase.length < length * width) {
            par1ArrayOfBiomeGenBase = new BiomeGenBase[length * width];
        }
        for (int i = 0; i < length * width; ++i) {
            par1ArrayOfBiomeGenBase[i] = arrayOfInts[i] >= 0 ? BiomeGenBase.getBiome(arrayOfInts[i]) : NiamosProvider.niamos;
        }
        return par1ArrayOfBiomeGenBase;
    }

    @Override
    public BiomeGenBase[] loadBlockGeneratorData(BiomeGenBase[] par1ArrayOfBiomeGenBase, int par2, int par3, int par4, int par5) {
        return this.getBiomeGenAt(par1ArrayOfBiomeGenBase, par2, par3, par4, par5, true);
    }

    @Override
    public BiomeGenBase[] getBiomeGenAt(BiomeGenBase[] par1ArrayOfBiomeGenBase, int x, int y, int width, int length, boolean cacheFlag) {
        int[] ai = this.zoomedBiomes.getInts(x, y, width, length);
        if (par1ArrayOfBiomeGenBase == null || par1ArrayOfBiomeGenBase.length < width * length) {
            par1ArrayOfBiomeGenBase = new BiomeGenBase[width * length];
        }
        if (cacheFlag && width == 16 && length == 16 && (x & 0xF) == 0 && (y & 0xF) == 0) {
            BiomeGenBase[] abiomegenbase = this.biomeCache.getCachedBiomes(x, y);
            System.arraycopy(abiomegenbase, 0, par1ArrayOfBiomeGenBase, 0, width * length);
            return par1ArrayOfBiomeGenBase;
        }
        for (int i = 0; i < width * length; ++i) {
            par1ArrayOfBiomeGenBase[i] = ai[i] >= 0 ? BiomeGenBase.getBiome(ai[i]) : NiamosProvider.niamos;
        }
        return par1ArrayOfBiomeGenBase;
    }

    @Override
    public boolean areBiomesViable(int par1, int par2, int par3, List par4List) {
        int i = par1 - par3 >> 2;
        int j = par2 - par3 >> 2;
        int k = par1 + par3 >> 2;
        int l = par2 + par3 >> 2;
        int i1 = k - i + 1;
        int j1 = l - j + 1;
        int[] ai = this.unzoomedBiomes.getInts(i, j, i1, j1);
        for (int k1 = 0; k1 < i1 * j1; ++k1) {
            BiomeGenBase biomegenbase = BiomeGenBase.getBiome(ai[k1]);
            if (par4List.contains(biomegenbase)) {
                continue;
            }
            return false;
        }
        return true;
    }

    @Override
    public ChunkPosition findBiomePosition(int par1, int par2, int par3, List par4List, Random par5Random) {
        int i = par1 - par3 >> 2;
        int j = par2 - par3 >> 2;
        int k = par1 + par3 >> 2;
        int l = par2 + par3 >> 2;
        int i1 = k - i + 1;
        int j1 = l - j + 1;
        int[] ai = this.unzoomedBiomes.getInts(i, j, i1, j1);
        ChunkPosition chunkposition = null;
        int k1 = 0;
        for (int l1 = 0; l1 < ai.length; ++l1) {
            int i2 = i + l1 % i1 << 2;
            int j2 = j + l1 / i1 << 2;
            BiomeGenBase biomegenbase = BiomeGenBase.getBiome(ai[l1]);
            if (!par4List.contains(biomegenbase) || chunkposition != null && par5Random.nextInt(k1 + 1) != 0) {
                continue;
            }
            chunkposition = new ChunkPosition(i2, 0, j2);
            ++k1;
        }
        return chunkposition;
    }

    @Override
    public void cleanupCache() {
        this.biomeCache.cleanupCache();
    }
}
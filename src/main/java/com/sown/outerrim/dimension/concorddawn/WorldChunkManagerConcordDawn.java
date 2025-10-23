package com.sown.outerrim.dimension.concorddawn;

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

public class WorldChunkManagerConcordDawn extends DimensionHelperChunkMgr {
	private GenLayer unzoomedBiomes;
	private GenLayer zoomedBiomes;
	private BiomeCache biomeCache = new BiomeCache(this);
	private List<BiomeGenBase> biomesToSpawn = new ArrayList<>();

	protected WorldChunkManagerConcordDawn() {
		this.biomesToSpawn.add(BiomeGenBase.forest);
		this.biomesToSpawn.add(BiomeGenBase.extremeHills);
		this.biomesToSpawn.add(BiomeGenBase.plains);
		this.biomesToSpawn.add(BiomeGenBase.taiga);
		this.biomesToSpawn.add(BiomeGenBase.coldTaiga);
	}

	public WorldChunkManagerConcordDawn(long seed) {
		this();
		GenLayer[] agenlayer = GenLayerBiomeConcordDawn.makeTheWorld(seed);
		this.unzoomedBiomes = agenlayer[0];
		this.zoomedBiomes = agenlayer[1];
	}

	public WorldChunkManagerConcordDawn(World world) {
		this(world.getSeed());
	}

	@Override
	public BiomeGenBase getBiome() {
		return BiomeGenBase.forest;
	}

	@Override
	public List getBiomesToSpawnIn() {
		return this.biomesToSpawn;
	}

	@Override
	public BiomeGenBase getBiomeGenAt(int par1, int par2) {
		BiomeGenBase biome = this.biomeCache.getBiomeGenAt(par1, par2);
		if (biome == null)
			return BiomeGenBase.forest;
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
			if (f > 1.0f)
				f = 1.0f;
			par1[i1] = f;
		}
		return par1;
	}

	@Override
	public BiomeGenBase[] getBiomesForGeneration(BiomeGenBase[] par1Array, int x, int z, int length, int width) {
		int[] ints = this.unzoomedBiomes.getInts(x, z, length, width);
		if (par1Array == null || par1Array.length < length * width) {
			par1Array = new BiomeGenBase[length * width];
		}
		for (int i = 0; i < length * width; ++i) {
			par1Array[i] = BiomeGenBase.getBiome(ints[i]);
			if (par1Array[i] == null)
				par1Array[i] = BiomeGenBase.forest;
		}
		return par1Array;
	}

	@Override
	public BiomeGenBase[] loadBlockGeneratorData(BiomeGenBase[] array, int x, int z, int width, int depth) {
		return this.getBiomeGenAt(array, x, z, width, depth, true);
	}

	@Override
	public BiomeGenBase[] getBiomeGenAt(BiomeGenBase[] array, int x, int y, int width, int length, boolean cacheFlag) {
		int[] ai = this.zoomedBiomes.getInts(x, y, width, length);
		if (array == null || array.length < width * length) {
			array = new BiomeGenBase[width * length];
		}
		if (cacheFlag && width == 16 && length == 16 && (x & 0xF) == 0 && (y & 0xF) == 0) {
			BiomeGenBase[] cached = this.biomeCache.getCachedBiomes(x, y);
			System.arraycopy(cached, 0, array, 0, width * length);
			return array;
		}
		for (int i = 0; i < width * length; ++i) {
			array[i] = BiomeGenBase.getBiome(ai[i]);
			if (array[i] == null)
				array[i] = BiomeGenBase.forest;
		}
		return array;
	}

	@Override
	public void cleanupCache() {
		this.biomeCache.cleanupCache();
	}
}

package com.sown.outerrim.dimension.concordia;

import com.sown.util.world.creation.DimensionHelperChunkMgr;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.world.World;
import net.minecraft.world.biome.*;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;

public class WorldChunkManagerConcordia extends DimensionHelperChunkMgr {
	private GenLayer unzoomedBiomes;
	private GenLayer zoomedBiomes;
	private BiomeCache biomeCache = new BiomeCache(this);
	private List<BiomeGenBase> biomesToSpawn = new ArrayList<>();

	protected WorldChunkManagerConcordia() {
		biomesToSpawn.add(BiomeGenBase.forest);
		biomesToSpawn.add(BiomeGenBase.plains);
		biomesToSpawn.add(BiomeGenBase.extremeHills);
		biomesToSpawn.add(BiomeGenBase.taiga);
		biomesToSpawn.add(BiomeGenBase.coldTaiga);
	}

	public WorldChunkManagerConcordia(long seed) {
		this();
		GenLayer[] agenlayer = GenLayerBiomeConcordia.makeTheWorld(seed);
		unzoomedBiomes = agenlayer[0];
		zoomedBiomes = agenlayer[1];
	}

	public WorldChunkManagerConcordia(World world) {
		this(world.getSeed());
	}

	@Override
	public BiomeGenBase getBiome() {
		return BiomeGenBase.forest;
	}

	@Override
	public List getBiomesToSpawnIn() {
		return biomesToSpawn;
	}

	@Override
	public BiomeGenBase getBiomeGenAt(int par1, int par2) {
		BiomeGenBase biome = biomeCache.getBiomeGenAt(par1, par2);
		if (biome == null)
			return BiomeGenBase.forest;
		return biome;
	}

	@Override
	public float[] getRainfall(float[] par1, int x, int z, int width, int depth) {
		IntCache.resetIntCache();
		int[] aint = zoomedBiomes.getInts(x, z, width, depth);
		if (par1 == null || par1.length < width * depth)
			par1 = new float[width * depth];
		for (int i = 0; i < width * depth; ++i) {
			float f = BiomeGenBase.getBiome(aint[i]).getIntRainfall() / 65536.0f;
			if (f > 1.0f)
				f = 1.0f;
			par1[i] = f;
		}
		return par1;
	}

	@Override
	public BiomeGenBase[] getBiomesForGeneration(BiomeGenBase[] par1Array, int x, int z, int length, int width) {
		int[] ints = unzoomedBiomes.getInts(x, z, length, width);
		if (par1Array == null || par1Array.length < length * width)
			par1Array = new BiomeGenBase[length * width];
		for (int i = 0; i < length * width; ++i) {
			par1Array[i] = BiomeGenBase.getBiome(ints[i]);
			if (par1Array[i] == null)
				par1Array[i] = BiomeGenBase.forest;
		}
		return par1Array;
	}

	@Override
	public BiomeGenBase[] loadBlockGeneratorData(BiomeGenBase[] array, int x, int z, int width, int depth) {
		return getBiomeGenAt(array, x, z, width, depth, true);
	}

	@Override
	public BiomeGenBase[] getBiomeGenAt(BiomeGenBase[] array, int x, int y, int width, int length, boolean cacheFlag) {
		int[] ai = zoomedBiomes.getInts(x, y, width, length);
		if (array == null || array.length < width * length)
			array = new BiomeGenBase[width * length];
		if (cacheFlag && width == 16 && length == 16 && (x & 0xF) == 0 && (y & 0xF) == 0) {
			BiomeGenBase[] cached = biomeCache.getCachedBiomes(x, y);
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
		biomeCache.cleanupCache();
	}
}

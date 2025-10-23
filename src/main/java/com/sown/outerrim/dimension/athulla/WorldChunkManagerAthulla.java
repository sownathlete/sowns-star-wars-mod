package com.sown.outerrim.dimension.athulla;

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

public class WorldChunkManagerAthulla extends DimensionHelperChunkMgr {
	private GenLayer unzoomedBiomes;
	private GenLayer zoomedBiomes;
	private BiomeCache biomeCache = new BiomeCache((WorldChunkManager) this);
	private List<BiomeGenBase> biomesToSpawn = new ArrayList<>();

	protected WorldChunkManagerAthulla() {
		biomesToSpawn.add(BiomeGenBase.jungle);
		biomesToSpawn.add(BiomeGenBase.forest);
		biomesToSpawn.add(BiomeGenBase.swampland);
		biomesToSpawn.add(BiomeGenBase.plains);
	}

	public WorldChunkManagerAthulla(long seed) {
		this();
		GenLayer[] agenlayer = GenLayerBiomeAthulla.makeTheWorld(seed);
		this.unzoomedBiomes = agenlayer[0];
		this.zoomedBiomes = agenlayer[1];
	}

	public WorldChunkManagerAthulla(World world) {
		this(world.getSeed());
	}

	@Override
	public BiomeGenBase getBiome() {
		return BiomeGenBase.jungle;
	}

	@Override
	public List getBiomesToSpawnIn() {
		return this.biomesToSpawn;
	}

	@Override
	public BiomeGenBase getBiomeGenAt(int x, int z) {
		BiomeGenBase biome = this.biomeCache.getBiomeGenAt(x, z);
		return biome != null ? biome : BiomeGenBase.jungle;
	}

	@Override
	public float[] getRainfall(float[] arr, int x, int z, int w, int h) {
		IntCache.resetIntCache();
		int[] ids = this.zoomedBiomes.getInts(x, z, w, h);
		if (arr == null || arr.length < w * h)
			arr = new float[w * h];
		for (int i = 0; i < w * h; ++i) {
			float f = BiomeGenBase.getBiome(ids[i]).getIntRainfall() / 65536.0f;
			if (f > 1.0f)
				f = 1.0f;
			arr[i] = f;
		}
		return arr;
	}

	@Override
	public float getTemperatureAtHeight(float base, int height) {
		return base;
	}

	@Override
	public BiomeGenBase[] getBiomesForGeneration(BiomeGenBase[] arr, int x, int z, int w, int h) {
		int[] ids = this.unzoomedBiomes.getInts(x, z, w, h);
		if (arr == null || arr.length < w * h)
			arr = new BiomeGenBase[w * h];
		for (int i = 0; i < w * h; ++i)
			arr[i] = ids[i] >= 0 ? BiomeGenBase.getBiome(ids[i]) : BiomeGenBase.jungle;
		return arr;
	}

	@Override
	public BiomeGenBase[] getBiomeGenAt(BiomeGenBase[] arr, int x, int z, int w, int h, boolean cache) {
		int[] ids = this.zoomedBiomes.getInts(x, z, w, h);
		if (arr == null || arr.length < w * h)
			arr = new BiomeGenBase[w * h];
		for (int i = 0; i < w * h; ++i)
			arr[i] = ids[i] >= 0 ? BiomeGenBase.getBiome(ids[i]) : BiomeGenBase.jungle;
		return arr;
	}

	@Override
	public boolean areBiomesViable(int x, int z, int radius, List list) {
		int i = x - radius >> 2;
		int j = z - radius >> 2;
		int k = x + radius >> 2;
		int l = z + radius >> 2;
		int w = k - i + 1;
		int h = l - j + 1;
		int[] ids = this.unzoomedBiomes.getInts(i, j, w, h);
		for (int n = 0; n < w * h; ++n)
			if (!list.contains(BiomeGenBase.getBiome(ids[n])))
				return false;
		return true;
	}

	@Override
	public ChunkPosition findBiomePosition(int x, int z, int radius, List list, Random rand) {
		int i = x - radius >> 2;
		int j = z - radius >> 2;
		int k = x + radius >> 2;
		int l = z + radius >> 2;
		int w = k - i + 1;
		int h = l - j + 1;
		int[] ids = this.unzoomedBiomes.getInts(i, j, w, h);
		ChunkPosition pos = null;
		int count = 0;
		for (int n = 0; n < ids.length; ++n) {
			int bx = i + n % w << 2;
			int bz = j + n / w << 2;
			BiomeGenBase biome = BiomeGenBase.getBiome(ids[n]);
			if (!list.contains(biome) || (pos != null && rand.nextInt(count + 1) != 0))
				continue;
			pos = new ChunkPosition(bx, 0, bz);
			++count;
		}
		return pos;
	}

	@Override
	public void cleanupCache() {
		this.biomeCache.cleanupCache();
	}
}

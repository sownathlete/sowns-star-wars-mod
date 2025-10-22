package com.sown.outerrim.dimension.wbw;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.IProgressUpdate;
import net.minecraft.world.ChunkPosition;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.ChunkProviderGenerate;
import net.minecraft.entity.EnumCreatureType;

public class BiomeChunkProviderWBW extends ChunkProviderGenerate {
    private final Random rand;
    private final World worldObj;

    public BiomeChunkProviderWBW(World world, long seed, boolean features) {
        super(world, seed, features);
        this.worldObj = world;
        this.rand     = new Random(seed);
    }

    @Override
    public boolean canSave() {
        return true;
    }

    @Override
    public boolean chunkExists(int x, int z) {
        return true;
    }

    @Override
    public Chunk loadChunk(int chunkX, int chunkZ) {
        return this.provideChunk(chunkX, chunkZ);
    }

    @Override
    public void func_147424_a(int x, int z, Block[] blocks) {
        // fill entire chunk with air
        Arrays.fill(blocks, Blocks.air);
    }

    @Override
    @SuppressWarnings("rawtypes")
    public List getPossibleCreatures(EnumCreatureType type, int x, int y, int z) {
        BiomeGenBase biome = this.worldObj.getBiomeGenForCoords(x, z);
        return biome.getSpawnableList(type);
    }

    @Override
    public Chunk provideChunk(int chunkX, int chunkZ) {
        // Re-seed for consistent randomness
        this.rand.setSeed((long)chunkX * 341873128712L + (long)chunkZ * 132897987541L);

        // Determine world height (usually 256)
        int height = worldObj.provider.getHeight();
        // Prepare block and metadata arrays
        Block[]   blockArray = new Block[16 * 16 * height];
        byte[]    metaArray  = new byte[blockArray.length];

        // Fill with air
        this.func_147424_a(chunkX, chunkZ, blockArray);

        // Create the chunk
        Chunk chunk = new Chunk(this.worldObj, blockArray, metaArray, chunkX, chunkZ);

        // Assign custom biome ID to every column in the chunk
        byte[] biomeArray = chunk.getBiomeArray();
        byte   wbwId      = (byte) WBWProvider.worldBetweenWorlds.biomeID;
        Arrays.fill(biomeArray, wbwId);

        // Build light map
        chunk.generateSkylightMap();
        return chunk;
    }

    @Override
    public void populate(IChunkProvider provider, int chunkX, int chunkZ) {
        // Allow default decoration (which will call your BiomeGenWBW.decorate)
        super.populate(provider, chunkX, chunkZ);
    }

    @Override
    public ChunkPosition func_147416_a(World world, String structureName, int x, int y, int z) {
        return null;
    }

    @Override
    public void recreateStructures(int x, int z) {
        // none
    }

    @Override
    public boolean saveChunks(boolean flag, IProgressUpdate progress) {
        return true;
    }

    @Override
    public void saveExtraData() { /* none */ }

    @Override
    public boolean unloadQueuedChunks() {
        return false;
    }

    @Override
    public String makeString() {
        return "WBWChunkProvider";
    }
}

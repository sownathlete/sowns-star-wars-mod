package com.sown.outerrim.dimension.savareen;

import cpw.mods.fml.common.eventhandler.Event;
import cpw.mods.fml.common.eventhandler.EventBus;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFalling;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.passive.EntitySquid;
import net.minecraft.init.Blocks;
import net.minecraft.util.IProgressUpdate;
import net.minecraft.util.MathHelper;
import net.minecraft.world.ChunkPosition;
import net.minecraft.world.SpawnerAnimals;
import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.WorldChunkManager;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.ChunkProviderGenerate;
import net.minecraft.world.gen.MapGenBase;
import net.minecraft.world.gen.MapGenCaves;
import net.minecraft.world.gen.MapGenRavine;
import net.minecraft.world.gen.NoiseGenerator;
import net.minecraft.world.gen.NoiseGeneratorOctaves;
import net.minecraft.world.gen.NoiseGeneratorPerlin;
import net.minecraft.world.gen.structure.MapGenMineshaft;
import net.minecraft.world.gen.structure.MapGenScatteredFeature;
import net.minecraft.world.gen.structure.MapGenStronghold;
import net.minecraft.world.gen.structure.MapGenVillage;
import net.minecraft.world.storage.WorldInfo;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.terraingen.ChunkProviderEvent;
import net.minecraftforge.event.terraingen.PopulateChunkEvent;
import net.minecraftforge.event.terraingen.TerrainGen;

public class BiomeChunkProviderSavareen
extends ChunkProviderGenerate {
    private Random rand;
    private NoiseGeneratorOctaves noiseGen1;
    private NoiseGeneratorOctaves nouseGen2;
    private NoiseGeneratorOctaves noiseGen3;
    private NoiseGeneratorPerlin noiseGen4;
    public NoiseGeneratorOctaves noiseGen5;
    public NoiseGeneratorOctaves noiseGen6;
    public NoiseGeneratorOctaves mobSpawnerNoise;
    private World worldObj;
    private final boolean mapFeaturesEnabled;
    private WorldType terrainType;
    private final double[] field_147434_q;
    private final float[] parabolicField;
    private double[] stoneNoise = new double[256];
    private MapGenBase caveGenerator = new MapGenCaves();
    private BiomeGenBase[] biomesForGeneration;
    double[] field_147427_d;
    double[] field_147428_e;
    double[] field_147425_f;
    double[] field_147426_g;
    int[][] field_73219_j = new int[32][32];

    public BiomeChunkProviderSavareen(World world, long seed, boolean features) {
        super(world, seed, features);
        this.worldObj = world;
        this.mapFeaturesEnabled = features;
        this.terrainType = world.getWorldInfo().getTerrainType();
        this.rand = new Random(seed);
        this.noiseGen1 = new NoiseGeneratorOctaves(this.rand, 16);
        this.nouseGen2 = new NoiseGeneratorOctaves(this.rand, 16);
        this.noiseGen3 = new NoiseGeneratorOctaves(this.rand, 8);
        this.noiseGen4 = new NoiseGeneratorPerlin(this.rand, 4);
        this.noiseGen5 = new NoiseGeneratorOctaves(this.rand, 10);
        this.noiseGen6 = new NoiseGeneratorOctaves(this.rand, 16);
        this.mobSpawnerNoise = new NoiseGeneratorOctaves(this.rand, 8);
        this.field_147434_q = new double[825];
        this.parabolicField = new float[25];
        for (int j = -2; j <= 2; ++j) {
            for (int k = -2; k <= 2; ++k) {
                float f;
                this.parabolicField[j + 2 + (k + 2) * 5] = f = 10.0f / MathHelper.sqrt_float((float)((float)(j * j + k * k) + 0.2f));
            }
        }
        NoiseGenerator[] noiseGens = new NoiseGenerator[]{this.noiseGen1, this.nouseGen2, this.noiseGen3, this.noiseGen4, this.noiseGen5, this.noiseGen6, this.mobSpawnerNoise};
        noiseGens = TerrainGen.getModdedNoiseGenerators((World)world, (Random)this.rand, (NoiseGenerator[])noiseGens);
        this.noiseGen1 = (NoiseGeneratorOctaves)noiseGens[0];
        this.nouseGen2 = (NoiseGeneratorOctaves)noiseGens[1];
        this.noiseGen3 = (NoiseGeneratorOctaves)noiseGens[2];
        this.noiseGen4 = (NoiseGeneratorPerlin)noiseGens[3];
        this.noiseGen5 = (NoiseGeneratorOctaves)noiseGens[4];
        this.noiseGen6 = (NoiseGeneratorOctaves)noiseGens[5];
        this.mobSpawnerNoise = (NoiseGeneratorOctaves)noiseGens[6];
    }

    public boolean canSave() {
        return true;
    }

    public boolean chunkExists(int x, int z) {
        return true;
    }

    private void func_147423_a(int par1, int par2, int par3) {
        this.field_147426_g = this.noiseGen6.generateNoiseOctaves(this.field_147426_g, par1, par3, 5, 5, 200.0, 200.0, 0.5);
        this.field_147427_d = this.noiseGen3.generateNoiseOctaves(this.field_147427_d, par1, par2, par3, 5, 33, 5, 8.555150000000001, 4.277575000000001, 8.555150000000001);
        this.field_147428_e = this.noiseGen1.generateNoiseOctaves(this.field_147428_e, par1, par2, par3, 5, 33, 5, 684.412, 684.412, 684.412);
        this.field_147425_f = this.nouseGen2.generateNoiseOctaves(this.field_147425_f, par1, par2, par3, 5, 33, 5, 684.412, 684.412, 684.412);
        int l = 0;
        int i1 = 0;
        for (int j1 = 0; j1 < 5; ++j1) {
            for (int k1 = 0; k1 < 5; ++k1) {
                double d = 0;
                float f = 0.0f;
                float f1 = 0.0f;
                float f2 = 0.0f;
                int b0 = 2;
                BiomeGenBase biomegenbase = this.biomesForGeneration[j1 + 2 + (k1 + 2) * 10];
                for (int l1 = -b0; l1 <= b0; ++l1) {
                    for (int i2 = -b0; i2 <= b0; ++i2) {
                        BiomeGenBase biomegenbase1 = this.biomesForGeneration[j1 + l1 + 2 + (k1 + i2 + 2) * 10];
                        float f3 = biomegenbase1.rootHeight;
                        float f4 = biomegenbase1.heightVariation;
                        if (this.terrainType == WorldType.DEFAULT && f3 > 0.0f) {
                            f3 = 1.0f + f3 * 2.0f;
                            f4 = 1.0f + f4 * 4.0f;
                        }
                        float f5 = this.parabolicField[l1 + 2 + (i2 + 2) * 5] / (f3 + 2.0f);
                        if (biomegenbase1.rootHeight > biomegenbase.rootHeight) {
                            f5 /= 2.0f;
                        }
                        f += f4 * f5;
                        f1 += f3 * f5;
                        f2 += f5;
                    }
                }
                f /= f2;
                f1 /= f2;
                f = f * 0.9f + 0.1f;
                f1 = (f1 * 4.0f - 1.0f) / 8.0f;
                double d12 = this.field_147426_g[i1] / 8000.0;
                if (d12 < 0.0) {
                    d12 = -d12 * 0.3;
                }
                d12 = d12 * 3.0 - 2.0;
                if (d < 0.0) {
                    double d2 = 0;
                    d12 /= 2.0;
                    if (d2 < -1.0) {
                        d12 = -1.0;
                    }
                    d12 /= 1.4;
                    d12 /= 2.0;
                } else {
                    if (d12 > 1.0) {
                        d12 = 1.0;
                    }
                    d12 /= 8.0;
                }
                ++i1;
                double d13 = f1;
                double d14 = f;
                d13 += d12 * 0.2;
                d13 = d13 * 8.5 / 8.0;
                double d5 = 8.5 + d13 * 4.0;
                for (int j2 = 0; j2 < 33; ++j2) {
                    double d6 = ((double)j2 - d5) * 12.0 * 128.0 / 256.0 / d14;
                    if (d6 < 0.0) {
                        d6 *= 4.0;
                    }
                    double d7 = this.field_147428_e[l] / 512.0;
                    double d8 = this.field_147425_f[l] / 512.0;
                    double d9 = (this.field_147427_d[l] / 10.0 + 1.0) / 2.0;
                    double d10 = MathHelper.denormalizeClamp((double)d7, (double)d8, (double)d9) - d6;
                    if (j2 > 29) {
                        double d11 = (float)(j2 - 29) / 3.0f;
                        d10 = d10 * (1.0 - d11) + -10.0 * d11;
                    }
                    this.field_147434_q[l] = d10;
                    ++l;
                }
            }
        }
    }

    public int getLoadedChunkCount() {
        return 0;
    }

    @Override
    public List<BiomeGenBase.SpawnListEntry> getPossibleCreatures(EnumCreatureType creatureType, int x, int y, int z) {
        BiomeGenBase biome = this.worldObj.getBiomeGenForCoords(x, z);

        // Prevent vanilla hostile mobs from spawning
        if (creatureType == EnumCreatureType.monster) {
            return java.util.Collections.emptyList(); // No hostile mobs
        }

        // Allow other default mob spawning (passive mobs, etc.)
        return super.getPossibleCreatures(creatureType, x, y, z);
    }

    public Chunk loadChunk(int chunkX, int chunkZ) {
        return this.provideChunk(chunkX, chunkZ);
    }

    public String makeString() {
        return "RandomLevelSource";
    }

    public void populate(IChunkProvider par1IChunkProvider, int par2, int par3) {
        BlockFalling.fallInstantly = true;
        int k = par2 * 16;
        int l = par3 * 16;
        BiomeGenBase biomegenbase = this.worldObj.getBiomeGenForCoords(k + 16, l + 16);
        this.rand.setSeed(this.worldObj.getSeed());
        long i1 = this.rand.nextLong() / 2L * 2L + 1L;
        long j1 = this.rand.nextLong() / 2L * 2L + 1L;
        this.rand.setSeed((long)par2 * i1 + (long)par3 * j1 ^ this.worldObj.getSeed());
        boolean flag = false;
        MinecraftForge.EVENT_BUS.post((Event)new PopulateChunkEvent.Pre(par1IChunkProvider, this.worldObj, this.rand, par2, par3, flag));
        boolean doGen = TerrainGen.populate((IChunkProvider)par1IChunkProvider, (World)this.worldObj, (Random)this.rand, (int)par2, (int)par3, (boolean)flag, (PopulateChunkEvent.Populate.EventType)PopulateChunkEvent.Populate.EventType.DUNGEON);
        biomegenbase.decorate(this.worldObj, this.rand, k, l);
        if (TerrainGen.populate((IChunkProvider)par1IChunkProvider, (World)this.worldObj, (Random)this.rand, (int)par2, (int)par3, (boolean)flag, (PopulateChunkEvent.Populate.EventType)PopulateChunkEvent.Populate.EventType.ANIMALS)) {
            SpawnerAnimals.performWorldGenSpawning((World)this.worldObj, (BiomeGenBase)biomegenbase, (int)(k + 8), (int)(l + 8), (int)16, (int)16, (Random)this.rand);
        }
        k += 8;
        l += 8;
        doGen = TerrainGen.populate((IChunkProvider)par1IChunkProvider, (World)this.worldObj, (Random)this.rand, (int)par2, (int)par3, (boolean)flag, (PopulateChunkEvent.Populate.EventType)PopulateChunkEvent.Populate.EventType.ICE);
        for (int k1 = 0; doGen && k1 < 16; ++k1) {
            for (int l1 = 0; l1 < 16; ++l1) {
                int i2 = this.worldObj.getPrecipitationHeight(k + k1, l + l1);
                if (this.worldObj.isBlockFreezable(k1 + k, i2 - 1, l1 + l)) {
                    this.worldObj.setBlock(k1 + k, i2 - 1, l1 + l, Blocks.ice, 0, 2);
                }
                if (!this.worldObj.func_147478_e(k1 + k, i2, l1 + l, true)) continue;
                this.worldObj.setBlock(k1 + k, i2, l1 + l, Blocks.snow_layer, 0, 2);
            }
        }
        MinecraftForge.EVENT_BUS.post((Event)new PopulateChunkEvent.Post(par1IChunkProvider, this.worldObj, this.rand, par2, par3, flag));
        BlockFalling.fallInstantly = false;
    }

    public Chunk provideChunk(int par1, int par2) {
        this.rand.setSeed((long)par1 * 341873128712L + (long)par2 * 132897987541L);
        Block[] ablock = new Block[65536];
        byte[] abyte = new byte[65536];
        this.func_147424_a(par1, par2, ablock);
        this.biomesForGeneration = this.worldObj.getWorldChunkManager().loadBlockGeneratorData(this.biomesForGeneration, par1 * 16, par2 * 16, 16, 16);
        this.replaceBlocksForBiome(par1, par2, ablock, abyte, this.biomesForGeneration);
        this.caveGenerator.func_151539_a((IChunkProvider)this, this.worldObj, par1, par2, ablock);
        Chunk chunk = new Chunk(this.worldObj, ablock, abyte, par1, par2);
        byte[] abyte1 = chunk.getBiomeArray();
        for (int k = 0; k < abyte1.length; ++k) {
            abyte1[k] = (byte)this.biomesForGeneration[k].biomeID;
        }
        chunk.generateSkylightMap();
        return chunk;
    }

    public void replaceBlocksForBiome(int par1, int par2, Block[] par3ArrayOfBlock, byte[] par4ArrayOfByte, BiomeGenBase[] par5ArrayOfBiomeGenBase) {
        ChunkProviderEvent.ReplaceBiomeBlocks event = new ChunkProviderEvent.ReplaceBiomeBlocks((IChunkProvider)this, par1, par2, par3ArrayOfBlock, par4ArrayOfByte, par5ArrayOfBiomeGenBase, this.worldObj);
        MinecraftForge.EVENT_BUS.post((Event)event);
        if (event.getResult() == Event.Result.DENY) {
            return;
        }
        double d0 = 0.03125;
        this.stoneNoise = this.noiseGen4.func_151599_a(this.stoneNoise, (double)(par1 * 16), (double)(par2 * 16), 16, 16, d0 * 2.0, d0 * 2.0, 1.0);
        for (int k = 0; k < 16; ++k) {
            for (int l = 0; l < 16; ++l) {
                BiomeGenBase biomegenbase = par5ArrayOfBiomeGenBase[l + k * 16];
                biomegenbase.genTerrainBlocks(this.worldObj, this.rand, par3ArrayOfBlock, par4ArrayOfByte, par1 * 16 + k, par2 * 16 + l, this.stoneNoise[l + k * 16]);
            }
        }
    }

    public boolean saveChunks(boolean par1, IProgressUpdate par2IProgressUpdate) {
        return true;
    }

    public void saveExtraData() {
    }

    public boolean unloadQueuedChunks() {
        return false;
    }
}
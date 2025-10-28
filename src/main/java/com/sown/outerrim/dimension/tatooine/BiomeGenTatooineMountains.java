package com.sown.outerrim.dimension.tatooine;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Arrays;
import java.util.Random;

import com.sown.outerrim.OuterRim;
import com.sown.outerrim.entities.EntityKaadu;
import com.sown.outerrim.entities.EntityPelikki;
import com.sown.outerrim.entities.EntityTuskenRaider;
import com.sown.outerrim.registry.BlockRegister;
import com.sown.outerrim.world.gen.WorldGenJaporIvoryTree;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.BiomeGenBase.SpawnListEntry;
import net.minecraft.world.gen.NoiseGeneratorPerlin;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;

public class BiomeGenTatooineMountains extends BiomeGenBase
{
    private byte[] field_150621_aC;
    private long field_150622_aD;
    private NoiseGeneratorPerlin field_150623_aE;
    private NoiseGeneratorPerlin field_150624_aF;
    private NoiseGeneratorPerlin field_150625_aG;
    private boolean field_150626_aH;
    private boolean field_150620_aI;

    public BiomeGenTatooineMountains(int p_i45380_1_, boolean p_i45380_2_, boolean p_i45380_3_)
    {
        super(p_i45380_1_);
        this.field_150626_aH = p_i45380_2_;
        this.field_150620_aI = p_i45380_3_;
        this.setDisableRain();
        this.setTemperatureRainfall(2.0F, 0.0F);
        this.spawnableCreatureList.clear();
        this.spawnableCaveCreatureList.clear();
        this.spawnableMonsterList.clear();
        this.spawnableWaterCreatureList.clear();
        this.spawnableCreatureList.add(new SpawnListEntry(EntityTuskenRaider.class, 8, 1, 4));
        this.topBlock = BlockRegister.getRegisteredBlock("tatooineSand2");
        this.field_150604_aj = 1;
        this.fillerBlock = BlockRegister.getRegisteredBlock("tatooineRock");
        this.theBiomeDecorator.treesPerChunk = -999;
        this.theBiomeDecorator.deadBushPerChunk = 20;
        this.theBiomeDecorator.reedsPerChunk = 3;
        this.theBiomeDecorator.cactiPerChunk = 5;
        this.theBiomeDecorator.flowersPerChunk = 0;

        if (p_i45380_3_)
        {
            this.theBiomeDecorator.treesPerChunk = 5;
        }
    }

    @Override
    public WorldGenAbstractTree func_150567_a(Random p_150567_1_)
    {
        return this.worldGeneratorTrees;
    }

    @Override
    public void decorate(World world, Random random, int chunkX, int chunkZ) {
        super.decorate(world, random, chunkX, chunkZ); // Call the base class decorate method

        // Add dead bush generation logic
        for (int i = 0; i < 10; i++) { // 10 attempts to place dead bushes per chunk
            int x = chunkX + random.nextInt(16) + 8; // Randomize X within chunk
            int z = chunkZ + random.nextInt(16) + 8; // Randomize Z within chunk
            int y = world.getHeightValue(x, z); // Get the height value at X, Z
            WorldGenDeadBush worldGenDeadBush = new WorldGenDeadBush(); // Create the generator instance
            worldGenDeadBush.generate(world, random, x, y, z); // Generate the dead bush
        }

        // 1/1000 chance to spawn a Japor Ivory Tree
        if (random.nextInt(1000) == 0) { // Generates once per 1000 chunks on average
            int x = chunkX + random.nextInt(16) + 8;
            int z = chunkZ + random.nextInt(16) + 8;
            int y = world.getHeightValue(x, z);

            //WorldGenJaporIvoryTree japorIvoryTree = new WorldGenJaporIvoryTree();
            //japorIvoryTree.generate(world, random, x, y, z);
        }
    }

    @Override
    public void genTerrainBlocks(World world, Random random, Block[] blocks, byte[] bytes, int chunkX, int chunkZ, double noiseVal) {
        if (this.field_150621_aC == null || this.field_150622_aD != world.getSeed()) {
            this.func_150619_a(world.getSeed());
        }

        if (this.field_150623_aE == null || this.field_150624_aF == null || this.field_150622_aD != world.getSeed()) {
            Random noiseRandom = new Random(this.field_150622_aD);
            this.field_150623_aE = new NoiseGeneratorPerlin(noiseRandom, 4);
            this.field_150624_aF = new NoiseGeneratorPerlin(noiseRandom, 1);
        }

        this.field_150622_aD = world.getSeed();
        int x = chunkX & 15;
        int z = chunkZ & 15;
        Block block = Blocks.stone;
        int blockHeight = blocks.length / 256;

        for (int y = 255; y >= 0; --y) {
            int index = (z * 16 + x) * blockHeight + y;

            if (y <= random.nextInt(5)) {
                blocks[index] = Blocks.bedrock;
            } else {
                Block blockAtPos = blocks[index];

                if (blockAtPos == null || blockAtPos.getMaterial() == Material.air) {
                    if (y < 50) {
                        blocks[index] = BlockRegister.getRegisteredBlock("tatooineRock");
                    } else {
                        blocks[index] = Blocks.air;
                    }
                } else if (blockAtPos == Blocks.stone) {
                    if (y < 50) {
                        blocks[index] = BlockRegister.getRegisteredBlock("tatooineRock");
                    } else if (y < 80) {
                        blocks[index] = random.nextInt(3) == 0 ? BlockRegister.getRegisteredBlock("tatooineSandstone") : BlockRegister.getRegisteredBlock("tatooineSand2");
                    } else if (y < 100) {
                        blocks[index] = random.nextInt(3) != 0 ? BlockRegister.getRegisteredBlock("tatooineSand1") : BlockRegister.getRegisteredBlock("tatooineRock");
                    } else {
                        blocks[index] = BlockRegister.getRegisteredBlock("tatooineSand1");
                    }

                    if (random.nextInt(10) == 0) {
                        blocks[index] = BlockRegister.getRegisteredBlock("tatooineCobblestone");
                    }
                }
            }
        }
    }

    public void func_150619_a(long p_150619_1_)
    {
        this.field_150621_aC = new byte[64];
        Arrays.fill(this.field_150621_aC, (byte)16);
        Random random = new Random(p_150619_1_);
        this.field_150625_aG = new NoiseGeneratorPerlin(random, 1);
        int j;

        for (j = 0; j < 64; ++j)
        {
            j += random.nextInt(5) + 1;

            if (j < 64)
            {
                this.field_150621_aC[j] = 1;
            }
        }

        j = random.nextInt(4) + 2;
        int k;
        int l;
        int i1;
        int j1;

        for (k = 0; k < j; ++k)
        {
            l = random.nextInt(3) + 1;
            i1 = random.nextInt(64);

            for (j1 = 0; i1 + j1 < 64 && j1 < l; ++j1)
            {
                this.field_150621_aC[i1 + j1] = 2;
            }
        }

        k = random.nextInt(4) + 2;
        int k1;

        for (l = 0; l < k; ++l)
        {
            i1 = random.nextInt(3) + 2;
            j1 = random.nextInt(64);

            for (k1 = 0; j1 + k1 < 64 && k1 < i1; ++k1)
            {
                this.field_150621_aC[j1 + k1] = 1;
            }
        }

        l = random.nextInt(4) + 2;

        for (i1 = 0; i1 < l; ++i1)
        {
            j1 = random.nextInt(3) + 1;
            k1 = random.nextInt(64);

            for (int l1 = 0; k1 + l1 < 64 && l1 < j1; ++l1)
            {
                this.field_150621_aC[k1 + l1] = 2;
            }
        }

        i1 = random.nextInt(3) + 3;
        j1 = 0;

        for (k1 = 0; k1 < i1; ++k1)
        {
            byte b0 = 1;
            j1 += random.nextInt(16) + 4;

            for (int i2 = 0; j1 + i2 < 64 && i2 < b0; ++i2)
            {
                this.field_150621_aC[j1 + i2] = 1;

                if (j1 + i2 > 1 && random.nextBoolean())
                {
                    this.field_150621_aC[j1 + i2 - 1] = 2;
                }

                if (j1 + i2 < 63 && random.nextBoolean())
                {
                    this.field_150621_aC[j1 + i2 + 1] = 0;
                }
            }
        }
    }

    /**
     * Provides the basic foliage color based on the biome temperature and rainfall
     */
    @Override
    @SideOnly(Side.CLIENT)
    public int getBiomeFoliageColor(int p_150571_1_, int p_150571_2_, int p_150571_3_)
    {
        return 10387789;
    }
    @Override
    @SideOnly(Side.CLIENT)
    public int getSkyColorByTemp(float temp)
    {
        return 6071516;
    }

    /**
     * Provides the basic grass color based on the biome temperature and rainfall
     */
    @Override
    @SideOnly(Side.CLIENT)
    public int getBiomeGrassColor(int p_150558_1_, int p_150558_2_, int p_150558_3_)
    {
        return 9470285;
    }

    public byte func_150618_d(int p_150618_1_, int p_150618_2_, int p_150618_3_)
    {
        int l = (int)Math.round(this.field_150625_aG.func_151601_a(p_150618_1_ * 1.0D / 512.0D, p_150618_1_ * 1.0D / 512.0D) * 2.0D);
        return this.field_150621_aC[(p_150618_2_ + l + 64) % 64];
    }

    /**
     * Creates a mutated version of the biome and places it into the biomeList with an index equal to the original plus
     * 128
     */
    @Override
    public BiomeGenBase createMutation()
    {
        boolean flag = this.biomeID == BiomeGenBase.mesa.biomeID;
        BiomeGenTatooineMountains biomegenmesa = new BiomeGenTatooineMountains(this.biomeID + 128, flag, this.field_150620_aI);

        if (!flag)
        {
            biomegenmesa.setHeight(height_LowHills);
            biomegenmesa.setBiomeName(this.biomeName + " M");
        }
        else
        {
            biomegenmesa.setBiomeName(this.biomeName + " (Bryce)");
        }

        biomegenmesa.func_150557_a(this.color, true);
        return biomegenmesa;
    }
}
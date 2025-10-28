package com.sown.outerrim.dimension.mustafar;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Arrays;
import java.util.Random;

import com.sown.outerrim.OuterRim;
import com.sown.outerrim.registry.BlockRegister;
import com.sown.util.world.creation.OROverworldBiome;
import com.sown.util.world.creation.OverworldBiomeFeatures;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.NoiseGeneratorPerlin;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;

public class BiomeGenMustafar extends OROverworldBiome
{
    private WorldGenMustafarVolcano volcanoGenerator;
    private byte[] field_150621_aC;
    private long field_150622_aD;
    private NoiseGeneratorPerlin field_150623_aE;
    private NoiseGeneratorPerlin field_150624_aF;
    private NoiseGeneratorPerlin field_150625_aG;
    private boolean field_150626_aH;
    private boolean field_150620_aI;

    public BiomeGenMustafar(int p_i45380_1_, boolean p_i45380_2_, boolean p_i45380_3_)
    {
        super(p_i45380_1_);
        this.volcanoGenerator = new WorldGenMustafarVolcano(Blocks.lava, BlockRegister.getRegisteredBlock("mustafarMagma"), BlockRegister.getRegisteredBlock("mustafarCobblestone"), BlockRegister.getRegisteredBlock("mustafarMagma"), BlockRegister.getRegisteredBlock("ashBlack"));
        this.field_150626_aH = p_i45380_2_;
        this.field_150620_aI = p_i45380_3_;
        this.setDisableRain();
        this.setTemperatureRainfall(2.0F, 0.0F);
        this.spawnableCreatureList.clear();
        this.topBlock = BlockRegister.getRegisteredBlock("mustafarCobblestone");
        this.field_150604_aj = 8;
        this.fillerBlock = BlockRegister.getRegisteredBlock("mustafarRock");
        this.theBiomeDecorator.treesPerChunk = -999;
        Random rand = new Random();
        ((OverworldBiomeFeatures)this.theBiomeDecorator.orFeatures).mustafarLavaSpoutsPerChunk = 1;
        this.theBiomeDecorator.deadBushPerChunk = 20;
        this.theBiomeDecorator.reedsPerChunk = 3;
        this.theBiomeDecorator.cactiPerChunk = 5;
        this.theBiomeDecorator.flowersPerChunk = 0;
        this.spawnableCreatureList.clear();
        this.spawnableMonsterList.clear();

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
        super.decorate(world, random, chunkX, chunkZ);

        int x, y, z;
        for (int i = 0; i < 3; ++i) {
            x = chunkX + random.nextInt(16) + 8;
            z = chunkZ + random.nextInt(16) + 8;
            if (random.nextInt(100) < 5) {  // 5% chance to spawn a volcano in a chunk.
                this.volcanoGenerator.generate(world, random, x, world.getHeightValue(x, z), z);
            }
        }
    }

    @Override
    public void genTerrainBlocks(World p_150573_1_, Random p_150573_2_, Block[] p_150573_3_, byte[] p_150573_4_, int p_150573_5_, int p_150573_6_, double p_150573_7_)
    {
        if (this.field_150621_aC == null || this.field_150622_aD != p_150573_1_.getSeed())
        {
            this.func_150619_a(p_150573_1_.getSeed());
        }

        if (this.field_150623_aE == null || this.field_150624_aF == null || this.field_150622_aD != p_150573_1_.getSeed())
        {
            Random random1 = new Random(this.field_150622_aD);
            this.field_150623_aE = new NoiseGeneratorPerlin(random1, 4);
            this.field_150624_aF = new NoiseGeneratorPerlin(random1, 1);
        }

        this.field_150622_aD = p_150573_1_.getSeed();
        double d5 = 0.0D;
        int k;
        int l;

        if (this.field_150626_aH)
        {
            k = (p_150573_5_ & -16) + (p_150573_6_ & 15);
            l = (p_150573_6_ & -16) + (p_150573_5_ & 15);
            double d1 = Math.min(Math.abs(p_150573_7_), this.field_150623_aE.func_151601_a(k * 0.25D, l * 0.25D));

            if (d1 > 0.0D)
            {
                double d2 = 0.001953125D;
                double d3 = Math.abs(this.field_150624_aF.func_151601_a(k * d2, l * d2));
                d5 = d1 * d1 * 2.5D;
                double d4 = Math.ceil(d3 * 50.0D) + 14.0D;

                if (d5 > d4)
                {
                    d5 = d4;
                }

                d5 += 64.0D;
            }
        }

        k = p_150573_5_ & 15;
        l = p_150573_6_ & 15;
        boolean flag = true;
        Block block = BlockRegister.getRegisteredBlock("mustafarCobblestone");
        Block block2 = this.fillerBlock;
        int i1 = (int)(p_150573_7_ / 3.0D + 3.0D + p_150573_2_.nextDouble() * 0.25D);
        boolean flag1 = Math.cos(p_150573_7_ / 3.0D * Math.PI) > 0.0D;
        int j1 = -1;
        boolean flag2 = false;
        int k1 = p_150573_3_.length / 256;

        for (int l1 = 255; l1 >= 0; --l1)
        {
            int i2 = (l * 16 + k) * k1 + l1;

            if ((p_150573_3_[i2] == null || p_150573_3_[i2].getMaterial() == Material.air) && l1 < (int)d5)
            {
                p_150573_3_[i2] = Blocks.stone;
            }

            if (l1 <= 0 + p_150573_2_.nextInt(5))
            {
                p_150573_3_[i2] = Blocks.bedrock;
            }
            else
            {
                Block block1 = p_150573_3_[i2];

                if (block1 != null && block1.getMaterial() != Material.air)
                {
                    if (block1 == Blocks.stone)
                    {
                        byte b0;

                        if (j1 == -1)
                        {
                            flag2 = false;

                            if (i1 <= 0)
                            {
                                block = null;
                                block2 = Blocks.stone;
                            }
                            else if (l1 >= 59 && l1 <= 64) {
                                // Create an array of the five possible blocks
                                Block[] possibleBlocks = new Block[] {
                                		BlockRegister.getRegisteredBlock("mustafarMagma"),
                                        BlockRegister.getRegisteredBlock("mustafarCobblestone"),
                                        BlockRegister.getRegisteredBlock("mustafarCobblestone"),
                                        BlockRegister.getRegisteredBlock("mustafarCobblestone"),
                                        BlockRegister.getRegisteredBlock("ashBlack")
                                };
                                // Choose a random block from the possibleBlocks array
                                int randomIndex = p_150573_2_.nextInt(5); // Fixed here
                                block = possibleBlocks[randomIndex];

                                block2 = possibleBlocks[randomIndex];
                            }

                            if (l1 < 63 && (block == null || block.getMaterial() == Material.air))
                            {
                                block = Blocks.lava;
                            }

                            j1 = i1 + Math.max(0, l1 - 63);

                            if (l1 >= 62)
                            {
                                if (this.field_150620_aI && l1 > 86 + i1 * 2)
                                {
                                    if (flag1)
                                    {
                                        p_150573_3_[i2] = Blocks.dirt;
                                        p_150573_4_[i2] = 8;
                                    }
                                    else
                                    {
                                        p_150573_3_[i2] = BlockRegister.getRegisteredBlock("ashBlack");
                                    }
                                }
                                else if (l1 > 66 + i1)
                                {
                                    b0 = 86;

                                    if (l1 >= 64 && l1 <= 107)
                                    {
                                        if (!flag1)
                                        {
                                            b0 = this.func_150618_d(p_150573_5_, l1, p_150573_6_);
                                        }
                                    }
                                    else
                                    {
                                        b0 = 8;
                                    }

                                    if (b0 < 16)
                                    {
                                        // Top and floor
                                        p_150573_3_[i2] = BlockRegister.getRegisteredBlock("mustafarCobblestone");
                                        p_150573_4_[i2] = b0;
                                    }
                                    else
                                    {
                                        p_150573_3_[i2] = BlockRegister.getRegisteredBlock("mustafarRock");
                                    }
                                }
                                else
                                {
                                    p_150573_3_[i2] = this.topBlock;
                                    p_150573_4_[i2] = (byte)this.field_150604_aj;
                                    flag2 = true;
                                }
                            }
                            else
                            {
                                p_150573_3_[i2] = block2;

                                if (block2 == BlockRegister.getRegisteredBlock("mustafarCobblestone"))
                                {
                                    p_150573_4_[i2] = 8;
                                }
                            }
                        }
                        else if (j1 > 0)
                        {
                            --j1;

                            if (flag2)
                            {
                                p_150573_3_[i2] = BlockRegister.getRegisteredBlock("mustafarCobblestone");
                                p_150573_4_[i2] = 10;
                            }
                            else
                            {
                                b0 = this.func_150618_d(p_150573_5_, l1, p_150573_6_);

                                if (b0 < 16)
                                {
                                    p_150573_3_[i2] = BlockRegister.getRegisteredBlock("mustafarCobblestone");
                                    p_150573_4_[i2] = b0;
                                }
                                else
                                {
                                    p_150573_3_[i2] = BlockRegister.getRegisteredBlock("mustafarRock");
                                }
                            }
                        }
                    }
                }
                else
                {
                    j1 = -1;
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
                this.field_150621_aC[j] = 8;
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
                this.field_150621_aC[i1 + j1] = 10;
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
                this.field_150621_aC[j1 + k1] = 8;
            }
        }

        l = random.nextInt(4) + 2;

        for (i1 = 0; i1 < l; ++i1)
        {
            j1 = random.nextInt(3) + 1;
            k1 = random.nextInt(64);

            for (int l1 = 0; k1 + l1 < 64 && l1 < j1; ++l1)
            {
                this.field_150621_aC[k1 + l1] = 10;
            }
        }

        i1 = random.nextInt(3) + 3;
        j1 = 0;

        for (k1 = 0; k1 < i1; ++k1)
        {
            byte b0 = 8;
            j1 += random.nextInt(16) + 4;

            for (int i2 = 0; j1 + i2 < 64 && i2 < b0; ++i2)
            {
                this.field_150621_aC[j1 + i2] = 8;

                if (j1 + i2 > 1 && random.nextBoolean())
                {
                    this.field_150621_aC[j1 + i2 - 1] = 10;
                }

                if (j1 + i2 < 63 && random.nextBoolean())
                {
                    this.field_150621_aC[j1 + i2 + 1] = 8;
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
        return 5452834;
    }
    @Override
    @SideOnly(Side.CLIENT)
    public int getSkyColorByTemp(float temp)
    {
        return 12151387;
    }

    /**
     * Provides the basic grass color based on the biome temperature and rainfall
     */
    @Override
    @SideOnly(Side.CLIENT)
    public int getBiomeGrassColor(int p_150558_1_, int p_150558_2_, int p_150558_3_)
    {
        return 5452834;
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
        BiomeGenMustafar biomegenmesa = new BiomeGenMustafar(this.biomeID + 128, flag, this.field_150620_aI);

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
package com.sown.outerrim.dimension.ryloth;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Arrays;
import java.util.Random;

import com.sown.outerrim.registry.BlockRegister;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.NoiseGeneratorPerlin;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;

public class BiomeGenRylothWastes extends BiomeGenBase {
    private byte[] field_150621_aC;
    private long field_150622_aD;
    private NoiseGeneratorPerlin field_150623_aE;
    private NoiseGeneratorPerlin field_150624_aF;
    private NoiseGeneratorPerlin field_150625_aG;
    private boolean field_150626_aH;
    private boolean field_150620_aI;

    public BiomeGenRylothWastes(int p_i45380_1_, boolean p_i45380_2_, boolean p_i45380_3_) {
        super(p_i45380_1_);
        this.field_150626_aH = p_i45380_2_;
        this.field_150620_aI = p_i45380_3_;
        this.setDisableRain();
        this.setTemperatureRainfall(2.0F, 0.0F);
        this.spawnableCreatureList.clear();
        this.topBlock = BlockRegister.getRegisteredBlock("rylothRock");
        this.field_150604_aj = 1;
        this.fillerBlock = BlockRegister.getRegisteredBlock("rylothRock");
        this.theBiomeDecorator.treesPerChunk = -999;
        this.theBiomeDecorator.deadBushPerChunk = 20;
        this.theBiomeDecorator.reedsPerChunk = 3;
        this.theBiomeDecorator.cactiPerChunk = 5;
        this.theBiomeDecorator.flowersPerChunk = 0;
        this.spawnableCreatureList.clear();
        this.spawnableMonsterList.clear();

        if (p_i45380_3_) {
            this.theBiomeDecorator.treesPerChunk = 5;
        }
    }

    @Override
    public WorldGenAbstractTree func_150567_a(Random p_150567_1_) {
        return this.worldGeneratorTrees;
    }

    @Override
    public void decorate(World p_76728_1_, Random p_76728_2_, int p_76728_3_, int p_76728_4_) {
        super.decorate(p_76728_1_, p_76728_2_, p_76728_3_, p_76728_4_);
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
        int blockHeight = blocks.length / 256;

        for (int y = 255; y >= 0; --y) {
            int index = (z * 16 + x) * blockHeight + y;

            if (y <= random.nextInt(5)) {
                blocks[index] = Blocks.bedrock;
            } else {
                Block blockAtPos = blocks[index];

                // Only apply the random block generation to blocks that would normally be terrain (i.e., not air or bedrock)
                if (blockAtPos != null && blockAtPos.getMaterial() != Material.air) {
                    // Apply the block ratio (7/9 rylothRock, 1/9 dirt, 1/9 rylothDirt)
                    int chance = random.nextInt(9);
                    if (chance < 7) {
                        blocks[index] = BlockRegister.getRegisteredBlock("rylothRock");
                    } else if (chance == 7) {
                        blocks[index] = Blocks.dirt;
                    } else {
                        blocks[index] = BlockRegister.getRegisteredBlock("rylothDirt");
                    }
                }
            }
        }
    }

    public void func_150619_a(long p_150619_1_) {
        this.field_150621_aC = new byte[64];
        Arrays.fill(this.field_150621_aC, (byte) 16);
        Random random = new Random(p_150619_1_);
        this.field_150625_aG = new NoiseGeneratorPerlin(random, 1);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getBiomeFoliageColor(int p_150571_1_, int p_150571_2_, int p_150571_3_) {
        return 10387789;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getSkyColorByTemp(float temp) {
        return 6071516;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getBiomeGrassColor(int p_150558_1_, int p_150558_2_, int p_150558_3_) {
        return 9470285;
    }

    public byte func_150618_d(int p_150618_1_, int p_150618_2_, int p_150618_3_) {
        int l = (int) Math.round(this.field_150625_aG.func_151601_a(p_150618_1_ * 1.0D / 512.0D, p_150618_1_ * 1.0D / 512.0D) * 2.0D);
        return this.field_150621_aC[(p_150618_2_ + l + 64) % 64];
    }

    @Override
    public BiomeGenBase createMutation() {
        BiomeGenRylothWastes biome = new BiomeGenRylothWastes(this.biomeID + 128, this.field_150626_aH, this.field_150620_aI);
        biome.setHeight(height_LowHills);
        biome.setBiomeName(this.biomeName + " M");
        biome.func_150557_a(this.color, true);
        return biome;
    }
}

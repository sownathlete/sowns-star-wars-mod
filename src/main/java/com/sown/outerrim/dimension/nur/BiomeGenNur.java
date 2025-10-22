package com.sown.outerrim.dimension.nur;

import java.util.Random;

import com.sown.outerrim.registry.BlockRegister;

import net.minecraft.block.Block;
import net.minecraft.entity.passive.EntitySquid;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;

public class BiomeGenNur extends BiomeGenBase {
    private static final BiomeGenBase.Height biomeHeight = new BiomeGenBase.Height(-2.5f, 0.1f);

    public BiomeGenNur(int biomeID) {
        super(biomeID);
        this.waterColorMultiplier = 2443330;
        this.setHeight(biomeHeight);
        this.setColor(27468);
        this.spawnableCreatureList.clear();
        this.spawnableCaveCreatureList.clear();
        this.spawnableMonsterList.clear();

        this.setTemperatureRainfall(0.5f, 0.9f);
    }

    @Override
    public void decorate(World world, Random random, int chunkX, int chunkZ) {
        super.decorate(world, random, chunkX, chunkZ);
        // Your decoration code here
    }

    @Override
    public void genTerrainBlocks(World world, Random rand, Block[] blocks, byte[] metadata, int x, int z, double noiseVal) {
        Block topBlock = this.topBlock;
        byte topBlockMeta = (byte) (this.field_150604_aj & 255);
        Block fillerBlock = this.fillerBlock;
        int var12 = -1;
        int var13 = (int) (noiseVal / 3.0D + 3.0D + rand.nextDouble() * 0.25D);
        int chunkX = x & 15;
        int chunkZ = z & 15;
        int seaLevel = 64; // You can set this to the sea level you want

        for (int y = 255; y >= 0; --y) {
            int index = (chunkZ * 16 + chunkX) * 256 + y;

            if (y <= rand.nextInt(5)) {
                blocks[index] = Blocks.bedrock;
            } else {
                Block currentBlock = blocks[index];

                if (currentBlock == Blocks.air) {
                    var12 = -1;
                } else if (currentBlock == Blocks.stone) {
                    if (var12 == -1) {
                        if (var13 <= 0) {
                            topBlock = null;
                            topBlockMeta = 0;
                            fillerBlock = Blocks.stone;
                        } else if (y >= seaLevel - 4 && y <= seaLevel + 1) {
                            topBlock = this.topBlock;
                            topBlockMeta = (byte) (this.field_150604_aj & 255);
                            fillerBlock = this.fillerBlock;
                        }

                        if (y < seaLevel && (topBlock == null || topBlock == Blocks.air)) {
                            topBlock = Blocks.water;
                            topBlockMeta = 0;
                        }

                        var12 = var13;

                        if (y >= seaLevel - 1) {
                            blocks[index] = topBlock;
                            metadata[index] = topBlockMeta;
                        } else if (y < seaLevel - 7 - var13) {
                            topBlock = null;
                            fillerBlock = Blocks.stone;

                            Block randomBlock;
                            int randomNum = rand.nextInt(100);

                            if (randomNum < 33) {
                                randomBlock = BlockRegister.getRegisteredBlock("nurRock");
                            } else if (randomNum < 66) {
                                randomBlock = BlockRegister.getRegisteredBlock("nurCobblestone");
                            } else {
                                randomBlock = BlockRegister.getRegisteredBlock("nurSoil");
                            }

                            blocks[index] = randomBlock;
                        } else {
                            blocks[index] = fillerBlock;
                        }
                    } else if (var12 > 0) {
                        --var12;
                        blocks[index] = fillerBlock;

                        if (var12 == 0 && fillerBlock == Blocks.sand) {
                            var12 = rand.nextInt(4);
                            fillerBlock = Blocks.sandstone;
                        }
                    }
                }
            }
        }
    }
    @Override
    public int getSkyColorByTemp(float currentTemperature) {
        return 5995903; // Your desired sky color
    }
}

package com.sown.util.world.creation.worldgen;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import com.sown.util.world.creation.ORBiome;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class WorldGenSplotches extends WorldGeneratorOR {
    private Block splotchBlock;

    private int splotchBlockMeta;

    private int numberOfBlocks;

    private List blockList;

    public WorldGenSplotches(Block quicksandBlock, int quicksandBlockMeta, int numberOfBlocks, Block... blockList) {
        super(true);
        this.splotchBlock = quicksandBlock;
        this.splotchBlockMeta = quicksandBlockMeta;
        this.numberOfBlocks = numberOfBlocks;
        this.blockList = Arrays.asList(blockList);
    }

    @Override
    public boolean generate(World world, Random random, int x, int y, int z) {
        float var6 = random.nextFloat() * 3.1415927F;
        double var7 = ((x + 8) + MathHelper.sin(var6) * this.numberOfBlocks / 8.0F);
        double var9 = ((x + 8) - MathHelper.sin(var6) * this.numberOfBlocks / 8.0F);
        double var11 = ((z + 8) + MathHelper.cos(var6) * this.numberOfBlocks / 8.0F);
        double var13 = ((z + 8) - MathHelper.cos(var6) * this.numberOfBlocks / 8.0F);
        double var15 = (y + random.nextInt(3) - 2);
        double var17 = (y + random.nextInt(3) - 2);
        for (int var19 = 0; var19 <= this.numberOfBlocks; var19++) {
            double var20 = var7 + (var9 - var7) * var19 / this.numberOfBlocks;
            double var22 = var15 + (var17 - var15) * var19 / this.numberOfBlocks;
            double var24 = var11 + (var13 - var11) * var19 / this.numberOfBlocks;
            double var26 = random.nextDouble() * this.numberOfBlocks / 16.0D;
            double var28 = (MathHelper.sin(var19 * 3.1415927F / this.numberOfBlocks) + 1.0F) * var26 + 1.0D;
            double var30 = (MathHelper.sin(var19 * 3.1415927F / this.numberOfBlocks) + 1.0F) * var26 + 1.0D;
            int var32 = MathHelper.floor_double(var20 - var28 / 2.0D);
            int var33 = MathHelper.floor_double(var22 - var30 / 2.0D);
            int var34 = MathHelper.floor_double(var24 - var28 / 2.0D);
            int var35 = MathHelper.floor_double(var20 + var28 / 2.0D);
            int var36 = MathHelper.floor_double(var22 + var30 / 2.0D);
            int var37 = MathHelper.floor_double(var24 + var28 / 2.0D);
            for (int var38 = var32; var38 <= var35; var38++) {
                double var39 = (var38 + 0.5D - var20) / var28 / 2.0D;
                if (var39 * var39 < 1.0D) {
                    for (int var41 = var33; var41 <= var36; var41++) {
                        double var42 = (var41 + 0.5D - var22) / var30 / 2.0D;
                        if (var39 * var39 + var42 * var42 < 1.0D) {
                            for (int var44 = var34; var44 <= var37; var44++) {
                                double var45 = (var44 + 0.5D - var24) / var28 / 2.0D;
                                if (var39 * var39 + var42 * var42 + var45 * var45 < 1.0D && world.getBlock(var38, var41, var44) != Blocks.air && this.blockList.contains(world.getBlock(var38, var41, var44))) {
                                    this.setBlockAndNotifyAdequately(world, var38, var41, var44, this.splotchBlock, this.splotchBlockMeta);
                                }
                            }
                        }
                    }
                }
            }
        }
        return true;
    }

    @Override
    public void setupGeneration(World world, Random random, ORBiome biome, String featureName, int x, int z) {
        if (!featureName.equals("generateMustafarMagma") || !((Boolean)biome.theBiomeDecorator.orFeatures.getFeature("generateMustafarMagma")).booleanValue()) {
            if (!featureName.equals("generateAcidicRock1") || !((Boolean)biome.theBiomeDecorator.orFeatures.getFeature("generateAcidicRock1")).booleanValue()) {
                if (!featureName.equals("generateAcidicRock2") || !((Boolean)biome.theBiomeDecorator.orFeatures.getFeature("generateAcidicRock2")).booleanValue()) {
                    if (!featureName.equals("generateKesselRock1") || !((Boolean)biome.theBiomeDecorator.orFeatures.getFeature("generateKesselRock1")).booleanValue()) {
                        if (!featureName.equals("generateKesselRock2") || !((Boolean)biome.theBiomeDecorator.orFeatures.getFeature("generateKesselRock2")).booleanValue()) {
                            if (!featureName.equals("generateMud") || !((Boolean)biome.theBiomeDecorator.orFeatures.getFeature("generateMud")).booleanValue()) {
                                if (!featureName.equals("generateKesselMud") || !((Boolean)biome.theBiomeDecorator.orFeatures.getFeature("generateKesselMud")).booleanValue()) {
                                    if (!featureName.equals("generateQuicksand") || !((Boolean)biome.theBiomeDecorator.orFeatures.getFeature("generateQuicksand")).booleanValue()) {
                                        if (!featureName.equals("generateCanyon") || !((Boolean)biome.theBiomeDecorator.orFeatures.getFeature("generateCanyon")).booleanValue()) {
                                            if (!featureName.equals("generateStoneInGrass") || !((Boolean)biome.theBiomeDecorator.orFeatures.getFeature("generateStoneInGrass")).booleanValue()) {
                                                if (!featureName.equals("generateStoneInGrass2") || !((Boolean)biome.theBiomeDecorator.orFeatures.getFeature("generateStoneInGrass2")).booleanValue()) {
                                                    if (!featureName.equals("generateGrass") || !((Boolean)biome.theBiomeDecorator.orFeatures.getFeature("generateGrass")).booleanValue()) {
                                                        if (!featureName.equals("generateSand") || !((Boolean)biome.theBiomeDecorator.orFeatures.getFeature("generateSand")).booleanValue()) {
                                                            if (!featureName.equals("generateQuagmire") || !((Boolean)biome.theBiomeDecorator.orFeatures.getFeature("generateQuagmire")).booleanValue()) {
                                                                if (!featureName.equals("generateAsh") || !((Boolean)biome.theBiomeDecorator.orFeatures.getFeature("generateAsh")).booleanValue()) {
                                                                    if (!featureName.equals("generateMycelium") || !((Boolean)biome.theBiomeDecorator.orFeatures.getFeature("generateMycelium")).booleanValue()) {
                                                                        if (featureName.equals("generateSponge") && ((Boolean)biome.theBiomeDecorator.orFeatures.getFeature("generateSponge")).booleanValue()) {
                                                                            for (int i5 = 0; i5 < 5; i5++) {
                                                                                int randX = x + random.nextInt(16);
                                                                                int randY = random.nextInt(64);
                                                                                int randZ = z + random.nextInt(16);
                                                                                this.generate(world, random, randX, randY, randZ);
                                                                            }
                                                                        }
                                                                        return;
                                                                    }
                                                                    for (int i4 = 0; i4 < 10; i4++) {
                                                                        int randX = x + random.nextInt(16);
                                                                        int randY = random.nextInt(128);
                                                                        int randZ = z + random.nextInt(16);
                                                                        this.generate(world, random, randX, randY, randZ);
                                                                    }
                                                                    return;
                                                                }
                                                                for (int i3 = 0; i3 < 10; i3++) {
                                                                    int randX = x + random.nextInt(16);
                                                                    int randY = random.nextInt(128);
                                                                    int randZ = z + random.nextInt(16);
                                                                    this.generate(world, random, randX, randY, randZ);
                                                                }
                                                                return;
                                                            }
                                                            for (int i2 = 0; i2 < 15; i2++) {
                                                                int randX = x + random.nextInt(16);
                                                                int randY = random.nextInt(64) + 64;
                                                                int randZ = z + random.nextInt(16);
                                                                this.generate(world, random, randX, randY, randZ);
                                                            }
                                                            return;
                                                        }
                                                        for (int i1 = 0; i1 < 15; i1++) {
                                                            int randX = x + random.nextInt(16);
                                                            int randY = random.nextInt(128);
                                                            int randZ = z + random.nextInt(16);
                                                            this.generate(world, random, randX, randY, randZ);
                                                        }
                                                        return;
                                                    }
                                                    for (int n = 0; n < 15; n++) {
                                                        int randX = x + random.nextInt(16);
                                                        int randY = random.nextInt(128);
                                                        int randZ = z + random.nextInt(16);
                                                        this.generate(world, random, randX, randY, randZ);
                                                    }
                                                    return;
                                                }
                                                for (int m = 0; m < 20; m++) {
                                                    int randX = x + random.nextInt(16);
                                                    int randY = random.nextInt(64) + 64;
                                                    int randZ = z + random.nextInt(16);
                                                    this.generate(world, random, randX, randY, randZ);
                                                }
                                                return;
                                            }
                                            for (int k = 0; k < 15; k++) {
                                                int randX = x + random.nextInt(16);
                                                int randY = random.nextInt(64) + 64;
                                                int randZ = z + random.nextInt(16);
                                                this.generate(world, random, randX, randY, randZ);
                                            }
                                            return;
                                        }
                                        for (int j = 0; j < 15; j++) {
                                            int randX = x + random.nextInt(16);
                                            int randY = random.nextInt(64) + 64;
                                            int randZ = z + random.nextInt(16);
                                            this.generate(world, random, randX, randY, randZ);
                                        }
                                        return;
                                    }
                                    for (int i = 0; i < 5; i++) {
                                        int randX = x + random.nextInt(16);
                                        int randY = random.nextInt(64) + 64;
                                        int randZ = z + random.nextInt(16);
                                        this.generate(world, random, randX, randY, randZ);
                                    }
                                }
                                for (int p2 = 0; p2 < 15; p2++) {
                                    int randX = x + random.nextInt(16);
                                    int randY = random.nextInt(64) + 64;
                                    int randZ = z + random.nextInt(16);
                                    this.generate(world, random, randX, randY, randZ);
                                }
                                return;
                            }
                            for (int p2 = 0; p2 < 15; p2++) {
                                int randX = x + random.nextInt(16);
                                int randY = random.nextInt(64) + 64;
                                int randZ = z + random.nextInt(16);
                                this.generate(world, random, randX, randY, randZ);
                            }
                            return;
                        }
                        for (int p2 = 0; p2 < 15; p2++) {
                            int randX = x + random.nextInt(16);
                            int randY = random.nextInt(64) + 64;
                            int randZ = z + random.nextInt(16);
                            this.generate(world, random, randX, randY, randZ);
                        }
                        return;
                    }
                    for (int p2 = 0; p2 < 15; p2++) {
                        int randX = x + random.nextInt(16);
                        int randY = random.nextInt(64) + 64;
                        int randZ = z + random.nextInt(16);
                        this.generate(world, random, randX, randY, randZ);
                    }
                    return;
                }
                for (int p2 = 0; p2 < 15; p2++) {
                    int randX = x + random.nextInt(16);
                    int randY = random.nextInt(64) + 64;
                    int randZ = z + random.nextInt(16);
                    this.generate(world, random, randX, randY, randZ);
                }
                return;
            }
            for (int p2 = 0; p2 < 15; p2++) {
                int randX = x + random.nextInt(16);
                int randY = random.nextInt(64) + 64;
                int randZ = z + random.nextInt(16);
                this.generate(world, random, randX, randY, randZ);
            }
            return;
        }
        for (int p2 = 0; p2 < 15; p2++) {
            int randX = x + random.nextInt(16);
            int randY = random.nextInt(64) + 64;
            int randZ = z + random.nextInt(16);
            this.generate(world, random, randX, randY, randZ);
        }
        return;
    }
}

package com.sown.outerrim.dimension.kessel;

import java.util.Random;

import com.sown.outerrim.registry.BlockRegister;

import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.MathHelper;
import cpw.mods.fml.common.IWorldGenerator;

public class CustomOreGenerator extends WorldGenerator implements IWorldGenerator {

    private Block block; // The block to generate.
    private int blockMeta; // The metadata of the block.
    private int numberOfBlocks; // Number of blocks in a vein.

    public CustomOreGenerator(Block block, int meta, int number) {
        this.block = block;
        this.blockMeta = meta;
        this.numberOfBlocks = number;
    }

    @Override
    public boolean generate(World world, Random rand, int x, int y, int z) {
        float f = rand.nextFloat() * (float) Math.PI;
        double d0 = ((double)(x + 8) + Math.sin(f) * (double)numberOfBlocks / 8.0D);
        double d1 = ((double)(x + 8) - Math.sin(f) * (double)numberOfBlocks / 8.0D);
        double d2 = ((double)(z + 8) + Math.cos(f) * (double)numberOfBlocks / 8.0D);
        double d3 = ((double)(z + 8) - Math.cos(f) * (double)numberOfBlocks / 8.0D);
        double d4 = (y + rand.nextInt(3) - 2);
        double d5 = (y + rand.nextInt(3) - 2);

        for (int i = 0; i < numberOfBlocks; ++i) {
            float f1 = (float)i / (float)numberOfBlocks;
            double d6 = d0 + (d1 - d0) * (double)f1;
            double d7 = d4 + (d5 - d4) * (double)f1;
            double d8 = d2 + (d3 - d2) * (double)f1;
            double d9 = rand.nextDouble() * (double)numberOfBlocks / 16.0D;
            double d10 = (double)(Math.sin((float)Math.PI * f1) + 1.0F) * d9 + 1.0D;
            double d11 = (double)(Math.sin((float)Math.PI * f1) + 1.0F) * d9 + 1.0D;
            int j = MathHelper.floor_double(d6 - d10 / 2.0D);
            int k = MathHelper.floor_double(d7 - d11 / 2.0D);
            int l = MathHelper.floor_double(d8 - d10 / 2.0D);
            int i1 = MathHelper.floor_double(d6 + d10 / 2.0D);
            int j1 = MathHelper.floor_double(d7 + d11 / 2.0D);
            int k1 = MathHelper.floor_double(d8 + d10 / 2.0D);

            for (int l1 = j; l1 <= i1; ++l1) {
                double d12 = ((double)l1 + 0.5D - d6) / (d10 / 2.0D);
                if (d12 * d12 < 1.0D) {
                    for (int i2 = k; i2 <= j1; ++i2) {
                        double d13 = ((double)i2 + 0.5D - d7) / (d11 / 2.0D);
                        if (d12 * d12 + d13 * d13 < 1.0D) {
                            for (int j2 = l; j2 <= k1; ++j2) {
                                double d14 = ((double)j2 + 0.5D - d8) / (d10 / 2.0D);
                                if (d12 * d12 + d13 * d13 + d14 * d14 < 1.0D && world.getBlock(l1, i2, j2).isReplaceableOreGen(world, l1, i2, j2, Blocks.stone)) {
                                    world.setBlock(l1, i2, j2, this.block, this.blockMeta, 2);
                                }
                            }
                        }
                    }
                }
            }
        }
        return true;
    }

    // Implement the interface's method to inject your ore generation into the world generation
    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
        if (world.provider.dimensionId == 124) { // You can change this to the dimension ID for your dimension
            this.generate(world, random, chunkX * 16, 0, chunkZ * 16);
        }
    }
}

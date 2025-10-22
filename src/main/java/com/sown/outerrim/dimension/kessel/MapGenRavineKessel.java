/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockGrass
 *  net.minecraft.block.BlockLiquid
 *  net.minecraft.init.Blocks
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.World
 *  net.minecraft.world.biome.BiomeGenBase
 *  net.minecraft.world.gen.MapGenBase
 */
package com.sown.outerrim.dimension.kessel;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockGrass;
import net.minecraft.block.BlockLiquid;
import net.minecraft.init.Blocks;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.MapGenBase;

public class MapGenRavineKessel
extends MapGenBase {
    private float[] field_75046_d = new float[1024];

    protected void func_151540_a(long par1, int par3, int par4, Block[] par5ArrayOfBlock, double par6, double par8, double par10, float par12, float par13, float par14, int par15, int par16, double par17) {
        Random random = new Random(par1);
        double d4 = par3 * 16 + 8;
        double d5 = par4 * 16 + 8;
        float f3 = 0.0f;
        float f4 = 0.0f;
        if (par16 <= 0) {
            int j1 = this.range * 16 - 16;
            par16 = j1 - random.nextInt(j1 / 4);
        }
        boolean flag1 = false;
        if (par15 == -1) {
            par15 = par16 / 2;
            flag1 = true;
        }
        float f5 = 1.0f;
        for (int k1 = 0; k1 < 256; ++k1) {
            if (k1 == 0 || random.nextInt(3) == 0) {
                f5 = 1.0f + random.nextFloat() * random.nextFloat() * 1.0f;
            }
            this.field_75046_d[k1] = f5 * f5;
        }
        while (par15 < par16) {
            double d12 = 1.5 + (double)(MathHelper.sin((float)((float)par15 * 3.1415927f / (float)par16)) * par12 * 1.0f);
            double d6 = d12 * par17;
            d12 *= (double)random.nextFloat() * 0.25 + 0.75;
            d6 *= (double)random.nextFloat() * 0.25 + 0.75;
            float f6 = MathHelper.cos((float)par14);
            float f7 = MathHelper.sin((float)par14);
            par6 += (double)(MathHelper.cos((float)par13) * f6);
            par8 += (double)f7;
            par10 += (double)(MathHelper.sin((float)par13) * f6);
            par14 *= 0.7f;
            par14 += f4 * 0.05f;
            par13 += f3 * 0.05f;
            f4 *= 0.8f;
            f3 *= 0.5f;
            f4 += (random.nextFloat() - random.nextFloat()) * random.nextFloat() * 2.0f;
            f3 += (random.nextFloat() - random.nextFloat()) * random.nextFloat() * 4.0f;
            if (flag1 || random.nextInt(4) != 0) {
                double d7 = par6 - d4;
                double d8 = par10 - d5;
                double d9 = par16 - par15;
                double d10 = par12 + 2.0f + 16.0f;
                if (d7 * d7 + d8 * d8 - d9 * d9 > d10 * d10) {
                    return;
                }
                if (par6 >= d4 - 16.0 - d12 * 2.0 && par10 >= d5 - 16.0 - d12 * 2.0 && par6 <= d4 + 16.0 + d12 * 2.0 && par10 <= d5 + 16.0 + d12 * 2.0) {
                    int k2;
                    int j3;
                    int i4 = MathHelper.floor_double((double)(par6 - d12)) - par3 * 16 - 1;
                    int l1 = MathHelper.floor_double((double)(par6 + d12)) - par3 * 16 + 1;
                    int j4 = MathHelper.floor_double((double)(par8 - d6)) - 1;
                    int i2 = MathHelper.floor_double((double)(par8 + d6)) + 1;
                    int k4 = MathHelper.floor_double((double)(par10 - d12)) - par4 * 16 - 1;
                    int j2 = MathHelper.floor_double((double)(par10 + d12)) - par4 * 16 + 1;
                    if (i4 < 0) {
                        i4 = 0;
                    }
                    if (l1 > 16) {
                        l1 = 16;
                    }
                    if (j4 < 1) {
                        j4 = 1;
                    }
                    if (i2 > 248) {
                        i2 = 248;
                    }
                    if (k4 < 0) {
                        k4 = 0;
                    }
                    if (j2 > 16) {
                        j2 = 16;
                    }
                    boolean flag2 = false;
                    for (k2 = i4; !flag2 && k2 < l1; ++k2) {
                        for (int l2 = k4; !flag2 && l2 < j2; ++l2) {
                            for (int i3 = i2 + 1; !flag2 && i3 >= j4 - 1; --i3) {
                                j3 = (k2 * 16 + l2) * 256 + i3;
                                if (i3 < 0 || i3 >= 256) continue;
                                if (this.isOceanBlock(par5ArrayOfBlock, j3, k2, i3, l2, par3, par4)) {
                                    flag2 = true;
                                }
                                if (i3 == j4 - 1 || k2 == i4 || k2 == l1 - 1 || l2 == k4 || l2 == j2 - 1) continue;
                                i3 = j4;
                            }
                        }
                    }
                    if (!flag2) {
                        for (k2 = i4; k2 < l1; ++k2) {
                            double d13 = ((double)(k2 + par3 * 16) + 0.5 - par6) / d12;
                            for (j3 = k4; j3 < j2; ++j3) {
                                double d14 = ((double)(j3 + par4 * 16) + 0.5 - par10) / d12;
                                int k3 = (k2 * 16 + j3) * 256 + i2;
                                boolean flag = false;
                                if (!(d13 * d13 + d14 * d14 < 1.0)) continue;
                                for (int l3 = i2 - 1; l3 >= j4; --l3) {
                                    double d11 = ((double)l3 + 0.5 - par8) / d6;
                                    if ((d13 * d13 + d14 * d14) * (double)this.field_75046_d[l3] + d11 * d11 / 6.0 < 1.0) {
                                        if (this.isTopBlock(par5ArrayOfBlock, k3, k2, l3, j3, par3, par4)) {
                                            flag = true;
                                        }
                                        this.digBlock(par5ArrayOfBlock, k3, k2, l3, j3, par3, par4, flag);
                                    }
                                    --k3;
                                }
                            }
                        }
                        if (flag1) break;
                    }
                }
            }
            ++par15;
        }
    }

    protected void func_151538_a(World par1World, int par2, int par3, int par4, int par5, Block[] par6ArrayOfBlock) {
        if (this.rand.nextInt(50) == 0) {
            double d0 = par2 * 16 + this.rand.nextInt(16);
            double d1 = this.rand.nextInt(this.rand.nextInt(40) + 8) + 20;
            double d2 = par3 * 16 + this.rand.nextInt(16);
            int b0 = 1;
            for (int i1 = 0; i1 < b0; ++i1) {
                float f = this.rand.nextFloat() * 6.2831855f;
                float f1 = (this.rand.nextFloat() - 0.5f) * 2.0f / 8.0f;
                float f2 = (this.rand.nextFloat() * 2.0f + this.rand.nextFloat()) * 2.0f;
                this.func_151540_a(this.rand.nextLong(), par4, par5, par6ArrayOfBlock, d0, d1, d2, f2, f, f1, 0, 0, 3.0);
            }
        }
    }

    protected boolean isOceanBlock(Block[] data, int index, int x, int y, int z, int chunkX, int chunkZ) {
        return data[index] == Blocks.water || data[index] == Blocks.flowing_water;
    }

    private boolean isExceptionBiome(BiomeGenBase biome) {
        if (biome == BiomeGenBase.mushroomIsland) {
            return true;
        }
        if (biome == BiomeGenBase.beach) {
            return true;
        }
        return biome == BiomeGenBase.desert;
    }

    private boolean isTopBlock(Block[] data, int index, int x, int y, int z, int chunkX, int chunkZ) {
        BiomeGenBase biome = this.worldObj.getBiomeGenForCoords(x + chunkX * 16, z + chunkZ * 16);
        return this.isExceptionBiome(biome) ? data[index] == Blocks.grass : data[index] == biome.topBlock;
    }

    protected void digBlock(Block[] data, int index, int x, int y, int z, int chunkX, int chunkZ, boolean foundTop) {
        BiomeGenBase biome = this.worldObj.getBiomeGenForCoords(x + chunkX * 16, z + chunkZ * 16);
        Block top = biome.topBlock;
        Block filler = biome.fillerBlock;
        Block block = data[index];
        if (block == Blocks.stone || block == filler || block == top) {
            if (y < 10) {
                data[index] = Blocks.flowing_lava;
            } else {
                data[index] = null;
                if (foundTop && data[index - 1] == filler) {
                    data[index - 1] = top;
                }
            }
        }
    }
}


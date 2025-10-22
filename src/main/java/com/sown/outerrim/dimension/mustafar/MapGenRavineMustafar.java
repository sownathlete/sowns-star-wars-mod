package com.sown.outerrim.dimension.mustafar;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.gen.MapGenBase;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenLakes;

public class MapGenRavineMustafar extends MapGenBase {

    private float[] field_75046_d = new float[1024];
    private World world;

    public MapGenRavineMustafar() {
        this.world = this.world;
    }

    protected void func_151540_a(long seed, int chunkX, int chunkZ, Block[] blocks, double x, double y, double z, float width, float depth, float scale, int step, int count, double threshold) {
        Random random = new Random(seed);
        double d4 = chunkX * 16 + 8;
        double d5 = chunkZ * 16 + 8;
        float f3 = 0.0f;
        float f4 = 0.0f;

        if (count <= 0) {
            int j1 = this.range * 16 - 16;
            count = j1 - random.nextInt(j1 / 4);
        }

        boolean flag1 = false;

        if (step == -1) {
            step = count / 2;
            flag1 = true;
        }

        float f5 = 1.0f;

        for (int k1 = 0; k1 < 256; ++k1) {
            if (k1 == 0 || random.nextInt(3) == 0) {
                f5 = 1.0f + random.nextFloat() * random.nextFloat() * 1.0f;
            }
            this.field_75046_d[k1] = f5 * f5;
        }

        for (; step < count; ++step) {
            double d12 = 1.5 + MathHelper.sin(step * 3.1415927f / count) * width * 1.0f;
            double d6 = d12 * threshold;
            d12 *= random.nextFloat() * 0.25 + 0.75;
            d6 *= random.nextFloat() * 0.25 + 0.75;

            float f6 = MathHelper.cos(scale);
            float f7 = MathHelper.sin(scale);
            x += MathHelper.cos(depth) * f6;
            y += f7;
            z += MathHelper.sin(depth) * f6;
            scale *= 0.7f;
            scale += f4 * 0.05f;
            depth += f3 * 0.05f;
            f4 *= 0.8f;
            f3 *= 0.5f;
            f4 += (random.nextFloat() - random.nextFloat()) * random.nextFloat() * 2.0f;
            f3 += (random.nextFloat() - random.nextFloat()) * random.nextFloat() * 4.0f;

            if (flag1 || random.nextInt(4) != 0) {
                double d7 = x - d4;
                double d8 = z - d5;
                double d9 = count - step;
                double d10 = width + 2.0f + 16.0f;

                if (d7 * d7 + d8 * d8 - d9 * d9 > d10 * d10)
                    return;

                if (x >= d4 - 16.0 - d12 * 2.0 && z >= d5 - 16.0 - d12 * 2.0 && x <= d4 + 16.0 + d12 * 2.0 && z <= d5 + 16.0 + d12 * 2.0) {
                    int l4 = MathHelper.floor_double(x - d12) - chunkX * 16 - 1;
                    int j5 = MathHelper.floor_double(x + d12) - chunkX * 16 + 1;
                    int l5 = MathHelper.floor_double(y - d6) - 1;
                    int k6 = MathHelper.floor_double(y + d6) + 1;
                    int i7 = MathHelper.floor_double(z - d12) - chunkZ * 16 - 1;
                    int l6 = MathHelper.floor_double(z + d12) - chunkZ * 16 + 1;

                    if (l4 < 0) {
                        l4 = 0;
                    }

                    if (j5 > 16) {
                        j5 = 16;
                    }

                    if (l5 < 1) {
                        l5 = 1;
                    }

                    if (k6 > 248) {
                        k6 = 248;
                    }

                    if (i7 < 0) {
                        i7 = 0;
                    }

                    if (l6 > 16) {
                        l6 = 16;
                    }

                    boolean flag2 = false;

                    for (int j2 = l4; !flag2 && j2 < j5; ++j2) {
                        for (int k2 = i7; !flag2 && k2 < l6; ++k2) {
                            for (int l2 = k6 + 1; !flag2 && l2 >= l5 - 1; --l2) {
                                int i3 = (j2 * 16 + k2) * 256 + l2;

                                if (l2 < 0 || l2 >= 256) {
                                    continue;
                                }

                                if (this.isOceanBlock(blocks, i3, j2, l2, k2, chunkX, chunkZ)) {
                                    flag2 = true;
                                }

                                if (l2 == l5 - 1 || j2 == l4 || j2 == j5 - 1 || k2 == i7 || k2 == l6 - 1) {
                                    continue;
                                }

                                l2 = l5;
                            }
                        }
                    }

                    if (!flag2) {
                        for (int j3 = l4; j3 < j5; ++j3) {
                            double d13 = ((j3 + chunkX * 16) + 0.5 - x) / d12;

                            for (int i4 = i7; i4 < l6; ++i4) {
                                double d14 = ((i4 + chunkZ * 16) + 0.5 - z) / d12;
                                int j4 = (j3 * 16 + i4) * 256 + k6;
                                boolean flag3 = false;

                                if ((d13 * d13 + d14 * d14) / 6.0 < 1.0) {
                                    for (int k5 = k6 - 1; k5 >= l5; --k5) {
                                        double d11 = ((k5 + 0.5 - y) - d6) / d6;

                                        if ((d13 * d13 + d14 * d14) * this.field_75046_d[k5] + d11 * d11 / 6.0 < 1.0) {
                                            Block block = blocks[j4];

                                            if (block == Blocks.stone || block == Blocks.grass || block == Blocks.dirt) {
                                                flag3 = true;
                                            }

                                            if (block == Blocks.water || block == Blocks.flowing_water) {
                                                if (k5 < 10) {
                                                    blocks[j4] = Blocks.flowing_lava;
                                                } else {
                                                    blocks[j4] = null;

                                                    if (flag3 && blocks[j4 - 1] == Blocks.dirt) {
                                                        blocks[j4 - 1] = Blocks.grass;
                                                    }
                                                }
                                            }
                                        }

                                        --j4;
                                    }
                                }
                            }
                        }

                        if (flag1) {
                            break;
                        }
                    }
                }
            }
        }
    }

    private boolean isOceanBlock(Block[] blocks, int index, int x, int y, int z, int chunkX, int chunkZ) {
        return blocks[index] == Blocks.water || blocks[index] == Blocks.flowing_water;
    }

    private boolean isTopBlock(Block[] blocks, int index, int x, int y, int z, int chunkX, int chunkZ) {
        Block block = blocks[index];
        Block top = this.world.getBiomeGenForCoords(x + chunkX * 16, z + chunkZ * 16).topBlock;
        return block == top;
    }

    @Override
    protected void func_151538_a(World world, int chunkX, int chunkZ, int p_151538_4_, int p_151538_5_, Block[] blocks) {
        int i = this.rand.nextInt(this.rand.nextInt(this.rand.nextInt(10) + 1) + 1);

        if (this.rand.nextInt(5) != 0) {
            i = 0;
        }

        for (int j = 0; j < i; ++j) {
            double x = chunkX * 16 + this.rand.nextInt(16);
            double y = this.rand.nextInt(this.rand.nextInt(120) + 8);
            double z = chunkZ * 16 + this.rand.nextInt(16);
            int k = 1;

            if (this.rand.nextInt(4) == 0) {
                this.func_151540_a(this.rand.nextLong(), p_151538_4_, p_151538_5_, blocks, x, y, z, 1.0f + this.rand.nextFloat() * 6.0f, 0.0f, 0.0f, -1, -1, 0.5);
                k += this.rand.nextInt(4);
            }

            for (int l = 0; l < k; ++l) {
                float f = this.rand.nextFloat() * 3.1415927f * 2.0f;
                float f1 = (this.rand.nextFloat() - 0.5f) * 2.0f / 8.0f;
                float f2 = this.rand.nextFloat() * 2.0f + this.rand.nextFloat();

                if (this.rand.nextInt(10) == 0) {
                    f2 *= this.rand.nextFloat() * this.rand.nextFloat() * 3.0f + 1.0f;
                }

                this.func_151540_a(this.rand.nextLong(), p_151538_4_, p_151538_5_, blocks, x, y, z, f2, f, f1, 0, 0, 1.0);
            }
        }
    }

    public void generate(IChunkProvider chunkProvider, World world, int chunkX, int chunkZ, Block[] blocks) {
        int blockX = chunkX * 16;
        int blockZ = chunkZ * 16;

        this.rand.setSeed(world.getSeed());
        long l1 = this.rand.nextLong() / 2L * 2L + 1L;
        long l2 = this.rand.nextLong() / 2L * 2L + 1L;
        this.rand.setSeed(chunkX * l1 + chunkZ * l2 ^ world.getSeed());

        this.func_151538_a(world, chunkX, chunkZ, blockX, blockZ, blocks);
    }
}
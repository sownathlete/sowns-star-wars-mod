package com.sown.outerrim.dimension.endor;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;

import java.util.Random;

public class WorldGenTallTaiga2 extends WorldGenAbstractTree {
    private static final Block logBlock = Blocks.log;
    private static final Block leafBlock = Blocks.leaves;

    public WorldGenTallTaiga2(boolean doBlockNotify) {
        super(doBlockNotify);
    }

    @Override
    public boolean generate(World world, Random rand, int x, int y, int z) {
        int height = rand.nextInt(4) + 6; // Base height
        height *= 3; // Make it 3 times taller

        if (y >= 1 && y + height + 1 <= 256) {
            boolean flag = true;

            for (int y1 = y; y1 <= y + 1 + height; ++y1) {
                byte b0 = 1;

                if (y1 == y) {
                    b0 = 0;
                }

                if (y1 >= y + 1 + height - 2) {
                    b0 = 2;
                }

                for (int x1 = x - b0; x1 <= x + b0 && flag; ++x1) {
                    for (int z1 = z - b0; z1 <= z + b0 && flag; ++z1) {
                        if (y1 >= 0 && y1 < 256) {
                            Block block = world.getBlock(x1, y1, z1);

                            if (!this.isReplaceable(world, x1, y1, z1)) {
                                flag = false;
                            }
                        } else {
                            flag = false;
                        }
                    }
                }
            }

            if (!flag) {
                return false;
            } else {
                Block block2 = world.getBlock(x, y - 1, z);

                if ((block2 == Blocks.grass || block2 == Blocks.dirt) && y < 256 - height - 1) {
                    this.func_150515_a(world, x, y - 1, z, Blocks.dirt);
                    int k2 = rand.nextInt(2);
                    int l2 = 1;
                    byte b1 = 0;

                    for (int y1 = 0; y1 <= height; ++y1) {
                        int j3 = y + height - y1;

                        for (int x1 = x - k2; x1 <= x + k2; ++x1) {
                            int l1 = x1 - x;

                            for (int z1 = z - k2; z1 <= z + k2; ++z1) {
                                int i2 = z1 - z;

                                if (Math.abs(l1) != k2 || Math.abs(i2) != k2 || k2 <= 0) {
                                    Block block1 = world.getBlock(x1, j3, z1);

                                    if (block1.isAir(world, x1, j3, z1) || block1.isLeaves(world, x1, j3, z1)) {
                                        this.setBlockAndNotifyAdequately(world, x1, j3, z1, leafBlock, 1);
                                    }
                                }
                            }
                        }

                        if (k2 >= l2) {
                            k2 = b1;
                            b1 = 1;
                            ++l2;

                            if (l2 > k2) {
                                l2 = k2;
                            }
                        } else {
                            ++k2;
                        }
                    }

                    int j2 = rand.nextInt(3);

                    for (int y1 = 0; y1 < height - j2; ++y1) {
                        Block block3 = world.getBlock(x, y + y1, z);

                        if (block3.isAir(world, x, y + y1, z) || block3.isLeaves(world, x, y + y1, z)) {
                            this.setBlockAndNotifyAdequately(world, x, y + y1, z, logBlock, 1);
                        }
                    }

                    return true;
                } else {
                    return false;
                }
            }
        } else {
            return false;
        }
    }
}

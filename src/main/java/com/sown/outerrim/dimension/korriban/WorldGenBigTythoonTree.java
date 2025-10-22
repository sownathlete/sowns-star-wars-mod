/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockLeaves
 *  net.minecraft.block.BlockSapling
 *  net.minecraft.init.Blocks
 *  net.minecraft.util.Direction
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 *  net.minecraft.world.gen.feature.WorldGenAbstractTree
 *  net.minecraftforge.common.IPlantable
 *  net.minecraftforge.common.util.ForgeDirection
 */
package com.sown.outerrim.dimension.korriban;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockSapling;
import net.minecraft.init.Blocks;
import net.minecraft.util.Direction;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.util.ForgeDirection;

public class WorldGenBigTythoonTree
extends WorldGenAbstractTree {
    public WorldGenBigTythoonTree(boolean doBlockNotify) {
        super(doBlockNotify);
    }

    public boolean generate(World world, Random rand, int x, int y, int z) {
        int l = rand.nextInt(3) + rand.nextInt(2) + 8;
        boolean flag = true;
        if (y >= 1 && y + l + 1 <= 256) {
            int j1;
            int k1;
            for (int i1 = y; i1 <= y + 1 + l; ++i1) {
                int b0 = 1;
                if (i1 == y) {
                    b0 = 0;
                }
                if (i1 >= y + 1 + l - 2) {
                    b0 = 2;
                }
                for (j1 = x - b0; j1 <= x + b0 && flag; ++j1) {
                    for (k1 = z - b0; k1 <= z + b0 && flag; ++k1) {
                        if (i1 >= 0 && i1 < 256) {
                            Block block = world.getBlock(j1, i1, k1);
                            if (this.isReplaceable(world, j1, i1, k1)) continue;
                            flag = false;
                            continue;
                        }
                        flag = false;
                    }
                }
            }
            if (!flag) {
                return false;
            }
            Block block2 = world.getBlock(x, y - 1, z);
            boolean isSoil = block2.canSustainPlant((IBlockAccess)world, x, y - 1, z, ForgeDirection.UP, (IPlantable)((BlockSapling)Blocks.sapling));
            if (isSoil && y < 256 - l - 1) {
                int j2;
                int k2;
                this.onPlantGrow(world, x, y - 1, z, x, y, z);
                this.onPlantGrow(world, x + 1, y - 1, z, x, y, z);
                this.onPlantGrow(world, x + 1, y - 1, z + 1, x, y, z);
                this.onPlantGrow(world, x, y - 1, z + 1, x, y, z);
                int j3 = rand.nextInt(4);
                j1 = l - rand.nextInt(4);
                k1 = 2 - rand.nextInt(3);
                int k3 = x;
                int l1 = z;
                int i2 = 0;
                for (j2 = 0; j2 < l; ++j2) {
                    Block block1;
                    k2 = y + j2;
                    if (j2 >= j1 && k1 > 0) {
                        k3 += Direction.offsetX[j3];
                        l1 += Direction.offsetZ[j3];
                        --k1;
                    }
                    if (!(block1 = world.getBlock(k3, k2, l1)).isAir((IBlockAccess)world, k3, k2, l1) && !block1.isLeaves((IBlockAccess)world, k3, k2, l1)) continue;
                    this.setBlockAndNotifyAdequately(world, k3, k2, l1, Blocks.log2, 1);
                    this.setBlockAndNotifyAdequately(world, k3 + 1, k2, l1, Blocks.log2, 1);
                    this.setBlockAndNotifyAdequately(world, k3, k2, l1 + 1, Blocks.log2, 1);
                    this.setBlockAndNotifyAdequately(world, k3 + 1, k2, l1 + 1, Blocks.log2, 1);
                    i2 = k2;
                }
                for (j2 = -2; j2 <= 0; ++j2) {
                    for (k2 = -2; k2 <= 0; ++k2) {
                        int b1 = -1;
                        this.leaves(world, k3 + j2, i2 + b1, l1 + k2);
                        this.leaves(world, 1 + k3 - j2, i2 + b1, l1 + k2);
                        this.leaves(world, k3 + j2, i2 + b1, 1 + l1 - k2);
                        this.leaves(world, 1 + k3 - j2, i2 + b1, 1 + l1 - k2);
                        if (j2 <= -2 && k2 <= -1 || j2 == -1 && k2 == -2) continue;
                        int b2 = 1;
                        this.leaves(world, k3 + j2, i2 + b2, l1 + k2);
                        this.leaves(world, 1 + k3 - j2, i2 + b2, l1 + k2);
                        this.leaves(world, k3 + j2, i2 + b2, 1 + l1 - k2);
                        this.leaves(world, 1 + k3 - j2, i2 + b2, 1 + l1 - k2);
                    }
                }
                if (rand.nextBoolean()) {
                    this.leaves(world, k3, i2 + 2, l1);
                    this.leaves(world, k3 + 1, i2 + 2, l1);
                    this.leaves(world, k3 + 1, i2 + 2, l1 + 1);
                    this.leaves(world, k3, i2 + 2, l1 + 1);
                }
                for (j2 = -3; j2 <= 4; ++j2) {
                    for (k2 = -3; k2 <= 4; ++k2) {
                        if (j2 == -3 && k2 == -3 || j2 == -3 && k2 == 4 || j2 == 4 && k2 == -3 || j2 == 4 && k2 == 4 || Math.abs(j2) >= 3 && Math.abs(k2) >= 3) continue;
                        this.leaves(world, k3 + j2, i2, l1 + k2);
                    }
                }
                for (j2 = -1; j2 <= 2; ++j2) {
                    for (k2 = -1; k2 <= 2; ++k2) {
                        int i3;
                        int l2;
                        if (j2 >= 0 && j2 <= 1 && k2 >= 0 && k2 <= 1 || rand.nextInt(3) > 0) continue;
                        int l3 = rand.nextInt(3) + 2;
                        for (l2 = 0; l2 < l3; ++l2) {
                            this.setBlockAndNotifyAdequately(world, x + j2, i2 - l2 - 1, z + k2, Blocks.log2, 1);
                        }
                        for (l2 = -1; l2 <= 1; ++l2) {
                            for (i3 = -1; i3 <= 1; ++i3) {
                                this.leaves(world, k3 + j2 + l2, i2 - 0, l1 + k2 + i3);
                            }
                        }
                        for (l2 = -2; l2 <= 2; ++l2) {
                            for (i3 = -2; i3 <= 2; ++i3) {
                                if (Math.abs(l2) == 2 && Math.abs(i3) == 2) continue;
                                this.leaves(world, k3 + j2 + l2, i2 - 1, l1 + k2 + i3);
                            }
                        }
                    }
                }
                return true;
            }
            return false;
        }
        return false;
    }

    private void leaves(World world, int x, int y, int z) {
        Block block = world.getBlock(x, y, z);
        if (block.isAir((IBlockAccess)world, x, y, z)) {
            this.setBlockAndNotifyAdequately(world, x, y, z, (Block)Blocks.leaves2, 1);
        }
    }

    private void onPlantGrow(World world, int x, int y, int z, int sourceX, int sourceY, int sourceZ) {
        world.getBlock(x, y, z).onPlantGrow(world, x, y, z, sourceX, sourceY, sourceZ);
    }
}


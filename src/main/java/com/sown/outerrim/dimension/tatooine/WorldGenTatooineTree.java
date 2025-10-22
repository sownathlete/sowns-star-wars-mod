/*
 * Decompiled with CFR 0.148.
 *
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.init.Blocks
 *  net.minecraft.util.Direction
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 *  net.minecraft.world.gen.feature.WorldGenAbstractTree
 *  net.minecraftforge.common.IPlantable
 *  net.minecraftforge.common.util.ForgeDirection
 */
package com.sown.outerrim.dimension.tatooine;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSapling;
import net.minecraft.init.Blocks;
import net.minecraft.util.Direction;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.util.ForgeDirection;

public class WorldGenTatooineTree
extends WorldGenAbstractTree {
    private int minTreeHeight;
    private boolean vinesGrow;
    private int metaWood;
    private int metaLeaves;
    private Block sapling;
    private Block log;
    private Block leaf;

    public WorldGenTatooineTree(boolean par1, int par2, int par3, int par4, boolean par5, Block sapling, Block log, Block leaf) {
        super(false);
        this.minTreeHeight = par2;
        this.metaWood = par3;
        this.metaLeaves = par4;
        this.vinesGrow = false;
        this.sapling = sapling;
        this.log = log;
        this.leaf = leaf;
    }

    @Override
    public boolean generate(World par1World, Random par2Random, int par3, int par4, int par5) {
        int l = par2Random.nextInt(3) + this.minTreeHeight;
        boolean flag = true;

        if (par4 >= 1 && par4 + l + 1 <= 256) {
            for (int i1 = par4; i1 <= par4 + 1 + l; ++i1) {
                int b0 = (i1 == par4) ? 0 : (i1 >= par4 + 1 + l - 2 ? 2 : 1);
                for (int j1 = par3 - b0; j1 <= par3 + b0 && flag; ++j1) {
                    for (int k1 = par5 - b0; k1 <= par5 + b0 && flag; ++k1) {
                        if (i1 >= 0 && i1 < 256) {
                            Block block = par1World.getBlock(j1, i1, k1);
                            if (this.isReplaceable(par1World, j1, i1, k1)) continue;
                            flag = false;
                        } else {
                            flag = false;
                        }
                    }
                }
            }

            if (!flag) return false;

            // Get the block under the tree
            Block blockUnder = par1World.getBlock(par3, par4 - 1, par5);
            boolean isSoil = blockUnder.canSustainPlant(par1World, par3, par4 - 1, par5, ForgeDirection.UP, (BlockSapling) this.sapling);

            if (isSoil && par4 < 256 - l - 1) {
                // Set block under the tree to grass
                par1World.setBlock(par3, par4 - 1, par5, Blocks.grass, 0, 2);

                // Continue with tree generation
                blockUnder.onPlantGrow(par1World, par3, par4 - 1, par5, par3, par4, par5);
                int b0 = 3;
                int b1 = 0;

                for (int k1 = par4 - b0 + l; k1 <= par4 + l; ++k1) {
                    int i3 = k1 - (par4 + l);
                    int l1 = b1 + 1 - i3 / 2;

                    for (int i2 = par3 - l1; i2 <= par3 + l1; ++i2) {
                        int j2 = i2 - par3;
                        for (int k2 = par5 - l1; k2 <= par5 + l1; ++k2) {
                            Block block1 = par1World.getBlock(i2, k1, k2);
                            int l2 = k2 - par5;

                            if (Math.abs(j2) == l1 && Math.abs(l2) == l1 && (par2Random.nextInt(2) == 0 || i3 == 0) || 
                                !(block1.isAir(par1World, i2, k1, k2) || block1.isLeaves(par1World, i2, k1, k2))) {
                                continue;
                            }

                            this.setBlockAndNotifyAdequately(par1World, i2, k1, k2, this.leaf, this.metaLeaves);
                        }
                    }
                }

                for (int k1 = 0; k1 < l; ++k1) {
                    Block block = par1World.getBlock(par3, par4 + k1, par5);
                    if (!block.isAir(par1World, par3, par4 + k1, par5) && !block.isLeaves(par1World, par3, par4 + k1, par5)) {
                        continue;
                    }
                    this.setBlockAndNotifyAdequately(par1World, par3, par4 + k1, par5, this.log, this.metaWood);
                }

                return true;
            }
        }
        return false;
    }

    private void growVines(World par1World, int par2, int par3, int par4, int par5) {
        this.setBlockAndNotifyAdequately(par1World, par2, par3, par4, Blocks.vine, par5);
        int i1 = 4;
        while (!par1World.getBlock(par2, --par3, par4).isAir(par1World, par2, par3, par4) && i1 > 0) {
            this.setBlockAndNotifyAdequately(par1World, par2, par3, par4, Blocks.vine, par5);
            --i1;
        }
        return;
    }
}


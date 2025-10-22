/*
 * Decompiled with CFR 0.148.
 *
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockGrass
 *  net.minecraft.block.BlockMycelium
 *  net.minecraft.block.material.Material
 *  net.minecraft.init.Blocks
 *  net.minecraft.world.EnumSkyBlock
 *  net.minecraft.world.World
 *  net.minecraft.world.biome.BiomeGenBase
 *  net.minecraft.world.gen.feature.WorldGenerator
 */
package com.sown.util.world.creation.worldgen;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockGrass;
import net.minecraft.block.BlockMycelium;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenOuterRimLake2
extends WorldGenerator {
    private Block field_150556_a;

    public WorldGenOuterRimLake2(Block p_i45455_1_) {
        this.field_150556_a = p_i45455_1_;
    }

    public boolean generate(World p_76484_1_, Random p_76484_2_, int p_76484_3_, int p_76484_4_, int p_76484_5_, Block block) {
        int i1;
        int j2;
        while (p_76484_4_ > 5 && p_76484_1_.isAirBlock(p_76484_3_ -= 8, p_76484_4_, p_76484_5_ -= 8)) {
            --p_76484_4_;
        }
        if (p_76484_4_ <= 4)
            return false;
        p_76484_4_ -= 4;
        boolean[] aboolean = new boolean[2048];
        int l = p_76484_2_.nextInt(4) + 4;
        for (i1 = 0; i1 < l; ++i1) {
            double d0 = p_76484_2_.nextDouble() * 6.0 + 3.0;
            double d1 = p_76484_2_.nextDouble() * 4.0 + 2.0;
            double d2 = p_76484_2_.nextDouble() * 6.0 + 3.0;
            double d3 = p_76484_2_.nextDouble() * (16.0 - d0 - 2.0) + 1.0 + d0 / 2.0;
            double d4 = p_76484_2_.nextDouble() * (8.0 - d1 - 4.0) + 2.0 + d1 / 2.0;
            double d5 = p_76484_2_.nextDouble() * (16.0 - d2 - 2.0) + 1.0 + d2 / 2.0;
            for (int k1 = 1; k1 < 15; ++k1) {
                for (int l1 = 1; l1 < 15; ++l1) {
                    for (int i2 = 1; i2 < 7; ++i2) {
                        double d6 = (k1 - d3) / (d0 / 2.0);
                        double d7 = (i2 - d4) / (d1 / 2.0);
                        double d8 = (l1 - d5) / (d2 / 2.0);
                        double d9 = d6 * d6 + d7 * d7 + d8 * d8;
                        if (!(d9 < 1.0)) {
                            continue;
                        }
                        aboolean[(k1 * 16 + l1) * 8 + i2] = true;
                    }
                }
            }
        }
        for (i1 = 0; i1 < 16; ++i1) {
            for (j2 = 0; j2 < 16; ++j2) {
                for (int j1 = 0; j1 < 8; ++j1) {
                    boolean flag;
                    boolean bl = flag = !aboolean[(i1 * 16 + j2) * 8 + j1] && (i1 < 15 && aboolean[((i1 + 1) * 16 + j2) * 8 + j1] || i1 > 0 && aboolean[((i1 - 1) * 16 + j2) * 8 + j1] || j2 < 15 && aboolean[(i1 * 16 + j2 + 1) * 8 + j1] || j2 > 0 && aboolean[(i1 * 16 + (j2 - 1)) * 8 + j1] || j1 < 7 && aboolean[(i1 * 16 + j2) * 8 + j1 + 1] || j1 > 0 && aboolean[(i1 * 16 + j2) * 8 + (j1 - 1)]);
                    if (!flag) {
                        continue;
                    }
                    Material material = p_76484_1_.getBlock(p_76484_3_ + i1, p_76484_4_ + j1, p_76484_5_ + j2).getMaterial();
                    if (j1 >= 4 && material.isLiquid())
                        return false;
                    if (j1 >= 4 || material.isSolid() || p_76484_1_.getBlock(p_76484_3_ + i1, p_76484_4_ + j1, p_76484_5_ + j2) == this.field_150556_a) {
                        continue;
                    }
                    return false;
                }
            }
        }
        for (i1 = 0; i1 < 16; ++i1) {
            for (j2 = 0; j2 < 16; ++j2) {
                for (int j1 = 0; j1 < 8; ++j1) {
                    if (!aboolean[(i1 * 16 + j2) * 8 + j1]) {
                        continue;
                    }
                    p_76484_1_.setBlock(p_76484_3_ + i1, p_76484_4_ + j1, p_76484_5_ + j2, j1 >= 4 ? Blocks.air : this.field_150556_a, 0, 2);
                }
            }
        }
        for (i1 = 0; i1 < 16; ++i1) {
            for (j2 = 0; j2 < 16; ++j2) {
                for (int j1 = 4; j1 < 8; ++j1) {
                    if (!aboolean[(i1 * 16 + j2) * 8 + j1] || p_76484_1_.getBlock(p_76484_3_ + i1, p_76484_4_ + j1 - 1, p_76484_5_ + j2) != Blocks.dirt || p_76484_1_.getSavedLightValue(EnumSkyBlock.Sky, p_76484_3_ + i1, p_76484_4_ + j1, p_76484_5_ + j2) <= 0) {
                        continue;
                    }
                    BiomeGenBase biomegenbase = p_76484_1_.getBiomeGenForCoords(p_76484_3_ + i1, p_76484_5_ + j2);
                    if (biomegenbase.topBlock == Blocks.mycelium) {
                        p_76484_1_.setBlock(p_76484_3_ + i1, p_76484_4_ + j1 - 1, p_76484_5_ + j2, Blocks.mycelium, 0, 2);
                        continue;
                    }
                    p_76484_1_.setBlock(p_76484_3_ + i1, p_76484_4_ + j1 - 1, p_76484_5_ + j2, Blocks.grass, 0, 2);
                }
            }
        }
        if (this.field_150556_a.getMaterial() == Material.lava) {
            for (i1 = 0; i1 < 16; ++i1) {
                for (j2 = 0; j2 < 16; ++j2) {
                    for (int j1 = 0; j1 < 8; ++j1) {
                        boolean flag;
                        boolean bl = flag = !aboolean[(i1 * 16 + j2) * 8 + j1] && (i1 < 15 && aboolean[((i1 + 1) * 16 + j2) * 8 + j1] || i1 > 0 && aboolean[((i1 - 1) * 16 + j2) * 8 + j1] || j2 < 15 && aboolean[(i1 * 16 + j2 + 1) * 8 + j1] || j2 > 0 && aboolean[(i1 * 16 + (j2 - 1)) * 8 + j1] || j1 < 7 && aboolean[(i1 * 16 + j2) * 8 + j1 + 1] || j1 > 0 && aboolean[(i1 * 16 + j2) * 8 + (j1 - 1)]);
                        if (!flag || j1 >= 4 && p_76484_2_.nextInt(2) == 0 || !p_76484_1_.getBlock(p_76484_3_ + i1, p_76484_4_ + j1, p_76484_5_ + j2).getMaterial().isSolid()) {
                            continue;
                        }
                        p_76484_1_.setBlock(p_76484_3_ + i1, p_76484_4_ + j1, p_76484_5_ + j2, block, 0, 2);
                    }
                }
            }
        }
        if (this.field_150556_a.getMaterial() == Material.water) {
            for (i1 = 0; i1 < 16; ++i1) {
                for (j2 = 0; j2 < 16; ++j2) {
                    int b0 = 4;
                    if (!p_76484_1_.isBlockFreezable(p_76484_3_ + i1, p_76484_4_ + b0, p_76484_5_ + j2)) {
                        continue;
                    }
                    p_76484_1_.setBlock(p_76484_3_ + i1, p_76484_4_ + b0, p_76484_5_ + j2, Blocks.ice, 0, 2);
                }
            }
        }
        return true;
    }

    @Override
    public boolean generate(World p_76484_1_, Random p_76484_2_, int p_76484_3_, int p_76484_4_, int p_76484_5_) {
        int i1;
        int j2;
        while (p_76484_4_ > 5 && p_76484_1_.isAirBlock(p_76484_3_ -= 8, p_76484_4_, p_76484_5_ -= 8)) {
            --p_76484_4_;
        }
        if (p_76484_4_ <= 4)
            return false;
        p_76484_4_ -= 4;
        boolean[] aboolean = new boolean[2048];
        int l = p_76484_2_.nextInt(4) + 4;
        for (i1 = 0; i1 < l; ++i1) {
            double d0 = p_76484_2_.nextDouble() * 6.0 + 3.0;
            double d1 = p_76484_2_.nextDouble() * 4.0 + 2.0;
            double d2 = p_76484_2_.nextDouble() * 6.0 + 3.0;
            double d3 = p_76484_2_.nextDouble() * (16.0 - d0 - 2.0) + 1.0 + d0 / 2.0;
            double d4 = p_76484_2_.nextDouble() * (8.0 - d1 - 4.0) + 2.0 + d1 / 2.0;
            double d5 = p_76484_2_.nextDouble() * (16.0 - d2 - 2.0) + 1.0 + d2 / 2.0;
            for (int k1 = 1; k1 < 15; ++k1) {
                for (int l1 = 1; l1 < 15; ++l1) {
                    for (int i2 = 1; i2 < 7; ++i2) {
                        double d6 = (k1 - d3) / (d0 / 2.0);
                        double d7 = (i2 - d4) / (d1 / 2.0);
                        double d8 = (l1 - d5) / (d2 / 2.0);
                        double d9 = d6 * d6 + d7 * d7 + d8 * d8;
                        if (!(d9 < 1.0)) {
                            continue;
                        }
                        aboolean[(k1 * 16 + l1) * 8 + i2] = true;
                    }
                }
            }
        }
        for (i1 = 0; i1 < 16; ++i1) {
            for (j2 = 0; j2 < 16; ++j2) {
                for (int j1 = 0; j1 < 8; ++j1) {
                    boolean flag;
                    boolean bl = flag = !aboolean[(i1 * 16 + j2) * 8 + j1] && (i1 < 15 && aboolean[((i1 + 1) * 16 + j2) * 8 + j1] || i1 > 0 && aboolean[((i1 - 1) * 16 + j2) * 8 + j1] || j2 < 15 && aboolean[(i1 * 16 + j2 + 1) * 8 + j1] || j2 > 0 && aboolean[(i1 * 16 + (j2 - 1)) * 8 + j1] || j1 < 7 && aboolean[(i1 * 16 + j2) * 8 + j1 + 1] || j1 > 0 && aboolean[(i1 * 16 + j2) * 8 + (j1 - 1)]);
                    if (!flag) {
                        continue;
                    }
                    Material material = p_76484_1_.getBlock(p_76484_3_ + i1, p_76484_4_ + j1, p_76484_5_ + j2).getMaterial();
                    if (j1 >= 4 && material.isLiquid())
                        return false;
                    if (j1 >= 4 || material.isSolid() || p_76484_1_.getBlock(p_76484_3_ + i1, p_76484_4_ + j1, p_76484_5_ + j2) == this.field_150556_a) {
                        continue;
                    }
                    return false;
                }
            }
        }
        for (i1 = 0; i1 < 16; ++i1) {
            for (j2 = 0; j2 < 16; ++j2) {
                for (int j1 = 0; j1 < 8; ++j1) {
                    if (!aboolean[(i1 * 16 + j2) * 8 + j1]) {
                        continue;
                    }
                    p_76484_1_.setBlock(p_76484_3_ + i1, p_76484_4_ + j1, p_76484_5_ + j2, j1 >= 4 ? Blocks.air : this.field_150556_a, 0, 2);
                }
            }
        }
        for (i1 = 0; i1 < 16; ++i1) {
            for (j2 = 0; j2 < 16; ++j2) {
                for (int j1 = 4; j1 < 8; ++j1) {
                    if (!aboolean[(i1 * 16 + j2) * 8 + j1] || p_76484_1_.getBlock(p_76484_3_ + i1, p_76484_4_ + j1 - 1, p_76484_5_ + j2) != Blocks.dirt || p_76484_1_.getSavedLightValue(EnumSkyBlock.Sky, p_76484_3_ + i1, p_76484_4_ + j1, p_76484_5_ + j2) <= 0) {
                        continue;
                    }
                    BiomeGenBase biomegenbase = p_76484_1_.getBiomeGenForCoords(p_76484_3_ + i1, p_76484_5_ + j2);
                    if (biomegenbase.topBlock == Blocks.mycelium) {
                        p_76484_1_.setBlock(p_76484_3_ + i1, p_76484_4_ + j1 - 1, p_76484_5_ + j2, Blocks.mycelium, 0, 2);
                        continue;
                    }
                    p_76484_1_.setBlock(p_76484_3_ + i1, p_76484_4_ + j1 - 1, p_76484_5_ + j2, Blocks.grass, 0, 2);
                }
            }
        }
        if (this.field_150556_a.getMaterial() == Material.lava) {
            for (i1 = 0; i1 < 16; ++i1) {
                for (j2 = 0; j2 < 16; ++j2) {
                    for (int j1 = 0; j1 < 8; ++j1) {
                        boolean flag;
                        boolean bl = flag = !aboolean[(i1 * 16 + j2) * 8 + j1] && (i1 < 15 && aboolean[((i1 + 1) * 16 + j2) * 8 + j1] || i1 > 0 && aboolean[((i1 - 1) * 16 + j2) * 8 + j1] || j2 < 15 && aboolean[(i1 * 16 + j2 + 1) * 8 + j1] || j2 > 0 && aboolean[(i1 * 16 + (j2 - 1)) * 8 + j1] || j1 < 7 && aboolean[(i1 * 16 + j2) * 8 + j1 + 1] || j1 > 0 && aboolean[(i1 * 16 + j2) * 8 + (j1 - 1)]);
                        if (!flag || j1 >= 4 && p_76484_2_.nextInt(2) == 0 || !p_76484_1_.getBlock(p_76484_3_ + i1, p_76484_4_ + j1, p_76484_5_ + j2).getMaterial().isSolid()) {
                            continue;
                        }
                        p_76484_1_.setBlock(p_76484_3_ + i1, p_76484_4_ + j1, p_76484_5_ + j2, Blocks.stone, 0, 2);
                    }
                }
            }
        }
        if (this.field_150556_a.getMaterial() == Material.water) {
            for (i1 = 0; i1 < 16; ++i1) {
                for (j2 = 0; j2 < 16; ++j2) {
                    int b0 = 4;
                    if (!p_76484_1_.isBlockFreezable(p_76484_3_ + i1, p_76484_4_ + b0, p_76484_5_ + j2)) {
                        continue;
                    }
                    p_76484_1_.setBlock(p_76484_3_ + i1, p_76484_4_ + b0, p_76484_5_ + j2, Blocks.ice, 0, 2);
                }
            }
        }
        return true;
    }
}


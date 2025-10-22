/*
 * Decompiled with CFR 0.148.
 *
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.init.Blocks
 *  net.minecraft.world.World
 *  net.minecraft.world.gen.feature.WorldGenerator
 */
package com.sown.outerrim.dimension.mustafar;

import com.sown.outerrim.OuterRim;
import com.sown.outerrim.registry.BlockRegister;
import com.sown.util.block.ORBlock;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenMustafarVolcano
extends WorldGenerator {
    private Block lava = Blocks.lava;
    private Block mustafarRock = BlockRegister.getRegisteredBlock("mustafarRock");
    private Block mustafarCobblestone = BlockRegister.getRegisteredBlock("mustafarCobblestone");
    private Block mustafarMagma = BlockRegister.getRegisteredBlock("mustafarMagma");
    private Block ashBlack = BlockRegister.getRegisteredBlock("ashBlack");

    public WorldGenMustafarVolcano(Block lava, Block mustafarRock, Block mustafarCobblestone, Block mustafarMagma, Block ashBlack) {
    }

    @Override
    public boolean generate(World world, Random rand, int x, int y, int z) {
        double distanceFromCenter;
        if (!this.canSpawnHere(world, rand, x, z))
            return false;
        y = 59;
        int radius = rand.nextInt(10) + 20;
        int height = rand.nextInt(20) + 40;
        int lavaHeight = height / 2;
        for (int i = -radius; i <= radius; ++i) {
            for (int j = -radius; j <= radius; ++j) {
                for (int k = 0; k <= height; ++k) {
                    double coneRadius;
                    distanceFromCenter = Math.sqrt(i * i + j * j);
                    if (!(distanceFromCenter <= (coneRadius = (height - k) * ((double)radius / (double)height)))) {
                        continue;
                    }
                    if (k > lavaHeight) {
                        if (distanceFromCenter <= coneRadius - 2.0) {
                            world.setBlock(x + i, y + k, z + j, Blocks.air, 0, 2);
                            continue;
                        }
                        world.setBlock(x + i, y + k, z + j, this.mustafarCobblestone, 0, 2);
                        continue;
                    }
                    if (k == lavaHeight) {
                        if (distanceFromCenter <= coneRadius - 2.0) {
                            world.setBlock(x + i, y + k, z + j, this.lava, 0, 2);
                            continue;
                        }
                        world.setBlock(x + i, y + k, z + j, this.mustafarCobblestone, 0, 2);
                        continue;
                    }
                    world.setBlock(x + i, y + k, z + j, this.mustafarCobblestone, 0, 2);
                }
            }
        }
        int craterRadius = radius / 2;
        for (int i = -craterRadius; i <= craterRadius; ++i) {
            for (int j = -craterRadius; j <= craterRadius; ++j) {
                distanceFromCenter = Math.sqrt(i * i + j * j);
                if (!(distanceFromCenter <= craterRadius)) {
                    continue;
                }
                for (int k = height; k >= height - craterRadius; --k) {
                    world.setBlock(x + i, y + k, z + j, Blocks.air, 0, 2);
                }
            }
        }

        // 1. Add a 3x3 layer of volcanicEruption blocks right on top of the lava layer.
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                world.setBlock(x + i, y + lavaHeight + 1, z + j, BlockRegister.getRegisteredBlock("volcanicEruption"), 0, 2);
            }
        }

        // 2. From the center of this 3x3 layer, create a column of volcanicEruption blocks.
        int topY = y + height - 5 + rand.nextInt(2); // -5 to -4 blocks from the original height
        for (int k = y + lavaHeight + 1; k <= topY; k++) {
            world.setBlock(x, k, z, BlockRegister.getRegisteredBlock("volcanicEruption"), 0, 2);
        }

        return true;
    }

    private boolean canSpawnHere(World world, Random rand, int x, int z) {
        if (rand.nextInt(20) != 0)
            return false;
        int radius = 20;
        for (int i = -radius; i <= radius; ++i) {
            for (int j = -radius; j <= radius; ++j) {
                if (world.getBlock(x + i, 59, z + j) != Blocks.lava) {
                    continue;
                }
                return false;
            }
        }
        return true;
    }
}


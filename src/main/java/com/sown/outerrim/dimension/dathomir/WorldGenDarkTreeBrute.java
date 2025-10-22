package com.sown.outerrim.dimension.dathomir;

import java.util.Random;

import com.sown.outerrim.registry.BlockRegister;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenDarkTreeBrute extends WorldGenerator {

    private final Block log = BlockRegister.getRegisteredBlock("darkTreeLog");
    private final Block leaves = BlockRegister.getRegisteredBlock("darkTreeLeaves");

    @Override
    public boolean generate(World world, Random rand, int x, int y, int z) {
        Block base = world.getBlock(x, y - 1, z);
        if (!isValidSoil(base)) return false;

        int height = 6 + rand.nextInt(5); // 6–10 blocks tall
        boolean wideTrunk = rand.nextBoolean();

        // Generate trunk (straight or fat)
        for (int i = 0; i < height; i++) {
            if (wideTrunk && i < height - 2) {
                placeLog(world, x, y + i, z);
                placeLog(world, x + 1, y + i, z);
                placeLog(world, x, y + i, z + 1);
                placeLog(world, x + 1, y + i, z + 1);
            } else {
                placeLog(world, x, y + i, z);
            }

            // Occasional side vines from trunk
            if (rand.nextInt(12) == 0) {
                int vx = x + rand.nextInt(3) - 1;
                int vz = z + rand.nextInt(3) - 1;
                int vy = y + i;
                placeVineColumn(world, vx, vy, vz, rand);
            }
        }

        // Add random branches
        if (rand.nextBoolean()) {
            int branchCount = 1 + rand.nextInt(3);
            for (int i = 0; i < branchCount; i++) {
                int branchY = y + height - 2 - rand.nextInt(3);

                int dx = 0, dz = 0;
                switch (rand.nextInt(4)) {
                    case 0: dx = 1; break;
                    case 1: dx = -1; break;
                    case 2: dz = 1; break;
                    case 3: dz = -1; break;
                }

                int length = 2 + rand.nextInt(2);
                int bx = x, by = branchY, bz = z;

                int style = rand.nextInt(3); // 0 = straight, 1 = sag, 2 = droop

                for (int j = 0; j < length; j++) {
                    bx += dx;
                    bz += dz;

                    if (style == 1 && j == 1) by -= 1;
                    if (style == 2 && j > 0) by -= 1;

                    placeLog(world, bx, by, bz);

                    // At end of branch, place leaves and occasional vine
                    if (j == length - 1) {
                        if (rand.nextBoolean()) {
                            world.setBlock(bx, by + 1, bz, leaves, 0, 2);
                        }

                        if (rand.nextInt(2) == 0) {
                            placeVineColumn(world, bx, by - 1, bz, rand);
                        }
                    }
                }
            }
        }

        // Generate canopy
        int leafRadius = 3;
        for (int dx = -leafRadius; dx <= leafRadius; dx++) {
            for (int dz = -leafRadius; dz <= leafRadius; dz++) {
                for (int dy = 0; dy <= 3; dy++) {
                    int dist = Math.abs(dx) + Math.abs(dz) + dy;
                    if (dist <= 4 && rand.nextInt(4) != 0) {
                        int px = x + dx;
                        int py = y + height - 2 + dy;
                        int pz = z + dz;

                        if (world.isAirBlock(px, py, pz)) {
                            world.setBlock(px, py, pz, leaves, 0, 2);

                            // Occasional hanging vine from leaf edge
                            if (rand.nextInt(10) == 0) {
                                placeVineColumn(world, px, py - 1, pz, rand);
                            }
                        }
                    }
                }
            }
        }

        return true;
    }

    private void placeVineColumn(World world, int x, int y, int z, Random rand) {
        int length = 1 + rand.nextInt(3);
        for (int i = 0; i < length; i++) {
            if (world.isAirBlock(x, y - i, z)) {
                world.setBlock(x, y - i, z, Blocks.vine, 0, 2);
            } else {
                break;
            }
        }
    }

    private boolean isValidSoil(Block block) {
        return block == BlockRegister.getRegisteredBlock("dathomirDirt") ||
               block == BlockRegister.getRegisteredBlock("dathomirSlate") ||
               block == BlockRegister.getRegisteredBlock("ritualGrass") ||
               block == Blocks.soul_sand;
    }

    private void placeLog(World world, int x, int y, int z) {
        if (world.isAirBlock(x, y, z) || world.getBlock(x, y, z).getMaterial().isReplaceable()) {
            world.setBlock(x, y, z, log, 0, 2);
        }
    }
}

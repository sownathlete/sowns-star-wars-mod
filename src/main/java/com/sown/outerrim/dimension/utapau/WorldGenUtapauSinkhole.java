package com.sown.outerrim.dimension.utapau;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

public class WorldGenUtapauSinkhole {

    public static final int MAX_RADIUS = 50; // Adjusted max radius for spacing

    /**
     * Generates a deep sinkhole-style crater at the given (x, z) world coordinates.
     */
    public void generateCrater(World world, Random rand, int centerX, int centerZ) {
    	int radius = 45 + rand.nextInt(31); // 45–75
        int worldBottom = 6 + rand.nextInt(4); // Y-level 6–9

        double baseFlatness = 0.75; // flat base is ~¾ of radius

        int[][] baseCoords = new int[(radius * 2 + 1) * (radius * 2 + 1)][2];
        int baseIndex = 0;

        for (int x = -radius; x <= radius; x++) {
            for (int z = -radius; z <= radius; z++) {
                double distance = Math.sqrt(x * x + z * z);
                if (distance <= radius) {
                    int worldX = centerX + x;
                    int worldZ = centerZ + z;

                    int topY = getTopSolidBlockY(world, worldX, worldZ);
                    int craterDepth = topY - worldBottom;

                    double normalized = distance / radius;

                    // Custom falloff curve that flattens bottom
                    double falloff;
                    if (normalized < baseFlatness) {
                        falloff = 1.0;
                    } else {
                        double outerNormalized = (normalized - baseFlatness) / (1.0 - baseFlatness);
                        falloff = Math.cos(outerNormalized * Math.PI / 2.0); // smooth sides
                    }

                    int yCrater = (int) (falloff * craterDepth);
                    int targetY = topY - yCrater;

                    // Carve down
                    for (int y = topY; y >= targetY; y--) {
                        world.setBlockToAir(worldX, y, worldZ);
                    }

                    // Save base location for final forced overwrite
                    if (normalized < baseFlatness) {
                        baseCoords[baseIndex][0] = worldX;
                        baseCoords[baseIndex][1] = worldZ;
                        baseIndex++;
                    }
                }
            }
        }
        
        // Cleanup pass: replace leftover stone with sandstone in crater walls
        for (int x = -radius; x <= radius; x++) {
            for (int z = -radius; z <= radius; z++) {
            	double angle = Math.atan2(z, x);
            	double radialNoise = Math.sin(angle * 4 + (centerX + centerZ) * 0.1) * 5 + rand.nextInt(5) - 2;
            	double distance = Math.sqrt(x * x + z * z) + radialNoise;
                if (distance <= radius) {
                    int worldX = centerX + x;
                    int worldZ = centerZ + z;
                    int topY = getTopSolidBlockY(world, worldX, worldZ);
                    for (int y = worldBottom; y <= topY; y++) {
                        Block b = world.getBlock(worldX, y, worldZ);
                        if (b == Blocks.stone) {
                            world.setBlock(worldX, y, worldZ, Blocks.sandstone);
                        }
                    }
                }
            }
        }
    }

    /**
     * Gets the Y height of the topmost solid block at (x, z).
     */
    private int getTopSolidBlockY(World world, int x, int z) {
        for (int y = world.getActualHeight() - 1; y > 0; y--) {
            Block block = world.getBlock(x, y, z);
            if (block != null && block.getMaterial().isSolid() && !block.isAir(world, x, y, z)) {
                return y;
            }
        }
        return 64; // fallback
    }

    /**
     * Finds the first non-air block downward from the given Y and returns the position below it.
     */
    private int getTopAirBelow(World world, int x, int z, int startY) {
        for (int y = startY; y > 0; y--) {
            if (!world.isAirBlock(x, y, z)) {
                return y + 1;
            }
        }
        return 6; // fallback base layer
    }
}

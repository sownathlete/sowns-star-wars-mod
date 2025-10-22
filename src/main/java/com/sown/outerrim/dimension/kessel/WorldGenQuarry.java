package com.sown.outerrim.dimension.kessel;

import java.util.Random;
import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

public class WorldGenQuarry {

    public static final int MAX_SIZE = 75;
    private static final List<int[]> recentQuarryCenters = new ArrayList<int[]>();

    public void generateQuarry(World world, Random rand, int centerX, int centerZ) {
        int size = 45 + rand.nextInt(31); // 45–75
        int halfSize = size / 2;

        // Prevent overlapping with recent quarries
        for (int[] coords : recentQuarryCenters) {
            int dx = coords[0] - centerX;
            int dz = coords[1] - centerZ;
            if (Math.abs(dx) < size && Math.abs(dz) < size) return;
        }

        int[] heightRange = getMinMaxHeight(world, centerX, centerZ, size);
        int minY = heightRange[0];
        int maxY = heightRange[1];

        if (maxY - minY > 12) return; // too uneven — probably hills or cliffs

        int topY = minY;

        // Clear air above to prevent terrain overhang
        for (int x = centerX - halfSize; x <= centerX + halfSize; x++) {
            for (int z = centerZ - halfSize; z <= centerZ + halfSize; z++) {
                for (int y = topY + 1; y < world.getActualHeight(); y++) {
                    Block b = world.getBlock(x, y, z);
                    if (b != null && (b.getMaterial().isSolid() || b.getMaterial().isLiquid())) {
                        world.setBlockToAir(x, y, z);
                    }
                }
            }
        }

        // Track this quarry to prevent future overlap
        recentQuarryCenters.add(new int[] {centerX, centerZ});
        if (recentQuarryCenters.size() > 16) recentQuarryCenters.remove(0);

        int worldBottom = 6 + rand.nextInt(4); // Y-level 6–9
        int depth = topY - worldBottom;
        int stepHeight = 4;
        int steps = depth / stepHeight;

        for (int step = 0; step < steps; step++) {
            int yStart = topY - step * stepHeight;
            int yEnd = yStart - stepHeight;
            int layerSize = size - (step * 4);

            int startX = centerX - layerSize / 2;
            int endX = centerX + layerSize / 2;
            int startZ = centerZ - layerSize / 2;
            int endZ = centerZ + layerSize / 2;

            for (int x = startX; x <= endX; x++) {
                for (int z = startZ; z <= endZ; z++) {
                    for (int y = yStart; y > yEnd; y--) {
                        world.setBlockToAir(x, y, z);
                    }
                }
            }
        }

        // Optional: Replace stone walls with sandstone for style
        for (int x = centerX - halfSize; x <= centerX + halfSize; x++) {
            for (int z = centerZ - halfSize; z <= centerZ + halfSize; z++) {
                for (int y = worldBottom; y <= topY; y++) {
                    Block b = world.getBlock(x, y, z);
                    if (b == Blocks.stone) {
                        world.setBlock(x, y, z, Blocks.stone);
                    }
                }
            }
        }
    }
    
    private int[] getMinMaxHeight(World world, int centerX, int centerZ, int size) {
        int half = size / 2;
        int minY = Integer.MAX_VALUE;
        int maxY = Integer.MIN_VALUE;

        for (int dx = -half; dx <= half; dx += 4) {
            for (int dz = -half; dz <= half; dz += 4) {
                int y = getTopSolidBlockY(world, centerX + dx, centerZ + dz);
                if (y < minY) minY = y;
                if (y > maxY) maxY = y;
            }
        }

        return new int[]{minY, maxY};
    }

    private int getAverageTopY(World world, int centerX, int centerZ, int size) {
        int total = 0;
        int samples = 0;
        int half = size / 2;

        for (int dx = -half; dx <= half; dx += 4) {
            for (int dz = -half; dz <= half; dz += 4) {
                total += getTopSolidBlockY(world, centerX + dx, centerZ + dz);
                samples++;
            }
        }

        return samples > 0 ? total / samples : 64;
    }

    private int getTopSolidBlockY(World world, int x, int z) {
        for (int y = world.getActualHeight() - 1; y > 0; y--) {
            Block block = world.getBlock(x, y, z);
            if (block != null && block.getMaterial().isSolid() && !block.isAir(world, x, y, z)) {
                return y;
            }
        }
        return 64;
    }

    private int getHighestBlockY(World world, int x, int z) {
        for (int y = world.getActualHeight() - 1; y > 0; y--) {
            if (!world.isAirBlock(x, y, z)) {
                return y;
            }
        }
        return 64;
    }
}

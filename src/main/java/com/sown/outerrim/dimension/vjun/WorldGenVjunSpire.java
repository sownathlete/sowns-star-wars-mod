package com.sown.outerrim.dimension.vjun;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenVjunSpire extends WorldGenerator {
    private final Block block = Blocks.stone;

    @Override
    public boolean generate(World world, Random rand, int x, int y, int z) {
        // Scale up original sizes by 3×
        int baseRadius = (2 + rand.nextInt(2)) * 3;   // Now 6–9
        int height     = (15 + rand.nextInt(10)) * 3;  // Now 45–75

        // Partial taper factor: 0.0 = perfect cylinder, 1.0 = full taper to a point
        final float taperFactor = 0.5f;

        // Track footprint of any blocks placed (to fill air beneath later)
        boolean[][] footprint = new boolean[baseRadius * 2 + 1][baseRadius * 2 + 1];

        for (int dy = 0; dy < height; dy++) {
            float t = (float) dy / (float) height;
            float radius = baseRadius * (1.0f - t * taperFactor);
            int r = Math.max(1, Math.round(radius));

            for (int dx = -r; dx <= r; dx++) {
                for (int dz = -r; dz <= r; dz++) {
                    if (dx * dx + dz * dz <= r * r + rand.nextInt(2)) {
                        int wx = x + dx;
                        int wy = y + dy;
                        int wz = z + dz;
                        world.setBlock(wx, wy, wz, block);

                        // Mark this (dx, dz) in the footprint array
                        footprint[dx + baseRadius][dz + baseRadius] = true;
                    }
                }
            }
        }

        // Fill any air directly beneath footprint down to solid ground
        for (int i = 0; i < footprint.length; i++) {
            for (int j = 0; j < footprint[i].length; j++) {
                if (!footprint[i][j]) continue;
                int wx = x + (i - baseRadius);
                int wz = z + (j - baseRadius);
                int wy = y - 1;
                // Drop down until we hit non-air or bedrock
                while (wy > 0 && world.isAirBlock(wx, wy, wz)) {
                    world.setBlock(wx, wy, wz, block);
                    wy--;
                }
            }
        }

        return true;
    }
}

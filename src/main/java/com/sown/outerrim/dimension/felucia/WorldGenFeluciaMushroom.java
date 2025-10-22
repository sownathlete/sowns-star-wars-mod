package com.sown.outerrim.dimension.felucia;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenFeluciaMushroom extends WorldGenerator {
    
    @Override
    public boolean generate(World world, Random rand, int x, int y, int z) {
        // Ensure it's generating on a valid surface
        Block ground = world.getBlock(x, y - 1, z);
        if (ground != Blocks.grass && ground != Blocks.dirt && ground != Blocks.mycelium) {
            return false;
        }
        
        int shiftX = 0;
        int shiftZ = 0;
        
        // Generate the base - Circular 6x6 shape instead of a square
        for (int dx = -3; dx <= 3; dx++) {
            for (int dz = -3; dz <= 3; dz++) {
                if (Math.abs(dx) + Math.abs(dz) <= 4) { // Creating a rounded base shape
                    world.setBlock(x + dx, y, z + dz, Blocks.stained_hardened_clay, 12, 2);
                }
            }
        }
        y++;
        
        // Ensure the structure has at least 9 white layers
        int totalWhiteLayers = rand.nextInt(3) + 9; // 9 to 11 white layers total

        // Build the alternating layers properly
        for (int i = 0; i < totalWhiteLayers; i++) {
            // 1/5 chance to shift the entire mushroom slightly
            if (rand.nextInt(3) == 0) {
                int direction = rand.nextInt(4);
                if (direction == 0) shiftX += 1;
                else if (direction == 1) shiftX -= 1;
                else if (direction == 2) shiftZ += 1;
                else shiftZ -= 1;
            }
            
            // White core 2x2x2
            for (int dx = -1; dx <= 0; dx++) {
                for (int dz = -1; dz <= 0; dz++) {
                    for (int dy = 0; dy < 2; dy++) {
                        world.setBlock(x + dx + shiftX, y + dy, z + dz + shiftZ, Blocks.red_mushroom_block, 10, 2);
                    }
                }
            }
            y += 2;
            
            // Brown ring (no corners, wrapping fully around the white core)
            for (int dx = -2; dx <= 1; dx++) {
                for (int dz = -2; dz <= 1; dz++) {
                    if (!((dx == -2 && dz == -2) || (dx == -2 && dz == 1) || (dx == 1 && dz == -2) || (dx == 1 && dz == 1))) {
                        world.setBlock(x + dx + shiftX, y, z + dz + shiftZ, Blocks.stained_hardened_clay, 12, 2);
                    }
                }
            }
            y++;
        }
        
        // Single-layer filled brown top cap (no corners)
        for (int dx = -2; dx <= 1; dx++) {
            for (int dz = -2; dz <= 1; dz++) {
                if (!((dx == -2 && dz == -2) || (dx == -2 && dz == 1) || (dx == 1 && dz == -2) || (dx == 1 && dz == 1))) {
                    world.setBlock(x + dx + shiftX, y, z + dz + shiftZ, Blocks.stained_hardened_clay, 12, 2);
                }
            }
        }
        
        return true;
    }
}

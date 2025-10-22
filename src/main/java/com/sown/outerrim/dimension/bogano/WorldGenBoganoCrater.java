package com.sown.outerrim.dimension.bogano;

import java.util.Random;

import com.sown.outerrim.registry.BlockRegister;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

public class WorldGenBoganoCrater {

    public void generateCrater(World world, Random rand, int centerX, int centerZ) {
        int radius = 24 + rand.nextInt(16); // 24–40 blocks wide
        int worldBottom = 6 + rand.nextInt(4); // floor level (Y=6 to Y=9)
        int waterLevel = 15; // fill water up to this Y-level

        for (int x = -radius; x <= radius; x++) {
            for (int z = -radius; z <= radius; z++) {
                double distance = Math.sqrt(x * x + z * z);
                if (distance <= radius) {
                    int worldX = centerX + x;
                    int worldZ = centerZ + z;

                    // determine top and bottom of crater at this column
                    int topY = getTopSolidBlockY(world, worldX, worldZ);
                    int craterDepth = topY - worldBottom;
                    double normalized = distance / radius;
                    double falloff = Math.pow(Math.cos(normalized * Math.PI * 0.5), 1.5); // steeper sides
                    int yCrater = (int) (falloff * craterDepth);
                    int targetY = topY - yCrater;

                    // carve out crater
                    for (int y = topY; y >= targetY; y--) {
                        world.setBlockToAir(worldX, y, worldZ);
                    }

                    // fill water up to waterLevel in carved space (only air blocks)
                    if (targetY <= waterLevel) {
                        for (int y = targetY; y <= waterLevel; y++) {
                            if (world.isAirBlock(worldX, y, worldZ)) {
                                world.setBlock(worldX, y, worldZ, Blocks.water);
                            }
                        }
                    }
                    // place solid floor if crater bottom is above water
                    else if (targetY <= worldBottom + 2) {
                        Block fill = rand.nextFloat() < 0.2F
                            ? BlockRegister.getRegisteredBlock("boganoRockDark")
                            : BlockRegister.getRegisteredBlock("boganoRockLight");
                        world.setBlock(worldX, worldBottom, worldZ, fill);
                    }
                }
            }
        }
    }

    private int getTopSolidBlockY(World world, int x, int z) {
        for (int y = world.getActualHeight() - 1; y > 0; y--) {
            Block block = world.getBlock(x, y, z);
            if (block != null && block.getMaterial().isSolid() && !block.isAir(world, x, y, z)) {
                return y;
            }
        }
        return 64; // fallback if no terrain found
    }
}

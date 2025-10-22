package com.sown.outerrim.dimension.bestine;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;

public class WorldGenPalmTreeA1 extends WorldGenAbstractTree {
    private int strengthRand;
    private int bMax;
    private double offset;

    public WorldGenPalmTreeA1() {
        this(35, 12, 1.3); // Increase base height variation
    }

    public WorldGenPalmTreeA1(int strengthRand, int bMax, double offset) {
        super(false);
        this.strengthRand = strengthRand;
        this.bMax = bMax;
        this.offset = offset;
    }

    public boolean generate(World world, Random random, int x, int y, int z) {
        while (world.isAirBlock(x, y, z) && y > 2) {
            --y;
        }
        Block block = world.getBlock(x, y, z);
        if (block != Blocks.grass) { // Only generate on grass
            return false;
        }

        int h = 5 + random.nextInt(6); // Increased height variation
        int trunkHeight = h + random.nextInt(2); // Ensure reasonable height variation

        // Adjust tree so trunk starts at y instead of being buried
        int startY = y + 1;

        // Build the tree trunk
        for (int i = 0; i < trunkHeight; i++) {
            world.setBlock(x, startY + i, z, Blocks.log, 3, 2); // Jungle log
        }

        this.generateTop(world, x, startY + trunkHeight, z);
        return true;
    }

    public void generateTop(World world, int x, int y, int z) {
        int leafMeta = 2 | 4; // Birch leaves, persistent (no decay)
        this.buildBlock(world, x + 2, y - 1, z, Blocks.leaves, leafMeta);
        this.buildBlock(world, x - 2, y - 1, z, Blocks.leaves, leafMeta);
        this.buildBlock(world, x, y - 1, z + 2, Blocks.leaves, leafMeta);
        this.buildBlock(world, x, y - 1, z - 2, Blocks.leaves, leafMeta);
        this.buildBlock(world, x + 1, y, z, Blocks.leaves, leafMeta);
        this.buildBlock(world, x - 1, y, z, Blocks.leaves, leafMeta);
        this.buildBlock(world, x, y, z + 1, Blocks.leaves, leafMeta);
        this.buildBlock(world, x, y, z - 1, Blocks.leaves, leafMeta);
        this.buildBlock(world, x + 2, y, z + 2, Blocks.leaves, leafMeta);
        this.buildBlock(world, x - 2, y, z - 2, Blocks.leaves, leafMeta);
        this.buildBlock(world, x + 2, y, z - 2, Blocks.leaves, leafMeta);
        this.buildBlock(world, x - 2, y, z + 2, Blocks.leaves, leafMeta);
        this.buildBlock(world, x + 1, y + 1, z - 1, Blocks.leaves, leafMeta);
        this.buildBlock(world, x - 1, y + 1, z + 1, Blocks.leaves, leafMeta);
        this.buildBlock(world, x + 1, y + 1, z + 1, Blocks.leaves, leafMeta);
        this.buildBlock(world, x - 1, y + 1, z - 1, Blocks.leaves, leafMeta);
        this.buildBlock(world, x, y + 1, z, Blocks.leaves, leafMeta);
        this.buildBlock(world, x + 2, y + 2, z, Blocks.leaves, leafMeta);
        this.buildBlock(world, x - 2, y + 2, z, Blocks.leaves, leafMeta);
        this.buildBlock(world, x, y + 2, z + 2, Blocks.leaves, leafMeta);
        this.buildBlock(world, x, y + 2, z - 2, Blocks.leaves, leafMeta);
    }

    public void buildBlock(World world, int x, int y, int z, Block block, int meta) {
        if (world.isAirBlock(x, y, z) || world.getBlock(x, y, z).isLeaves((IBlockAccess)world, x, y, z)) {
            world.setBlock(x, y, z, block, meta, 2);
        }
    }
}
/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 *  net.minecraft.world.gen.feature.WorldGenerator
 */
package com.sown.outerrim.dimension.kessel;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenKesselMud
extends WorldGenerator {
    private Block flowerBlock;
    private int flowerBlockMeta;
    private List<Block> blocksToSplatter;

    public WorldGenKesselMud(Block flowerBlock, int flowerBlockMeta, Block ... blocksToSplatter) {
        this.flowerBlock = flowerBlock;
        this.flowerBlockMeta = flowerBlockMeta;
        this.blocksToSplatter = Arrays.asList(blocksToSplatter);
    }

    public WorldGenKesselMud(Block flowerBlock, Block ... blocksToSplatter) {
        this(flowerBlock, 0, blocksToSplatter);
    }

    public boolean generate(World world, Random rand, int x, int y, int z) {
        Block block;
        while (((block = world.getBlock(x, y, z)).isLeaves((IBlockAccess)world, x, y, z) || block.isAir((IBlockAccess)world, x, y, z)) && --y > 0) {
        }
        for (int l = 0; l < 128; ++l) {
            int j1;
            int k1;
            int i1 = x + rand.nextInt(8) - rand.nextInt(8);
            if (!world.isAirBlock(i1, j1 = y + rand.nextInt(4) - rand.nextInt(4), k1 = z + rand.nextInt(8) - rand.nextInt(8)) || !this.blocksToSplatter.contains((Object)world.getBlock(i1, j1 - 1, k1))) continue;
            world.setBlock(i1, j1 - 1, k1, this.flowerBlock, this.flowerBlockMeta, 2);
        }
        return true;
    }
}


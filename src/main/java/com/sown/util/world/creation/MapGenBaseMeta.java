/*
 * Decompiled with CFR 0.148.
 *
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.world.World
 *  net.minecraft.world.chunk.IChunkProvider
 */
package com.sown.util.world.creation;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;

public abstract class MapGenBaseMeta {
    protected int range = 8;
    protected Random rand = new Random();
    protected World worldObj;

    public void generate(IChunkProvider par1IChunkProvider, World world, int chunkX, int chunkZ, Block[] blocks, byte[] metadata) {
        this.worldObj = world;
        this.rand.setSeed(world.getSeed());
        long r0 = this.rand.nextLong();
        long r1 = this.rand.nextLong();
        for (int x0 = chunkX - this.range; x0 <= chunkX + this.range; ++x0) {
            for (int y0 = chunkZ - this.range; y0 <= chunkZ + this.range; ++y0) {
                long randX = x0 * r0;
                long randZ = y0 * r1;
                this.rand.setSeed(randX ^ randZ ^ world.getSeed());
                this.recursiveGenerate(world, x0, y0, chunkX, chunkZ, blocks, metadata);
            }
        }
    }

    protected void recursiveGenerate(World world, int xChunkCoord, int zChunkCoord, int origXChunkCoord, int origZChunkCoord, Block[] blocks, byte[] metadata) {
    }
}


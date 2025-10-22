/*
 * Decompiled with CFR 0.148.
 *
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.World
 *  net.minecraft.world.gen.feature.WorldGenMinable
 */
package com.sown.util.world.creation.worldgen;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenMinable;

public class WorldGenOuterRimMinableMeta
extends WorldGenMinable {
    private final Block minableBlockId;
    private final int numberOfBlocks;
    private final int metadata;
    private boolean usingMetadata = false;
    private final Block fillerID;
    private final int fillerMetadata;

    public WorldGenOuterRimMinableMeta(Block par1, int par2, int par3, boolean par4, Block id, int meta) {
        super(par1, par3, par2, id);
        this.minableBlockId = par1;
        this.numberOfBlocks = par2;
        this.metadata = par3;
        this.usingMetadata = par4;
        this.fillerID = id;
        this.fillerMetadata = meta;
    }

    @Override
    public boolean generate(World par1World, Random par2Random, int px, int py, int pz) {
        float f = par2Random.nextFloat() * 3.1415927f;
        float sinf = MathHelper.sin(f) * this.numberOfBlocks / 8.0f;
        float cosf = MathHelper.cos(f) * this.numberOfBlocks / 8.0f;
        float x1 = px + 8 + sinf;
        float x2 = -2.0f * sinf;
        float z1 = pz + 8 + cosf;
        float z2 = -2.0f * cosf;
        float y1 = py + par2Random.nextInt(3) - 2;
        float y2 = py + par2Random.nextInt(3) - 2 - y1;
        for (int l = 0; l <= this.numberOfBlocks; ++l) {
            float progress = (float)l / (float)this.numberOfBlocks;
            float cx = x1 + x2 * progress;
            float cy = y1 + y2 * progress;
            float cz = z1 + z2 * progress;
            float size = ((MathHelper.sin(3.1415927f * progress) + 1.0f) * par2Random.nextFloat() * this.numberOfBlocks / 16.0f + 1.0f) / 2.0f;
            int xMin = MathHelper.floor_float(cx - size);
            int yMin = MathHelper.floor_float(cy - size);
            int zMin = MathHelper.floor_float(cz - size);
            int xMax = MathHelper.floor_float(cx + size);
            int yMax = MathHelper.floor_float(cy + size);
            int zMax = MathHelper.floor_float(cz + size);
            for (int ix = xMin; ix <= xMax; ++ix) {
                float dx = (ix + 0.5f - cx) / size;
                if (!((dx *= dx) < 1.0f)) {
                    continue;
                }
                for (int iy = yMin; iy <= yMax; ++iy) {
                    float dy = (iy + 0.5f - cy) / size;
                    if (!(dx + (dy *= dy) < 1.0f)) {
                        continue;
                    }
                    for (int iz = zMin; iz <= zMax; ++iz) {
                        float dz = (iz + 0.5f - cz) / size;
                        if (!(dx + dy + (dz *= dz) < 1.0f) || par1World.getBlock(ix, iy, iz) != this.fillerID || par1World.getBlockMetadata(ix, iy, iz) != this.fillerMetadata) {
                            continue;
                        }
                        if (!this.usingMetadata) {
                            par1World.setBlock(ix, iy, iz, this.minableBlockId, 0, 3);
                            continue;
                        }
                        par1World.setBlock(ix, iy, iz, this.minableBlockId, this.metadata, 3);
                    }
                }
            }
        }
        return true;
    }
}


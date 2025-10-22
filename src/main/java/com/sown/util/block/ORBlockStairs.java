/*
 * Decompiled with CFR 0.148.
 *
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockStairs
 */
package com.sown.util.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockStairs;

public class ORBlockStairs
extends BlockStairs {
    public String name;

    public ORBlockStairs(String name, Block block, int meta) {
        super(block, meta);
        this.name = name;
        this.setBlockName("outerrim." + this.name);
    }
}


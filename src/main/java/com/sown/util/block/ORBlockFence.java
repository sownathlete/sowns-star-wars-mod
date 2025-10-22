/*
 * Decompiled with CFR 0.148.
 *
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockFence
 *  net.minecraft.block.material.Material
 */
package com.sown.util.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFence;
import net.minecraft.block.material.Material;

public class ORBlockFence
extends BlockFence {
    public String name;

    public ORBlockFence(String name, String texture, Material material) {
        super(texture, material);
        this.name = name;
        this.setBlockName("outerrim." + this.name);
    }
}


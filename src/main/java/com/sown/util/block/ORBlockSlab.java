/*
 * Decompiled with CFR 0.148.
 *
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockSlab
 *  net.minecraft.block.material.Material
 */
package com.sown.util.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.material.Material;

public class ORBlockSlab
extends BlockSlab {
    public String name;

    public ORBlockSlab(String name, boolean fullBlock, Material material) {
        super(fullBlock, material);
        this.name = name;
        this.setBlockName("outerrim." + this.name);
    }

    @Override
    public String func_150002_b(int p_150002_1_) {
        return this.name;
    }
}


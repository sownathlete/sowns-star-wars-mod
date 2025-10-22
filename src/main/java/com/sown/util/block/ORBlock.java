/*
 * Decompiled with CFR 0.148.
 *
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.material.Material
 */
package com.sown.util.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class ORBlock
extends Block {
    public String name;

    public ORBlock(String name, Material material) {
        super(material);
        this.name = name;
        this.setBlockName("outerrim." + this.name);
    }
}


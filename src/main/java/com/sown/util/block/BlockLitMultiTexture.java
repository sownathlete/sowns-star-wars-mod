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

public class BlockLitMultiTexture
extends BlockMultiTexture {
    public BlockLitMultiTexture(String base, String[] postfixes, float light, Material material) {
        super(base, postfixes, material);
        this.setLightLevel(light);
    }
}


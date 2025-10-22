/*
 * Decompiled with CFR 0.148.
 *
 * Could not load the following classes:
 *  cpw.mods.fml.common.registry.GameRegistry
 *  net.minecraft.block.Block
 */
package com.sown.util.block;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;

public class ORBlockReg {
    public static ORBlock registerBlock(Class<? extends ORBlock> blockClass) {
        try {
            ORBlock b = blockClass.newInstance();
            GameRegistry.registerBlock(b, b.name);
            return b;
        }
        catch (IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static ORBlockContainer registerBlockContainer(Class<? extends ORBlockContainer> blockClass) {
        try {
            ORBlockContainer b = blockClass.newInstance();
            GameRegistry.registerBlock(b, b.name);
            return b;
        }
        catch (IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static ORBlockStairs registerBlockStairs(Class<? extends ORBlockStairs> blockClass) {
        try {
            ORBlockStairs b = blockClass.newInstance();
            GameRegistry.registerBlock(b, b.name);
            return b;
        }
        catch (IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static ORBlockSlab registerBlockSlab(Class<? extends ORBlockSlab> blockClass) {
        try {
            ORBlockSlab b = blockClass.newInstance();
            GameRegistry.registerBlock(b, b.name);
            return b;
        }
        catch (IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static ORBlockFence registerBlockFence(Class<? extends ORBlockFence> blockClass) {
        try {
            ORBlockFence b = blockClass.newInstance();
            GameRegistry.registerBlock(b, b.name);
            return b;
        }
        catch (IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
            return null;
        }
    }
}


/*
 * Decompiled with CFR 0.148.
 *
 * Could not load the following classes:
 *  net.minecraft.world.World
 */
package com.sown.util.world.creation;

import java.util.Random;
import net.minecraft.world.World;

public interface IORWorldGenerator {
    public boolean generate(World var1, Random var2, int var3, int var4, int var5);

    public void setupGeneration(World var1, Random var2, ORBiome var3, String var4, int var5, int var6);
}


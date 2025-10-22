/*
 * Decompiled with CFR 0.148.
 *
 * Could not load the following classes:
 *  net.minecraft.world.World
 *  net.minecraft.world.gen.feature.WorldGenerator
 */
package com.sown.util.world.creation;

import java.util.Random;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public abstract class ForcedWorldFeatureOR
implements IORWorldGenerator {
    private WorldGenerator worldGenerator;

    public ForcedWorldFeatureOR(WorldGenerator worldGenerator) {
        this.worldGenerator = worldGenerator;
    }

    @Override
    public boolean generate(World world, Random random, int x, int y, int z) {
        return this.worldGenerator.generate(world, random, x, y, z);
    }
}


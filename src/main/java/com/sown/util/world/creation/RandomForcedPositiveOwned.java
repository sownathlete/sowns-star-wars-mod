/*
 * Decompiled with CFR 0.148.
 *
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.ReflectionHelper
 */
package com.sown.util.world.creation;

import cpw.mods.fml.relauncher.ReflectionHelper;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;

public class RandomForcedPositiveOwned
extends Random {
    private final Random parent;

    public RandomForcedPositiveOwned(Random parent) {
        super(((AtomicLong)ReflectionHelper.getPrivateValue(Random.class, parent, new String[]{"seed"})).longValue());
        this.parent = parent;
    }

    @Override
    public int nextInt() {
        return this.nextInt(1);
    }

    @Override
    public int nextInt(int n) {
        if (n > 0)
            return this.parent.nextInt(n);
        return 0;
    }
}


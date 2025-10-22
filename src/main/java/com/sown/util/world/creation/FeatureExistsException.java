/*
 * Decompiled with CFR 0.148.
 */
package com.sown.util.world.creation;

public class FeatureExistsException
extends RuntimeException {
    public FeatureExistsException(String name) {
        super("Feature " + name + " already exists!");
    }
}


/*
 * Decompiled with CFR 0.148.
 */
package com.sown.util.world.creation;

public class NoSuchFeatureException
extends RuntimeException {
    public NoSuchFeatureException(String name) {
        super("Feature " + name + " does not exist!");
    }
}


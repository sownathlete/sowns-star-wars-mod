/*
 * Decompiled with CFR 0.148.
 */
package com.sown.util.world.creation;

public abstract class NoiseModule {
    public float frequencyX = 1.0f;
    public float frequencyY = 1.0f;
    public float frequencyZ = 1.0f;
    public float amplitude = 1.0f;

    public abstract float getNoise(float var1);

    public abstract float getNoise(float var1, float var2);

    public abstract float getNoise(float var1, float var2, float var3);

    public void setFrequency(float frequency) {
        this.frequencyX = frequency;
        this.frequencyY = frequency;
        this.frequencyZ = frequency;
    }
}


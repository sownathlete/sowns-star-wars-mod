/*
 * Decompiled with CFR 0.148.
 */
package com.sown.util.world.creation;

import java.util.Random;

public class GradientUtil
extends NoiseModule {
    private final NoiseUtil noiseGen;
    private final float offsetX;
    private final float offsetY;
    private final float offsetZ;
    private final int numOctaves;
    private final float persistance;

    public GradientUtil(long seed, int nOctaves, float p) {
        this.numOctaves = nOctaves;
        this.persistance = p;
        Random rand = new Random(seed);
        this.offsetX = rand.nextFloat() / 2.0f + 0.01f;
        this.offsetY = rand.nextFloat() / 2.0f + 0.01f;
        this.offsetZ = rand.nextFloat() / 2.0f + 0.01f;
        this.noiseGen = new NoiseUtil(seed);
    }

    @Override
    public float getNoise(float i) {
        i *= this.frequencyX;
        float val = 0.0f;
        float curAmplitude = this.amplitude;
        for (int n = 0; n < this.numOctaves; ++n) {
            val += this.noiseGen.noise2d(i + this.offsetX, this.offsetY) * curAmplitude;
            i *= 2.0f;
            curAmplitude *= this.persistance;
        }
        return val;
    }

    @Override
    public float getNoise(float i, float j) {
        if (this.numOctaves == 1)
            return this.noiseGen.noise2d(i * this.frequencyX + this.offsetX, j * this.frequencyY + this.offsetY) * this.amplitude;
        i *= this.frequencyX;
        j *= this.frequencyY;
        float val = 0.0f;
        float curAmplitude = this.amplitude;
        for (int n = 0; n < this.numOctaves; ++n) {
            val += this.noiseGen.noise2d(i + this.offsetX, j + this.offsetY) * curAmplitude;
            i *= 2.0f;
            j *= 2.0f;
            curAmplitude *= this.persistance;
        }
        return val;
    }

    @Override
    public float getNoise(float i, float j, float k) {
        if (this.numOctaves == 1)
            return this.noiseGen.noise3d(i * this.frequencyX + this.offsetX, j * this.frequencyY + this.offsetY, k * this.frequencyZ + this.offsetZ) * this.amplitude;
        i *= this.frequencyX;
        j *= this.frequencyY;
        k *= this.frequencyZ;
        float val = 0.0f;
        float curAmplitude = this.amplitude;
        for (int n = 0; n < this.numOctaves; ++n) {
            val += this.noiseGen.noise3d(i + this.offsetX, j + this.offsetY, k + this.offsetZ) * curAmplitude;
            i *= 2.0f;
            j *= 2.0f;
            k *= 2.0f;
            curAmplitude *= this.persistance;
        }
        return val;
    }
}


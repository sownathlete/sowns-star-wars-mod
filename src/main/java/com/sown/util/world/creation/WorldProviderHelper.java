/*
 * Decompiled with CFR 0.148.
 */
package com.sown.util.world.creation;


public interface WorldProviderHelper {
    public float getGravity();

    public double getMeteorFrequency();

    public double getFuelUsageMultiplier();

    public boolean canSpaceshipTierPass(int var1);

    public float getFallDamageModifier();

    public float getSoundVolReductionAmount();

    public boolean hasBreathableAtmosphere();

    public boolean netherPortalsOperational();

    public float getThermalLevelModifier();

    public float getWindLevel();

    public float getSolarSize();

    public GlobalPreset getCelestialBody();
}


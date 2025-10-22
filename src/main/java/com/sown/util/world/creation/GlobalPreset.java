/*
 * Decompiled with CFR 0.148.
 *
 * Could not load the following classes:
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.util.StatCollector
 *  net.minecraft.world.WorldProvider
 *  org.apache.commons.lang3.builder.EqualsBuilder
 */
package com.sown.util.world.creation;

import java.util.ArrayList;
import java.util.Locale;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import net.minecraft.world.WorldProvider;
import org.apache.commons.lang3.builder.EqualsBuilder;

public abstract class GlobalPreset
implements Comparable<GlobalPreset> {
    protected final String bodyName;
    protected String unlocalizedName;
    protected float relativeSize = 1.0f;
    protected ScalableDistance relativeDistanceFromCenter = new ScalableDistance(1.0f, 1.0f);
    protected float relativeOrbitTime = 1.0f;
    protected float phaseShift = 0.0f;
    protected int dimensionID = 0;
    protected Class<? extends WorldProvider> providerClass;
    protected boolean autoRegisterDimension = false;
    protected boolean isReachable = false;
    protected boolean forceStaticLoad = true;
    protected int tierRequired = 0;
    protected ResourceLocation celestialBodyIcon;
    protected float ringColorR = 1.0f;
    protected float ringColorG = 1.0f;
    protected float ringColorB = 1.0f;

    public GlobalPreset(String bodyName) {
        this.bodyName = bodyName.toLowerCase(Locale.ENGLISH);
        this.unlocalizedName = bodyName;
    }

    public abstract int getID();

    public abstract String getUnlocalizedNamePrefix();

    public String getName() {
        return this.bodyName;
    }

    public String getUnlocalizedName() {
        return this.getUnlocalizedNamePrefix() + "." + this.unlocalizedName;
    }

    public String getLocalizedName() {
        String s = this.getUnlocalizedName();
        s = s == null ? "" : StatCollector.translateToLocal(s);
        int comment = s.indexOf(35);
        return comment > 0 ? s.substring(0, comment).trim() : s;
    }

    public float getRelativeSize() {
        return this.relativeSize;
    }

    public ScalableDistance getRelativeDistanceFromCenter() {
        return this.relativeDistanceFromCenter;
    }

    public float getPhaseShift() {
        return this.phaseShift;
    }

    public float getRelativeOrbitTime() {
        return this.relativeOrbitTime;
    }

    public int getTierRequirement() {
        return this.tierRequired;
    }

    public GlobalPreset setTierRequired(int tierRequired) {
        this.tierRequired = tierRequired;
        return this;
    }

    public GlobalPreset setRelativeSize(float relativeSize) {
        this.relativeSize = relativeSize;
        return this;
    }

    public GlobalPreset setRelativeDistanceFromCenter(ScalableDistance relativeDistanceFromCenter) {
        this.relativeDistanceFromCenter = relativeDistanceFromCenter;
        return this;
    }

    public GlobalPreset setPhaseShift(float phaseShift) {
        this.phaseShift = phaseShift;
        return this;
    }

    public GlobalPreset setRelativeOrbitTime(float relativeOrbitTime) {
        this.relativeOrbitTime = relativeOrbitTime;
        return this;
    }

    public GlobalPreset setDimensionInfo(int dimID, Class<? extends WorldProvider> providerClass) {
        return this.setDimensionInfo(dimID, providerClass, true);
    }

    public GlobalPreset setDimensionInfo(int providerId, Class<? extends WorldProvider> providerClass, boolean autoRegister) {
        this.dimensionID = providerId;
        this.providerClass = providerClass;
        this.autoRegisterDimension = autoRegister;
        this.isReachable = true;
        return this;
    }

    public boolean shouldAutoRegister() {
        return this.autoRegisterDimension;
    }

    public int getDimensionID() {
        return this.dimensionID;
    }

    public Class<? extends WorldProvider> getWorldProvider() {
        return this.providerClass;
    }

    public boolean getReachable() {
        return this.isReachable;
    }
    public GlobalPreset setRingColorRGB(float ringColorR, float ringColorG, float ringColorB) {
        this.ringColorR = ringColorR;
        this.ringColorG = ringColorG;
        this.ringColorB = ringColorB;
        return this;
    }

    public float getRingColorR() {
        return this.ringColorR;
    }

    public float getRingColorG() {
        return this.ringColorG;
    }

    public float getRingColorB() {
        return this.ringColorB;
    }

    public ResourceLocation getBodyIcon() {
        return this.celestialBodyIcon;
    }

    public GlobalPreset setBodyIcon(ResourceLocation planetIcon) {
        this.celestialBodyIcon = planetIcon;
        return this;
    }

    public boolean getForceStaticLoad() {
        return this.forceStaticLoad;
    }

    public GlobalPreset setForceStaticLoad(boolean force) {
        this.forceStaticLoad = force;
        return this;
    }

    @Override
    public int hashCode() {
        return this.getUnlocalizedName().hashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof GlobalPreset)
            return new EqualsBuilder().append(this.getUnlocalizedName(), ((GlobalPreset)other).getUnlocalizedName()).isEquals();
        return false;
    }

    @Override
    public int compareTo(GlobalPreset other) {
        ScalableDistance thisDistance = this.getRelativeDistanceFromCenter();
        ScalableDistance otherDistance = other.getRelativeDistanceFromCenter();
        return otherDistance.unScaledDistance < thisDistance.unScaledDistance ? 1 : (otherDistance.unScaledDistance > thisDistance.unScaledDistance ? -1 : 0);
    }

    public void setUnreachable() {
        this.isReachable = false;
    }

    public static class ScalableDistance {
        public final float unScaledDistance;
        public final float scaledDistance;

        public ScalableDistance(float unScaledDistance, float scaledDistance) {
            this.unScaledDistance = unScaledDistance;
            this.scaledDistance = scaledDistance;
        }
    }

}


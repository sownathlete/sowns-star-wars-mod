/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.biome.WorldChunkManager
 *  net.minecraft.world.chunk.IChunkProvider
 */
package com.sown.outerrim.dimension.kessel;

import com.sown.outerrim.dimension.kessel.ChunkProviderKessel;
import com.sown.outerrim.dimension.kessel.WorldChunkManagerKessel;
import com.sown.util.world.creation.GlobalPreset;
import com.sown.util.world.creation.Vector3;
import com.sown.util.world.creation.WorldProviderHelper;
import com.sown.util.world.creation.WorldProviderSpace;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraft.world.biome.WorldChunkManager;
import net.minecraft.world.chunk.IChunkProvider;

public class WorldProviderKessel
extends WorldProviderSpace
implements WorldProviderHelper {
    @Override
    public boolean canRainOrSnow() {
        return false;
    }

    @Override
    public boolean hasSunset() {
        return true;
    }
    
    @Override
    public float getCloudHeight() {
        return 256.0F;
    }

    @Override
    public boolean canBlockFreeze(int x, int y, int z, boolean byWater) {
        return false;
    }

    @Override
    public long getDayLength() {
        return 24000L;
    }

    public boolean shouldForceRespawn() {
        return true;
    }
    
    @SideOnly(Side.CLIENT)
    @Override
    public Vec3 getSkyColor(Entity cameraEntity, float partialTicks) {
        int color = 0xCCB475;
        float dr = ((color >> 16) & 0xFF) / 255.0f;
        float dg = ((color >>  8) & 0xFF) / 255.0f;
        float db =  (color        & 0xFF) / 255.0f;

        float star = this.worldObj.getStarBrightness(partialTicks);
        float bright = 1.0f - star;

        return Vec3.createVectorHelper(dr * bright,
                                       dg * bright,
                                       db * bright);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public Vec3 getFogColor(float par1, float par2) {
        int color = 0xAA904F;
        float fr = ((color >> 16) & 0xFF) / 255.0f;
        float fg = ((color >>  8) & 0xFF) / 255.0f;
        float fb =  (color        & 0xFF) / 255.0f;

        float star = this.worldObj.getStarBrightness(par2);
        float bright = 1.0f - star;

        return Vec3.createVectorHelper(fr * bright,
                                       fg * bright,
                                       fb * bright);
    }

    @Override
    public Class<? extends IChunkProvider> getChunkProviderClass() {
        return ChunkProviderKessel.class;
    }

    @Override
    public Class<? extends WorldChunkManager> getWorldChunkManagerClass() {
        return WorldChunkManagerKessel.class;
    }

    public double getHorizon() {
        return 44.0;
    }

    public int getAverageGroundLevel() {
        return 44;
    }

    public boolean canCoordinateBeSpawn(int var1, int var2) {
        return true;
    }

    @Override
    public float getGravity() {
        return 0.03f;
    }

    public int getHeight() {
        return 800;
    }

    @Override
    public double getMeteorFrequency() {
        return 0.0;
    }

    @Override
    public double getFuelUsageMultiplier() {
        return 1.0;
    }

    @Override
    public float getFallDamageModifier() {
        return 0.95f;
    }

    @Override
    public float getSoundVolReductionAmount() {
        return 5.0f;
    }

    @Override
    public boolean hasBreathableAtmosphere() {
        return true;
    }

    @Override
    public float getThermalLevelModifier() {
        return 0.0f;
    }

    @Override
    public float getWindLevel() {
        return 3.0f;
    }

    @Override
    public boolean canSpaceshipTierPass(int var1) {
        return false;
    }

    @Override
    public GlobalPreset getCelestialBody() {
        return null;
    }

    public String getDimensionName() {
        return null;
    }
}


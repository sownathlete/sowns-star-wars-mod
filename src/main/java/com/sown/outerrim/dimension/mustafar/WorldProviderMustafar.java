/*
 * Decompiled with CFR 0.148.
 *
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  micdoodle8.mods.galacticraft.api.galaxies.CelestialBody
 *  micdoodle8.mods.galacticraft.api.galaxies.Planet
 *  micdoodle8.mods.galacticraft.api.prefab.world.gen.WorldProviderSpace
 *  micdoodle8.mods.galacticraft.api.vector.Vector3
 *  micdoodle8.mods.galacticraft.api.world.IGalacticraftWorldProvider
 *  micdoodle8.mods.galacticraft.api.world.ISolarLevel
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.World
 *  net.minecraft.world.biome.WorldChunkManager
 *  net.minecraft.world.chunk.IChunkProvider
 */
package com.sown.outerrim.dimension.mustafar;

import com.sown.util.world.creation.GlobalPreset;
import com.sown.util.world.creation.WorldProviderHelper;
import com.sown.util.world.creation.Vector3;
import com.sown.util.world.creation.WorldProviderSpace;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraft.world.biome.WorldChunkManager;
import net.minecraft.world.chunk.IChunkProvider;

public class WorldProviderMustafar
extends WorldProviderSpace
implements WorldProviderHelper{

    @Override
    public boolean canRainOrSnow() {
        return false;
    }

    @Override
    public boolean hasSunset() {
        return true;
    }

    @Override
    public boolean canBlockFreeze(int x, int y, int z, boolean bywater) {
        return false;
    }

    @Override
    public long getDayLength() {
        return 24000L;
    }

    public boolean shouldForceRespawn() {
        return true;
    }

    @Override
    public Class<? extends IChunkProvider> getChunkProviderClass() {
        return ChunkProviderMustafar.class;
    }

    @Override
    public Class<? extends WorldChunkManager> getWorldChunkManagerClass() {
        return WorldChunkManagerMustafar.class;
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public float getStarBrightness(float par1) {
        float f1 = this.worldObj.getCelestialAngle(par1);
        float f2 = 1.0f - (MathHelper.cos(f1 * 6.2831855f) * 2.0f + 0.25f);
        if (f2 < 0.0f) {
            f2 = 1.0f;
        }
        if (f2 > 1.0f) {
            f2 = 1.0f;
        }
        return f2 * f2 * 0.7f;
    }

    @SideOnly(value=Side.CLIENT)
    public float getCustomStarBrightness(float par1) {
        float f1 = this.worldObj.getCelestialAngle(par1);
        float f2 = 1.0f - (MathHelper.cos(f1 * 6.2831855f) * 2.0f + 0.25f);
        if (f2 < 0.0f) {
            f2 = 1.0f;
        }
        if (f2 > 1.0f) {
            f2 = 1.0f;
        }
        return f2 * f2 * (this.getSunBrightness(1.0f) * -1.0f) * f1;
    }
    
    @SideOnly(value = Side.CLIENT)
    public Vec3 getFogColor(float par1, float par2) {
        int color = 12151387;
        float r = (float) ((color >> 16) & 255) / 255.0f; // Extract Red
        float g = (float) ((color >> 8) & 255) / 255.0f;  // Extract Green
        float b = (float) (color & 255) / 255.0f;         // Extract Blue
        return Vec3.createVectorHelper(r, g, b);
    }

    @SideOnly(value = Side.CLIENT)
    public Vec3 getSkyColor(Entity cameraEntity, float partialTicks) {
        int color = 12151387;
        float r = (float) ((color >> 16) & 255) / 255.0f; // Extract Red
        float g = (float) ((color >> 8) & 255) / 255.0f;  // Extract Green
        float b = (float) (color & 255) / 255.0f;         // Extract Blue
        return Vec3.createVectorHelper(r, g, b);
    }

    @Override
    public double getHorizon() {
        return 44.0;
    }

    @Override
    public int getAverageGroundLevel() {
        return 44;
    }

    @Override
    public boolean canCoordinateBeSpawn(int var1, int var2) {
        return true;
    }

    @Override
    public float getGravity() {
        return 0.03f;
    }

    @Override
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
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public GlobalPreset getCelestialBody() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String getDimensionName() {
        // TODO Auto-generated method stub
        return null;
    }
}


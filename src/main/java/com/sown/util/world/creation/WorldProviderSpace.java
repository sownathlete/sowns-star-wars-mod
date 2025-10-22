/*
 * Decompiled with CFR 0.148.
 *
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.Vec3
 *  net.minecraft.world.World
 *  net.minecraft.world.WorldProvider
 *  net.minecraft.world.biome.WorldChunkManager
 *  net.minecraft.world.chunk.Chunk
 *  net.minecraft.world.chunk.IChunkProvider
 *  net.minecraft.world.storage.WorldInfo
 */
package com.sown.util.world.creation;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Arrays;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.biome.WorldChunkManager;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.storage.WorldInfo;

public abstract class WorldProviderSpace
extends WorldProvider
implements WorldProviderHelper {

    public abstract boolean canRainOrSnow();

    public abstract boolean hasSunset();

    public abstract long getDayLength();

    public abstract Class<? extends IChunkProvider> getChunkProviderClass();

    public abstract Class<? extends WorldChunkManager> getWorldChunkManagerClass();

    @Override
    public void setDimension(int var1) {
        this.dimensionId = var1;
        super.setDimension(var1);
    }

    @Override
    public void updateWeather() {
        if (this.canRainOrSnow()) {
            super.updateWeather();
        } else {
            this.worldObj.getWorldInfo().setRainTime(0);
            this.worldObj.getWorldInfo().setRaining(false);
            this.worldObj.getWorldInfo().setThunderTime(0);
            this.worldObj.getWorldInfo().setThundering(false);
            this.worldObj.rainingStrength = 0.0f;
            this.worldObj.thunderingStrength = 0.0f;
        }
    }
    
    @Override
    public int getActualHeight() {
        return 256; // Ensure the dimension behaves like the Overworld
    }

    @Override
    public String getWelcomeMessage() {
        return "Entering " + this.getCelestialBody().getLocalizedName();
    }

    @Override
    public String getDepartMessage() {
        return "Leaving " + this.getCelestialBody().getLocalizedName();
    }

    @Override
    public boolean canBlockFreeze(int x, int y, int z, boolean byWater) {
        return this.canRainOrSnow();
    }

    @Override
    public boolean canDoLightning(Chunk chunk) {
        return this.canRainOrSnow();
    }

    @Override
    public boolean canDoRainSnowIce(Chunk chunk) {
        return this.canRainOrSnow();
    }

    @Override
    public float[] calcSunriseSunsetColors(float var1, float var2) {
        return this.hasSunset() ? super.calcSunriseSunsetColors(var1, var2) : null;
    }

    @Override
    public float calculateCelestialAngle(long par1, float par3) {
        long dayLength = this.getDayLength();
        if (dayLength <= 0) {
            dayLength = 24000L; // Fallback to default day length
        }

        int j = (int)(par1 % dayLength);
        float f1 = (j + par3) / dayLength - 0.25f;

        if (f1 < 0.0f) {
            f1 += 1.0f;
        }
        if (f1 > 1.0f) {
            f1 -= 1.0f;
        }

        float f2 = f1;
        f1 = 0.5f - MathHelper.cos(f1 * 3.1415927f) / 2.0f;
        return f2 + (f1 - f2) / 3.0f;
    }


    @Override
    public boolean isSkyColored() {
        return true;
    }

    @Override
    public boolean isSurfaceWorld() {
        return true;  // Makes the dimension always treated as a surface world like the Overworld
    }

    @Override
    public boolean canRespawnHere() {
        return true;
    }

    @Override
    public boolean netherPortalsOperational() {
        return false;
    }

    @Override
    public IChunkProvider createChunkGenerator() {
        try {
            Class<? extends IChunkProvider> chunkProviderClass = this.getChunkProviderClass();
            Constructor<?>[] constructors = chunkProviderClass.getConstructors();
            for (Constructor<?> constr : constructors) {
                if (Arrays.equals(constr.getParameterTypes(), new Object[]{World.class, Long.TYPE, Boolean.TYPE}))
                    return (IChunkProvider)constr.newInstance(new Object[]{this.worldObj, this.worldObj.getSeed(), this.worldObj.getWorldInfo().isMapFeaturesEnabled()});
                if (constr.getParameterTypes().length != 0) {
                    continue;
                }
                return (IChunkProvider)constr.newInstance(new Object[0]);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void registerWorldChunkManager() {
        if (this.getWorldChunkManagerClass() == null) {
            super.registerWorldChunkManager();
        } else {
            try {
                Constructor<?>[] constructors;
                Class<? extends WorldChunkManager> chunkManagerClass = this.getWorldChunkManagerClass();
                for (Constructor<?> constr : constructors = chunkManagerClass.getConstructors()) {
                    if (Arrays.equals(constr.getParameterTypes(), new Object[]{World.class})) {
                        this.worldChunkMgr = (WorldChunkManager)constr.newInstance(new Object[]{this.worldObj});
                        continue;
                    }
                    if (constr.getParameterTypes().length != 0) {
                        continue;
                    }
                    this.worldChunkMgr = (WorldChunkManager)constr.newInstance(new Object[0]);
                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public boolean shouldMapSpin(String entity, double x, double y, double z) {
        return false;
    }

    @Override
    public float getSolarSize() {
        return 1.0f / this.getCelestialBody().getRelativeDistanceFromCenter().unScaledDistance;
    }
}


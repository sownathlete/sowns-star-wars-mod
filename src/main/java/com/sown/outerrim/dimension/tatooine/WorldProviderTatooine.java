/*
 * Decompiled with CFR 0.148.
 *
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.World
 *  net.minecraft.world.biome.WorldChunkManager
 *  net.minecraft.world.chunk.IChunkProvider
 */
package com.sown.outerrim.dimension.tatooine;

import com.sown.outerrim.dimension.deathstar.DrawSkybox;
import com.sown.util.world.creation.GlobalPreset;
import com.sown.util.world.creation.WorldProviderHelper;
import com.sown.util.world.creation.Vector3;
import com.sown.util.world.creation.WorldProviderSpace;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.biome.WorldChunkManager;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraftforge.client.IRenderHandler;

public class WorldProviderTatooine
extends WorldProviderSpace
implements WorldProviderHelper{
    //@Override
    //public Vector3 getFogColor() {
    //    float f = 1.0f - this.getStarBrightness(1.0f);
    //    //return new Vector3(0.788f * f, 0.58f * f, 0.322f * f);
    //    return new Vector3(0.576,0.627,0.663);
    //}

    //@Override
    //public Vector3 getSkyColor() {
    //float f = 1.0f - this.getCustomStarBrightness(1.0f);
    //return new Vector3(0.4f * f, 0.69803923f * f, 1.2439024f * f);
    //return new Vector3(0. * f,0.329 * f,0.569 * f);
    //}


    @Override
    public boolean canRainOrSnow() {
        return false;
    }
    
    public boolean canRespawnHere()
    {
        return true;
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
    
    @SideOnly(Side.CLIENT)
    private IRenderHandler skyRenderer;

    @Override
    @SideOnly(Side.CLIENT)
    public IRenderHandler getSkyRenderer() {
        if (skyRenderer == null) {
            skyRenderer = new DrawTatooineSky();
        }
        return skyRenderer;
    }

//    @SideOnly(Side.CLIENT)
//    @Override
//    public IRenderHandler getSkyRenderer() {
//        if (this.skyRenderer == null) {
//            // "outerrim" is your modid, "deathStar" is the sub-folder under textures/sky
//            this.skyRenderer = new DrawSkybox("outerrim", "tatooine");
//        }
//        return this.skyRenderer;
//    }

    @Override
    public long getDayLength() {
        return 24000L;
    }

    public boolean shouldForceRespawn() {
        return true;
    }

    @Override
    public Class<? extends IChunkProvider> getChunkProviderClass() {
        return ChunkProviderTatooine.class;
    }

    @Override
    public Class<? extends WorldChunkManager> getWorldChunkManagerClass() {
        return WorldChunkManagerTatooine.class;
    }

    public boolean isSurfaceWorld() {
        return true;
    }

    //    @Override
    //    @SideOnly(value=Side.CLIENT)
    //    public float getStarBrightness(float par1) {
    //        float f1 = this.worldObj.getCelestialAngle(par1);
    //        float f2 = 1.0f - (MathHelper.cos(f1 * 6.2831855f) * 2.0f + 0.25f);
    //        if (f2 < 0.0f) {
    //            f2 = 1.0f;
    //        }
    //        if (f2 > 1.0f) {
    //            f2 = 1.0f;
    //        }
    //        return f2 * f2 * 0.7f;
    //    }
    //
    //    @SideOnly(value=Side.CLIENT)
    //    public float getCustomStarBrightness(float par1) {
    //        float f1 = this.worldObj.getCelestialAngle(par1);
    //        float f2 = 1.0f - (MathHelper.cos(f1 * 6.2831855f) * 2.0f + 0.25f);
    //        if (f2 < 0.0f) {
    //            f2 = 1.0f;
    //        }
    //        if (f2 > 1.0f) {
    //            f2 = 1.0f;
    //        }
    //        return f2 * f2 * (this.getSunBrightness(1.0f) * -1.0f) * f1;
    //    }

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


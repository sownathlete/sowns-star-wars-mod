/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.biome.WorldChunkManager
 *  net.minecraft.world.chunk.IChunkProvider
 */
package com.sown.outerrim.dimension.crait;

import java.util.ArrayList;
import java.util.List;

import com.sown.outerrim.OuterRimResources;
import com.sown.outerrim.dimension.naboo.BiomeGenNabooPlains;
import com.sown.util.world.creation.GlobalPreset;
import com.sown.util.world.creation.Vector3;
import com.sown.util.world.creation.WorldProviderSpace;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.BiomeGenPlains;
import net.minecraft.world.biome.WorldChunkManager;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraftforge.client.IRenderHandler;

public class CraitProvider
extends WorldProviderSpace{
    public static final List<BiomeGenBase> biomes = new ArrayList<>();
    public static final BiomeGenBase crait = (new BiomeGenCrait(OuterRimResources.ConfigOptions.biomeCraitId)).setColor(7580259).setBiomeName("Crait Salt Flats");
    public static final BiomeGenBase craitMountains = (new BiomeGenCraitMountains(OuterRimResources.ConfigOptions.biomeCraitMountainsId)).setColor(7580259).setBiomeName("Crait Mountains");
    @SideOnly(value=Side.CLIENT)
    private IRenderHandler skyRenderer;

    static {
        // Add other biomes as needed
    	biomes.add(crait);
    	biomes.add(craitMountains);
    }
    
    @Override
    public boolean canRainOrSnow() {
        return false;
    }

    @Override
    public boolean hasSunset() {
        return true;
    }

    @Override
    public boolean canBlockFreeze(int x, int y, int z, boolean byWater) {
        return false;
    }

    @Override
    public long getDayLength() {
        return 24000L;
    }

    @Override
    public void updateWeather() {
            this.worldObj.getWorldInfo().setRaining(false);
        }
    
    public boolean isRaining() {
        return false;
    }

    public boolean shouldForceRespawn() {
        return true;
    }

    @Override
    public Class<? extends IChunkProvider> getChunkProviderClass() {
        return BiomeChunkProviderCrait.class;
    }

    @Override
    public Class<? extends WorldChunkManager> getWorldChunkManagerClass() {
        return WorldChunkManagerCrait.class;
    }

    public double getHorizon() {
        return 44.0;
    }

    @SideOnly(value=Side.CLIENT)
    public IRenderHandler getSkyRenderer() {
        return null;
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
    
    @Override
    public float getCloudHeight() {
        return 256.0F;
    }
    
    @Override
    public boolean doesXZShowFog(int x, int z) {
        return true;
    }
}

/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.biome.WorldChunkManager
 *  net.minecraft.world.chunk.IChunkProvider
 */
package com.sown.outerrim.dimension.hoth;

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

public class HothProvider
extends WorldProviderSpace{
    public static final List<BiomeGenBase> biomes = new ArrayList<>();
    public static final BiomeGenBase hoth = (new BiomeGenHoth(OuterRimResources.ConfigOptions.biomeHothId)).setColor(7580259).setBiomeName("Hoth Plains");
    public static final BiomeGenBase hothMountains = (new BiomeGenHothMountains(OuterRimResources.ConfigOptions.biomeHothMountainsId)).setColor(7580259).setBiomeName("Hoth Mountains");
    @SideOnly(value=Side.CLIENT)
    private IRenderHandler skyRenderer;

    static {
        // Add other biomes as needed
    	biomes.add(hoth);
    	biomes.add(hothMountains);
    }
    
    @Override
    public boolean canRainOrSnow() {
        return true;
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
            this.worldObj.getWorldInfo().setRainTime(0);
            this.worldObj.getWorldInfo().setRaining(true);
            this.worldObj.rainingStrength = 1.0f;
        }
    
    public boolean isRaining() {
        return true;
    }

    public boolean shouldForceRespawn() {
        return true;
    }

    @Override
    public Class<? extends IChunkProvider> getChunkProviderClass() {
        return BiomeChunkProviderHoth.class;
    }

    @Override
    public Class<? extends WorldChunkManager> getWorldChunkManagerClass() {
        return WorldChunkManagerHoth.class;
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
        return 128.0F;  // This is the typical cloud height for the Overworld in Minecraft. Adjust as necessary.
    }

    @Override
    public boolean doesXZShowFog(int x, int z) {
        return true;  // This ensures that the (x, z) coordinates won't show fog, which can obscure clouds.
    }
}

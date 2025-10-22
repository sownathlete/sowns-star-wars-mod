/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.biome.WorldChunkManager
 *  net.minecraft.world.chunk.IChunkProvider
 */
package com.sown.outerrim.dimension.dagobah;

import java.util.ArrayList;
import java.util.List;

import com.sown.outerrim.OuterRimResources;
import com.sown.util.world.creation.GlobalPreset;
import com.sown.util.world.creation.WorldProviderSpace;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.WorldChunkManager;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraftforge.client.IRenderHandler;

public class DagobahProvider
extends WorldProviderSpace{
    public static final List<BiomeGenBase> biomes = new ArrayList<>();
    protected static final BiomeGenBase.Height height_PartiallySubmerged = new BiomeGenBase.Height(-0.2F, 0.1F);
    public static final BiomeGenBase dagobah = (new BiomeGenDagobah(OuterRimResources.ConfigOptions.biomeDagobahId)).setBiomeName("Dagobah Swamp").setHeight(height_PartiallySubmerged).setTemperatureRainfall(0.8F, 0.9F);
    @SideOnly(value=Side.CLIENT)
    private IRenderHandler skyRenderer;
    

    static {
        // Add other biomes as needed
    	biomes.add(dagobah);
    }
    
    @Override
    public boolean canRainOrSnow() {
        return true;
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public Vec3 getFogColor(float celestialAngle, float partialTicks) {
        // calculate brightness (clamped 0–1)
        float brightness = MathHelper.cos(celestialAngle * (float)Math.PI * 2F) * 2F + 0.5F;
        brightness = Math.max(0F, Math.min(1F, brightness));

        // base color = #172A28   R=23, G=42, B=40
        float baseR = 23f  / 255f;  //  0.0902
        float baseG = 42f  / 255f;  //  0.1647
        float baseB = 40f  / 255f;  //  0.1569

        // apply your original multipliers
        float red   = baseR * (brightness * 0.94F + 0.06F);
        float green = baseG * (brightness * 0.94F + 0.06F);
        float blue  = baseB * (brightness * 0.91F + 0.09F);

        return Vec3.createVectorHelper(red, green, blue);
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
    
    public boolean isRaining() {
        return false;
    }

    public boolean shouldForceRespawn() {
        return true;
    }

    @Override
    public Class<? extends IChunkProvider> getChunkProviderClass() {
        return BiomeChunkProviderDagobah.class;
    }

    @Override
    public Class<? extends WorldChunkManager> getWorldChunkManagerClass() {
        return WorldChunkManagerDagobah.class;
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
        return 100.0F;  // This is the typical cloud height for the Overworld in Minecraft. Adjust as necessary.
    }

    @Override
    public boolean doesXZShowFog(int x, int z) {
        return true;  // This ensures that the (x, z) coordinates won't show fog, which can obscure clouds.
    }
}

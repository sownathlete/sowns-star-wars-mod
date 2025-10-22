/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.biome.WorldChunkManager
 *  net.minecraft.world.chunk.IChunkProvider
 */
package com.sown.outerrim.dimension.bespin;

import java.util.ArrayList;
import java.util.List;

import com.sown.outerrim.OuterRimResources;
import com.sown.outerrim.dimension.naboo.BiomeGenNabooPlains;
import com.sown.util.world.creation.GlobalPreset;
import com.sown.util.world.creation.Vector3;
import com.sown.util.world.creation.WorldProviderSpace;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.BiomeGenPlains;
import net.minecraft.world.biome.WorldChunkManager;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraftforge.client.IRenderHandler;

public class BespinProvider
extends WorldProviderSpace{
    public static final List<BiomeGenBase> biomes = new ArrayList<>();
    public static final BiomeGenBase bespin = (new BiomeGenBespin(OuterRimResources.ConfigOptions.biomeBespinId)).setColor(7580259).setBiomeName("Bespin");
    @SideOnly(value=Side.CLIENT)
    private IRenderHandler skyRenderer;

    static {
        // Add other biomes as needed
    	biomes.add(bespin);
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
    @SideOnly(Side.CLIENT)
    public Vec3 getFogColor(float celestialAngle, float partialTicks) {
        // get brightness (clamped 0.01.0)
        float brightness = MathHelper.cos(celestialAngle * (float)Math.PI * 2.0F) * 2.0F + 0.5F;
        if (brightness < 0.0F) brightness = 0.0F;
        if (brightness > 1.0F) brightness = 1.0F;

        // base color = #FFC87E   R=255, G=200, B=126
        float baseR = 255f / 255f;  // = 1.0F
        float baseG = 200f / 255f;  //   0.7843F
        float baseB = 126f / 255f;  //   0.4941F

        // apply your original brightness multipliers
        float red   = baseR * (brightness * 0.94F + 0.06F);
        float green = baseG * (brightness * 0.94F + 0.06F);
        float blue  = baseB * (brightness * 0.91F + 0.09F);

        // debug if you want
        // System.out.println("Fog color: R=" + red + ", G=" + green + ", B=" + blue);

        return Vec3.createVectorHelper(red, green, blue);
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
        return true;
    }

    public boolean shouldForceRespawn() {
        return true;
    }

    @Override
    public Class<? extends IChunkProvider> getChunkProviderClass() {
        return BiomeChunkProviderBespin.class;
    }

    @Override
    public Class<? extends WorldChunkManager> getWorldChunkManagerClass() {
        return WorldChunkManagerBespin.class;
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
    public boolean canRespawnHere() {
        return true;
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

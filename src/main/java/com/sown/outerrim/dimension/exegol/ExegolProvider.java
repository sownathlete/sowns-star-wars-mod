/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.biome.WorldChunkManager
 *  net.minecraft.world.chunk.IChunkProvider
 */
package com.sown.outerrim.dimension.exegol;

import java.util.Random;

import com.sown.outerrim.OuterRimResources;
import com.sown.outerrim.dimension.BiomeChunkProviderGeneric;
import com.sown.outerrim.dimension.kessel.ChunkProviderKessel;
import com.sown.outerrim.dimension.kessel.WorldChunkManagerKessel;
import com.sown.outerrim.dimension.tatooine.WorldChunkManagerTatooine;
import com.sown.util.world.creation.GlobalPreset;
import com.sown.util.world.creation.Vector3;
import com.sown.util.world.creation.WorldProviderHelper;
import com.sown.util.world.creation.WorldProviderSpace;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent.WorldTickEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.Entity;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.Vec3;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.WorldChunkManager;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraftforge.common.MinecraftForge;

public class ExegolProvider extends WorldProviderSpace {
    public static final BiomeGenBase exegol = new BiomeGenExegol(OuterRimResources.ConfigOptions.biomeExegolId).setColor(112).setBiomeName("Exegol").setDisableRain().setTemperatureRainfall(2.0F, 0.0F);

    public ExegolProvider() {
        MinecraftForge.EVENT_BUS.register(this); // Register world tick event
    }

    @Override
    public boolean canRainOrSnow() {
        return true; // Ensure storms
    }

    @Override
    public boolean hasSunset() {
        return false; // No sunset, eternal night
    }

    @Override
    public boolean canBlockFreeze(int x, int y, int z, boolean byWater) {
        return false;
    }

    @Override
    public void updateWeather() {
        this.worldObj.getWorldInfo().setRainTime(100000); // Long storm duration
        this.worldObj.getWorldInfo().setRaining(true); // No rain!
        this.worldObj.getWorldInfo().setThunderTime(100000);
        this.worldObj.getWorldInfo().setThundering(true); // Thunder still happens

        this.worldObj.rainingStrength = 1.0f; // Ensure rain particles don't appear
        this.worldObj.thunderingStrength = 1.0f; // Keep thunderstorm intensity

        // Lock time to midnight
        this.worldObj.getWorldInfo().setWorldTime(18000L);
    }
    

    @SideOnly(value = Side.CLIENT)
    public Vec3 getFogColor(float par1, float par2) {
        int color = 0x064053; // Dark stormy fog
        float r = (color >> 16 & 255) / 255.0f;
        float g = (color >> 8 & 255) / 255.0f;
        float b = (color & 255) / 255.0f;
        return Vec3.createVectorHelper(r, g, b);
    }

    @SideOnly(value = Side.CLIENT)
    public Vec3 getSkyColor(Entity cameraEntity, float partialTicks) {
        int color = 0x042A3D; // Dark blue sky
        float r = (color >> 16 & 255) / 255.0f;
        float g = (color >> 8 & 255) / 255.0f;
        float b = (color & 255) / 255.0f;
        return Vec3.createVectorHelper(r, g, b);
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
        return BiomeChunkProviderExegol.class;
    }

    @Override
    public Class<? extends WorldChunkManager> getWorldChunkManagerClass() {
        return WorldChunkManagerExegol.class;
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
    public String getDimensionName() {
        return "Exegol";
    }

	@Override
	public GlobalPreset getCelestialBody() {
		// TODO Auto-generated method stub
		return null;
	}
}
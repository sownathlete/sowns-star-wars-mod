package com.sown.outerrim.dimension.dathomir;

import com.sown.util.world.creation.GlobalPreset;
import com.sown.util.world.creation.WorldProviderHelper;
import com.sown.util.world.creation.WorldProviderSpace;
import com.sown.outerrim.dimension.BiomeChunkProviderGeneric;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.Entity;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraft.world.biome.WorldChunkManager;
import net.minecraft.world.chunk.IChunkProvider;

public class WorldProviderDathomir extends WorldProviderSpace implements WorldProviderHelper {

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
    public Class<? extends IChunkProvider> getChunkProviderClass() {
        return BiomeChunkProviderGeneric.class;
    }

    @Override
    public Class<? extends WorldChunkManager> getWorldChunkManagerClass() {
        return WorldChunkManagerDathomir.class;
    }

    @Override
    public boolean isSurfaceWorld() {
        return true;
    }

    @Override
    public String getWelcomeMessage() {
        return "You have entered Dathomir";
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
    public boolean canCoordinateBeSpawn(int x, int z) {
        return true;
    }

    @Override
    public float getGravity() {
        return 0.035f;
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
        return 0.9f;
    }

    @Override
    public float getSoundVolReductionAmount() {
        return 4.0f;
    }

    @Override
    public boolean hasBreathableAtmosphere() {
        return true;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public Vec3 getSkyColor(Entity cameraEntity, float partialTicks) {
        int color = 0x2B1616; // dark reddish-brown Dathomir sky
        float r = ((color >> 16) & 255) / 255.0f;
        float g = ((color >> 8) & 255) / 255.0f;
        float b = (color & 255) / 255.0f;
        return Vec3.createVectorHelper(r, g, b);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public Vec3 getFogColor(float par1, float par2) {
        int color = 0x1E0D0D; // shadowy reddish fog
        float r = ((color >> 16) & 255) / 255.0f;
        float g = ((color >> 8) & 255) / 255.0f;
        float b = (color & 255) / 255.0f;
        return Vec3.createVectorHelper(r, g, b);
    }

    @Override
    public float getThermalLevelModifier() {
        return 0.1f;
    }

    @Override
    public float getWindLevel() {
        return 2.5f;
    }

    @Override
    public boolean canSpaceshipTierPass(int tier) {
        return tier >= 1;
    }

    @Override
    public GlobalPreset getCelestialBody() {
        return null;
    }

    @Override
    public String getDimensionName() {
        return "Dathomir";
    }
}

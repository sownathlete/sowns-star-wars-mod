package com.sown.outerrim.dimension.kamino;

import com.sown.outerrim.OuterRimResources;
import com.sown.outerrim.dimension.deathstar.DrawSkybox;
import com.sown.outerrim.dimension.kamino.BiomeChunkProviderKamino;
import com.sown.outerrim.dimension.kamino.BiomeGenKamino;
import com.sown.outerrim.dimension.kamino.WorldChunkManagerKamino;
import com.sown.util.world.creation.GlobalPreset;
import com.sown.util.world.creation.WorldProviderSpace;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.Entity;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.WorldChunkManager;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.storage.WorldInfo;
import net.minecraftforge.client.IRenderHandler;

public class KaminoProvider
extends WorldProviderSpace {
    public static final BiomeGenBase kamino = new BiomeGenKamino(OuterRimResources.ConfigOptions.biomeKaminoId).setColor(112).setBiomeName("Kamino");

    @SideOnly(Side.CLIENT)
    private IRenderHandler skyRenderer;

    @Override
    public boolean canRainOrSnow() {
        return true;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public IRenderHandler getSkyRenderer() {
        if (this.skyRenderer == null) {
            // "outerrim" is your modid, "deathStar" is the sub-folder under textures/sky
            this.skyRenderer = new DrawSkybox("outerrim", "kamino");
        }
        return this.skyRenderer;
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
        this.worldObj.getWorldInfo().setThunderTime(0);
        this.worldObj.getWorldInfo().setThundering(true);
        this.worldObj.rainingStrength = 1.0f;
        this.worldObj.thunderingStrength = 1.0f;
    }

    public boolean isRaining() {
        return true;
    }

    @SideOnly(value=Side.CLIENT)
    public Vec3 getFogColor(float par1, float par2) {
        int color = 2501692;
        float r = (float)(color >> 16 & 0xFF) / 255.0f;
        float g = (float)(color >> 8 & 0xFF) / 255.0f;
        float b = (float)(color & 0xFF) / 255.0f;
        return Vec3.createVectorHelper((double)r, (double)g, (double)b);
    }

    @SideOnly(value=Side.CLIENT)
    public Vec3 getSkyColor(Entity cameraEntity, float partialTicks) {
        int color = 1645867;
        float r = (float)(color >> 16 & 0xFF) / 255.0f;
        float g = (float)(color >> 8 & 0xFF) / 255.0f;
        float b = (float)(color & 0xFF) / 255.0f;
        return Vec3.createVectorHelper((double)r, (double)g, (double)b);
    }

    public boolean shouldForceRespawn() {
        return true;
    }

    @Override
    public Class<? extends IChunkProvider> getChunkProviderClass() {
        return BiomeChunkProviderKamino.class;
    }

    @Override
    public Class<? extends WorldChunkManager> getWorldChunkManagerClass() {
        return WorldChunkManagerKamino.class;
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
    public boolean isSurfaceWorld() {
        return true;
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


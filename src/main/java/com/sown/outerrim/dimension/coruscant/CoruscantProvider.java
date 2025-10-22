package com.sown.outerrim.dimension.coruscant;

import com.sown.outerrim.OuterRimResources;
import com.sown.outerrim.dimension.deathstar.DrawSkybox;
import com.sown.util.world.creation.GlobalPreset;
import com.sown.util.world.creation.WorldProviderHelper;
import com.sown.util.world.creation.WorldProviderSpace;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.entity.Entity;
import net.minecraft.util.Vec3;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.WorldChunkManager;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraftforge.client.IRenderHandler;

public class CoruscantProvider
extends WorldProviderSpace
implements WorldProviderHelper {

    public static final BiomeGenBase coruscant =
        new BiomeGenCoruscant(OuterRimResources.ConfigOptions.biomeCoruscantId)
            .setColor(112)
            .setBiomeName("Coruscant");

    @SideOnly(Side.CLIENT)
    private IRenderHandler skyRenderer;

    @SideOnly(Side.CLIENT)
    private IRenderHandler cloudRenderer;

    @Override
    public void registerWorldChunkManager() {
        this.worldChunkMgr = new WorldChunkManagerCoruscant();
        this.dimensionId = OuterRimResources.ConfigOptions.dimCoruscantId;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public IRenderHandler getSkyRenderer() {
        if (this.skyRenderer == null) {
            this.skyRenderer = new DrawSkybox("outerrim", "coruscant");
        }
        return this.skyRenderer;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public IRenderHandler getCloudRenderer() {
        if (this.cloudRenderer == null) {
            this.cloudRenderer = new EmptyCloudRenderer();
        }
        return this.cloudRenderer;
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
    public long getWorldTime() {
        return 12450L;
    }

    public boolean shouldForceRespawn() {
        return true;
    }

    @Override
    public Class<? extends IChunkProvider> getChunkProviderClass() {
        return BiomeChunkProviderCoruscant.class;
    }

    @Override
    public Class<? extends WorldChunkManager> getWorldChunkManagerClass() {
        return WorldChunkManagerCoruscant.class;
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
        return false;
    }

    @Override
    public GlobalPreset getCelestialBody() {
        return null;
    }

    @Override
    public String getDimensionName() {
        return "Coruscant";
    }

    @SideOnly(Side.CLIENT)
    private static class EmptyCloudRenderer extends IRenderHandler {
        @Override
        public void render(float partialTicks, WorldClient world, net.minecraft.client.Minecraft mc) {
        }
    }
}

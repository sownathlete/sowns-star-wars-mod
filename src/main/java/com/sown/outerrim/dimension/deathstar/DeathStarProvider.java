package com.sown.outerrim.dimension.deathstar;

import com.sown.outerrim.OuterRimResources;
import com.sown.outerrim.dimension.BiomeChunkProviderGeneric;
import com.sown.util.world.creation.GlobalPreset;
import com.sown.util.world.creation.WorldProviderHelper;
import com.sown.util.world.creation.WorldProviderSpace;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.util.Vec3;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.WorldChunkManager;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.storage.WorldInfo;
import net.minecraftforge.client.IRenderHandler;
import org.lwjgl.opengl.GL11;

public class DeathStarProvider extends WorldProviderSpace implements WorldProviderHelper {
    public static final BiomeGenBase deathStar =
        new BiomeGenDeathStar(OuterRimResources.ConfigOptions.biomeDeathStarId)
            .setColor(112)
            .setBiomeName("Death Star");

    @SideOnly(Side.CLIENT)
    private IRenderHandler skyRenderer;

    @SideOnly(Side.CLIENT)
    private IRenderHandler cloudRenderer;

    @SideOnly(Side.CLIENT)
    @Override
    public IRenderHandler getSkyRenderer() {
        if (this.skyRenderer == null) this.skyRenderer = new DrawSpaceSky();
        return this.skyRenderer;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public IRenderHandler getCloudRenderer() {
        if (this.cloudRenderer == null) this.cloudRenderer = new EmptyCloudRenderer();
        return this.cloudRenderer;
    }

    @Override
    public void registerWorldChunkManager() {
        this.worldChunkMgr = new WorldChunkManagerDeathStar();
        this.dimensionId = OuterRimResources.ConfigOptions.dimDeathStarId;
    }

    @Override
    public boolean canRainOrSnow() { return false; }

    @Override
    public boolean hasSunset() { return false; }

    @Override
    public boolean canBlockFreeze(int x, int y, int z, boolean byWater) { return false; }

    @Override
    public long getDayLength() { return 24000L; }

    @Override
    public void updateWeather() {
        if (worldObj.isRemote) return;
        WorldInfo info = worldObj.getWorldInfo();
        info.setWorldTime(6000L);
        info.setRaining(false);
        info.setThundering(false);
    }

    public boolean shouldForceRespawn() { return true; }

    @Override
    public Class<? extends IChunkProvider> getChunkProviderClass() {
        return BiomeChunkProviderGeneric.class;
    }

    @Override
    public Class<? extends WorldChunkManager> getWorldChunkManagerClass() {
        return WorldChunkManagerDeathStar.class;
    }

    @Override
    public double getHorizon() { return 44.0; }

    @Override
    public int getAverageGroundLevel() { return 44; }

    @Override
    public boolean canCoordinateBeSpawn(int x, int z) { return true; }

    @Override
    public float getGravity() { return 0.03f; }

    @Override
    public int getHeight() { return 800; }

    @Override
    public double getMeteorFrequency() { return 0.0; }

    @Override
    public double getFuelUsageMultiplier() { return 1.0; }

    @Override
    public float getFallDamageModifier() { return 0.95f; }

    @Override
    public float getSoundVolReductionAmount() { return 5.0f; }

    @Override
    public boolean hasBreathableAtmosphere() { return true; }

    @Override
    public float getThermalLevelModifier() { return 0.0f; }

    @Override
    public float getWindLevel() { return 3.0f; }

    @Override
    public boolean canSpaceshipTierPass(int tier) { return false; }

    @Override
    public GlobalPreset getCelestialBody() { return null; }

    @Override
    public String getDimensionName() { return "Death Star"; }

    @SideOnly(Side.CLIENT)
    @Override
    public Vec3 getFogColor(float celestialAngle, float partialTicks) {
        GL11.glDisable(GL11.GL_FOG);
        return Vec3.createVectorHelper(0.0, 0.0, 0.0);
    }
    
    @Override
    public float calculateCelestialAngle(long worldTime, float partialTicks) {
        return 0.0F;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public boolean doesXZShowFog(int x, int z) { 
        GL11.glDisable(GL11.GL_FOG);
        return false; 
    }

    @SideOnly(Side.CLIENT)
    @Override
    public float getStarBrightness(float partialTicks) {
        return 1.0F; // removes ambient darkness/shadows
    }

    @Override
    public boolean isSurfaceWorld() {
        return false; // prevents vanilla sky lighting and ambient dimming
    }

    @Override
    public float getCloudHeight() {
        return Float.MAX_VALUE; // no lighting transitions
    }

    @Override
    public boolean isSkyColored() {
        return false; // keeps lighting neutral, no tinting
    }

    @SideOnly(Side.CLIENT)
    private static class EmptyCloudRenderer extends IRenderHandler {
        @Override
        public void render(float partialTicks, net.minecraft.client.multiplayer.WorldClient world, net.minecraft.client.Minecraft mc) {}
    }
}
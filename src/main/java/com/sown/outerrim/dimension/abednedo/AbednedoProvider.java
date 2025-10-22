package com.sown.outerrim.dimension.abednedo;

import com.sown.outerrim.OuterRimResources;
import com.sown.util.world.creation.GlobalPreset;
import com.sown.util.world.creation.WorldProviderSpace;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.Entity;
import net.minecraft.util.Vec3;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.WorldChunkManager;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.storage.WorldInfo;
import java.util.Random;

public class AbednedoProvider extends WorldProviderSpace {
    public static final BiomeGenBase abednedo =
        new BiomeGenAbednedo(OuterRimResources.ConfigOptions.biomeAbednedoId)
            .setColor(0x6E9966)
            .setBiomeName("Abednedo");

    private final Random weatherRng = new Random();

    @Override
    public void registerWorldChunkManager() {
        this.worldChunkMgr = new WorldChunkManagerAbednedo();
        this.dimensionId = OuterRimResources.ConfigOptions.dimAbednedoId;
    }

    @Override public boolean canRainOrSnow() { return true; }
    @Override public boolean hasSunset() { return false; }
    @Override public boolean canBlockFreeze(int x, int y, int z, boolean byWater) { return false; }
    @Override public long getDayLength() { return 24000L; }

    @Override
    public void updateWeather() {
        if (worldObj.isRemote) return;
        WorldInfo info = worldObj.getWorldInfo();
        int rainTime = info.getRainTime();
        int thunderTime = info.getThunderTime();
        if (rainTime > 0) info.setRainTime(rainTime - 1);
        if (thunderTime > 0) info.setThunderTime(thunderTime - 1);
        if (info.getRainTime() <= 0) {
            int next = weatherRng.nextInt(100);
            if (next < 70) {
                info.setRaining(false);
                info.setRainTime(16000 + weatherRng.nextInt(16000));
            } else {
                info.setRaining(true);
                info.setRainTime(8000 + weatherRng.nextInt(8000));
            }
        }
        if (info.getThunderTime() <= 0) {
            if (info.isRaining() && weatherRng.nextInt(100) < 15) {
                info.setThundering(true);
                info.setThunderTime(3000 + weatherRng.nextInt(4000));
            } else {
                info.setThundering(false);
                info.setThunderTime(10000 + weatherRng.nextInt(10000));
            }
        }
        worldObj.rainingStrength = info.isRaining() ? 1.0F : 0.0F;
        worldObj.thunderingStrength = info.isThundering() ? 0.5F : 0.0F;
    }

    public boolean shouldForceRespawn() { return true; }

    @Override public Class<? extends IChunkProvider> getChunkProviderClass() { return com.sown.outerrim.dimension.BiomeChunkProviderGeneric.class; }
    @Override public Class<? extends WorldChunkManager> getWorldChunkManagerClass() { return WorldChunkManagerAbednedo.class; }
    @Override public double getHorizon() { return 63.0; }
    @Override public int getAverageGroundLevel() { return 63; }
    @Override public boolean canCoordinateBeSpawn(int x, int z) { return true; }
    @Override public float getGravity() { return 0.08f; }
    @Override public int getHeight() { return 256; }
    @Override public double getMeteorFrequency() { return 0.0; }
    @Override public double getFuelUsageMultiplier() { return 1.0; }
    @Override public float getFallDamageModifier() { return 1.0f; }
    @Override public float getSoundVolReductionAmount() { return 0.0f; }
    @Override public boolean hasBreathableAtmosphere() { return true; }
    @Override public float getThermalLevelModifier() { return 0.0f; }
    @Override public float getWindLevel() { return 3.0f; }
    @Override public boolean canSpaceshipTierPass(int tier) { return tier >= 1; }
    @Override public GlobalPreset getCelestialBody() { return null; }
    @Override public boolean isSurfaceWorld() { return true; }
    @Override public String getDimensionName() { return "Abednedo"; }

    private static int lerpRGB(int a, int b, float t) {
        int ar = (a >> 16) & 255, ag = (a >> 8) & 255, ab = a & 255;
        int br = (b >> 16) & 255, bg = (b >> 8) & 255, bb = b & 255;
        int r = ar + Math.round((br - ar) * t);
        int g = ag + Math.round((bg - ag) * t);
        int bl = ab + Math.round((bb - ab) * t);
        return (r << 16) | (g << 8) | bl;
    }

    private static float clamp01(float v) {
        return v < 0.0f ? 0.0f : (v > 1.0f ? 1.0f : v);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public Vec3 getSkyColor(Entity cameraEntity, float partialTicks) {
        int day = 0xC9E5C5;   // muted light green
        int night = 0x121A12; // deep natural green
        float a = worldObj.getCelestialAngle(partialTicks);
        float t = (float)(Math.cos(a * Math.PI * 2.0) * 0.5 + 0.5);
        int c = lerpRGB(night, day, clamp01(t));
        float dim = 0.88f; // de-neon
        double r = ((c >> 16) & 255) / 255.0 * dim;
        double g = ((c >> 8) & 255) / 255.0 * dim;
        double b = (c & 255) / 255.0 * dim;
        return Vec3.createVectorHelper(r, g, b);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public Vec3 getFogColor(float par1, float par2) {
        int fog = 0x7C9E7A; // muted green fog
        float rain = worldObj.getRainStrength(par2);
        float th = worldObj.getWeightedThunderStrength(par2);
        float dim = 0.95f - 0.15f * rain - 0.05f * th;
        double r = ((fog >> 16) & 255) / 255.0 * dim;
        double g = ((fog >> 8) & 255) / 255.0 * dim;
        double b = (fog & 255) / 255.0 * dim;
        return Vec3.createVectorHelper(r, g, b);
    }
}

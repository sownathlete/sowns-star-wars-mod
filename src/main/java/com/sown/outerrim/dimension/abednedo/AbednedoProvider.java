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
import net.minecraft.world.World;
import java.util.Random;

public class AbednedoProvider extends WorldProviderSpace {
    public static final BiomeGenBase abednedo =
        new BiomeGenAbednedo(OuterRimResources.ConfigOptions.biomeAbednedoId)
            .setColor(0x70A068)
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

    @SideOnly(Side.CLIENT)
    @Override
    public Vec3 getSkyColor(Entity cameraEntity, float partialTicks) {
        int day = 0xB6F2A0;
        int night = 0x0B1A0B;
        float angle = worldObj.getCelestialAngle(partialTicks);
        float blend = (float)(Math.cos(angle * Math.PI * 2.0) * 0.5 + 0.5);
        float dr = ((day >> 16) & 255) / 255.0F;
        float dg = ((day >> 8) & 255) / 255.0F;
        float db = (day & 255) / 255.0F;
        float nr = ((night >> 16) & 255) / 255.0F;
        float ng = ((night >> 8) & 255) / 255.0F;
        float nb = (night & 255) / 255.0F;
        float r = nr + (dr - nr) * blend;
        float g = ng + (dg - ng) * blend;
        float b = nb + (db - nb) * blend;
        return Vec3.createVectorHelper(r, g, b);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public Vec3 getFogColor(float par1, float par2) {
        int fog = 0x4EA84A;
        float r = ((fog >> 16) & 255) / 255.0F;
        float g = ((fog >> 8) & 255) / 255.0F;
        float b = (fog & 255) / 255.0F;
        return Vec3.createVectorHelper(r, g, b);
    }
}

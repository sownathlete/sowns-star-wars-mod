package com.sown.outerrim.dimension.catoneimoidia;

import com.sown.util.world.creation.GlobalPreset;
import com.sown.util.world.creation.WorldProviderSpace;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.WorldChunkManager;
import net.minecraft.world.chunk.IChunkProvider;
import com.sown.outerrim.dimension.BiomeChunkProviderGenericPeaceful;

public class CatoNeimoidiaProvider extends WorldProviderSpace {
    public static final BiomeGenBase catoneimoidia = BiomeGenBase.plains;

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
        return BiomeChunkProviderGenericPeaceful.class;
    }

    @Override
    public Class<? extends WorldChunkManager> getWorldChunkManagerClass() {
        return WorldChunkManagerCatoNeimoidia.class;
    }

    @Override
    public double getHorizon() {
        return 50.0;
    }

    @Override
    public int getAverageGroundLevel() {
        return 40;
    }

    @Override
    public boolean canCoordinateBeSpawn(int x, int z) {
        return true;
    }

    @Override
    public float getGravity() {
        return 0.037f;
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
        return 0.8f;
    }

    @Override
    public float getSoundVolReductionAmount() {
        return 3.5f;
    }

    @Override
    public boolean hasBreathableAtmosphere() {
        return true;
    }

    @Override
    public float getThermalLevelModifier() {
        return 0.2f;
    }

    @Override
    public float getWindLevel() {
        return 5.0f;
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
        return "Cato Neimoidia";
    }
}

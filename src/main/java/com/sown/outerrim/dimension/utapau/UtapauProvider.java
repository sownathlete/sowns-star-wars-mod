package com.sown.outerrim.dimension.utapau;

import com.sown.outerrim.OuterRimResources;
import com.sown.util.world.creation.GlobalPreset;
import com.sown.util.world.creation.WorldProviderSpace;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.WorldChunkManager;
import net.minecraft.world.chunk.IChunkProvider;

public class UtapauProvider extends WorldProviderSpace {
    public static final BiomeGenBase utapau = new BiomeGenUtapau(OuterRimResources.ConfigOptions.biomeUtapauId)
            .setColor(9474192)
            .setBiomeName("Utapau");

    @Override
    public boolean canRainOrSnow() {
        return false;
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
        return BiomeChunkProviderUtapau.class;
    }

    @Override
    public Class<? extends WorldChunkManager> getWorldChunkManagerClass() {
        return WorldChunkManagerUtapau.class;
    }

    @Override
    public double getHorizon() {
        return 40.0;
    }
    
    @Override
    public float getCloudHeight() {
        return 384.0F;
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
        return 0.04f; // Slightly higher than Jakku
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
        return 0.90f; // Slightly more forgiving than Jakku due to sinkhole layers
    }

    @Override
    public float getSoundVolReductionAmount() {
        return 4.0f;
    }

    @Override
    public boolean hasBreathableAtmosphere() {
        return true;
    }

    @Override
    public float getThermalLevelModifier() {
        return -0.2f; // Cooler due to altitude + shade in sinkholes
    }

    @Override
    public float getWindLevel() {
        return 6.0f; // High winds at surface
    }

    @Override
    public boolean canSpaceshipTierPass(int tier) {
        return tier >= 1;
    }

    @Override
    public GlobalPreset getCelestialBody() {
        return null; // Replace with GlobalPreset.UTAPAU if you define one
    }

    @Override
    public String getDimensionName() {
        return "Utapau";
    }
}

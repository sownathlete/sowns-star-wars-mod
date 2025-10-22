package com.sown.outerrim.dimension.wbw;

import com.sown.outerrim.OuterRimResources;
import com.sown.outerrim.dimension.BiomeChunkProviderGeneric;
import com.sown.outerrim.dimension.kessel.ChunkProviderKessel;
import com.sown.outerrim.dimension.kessel.WorldChunkManagerKessel;
import com.sown.outerrim.dimension.tatooine.WorldChunkManagerTatooine;
import com.sown.util.world.creation.GlobalPreset;
import com.sown.util.world.creation.Vector3;
import com.sown.util.world.creation.WorldProviderHelper;
import com.sown.util.world.creation.WorldProviderSpace;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.WorldChunkManager;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.storage.WorldInfo;
import net.minecraftforge.client.IRenderHandler;

public class WBWProvider extends WorldProviderSpace {
    public static final BiomeGenBase worldBetweenWorlds =
        new BiomeGenWBW()
            .setColor(112)
            .setBiomeName("World Between Worlds");

    @SideOnly(Side.CLIENT)
    private IRenderHandler skyRenderer;

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
    
    @SideOnly(Side.CLIENT)
    @Override
    public IRenderHandler getSkyRenderer() {
        if (this.skyRenderer == null) {
            this.skyRenderer = new DrawWBWSky();
        }
        return this.skyRenderer;
    }
    
    @Override
    public void updateWeather() {
        // Only run on the server side
        if (worldObj.isRemote) {
            return;
        }

        // Grab the WorldInfo once
        WorldInfo info = worldObj.getWorldInfo();

        // 1) Lock time at tick 6000 (noon)
        info.setWorldTime(6000L);

        // 2) If it’s raining or thundering, clear both
        if (info.isRaining() || info.isThundering()) {
            info.setRaining(false);
            info.setThundering(false);
        }
    }
    
    @Override
    public Class<? extends IChunkProvider> getChunkProviderClass() {
        return BiomeChunkProviderWBW.class;
    }

    @Override
    public Class<? extends WorldChunkManager> getWorldChunkManagerClass() {
        return WorldChunkManagerWBW.class;
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
    public boolean canSpaceshipTierPass(int tier) {
        return false;
    }

    @Override
    public GlobalPreset getCelestialBody() {
        return null;
    }

    @Override
    public String getDimensionName() {
        return "World Between Worlds";
    }
}

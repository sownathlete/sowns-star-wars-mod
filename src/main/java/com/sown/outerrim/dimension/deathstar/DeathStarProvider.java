/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.biome.WorldChunkManager
 *  net.minecraft.world.chunk.IChunkProvider
 */
package com.sown.outerrim.dimension.deathstar;

import org.lwjgl.opengl.GL11;

import com.sown.outerrim.OuterRimResources;
import com.sown.outerrim.dimension.BiomeChunkProviderGeneric;
import com.sown.outerrim.dimension.kessel.ChunkProviderKessel;
import com.sown.outerrim.dimension.kessel.WorldChunkManagerKessel;
import com.sown.outerrim.dimension.tatooine.WorldChunkManagerTatooine;
import com.sown.outerrim.dimension.wbw.DrawWBWSky;
import com.sown.util.world.creation.GlobalPreset;
import com.sown.util.world.creation.Vector3;
import com.sown.util.world.creation.WorldProviderHelper;
import com.sown.util.world.creation.WorldProviderSpace;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.WorldChunkManager;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.storage.WorldInfo;
import net.minecraftforge.client.IRenderHandler;

public class DeathStarProvider
extends WorldProviderSpace
implements WorldProviderHelper {
    public static final BiomeGenBase deathStar = new BiomeGenDeathStar(OuterRimResources.ConfigOptions.biomeDeathStarId).setColor(112).setBiomeName("Death Star");

    @SideOnly(Side.CLIENT)
    private IRenderHandler skyRenderer;

    @SideOnly(Side.CLIENT)
    @Override
    public IRenderHandler getSkyRenderer() {
        if (this.skyRenderer == null) {
            this.skyRenderer = new DrawSpaceSky();
        }
        return this.skyRenderer;
    }
    
    public boolean canRainOrSnow() {
        return false;
    }

    @Override
    public void registerWorldChunkManager() {
        this.worldChunkMgr = new WorldChunkManagerDeathStar();
        this.dimensionId = OuterRimResources.ConfigOptions.dimDeathStarId;
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
        // Only run on the server side
        if (worldObj.isRemote) {
            return;
        }

        // Grab the WorldInfo once
        WorldInfo info = worldObj.getWorldInfo();

        // 1) Lock time at tick 6000 (noon)
        info.setWorldTime(6000L);

        // 2) If its raining or thundering, clear both
        if (info.isRaining() || info.isThundering()) {
            info.setRaining(false);
            info.setThundering(false);
        }
    }
    
    public boolean shouldForceRespawn() {
        return true;
    }

    @Override
    public Class<? extends IChunkProvider> getChunkProviderClass() {
        return BiomeChunkProviderGeneric.class;
    }

    @Override
    public Class<? extends WorldChunkManager> getWorldChunkManagerClass() {
        return WorldChunkManagerDeathStar.class;
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
    public GlobalPreset getCelestialBody() {
        return null;
    }

    public String getDimensionName() {
        return null;
    }
}


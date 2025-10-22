package com.sown.outerrim.dimension.niamos;

import java.util.ArrayList;
import java.util.List;

import com.sown.outerrim.OuterRimResources;
import com.sown.util.world.creation.GlobalPreset;
import com.sown.util.world.creation.WorldProviderSpace;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.WorldChunkManager;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraftforge.client.IRenderHandler;

public class NiamosProvider extends WorldProviderSpace {
    /** All biomes that can spawn in this dimension */
    public static final List<net.minecraft.world.biome.BiomeGenBase> biomes = new ArrayList<>();
    public static final BiomeGenBase niamos = new BiomeGenNiamos(OuterRimResources.ConfigOptions.biomeNiamosId).setColor(112).setBiomeName("Niamos");

    @SideOnly(Side.CLIENT)
    private IRenderHandler skyRenderer;

    static {
        // Niamos beaches and shallow water
        biomes.add(niamos);
        // open ocean
        biomes.add(net.minecraft.world.biome.BiomeGenBase.ocean);
    }

    @Override public boolean canRainOrSnow()                    { return true; }
    @Override public boolean hasSunset()                        { return true; }
    @Override public boolean canBlockFreeze(int x,int y,int z,boolean w) { return false; }
    @Override public long    getDayLength()                     { return 24000L; }
    public      boolean       isRaining()                       { return true; }
    public      boolean       shouldForceRespawn()              { return true; }
    @Override public Class<? extends IChunkProvider>    getChunkProviderClass()     { return BiomeChunkProviderNiamos.class; }
    @Override public Class<? extends WorldChunkManager> getWorldChunkManagerClass() { return WorldChunkManagerNiamos.class; }
    @Override public double   getHorizon()                       { return 44.0; }
    @Override public int      getAverageGroundLevel()           { return 44; }
    @Override public boolean  canCoordinateBeSpawn(int x,int z)  { return true; }
    @Override public float    getGravity()                      { return 0.03f; }
    @Override public int      getHeight()                       { return 800; }
    @Override public double   getMeteorFrequency()              { return 0.0; }
    @Override public double   getFuelUsageMultiplier()          { return 1.0; }
    @Override public float    getFallDamageModifier()           { return 0.95f; }
    @Override public float    getSoundVolReductionAmount()      { return 5.0f; }
    @Override public boolean  hasBreathableAtmosphere()         { return true; }
    @Override public float    getThermalLevelModifier()         { return 0.0f; }
    @Override public float    getWindLevel()                    { return 3.0f; }
    @Override public boolean  canSpaceshipTierPass(int tier)    { return false; }
    @Override public GlobalPreset getCelestialBody()            { return null; /* your Niamos preset */ }
    @Override public String   getDimensionName()                { return "Niamos"; }
    @Override public float    getCloudHeight()                  { return 128F; }
    @Override public boolean  doesXZShowFog(int x,int z)        { return false; }

    @SideOnly(Side.CLIENT)
    @Override
    public IRenderHandler getSkyRenderer() {
        // if you want custom sky, assign it here; otherwise null -> vanilla sky
        return skyRenderer;
    }
}

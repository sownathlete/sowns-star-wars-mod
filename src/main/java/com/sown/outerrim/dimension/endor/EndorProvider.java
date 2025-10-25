/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.biome.WorldChunkManager
 *  net.minecraft.world.chunk.IChunkProvider
 */
package com.sown.outerrim.dimension.endor;

import java.util.ArrayList;
import java.util.List;

import com.sown.outerrim.OuterRimResources;
import com.sown.util.world.creation.GlobalPreset;
import com.sown.util.world.creation.WorldProviderSpace;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.BiomeGenTaiga;
import net.minecraft.world.biome.WorldChunkManager;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraftforge.client.IRenderHandler;

public class EndorProvider
extends WorldProviderSpace{
    public static final List<BiomeGenBase> biomes = new ArrayList<>();
    protected static final BiomeGenBase.Height height_MidPlains = new BiomeGenBase.Height(0.2F, 0.2F);
    protected static final BiomeGenBase.Height height_LowHills = new BiomeGenBase.Height(0.45F, 0.3F);
    public static final BiomeGenBase endorTaiga = (new BiomeGenEndorTaiga(OuterRimResources.ConfigOptions.biomeEndorTaigaId, 0)).setBiomeName("Endor Taiga").setHeight(height_MidPlains).setTemperatureRainfall(0.45F, 0.8F);
    public static final BiomeGenBase megaEndorTaiga = (new BiomeGenEndorTaiga(OuterRimResources.ConfigOptions.biomeEndorMegaTaigaId, 1)).setColor(5858897).setBiomeName("Mega Endor Taiga").func_76733_a(5159473).setTemperatureRainfall(0.3F, 0.8F).setHeight(height_MidPlains);
    public static final BiomeGenBase megaEndorTaigaHills = (new BiomeGenEndorTaiga(OuterRimResources.ConfigOptions.biomeEndorMegaTaigaHillsId, 1)).setColor(4542270).setBiomeName("Mega Endor Taiga Hills").func_76733_a(5159473).setTemperatureRainfall(0.3F, 0.8F).setHeight(height_LowHills);

    @SideOnly(value=Side.CLIENT)
    private IRenderHandler skyRenderer;
    

    static {
    	biomes.add(endorTaiga);
    	biomes.add(megaEndorTaiga);
    	biomes.add(megaEndorTaigaHills);
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
    
    public boolean isRaining() {
        return false;
    }

    public boolean shouldForceRespawn() {
        return true;
    }

    @Override
    public Class<? extends IChunkProvider> getChunkProviderClass() {
        return BiomeChunkProviderEndor.class;
    }

    @Override
    public Class<? extends WorldChunkManager> getWorldChunkManagerClass() {
        return WorldChunkManagerEndor.class;
    }

    public double getHorizon() {
        return 44.0;
    }

    @SideOnly(value=Side.CLIENT)
    public IRenderHandler getSkyRenderer() {
        return null;
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

    @Override
    public float getCloudHeight() {
        return 256.0F;
    }
    
    @Override
    public boolean doesXZShowFog(int x, int z) {
        return true;
    }
}

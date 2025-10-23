package com.sown.outerrim.dimension.concorddawn;

import java.util.ArrayList;
import java.util.List;

import com.sown.util.world.creation.GlobalPreset;
import com.sown.util.world.creation.WorldProviderSpace;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.WorldChunkManager;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraftforge.client.IRenderHandler;

public class ConcordDawnProvider extends WorldProviderSpace {
	public static final List<BiomeGenBase> biomes = new ArrayList<>();
	@SideOnly(Side.CLIENT)
	private IRenderHandler skyRenderer;

	static {
		biomes.add(BiomeGenBase.extremeHills);
		biomes.add(BiomeGenBase.extremeHillsEdge);
		biomes.add(BiomeGenBase.forest);
		biomes.add(BiomeGenBase.plains);
		biomes.add(BiomeGenBase.taiga);
		biomes.add(BiomeGenBase.coldTaiga);
		biomes.add(BiomeGenBase.icePlains);
	}

	@Override
	public boolean canRainOrSnow() {
		return true;
	}

	@Override
	public boolean hasSunset() {
		return true;
	}

	public boolean canBlockFreeze(int x, int y, int z, boolean byWater) {
	    return false;
	}
	
	@Override
	public long getDayLength() {
		return 24000L;
	}

	public boolean shouldForceRespawn() {
		return true;
	}

	@Override
	public Class<? extends IChunkProvider> getChunkProviderClass() {
		return com.sown.outerrim.dimension.BiomeChunkProviderGenericPeaceful.class;
	}

	@Override
	public Class<? extends WorldChunkManager> getWorldChunkManagerClass() {
		return WorldChunkManagerConcordDawn.class;
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
		return 4.0f;
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
		return 2.8f;
	}

	@Override
	public boolean canSpaceshipTierPass(int tier) {
		return true;
	}

	@Override
	public GlobalPreset getCelestialBody() {
		return null;
	}

	@Override
	public String getDimensionName() {
		return "Concord Dawn";
	}

	@Override
	public float getCloudHeight() {
		return 130.0F;
	}

	@Override
	public boolean doesXZShowFog(int x, int z) {
		return false;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public IRenderHandler getSkyRenderer() {
		return skyRenderer;
	}

	@Override
	public float getGravity() {
		return 0.03F;
	}
}

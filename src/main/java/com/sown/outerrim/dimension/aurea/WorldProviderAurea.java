package com.sown.outerrim.dimension.aurea;

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

public class WorldProviderAurea extends WorldProviderSpace {
	public static final List<BiomeGenBase> biomes = new ArrayList<>();
	@SideOnly(Side.CLIENT)
	private IRenderHandler skyRenderer;

	static {
		biomes.add(BiomeGenBase.jungle);
		biomes.add(BiomeGenBase.forest);
		biomes.add(BiomeGenBase.plains);
		biomes.add(BiomeGenBase.desert);
		biomes.add(BiomeGenBase.savanna);
		biomes.add(BiomeGenBase.extremeHills);
		biomes.add(BiomeGenBase.mesaPlateau);
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

	public boolean shouldForceRespawn() {
		return true;
	}

	@Override
	public Class<? extends IChunkProvider> getChunkProviderClass() {
		return com.sown.outerrim.dimension.BiomeChunkProviderGenericPeaceful.class;
	}

	@Override
	public Class<? extends WorldChunkManager> getWorldChunkManagerClass() {
		return WorldChunkManagerAurea.class;
	}

	@Override
	public double getHorizon() {
		return 46.0;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public IRenderHandler getSkyRenderer() {
		return null;
	}

	@Override
	public int getAverageGroundLevel() {
		return 46;
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
		return 0.94f;
	}

	@Override
	public float getSoundVolReductionAmount() {
		return 4.5f;
	}

	@Override
	public boolean hasBreathableAtmosphere() {
		return true;
	}

	@Override
	public float getThermalLevelModifier() {
		return 0.25f;
	}

	@Override
	public float getWindLevel() {
		return 3.5f;
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
		return "Aurea";
	}

	@Override
	public float getCloudHeight() {
		return 125.0F;
	}

	@Override
	public boolean doesXZShowFog(int x, int z) {
		return false;
	}
}

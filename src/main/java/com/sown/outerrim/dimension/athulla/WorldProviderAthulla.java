package com.sown.outerrim.dimension.athulla;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.opengl.GL11;

import com.sown.util.world.creation.GlobalPreset;
import com.sown.util.world.creation.WorldProviderSpace;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.WorldChunkManager;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraftforge.client.IRenderHandler;

public class WorldProviderAthulla extends WorldProviderSpace {

	public static final List<BiomeGenBase> biomes = new ArrayList<BiomeGenBase>();

	static {
		biomes.add(BiomeGenBase.jungle);
		biomes.add(BiomeGenBase.forest);
		biomes.add(BiomeGenBase.swampland);
		biomes.add(BiomeGenBase.plains);
		biomes.add(BiomeGenBase.extremeHills);
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
	
	@Override
	public Class<? extends IChunkProvider> getChunkProviderClass() {
		return com.sown.outerrim.dimension.BiomeChunkProviderGenericPeaceful.class;
	}

	@Override
	public Class<? extends WorldChunkManager> getWorldChunkManagerClass() {
		return WorldChunkManagerAthulla.class;
	}

	@Override
	public double getHorizon() {
		return 45.0;
	}
	
	@Override
	public int getAverageGroundLevel() {
		return 45;
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
		return 0.98f;
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
		return 0.10f;
	}

	@Override
	public float getWindLevel() {
		return 2.8f;
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
		return "Athulla";
	}

	@Override
	public float getCloudHeight() {
		return 128.0F;
	}

	@Override
	public boolean doesXZShowFog(int x, int z) {
		return true;
	}
}

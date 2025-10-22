package com.sown.outerrim.dimension.korriban;

import com.sown.util.world.creation.GlobalPreset;
import com.sown.util.world.creation.WorldProviderHelper;
import com.sown.util.world.creation.Vector3;
import com.sown.util.world.creation.WorldProviderSpace;

import cpw.mods.fml.common.Loader;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraft.world.biome.WorldChunkManager;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraftforge.client.IRenderHandler;

public class WorldProviderKorriban extends WorldProviderSpace implements WorldProviderHelper {

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

    public boolean shouldForceRespawn() {
        return true;
    }

    @Override
    public Class<? extends IChunkProvider> getChunkProviderClass() {
        return ChunkProviderKorriban.class;
    }

    @Override
    public Class<? extends WorldChunkManager> getWorldChunkManagerClass() {
        return WorldChunkManagerKorriban.class;
    }

    public boolean isSurfaceWorld() {
        return true;
    }
    
    @Override
    public String getWelcomeMessage() {
        return "Entering Korriban";
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
    public boolean canCoordinateBeSpawn(int var1, int var2) {
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

    @SideOnly(Side.CLIENT)
    public IRenderHandler getSkyRenderer() {
        // Only execute this block if the 'legends' mod is loaded
        if (Loader.isModLoaded("legends")) {
            try {
                // Dynamically load the KorribanSkyRender class
                Class<?> skyRenderClass = Class.forName("com.tihyo.starwars.galactic.planets.korriban.KorribanSkyRender");
                return (IRenderHandler) skyRenderClass.newInstance();
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
                // Log the error or handle it appropriately
                System.err.println("KorribanSkyRender class not found or could not be instantiated.");
            }
        }
        return null; // Default return if the mod is not loaded or class cannot be loaded
    }

    @SideOnly(value=Side.CLIENT)
    public Vec3 getSkyColor(Entity cameraEntity, float partialTicks) {
        long time = this.worldObj.getWorldTime();
        float f2 = time > 14000L && time < 23000L ? 0.0f : 1.0f;
        float r = 0.7f;
        float g = 0.5f;
        float b = 0.4f;
        return Vec3.createVectorHelper((double)(r *= f2 * 0.94f + 0.06f), (double)(g *= f2 * 0.94f + 0.06f), (double)(b *= f2 * 0.94f + 0.06f));
    }

    @SideOnly(value=Side.CLIENT)
    public Vec3 getFogColor(float par1, float par2) {
        float f2 = MathHelper.cos((float)(par1 * 3.1415927f * 2.0f)) * 2.0f + 0.5f;
        if (f2 < 0.0f) {
            f2 = 0.0f;
        }
        if (f2 > 1.0f) {
            f2 = 1.0f;
        }
        float r = 0.7f;
        float g = 0.5f;
        float b = 0.4f;
        return Vec3.createVectorHelper((double)(r *= f2 * 0.94f + 0.06f), (double)(g *= f2 * 0.94f + 0.06f), (double)(b *= f2 * 0.94f + 0.06f));
    }

    @SideOnly(value=Side.CLIENT)
    public boolean renderClouds() {
        return true;
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

    @Override
    public String getDimensionName() {
        return "Korriban";
    }
}

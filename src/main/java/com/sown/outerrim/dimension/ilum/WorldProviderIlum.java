package com.sown.outerrim.dimension.ilum;

import com.sown.util.world.creation.GlobalPreset;
import com.sown.util.world.creation.WorldProviderHelper;
import com.sown.util.world.creation.Vector3;
import com.sown.util.world.creation.WorldProviderSpace;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraft.world.biome.WorldChunkManager;
import net.minecraft.world.chunk.IChunkProvider;

public class WorldProviderIlum extends WorldProviderSpace implements WorldProviderHelper {

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
        return ChunkProviderIlum.class;
    }

    @Override
    public Class<? extends WorldChunkManager> getWorldChunkManagerClass() {
        return WorldChunkManagerIlum.class;
    }

    public boolean isSurfaceWorld() {
        return true;
    }
    
    @Override
    public String getWelcomeMessage() {
        return "Entering Ilum";
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

    @SideOnly(value=Side.CLIENT)
    public Vec3 getFogColor(float par1, float par2) {
        float f2 = MathHelper.cos((float)(par1 * 3.1415927f * 2.0f)) * 2.0f + 0.5f;
        if (f2 < 0.0f) {
            f2 = 0.0f;
        }
        if (f2 > 1.0f) {
            f2 = 1.0f;
        }
        float r = 0.4f;
        float g = 0.5f;
        float b = 0.6f;
        return Vec3.createVectorHelper((double)(r *= f2 * 0.94f + 0.06f), (double)(g *= f2 * 0.94f + 0.06f), (double)(b *= f2 * 0.94f + 0.06f));
    }

    @SideOnly(value=Side.CLIENT)
    public Vec3 getSkyColor(Entity cameraEntity, float partialTicks) {
        long time = this.worldObj.getWorldTime();
        float f2 = time > 14000L && time < 23000L ? 0.0f : 1.0f;
        float r = 0.4f;
        float g = 0.5f;
        float b = 0.6f;
        return Vec3.createVectorHelper((double)(r *= f2 * 0.94f + 0.06f), (double)(g *= f2 * 0.94f + 0.06f), (double)(b *= f2 * 0.94f + 0.06f));
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
    public boolean canSpaceshipTierPass(int var1) {
        return false;
    }

    @Override
    public GlobalPreset getCelestialBody() {
        return null;
    }

    @Override
    public String getDimensionName() {
        return "Ilum";
    }
}

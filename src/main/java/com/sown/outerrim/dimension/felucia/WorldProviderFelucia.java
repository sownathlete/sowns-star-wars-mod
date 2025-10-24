package com.sown.outerrim.dimension.felucia;

import com.sown.outerrim.dimension.deathstar.DrawSkybox;
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
import net.minecraftforge.client.IRenderHandler;

public class WorldProviderFelucia extends WorldProviderSpace implements WorldProviderHelper {

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

    public boolean shouldForceRespawn() {
        return true;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public Vec3 getSkyColor(Entity cameraEntity, float partialTicks) {
        float angle = this.worldObj.getCelestialAngle(partialTicks);

        float brightness = MathHelper.cos(angle * (float)Math.PI * 2.0F) * 2.0F + 0.5F;
        brightness = MathHelper.clamp_float(brightness, 0.0F, 1.0F);

        int dayColor = 0xDAA263;
        float dayR = ((dayColor >> 16) & 255) / 255.0F;
        float dayG = ((dayColor >> 8) & 255) / 255.0F;
        float dayB = (dayColor & 255) / 255.0F;


        int nightColor = 0x19150F;
        float nightR = ((nightColor >> 16) & 255) / 255.0F;
        float nightG = ((nightColor >> 8) & 255) / 255.0F;
        float nightB = (nightColor & 255) / 255.0F;

        float r = nightR + (dayR - nightR) * brightness;
        float g = nightG + (dayG - nightG) * brightness;
        float b = nightB + (dayB - nightB) * brightness;

        return Vec3.createVectorHelper(r, g, b);
    }

//    @SideOnly(Side.CLIENT)
//    @Override
//    public IRenderHandler getSkyRenderer() {
//        if (this.skyRenderer == null) {
//            // "outerrim" is your modid, "deathStar" is the sub-folder under textures/sky
//            this.skyRenderer = new DrawSkybox("outerrim", "felucia");
//        }
//        return this.skyRenderer;
//    }

    @Override
    public Class<? extends IChunkProvider> getChunkProviderClass() {
        return ChunkProviderFelucia.class;
    }

    @Override
    public Class<? extends WorldChunkManager> getWorldChunkManagerClass() {
        return WorldChunkManagerFelucia.class;
    }

    public boolean isSurfaceWorld() {
        return true;
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
    
    @SideOnly(value = Side.CLIENT)
    public Vec3 getFogColor(float par1, float par2) {
        int color = 14328419;
        float r = (float) ((color >> 16) & 255) / 255.0f; // Extract Red
        float g = (float) ((color >> 8) & 255) / 255.0f;  // Extract Green
        float b = (float) (color & 255) / 255.0f;         // Extract Blue
        return Vec3.createVectorHelper(r, g, b);
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
        return "Felucia";
    }
}

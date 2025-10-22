package com.sown.outerrim.dimension.abednedo;

import com.sown.outerrim.OuterRimResources;
import com.sown.util.world.creation.GlobalPreset;
import com.sown.util.world.creation.WorldProviderSpace;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.Entity;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.WorldChunkManager;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.storage.WorldInfo;

import java.util.Random;

public class AbednedoProvider extends WorldProviderSpace {
    public static final BiomeGenBase abednedo =
        new BiomeGenAbednedo(OuterRimResources.ConfigOptions.biomeAbednedoId)
            .setColor(0x70A068)
            .setBiomeName("Abednedo");

    private final Random weatherRng = new Random();

    @Override
    public void registerWorldChunkManager() {
        this.worldChunkMgr = new WorldChunkManagerAbednedo();
        this.dimensionId = OuterRimResources.ConfigOptions.dimAbednedoId;
    }

    // Canon-friendly: breathable, temperate, normal day/night, not a storm planet.
    @Override public boolean canRainOrSnow() { return true; }
    @Override public boolean hasSunset() { return true; }
    @Override public boolean canBlockFreeze(int x, int y, int z, boolean byWater) { return false; }

    // Keep a vanilla-like day so server tick logic remains predictable.
    @Override public long getDayLength() { return 24000L; }

    // Normal weather engine with sensible durations; no forced perma-rain or perma-thunder.
    @Override
    public void updateWeather() {
        if (worldObj.isRemote) return;
        WorldInfo info = worldObj.getWorldInfo();

        // Decrement existing timers
        int rainTime = info.getRainTime();
        int thunderTime = info.getThunderTime();

        if (rainTime > 0) info.setRainTime(rainTime - 1);
        if (thunderTime > 0) info.setThunderTime(thunderTime - 1);

        // If rain timer expired, roll a new state with realistic chances.
        if (info.getRainTime() <= 0) {
            // About 60% clear, 35% rain, 5% long rain
            int next = weatherRng.nextInt(100);
            if (next < 60) {
                info.setRaining(false);
                info.setRainTime(12000 + weatherRng.nextInt(12000)); // 10-20 min MC time
            } else {
                info.setRaining(true);
                info.setRainTime(6000 + weatherRng.nextInt(12000)); // 5-15 min
            }
        }

        // If thunder timer expired, decide if the next rainy window will have thunder.
        if (info.getThunderTime() <= 0) {
            // Thunder only if raining and with a small probability
            if (info.isRaining() && weatherRng.nextInt(100) < 20) {
                info.setThundering(true);
                info.setThunderTime(3000 + weatherRng.nextInt(6000)); // 2.5-7.5 min
            } else {
                info.setThundering(false);
                info.setThunderTime(8000 + weatherRng.nextInt(12000));
            }
        }

        // Visual strengths smoothly follow current flags (server side mirrors to clients)
        worldObj.rainingStrength = info.isRaining() ? 1.0F : 0.0F;
        worldObj.thunderingStrength = info.isThundering() ? 0.5F : 0.0F;
    }

    @Override
    public Class<? extends IChunkProvider> getChunkProviderClass() {
        return com.sown.outerrim.dimension.BiomeChunkProviderGeneric.class;
    }

    @Override
    public Class<? extends WorldChunkManager> getWorldChunkManagerClass() { return WorldChunkManagerAbednedo.class; }

    // Earth-like surface world and ground references
    @Override public double getHorizon() { return 63.0; }
    @Override public int getAverageGroundLevel() { return 63; }
    @Override public boolean canCoordinateBeSpawn(int x, int z) { return true; }

    // Use Earth-like gravity so movement/combat/building feel right
    @Override public float getGravity() { return 0.08f; }

    // Use standard height; 800 can break a lot of 1.7.10 assumptions
    @Override public int getHeight() { return 256; }

    @Override public double getMeteorFrequency() { return 0.0; }
    @Override public double getFuelUsageMultiplier() { return 1.0; }
    @Override public float getFallDamageModifier() { return 1.0f; }
    @Override public float getSoundVolReductionAmount() { return 0.0f; }
    @Override public boolean hasBreathableAtmosphere() { return true; }
    @Override public float getThermalLevelModifier() { return 0.0f; }
    @Override public float getWindLevel() { return 3.0f; }

    // Allow normal access; tune tier gates if your progression expects it
    @Override public boolean canSpaceshipTierPass(int tier) { return tier >= 1; }

    @Override public GlobalPreset getCelestialBody() { return null; }

    @Override public boolean isSurfaceWorld() { return true; }
    @Override public String getDimensionName() { return "Abednedo"; }

    // Sky and fog: distinct day/night with warm dawn/dusk blends; fog tints cooler when raining.
    @SideOnly(Side.CLIENT)
    @Override
    public Vec3 getSkyColor(Entity cameraEntity, float partialTicks) {
        // Key colors
        final int NIGHT  = 0x0F1214; // deep slate blue-gray
        final int DAWN   = 0xE5B97A; // warm amber
        final int DAY    = 0xC9C086; // sunlit, slightly olive
        final int DUSK   = 0xD69A63; // warm orange

        float a = worldObj.getCelestialAngle(partialTicks); // 0..1
        // Define windows (approx): 0.0 sunrise, 0.25 noon, 0.5 sunset, 0.75 midnight
        int from, to;
        float t;

        if (a < 0.10f) { // night -> dawn
            from = NIGHT; to = DAWN; t = a / 0.10f;
        } else if (a < 0.35f) { // dawn -> day
            from = DAWN; to = DAY; t = (a - 0.10f) / 0.25f;
        } else if (a < 0.65f) { // midday plateau (slight shift to keep it alive)
            from = DAY; to = DAY; t = 0.0f;
        } else if (a < 0.85f) { // day -> dusk
            from = DAY; to = DUSK; t = (a - 0.65f) / 0.20f;
        } else if (a < 0.95f) { // dusk -> night
            from = DUSK; to = NIGHT; t = (a - 0.85f) / 0.10f;
        } else { // night tail
            from = NIGHT; to = NIGHT; t = 0.0f;
        }

        int c = lerpRGB(from, to, clamp01(t));

        // Slight desaturation under rain/thunder
        float rain = worldObj.getRainStrength(partialTicks);
        float thun = worldObj.getWeightedThunderStrength(partialTicks);
        float dim = 1.0f - (0.15f * rain) - (0.10f * thun);

        double r = ((c >> 16) & 255) / 255.0 * dim;
        double g = ((c >> 8) & 255) / 255.0 * dim;
        double b = (c & 255) / 255.0 * dim;
        return Vec3.createVectorHelper(r, g, b);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public Vec3 getFogColor(float par1, float par2) {
        // Base fog: cool green per art direction, shifts cooler and darker when raining
        int base = 0x6FA079;
        float rain = worldObj.getRainStrength(par2);
        float thun = worldObj.getWeightedThunderStrength(par2);

        // Shift toward a cooler gray-green in rain/thunder
        int storm = 0x5B7F6A;
        int c = lerpRGB(base, storm, clamp01(0.6f * rain + 0.4f * thun));

        float r = ((c >> 16) & 255) / 255.0F;
        float g = ((c >> 8) & 255) / 255.0F;
        float b = (c & 255) / 255.0F;
        return Vec3.createVectorHelper(r, g, b);
    }

    private static int lerpRGB(int a, int b, float t) {
        int ar = (a >> 16) & 255, ag = (a >> 8) & 255, ab = a & 255;
        int br = (b >> 16) & 255, bg = (b >> 8) & 255, bb = b & 255;
        int r = ar + Math.round((br - ar) * t);
        int g = ag + Math.round((bg - ag) * t);
        int bl = ab + Math.round((bb - ab) * t);
        return (r << 16) | (g << 8) | bl;
    }

    private static float clamp01(float v) {
        return v < 0.0f ? 0.0f : (v > 1.0f ? 1.0f : v);
    }
}

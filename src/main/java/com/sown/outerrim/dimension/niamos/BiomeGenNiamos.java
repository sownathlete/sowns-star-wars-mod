package com.sown.outerrim.dimension.niamos;

import java.util.Random;
import com.sown.outerrim.dimension.bestine.WorldGenSmallShrub;
import com.sown.outerrim.dimension.scarif.WorldGenPalmTree3;
import com.sown.outerrim.dimension.scarif.WorldGenPalmTree4;
import com.sown.outerrim.dimension.scarif.WorldGenPalmTree5;
import com.sown.util.world.creation.ORSubBiome;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;

public class BiomeGenNiamos extends ORSubBiome {
    private static final BiomeGenBase.Height BIOME_HEIGHT = new BiomeGenBase.Height(0.05f, 0.02f);
    private static final int SEA_LEVEL = 63;

    public BiomeGenNiamos(int id) {
        super(id);
        zoom = 0.8;
        threshold = 0.3;
        setHeight(BIOME_HEIGHT);
        setColor(0xF4E7B8);
        setTemperatureRainfall(0.8F, 0.4F);
        topBlock = Blocks.sand;
        fillerBlock = Blocks.sand;
        spawnableCreatureList.clear();
        spawnableMonsterList.clear();
        theBiomeDecorator.treesPerChunk = -999;
        theBiomeDecorator.deadBushPerChunk = 0;
        theBiomeDecorator.reedsPerChunk = 0;
        theBiomeDecorator.cactiPerChunk = 0;
    }

    @Override
    public void decorate(World w, Random r, int chunkX, int chunkZ) {
        super.decorate(w, r, chunkX, chunkZ);

        int plants = 6 + r.nextInt(5);
        for (int i = 0; i < plants; i++) {
            int lx = r.nextInt(16), lz = r.nextInt(16);
            int x = chunkX + lx, z = chunkZ + lz;
            int y = w.getTopSolidOrLiquidBlock(x, z) - 1;
            if (w.getBlock(x, y, z) == Blocks.sand) {
                if (r.nextInt(50) == 0) {
                    int t = r.nextInt(3);
                    if (t == 0) new WorldGenPalmTree3().generate(w, r, x, y + 1, z);
                    else if (t == 1) new WorldGenPalmTree4().generate(w, r, x, y + 1, z);
                    else             new WorldGenPalmTree5().generate(w, r, x, y + 1, z);
                } else new WorldGenSmallShrub().generate(w, r, x, y + 1, z);
            }
        }

        if (r.nextFloat() < 0.05f) {
            int dx = r.nextInt(16), dz = r.nextInt(16);
            int x = chunkX + dx, z = chunkZ + dz;
            int y = w.getTopSolidOrLiquidBlock(x, z) - 1;
            if (w.getBlock(x, y, z) == Blocks.sand && w.isAirBlock(x, y + 1, z))
                w.setBlock(x, y + 1, z, Blocks.tallgrass, 1, 2);
        }

        if (r.nextInt(3) == 0) {
            int patchCount = 1 + r.nextInt(3);
            for (int p = 0; p < patchCount; p++) {
                int cx = r.nextInt(16), cz = r.nextInt(16);
                int centerX = chunkX + cx;
                int centerZ = chunkZ + cz;
                int cy = w.getTopSolidOrLiquidBlock(centerX, centerZ) - 1;
                if (cy <= SEA_LEVEL || w.getBlock(centerX, cy, centerZ) != Blocks.sand) continue;
                int radius = 3 + r.nextInt(4);
                generateGrassPatch(w, r, centerX, centerZ, radius);
            }
        }

        if (r.nextFloat() < 0.005f) {
            int centerX = chunkX + r.nextInt(16);
            int centerZ = chunkZ + r.nextInt(16);
            int cy = w.getTopSolidOrLiquidBlock(centerX, centerZ) - 1;
            if (cy > SEA_LEVEL && w.getBlock(centerX, cy, centerZ) == Blocks.sand) {
                int radius = 8 + r.nextInt(8);
                generateGrassPatch(w, r, centerX, centerZ, radius);
            }
        }
    }

    private void generateGrassPatch(World w, Random r, int centerX, int centerZ, int radius) {
        double maxDist2 = radius * radius;
        for (int dx = -radius; dx <= radius; dx++) {
            for (int dz = -radius; dz <= radius; dz++) {
                double dist2 = dx * dx + dz * dz;
                if (dist2 > maxDist2 * (0.6 + r.nextDouble() * 0.8)) continue;
                int gx = centerX + dx;
                int gz = centerZ + dz;
                int gy = w.getTopSolidOrLiquidBlock(gx, gz) - 1;
                if (gy <= SEA_LEVEL) continue;
                if (w.getBlock(gx, gy, gz) == Blocks.sand) {
                    w.setBlock(gx, gy, gz, Blocks.grass, 0, 2);
                }
            }
        }
    }
}

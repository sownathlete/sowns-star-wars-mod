package com.sown.outerrim.dimension.geonosis;

import java.util.Random;

import com.sown.outerrim.OuterRimResources;
import com.sown.outerrim.entities.EntityB2BattleDroid;
import com.sown.outerrim.entities.EntityBattleDroid;
import com.sown.outerrim.registry.BlockRegister;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.world.World;

public class BiomeGenGeonosisDesert extends GeonosisBiomes {

    public BiomeGenGeonosisDesert() {
        super(OuterRimResources.ConfigOptions.biomeGeonosisDesertId);
        this.enableRain = true;
        this.enableSnow = true;
        this.rootHeight = 0.1F;
        this.heightVariation = 0.2F;
        this.setTemperatureRainfall(0.8f, 0.9f);
        this.topBlock = BlockRegister.getRegisteredBlock("geonosisSand");
        this.topMeta = 0;
        this.fillerBlock = BlockRegister.getRegisteredBlock("geonosisGravel");
        this.fillerMeta = 0;
        this.stoneBlock = BlockRegister.getRegisteredBlock("geonosisRock");
        this.stoneMeta = 1;
    }

    @Override
    public void decorate(World world, Random rand, int chunkX, int chunkZ) {
        super.decorate(world, rand, chunkX, chunkZ);

        // Geonosis Pillars1
        if (rand.nextInt(1000) < 1) {
            int k = chunkX + rand.nextInt(16) + 8;
            int l = chunkZ + rand.nextInt(16) + 8;
            new WorldGenGeonosisPillars1(
                BlockRegister.getRegisteredBlock("geonosisRock"),
                BlockRegister.getRegisteredBlock("geonosisCobblestone")
            ).generate(world, rand, k, world.getHeightValue(k, l), l);
        }

        // Geonosis Pillars2
        if (rand.nextInt(1000) < 2) {
            int k = chunkX + rand.nextInt(16) + 8;
            int l = chunkZ + rand.nextInt(16) + 8;
            new WorldGenGeonosisPillars(
                BlockRegister.getRegisteredBlock("geonosisRock"),
                BlockRegister.getRegisteredBlock("geonosisCobblestone"),
                BlockRegister.getRegisteredBlock("geonosisRock_wall")
            ).generate(world, rand, k, world.getHeightValue(k, l), l);
        }

        // Geonosis DSD1 Structure + Droid Spawn
        if (rand.nextInt(80) == 0) {
            int x = chunkX + rand.nextInt(16);
            int z = chunkZ + rand.nextInt(16);
            int y = getTopSolidBlockY(world, x, z);

            new WorldGenDSD1().generate(world, rand, x, y, z);

            if (!world.isRemote) {
                int centerX = x + 7;
                int centerZ = z + 7;

                int numBattleDroids = 6 + rand.nextInt(7);   // 6–12
                int numB2Droids = 3 + rand.nextInt(4);       // 3–6

                for (int i = 0; i < numBattleDroids; i++) {
                    int dx = centerX + rand.nextInt(15) - 7;
                    int dz = centerZ + rand.nextInt(15) - 7;
                    int dy = getTopSolidBlockY(world, dx, dz);

                    EntityBattleDroid droid = new EntityBattleDroid(world);
                    droid.setLocationAndAngles(dx + 0.5, dy + 1, dz + 0.5, rand.nextFloat() * 360F, 0);
                    world.spawnEntityInWorld(droid);
                }

                for (int i = 0; i < numB2Droids; i++) {
                    int dx = centerX + rand.nextInt(15) - 7;
                    int dz = centerZ + rand.nextInt(15) - 7;
                    int dy = getTopSolidBlockY(world, dx, dz);

                    EntityB2BattleDroid b2 = new EntityB2BattleDroid(world);
                    b2.setLocationAndAngles(dx + 0.5, dy + 1, dz + 0.5, rand.nextFloat() * 360F, 0);
                    world.spawnEntityInWorld(b2);
                }
            }
        }
    }

    private int getTopSolidBlockY(World world, int x, int z) {
        for (int y = world.getActualHeight() - 1; y > 0; y--) {
            Block block = world.getBlock(x, y, z);
            if (block != null && block.getMaterial().isSolid() && !block.isAir(world, x, y, z)) {
                return y;
            }
        }
        return 64;
    }

    @Override
    public int getBiomeGrassColor(int x, int y, int z) {
        return 12165249;
    }

    @Override
    public int getBiomeFoliageColor(int x, int y, int z) {
        return 12165249;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getSkyColorByTemp(float temp) {
        return 14131796;
    }
}

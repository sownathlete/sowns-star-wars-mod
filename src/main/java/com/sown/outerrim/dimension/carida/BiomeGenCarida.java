package com.sown.outerrim.dimension.carida;

import java.util.Random;
import com.sown.util.world.creation.ORSubBiome;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.entity.monster.EntitySpider;

public class BiomeGenCarida extends ORSubBiome {
    private static final BiomeGenBase.Height BIOME_HEIGHT = new BiomeGenBase.Height(0.3f, 0.6f);

    public BiomeGenCarida(int biomeID) {
        super(biomeID);

        setHeight(BIOME_HEIGHT);
        setTemperatureRainfall(1.2f, 0.1f);
        setColor(10526880);
        topBlock = Blocks.gravel;
        fillerBlock = Blocks.stone;

        theBiomeDecorator.treesPerChunk    = 1;
        theBiomeDecorator.deadBushPerChunk = 8;
        theBiomeDecorator.cactiPerChunk    = 4;
        theBiomeDecorator.reedsPerChunk    = 2;
        theBiomeDecorator.grassPerChunk    = 5;

        spawnableCreatureList.clear();
        spawnableMonsterList.clear();
        spawnableMonsterList.add(new BiomeGenBase.SpawnListEntry(EntitySpider.class, 5, 2, 4));
    }

    @Override
    public void decorate(World world, Random rand, int chunkX, int chunkZ) {
        super.decorate(world, rand, chunkX, chunkZ);

        int sandPatches = 2 + rand.nextInt(3);
        for (int i = 0; i < sandPatches; i++) {
            int cx = chunkX + rand.nextInt(16);
            int cz = chunkZ + rand.nextInt(16);
            int cy = world.getTopSolidOrLiquidBlock(cx, cz) - 1;
            if (cy <= 0) continue;
            int radius = 3 + rand.nextInt(4);
            for (int dx = -radius; dx <= radius; dx++) {
                for (int dz = -radius; dz <= radius; dz++) {
                    if (dx * dx + dz * dz <= radius * radius * (0.5 + rand.nextDouble() * 0.5)) {
                        int x = cx + dx;
                        int z = cz + dz;
                        int y = world.getTopSolidOrLiquidBlock(x, z) - 1;
                        if (world.getBlock(x, y, z) == Blocks.gravel) {
                            world.setBlock(x, y, z, Blocks.sand, 0, 2);
                        }
                    }
                }
            }
        }
        
        if (rand.nextInt(20) == 0) {
            int cx = chunkX + rand.nextInt(16);
            int cz = chunkZ + rand.nextInt(16);
            int cy = world.getTopSolidOrLiquidBlock(cx, cz) - 1;
            if (world.getBlock(cx, cy, cz) == Blocks.gravel) {
                world.setBlock(cx, cy, cz, Blocks.water, 0, 2);
                for (int dx = -1; dx <= 1; dx++) {
                    for (int dz = -1; dz <= 1; dz++) {
                        if (Math.abs(dx) + Math.abs(dz) == 1) {
                            int x = cx + dx;
                            int z = cz + dz;
                            int y = world.getTopSolidOrLiquidBlock(x, z) - 1;
                            world.setBlock(x, y, z, Blocks.sand, 0, 2);
                        }
                    }
                }
            }
        }
    }

    @Override
    public int getSkyColorByTemp(float temp) {
        return 10281656;
    }

    @Override
    public int getWaterColorMultiplier() {
        return 6528975;
    }

    @Override
    public int getBiomeFoliageColor(int x, int y, int z) {
        return 4173640;
    }

    @Override
    public int getBiomeGrassColor(int x, int y, int z) {
        return 3957322;
    }
}

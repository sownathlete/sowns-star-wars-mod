package com.sown.outerrim.dimension.scarif;

import java.util.Random;

import com.sown.outerrim.dimension.bestine.WorldGenSmallShrub;
import com.sown.util.world.creation.ORSubBiome;

import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;

public class BiomeGenScarif extends ORSubBiome {
    private static final BiomeGenBase.Height biomeHeight = new BiomeGenBase.Height(-0.3f, 0.0f);

    public BiomeGenScarif(int biomeID) {
        super(biomeID);
        this.zoom = 0.5;
        this.threshold = 0.5;
        this.setHeight(biomeHeight);
        this.setColor(7712283);
        this.setTemperatureRainfall(0.5f, 0.9f);
        this.topBlock = Blocks.sand;
        this.fillerBlock = Blocks.dirt;
        this.spawnableCreatureList.clear();
        this.spawnableMonsterList.clear();
        this.theBiomeDecorator.treesPerChunk = -999;
    }

    @Override
    public void decorate(World world, Random random, int chunkX, int chunkZ) {
        super.decorate(world, random, chunkX, chunkZ);

        int totalPlants = 6 + random.nextInt(5);
        boolean[][] treeSpots = new boolean[16][16]; // track where trees/shrubs are

        for (int i = 0; i < totalPlants; i++) {
            int localX = random.nextInt(16);
            int localZ = random.nextInt(16);
            int x = chunkX + localX;
            int z = chunkZ + localZ;
            int y = world.getTopSolidOrLiquidBlock(x, z) - 1;

            if (world.getBlock(x, y, z) == Blocks.grass) {
                treeSpots[localX][localZ] = true;

                if (random.nextInt(4) == 0) {
                    // 25% chance to spawn a palm variant
                    // Weights (total 17):
                    //   0      -> WorldGenPalmTree1       (1 slot)
                    //   1–2    -> WorldGenPalmTree2       (2 slots)
                    //   3–5    -> WorldGenPalmTree3       (3 slots)
                    //   6–9    -> WorldGenPalmTree4       (4 slots)
                    //   10–16  -> WorldGenPalmTree5       (7 slots, +15% boost)
                    int weightRoll = random.nextInt(17);
                    if (weightRoll == 0) {
                        new WorldGenPalmTree1().generate(world, random, x, y + 1, z);
                    } else if (weightRoll < 3) {    // 1–2
                        new WorldGenPalmTree2().generate(world, random, x, y + 1, z);
                    } else if (weightRoll < 6) {    // 3–5
                        new WorldGenPalmTree3().generate(world, random, x, y + 1, z);
                    } else if (weightRoll < 10) {   // 6–9
                        new WorldGenPalmTree4().generate(world, random, x, y + 1, z);
                    } else {                        // 10–16
                        new WorldGenPalmTree5().generate(world, random, x, y + 1, z);
                    }
                } else {
                    // 75% chance: spawn a small shrub
                    new WorldGenSmallShrub().generate(world, random, x, y + 1, z);
                }
            }
        }

        // Place tall grass where no tree/shrub was placed
        for (int localX = 0; localX < 16; localX++) {
            for (int localZ = 0; localZ < 16; localZ++) {
                if (treeSpots[localX][localZ]) continue;

                int x = chunkX + localX;
                int z = chunkZ + localZ;
                int y = world.getTopSolidOrLiquidBlock(x, z) - 1;

                if (world.getBlock(x, y, z) == Blocks.grass && world.isAirBlock(x, y + 1, z)) {
                    if (random.nextFloat() < 0.7f) {
                        world.setBlock(x, y + 1, z, Blocks.tallgrass, 1, 2);
                    }
                }
            }
        }
    }
}

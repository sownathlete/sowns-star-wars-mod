package com.sown.outerrim.dimension.kashyyyk;

import java.util.Random;

import com.sown.util.world.creation.ORSubBiome;
import com.sown.outerrim.entities.EntityWookiee;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.BiomeGenBase.SpawnListEntry;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenBigTree;
import net.minecraft.world.gen.feature.WorldGenMegaJungle;

public class BiomeGenKashyyyk extends ORSubBiome {
    private static final BiomeGenBase.Height biomeHeight = new BiomeGenBase.Height(-0.3f, 0.5f);

    public BiomeGenKashyyyk(int biomeID) {
        super(biomeID);

        this.zoom = 0.5;
        this.threshold = 0.5;
        this.setHeight(biomeHeight);
        this.setColor(5349438);
        this.setTemperatureRainfall(0.95f, 1.2f);

        this.topBlock = Blocks.sand;
        this.fillerBlock = Blocks.dirt;

        // Clear default spawns
        this.spawnableCreatureList.clear();
        this.spawnableMonsterList.clear();

        // Add Wookie spawn entries: weight=10, min group=2, max group=4
        this.spawnableCreatureList.add(new SpawnListEntry(EntityWookiee.class, 10, 2, 4));
    }

    @Override
    public WorldGenAbstractTree func_150567_a(Random random) {
        if (random.nextInt(3) == 0) {
            // Mega jungle gives tall Kashyyyk trees
            return new WorldGenMegaJungle(true, 10, 20, 3, 3);
        } else {
            return new WorldGenBigTree(true);
        }
    }

    @Override
    public void decorate(World world, Random random, int chunkX, int chunkZ) {
        super.decorate(world, random, chunkX, chunkZ);

        int totalTrees = 6 + random.nextInt(5);
        boolean[][] treePositions = new boolean[16][16];

        for (int i = 0; i < totalTrees; i++) {
            int dx = random.nextInt(16);
            int dz = random.nextInt(16);
            int x = chunkX + dx;
            int z = chunkZ + dz;
            int y = world.getHeightValue(x, z);

            if (y <= 0 || y > 255 || !world.blockExists(x, y, z)) continue;
            if (world.getBlock(x, y - 1, z) == Blocks.grass) {
                WorldGenAbstractTree tree = func_150567_a(random);
                tree.generate(world, random, x, y, z);
                treePositions[dx][dz] = true;
            }
        }

        // Vegetation pass
        for (int dx = 0; dx < 16; dx++) {
            for (int dz = 0; dz < 16; dz++) {
                int x = chunkX + dx;
                int z = chunkZ + dz;
                int y = world.getHeightValue(x, z);

                if (y <= 0 || y > 255 || !world.blockExists(x, y, z)) continue;
                if (world.getBlock(x, y - 1, z) == Blocks.grass && !treePositions[dx][dz]) {
                    int chance = random.nextInt(100);
                    if (chance < 50) {
                        world.setBlock(x, y, z, Blocks.tallgrass, 2, 2);
                    } else if (chance < 75) {
                        world.setBlock(x, y, z, Blocks.double_plant, 3, 2);
                    } else if (chance < 90) {
                        world.setBlock(x, y, z, Blocks.vine, 0, 2);
                    }
                }
            }
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getBiomeGrassColor(int x, int y, int z) {
        return 7836007;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getBiomeFoliageColor(int x, int y, int z) {
        return 7836007;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getWaterColorMultiplier() {
        return 4159204;
    }
}

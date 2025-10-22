package com.sown.outerrim.dimension.ajankloss;

import java.util.Random;

import com.sown.util.world.creation.ORBiomeDecorator;
import com.sown.util.world.creation.ORSubBiome;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import net.minecraft.block.Block;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.monster.EntityWitch;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenLakes;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenTrees;

public class BiomeGenAjanKloss extends ORSubBiome {
    private ORBiomeDecorator customBiomeDecorator;
    private static final BiomeGenBase.Height biomeHeight = new BiomeGenBase.Height(0.35f, 0.4f);

    public BiomeGenAjanKloss(int biomeID) {
        super(biomeID);

        // — General properties
        this.setHeight(biomeHeight);
        // warm & humid
        this.setTemperatureRainfall(0.95f, 0.9f);
        // optional: tint the map color if you like
        this.setColor(0x4DC58F);

        // surface blocks
        this.topBlock = Blocks.grass;
        this.fillerBlock = Blocks.dirt;

        // — Clear defaults & add only nighttime hostiles
        this.spawnableCreatureList.clear();
        this.spawnableMonsterList.clear();
        this.spawnableMonsterList.add(new SpawnListEntry(EntityZombie.class,   100, 2, 4));
        this.spawnableMonsterList.add(new SpawnListEntry(EntitySkeleton.class, 100, 2, 4));
        this.spawnableMonsterList.add(new SpawnListEntry(EntitySpider.class,   100, 2, 4));
        this.spawnableMonsterList.add(new SpawnListEntry(EntityCreeper.class,   80,  1, 3));
        this.spawnableMonsterList.add(new SpawnListEntry(EntityEnderman.class,  30,  1, 2));
        this.spawnableMonsterList.add(new SpawnListEntry(EntityWitch.class,     10,  1, 1));

        // — Decorator tweaks
        this.customBiomeDecorator = this.theBiomeDecorator;
        customBiomeDecorator.treesPerChunk   = 8;
        customBiomeDecorator.grassPerChunk   = 20;
        customBiomeDecorator.flowersPerChunk = 6;
        customBiomeDecorator.reedsPerChunk   = 10;
        customBiomeDecorator.clayPerChunk    = 2;
        customBiomeDecorator.generateLakes   = true;
    }

    @Override
    public void decorate(World world, Random random, int chunkX, int chunkZ) {
        super.decorate(world, random, chunkX, chunkZ);

        // occasionally drop in random big trees
        if (random.nextInt(4) == 0) {
            generateTrees(world, random, chunkX, chunkZ);
        }

        // ----- replace grass at fixed heights -----
        // chunkX and chunkZ here are block coordinates of the chunk corner
        for (int dx = 0; dx < 16; dx++) {
            for (int dz = 0; dz < 16; dz++) {
                int x = chunkX + dx;
                int z = chunkZ + dz;

                // Y = 61 to sand
                Block at61 = world.getBlock(x, 61, z);
                if (at61 == Blocks.grass) {
                    world.setBlock(x, 61, z, Blocks.sand, 0, 2);
                }

                // Y = 60 down to 55 to gravel
                for (int y = 60; y >= 55; y--) {
                    Block atY = world.getBlock(x, y, z);
                    if (atY == Blocks.grass) {
                        world.setBlock(x, y, z, Blocks.gravel, 0, 2);
                    }
                }
            }
        }
    }

    private void generateTrees(World world, Random random, int chunkX, int chunkZ) {
        int x = chunkX + random.nextInt(16);
        int z = chunkZ + random.nextInt(16);
        int y = world.getHeightValue(x, z);
        if (world.getBlock(x, y - 1, z) == Blocks.grass) {
            new WorldGenTrees(false).generate(world, random, x, y, z);
        }
    }

    // — Sky & water tints
    @Override
    @SideOnly(Side.CLIENT)
    public int getSkyColorByTemp(float temp) {
        return 8959692;  // pale blue sky
    }
    
    // — exactly your grass & foliage
    @Override
    @SideOnly(Side.CLIENT)
    public int getBiomeGrassColor(int x, int y, int z) {
        return 0x798C34;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getBiomeFoliageColor(int x, int y, int z) {
        return 7239776;
    }
}

package com.sown.outerrim.dimension.dathomir;

import java.util.Random;

import com.sown.outerrim.entities.EntityTuskenRaider;
import com.sown.outerrim.entities.EntityNightsister;
import com.sown.outerrim.registry.BlockRegister;
import com.sown.util.world.creation.ORSubBiome;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.BiomeGenBase.SpawnListEntry;

public class BiomeGenDathomirSwamp extends ORSubBiome {

    public BiomeGenDathomirSwamp(int id) {
        super(id);
        this.setBiomeName("Dathomir Swamp");
        this.setColor(0x2B1E1C);
        this.setTemperatureRainfall(0.9F, 1.0F);
        this.setHeight(new BiomeGenBase.Height(-0.1F, 0.05F));

        this.topBlock = BlockRegister.getRegisteredBlock("dathomirDirt"); // used as default
        this.fillerBlock = BlockRegister.getRegisteredBlock("dathomirSlate");

        this.spawnableMonsterList.clear();
        this.spawnableCreatureList.clear();
        this.spawnableCaveCreatureList.clear();
        this.spawnableWaterCreatureList.clear();

        this.spawnableCreatureList.add(new SpawnListEntry(EntityNightsister.class, 8, 1, 4));

        BiomeDecoratorDathomir decorator = new BiomeDecoratorDathomir();
        decorator.darkTreesPerChunk = 2;
        decorator.lakesPerChunk = 2;
        decorator.grassPerChunk = 5;
        decorator.reedsPerChunk = 10;
        decorator.mushroomsPerChunk = 8;
        decorator.deadBushPerChunk = 1;
        decorator.clayPerChunk = 1;
        decorator.waterlilyPerChunk = 4;
        this.theBiomeDecorator = decorator;
    }

    @Override
    public void decorate(World world, Random rand, int chunkX, int chunkZ) {
        super.decorate(world, rand, chunkX, chunkZ);

        // Generate Dark Trees manually
        int treeChance = 10; // Chance out of 1000 per chunk
        if (rand.nextInt(1000) < treeChance) {
            int x = chunkX + rand.nextInt(16) + 8;
            int z = chunkZ + rand.nextInt(16) + 8;
            int y = world.getHeightValue(x, z);

            WorldGenDarkTreeBrute treeGen = new WorldGenDarkTreeBrute();
            treeGen.generate(world, rand, x, y, z);
        }
    }

    @Override
    public void generateBiomeTerrain(World world, Random rand, Block[] blocks, byte[] metas, int x, int z, double noiseVal) {
        double lilyChance = plantNoise.func_151601_a(x * 0.25D, z * 0.25D);

        if (lilyChance > 0.0D) {
            int maskX = x & 15;
            int maskZ = z & 15;
            int worldHeight = blocks.length / 256;

            for (int y = 255; y >= 0; --y) {
                int index = (maskZ * 16 + maskX) * worldHeight + y;
                Block block = blocks[index];

                if (block == null || block.getMaterial() == Material.air) continue;

                if (y == 62 && block != Blocks.water) {
                    blocks[index] = Blocks.water;
                    if (lilyChance < 0.12D && y + 1 < 256) {
                        blocks[index + 1] = Blocks.waterlily;
                    }
                }

                break;
            }
        }

        super.generateBiomeTerrain(world, rand, blocks, metas, x, z, noiseVal);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public int getBiomeGrassColor(int x, int y, int z) {
        return 0x382B20;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public int getBiomeFoliageColor(int x, int y, int z) {
        return 0x382B20;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public int getWaterColorMultiplier() {
        return 0x5C0000; // dark blood red water
    }
}

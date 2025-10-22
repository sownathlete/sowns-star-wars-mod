package com.sown.outerrim.dimension.dathomir;

import java.util.Random;

import com.sown.outerrim.entities.EntityNightsister;
import com.sown.outerrim.entities.EntityZabrak;
import com.sown.outerrim.registry.BlockRegister;
import com.sown.util.world.creation.ORSubBiome;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.BiomeGenBase.SpawnListEntry;

public class BiomeGenDathomirForest extends ORSubBiome {
    public BiomeGenDathomirForest(int id) {
        super(id);
        this.setBiomeName("Dathomir Forest");
        this.setColor(0x3D1C1C);
        this.setTemperatureRainfall(0.8F, 0.9F);
        this.setHeight(new BiomeGenBase.Height(0.2F, 0.1F));
        this.topBlock = BlockRegister.getRegisteredBlock("dathomirDirt");
        this.fillerBlock = BlockRegister.getRegisteredBlock("dathomirRock");

        this.spawnableCreatureList.clear();
        this.spawnableMonsterList.clear();
        this.spawnableCaveCreatureList.clear();
        this.spawnableWaterCreatureList.clear();

        this.spawnableCreatureList.add(new SpawnListEntry(EntityNightsister.class, 8, 1, 4));
        this.spawnableCreatureList.add(new SpawnListEntry(EntityZabrak.class, 8, 1, 4));

        BiomeDecoratorDathomir decorator = new BiomeDecoratorDathomir();
        decorator.darkTreesPerChunk = 10;
        decorator.lakesPerChunk = 0;
        this.theBiomeDecorator = decorator;
    }

    @Override
    public void decorate(World world, Random rand, int chunkX, int chunkZ) {
        super.decorate(world, rand, chunkX, chunkZ);

        int clusterSize = 2 + rand.nextInt(5); // 2–6 trees per cluster
        int baseX = chunkX + 8 + rand.nextInt(8);
        int baseZ = chunkZ + 8 + rand.nextInt(8);

        for (int i = 0; i < clusterSize; i++) {
            int offsetX = baseX + rand.nextInt(6) - 3;
            int offsetZ = baseZ + rand.nextInt(6) - 3;
            int y = world.getHeightValue(offsetX, offsetZ);

            // 20% chance to use the brute variant
            if (rand.nextInt(5) == 0) {
                new WorldGenDarkTreeBrute().generate(world, rand, offsetX, y, offsetZ);
            } else {
                new WorldGenDarkTree().generate(world, rand, offsetX, y, offsetZ);
            }

            // 1 in 5 chance to corrupt ground with green "ritual grass"
            if (rand.nextInt(5) == 0) {
                for (int dx = -1; dx <= 1; dx++) {
                    for (int dz = -1; dz <= 1; dz++) {
                        int patchX = offsetX + dx;
                        int patchZ = offsetZ + dz;
                        int patchY = world.getHeightValue(patchX, patchZ) - 1;

                        Block ground = world.getBlock(patchX, patchY, patchZ);
                        if (ground == BlockRegister.getRegisteredBlock("dathomirDirt") || ground == Blocks.soul_sand) {
                            world.setBlock(patchX, patchY, patchZ, Blocks.grass, 0, 2);
                        }
                    }
                }
            }
        }
    }
    
    @Override
    public void generateBiomeTerrain(World world, Random rand, Block[] blocks, byte[] metas, int x, int z, double noiseVal) {
        int maskX = x & 15;
        int maskZ = z & 15;
        int height = blocks.length / 256;

        boolean placedSurface = false;

        for (int y = 255; y >= 0; --y) {
            int index = (maskZ * 16 + maskX) * height + y;
            Block block = blocks[index];

            if (block == null || block.getMaterial().isReplaceable()) continue;

            if (!placedSurface) {
                Block top = rand.nextInt(3) == 0
                    ? Blocks.soul_sand
                    : BlockRegister.getRegisteredBlock("dathomirDirt");

                blocks[index] = top;
                placedSurface = true;
            } else if (y < 62 && y >= 60) {
                blocks[index] = this.fillerBlock;
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

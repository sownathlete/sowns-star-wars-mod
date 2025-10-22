package com.sown.outerrim.dimension.dathomir;

import java.util.Random;

import com.sown.outerrim.entities.EntityNightsister;
import com.sown.outerrim.entities.EntityZabrak;
import com.sown.outerrim.registry.BlockRegister;
import com.sown.util.structures.StructureLoader;
import com.sown.util.world.creation.ORSubBiome;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.BiomeGenBase.SpawnListEntry;

public class BiomeGenDathomirCliffs extends ORSubBiome {
    private final Block slate = BlockRegister.getRegisteredBlock("dathomirSlate");
    private final Block rock = BlockRegister.getRegisteredBlock("dathomirRock");
    private final WorldGenDathomirRuins ruinsGen = new WorldGenDathomirRuins();
    private final WorldGenDathomirTower1 towerGen = new WorldGenDathomirTower1();

    public BiomeGenDathomirCliffs(int id) {
        super(id);
        this.setBiomeName("Dathomir Cliffs");
        this.setColor(0x443333);
        this.setTemperatureRainfall(0.6F, 0.2F);
        this.setHeight(new BiomeGenBase.Height(1.0F, 0.4F));

        this.topBlock = slate;
        this.fillerBlock = rock;

        this.spawnableCreatureList.clear();
        this.spawnableMonsterList.clear();
        this.spawnableCaveCreatureList.clear();
        this.spawnableWaterCreatureList.clear();

        this.spawnableCreatureList.add(new SpawnListEntry(EntityZabrak.class, 8, 1, 4));

        BiomeDecoratorDathomir decorator = new BiomeDecoratorDathomir();
        decorator.darkTreesPerChunk = 1;
        decorator.lakesPerChunk = 0;
        this.theBiomeDecorator = decorator;
    }

    @Override
    public void decorate(World world, Random rand, int chunkX, int chunkZ) {
        super.decorate(world, rand, chunkX, chunkZ);

        // Existing ruins generator  keep it
        if (rand.nextInt(20) == 0) {
            int x = chunkX + rand.nextInt(16);
            int z = chunkZ + rand.nextInt(16);
            int y = getTopSolidBlockY(world, x, z);
            ruinsGen.generate(world, rand, x, y, z);
        }
        if (rand.nextInt(30) == 0) {
            int x = chunkX + rand.nextInt(16);
            int z = chunkZ + rand.nextInt(16);
            int y = getTopSolidBlockY(world, x, z);
            towerGen.generate(world, rand, x, y, z);
        }
    }

    @Override
    public void generateBiomeTerrain(World world, Random rand, Block[] blocks, byte[] metadata, int x, int z, double stoneNoise) {
        super.generateBiomeTerrain(world, rand, blocks, metadata, x, z, stoneNoise);

        int index = (z & 15) * 16 + (x & 15);
        int height = blocks.length / 256;

        for (int y = 255; y >= 0; --y) {
            int i = index * height + y;
            Block current = blocks[i];

            if (current == this.topBlock || current == this.fillerBlock) {
                blocks[i] = (rand.nextInt(100) < 30) ? rock : slate;
                break;
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
        return 0x5C0000;
    }
}

package com.sown.outerrim.dimension.tython;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFlower;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;

import com.sown.outerrim.dimension.tython.WorldGenBigTythoonTree;

public class BiomeGenTythonForest extends BiomeGenBase {
    protected static final WorldGenBigTythoonTree tree1 = new WorldGenBigTythoonTree(false);
    public static final BiomeGenBase.Height height_Flat = new BiomeGenBase.Height(0.0f, 0.0f);

    public BiomeGenTythonForest(int id) {
        super(id);
        this.setHeight(height_Flat);
        this.heightVariation      = 0.1f;
        this.setColor(1681198);
        this.setTemperatureRainfall(0.95f, 0.9f);

        this.topBlock    = Blocks.grass;
        this.fillerBlock = Blocks.dirt;

        this.theBiomeDecorator.treesPerChunk   = 5;
        this.theBiomeDecorator.flowersPerChunk = 4;
        this.theBiomeDecorator.grassPerChunk   = 20;
        this.setBiomeName("Tython Forest");

        this.spawnableMonsterList.clear();
        this.spawnableCreatureList.clear();
        this.spawnableWaterCreatureList.clear();
        this.spawnableCaveCreatureList.clear();
    }

    public WorldGenAbstractTree genBigTreeChance(Random rand) {
        return rand.nextInt(10) > 5 ? tree1 : this.worldGeneratorTrees;
    }

    @Override
    public void decorate(World world, Random rand, int chunkX, int chunkZ) {
        // standard decoration
        this.theBiomeDecorator.decorateChunk(world, rand, this, chunkX, chunkZ);

        // also convert dirt under air, replaceable, or flowers into grass (y=61…57)
        for (int dx = 0; dx < 16; dx++) {
            for (int dz = 0; dz < 16; dz++) {
                int wx = chunkX + dx;
                int wz = chunkZ + dz;
                for (int y = 61; y >= 57; y--) {
                    Block here  = world.getBlock(wx, y, wz);
                    Block above = world.getBlock(wx, y + 1, wz);

                    boolean isReplaceable = above.isAir(world, wx, y + 1, wz)
                        || above.getMaterial().isReplaceable()
                        || above instanceof BlockFlower;

                    if (here == Blocks.dirt && isReplaceable) {
                        world.setBlock(wx, y, wz, Blocks.grass, 0, 2);
                    }
                }
            }
        }
    }
}

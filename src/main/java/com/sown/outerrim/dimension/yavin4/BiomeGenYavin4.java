package com.sown.outerrim.dimension.yavin4;

import java.util.Random;

import com.sown.outerrim.registry.BlockRegister;
import com.sown.util.world.creation.ORSubBiome;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenBigTree;
import net.minecraft.world.gen.feature.WorldGenForest;
import net.minecraft.world.gen.feature.WorldGenShrub;
import net.minecraft.world.gen.feature.WorldGenTrees;
import net.minecraft.world.gen.feature.WorldGenerator;

public class BiomeGenYavin4 extends ORSubBiome {
    private static final Block MASSASSI_STONE  = BlockRegister.getRegisteredBlock("massassiRock");
    private static final Block MASSASSI_COBBLE = BlockRegister.getRegisteredBlock("massassiCobblestone");

    // big mixed veins generators
    private final WorldGenMinable marbleStoneGen;
    private final WorldGenMinable marbleCobbleGen;

    public BiomeGenYavin4(int biomeId) {
        super(biomeId);
        setBiomeName("Yavin Four");
        enableRain       = true;
        enableSnow       = false;
        rootHeight       = 0.1f;
        heightVariation  = 0.2f;

        // keep default surface
        topBlock    = Blocks.grass;
        fillerBlock = Blocks.dirt;

        // bigger veins: size 32 stone, 16 cobble
        marbleStoneGen  = new WorldGenMinable(MASSASSI_STONE, 32, Blocks.stone);
        marbleCobbleGen = new WorldGenMinable(MASSASSI_COBBLE, 16, MASSASSI_STONE);

        // decoration settings
        theBiomeDecorator.grassPerChunk    = 8;
        theBiomeDecorator.treesPerChunk    = 5;
        theBiomeDecorator.deadBushPerChunk = -999;
        theBiomeDecorator.reedsPerChunk    = 3;
        theBiomeDecorator.cactiPerChunk    = -999;

        spawnableMonsterList.clear();
    }

    @Override
    public void decorate(World world, Random rand, int chunkX, int chunkZ) {
        super.decorate(world, rand, chunkX, chunkZ);

        // large, common mixed veins, no Y limit
        for (int i = 0; i < 160; i++) {
            int x = chunkX + rand.nextInt(16);
            int y = rand.nextInt(256);
            int z = chunkZ + rand.nextInt(16);
            // first carve out stone pocket
            marbleStoneGen.generate(world, rand, x, y, z);
            // then carve cobble inside it
            marbleCobbleGen.generate(world, rand, x, y, z);
        }

        // replace grass layers Y=61 56 with sand
        for (int x = chunkX; x < chunkX + 16; x++) {
            for (int z = chunkZ; z < chunkZ + 16; z++) {
                for (int y = 61; y >= 56; y--) {
                    if (world.getBlock(x, y, z) == Blocks.grass) {
                        world.setBlock(x, y, z, Blocks.sand);
                    }
                }
            }
        }

        // grass generation
        for (int j = 0; j < theBiomeDecorator.grassPerChunk; j++) {
            int x = chunkX + rand.nextInt(16) + 8;
            int z = chunkZ + rand.nextInt(16) + 8;
            int y = rand.nextInt(world.getHeightValue(x, z) * 2);

            WorldGenerator grassGen = getRandomWorldGenForGrass(rand);
            grassGen.generate(world, rand, x, y, z);
        }
    }
    
    /**
     * Spawn trees during populate() to avoid leaf decay.
     */
    public void spawnTrees(World world, Random rand, int chunkX, int chunkZ) {
        for (int j = 0; j < this.theBiomeDecorator.treesPerChunk; j++) {
            int x = chunkX + rand.nextInt(16) + 8;
            int z = chunkZ + rand.nextInt(16) + 8;
            int y = world.getHeightValue(x, z);

            WorldGenAbstractTree tree = func_150567_a(rand);
            if (tree != null && tree.generate(world, rand, x, y, z)) {
                tree.func_150524_b(world, rand, x, y, z);
            }
        }
    }

    @Override
    public WorldGenAbstractTree func_150567_a(Random rand) {
        switch (rand.nextInt(4)) {
            case 0: return new WorldGenBigTree(true);
            case 1: return new WorldGenForest(true, true);
            case 2: return new WorldGenShrub(2, 0);
            default: return new WorldGenTrees(true, 10, 3, 3, false);
        }
    }
}

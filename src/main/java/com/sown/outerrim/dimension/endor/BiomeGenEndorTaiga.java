package com.sown.outerrim.dimension.endor;

import java.util.Random;

import com.sown.outerrim.entities.EntityEwok;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenBlockBlob;
import net.minecraft.world.gen.feature.WorldGenTallGrass;
import net.minecraft.world.gen.feature.WorldGenerator;

public class BiomeGenEndorTaiga extends BiomeGenBase {
    private static final WorldGenBlockBlob field_150643_aG = new WorldGenBlockBlob(Blocks.mossy_cobblestone, 0);
    private final int field_150644_aH;

    public BiomeGenEndorTaiga(int biomeId, int variantType) {
        super(biomeId);
        this.field_150644_aH = variantType;
        this.spawnableCreatureList.add(new SpawnListEntry(EntityEwok.class, 16, 4, 4)); // Instead of 8
        this.theBiomeDecorator.treesPerChunk = -999; // Disable vanilla trees

        if (variantType != 1 && variantType != 2) {
            this.theBiomeDecorator.grassPerChunk = 1;
            this.theBiomeDecorator.mushroomsPerChunk = 1;
        } else {
            this.theBiomeDecorator.grassPerChunk = 7;
            this.theBiomeDecorator.deadBushPerChunk = 1;
            this.theBiomeDecorator.mushroomsPerChunk = 3;
        }
    }

    @Override
    public WorldGenerator getRandomWorldGenForGrass(Random rand) {
        return rand.nextInt(5) > 0
            ? new WorldGenTallGrass(Blocks.tallgrass, 2)
            : new WorldGenTallGrass(Blocks.tallgrass, 1);
    }

    @Override
    public void decorate(World world, Random rand, int chunkX, int chunkZ) {
        super.decorate(world, rand, chunkX, chunkZ);

        if (rand.nextInt(5) == 0) {
            int px = chunkX + rand.nextInt(16) + 8;
            int pz = chunkZ + rand.nextInt(16) + 8;
            int py = world.getTopSolidOrLiquidBlock(px, pz);

            if (new WorldGenEndorLandingPad().generate(world, rand, px, py, pz)) {
                return;
            }
        }

        // ~20.0% chance of bunker (1/5)
//        if (rand.nextInt(5) == 0) {
//            int bx = chunkX + rand.nextInt(16) + 8;
//            int bz = chunkZ + rand.nextInt(16) + 8;
//            int by = world.getTopSolidOrLiquidBlock(bx, bz);
//
//            // don’t overlap pads
//            if (!WorldGenEndorLandingPad.intersectsPad(bx, bz)) {
//                new WorldGenEndorBunker().generate(world, rand, bx, by, bz);
//            }
//        }

        /* ---------------------------------------------------------------
           1)  Mossy-cobblestone blobs  (rarer than before)
               Only variants 1 & 2 ever get them.
               Old: rand.nextInt(3)  blobs every chunk
               New: 50 % of chunks -> 0–1 blobs
        ---------------------------------------------------------------- */
        if ((field_150644_aH == 1 || field_150644_aH == 2) && rand.nextInt(2) == 0) {
            int blobs = rand.nextInt(2);       // 0 – 1 blobs
            for (int b = 0; b < blobs; ++b) {
                int x = chunkX + rand.nextInt(16) + 8;
                int z = chunkZ + rand.nextInt(16) + 8;

                // skip if this column lies inside any pad’s bounding-box
                if (WorldGenEndorLandingPad.intersectsPad(x, z)) continue;

                int y = world.getHeightValue(x, z);
                field_150643_aG.generate(world, rand, x, y, z);
            }
        }

        genTallFlowers.func_150548_a(3);
        for (int i = 0; i < 7; ++i) {
            int x = chunkX + rand.nextInt(16) + 8;
            int z = chunkZ + rand.nextInt(16) + 8;

            if (WorldGenEndorLandingPad.intersectsPad(x, z)) continue;

            int y = rand.nextInt(world.getHeightValue(x, z) + 32);
            genTallFlowers.generate(world, rand, x, y, z);
        }

        for (int t = 0; t < 7; ++t) {
            int x = chunkX + rand.nextInt(16) + 8;
            int z = chunkZ + rand.nextInt(16) + 8;

            if (WorldGenEndorLandingPad.intersectsPad(x, z)) continue;

            int y = world.getTopSolidOrLiquidBlock(x, z);

            WorldGenerator treeGen;
            switch (rand.nextInt(10)) {
                case 0:  treeGen = new WorldGenEndorTree1();  break;
                case 1:  treeGen = new WorldGenEndorTree2();  break;
                case 2:  treeGen = new WorldGenEndorTree3();  break;
                case 3:  treeGen = new WorldGenEndorTree4();  break;
                case 4:  treeGen = new WorldGenEndorTree5();  break;
                case 5:  treeGen = new WorldGenEndorTree6();  break;
                case 6:  treeGen = new WorldGenEndorTree7();  break;
                case 7:  treeGen = new WorldGenEndorTree8();  break;
                case 8:  treeGen = new WorldGenEndorTree9();  break;
                default: treeGen = new WorldGenEndorTree10(); break;
            }

            Block at = world.getBlock(x, y, z);
            if (at.getMaterial() == Material.plants || at.getMaterial() == Material.vine) {
                world.setBlockToAir(x, y, z);
            }

            treeGen.generate(world, rand, x, y, z);
        }
    }

    @Override
    public WorldGenAbstractTree func_150567_a(Random rand) {
        return new WorldGenAbstractTree(false) {
            @Override
            public boolean generate(World world, Random rand, int x, int y, int z) {
                return false;
            }
        };
    }

    @Override
    public void genTerrainBlocks(World world, Random rand, Block[] blocks, byte[] metadata, int x, int z, double noiseVal) {
        if (this.field_150644_aH == 1 || this.field_150644_aH == 2) {
            this.topBlock = Blocks.grass;
            this.field_150604_aj = 0;
            this.fillerBlock = Blocks.dirt;

            if (noiseVal > 1.75D) {
                this.topBlock = Blocks.dirt;
                this.field_150604_aj = 1;
            } else if (noiseVal > -0.95D) {
                this.topBlock = Blocks.dirt;
                this.field_150604_aj = 2;
            }
        }

        this.genBiomeTerrain(world, rand, blocks, metadata, x, z, noiseVal);
    }
}

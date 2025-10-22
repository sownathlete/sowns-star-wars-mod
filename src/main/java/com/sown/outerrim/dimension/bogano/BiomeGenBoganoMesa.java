package com.sown.outerrim.dimension.bogano;

import java.util.Random;

import com.sown.outerrim.registry.BlockRegister;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;

public class BiomeGenBoganoMesa extends BiomeGenBase {

    private static final Block boganoRock1 = BlockRegister.getRegisteredBlock("boganoRock1");
    private static final Block boganoRock2 = BlockRegister.getRegisteredBlock("boganoRock2");
    private static final Block boganoGrass = BlockRegister.getRegisteredBlock("boganoGrass");
    
    public BiomeGenBoganoMesa(int id) {
        super(id);
        this.topBlock = boganoGrass;
        this.fillerBlock = boganoRock2;
        this.theBiomeDecorator.treesPerChunk = -999; // No trees
    }

    @Override
    public void genTerrainBlocks(World world, Random rand, Block[] blocks, byte[] blockMetadata, int x, int z, double noiseVal) {
        for (int y = 0; y < 256; y++) {
            if (y <= 63) {
                blocks[y] = Blocks.water;
            } else if (y == 64) {
                blocks[y] = this.topBlock;
            } else if (y < 70) {
                if (rand.nextInt(4) == 0) {
                    blocks[y] = boganoRock1;
                } else {
                    blocks[y] = boganoRock2;
                }
            } else {
                blocks[y] = Blocks.air;
            }
        }
    }

    @Override
    public void decorate(World world, Random rand, int x, int z) {
        // Additional decoration can be added here, like cacti or dead bushes if you wish
        super.decorate(world, rand, x, z);
    }
}

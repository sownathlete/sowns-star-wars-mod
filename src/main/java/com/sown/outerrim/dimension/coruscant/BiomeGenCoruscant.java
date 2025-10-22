package com.sown.outerrim.dimension.coruscant;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;

import java.util.Random;

public class BiomeGenCoruscant extends BiomeGenBase {

    public BiomeGenCoruscant(int id) {
        super(id);

        // Basic biome properties
        this.topBlock = Blocks.stone;
        this.fillerBlock = Blocks.stone;
        this.temperature = 0.5F;
        this.rainfall = 0.5F;

        // Setting height to flat
        this.setHeight(new Height(0.0F, 0.0F));

        // Remove default vegetation
        this.theBiomeDecorator.treesPerChunk = -999;
        this.theBiomeDecorator.grassPerChunk = -999;
        this.theBiomeDecorator.flowersPerChunk = -999;
        this.theBiomeDecorator.deadBushPerChunk = -999;

        // City coloration
        this.setColor(0x808080); // Gray, like a city

        // No animals in our city biome by default
        this.spawnableCreatureList.clear();
        this.spawnableMonsterList.clear();
    }

    @Override
    public void genTerrainBlocks(World world, Random rand, Block[] blocks, byte[] metadata, int chunkX, int chunkZ, double noiseVal) {
        int baseX = chunkX & 15;
        int baseZ = chunkZ & 15;
        
        // Set only the bedrock layer to stone, everything else air
        for (int y = 0; y < 256; y++) {
            if (y == 0) {
                blocks[(baseZ * 16 + baseX) * 256 + y] = Blocks.stone; // Set stone at bedrock level (y=0)
            } else {
                blocks[(baseZ * 16 + baseX) * 256 + y] = null; // Air above
            }
        }
    }

    @Override
    public void decorate(World world, Random rand, int chunkX, int chunkZ) {
        // No additional decorations
    }
}

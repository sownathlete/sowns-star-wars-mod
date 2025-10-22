package com.sown.outerrim.dimension.bogano;

import java.util.Random;

import com.sown.outerrim.registry.BlockRegister;
import com.sown.util.world.creation.ORBiomeDecorator;
import com.sown.util.world.creation.OROverworldBiomeDecorator;
import com.sown.util.world.creation.ORSubBiome;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;

public class BiomeGenBogano extends ORSubBiome {
    private ORBiomeDecorator customBiomeDecorator;
    private static final BiomeGenBase.Height biomeHeight = new BiomeGenBase.Height(1.2f, 0.4f);
    private final WorldGenBoganoCrater craterGen = new WorldGenBoganoCrater();

    public BiomeGenBogano(int biomeID) {
        super(biomeID);
        this.zoom = 0.4;
        this.threshold = 0.6;
        this.setHeight(biomeHeight);
        this.setDisableRain();
        this.setColor(7500402); // dusty rocky tone
        this.setTemperatureRainfall(1.2f, 0.0f);

        this.spawnableCreatureList.clear();
        this.spawnableCaveCreatureList.clear();
        this.spawnableMonsterList.clear();
        this.spawnableWaterCreatureList.clear();

        this.topBlock = BlockRegister.getRegisteredBlock("boganoGrass");
        this.fillerBlock = BlockRegister.getRegisteredBlock("boganoRockLight");

        ((OROverworldBiomeDecorator)this.theBiomeDecorator).treesPerChunk = -999;
        ((OROverworldBiomeDecorator)this.theBiomeDecorator).cactiPerChunk = -999;
        ((OROverworldBiomeDecorator)this.theBiomeDecorator).reedsPerChunk = -999;

        this.customBiomeDecorator = this.theBiomeDecorator;
        this.customBiomeDecorator.treesPerChunk = -999;
        this.customBiomeDecorator.deadBushPerChunk = 1;
        this.customBiomeDecorator.reedsPerChunk = -999;
        this.customBiomeDecorator.generateLakes = false;
    }

    @Override
    public void decorate(World world, Random rand, int chunkX, int chunkZ) {
        super.decorate(world, rand, chunkX, chunkZ);

        if (rand.nextInt(6) == 0) {
            int x = chunkX + rand.nextInt(16);
            int z = chunkZ + rand.nextInt(16);
            craterGen.generateCrater(world, rand, x, z);
        }
    }

    @Override
    public void genTerrainBlocks(World world, Random rand, Block[] blocks, byte[] meta, int x, int z, double noise) {
        Block top = this.topBlock;
        Block filler = this.fillerBlock;

        int chunkX = x & 15;
        int chunkZ = z & 15;
        int index;

        for (int y = 255; y >= 10; --y) {
            index = (chunkZ * 16 + chunkX) * 256 + y;
            Block current = blocks[index];

            if (current == Blocks.stone) {
                if (y >= 81) {
                    blocks[index] = top;
                } else {
                    blocks[index] = getStratifiedLayerBlock(y);
                }
            }
        }
    }

    private Block getStratifiedLayerBlock(int y) {
        int band = (80 - y) / 2; // 2-block thick bands from y=80 down to y=10
        return (band % 10 < 7) ? BlockRegister.getRegisteredBlock("boganoRockLight")
                               : BlockRegister.getRegisteredBlock("boganoRockDark");
    }

    @SideOnly(Side.CLIENT)
    public int getSkyColorByTemp(float temp) {
        return 10797476;
    }
}

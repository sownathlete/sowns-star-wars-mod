package com.sown.outerrim.dimension.deathstar;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;

import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BiomeGenDeathStar extends BiomeGenBase {

    public BiomeGenDeathStar(int id) {
        super(id);

        // Basic biome properties
        this.topBlock = Blocks.cobblestone;
        this.fillerBlock = Blocks.cobblestone;
        this.temperature = 1.0F;
        this.enableRain = false;
        this.enableSnow = false;

        // Setting height to artificial construct
        this.setHeight(new Height(0.0F, 0.0F));

        // Remove default vegetation
        this.theBiomeDecorator.treesPerChunk = -999;
        this.theBiomeDecorator.grassPerChunk = -999;
        this.theBiomeDecorator.flowersPerChunk = -999;
        this.theBiomeDecorator.deadBushPerChunk = -999;

        // Death Star coloration
        this.setColor(0x505050); // Dark gray, like a space station

        // No animals in the Death Star biome
        this.spawnableCreatureList.clear();
        this.spawnableMonsterList.clear();
    }

    @Override
    public void genTerrainBlocks(World world, Random rand, Block[] blocks, byte[] metadata, int chunkX, int chunkZ, double noiseVal) {
        int baseX = chunkX & 15;
        int baseZ = chunkZ & 15;
        
        for (int y = 0; y < 256; y++) {
            if (y == 0) {
                blocks[(baseZ * 16 + baseX) * 256 + y] = Blocks.bedrock; // Set bedrock layer at y=0
            } else if (y >= 123 && y <= 186) {
                blocks[(baseZ * 16 + baseX) * 256 + y] = Blocks.cobblestone; // Set cobblestone from y=123 to y=186
            } else {
                blocks[(baseZ * 16 + baseX) * 256 + y] = null; // Air elsewhere
            }
        }
    }

    @Override
    public void decorate(World world, Random rand, int chunkX, int chunkZ) {
        // No additional decorations
    }
    
    @SideOnly(Side.CLIENT)
    public Vec3 getFogColor(float par1, float par2) {
        // ignore time of day, always black:
        return Vec3.createVectorHelper(0.0, 0.0, 0.0);
    }
    
}

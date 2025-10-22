package com.sown.outerrim.dimension.bogano;

import java.util.Random;

import com.sown.util.world.creation.ORBiomeDecorator;
import com.sown.util.world.creation.OROverworldBiomeDecorator;
import com.sown.util.world.creation.ORSubBiome;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;

public class BiomeGenBoganoCanyons extends ORSubBiome {
    private ORBiomeDecorator customBiomeDecorator;
    private static final BiomeGenBase.Height biomeHeight = new BiomeGenBase.Height(1.2f, 0.4f);

    public BiomeGenBoganoCanyons(int biomeID) {
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

        this.topBlock = Blocks.sand;
        this.fillerBlock = Blocks.sandstone;

        ((OROverworldBiomeDecorator)this.theBiomeDecorator).treesPerChunk = -999;
        ((OROverworldBiomeDecorator)this.theBiomeDecorator).cactiPerChunk = -999;
        ((OROverworldBiomeDecorator)this.theBiomeDecorator).reedsPerChunk = -999;

        this.customBiomeDecorator = this.theBiomeDecorator;
        this.customBiomeDecorator.treesPerChunk = -999;
        this.customBiomeDecorator.deadBushPerChunk = 1;
        this.customBiomeDecorator.reedsPerChunk = -999;
        this.customBiomeDecorator.generateLakes = false;
    }

    private final WorldGenBoganoCrater craterGen = new WorldGenBoganoCrater();

    @Override
    public void decorate(World world, Random rand, int chunkX, int chunkZ) {
        super.decorate(world, rand, chunkX, chunkZ);

        // Try generating 1 crater every ~5 chunks
        if (rand.nextInt(5) == 0) {
            int x = chunkX + rand.nextInt(16);
            int z = chunkZ + rand.nextInt(16);
            craterGen.generateCrater(world, rand, x, z);
        }
    }

    @SideOnly(Side.CLIENT)
    public int getSkyColorByTemp(float temp) {
        return 10797476;
    }
}

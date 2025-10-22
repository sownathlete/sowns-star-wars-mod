// BiomeGenWBW.java
package com.sown.outerrim.dimension.wbw;

import java.util.Random;

import com.sown.outerrim.registry.BlockRegister;

import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.IChunkProvider;

/**
 * "World Between Worlds" biome.  Delegates decoration to WorldGenLightPath.
 */
public class BiomeGenWBW extends BiomeGenBase {
    public static final int BIOME_ID = com.sown.outerrim.OuterRimResources.ConfigOptions.dimWorldBetweenWorldsId;
    // single instance of our generator
    private static final WorldGenLightPath PATH_GEN = new WorldGenLightPath();

    static {
        // register world-gen so it runs on every chunk population
        WorldGenLightPath.register();
    }

    public BiomeGenWBW() {
        super(BIOME_ID);
        this.setBiomeName("World Between Worlds");
        this.enableRain = false;
        this.enableSnow = false;
        this.rootHeight = 0.1f;
        this.heightVariation = 0.0f;
        this.topBlock = null;
        this.fillerBlock = null;
        this.spawnableCreatureList.clear();
        this.spawnableCaveCreatureList.clear();
        this.spawnableMonsterList.clear();
        this.spawnableWaterCreatureList.clear();
        // disable all vanilla decorations
        this.theBiomeDecorator.treesPerChunk    = -999;
        this.theBiomeDecorator.deadBushPerChunk = -999;
        this.theBiomeDecorator.reedsPerChunk    = -999;
        this.theBiomeDecorator.cactiPerChunk    = -999;
    }

    /**
     * Called once per chunk if this biome is used there.
     * We forward to our world-gen to carve the infinite path.
     */
    @Override
    public void decorate(World world, Random rand, int chunkX, int chunkZ) {
        // delegate to world-gen with chunk provider as both generator args
        IChunkProvider cp = world.getChunkProvider();
        PATH_GEN.generate(rand, chunkX, chunkZ, world, cp, cp);
    }
}

// WorldGenLightPath.java
package com.sown.outerrim.dimension.wbw;

import java.util.Random;

import com.sown.outerrim.OuterRimResources;
import com.sown.outerrim.registry.BlockRegister;

import cpw.mods.fml.common.IWorldGenerator;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;

/**
 * Places a 5-wide strip (X = -1..3) at Y=120 for every Z in each chunk of the WBW dimension.
 */
public class WorldGenLightPath implements IWorldGenerator {
    // your WBW dimension ID
    private static final int DIM_ID = OuterRimResources.ConfigOptions.dimWorldBetweenWorldsId;

    /** Register this generator once (we do it from BiomeGenWBWs static block). */
    public static void register() {
        GameRegistry.registerWorldGenerator(new WorldGenLightPath(), 0);
    }

    @Override
    public void generate(Random rand,
                         int chunkX, int chunkZ,
                         World world,
                         IChunkProvider chunkGenerator,
                         IChunkProvider chunkProvider) {
        if (world.provider.dimensionId != DIM_ID) return;

        Block pathBlock = BlockRegister.getRegisteredBlock("forceLayer");
        if (pathBlock == null) return;

        final int y     = 120;
        final int baseZ = chunkZ << 4;

        // carve global X = -1 to 3 for each of this chunks 16 Z-coordinates
        for (int x = -1; x <= 3; x++) {
            for (int dz = 0; dz < 16; dz++) {
                int z = baseZ + dz;
                world.setBlock(x, y, z, pathBlock, 0, 2);
            }
        }
    }
}

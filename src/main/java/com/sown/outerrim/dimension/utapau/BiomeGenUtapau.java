package com.sown.outerrim.dimension.utapau;

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

public class BiomeGenUtapau extends ORSubBiome {
    private ORBiomeDecorator customBiomeDecorator;
    private final WorldGenUtapauSinkhole craterGen = new WorldGenUtapauSinkhole();
    private static final BiomeGenBase.Height biomeHeight = new BiomeGenBase.Height(4.75f, 0.0f);

    public BiomeGenUtapau(int biomeID) {
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

        this.topBlock = Blocks.sand;          // base default
        this.fillerBlock = Blocks.sandstone;  // filler beneath top

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

        // Crater region spacing
        final int regionSize = 304; // slightly more than 2x max radius
        final int buffer = WorldGenUtapauSinkhole.MAX_RADIUS + 8;

        int blockX = chunkX;
        int blockZ = chunkZ;

        int regionX = (blockX / regionSize) * regionSize;
        int regionZ = (blockZ / regionSize) * regionSize;

        if (blockX % regionSize == 0 && blockZ % regionSize == 0) {
            int x = regionX + buffer + rand.nextInt(regionSize - 2 * buffer);
            int z = regionZ + buffer + rand.nextInt(regionSize - 2 * buffer);
            this.craterGen.generateCrater(world, rand, x, z);
        }

        // Replace top sand with grass in clustered clumps
        for (int dx = 0; dx < 16; dx++) {
            for (int dz = 0; dz < 16; dz++) {
                int x = chunkX + dx;
                int z = chunkZ + dz;

                // Seeded per-column randomness
                long seed = (x * 341873128712L + z * 132897987541L) ^ 987234129L;
                Random randPerColumn = new Random(seed);

                // 15% chance to convert sand into grass here
                if (randPerColumn.nextFloat() < 0.15F) {
                    int topY = getTopSolidBlockY(world, x, z);
                    Block current = world.getBlock(x, topY, z);
                    Block above = world.getBlock(x, topY + 1, z);

                    if (current == Blocks.sand && above.isAir(world, x, topY + 1, z)) {
                        world.setBlock(x, topY, z, Blocks.grass);
                    }
                }
            }
        }
        
        for (int i = 0; i < 3 + rand.nextInt(2); i++) { // ~34 per chunk
            int x = chunkX + rand.nextInt(16);
            int z = chunkZ + rand.nextInt(16);
            int y = getTopSolidBlockY(world, x, z);

            // Skip low elevations (craters) and avoid steep changes
            if (y < 68) continue; // Don't generate in deep areas
            int y1 = getTopSolidBlockY(world, x + 1, z);
            int y2 = getTopSolidBlockY(world, x - 1, z);
            int y3 = getTopSolidBlockY(world, x, z + 1);
            int y4 = getTopSolidBlockY(world, x, z - 1);

            if (Math.abs(y - y1) > 2 || Math.abs(y - y2) > 2 || Math.abs(y - y3) > 2 || Math.abs(y - y4) > 2)
                continue; // Skip if terrain is too steep (likely crater edge)

            Block ground = world.getBlock(x, y, z);
            if (ground == Blocks.sand || ground == Blocks.grass || ground == Blocks.dirt) {
                int yAbove = y + 1;
                if (world.isAirBlock(x, yAbove, z)) {
                    Block deadBush = BlockRegister.getRegisteredBlock("dead_bush");
                    if (deadBush.canPlaceBlockAt(world, x, yAbove, z)) {
                        world.setBlock(x, yAbove, z, deadBush, 0, 2);
                    }
                }
            }
        }
    }

    private int getTopSolidBlockY(World world, int x, int z) {
        for (int y = world.getActualHeight() - 1; y > 0; y--) {
            Block block = world.getBlock(x, y, z);
            if (block != null && block.getMaterial().isSolid() && !block.isAir(world, x, y, z)) {
                return y;
            }
        }
        return 64;
    }

    @SideOnly(Side.CLIENT)
    public int getSkyColorByTemp(float temp) {
        return 10797476;
    }
}

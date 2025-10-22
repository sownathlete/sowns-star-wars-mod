package com.sown.outerrim.dimension.mimban;

import java.util.Random;

import com.sown.outerrim.OuterRimResources;
import com.sown.outerrim.registry.BlockRegister;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenClay;
import net.minecraft.world.gen.feature.WorldGenFlowers;
import net.minecraft.world.gen.feature.WorldGenReed;
import net.minecraft.world.gen.feature.WorldGenWaterlily;

/**
 * Mimban mud-field biome (trenches & shell-craters re-worked).
 *
 * • Trenches sit flush with the surface, carve 4 blocks deep, and never overlap
 *   within the same chunk. Interior walls are lined with oak planks.
 * • ~25 % of chunks receive 1–2 trenches; length, width, and direction vary.
 * • Small craters (radius 1-3, depth  2) add extra battlefield scarring.
 */
public class BiomeGenMimbanMudField extends BiomeGenBase {

    /* --------------------------------------------------------------------- */
    /*  Feature generators                                                   */
    /* --------------------------------------------------------------------- */
    private final WorldGenClay      clayGen = new WorldGenClay(4);
    private final WorldGenReed      reedGen = new WorldGenReed();
    private final WorldGenWaterlily lilyGen = new WorldGenWaterlily();
    private final WorldGenFlowers   mushGen = new WorldGenFlowers(Blocks.brown_mushroom);

    /* --------------------------------------------------------------------- */
    /*  Trench/crater tunables                                               */
    /* --------------------------------------------------------------------- */
    private static final int   TRENCH_MIN_LEN   = 10;
    private static final int   TRENCH_MAX_LEN   = 22;
    private static final int   TRENCH_MIN_W     = 2;     // half-width
    private static final int   TRENCH_MAX_W     = 3;
    private static final int   TRENCH_DEPTH     = 4;
    private static final float TRENCH_CHANCE    = 0.25F; // 25 % of chunks

    private static final int   CRATER_ATTEMPTS  = 2;     // per chunk
    private static final int   CRATER_MIN_R     = 1;
    private static final int   CRATER_MAX_R     = 3;
    private static final int   CRATER_DEPTH     = 2;

    /* --------------------------------------------------------------------- */
    /*  Constructor                                                          */
    /* --------------------------------------------------------------------- */
    public BiomeGenMimbanMudField() {
        super(OuterRimResources.ConfigOptions.biomeMimbanId);

        rootHeight      = 0.0F;
        heightVariation = 0.075F;

        setTemperatureRainfall(0.9F, 1.0F);
        setColor(0x403722);

        topBlock    = BlockRegister.getRegisteredBlock("dullMud");
        fillerBlock = BlockRegister.getRegisteredBlock("mud");

        theBiomeDecorator.treesPerChunk     = 2;
        theBiomeDecorator.deadBushPerChunk  = 4;
        theBiomeDecorator.mushroomsPerChunk = 1;

        spawnableCreatureList.clear();
        spawnableMonsterList.clear();
    }

    /* --------------------------------------------------------------------- */
    /*  Decoration pass                                                      */
    /* --------------------------------------------------------------------- */
    @Override
    public void decorate(World world, Random rand, int chunkX, int chunkZ) {

        /* ---- 1. clay pockets -------------------------------------------- */
        for (int i = 0; i < 4; i++) {
            int x = chunkX + rand.nextInt(16) + 8;
            int z = chunkZ + rand.nextInt(16) + 8;
            int y = world.getHeightValue(x, z);
            clayGen.generate(world, rand, x, y, z);
        }

        /* ---- 2. trenches ------------------------------------------------- */
        if (rand.nextFloat() < TRENCH_CHANCE) {
            boolean[][] carved = new boolean[16][16];          // overlap guard

            int trenchCount = 1 + (rand.nextInt(5) == 0 ? 1 : 0); // occasionally two
            for (int t = 0; t < trenchCount; t++) {
                int sx = rand.nextInt(16);
                int sz = rand.nextInt(16);
                int length = TRENCH_MIN_LEN + rand.nextInt(TRENCH_MAX_LEN - TRENCH_MIN_LEN + 1);
                int halfW  = TRENCH_MIN_W   + rand.nextInt(TRENCH_MAX_W  - TRENCH_MIN_W   + 1);
                double ang = rand.nextDouble() * Math.PI * 2;
                double dx  = Math.cos(ang);
                double dz  = Math.sin(ang);

                for (int l = 0; l < length; l++) {
                    int lx = (int) (sx + dx * l);
                    int lz = (int) (sz + dz * l);
                    if (lx < 0 || lz < 0 || lx > 15 || lz > 15) continue;
                    if (carved[lx][lz]) continue;
                    carved[lx][lz] = true;

                    int wx0   = chunkX + lx;
                    int wz0   = chunkZ + lz;
                    int topY0 = world.getHeightValue(wx0, wz0) - 1;

                    for (int wx = -halfW; wx <= halfW; wx++) {
                        for (int wz = -halfW; wz <= halfW; wz++) {
                            if (wx * wx + wz * wz > halfW * halfW) continue;

                            int x = wx0 + wx;
                            int z = wz0 + wz;

                            /* carve vertical shaft */
                            for (int d = 0; d < TRENCH_DEPTH; d++) {
                                world.setBlock(x, topY0 - d, z, Blocks.air, 0, 2);

                                boolean wall = (Math.abs(wx) == halfW || Math.abs(wz) == halfW);
                                if (wall) { // oak plank lining
                                    world.setBlock(x, topY0 - d, z, Blocks.planks, 0, 2);
                                }
                            }
                            /* trench floor */
                            world.setBlock(x, topY0 - TRENCH_DEPTH, z,
                                    BlockRegister.getRegisteredBlock("dullMud"), 0, 2);
                        }
                    }
                }
            }
        }

        /* ---- 3. shell craters ------------------------------------------- */
        for (int c = 0; c < CRATER_ATTEMPTS; c++) {
            int r  = CRATER_MIN_R + rand.nextInt(CRATER_MAX_R - CRATER_MIN_R + 1);
            int cx = chunkX + rand.nextInt(16);
            int cz = chunkZ + rand.nextInt(16);
            int cy = world.getHeightValue(cx, cz) - 1;

            if (world.getBlock(cx, cy, cz) != topBlock) continue; // only on flat mud

            for (int dx = -r; dx <= r; dx++) {
                for (int dz = -r; dz <= r; dz++) {
                    if (dx * dx + dz * dz > r * r) continue;

                    int x = cx + dx;
                    int z = cz + dz;
                    int depth = CRATER_DEPTH - rand.nextInt(2);

                    for (int d = 0; d < depth; d++)
                        world.setBlock(x, cy - d, z, Blocks.air, 0, 2);

                    world.setBlock(x, cy - CRATER_DEPTH, z,
                            BlockRegister.getRegisteredBlock("dullMud"), 0, 2);
                }
            }
        }

        /* ---- 4. water  mud swap ---------------------------------------- */
        for (int dx = 0; dx < 16; dx++) {
            for (int dz = 0; dz < 16; dz++) {
                int x = chunkX + dx, z = chunkZ + dz;
                int topY = world.getHeightValue(x, z);
                for (int y = 0; y <= topY; y++) {
                    if (world.getBlock(x, y, z).getMaterial() == Material.water) {
                        world.setBlock(x, y, z, BlockRegister.getRegisteredBlock("mud"), 0, 2);
                    }
                }
            }
        }

        /* ---- 5. stone near surface  dirt -------------------------------- */
        for (int dx = 0; dx < 16; dx++) {
            for (int dz = 0; dz < 16; dz++) {
                int x = chunkX + dx, z = chunkZ + dz;
                int topY = world.getHeightValue(x, z) - 1;
                for (int d = 1; d <= 5 && topY - d > 0; d++) {
                    int y = topY - d;
                    if (world.getBlock(x, y, z) == Blocks.stone) {
                        world.setBlock(x, y, z, Blocks.dirt, 0, 2);
                    }
                }
            }
        }

        /* ---- 6. reeds on remaining water -------------------------------- */
        for (int i = 0; i < theBiomeDecorator.reedsPerChunk; i++) {
            int x = chunkX + rand.nextInt(16) + 8;
            int z = chunkZ + rand.nextInt(16) + 8;
            int y = world.getHeightValue(x, z);
            if (world.getBlock(x, y - 1, z).getMaterial().isLiquid()) {
                reedGen.generate(world, rand, x, y, z);
            }
        }

        /* ---- 7. mushrooms under overhangs ------------------------------- */
        for (int i = 0; i < theBiomeDecorator.mushroomsPerChunk; i++) {
            int x = chunkX + rand.nextInt(16) + 8;
            int z = chunkZ + rand.nextInt(16) + 8;
            int y = world.getHeightValue(x, z);
            if (!world.canBlockSeeTheSky(x, y + 1, z)) {
                mushGen.generate(world, rand, x, y, z);
            }
        }
    }

    /* --------------------------------------------------------------------- */
    /*  Client colour overrides                                              */
    /* --------------------------------------------------------------------- */
    @SideOnly(Side.CLIENT)
    public Vec3 getFogColor(float p1, float p2) {
        float f = MathHelper.cos(p1 * (float) Math.PI * 2.0F) * 2.0F + 0.5F;
        f = MathHelper.clamp_float(f, 0.0F, 1.0F);
        float scale = f * 0.94F + 0.06F;
        float r = (80F / 255F) * scale;
        float g = (63F / 255F) * scale;
        float b = (58F / 255F) * scale;
        return Vec3.createVectorHelper(r, g, b);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public int getSkyColorByTemp(float temp) { return 0x503F3A; }

    @SideOnly(Side.CLIENT)
    @Override
    public int getBiomeFoliageColor(int x, int y, int z) { return 0xD8C8A0; } // muted olive

    @SideOnly(Side.CLIENT)
    @Override
    public int getBiomeGrassColor(int x, int y, int z) { return 0x503F3A; }
}

package com.sown.outerrim.dimension.felucia;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.sown.outerrim.OuterRimResources;
import com.sown.outerrim.registry.BlockRegister;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.NoiseGeneratorPerlin;
import net.minecraft.world.gen.feature.WorldGenBigMushroom;
import net.minecraft.world.gen.feature.WorldGenLakes;
import net.minecraft.world.gen.feature.WorldGenerator;

public class BiomeGenFeluciaJungle extends FeluciaBiomes {

    public BiomeGenFeluciaJungle() {
        super(OuterRimResources.ConfigOptions.biomeFeluciaJungleId); // Unique biome ID

        // General biome properties
        this.enableRain = true;
        this.enableSnow = false;
        this.setTemperatureRainfall(1.5F, 0.9F);

        // Block assignments
        this.topBlock = Blocks.dirt;
        this.topMeta = 2;
        this.fillerBlock = Blocks.dirt;
        this.fillerMeta = 0;
        this.stoneBlock = Blocks.stone;
        this.stoneMeta = 0;
    }

    @Override
    public void decorate(World world, Random random, int chunkX, int chunkZ) {
        super.decorate(world, random, chunkX, chunkZ);

        if (random.nextInt(4) == 0) {               // 25% chance per chunk
            int x = chunkX + random.nextInt(16);
            int z = chunkZ + random.nextInt(16);
            int y = world.getTopSolidOrLiquidBlock(x, z);

            new WorldGenFeluciaFlowerTurquoise("felucia_flower_tall_turquoise", 0)
            .generate(world, random, x, y, z);
        }

        Block coarse = null;
        if (cpw.mods.fml.common.Loader.isModLoaded("etfuturum")) {
            coarse = GameRegistry.findBlock("etfuturum", "coarse_dirt");
            long worldSeed = world.getSeed();
            int gridSize = 20;

            int baseX = chunkX;
            int baseZ = chunkZ;

            if (coarse != null) {
                for (int dx = 0; dx < 16; dx++) {
                    for (int dz = 0; dz < 16; dz++) {
                        if (random.nextFloat() < 1.0f/3.0f) {
                            int x = chunkX + dx;
                            int z = chunkZ + dz;
                            int y = world.getTopSolidOrLiquidBlock(x, z) - 1;

                            if (world.getBlock(x, y, z) == Blocks.dirt
                             && world.getBlockMetadata(x, y, z) == 2) {
                                world.setBlock(x, y, z, coarse, 0, 2);
                            }
                        }
                    }
                }
            }
            
            for (int dx = -gridSize; dx <= 32; dx += gridSize) {
                for (int dz = -gridSize; dz <= 32; dz += gridSize) {
                    int x = baseX + dx;
                    int z = baseZ + dz;

                    int gridX = x / gridSize;
                    int gridZ = z / gridSize;

                    Random flowerRand = new Random(worldSeed + gridX * 341873128712L + gridZ * 132897987541L);

                    // Felucia Flowers 1-4
                    if (flowerRand.nextFloat() < 0.15f) new WorldGenFeluciaFlower1().generate(world, flowerRand, gridX * gridSize + flowerRand.nextInt(gridSize), world.getTopSolidOrLiquidBlock(gridX * gridSize + flowerRand.nextInt(gridSize), gridZ * gridSize + flowerRand.nextInt(gridSize)), gridZ * gridSize + flowerRand.nextInt(gridSize));
                    if (flowerRand.nextFloat() < 0.15f) new WorldGenFeluciaFlower2().generate(world, flowerRand, gridX * gridSize + flowerRand.nextInt(gridSize), world.getTopSolidOrLiquidBlock(gridX * gridSize + flowerRand.nextInt(gridSize), gridZ * gridSize + flowerRand.nextInt(gridSize)), gridZ * gridSize + flowerRand.nextInt(gridSize));
                    if (flowerRand.nextFloat() < 0.15f) new WorldGenFeluciaFlower3().generate(world, flowerRand, gridX * gridSize + flowerRand.nextInt(gridSize), world.getTopSolidOrLiquidBlock(gridX * gridSize + flowerRand.nextInt(gridSize), gridZ * gridSize + flowerRand.nextInt(gridSize)), gridZ * gridSize + flowerRand.nextInt(gridSize));
                    if (flowerRand.nextFloat() < 0.15f) new WorldGenFeluciaFlower4().generate(world, flowerRand, gridX * gridSize + flowerRand.nextInt(gridSize), world.getTopSolidOrLiquidBlock(gridX * gridSize + flowerRand.nextInt(gridSize), gridZ * gridSize + flowerRand.nextInt(gridSize)), gridZ * gridSize + flowerRand.nextInt(gridSize));

                    // Felucia Plants 1-4
                    if (flowerRand.nextFloat() < 0.15f) new WorldGenFeluciaPlant1().generate(world, flowerRand, gridX * gridSize + flowerRand.nextInt(gridSize), world.getTopSolidOrLiquidBlock(gridX * gridSize + flowerRand.nextInt(gridSize), gridZ * gridSize + flowerRand.nextInt(gridSize)), gridZ * gridSize + flowerRand.nextInt(gridSize));
                    if (flowerRand.nextFloat() < 0.15f) new WorldGenFeluciaPlant2().generate(world, flowerRand, gridX * gridSize + flowerRand.nextInt(gridSize), world.getTopSolidOrLiquidBlock(gridX * gridSize + flowerRand.nextInt(gridSize), gridZ * gridSize + flowerRand.nextInt(gridSize)), gridZ * gridSize + flowerRand.nextInt(gridSize));
                    if (flowerRand.nextFloat() < 0.15f) new WorldGenFeluciaPlant3().generate(world, flowerRand, gridX * gridSize + flowerRand.nextInt(gridSize), world.getTopSolidOrLiquidBlock(gridX * gridSize + flowerRand.nextInt(gridSize), gridZ * gridSize + flowerRand.nextInt(gridSize)), gridZ * gridSize + flowerRand.nextInt(gridSize));
                    if (flowerRand.nextFloat() < 0.15f) new WorldGenFeluciaPlant4().generate(world, flowerRand, gridX * gridSize + flowerRand.nextInt(gridSize), world.getTopSolidOrLiquidBlock(gridX * gridSize + flowerRand.nextInt(gridSize), gridZ * gridSize + flowerRand.nextInt(gridSize)), gridZ * gridSize + flowerRand.nextInt(gridSize));

                    // Felucia Plant A14
                    if (flowerRand.nextFloat() < 0.15f) new WorldGenFeluciaPlantA1().generate(world, flowerRand, gridX * gridSize + flowerRand.nextInt(gridSize), world.getTopSolidOrLiquidBlock(gridX * gridSize + flowerRand.nextInt(gridSize), gridZ * gridSize + flowerRand.nextInt(gridSize)), gridZ * gridSize + flowerRand.nextInt(gridSize));
                    if (flowerRand.nextFloat() < 0.15f) new WorldGenFeluciaPlantA2().generate(world, flowerRand, gridX * gridSize + flowerRand.nextInt(gridSize), world.getTopSolidOrLiquidBlock(gridX * gridSize + flowerRand.nextInt(gridSize), gridZ * gridSize + flowerRand.nextInt(gridSize)), gridZ * gridSize + flowerRand.nextInt(gridSize));
                    if (flowerRand.nextFloat() < 0.15f) new WorldGenFeluciaPlantA3().generate(world, flowerRand, gridX * gridSize + flowerRand.nextInt(gridSize), world.getTopSolidOrLiquidBlock(gridX * gridSize + flowerRand.nextInt(gridSize), gridZ * gridSize + flowerRand.nextInt(gridSize)), gridZ * gridSize + flowerRand.nextInt(gridSize));
                    if (flowerRand.nextFloat() < 0.15f) new WorldGenFeluciaPlantA4().generate(world, flowerRand, gridX * gridSize + flowerRand.nextInt(gridSize), world.getTopSolidOrLiquidBlock(gridX * gridSize + flowerRand.nextInt(gridSize), gridZ * gridSize + flowerRand.nextInt(gridSize)), gridZ * gridSize + flowerRand.nextInt(gridSize));

                    // Felucia Plant B14
                    if (flowerRand.nextFloat() < 0.15f) new WorldGenFeluciaPlantB1().generate(world, flowerRand, gridX * gridSize + flowerRand.nextInt(gridSize), world.getTopSolidOrLiquidBlock(gridX * gridSize + flowerRand.nextInt(gridSize), gridZ * gridSize + flowerRand.nextInt(gridSize)), gridZ * gridSize + flowerRand.nextInt(gridSize));
                    if (flowerRand.nextFloat() < 0.15f) new WorldGenFeluciaPlantB2().generate(world, flowerRand, gridX * gridSize + flowerRand.nextInt(gridSize), world.getTopSolidOrLiquidBlock(gridX * gridSize + flowerRand.nextInt(gridSize), gridZ * gridSize + flowerRand.nextInt(gridSize)), gridZ * gridSize + flowerRand.nextInt(gridSize));
                    if (flowerRand.nextFloat() < 0.15f) new WorldGenFeluciaPlantB3().generate(world, flowerRand, gridX * gridSize + flowerRand.nextInt(gridSize), world.getTopSolidOrLiquidBlock(gridX * gridSize + flowerRand.nextInt(gridSize), gridZ * gridSize + flowerRand.nextInt(gridSize)), gridZ * gridSize + flowerRand.nextInt(gridSize));
                    if (flowerRand.nextFloat() < 0.15f) new WorldGenFeluciaPlantB4().generate(world, flowerRand, gridX * gridSize + flowerRand.nextInt(gridSize), world.getTopSolidOrLiquidBlock(gridX * gridSize + flowerRand.nextInt(gridSize), gridZ * gridSize + flowerRand.nextInt(gridSize)), gridZ * gridSize + flowerRand.nextInt(gridSize));
                }
            }
        } else {
            for (int i = 0; i < 4; i++) {
                int x = chunkX + random.nextInt(16);
                int z = chunkZ + random.nextInt(16);
                int y = world.getTopSolidOrLiquidBlock(x, z);
                new WorldGenBigMushroom(random.nextBoolean() ? 1 : 0).generate(world, random, x, y, z);
            }
        }

        if (random.nextInt(4) == 0) {
            int x = chunkX + random.nextInt(16);
            int z = chunkZ + random.nextInt(16);
            int y = world.getTopSolidOrLiquidBlock(x, z);
            new WorldGenFeluciaMushroom().generate(world, random, x, y, z);
        }

        if (random.nextInt(10) == 0) {
            int lakeX = chunkX + random.nextInt(16);
            int lakeZ = chunkZ + random.nextInt(16);
            int lakeY = random.nextInt(128);
            new WorldGenLakes(Blocks.water).generate(world, random, lakeX, lakeY, lakeZ);
        }

        for (int i = 0; i < 10; i++) {
            int x = chunkX + random.nextInt(16);
            int z = chunkZ + random.nextInt(16);
            int y = world.getTopSolidOrLiquidBlock(x, z);
            if (world.getBlock(x, y - 1, z) == Blocks.grass || world.getBlock(x, y - 1, z) == Blocks.dirt) {
                world.setBlock(x, y, z, Blocks.double_plant, 2, 2);
                world.setBlock(x, y + 1, z, Blocks.double_plant, 10, 2);
            }
        }

        for (int i = 0; i < 8; i++) {
            int x = chunkX + random.nextInt(16);
            int z = chunkZ + random.nextInt(16);
            int y = world.getTopSolidOrLiquidBlock(x, z);
            if (world.getBlock(x, y - 1, z) == Blocks.grass || world.getBlock(x, y - 1, z) == Blocks.dirt) {
                Block mushroomType = random.nextBoolean() ? Blocks.red_mushroom : Blocks.brown_mushroom;
                world.setBlock(x, y, z, mushroomType);
            }
        }
    }

    @SideOnly(value = Side.CLIENT)
    public Vec3 getFogColor(float par1, float par2) {
        float f2 = MathHelper.cos(par1 * (float) Math.PI * 2.0f) * 2.0f + 0.5f;
        if (f2 < 0.0f) {
            f2 = 0.0f;
        }
        if (f2 > 1.0f) {
            f2 = 1.0f;
        }

        // Decimal color 13342303 -> RGB(203, 230, 255)
        float r = 203 / 255.0f; // Normalize to [0, 1]
        float g = 230 / 255.0f; // Normalize to [0, 1]
        float b = 255 / 255.0f; // Normalize to [0, 1]

        r *= f2 * 0.94f + 0.06f;
        g *= f2 * 0.94f + 0.06f;
        b *= f2 * 0.94f + 0.06f;

        return Vec3.createVectorHelper(r, g, b);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getBiomeFoliageColor(int x, int y, int z) {
        return 8225876; // Dull junkyard foliage (dry and desaturated)
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getWaterColorMultiplier() {
        return 0x3DBB3D; // Green water color
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getSkyColorByTemp(float temp) {
        return 13342303; // Grayish sky for a wasteland feel
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getBiomeGrassColor(int x, int y, int z) {
        return 8747848; // Similar to foliage, indicating dryness
    }
}

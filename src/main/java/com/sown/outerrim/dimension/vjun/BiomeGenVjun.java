package com.sown.outerrim.dimension.vjun;

import java.util.Random;

import com.sown.util.world.creation.ORBiomeDecorator;
import com.sown.util.world.creation.OROverworldBiomeDecorator;
import com.sown.util.world.creation.ORSubBiome;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import com.sown.outerrim.registry.FluidRegister;
import com.sown.outerrim.fluids.BlockFluidCustom;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.fluids.Fluid;

public class BiomeGenVjun extends ORSubBiome {
    private ORBiomeDecorator customBiomeDecorator;
    private static final BiomeGenBase.Height biomeHeight = new BiomeGenBase.Height(0.1f, 0.2f);

    // Reference the acid fluid and block
    static Fluid acidFluid = FluidRegister.getRegisteredFluid("liquid_acid");
    static Block acidBlock = acidFluid.getBlock();
    static BlockFluidCustom customAcidBlock = (BlockFluidCustom) acidBlock;

    public BiomeGenVjun(int biomeID) {
        super(biomeID);

        // Set the general biome properties
        this.zoom = 0.4;
        this.threshold = 0.5;
        this.setHeight(biomeHeight);
        this.setDisableRain();
        this.setColor(5724966); // Acidic sky color in hexadecimal (equivalent to 5724966 in decimal)
        this.setTemperatureRainfall(0.8f, 0.1f); // Hot and dry

        // Remove all spawn lists
        this.spawnableCreatureList.clear();
        this.spawnableCaveCreatureList.clear();
        this.spawnableMonsterList.clear();
        this.spawnableWaterCreatureList.clear();

        // Set top and filler blocks
        this.topBlock = Blocks.stone; // Acid-corrupted dirt
        this.fillerBlock = Blocks.stone; // Underlying stone layer

        // Adjust decoration settings
        ((OROverworldBiomeDecorator) this.theBiomeDecorator).treesPerChunk = 0;
        ((OROverworldBiomeDecorator) this.theBiomeDecorator).cactiPerChunk = -999;
        ((OROverworldBiomeDecorator) this.theBiomeDecorator).reedsPerChunk = -999;
        ((OROverworldBiomeDecorator) this.theBiomeDecorator).deadBushPerChunk = 1; // Sparse dead bushes
        this.customBiomeDecorator = this.theBiomeDecorator;
        this.customBiomeDecorator.generateLakes = false;

        // Custom values for biome generation
        this.customBiomeDecorator.deadBushPerChunk = 2;
        this.customBiomeDecorator.reedsPerChunk = -999;
    }

    @Override
    public void decorate(World world, Random random, int chunkX, int chunkZ) {
        super.decorate(world, random, chunkX, chunkZ);

        // Replace all water with acid
        replaceWaterWithAcid(world, chunkX, chunkZ);

        if (random.nextInt(30) == 0) {
            int x = chunkX + random.nextInt(16);
            int z = chunkZ + random.nextInt(16);
            int y = world.getTopSolidOrLiquidBlock(x, z);

            // Check 7x7 base for solid support
            boolean solidBase = true;
            for (int dx = -3; dx <= 3 && solidBase; dx++) {
                for (int dz = -3; dz <= 3 && solidBase; dz++) {
                    Block baseBlock = world.getBlock(x + dx, y - 1, z + dz);
                    if (baseBlock == null || !baseBlock.getMaterial().isSolid()) {
                        solidBase = false;
                    }
                }
            }

            if (solidBase) {
                new WorldGenVjunSpire().generate(world, random, x, y, z);
            }
        }
    }

    private void replaceWaterWithAcid(World world, int chunkX, int chunkZ) {
        for (int x = chunkX; x < chunkX + 16; x++) {
            for (int z = chunkZ; z < chunkZ + 16; z++) {
                for (int y = 0; y < 256; y++) { // Loop through all heights
                    if (world.getBlock(x, y, z) == Blocks.water || world.getBlock(x, y, z) == Blocks.flowing_water) {
                        world.setBlock(x, y, z, customAcidBlock);
                    }
                }
            }
        }
    }

    @SideOnly(Side.CLIENT)
    public int getSkyColorByTemp(float temp) {
        return 5724966; // Acidic greenish-brown sky in decimal (hex: 585B26)
    }

    @SideOnly(Side.CLIENT)
    public int getWaterColorMultiplier() {
        return 5724966; // Match water color multiplier to the sky color
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getBiomeFoliageColor(int x, int y, int z) {
        return 5724966; // Muted acidic foliage color (matches theme)
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getBiomeGrassColor(int x, int y, int z) {
        return 5724966; // Muted acidic grass color (matches theme)
    }
}

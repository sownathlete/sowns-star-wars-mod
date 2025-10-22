package com.sown.outerrim.dimension.ilum;

import java.util.Random;

import com.sown.outerrim.OuterRimResources;
import com.sown.outerrim.registry.BlockRegister;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;

public class BiomeGenIlumPlains extends IlumBiomes {

    public BiomeGenIlumPlains() {
        super(OuterRimResources.ConfigOptions.biomeIlumPlainsId); // Unique biome ID (adjust if needed)

        // General biome properties
        this.enableRain = false;
        this.enableSnow = true; // Snowy biome
        this.rootHeight = 0.125F; // Slightly above flat
        this.heightVariation = 0.05F; // Minimal undulations for plains
        this.setTemperatureRainfall(-1.0F, 0.5F); // Cold and snowy
        this.spawnableCreatureList.clear(); // No passive mobs
        this.spawnableMonsterList.clear(); // No monsters

        // Block assignments
        this.topBlock = Blocks.snow; // Snow on top
        this.topMeta = 0;
        this.fillerBlock = BlockRegister.getRegisteredBlock("ilumIce"); // Ice underneath
        this.fillerMeta = 0;
        this.stoneBlock = BlockRegister.getRegisteredBlock("ilumRock"); // Ilum rock as stone
        this.stoneMeta = 0;
    }

    @Override
    public int getBiomeGrassColor(int x, int y, int z) {
        return 12632256; // A frosty, pale gray color
    }

    @Override
    public int getBiomeFoliageColor(int x, int y, int z) {
        return 12632256; // Matches the frosty theme
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getSkyColorByTemp(float temp) {
        return 12306124; // A cold, pale blue sky
    }

    @Override
    public void decorate(World world, Random random, int chunkX, int chunkZ) {
        super.decorate(world, random, chunkX, chunkZ);

        for (int x = chunkX; x < chunkX + 16; x++) {
            for (int z = chunkZ; z < chunkZ + 16; z++) {
                int y = world.getTopSolidOrLiquidBlock(x, z);

                // Check if the top block is snow and the block below is ilumRock
                if (world.getBlock(x, y - 1, z) == BlockRegister.getRegisteredBlock("ilumRock") &&
                    world.getBlock(x, y, z) == Blocks.snow_layer) {
                    // Replace ilumRock with ilumRockSnowy
                    world.setBlock(x, y - 1, z, BlockRegister.getRegisteredBlock("ilumRockSnowy"), 0, 2);
                }
            }
        }
    }
}

package com.sown.outerrim.dimension.mandalore;

import java.util.Random;

import com.sown.outerrim.OuterRimResources;
import com.sown.outerrim.registry.BlockRegister;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;

public class BiomeGenMandaloreDesert extends MandaloreBiomes {

    public BiomeGenMandaloreDesert() {
        super(OuterRimResources.ConfigOptions.biomeMandaloreId); // Unique biome ID

        // General biome properties
        this.enableRain = false; // Deserts typically don't have rain
        this.enableSnow = false;
        this.rootHeight = 0.125F; // Slightly above flat
        this.heightVariation = 0.00F; // Minimal terrain variation
        this.setTemperatureRainfall(2.0F, 0.0F); // Hot and dry climate

        // Block assignments
        this.topBlock = BlockRegister.getRegisteredBlock("mandaloreSand");
        this.topMeta = 0;
        this.fillerBlock = BlockRegister.getRegisteredBlock("mandaloreSandstone");
        this.fillerMeta = 0;
        this.stoneBlock = Blocks.stone;
        this.stoneMeta = 0;
    }

    @Override
    public int getBiomeGrassColor(int x, int y, int z) {
        return 16767387; // A pale desert-like color
    }

    @Override
    public int getBiomeFoliageColor(int x, int y, int z) {
        return 16767387; // Matches grass color
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getSkyColorByTemp(float temp) {
        return 7368816; // Grayish sky for a wasteland feel
    }
}

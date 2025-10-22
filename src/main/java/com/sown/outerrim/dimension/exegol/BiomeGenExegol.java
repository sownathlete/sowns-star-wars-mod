package com.sown.outerrim.dimension.exegol;

import java.util.Random;

import com.sown.outerrim.registry.BlockRegister;
import com.sown.util.world.creation.ORBiomeDecorator;
import com.sown.util.world.creation.ORSubBiome;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;

public class BiomeGenExegol extends ORSubBiome {
    private static final BiomeGenBase.Height biomeHeight = new BiomeGenBase.Height(0.2f, 0.0f);
    private ORBiomeDecorator customBiomeDecorator;

    public BiomeGenExegol(int biomeID) {
        super(biomeID);
        
        this.setHeight(biomeHeight);
        this.setDisableRain();
        this.setColor(0x073F53); // Dark cyan sky
        this.setTemperatureRainfall(2.0F, 0.0F); // High temperature, no rain
        this.enableSnow = false;
        this.enableRain = false;
        
        // No mobs, no plants
        this.spawnableCreatureList.clear();
        this.spawnableCaveCreatureList.clear();
        this.spawnableMonsterList.clear();
        this.spawnableWaterCreatureList.clear();

        // Flat terrain with Exegol Stone
        this.topBlock = Blocks.stone;
        this.fillerBlock = Blocks.stone;

        // No natural structures or vegetation
        this.customBiomeDecorator = this.theBiomeDecorator;
        this.customBiomeDecorator.generateLakes = false;
        this.customBiomeDecorator.treesPerChunk = 0;
        this.customBiomeDecorator.deadBushPerChunk = 0;
    }
    
    @Override
    public boolean canSpawnLightningBolt() {
        return true; // No storms or snow
    }

    @Override
    public boolean getEnableSnow() {
        return false; // Completely disables snow layers
    }

    @SideOnly(Side.CLIENT)
    public int getSkyColorByTemp(float temp) {
        return 0x073F53; // Dark cyan sky
    }

    @SideOnly(Side.CLIENT)
    public int getWaterColorMultiplier() {
        return 0x074D61; // Darker cyan fog
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getBiomeFoliageColor(int x, int y, int z) {
        return 0x073F53; // Dark cyan tint
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getBiomeGrassColor(int x, int y, int z) {
        return 0x073F53; // Dark cyan tint
    }

    @Override
    public void decorate(World world, Random random, int chunkX, int chunkZ) {
        super.decorate(world, random, chunkX, chunkZ);
    }
}

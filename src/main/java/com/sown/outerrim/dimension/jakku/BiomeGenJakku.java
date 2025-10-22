/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockSand
 *  net.minecraft.init.Blocks
 *  net.minecraft.world.World
 *  net.minecraft.world.biome.BiomeGenBase
 *  net.minecraft.world.biome.BiomeGenBase$Height
 */
package com.sown.outerrim.dimension.jakku;

import java.util.List;
import java.util.Random;

import com.sown.util.world.creation.ORBiomeDecorator;
import com.sown.util.world.creation.OROverworldBiomeDecorator;
import com.sown.util.world.creation.ORSubBiome;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSand;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;

public class BiomeGenJakku
extends ORSubBiome {
    private ORBiomeDecorator customBiomeDecorator;
    private static final BiomeGenBase.Height biomeHeight = new BiomeGenBase.Height(0.9f, 0.3f);

    public BiomeGenJakku(int biomeID) {
        super(biomeID);
        this.zoom = 0.5;
        this.threshold = 0.5;
        this.setHeight(biomeHeight);
        this.setDisableRain();
        this.setColor(7712283);
        this.setTemperatureRainfall(0.5f, 0.9f);
        this.spawnableCreatureList.clear();
        this.spawnableCaveCreatureList.clear();
        this.spawnableMonsterList.clear();
        this.spawnableWaterCreatureList.clear();
        this.topBlock = Blocks.sand;
        this.fillerBlock = Blocks.sand;
        ((OROverworldBiomeDecorator)this.theBiomeDecorator).treesPerChunk = -999;
        ((OROverworldBiomeDecorator)this.theBiomeDecorator).cactiPerChunk = -999;
        ((OROverworldBiomeDecorator)this.theBiomeDecorator).reedsPerChunk = -999;
        this.customBiomeDecorator = this.theBiomeDecorator;
        this.customBiomeDecorator.treesPerChunk = -999;
        this.customBiomeDecorator.deadBushPerChunk = -999;
        this.customBiomeDecorator.reedsPerChunk = -999;
        this.customBiomeDecorator.reedsPerChunk = -999;
        this.customBiomeDecorator.generateLakes = false;
        this.spawnableMonsterList.clear();
    }

    @Override
    public void decorate(World world, Random random, int chunkX, int chunkZ) {
        super.decorate(world, random, chunkX, chunkZ);
        int var5 = 12 + random.nextInt(6);
        for (int var6 = 0; var6 < var5; ++var6) {
            int y;
            int z;
            int x = chunkX + random.nextInt(16);
            Block block = world.getBlock(x, y = random.nextInt(28) + 4, z = chunkZ + random.nextInt(16));
        }
    }

    @SideOnly(value=Side.CLIENT)
    public int getSkyColorByTemp(float temp) {
        return 6076124;
    }
}


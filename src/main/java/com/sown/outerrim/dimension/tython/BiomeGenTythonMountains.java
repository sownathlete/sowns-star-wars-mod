/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.init.Blocks
 *  net.minecraft.world.World
 *  net.minecraft.world.biome.BiomeDecorator
 *  net.minecraft.world.biome.BiomeGenBase
 *  net.minecraft.world.biome.BiomeGenBase$Height
 */
package com.sown.outerrim.dimension.tython;

import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeDecorator;
import net.minecraft.world.biome.BiomeGenBase;

public class BiomeGenTythonMountains
extends BiomeGenBase {
    public int yPos;

    public BiomeGenTythonMountains(int id) {
        super(id);
        this.setHeight(new BiomeGenBase.Height(1.5f, 0.5f));
        this.setColor(1681198);
        this.setTemperatureRainfall(0.95f, 0.9f);
        this.topBlock = Blocks.stone;
        this.fillerBlock = Blocks.stone;
        this.setBiomeName("Tython Mountains");
        this.spawnableMonsterList.clear();
        this.spawnableCreatureList.clear();
        this.spawnableWaterCreatureList.clear();
        this.spawnableCaveCreatureList.clear();
    }

    public void decorate(World world, Random rand, int chunkX, int chunkZ) {
        this.theBiomeDecorator.decorateChunk(world, rand, (BiomeGenBase)this, chunkX, chunkZ);
    }
}


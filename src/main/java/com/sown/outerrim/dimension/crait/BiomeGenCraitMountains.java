/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.init.Blocks
 *  net.minecraft.world.World
 *  net.minecraft.world.biome.BiomeDecorator
 *  net.minecraft.world.biome.BiomeGenBase
 */
package com.sown.outerrim.dimension.crait;

import com.sown.outerrim.registry.BlockRegister;

import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;

public class BiomeGenCraitMountains
extends BiomeGenBase {

    public BiomeGenCraitMountains(int biomeId) {
        super(biomeId);
        this.setBiomeName("Crait Mountains");
        this.rootHeight = 2.25f;
        this.heightVariation = 0.025f;
        this.temperature = 0.0f;
        this.rainfall = 1.0f;
        this.spawnableCreatureList.clear();
        this.spawnableCaveCreatureList.clear();
        this.spawnableMonsterList.clear();
        this.spawnableWaterCreatureList.clear();
        this.topBlock = BlockRegister.getRegisteredBlock("salt");
        this.fillerBlock = Blocks.stone;
        this.theBiomeDecorator.treesPerChunk = -999;
        this.theBiomeDecorator.deadBushPerChunk = -999;
        this.theBiomeDecorator.reedsPerChunk = -999;
        this.theBiomeDecorator.cactiPerChunk = -999;
        this.spawnableCreatureList.clear();
        this.spawnableCaveCreatureList.clear();
        this.spawnableMonsterList.clear();
        this.spawnableWaterCreatureList.clear();
    }
}


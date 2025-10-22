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

public class BiomeGenCrait
extends BiomeGenBase {

    public BiomeGenCrait(int biomeId) {
        super(biomeId);
        this.setBiomeName("Crait");
        this.rootHeight = 0.1f;
        this.heightVariation = 0.000000f;
        this.temperature = 0.0f;
        this.rainfall = 1.0f;
        this.spawnableCreatureList.clear();
        this.spawnableCaveCreatureList.clear();
        this.spawnableMonsterList.clear();
        this.spawnableWaterCreatureList.clear();
        this.topBlock = BlockRegister.getRegisteredBlock("salt_layer");
        this.fillerBlock = BlockRegister.getRegisteredBlock("redSalt");
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


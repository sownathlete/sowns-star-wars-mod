/*
 * Decompiled with CFR 0.148.
 *
 * Could not load the following classes:
 *  micdoodle8.mods.galacticraft.api.prefab.world.gen.BiomeDecoratorSpace
 *  micdoodle8.mods.galacticraft.core.world.gen.WorldGenOuterRimMinableMeta
 *  net.minecraft.block.Block
 *  net.minecraft.world.World
 *  net.minecraft.world.gen.feature.WorldGenerator
 */
package com.sown.outerrim.dimension.geonosis;

import com.sown.outerrim.OuterRim;
import com.sown.outerrim.registry.BlockRegister;
import com.sown.util.world.creation.BiomeDecoratorPreset;
import com.sown.util.world.creation.worldgen.WorldGenOuterRimMinableMeta;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class BiomeDecoratorGeonosisOres
extends BiomeDecoratorPreset {
    private WorldGenerator ironGen = new WorldGenOuterRimMinableMeta(Blocks.iron_ore, 8, 2, true, BlockRegister.getRegisteredBlock("geonosisRock"), 1);
    private WorldGenerator coalGen = new WorldGenOuterRimMinableMeta(Blocks.coal_ore, 8, 5, true, BlockRegister.getRegisteredBlock("geonosisRock"), 1);
    private WorldGenerator diamondGen = new WorldGenOuterRimMinableMeta(Blocks.diamond_ore, 8, 6, true, BlockRegister.getRegisteredBlock("geonosisRock"), 1);
    private WorldGenerator goldGen = new WorldGenOuterRimMinableMeta(Blocks.gold_ore, 8, 8, true, BlockRegister.getRegisteredBlock("geonosisRock"), 1);
    private WorldGenerator lapisGen = new WorldGenOuterRimMinableMeta(Blocks.lapis_ore, 8, 9, true, BlockRegister.getRegisteredBlock("geonosisRock"), 1);
    private WorldGenerator redstoneGen = new WorldGenOuterRimMinableMeta(Blocks.redstone_ore, 8, 10, true, BlockRegister.getRegisteredBlock("geonosisRock"), 1);
    private World currentWorld;
    private boolean isDecorating = false;

    @Override
    protected void setCurrentWorld(World world) {
        this.currentWorld = world;
    }

    @Override
    protected World getCurrentWorld() {
        return this.currentWorld;
    }

    @Override
    protected void decorate() {
        if (this.isDecorating)
            return;
        this.isDecorating = true;
        //int amountPerChunk, WorldGenerator worldGenerator, int minY, int maxY
        this.generateOre(4, this.ironGen, 0, 25);
        this.generateOre(4, this.coalGen, 0, 64);
        this.generateOre(4, this.diamondGen, 0, 10);
        this.generateOre(4, this.lapisGen, 0, 10);
        this.generateOre(4, this.goldGen, 0, 10);
        this.generateOre(4, this.redstoneGen, 0, 10);
        this.isDecorating = false;
    }
}


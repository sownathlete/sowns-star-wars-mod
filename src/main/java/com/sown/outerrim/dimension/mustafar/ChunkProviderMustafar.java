/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 *  net.minecraft.block.Block
 *  net.minecraft.world.World
 *  net.minecraft.world.chunk.IChunkProvider
 */
package com.sown.outerrim.dimension.mustafar;

import com.google.common.collect.Lists;
import com.sown.outerrim.dimension.mustafar.BiomeDecoratorMustafarOres;
import com.sown.outerrim.dimension.mustafar.MapGenCaveMustafar;
import com.sown.outerrim.dimension.mustafar.MapGenRavineMustafar;
import com.sown.util.world.creation.ChunkProviderLavaPreset;
import com.sown.util.world.creation.MapGenBaseMeta;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;

public class ChunkProviderMustafar
extends ChunkProviderLavaPreset {
    private MapGenCaveMustafar caveGenerator = new MapGenCaveMustafar();
    private MapGenRavineMustafar ravineGenerator = new MapGenRavineMustafar();
    private final BiomeDecoratorMustafarOres BiomeDecorator = new BiomeDecoratorMustafarOres();

    @Override
    protected List<MapGenBaseMeta> getWorldGenerators() {
        ArrayList generators = Lists.newArrayList();
        generators.add(this.caveGenerator);
        return generators;
    }

    public ChunkProviderMustafar(World world, long seed, boolean flag) {
        super(world, seed, flag);
    }

    @Override
    public void decoratePlanet(World world, Random rand, int x, int z) {
        this.BiomeDecorator.decorate(world, rand, x, z);
    }

    @Override
    public String makeString() {
        return "MustafarLevelSource";
    }

    @Override
    public void onChunkProvide(int cX, int cZ, Block[] blocks, byte[] metadata) {
        this.ravineGenerator.func_151539_a((IChunkProvider)this, this.worldObj, cX, cZ, blocks);
    }

    @Override
    public void recreateStructures(int paramInt1, int paramInt2) {
    }
}


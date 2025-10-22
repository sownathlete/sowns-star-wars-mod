package com.sown.outerrim.dimension.geonosis;

import com.google.common.collect.Lists;
import com.sown.util.world.creation.ChunkProviderDryPreset;
import com.sown.util.world.creation.ChunkProviderLavaPreset;
import com.sown.util.world.creation.MapGenBaseMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;

public class ChunkProviderGeonosis extends ChunkProviderDryPreset {
    private MapGenCaveGeonosis caveGenerator = new MapGenCaveGeonosis();

    private MapGenRavineGeonosis ravineGenerator = new MapGenRavineGeonosis();

    private final BiomeDecoratorGeonosisOres BiomeDecorator = new BiomeDecoratorGeonosisOres();

    @Override
    protected List<MapGenBaseMeta> getWorldGenerators() {
        ArrayList generators = Lists.newArrayList();
        generators.add(this.caveGenerator);
        return generators;
    }

    public ChunkProviderGeonosis(World world, long seed, boolean flag) {
        super(world, seed, flag);
    }

    @Override
    public void decoratePlanet(World world, Random rand, int x, int z) {
        this.BiomeDecorator.decorate(world, rand, x, z);
    }

    @Override
    public String makeString() {
        return "GeonosisLevelSource";
    }

    @Override
    public void onChunkProvide(int cX, int cZ, Block[] blocks, byte[] metadata) {
        this.ravineGenerator.func_151539_a(this, this.worldObj, cX, cZ, blocks);
    }

    @Override
    public void recreateStructures(int paramInt1, int paramInt2) {
        // TODO Auto-generated method stub

    }
}

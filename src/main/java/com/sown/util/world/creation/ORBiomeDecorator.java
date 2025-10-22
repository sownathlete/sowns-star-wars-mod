package com.sown.util.world.creation;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import com.sown.util.world.creation.worldgen.WorldGenFieldAssociation;

import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeDecorator;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.event.terraingen.DecorateBiomeEvent;
import net.minecraftforge.event.terraingen.TerrainGen;

public class ORBiomeDecorator<T extends BiomeFeatures> extends BiomeDecorator {
    public BiomeFeatures orFeatures;

    public ORBiomeDecorator(Class<T> biomeFeaturesClass) {
        try {
            this.orFeatures = biomeFeaturesClass.newInstance();
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    @Override
    public void decorateChunk(World world, Random random, BiomeGenBase biome, int chunkX, int chunkZ) {
        if (this.currentWorld != null)
            return;
        this.currentWorld = world;
        this.randomGenerator = new RandomForcedPositiveOwned(random);
        this.chunk_X = chunkX;
        this.chunk_Z = chunkZ;
        this.genDecorations(biome);
        this.currentWorld = null;
        this.randomGenerator = null;
    }

    @Override
    protected void genDecorations(BiomeGenBase biome) {
        super.genDecorations(biome);
        ORBiome bopBiome = (ORBiome)biome;
        for (String featureName : this.orFeatures.getFeatureNames()) {
            if (featureName.equals("bopFlowersPerChunk") ? !TerrainGen.decorate(this.currentWorld, this.randomGenerator, this.chunk_X, this.chunk_Z, DecorateBiomeEvent.Decorate.EventType.FLOWERS) : (featureName.equals("bopGrassPerChunk") && !TerrainGen.decorate(this.currentWorld, this.randomGenerator, this.chunk_X, this.chunk_Z, DecorateBiomeEvent.Decorate.EventType.GRASS))) {
                continue;
            }
            WorldGenFieldAssociation.WorldFeature worldFeature = WorldGenFieldAssociation.getAssociatedFeature(featureName);
            IORWorldGenerator worldGenerator;
            if (worldFeature == null || (worldGenerator = worldFeature.getBOPWorldGenerator()) == null) {
                continue;
            }
            worldGenerator.setupGeneration(this.currentWorld, this.randomGenerator, bopBiome, featureName, this.chunk_X, this.chunk_Z);
        }
    }

    public static <T extends net.minecraft.world.gen.feature.WorldGenerator> T getRandomWeightedWorldGenerator(HashMap<T, ? extends Number> worldGeneratorMap) {
        double completeWeight = 0.0D;
        for (Number weight : worldGeneratorMap.values()) {
            completeWeight += Double.parseDouble(weight.toString());
        }
        double random = Math.random() * completeWeight;
        double countWeight = 0.0D;
        for (Map.Entry<T, ? extends Number> entry : worldGeneratorMap.entrySet()) {
            if ((countWeight += Double.parseDouble(((Number)entry.getValue()).toString())) < random) {
                continue;
            }
            return entry.getKey();
        }
        return null;
    }

    protected int nextInt(int i) {
        if (i <= 1)
            return 0;
        return this.randomGenerator.nextInt(i);
    }
}

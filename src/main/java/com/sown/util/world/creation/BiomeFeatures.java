package com.sown.util.world.creation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import net.minecraft.world.gen.feature.WorldGenerator;

public class BiomeFeatures {
    public HashMap<WorldGenerator, Double> weightedGrassGen = new HashMap<>();

    public HashMap<WorldGenerator, Integer> weightedFlowerGen = new HashMap<>();

    private ArrayList<String> features = new ArrayList<>();

    @BiomeFeature
    public int bopFlowersPerChunk = 0;

    @BiomeFeature
    public int bopGrassPerChunk = 0;

    public BiomeFeatures() {
        for (Field field : this.getClass().getFields()) {
            if (field.isAnnotationPresent(BiomeFeature.class))
                if (!this.features.contains(field.getName())) {
                    this.features.add(field.getName());
                } else
                    throw new FeatureExistsException(field.getName());
        }
    }

    public Object getFeature(String featureName) {
        try {
            return this.getClass().getField(featureName).get(this);
        } catch (Exception e) {
            throw new NoSuchFeatureException(featureName);
        }
    }

    public ArrayList<String> getFeatureNames() {
        return this.features;
    }

    @Retention(RetentionPolicy.RUNTIME)
    @Target({ElementType.FIELD})
    protected static @interface BiomeFeature {}
}

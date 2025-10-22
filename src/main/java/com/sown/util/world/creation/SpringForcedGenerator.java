/*
 * Decompiled with CFR 0.148.
 *
 * Could not load the following classes:
 *  net.minecraft.world.World
 *  net.minecraft.world.gen.feature.WorldGenerator
 */
package com.sown.util.world.creation;

import java.util.Random;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class SpringForcedGenerator
extends ForcedWorldFeatureOR {
    public SpringForcedGenerator(WorldGenerator worldGenerator) {
        super(worldGenerator);
    }

    @Override
    public void setupGeneration(World world, Random random, ORBiome biome, String featureName, int x, int z) {
        if (biome.theBiomeDecorator.generateLakes) {
            int i;
            int randZ;
            int randY;
            int randX;
            for (i = 0; i < (Integer)biome.theBiomeDecorator.orFeatures.getFeature("waterSpringsPerChunk"); ++i) {
                randX = x + random.nextInt(16) + 8;
                randY = random.nextInt(random.nextInt(120) + 8);
                randZ = z + random.nextInt(16) + 8;
                this.generate(world, random, randX, randY, randZ);
            }
            for (i = 0; i < (Integer)biome.theBiomeDecorator.orFeatures.getFeature("lavaSpringsPerChunk"); ++i) {
                randX = x + random.nextInt(16) + 8;
                randY = random.nextInt(random.nextInt(random.nextInt(112) + 8) + 8);
                randZ = z + random.nextInt(16) + 8;
                this.generate(world, random, randX, randY, randZ);
            }
            for (i = 0; i < (Integer)biome.theBiomeDecorator.orFeatures.getFeature("acidSpringsPerChunk"); ++i) {
                randX = x + random.nextInt(16) + 8;
                randY = random.nextInt(random.nextInt(random.nextInt(112) + 8) + 8);
                randZ = z + random.nextInt(16) + 8;
                this.generate(world, random, randX, randY, randZ);
            }
            for (i = 0; i < (Integer)biome.theBiomeDecorator.orFeatures.getFeature("mudSpringsPerChunk"); ++i) {
                randX = x + random.nextInt(16) + 8;
                randY = random.nextInt(random.nextInt(random.nextInt(112) + 8) + 8);
                randZ = z + random.nextInt(16) + 8;
                this.generate(world, random, randX, randY, randZ);
            }
            for (i = 0; i < (Integer)biome.theBiomeDecorator.orFeatures.getFeature("kesselMudSpringsPerChunk"); ++i) {
                randX = x + random.nextInt(16) + 8;
                randY = random.nextInt(random.nextInt(random.nextInt(112) + 8) + 8);
                randZ = z + random.nextInt(16) + 8;
                this.generate(world, random, randX, randY, randZ);
            }
        }
    }
}


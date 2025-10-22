package com.sown.outerrim.dimension.naboo;

import com.sown.outerrim.OuterRimResources;

import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;

public class GenLayerRestrictMountains extends GenLayer {

    private static final int MOUNTAIN_BIOME_ID = OuterRimResources.ConfigOptions.biomeNabooMountainsId; // replace with your mountain biome ID
    private static final int DEFAULT_BIOME_ID = OuterRimResources.ConfigOptions.biomeNabooGreatPlainsId;   // replace with your default biome ID
    private static final int ALLOWED_MOUNTAIN_WIDTH = 16; // assuming one chunk width

    public GenLayerRestrictMountains(long seed, GenLayer genLayer) {
        super(seed);
        this.parent = genLayer;
    }

    @Override
    public int[] getInts(int areaX, int areaY, int areaWidth, int areaHeight) {
        int[] parentBiomes = this.parent.getInts(areaX, areaY, areaWidth, areaHeight);
        int[] newBiomes = IntCache.getIntCache(areaWidth * areaHeight);
        
        for (int y = 0; y < areaHeight; y++) {
            for (int x = 0; x < areaWidth; x++) {
                int currentIdx = x + y * areaWidth;
                newBiomes[currentIdx] = parentBiomes[currentIdx];

                if (parentBiomes[currentIdx] == MOUNTAIN_BIOME_ID) {
                    int count = 1;
                    
                    // Check left neighbors
                    for (int dx = 1; x - dx >= 0 && parentBiomes[currentIdx - dx] == MOUNTAIN_BIOME_ID; dx++) {
                        count++;
                    }
                    
                    // Check right neighbors
                    for (int dx = 1; x + dx < areaWidth && parentBiomes[currentIdx + dx] == MOUNTAIN_BIOME_ID; dx++) {
                        count++;
                    }
                    
                    // Replace the biomes if count exceeds allowed width
                    if (count > ALLOWED_MOUNTAIN_WIDTH) {
                        newBiomes[currentIdx] = DEFAULT_BIOME_ID;
                    }
                }
            }
        }

        return newBiomes;
    }
}

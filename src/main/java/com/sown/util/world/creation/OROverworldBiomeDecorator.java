package com.sown.util.world.creation;

import java.util.Random;
import net.minecraft.world.World;

/**
 * Abstract base class for biome decoration in the Overworld. This class provides
 * a framework for extending biome-specific decoration by subclasses.
 */
public class OROverworldBiomeDecorator extends ORBiomeDecorator<OverworldBiomeFeatures> {
    /**
     * Constructs an OverworldBiomeDecorator with features specific to Overworld biomes.
     */
    public OROverworldBiomeDecorator() {
        super(OverworldBiomeFeatures.class);
    }

    /**
     * Decorates a chunk of the world with biome-specific features.
     * This method can be overridden by subclasses to implement specific decoration behaviors.
     *
     * @param world The world in which decoration is taking place.
     * @param random A Random instance for random number generation during decoration.
     * @param chunkX The x coordinate of the chunk being decorated.
     * @param chunkZ The z coordinate of the chunk being decorated.
     */
    public void decorate(World world, Random random, int chunkX, int chunkZ) {
        // Default implementation: perform no decoration.
        // Subclasses should override this method to add specific decorations.
    }
}

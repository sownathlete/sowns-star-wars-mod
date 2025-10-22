package com.sown.util.world.creation;

import java.util.Random;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;

public class ORBiome<T extends ORBiomeDecorator> extends BiomeGenBase {
    public T theBiomeDecorator;

    public ORBiome(int biomeID, Class<T> clazz, boolean register) {
        super(biomeID, register);
        try {
            this.theBiomeDecorator = clazz.newInstance();
        } catch (Exception e) {
            throw new RuntimeException();
        }
        this.flowers.clear();
        this.addDefaultFlowers();
        ((ORBiomeDecorator)this.theBiomeDecorator).flowersPerChunk = 0;
        ((ORBiomeDecorator)this.theBiomeDecorator).grassPerChunk = 0;
    }

    public ORBiome(int biomeID, Class<T> clazz) {
        this(biomeID, clazz, true);
    }

    @Override
    public void decorate(World world, Random random, int chunkX, int chunkZ) {
        this.theBiomeDecorator.decorateChunk(world, random, this, chunkX, chunkZ);
    }
}

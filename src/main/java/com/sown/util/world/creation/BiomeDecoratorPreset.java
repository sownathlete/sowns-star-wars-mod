/*
 * Decompiled with CFR 0.148.
 *
 * Could not load the following classes:
 *  cpw.mods.fml.common.eventhandler.Event
 *  cpw.mods.fml.common.eventhandler.EventBus
 *  net.minecraft.world.World
 *  net.minecraft.world.gen.feature.WorldGenerator
 *  net.minecraftforge.common.MinecraftForge
 */
package com.sown.util.world.creation;

import cpw.mods.fml.common.eventhandler.Event;
import java.util.Random;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.common.MinecraftForge;

public abstract class BiomeDecoratorPreset {
    protected Random rand;
    protected int chunkX;
    protected int chunkZ;

    public void decorate(World world, Random random, int chunkX, int chunkZ) {
        if (this.getCurrentWorld() != null)
            throw new RuntimeException("Already decorating!!");
        this.setCurrentWorld(world);
        this.rand = random;
        this.chunkX = chunkX;
        this.chunkZ = chunkZ;
        MinecraftForge.EVENT_BUS.post((Event)new ORCoreEventPopulate.Pre(world, this.rand, this.chunkX, this.chunkZ));
        this.decorate();
        MinecraftForge.EVENT_BUS.post((Event)new ORCoreEventPopulate.Post(world, this.rand, this.chunkX, this.chunkZ));
        this.setCurrentWorld(null);
        this.rand = null;
    }

    protected abstract void setCurrentWorld(World var1);

    protected abstract World getCurrentWorld();

    protected void generateOre(int amountPerChunk, WorldGenerator worldGenerator, int minY, int maxY) {
        World currentWorld = this.getCurrentWorld();
        for (int var5 = 0; var5 < amountPerChunk; ++var5) {
            int var6 = this.chunkX + this.rand.nextInt(16);
            int var7 = this.rand.nextInt(maxY - minY) + minY;
            int var8 = this.chunkZ + this.rand.nextInt(16);
            worldGenerator.generate(currentWorld, this.rand, var6, var7, var8);
        }
    }

    protected abstract void decorate();
}


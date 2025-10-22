/*
 * Decompiled with CFR 0.148.
 *
 * Could not load the following classes:
 *  cpw.mods.fml.common.eventhandler.Event
 *  net.minecraft.world.World
 */
package com.sown.util.world.creation;

import cpw.mods.fml.common.eventhandler.Event;
import java.util.Random;
import net.minecraft.world.World;

public class ORCoreEventPopulate
extends Event {
    public final World worldObj;
    public final Random rand;
    public final int chunkX;
    public final int chunkZ;

    public ORCoreEventPopulate(World worldObj, Random rand, int chunkX, int chunkZ) {
        this.worldObj = worldObj;
        this.rand = rand;
        this.chunkX = chunkX;
        this.chunkZ = chunkZ;
    }

    public static class Post
    extends ORCoreEventPopulate {
        public Post(World world, Random rand, int worldX, int worldZ) {
            super(world, rand, worldX, worldZ);
        }
    }

    public static class Pre
    extends ORCoreEventPopulate {
        public Pre(World world, Random rand, int worldX, int worldZ) {
            super(world, rand, worldX, worldZ);
        }
    }

}


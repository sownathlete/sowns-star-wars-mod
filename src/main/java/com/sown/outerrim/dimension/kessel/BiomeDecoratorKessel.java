/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.eventhandler.Event
 *  cpw.mods.fml.common.eventhandler.EventBus
 *  net.minecraft.block.Block
 *  net.minecraft.world.World
 *  net.minecraft.world.biome.BiomeDecorator
 *  net.minecraft.world.biome.BiomeGenBase
 *  net.minecraftforge.common.MinecraftForge
 *  net.minecraftforge.event.terraingen.DecorateBiomeEvent
 *  net.minecraftforge.event.terraingen.DecorateBiomeEvent$Decorate
 *  net.minecraftforge.event.terraingen.DecorateBiomeEvent$Decorate$EventType
 *  net.minecraftforge.event.terraingen.DecorateBiomeEvent$Post
 *  net.minecraftforge.event.terraingen.DecorateBiomeEvent$Pre
 *  net.minecraftforge.event.terraingen.TerrainGen
 */
package com.sown.outerrim.dimension.kessel;

import com.sown.outerrim.OuterRim;
import com.sown.outerrim.dimension.kessel.WorldGenKesselMud;
import com.sown.outerrim.registry.BlockRegister;
import com.sown.outerrim.registry.FluidRegister;
import com.sown.util.block.ORBlock;
import com.sown.util.world.creation.worldgen.WorldGenHelper;

import cpw.mods.fml.common.eventhandler.Event;
import cpw.mods.fml.common.eventhandler.EventBus;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeDecorator;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.terraingen.DecorateBiomeEvent;
import net.minecraftforge.event.terraingen.TerrainGen;

public class BiomeDecoratorKessel
extends BiomeDecorator {
    public int LakesPerChunk;
    public int Acid1LakesPerChunk;
    public int Acid2LakesPerChunk;
    public int KesselAcidPerChunk;
    public int InfectedLakesPerChunk;
    public int testPerChunk;
    private World currentWorld;
    protected Random rand;
    protected int chunkX;
    protected int chunkZ;
    private boolean isDecorating = false;

    public void decorateChunk(World world, Random rand, BiomeGenBase biome, int xChunk, int zChunk) {
        int i;
        if (this.isDecorating) {
            return;
        }
        this.isDecorating = true;
        this.currentWorld = world;
        this.rand = rand;
        this.chunk_X = xChunk;
        this.chunk_Z = zChunk;
        MinecraftForge.EVENT_BUS.post((Event)new DecorateBiomeEvent.Pre(this.currentWorld, this.rand, this.chunkX, this.chunkZ));
        for (i = 0; TerrainGen.decorate((World)this.currentWorld, (Random)this.rand, (int)this.chunk_X, (int)this.chunk_Z, (DecorateBiomeEvent.Decorate.EventType)DecorateBiomeEvent.Decorate.EventType.LAKE) && i < this.Acid2LakesPerChunk; ++i) {
            if (this.rand.nextInt(10) != 0) continue;
            WorldGenHelper.generateLake(this.currentWorld, this.rand, this.chunk_X, this.rand.nextInt(256), this.chunk_Z, FluidRegister.getRegisteredFluid("liquid_acid"), BlockRegister.getRegisteredBlock("kesselAcidicRock2"));
        }
        for (i = 0; i < this.Acid2LakesPerChunk; ++i) {
            if (this.rand.nextInt(15) != 0) continue;
            WorldGenHelper.generateLake(this.currentWorld, this.rand, this.chunk_X, 0, this.chunk_Z, FluidRegister.getRegisteredFluid("liquid_acid"), BlockRegister.getRegisteredBlock("kesselAcidicRock2"));
        }
        for (i = 0; TerrainGen.decorate((World)this.currentWorld, (Random)this.rand, (int)this.chunk_X, (int)this.chunk_Z, (DecorateBiomeEvent.Decorate.EventType)DecorateBiomeEvent.Decorate.EventType.GRASS) && i < this.testPerChunk; ++i) {
            int x = this.chunk_X + this.rand.nextInt(8) + 8;
            int z = this.chunk_Z + this.rand.nextInt(8) + 8;
            int y = this.rand.nextInt(256);
            new WorldGenKesselMud((Block)BlockRegister.getRegisteredBlock("kesselMud"), 9, new Block[0]).generate(world, rand, x, y, z);
        }
        MinecraftForge.EVENT_BUS.post((Event)new DecorateBiomeEvent.Post(this.currentWorld, this.rand, this.chunkX, this.chunkZ));
        this.isDecorating = false;
    }
}


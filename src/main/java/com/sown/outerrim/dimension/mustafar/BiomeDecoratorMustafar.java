/*
 * Decompiled with CFR 0.148.
 *
 * Could not load the following classes:
 *  cpw.mods.fml.common.eventhandler.Event
 *  cpw.mods.fml.common.eventhandler.EventBus
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockGrass
 *  net.minecraft.init.Blocks
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
package com.sown.outerrim.dimension.mustafar;

import cpw.mods.fml.common.eventhandler.Event;
import cpw.mods.fml.common.eventhandler.EventBus;
import java.util.Random;

import com.sown.outerrim.OuterRim;
import com.sown.outerrim.dimension.kessel.WorldGenKesselMud;
import com.sown.outerrim.registry.BlockRegister;
import com.sown.util.world.creation.worldgen.WorldGenHelper;

import net.minecraft.block.Block;
import net.minecraft.block.BlockGrass;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeDecorator;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.terraingen.DecorateBiomeEvent;
import net.minecraftforge.event.terraingen.TerrainGen;

public class BiomeDecoratorMustafar
extends BiomeDecorator {
    public int LakesPerChunk;
    public int InfectedLakesPerChunk;
    public int lavaPerChunk;
    public int magmaPerChunk;
    public int geonosisSpikePerChunk;
    private World currentWorld;
    protected Random rand;
    protected int chunkX;
    protected int chunkZ;
    private boolean isDecorating = false;

    @Override
    public void decorateChunk(World world, Random rand, BiomeGenBase biome, int xChunk, int zChunk) {
        int i;
        int y;
        int z;
        int x;
        if (this.isDecorating)
            return;
        this.isDecorating = true;
        this.currentWorld = world;
        this.rand = rand;
        this.chunk_X = xChunk;
        this.chunk_Z = zChunk;
        MinecraftForge.EVENT_BUS.post(new DecorateBiomeEvent.Pre(this.currentWorld, this.rand, this.chunkX, this.chunkZ));
        for (i = 0; TerrainGen.decorate(this.currentWorld, this.rand, this.chunk_X, this.chunk_Z, DecorateBiomeEvent.Decorate.EventType.LAKE) && i < this.lavaPerChunk; ++i) {
            if (this.rand.nextInt(10) != 0) {
                continue;
            }
            WorldGenHelper.generateLake(this.currentWorld, this.rand, this.chunk_X, this.rand.nextInt(256), this.chunk_Z, Blocks.lava, BlockRegister.getRegisteredBlock("mustafarMagma"));
        }
        for (i = 0; i < this.lavaPerChunk; ++i) {
            if (this.rand.nextInt(15) != 0) {
                continue;
            }
            WorldGenHelper.generateLake(this.currentWorld, this.rand, this.chunk_X, 0, this.chunk_Z, BlockRegister.getRegisteredBlock("mustafarMagma"), BlockRegister.getRegisteredBlock("mustafarCobblestone"));
        }
        for (i = 0; TerrainGen.decorate(this.currentWorld, this.rand, this.chunk_X, this.chunk_Z, DecorateBiomeEvent.Decorate.EventType.GRASS) && i < this.magmaPerChunk; ++i) {
            x = this.chunk_X + this.rand.nextInt(8) + 8;
            z = this.chunk_Z + this.rand.nextInt(8) + 8;
            y = this.rand.nextInt(256);
            new WorldGenKesselMud(BlockRegister.getRegisteredBlock("mustafarMagma"), 9).generate(world, rand, x, y, z);
        }
        MinecraftForge.EVENT_BUS.post(new DecorateBiomeEvent.Post(this.currentWorld, this.rand, this.chunkX, this.chunkZ));
        this.isDecorating = false;
    }
}


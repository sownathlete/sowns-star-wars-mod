/*
 * Decompiled with CFR 0.148.
 *
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockGrass
 *  net.minecraft.block.BlockLiquid
 *  net.minecraft.block.BlockMycelium
 *  net.minecraft.block.BlockSand
 *  net.minecraft.init.Blocks
 *  net.minecraft.world.gen.feature.WorldGenLakes
 *  net.minecraft.world.gen.feature.WorldGenLiquids
 *  net.minecraft.world.gen.feature.WorldGenMelon
 *  net.minecraft.world.gen.feature.WorldGenerator
 */
package com.sown.util.world.creation.worldgen;

import java.lang.reflect.Constructor;
import java.util.HashMap;

import com.sown.outerrim.OuterRim;
import com.sown.outerrim.fluids.BlockFluidCustom;
import com.sown.outerrim.registry.BlockRegister;
import com.sown.outerrim.registry.FluidRegister;
import com.sown.util.world.creation.ForcedWorldFeatureOR;
import com.sown.util.world.creation.IORWorldGenerator;
import com.sown.util.world.creation.LakesForcedGenerator;
import com.sown.util.world.creation.MelonForcedGenerator;
import com.sown.util.world.creation.SpringForcedGenerator;

import net.minecraft.block.Block;
import net.minecraft.block.BlockGrass;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.BlockMycelium;
import net.minecraft.block.BlockSand;
import net.minecraft.init.Blocks;
import net.minecraft.world.gen.feature.WorldGenLakes;
import net.minecraft.world.gen.feature.WorldGenLiquids;
import net.minecraft.world.gen.feature.WorldGenMelon;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.fluids.Fluid;

public class WorldGenFieldAssociation {
    public static HashMap<String, WorldFeature> featureMap = new HashMap();
    static Fluid fluid = FluidRegister.getRegisteredFluid("liquid_acid");
    static Block block = fluid.getBlock();
    static BlockFluidCustom customBlock = (BlockFluidCustom) block;

    public static void init() {
        WorldGenFieldAssociation.associateFeatures();
        WorldGenFieldAssociation.associateFeaturesForced();
    }

    private static void associateFeatures() {
        WorldGenFieldAssociation.associateFeature("generateQuicksand", new WorldGenSplotches(BlockRegister.getRegisteredBlock("mud"), 0, 24, new Block[]{Blocks.grass, Blocks.dirt, Blocks.sand}));
        WorldGenFieldAssociation.associateFeature("generateCanyon", new WorldGenSplotches(Blocks.stone, 0, 48, Blocks.stone));
        WorldGenFieldAssociation.associateFeature("generateStoneInGrass", new WorldGenSplotches(Blocks.stone, 0, 32, new Block[]{Blocks.grass}));
        WorldGenFieldAssociation.associateFeature("generateAcidicRock1", new WorldGenSplotches(BlockRegister.getRegisteredBlock("kesselAcidicRock1"), 0, 32, new Block[]{BlockRegister.getRegisteredBlock("kesselDirt1"), BlockRegister.getRegisteredBlock("kesselDirt2"), BlockRegister.getRegisteredBlock("kesselRock1"), BlockRegister.getRegisteredBlock("kesselRock2")}));
        WorldGenFieldAssociation.associateFeature("generateAcidicRock2", new WorldGenSplotches(BlockRegister.getRegisteredBlock("kesselAcidicRock2"), 0, 32, new Block[]{BlockRegister.getRegisteredBlock("kesselDirt1"), BlockRegister.getRegisteredBlock("kesselDirt2"), BlockRegister.getRegisteredBlock("kesselRock1"), BlockRegister.getRegisteredBlock("kesselRock2")}));
        WorldGenFieldAssociation.associateFeature("generateKesselRock1", new WorldGenSplotches(BlockRegister.getRegisteredBlock("kesselRock1"), 0, 32, new Block[]{BlockRegister.getRegisteredBlock("kesselDirt1"), BlockRegister.getRegisteredBlock("kesselDirt2"), BlockRegister.getRegisteredBlock("kesselRock1"), BlockRegister.getRegisteredBlock("kesselRock2")}));
        WorldGenFieldAssociation.associateFeature("generateKesselRock2", new WorldGenSplotches(BlockRegister.getRegisteredBlock("kesselRock2"), 0, 32, new Block[]{BlockRegister.getRegisteredBlock("kesselDirt1"), BlockRegister.getRegisteredBlock("kesselDirt2"), BlockRegister.getRegisteredBlock("kesselRock1"), BlockRegister.getRegisteredBlock("kesselRock2")}));
        WorldGenFieldAssociation.associateFeature("generateStoneInGrass2", new WorldGenSplotches(Blocks.stone, 0, 48, new Block[]{Blocks.grass, Blocks.dirt}));
        WorldGenFieldAssociation.associateFeature("generateGrass", new WorldGenSplotches((Block)Blocks.grass, 0, 48, Blocks.stone));
        WorldGenFieldAssociation.associateFeature("generateKesselMud", new WorldGenSplotches(BlockRegister.getRegisteredBlock("kesselMud"), 0, 18, new Block[]{BlockRegister.getRegisteredBlock("kesselDirt1"), BlockRegister.getRegisteredBlock("kesselDirt2"), BlockRegister.getRegisteredBlock("kesselRock1"), BlockRegister.getRegisteredBlock("kesselRock2")}));
        WorldGenFieldAssociation.associateFeature("generateMud", new WorldGenSplotches((Block)BlockRegister.getRegisteredBlock("mud"), 0, 18, Blocks.grass));
        WorldGenFieldAssociation.associateFeature("generateSand", new WorldGenSplotches((Block)Blocks.sand, 0, 32, Blocks.stone));
        WorldGenFieldAssociation.associateFeature("generateQuagmire", new WorldGenSplotches((Block)Blocks.grass, 0, 48, BlockRegister.getRegisteredBlock("mud")));
        WorldGenFieldAssociation.associateFeature("generateAsh", new WorldGenSplotches(BlockRegister.getRegisteredBlock("ashBlack"), 0, 32, BlockRegister.getRegisteredBlock("dullMud"), Blocks.netherrack));
        WorldGenFieldAssociation.associateFeature("generateMycelium", new WorldGenSplotches(Blocks.mycelium, 0, 32, new Block[]{Blocks.grass}));
        WorldGenFieldAssociation.associateFeature("generateSponge", new WorldGenSplotches(Blocks.sponge, 0, 24, new Block[]{Blocks.dirt, Blocks.sand, Blocks.gravel}));
        WorldGenFieldAssociation.associateFeature("mudPerChunk", new WorldGenWaterside(BlockRegister.getRegisteredBlock("dullMud"), 7, new Block[]{Blocks.dirt, Blocks.grass}));
        WorldGenFieldAssociation.associateFeature("gravelPerChunk", new WorldGenWaterside(Blocks.gravel, 7, new Block[]{Blocks.dirt, Blocks.grass}));
        WorldGenFieldAssociation.associateFeature("riverCanePerChunk", new WorldGenRiverCane());
        WorldGenFieldAssociation.associateFeature("shrubsPerChunk", new WorldGenORFlora(Blocks.leaves, 0));
        WorldGenFieldAssociation.associateFeature("bushesPerChunk", new WorldGenORFlora(Blocks.leaves, 3));
        WorldGenFieldAssociation.associateFeature("grassSplatterPerChunk", new WorldGenGrassSplatter());
        WorldGenFieldAssociation.associateFeature("xericSplatterPerChunk", new WorldGenXericSplatter());
        WorldGenFieldAssociation.associateFeature("logsPerChunk", new WorldGenLog());
        WorldGenFieldAssociation.associateFeature("lavaSpoutsPerChunk", new WorldGenLavaSpout());
        WorldGenFieldAssociation.associateFeature("mustafarLavaSpoutsPerChunk", new WorldGenMustafarLavaSpout());
        WorldGenFieldAssociation.associateFeature("kesselAcidSpoutsPerChunk", new WorldGenKesselAcidSpout());
    }

    private static void associateFeaturesForced() {
        WorldGenFieldAssociation.associateFeatureForced("waterSpringsPerChunk", new WorldGenLiquids(Blocks.flowing_water), SpringForcedGenerator.class);
//        WorldGenFieldAssociation.associateFeatureForced("lavaSpringsPerChunk", new WorldGenLiquids(Blocks.flowing_lava), SpringForcedGenerator.class);
//        WorldGenFieldAssociation.associateFeatureForced("acidSpringsPerChunk", new WorldGenLiquids((BlockFluidCustom) customBlock), SpringForcedGenerator.class);
//        WorldGenFieldAssociation.associateFeatureForced("mudSpringsPerChunk", new WorldGenLiquids(BlockRegister.getRegisteredBlock("mud")), SpringForcedGenerator.class);
//        WorldGenFieldAssociation.associateFeatureForced("kesselMudSpringsPerChunk", new WorldGenLiquids(BlockRegister.getRegisteredBlock("kesselMud")), SpringForcedGenerator.class);
        WorldGenFieldAssociation.associateFeatureForced("waterLakesPerChunk", new WorldGenLakes(Blocks.water), LakesForcedGenerator.class);
        WorldGenFieldAssociation.associateFeatureForced("lavaLakesPerChunk", new WorldGenLakes(Blocks.lava), LakesForcedGenerator.class);
        WorldGenFieldAssociation.associateFeatureForced("acidLakesPerChunk", new WorldGenLakes(customBlock), LakesForcedGenerator.class);
        WorldGenFieldAssociation.associateFeatureForced("mudLakesPerChunk", new WorldGenLakes(BlockRegister.getRegisteredBlock("mud")), LakesForcedGenerator.class);
        WorldGenFieldAssociation.associateFeatureForced("kesselMudLakesPerChunk", new WorldGenLakes(BlockRegister.getRegisteredBlock("kesselMud")), LakesForcedGenerator.class);
    }

    public static void associateFeature(String name, WorldFeature feature) {
        featureMap.put(name, feature);
    }

    public static void associateFeature(String name, WorldGenerator generator) {
        featureMap.put(name, new WorldFeature(generator));
    }

    public static void associateFeatureForced(String name, WorldGenerator generator, Class<? extends ForcedWorldFeatureOR> forcedFeature) {
        WorldGenFieldAssociation.associateFeature(name, new WorldFeature(generator, forcedFeature));
    }

    public static WorldFeature getAssociatedFeature(String name) {
        return featureMap.get(name);
    }

    public static class WorldFeature {
        private WorldGenerator worldGenerator;
        private Class<? extends ForcedWorldFeatureOR> forcedFeature;

        protected WorldFeature(WorldGenerator worldGenerator, Class<? extends ForcedWorldFeatureOR> forcedFeature) {
            this.worldGenerator = worldGenerator;
            this.forcedFeature = forcedFeature;
        }

        protected WorldFeature(WorldGenerator worldGenerator) {
            this(worldGenerator, null);
        }

        public IORWorldGenerator getBOPWorldGenerator() {
            if (this.worldGenerator instanceof IORWorldGenerator)
                return (IORWorldGenerator)this.worldGenerator;
            if (this.forcedFeature != null) {
                try {
                    return this.forcedFeature.getConstructor(WorldGenerator.class).newInstance(new Object[]{this.worldGenerator});
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return null;
        }
    }

}


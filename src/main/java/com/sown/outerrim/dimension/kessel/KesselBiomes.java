/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockSand
 *  net.minecraft.block.material.Material
 *  net.minecraft.init.Blocks
 *  net.minecraft.world.World
 *  net.minecraft.world.biome.BiomeDecorator
 *  net.minecraft.world.biome.BiomeGenBase
 *  net.minecraft.world.biome.BiomeGenBase$Height
 */
package com.sown.outerrim.dimension.kessel;

import com.sown.outerrim.OuterRim;
import com.sown.outerrim.OuterRimResources;
import com.sown.outerrim.dimension.kessel.BiomeDecoratorKessel;
import com.sown.outerrim.dimension.kessel.BiomeGenKesselDirt1;
import com.sown.outerrim.dimension.kessel.BiomeGenKesselDirt2;
import com.sown.outerrim.dimension.kessel.BiomeGenKesselMountains;
import com.sown.outerrim.dimension.kessel.BiomeGenKesselMud;
import com.sown.outerrim.dimension.kessel.BiomeGenKesselRock1;
import com.sown.outerrim.dimension.kessel.BiomeGenKesselRock2;
import com.sown.outerrim.registry.BlockRegister;
import com.sown.util.block.ORBlock;
import com.sown.util.world.creation.ORBiomeDecorator;
import com.sown.util.world.creation.OROverworldBiome;
import com.sown.util.world.creation.OROverworldBiomeDecorator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSand;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeDecorator;
import net.minecraft.world.biome.BiomeGenBase;

public class KesselBiomes
extends OROverworldBiome {
    public static final List<BiomeGenBase> biomes = new ArrayList<>();
    protected static final BiomeGenBase.Height height_KesselMountains = new BiomeGenBase.Height(2.0f, 0.025f);
    public static final BiomeGenBase kesselRock1 = new BiomeGenKesselRock1().setColor(112).setBiomeName("Kessel Stone Cliffs").setHeight(height_LowPlains);
    public static final BiomeGenBase kesselRock2 = new BiomeGenKesselRock2().setColor(112).setBiomeName("Kessel Rocky Hills").setHeight(height_MidHills);
    public static final BiomeGenBase kesselDirt1 = new BiomeGenKesselDirt1().setColor(112).setBiomeName("Kessel Dusty Flats").setHeight(height_LowPlains);
    public static final BiomeGenBase kesselDirt2 = new BiomeGenKesselDirt2().setColor(112).setBiomeName("Kessel Arid Hills").setHeight(height_LowPlains);
    public static final BiomeGenBase kesselMud = new BiomeGenKesselMud().setColor(112).setBiomeName("Kessel Mudlands").setHeight(height_PartiallySubmerged);
    public static final BiomeGenBase kesselMountains = new BiomeGenKesselMountains(OuterRimResources.ConfigOptions.biomeKesselMountainsId).setColor(112).setBiomeName("Kessel Mountains").setHeight(height_KesselMountains);
    protected Block stoneBlock;
    protected byte topMeta;
    protected byte fillerMeta;
    protected byte stoneMeta;
    public BiomeTerrain biomeTerrain = new BiomeTerrain(this);

    static {
        biomes.add(kesselRock1);
        biomes.add(kesselRock2);
        biomes.add(kesselDirt1);
        biomes.add(kesselDirt2);
        biomes.add(kesselMud);
        biomes.add(kesselMountains);
        // Add other biomes as needed
    }

    public KesselBiomes(int id) {
        super(id);
        this.spawnableMonsterList.clear();
        this.spawnableWaterCreatureList.clear();
        this.spawnableCreatureList.clear();
        this.spawnableCaveCreatureList.clear();
        this.rainfall = 0.0f;
        this.setColor(-16744448);
        this.enableRain = true;
        this.enableSnow = true;
        this.topBlock = BlockRegister.getRegisteredBlock("kesselRock2");
        this.topMeta = 0;
        this.fillerBlock = BlockRegister.getRegisteredBlock("kesselDirt2");
        this.fillerMeta = 0;
        this.stoneBlock = BlockRegister.getRegisteredBlock("kesselRock1");
        this.stoneMeta = 1;
        ((OROverworldBiomeDecorator)this.theBiomeDecorator).generateLakes = true;
    }

    public BiomeDecorator createBiomeDecorator() {
        return new BiomeDecoratorKessel();
    }

    public static class BiomeTerrain {
        private KesselBiomes theBiome;
        private double xzScale = -1.0;
        private double heightStretchFactor = -1.0;

        public BiomeTerrain(KesselBiomes biome) {
            this.theBiome = biome;
        }

        public void setXZScale(double d) {
            this.xzScale = d;
        }

        public void resetXZScale() {
            this.setXZScale(-1.0);
        }

        public boolean hasXZScale() {
            return this.xzScale != -1.0;
        }

        public double getXZScale() {
            return this.xzScale;
        }

        public void setHeightStretchFactor(double d) {
            this.heightStretchFactor = d;
        }

        public void resetHeightStretchFactor() {
            this.setHeightStretchFactor(-1.0);
        }

        public boolean hasHeightStretchFactor() {
            return this.heightStretchFactor != -1.0;
        }

        public double getHeightStretchFactor() {
            return this.heightStretchFactor;
        }
    }
    
    @Override
    public boolean canSpawnLightningBolt() {
        return this.func_150559_j() ? false : this.enableRain;
    }

    @Override
    public void genTerrainBlocks(World world, Random rand, Block[] block, byte[] meta, int x, int z, double stoneNoise) {
        this.generateBiomeTerrain(world, rand, block, meta, x, z, stoneNoise);
    }

    @Override
    public void generateBiomeTerrain(World world, Random rand, Block[] block, byte[] meta, int x, int z, double stoneNoise) {
        Block topBlock = this.topBlock;
        byte topMeta = this.topMeta;
        Block fillerBlock = this.fillerBlock;
        byte fillerMeta = this.fillerMeta;
        int currentFillerDepth = -1;
        int maxFillerDepth = (int)(stoneNoise / 3.0 + 3.0 + rand.nextDouble() * 0.25);
        int maskX = x & 0xF;
        int maskZ = z & 0xF;
        int worldHeight = block.length / 256;
        int seaLevel = 32;
        for (int y = 255; y >= 0; --y) {
            int index = (maskZ * 16 + maskX) * worldHeight + y;
            if (y <= 0 + rand.nextInt(5)) {
                block[index] = Blocks.bedrock;
                continue;
            }
            Block currentBlock = block[index];
            if (currentBlock == null || currentBlock.getMaterial() == Material.air || currentBlock != Blocks.stone) continue;
            if (this.stoneBlock != null) {
                block[index] = this.stoneBlock;
                meta[index] = this.stoneMeta;
            }
            if (currentFillerDepth == -1) {
                if (maxFillerDepth <= 0) {
                    topBlock = null;
                    topMeta = 0;
                    fillerBlock = Blocks.wool;
                    fillerMeta = 1;
                } else if (y >= seaLevel - 5 && y <= seaLevel) {
                    topBlock = this.topBlock;
                    topMeta = this.topMeta;
                    fillerBlock = this.fillerBlock;
                    fillerMeta = 0;
                }
                if (y < seaLevel - 1 && (topBlock == null || topBlock.getMaterial() == Material.air)) {
                    if (this.getFloatTemperature(x, y, z) < 0.15f) {
                        topBlock = Blocks.ice;
                        topMeta = 0;
                    } else {
                        topBlock = Blocks.water;
                        topMeta = 0;
                    }
                }
                currentFillerDepth = maxFillerDepth;
                if (y >= seaLevel - 2) {
                    block[index] = topBlock;
                    meta[index] = topMeta;
                    continue;
                }
                if (y < seaLevel - 8 - maxFillerDepth) {
                    topBlock = null;
                    fillerBlock = Blocks.stone;
                    fillerMeta = 1;
                    block[index] = Blocks.gravel;
                    continue;
                }
                block[index] = fillerBlock;
                meta[index] = fillerMeta;
                continue;
            }
            if (currentFillerDepth <= 0) continue;
            block[index] = fillerBlock;
            meta[index] = fillerMeta;
            if (--currentFillerDepth != 0 || fillerBlock != Blocks.sand) continue;
            currentFillerDepth = rand.nextInt(4) + Math.max(0, y - (seaLevel - 1));
            fillerBlock = Blocks.stone;
            fillerMeta = 0;
        }
    }

    public BiomeGenBase setColor(int var1) {
        return super.setColor(var1);
    }

    public float getSpawningChance() {
        return 0.1f;
    }
}


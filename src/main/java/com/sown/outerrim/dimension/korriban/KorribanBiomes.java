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
package com.sown.outerrim.dimension.korriban;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.sown.outerrim.OuterRimResources;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSand;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeDecorator;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.BiomeGenOcean;
import net.minecraftforge.common.BiomeManager;

public class KorribanBiomes
extends BiomeGenBase {
    public static final List<BiomeGenBase> biomes = new ArrayList<>();
    public static final BiomeGenBase korribanDesert = new BiomeGenKorribanDesert(OuterRimResources.ConfigOptions.biomeKorribanDesertId).setColor(112).setBiomeName("Korriban Desert");
    public static final BiomeGenBase korribanMountains = new BiomeGenKorribanMountains(OuterRimResources.ConfigOptions.biomeKorribanMountainsId).setColor(112).setBiomeName("Korriban Mountains");
    protected Block stoneBlock;
    protected byte topMeta;
    protected byte fillerMeta;
    protected byte stoneMeta;

    static {
        biomes.add(korribanDesert);
        biomes.add(korribanMountains);
        // Add other biomes as needed
    }

    public KorribanBiomes(int id) {
        super(id);
        this.spawnableMonsterList.clear();
        this.spawnableWaterCreatureList.clear();
        this.spawnableCreatureList.clear();
        this.spawnableCaveCreatureList.clear();
        this.rainfall = 0.0f;
        this.setColor(-16744448);
        this.enableRain = false;
        this.enableSnow = false;
        this.topBlock = Blocks.sand;
        this.topMeta = 1;
        this.fillerBlock = Blocks.sand;
        this.fillerMeta = 1;
        this.stoneBlock = Blocks.stone;
        this.stoneMeta = 0;
        this.theBiomeDecorator.generateLakes = false;
    }

    @Override
    public BiomeDecorator createBiomeDecorator() {
        return new BiomeDecoratorKorriban();
    }

    protected BiomeDecoratorKorriban getBiomeDecorator() {
        return (BiomeDecoratorKorriban)this.theBiomeDecorator;
    }

    @Override
    public boolean canSpawnLightningBolt() {
        return this.func_150559_j() ? false : this.enableRain;
    }

    @Override
    public void genTerrainBlocks(World world, Random rand, Block[] block, byte[] meta, int x, int z, double stoneNoise) {
        this.generateBiomeTerrain(world, rand, block, meta, x, z, stoneNoise);
    }

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
            if (currentBlock == null || currentBlock.getMaterial() == Material.air || currentBlock != Blocks.stone) {
                continue;
            }
            if (this.stoneBlock != null) {
                block[index] = this.stoneBlock;
                meta[index] = this.stoneMeta;
            }
            if (currentFillerDepth == -1) {
                if (maxFillerDepth <= 0) {
                    topBlock = null;
                    topMeta = 0;
                    fillerBlock = Blocks.stone;
                    fillerMeta = 0;
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
                    fillerMeta = 0;
                    block[index] = Blocks.gravel;
                    continue;
                }
                block[index] = fillerBlock;
                meta[index] = fillerMeta;
                continue;
            }
            if (currentFillerDepth <= 0) {
                continue;
            }
            block[index] = fillerBlock;
            meta[index] = fillerMeta;
            if (--currentFillerDepth != 0 || fillerBlock != Blocks.sand) {
                continue;
            }
            currentFillerDepth = rand.nextInt(4) + Math.max(0, y - (seaLevel - 1));
            fillerBlock = Blocks.sandstone;
            fillerMeta = 0;
        }
    }

    @Override
    public BiomeGenBase setColor(int var1) {
        return super.setColor(var1);
    }

    @Override
    public float getSpawningChance() {
        return 0.1f;
    }
}


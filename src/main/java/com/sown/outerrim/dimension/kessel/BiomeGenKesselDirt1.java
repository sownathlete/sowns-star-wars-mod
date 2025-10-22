/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockSand
 *  net.minecraft.block.material.Material
 *  net.minecraft.init.Blocks
 *  net.minecraft.world.World
 *  net.minecraft.world.biome.BiomeGenBase
 */
package com.sown.outerrim.dimension.kessel;

import com.sown.outerrim.OuterRim;
import com.sown.outerrim.OuterRimResources;
import com.sown.outerrim.dimension.kessel.KesselBiomes;
import com.sown.outerrim.registry.BlockRegister;
import com.sown.util.block.ORBlock;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSand;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;

public class BiomeGenKesselDirt1
extends KesselBiomes {
    public BiomeGenKesselDirt1() {
        super(OuterRimResources.ConfigOptions.biomeKesselDirt1Id);
        this.enableRain = true;
        this.enableSnow = true;
        this.rootHeight = 0.1f;
        this.heightVariation = 0.2f;
        this.setTemperatureRainfall(0.8f, 0.9f);
        this.topBlock = BlockRegister.getRegisteredBlock("kesselDirt1");
        this.topMeta = 0;
        this.fillerBlock = BlockRegister.getRegisteredBlock("kesselDirt1");
        this.fillerMeta = 0;
        this.stoneBlock = BlockRegister.getRegisteredBlock("kesselRock1");
        this.stoneMeta = 0;
    }

    @Override
    public void decorate(World par1World, Random par2Random, int chunkX, int chunkZ) {
        if (par2Random.nextInt(30) == 0) {
            int k = chunkX + par2Random.nextInt(16) + 8;
            int n = chunkZ + par2Random.nextInt(16) + 8;
        }
    }

    public int getBiomeGrassColor(int par1, int par2, int par3) {
        return 12165249;
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
                    fillerBlock = Blocks.glass;
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
            fillerBlock = Blocks.sandstone;
            fillerMeta = 0;
        }
    }

    public int getBiomeFoliageColor(int x, int y, int z) {
        return 12165249;
    }

    @SideOnly(value=Side.CLIENT)
    public int getSkyColorByTemp(float temp) {
        return 11969885;
    }
}


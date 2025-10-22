package com.sown.outerrim.dimension.dathomir;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.sown.outerrim.OuterRimResources;
import com.sown.outerrim.registry.BlockRegister;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeDecorator;
import net.minecraft.world.biome.BiomeGenBase;

public class DathomirBiomes extends BiomeGenBase {
    public static final List<BiomeGenBase> biomes = new ArrayList<>();
    protected static final BiomeGenBase.Height height_swamp = new BiomeGenBase.Height(-0.1F, 0.05F);
    protected static final BiomeGenBase.Height height_forest = new BiomeGenBase.Height(0.2F, 0.1F);
    protected static final BiomeGenBase.Height height_cliffs = new BiomeGenBase.Height(1.0F, 0.4F);

    public static final BiomeGenBase dathomirSwamp = new BiomeGenDathomirSwamp(OuterRimResources.ConfigOptions.biomeDathomirSwampId)
            .setColor(0x2B1E1C)
            .setBiomeName("Dathomir Swamp")
            .setHeight(height_swamp);
    public static final BiomeGenBase dathomirForest = new BiomeGenDathomirForest(OuterRimResources.ConfigOptions.biomeDathomirForestId)
            .setColor(0x3D1C1C)
            .setBiomeName("Dathomir Forest")
            .setHeight(height_forest);
    public static final BiomeGenBase dathomirCliffs = new BiomeGenDathomirCliffs(OuterRimResources.ConfigOptions.biomeDathomirCliffsId)
            .setColor(0x443333)
            .setBiomeName("Dathomir Cliffs")
            .setHeight(height_cliffs);

    protected Block stoneBlock;
    protected byte topMeta;
    protected byte fillerMeta;
    protected byte stoneMeta;

    static {
        biomes.add(dathomirForest);
        biomes.add(dathomirSwamp);
        biomes.add(dathomirCliffs);
    }

    public DathomirBiomes(int id) {
        super(id);
        this.spawnableMonsterList.clear();
        this.spawnableWaterCreatureList.clear();
        this.spawnableCreatureList.clear();
        this.spawnableCaveCreatureList.clear();
        this.rainfall = 1.0f;
        this.enableRain = true;
        this.enableSnow = false;

        this.topBlock = BlockRegister.getRegisteredBlock("dathomirDirt");
        this.topMeta = 0;
        this.fillerBlock = BlockRegister.getRegisteredBlock("dathomirRock");
        this.fillerMeta = 0;
        this.stoneBlock = BlockRegister.getRegisteredBlock("dathomirSlate");
        this.stoneMeta = 0;

        this.theBiomeDecorator.generateLakes = true;
    }

    @Override
    public BiomeDecorator createBiomeDecorator() {
        return new BiomeDecoratorDathomir(); // You should create this to replace BiomeDecoratorFelucia
    }

    protected BiomeDecoratorDathomir getBiomeDecorator() {
        return (BiomeDecoratorDathomir)this.theBiomeDecorator;
    }

    @Override
    public boolean canSpawnLightningBolt() {
        return this.enableRain;
    }

    @Override
    public void genTerrainBlocks(World world, Random rand, Block[] blocks, byte[] metas, int x, int z, double stoneNoise) {
        this.generateBiomeTerrain(world, rand, blocks, metas, x, z, stoneNoise);
    }

    public void generateBiomeTerrain(World world, Random rand, Block[] blocks, byte[] metas, int x, int z, double stoneNoise) {
        Block top = this.topBlock;
        byte topMeta = this.topMeta;
        Block filler = this.fillerBlock;
        byte fillerMeta = this.fillerMeta;
        int depth = -1;
        int maxDepth = (int)(stoneNoise / 3.0 + 3.0 + rand.nextDouble() * 0.25);
        int maskX = x & 0xF;
        int maskZ = z & 0xF;
        int height = blocks.length / 256;
        int seaLevel = 32;

        for (int y = 255; y >= 0; --y) {
            int idx = (maskZ * 16 + maskX) * height + y;

            if (y <= 0 + rand.nextInt(5)) {
                blocks[idx] = Blocks.bedrock;
                continue;
            }

            Block block = blocks[idx];
            if (block == null || block.getMaterial() == Material.air) {
                depth = -1;
                continue;
            }

            if (block != Blocks.stone && block != this.stoneBlock) continue;

            blocks[idx] = this.stoneBlock;
            metas[idx] = this.stoneMeta;

            if (depth == -1) {
                if (maxDepth <= 0) {
                    top = null;
                    filler = this.stoneBlock;
                    fillerMeta = this.stoneMeta;
                } else if (y >= seaLevel - 5 && y <= seaLevel) {
                    top = this.topBlock;
                    filler = this.fillerBlock;
                }

                depth = maxDepth;

                if (y >= seaLevel - 2) {
                    blocks[idx] = top;
                    metas[idx] = topMeta;
                } else if (y < seaLevel - 8 - maxDepth) {
                    blocks[idx] = Blocks.gravel;
                } else {
                    blocks[idx] = filler;
                    metas[idx] = fillerMeta;
                }

            } else if (depth > 0) {
                --depth;
                blocks[idx] = filler;
                metas[idx] = fillerMeta;
            }
        }
    }

    @Override
    public BiomeGenBase setColor(int color) {
        return super.setColor(color);
    }

    @Override
    public float getSpawningChance() {
        return 0.2f;
    }
}

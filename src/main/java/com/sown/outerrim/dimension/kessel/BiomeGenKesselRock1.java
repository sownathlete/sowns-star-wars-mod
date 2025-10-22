package com.sown.outerrim.dimension.kessel;

import com.sown.outerrim.OuterRim;
import com.sown.outerrim.OuterRimResources;
import com.sown.outerrim.registry.BlockRegister;
import com.sown.util.block.ORBlock;
import com.sown.util.world.creation.ORBiomeDecorator;
import com.sown.util.world.creation.OROverworldBiome;
import com.sown.util.world.creation.OROverworldBiomeDecorator;
import com.sown.util.world.creation.BiomeFeatures;
import com.sown.util.world.creation.OverworldBiomeFeatures;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSand;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;

public class BiomeGenKesselRock1 extends OROverworldBiome {
    public BiomeGenKesselRock1() {
        super(OuterRimResources.ConfigOptions.biomeKesselRock1Id);
        this.enableRain = true;
        this.enableSnow = true;
        this.rootHeight = 0.1f;
        this.heightVariation = 0.2f;
        this.setTemperatureRainfall(0.8f, 0.9f);
        this.topBlock = BlockRegister.getRegisteredBlock("kesselRock2");
        this.topMeta = 0;
        this.fillerBlock = BlockRegister.getRegisteredBlock("kesselDirt2");
        this.fillerMeta = 0;
        this.stoneBlock = BlockRegister.getRegisteredBlock("kesselRock1");
        this.stoneMeta = 0;
        this.spawnableMonsterList.clear();
        this.spawnableWaterCreatureList.clear();
        this.spawnableCreatureList.clear();
        this.spawnableCaveCreatureList.clear();
        ((OverworldBiomeFeatures)((OROverworldBiomeDecorator)this.theBiomeDecorator).orFeatures).generateAcidicRock2 = true;
        ((OverworldBiomeFeatures)((OROverworldBiomeDecorator)this.theBiomeDecorator).orFeatures).generateKesselRock1 = true;
        ((OverworldBiomeFeatures)((OROverworldBiomeDecorator)this.theBiomeDecorator).orFeatures).generateAcidicRock1 = true;
        ((OverworldBiomeFeatures)((OROverworldBiomeDecorator)this.theBiomeDecorator).orFeatures).kesselMudLakesPerChunk = 5;
        ((OverworldBiomeFeatures)((OROverworldBiomeDecorator)this.theBiomeDecorator).orFeatures).acidLakesPerChunk = 15;
        ((OverworldBiomeFeatures)((OROverworldBiomeDecorator)this.theBiomeDecorator).orFeatures).kesselAcidSpoutsPerChunk = 15;
        ((OverworldBiomeFeatures)((OROverworldBiomeDecorator)this.theBiomeDecorator).orFeatures).acidSpringsPerChunk = 15;
        ((OverworldBiomeFeatures)((OROverworldBiomeDecorator)this.theBiomeDecorator).orFeatures).kesselMudSpringsPerChunk = 1;
    }

    @Override
    public void decorate(World world, Random rand, int chunkX, int chunkZ) {
        super.decorate(world, rand, chunkX, chunkZ);

        int baseX = chunkX + rand.nextInt(16) + 8;
        int baseZ = chunkZ + rand.nextInt(16) + 8;
        int baseY = world.getHeightValue(baseX, baseZ);

        // Drill: 1 in 150 chance (5x rarer than before)
        if (rand.nextInt(150) == 0 && canPlaceOnSolidGround(world, baseX, baseY, baseZ, 5, 5)) {
            new WorldGenKesselDrill().generate(world, rand, baseX, baseY, baseZ);
        }

        // Watchtower: 1 in 200 chance (5x rarer than before)
        if (rand.nextInt(200) == 0 && canPlaceOnSolidGround(world, baseX, baseY, baseZ, 7, 7)) {
            new WorldGenKesselWatchtower().generate(world, rand, baseX, baseY, baseZ);
        }

        // Extractor: 1 in 125 chance (5x rarer than before)
        if (rand.nextInt(125) == 0 && canPlaceOnSolidGround(world, baseX, baseY, baseZ, 5, 5)) {
            new WorldGenKesselExtractor().generate(world, rand, baseX, baseY, baseZ);
        }

        // Quarry remains unchanged (extremely rare)
        int regionX = chunkX >> 4;
        int regionZ = chunkZ >> 4;
        long regionSeed = ((long) regionX * 341873128L + (long) regionZ * 1328979875L) ^ world.getSeed();
        Random seededRand = new Random(regionSeed ^ 0x5F3759DFL);

        if (seededRand.nextInt(10240) == 0) { // 1 in 10,240 chance per region
            int x = chunkX + rand.nextInt(16);
            int z = chunkZ + rand.nextInt(16);
            int y = world.getTopSolidOrLiquidBlock(x, z);
            new WorldGenQuarry().generateQuarry(world, rand, baseX, baseZ);
        }
    }

    private boolean canPlaceOnSolidGround(World world, int x, int y, int z, int width, int length) {
        int halfW = width / 2;
        int halfL = length / 2;
        for (int dx = -halfW; dx <= halfW; dx++) {
            for (int dz = -halfL; dz <= halfL; dz++) {
                Block blockBelow = world.getBlock(x + dx, y - 1, z + dz);
                if (blockBelow == null || !blockBelow.getMaterial().isSolid()) {
                    return false;
                }
            }
        }
        return true;
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

    @SideOnly(value = Side.CLIENT)
    public int getSkyColorByTemp(float temp) {
        return 11969885;
    }
}

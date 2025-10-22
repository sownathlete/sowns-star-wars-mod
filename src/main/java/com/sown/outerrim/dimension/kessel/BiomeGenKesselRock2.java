/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.world.World
 *  net.minecraft.world.biome.BiomeGenBase
 */
package com.sown.outerrim.dimension.kessel;

import com.sown.outerrim.OuterRimResources;
import com.sown.outerrim.registry.BlockRegister;
import com.sown.util.world.creation.OROverworldBiome;
import com.sown.util.world.creation.OROverworldBiomeDecorator;
import com.sown.util.world.creation.OverworldBiomeFeatures;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.world.World;

public class BiomeGenKesselRock2
extends OROverworldBiome {
    public BiomeGenKesselRock2() {
        super(OuterRimResources.ConfigOptions.biomeKesselRock2Id);
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
        ((OverworldBiomeFeatures)((OROverworldBiomeDecorator)this.theBiomeDecorator).orFeatures).kesselAcidSpoutsPerChunk = 15;
        ((OverworldBiomeFeatures)((OROverworldBiomeDecorator)this.theBiomeDecorator).orFeatures).acidLakesPerChunk = 15;
        ((OverworldBiomeFeatures)((OROverworldBiomeDecorator)this.theBiomeDecorator).orFeatures).acidSpringsPerChunk = 15;
        ((OverworldBiomeFeatures)((OROverworldBiomeDecorator)this.theBiomeDecorator).orFeatures).kesselMudSpringsPerChunk = 1;
    }

    @Override
    public void decorate(World world, Random rand, int chunkX, int chunkZ) {
        super.decorate(world, rand, chunkX, chunkZ);

        int baseX = chunkX + rand.nextInt(16) + 8;
        int baseZ = chunkZ + rand.nextInt(16) + 8;
        int baseY = world.getHeightValue(baseX, baseZ);

        // Drill: 1 in 30 chance
        if (rand.nextInt(30) == 0 && canPlaceOnSolidGround(world, baseX, baseY, baseZ, 5, 5)) {
            new WorldGenKesselDrill().generate(world, rand, baseX, baseY, baseZ);
        }

        // Watchtower: 1 in 40 chance
        if (rand.nextInt(40) == 0 && canPlaceOnSolidGround(world, baseX, baseY, baseZ, 7, 7)) {
            new WorldGenKesselWatchtower().generate(world, rand, baseX, baseY, baseZ);
        }

        // Extractor: 1 in 25 chance
        if (rand.nextInt(25) == 0 && canPlaceOnSolidGround(world, baseX, baseY, baseZ, 5, 5)) {
            new WorldGenKesselExtractor().generate(world, rand, baseX, baseY, baseZ);
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

    public int getBiomeFoliageColor(int x, int y, int z) {
        return 12165249;
    }

    @SideOnly(value=Side.CLIENT)
    public int getSkyColorByTemp(float temp) {
        return 11969885;
    }
}


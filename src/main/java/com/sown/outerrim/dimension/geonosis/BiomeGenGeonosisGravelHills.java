/*
 * Decompiled with CFR 0.148.
 *
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.entity.monster.EntityCreeper
 *  net.minecraft.entity.monster.EntityEnderman
 *  net.minecraft.entity.monster.EntitySkeleton
 *  net.minecraft.entity.monster.EntitySpider
 *  net.minecraft.entity.monster.EntityWitch
 *  net.minecraft.entity.monster.EntityZombie
 *  net.minecraft.entity.passive.EntityChicken
 *  net.minecraft.entity.passive.EntityCow
 *  net.minecraft.entity.passive.EntityPig
 *  net.minecraft.entity.passive.EntitySheep
 *  net.minecraft.world.biome.BiomeGenBase
 *  net.minecraft.world.biome.BiomeGenBase$SpawnListEntry
 */
package com.sown.outerrim.dimension.geonosis;

import java.util.List;
import java.util.Random;

import com.sown.outerrim.OuterRim;
import com.sown.outerrim.OuterRimResources;
import com.sown.outerrim.registry.BlockRegister;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.monster.EntityWitch;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;

public class BiomeGenGeonosisGravelHills
extends GeonosisBiomes {
    public BiomeGenGeonosisGravelHills() {
        super(OuterRimResources.ConfigOptions.biomeGeonosisGravelHillsId);
        this.enableRain = true;
        this.enableSnow = true;
        this.setTemperatureRainfall(0.8f, 0.9f);
        this.topBlock = BlockRegister.getRegisteredBlock("geonosisGravel");
        this.topMeta = 0;
        this.fillerBlock = BlockRegister.getRegisteredBlock("geonosisGravel");
        this.fillerMeta = 0;
        this.stoneBlock = BlockRegister.getRegisteredBlock("geonosisRock");
        this.stoneMeta = 1;
    }

    @Override
    public void decorate(World world, Random rand, int chunkX, int chunkZ) {
        super.decorate(world, rand, chunkX, chunkZ);

        // Geonosis Pillars1
        int pillarChance1 = 9; // adjust this to change the spawn frequency of Geonosis pillars.
        if (rand.nextInt(1000) < pillarChance1) {
            int k = chunkX + rand.nextInt(16) + 8;
            int l = chunkZ + rand.nextInt(16) + 8;
            WorldGenGeonosisPillars1 pillarGenerator = new WorldGenGeonosisPillars1(BlockRegister.getRegisteredBlock("geonosisRock"), BlockRegister.getRegisteredBlock("geonosisCobblestone"));
            pillarGenerator.generate(world, rand, k, world.getHeightValue(k, l), l);
        }
        int pillarChance = 2; // adjust this to change the spawn frequency of Geonosis pillars.
        if (rand.nextInt(1000) < pillarChance) {
            int k = chunkX + rand.nextInt(16) + 8;
            int l = chunkZ + rand.nextInt(16) + 8;
            WorldGenGeonosisPillars pillarGenerator = new WorldGenGeonosisPillars(BlockRegister.getRegisteredBlock("geonosisRock"), BlockRegister.getRegisteredBlock("geonosisCobblestone"), BlockRegister.getRegisteredBlock("geonosisRock_wall"));
            pillarGenerator.generate(world, rand, k, world.getHeightValue(k, l), l);
        }
    }

    @Override
    public int getBiomeGrassColor(int p_150558_1_, int p_150558_2_, int p_150558_3_) {
        return 12165249;
    }

    @Override
    public int getBiomeFoliageColor(int x, int y, int z) {
        return 12165249;
    }
    @Override
    @SideOnly(Side.CLIENT)
    public int getSkyColorByTemp(float temp)
    {
        return 14131796;
    }
}


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

import com.sown.outerrim.OuterRim;
import com.sown.outerrim.OuterRimResources;
import com.sown.outerrim.dimension.kessel.KesselBiomes;
import com.sown.outerrim.registry.BlockRegister;
import com.sown.util.block.ORBlock;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;

public class BiomeGenKesselMud
extends KesselBiomes {
    public BiomeGenKesselMud() {
        super(OuterRimResources.ConfigOptions.biomeKesselMudId);
        this.enableRain = true;
        this.enableSnow = true;
        this.rootHeight = 0.1f;
        this.heightVariation = 0.2f;
        this.setTemperatureRainfall(0.8f, 0.9f);
        this.topBlock = BlockRegister.getRegisteredBlock("kesselMud");
        this.topMeta = 0;
        this.fillerBlock = BlockRegister.getRegisteredBlock("kesselMud");
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

    public int getBiomeFoliageColor(int x, int y, int z) {
        return 12165249;
    }

    @SideOnly(value=Side.CLIENT)
    public int getSkyColorByTemp(float temp) {
        return 11969885;
    }
}


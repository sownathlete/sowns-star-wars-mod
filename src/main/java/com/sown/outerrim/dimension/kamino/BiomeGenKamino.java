/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.init.Blocks
 *  net.minecraft.world.World
 *  net.minecraft.world.biome.BiomeGenBase
 *  net.minecraft.world.biome.BiomeGenBase$Height
 */
package com.sown.outerrim.dimension.kamino;

import java.util.List;
import java.util.Random;

import com.sown.util.world.creation.OROverworldBiomeDecorator;
import com.sown.util.world.creation.OverworldBiomeFeatures;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.entity.passive.EntitySquid;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.BiomeGenBase.SpawnListEntry;

public class BiomeGenKamino
extends BiomeGenBase {
    private static final BiomeGenBase.Height biomeHeight = new BiomeGenBase.Height(-2.5f, 0.1f);

    public BiomeGenKamino(int biomeID) {
        super(biomeID);
        this.waterColorMultiplier = 333893;
        this.setHeight(biomeHeight);
        this.setColor(27468);
        this.spawnableMonsterList.clear();

        this.setTemperatureRainfall(0.5f, 0.9f);
    }

    @Override
    public void decorate(World world, Random random, int chunkX, int chunkZ) {
        super.decorate(world, random, chunkX, chunkZ);
        int var5 = 12 + random.nextInt(6);
        for (int var6 = 0; var6 < var5; ++var6) {
            int y;
            int z;
            int x = chunkX + random.nextInt(16);
            Block block = world.getBlock(x, y = random.nextInt(28) + 4, z = chunkZ + random.nextInt(16));
        }
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public int getSkyColorByTemp(float par1) {
        return 1645867; // Custom sky color for Kamino
    }

}


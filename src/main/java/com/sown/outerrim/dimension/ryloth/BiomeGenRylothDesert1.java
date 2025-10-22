package com.sown.outerrim.dimension.ryloth;

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

public class BiomeGenRylothDesert1 extends RylothBiomes {
    public BiomeGenRylothDesert1() {
        super(OuterRimResources.ConfigOptions.biomeRylothId);
        this.enableRain = true;
        this.enableSnow = true;
        this.rootHeight = -0.1F;
        this.heightVariation = 0.2F;
        this.setTemperatureRainfall(0.8f, 0.9f);
        this.topBlock = BlockRegister.getRegisteredBlock("rylothDirt");
        this.topMeta = 0;
        this.fillerBlock = BlockRegister.getRegisteredBlock("rylothDirt");
        this.fillerMeta = 0;
        this.stoneBlock = BlockRegister.getRegisteredBlock("rylothRock");
        this.stoneMeta = 1;
    }

    @Override
    public void decorate(World par1World, Random par2Random, int chunkX, int chunkZ) {
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
    public int getSkyColorByTemp(float temp) {
        return 6071516;
    }
}

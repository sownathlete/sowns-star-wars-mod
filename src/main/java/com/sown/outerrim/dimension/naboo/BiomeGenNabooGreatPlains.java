package com.sown.outerrim.dimension.naboo;

import java.util.Random;

import com.sown.outerrim.entities.EntityKaadu;
import com.sown.outerrim.entities.EntityPelikki;

import net.minecraft.block.BlockFlower;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.BiomeGenBase.SpawnListEntry;

public class BiomeGenNabooGreatPlains extends BiomeGenBase {

    protected boolean field_150628_aC;

    public BiomeGenNabooGreatPlains(int p_i1986_1_) {
        super(p_i1986_1_);
        this.setTemperatureRainfall(0.8F, 0.8F);
        this.setHeight(new Height(0.025F, 0.025F));  // Flatter terrain
        this.theBiomeDecorator.treesPerChunk = -999;  // No trees for an open plains look
        this.theBiomeDecorator.flowersPerChunk = 4;
        this.theBiomeDecorator.grassPerChunk = 20;  // Dense grass
        this.theBiomeDecorator.waterlilyPerChunk = 2;  
        this.flowers.clear();
        this.spawnableCreatureList.clear();
        this.spawnableMonsterList.clear();
        this.spawnableCreatureList.add(new SpawnListEntry(EntityKaadu.class, 10, 4, 4));
        this.spawnableCreatureList.add(new SpawnListEntry(EntityPelikki.class, 8, 4, 4));
        
        // Modified flower distribution to represent Naboo's unique flora
        this.addFlower(Blocks.red_flower, 0, 4);
        this.addFlower(Blocks.red_flower, 3, 4);
        this.addFlower(Blocks.red_flower, 8, 4);
        this.addFlower(Blocks.yellow_flower, 0, 6);
    }

    public String func_150572_a(Random p_150572_1_, int p_150572_2_, int p_150572_3_, int p_150572_4_) {
        return BlockFlower.field_149858_b[0];
    }

    public void decorate(World p_76728_1_, Random p_76728_2_, int p_76728_3_, int p_76728_4_) {
        super.decorate(p_76728_1_, p_76728_2_, p_76728_3_, p_76728_4_);
    }

    /**
     * Creates a mutated version of the biome and places it into the biomeList with an index equal to the original plus
     * 128
     */
    public BiomeGenBase createMutation() {
        BiomeGenNabooGreatPlains biomegenplains = new BiomeGenNabooGreatPlains(this.biomeID + 128);
        biomegenplains.setBiomeName("Sunflower Plains");
        biomegenplains.field_150628_aC = true;
        biomegenplains.setColor(9286496);
        biomegenplains.field_150609_ah = 14273354;
        return biomegenplains;
    }
}
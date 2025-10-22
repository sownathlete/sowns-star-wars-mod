package com.sown.outerrim.dimension.bespin;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;

public class BiomeGenBespin extends BiomeGenBase {

    public BiomeGenBespin(int biomeId) {
        super(biomeId);
        this.setBiomeName("Bespin");
        this.rootHeight = -10.0F;
        this.heightVariation = 0.0F;
        this.temperature = 0.5F;   // a “normal” sky color
        this.enableRain = false;

        this.spawnableCreatureList.clear();
        this.spawnableCaveCreatureList.clear();
        this.spawnableMonsterList.clear();
        this.spawnableWaterCreatureList.clear();

        this.topBlock    = Blocks.cobblestone;
        this.fillerBlock = Blocks.bedrock;

        this.theBiomeDecorator.treesPerChunk     = -999;
        this.theBiomeDecorator.deadBushPerChunk  = -999;
        this.theBiomeDecorator.reedsPerChunk     = -999;
        this.theBiomeDecorator.cactiPerChunk     = -999;
    }

    // Remove your old custom sky override and let vanilla handle it:
    @Override
    @SideOnly(Side.CLIENT)
    public int getSkyColorByTemp(float temp) {
        return super.getSkyColorByTemp(temp);
    }

    // Add this to make fog the same color as the sky:
    @SideOnly(Side.CLIENT)
    public int getFogColor() {
        // pass your biome temperature into the sky-color logic and return that
        return this.getSkyColorByTemp(this.temperature);
    }
}

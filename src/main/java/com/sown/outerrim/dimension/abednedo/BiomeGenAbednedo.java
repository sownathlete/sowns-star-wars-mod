package com.sown.outerrim.dimension.abednedo;

import java.util.Random;
import com.sown.util.world.creation.ORBiomeDecorator;
import com.sown.util.world.creation.ORSubBiome;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenBigTree;
import net.minecraft.world.gen.feature.WorldGenLakes;
import net.minecraft.world.gen.feature.WorldGenTrees;
import net.minecraft.block.Block;

public class BiomeGenAbednedo extends ORSubBiome {
    private static final BiomeGenBase.Height H = new BiomeGenBase.Height(0.10F, 0.14F);
    private final WorldGenTrees smallTree = new WorldGenTrees(false, 5, 0, 0, false);
    private final WorldGenBigTree bigTree = new WorldGenBigTree(false);
    private final WorldGenLakes lakeWater = new WorldGenLakes(Blocks.water);

    public BiomeGenAbednedo(int id) {
        super(id);
        this.setHeight(H);
        this.setTemperatureRainfall(0.72F, 0.84F);
        this.waterColorMultiplier = 0x4A6F4C;
        this.topBlock = Blocks.grass;
        this.fillerBlock = Blocks.dirt;
        this.spawnableMonsterList.clear();
        ORBiomeDecorator d = this.theBiomeDecorator;
        d.treesPerChunk = 2;
        d.grassPerChunk = 10;
        d.flowersPerChunk = 8;
        d.reedsPerChunk = 6;
        d.clayPerChunk = 1;
        d.sandPerChunk = 2;
        d.sandPerChunk2 = 1;
        d.generateLakes = true;
    }

    @Override
    public void decorate(World world, Random rand, int chunkX, int chunkZ) {
        super.decorate(world, rand, chunkX, chunkZ);

        if (rand.nextInt(2) == 0) {
            int x = chunkX + rand.nextInt(16) + 8;
            int z = chunkZ + rand.nextInt(16) + 8;
            int y = world.getHeightValue(x, z);
            if (rand.nextInt(4) == 0) bigTree.generate(world, rand, x, y, z);
            else smallTree.generate(world, rand, x, y, z);
        }

        if (theBiomeDecorator.generateLakes && rand.nextInt(7) == 0) {
            int x = chunkX + rand.nextInt(16) + 8;
            int z = chunkZ + rand.nextInt(16) + 8;
            int y = world.getHeightValue(x, z);
            lakeWater.generate(world, rand, x, y, z);
        }
    }

    @SideOnly(Side.CLIENT)
    @Override
    public int getSkyColorByTemp(float t) { return 0xC9E5C5; }

    @SideOnly(Side.CLIENT)
    @Override
    public int getWaterColorMultiplier() { return 0x4A6F4C; }

    @SideOnly(Side.CLIENT)
    @Override
    public int getBiomeFoliageColor(int x, int y, int z) { return 0x93A700; }

    @SideOnly(Side.CLIENT)
    @Override
    public int getBiomeGrassColor(int x, int y, int z) { return 0x93A700; }
}

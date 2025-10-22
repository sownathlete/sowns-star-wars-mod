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

public class BiomeGenAbednedo extends ORSubBiome {
    private static final BiomeGenBase.Height H = new BiomeGenBase.Height(0.12F, 0.16F);
    private final WorldGenTrees smallTree = new WorldGenTrees(false, 5, 0, 0, false);
    private final WorldGenBigTree bigTree = new WorldGenBigTree(false);
    private final WorldGenLakes lakeWater = new WorldGenLakes(Blocks.water);

    public BiomeGenAbednedo(int id) {
        super(id);
        this.setHeight(H);
        this.setTemperatureRainfall(0.75F, 0.85F);
        this.waterColorMultiplier = 0x4EA84A;
        this.topBlock = Blocks.grass;
        this.fillerBlock = Blocks.dirt;
        this.spawnableMonsterList.clear();
        ORBiomeDecorator d = this.theBiomeDecorator;
        d.treesPerChunk = 1;
        d.grassPerChunk = 8;
        d.flowersPerChunk = 8;
        d.reedsPerChunk = 6;
        d.clayPerChunk = 1;
        d.sandPerChunk = 1;
        d.sandPerChunk2 = 0;
        d.generateLakes = true;
    }

    @Override
    public void decorate(World world, Random rand, int chunkX, int chunkZ) {
        super.decorate(world, rand, chunkX, chunkZ);
        if (rand.nextInt(3) == 0) {
            int x = chunkX + rand.nextInt(16) + 8;
            int z = chunkZ + rand.nextInt(16) + 8;
            int y = world.getHeightValue(x, z);
            if (rand.nextInt(4) == 0) bigTree.generate(world, rand, x, y, z);
            else smallTree.generate(world, rand, x, y, z);
        }
        if (theBiomeDecorator.generateLakes && rand.nextInt(10) == 0) {
            int x = chunkX + rand.nextInt(16) + 8;
            int z = chunkZ + rand.nextInt(16) + 8;
            int y = world.getHeightValue(x, z);
            lakeWater.generate(world, rand, x, y, z);
        }
    }

    @SideOnly(Side.CLIENT)
    @Override
    public int getSkyColorByTemp(float t) { return 0xB6F2A0; }

    @SideOnly(Side.CLIENT)
    @Override
    public int getWaterColorMultiplier() { return 0x4EA84A; }

    @SideOnly(Side.CLIENT)
    @Override
    public int getBiomeFoliageColor(int x, int y, int z) { return 0x95A500; }

    @SideOnly(Side.CLIENT)
    @Override
    public int getBiomeGrassColor(int x, int y, int z) { return 0x95A500; }
}

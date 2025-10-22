package com.sown.outerrim.dimension.byss;

import java.util.Random;

import com.sown.util.world.creation.ORBiomeDecorator;
import com.sown.util.world.creation.ORSubBiome;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import net.minecraft.block.Block;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.monster.EntityWitch;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenLakes;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenTrees;

public class BiomeGenByss extends ORSubBiome {
    private ORBiomeDecorator customBiomeDecorator;
    private static final BiomeGenBase.Height biomeHeight = new BiomeGenBase.Height(0.2f, 0.4f);

    public BiomeGenByss(int biomeID) {
        super(biomeID);

        // General properties
        this.setHeight(biomeHeight);
        this.setTemperatureRainfall(0.8f, 0.1f); // Mild and dry
        this.setColor(4992000); // Blue-green sky color in decimal

        // Top and filler blocks
        this.topBlock = Blocks.grass; // Surface
        this.fillerBlock = Blocks.dirt; // Underground filler

        // Clear spawn lists to control mob spawning
        this.spawnableCreatureList.clear();
        this.spawnableMonsterList.clear(); // Clear to avoid duplication

        // Add only nocturnal hostile mobs (mobs that spawn at night)
        this.spawnableMonsterList.add(new SpawnListEntry(EntityZombie.class, 100, 2, 4));  // Common
        this.spawnableMonsterList.add(new SpawnListEntry(EntitySkeleton.class, 100, 2, 4)); // Common
        this.spawnableMonsterList.add(new SpawnListEntry(EntitySpider.class, 100, 2, 4)); // Common
        this.spawnableMonsterList.add(new SpawnListEntry(EntityCreeper.class, 80, 1, 3)); // Explosives
        this.spawnableMonsterList.add(new SpawnListEntry(EntityEnderman.class, 30, 1, 2)); // Rare
        this.spawnableMonsterList.add(new SpawnListEntry(EntityWitch.class, 10, 1, 1)); // Very rare

        // Adjust biome decorator
        this.customBiomeDecorator = this.theBiomeDecorator;
        this.customBiomeDecorator.treesPerChunk = 1; // Sparse vegetation
        this.customBiomeDecorator.grassPerChunk = 5; // Fern-like plants
        this.customBiomeDecorator.generateLakes = true; // Include lakes
    }
    
    @Override
    public void decorate(World world, Random random, int chunkX, int chunkZ) {
        super.decorate(world, random, chunkX, chunkZ);

        for (int xOffset = 0; xOffset < 16; xOffset++) {
            for (int zOffset = 0; zOffset < 16; zOffset++) {
                int x = chunkX + xOffset;
                int z = chunkZ + zOffset;

                // Start from the surface and search downward for the first underwater grass block
                for (int y = 61; y >= 30; y--) {
                    Block block = world.getBlock(x, y, z);
                    Block aboveBlock = world.getBlock(x, y + 1, z); // Check if it's submerged

                    // If we find a grass block underwater, replace it and stop searching further down
                    if (block == Blocks.grass && aboveBlock == Blocks.water) {
                        world.setBlock(x, y, z, Blocks.sand);
                        break; // Stop searching below this point
                    }
                }

                // 10% chance to place double tallgrass on valid ground (grass only)
                int topY = world.getTopSolidOrLiquidBlock(x, z) - 1; // Get the real topmost block
                Block groundBlock = world.getBlock(x, topY, z);

                if (random.nextInt(10) == 0 && groundBlock == Blocks.grass) {
                    // Ensure there's space for the top block
                    if (world.isAirBlock(x, topY + 1, z)) {
                        world.setBlock(x, topY + 1, z, Blocks.double_plant, 2, 2); // Bottom half
                        world.setBlock(x, topY + 2, z, Blocks.double_plant, 8, 2); // Top half
                    }
                }
            }
        }
    }

    private void generateTrees(World world, Random random, int chunkX, int chunkZ) {
        int x = chunkX + random.nextInt(16);
        int z = chunkZ + random.nextInt(16);
        int y = world.getHeightValue(x, z); // Avoid liquids

        // Ensure no infinite updates by isolating tree placement
        if (world.getBlock(x, y - 1, z) == Blocks.grass) {
            WorldGenTrees treeGenerator = new WorldGenTrees(false);
            treeGenerator.generate(world, random, x, y, z);
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getSkyColorByTemp(float temp) {
        return 7451294; // Eerie blue-green sky
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getWaterColorMultiplier() {
        return 2520992; // Blue-green water tint
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getBiomeGrassColor(int x, int y, int z) {
        return 6192150; // Slightly corrupted green grass
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getBiomeFoliageColor(int x, int y, int z) {
        return 6192150; // Matches the grass color
    }
}

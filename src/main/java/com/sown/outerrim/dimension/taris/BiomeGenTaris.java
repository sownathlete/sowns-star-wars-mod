package com.sown.outerrim.dimension.taris;

import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.monster.EntitySilverfish;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.passive.EntityBat;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;

public class BiomeGenTaris extends BiomeGenBase {

    public BiomeGenTaris(int biomeID) {
        super(biomeID);

        // General properties
        this.setTemperatureRainfall(1.0F, 0.3F); // Warm, slightly humid due to pollution
        this.setColor(10329501); // Grayish, dull urban aesthetic

        // Surface and filler blocks
        this.topBlock = Blocks.gravel;  // Overgrown surface
        this.fillerBlock = Blocks.stone; // Represents ruined structures underneath

        // Biome decoration settings
        this.theBiomeDecorator.treesPerChunk = 0; // No trees
        this.theBiomeDecorator.deadBushPerChunk = 4; // Represents dry, ruined vegetation
        this.theBiomeDecorator.reedsPerChunk = 0;
        this.theBiomeDecorator.grassPerChunk = 2; // Some overgrown sections
        this.theBiomeDecorator.mushroomsPerChunk = 3; // Polluted fungal growth

        // Mobs
        this.spawnableMonsterList.clear();
        this.spawnableMonsterList.add(new SpawnListEntry(EntitySpider.class, 10, 2, 4));
        this.spawnableMonsterList.add(new SpawnListEntry(EntitySilverfish.class, 8, 1, 3)); // Represents scavengers
        this.spawnableCreatureList.clear();
        this.spawnableCreatureList.add(new SpawnListEntry(EntityBat.class, 5, 1, 2)); // Bats lurking in ruins
    }

    @Override
    public void decorate(World world, Random random, int chunkX, int chunkZ) {
        super.decorate(world, random, chunkX, chunkZ);

        // Generate ruins and wreckage
        generateRuins(world, random, chunkX, chunkZ);
    }

    private void generateRuins(World world, Random random, int chunkX, int chunkZ) {
        // 1/500 chance per chunk
        if (random.nextInt(500) != 0) {
            return; // Skip this chunk if it doesn't meet the probability check
        }

        for (int i = 0; i < 3; i++) { // 3 ruined structures per chunk
            int x = chunkX + random.nextInt(16);
            int z = chunkZ + random.nextInt(16);
            int y = world.getHeightValue(x, z);

            // Ensure ruins generate only on solid ground (no water)
            Block groundBlock = world.getBlock(x, y - 1, z);
            if (groundBlock != Blocks.grass && groundBlock != Blocks.dirt && groundBlock != Blocks.stone) {
                continue; // Skip if the block isn't a solid surface
            }

            // Build a small ruined structure (2x2 pillars)
            world.setBlock(x, y, z, Blocks.cobblestone);
            world.setBlock(x, y + 1, z, Blocks.stonebrick, 2, 2); // Cracked stone brick (meta 2)
            world.setBlock(x + 1, y, z, Blocks.cobblestone);
            world.setBlock(x + 1, y + 1, z, Blocks.stonebrick, 1, 2); // Mossy stone brick (meta 1)

            // Occasionally add walls or fences for variation
            if (random.nextBoolean()) {
                world.setBlock(x, y + 2, z, Blocks.fence);
            }
            if (random.nextBoolean()) {
                world.setBlock(x + 1, y + 2, z, Blocks.stonebrick, 2, 2);
            }
        }
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public int getBiomeGrassColor(int x, int y, int z) {
        return 3763041;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getBiomeFoliageColor(int x, int y, int z) {
        return 4173640;
    }

}

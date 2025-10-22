package com.sown.outerrim.dimension.tatooine;

import java.util.Random;

import com.sown.outerrim.OuterRimResources;
import com.sown.outerrim.entities.EntityTuskenRaider;
import com.sown.outerrim.entities.MobTatooineCommoner;

import net.minecraft.block.Block;
import net.minecraft.entity.monster.*;
import net.minecraft.entity.passive.*;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.BiomeGenBase.SpawnListEntry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BiomeGenTatooineDesert extends TatooineBiomes {

    private final Block tatooineSand3;
    private final Block tatooineSand4;

    public BiomeGenTatooineDesert() {
        super(OuterRimResources.ConfigOptions.biomeTatooineDesertId);
        tatooineSand3 = Blocks.sand;
        tatooineSand4 = Blocks.sand;

        this.enableRain = true;
        this.enableSnow = true;
        this.rootHeight = 0.1F;
        this.heightVariation = 0.0F;
        this.setTemperatureRainfall(0.8f, 0.9f);
        this.topBlock = tatooineSand3;
        this.topMeta = 0;
        this.fillerBlock = tatooineSand3;
        this.fillerMeta = 0;
        this.stoneBlock = tatooineSand4;
        this.stoneMeta = 0;

        this.spawnableCreatureList.clear();
        this.spawnableCaveCreatureList.clear();
        this.spawnableMonsterList.clear();
        this.spawnableWaterCreatureList.clear();

        this.spawnableCreatureList.add(new SpawnListEntry(EntityTuskenRaider.class, 8, 1, 4));
    }

    @Override
    public void genTerrainBlocks(World world, Random rand, Block[] blocks, byte[] meta, int chunkX, int chunkZ, double stoneNoise) {
        for (int i = 0; i < blocks.length; i++) {
            if (blocks[i] == this.topBlock || blocks[i] == this.fillerBlock || blocks[i] == this.stoneBlock) {
                blocks[i] = rand.nextInt(6) < 5 ? tatooineSand4 : tatooineSand3;
            }
        }
        super.genTerrainBlocks(world, rand, blocks, meta, chunkX, chunkZ, stoneNoise);
    }

    @Override
    public void decorate(World world, Random rand, int chunkX, int chunkZ) {
        // Vaporator
        if (rand.nextInt(30) == 0) {
            int k = chunkX + rand.nextInt(16) + 8;
            int l = chunkZ + rand.nextInt(16) + 8;
            new WorldGenVaporatorSand().generate(world, rand, k, world.getHeightValue(k, l) + 2, l);
        }

//        if (rand.nextInt(75) == 0) {
//            int x = chunkX + rand.nextInt(16);
//            int z = chunkZ + rand.nextInt(16);
//            int y = getTopSolidBlockY(world, x, z);
//            new WorldGenTatooineHomestead().generate(world, rand, x, y, z);
//
//            if (!world.isRemote) {
//            	MobTatooineCommoner commoner = new MobTatooineCommoner(world);
//            	int safeY = getTopSolidBlockY(world, x + 4, z + 4); // center-ish of structure
//            	commoner.setLocationAndAngles(x + 4.5, safeY + 1, z + 4.5, rand.nextFloat() * 360F, 0F);
//                boolean success = world.spawnEntityInWorld(commoner);
//                System.out.println("Spawn success: " + success);
//            }
//        }

//        if (rand.nextInt(75) == 0) {
//            int x = chunkX + rand.nextInt(16);
//            int z = chunkZ + rand.nextInt(16);
//            int y = getTopSolidBlockY(world, x, z);
//            new WorldGenTatooineHomesteadNew().generate(world, rand, x, y - 2, z);
//
//            if (!world.isRemote) {
//                int count = 1 + rand.nextInt(5); // 1 to 5
//                int vaporatorX = x + 13;
//                int vaporatorZ = z + 11;
//                int baseY = y - 1;
//
//                for (int i = 0; i < count; i++) {
//                    int offsetX = vaporatorX + rand.nextInt(5) - 2;
//                    int offsetZ = vaporatorZ + rand.nextInt(5) - 2;
//
//                    // try to find a safe spot near the ground floor
//                    int spawnY = getTopSolidBlockY(world, offsetX, offsetZ);
//
//                    world.setBlockToAir(offsetX, spawnY, offsetZ);
//                    world.setBlockToAir(offsetX, spawnY + 1, offsetZ);
//
//                    MobTatooineCommoner commoner = new MobTatooineCommoner(world);
//                    commoner.setLocationAndAngles(offsetX + 0.5, spawnY + 1, offsetZ + 0.5, rand.nextFloat() * 360F, 0F);
//                    boolean success = world.spawnEntityInWorld(commoner);
//                    System.out.println("Spawn success: " + success);
//                }
//            }
//        }

//        if (rand.nextInt(10) == 0) {
//            int x = chunkX + rand.nextInt(16);
//            int z = chunkZ + rand.nextInt(16);
//            int y = getTopSolidBlockY(world, x, z);
//            new WorldGenTatooineTriad().generate(world, rand, x, y, z);
//
//            if (!world.isRemote) {
//            	MobTatooineCommoner raider = new MobTatooineCommoner(world);
//                raider.setLocationAndAngles(x + 4.5, y + 1, z + 4.5, rand.nextFloat() * 360F, 0);
//                world.spawnEntityInWorld(raider);
//            }
//        }
    }

    private int getTopSolidBlockY(World world, int x, int z) {
        for (int y = world.getActualHeight() - 1; y > 0; y--) {
            Block block = world.getBlock(x, y, z);
            if (block != null && block.getMaterial().isSolid() && !block.isAir(world, x, y, z)) {
                return y;
            }
        }
        return 64;
    }

    @Override
    public int getBiomeGrassColor(int x, int y, int z) {
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

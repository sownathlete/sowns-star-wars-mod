package com.sown.outerrim.worldgen;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class WorldGenCustomCrystalCaves {
    public void generate(Block block, int color, World world, Random rand, int chunkX, int chunkZ, int chancesToSpawn, int minHeight, int maxHeight, Block parentBlock) {
        if (minHeight < 0 || maxHeight > 256 || minHeight > maxHeight) {
            throw new IllegalArgumentException("Illegal Height Arguments for WorldGenerator");
        }
        int heightDiff = maxHeight - minHeight + 1;
        for (int i = 0; i < chancesToSpawn; ++i) {
            int x = chunkX * 16 + rand.nextInt(16);
            int z = chunkZ * 16 + rand.nextInt(16);
            int y = minHeight + rand.nextInt(heightDiff);

            if (!world.isAirBlock(x, y, z)) continue;

            if (world.getBlock(x - 1, y, z) == parentBlock) {
                this.generateCrystal(world, color, rand, block, x, y, z, 1);
            } else if (world.getBlock(x + 1, y, z) == parentBlock) {
                this.generateCrystal(world, color, rand, block, x, y, z, 2);
            } else if (world.getBlock(x, y, z - 1) == parentBlock) {
                this.generateCrystal(world, color, rand, block, x, y, z, 3);
            } else if (world.getBlock(x, y, z + 1) == parentBlock) {
                this.generateCrystal(world, color, rand, block, x, y, z, 4);
            }
            if (world.getBlock(x, y - 1, z) == parentBlock) {
                this.generateCrystal(world, color, rand, block, x, y, z, 5);
            }
            if (world.getBlock(x, y + 1, z) == parentBlock) {
                this.generateCrystal(world, color, rand, block, x, y, z, 6);
            }
        }
    }

    public void generateCrystal(World world, int color, Random rand, Block block, int x, int y, int z, int metadata) {
        world.setBlock(x, y, z, block, metadata, 2);
        TileEntity tileEntity = world.getTileEntity(x, y, z);
        if (tileEntity != null) {
            NBTTagCompound nbt = new NBTTagCompound();
            tileEntity.writeToNBT(nbt); // Read existing data
            nbt.setInteger("color", color); // Add the color property
            tileEntity.readFromNBT(nbt); // Write back modified data
            tileEntity.markDirty(); // Mark the TileEntity as updated
            world.markBlockForUpdate(x, y, z); // Notify the world of the update
        }
    }

}

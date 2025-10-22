/*
 * Decompiled with CFR 0.148.
 *
 * Could not load the following classes:
 *  cpw.mods.fml.common.FMLCommonHandler
 *  net.minecraft.block.Block
 *  net.minecraft.server.MinecraftServer
 *  net.minecraft.world.World
 *  net.minecraft.world.WorldProvider
 *  net.minecraft.world.WorldServer
 *  net.minecraft.world.chunk.Chunk
 *  net.minecraftforge.common.DimensionManager
 *  net.minecraftforge.common.util.BlockSnapshot
 */
package com.sown.util.world;

import cpw.mods.fml.common.FMLCommonHandler;
import java.util.ArrayList;
import net.minecraft.block.Block;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.WorldServer;
import net.minecraft.world.chunk.Chunk;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.util.BlockSnapshot;

public class WorldUtils {
    public static void registerDimension(int dimId) {
        DimensionManager.registerDimension(dimId, dimId);
        FMLCommonHandler.instance().getMinecraftServerInstance().worldServerForDimension(dimId);
    }

    public static void registerDimension(int dimId, Class<? extends WorldProvider> class1) {
        DimensionManager.registerProviderType(dimId, class1, true);
        DimensionManager.registerDimension(dimId, dimId);
    }

    public static boolean setBlock(World world, int x, int y, int z, Block block, int metadata, int flags) {
        boolean flag;
        if (x < -30000000 || z < -30000000 || x >= 30000000 || z >= 30000000 || y < 0 || y >= 256)
            return false;
        Chunk chunk = world.getChunkFromChunkCoords(x >> 4, z >> 4);
        Block block1 = null;
        BlockSnapshot blockSnapshot = null;
        if ((flags & 1) != 0) {
            block1 = chunk.getBlock(x & 0xF, y, z & 0xF);
        }
        if (world.captureBlockSnapshots && !world.isRemote) {
            blockSnapshot = BlockSnapshot.getBlockSnapshot(world, x, y, z, flags);
            world.capturedBlockSnapshots.add(blockSnapshot);
        }
        if (!(flag = chunk.func_150807_a(x & 0xF, y, z & 0xF, block, metadata)) && blockSnapshot != null) {
            world.capturedBlockSnapshots.remove(blockSnapshot);
            blockSnapshot = null;
        }
        if (flag && blockSnapshot == null) {
            world.markAndNotifyBlock(x, y, z, chunk, block1, block, flags);
        }
        return flag;
    }

    public static void b(World world, int x, int y, int z, Block block, int metadata) {
        WorldUtils.setBlock(world, x, y, z, block, metadata, 2);
    }

    public static void m(World world, int x, int y, int z, int metadata) {
        world.setBlockMetadataWithNotify(x, y, z, metadata, 2);
    }
}


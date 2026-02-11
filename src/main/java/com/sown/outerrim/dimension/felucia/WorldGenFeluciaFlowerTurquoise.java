package com.sown.outerrim.dimension.felucia;

import com.sown.outerrim.registry.BlockRegister;
import com.sown.outerrim.tileentities.TileEntityFeluciaFlowerTurquoise;

import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.Random;

public class WorldGenFeluciaFlowerTurquoise extends WorldGenerator {

    // Prefer storing the registry name and resolving lazily.
    // This avoids NPEs when generators are constructed before block registration.
    private final String flowerBlockName;
    private final int flowerMeta;

    // Cached after first resolve
    private Block cachedFlowerBlock;

    /**
     * Recommended ctor: pass the registry name used by your BlockRegister.
     * Example: new WorldGenFeluciaFlowerTurquoise("feluciaFlowerTurquoise", 0)
     */
    public WorldGenFeluciaFlowerTurquoise(String flowerBlockName, int flowerMeta) {
        this.flowerBlockName = flowerBlockName;
        this.flowerMeta = flowerMeta;
    }

    /**
     * Backward-compatible ctor: if some code is still calling (Block, meta),
     * we keep it, but still protect against null by resolving later if needed.
     */
    public WorldGenFeluciaFlowerTurquoise(Block flowerBlock, int flowerMeta) {
        this.cachedFlowerBlock = flowerBlock;
        this.flowerMeta = flowerMeta;
        this.flowerBlockName = null;
    }

    private Block getFlowerBlock() {
        if (cachedFlowerBlock != null) return cachedFlowerBlock;

        if (flowerBlockName != null) {
            Block b = BlockRegister.getRegisteredBlock(flowerBlockName);
            if (b != null) {
                cachedFlowerBlock = b;
                return b;
            }
        }
        return null;
    }

    @Override
    public boolean generate(World world, Random rand, int x, int y, int z) {
        Block flowerBlock = getFlowerBlock();
        if (flowerBlock == null) {
            // If block isn't registered/available, don't crash worldgen.
            return false;
        }

        // Vanilla small mushroom style: 64 tries around origin
        for (int i = 0; i < 64; i++) {
            int dx = x + rand.nextInt(8) - rand.nextInt(8);
            int dy = y + rand.nextInt(4) - rand.nextInt(4);
            int dz = z + rand.nextInt(8) - rand.nextInt(8);

            if (!world.isAirBlock(dx, dy, dz)) continue;

            // canBlockStay can assume non-null now
            if (!flowerBlock.canBlockStay(world, dx, dy, dz)) continue;

            // Place the flower (flag 2 = notify neighbors, no block update spam)
            if (!world.setBlock(dx, dy, dz, flowerBlock, flowerMeta, 2)) continue;

            // If it has a TE, give it a random facing 0..3
            TileEntity te = world.getTileEntity(dx, dy, dz);
            if (te instanceof TileEntityFeluciaFlowerTurquoise) {
                ((TileEntityFeluciaFlowerTurquoise) te).setFacing(rand.nextInt(4));
            }
        }

        return true;
    }
}

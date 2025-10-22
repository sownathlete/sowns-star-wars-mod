package com.sown.outerrim.dimension.felucia;

import com.sown.outerrim.registry.BlockRegister;
import com.sown.outerrim.blocks.BlockFeluciaFlowerTurquoise;
import com.sown.outerrim.tileentities.TileEntityFeluciaFlowerTurquoise;
import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.Random;

public class WorldGenFeluciaFlowerTurquoise extends WorldGenerator {
    private final Block flowerBlock;
    private final int  flowerMeta;

    public WorldGenFeluciaFlowerTurquoise(Block flowerBlock, int flowerMeta) {
        this.flowerBlock = flowerBlock;
        this.flowerMeta  = flowerMeta;
    }

    @Override
    public boolean generate(World world, Random rand, int x, int y, int z) {
        // vanilla “small mushroom” style: 64 tries around the origin
        for (int i = 0; i < 64; i++) {
            int dx = x + rand.nextInt(8) - rand.nextInt(8);
            int dy = y + rand.nextInt(4) - rand.nextInt(4);
            int dz = z + rand.nextInt(8) - rand.nextInt(8);

            if (!world.isAirBlock(dx, dy, dz)) continue;
            if (!flowerBlock.canBlockStay(world, dx, dy, dz)) continue;

            // place your flower and create its TE
            world.setBlock(dx, dy, dz, flowerBlock, flowerMeta, 2);

            // now give it a random facing 0–3
            TileEntity te = world.getTileEntity(dx, dy, dz);
            if (te instanceof TileEntityFeluciaFlowerTurquoise) {
                ((TileEntityFeluciaFlowerTurquoise) te).setFacing(rand.nextInt(4));
            }
        }
        return true;
    }
}

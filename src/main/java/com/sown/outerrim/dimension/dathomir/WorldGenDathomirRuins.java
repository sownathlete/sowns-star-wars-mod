package com.sown.outerrim.dimension.dathomir;

import java.util.*;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import com.sown.outerrim.registry.BlockRegister;
import com.sown.outerrim.registry.ItemRegister;

public class WorldGenDathomirRuins extends WorldGenerator {

    private final Block brick = BlockRegister.getRegisteredBlock("dathomirBricks");
    private final Block slab = BlockRegister.getRegisteredBlock("dathomirBricks_slab");
    private final Block stairs = BlockRegister.getRegisteredBlock("dathomirBricks_stairs");
    private final Block wall = BlockRegister.getRegisteredBlock("dathomirBricks_wall");

    @Override
    public boolean generate(World world, Random rand, int x, int y, int z) {
        if (y < 1 || y > 250) return false;
        Block ground = world.getBlock(x, y - 1, z);
        if (ground == null || !ground.getMaterial().isSolid()) return false;

        boolean advanced = rand.nextFloat() < 0.3f;
        boolean withChest = rand.nextInt(20) == 0;

        int width = advanced ? 8 + rand.nextInt(5) : 5 + rand.nextInt(3);
        int length = advanced ? 8 + rand.nextInt(5) : 5 + rand.nextInt(3);
        int height = advanced ? 5 + rand.nextInt(3) : 3 + rand.nextInt(2);

        Set<String> placed = new HashSet<>();
        List<int[]> validInteriorSpots = new ArrayList<>();

        for (int dx = 0; dx < width; dx++) {
            for (int dz = 0; dz < length; dz++) {
                int wx = x + dx;
                int wz = z + dz;
                Block below = world.getBlock(wx, y - 1, wz);
                if (below == null || !below.getMaterial().isSolid()) continue;

                boolean border = dx == 0 || dx == width - 1 || dz == 0 || dz == length - 1;
                boolean shouldPlace = border || rand.nextFloat() < 0.1f;

                if (shouldPlace) {
                    Block block = border ? brick : slab;
                    safePlace(world, wx, y, wz, block);
                    placed.add(wx + "," + y + "," + wz);
                }

                if (!border && below.getMaterial().isSolid()) {
                    validInteriorSpots.add(new int[]{wx, y + 1, wz});
                }
            }
        }

        if (placed.isEmpty()) return false;

        int[][] basePillars = {
            {x, z},
            {x + width - 1, z},
            {x, z + length - 1},
            {x + width - 1, z + length - 1}
        };

        for (int[] pos : basePillars) {
            int px = pos[0], pz = pos[1];
            if (!placed.contains(px + "," + y + "," + pz)) continue;

            int pHeight = height - rand.nextInt(2);
            for (int h = 1; h <= pHeight; h++) {
                safePlace(world, px, y + h, pz, wall);
                placed.add(px + "," + (y + h) + "," + pz);
            }
        }

        if (advanced) {
            int extraPillars = 2 + rand.nextInt(4);
            for (int i = 0; i < extraPillars; i++) {
                int px = x + 1 + rand.nextInt(width - 2);
                int pz = z + 1 + rand.nextInt(length - 2);
                if (!placed.contains(px + "," + y + "," + pz)) continue;

                int pHeight = 3 + rand.nextInt(2);
                for (int h = 1; h <= pHeight; h++) {
                    safePlace(world, px, y + h, pz, wall);
                    placed.add(px + "," + (y + h) + "," + pz);
                }
            }
        }

        if (rand.nextBoolean()) {
            int ax = x + width / 2;
            int az = z;
            if (placed.contains(ax + "," + y + "," + az)) {
                for (int h = 1; h <= 2; h++) {
                    safePlace(world, ax, y + h, az, stairs);
                }
            }
        }

        if (advanced && rand.nextBoolean()) {
            int ax = x;
            int az = z + length / 2;
            if (placed.contains(ax + "," + y + "," + az)) {
                for (int h = 1; h <= 2; h++) {
                    safePlace(world, ax, y + h, az, stairs);
                }
            }
        }

        if (withChest && !validInteriorSpots.isEmpty()) {
            int[] spot = validInteriorSpots.get(rand.nextInt(validInteriorSpots.size()));
            Block under = world.getBlock(spot[0], spot[1] - 1, spot[2]);
            if (under != null && under.getMaterial().isSolid()) {
                world.setBlock(spot[0], spot[1], spot[2], Blocks.chest, 0, 2);
                TileEntityChest te = (TileEntityChest) world.getTileEntity(spot[0], spot[1], spot[2]);
                if (te != null) {
                    int items = 3 + rand.nextInt(3);
                    for (int i = 0; i < items; i++) {
                        int slot = rand.nextInt(te.getSizeInventory());
                        te.setInventorySlotContents(slot, getRandomLoot(rand));
                    }
                }
            }
        }

        return true;
    }

    private ItemStack getRandomLoot(Random rand) {
        switch (rand.nextInt(7)) {
            case 0: return new ItemStack(Items.iron_pickaxe);
            case 1: return new ItemStack(ItemRegister.getRegisteredItem("nightbrotherMalletSword"));
            case 2: return new ItemStack(Items.bread, 2 + rand.nextInt(3));
            case 3: return new ItemStack(Items.apple, 1 + rand.nextInt(2));
            case 4: return new ItemStack(Items.cooked_beef, 1 + rand.nextInt(2));
            case 5: return new ItemStack(ItemRegister.getRegisteredItem("galactic_credit_1"), 3 + rand.nextInt(5));
            default: return new ItemStack(ItemRegister.getRegisteredItem("galactic_credit_10"), 1 + rand.nextInt(2));
        }
    }

    private void safePlace(World world, int x, int y, int z, Block block) {
        if (y < 0 || y >= 256 || block == null) return;
        try {
            world.setBlock(x, y, z, block, 0, 2);
        } catch (Throwable t) {
            System.err.println("[Outer Rim] Failed to place block at " + x + "," + y + "," + z);
        }
    }
}

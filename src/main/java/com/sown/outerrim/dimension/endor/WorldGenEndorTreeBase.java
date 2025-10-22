package com.sown.outerrim.dimension.endor;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public abstract class WorldGenEndorTreeBase extends WorldGenerator {
    // dry-run recording bounds
    private int minDX, maxDX, minDZ, maxDZ;
    private boolean recording;
    private int originX, originY, originZ;

    // all offsets the tree would place
    private final List<int[]> recordedPositions = new ArrayList<>();

    /** Subclasses implement this using `place(...)` for every block. */
    protected abstract void buildTree(World world, Random rand, int x, int y, int z);

    @Override
    public final boolean generate(World world, Random rand, int x, int y, int z) {
        // 1) find non-liquid ground
        y = findGroundY(world, x, z);
        if (world.getBlock(x, y - 1, z).getMaterial().isLiquid()) return false;

        originX = x; originY = y; originZ = z;

        // 2) dry-run to record footprint
        recording = true;
        recordedPositions.clear();
        minDX = minDZ = Integer.MAX_VALUE;
        maxDX = maxDZ = Integer.MIN_VALUE;
        buildTree(world, rand, x, y, z);
        recording = false;

        // 3) cancel if any recorded spot overlaps liquid or is in an unloaded chunk
        for (int[] pos : recordedPositions) {
            int bx = x + pos[0], by = y + pos[1], bz = z + pos[2];
            // avoid loading new chunks
            if (!world.blockExists(bx, by, bz)) {
                return false;
            }
            Block b = world.getBlock(bx, by, bz);
            if (b.getMaterial().isLiquid()) {
                return false;
            }
        }

        // 4) ensure ground under footprint is loaded, solid, non-liquid
        int width  = maxDX - minDX + 1;
        int length = maxDZ - minDZ + 1;
        if (!canPlaceOnSolidGround(world, x + minDX, y, z + minDZ, width, length)) {
            return false;
        }

        // 5) clear only truly replaceable materials at surface
        for (int dx = minDX; dx <= maxDX; dx++) {
            for (int dz = minDZ; dz <= maxDZ; dz++) {
                int bx = x + dx, bz = z + dz;
                if (!world.blockExists(bx, y, bz)) continue;
                Block here = world.getBlock(bx, y, bz);
                if (here != null) {
                    Material m = here.getMaterial();
                    if (m.isReplaceable()
                     || m == Material.plants
                     || m == Material.vine
                     || m == Material.leaves
                     || m.isLiquid()) {
                        world.setBlockToAir(bx, y, bz);
                    }
                }
            }
        }

        // 6) actually build
        buildTree(world, rand, x, y, z);

        // 7) fill air under footprint
        for (int[] pos : recordedPositions) {
            int bx = x + pos[0], bz = z + pos[2], by = y - 1;
            while (by > 0
                && world.blockExists(bx, by, bz)
                && world.isAirBlock(bx, by, bz)
                && !world.getBlock(bx, by, bz).getMaterial().isLiquid()) {
                place(world, bx, by, bz, Blocks.log, 0);
                by--;
            }
        }

        // 8) blend buried logs
        for (int[] pos : recordedPositions) {
            int bx = x + pos[0], bz = z + pos[2];
            for (int by2 = y - 1; by2 >= 1; by2--) {
                if (!world.blockExists(bx, by2, bz)) break;
                Block here = world.getBlock(bx, by2, bz);
                if (here != Blocks.log) continue;

                Map<String, Integer> counts = new HashMap<>();
                int[][] offs = {{1,0},{-1,0},{0,1},{0,-1}};
                for (int[] d : offs) {
                    int nx = bx + d[0], nz = bz + d[1];
                    if (!world.blockExists(nx, by2, nz)) continue;
                    Block nb = world.getBlock(nx, by2, nz);
                    if (nb == null) continue;
                    Material m = nb.getMaterial();
                    if (!m.isSolid() || m.isLiquid()) continue;
                    int meta = world.getBlockMetadata(nx, by2, nz);
                    String key = Block.blockRegistry.getNameForObject(nb) + ":" + meta;
                    counts.put(key, counts.getOrDefault(key, 0) + 1);
                }
                String best = null; int bestC = 0;
                for (Map.Entry<String, Integer> e : counts.entrySet()) {
                    if (e.getValue() >= 2 && e.getValue() > bestC) {
                        best = e.getKey(); bestC = e.getValue();
                    }
                }
                if (best != null) {
                    String[] parts = best.split(":");
                    Block repl = (Block) Block.blockRegistry.getObject(parts[0]);
                    int meta;
                    try { meta = Integer.parseInt(parts[1]); }
                    catch (NumberFormatException ex) { continue; }
                    if (repl != null) world.setBlock(bx, by2, bz, repl, meta, 2);
                }
            }
        }

        return true;
    }

    /**
     * - Records all placements when `recording==true`.
     * - In real run, skips placing if existing block is solid (no overwrites).
     */
    protected void place(World world, int bx, int by, int bz, Block block, int meta) {
        if (recording) {
            int dx = bx - originX, dy = by - originY, dz = bz - originZ;
            recordedPositions.add(new int[]{dx, dy, dz});
            minDX = Math.min(minDX, dx);
            maxDX = Math.max(maxDX, dx);
            minDZ = Math.min(minDZ, dz);
            maxDZ = Math.max(maxDZ, dz);
            return;
        }

        if (!world.blockExists(bx, by, bz)) return;
        Block existing = world.getBlock(bx, by, bz);
        // never overwrite any solid block
        if (existing != null && existing.getMaterial().isSolid()) {
            return;
        }

        world.setBlock(bx, by, bz, block, meta, 2);
    }

    /** True if every column below the footprint is loaded and solid non-liquid */
    private boolean canPlaceOnSolidGround(World world, int x, int y, int z, int w, int l) {
        // ensure entire area is already generated
        if (!world.checkChunksExist(x, y - 1, z, x + w - 1, y - 1, z + l - 1)) {
            return false;
        }
        for (int dx = 0; dx < w; dx++) {
            for (int dz = 0; dz < l; dz++) {
                Block b = world.getBlock(x + dx, y - 1, z + dz);
                if (b == null
                 || !b.getMaterial().isSolid()
                 || b.getMaterial().isLiquid()) {
                    return false;
                }
            }
        }
        return true;
    }

    /** Find the first non-liquid solid block and return one above it */
    private int findGroundY(World world, int x, int z) {
        int y = world.getTopSolidOrLiquidBlock(x, z);
        while (y > 0) {
            Block b = world.getBlock(x, y - 1, z);
            if (b != null
             && b.getMaterial().isSolid()
             && !b.getMaterial().isLiquid()) {
                return y;
            }
            y--;
        }
        return 1;
    }
}

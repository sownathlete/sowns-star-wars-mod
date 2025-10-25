package com.sown.util.block;

import com.sown.outerrim.tileentities.TileEntityCoaxiumPump;
import com.sown.outerrim.utils.BoundingBoxTile;
import com.sown.outerrim.utils.BoundingComponent;
import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import scala.Int;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MultiblockUtil {
    public static List<BoundingComponent> footprintFor(BoundingComponent[] footprint, int rot) {
        List<BoundingComponent> out = new ArrayList<>(footprint.length);
        for (BoundingComponent p : footprint) out.add(rotate(p, rot));
        return out;
    }

    private static BoundingComponent rotate(BoundingComponent p, int rot) {
        int dx = p.dx, dz = p.dz;
        for (int i = 0; i < rot; i++) { int ndx = -dz; int ndz = dx; dx = ndx; dz = ndz; }
        return new BoundingComponent(dx, p.dy, dz, p.minX, p.minY, p.minZ, p.maxX, p.maxY, p.maxZ);
    }

    // Here the c prefix for the coordinates stands for center
    public static void ensureParts(World world, int cx, int cy, int cz, int rot, Block block, BoundingComponent[] footprint, Map<Integer, Integer> meta_map) {
        List<BoundingComponent> components = MultiblockUtil.footprintFor(footprint, rot);

        for (int partIndex = 0; partIndex < components.size(); partIndex++) {
            BoundingComponent comp = components.get(partIndex);

            Integer meta = meta_map.get(partIndex);
            if (meta == null) {
                continue;
            }

            int px = cx + comp.dx;
            int py = cy + comp.dy;
            int pz = cz + comp.dz;

            boolean isSpace = placeOrReplace(world, px, py, pz, meta, block);

            if (isSpace) {
                world.setTileEntity(px, py, pz, new BoundingBoxTile());
            }

            TileEntity placedTE = world.getTileEntity(px, py, pz);
            if (placedTE instanceof BoundingBoxTile part) {
                part.setCorePos(cx, cy, cz);
            }
        }
    }

    private static boolean placeOrReplace(World world, int x, int y, int z, int meta, Block block) {
        Block existing = world.getBlock(x, y, z);
        if (world.isAirBlock(x, y, z) || existing.isReplaceable(world, x, y, z)) {
            world.setBlock(x, y, z, block, meta, 3);
            return true;
        }

        return false;
    }

    public static boolean canPlaceParts(World world, int cx, int cy, int cz, int rot, BoundingComponent[] footprint) {
        for (BoundingComponent p : MultiblockUtil.footprintFor(footprint, rot)) {
            int px = cx + p.dx;
            int py = cy + p.dy;
            int pz = cz + p.dz;

            Block existing = world.getBlock(px, py, pz);
            if (!world.isAirBlock(px, py, pz) && !existing.isReplaceable(world, px, py, pz)) {
                return false;
            }
        }
        return true;
    }

    public static void removeParts(World world, int cx, int cy, int cz, int rot, boolean breakCore, BoundingComponent[] footprint) {
        for (BoundingComponent comp : MultiblockUtil.footprintFor(footprint, rot)) {
            int px = cx + comp.dx;
            int py = cy + comp.dy;
            int pz = cz + comp.dz;

            TileEntity placedTE = world.getTileEntity(px, py, pz);
            if (placedTE instanceof BoundingBoxTile) {
                world.setBlockToAir(px, py, pz);
                world.setTileEntity(px, py, pz, null);
            }
        }

        if (breakCore) {
            world.setBlockToAir(cx, cy, cz);
            world.setTileEntity(cx, cy, cz, null);
        }
    }

    public static int[] findCoreAround(World world, int x, int y, int z) {
        TileEntity tileEntity = world.getTileEntity(x, y, z);
        if (tileEntity instanceof TileEntityCoaxiumPump) return new int[] { x, y, z};
        else if (tileEntity instanceof BoundingBoxTile boundingBoxTile) {
            return boundingBoxTile.getCorePos();
        } else {
            return null;
        }
    }

    public static Map<Integer, BoundingComponent> createBoundsMap(BoundingComponent[] footprint) {
        Map<Integer, BoundingComponent> boundsMap = new HashMap<>();
        Map<BoundingComponent, Integer> sizeToMetaMap = new HashMap<>();
        int nextUniqueMeta = 1; // Start assignment for unique non-core sizes from 1.

        for (BoundingComponent comp : footprint) {
            int metaToAssign;

            Integer assignedMeta = sizeToMetaMap.get(comp);

            if (assignedMeta == null) {
                if (nextUniqueMeta > 15) {
                    System.err.println("MultiBlock structure exceeded 16 unique bounding box sizes!");
                    metaToAssign = 15;
                } else {
                    metaToAssign = nextUniqueMeta++;
                }

                sizeToMetaMap.put(comp, metaToAssign);
            } else {
                metaToAssign = assignedMeta;
            }

            boundsMap.put(metaToAssign, comp);
        }

        return boundsMap;
    }

    public static Map<Integer, Integer> createMetaMap(BoundingComponent[] footprint) {
        Map<Integer, Integer> metaMap = new HashMap<>();
        Map<BoundingComponent, Integer> sizeToMetaMap = new HashMap<>();
        int nextUniqueMeta = 1;

        for (int partIndex = 0; partIndex < footprint.length; partIndex++) {
            BoundingComponent comp = footprint[partIndex];
            int metaToAssign;

            Integer assignedMeta = sizeToMetaMap.get(comp);

            if (assignedMeta == null) {
                if (nextUniqueMeta > 15) {
                    System.err.println("MultiBlock structure exceeded 16 unique bounding box sizes!");
                    metaToAssign = 15;
                } else {
                    metaToAssign = nextUniqueMeta++;
                }

                sizeToMetaMap.put(comp, metaToAssign);
            } else {
                metaToAssign = assignedMeta;
            }

            metaMap.put(partIndex, metaToAssign);
        }

        return metaMap;
    }
}

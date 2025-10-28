package com.sown.util.block;

import com.sown.outerrim.utils.BoundingBoxTile;
import com.sown.outerrim.utils.BoundingComponent;
import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import java.util.*;

/**
 * Multiblock utility helpers for 1.7.10.
 */
public class MultiblockUtil {

    /**
     * Creates a cubic footprint and the BoundingComponents from base to the footprint.
     * @param base       An array of BoundingComponents to be added to the footprint
     * @param sizeX      Size along X (positive)
     * @param sizeY      Size along Y (positive)
     * @param sizeZ      Size along Z (positive)
     * @param corePosX   Core offset X within the cube [0..sizeX-1]
     * @param corePosY   Core offset Y within the cube [0..sizeY-1]
     * @param corePosZ   Core offset Z within the cube [0..sizeZ-1]
     * @return           Combined array of base + generated components (excluding 0,0,0)
     */
    public static BoundingComponent[] createCubicFootprint(BoundingComponent[] base,
                                                           int sizeX, int sizeY, int sizeZ,
                                                           int corePosX, int corePosY, int corePosZ) {
        ArrayList<BoundingComponent> footprintList = new ArrayList<BoundingComponent>(
                base != null ? base.length + (sizeX * sizeY * sizeZ) - 1 : (sizeX * sizeY * sizeZ) - 1
        );
        if (base != null && base.length > 0) {
            Collections.addAll(footprintList, base);
        }

        for (int x = 0; x < sizeX; x++) {
            for (int y = 0; y < sizeY; y++) {
                for (int z = 0; z < sizeZ; z++) {
                    int dx = x - corePosX;
                    int dy = y - corePosY;
                    int dz = z - corePosZ;

                    if (!(dx == 0 && dy == 0 && dz == 0)) {
                        footprintList.add(new BoundingComponent(dx, dy, dz));
                    }
                }
            }
        }

        return footprintList.toArray(new BoundingComponent[footprintList.size()]);
    }

    public static List<BoundingComponent> footprintFor(BoundingComponent[] footprint, int rot) {
        List<BoundingComponent> out = new ArrayList<BoundingComponent>(footprint.length);
        for (BoundingComponent p : footprint) out.add(rotate(p, rot));
        return out;
    }

    private static BoundingComponent rotate(BoundingComponent p, int rot) {
        int dx = p.dx, dz = p.dz;
        int turns = ((rot % 4) + 4) % 4;
        for (int i = 0; i < turns; i++) {
            int ndx = -dz;
            int ndz = dx;
            dx = ndx;
            dz = ndz;
        }
        return new BoundingComponent(dx, p.dy, dz, p.minX, p.minY, p.minZ, p.maxX, p.maxY, p.maxZ);
    }

    /**
     * Ensure all multiblock parts exist around the core.
     * @param world     world
     * @param cx,cy,cz  core (center) coords
     * @param rot       0..3 clockwise 90° steps
     * @param block     multiblock-part block to place
     * @param footprint unrotated footprint (relative offsets)
     * @param meta_map  partIndex -> metadata for block state/shape
     */
    public static void ensureParts(World world, int cx, int cy, int cz, int rot, Block block,
                                   BoundingComponent[] footprint, Map<Integer, Integer> meta_map) {
        List<BoundingComponent> components = MultiblockUtil.footprintFor(footprint, rot);

        for (int partIndex = 0; partIndex < components.size(); partIndex++) {
            BoundingComponent comp = components.get(partIndex);

            Integer meta = meta_map.get(partIndex);
            if (meta == null) continue;

            int px = cx + comp.dx;
            int py = cy + comp.dy;
            int pz = cz + comp.dz;

            boolean placedNow = placeOrReplace(world, px, py, pz, meta.intValue(), block);

            if (placedNow) {
                world.setTileEntity(px, py, pz, new BoundingBoxTile());
            }

            TileEntity te = world.getTileEntity(px, py, pz);
            if (te instanceof BoundingBoxTile) {
                ((BoundingBoxTile) te).setCorePos(cx, cy, cz);
            }
        }
    }

    private static boolean placeOrReplace(World world, int x, int y, int z, int meta, Block block) {
        Block existing = world.getBlock(x, y, z);
        if (world.isAirBlock(x, y, z) || (existing != null && existing.isReplaceable(world, x, y, z))) {
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
            if (!world.isAirBlock(px, py, pz) && (existing == null || !existing.isReplaceable(world, px, py, pz))) {
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

            TileEntity te = world.getTileEntity(px, py, pz);
            if (te instanceof BoundingBoxTile) {
                world.setBlockToAir(px, py, pz);
                world.removeTileEntity(px, py, pz);
            }
        }

        if (breakCore) {
            world.setBlockToAir(cx, cy, cz);
            world.removeTileEntity(cx, cy, cz);
        }
    }

    /**
     * If (x,y,z) is a part tile, returns its core position; else returns (x,y,z).
     */
    public static int[] findCoreAround(World world, int x, int y, int z) {
        TileEntity te = world.getTileEntity(x, y, z);
        if (te instanceof BoundingBoxTile) {
            return ((BoundingBoxTile) te).getCorePos();
        }
        return new int[]{x, y, z};
    }

    /**
     * Builds a map of meta -> BoundingComponent using uniqueness of the component's bounds.
     * Assumes BoundingComponent implements equals/hashCode by its bounding box fields.
     */
    public static Map<Integer, BoundingComponent> createBoundsMap(BoundingComponent[] footprint) {
        Map<Integer, BoundingComponent> boundsMap = new HashMap<Integer, BoundingComponent>();
        Map<BoundingComponent, Integer> sizeToMetaMap = new HashMap<BoundingComponent, Integer>();
        int nextUniqueMeta = 1; // 0 reserved for core/default; use 1..15 for parts

        for (BoundingComponent comp : footprint) {
            Integer assignedMeta = sizeToMetaMap.get(comp);
            int metaToAssign;
            if (assignedMeta == null) {
                if (nextUniqueMeta > 15) {
                    System.err.println("MultiBlock structure exceeded 16 unique bounding box sizes!");
                    metaToAssign = 15;
                } else {
                    metaToAssign = nextUniqueMeta++;
                }
                sizeToMetaMap.put(comp, metaToAssign);
            } else {
                metaToAssign = assignedMeta.intValue();
            }

            boundsMap.put(metaToAssign, comp);
        }

        return boundsMap;
    }

    /**
     * Builds a map of partIndex -> meta, deduplicating metas for equal-sized BoundingComponents.
     */
    public static Map<Integer, Integer> createMetaMap(BoundingComponent[] footprint) {
        Map<Integer, Integer> metaMap = new HashMap<Integer, Integer>();
        Map<BoundingComponent, Integer> sizeToMetaMap = new HashMap<BoundingComponent, Integer>();
        int nextUniqueMeta = 1; // 0 reserved

        for (int partIndex = 0; partIndex < footprint.length; partIndex++) {
            BoundingComponent comp = footprint[partIndex];

            Integer assignedMeta = sizeToMetaMap.get(comp);
            int metaToAssign;
            if (assignedMeta == null) {
                if (nextUniqueMeta > 15) {
                    System.err.println("MultiBlock structure exceeded 16 unique bounding box sizes!");
                    metaToAssign = 15;
                } else {
                    metaToAssign = nextUniqueMeta++;
                }
                sizeToMetaMap.put(comp, metaToAssign);
            } else {
                metaToAssign = assignedMeta.intValue();
            }

            metaMap.put(partIndex, metaToAssign);
        }

        return metaMap;
    }
}

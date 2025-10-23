package com.sown.outerrim.blocks;

import com.sown.outerrim.OuterRim;
import com.sown.outerrim.OuterRimResources;
import com.sown.outerrim.tileentities.TileEntityCoaxiumRefinery;
import com.sown.util.block.ORBlockContainer;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public class BlockCoaxiumRefinery extends ORBlockContainer {

    private static final int META_CORE = 0;
    private static final int META_FULL = 1;
    private static final int META_HALF = 2;

    private static boolean BREAKING_CORE = false;

    public BlockCoaxiumRefinery(String name, Material material, float hardness, String toolType, int harvestLevel, Block.SoundType stepSound, boolean isMultiSided) {
        super("coaxium_refinery", Material.iron);
        setCreativeTab(OuterRim.tabUtil);
        setHardness(6.0f);
        setHarvestLevel("pickaxe", 2);
        setResistance(50F);
        setBlockBounds(0,0,0,1,1,1);
    }

    @Override public int getRenderType() { return -1; }
    @Override public boolean isOpaqueCube() { return false; }
    @Override public boolean renderAsNormalBlock() { return false; }

    @Override
    public TileEntity createNewTileEntity(World w, int meta) {
        return (meta == META_CORE) ? new TileEntityCoaxiumRefinery() : null;
    }

    @Override
    public void onBlockPlacedBy(World w, int x, int y, int z, EntityLivingBase placer, ItemStack stack) {
        super.onBlockPlacedBy(w, x, y, z, placer, stack);
        w.setBlockMetadataWithNotify(x, y, z, META_CORE, 2);
        TileEntity te = w.getTileEntity(x, y, z);
        if (te instanceof TileEntityCoaxiumRefinery) {
            int rot = (MathHelper.floor_double(placer.rotationYaw * 4.0 / 360.0 + 0.5) & 3);
            ((TileEntityCoaxiumRefinery) te).setFacing(rot);
            ensureParts(w, x, y, z, rot);
        }
    }

    @Override
    public boolean onBlockActivated(World w, int x, int y, int z, EntityPlayer p, int side, float hx, float hy, float hz) {
        int meta = w.getBlockMetadata(x,y,z);
        if (meta == META_CORE) {
            if (w.isRemote) return false;
            p.openGui(OuterRim.instance, OuterRimResources.ConfigOptions.GUI_REFINERY, w, x, y, z);
            return true;
        }
        int[] c = findCoreAround(w, x, y, z);
        if (c != null) {
            Block b = w.getBlock(c[0], c[1], c[2]);
            return b != null && b.onBlockActivated(w, c[0], c[1], c[2], p, side, hx, hy, hz);
        }
        return false;
    }

    @Override
    public void onBlockPreDestroy(World w, int x, int y, int z, int meta) {
        if (meta == META_CORE) {
            removeParts(w, x, y, z);
        } else {
            if (!BREAKING_CORE) {
                int[] c = findCoreAround(w, x, y, z);
                if (c != null) {
                    try {
                        BREAKING_CORE = true;
                        w.func_147480_a(c[0], c[1], c[2], false);
                    } finally {
                        BREAKING_CORE = false;
                    }
                }
            }
        }
        super.onBlockPreDestroy(w, x, y, z, meta);
    }

    @Override
    public void breakBlock(World w, int x, int y, int z, Block b, int meta) {
        if (meta == META_CORE) removeParts(w, x, y, z);
        super.breakBlock(w, x, y, z, b, meta);
    }

    @Override
    public void setBlockBoundsBasedOnState(IBlockAccess world, int x, int y, int z) {
        int meta = world.getBlockMetadata(x, y, z);
        if (meta == META_CORE || meta == META_FULL) {
            setBlockBounds(0, 0, 0, 1, 1, 1);
        } else if (meta == META_HALF) {
            setBlockBounds(0, 0, 0, 1, 0.5f, 1);
        }
    }

    @Override @SuppressWarnings("rawtypes")
    public void addCollisionBoxesToList(World w, int x, int y, int z, AxisAlignedBB mask, List list, Entity e) {
        setBlockBoundsBasedOnState(w,x,y,z);
        super.addCollisionBoxesToList(w, x, y, z, mask, list, e);
        setBlockBounds(0,0,0,1,1,1);
    }

    @Override
    public AxisAlignedBB getSelectedBoundingBoxFromPool(World w, int x, int y, int z) {
        setBlockBoundsBasedOnState(w,x,y,z);
        AxisAlignedBB a = super.getSelectedBoundingBoxFromPool(w, x, y, z);
        setBlockBounds(0,0,0,1,1,1);
        return a;
    }

    @Override
    public void registerBlockIcons(IIconRegister reg) {
        blockIcon = reg.registerIcon("outerrim:icon_coaxium_refinery");
    }

    /* This is a map of the bounding box. The player places the block from the left side of the chart
    for y = 0
    X = Block, H = Half Block, M = center, . = space
             z = -1   z = 0    z = +1
        x=-2   X        X        X
        x=-1   H        H        H
        x= 0   .        M        .
        x=+1   H        H        H
        x=+2   X        X        X
     */

    private static final Part[] FOOTPRINT_SOUTH = new Part[] {
            new Part(-2, 0, 0, META_FULL),
            new Part(-2, 0, 1, META_FULL),
            new Part(-2, 1, 0, META_HALF),
            new Part(-2, 1, 1, META_HALF),

            new Part(-1, 0, 1, META_HALF),
            new Part(-1, 0, 0, META_HALF),

            new Part(0, 1, 0, META_HALF),

            new Part( 1, 0, 1, META_HALF),
            new Part(1, 0, 0, META_HALF),

            new Part( 2, 0, 0, META_FULL),
            new Part( 2, 0, 1, META_FULL),
            new Part(2, 1, 0, META_HALF),
            new Part(2, 1, 1, META_HALF),
    };

    private static class Part {
        final int dx, dy, dz, meta;
        Part(int dx, int dy, int dz, int meta) { this.dx = dx; this.dy = dy; this.dz = dz; this.meta = meta; }
    }

    private static Part rotate(Part p, int rot) {
        int dx = p.dx, dz = p.dz;
        for (int i = 0; i < rot; i++) { int ndx = -dz; int ndz = dx; dx = ndx; dz = ndz; }
        return new Part(dx, p.dy, dz, p.meta);
    }
    private static List<Part> footprintFor(int rot) {
        List<Part> out = new ArrayList<Part>(FOOTPRINT_SOUTH.length);
        for (Part p : FOOTPRINT_SOUTH) out.add(rotate(p, rot));
        return out;
    }

    private void ensureParts(World w, int x, int y, int z, int rot) {
        for (Part p : footprintFor(rot)) {
            int px = x + p.dx, py = y + p.dy, pz = z + p.dz;
            Block b = w.getBlock(px, py, pz);
            int m = w.getBlockMetadata(px, py, pz);
            if (b != this || m != p.meta) {
                placeOrReplace(w, px, py, pz, p.meta);
            }
        }
    }

    private void placeOrReplace(World w, int x, int y, int z, int meta) {
        Block existing = w.getBlock(x, y, z);
        if (w.isAirBlock(x, y, z) || existing.isReplaceable(w, x, y, z)) {
            w.setBlock(x, y, z, this, meta, 3);
        }
    }

    private void removeParts(World w, int x, int y, int z) {
        for (int dx = -3; dx <= 3; dx++) {
            for (int dy = -1; dy <= 1; dy++) {
                for (int dz = -3; dz <= 3; dz++) {
                    int px = x + dx, py = y + dy, pz = z + dz;
                    if (w.getBlock(px, py, pz) == this && w.getBlockMetadata(px, py, pz) != META_CORE) {
                        w.setBlockToAir(px, py, pz);
                    }
                }
            }
        }
    }

    private int[] findCoreAround(World w, int x, int y, int z) {
        for (int dx = -3; dx <= 3; dx++) {
            for (int dy = -1; dy <= 1; dy++) {
                for (int dz = -3; dz <= 3; dz++) {
                    int px = x + dx, py = y + dy, pz = z + dz;
                    if (w.getBlock(px, py, pz) == this && w.getBlockMetadata(px, py, pz) == META_CORE) {
                        return new int[]{px, py, pz};
                    }
                }
            }
        }
        return null;
    }
}

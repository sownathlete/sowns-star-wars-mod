package com.sown.outerrim.blocks;

import com.sown.outerrim.OuterRim;
import com.sown.outerrim.OuterRimResources;
import com.sown.outerrim.tileentities.TileEntityCoaxiumPump;
import com.sown.util.block.ORBlockContainer;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
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

import java.util.List;

public class BlockCoaxiumPump extends ORBlockContainer {

    // Metadata layout
    private static final int META_CORE = 0;   // has TileEntity + TESR
    private static final int META_PART = 1;   // solid, invisible, forwards clicks
    private static final int META_HEAD = 2;   // solid, invisible, forwards clicks (top 1x1x1)

    private static boolean BREAKING_CORE = false;

    public BlockCoaxiumPump(String name, Material material, float hardness, String toolType, int harvestLevel, Block.SoundType stepSound, boolean isMultiSided) {
        super(name, material);
        setCreativeTab(OuterRim.tabUtil);
        setHardness(hardness);
        setHarvestLevel(toolType, harvestLevel);
        setResistance(50f);
        setStepSound(stepSound);
        setBlockBounds(0f, 0f, 0f, 1f, 1f, 1f);
    }

    // TESR render-type
    @Override public int getRenderType()           { return -1; }
    @Override public boolean isOpaqueCube()        { return false; }
    @Override public boolean renderAsNormalBlock() { return false; }

    // --- TileEntity only exists on the CORE
    @Override
    public TileEntity createNewTileEntity(World w, int meta) {
        return (meta == META_CORE) ? new TileEntityCoaxiumPump() : null;
    }

    // Face the core based on player yaw
    @Override
    public void onBlockPlacedBy(World w, int x, int y, int z, EntityLivingBase pl, ItemStack st) {
        w.setBlockMetadataWithNotify(x, y, z, META_CORE, 2);
        TileEntity te = w.getTileEntity(x, y, z);
        if (te instanceof TileEntityCoaxiumPump) {
            int rot = MathHelper.floor_double(pl.rotationYaw * 4F / 360F + 0.5D) & 3;
            ((TileEntityCoaxiumPump) te).setFacing(rot);
            ensureParts(w, x, y, z); // fill out the 3?3?5 + head
        }
    }

    @Override
    public void onBlockAdded(World w, int x, int y, int z) {
        super.onBlockAdded(w, x, y, z);
        if (w.getBlockMetadata(x, y, z) == META_CORE) {
            ensureParts(w, x, y, z);
        }
    }

    // Open GUI from the core; parts forward to core
    @Override
    public boolean onBlockActivated(World w, int x, int y, int z, EntityPlayer p, int side, float hx, float hy, float hz) {
        int meta = w.getBlockMetadata(x, y, z);
        if (meta == META_CORE) {
            if (w.isRemote) return true;
            p.openGui(OuterRim.instance, OuterRimResources.ConfigOptions.GUI_COAXIUM_PUMP, w, x, y, z);
            return true;
        } else {
            int[] c = findCoreAround(w, x, y, z);
            if (c != null) {
                Block b = w.getBlock(c[0], c[1], c[2]);
                return b != null && b.onBlockActivated(w, c[0], c[1], c[2], p, side, hx, hy, hz);
            }
            return false;
        }
    }

    // Break behavior: breaking any part breaks the core (drop once)
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
                        w.func_147480_a(c[0], c[1], c[2], true); // drop from core once
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

    // ---- COLLISION & SELECTION ----
    // We DO NOT try to fake a giant AABB on the core anymore;
    // real solidity comes from the placed PART blocks filling the volume.

    @Override
    public void setBlockBoundsBasedOnState(IBlockAccess world, int x, int y, int z) {
        // every physical block is a full cube for collision/selection
        setBlockBounds(0f, 0f, 0f, 1f, 1f, 1f);
    }

    @Override @SuppressWarnings({"rawtypes","unchecked"})
    public void addCollisionBoxesToList(World w, int x, int y, int z, AxisAlignedBB mask, List list, Entity e) {
        setBlockBoundsBasedOnState(w, x, y, z);
        super.addCollisionBoxesToList(w, x, y, z, mask, list, e);
        setBlockBounds(0, 0, 0, 1, 1, 1);
    }

    @Override
    public AxisAlignedBB getSelectedBoundingBoxFromPool(World w, int x, int y, int z) {
        // Optional: show a large outline ONLY when looking at the core
        if (w.getBlockMetadata(x, y, z) == META_CORE) {
            return AxisAlignedBB.getBoundingBox(x - 1.0, y, z - 1.0, x + 2.0, y + 6.0, z + 2.0);
        }
        return super.getSelectedBoundingBoxFromPool(w, x, y, z);
    }

    // No icon ? item will render via IItemRenderer + TESR model
    @Override @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister r) { /* no icon */ }

    // ---- MULTIBLOCK SHAPE ----
    // 3x3 area centered on core, from y...y+4; plus a single head at y+5
    private void ensureParts(World w, int x, int y, int z) {
        // fill 3x3x5 (skip the core itself at dy=0, (0,0))
        for (int dy = 0; dy <= 4; dy++) {
            for (int dx = -1; dx <= 1; dx++) {
                for (int dz = -1; dz <= 1; dz++) {
                    int px = x + dx, py = y + dy, pz = z + dz;
                    if (dy == 0 && dx == 0 && dz == 0) continue; // core spot
                    placeOrReplace(w, px, py, pz, META_PART);
                }
            }
        }
        // head at y+5, center
        placeOrReplace(w, x, y + 5, z, META_HEAD);
    }

    private void placeOrReplace(World w, int x, int y, int z, int meta) {
        Block existing = w.getBlock(x, y, z);
        if (w.isAirBlock(x, y, z) || existing.isReplaceable(w, x, y, z) || existing == this) {
            w.setBlock(x, y, z, this, meta, 3);
        }
    }

    private void removeParts(World w, int x, int y, int z) {
        // remove any non-core piece in a reasonable radius; no drops for parts
        for (int dx = -2; dx <= 2; dx++)
            for (int dy = 0; dy <= 5; dy++)
                for (int dz = -2; dz <= 2; dz++) {
                    int px = x + dx, py = y + dy, pz = z + dz;
                    if (w.getBlock(px, py, pz) == this && w.getBlockMetadata(px, py, pz) != META_CORE) {
                        w.setBlockToAir(px, py, pz);
                    }
                }
    }

    private int[] findCoreAround(World w, int x, int y, int z) {
        for (int dx = -2; dx <= 2; dx++)
            for (int dy = -1; dy <= 6; dy++)
                for (int dz = -2; dz <= 2; dz++) {
                    int px = x + dx, py = y + dy, pz = z + dz;
                    if (w.getBlock(px, py, pz) == this && w.getBlockMetadata(px, py, pz) == META_CORE) {
                        return new int[]{px, py, pz};
                    }
                }
        return null;
    }
}

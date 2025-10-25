package com.sown.outerrim.blocks;

import com.sown.outerrim.OuterRim;
import com.sown.outerrim.OuterRimResources;
import com.sown.outerrim.tileentities.TileEntityCoaxiumPump;
import com.sown.outerrim.tileentities.TileEntityCoaxiumRefinery;
import com.sown.outerrim.utils.BoundingBoxTile;
import com.sown.outerrim.utils.BoundingComponent;
import com.sown.util.block.MultiblockUtil;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BlockCoaxiumRefinery extends ORBlockContainer {

    private static final int META_CORE = 0;
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
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase placer, ItemStack stack) {
        super.onBlockPlacedBy(world, x, y, z, placer, stack);
        world.setBlockMetadataWithNotify(x, y, z, META_CORE, 2);
        TileEntity te = world.getTileEntity(x, y, z);
        if (te instanceof TileEntityCoaxiumRefinery) {
            int rot = (placer != null) ? (MathHelper.floor_double(placer.rotationYaw * 4.0 / 360.0 + 0.5) & 3) : 0;
            if (MultiblockUtil.canPlaceParts(world, x, y, z, rot, FOOTPRINT_SOUTH)) {
                ((TileEntityCoaxiumRefinery) te).setFacing(rot);
                MultiblockUtil.ensureParts(world, x, y, z, rot, this, FOOTPRINT_SOUTH, META_MAP);
            } else {
                world.setBlockToAir(x, y, z);
                if (!world.isRemote && placer instanceof EntityPlayer player) {
                    if (player.capabilities.isCreativeMode) return;
                    ItemStack returned = new ItemStack(this);

                    if (!player.inventory.addItemStackToInventory(returned)) {
                        player.dropPlayerItemWithRandomChoice(returned, false);
                    }

                    player.inventoryContainer.detectAndSendChanges();
                }

            }
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
        int[] c = MultiblockUtil.findCoreAround(w, x, y, z);
        if (c != null) {
            Block b = w.getBlock(c[0], c[1], c[2]);
            return b != null && b.onBlockActivated(w, c[0], c[1], c[2], p, side, hx, hy, hz);
        }
        return false;
    }

    @Override
    public void onBlockPreDestroy(World world, int x, int y, int z, int meta) {
        if (BREAKING_CORE) return;
        TileEntity tileEntity = world.getTileEntity(x, y, z);
        TileEntityCoaxiumRefinery core = null;
        boolean breakCore = false;
        if (tileEntity instanceof TileEntityCoaxiumRefinery) {
            core = (TileEntityCoaxiumRefinery) tileEntity;
        } else if (tileEntity instanceof BoundingBoxTile boundingBoxTile) {
            core = (TileEntityCoaxiumRefinery) boundingBoxTile.getCore();
            breakCore = true;
        }

        if (core != null) {
            BREAKING_CORE = true;
            MultiblockUtil.removeParts(world, core.xCoord, core.yCoord, core.zCoord, core.getFacing(), breakCore, FOOTPRINT_SOUTH);
        }

        BREAKING_CORE = false;
        super.onBlockPreDestroy(world, x, y, z, meta);
    }

    @Override
    public void setBlockBoundsBasedOnState(IBlockAccess world, int x, int y, int z) {
        int meta = world.getBlockMetadata(x, y, z);

        BoundingComponent bounds = BOUNDS_MAP.get(meta);

        if (bounds != null) {
            this.setBlockBounds(
                bounds.minX, bounds.minY, bounds.minZ,
                bounds.maxX, bounds.maxY, bounds.maxZ
            );
        } else {
            // Fallback for an unknown metadata value
            this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
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
        double d = 0.0001;
        return AxisAlignedBB.getBoundingBox(
                x + 0.5 - d, y + 0.5 - d, z + 0.5 - d,
                x + 0.5 + d, y + 0.5 + d, z + 0.5 + d
        );
    }

    @Override
    public void registerBlockIcons(IIconRegister reg) {
        blockIcon = reg.registerIcon("outerrim:icon_coaxium_refinery");
    }

    /* This is a map of the bounding box. The player places the block from the left side of the chart
    for y = 0
    X = Block, H = Half Block, M = center, . = space
             z = -1   z = 0    z = +1
        x=-2   .        X        X
        x=-1   .        H        H
        x= 0   .        M        .
        x=+1   .        H        H
        x=+2   .        X        X
     */

    private static final BoundingComponent[] FOOTPRINT_SOUTH = new BoundingComponent[] {
            new BoundingComponent(-2, 0, 0),
            new BoundingComponent(-2, 0, 1),

            new BoundingComponent(-1, 0, 1, 0.5f),
            new BoundingComponent(-1, 0, 0, 0.5f),

            new BoundingComponent(0, 1, 0, 0.5f),

            new BoundingComponent( 1, 0, 1, 0.5f),
            new BoundingComponent(1, 0, 0, 0.5f),

            new BoundingComponent( 2, 0, 0),
            new BoundingComponent( 2, 0, 1),
    };

    private static final Map<Integer, BoundingComponent> BOUNDS_MAP = MultiblockUtil.createBoundsMap(FOOTPRINT_SOUTH);
    private static final Map<Integer, Integer> META_MAP = MultiblockUtil.createMetaMap(FOOTPRINT_SOUTH);
}

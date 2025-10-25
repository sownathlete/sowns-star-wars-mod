package com.sown.outerrim.blocks;

import com.sown.outerrim.OuterRim;
import com.sown.outerrim.OuterRimResources;
import com.sown.outerrim.tileentities.TileEntityMediumCoaxiumPump;
import com.sown.outerrim.utils.BoundingBoxTile;
import com.sown.outerrim.utils.BoundingComponent;
import com.sown.util.block.MultiblockUtil;
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
import java.util.Map;

public class BlockMediumCoaxiumPump extends ORBlockContainer {

	private static final int META_CORE = 0;
	private static boolean BREAKING_CORE = false;

	public BlockMediumCoaxiumPump(String name, Material material, float hardness, String toolType, int harvestLevel,
			Block.SoundType stepSound, boolean isMultiSided) {
		super(name, material);
		setCreativeTab(OuterRim.tabUtil);
		setHardness(hardness);
		setHarvestLevel(toolType, harvestLevel);
		setResistance(60f);
		setStepSound(stepSound);
		setBlockBounds(0f, 0f, 0f, 1f, 1f, 1f);
	}

	@Override
	public int getRenderType() {
		return -1;
	}

	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}

	@Override
	public TileEntity createNewTileEntity(World w, int meta) {
		return (meta == META_CORE) ? new TileEntityMediumCoaxiumPump() : null;
	}

	@Override
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase placer, ItemStack stack) {
		super.onBlockPlacedBy(world, x, y, z, placer, stack);
		world.setBlockMetadataWithNotify(x, y, z, META_CORE, 2);
		TileEntity te = world.getTileEntity(x, y, z);
		if (te instanceof TileEntityMediumCoaxiumPump) {
			int rot = (placer != null) ? (MathHelper.floor_double(placer.rotationYaw * 4.0 / 360.0 + 0.5) & 3) : 0;
			if (MultiblockUtil.canPlaceParts(world, x, y, z, rot, FOOTPRINT_SOUTH)) {
				((TileEntityMediumCoaxiumPump) te).setFacing(rot);
				MultiblockUtil.ensureParts(world, x, y, z, rot, this, FOOTPRINT_SOUTH, META_MAP);
			} else {
				world.setBlockToAir(x, y, z);
				if (!world.isRemote && placer instanceof EntityPlayer player) {
					if (player.capabilities.isCreativeMode)
						return;
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
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer p, int side, float hx, float hy,
			float hz) {
		if (world.isRemote)
			return true;
		int[] coreLoc = MultiblockUtil.findCoreAround(world, x, y, z);
		p.openGui(OuterRim.instance, OuterRimResources.ConfigOptions.GUI_COAXIUM_PUMP, world, coreLoc[0],
				coreLoc[1], coreLoc[2]);
		return true;
	}

	@Override
	public void onBlockPreDestroy(World world, int x, int y, int z, int meta) {
		if (BREAKING_CORE)
			return;
		TileEntity tileEntity = world.getTileEntity(x, y, z);
		TileEntityMediumCoaxiumPump core = null;
		boolean breakCore = false;

		if (tileEntity instanceof TileEntityMediumCoaxiumPump) {
			core = (TileEntityMediumCoaxiumPump) tileEntity;
		} else if (tileEntity instanceof BoundingBoxTile boundingBoxTile) {
			if (boundingBoxTile.getCore() instanceof TileEntityMediumCoaxiumPump)
				core = (TileEntityMediumCoaxiumPump) boundingBoxTile.getCore();
			breakCore = true;
		}

		if (core != null) {
			BREAKING_CORE = true;
			MultiblockUtil.removeParts(world, core.xCoord, core.yCoord, core.zCoord, core.getFacing(), breakCore,
					FOOTPRINT_SOUTH);
		}

		BREAKING_CORE = false;
		super.onBlockPreDestroy(world, x, y, z, meta);
	}

	@Override
	public void setBlockBoundsBasedOnState(IBlockAccess world, int x, int y, int z) {
		int meta = world.getBlockMetadata(x, y, z);
		BoundingComponent bounds = BOUNDS_MAP.get(meta);
		if (bounds != null) {
			this.setBlockBounds(bounds.minX, bounds.minY, bounds.minZ, bounds.maxX, bounds.maxY, bounds.maxZ);
		} else {
			this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
		}
	}

	@Override
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void addCollisionBoxesToList(World w, int x, int y, int z, AxisAlignedBB mask, List list, Entity e) {
		setBlockBoundsBasedOnState(w, x, y, z);
		super.addCollisionBoxesToList(w, x, y, z, mask, list, e);
		setBlockBounds(0, 0, 0, 1, 1, 1);
	}

	@Override
	public AxisAlignedBB getSelectedBoundingBoxFromPool(World w, int x, int y, int z) {
		if (w.getBlockMetadata(x, y, z) == META_CORE) {
			return AxisAlignedBB.getBoundingBox(x - 1.0, y, z - 1.0, x + 2.0, y + 6.0, z + 2.0);
		}
		return super.getSelectedBoundingBoxFromPool(w, x, y, z);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister r) {
		}

	private static final BoundingComponent[] FOOTPRINT = { new BoundingComponent(0, 5, 0) };
	private static final BoundingComponent[] FOOTPRINT_SOUTH = MultiblockUtil.createCubicFootprint(FOOTPRINT, 4, 6, 4,
			1, 0, 1);

	private static final Map<Integer, BoundingComponent> BOUNDS_MAP = MultiblockUtil.createBoundsMap(FOOTPRINT_SOUTH);
	private static final Map<Integer, Integer> META_MAP = MultiblockUtil.createMetaMap(FOOTPRINT_SOUTH);
}

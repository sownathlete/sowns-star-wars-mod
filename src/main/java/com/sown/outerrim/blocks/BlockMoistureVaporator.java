package com.sown.outerrim.blocks;

import java.util.List;

import com.sown.outerrim.OuterRim;
import com.sown.outerrim.OuterRimResources;
import com.sown.outerrim.tileentities.TileEntityMoistureVaporator;
import com.sown.util.block.IDebugProvider;
import com.sown.util.block.ORBlockContainer;
import com.sown.util.ui.LangUtils;

import net.minecraft.block.Block;
import net.minecraft.block.Block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockMoistureVaporator extends ORBlockContainer implements IDebugProvider {

    public BlockMoistureVaporator(String name, Material material, float hardness, String toolType, int harvestLevel, SoundType stepSound, boolean isMultiSided) {
        super("moistureVaporator", Material.iron);
        this.setCreativeTab(OuterRim.tabUtil);
        this.setBlockBounds(0.0f, 0.0f, 0.0f, 1.0f, 2.0f, 1.0f);
        this.setHardness(50.0f);
        this.setHarvestLevel("pickaxe", 2);
        this.setStepSound(stepSound);
    }

    @Override
    public TileEntity createNewTileEntity(World world, int meta) {
        return new TileEntityMoistureVaporator();
    }

    @Override
    public void breakBlock(World world, int x, int y, int z, Block block, int wut) {
        TileEntityMoistureVaporator moistureVap = (TileEntityMoistureVaporator) world.getTileEntity(x, y, z);
        if (moistureVap != null) {
            ItemStack itemstack = moistureVap.getStackInSlot(0);
            if (itemstack != null) {
                EntityItem entityitem = new EntityItem(world, x, y, z, itemstack);
                if (itemstack.hasTagCompound()) {
                    entityitem.getEntityItem().setTagCompound((NBTTagCompound) itemstack.getTagCompound().copy());
                }
                world.spawnEntityInWorld(entityitem);
            }
            world.func_147453_f(x, y, z, block);
        }
        super.breakBlock(world, x, y, z, block, wut);
    }

    @Override
    public List<String> getDebugText(List<String> list, EntityPlayer player, World world, int x, int y, int z) {
        TileEntity tile = world.getTileEntity(x, y, z);
        if (tile instanceof TileEntityMoistureVaporator) {
            TileEntityMoistureVaporator vap = (TileEntityMoistureVaporator) tile;
            float l = (float) vap.progressTicks / (float) vap.totalTicks;
            list.add((int) (l * 100.0f) + "%");
            if (vap.getStackInSlot(0) != null) {
                list.add(LangUtils.translateKeyFormat("0.droplets", vap.getStackInSlot(0).stackSize));
            }
        }
        return list;
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
    public boolean isBlockNormalCube() {
        return false;
    }

    @Override
    public boolean renderAsNormalBlock() {
        return false;
    }

    @Override
    public void registerBlockIcons(IIconRegister icon) {
        this.blockIcon = icon.registerIcon("outerrim:iconMoistureVaporator");
    }

    @Override
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z) {
        return AxisAlignedBB.getBoundingBox(x, y, z, x + 1, y + 2, z + 1);
    }

    @Override
    public void setBlockBoundsBasedOnState(IBlockAccess block, int x, int y, int z) {
        this.setBlockBounds(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f);
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int metadata, float e, float f, float g) {
        if (!world.isRemote) {
            player.openGui(OuterRim.instance, OuterRimResources.ConfigOptions.GUI_VAPORATOR, world, x, y, z);
        }
        return true;
    }

    @Override
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase player, ItemStack item) {
        TileEntity tile = world.getTileEntity(x, y, z);
        if (tile instanceof TileEntityMoistureVaporator vap) {
            int l = MathHelper.floor_double(player.rotationYaw * 8.0f / 360.0f + 0.5) & 3;
            vap.setFacing(l);
        }
    }
}

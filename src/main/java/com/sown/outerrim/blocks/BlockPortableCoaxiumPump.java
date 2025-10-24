package com.sown.outerrim.blocks;

import com.sown.outerrim.OuterRim;
import com.sown.outerrim.OuterRimResources;
import com.sown.outerrim.tileentities.TileEntityPortableCoaxiumPump;
import com.sown.util.block.ORBlockContainer;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class BlockPortableCoaxiumPump extends ORBlockContainer {

    public BlockPortableCoaxiumPump(String name, Material material, float hardness, String toolType, int harvestLevel, Block.SoundType stepSound, boolean isMultiSided) {
        super(name, material);
        setCreativeTab(OuterRim.tabUtil);
        setHardness(hardness);
        setHarvestLevel(toolType, harvestLevel);
        setResistance(50f);
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
    public TileEntity createNewTileEntity(World world, int meta) {
        return new TileEntityPortableCoaxiumPump();
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hx, float hy, float hz) {
        if (world.isRemote) return true;
        player.openGui(OuterRim.instance, OuterRimResources.ConfigOptions.GUI_PORTABLE_PUMP, world, x, y, z);
        return true;
    }

    @Override
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase placer, ItemStack stack) {
        super.onBlockPlacedBy(world, x, y, z, placer, stack);
        TileEntity te = world.getTileEntity(x, y, z);
        if (te instanceof TileEntityPortableCoaxiumPump) {
            int rot = MathHelper.floor_double(placer.rotationYaw * 4.0F / 360.0F + 0.5D) & 3;
            ((TileEntityPortableCoaxiumPump) te).setFacing(rot);
        }
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void registerBlockIcons(IIconRegister reg) {
        blockIcon = reg.registerIcon("outerrim:portable_pump_icon");
    }
}

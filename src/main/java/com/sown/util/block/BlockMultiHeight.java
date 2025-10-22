/*
 * Decompiled with CFR 0.148.
 *
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.block.material.Material
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.IIcon
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 */
package com.sown.util.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockMultiHeight
extends ORBlock {
    private String texture;

    public BlockMultiHeight(String texture) {
        super("multiHeight." + texture, Material.rock);
        this.setBlockBounds(0.0f, 0.0f, 0.0f, 1.0f, 0.125f, 1.0f);
        //this.setCreativeTab(OuterRim.StarWarsTabBlocks);
        this.setBlockBoundsFromHeight(0);
        this.texture = texture;
    }

    @SideOnly(value=Side.CLIENT)
    public void func_149651_a(IIconRegister register) {
        this.blockIcon = register.registerIcon("outerrim:" + this.texture);
    }

    public AxisAlignedBB func_149668_a(World world, int x, int y, int z) {
        int l = (world.getBlockMetadata(x, y, z) & 0xF) + 1;
        float f = 0.0625f;
        return AxisAlignedBB.getBoundingBox(x + this.minX, y + this.minY, z + this.minZ, x + this.maxX, y + l * f, z + this.maxZ);
    }

    public boolean func_149662_c() {
        return false;
    }

    public boolean func_149686_d() {
        return false;
    }

    public void func_149683_g() {
        this.setBlockBoundsFromHeight(0);
    }

    public void func_149719_a(IBlockAccess blockAccess, int x, int y, int z) {
        this.setBlockBoundsFromHeight(blockAccess.getBlockMetadata(x, y, z));
    }

    private void setBlockBoundsFromHeight(int height) {
        int j = height & 0xF;
        float f = (1 + j) / 16.0f;
        this.setBlockBounds(0.0f, 0.0f, 0.0f, 1.0f, f, 1.0f);
    }

    public void func_149636_a(World world, EntityPlayer player, int x, int y, int z, int meta) {
        super.harvestBlock(world, player, x, y, z, meta);
        world.setBlockToAir(x, y, z);
    }

    @SideOnly(value=Side.CLIENT)
    public boolean func_149646_a(IBlockAccess blockAccess, int x, int y, int z, int side) {
        return side == 1 || super.shouldSideBeRendered(blockAccess, x, y, z, side);
    }

    @Override
    public boolean isReplaceable(IBlockAccess world, int x, int y, int z) {
        return false;
    }
}


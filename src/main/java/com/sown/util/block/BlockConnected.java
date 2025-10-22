/*
 * Decompiled with CFR 0.148.
 *
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.block.material.Material
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.util.IIcon
 *  net.minecraft.world.IBlockAccess
 */
package com.sown.util.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;

public class BlockConnected
extends ORBlock {
    protected IIcon[] icons = new IIcon[16];
    private String folder;
    private String texture;

    public BlockConnected(String name, Material mat) {
        this(name, name, name, mat);
    }

    public BlockConnected(String unlocalName, String name, Material mat) {
        this(unlocalName, name, name, mat);
    }

    public BlockConnected(String unlocalName, String folder, String name, Material mat) {
        super(unlocalName, mat);
        this.texture = name;
        this.folder = folder;
    }

    public IIcon getConnectedBlockTexture(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5, IIcon[] icons) {
        boolean isOpenUp = false;
        boolean isOpenDown = false;
        boolean isOpenLeft = false;
        boolean isOpenRight = false;
        switch (par5) {
            case 0:
            case 1: {
                if (this.shouldConnectToBlock(par1IBlockAccess, par2, par3, par4, par1IBlockAccess.getBlock(par2 - 1, par3, par4), par1IBlockAccess.getBlockMetadata(par2 - 1, par3, par4))) {
                    isOpenDown = true;
                }
                if (this.shouldConnectToBlock(par1IBlockAccess, par2, par3, par4, par1IBlockAccess.getBlock(par2 + 1, par3, par4), par1IBlockAccess.getBlockMetadata(par2 + 1, par3, par4))) {
                    isOpenUp = true;
                }
                if (this.shouldConnectToBlock(par1IBlockAccess, par2, par3, par4, par1IBlockAccess.getBlock(par2, par3, par4 - 1), par1IBlockAccess.getBlockMetadata(par2, par3, par4 - 1))) {
                    isOpenLeft = true;
                }
                if (this.shouldConnectToBlock(par1IBlockAccess, par2, par3, par4, par1IBlockAccess.getBlock(par2, par3, par4 + 1), par1IBlockAccess.getBlockMetadata(par2, par3, par4 + 1))) {
                    isOpenRight = true;
                }
                if (isOpenUp && isOpenDown && isOpenLeft && isOpenRight)
                    return icons[15];
                if (isOpenUp && isOpenDown && isOpenLeft)
                    return icons[11];
                if (isOpenUp && isOpenDown && isOpenRight)
                    return icons[12];
                if (isOpenUp && isOpenLeft && isOpenRight)
                    return icons[13];
                if (isOpenDown && isOpenLeft && isOpenRight)
                    return icons[14];
                if (isOpenDown && isOpenUp)
                    return icons[5];
                if (isOpenLeft && isOpenRight)
                    return icons[6];
                if (isOpenDown && isOpenLeft)
                    return icons[8];
                if (isOpenDown && isOpenRight)
                    return icons[10];
                if (isOpenUp && isOpenLeft)
                    return icons[7];
                if (isOpenUp && isOpenRight)
                    return icons[9];
                if (isOpenDown)
                    return icons[3];
                if (isOpenUp)
                    return icons[4];
                if (isOpenLeft)
                    return icons[2];
                if (!isOpenRight) {
                    break;
                }
                return icons[1];
            }
            case 2: {
                if (this.shouldConnectToBlock(par1IBlockAccess, par2, par3, par4, par1IBlockAccess.getBlock(par2, par3 - 1, par4), par1IBlockAccess.getBlockMetadata(par2, par3 - 1, par4))) {
                    isOpenDown = true;
                }
                if (this.shouldConnectToBlock(par1IBlockAccess, par2, par3, par4, par1IBlockAccess.getBlock(par2, par3 + 1, par4), par1IBlockAccess.getBlockMetadata(par2, par3 + 1, par4))) {
                    isOpenUp = true;
                }
                if (this.shouldConnectToBlock(par1IBlockAccess, par2, par3, par4, par1IBlockAccess.getBlock(par2 - 1, par3, par4), par1IBlockAccess.getBlockMetadata(par2 - 1, par3, par4))) {
                    isOpenLeft = true;
                }
                if (this.shouldConnectToBlock(par1IBlockAccess, par2, par3, par4, par1IBlockAccess.getBlock(par2 + 1, par3, par4), par1IBlockAccess.getBlockMetadata(par2 + 1, par3, par4))) {
                    isOpenRight = true;
                }
                if (isOpenUp && isOpenDown && isOpenLeft && isOpenRight)
                    return icons[15];
                if (isOpenUp && isOpenDown && isOpenLeft)
                    return icons[13];
                if (isOpenUp && isOpenDown && isOpenRight)
                    return icons[14];
                if (isOpenUp && isOpenLeft && isOpenRight)
                    return icons[11];
                if (isOpenDown && isOpenLeft && isOpenRight)
                    return icons[12];
                if (isOpenDown && isOpenUp)
                    return icons[6];
                if (isOpenLeft && isOpenRight)
                    return icons[5];
                if (isOpenDown && isOpenLeft)
                    return icons[9];
                if (isOpenDown && isOpenRight)
                    return icons[10];
                if (isOpenUp && isOpenLeft)
                    return icons[7];
                if (isOpenUp && isOpenRight)
                    return icons[8];
                if (isOpenDown)
                    return icons[1];
                if (isOpenUp)
                    return icons[2];
                if (isOpenLeft)
                    return icons[4];
                if (!isOpenRight) {
                    break;
                }
                return icons[3];
            }
            case 3: {
                if (this.shouldConnectToBlock(par1IBlockAccess, par2, par3, par4, par1IBlockAccess.getBlock(par2, par3 - 1, par4), par1IBlockAccess.getBlockMetadata(par2, par3 - 1, par4))) {
                    isOpenDown = true;
                }
                if (this.shouldConnectToBlock(par1IBlockAccess, par2, par3, par4, par1IBlockAccess.getBlock(par2, par3 + 1, par4), par1IBlockAccess.getBlockMetadata(par2, par3 + 1, par4))) {
                    isOpenUp = true;
                }
                if (this.shouldConnectToBlock(par1IBlockAccess, par2, par3, par4, par1IBlockAccess.getBlock(par2 - 1, par3, par4), par1IBlockAccess.getBlockMetadata(par2 - 1, par3, par4))) {
                    isOpenLeft = true;
                }
                if (this.shouldConnectToBlock(par1IBlockAccess, par2, par3, par4, par1IBlockAccess.getBlock(par2 + 1, par3, par4), par1IBlockAccess.getBlockMetadata(par2 + 1, par3, par4))) {
                    isOpenRight = true;
                }
                if (isOpenUp && isOpenDown && isOpenLeft && isOpenRight)
                    return icons[15];
                if (isOpenUp && isOpenDown && isOpenLeft)
                    return icons[14];
                if (isOpenUp && isOpenDown && isOpenRight)
                    return icons[13];
                if (isOpenUp && isOpenLeft && isOpenRight)
                    return icons[11];
                if (isOpenDown && isOpenLeft && isOpenRight)
                    return icons[12];
                if (isOpenDown && isOpenUp)
                    return icons[6];
                if (isOpenLeft && isOpenRight)
                    return icons[5];
                if (isOpenDown && isOpenLeft)
                    return icons[10];
                if (isOpenDown && isOpenRight)
                    return icons[9];
                if (isOpenUp && isOpenLeft)
                    return icons[8];
                if (isOpenUp && isOpenRight)
                    return icons[7];
                if (isOpenDown)
                    return icons[1];
                if (isOpenUp)
                    return icons[2];
                if (isOpenLeft)
                    return icons[3];
                if (!isOpenRight) {
                    break;
                }
                return icons[4];
            }
            case 4: {
                if (this.shouldConnectToBlock(par1IBlockAccess, par2, par3, par4, par1IBlockAccess.getBlock(par2, par3 - 1, par4), par1IBlockAccess.getBlockMetadata(par2, par3 - 1, par4))) {
                    isOpenDown = true;
                }
                if (this.shouldConnectToBlock(par1IBlockAccess, par2, par3, par4, par1IBlockAccess.getBlock(par2, par3 + 1, par4), par1IBlockAccess.getBlockMetadata(par2, par3 + 1, par4))) {
                    isOpenUp = true;
                }
                if (this.shouldConnectToBlock(par1IBlockAccess, par2, par3, par4, par1IBlockAccess.getBlock(par2, par3, par4 - 1), par1IBlockAccess.getBlockMetadata(par2, par3, par4 - 1))) {
                    isOpenLeft = true;
                }
                if (this.shouldConnectToBlock(par1IBlockAccess, par2, par3, par4, par1IBlockAccess.getBlock(par2, par3, par4 + 1), par1IBlockAccess.getBlockMetadata(par2, par3, par4 + 1))) {
                    isOpenRight = true;
                }
                if (isOpenUp && isOpenDown && isOpenLeft && isOpenRight)
                    return icons[15];
                if (isOpenUp && isOpenDown && isOpenLeft)
                    return icons[14];
                if (isOpenUp && isOpenDown && isOpenRight)
                    return icons[13];
                if (isOpenUp && isOpenLeft && isOpenRight)
                    return icons[11];
                if (isOpenDown && isOpenLeft && isOpenRight)
                    return icons[12];
                if (isOpenDown && isOpenUp)
                    return icons[6];
                if (isOpenLeft && isOpenRight)
                    return icons[5];
                if (isOpenDown && isOpenLeft)
                    return icons[10];
                if (isOpenDown && isOpenRight)
                    return icons[9];
                if (isOpenUp && isOpenLeft)
                    return icons[8];
                if (isOpenUp && isOpenRight)
                    return icons[7];
                if (isOpenDown)
                    return icons[1];
                if (isOpenUp)
                    return icons[2];
                if (isOpenLeft)
                    return icons[3];
                if (!isOpenRight) {
                    break;
                }
                return icons[4];
            }
            case 5: {
                if (this.shouldConnectToBlock(par1IBlockAccess, par2, par3, par4, par1IBlockAccess.getBlock(par2, par3 - 1, par4), par1IBlockAccess.getBlockMetadata(par2, par3 - 1, par4))) {
                    isOpenDown = true;
                }
                if (this.shouldConnectToBlock(par1IBlockAccess, par2, par3, par4, par1IBlockAccess.getBlock(par2, par3 + 1, par4), par1IBlockAccess.getBlockMetadata(par2, par3 + 1, par4))) {
                    isOpenUp = true;
                }
                if (this.shouldConnectToBlock(par1IBlockAccess, par2, par3, par4, par1IBlockAccess.getBlock(par2, par3, par4 - 1), par1IBlockAccess.getBlockMetadata(par2, par3, par4 - 1))) {
                    isOpenLeft = true;
                }
                if (this.shouldConnectToBlock(par1IBlockAccess, par2, par3, par4, par1IBlockAccess.getBlock(par2, par3, par4 + 1), par1IBlockAccess.getBlockMetadata(par2, par3, par4 + 1))) {
                    isOpenRight = true;
                }
                if (isOpenUp && isOpenDown && isOpenLeft && isOpenRight)
                    return icons[15];
                if (isOpenUp && isOpenDown && isOpenLeft)
                    return icons[13];
                if (isOpenUp && isOpenDown && isOpenRight)
                    return icons[14];
                if (isOpenUp && isOpenLeft && isOpenRight)
                    return icons[11];
                if (isOpenDown && isOpenLeft && isOpenRight)
                    return icons[12];
                if (isOpenDown && isOpenUp)
                    return icons[6];
                if (isOpenLeft && isOpenRight)
                    return icons[5];
                if (isOpenDown && isOpenLeft)
                    return icons[9];
                if (isOpenDown && isOpenRight)
                    return icons[10];
                if (isOpenUp && isOpenLeft)
                    return icons[7];
                if (isOpenUp && isOpenRight)
                    return icons[8];
                if (isOpenDown)
                    return icons[1];
                if (isOpenUp)
                    return icons[2];
                if (isOpenLeft)
                    return icons[4];
                if (!isOpenRight) {
                    break;
                }
                return icons[3];
            }
        }
        return icons[0];
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon func_149673_e(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5) {
        return par1IBlockAccess.getBlockMetadata(par2, par3, par4) == 15 ? this.icons[0] : this.getConnectedBlockTexture(par1IBlockAccess, par2, par3, par4, par5, this.icons);
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon func_149691_a(int par1, int par2) {
        return this.icons[0];
    }

    public void func_149651_a(IIconRegister par1IconRegister) {
        this.icons[0] = par1IconRegister.registerIcon("outerrim:connected/" + this.folder + "/" + this.texture);
        this.icons[1] = par1IconRegister.registerIcon("outerrim:connected/" + this.folder + "/" + this.texture + "_1_d");
        this.icons[2] = par1IconRegister.registerIcon("outerrim:connected/" + this.folder + "/" + this.texture + "_1_u");
        this.icons[3] = par1IconRegister.registerIcon("outerrim:connected/" + this.folder + "/" + this.texture + "_1_l");
        this.icons[4] = par1IconRegister.registerIcon("outerrim:connected/" + this.folder + "/" + this.texture + "_1_r");
        this.icons[5] = par1IconRegister.registerIcon("outerrim:connected/" + this.folder + "/" + this.texture + "_2_h");
        this.icons[6] = par1IconRegister.registerIcon("outerrim:connected/" + this.folder + "/" + this.texture + "_2_v");
        this.icons[7] = par1IconRegister.registerIcon("outerrim:connected/" + this.folder + "/" + this.texture + "_2_dl");
        this.icons[8] = par1IconRegister.registerIcon("outerrim:connected/" + this.folder + "/" + this.texture + "_2_dr");
        this.icons[9] = par1IconRegister.registerIcon("outerrim:connected/" + this.folder + "/" + this.texture + "_2_ul");
        this.icons[10] = par1IconRegister.registerIcon("outerrim:connected/" + this.folder + "/" + this.texture + "_2_ur");
        this.icons[11] = par1IconRegister.registerIcon("outerrim:connected/" + this.folder + "/" + this.texture + "_3_d");
        this.icons[12] = par1IconRegister.registerIcon("outerrim:connected/" + this.folder + "/" + this.texture + "_3_u");
        this.icons[13] = par1IconRegister.registerIcon("outerrim:connected/" + this.folder + "/" + this.texture + "_3_l");
        this.icons[14] = par1IconRegister.registerIcon("outerrim:connected/" + this.folder + "/" + this.texture + "_3_r");
        this.icons[15] = par1IconRegister.registerIcon("outerrim:connected/" + this.folder + "/" + this.texture + "_4");
    }

    private boolean shouldConnectToBlock(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, Block par5, int par6) {
        return par5 == this;
    }

    public boolean func_149646_a(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5) {
        Block b = par1IBlockAccess.getBlock(par2, par3, par4);
        return b != this && super.shouldSideBeRendered  (par1IBlockAccess, par2, par3, par4, par5);
    }
}


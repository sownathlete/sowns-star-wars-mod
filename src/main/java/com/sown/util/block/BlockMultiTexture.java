/*
 * Decompiled with CFR 0.148.
 *
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.material.Material
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.IIcon
 */
package com.sown.util.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

public class BlockMultiTexture
extends ORBlock {
    private String[] names;
    private final boolean newStyleName;
    private int subtypes;
    @SideOnly(value=Side.CLIENT)
    private IIcon[] icons;

    public BlockMultiTexture(String base, String[] postfixes, Material material) {
        this(base, postfixes, material, false);
    }

    public BlockMultiTexture(String base, String[] postfixes, Material material, boolean newStyleName) {
        super(base, material);
        this.names = postfixes;
        this.newStyleName = newStyleName;
        this.subtypes = this.names.length;
    }

    public int func_149692_a(int meta) {
        return meta;
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon func_149691_a(int side, int meta) {
        return this.icons[meta];
    }

    @SideOnly(value=Side.CLIENT)
    public void func_149666_a(Item item, CreativeTabs tab, List metaTypes) {
        for (int i = 0; i < this.subtypes; ++i) {
            metaTypes.add(new ItemStack(item, 1, i));
        }
    }

    @SideOnly(value=Side.CLIENT)
    public void func_149651_a(IIconRegister par1IconRegister) {
        this.icons = new IIcon[this.subtypes];
        for (int i = 0; i < this.subtypes; ++i) {
            this.icons[i] = this.newStyleName ? par1IconRegister.registerIcon("outerrim:" + this.name + '/' + this.names[i]) : par1IconRegister.registerIcon("outerrim:" + this.name + this.names[i]);
        }
    }
}


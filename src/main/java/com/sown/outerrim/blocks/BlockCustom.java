package com.sown.outerrim.blocks;

import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.util.IIcon;

public class BlockCustom extends Block {
    protected String name;
    protected Block blockDropped;
    protected Item itemDropped;
    protected IIcon[] icons;

    public BlockCustom(
    	    String name,
    	    Material material,
    	    float hardness,
    	    String harvestTool,
    	    int harvestLevel,
    	    Block.SoundType stepSound
    	) {
    	    super(material);
    	    this.name = name;
    	    this.icons = new IIcon[1];
    	    this.setBlockName(name);
    	    this.setHardness(hardness);
    	    this.setHarvestLevel(harvestTool, harvestLevel);
    	    this.setStepSound(stepSound);
    	}

    public Block getBlockDropped(int metadata, Random rand, int fortune) {
        return this.blockDropped;
    }

    @Override
    public Item getItemDropped(int metadata, Random rand, int fortune) {
        return this.itemDropped;
    }

    @Override
    public int damageDropped(int meta) {
        return meta;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int meta) {
        return this.icons[0];
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister par1IconRegister) {
        this.icons[0] = par1IconRegister.registerIcon("outerrim:" + this.name);
    }
}
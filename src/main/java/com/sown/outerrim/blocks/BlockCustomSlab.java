package com.sown.outerrim.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import java.util.Random;

import com.sown.outerrim.OuterRim;
import com.sown.outerrim.registry.BlockRegister;

import net.minecraft.block.Block;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class BlockCustomSlab extends BlockSlab {
    private final boolean isDouble;
    private final Block mainBlock;
    private final Item singleSlabItem;

    public BlockCustomSlab(boolean isDouble, Block mainBlock, Item singleSlabItem) {
        super(isDouble, mainBlock.getMaterial());
        this.isDouble = isDouble;
        this.mainBlock = mainBlock;
        this.singleSlabItem = singleSlabItem;

        String name = mainBlock.getUnlocalizedName().substring(5);
        setBlockName(name + "_slab");
        setHardness(mainBlock.getBlockHardness(null, 0, 0, 0));
        setStepSound(mainBlock.stepSound);

        if (!isDouble) {
            this.setCreativeTab(OuterRim.tabBlock);
        }
    }

    @Override
    public int quantityDropped(Random random) {
        return this.isDouble ? 1 : 1;
    }

    @Override
    public Item getItemDropped(int metadata, Random rand, int fortune) {
        Item item;
        if (this.isDouble) {
            item = Item.getItemFromBlock(mainBlock);
        } else {
        	return Item.getItemFromBlock(this);
        }
        System.out.println("Dropping item: " + item);
        return item;
    }
    
    @Override
    public IIcon getIcon(int side, int meta) {
        return mainBlock.getIcon(side, meta);
    }

    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconRegister) {
    }

    @Override
    public String func_150002_b(int metadata) {
        return this.getUnlocalizedName();
    }

    public String getUnlocalizedName(int id) {
        return this.getUnlocalizedName();
    }

    protected ItemStack createStackedBlock(int id) {
        return new ItemStack(this, 1, id);
    }

    @SideOnly(Side.CLIENT)
    private static boolean func_150003_a(Block block) {
        return block instanceof BlockCustomSlab;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public Item getItem(World world, int x, int y, int z) {
        if (this.isDouble) {
            return Item.getItemFromBlock(mainBlock);
        } else {
        	return Item.getItemFromBlock(this);
        }
    }
}
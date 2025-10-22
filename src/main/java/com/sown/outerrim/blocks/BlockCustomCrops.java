package com.sown.outerrim.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockCrops;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class BlockCustomCrops extends BlockCrops {
    private Item customSeed;
    private Item customFood;
    private static final int NUMBER_OF_STAGES = 8;
    private IIcon[] icons;
    private String name;

    public BlockCustomCrops(String name) {
        super();
        this.name = name;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister reg) {
        this.icons = new IIcon[NUMBER_OF_STAGES];
        for (int i = 0; i < this.icons.length; ++i) {
            this.icons[i] = reg.registerIcon("outerrim:" + name + "_stage_" + i);
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int meta) {
        if (meta < 0 || meta >= this.icons.length) {
            meta = this.icons.length - 1;
        }

        return this.icons[meta];
    }
    
    public void setCustomDrops(Item customSeed, Item customFood) {
        this.customSeed = customSeed;
        this.customFood = customFood;
    }
    
    @Override
    public Item getItem(World world, int x, int y, int z) {
        return customSeed;
    }

    @Override
    public void dropBlockAsItemWithChance(World world, int x, int y, int z, int metadata, float chance, int fortune) {
        if (!world.isRemote && world.getGameRules().getGameRuleBooleanValue("doTileDrops")) {
            if (metadata >= 7) {
                int count = 3 + fortune;
                for (int i = 0; i < count; i++) {
                    if (world.rand.nextInt(15) <= metadata) {
                        dropBlockAsItem(world, x, y, z, new ItemStack(customSeed, 1, 0));
                    }
                }
                dropBlockAsItem(world, x, y, z, new ItemStack(customFood, 1, 0));
            }
        }
    }
    
}
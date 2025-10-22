package com.sown.outerrim.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockLeaves;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Random;

import com.sown.outerrim.OuterRim;

public class BlockCustomLeaves extends BlockLeaves {
    private IIcon leavesIcon;
    private IIcon opaqueIcon;

    public BlockCustomLeaves(String name, float hardness, String harvestTool, int harvestLevel, Block.SoundType stepSound) {
        super();
        setBlockName(name);
        setBlockTextureName("outerrim:" + name);
        setHardness(hardness);
        setHarvestLevel(harvestTool, harvestLevel);
        setStepSound(stepSound);
        setLightOpacity(1);
        setCreativeTab(OuterRim.tabDeco);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void registerBlockIcons(IIconRegister iconRegister) {
        this.leavesIcon = iconRegister.registerIcon("outerrim:" + getUnlocalizedName().substring(5));
        this.opaqueIcon = iconRegister.registerIcon("outerrim:" + getUnlocalizedName().substring(5) + "_opaque");
    }

    @SideOnly(Side.CLIENT)
    @Override
    public IIcon getIcon(int side, int meta) {
        return !this.field_150121_P ? this.leavesIcon : this.opaqueIcon;
    }

    @Override
    public Item getItemDropped(int meta, Random random, int fortune) {
        return Item.getItemFromBlock(Blocks.sapling);
    }

    @Override
    public int quantityDropped(Random random) {
        return random.nextInt(20) == 0 ? 1 : 0;
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
    public boolean shouldSideBeRendered(IBlockAccess blockAccess, int x, int y, int z, int side) {
        return true;
    }

    @Override
    public String[] func_150125_e() {
        return new String[]{"customType"};
    }
}

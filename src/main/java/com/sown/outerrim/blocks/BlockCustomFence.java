package com.sown.outerrim.blocks;

import com.sown.outerrim.OuterRim;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFence;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

import java.util.List;

public class BlockCustomFence extends BlockFence {
    private final Block mainBlock;

    public BlockCustomFence(Block mainBlock) {
        super(mainBlock.getUnlocalizedName().substring(5) + "_fence", mainBlock.getMaterial());

        this.mainBlock = mainBlock;
        setBlockName(mainBlock.getUnlocalizedName().substring(5) + "_fence");
        setBlockTextureName("outerrim:" + mainBlock.getUnlocalizedName().substring(5));
        setHardness(mainBlock.getBlockHardness(null, 0, 0, 0));
        setResistance(mainBlock.getExplosionResistance(null));
        setStepSound(mainBlock.stepSound);
        this.setCreativeTab(OuterRim.tabBlock);
    }

    @Override
    public IIcon getIcon(int side, int meta) {
        return mainBlock.getIcon(side, meta);
    }

    @SuppressWarnings("unchecked")
    @Override
    public void getSubBlocks(Item item, CreativeTabs tab, List list) {
        if (Item.getItemFromBlock(this) == item) {
            list.add(new ItemStack(item));
        }
    }
}

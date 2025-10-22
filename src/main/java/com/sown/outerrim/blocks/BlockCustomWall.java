package com.sown.outerrim.blocks;

import com.sown.outerrim.OuterRim;
import net.minecraft.block.Block;
import net.minecraft.block.BlockWall;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

import java.util.List;

public class BlockCustomWall extends BlockWall {

    private Block mainBlock;

    public BlockCustomWall(Block mainBlock) {
        super(mainBlock);
        this.mainBlock = mainBlock;
        String name = mainBlock.getUnlocalizedName().substring(5);
        setBlockName(name + "_wall");
        setBlockTextureName("outerrim:" + name);
        setHardness(mainBlock.getBlockHardness(null, 0, 0, 0));
        setStepSound(mainBlock.stepSound);
        this.setCreativeTab(OuterRim.tabBlock);
    }

    @SuppressWarnings("unchecked")
    @Override
    public void getSubBlocks(Item item, CreativeTabs tab, @SuppressWarnings("rawtypes") List list) {
        if (Item.getItemFromBlock(this) == item) {
            list.add(new ItemStack(item));
        }
    }

    @Override
    public IIcon getIcon(int side, int meta) {
        return mainBlock.getIcon(side, meta);
    }
}

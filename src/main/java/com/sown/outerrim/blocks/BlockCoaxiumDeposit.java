package com.sown.outerrim.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import com.sown.outerrim.OuterRim;

public class BlockCoaxiumDeposit extends Block {
    public BlockCoaxiumDeposit() {
        super(Material.rock);
        setBlockName("outerrim.coaxiumDeposit");
        setBlockTextureName("outerrim:coaxium_deposit_full");
        setBlockUnbreakable().setResistance(6000000F);
        setCreativeTab(OuterRim.tabBlock);
    }
}

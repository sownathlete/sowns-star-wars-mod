package com.sown.outerrim.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import com.sown.outerrim.OuterRim;

public class BlockCoaxiumDepositEmpty extends Block {
    public BlockCoaxiumDepositEmpty() {
        super(Material.rock);
        setBlockName("outerrim.coaxiumDepositEmpty");
        setBlockTextureName("outerrim:coaxium_deposit_empty");
        setBlockUnbreakable().setResistance(6000000F);
        setCreativeTab(OuterRim.tabBlock);
    }
}

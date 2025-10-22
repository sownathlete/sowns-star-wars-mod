package com.sown.outerrim.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import com.sown.outerrim.OuterRim;

public class BlockCoaxiumDepositVolatile extends Block {
    public BlockCoaxiumDepositVolatile() {
        super(Material.rock);
        setBlockName("outerrim.coaxiumDepositVolatile");
        setBlockTextureName("outerrim:coaxium_deposit_volatile");
        setHardness(50F).setResistance(2000F);
        setHarvestLevel("pickaxe", 3);
        setCreativeTab(OuterRim.tabBlock);
    }
}

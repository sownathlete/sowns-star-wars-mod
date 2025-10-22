package com.sown.outerrim.items;

import java.util.HashMap;
import java.util.Map;

import com.sown.outerrim.OuterRim;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.ItemBucket;
import net.minecraftforge.fluids.BlockFluidClassic;

public class ItemCustomBucket extends ItemBucket {
    private static Map<String, ItemCustomBucket> buckets = new HashMap<>();

    public static ItemCustomBucket getBucketForFluid(String fluidName) {
        return buckets.get(fluidName);
    }

    public ItemCustomBucket(Block fluidBlock) {
        super(fluidBlock);
        this.setCreativeTab(OuterRim.tabMisc);
        buckets.put(((BlockFluidClassic)fluidBlock).getFluid().getName(), this);
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister iconRegister) {
        this.itemIcon = iconRegister.registerIcon("outerrim:" + this.getUnlocalizedName().substring(5));
    }
}

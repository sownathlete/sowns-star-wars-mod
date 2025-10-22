package com.sown.outerrim.items;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemCoaxiumVialUnstableMax extends ItemCoaxiumVialBase {
    public ItemCoaxiumVialUnstableMax() {
        super("coaxiumVialMax", 200);
    }

    @Override
    public void onCreated(ItemStack stack, World world, EntityPlayer player) {
        initTags(stack, 180, world.provider.dimensionId);
    }
}

package com.sown.outerrim.items;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemCoaxiumVialUnstableMedium extends ItemCoaxiumVialBase {
    public ItemCoaxiumVialUnstableMedium() {
        super("coaxiumVialMed", 200);
    }

    @Override
    public void onCreated(ItemStack stack, World world, EntityPlayer player) {
        initTags(stack, 100, world.provider.dimensionId);
    }
}
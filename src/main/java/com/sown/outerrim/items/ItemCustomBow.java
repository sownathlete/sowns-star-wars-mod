package com.sown.outerrim.items;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemCustomBow extends ItemBow {

    // Constructor: You can set additional properties here
    public ItemCustomBow() {
        super();
        // Set custom properties if needed, like the max stack size, max damage, etc.
    }

    // Override other methods if you want to change the behavior of the bow

    @Override
    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
        // Customize the behavior when the player right-clicks with this item
        return super.onItemRightClick(stack, world, player);
    }

    // Add other methods to customize this bow's functionality as needed

}
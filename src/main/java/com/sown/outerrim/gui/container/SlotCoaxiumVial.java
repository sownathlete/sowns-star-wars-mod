package com.sown.outerrim.gui.container;

import com.sown.outerrim.items.ItemCoaxiumVialRaw;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotCoaxiumVial extends Slot {
    public SlotCoaxiumVial(IInventory inv, int index, int x, int y) {
        super(inv, index, x, y);
    }

    @Override
    public boolean isItemValid(ItemStack stack) {
        return stack != null && stack.getItem() instanceof ItemCoaxiumVialRaw;
    }
}

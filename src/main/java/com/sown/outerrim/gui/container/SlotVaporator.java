package com.sown.outerrim.gui.container;

import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotVaporator extends Slot {
    private final IInventory inventory;

    public SlotVaporator(IInventory inventory, int index, int xPosition, int yPosition) {
        super(inventory, index, xPosition, yPosition);
        this.inventory = inventory;
    }

    @Override
    public boolean isItemValid(ItemStack stack) {
        return this.inventory.isItemValidForSlot(this.getSlotIndex(), stack);
    }
    
    @Override
    public int getSlotStackLimit() {
        ItemStack stack = getStack();
        if (stack != null && stack.getItem() == Items.bucket) {
            return 1;
        } else {
            return super.getSlotStackLimit();
        }
    }
}

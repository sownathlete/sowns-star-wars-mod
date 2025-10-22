package com.sown.outerrim.gui.container;

import com.sown.outerrim.items.ItemCoaxiumVialRaw;
import com.sown.outerrim.registry.ItemRegister;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class SlotCoaxiumContainer extends Slot {

    private static final Item EMPTY  = ItemRegister.getRegisteredItem("vialEmpty");
    private static final Item RAW    = ItemRegister.getRegisteredItem("vialCoaxiumRaw");
    private static final Item CLEAN  = ItemRegister.getRegisteredItem("vialCoaxiumRefined");

    public SlotCoaxiumContainer(IInventory inv, int id, int x, int y) {
        super(inv, id, x, y);
    }

    @Override public boolean isItemValid(ItemStack s) {
        if (s == null) return false;
        Item i = s.getItem();
        return i == EMPTY || i == RAW || i == CLEAN || i instanceof ItemCoaxiumVialRaw;
    }
}

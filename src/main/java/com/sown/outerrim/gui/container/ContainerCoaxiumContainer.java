package com.sown.outerrim.gui.container;

import com.sown.outerrim.tileentities.TileEntityCoaxiumContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerCoaxiumContainer extends Container {

    private final TileEntityCoaxiumContainer tile;

    private static final int SLOT_SIZE = 18;
    private static final int INV_START_Y = 49;
    private static final int HOTBAR_Y = 107;

    public ContainerCoaxiumContainer(InventoryPlayer invPlayer, TileEntityCoaxiumContainer t) {
        tile = t;
        for (int i = 0; i < 9; i++) addSlotToContainer(new SlotCoaxiumContainer(t, i, 8 + i * SLOT_SIZE, 18));
        for (int r = 0; r < 3; r++)
            for (int c = 0; c < 9; c++)
                addSlotToContainer(new Slot(invPlayer, c + r * 9 + 9, 8 + c * SLOT_SIZE, INV_START_Y + r * SLOT_SIZE));
        for (int c = 0; c < 9; c++) addSlotToContainer(new Slot(invPlayer, c, 8 + c * SLOT_SIZE, HOTBAR_Y));
    }

    @Override public boolean canInteractWith(EntityPlayer p) { return tile.isUseableByPlayer(p); }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer p, int idx) {
        ItemStack res = null;
        Slot s = (Slot) inventorySlots.get(idx);
        if (s != null && s.getHasStack()) {
            ItemStack st = s.getStack();
            res = st.copy();
            int containerEnd = 9;
            int playerInvEnd = containerEnd + 27;
            int slots = playerInvEnd + 9;
            if (idx < containerEnd) {
                if (!mergeItemStack(st, containerEnd, slots, true)) return null;
            } else {
                if (!((Slot) inventorySlots.get(0)).isItemValid(st)) return null;
                if (!mergeItemStack(st, 0, containerEnd, false)) return null;
            }
            if (st.stackSize == 0) s.putStack(null);
            else s.onSlotChanged();
        }
        return res;
    }
}

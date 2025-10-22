package com.sown.outerrim.gui.container;

import com.sown.outerrim.items.ItemCoaxiumVialRaw;
import com.sown.outerrim.tileentities.TileEntityCoaxiumRefinery;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.SlotFurnace;
import net.minecraft.item.ItemStack;

public class ContainerCoaxiumRefinery extends Container {

    private final TileEntityCoaxiumRefinery tile;
    private int lastProcessTime;
    private int lastTotalProcessTime;

    private static final int SLOT_SPACING = 18;

    private static final int L_IN_X  = 37;
    private static final int L_OUT_X = 5;
    private static final int R_IN_X  = 123;
    private static final int R_OUT_X = 155;

    private static final int IN_Y0   = 20;
    private static final int OUT_Y0  = 20;
    private static final int COL_DY  = 18;

    private static final int INV_X0   = 8;
    private static final int INV_Y0   = 90;
    private static final int HOTBAR_Y = 148;

    public ContainerCoaxiumRefinery(InventoryPlayer invPlayer, TileEntityCoaxiumRefinery tile) {
        this.tile = tile;

        for (int i = 0; i < 3; i++) addSlotToContainer(new SlotCoaxiumVial(tile,       0 + i, L_IN_X, IN_Y0 + i * COL_DY));
        for (int i = 0; i < 3; i++) addSlotToContainer(new SlotCoaxiumVial(tile,       3 + i, R_IN_X, IN_Y0 + i * COL_DY));

        for (int i = 0; i < 3; i++) addSlotToContainer(new SlotFurnace(invPlayer.player, tile, 6 + i, L_OUT_X, OUT_Y0 + i * COL_DY));
        for (int i = 0; i < 3; i++) addSlotToContainer(new SlotFurnace(invPlayer.player, tile, 9 + i, R_OUT_X, OUT_Y0 + i * COL_DY));

        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 9; col++) {
                addSlotToContainer(new Slot(invPlayer, col + row * 9 + 9,
                        INV_X0 + col * SLOT_SPACING,
                        INV_Y0 + row * SLOT_SPACING));
            }
        }

        for (int col = 0; col < 9; col++) {
            addSlotToContainer(new Slot(invPlayer, col, INV_X0 + col * SLOT_SPACING, HOTBAR_Y));
        }
    }

    @Override
    public void addCraftingToCrafters(ICrafting c) {
        super.addCraftingToCrafters(c);
        c.sendProgressBarUpdate(this, 0, tile.processTime);
        c.sendProgressBarUpdate(this, 1, tile.totalProcessTime);
    }

    @Override
    public void detectAndSendChanges() {
        super.detectAndSendChanges();
        for (Object o : crafters) {
            ICrafting ic = (ICrafting) o;
            if (lastProcessTime != tile.processTime)
                ic.sendProgressBarUpdate(this, 0, tile.processTime);
            if (lastTotalProcessTime != tile.totalProcessTime)
                ic.sendProgressBarUpdate(this, 1, tile.totalProcessTime);
        }
        lastProcessTime = tile.processTime;
        lastTotalProcessTime = tile.totalProcessTime;
    }

    @Override
    public void updateProgressBar(int id, int data) {
        if (id == 0)      tile.processTime      = data;
        else if (id == 1) tile.totalProcessTime = data;
    }

    @Override
    public boolean canInteractWith(EntityPlayer player) {
        return tile.isUseableByPlayer(player);
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer player, int index) {
        ItemStack result = null;
        Slot slot = (Slot) inventorySlots.get(index);

        if (slot != null && slot.getHasStack()) {
            ItemStack stack = slot.getStack();
            result = stack.copy();

            final int MACHINE_START = 0;
            final int MACHINE_END   = 12;

            final int PLAYER_INV_START = MACHINE_END;
            final int PLAYER_INV_END   = PLAYER_INV_START + 27;
            final int HOTBAR_START     = PLAYER_INV_END;
            final int HOTBAR_END       = HOTBAR_START + 9;

            if (index < MACHINE_END) {
                if (!mergeItemStack(stack, PLAYER_INV_START, PLAYER_INV_END, false) &&
                    !mergeItemStack(stack, HOTBAR_START, HOTBAR_END, false)) {
                    return null;
                }
                slot.onPickupFromSlot(player, stack);
            } else {
                boolean moved = false;
                if (stack.getItem() instanceof ItemCoaxiumVialRaw) {
                    moved = mergeItemStack(stack, 0, 3, false) ||
                            mergeItemStack(stack, 3, 6, false);
                }

                if (!moved) {
                    if (index >= PLAYER_INV_START && index < PLAYER_INV_END) {
                        if (!mergeItemStack(stack, HOTBAR_START, HOTBAR_END, false)) return null;
                    } else if (index >= HOTBAR_START && index < HOTBAR_END) {
                        if (!mergeItemStack(stack, PLAYER_INV_START, PLAYER_INV_END, false)) return null;
                    } else {
                        return null;
                    }
                }
            }

            if (stack.stackSize == 0) slot.putStack(null);
            else slot.onSlotChanged();
        }

        return result;
    }
}

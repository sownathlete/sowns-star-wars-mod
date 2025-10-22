package com.sown.outerrim.gui.container;

import com.sown.outerrim.tileentities.TileEntityCoaxiumPump;
import com.sown.outerrim.registry.ItemRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerCoaxiumPump extends Container {
    private final TileEntityCoaxiumPump tile;
    private int[] lastProg;
    private static final int SLOTS = 9;

    public ContainerCoaxiumPump(InventoryPlayer inv, TileEntityCoaxiumPump te) {
        tile = te;
        lastProg = new int[SLOTS];
        for (int i = 0; i < SLOTS; i++) {
            addSlotToContainer(new Slot(te, i, 8 + i*18, 18) {
                @Override public boolean isItemValid(ItemStack s) {
                    return s != null && s.getItem() == ItemRegister.getRegisteredItem("vialEmpty");
                }
                @Override public int getSlotStackLimit() { return 1; }
            });
        }
        int top = 60;
        for (int r = 0; r < 3; r++)
            for (int c = 0; c < 9; c++)
                addSlotToContainer(new Slot(inv, c + r*9 + 9, 8 + c*18, top + r*18));
        for (int c = 0; c < 9; c++)
            addSlotToContainer(new Slot(inv, c, 8 + c*18, top + 58));
    }
    @Override public boolean canInteractWith(EntityPlayer p) { return tile.isUseableByPlayer(p); }
    @Override
    public void addCraftingToCrafters(ICrafting crafter) {
        super.addCraftingToCrafters(crafter);
        for (int i = 0; i < SLOTS; i++) {
            crafter.sendProgressBarUpdate(this, i, tile.clientProgress[i]);
        }
    }
    @Override
    public void detectAndSendChanges() {
        super.detectAndSendChanges();
        for (Object o : crafters) {
            ICrafting c = (ICrafting)o;
            for (int i = 0; i < SLOTS; i++) {
                if (lastProg[i] != tile.clientProgress[i]) {
                    c.sendProgressBarUpdate(this, i, tile.clientProgress[i]);
                }
            }
        }
        System.arraycopy(tile.clientProgress, 0, lastProg, 0, SLOTS);
    }
    @Override
    public void updateProgressBar(int id, int v) {
        if (id >= 0 && id < SLOTS) tile.clientProgress[id] = v;
    }
    @Override public ItemStack transferStackInSlot(EntityPlayer p, int idx) { return null; }
}
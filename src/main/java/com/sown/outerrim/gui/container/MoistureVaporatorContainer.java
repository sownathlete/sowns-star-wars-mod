package com.sown.outerrim.gui.container;

import com.sown.outerrim.tileentities.TileEntityMoistureVaporator;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class MoistureVaporatorContainer
extends Container {
    private final TileEntityMoistureVaporator tileMV;

    public MoistureVaporatorContainer(InventoryPlayer player, TileEntityMoistureVaporator vap) {
        int i;
        this.tileMV = vap;
        this.addSlotToContainer(new SlotVaporator(vap, 0, 80, 35));
        for (i = 0; i < 3; ++i) {
            for (int j = 0; j < 9; ++j) {
                this.addSlotToContainer(new SlotVaporator(player, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }
        for (i = 0; i < 9; ++i) {
            this.addSlotToContainer(new SlotVaporator(player, i, 8 + i * 18, 142));
        }
    }

    @Override
    public boolean canInteractWith(EntityPlayer player) {
        return this.tileMV.isUseableByPlayer(player);
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer player, int slotNumber) {
        ItemStack itemstack = null;
        Slot slot = (Slot)this.inventorySlots.get(slotNumber);
        if (slot != null && slot.getHasStack()) {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();

            if (slotNumber == 0) {
                if (!this.mergeItemStack(itemstack1, 1, 37, true))
                    return null;
                slot.onSlotChange(itemstack1, itemstack);
            } else {
                if(itemstack1.getItem() == Items.bucket) {
                    if (!this.getSlot(0).getHasStack()) {
                        this.getSlot(0).putStack(new ItemStack(Items.bucket, 1));
                        itemstack1.stackSize -= 1;
                    }
                    return null;
                } else if (slotNumber < 28 ? !this.mergeItemStack(itemstack1, 28, 37, false) : slotNumber < 37 && !this.mergeItemStack(itemstack1, 1, 28, false)) {
                    return null;
                }
            }

            if (itemstack1.stackSize == 0) {
                slot.putStack(null);
            } else {
                slot.onSlotChanged();
            }

            if (itemstack1.stackSize == itemstack.stackSize)
                return null;

            slot.onPickupFromSlot(player, itemstack1);
        }
        return itemstack;
    }

}


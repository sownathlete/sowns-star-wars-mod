package com.sown.outerrim.tileentities;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;

public class TileEntityItemInventory extends TileEntity implements IInventory, ISidedInventory {
    private ItemStack inventory;

    public TileEntityItemInventory() {
        this.inventory = null;
    }

    // TileEntity methods

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        // Read inventory data from the NBT compound
        if (compound.hasKey("Inventory")) {
            this.inventory = ItemStack.loadItemStackFromNBT(compound.getCompoundTag("Inventory"));
        }
    }

    @Override
    public void writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);
        // Write inventory data to the NBT compound
        if (this.inventory != null) {
            NBTTagCompound inventoryTag = new NBTTagCompound();
            this.inventory.writeToNBT(inventoryTag);
            compound.setTag("Inventory", inventoryTag);
        }
    }

    // IInventory methods

    @Override
    public int getSizeInventory() {
        return 1;
    }

    @Override
    public ItemStack getStackInSlot(int index) {
        return index == 0 ? this.inventory : null;
    }

    @Override
    public ItemStack decrStackSize(int index, int count) {
        if (index == 0 && this.inventory != null) {
            ItemStack stack = this.inventory.splitStack(count);
            if (this.inventory.stackSize <= 0) {
                this.inventory = null;
            }
            return stack;
        }
        return null;
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int index) {
        if (index == 0 && this.inventory != null) {
            ItemStack stack = this.inventory;
            this.inventory = null;
            return stack;
        }
        return null;
    }

    @Override
    public void setInventorySlotContents(int index, ItemStack stack) {
        if (index == 0) {
            this.inventory = stack;
        }
    }

    @Override
    public String getInventoryName() {
        return null; // Set a custom name for the inventory if needed
    }

    @Override
    public boolean hasCustomInventoryName() {
        return false; // Set to true if using a custom name
    }

    @Override
    public int getInventoryStackLimit() {
        return 64; // Set the stack limit for the inventory
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer player) {
        return this.worldObj.getTileEntity(this.xCoord, this.yCoord, this.zCoord) != this ? false
                : player.getDistanceSq(this.xCoord + 0.5D, this.yCoord + 0.5D, this.zCoord + 0.5D) <= 64.0D;
    }

    @Override
    public void openInventory() {}

    @Override
    public void closeInventory() {}

    @Override
    public boolean isItemValidForSlot(int index, ItemStack stack) {
        return true; // Add any specific validation logic for the slot
    }

    // ISidedInventory methods

    @Override
    public int[] getAccessibleSlotsFromSide(int side) {
        return new int[] { 0 };
    }

    @Override
    public boolean canInsertItem(int index, ItemStack stack, int side) {
        return index == 0 && this.isItemValidForSlot(index, stack);
    }

    @Override
    public boolean canExtractItem(int index, ItemStack stack, int side) {
        return index == 0;
    }
}
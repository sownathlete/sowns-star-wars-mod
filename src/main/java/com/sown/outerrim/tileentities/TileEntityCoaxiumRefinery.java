package com.sown.outerrim.tileentities;

import java.util.stream.IntStream;

import com.sown.outerrim.items.ItemCoaxiumVialRaw;
import com.sown.outerrim.registry.ItemRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;

/**
 * Controller for the refinery.
 * Inputs = 0..5 (left 0..2, right 3..5)
 * Outputs = 6..11 (paired: out = in + 6)
 */
public class TileEntityCoaxiumRefinery extends TileEntity implements ISidedInventory {

    private static final int TICKS_TO_REFINE = 200;

    public int processTime = 0;              // max(slotProgress)
    public int totalProcessTime = TICKS_TO_REFINE;

    public static final int INPUT_SLOTS = 6;

    private int facing = 0;

    private final ItemStack[] inv = new ItemStack[INPUT_SLOTS * 2];
    private final int[] slotProgress = new int[INPUT_SLOTS];

    public int getFacing() { return facing; }
    public void setFacing(int f) { facing = (f & 3); markDirty(); }

    @Override
    public void updateEntity() {
        if (worldObj.isRemote) return;

        boolean changed = false;
        int maxProg = 0;

        for (int i = 0; i < INPUT_SLOTS; i++) {
            ItemStack in = inv[i];

            if (in != null && in.getItem() instanceof ItemCoaxiumVialRaw) {
                int outSlot = i + INPUT_SLOTS;
                ItemStack refined = new ItemStack(
                        ItemRegister.getRegisteredItem("vialCoaxiumRefined"), 1, 0);

                if (canOutput(outSlot, refined)) {
                    slotProgress[i]++;
                    changed = true;

                    if (slotProgress[i] >= TICKS_TO_REFINE) {
                        in.stackSize--;
                        if (in.stackSize <= 0) inv[i] = null;

                        if (inv[outSlot] == null) {
                            inv[outSlot] = refined.copy();
                        } else {
                            inv[outSlot].stackSize++;
                        }

                        slotProgress[i] = 0;
                        changed = true;
                    }
                } else {
                    if (slotProgress[i] >= TICKS_TO_REFINE) {
                        slotProgress[i] = TICKS_TO_REFINE - 1;
                    }
                }

                if (slotProgress[i] > maxProg) maxProg = slotProgress[i];
            } else {
                if (slotProgress[i] != 0) {
                    slotProgress[i] = 0;
                    changed = true;
                }
            }
        }

        this.processTime = maxProg;
        this.totalProcessTime = TICKS_TO_REFINE;

        if (changed) {
            markDirty();
            worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
        }
    }

    private boolean canOutput(int outSlot, ItemStack toAdd) {
        if (outSlot < 0 || outSlot >= inv.length) return false;
        ItemStack out = inv[outSlot];
        if (out == null) return true;
        if (!out.isItemEqual(toAdd) || !ItemStack.areItemStackTagsEqual(out, toAdd)) return false;
        int limit = Math.min(getInventoryStackLimit(), out.getMaxStackSize());
        return out.stackSize < limit;
    }

    // GUI helpers
    public int getProcessTimeForSlot(int slotIndex) { return slotProgress[slotIndex]; }
    public int getTotalProcessTime() { return totalProcessTime; }
    public static int getTicksToRefine() { return TICKS_TO_REFINE; }

    // ISidedInventory
    @Override public int[] getAccessibleSlotsFromSide(int side) {
        return IntStream.range(0, inv.length).toArray();
    }
    @Override public boolean canInsertItem(int slot, ItemStack stack, int side) {
        return slot >= 0 && slot < INPUT_SLOTS && isItemValidForSlot(slot, stack);
    }
    @Override public boolean canExtractItem(int slot, ItemStack stack, int side) {
        return slot >= INPUT_SLOTS && slot < inv.length;
    }

    // IInventory
    @Override public int getSizeInventory() { return inv.length; }
    @Override public ItemStack getStackInSlot(int i) { return inv[i]; }

    @Override
    public ItemStack decrStackSize(int slot, int amount) {
        if (inv[slot] == null) return null;
        ItemStack out;
        if (inv[slot].stackSize <= amount) {
            out = inv[slot];
            inv[slot] = null;
        } else {
            out = inv[slot].splitStack(amount);
            if (inv[slot].stackSize == 0) inv[slot] = null;
        }
        markDirty();
        return out;
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int slot) {
        ItemStack st = inv[slot];
        inv[slot] = null;
        return st;
    }

    @Override
    public void setInventorySlotContents(int slot, ItemStack stack) {
        inv[slot] = stack;
        markDirty();
    }

    @Override public String getInventoryName() { return "Coaxium Refinery"; }
    @Override public boolean hasCustomInventoryName() { return false; }
    @Override public int getInventoryStackLimit() { return 64; }

    @Override
    public boolean isUseableByPlayer(EntityPlayer p) {
        return worldObj.getTileEntity(xCoord, yCoord, zCoord) == this
                && p.getDistanceSq(xCoord + 0.5, yCoord + 0.5, zCoord + 0.5) <= 64;
    }

    @Override public void openInventory() {}
    @Override public void closeInventory() {}

    @Override
    public boolean isItemValidForSlot(int index, ItemStack stack) {
        return index >= 0 && index < INPUT_SLOTS
                && stack != null
                && stack.getItem() instanceof ItemCoaxiumVialRaw;
    }

    // NBT
    @Override
    public void writeToNBT(NBTTagCompound tag) {
        super.writeToNBT(tag);
        tag.setInteger("facing", facing);
        for (int i = 0; i < INPUT_SLOTS; i++) tag.setInteger("prog" + i, slotProgress[i]);

        NBTTagList list = new NBTTagList();
        for (int i = 0; i < inv.length; i++) {
            if (inv[i] != null) {
                NBTTagCompound t = new NBTTagCompound();
                t.setByte("Slot", (byte) i);
                inv[i].writeToNBT(t);
                list.appendTag(t);
            }
        }
        tag.setTag("Items", list);
    }

    @Override
    public void readFromNBT(NBTTagCompound tag) {
        super.readFromNBT(tag);
        facing = tag.getInteger("facing") & 3;
        for (int i = 0; i < INPUT_SLOTS; i++) slotProgress[i] = tag.getInteger("prog" + i);

        NBTTagList list = tag.getTagList("Items", 10);
        for (int j = 0; j < list.tagCount(); j++) {
            NBTTagCompound t = list.getCompoundTagAt(j);
            int slot = t.getByte("Slot") & 0xFF;
            if (slot >= 0 && slot < inv.length) {
                inv[slot] = ItemStack.loadItemStackFromNBT(t);
            }
        }
    }

    // net sync
    @Override
    public Packet getDescriptionPacket() {
        NBTTagCompound tag = new NBTTagCompound();
        writeToNBT(tag);
        return new S35PacketUpdateTileEntity(xCoord, yCoord, zCoord, 0, tag);
    }

    @Override
    public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
        readFromNBT(pkt.func_148857_g());
    }

    // --- keep TESR visible farther & stop frustum culling too early ---

    @Override
    public AxisAlignedBB getRenderBoundingBox() {
        return INFINITE_EXTENT_AABB;
    }

    @Override
    public double getMaxRenderDistanceSquared() {
        return Double.MAX_VALUE;
    }
}

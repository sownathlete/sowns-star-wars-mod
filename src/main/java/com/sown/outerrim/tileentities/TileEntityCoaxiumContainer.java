package com.sown.outerrim.tileentities;

import com.sown.outerrim.items.ItemCoaxiumVialRaw;
import com.sown.outerrim.registry.ItemRegister;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;

public class TileEntityCoaxiumContainer extends TileEntity implements IInventory {
    private static final Item EMPTY   = ItemRegister.getRegisteredItem("vialEmpty");
    private static final Item RAW     = ItemRegister.getRegisteredItem("vialCoaxiumRaw");
    private static final Item REFINED = ItemRegister.getRegisteredItem("vialCoaxiumRefined");

    private int facing;
    private final ItemStack[] inv = new ItemStack[9];

    public int getFacing() { return facing; }
    public void setFacing(int f) { facing = f; markDirty(); }

    @Override
    public void updateEntity() {
        if (worldObj.isRemote) return;
        for (ItemStack st : inv) {
            if (st != null && st.getItem() == RAW) {
                if (!st.hasTagCompound()) st.setTagCompound(new NBTTagCompound());
                st.getTagCompound().setBoolean("Paused", true);
            }
        }
    }

    @Override public int getSizeInventory() { return inv.length; }
    @Override public ItemStack getStackInSlot(int i) { return inv[i]; }

    @Override
    public ItemStack decrStackSize(int slot, int amt) {
        if (inv[slot] == null) return null;
        ItemStack out;
        if (inv[slot].stackSize <= amt) {
            out = inv[slot];
            inv[slot] = null;
        } else {
            out = inv[slot].splitStack(amt);
            if (inv[slot].stackSize == 0) inv[slot] = null;
        }
        markDirty();
        return out;
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int s) {
        ItemStack st = inv[s];
        inv[s] = null;
        return st;
    }

    @Override
    public void setInventorySlotContents(int slot, ItemStack stack) {
        if (stack != null && !isItemValidForSlot(slot, stack)) {
            if (!worldObj.isRemote) {
                EntityItem ei = new EntityItem(worldObj, xCoord + 0.5, yCoord + 1.1, zCoord + 0.5, stack.copy());
                worldObj.spawnEntityInWorld(ei);
            }
            return;
        }
        inv[slot] = stack;
        markDirty();
    }

    @Override public String getInventoryName() { return "Coaxium Container"; }
    @Override public boolean hasCustomInventoryName() { return false; }
    @Override public int getInventoryStackLimit() { return 64; }
    @Override public boolean isUseableByPlayer(EntityPlayer p) { return true; }
    @Override public void openInventory() {}
    @Override public void closeInventory() {}

    @Override
    public boolean isItemValidForSlot(int slot, ItemStack stack) {
        if (stack == null) return false;
        Item i = stack.getItem();
        return i == EMPTY || i == RAW || i == REFINED;
    }

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

    @Override
    public void writeToNBT(NBTTagCompound n) {
        super.writeToNBT(n);
        n.setInteger("facing", facing);
        NBTTagList list = new NBTTagList();
        for (int i = 0; i < inv.length; i++) {
            if (inv[i] != null) {
                NBTTagCompound t = new NBTTagCompound();
                t.setByte("Slot", (byte) i);
                inv[i].writeToNBT(t);
                list.appendTag(t);
            }
        }
        n.setTag("Items", list);
    }

    @Override
    public void readFromNBT(NBTTagCompound n) {
        super.readFromNBT(n);
        facing = n.getInteger("facing");
        NBTTagList list = n.getTagList("Items", 10);
        for (int j = 0; j < list.tagCount(); j++) {
            NBTTagCompound t = list.getCompoundTagAt(j);
            int slot = t.getByte("Slot") & 0xFF;
            inv[slot] = ItemStack.loadItemStackFromNBT(t);
        }
    }
    
    @Override
    public double getMaxRenderDistanceSquared() {
        return Double.MAX_VALUE;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public AxisAlignedBB getRenderBoundingBox() {
        return TileEntity.INFINITE_EXTENT_AABB;
    }

}

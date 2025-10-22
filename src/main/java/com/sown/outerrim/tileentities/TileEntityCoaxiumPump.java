package com.sown.outerrim.tileentities;

import com.sown.outerrim.registry.ItemRegister;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

public class TileEntityCoaxiumPump extends TileEntity implements IInventory {
    private static final int SLOTS = 9;
    private static final String SOURCE_NAME = "outerrim:coaxiumDeposit";
    private static final int PROCESS_TIME = 200;

    private int facing;
    private ItemStack[] slots = new ItemStack[SLOTS];
    private int[] progress = new int[SLOTS];
    public int[] clientProgress = new int[SLOTS];

    private void dbg(String msg) {
        System.out.println("[Pump9] ("+xCoord+","+yCoord+","+zCoord+") " + msg);
    }

    @Override
    public void updateEntity() {
        if (worldObj == null || worldObj.isRemote) return;
        Block below = worldObj.getBlock(xCoord, yCoord - 1, zCoord);
        String nameBelow = Block.blockRegistry.getNameForObject(below);
        boolean valid = SOURCE_NAME.equals(nameBelow);
        for (int i = 0; i < SLOTS; i++) {
            ItemStack st = slots[i];
            boolean hasEmpty = st != null && st.getItem() == ItemRegister.getRegisteredItem("vialEmpty");
            if (hasEmpty && valid) {
                progress[i]++;
                if (progress[i] >= PROCESS_TIME) {
                    ItemStack out = new ItemStack(ItemRegister.getRegisteredItem("vialCoaxiumRaw"));
                    NBTTagCompound tag = new NBTTagCompound();
                    tag.setInteger("Volatility", 1);
                    tag.setInteger("TickCounter", 0);
                    tag.setBoolean("Paused", false);
                    tag.setInteger("OriginDim", worldObj.provider.dimensionId);
                    tag.setLong("LastSync", worldObj.getTotalWorldTime());
                    out.setTagCompound(tag);
                    slots[i] = out;
                    progress[i] = 0;
                    dbg("slot"+i+" complete vol=1%");
                }
            } else if (progress[i] != 0) {
                progress[i] = 0;
                dbg("slot"+i+" reset");
            }
            clientProgress[i] = progress[i];
        }
        markDirty();
        worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
    }

    // facing
    public void setFacing(int f) { facing = f; markDirty(); }
    public int getFacing() { return facing; }

    // IInventory
    @Override public int getSizeInventory() { return SLOTS; }

    @Override public ItemStack getStackInSlot(int i) { return slots[i]; }

    @Override
    public ItemStack decrStackSize(int i,int amt) {
        ItemStack st = slots[i]; if (st == null) return null;
        ItemStack out = st.splitStack(amt);
        if (st.stackSize == 0) slots[i] = null;
        progress[i] = 0; markDirty(); dbg("decr slot"+i);
        return out;
    }

    @Override public ItemStack getStackInSlotOnClosing(int i) { ItemStack r = slots[i]; slots[i] = null; progress[i]=0; return r; }
    @Override
    public void setInventorySlotContents(int i, ItemStack s) {
        slots[i] = s; progress[i] = 0; markDirty(); dbg("set slot"+i);
    }

    @Override public String getInventoryName() { return "Coaxium Pump"; }
    @Override public boolean hasCustomInventoryName() { return false; }
    @Override public int getInventoryStackLimit() { return 1; }

    @Override
    public boolean isItemValidForSlot(int i,ItemStack s) {
        return s != null && s.getItem() == ItemRegister.getRegisteredItem("vialEmpty");
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer p) {
        return worldObj.getTileEntity(xCoord,yCoord,zCoord)==this
            && p.getDistanceSq(xCoord+.5, yCoord+.5, zCoord+.5)<=64;
    }
    @Override public void openInventory() {}

    @Override public void closeInventory() {}

    // Network sync
    @Override
    public Packet getDescriptionPacket() {
        NBTTagCompound n=new NBTTagCompound(); writeToNBT(n);
        return new S35PacketUpdateTileEntity(xCoord, yCoord, zCoord, 0, n);
    }

    @Override
    public void onDataPacket(net.minecraft.network.NetworkManager net, S35PacketUpdateTileEntity pkt) {
        readFromNBT(pkt.func_148857_g());
    }

    @Override
    public void writeToNBT(NBTTagCompound n) {
        super.writeToNBT(n);
        n.setInteger("Facing", facing);
        for (int i=0; i<SLOTS; i++) {
            NBTTagCompound tag = new NBTTagCompound();
            if (slots[i] != null) slots[i].writeToNBT(tag);
            n.setTag("Slot"+i, tag);
            n.setInteger("Prog"+i, progress[i]);
        }
    }

    @Override
    public void readFromNBT(NBTTagCompound n) {
        super.readFromNBT(n);
        facing = n.getInteger("Facing");
        for (int i=0; i<SLOTS; i++) {
            progress[i] = n.getInteger("Prog"+i);
            NBTTagCompound tag = n.getCompoundTag("Slot"+i);
            slots[i] = ItemStack.loadItemStackFromNBT(tag);
            clientProgress[i] = progress[i];
        }
    }
    
    public int getProgressScaled(int slotIndex, int scale) {
        return clientProgress[slotIndex] * scale / PROCESS_TIME;
    }
    
    @Override
    public double getMaxRenderDistanceSquared() {
        return Double.MAX_VALUE;
    }

}

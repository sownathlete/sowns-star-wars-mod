package com.sown.outerrim.tileentities;

import com.sown.outerrim.registry.ItemRegister;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;

public class TileEntityPortableCoaxiumPump extends TileEntity implements IInventory {
    private static final String SOURCE_NAME   = "outerrim:coaxiumDepositVolatile";
    private static final int    PROCESS_TIME  = 200;
    private static final String TAG_VOL       = "Volatility";
    private static final String TAG_TICK      = "TickCounter";
    private static final String TAG_PAUSE     = "Paused";
    private static final String TAG_DIM       = "OriginDim";
    private static final String TAG_LAST_SYNC = "LastSync";

    private int        facing;
    private ItemStack  slot;
    private int        progress;
    public  int        clientProgress;

    private void dbg(String msg) {
        System.out.println("[Pump] ("+xCoord+","+yCoord+","+zCoord+") " + msg);
    }

    @Override
    public void updateEntity() {
        if (worldObj == null || worldObj.isRemote) return;

        Block below = worldObj.getBlock(xCoord, yCoord - 1, zCoord);
        boolean hasEmpty = slot != null
                         && slot.getItem() == ItemRegister.getRegisteredItem("vialEmpty");
        String nameBelow = Block.blockRegistry.getNameForObject(below);
        boolean validOre = SOURCE_NAME.equals(nameBelow);

        dbg("tick hasEmpty=" + hasEmpty
           + " ore=" + nameBelow
           + " valid=" + validOre
           + " prog=" + progress);

        if (hasEmpty && validOre) {
            progress++;
            if (progress >= PROCESS_TIME) {
                ItemStack result = new ItemStack(ItemRegister.getRegisteredItem("vialCoaxiumRaw"));
                NBTTagCompound tag = new NBTTagCompound();
                int volatility = 30 + worldObj.rand.nextInt(26);  // 3055%
                tag.setInteger(TAG_VOL, volatility);
                tag.setInteger(TAG_TICK, 0);
                tag.setBoolean(TAG_PAUSE, false);
                tag.setInteger(TAG_DIM, worldObj.provider.dimensionId);
                tag.setLong(TAG_LAST_SYNC, worldObj.getTotalWorldTime());
                result.setTagCompound(tag);

                slot = result;
                progress = 0;
                dbg("** COMPLETED  output vialCoaxiumRaw volatility=" + volatility + "% **");

                // replace the volatile deposit below with an empty deposit
                Block empty = (Block)Block.blockRegistry.getObject("outerrim:coaxiumDepositEmpty");
                if (empty != null) {
                    worldObj.setBlock(xCoord, yCoord - 1, zCoord, empty);
                }
            }
        } else {
            if (progress != 0) dbg("reset progress to 0");
            progress = 0;
        }

        markDirty();
        worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
    }

    public int getProgress() { return progress; }
    public void setFacing(int f) { facing = f; markDirty(); }
    public int getFacing() { return facing; }
    public int getProgressScaled(int px) { return clientProgress * px / PROCESS_TIME; }

    @Override
    public Packet getDescriptionPacket() {
        NBTTagCompound n = new NBTTagCompound();
        writeToNBT(n);
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
        n.setInteger("Prog", progress);
        if (slot != null) {
            NBTTagCompound s = new NBTTagCompound();
            slot.writeToNBT(s);
            n.setTag("Slot", s);
        }
    }

    @Override
    public void readFromNBT(NBTTagCompound n) {
        super.readFromNBT(n);
        facing         = n.getInteger("Facing");
        progress       = n.getInteger("Prog");
        clientProgress = progress;
        slot = n.hasKey("Slot")
             ? ItemStack.loadItemStackFromNBT(n.getCompoundTag("Slot"))
             : null;
    }

    @Override public int getSizeInventory()                        { return 1; }
    @Override public ItemStack getStackInSlot(int i)              { return slot; }
    @Override
    public ItemStack decrStackSize(int i,int amt) {
        if (slot == null) return null;
        ItemStack out = slot.splitStack(amt);
        if (slot.stackSize == 0) slot = null;
        progress = 0;
        markDirty();
        dbg("decrStackSize reset");
        return out;
    }
    @Override public ItemStack getStackInSlotOnClosing(int i)      { ItemStack r = slot; slot = null; progress = 0; return r; }
    @Override
    public void setInventorySlotContents(int i, ItemStack s) {
        slot = s;
        progress = 0;
        markDirty();
        dbg("slot set, progress reset");
    }
    @Override public String getInventoryName()                     { return "Portable Pump"; }
    @Override public boolean hasCustomInventoryName()               { return false; }
    @Override public int getInventoryStackLimit()                  { return 1; }
    @Override
    public boolean isItemValidForSlot(int i,ItemStack s) {
        return s != null && s.getItem() == ItemRegister.getRegisteredItem("vialEmpty");
    }
    @Override
    public boolean isUseableByPlayer(EntityPlayer p) {
        return worldObj.getTileEntity(xCoord,yCoord,zCoord) == this
            && p.getDistanceSq(xCoord+.5,yCoord+.5,zCoord+.5) <= 64;
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
    
    @Override public void openInventory()                          {}
    @Override public void closeInventory()                         {}
}

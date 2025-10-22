package com.sown.outerrim.tileentities;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.Packet;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

public class TileEntityFeluciaFlowerTurquoise extends TileEntity {
    private int facing = 0;
    private long lastPoison     = 0;
    private long nextParticles  = 0;

    public int  getFacing()            { return facing; }
    public void setFacing(int f)       { facing = f & 3; markDirty(); }

    public long getLastPoisonTime()    { return lastPoison; }
    public void setLastPoisonTime(long t) { lastPoison = t; }

    public long getNextParticleTime()  { return nextParticles; }

    /** schedule first/next burst (300–1200 ticks = 15–60s) */
    public void scheduleNextParticles(long now) {
        nextParticles = now + 300 + new java.util.Random().nextInt(901);
        markDirty();
    }

    @Override
    public void writeToNBT(NBTTagCompound nbt) {
        super.writeToNBT(nbt);
        nbt.setInteger("facing", facing);
        nbt.setLong   ("lastPoison", lastPoison);
        nbt.setLong   ("nextParticles", nextParticles);
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        super.readFromNBT(nbt);
        facing       = nbt.getInteger("facing");
        lastPoison   = nbt.getLong("lastPoison");
        nextParticles= nbt.getLong("nextParticles");
    }

    @Override
    public Packet getDescriptionPacket() {
        NBTTagCompound tag = new NBTTagCompound();
        writeToNBT(tag);
        return new S35PacketUpdateTileEntity(
            xCoord, yCoord, zCoord, 0, tag
        );
    }

    @Override
    public void onDataPacket(NetworkManager net,
                             S35PacketUpdateTileEntity pkt) {
        readFromNBT(pkt.func_148857_g());
    }

    @Override public boolean canUpdate() { return false; }
}

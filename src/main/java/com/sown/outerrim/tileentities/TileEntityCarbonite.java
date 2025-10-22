package com.sown.outerrim.tileentities;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

public class TileEntityCarbonite extends TileEntity {
    private boolean usePartsTexture = false;
    private String frozenPlayerName = "";
    private String cachedUUID = "";
    private boolean useDefaultTexture = false;
    private int facing = 0;
    private int clicks = 0;
    private boolean isLyingFlat = false;

    public TileEntityCarbonite() {}

    // Set and get the frozen player's name
    public void setFrozenPlayerName(String playerName) {
        this.frozenPlayerName = playerName;
        this.markDirty();
    }

    public String getFrozenPlayerName() {
        return this.frozenPlayerName;
    }

    // Get and set cached UUID
    public String getCachedUUID() {
        return this.cachedUUID;
    }

    public void setCachedUUID(String uuid) {
        this.cachedUUID = uuid;
        this.markDirty();
    }

    // Set and get whether to use the default texture
    public void setUseDefaultTexture(boolean useDefaultTexture) {
        this.useDefaultTexture = useDefaultTexture;
        this.markDirty();
    }

    public boolean shouldUseDefaultTexture() {
        return this.useDefaultTexture;
    }

    // Set and get whether to use the parts texture
    public boolean shouldUsePartsTexture() {
        return this.usePartsTexture;
    }

    public void setUsePartsTexture(boolean usePartsTexture) {
        this.usePartsTexture = usePartsTexture;
        this.markDirty();
    }

    // Set and get the lying flat state
    public boolean isLyingFlat() {
        return this.isLyingFlat;
    }

    public void setLyingFlat(boolean lyingFlat) {
        this.isLyingFlat = lyingFlat;
        this.markDirty();
    }

    // Set and get the facing direction
    public int getFacing() {
        return this.facing;
    }

    public void setFacing(int direction) {
        this.facing = direction;
        this.markDirty();
    }

    // Set and get the number of clicks
    public int getClicks() {
        return this.clicks;
    }

    public void setClicks(int clicks) {
        this.clicks = clicks;
        this.markDirty();
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        this.usePartsTexture = compound.getBoolean("UsePartsTexture");
        this.facing = compound.getInteger("facing");
        this.clicks = compound.getInteger("clicks");
        this.frozenPlayerName = compound.getString("FrozenPlayerName");  // Load player's name
        this.cachedUUID = compound.getString("CachedUUID");              // Load cached UUID
        this.useDefaultTexture = compound.getBoolean("UseDefaultTexture"); // Load texture preference
    }

    @Override
    public void writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);
        compound.setBoolean("UsePartsTexture", this.usePartsTexture);
        compound.setInteger("facing", this.facing);
        compound.setInteger("clicks", this.clicks);
        compound.setString("FrozenPlayerName", this.frozenPlayerName);  // Save player's name
        compound.setString("CachedUUID", this.cachedUUID);              // Save cached UUID
        compound.setBoolean("UseDefaultTexture", this.useDefaultTexture); // Save texture preference
    }

    @Override
    public Packet getDescriptionPacket() {
        NBTTagCompound tag = new NBTTagCompound();
        this.writeToNBT(tag);
        return new S35PacketUpdateTileEntity(this.xCoord, this.yCoord, this.zCoord, 64537, tag);
    }

    @Override
    public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity packet) {
        super.onDataPacket(net, packet);
        this.readFromNBT(packet.func_148857_g());
    }
}

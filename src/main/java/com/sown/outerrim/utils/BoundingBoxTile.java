package com.sown.outerrim.utils;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

public class BoundingBoxTile extends TileEntity {
    public int coreX, coreY, coreZ;

    public float minX, minY, minZ;
    public float maxX, maxY, maxZ;

    public void setCorePos(int x, int y, int z) {
        this.coreX = x;
        this.coreY = y;
        this.coreZ = z;
    }

    public void setBounds(float minX, float minY, float minZ, float maxX, float maxY, float maxZ) {
        this.minX = minX;
        this.minY = minY;
        this.minZ = minZ;
        this.maxX = maxX;
        this.maxY = maxY;
        this.maxZ = maxZ;
    }

    public AxisAlignedBB getLocalBounds() {
        return AxisAlignedBB.getBoundingBox(minX, minY, minZ, maxX, maxY, maxZ);
    }

    public TileEntity getCore() {
        return worldObj.getTileEntity(coreX, coreY, coreZ);
    }

    public int[] getCorePos() {
        return new int[] { coreX, coreY, coreZ };
    }

    @Override
    public void readFromNBT(NBTTagCompound tag) {
        super.readFromNBT(tag);

        coreX = tag.getInteger("coreX");
        coreY = tag.getInteger("coreY");
        coreZ = tag.getInteger("coreZ");

        minX = tag.getFloat("minX");
        minY = tag.getFloat("minY");
        minZ = tag.getFloat("minZ");
        maxX = tag.getFloat("maxX");
        maxY = tag.getFloat("maxY");
        maxZ = tag.getFloat("maxZ");
    }

    public void writeToNBT(NBTTagCompound tag) {
        super.writeToNBT(tag);

        tag.setInteger("coreX", coreX);
        tag.setInteger("coreY", coreY);
        tag.setInteger("coreZ", coreZ);

        tag.setFloat("minX", minX);
        tag.setFloat("minY", minY);
        tag.setFloat("minZ", minZ);
        tag.setFloat("maxX", maxX);
        tag.setFloat("maxY", maxY);
        tag.setFloat("maxZ", maxZ);
    }

    @Override
    public String toString() {
        return "BoundingBoxTile{" +
                "coreX=" + coreX +
                ", coreY=" + coreY +
                ", coreZ=" + coreZ +
                ", minX=" + minX +
                ", minY=" + minY +
                ", minZ=" + minZ +
                ", maxX=" + maxX +
                ", maxY=" + maxY +
                ", maxZ=" + maxZ +
                '}';
    }
}


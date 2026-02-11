package com.sown.outerrim.entities.vehicles;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

/**
 * Simplified base vehicle entity (ground + air capable) for Outer Rim.
 */
public class VehicleBaseOuterRim extends EntityLiving {

    public static final int YAW_DW = 16;
    public static final int PITCH_DW = 17;
    public float vehicYOffset = 0.0F;
    public float moveModifier = 1.0F;

    public VehicleBaseOuterRim(World world) {
        super(world);
        this.setSize(2.0F, 1.0F);
        this.isImmuneToFire = true;
    }

    @Override
    protected void entityInit() {
        super.entityInit();
        this.dataWatcher.addObject(YAW_DW, Float.valueOf(0.0F));
        this.dataWatcher.addObject(PITCH_DW, Float.valueOf(0.0F));
    }

    public float getRealYaw() {
        return this.dataWatcher.getWatchableObjectFloat(YAW_DW);
    }

    public void setRealYaw(float yaw) {
        this.dataWatcher.updateObject(YAW_DW, Float.valueOf(yaw));
    }

    public float getRealPitch() {
        return this.dataWatcher.getWatchableObjectFloat(PITCH_DW);
    }

    public void setRealPitch(float pitch) {
        this.dataWatcher.updateObject(PITCH_DW, Float.valueOf(pitch));
    }

    @Override
    public boolean interact(EntityPlayer player) {
        if (!this.worldObj.isRemote && this.riddenByEntity == null) {
            player.mountEntity(this);
            return true;
        }
        return false;
    }

    @Override
    protected boolean canDespawn() {
        return false;
    }

    @Override
    public void fall(float distance) {
        // no fall damage
    }

    @Override
    public void collideWithEntity(Entity e) {
        if (e != this.riddenByEntity && (Math.abs(this.motionX) + Math.abs(this.motionZ)) > 0.1D) {
            e.attackEntityFrom(DamageSource.cactus, 2.0F);
        }
    }

    @Override
    public void onUpdate() {
        super.onUpdate();

        // Keep yaw/pitch synced
        if (this.riddenByEntity != null) {
            this.setRealYaw(this.riddenByEntity.rotationYaw);
            this.setRealPitch(this.riddenByEntity.rotationPitch);
        } else {
            this.rotationYaw = this.getRealYaw();
            this.rotationPitch = this.getRealPitch();
        }

        this.moveEntityWithHeading(0.0F, 0.0F);
    }

    @Override
    public void updateRiderPosition() {
        if (this.riddenByEntity != null) {
            this.riddenByEntity.setPosition(this.posX, this.posY + this.getMountedYOffset() + this.vehicYOffset, this.posZ);
        }
    }

    @Override
    protected void func_145780_a(int x, int y, int z, Block block) {
        this.playSound("outerrim:vehicle.default.move", 0.2F, 1.0F);
    }
}

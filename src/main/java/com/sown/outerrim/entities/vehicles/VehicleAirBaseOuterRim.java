package com.sown.outerrim.entities.vehicles;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

/**
 * Basic hover/flight movement and thrust handling.
 */
public class VehicleAirBaseOuterRim extends VehicleBaseOuterRim {

    public static final int HOVER_MODE_DW = 18;
    public float gravity = 0.015F;
    public float thrust = 0.0F;

    public VehicleAirBaseOuterRim(World world) {
        super(world);
        this.dataWatcher.addObject(HOVER_MODE_DW, 0);
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(50.0D);
    }

    public boolean getHoverMode() {
        return this.dataWatcher.getWatchableObjectInt(HOVER_MODE_DW) == 1;
    }

    public void setHoverMode(boolean hover) {
        this.dataWatcher.updateObject(HOVER_MODE_DW, hover ? 1 : 0);
    }

    @Override
    public void moveEntityWithHeading(float strafe, float forward) {
        if (this.riddenByEntity instanceof EntityPlayer) {
            EntityPlayer pilot = (EntityPlayer) this.riddenByEntity;

            // Pitch affects vertical motion
            this.motionY = -pilot.rotationPitch / 180F * this.thrust;

            if (pilot.moveForward > 0.0F) {
                this.thrust += 0.05F;
            } else if (pilot.moveForward < 0.0F) {
                this.thrust -= 0.05F;
            } else {
                this.thrust *= 0.98F;
            }

            if (this.thrust < 0.0F) this.thrust = 0.0F;
            if (this.thrust > this.moveModifier) this.thrust = this.moveModifier;

            float yawRad = this.rotationYaw * (float) Math.PI / 180.0F;
            float forwardMotion = this.thrust * 0.4F;

            this.motionX += -MathHelper.sin(yawRad) * forwardMotion;
            this.motionZ += MathHelper.cos(yawRad) * forwardMotion;

            if (!this.getHoverMode()) {
                this.motionY -= this.gravity;
            }
        }

        super.moveEntityWithHeading(strafe, forward);
    }
}

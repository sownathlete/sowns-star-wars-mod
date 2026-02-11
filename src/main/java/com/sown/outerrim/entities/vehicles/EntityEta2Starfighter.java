package com.sown.outerrim.entities.vehicles;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

/**
 * ETA-2 Actis-class Starfighter entity.
 * Similar to the N-1 Starfighter but lighter and faster.
 */
public class EntityEta2Starfighter extends VehicleAirBaseOuterRim {

    public EntityEta2Starfighter(World world) {
        super(world);
        this.setSize(2.6F, 1.2F);
        this.vehicYOffset = -1.3F;
        this.moveModifier = 2.3F; // faster and lighter
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(45.0D);
        this.setHealth(45.0F);
    }

    @Override
    public boolean interact(EntityPlayer player) {
        if (super.interact(player)) {
            this.worldObj.playSoundAtEntity(this, "outerrim:vehicle.eta2.start", 0.6F, 1.0F);
            return true;
        }
        return false;
    }

    @Override
    public void onDeath(DamageSource source) {
        super.onDeath(source);
        if (!this.worldObj.isRemote) {
            this.worldObj.createExplosion(this, this.posX, this.posY, this.posZ, 3.5F, true);
        }
        this.worldObj.playSoundAtEntity(this, "outerrim:vehicle.eta2.destroy", 1.0F, 1.0F);
    }

    @Override
    public String getCommandSenderName() {
        return "ETA-2 Starfighter";
    }

    @Override
    protected String getDeathSound() {
        return "outerrim:vehicle.eta2.destroy";
    }

    @Override
    protected String getLivingSound() {
        return "outerrim:vehicle.eta2.move";
    }
}

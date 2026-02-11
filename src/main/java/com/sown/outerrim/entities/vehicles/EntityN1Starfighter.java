package com.sown.outerrim.entities.vehicles;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class EntityN1Starfighter extends VehicleAirBaseOuterRim {

    public EntityN1Starfighter(World world) {
        super(world);
        this.setSize(3.0F, 1.5F);
        this.vehicYOffset = -1.5F;
        this.moveModifier = 1.75F;
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(60.0D);
        this.setHealth(60.0F);
    }

    @Override
    public boolean interact(EntityPlayer player) {
        if (super.interact(player)) {
            this.worldObj.playSoundAtEntity(this, "outerrim:vehicle.n1.start", 0.5F, 1.0F);
            return true;
        }
        return false;
    }

    @Override
    public void onDeath(DamageSource src) {
        super.onDeath(src);
        if (!this.worldObj.isRemote) {
            this.worldObj.createExplosion(this, this.posX, this.posY, this.posZ, 4.0F, true);
        }
        this.worldObj.playSoundAtEntity(this, "outerrim:vehicle.n1.destroy", 1.0F, 1.0F);
    }

    @Override
    public String getCommandSenderName() {
        return "N-1 Starfighter";
    }

    @Override
    protected String getDeathSound() {
        return "outerrim:vehicle.n1.destroy";
    }

    @Override
    protected String getLivingSound() {
        return "outerrim:vehicle.n1.move";
    }
}

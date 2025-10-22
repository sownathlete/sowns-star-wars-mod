/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.world.World
 */
package com.sown.outerrim.registry;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class EntityBlasterVariableBolt
extends EntityBlasterBoltBase {
    public EntityBlasterVariableBolt(World par1World) {
        super(par1World, 3.0f);
    }

    public EntityBlasterVariableBolt(World par1World, float damage) {
        super(par1World, damage);
    }

    public EntityBlasterVariableBolt(World par1World, double par2, double par4, double par6, float damage) {
        super(par1World, par2, par4, par6, damage);
    }

    public EntityBlasterVariableBolt(World par1World, EntityLivingBase par2EntityLivingBase, float damage) {
        super(par1World, par2EntityLivingBase, damage);
    }

    @Override
    public void recreate(EntityPlayer hit) {
        EntityBlasterVariableBolt bolt = new EntityBlasterVariableBolt(this.worldObj, (EntityLivingBase)hit, 3.0f);
        this.worldObj.spawnEntityInWorld((Entity)bolt);
        this.setDead();
    }
    
    @Override
    protected void onImpact(MovingObjectPosition position) {
        if (position.entityHit != null) {
            // Check if the hit entity is a living entity (like a mob)
            if (position.entityHit instanceof EntityLivingBase) {
                EntityLivingBase living = (EntityLivingBase) position.entityHit;

                // Deal damage. 3 hearts = 6 damage points (since 1 heart = 2 damage points)
                living.attackEntityFrom(DamageSource.causeThrownDamage(this, this.getThrower()), 6.0f);
            }
        }

        // Immediately set the bolt to dead, preventing any bouncing or ricocheting
        this.setDead();
    }


}


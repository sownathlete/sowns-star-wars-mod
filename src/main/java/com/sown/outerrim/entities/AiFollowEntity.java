/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.ai.EntityAIBase
 *  net.minecraft.pathfinding.PathNavigate
 */
package com.sown.outerrim.entities;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.pathfinding.PathNavigate;

public class AiFollowEntity
extends EntityAIBase {
    private EntityLiving theEntity;
    public Entity targetEntity;
    private double speed;
    private int ticksUntilPathUpdate;

    public AiFollowEntity(EntityLiving thisEntity, Entity target, double speed) {
        this.theEntity = thisEntity;
        this.targetEntity = target;
        this.speed = speed;
    }

    public boolean shouldExecute() {
        return this.theEntity != null && this.targetEntity != null && this.targetEntity.isEntityAlive();
    }

    public boolean continueExecuting() {
        return this.shouldExecute();
    }

    public void startExecuting() {
        this.ticksUntilPathUpdate = 0;
    }

    public void resetTask() {
        this.targetEntity = null;
    }

    public void updateTask() {
        if (--this.ticksUntilPathUpdate <= 0) {
            this.ticksUntilPathUpdate = 10;
            this.theEntity.getNavigator().tryMoveToEntityLiving(this.targetEntity, this.speed);
        }
    }
}


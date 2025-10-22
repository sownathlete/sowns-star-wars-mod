package com.sown.outerrim.entities;

import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
import net.minecraft.entity.ai.EntityAIPanic;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class EntityGM12L1 extends EntityCreature {

    public EntityGM12L1(World worldIn) {
        super(worldIn);
        // Human-sized: width 0.6, height 1.8
        this.setSize(0.6F, 1.8F);
        this.getNavigator().setCanSwim(true);

        // AI Tasks
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(1, new EntityAIPanic(this, 1.25D));
        this.tasks.addTask(2, new EntityAIMoveTowardsRestriction(this, 1.0D));
        this.tasks.addTask(3, new EntityAIWander(this, 0.6D));
        this.tasks.addTask(4, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(5, new EntityAILookIdle(this));
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        // Follow range
        this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(30.0D);
        // Max health (10 hearts)
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(20.0D);
        // Movement speed
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.25D);
        // (No attack damage unless explicitly needed)
        // this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(2.0D);
    }

    @Override
    public String getCommandSenderName() {
        return "GM12-L1";
    }

    @Override
    protected String getLivingSound() {
        // e.g. return "outerrim:mob.gm12l1.say";
        return null;
    }

    @Override
    protected String getHurtSound() {
        // e.g. return "outerrim:mob.gm12l1.hurt";
        return null;
    }

    @Override
    protected String getDeathSound() {
        // e.g. return "outerrim:mob.gm12l1.die";
        return null;
    }

    @Override
    public int getTalkInterval() {
        // Once every 60 seconds (20 ticks = 1 second)
        return 1200;
    }

    @Override
    public boolean canDespawn() {
        // Prevent this entity from despawning naturally
        return false;
    }
}

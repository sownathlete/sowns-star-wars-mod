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

public class EntityKesselMineWorker extends EntityCreature {

    public EntityKesselMineWorker(World worldIn) {
        super(worldIn);
        // Size: human sized but slightly shorter (tweak as needed)
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
        // Follow range (how far it can notice players)
        this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(30.0D);
        // Health (10 hearts)
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(20.0D);
        // Movement speed
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.25D);

        // --- REMOVE or COMMENT OUT attackDamage if you do not explicitly register it ---
        // this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(2.0D);
    }

    @Override
    public String getCommandSenderName() {
        return "Kessel Mine Worker";
    }

    @Override
    protected String getLivingSound() {
        // e.g. return "outerrim:mob.kessel.mineworker.say"; 
        return null;
    }

    @Override
    protected String getHurtSound() {
        // e.g. return "outerrim:mob.kessel.mineworker.hurt";
        return null;
    }

    @Override
    protected String getDeathSound() {
        // e.g. return "outerrim:mob.kessel.mineworker.die";
        return null;
    }

    @Override
    public int getTalkInterval() {
        return 1200; // once every 60 seconds (20 ticks = 1 second)
    }

    @Override
    public boolean canDespawn() {
        // Prevent natural despawning so workers persist
        return false;
    }
}

package com.sown.outerrim.entities;

import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.util.ChatComponentText;

public class MobYoda extends EntityTameable {
    private float currentRobeTilt = 0.0F;

    public MobYoda(World worldIn) {
        super(worldIn);
        this.setSize(0.6F, 1.5F); // Adjust to Yoda's size
        this.getNavigator().setAvoidsWater(true);

        // Add AI tasks
        this.tasks.addTask(1, new EntityAISwimming(this));
        this.tasks.addTask(2, this.aiSit);
        this.tasks.addTask(4, new EntityAIFollowOwner(this, 1.0D, 10.0F, 2.0F));
        this.tasks.addTask(5, new EntityAIWander(this, 1.0D));
        this.tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(7, new EntityAILookIdle(this));

        this.targetTasks.addTask(1, new EntityAIOwnerHurtByTarget(this));
        this.targetTasks.addTask(2, new EntityAIOwnerHurtTarget(this));

        this.setTamed(false); // Yoda starts untamed
    }

    @Override
    public void onLivingUpdate() {
        super.onLivingUpdate();

        // Update the robe tilt value based on movement
        float targetRobeTilt = this.isMoving() ? 0.295F : 0.0F; // ~16.91 degrees when walking
        currentRobeTilt += (targetRobeTilt - currentRobeTilt) * 0.1F; // Smooth transition
    }

    public float getCurrentRobeTilt() {
        return currentRobeTilt;
    }

    private boolean isMoving() {
        return this.motionX != 0 || this.motionZ != 0; // Checks if Yoda is moving
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(30.0D); // More health
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.3D); // Moderate speed
    }

    @Override
    public void setTamed(boolean tamed) {
        super.setTamed(tamed);
        if (tamed) {
            this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(40.0D);
        } else {
            this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(30.0D);
        }
    }

    @Override
    public EntityAgeable createChild(EntityAgeable ageable) {
        return null; // Yoda doesn't breed
    }

    @Override
    public void onDeath(DamageSource cause) {
        if (this.isTamed() && this.getOwner() instanceof EntityPlayer) {
            ((EntityPlayer) this.getOwner()).addChatMessage(new ChatComponentText("Yoda has died."));
        }
        super.onDeath(cause);
    }

    @Override
    protected void dropFewItems(boolean wasRecentlyHit, int lootingModifier) {
        // Drop Yoda's lightsaber on death
        this.dropItem(Items.diamond_sword, 1);
    }

    // Sound for ambient (living) sounds
    @Override
    protected String getLivingSound() {
        return "outerrim:entity.yoda.ambient"; // Use Yoda's ambient sounds
    }

    // Sound for hurt events
    @Override
    protected String getHurtSound() {
        return "outerrim:entity.yoda.hurt"; // Use Yoda's hurt sounds
    }

    // Sound for death events
    @Override
    protected String getDeathSound() {
        return "outerrim:entity.yoda.death"; // Use Yoda's death sounds
    }
    
    @Override
    public int getTalkInterval() {
        return 1200; // 1200 ticks = 60 seconds
    }
}

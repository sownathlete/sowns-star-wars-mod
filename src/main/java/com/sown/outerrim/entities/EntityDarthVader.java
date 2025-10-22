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

public class EntityDarthVader extends EntityCreature {

    public EntityDarthVader(World worldIn) {
        super(worldIn);
        this.setSize(0.6F, 1.95F);
        this.getNavigator().setCanSwim(true);

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
        this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(35.0D);
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(20.0D);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.30D);
    }

    @Override
    public String getCommandSenderName() {
        return "Darth Vader";
    }

    @Override
    protected String getLivingSound() {
        return "outerrim:mob.darth_vader.say";
    }

    @Override
    protected String getHurtSound() {
        return "outerrim:mob.darth_vader.hurt";
    }

    @Override
    protected String getDeathSound() {
        return "outerrim:mob.darth_vader.die";
    }

    @Override
    public int getTalkInterval() {
        return 1200;
    }

    @Override
    public boolean canDespawn() {
        return false;
    }

    @Override
    public void onLivingUpdate() {
        super.onLivingUpdate();
        if (this.ticksExisted == 1 && !this.worldObj.isRemote) {
            this.worldObj.playSoundEffect(
                this.posX, this.posY, this.posZ,
                "outerrim:music.hero.vader.intro",
                1.0F, 1.0F
            );
        }
    }
}

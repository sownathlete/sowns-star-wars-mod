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

public class EntityCountDooku extends EntityCreature {

    public EntityCountDooku(World worldIn) {
        super(worldIn);
        // Size: roughly human-sized
        this.setSize(0.6F, 1.95F);
        this.getNavigator().setCanSwim(true);

        // AI Tasks
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(1, new EntityAIPanic(this, 1.25D));
        this.tasks.addTask(2, new EntityAIMoveTowardsRestriction(this, 1.0D));
        this.tasks.addTask(3, new EntityAIWander(this, 0.6D));
        this.tasks.addTask(4, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(5, new EntityAILookIdle(this));
        // If you want him to attack, you could add e.g.:
        // this.targetTasks.addTask(1, new EntityAINearestAttackableTarget<>(this, EntityPlayer.class, true));
        // this.tasks.addTask(6, new EntityAIAttackMelee(this, 1.0D, false));
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        // Detect players from afar
        this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(35.0D);
        // Health (e.g. 20 = 10 hearts)
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(20.0D);
        // Movement speed
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.30D);
        // Uncomment if you add attack AI:
        // this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(4.0D);
    }

    @Override
    public String getCommandSenderName() {
        return "Count Dooku";
    }

    @Override
    protected String getLivingSound() {
        return "outerrim:mob.count_dooku.say";
    }

    @Override
    protected String getHurtSound() {
        return "outerrim:mob.count_dooku.hurt";
    }

    @Override
    protected String getDeathSound() {
        return "outerrim:mob.count_dooku.die";
    }

    @Override
    public int getTalkInterval() {
        // roughly once per minute
        return 1200;
    }

    @Override
    public boolean canDespawn() {
        // Keep him around
        return false;
    }

    @Override
    public void onLivingUpdate() {
        super.onLivingUpdate();
        // Play hero intro music on spawn (first tick)
        if (this.ticksExisted == 1 && !this.worldObj.isRemote) {
            this.worldObj.playSoundEffect(
                this.posX, this.posY, this.posZ,
                "outerrim:music.hero.dooku.intro",
                1.0F, 1.0F
            );
        }
    }
}

package com.sown.outerrim.entities;

import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class MobCoruscantCommoner extends EntityCreature {
    public AiFollowEntity aiFollowEntity;

    public MobCoruscantCommoner(World worldIn) {
        super(worldIn);
        this.setSize(1.0f, 2.0f);
        this.getNavigator().setCanSwim(true);
        this.aiFollowEntity = new AiFollowEntity((EntityLiving) this, null, 0.5);
        this.tasks.addTask(0, (EntityAIBase) this.aiFollowEntity);
        this.getNavigator().setEnterDoors(true);
        this.tasks.addTask(1, (EntityAIBase) new AiFreqMove((EntityLiving) this, 0.30000001192092896, 20));
        this.tasks.addTask(2, (EntityAIBase) new EntityAIAttackOnCollide((EntityCreature) this, EntityPlayer.class, 0.25, false));

        boolean isMale = this.rand.nextBoolean(); // Randomly assign gender
        this.getDataWatcher().updateObject(28, isMale ? 1 : 0); // Store gender in data watcher

        int textureIndex = isMale ? this.rand.nextInt(3) + 3 : this.rand.nextInt(3);
        this.getDataWatcher().updateObject(25, textureIndex);
    }

    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(50.0);
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(20.0);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.3);
    }

    protected void entityInit() {
        super.entityInit();
        this.getDataWatcher().addObject(25, 0);
        this.getDataWatcher().addObject(26, this.rand.nextInt(10));
        this.getDataWatcher().addObject(27, 0);
        this.getDataWatcher().addObject(28, 0); // Initialize gender watchable object
    }

    public boolean isMale() {
        return this.getDataWatcher().getWatchableObjectInt(28) == 1;
    }

    public String getCommandSenderName() {
        return "Coruscant Commoner";
    }

    protected String getDeathSound() {
        return isMale() ? "outerrim:mob.commoner.die_male" : "outerrim:mob.commoner.die_female";
    }

    protected String getHurtSound() {
        return isMale() ? "outerrim:mob.commoner.hit_male" : "outerrim:mob.commoner.hit_female";
    }

    protected String getLivingSound() {
        return isMale() ? "outerrim:mob.commoner.say_male" : "outerrim:mob.commoner.say_female";
    }
    
    @Override
    public int getTalkInterval() {
        return 1200; // 1200 ticks = 60 seconds
    }
}
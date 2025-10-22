package com.sown.outerrim.entities;

import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIAvoidEntity;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class MobAlex
extends EntityCreature{
    public AiFollowEntity aiFollowEntity;

    public MobAlex(World worldIn) {
        super(worldIn);
        this.setSize(1.0f, 2.0f);
        this.getNavigator().setCanSwim(true);
        this.aiFollowEntity = new AiFollowEntity((EntityLiving) this, null, 0.5);
        this.tasks.addTask(0, (EntityAIBase) this.aiFollowEntity);
        this.getNavigator().setEnterDoors(true);
        this.tasks.addTask(3, (EntityAIBase) new AiFreqMove((EntityLiving) this, 0.30000001192092896, 20));
        this.tasks.addTask(2, (EntityAIBase) new EntityAIAttackOnCollide((EntityCreature) this, EntityPlayer.class, 0.25, false));
        this.tasks.addTask(4, new AiRandomSneak(this));

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
    
    protected String getDeathSound() {
        return "game.player.die"; // Default player death sound
    }

    protected String getHurtSound() {
        return "game.player.hurt"; // Default player hurt sound
    }
    
    @Override
    public int getTalkInterval() {
        return 1200; // 1200 ticks = 60 seconds
    }
}


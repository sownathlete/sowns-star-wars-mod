package com.sown.outerrim.entities;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIEatGrass;
import net.minecraft.entity.ai.EntityAIFollowParent;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMate;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class MobBantha extends EntityAnimal
{
    
    public MobBantha(World world) {
        super(world);
        this.setSize(2.9F, 2.9F);
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(1, new EntityAIAttackOnCollide(this, 1.0D, true));
        this.tasks.addTask(2, new EntityAIMate(this, 1.0D));
        this.tasks.addTask(4, new EntityAILookIdle(this));
        this.tasks.addTask(5, new EntityAIWander(this, 1.0D));
        this.tasks.addTask(6, new EntityAIEatGrass(this));
        this.tasks.addTask(7, new EntityAIFollowParent(this, 1.25D));
        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true));
    }

    protected void entityInit()
    {
        super.entityInit();
        this.dataWatcher.addObject(16, Byte.valueOf((byte)0));
    }

    public boolean isAIEnabled()
    {
        return true;
    }

    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(100.0D);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.25D);
    }

    protected void collideWithEntity(Entity p_82167_1_)
    {
        if (p_82167_1_ instanceof IMob && this.getRNG().nextInt(20) == 0)
        {
            this.setAttackTarget((EntityLivingBase)p_82167_1_);
        }

        super.collideWithEntity(p_82167_1_);
    }

    @Override
    public boolean attackEntityAsMob(Entity target) {
        this.worldObj.setEntityState(this, (byte)4);
        boolean attackSuccessful = target.attackEntityFrom(DamageSource.causeMobDamage(this), 2.0f);
        if (attackSuccessful) {
            target.motionY += 0.1D;
        }

        this.playSound("mob.moose.attack", 1.0F, 1.0F);
        return attackSuccessful;
    }

    protected String getHurtSound()
    {
        return "mob.moose.hurt";
    }
    
    protected String getDeathSound()
    {
        return "mob.moose.death";
    }

    protected void func_145780_a(int p_145780_1_, int p_145780_2_, int p_145780_3_, Block p_145780_4_)
    {
        this.playSound("mob.moose.step", 0.15F, 1.0F);
    }

	@Override
	public EntityAgeable createChild(EntityAgeable p_90011_1_) {
        return new MobBantha(this.worldObj);
	}
}
package com.sown.outerrim.entities;

import java.util.List;

import com.sown.outerrim.registry.ItemRegister;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class EntityMimbaneseSoldier extends EntityCreature {

    public EntityMimbaneseSoldier(World world) {
        super(world);
        this.setSize(0.6F, 1.95F);
        this.getNavigator().setCanSwim(true);

        this.tasks.addTask(1, new EntityAISwimming(this));
        this.tasks.addTask(2, new EntityAIAttackOnCollide(this, EntityLivingBase.class, 1.2D, true));
        this.tasks.addTask(3, new EntityAIMoveTowardsRestriction(this, 1.0D));
        this.tasks.addTask(4, new EntityAIWander(this, 1.0D));
        this.tasks.addTask(5, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(6, new EntityAILookIdle(this));

        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
        this.targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntityZombie.class,   0, true));
        this.targetTasks.addTask(4, new EntityAINearestAttackableTarget(this, EntitySkeleton.class, 0, true));
        this.targetTasks.addTask(5, new EntityAINearestAttackableTarget(this, EntitySpider.class,   0, true));
        this.targetTasks.addTask(6, new EntityAINearestAttackableTarget(this, EntityEnderman.class, 0, true));
        this.targetTasks.addTask(7, new EntityAINearestAttackableTarget(this, EntityCreeper.class, 0, true));

        ItemStack mainHand = ItemRegister.getRegisteredItem("blaster") != null
                ? new ItemStack(ItemRegister.getRegisteredItem("blaster"))
                : new ItemStack(Items.iron_sword);
        this.setCurrentItemOrArmor(0, mainHand);
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(40.0D);
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(18.0D);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.30D);
        this.getAttributeMap().registerAttribute(SharedMonsterAttributes.attackDamage);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(5.0D);
    }

    @Override
    public boolean attackEntityAsMob(Entity target) {
        float dmg = (float) this.getEntityAttribute(SharedMonsterAttributes.attackDamage).getAttributeValue();
        return target.attackEntityFrom(DamageSource.causeMobDamage(this), dmg);
    }

    @Override
    public void onDeath(DamageSource cause) {
        super.onDeath(cause);
        if (!this.worldObj.isRemote) {
            // guaranteed mud ball, plus chance for rifle to drop
            this.dropItem(Items.clay_ball, 1);
            if (this.rand.nextFloat() < 0.15F) {
                this.entityDropItem(new ItemStack(Items.stick), 0.0F);
            }
        }
    }

    @Override
    protected String getLivingSound() { return "outerrim:entity.mimbanese.ambient"; }
    @Override
    protected String getHurtSound()   { return "outerrim:entity.mimbanese.hurt";    }
    @Override
    protected String getDeathSound()  { return "outerrim:entity.mimbanese.death";   }

    @Override
    public boolean isAIEnabled() { return true; }

    @Override
    protected float applyArmorCalculations(DamageSource source, float damage) {
        return super.applyArmorCalculations(source, damage * 0.95F);
    }
}

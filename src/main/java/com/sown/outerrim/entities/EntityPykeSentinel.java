package com.sown.outerrim.entities;

import java.util.List;

import com.sown.outerrim.registry.ItemRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.monster.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class EntityPykeSentinel extends EntityCreature {

    public EntityPykeSentinel(World worldIn) {
        super(worldIn);
        this.setSize(0.9F, 2.2F);
        this.getNavigator().setCanSwim(true);

        // AI tasks
        this.tasks.addTask(1, new EntityAISwimming(this));
        this.tasks.addTask(2, new EntityAIAttackOnCollide(this, EntityLivingBase.class, 1.1D, true));
        this.tasks.addTask(3, new EntityAIMoveTowardsRestriction(this, 1.0D));
        this.tasks.addTask(4, new EntityAIWander(this, 1.0D));
        this.tasks.addTask(5, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(6, new EntityAILookIdle(this));

        // Target tasks
        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
        this.targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntityZombie.class, 0, true));
        this.targetTasks.addTask(4, new EntityAINearestAttackableTarget(this, EntitySkeleton.class, 0, true));
        this.targetTasks.addTask(5, new EntityAINearestAttackableTarget(this, EntityCreeper.class, 0, true));
        this.targetTasks.addTask(6, new EntityAINearestAttackableTarget(this, EntitySpider.class, 0, true));
        this.targetTasks.addTask(7, new EntityAINearestAttackableTarget(this, EntityEnderman.class, 0, true));

        // Only give it a weapon if the item is registered properly
        Item pykeWeapon = ItemRegister.getRegisteredItem("pykeStaff");
        if (this.rand.nextFloat() < 0.5F && pykeWeapon != null) {
            this.setCurrentItemOrArmor(0, new ItemStack(pykeWeapon, 1, 0));
        }
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(40.0);
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(24.0);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.28);
        this.getAttributeMap().registerAttribute(SharedMonsterAttributes.attackDamage);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(7.0);
    }

    @Override
    public boolean attackEntityFrom(DamageSource source, float amount) {
        if (this.isEntityInvulnerable()) {
            return false;
        } else {
            Entity entity = source.getEntity();
            if (entity instanceof EntityPlayer) {
                this.becomeAngryAt(entity);
                List<Entity> nearby = this.worldObj.getEntitiesWithinAABBExcludingEntity(this, this.boundingBox.expand(32.0D, 32.0D, 32.0D));
                for (Entity other : nearby) {
                    if (other instanceof EntityPykeSentinel) {
                        ((EntityPykeSentinel) other).becomeAngryAt(entity);
                    }
                }
            }
            return super.attackEntityFrom(source, amount);
        }
    }

    private void becomeAngryAt(Entity target) {
        this.entityToAttack = target;
        if (target instanceof EntityLivingBase) {
            this.setRevengeTarget((EntityLivingBase) target);
        }
    }

    @Override
    public boolean isAIEnabled() {
        return true;
    }

    @Override
    public boolean attackEntityAsMob(Entity entityIn) {
        float damage = (float) this.getEntityAttribute(SharedMonsterAttributes.attackDamage).getAttributeValue();
        return entityIn.attackEntityFrom(DamageSource.causeMobDamage(this), damage);
    }

    @Override
    protected void entityInit() {
        super.entityInit();
    }

    @Override
    public String getCommandSenderName() {
        return "Pyke Sentinel";
    }

    @Override
    protected String getDeathSound() {
        return "outerrim:mob.pykesentinel.die";
    }

    @Override
    protected String getHurtSound() {
        return "outerrim:mob.pykesentinel.hit";
    }

    @Override
    protected String getLivingSound() {
        return "outerrim:mob.pykesentinel.say";
    }

    @Override
    public int getTalkInterval() {
        return 1200;
    }
}

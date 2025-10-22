package com.sown.outerrim.entities;

import java.util.List;

import com.sown.outerrim.registry.ItemRegister;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIAvoidEntity;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMate;
import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAITempt;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class EntitySandBeast
extends EntityTameable{
    public AiFollowEntity aiFollowEntity;

    public EntitySandBeast(World worldIn) {
        super(worldIn);
        this.setSize(1.0f, 2.0f);
        this.getNavigator().setCanSwim(true);

        // Add AI tasks (copied from Battle Droid and adjusted for melee)
        this.tasks.addTask(1, new EntityAISwimming(this));  // Can swim
        this.tasks.addTask(2, new EntityAIAttackOnCollide(this, EntityLivingBase.class, 1.0D, true));  // Melee attack on target
        this.tasks.addTask(3, new EntityAIMoveTowardsRestriction(this, 1.0D));  // Move towards restriction (if needed)
        this.tasks.addTask(4, new EntityAIWander(this, 1.0D));  // Wander around
        this.tasks.addTask(5, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));  // Watch closest player
        this.tasks.addTask(6, new EntityAILookIdle(this));  // Idle look-around behavior
        this.tasks.addTask(7, new EntityAITempt(this, 1.25D, Items.bread, false)); // Tempt with bread
        this.tasks.addTask(8, new EntityAIMate(this, 1.0D));

        // Add target tasks (copied from Battle Droid with melee focus)
        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true));  // Attack when hurt
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));  // Attack players
        this.targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntityZombie.class, 0, true));  // Attack zombies
        this.targetTasks.addTask(4, new EntityAINearestAttackableTarget(this, EntitySkeleton.class, 0, true));  // Attack skeletons
        this.targetTasks.addTask(5, new EntityAINearestAttackableTarget(this, EntityCreeper.class, 0, true));  // Attack creepers
        this.targetTasks.addTask(6, new EntityAINearestAttackableTarget(this, EntitySpider.class, 0, true));  // Attack spiders
        this.targetTasks.addTask(7, new EntityAINearestAttackableTarget(this, EntityEnderman.class, 0, true));  // Attack endermen

        // Random skin index for variety
        this.getDataWatcher().updateObject(25, this.rand.nextInt(3));
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(50.0);  // Large follow range
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(20.0);  // Tusken Raider health
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.3);  // Movement speed
        this.getAttributeMap().registerAttribute(SharedMonsterAttributes.attackDamage);  // Melee attack damage
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(6.0);  // Tusken Raider melee attack strength
    }

    @Override
    public boolean attackEntityFrom(DamageSource source, float amount) {
        if (this.isEntityInvulnerable()) {
            return false;
        }

        Entity entity = source.getEntity();

        if (this.isTamed() && entity instanceof EntityPlayer && this.func_152113_b().equals(entity.getUniqueID())) {
            // Do not react to the owner attacking
            return false;
        }

        if (entity instanceof EntityPlayer) {
            // Become angry at the attacker
            this.becomeAngryAt(entity);

            // Make nearby Tusken Raiders angry too
            List<Entity> nearbyEntities = this.worldObj.getEntitiesWithinAABBExcludingEntity(this, this.boundingBox.expand(32.0D, 32.0D, 32.0D));
            for (Entity nearbyEntity : nearbyEntities) {
                if (nearbyEntity instanceof EntitySandBeast) {
                    EntitySandBeast nearbyTuskenRaider = (EntitySandBeast) nearbyEntity;
                    nearbyTuskenRaider.becomeAngryAt(entity);
                }
            }
        }

        return super.attackEntityFrom(source, amount);
    }

    // Make the Tusken Raider aggressive toward a target
    private void becomeAngryAt(Entity target) {
        this.entityToAttack = target;
        this.setRevengeTarget((EntityLivingBase) target);
    }

	public boolean isAIEnabled() {
		return true;
	}

	@Override
	public void setAttackTarget(EntityLivingBase target) {
	    if (this.isTamed()) {
	        // If tamed, do not target the owner
	        if (target instanceof EntityPlayer && this.func_152113_b().equals(target.getUniqueID())) {
	            return; // Do nothing if the target is the owner
	        }
	    }
	    
	    // If not the owner, or if untamed, call the superclass logic
	    super.setAttackTarget(target);

	    // Handle anger state for non-tamed Tuskens
	    if (target == null) {
	        this.setAngry(false);
	    } else if (!this.isTamed()) {
	        this.setAngry(true);
	    }
	}

	public boolean isAngry() {
		return (this.dataWatcher.getWatchableObjectByte(16) & 2) != 0;
	}

	public void setAngry(boolean angry) {
		byte b0 = this.dataWatcher.getWatchableObjectByte(16);

		if (angry) {
			this.dataWatcher.updateObject(16, Byte.valueOf((byte) (b0 | 2)));
		} else {
			this.dataWatcher.updateObject(16, Byte.valueOf((byte) (b0 & -3)));
		}
	}

    // Perform melee attack
    @Override
    public boolean attackEntityAsMob(Entity entityIn) {
        float damage = (float) this.getEntityAttribute(SharedMonsterAttributes.attackDamage).getAttributeValue();
        return entityIn.attackEntityFrom(DamageSource.causeMobDamage(this), damage);
    }

    @Override
    protected void entityInit() {
        super.entityInit();
        this.getDataWatcher().addObject(25, 0);
        this.getDataWatcher().addObject(26, this.rand.nextInt(10));
        this.getDataWatcher().addObject(27, 0);
    }

    public String getCommandSenderName() {
        return "Sand Beast";
    }

    protected String getDeathSound() {
        return "outerrim:entity.tusken.death";
    }

    protected String getHurtSound() {
        return "outerrim:entity.tusken.hurt";
    }

    protected String getLivingSound() {
        return "outerrim:entity.tusken.ambient";
    }

    public int getTalkInterval() {
        return 400;
    }

    @Override
    public boolean isBreedingItem(ItemStack stack) {
        // Use bread as the breeding item (can replace with your custom item)
        return stack != null && stack.getItem() == Items.bread;
    }

    @Override
    public EntityAgeable createChild(EntityAgeable ageable) {
        EntitySandBeast child = new EntitySandBeast(this.worldObj);
        if (this.isTamed()) {
            child.setTamed(true);
            child.func_152115_b(this.func_152113_b()); // Copy owner's UUID
        }
        return child;
    }

}


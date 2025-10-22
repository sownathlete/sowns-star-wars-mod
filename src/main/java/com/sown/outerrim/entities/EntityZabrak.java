package com.sown.outerrim.entities;

import java.util.List;

import com.sown.outerrim.registry.ItemRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class EntityZabrak extends EntityTameable {

    public EntityZabrak(World worldIn) {
        super(worldIn);
        this.setSize(1.0F, 2.0F);  // Set the size similar to a humanoid creature
        this.getNavigator().setCanSwim(true);  // Allow the Zabrak to swim

        // Add AI tasks (copied from Battle Droid and adjusted for melee)
        this.tasks.addTask(1, new EntityAISwimming(this));  // Can swim
        this.tasks.addTask(2, new EntityAIAttackOnCollide(this, EntityLivingBase.class, 1.0D, true));  // Melee attack on target
        this.tasks.addTask(3, new EntityAIMoveTowardsRestriction(this, 1.0D));  // Move towards restriction (if needed)
        this.tasks.addTask(4, new EntityAIWander(this, 1.0D));  // Wander around
        this.tasks.addTask(5, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));  // Watch closest player
        this.tasks.addTask(6, new EntityAILookIdle(this));  // Idle look-around behavior

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

        // If the Zabrak is male, give it a 50% chance to spawn with a sword
        if (this.rand.nextFloat() < 0.5F) {
            this.setCurrentItemOrArmor(0, new ItemStack(ItemRegister.getRegisteredItem("nightbrotherMalletSword"), 1, 0));
        }
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(50.0);  // Large follow range
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(20.0);  // Zabrak health
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.3);  // Movement speed
        this.getAttributeMap().registerAttribute(SharedMonsterAttributes.attackDamage);  // Melee attack damage
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(6.0);  // Zabrak melee attack strength
    }

    // Handle being attacked
    @Override
    public boolean attackEntityFrom(DamageSource source, float amount) {
        if (this.isEntityInvulnerable()) {
            return false;
        } else {
            Entity entity = source.getEntity();

            if (entity instanceof EntityPlayer) {
                // Become angry at the attacker
                this.becomeAngryAt(entity);

                // Make nearby Zabraks angry too
                List<Entity> nearbyEntities = this.worldObj.getEntitiesWithinAABBExcludingEntity(this, this.boundingBox.expand(32.0D, 32.0D, 32.0D));
                for (Entity nearbyEntity : nearbyEntities) {
                    if (nearbyEntity instanceof EntityZabrak) {
                        EntityZabrak nearbyZabrak = (EntityZabrak) nearbyEntity;
                        nearbyZabrak.becomeAngryAt(entity);
                    }
                }
            }
            return super.attackEntityFrom(source, amount);
        }
    }

    // Make the Zabrak aggressive toward a target
    private void becomeAngryAt(Entity target) {
        this.entityToAttack = target;
        this.setRevengeTarget((EntityLivingBase) target);
    }

	public boolean isAIEnabled() {
		return true;
	}

	public void setAttackTarget(EntityLivingBase target) {
		super.setAttackTarget(target);

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

    // Set the custom name for the Zabrak
    @Override
    public String getCommandSenderName() {
        return "Zabrak";
    }

    // Sound effects for the Zabrak
    @Override
    protected String getDeathSound() {
        return "outerrim:mob.zabrak.die_male";
    }

    @Override
    protected String getHurtSound() {
        return "outerrim:mob.zabrak.hit_male";
    }

    @Override
    protected String getLivingSound() {
        return "outerrim:mob.zabrak.say_male";
    }
    
    @Override
    public int getTalkInterval() {
        return 1200; // 1200 ticks = 60 seconds
    }

	@Override
	public EntityAgeable createChild(EntityAgeable p_90011_1_) {
		// TODO Auto-generated method stub
		return null;
	}
}

package com.sown.outerrim.entities;

import com.sown.outerrim.registry.ItemRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.monster.*;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityB2BattleDroid extends EntityTameable implements IRangedAttackMob {

    private boolean isShooting = false; // Tracks if the droid is in a shooting state
    private int shootingTimer = 0; // Timer to reset the shooting state

    public EntityB2BattleDroid(World worldIn) {
        super(worldIn);
        this.setSize(1.2f, 2.2f); // B2 droids are slightly bigger than B1 droids
        this.getNavigator().setCanSwim(true);

        // Task assignment
        this.tasks.addTask(1, new EntityAISwimming(this));
        this.tasks.addTask(2, this.aiSit);
        this.tasks.addTask(3, new EntityAIAttackWithBow(this, 1.0D, 20, 20.0F)); // More range and accuracy
        this.tasks.addTask(4, new EntityAIAttackOnCollide(this, EntityLivingBase.class, 1.0D, true));
        this.tasks.addTask(5, new EntityAIFollowOwner(this, 1.0D, 10.0F, 2.0F));
        this.tasks.addTask(6, new EntityAIMoveTowardsRestriction(this, 1.0D));
        this.tasks.addTask(7, new EntityAIWander(this, 1.0D));
        this.tasks.addTask(8, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(9, new EntityAILookIdle(this));

        // Target tasks
        this.targetTasks.addTask(1, new EntityAIOwnerHurtByTarget(this));
        this.targetTasks.addTask(2, new EntityAIOwnerHurtTarget(this));
        this.targetTasks.addTask(3, new EntityAIHurtByTarget(this, true));

        // High priority: Target MobCloneTrooperBase and EntityReconTrooper first
        this.targetTasks.addTask(4, new EntityAINearestAttackableTarget(this, EntityCloneTrooperBase.class, 0, true));
        this.targetTasks.addTask(5, new EntityAINearestAttackableTarget(this, EntityReconTrooperBase.class, 0, true));

        // Other targets (players, zombies, skeletons, etc.)
        this.targetTasks.addTask(7, new EntityAINearestAttackableTarget(this, EntityZombie.class, 0, true));
        this.targetTasks.addTask(8, new EntityAINearestAttackableTarget(this, EntitySkeleton.class, 0, true));
        this.targetTasks.addTask(9, new EntityAINearestAttackableTarget(this, EntityCreeper.class, 0, true));
        this.targetTasks.addTask(10, new EntityAINearestAttackableTarget(this, EntitySpider.class, 0, true));
        this.targetTasks.addTask(11, new EntityAINearestAttackableTarget(this, EntityEnderman.class, 0, true));
        this.targetTasks.addTask(12, new EntityAINearestAttackableTarget(this, EntitySilverfish.class, 0, true));


        this.setTamed(false); // B2 droids start as untamed
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(60.0); // B2 has better range
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(40.0); // More health than B1
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.30); // Slightly faster
    }

    public boolean isAIEnabled() {
        return true;
    }

    @Override
    public void attackEntityWithRangedAttack(EntityLivingBase target, float distanceFactor) {
        this.isShooting = true;
        // Create a new laser projectile entity
        EntityLaserProjectileRed entityLaser = new EntityLaserProjectileRed(this.worldObj, this, distanceFactor);

        // Get the direction vectors from this entity to the target
        double dX = target.posX - this.posX;
        double dY = (target.posY + target.getEyeHeight()) - (this.posY + this.getEyeHeight());
        double dZ = target.posZ - this.posZ;
        double distance = MathHelper.sqrt_double(dX * dX + dZ * dZ);

        // Set the heading of the laser (including X, Y, Z motion vectors and velocity)
        entityLaser.setThrowableHeading(dX, dY, dZ, 1.6F, 0.0F);

        // Set the damage based on the distance factor
        entityLaser.setDamage((double) (distanceFactor * 2.0F));

        // Adjust the position to make sure the projectile originates from the entity's eye level
        entityLaser.setPosition(this.posX, this.posY + this.getEyeHeight(), this.posZ);

        // Spawn the projectile in the world
        this.worldObj.spawnEntityInWorld(entityLaser);

        // Play the blaster sound when firing
        this.playShootSound();

        // Set the droid to shooting state for 3 seconds (60 ticks)
        this.isShooting = true;
        this.shootingTimer = 60;
    }

    // Override to prevent laser projectile damage
    @Override
    public boolean attackEntityFrom(DamageSource source, float amount) {
        if (source.getSourceOfDamage() instanceof EntityLaserProjectileRed) {
            return false;  // Invulnerable to lasers
        }
        return super.attackEntityFrom(source, amount);
    }
    
	public void setTamed(boolean tamed) {
		super.setTamed(tamed);

		if (tamed) {
			this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(30.0D);
		} else {
			this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(20.0D);
		}
	}

	@Override
	public boolean interact(EntityPlayer player) {
		ItemStack itemstack = player.getCurrentEquippedItem();

		// Taming logic using a different item, e.g., a circuit board
		if (!this.isTamed() && itemstack != null
				&& itemstack.getItem() == Items.iron_ingot) {
			if (!this.worldObj.isRemote) {
				this.setTamed(true);
				this.func_152115_b(player.getUniqueID().toString());
				this.playTameEffect(true);
				this.worldObj.setEntityState(this, (byte) 7);
			}
			if (!player.capabilities.isCreativeMode) {
				--itemstack.stackSize;
			}
			return true;
		}

		// Armor and equipment handling remains the same
		if (this.isTamed() && this.func_152114_e(player)) {
			if (player.isSneaking() && itemstack != null) {
				// Handle armor and equipment as before
				if (itemstack.getItem() instanceof ItemArmor) {
					ItemArmor armor = (ItemArmor) itemstack.getItem();
					int armorSlot = 3 - armor.armorType;
					ItemStack currentArmor = this.getCurrentArmor(armorSlot);

					if (currentArmor != null && !this.worldObj.isRemote) {
						this.entityDropItem(currentArmor, 0.5F);
					}

					this.setCurrentItemOrArmor(armorSlot + 1, itemstack);
				} else {
					ItemStack currentItem = this.getHeldItem();
					if (currentItem != null && !this.worldObj.isRemote) {
						this.entityDropItem(currentItem, 0.5F);
					}

					this.setCurrentItemOrArmor(0, itemstack);
				}

				if (!player.capabilities.isCreativeMode) {
					player.inventory.setInventorySlotContents(player.inventory.currentItem, null);
				}
				return true;
			} else if (!this.worldObj.isRemote && itemstack == null) {
				this.aiSit.setSitting(!this.isSitting());
				this.isJumping = false;
				this.setPathToEntity(null);
				this.setTarget(null);
				this.setAttackTarget(null);
				return true;
			}
		}

		return super.interact(player);
	}

	public ItemStack getCurrentArmor(int slot) {
		return this.getEquipmentInSlot(slot + 1);
	}

    @Override
    public void onUpdate() {
        super.onUpdate();

        // If the B2 is shooting, decrease the timer
        if (this.isShooting) {
            if (this.shootingTimer > 0) {
                this.shootingTimer--;
            } else {
                this.isShooting = false; // Reset shooting state when the timer runs out
            }
        }
    }

    public boolean isShooting() {
        return this.isShooting;
    }

    @Override
    protected String getLivingSound() {
        return "outerrim:entity.b2.ambient"; // B2 droid ambient sound
    }

    @Override
    protected String getHurtSound() {
        return "outerrim:entity.b2.hurt"; // B2 droid hurt sound
    }

    @Override
    protected String getDeathSound() {
        return "outerrim:entity.b2.death"; // B2 droid death sound
    }

    public void playShootSound() {
        this.playSound("outerrim:entity.b1.droid_shoot", 1.0F, 1.0F); // B2 shoot sound
    }

    @Override
    public IEntityLivingData onSpawnWithEgg(IEntityLivingData data) {
        this.playSound("outerrim:entity.b2.spawn", 1.0F, 1.0F); // Play B2 droid spawn sound
        return super.onSpawnWithEgg(data);
    }

    @Override
    public EntityAgeable createChild(EntityAgeable ageable) {
        return null; // B2 droids don't breed
    }

    @Override
    public void onDeath(DamageSource cause) {
        if (this.isTamed() && this.getOwner() instanceof EntityPlayer) {
            ((EntityPlayer) this.getOwner())
                    .addChatMessage(new ChatComponentText(this.getCommandSenderName() + " has been destroyed."));
        }
        super.onDeath(cause);
    }
    
    @Override
    public int getTalkInterval() {
        return 1200; // 1200 ticks = 60 seconds
    }
}

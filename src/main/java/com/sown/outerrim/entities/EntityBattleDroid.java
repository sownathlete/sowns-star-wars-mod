package com.sown.outerrim.entities;

import com.sown.outerrim.registry.ItemRegister;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
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

public class EntityBattleDroid extends EntityTameable implements IRangedAttackMob {

	public EntityBattleDroid(World worldIn) {
		super(worldIn);
		this.setSize(1.0f, 2.0f);
		this.getNavigator().setCanSwim(false);
		this.tasks.addTask(1, new EntityAISwimming(this));
		this.tasks.addTask(2, this.aiSit);
		this.tasks.addTask(3, new EntityAIAttackWithBow(this, 1.0D, 20, 15.0F));
		this.tasks.addTask(4, new EntityAIAttackOnCollide(this, EntityLivingBase.class, 1.0D, true));
		this.tasks.addTask(5, new EntityAIFollowOwner(this, 1.0D, 10.0F, 2.0F));
		this.tasks.addTask(6, new EntityAIMoveTowardsRestriction(this, 1.0D));
		this.tasks.addTask(7, new EntityAIWander(this, 1.0D));
		this.tasks.addTask(8, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
		this.tasks.addTask(9, new EntityAILookIdle(this));
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

		this.setTamed(false);

		int textureIndex = this.rand.nextInt(3) + 3; // Use droid textures
		this.getDataWatcher().updateObject(25, textureIndex);
	}

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(40.0); // Decent range
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(20.0);  // Logical health
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.3); // Reasonable speed
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

	@Override
	protected void entityInit() {
		super.entityInit();
		this.getDataWatcher().addObject(25, 0);
		this.getDataWatcher().addObject(26, this.rand.nextInt(10));
		this.getDataWatcher().addObject(27, 0);
	}

	public boolean attackEntityAsMob(Entity entity) {
		int i = this.isTamed() ? 4 : 2;
		return entity.attackEntityFrom(DamageSource.causeMobDamage(this), (float) i);
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
	public void writeEntityToNBT(NBTTagCompound compound) {
		super.writeEntityToNBT(compound);
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound compound) {
		super.readEntityFromNBT(compound);
	}

	@Override
	public EntityAgeable createChild(EntityAgeable ageable) {
		return null;
	}

	@Override
	public void onDeath(DamageSource cause) {
		if (this.isTamed() && this.getOwner() != null && this.getOwner() instanceof EntityPlayer) {
			((EntityPlayer) this.getOwner())
					.addChatMessage(new ChatComponentText(this.getCommandSenderName() + " has been destroyed."));
		}
	}

    @Override
    public void attackEntityWithRangedAttack(EntityLivingBase target, float distanceFactor) {
        // Create a new laser projectile entity
        EntityLaserProjectileRed entityLaser = new EntityLaserProjectileRed(this.worldObj, this, distanceFactor);

        // Get the direction vectors from this entity to the target
        double dX = target.posX - this.posX;
        double dY = (target.posY + target.getEyeHeight()) - (this.posY + this.getEyeHeight());
        double dZ = target.posZ - this.posZ;
        double distance = MathHelper.sqrt_double(dX * dX + dZ * dZ);

        // Set the heading of the laser (including X, Y, Z motion vectors and velocity)
        entityLaser.setThrowableHeading(dX, dY, dZ, 1.6F, 0.0F);  // Remove the unnecessary vertical adjustment

        // Set the damage based on the distance factor
        entityLaser.setDamage((double) (distanceFactor * 2.0F));

        // Adjust the position to make sure the projectile originates from the entity's eye level
        entityLaser.setPosition(this.posX, this.posY + this.getEyeHeight(), this.posZ);

        // Spawn the projectile in the world
        this.worldObj.spawnEntityInWorld(entityLaser);

        // Play the blaster sound when firing
        this.playShootSound();
    }

    // Override to prevent laser projectile damage
    @Override
    public boolean attackEntityFrom(DamageSource source, float amount) {
        if (source.getSourceOfDamage() instanceof EntityLaserProjectileRed) {
            return false;  // Invulnerable to lasers
        }
        return super.attackEntityFrom(source, amount);
    }

	@Override
	protected String getLivingSound() {
		return "outerrim:entity.b1.ambient"; // Use ambient sound pool for when the droid is alive
	}

	@Override
	protected String getHurtSound() {
		return "outerrim:entity.b1.hurt"; // Use hurt sound pool for when the droid gets hurt
	}

	@Override
	protected String getDeathSound() {
		return "outerrim:entity.b1.death"; // Use death sound pool for when the droid dies
	}

	public void playMeleeSound() {
		this.playSound("outerrim:entity.b1.melee", 1.0F, 1.0F); // Play melee attack sound pool
	}

    public void playShootSound() {
        this.playSound("outerrim:entity.b1.droid_shoot", 1.0F, 1.0F);  // Sound event when the clone trooper shoots
    }

	public void playPanicSound() {
		this.playSound("outerrim:entity.b1.panic", 1.0F, 1.0F); // Play panic sound pool
	}

	@Override
	public IEntityLivingData onSpawnWithEgg(IEntityLivingData data) {
		this.playSound("outerrim:entity.b1.spawn", 1.0F, 1.0F); // Play spawn sound pool
		return super.onSpawnWithEgg(data);
	}
}

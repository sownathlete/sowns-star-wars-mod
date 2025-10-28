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

	// ===== Tunables (doubled ranges to match Droideka behavior) =====
	private static final double AGGRO_RANGE = 128.0D;
	private static final float SHOOT_RANGE = 68.0F;

	// “Never forget clones” sweep
	private static final int RETARGET_COOLDOWN_TICKS = 10; // every 0.5s
	private int retargetCooldown = RETARGET_COOLDOWN_TICKS;

	// Visual/animation flag you had
	private boolean isShooting = false;
	private int shootingTimer = 0;

	public EntityB2BattleDroid(World worldIn) {
		super(worldIn);
		this.setSize(1.2f, 2.2f);
		this.getNavigator().setCanSwim(true);

		// Tasks
		this.tasks.addTask(1, new EntityAISwimming(this));
		this.tasks.addTask(2, this.aiSit);
		this.tasks.addTask(3, new EntityAIAttackWithBow(this, 1.0D, 20, SHOOT_RANGE)); // use 100f shoot range
		this.tasks.addTask(4, new EntityAIAttackOnCollide(this, EntityLivingBase.class, 1.0D, true));
		this.tasks.addTask(5, new EntityAIFollowOwner(this, 1.0D, 10.0F, 2.0F));
		this.tasks.addTask(6, new EntityAIMoveTowardsRestriction(this, 1.0D));
		this.tasks.addTask(7, new EntityAIWander(this, 1.0D));
		this.tasks.addTask(8, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
		this.tasks.addTask(9, new EntityAILookIdle(this));

		// Targets
		this.targetTasks.addTask(1, new EntityAIOwnerHurtByTarget(this));
		this.targetTasks.addTask(2, new EntityAIOwnerHurtTarget(this));
		this.targetTasks.addTask(3, new EntityAIHurtByTarget(this, true));

		// Highest priority: clones; no LOS requirement so we don’t drop target
		this.targetTasks.addTask(4, new EntityAINearestAttackableTarget(this, EntityCloneTrooperBase.class, 0, false));
		this.targetTasks.addTask(5, new EntityAINearestAttackableTarget(this, EntityReconTrooperBase.class, 0, false));

		// Other targets (keep LOS true)
		this.targetTasks.addTask(7, new EntityAINearestAttackableTarget(this, EntityZombie.class, 0, true));
		this.targetTasks.addTask(8, new EntityAINearestAttackableTarget(this, EntitySkeleton.class, 0, true));
		this.targetTasks.addTask(9, new EntityAINearestAttackableTarget(this, EntityCreeper.class, 0, true));
		this.targetTasks.addTask(10, new EntityAINearestAttackableTarget(this, EntitySpider.class, 0, true));
		this.targetTasks.addTask(11, new EntityAINearestAttackableTarget(this, EntityEnderman.class, 0, true));
		this.targetTasks.addTask(12, new EntityAINearestAttackableTarget(this, EntitySilverfish.class, 0, true));

		this.setTamed(false);
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(AGGRO_RANGE);
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(40.0);
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.30);
	}

	@Override
	public void attackEntityWithRangedAttack(EntityLivingBase target, float distanceFactor) {
		this.isShooting = true;

		EntityLaserProjectileRed bolt = new EntityLaserProjectileRed(this.worldObj, this, distanceFactor);

		double dX = target.posX - this.posX;
		double dY = (target.posY + target.getEyeHeight()) - (this.posY + this.getEyeHeight());
		double dZ = target.posZ - this.posZ;

		bolt.setThrowableHeading(dX, dY, dZ, 1.6F, 0.0F);
		bolt.setDamage((double) (distanceFactor * 2.0F));
		bolt.setPosition(this.posX, this.posY + this.getEyeHeight(), this.posZ);

		this.worldObj.spawnEntityInWorld(bolt);
		this.playShootSound();

		// 3s shooting flag (client anims, etc.)
		this.shootingTimer = 60;
	}

	// Laser immunity (red bolt)
	@Override
	public boolean attackEntityFrom(DamageSource source, float amount) {
		if (source.getSourceOfDamage() instanceof EntityLaserProjectileRed)
			return false;
		return super.attackEntityFrom(source, amount);
	}

	@Override
	public void setTamed(boolean tamed) {
		super.setTamed(tamed);
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(tamed ? 30.0D : 20.0D);
	}

	@Override
	public boolean interact(EntityPlayer player) {
		ItemStack itemstack = player.getCurrentEquippedItem();

		// Tame with iron ingot
		if (!this.isTamed() && itemstack != null && itemstack.getItem() == Items.iron_ingot) {
			if (!this.worldObj.isRemote) {
				this.setTamed(true);
				this.func_152115_b(player.getUniqueID().toString());
				this.playTameEffect(true);
				this.worldObj.setEntityState(this, (byte) 7);
			}
			if (!player.capabilities.isCreativeMode)
				--itemstack.stackSize;
			return true;
		}

		// Armor/equipment handling (unchanged)
		if (this.isTamed() && this.func_152114_e(player)) {
			if (player.isSneaking() && itemstack != null) {
				if (itemstack.getItem() instanceof ItemArmor) {
					ItemArmor armor = (ItemArmor) itemstack.getItem();
					int armorSlot = 3 - armor.armorType;
					ItemStack currentArmor = this.getCurrentArmor(armorSlot);
					if (currentArmor != null && !this.worldObj.isRemote)
						this.entityDropItem(currentArmor, 0.5F);
					this.setCurrentItemOrArmor(armorSlot + 1, itemstack);
				} else {
					ItemStack currentItem = this.getHeldItem();
					if (currentItem != null && !this.worldObj.isRemote)
						this.entityDropItem(currentItem, 0.5F);
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
	public void onUpdate() {
		super.onUpdate();

		// Shooting flag countdown
		if (this.isShooting) {
			if (this.shootingTimer > 0)
				--this.shootingTimer;
			else
				this.isShooting = false;
		}

		// Server-side: force retarget to clones so we never “forget”
		if (!this.worldObj.isRemote) {
			if (--retargetCooldown <= 0) {
				retargetCooldown = RETARGET_COOLDOWN_TICKS;
				ensurePrimaryCloneTarget();
			}
		}
	}

	/**
	 * If no valid target (or too far), force-pick the nearest clone/recon within
	 * AGGRO_RANGE.
	 */
	private void ensurePrimaryCloneTarget() {
		EntityLivingBase curr = this.getAttackTarget();
		boolean needsTarget = (curr == null) || curr.isDead
				|| this.getDistanceSqToEntity(curr) > (AGGRO_RANGE * AGGRO_RANGE);

		if (!needsTarget)
			return;

		EntityLivingBase best = null;
		double bestDistSq = Double.MAX_VALUE;

		for (Object o : this.worldObj.getEntitiesWithinAABB(EntityCloneTrooperBase.class,
				this.boundingBox.expand(AGGRO_RANGE, AGGRO_RANGE, AGGRO_RANGE))) {
			EntityCloneTrooperBase e = (EntityCloneTrooperBase) o;
			if (e.isDead)
				continue;
			double d = this.getDistanceSqToEntity(e);
			if (d < bestDistSq) {
				bestDistSq = d;
				best = e;
			}
		}
		for (Object o : this.worldObj.getEntitiesWithinAABB(EntityReconTrooperBase.class,
				this.boundingBox.expand(AGGRO_RANGE, AGGRO_RANGE, AGGRO_RANGE))) {
			EntityReconTrooperBase e = (EntityReconTrooperBase) o;
			if (e.isDead)
				continue;
			double d = this.getDistanceSqToEntity(e);
			if (d < bestDistSq) {
				bestDistSq = d;
				best = e;
			}
		}

		if (best != null) {
			this.setAttackTarget(best);
		}
	}

	public boolean isShooting() {
		return this.isShooting;
	}

	@Override
	public boolean isAIEnabled() {
		return true;
	}

	@Override
	protected String getLivingSound() {
		return "outerrim:entity.b2.ambient";
	}

	@Override
	protected String getHurtSound() {
		return "outerrim:entity.b2.hurt";
	}

	@Override
	protected String getDeathSound() {
		return "outerrim:entity.b2.death";
	}

	public void playShootSound() {
		this.playSound("outerrim:entity.b1.droid_shoot", 1.0F, 1.0F);
	}

	@Override
	public IEntityLivingData onSpawnWithEgg(IEntityLivingData data) {
		this.playSound("outerrim:entity.b2.spawn", 1.0F, 1.0F);
		return super.onSpawnWithEgg(data);
	}

	@Override
	public EntityAgeable createChild(EntityAgeable ageable) {
		return null;
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
		return 1200;
	}
}

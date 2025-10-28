package com.sown.outerrim.entities;

import com.sown.outerrim.registry.ItemRegister; // kept even if unused
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

public class EntityDroideka extends EntityTameable implements IRangedAttackMob {

	private boolean isShieldActive = true;

	// ===== Tunables (doubled ranges) =====
	private static final double AGGRO_RANGE = 64.0D;
	private static final float SHOOT_RANGE = 64.0F;

	// ===== Burst-fire state =====
	private static final int BURST_GAP_TICKS = 5;
	private static final float BARREL_OFFSET = 0.42F; // a touch wider spacing

	private static final float SPAWN_Y_LOWER = 0.25F;
	private static final double CONVERGE_OVERSHOOT = 1.35D;

	private int burstShotsPending = 0;
	private int burstDelayTicks = 0;
	private boolean nextBarrelLeft = true;

	// ===== Retarget sweep to “never forget” clones =====
	private static final int RETARGET_COOLDOWN_TICKS = 10;
	private int retargetCooldown = RETARGET_COOLDOWN_TICKS;

	public EntityDroideka(World worldIn) {
		super(worldIn);
		this.setSize(1.0f, 2.0f);
		this.getNavigator().setCanSwim(false);

		this.tasks.addTask(1, new EntityAISwimming(this));
		this.tasks.addTask(2, this.aiSit);
		this.tasks.addTask(3, new EntityAIAttackWithBow(this, 1.0D, 20, SHOOT_RANGE));
		this.tasks.addTask(4, new EntityAIAttackOnCollide(this, EntityLivingBase.class, 1.0D, true));
		this.tasks.addTask(5, new EntityAIFollowOwner(this, 1.0D, 10.0F, 2.0F));
		this.tasks.addTask(6, new EntityAIMoveTowardsRestriction(this, 1.0D));
		this.tasks.addTask(7, new EntityAIWander(this, 1.0D));
		this.tasks.addTask(8, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
		this.tasks.addTask(9, new EntityAILookIdle(this));

		this.targetTasks.addTask(1, new EntityAIOwnerHurtByTarget(this));
		this.targetTasks.addTask(2, new EntityAIOwnerHurtTarget(this));
		this.targetTasks.addTask(3, new EntityAIHurtByTarget(this, true));

		// Highest-priority hard targets: clones (no LOS requirement so we don't lose
		// target)
		this.targetTasks.addTask(4, new EntityAINearestAttackableTarget(this, EntityCloneTrooperBase.class, 0, false));
		this.targetTasks.addTask(5, new EntityAINearestAttackableTarget(this, EntityReconTrooperBase.class, 0, false));

		// Other targets
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
		this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(AGGRO_RANGE);
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(40.0);
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.01);
	}

	@Override
	public boolean isAIEnabled() {
		return true;
	}

	@Override
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
		if (angry)
			this.dataWatcher.updateObject(16, Byte.valueOf((byte) (b0 | 2)));
		else
			this.dataWatcher.updateObject(16, Byte.valueOf((byte) (b0 & -3)));
	}

	@Override
	protected void entityInit() {
		super.entityInit();
		this.getDataWatcher().addObject(25, 0);
		this.getDataWatcher().addObject(26, this.rand.nextInt(10));
		this.getDataWatcher().addObject(27, 0);
	}

	@Override
	public boolean attackEntityAsMob(Entity entity) {
		int i = this.isTamed() ? 4 : 2;
		return entity.attackEntityFrom(DamageSource.causeMobDamage(this), (float) i);
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

		// Armor/equipment handling
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
	public EntityAgeable createChild(EntityAgeable ageable) {
		return null;
	}

	@Override
	public void onDeath(DamageSource cause) {
		if (this.isTamed() && this.getOwner() instanceof EntityPlayer) {
			((EntityPlayer) this.getOwner())
					.addChatMessage(new ChatComponentText(this.getCommandSenderName() + " has been destroyed."));
		}
	}

	// ====== Burst fire (two shots, 8 ticks apart) with dual-barrel offset ======
	@Override
	public void attackEntityWithRangedAttack(EntityLivingBase target, float distanceFactor) {
		// Fire first shot immediately
		fireLaserAt(target, distanceFactor, nextBarrelLeft ? +BARREL_OFFSET : -BARREL_OFFSET);
		// Schedule one follow-up shot
		this.burstShotsPending = 1;
		this.burstDelayTicks = BURST_GAP_TICKS;
		// Flip starter barrel for next burst
		this.nextBarrelLeft = !this.nextBarrelLeft;
	}

	@Override
	public void onUpdate() {
		super.onUpdate();
		this.isShieldActive = true; // keep shield on

		if (!this.worldObj.isRemote) {
			// Follow-up burst timing
			if (this.burstShotsPending > 0) {
				if (--this.burstDelayTicks <= 0) {
					EntityLivingBase tgt = this.getAttackTarget();
					if (tgt != null && !tgt.isDead) {
						double dx = tgt.posX - this.posX;
						double dz = tgt.posZ - this.posZ;
						float distScale = (float) MathHelper.sqrt_double(dx * dx + dz * dz);
						fireLaserAt(tgt, distScale, this.nextBarrelLeft ? +BARREL_OFFSET : -BARREL_OFFSET);
					}
					this.burstShotsPending = 0; // exactly two shots
				}
			}

			// Hard retarget to ensure clones are always considered mortal enemies
			if (--retargetCooldown <= 0) {
				retargetCooldown = RETARGET_COOLDOWN_TICKS;
				ensurePrimaryCloneTarget();
			}
		}
	}

	/**
	 * If no valid target (or target is far/out), force-pick the nearest clone (or
	 * recon clone) within AGGRO_RANGE and set it as attack target.
	 */
	private void ensurePrimaryCloneTarget() {
		EntityLivingBase curr = this.getAttackTarget();
		boolean needsTarget = (curr == null) || curr.isDead
				|| this.getDistanceSqToEntity(curr) > (AGGRO_RANGE * AGGRO_RANGE);

		if (!needsTarget)
			return;

		EntityLivingBase best = null;
		double bestDistSq = Double.MAX_VALUE;

		// Scan clones
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
		// Scan recon clones too
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
			this.setAngry(true);
		}
	}

	/**
	 * Fires a single red laser from a laterally offset barrel, spawned a bit lower,
	 * and aimed to a single convergence point *behind* the target so the two bolts
	 * cross just past it.
	 */
	private void fireLaserAt(EntityLivingBase target, float distanceFactor, float lateralOffset) {
		EntityLaserProjectileRed bolt = new EntityLaserProjectileRed(this.worldObj, this, distanceFactor);

		// Center-to-target vector (from droideka center)
		double dXc = target.posX - this.posX;
		double dYc = (target.posY + target.getEyeHeight()) - (this.posY + this.getEyeHeight());
		double dZc = target.posZ - this.posZ;

		// Horizontal right vector for lateral barrel offset
		double horizLen = Math.sqrt(dXc * dXc + dZc * dZc);
		double rightX, rightZ;
		if (horizLen > 1.0E-6) {
			rightX = -dZc / horizLen;
			rightZ = dXc / horizLen;
		} else {
			rightX = 1.0;
			rightZ = 0.0;
		}

		// Spawn position: eye height minus a little, offset sideways by barrel
		double spawnX = this.posX + rightX * lateralOffset;
		double spawnY = this.posY + this.getEyeHeight() - SPAWN_Y_LOWER;
		double spawnZ = this.posZ + rightZ * lateralOffset;
		bolt.setPosition(spawnX, spawnY, spawnZ);

		// Convergence point: a bit *beyond* the target along the centerline
		double lenC = Math.sqrt(dXc * dXc + dYc * dYc + dZc * dZc);
		double dirX = (lenC > 1.0E-6) ? (dXc / lenC) : 0.0;
		double dirY = (lenC > 1.0E-6) ? (dYc / lenC) : 0.0;
		double dirZ = (lenC > 1.0E-6) ? (dZc / lenC) : 0.0;

		double aimX = target.posX + dirX * CONVERGE_OVERSHOOT;
		double aimY = target.posY + target.getEyeHeight() + dirY * CONVERGE_OVERSHOOT;
		double aimZ = target.posZ + dirZ * CONVERGE_OVERSHOOT;

		// Final heading from the *spawn* to the convergence point
		double dX = aimX - spawnX;
		double dY = aimY - spawnY;
		double dZ = aimZ - spawnZ;

		bolt.setThrowableHeading(dX, dY, dZ, 1.6F, 0.0F);
		bolt.setDamage((double) (distanceFactor * 2.0F));

		this.worldObj.spawnEntityInWorld(bolt);
		this.playShootSound();
	}

	@Override
	public boolean attackEntityFrom(DamageSource source, float amount) {
		if (source.isProjectile() || source.isExplosion())
			return false; // shielded
		return super.attackEntityFrom(source, amount);
	}

	@Override
	protected String getLivingSound() {
		return "outerrim:entity.droideka.step";
	}

	@Override
	protected String getHurtSound() {
		return "outerrim:entity.droideka.step";
	}

	@Override
	protected String getDeathSound() {
		return "outerrim:entity.droideka.step";
	}

	public void playMeleeSound() {
		this.playSound("outerrim:entity.droideka.step", 1.0F, 1.0F);
	}

	public void playShootSound() {
		this.playSound("outerrim:entity.droideka.shoot", 1.0F, 1.0F);
	}

	public void playPanicSound() {
		this.playSound("outerrim:entity.droideka.step", 1.0F, 1.0F);
	}

	public boolean isShieldActive() {
		return true;
	}

	@Override
	public IEntityLivingData onSpawnWithEgg(IEntityLivingData data) {
		this.playSound("outerrim:entity.b1.spawn", 1.0F, 1.0F);
		return super.onSpawnWithEgg(data);
	}

	@Override
	public int getTalkInterval() {
		return 1200;
	}
}

package com.sown.outerrim.entities;

import com.sown.outerrim.registry.ItemRegister;

import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.monster.*;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public abstract class EntityCloneTrooperBase extends EntityTameable implements IRangedAttackMob {

	// ===== Tunables (match the droid side) =====
	protected static final double AGGRO_RANGE = 256.0D;
	protected static final float SHOOT_RANGE = 112.0F;

	// “Never forget droids” sweep
	private static final int RETARGET_COOLDOWN_TICKS = 10; // every 0.5s
	private int retargetCooldown = RETARGET_COOLDOWN_TICKS;

	public EntityCloneTrooperBase(World worldIn) {
		super(worldIn);
		this.setSize(1.0f, 2.0f);
		this.getNavigator().setCanSwim(true);
		this.getNavigator().setEnterDoors(true);

		// Tasks
		this.tasks.addTask(1, new EntityAISwimming(this));
		this.tasks.addTask(2, new EntityAIAttackWithBow(this, 1.0D, 20, SHOOT_RANGE)); // 100f shoot range
		this.tasks.addTask(3, new EntityAIWander(this, 1.0D));

		// Prioritize protecting the owner
		this.targetTasks.addTask(1, new EntityAIOwnerHurtByTarget(this));
		this.targetTasks.addTask(2, new EntityAIOwnerHurtTarget(this));

		// General retaliation
		this.targetTasks.addTask(3, new EntityAIHurtByTarget(this, true));

		// High priority: droids (no LOS requirement so we don’t drop them)
		this.targetTasks.addTask(4, new EntityAINearestAttackableTarget(this, EntityBattleDroid.class, 0, false));
		this.targetTasks.addTask(5, new EntityAINearestAttackableTarget(this, EntityB2BattleDroid.class, 0, false));
		this.targetTasks.addTask(6,
				new EntityAINearestAttackableTarget(this, EntityRustyB2BattleDroid.class, 0, false));
		this.targetTasks.addTask(7, new EntityAINearestAttackableTarget(this, EntityDroideka.class, 0, false));

		// Other hostiles you already had
		this.targetTasks.addTask(8, new EntityAINearestAttackableTarget(this, EntityTuskenRaider.class, 0, true));
		// Example for vanilla hostiles if you decide to enable:
		// this.targetTasks.addTask(9, new EntityAINearestAttackableTarget(this,
		// IMob.class, 0, true));
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(AGGRO_RANGE);
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(30.0);
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.3);
	}

	@Override
	public boolean isAIEnabled() {
		return true;
	}

	@Override
	public boolean interact(EntityPlayer player) {
		ItemStack itemstack = player.getCurrentEquippedItem();

		// Tame with 100-credit chit
		if (!this.isTamed() && itemstack != null
				&& itemstack.getItem() == ItemRegister.getRegisteredItem("galactic_credit_100")) {
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

		// Toggle sit for tamed clones (empty hand)
		if (this.isTamed() && this.func_152114_e(player)) {
			if (!this.worldObj.isRemote && itemstack == null) {
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

	@Override
	public void attackEntityWithRangedAttack(EntityLivingBase target, float distanceFactor) {
		// Standard single-barrel clone bolt
		EntityLaserProjectile bolt = new EntityLaserProjectile(this.worldObj, this, distanceFactor);

		double dX = target.posX - this.posX;
		double dY = (target.posY + target.getEyeHeight()) - (this.posY + this.getEyeHeight());
		double dZ = target.posZ - this.posZ;

		bolt.setThrowableHeading(dX, dY, dZ, 1.6F, 0.0F);
		bolt.setDamage((double) (distanceFactor * 2.0F));
		bolt.setPosition(this.posX, this.posY + this.getEyeHeight(), this.posZ);

		this.worldObj.spawnEntityInWorld(bolt);
		this.playShootSound();
	}

	// Laser immunity (keep your original behavior)
	@Override
	public boolean attackEntityFrom(DamageSource source, float amount) {
		if (source.getSourceOfDamage() instanceof EntityLaserProjectile)
			return false;
		return super.attackEntityFrom(source, amount);
	}

	// “Never forget droids” — server tick sweep
	@Override
	public void onUpdate() {
		super.onUpdate();
		if (!this.worldObj.isRemote) {
			if (--retargetCooldown <= 0) {
				retargetCooldown = RETARGET_COOLDOWN_TICKS;
				ensurePrimaryDroidTarget();
			}
		}
	}

	/**
	 * If no valid target (or too far), force-pick the nearest droid within
	 * AGGRO_RANGE.
	 */
	private void ensurePrimaryDroidTarget() {
		EntityLivingBase curr = this.getAttackTarget();
		boolean needsTarget = (curr == null) || curr.isDead
				|| this.getDistanceSqToEntity(curr) > (AGGRO_RANGE * AGGRO_RANGE);
		if (!needsTarget)
			return;

		EntityLivingBase best = null;
		double bestDistSq = Double.MAX_VALUE;

		// Battle droids
		for (Object o : this.worldObj.getEntitiesWithinAABB(EntityBattleDroid.class,
				this.boundingBox.expand(AGGRO_RANGE, AGGRO_RANGE, AGGRO_RANGE))) {
			EntityBattleDroid e = (EntityBattleDroid) o;
			if (e.isDead)
				continue;
			double d = this.getDistanceSqToEntity(e);
			if (d < bestDistSq) {
				bestDistSq = d;
				best = e;
			}
		}
		// B2 droids
		for (Object o : this.worldObj.getEntitiesWithinAABB(EntityB2BattleDroid.class,
				this.boundingBox.expand(AGGRO_RANGE, AGGRO_RANGE, AGGRO_RANGE))) {
			EntityB2BattleDroid e = (EntityB2BattleDroid) o;
			if (e.isDead)
				continue;
			double d = this.getDistanceSqToEntity(e);
			if (d < bestDistSq) {
				bestDistSq = d;
				best = e;
			}
		}
		// Rusty B2 droids
		for (Object o : this.worldObj.getEntitiesWithinAABB(EntityRustyB2BattleDroid.class,
				this.boundingBox.expand(AGGRO_RANGE, AGGRO_RANGE, AGGRO_RANGE))) {
			EntityRustyB2BattleDroid e = (EntityRustyB2BattleDroid) o;
			if (e.isDead)
				continue;
			double d = this.getDistanceSqToEntity(e);
			if (d < bestDistSq) {
				bestDistSq = d;
				best = e;
			}
		}
		// Droidekas
		for (Object o : this.worldObj.getEntitiesWithinAABB(EntityDroideka.class,
				this.boundingBox.expand(AGGRO_RANGE, AGGRO_RANGE, AGGRO_RANGE))) {
			EntityDroideka e = (EntityDroideka) o;
			if (e.isDead)
				continue;
			double d = this.getDistanceSqToEntity(e);
			if (d < bestDistSq) {
				bestDistSq = d;
				best = e;
			}
		}

		if (best != null)
			this.setAttackTarget(best);
	}

	public abstract String getTrooperType();

	@Override
	protected String getLivingSound() {
		return "outerrim:entity.clone.ambient";
	}

	@Override
	protected String getHurtSound() {
		return "outerrim:entity.clone.hurt";
	}

	@Override
	protected String getDeathSound() {
		return "outerrim:entity.clone.death";
	}

	@Override
	public int getTalkInterval() {
		return 1200;
	}

	// Custom AI to avoid targeting other clones (kept)
	class CustomAINearestAttackableTarget extends EntityAINearestAttackableTarget {
		public CustomAINearestAttackableTarget(EntityCreature owner, Class targetClass, boolean checkSight) {
			super(owner, targetClass, 0, checkSight);
		}

		@Override
		protected boolean isSuitableTarget(EntityLivingBase target, boolean includeInvincibles) {
			if (target instanceof EntityCloneTrooperBase || target instanceof EntityReconTrooperBase)
				return false;
			return super.isSuitableTarget(target, includeInvincibles);
		}
	}

	public void playShootSound() {
		this.playSound("outerrim:entity.clone.shoot", 1.0F, 1.0F);
	}

}

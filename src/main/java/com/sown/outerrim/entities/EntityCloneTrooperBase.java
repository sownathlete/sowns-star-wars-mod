package com.sown.outerrim.entities;

import com.sown.outerrim.registry.ItemRegister;

import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
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

    public EntityCloneTrooperBase(World worldIn) {
        super(worldIn);
        this.setSize(1.0f, 2.0f);
        this.getNavigator().setCanSwim(true);
        this.getNavigator().setEnterDoors(true);

        // Adding AI tasks with corrected priorities and settings
        this.tasks.addTask(1, new EntityAISwimming(this));
        this.tasks.addTask(2, new EntityAIAttackWithBow(this, 1.0D, 20, 15.0F));  // Only ranged attacks for now
        this.tasks.addTask(3, new EntityAIWander(this, 1.0D));
        
        // Prioritize protecting the owner
        this.targetTasks.addTask(1, new EntityAIOwnerHurtByTarget(this));  // Attack entities that attack the owner
        this.targetTasks.addTask(2, new EntityAIOwnerHurtTarget(this));    // Attack entities that the owner attacks

        // General retaliation behavior
        this.targetTasks.addTask(3, new EntityAIHurtByTarget(this, true));  // Retaliate against attackers

        // Target droids
        this.targetTasks.addTask(4, new EntityAINearestAttackableTarget(this, EntityBattleDroid.class, 0, true));
        this.targetTasks.addTask(5, new EntityAINearestAttackableTarget(this, EntityB2BattleDroid.class, 1, true));
        this.targetTasks.addTask(6, new EntityAINearestAttackableTarget(this, EntityRustyB2BattleDroid.class, 2, true));
        this.targetTasks.addTask(7, new EntityAINearestAttackableTarget(this, EntityDroideka.class, 3, true));

        // Target EntityTuskenRaider
        this.targetTasks.addTask(8, new EntityAINearestAttackableTarget(this, EntityTuskenRaider.class, 4, true));

        // Target vanilla hostile mobs (IMob covers zombies, skeletons, creepers, etc.)
        // this.targetTasks.addTask(9, new EntityAINearestAttackableTarget(this, IMob.class, 5, true));
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(200.0);  // Increased follow range
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(30.0);  // Standard health
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.3);  // Decent movement speed
    }

    @Override
    public boolean isAIEnabled() {
        return true;
    }
    
    @Override
    public boolean interact(EntityPlayer player) {
        ItemStack itemstack = player.getCurrentEquippedItem();

        // Taming logic using credit_chit
        if (!this.isTamed() && itemstack != null && itemstack.getItem() == ItemRegister.getRegisteredItem("galactic_credit_100")) {
            if (!this.worldObj.isRemote) {
                this.setTamed(true);
                this.func_152115_b(player.getUniqueID().toString()); // Bind the tamed entity to the player
                this.playTameEffect(true);
                this.worldObj.setEntityState(this, (byte) 7); // Play the taming particle effect
            }
            if (!player.capabilities.isCreativeMode) {
                --itemstack.stackSize; // Decrease the item stack size
            }
            return true;
        }

        // Behavior for tamed clones
        if (this.isTamed() && this.func_152114_e(player)) {
            if (!this.worldObj.isRemote && itemstack == null) {
                // Toggle sitting state
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
        // Create a new laser projectile entity
        EntityLaserProjectile entityLaser = new EntityLaserProjectile(this.worldObj, this, distanceFactor);

        // Get the direction vectors from this entity to the target
        double dX = target.posX - this.posX;
        double dY = (target.posY + target.getEyeHeight()) - (this.posY + this.getEyeHeight());
        double dZ = target.posZ - this.posZ;
        double distance = MathHelper.sqrt_double(dX * dX + dZ * dZ);

        // Set the heading of the laser
        entityLaser.setThrowableHeading(dX, dY, dZ, 1.6F, 0.0F);
        entityLaser.setDamage((double) (distanceFactor * 2.0F));
        entityLaser.setPosition(this.posX, this.posY + this.getEyeHeight(), this.posZ);

        // Spawn the projectile in the world
        this.worldObj.spawnEntityInWorld(entityLaser);
        this.playShootSound();
    }

    // Override to prevent laser projectile damage
    @Override
    public boolean attackEntityFrom(DamageSource source, float amount) {
        if (source.getSourceOfDamage() instanceof EntityLaserProjectile) {
            return false;  // Invulnerable to lasers
        }
        return super.attackEntityFrom(source, amount);
    }

    public abstract String getTrooperType();  // Each subclass will implement this

    @Override
    public void onDeath(DamageSource cause) {
        super.onDeath(cause);
    }

    @Override
    public ItemStack getHeldItem() {
        return null;
    }

    @Override
    public ItemStack getEquipmentInSlot(int slot) {
        return null;
    }

    public ItemStack getCurrentArmor(int slot) {
        return null;
    }

    public void playShootSound() {
        this.playSound("outerrim:entity.clone.shoot", 1.0F, 1.0F);  // Sound event when the clone trooper shoots
    }

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
        return 1200; // 1200 ticks = 60 seconds
    }

    // Custom AI to avoid targeting other clones
    class CustomAINearestAttackableTarget extends EntityAINearestAttackableTarget {
        public CustomAINearestAttackableTarget(EntityCreature owner, Class targetClass, boolean checkSight) {
            super(owner, targetClass, 0, checkSight);
        }

        @Override
        protected boolean isSuitableTarget(EntityLivingBase target, boolean includeInvincibles) {
            if (target instanceof EntityCloneTrooperBase || target instanceof EntityReconTrooperBase) {
                return false;  // Avoid targeting other clone troopers
            }
            return super.isSuitableTarget(target, includeInvincibles);
        }
    }
}

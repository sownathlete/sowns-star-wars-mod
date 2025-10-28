package com.sown.outerrim.entities;

import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class MobYoda extends EntityTameable {

    // Use a DataWatcher slot that doesn't collide with Tameable's indices.
    // In 1.7.10, Tameable uses 16 (flags), 17 (owner name). We'll use 20.
    private static final int DW_ROBE_TILT = 20;

    public MobYoda(World worldIn) {
        super(worldIn);
        this.setSize(0.6F, 1.5F);
        this.getNavigator().setAvoidsWater(true);

        this.tasks.addTask(1, new EntityAISwimming(this));
        this.tasks.addTask(2, this.aiSit);
        this.tasks.addTask(4, new EntityAIFollowOwner(this, 1.0D, 10.0F, 2.0F));
        this.tasks.addTask(5, new EntityAIWander(this, 1.0D));
        this.tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(7, new EntityAILookIdle(this));

        this.targetTasks.addTask(1, new EntityAIOwnerHurtByTarget(this));
        this.targetTasks.addTask(2, new EntityAIOwnerHurtTarget(this));

        this.setTamed(false);
    }

    @Override
    protected void entityInit() {
        super.entityInit();
        // Initialize robe tilt watcher
        this.dataWatcher.addObject(DW_ROBE_TILT, Float.valueOf(0.0F));
    }

    // --- Per-entity robe tilt ---

    public float getCurrentRobeTilt() {
        return this.dataWatcher.getWatchableObjectFloat(DW_ROBE_TILT);
    }

    private void setCurrentRobeTilt(float value) {
        // clamp a bit to be safe
        if (value < -1.0F) value = -1.0F;
        if (value >  1.0F) value =  1.0F;
        this.dataWatcher.updateObject(DW_ROBE_TILT, Float.valueOf(value));
    }

    private boolean isMoving() {
        // Robust per-tick horizontal movement check (ignores tiny jitters)
        double dx = this.posX - this.prevPosX;
        double dz = this.posZ - this.prevPosZ;
        double dist2 = dx*dx + dz*dz;
        return !this.isSitting() && dist2 > 0.0004D; // ~0.02 blocks per tick threshold
    }

    @Override
    public void onLivingUpdate() {
        super.onLivingUpdate();

        final boolean groundedMove =
                this.onGround && !this.isSitting() &&
                (Math.abs(this.posX - this.prevPosX) > 0.02D ||
                 Math.abs(this.posZ - this.prevPosZ) > 0.02D);

        // If hurt or airborne, bias toward no-tilt to avoid spaz
        float target = (groundedMove && this.hurtTime == 0) ? 0.295F : 0.0F;
        float current = getCurrentRobeTilt();
        float next = current + (target - current) * 0.15F;
        setCurrentRobeTilt(next);
    }
    
    // --- Attributes / tame toggles ---

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(30.0D);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.3D);
    }

    @Override
    public void setTamed(boolean tamed) {
        super.setTamed(tamed);
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(tamed ? 40.0D : 30.0D);
    }

    // --- Sounds & drops ---

    @Override
    protected String getLivingSound() { return "outerrim:entity.yoda.ambient"; }

    @Override
    protected String getHurtSound() { return "outerrim:entity.yoda.hurt"; }

    @Override
    protected String getDeathSound() { return "outerrim:entity.yoda.death"; }

    @Override
    protected void dropFewItems(boolean wasRecentlyHit, int lootingModifier) {
        this.dropItem(Items.diamond_sword, 1);
    }

    // --- Lifecycle / messaging ---

    @Override
    public void onDeath(DamageSource cause) {
        if (this.isTamed() && this.getOwner() instanceof EntityPlayer) {
            ((EntityPlayer)this.getOwner()).addChatMessage(new ChatComponentText("Yoda has died."));
        }
        super.onDeath(cause);
    }

    // --- Breeding disabled ---

    @Override
    public EntityAgeable createChild(EntityAgeable ageable) { return null; }

    @Override
    public int getTalkInterval() { return 1200; }

    // --- Save / load ---

    @Override
    public void writeEntityToNBT(NBTTagCompound nbt) {
        super.writeEntityToNBT(nbt);
        nbt.setFloat("RobeTilt", getCurrentRobeTilt());
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound nbt) {
        super.readEntityFromNBT(nbt);
        if (nbt.hasKey("RobeTilt")) {
            setCurrentRobeTilt(nbt.getFloat("RobeTilt"));
        } else {
            setCurrentRobeTilt(0.0F);
        }
    }
}

package com.sown.outerrim.entities;

import java.util.Random;

import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class EntityKaadu extends EntityTameable {
    private final Random rand = new Random();

    public EntityKaadu(World world) {
        super(world);
        this.setSize(1.5F, 2.0F);
        this.getNavigator().setAvoidsWater(true);

        // AI tasks
        this.tasks.addTask(1, new EntityAISwimming(this));
        this.tasks.addTask(2, new EntityAIPanic(this, 1.5D));
        this.tasks.addTask(3, new EntityAIWander(this, 1.0D));
        this.tasks.addTask(4, new EntityAIMate(this, 1.0D));
        this.tasks.addTask(5, new EntityAITempt(this, 1.2D, Items.wheat, false));
        this.tasks.addTask(6, new EntityAIFollowOwner(this, 1.0D, 10.0F, 2.0F));
        this.tasks.addTask(7, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
        this.tasks.addTask(8, new EntityAILookIdle(this));
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(30.0D);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.25D);
    }

    @Override
    public void onLivingUpdate() {
        super.onLivingUpdate();
        // Scale properly for babies:
        this.setScaleForAge(this.isChild());

        // Riding control:
        if (this.riddenByEntity instanceof EntityPlayer) {
            EntityPlayer rider = (EntityPlayer) this.riddenByEntity;
            this.rotationYaw     = rider.rotationYaw;
            this.prevRotationYaw = this.rotationYaw;
            this.rotationPitch   = rider.rotationPitch * 0.5F;
            float forward = rider.moveForward, strafe = rider.moveStrafing;
            double speedMod = this.onGround ? 0.5D : 0.2D;
            this.moveEntityWithHeading(strafe, forward * (float)speedMod);
        }
    }

    @Override
    public void setScaleForAge(boolean child) {
        if (child) {
            this.setSize(0.75F, 1.0F);
        } else {
            this.setSize(1.5F, 2.0F);
        }
    }

    // Sounds
    @Override protected String getLivingSound() { return "outerrim:entity.kaadu.ambient"; }
    @Override protected String getHurtSound()   { return "outerrim:entity.kaadu.hurt"; }
    @Override protected String getDeathSound()  { return "outerrim:entity.kaadu.death"; }

    @Override
    public boolean attackEntityFrom(DamageSource src, float amt) {
        if (this.isEntityInvulnerable()) return false;
        this.tasks.addTask(2, new EntityAIPanic(this, 1.5D));
        return super.attackEntityFrom(src, amt);
    }

    @Override
    public boolean interact(EntityPlayer player) {
        ItemStack stack = player.inventory.getCurrentItem();

        // 1) If already tamed and you're the owner, mount immediately
        if (this.isTamed() && player.getCommandSenderName().equals(this.func_152113_b())) {
            if (!worldObj.isRemote) {
                player.mountEntity(this);
            }
            return true;
        }

        // 2) Tame with wheat if not yet tamed
        if (!this.isTamed() && stack != null && stack.getItem() == Items.wheat) {
            if (!player.capabilities.isCreativeMode) stack.stackSize--;
            if (!worldObj.isRemote) {
                if (rand.nextInt(3) == 0) {
                    this.setTamed(true);
                    // **Store the owner's username, not UUID**
                    this.func_152115_b(player.getCommandSenderName());
                    worldObj.setEntityState(this, (byte)7); // hearts
                } else {
                    worldObj.setEntityState(this, (byte)6); // smoke
                }
            }
            return true;
        }

        // 3) Otherwise fall back to super (handles breeding via AI)
        return super.interact(player);
    }

    @Override
    public void updateRiderPosition() {
        super.updateRiderPosition();
        if (riddenByEntity != null) {
            double yOff = 1.7D;
            riddenByEntity.setPosition(
                posX, posY + getMountedYOffset() + yOff, posZ
            );
        }
    }

    @Override
    public double getMountedYOffset() {
        return this.height * 1.0D;
    }

    @Override
    public void onDeath(DamageSource cause) {
        super.onDeath(cause);
        if (!worldObj.isRemote) {
            this.dropItem(Items.leather, rand.nextInt(3));
            this.dropItem(Items.beef,    rand.nextInt(3));
        }
    }

    // Breeding & children
    @Override
    public EntityAgeable createChild(EntityAgeable mate) {
        return new EntityKaadu(this.worldObj);
    }

    @Override
    public boolean isBreedingItem(ItemStack stack) {
        return stack != null && stack.getItem() == Items.wheat;
    }
}

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

public class EntityFalumpaset extends EntityTameable {
    private final Random rand = new Random();

    public EntityFalumpaset(World world) {
        super(world);
        this.setSize(2.0F, 3.0F);
        this.getNavigator().setAvoidsWater(false);

        // AI
        this.tasks.addTask(1, new EntityAISwimming(this));
        this.tasks.addTask(2, new EntityAIPanic(this, 1.7D));
        this.tasks.addTask(3, new EntityAIMate(this, 1.0D));                      // breeding
        this.tasks.addTask(4, new EntityAITempt(this, 1.3D, Items.wheat, false));
        this.tasks.addTask(5, new EntityAIFollowOwner(this, 1.2D, 8.0F, 2.0F));
        this.tasks.addTask(6, new EntityAIAttackOnCollide(this, 1.4D, true));
        this.tasks.addTask(7, new EntityAIWander(this, 1.0D));
        this.tasks.addTask(8, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(9, new EntityAILookIdle(this));
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth)
            .setBaseValue(40.0D);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed)
            .setBaseValue(0.30D);
    }

    @Override
    public void onLivingUpdate() {
        super.onLivingUpdate();
        // riding
        if (this.riddenByEntity instanceof EntityPlayer) {
            EntityPlayer rider = (EntityPlayer)this.riddenByEntity;
            this.rotationYaw     = rider.rotationYaw;
            this.prevRotationYaw = this.rotationYaw;
            this.rotationPitch   = rider.rotationPitch * 0.5F;
            float forward = rider.moveForward;
            float strafe  = rider.moveStrafing;
            double speedMod = this.onGround ? 0.6D : 0.2D;
            this.moveEntityWithHeading(strafe, forward * (float)speedMod);
        }
    }

    // sounds
    @Override protected String getLivingSound() { return "outerrim:entity.falumpaset.ambient"; }
    @Override protected String getHurtSound()   { return "outerrim:entity.falumpaset.hurt"; }
    @Override protected String getDeathSound()  { return "outerrim:entity.falumpaset.death"; }

    @Override
    public boolean attackEntityFrom(DamageSource src, float amount) {
        if (this.isEntityInvulnerable()) return false;
        this.tasks.addTask(2, new EntityAIPanic(this, 1.7D));
        return super.attackEntityFrom(src, amount);
    }

    @Override
    public boolean interact(EntityPlayer player) {
        ItemStack stack = player.inventory.getCurrentItem();

        // milk with bucket
        if (stack != null && stack.getItem() == Items.bucket) {
            if (!player.capabilities.isCreativeMode) stack.stackSize--;
            ItemStack milk = new ItemStack(Items.milk_bucket);
            if (!player.inventory.addItemStackToInventory(milk)) {
                player.dropPlayerItemWithRandomChoice(milk, false);
            }
            return true;
        }

        // if not tamed yet and feeding wheat => tame attempt
        if (!this.isTamed() && stack != null && stack.getItem() == Items.wheat) {
            if (!player.capabilities.isCreativeMode) stack.stackSize--;
            if (!worldObj.isRemote) {
                if (this.rand.nextInt(4) == 0) {
                    this.setTamed(true);
                    // set owner via obfuscated name
                    this.func_152115_b(player.getUniqueID().toString());
                    worldObj.setEntityState(this, (byte)7); // hearts
                } else {
                    worldObj.setEntityState(this, (byte)6); // smoke
                }
            }
            return true;
        }

        // once tamed, let super handle breeding and sitting
        if (this.isTamed()) {
            // ride if owner and not sneaking
            if (player.getCommandSenderName().equals(this.func_152113_b()) && !player.isSneaking()) {
                if (!worldObj.isRemote) {
                    player.mountEntity(this);
                }
                return true;
            }
            // pass wheat to super for breeding (EntityAnimal handles love mode)
            return super.interact(player);
        }

        return super.interact(player);
    }

    @Override
    public void updateRiderPosition() {
        super.updateRiderPosition();
        if (this.riddenByEntity != null) {
            double yOff = 2.2D;
            this.riddenByEntity.setPosition(
                posX, posY + getMountedYOffset() + yOff, posZ
            );
        }
    }

    @Override
    public double getMountedYOffset() {
        return this.height * 0.75D;
    }

    @Override
    public void onDeath(DamageSource cause) {
        super.onDeath(cause);
        if (!worldObj.isRemote) {
            entityDropItem(new ItemStack(Items.leather, rand.nextInt(3)), 0.0F);
            entityDropItem(new ItemStack(Items.beef,    rand.nextInt(3)), 0.0F);
        }
    }

    // breeding / baby

    /** Called by EntityAIMate on two adults in love mode */
    @Override
    public EntityAgeable createChild(EntityAgeable mate) {
        return new EntityFalumpaset(this.worldObj);
    }

    @Override
    public boolean isBreedingItem(ItemStack stack) {
        return stack != null && stack.getItem() == Items.wheat;
    }
}

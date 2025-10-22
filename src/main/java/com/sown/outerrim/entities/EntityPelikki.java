package com.sown.outerrim.entities;

import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class EntityPelikki extends EntityTameable {

    public EntityPelikki(World world) {
        super(world);
        this.setSize(0.8F, 1.0F);  // Set size of Pelikki (width, height)
        this.setTamed(false);  // Default to untamed
        this.getNavigator().setAvoidsWater(false);  // Does not avoid water, they float

        // AI tasks
        this.tasks.addTask(1, new EntityAISwimming(this));
        this.tasks.addTask(2, new EntityAIPanic(this, 1.5D));  // Panic if attacked
        this.tasks.addTask(3, new EntityAIWander(this, 1.0D));  // Wander when not tamed
        this.tasks.addTask(4, new EntityAIMate(this, 1.0D));  // Breeding behavior
        this.tasks.addTask(5, new EntityAITempt(this, 1.2D, Items.wheat_seeds, false));  // Taming item (seeds)
        this.tasks.addTask(6, new EntityAIFollowOwner(this, 1.0D, 10.0F, 2.0F));  // Follow owner if tamed
        this.tasks.addTask(7, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));  // Watch players closely
        this.tasks.addTask(8, new EntityAILookIdle(this));  // Look idle when doing nothing
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(20.0D);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.25D);
    }

    @Override
    protected String getHurtSound() {
        return "mob.chicken.hurt";
    }

    @Override
    protected String getDeathSound() {
        return "mob.chicken.hurt";
    }

    @Override
    protected String getLivingSound() {
        return "mob.chicken.say";
    }

    @Override
    public boolean attackEntityFrom(DamageSource source, float amount) {
        if (this.isEntityInvulnerable()) {
            return false;
        } else {
            this.tasks.addTask(2, new EntityAIPanic(this, 1.5D));  // Panic when attacked
            return super.attackEntityFrom(source, amount);
        }
    }

    @Override
    public boolean interact(EntityPlayer player) {
        ItemStack itemstack = player.inventory.getCurrentItem();

        // If the Pelikki is tamed
        if (this.isTamed()) {
            return true;  // No interaction if already tamed
        } else {
            // Tame the Pelikki if fed seeds
            if (itemstack != null && itemstack.getItem() == Items.wheat_seeds) {
                if (!player.capabilities.isCreativeMode) {
                    --itemstack.stackSize;
                }

                if (!this.worldObj.isRemote) {
                    if (this.rand.nextInt(3) == 0) {
                        this.setTamed(true);
                        this.func_152115_b(player.getCommandSenderName());  // func_152115_b() sets the owner
                        this.worldObj.setEntityState(this, (byte) 7);  // Heart particles
                    } else {
                        this.worldObj.setEntityState(this, (byte) 6);  // Smoke particles (failed taming)
                    }
                }
                return true;
            }
        }

        return super.interact(player);
    }

    @Override
    public void onLivingUpdate() {
        super.onLivingUpdate();

        if (this.isInWater()) {
            this.motionY += 0.05D;  // Float up in water
        }

        // Laying eggs
        if (!this.worldObj.isRemote && this.rand.nextInt(6000) == 0) {
            this.entityDropItem(new ItemStack(Items.egg, 1), 0.0F);
        }
    }

    @Override
    public void onDeath(DamageSource cause) {
        super.onDeath(cause);
        if (!this.worldObj.isRemote) {
            // Drop feathers upon death
            this.dropItem(Items.feather, this.rand.nextInt(3));
        }
    }

    @Override
    public EntityAgeable createChild(EntityAgeable parent) {
        return new EntityPelikki(this.worldObj);  // Return a new Pelikki child
    }

    @Override
    public boolean isBreedingItem(ItemStack stack) {
        return stack != null && stack.getItem() == Items.wheat_seeds;
    }
}

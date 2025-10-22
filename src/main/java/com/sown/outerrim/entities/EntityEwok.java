package com.sown.outerrim.entities;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.ai.EntityAIAvoidEntity;
import net.minecraft.entity.ai.EntityAIMate;
import net.minecraft.entity.ai.EntityAIPanic;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAITempt;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIArrowAttack;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class EntityEwok extends EntityAnimal implements IRangedAttackMob {
    public EntityEwok(World world) {
        super(world);
        this.setSize(0.6F, 1.0F);
        this.getNavigator().setAvoidsWater(true);

        this.tasks.addTask(1, new EntityAISwimming(this));
        this.tasks.addTask(2, new EntityAIPanic(this, 1.4D));
        this.tasks.addTask(3, new EntityAIMate(this, 1.0D));
        this.tasks.addTask(4, new EntityAITempt(this, 1.2D, Items.apple, false));
        this.tasks.addTask(5, new EntityAIAvoidEntity(this, EntityPlayer.class, 8.0F, 1.0D, 1.2D));
        this.tasks.addTask(6, new EntityAIWander(this, 1.0D));
        this.tasks.addTask(7, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
        this.tasks.addTask(8, new EntityAILookIdle(this));

        if (!world.isRemote && this.rand.nextFloat() < 0.3F) {
            this.setCurrentItemOrArmor(0, new ItemStack(Items.bow));
            this.tasks.addTask(9, new EntityAIArrowAttack(this, 1.0D, 20, 15.0F));
        } else {
            this.tasks.addTask(9, new EntityAIAttackOnCollide(this, 1.2D, true));
        }
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(12.0D);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.25D);
        // Do not set attackDamage here, since it may not be registered on EntityAnimal in 1.7.10
    }

    @Override
    protected String getLivingSound() {
        return "outerrim:entity.ewok.ambient";
    }

    @Override
    protected String getHurtSound() {
        return "outerrim:entity.ewok.hurt";
    }

    @Override
    protected String getDeathSound() {
        return "outerrim:entity.ewok.death";
    }

    @Override
    protected float getSoundVolume() {
        return 0.4F;
    }

    @Override
    public boolean attackEntityFrom(DamageSource source, float amount) {
        if (this.isEntityInvulnerable()) {
            return false;
        } else {
            this.tasks.addTask(2, new EntityAIPanic(this, 1.6D));
            return super.attackEntityFrom(source, amount);
        }
    }

    @Override
    public boolean interact(EntityPlayer player) {
        ItemStack held = player.inventory.getCurrentItem();
        if (held != null && held.getItem() == Items.apple) {
            if (!player.capabilities.isCreativeMode) {
                held.stackSize--;
            }
            return true;
        }
        return super.interact(player);
    }

    @Override
    public EntityAgeable createChild(EntityAgeable parent) {
        return new EntityEwok(this.worldObj);
    }

    @Override
    public boolean isBreedingItem(ItemStack stack) {
        return stack != null && stack.getItem() == Items.apple;
    }

    @Override
    public void onDeath(DamageSource cause) {
        super.onDeath(cause);
        if (!this.worldObj.isRemote) {
            if (this.rand.nextFloat() < 0.5F) {
                this.dropItem(Items.leather, 1);
            }
            if (this.rand.nextFloat() < 0.3F) {
                this.dropItem(Items.stick, 1);
            }
        }
    }

    @Override
    public void attackEntityWithRangedAttack(net.minecraft.entity.EntityLivingBase target, float distanceFactor) {
        if (this.getHeldItem() != null && this.getHeldItem().getItem() == Items.bow) {
            EntityArrow arrow = new EntityArrow(this.worldObj, this, target, 1.6F,
                (float)(14 - this.worldObj.difficultySetting.getDifficultyId() * 4));
            int powerLevel = EnchantmentHelper.getEnchantmentLevel(Enchantment.power.effectId, this.getHeldItem());
            int punchLevel = EnchantmentHelper.getEnchantmentLevel(Enchantment.punch.effectId, this.getHeldItem());
            if (powerLevel > 0) {
                arrow.setDamage(arrow.getDamage() + powerLevel * 0.5D + 0.5D);
            }
            if (punchLevel > 0) {
                arrow.setKnockbackStrength(punchLevel);
            }
            if (EnchantmentHelper.getEnchantmentLevel(Enchantment.flame.effectId, this.getHeldItem()) > 0) {
                arrow.setFire(100);
            }
            this.playSound("random.bow", 1.0F, 1.0F / (this.rand.nextFloat() * 0.4F + 0.8F));
            this.worldObj.spawnEntityInWorld(arrow);
        }
    }
}

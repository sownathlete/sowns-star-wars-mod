package com.sown.outerrim.entities;

import com.sown.outerrim.registry.ItemRegister;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLivingBase;
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
import net.minecraft.world.World;

public class MobCloneTrooperP1 extends EntityTameable implements IRangedAttackMob {

    public MobCloneTrooperP1(World worldIn) {
        super(worldIn);
        this.setSize(1.0f, 2.0f);
        this.getNavigator().setCanSwim(true);
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
        this.targetTasks.addTask(4, new EntityAINearestAttackableTarget(this, EntityZombie.class, 0, true));
        this.targetTasks.addTask(5, new EntityAINearestAttackableTarget(this, EntitySkeleton.class, 0, true));
        this.targetTasks.addTask(6, new EntityAINearestAttackableTarget(this, EntityCreeper.class, 0, true));
        this.targetTasks.addTask(7, new EntityAINearestAttackableTarget(this, EntitySpider.class, 0, true));
        this.targetTasks.addTask(8, new EntityAINearestAttackableTarget(this, EntityEnderman.class, 0, true));
        this.targetTasks.addTask(9, new EntityAINearestAttackableTarget(this, EntitySilverfish.class, 0, true));

        int textureIndex = this.rand.nextInt(3) + 3; // Use male textures only
        this.getDataWatcher().updateObject(25, textureIndex);
        equipArmor();
    }

    private void equipArmor() {
//        this.setCurrentItemOrArmor(4, new ItemStack(ItemRegister.getRegisteredItem("clone_trooper_p1Helmet")));
//        this.setCurrentItemOrArmor(3, new ItemStack(ItemRegister.getRegisteredItem("clone_trooper_p1Chestplate")));
//        this.setCurrentItemOrArmor(2, new ItemStack(ItemRegister.getRegisteredItem("clone_trooper_p1Leggings")));
//        this.setCurrentItemOrArmor(1, new ItemStack(ItemRegister.getRegisteredItem("clone_trooper_p1Boots")));
        this.setCurrentItemOrArmor(0, new ItemStack(ItemRegister.getRegisteredItem("customBlaster"))); // Equip the custom blaster
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(50.0);
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(20.0);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.3);
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
            this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(20.0D);
        } else {
            this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(8.0D);
        }
    }

    @Override
    public boolean interact(EntityPlayer player) {
        ItemStack itemstack = player.getCurrentEquippedItem();

        // Taming logic
        if (!this.isTamed() && itemstack != null && itemstack.getItem() == ItemRegister.getRegisteredItem("credit_chit")) {
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

        // Interact to give item to the mob or make it sit/stand
        if (this.isTamed() && this.func_152114_e(player)) {
            if (player.isSneaking() && itemstack != null) {
                // Check if the item is armor and equip it in the correct slot
                if (itemstack.getItem() instanceof ItemArmor) {
                    ItemArmor armor = (ItemArmor) itemstack.getItem();
                    int armorSlot = 3 - armor.armorType; // Convert armor type to armor slot index
                    ItemStack currentArmor = this.getCurrentArmor(armorSlot);

                    // Drop current armor if exists
                    if (currentArmor != null && !this.worldObj.isRemote) {
                        this.entityDropItem(currentArmor, 0.5F);
                    }

                    // Equip new armor
                    this.setCurrentItemOrArmor(armorSlot + 1, itemstack);
                } else {
                    // Drop current held item if exists
                    ItemStack currentItem = this.getHeldItem();
                    if (currentItem != null && !this.worldObj.isRemote) {
                        this.entityDropItem(currentItem, 0.5F);
                    }

                    // Equip new item
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
            ((EntityPlayer) this.getOwner()).addChatMessage(new ChatComponentText(this.getCommandSenderName() + " has died."));
        }
    }

    @Override
    public void attackEntityWithRangedAttack(EntityLivingBase target, float distanceFactor) {
        ItemStack itemstack = this.getHeldItem();

        if (itemstack != null && itemstack.getItem() == ItemRegister.getRegisteredItem("customBlaster")) { // Check if it's holding the custom blaster
            EntityLaserProjectile entityLaser = new EntityLaserProjectile(this.worldObj, this, target, 1.6F);
            entityLaser.setDamage((double) (distanceFactor * 2.0F) + this.rand.nextGaussian() * 0.25D + (double) ((float) this.worldObj.difficultySetting.getDifficultyId() * 0.11F));

            // Adjust target aiming height
            entityLaser.posY -= 1.25;

            int power = EnchantmentHelper.getEnchantmentLevel(Enchantment.power.effectId, itemstack);
            if (power > 0) {
                entityLaser.setDamage(entityLaser.getDamage() + (double) power * 0.5D + 0.5D);
            }

            int punch = EnchantmentHelper.getEnchantmentLevel(Enchantment.punch.effectId, itemstack);
            if (punch > 0) {
                entityLaser.setKnockbackStrength(punch);
            }

            if (EnchantmentHelper.getEnchantmentLevel(Enchantment.flame.effectId, itemstack) > 0) {
                entityLaser.setFire(100);
            }

            itemstack.damageItem(1, this);
            this.playSound("outerrim:item.blaster.dc15s", 1.0F, 1.0F / (this.getRNG().nextFloat() * 0.4F + 0.8F)); // Play custom blaster sound
            this.worldObj.spawnEntityInWorld(entityLaser);
        }
    }
}

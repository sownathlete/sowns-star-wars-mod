package com.sown.outerrim.entities;

import cpw.mods.fml.common.Loader;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

import java.lang.reflect.Method;

public class EntityCountDooku extends EntityCreature {

    private int attackCooldown = 0;

    public EntityCountDooku(World world) {
        super(world);
        this.setSize(0.6F, 1.8F);
        this.experienceValue = 25;

        // Behavior: wander, look, idle, attack players
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(1, new EntityAIAttackOnCollide(this, EntityLivingBase.class, 1.1D, false));
        this.tasks.addTask(2, new EntityAIWander(this, 0.8D));
        this.tasks.addTask(3, new EntityAIWatchClosest(this, EntityLivingBase.class, 8.0F));
        this.tasks.addTask(4, new EntityAILookIdle(this));

        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, false));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityLivingBase.class, 0, true));

        // Give him his lightsaber if Legends is loaded
        this.setCurrentItemOrArmor(0, tryGetLightsaber());
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(60.0D);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.32D);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(10.0D);
        this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(24.0D);
    }

    @Override
    public void onUpdate() {
        super.onUpdate();

        // Keep the saber active & stable (fixes "stub blade" bug)
        ItemStack held = this.getHeldItem();
        if (held != null && Loader.isModLoaded("legends")) {
            try {
                Class<?> cls = Class.forName("com.tihyo.starwars.items.weapons.ItemLightsaberBase");
                Method update = cls.getDeclaredMethod("updateActiveState", EntityLivingBase.class, ItemStack.class);
                update.invoke(cls, this, held);
            } catch (Throwable ignored) {}
        }
    }

    @Override
    public void onLivingUpdate() {
        super.onLivingUpdate();
        if (attackCooldown > 0) attackCooldown--;
    }

    @Override
    public boolean attackEntityAsMob(Entity target) {
        if (attackCooldown <= 0) {
            attackCooldown = 20; // 1s swing delay
            boolean result = super.attackEntityAsMob(target);
            if (result && target instanceof EntityLivingBase) {
                ((EntityLivingBase) target).attackEntityFrom(DamageSource.causeMobDamage(this),
                        (float) this.getEntityAttribute(SharedMonsterAttributes.attackDamage).getAttributeValue());
            }
            swingItem();
            return result;
        }
        return false;
    }

    private ItemStack tryGetLightsaber() {
        if (!Loader.isModLoaded("legends")) return null;
        try {
            Class<?> cls = Class.forName("com.tihyo.starwars.items.StarWarsItems");
            ItemStack redSaber = (ItemStack) cls.getField("lightsaber_red").get(null);
            if (redSaber != null) return redSaber.copy();
        } catch (Throwable ignored) {}
        return null;
    }

    @Override
    protected String getLivingSound() { return "mob.blaze.breathe"; }

    @Override
    protected String getHurtSound() { return "mob.blaze.hit"; }

    @Override
    protected String getDeathSound() { return "mob.blaze.death"; }
}

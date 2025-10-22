package com.sown.outerrim.entities;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAIAvoidEntity;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class EntityAstromech extends EntityCreature {
    public EntityAstromech(World worldIn) {
        super(worldIn);
        this.setSize(0.7f, 1.2f); // Size of the droid (width x height)
        this.getNavigator().setCanSwim(true);
        this.tasks.addTask(0, new EntityAIWander(this, 0.5)); // Wanders around
        this.tasks.addTask(1, new EntityAIAvoidEntity(this, EntityPlayer.class, 6.0f, 0.6, 0.8)); // Avoids players
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(40.0);
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(15.0); // Droids have lower health
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.3); // Movement speed
    }

    @Override
    protected String getDeathSound() {
        return "outerrim:entity.droid.flee";
    }

    @Override
    protected String getHurtSound() {
        return "outerrim:entity.droid.hurt";
    }

    @Override
    protected String getLivingSound() {
        return "outerrim:entity.droid.ambient";
    }

    @Override
    protected void func_145780_a(int x, int y, int z, Block block) {
        this.worldObj.playSoundEffect(
            this.posX, 
            this.posY, 
            this.posZ, 
            "outerrim:entity.droid.move", // Custom step sound
            0.15F, // Volume
            1.0F   // Pitch
        );
    }

    @Override
    public boolean attackEntityFrom(DamageSource source, float amount) {
        if (source.isExplosion()) {
            return false; // Droids resist explosions
        }
        return super.attackEntityFrom(source, amount);
    }

    @Override
    public String getCommandSenderName() {
        return "Astromech Droid";
    }

    @Override
    protected void entityInit() {
        super.entityInit();
        this.getDataWatcher().addObject(25, 0); // Placeholder for additional states
    }
    
    @Override
    public int getTalkInterval() {
        return 1200; // 1200 ticks = 60 seconds
    }
}

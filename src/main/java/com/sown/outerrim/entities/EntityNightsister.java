package com.sown.outerrim.entities;

import java.util.List;

import com.sown.outerrim.registry.ItemRegister;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class EntityNightsister extends EntityCreature {
    public AiFollowEntity aiFollowEntity;
    private int angerLevel;
    public EntityNightsister(World worldIn) {
        super(worldIn);
        this.setSize(0.6F, 1.8F);
        this.getNavigator().setCanSwim(true);
        this.aiFollowEntity = new AiFollowEntity((EntityLiving) this, null, 0.5);
        this.tasks.addTask(0, (EntityAIBase) this.aiFollowEntity);
        this.tasks.addTask(1, new EntityAIAttackOnCollide(this, EntityPlayer.class, 1.0D, true)); // Allow attacking players
        this.tasks.addTask(2, (EntityAIBase) new AiFreqMove((EntityLiving) this, 0.30000001192092896, 20));
        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 50, true, false, null));
        this.getNavigator().setEnterDoors(true);
        this.tasks.addTask(3, (EntityAIBase) new AiFreqMove((EntityLiving) this, 0.30000001192092896, 20));
        //this.tasks.addTask(2, (EntityAIBase) new EntityAIAttackOnCollide((EntityCreature) this, EntityPlayer.class, 0.25, true));
    }
    
    protected Entity findPlayerToAttack() {
        if(this.angerLevel == 0) {
            return null;
        } else {
            // Return the closest vulnerable player within a certain range
            return this.worldObj.getClosestVulnerablePlayerToEntity(this, 16.0D);
        }
    }

    /**
     * Called when the entity is attacked.
     */
    public boolean attackEntityFrom(DamageSource p_70097_1_, float p_70097_2_)
    {
        if (this.isEntityInvulnerable())
        {
            return false;
        }
        else
        {
            Entity entity = p_70097_1_.getEntity();

            if (entity instanceof EntityPlayer)
            {
                List list = this.worldObj.getEntitiesWithinAABBExcludingEntity(this, this.boundingBox.expand(32.0D, 32.0D, 32.0D));

                for (int i = 0; i < list.size(); ++i)
                {
                    Entity entity1 = (Entity)list.get(i);

                    if (entity1 instanceof EntityNightsister)
                    {
                        EntityNightsister zabrak = (EntityNightsister)entity1;
                        zabrak.becomeAngryAt(entity);
                    }
                }

                this.becomeAngryAt(entity);
            }

            return super.attackEntityFrom(p_70097_1_, p_70097_2_);
        }
    }

    /**
     * Causes this PigZombie to become angry at the supplied Entity (which will be a player).
     */
    private void becomeAngryAt(Entity p_70835_1_)
    {
        this.entityToAttack = p_70835_1_;
        this.angerLevel = 400 + this.rand.nextInt(400);
    }
    
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(50.0);
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(20.0);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.3);
        this.getAttributeMap().registerAttribute(SharedMonsterAttributes.attackDamage);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(3.0);
    }
    
    @Override
    public boolean attackEntityAsMob(Entity entityIn) {
        return entityIn.attackEntityFrom(DamageSource.causeMobDamage(this), (float)getEntityAttribute(SharedMonsterAttributes.attackDamage).getBaseValue());
    }
    
    protected void entityInit() {
        super.entityInit();
        this.getDataWatcher().addObject(25, 0);
        this.getDataWatcher().addObject(26, this.rand.nextInt(10));
        this.getDataWatcher().addObject(27, 0);
        this.getDataWatcher().addObject(28, 0); // Initialize gender watchable object
    }

    public String getCommandSenderName() {
        return "Nightsister";
    }

    protected String getDeathSound() {
        return "outerrim:mob.commoner.die_female";
    }

    protected String getHurtSound() {
        return "outerrim:mob.commoner.hit_female";
    }

    protected String getLivingSound() {
        return "outerrim:mob.commoner.say_female";
    }
    
    @Override
    public int getTalkInterval() {
        return 1200; // 1200 ticks = 60 seconds
    }
}
/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  com.parzivail.pswm.ai.AiFollowEntity
 *  com.parzivail.pswm.ai.AiFreqMove
 *  com.parzivail.pswm.ai.AiMelee
 *  com.parzivail.util.entity.trade.WeightedTradeItem
 *  net.minecraft.entity.DataWatcher
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityCreature
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.ai.EntityAIBase
 *  net.minecraft.entity.ai.EntityAIHurtByTarget
 *  net.minecraft.entity.ai.EntityAITasks
 *  net.minecraft.entity.ai.attributes.IAttribute
 *  net.minecraft.entity.ai.attributes.IAttributeInstance
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.pathfinding.PathNavigate
 *  net.minecraft.village.MerchantRecipe
 *  net.minecraft.village.MerchantRecipeList
 *  net.minecraft.world.World
 */
package com.sown.outerrim.entities;

import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class EntityJabba
extends EntityCreature{
    public AiFollowEntity aiFollowEntity;

    public EntityJabba(World worldIn) {
        super(worldIn);
        this.setSize(1.0f, 2.0f);
        this.getNavigator().setCanSwim(true);
        this.aiFollowEntity = new AiFollowEntity((EntityLiving)this, null, 0.5);
        this.tasks.addTask(0, (EntityAIBase)this.aiFollowEntity);
        this.getNavigator().setEnterDoors(true);
        this.tasks.addTask(1, (EntityAIBase)new AiMelee((EntityCreature)this, EntityPlayer.class, 1.0, false, 3.0f));
        this.tasks.addTask(2, (EntityAIBase)new AiFreqMove((EntityLiving)this, 1.0, 20));
        this.targetTasks.addTask(0, (EntityAIBase)new EntityAIHurtByTarget((EntityCreature)this, true));
    }

    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(50.0);
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(20.0);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.3);
    }

    protected void entityInit() {
        super.entityInit();
        this.getDataWatcher().addObject(25, (Object)this.rand.nextInt(5));
        this.getDataWatcher().addObject(26, (Object)this.rand.nextInt(10));
        this.getDataWatcher().addObject(27, (Object)0);
    }

    public String getCommandSenderName() {
        return "Jabba the Hutt";
    }

    protected String getDeathSound() {
        return "outerrim:entity.jabba.death";
    }

    protected String getHurtSound() {
        return "outerrim:entity.jabba.hurt";
    }

    protected String getLivingSound() {
        return "outerrim:entity.jabba.ambient";
    }
    
    @Override
    public int getTalkInterval() {
        return 1200; // 1200 ticks = 60 seconds
    }
}


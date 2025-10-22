package com.sown.outerrim.entities;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

public class RenderSteve extends RenderBiped {
    public static ResourceLocation texture = new ResourceLocation("textures/entity/steve.png");

    public RenderSteve(ModelBiped par1ModelBase, float par2) {
        super(par1ModelBase, par2);
    }

    protected ResourceLocation getEntityTexture(Entity entity) {
        if (entity instanceof MobSteve) {
        	MobSteve steve = (MobSteve) entity;
            switch (steve.getDataWatcher().getWatchableObjectInt(25)) {
            }
        }
        return texture;
    }

    @Override
    protected void preRenderCallback(EntityLivingBase entity, float partialTickTime) {
        if (entity instanceof MobSteve) {
            MobSteve steve = (MobSteve) entity;
            GL11.glScalef(0.9375f, 0.9375f, 0.9375f);
            
            // Check if the entity has a helmet
            if (steve.getEquipmentInSlot(4) != null) {
                ((ModelBiped) this.mainModel).bipedHeadwear.showModel = false;
            } else {
                ((ModelHuman) this.mainModel).bipedHeadwear.showModel = false;
            }

            // Check for chestplate
            if (steve.getEquipmentInSlot(3) != null) {
            	((ModelHuman) this.mainModel).jacket.showModel = false;
            	((ModelHuman) this.mainModel).leftSleeve.showModel = false;
            	((ModelHuman) this.mainModel).rightSleeve.showModel = false;
            } else {
            	((ModelHuman) this.mainModel).jacket.showModel = true;
            	((ModelHuman) this.mainModel).leftSleeve.showModel = true;
            	((ModelHuman) this.mainModel).rightSleeve.showModel = true;
            }

            // Check for leggings
            if (steve.getEquipmentInSlot(2) != null) {
            	((ModelHuman) this.mainModel).leftPantLeg.showModel = false;
            	((ModelHuman) this.mainModel).rightPantLeg.showModel = false;
            } else {
            	((ModelHuman) this.mainModel).leftPantLeg.showModel = true;
            	((ModelHuman) this.mainModel).rightPantLeg.showModel = true;
            }
             if (steve.getEquipmentInSlot(1) != null) {
            	 ((ModelHuman) this.mainModel).leftPantLeg.showModel = false;
            	 ((ModelHuman) this.mainModel).rightPantLeg.showModel = false;
             } else {
            	 ((ModelHuman) this.mainModel).leftPantLeg.showModel = true;
            	 ((ModelHuman) this.mainModel).rightPantLeg.showModel = true;
             }
        }
    }
}
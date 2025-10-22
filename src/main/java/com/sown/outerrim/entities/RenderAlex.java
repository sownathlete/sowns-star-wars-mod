package com.sown.outerrim.entities;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

public class RenderAlex extends RenderBiped {
    public static ResourceLocation texture = new ResourceLocation("textures/entity/alex.png");

    public RenderAlex(ModelBiped par1ModelBase, float par2) {
        super(par1ModelBase, par2);
    }

    protected ResourceLocation getEntityTexture(Entity entity) {
        if (entity instanceof MobAlex) {
        	MobAlex alex = (MobAlex) entity;
            switch (alex.getDataWatcher().getWatchableObjectInt(25)) {
            }
        }
        return texture;
    }

    @Override
    protected void preRenderCallback(EntityLivingBase entity, float partialTickTime) {
        if (entity instanceof MobAlex) {
            MobAlex alex = (MobAlex) entity;
            GL11.glScalef(0.9375f, 0.9375f, 0.9375f);
            
            // Check if the entity has a helmet
            if (alex.getEquipmentInSlot(4) != null) {
                ((ModelBiped) this.mainModel).bipedHeadwear.showModel = false;
            } else {
                ((ModelHumanSlim) this.mainModel).bipedHeadwear.showModel = false;
            }

            // Check for chestplate
            if (alex.getEquipmentInSlot(3) != null) {
            	((ModelHumanSlim) this.mainModel).jacket.showModel = false;
            	((ModelHumanSlim) this.mainModel).leftSleeve.showModel = false;
            	((ModelHumanSlim) this.mainModel).rightSleeve.showModel = false;
            } else {
            	((ModelHumanSlim) this.mainModel).jacket.showModel = true;
            	((ModelHumanSlim) this.mainModel).leftSleeve.showModel = true;
            	((ModelHumanSlim) this.mainModel).rightSleeve.showModel = true;
            }

            // Check for leggings
            if (alex.getEquipmentInSlot(2) != null) {
            	((ModelHumanSlim) this.mainModel).leftPantLeg.showModel = false;
            	((ModelHumanSlim) this.mainModel).rightPantLeg.showModel = false;
            } else {
            	((ModelHumanSlim) this.mainModel).leftPantLeg.showModel = true;
            	((ModelHumanSlim) this.mainModel).rightPantLeg.showModel = true;
            }
             if (alex.getEquipmentInSlot(1) != null) {
            	 ((ModelHumanSlim) this.mainModel).leftPantLeg.showModel = false;
            	 ((ModelHumanSlim) this.mainModel).rightPantLeg.showModel = false;
             } else {
            	 ((ModelHumanSlim) this.mainModel).leftPantLeg.showModel = true;
            	 ((ModelHumanSlim) this.mainModel).rightPantLeg.showModel = true;
             }
        }
    }
}
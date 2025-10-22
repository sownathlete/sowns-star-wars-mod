package com.sown.outerrim.entities;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

public class RenderInquisitor
extends RenderBiped {
    public static ResourceLocation z1 = new ResourceLocation("outerrim", "textures/models/species/human/inquisitor/female/inquisitor.png");
    public static ResourceLocation z2 = new ResourceLocation("outerrim", "textures/models/species/human/inquisitor/female/inquisitor.png");
    public static ResourceLocation z3 = new ResourceLocation("outerrim", "textures/models/species/human/inquisitor/female/inquisitor.png");
    public static ResourceLocation z4 = new ResourceLocation("outerrim", "textures/models/species/human/inquisitor/male/inquisitor.png");
    public static ResourceLocation z5 = new ResourceLocation("outerrim", "textures/models/species/human/inquisitor/male/inquisitor.png");
    public static ResourceLocation z6 = new ResourceLocation("outerrim", "textures/models/species/human/inquisitor/male/inquisitor.png");
    public static ResourceLocation texture = new ResourceLocation("textures/entity/steve.png");

    public RenderInquisitor(ModelBiped par1ModelBase, float par2) {
        super(par1ModelBase, par2);
    }

    protected ResourceLocation getEntityTexture(Entity entity) {
        if (entity instanceof MobInquisitor) {
            MobInquisitor commoner = (MobInquisitor)entity;
            switch (commoner.getDataWatcher().getWatchableObjectInt(25)) {
                case 0: {
                    return z1;
                }
                case 1: {
                    return z2;
                }
                case 2: {
                    return z3;
                }
                case 3: {
                    return z4;
                }
                case 4: {
                    return z5;
                }
                case 5: {
                    return z6;
                }
            }
        }
        return z4;
    }

    @Override
    protected void preRenderCallback(EntityLivingBase entity, float partialTickTime) {
        if (entity instanceof MobInquisitor) {
        	MobInquisitor inquisitor = (MobInquisitor) entity;
            GL11.glScalef(0.9375f, 0.9375f, 0.9375f);
            
            // Check if the entity has a helmet
            if (inquisitor.getEquipmentInSlot(4) != null) {
                ((ModelBiped) this.mainModel).bipedHeadwear.showModel = false;
                ((ModelHuman) this.mainModel).bipedHeadwear.showModel = false;
            } else {
                ((ModelHuman) this.mainModel).bipedHeadwear.showModel = false;
            }

            // Check for chestplate
            if (inquisitor.getEquipmentInSlot(3) != null) {
            	((ModelHuman) this.mainModel).jacket.showModel = false;
            	((ModelHuman) this.mainModel).leftSleeve.showModel = false;
            	((ModelHuman) this.mainModel).rightSleeve.showModel = false;
            } else {
            	((ModelHuman) this.mainModel).jacket.showModel = true;
            	((ModelHuman) this.mainModel).leftSleeve.showModel = true;
            	((ModelHuman) this.mainModel).rightSleeve.showModel = true;
            }

            // Check for leggings
            if (inquisitor.getEquipmentInSlot(2) != null) {
            	((ModelHuman) this.mainModel).leftPantLeg.showModel = false;
            	((ModelHuman) this.mainModel).rightPantLeg.showModel = false;
            } else {
            	((ModelHuman) this.mainModel).leftPantLeg.showModel = true;
            	((ModelHuman) this.mainModel).rightPantLeg.showModel = true;
            }
             if (inquisitor.getEquipmentInSlot(1) != null) {
            	 ((ModelHuman) this.mainModel).leftPantLeg.showModel = false;
            	 ((ModelHuman) this.mainModel).rightPantLeg.showModel = false;
             } else {
            	 ((ModelHuman) this.mainModel).leftPantLeg.showModel = true;
            	 ((ModelHuman) this.mainModel).rightPantLeg.showModel = true;
             }
        }
    }
}

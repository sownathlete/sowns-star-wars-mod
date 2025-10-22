package com.sown.outerrim.entities;

import org.lwjgl.opengl.GL11;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

public class RenderCloneTrooper extends RenderLiving {
    private final ResourceLocation texture;  // Dynamic texture based on trooper type

    // Constructor that accepts the model and texture path
    public RenderCloneTrooper(ModelBase model, float shadowSize, String trooperType) {
        super(model, shadowSize);
        this.texture = new ResourceLocation("outerrim", "textures/models/species/human/clone/" + trooperType + "/phase2.png");
    }

    @Override
    protected ResourceLocation getEntityTexture(Entity entity) {
        return texture;  // Return the dynamically generated texture
    }

    @Override
    protected void preRenderCallback(EntityLivingBase entity, float partialTickTime) {
        if (entity instanceof EntityCloneTrooperBase) {
        	EntityCloneTrooperBase trooper = (EntityCloneTrooperBase) entity;

            // Apply scaling
            GL11.glScalef(0.9375f, 0.9375f, 0.9375f);

            // Helmet handling
            if (trooper.getEquipmentInSlot(4) != null) {
                // Hide helmet model if wearing a helmet
                ((ModelCloneTrooperPhase2) this.mainModel).helmet.showModel = false;
            } else {
                ((ModelCloneTrooperPhase2) this.mainModel).helmet.showModel = true;
            }

            // Chestplate handling
            if (trooper.getEquipmentInSlot(3) != null) {
                ((ModelCloneTrooperPhase2) this.mainModel).jacket.showModel = false;
                ((ModelCloneTrooperPhase2) this.mainModel).leftSleeve.showModel = false;
                ((ModelCloneTrooperPhase2) this.mainModel).rightSleeve.showModel = false;
            } else {
                ((ModelCloneTrooperPhase2) this.mainModel).jacket.showModel = true;
                ((ModelCloneTrooperPhase2) this.mainModel).leftSleeve.showModel = true;
                ((ModelCloneTrooperPhase2) this.mainModel).rightSleeve.showModel = true;
            }

            // Leggings handling
            if (trooper.getEquipmentInSlot(2) != null) {
                ((ModelCloneTrooperPhase2) this.mainModel).leftPantLeg.showModel = false;
                ((ModelCloneTrooperPhase2) this.mainModel).rightPantLeg.showModel = false;
            } else {
                ((ModelCloneTrooperPhase2) this.mainModel).leftPantLeg.showModel = true;
                ((ModelCloneTrooperPhase2) this.mainModel).rightPantLeg.showModel = true;
            }

            // Boots handling
            if (trooper.getEquipmentInSlot(1) != null) {
                ((ModelCloneTrooperPhase2) this.mainModel).leftPantLeg.showModel = false;
                ((ModelCloneTrooperPhase2) this.mainModel).rightPantLeg.showModel = false;
            } else {
                ((ModelCloneTrooperPhase2) this.mainModel).leftPantLeg.showModel = true;
                ((ModelCloneTrooperPhase2) this.mainModel).rightPantLeg.showModel = true;
            }
        }
    }
}

package com.sown.outerrim.entities;

import org.lwjgl.opengl.GL11;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

public class RenderReconTrooper extends RenderLiving {
    // Texture for the recon trooper
    public static final ResourceLocation texture = new ResourceLocation("outerrim", "textures/models/entity/recon_trooper/white.png");

    // Constructor to set the model and shadow size
    public RenderReconTrooper(ModelBase model, float shadowSize) {
        super(model, shadowSize);
    }

    @Override
    protected ResourceLocation getEntityTexture(Entity entity) {
        // Ensure the correct texture is used for the recon trooper
        return texture;
    }

    @Override
    protected void preRenderCallback(EntityLivingBase entity, float partialTickTime) {
        if (entity instanceof EntityReconTrooperBase) {
            EntityReconTrooperBase trooper = (EntityReconTrooperBase) entity;

            // Apply scaling or other transformations before rendering
            GL11.glScalef(0.9375f, 0.9375f, 0.9375f);

            // Helmet handling
            if (trooper.getEquipmentInSlot(4) != null) {
                // Hide helmet model if wearing a helmet
                ((ModelReconTrooper) this.mainModel).helmet.showModel = false;
            } else {
                ((ModelReconTrooper) this.mainModel).helmet.showModel = true;
            }

            // Chestplate handling
            if (trooper.getEquipmentInSlot(3) != null) {
                ((ModelReconTrooper) this.mainModel).jacket.showModel = false;
                ((ModelReconTrooper) this.mainModel).leftSleeve.showModel = false;
                ((ModelReconTrooper) this.mainModel).rightSleeve.showModel = false;
            } else {
                ((ModelReconTrooper) this.mainModel).jacket.showModel = true;
                ((ModelReconTrooper) this.mainModel).leftSleeve.showModel = true;
                ((ModelReconTrooper) this.mainModel).rightSleeve.showModel = true;
            }

            // Leggings handling
            if (trooper.getEquipmentInSlot(2) != null) {
                ((ModelReconTrooper) this.mainModel).leftPantLeg.showModel = false;
                ((ModelReconTrooper) this.mainModel).rightPantLeg.showModel = false;
            } else {
                ((ModelReconTrooper) this.mainModel).leftPantLeg.showModel = true;
                ((ModelReconTrooper) this.mainModel).rightPantLeg.showModel = true;
            }

            // Boots handling
            if (trooper.getEquipmentInSlot(1) != null) {
                ((ModelReconTrooper) this.mainModel).leftPantLeg.showModel = false;
                ((ModelReconTrooper) this.mainModel).rightPantLeg.showModel = false;
            } else {
                ((ModelReconTrooper) this.mainModel).leftPantLeg.showModel = true;
                ((ModelReconTrooper) this.mainModel).rightPantLeg.showModel = true;
            }
        }
    }
}

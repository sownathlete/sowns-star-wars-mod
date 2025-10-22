package com.sown.outerrim.entities;

import org.lwjgl.opengl.GL11;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

public class RenderCaptainRex extends RenderLiving {
    private static final ResourceLocation texture = new ResourceLocation("outerrim", "textures/models/entity/captain_rex/captain_rex.png");

    // Constructor that accepts the model and shadow size
    public RenderCaptainRex(ModelBase model, float shadowSize) {
        super(model, shadowSize);
    }

    @Override
    protected ResourceLocation getEntityTexture(Entity entity) {
        return texture;  // Return the Captain Rex texture
    }

    @Override
    protected void preRenderCallback(EntityLivingBase entity, float partialTickTime) {
        if (entity instanceof EntityCloneTrooperBase) {
            EntityCloneTrooperBase trooper = (EntityCloneTrooperBase) entity;

            // Apply scaling for Captain Rex
            GL11.glScalef(0.9375f, 0.9375f, 0.9375f);

            // Helmet handling
            if (trooper.getEquipmentInSlot(4) != null) {
                // Hide helmet model if wearing a helmet
                ((ModelCaptainRex) this.mainModel).helmet.showModel = false;
            } else {
                ((ModelCaptainRex) this.mainModel).helmet.showModel = true;
            }

            // Chestplate handling
            if (trooper.getEquipmentInSlot(3) != null) {
                ((ModelCaptainRex) this.mainModel).jacket.showModel = false;
                ((ModelCaptainRex) this.mainModel).leftSleeve.showModel = false;
                ((ModelCaptainRex) this.mainModel).rightSleeve.showModel = false;
            } else {
                ((ModelCaptainRex) this.mainModel).jacket.showModel = true;
                ((ModelCaptainRex) this.mainModel).leftSleeve.showModel = true;
                ((ModelCaptainRex) this.mainModel).rightSleeve.showModel = true;
            }

            // Leggings handling
            if (trooper.getEquipmentInSlot(2) != null) {
                ((ModelCaptainRex) this.mainModel).leftPantLeg.showModel = false;
                ((ModelCaptainRex) this.mainModel).rightPantLeg.showModel = false;
            } else {
                ((ModelCaptainRex) this.mainModel).leftPantLeg.showModel = true;
                ((ModelCaptainRex) this.mainModel).rightPantLeg.showModel = true;
            }

            // Boots handling
            if (trooper.getEquipmentInSlot(1) != null) {
                ((ModelCaptainRex) this.mainModel).leftPantLeg.showModel = false;
                ((ModelCaptainRex) this.mainModel).rightPantLeg.showModel = false;
            } else {
                ((ModelCaptainRex) this.mainModel).leftPantLeg.showModel = true;
                ((ModelCaptainRex) this.mainModel).rightPantLeg.showModel = true;
            }
        }
    }
}

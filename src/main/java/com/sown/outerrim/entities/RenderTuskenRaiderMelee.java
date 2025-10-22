package com.sown.outerrim.entities;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

public class RenderTuskenRaiderMelee extends RenderBiped {
    private static final ResourceLocation texture = new ResourceLocation("outerrim", "textures/models/entity/tusken_raider/tusken_raider_melee.png");

    public RenderTuskenRaiderMelee(ModelBiped par1ModelBase, float par2) {
        super(par1ModelBase, par2);
    }

    protected ResourceLocation getEntityTexture(Entity entity) {
        if (entity instanceof EntityTuskenRaider) {
        	EntityTuskenRaider steve = (EntityTuskenRaider) entity;
            switch (steve.getDataWatcher().getWatchableObjectInt(25)) {
            }
        }
        return texture;
    }

    @Override
    protected void preRenderCallback(EntityLivingBase entity, float partialTickTime) {
        if (entity instanceof EntityTuskenRaider) {
            EntityTuskenRaider tusken = (EntityTuskenRaider) entity;

            // Check if the entity is a child and apply a smaller scale
            if (tusken.isChild()) {
                GL11.glScalef(0.5f, 0.5f, 0.5f); // Scale down the model to 50% for children
            } else {
                GL11.glScalef(0.9375f, 0.9375f, 0.9375f); // Normal scale for adults
            }

            // Check for chestplate
            if (tusken.getEquipmentInSlot(3) != null) {
                ((ModelTuskenRaiderMelee) this.mainModel).jacket.showModel = false;
                ((ModelTuskenRaiderMelee) this.mainModel).leftSleeve.showModel = false;
                ((ModelTuskenRaiderMelee) this.mainModel).rightSleeve.showModel = false;
            } else {
                ((ModelTuskenRaiderMelee) this.mainModel).jacket.showModel = true;
                ((ModelTuskenRaiderMelee) this.mainModel).leftSleeve.showModel = true;
                ((ModelTuskenRaiderMelee) this.mainModel).rightSleeve.showModel = true;
            }

            // Check for leggings
            if (tusken.getEquipmentInSlot(2) != null) {
                ((ModelTuskenRaiderMelee) this.mainModel).leftPantLeg.showModel = false;
                ((ModelTuskenRaiderMelee) this.mainModel).rightPantLeg.showModel = false;
            } else {
                ((ModelTuskenRaiderMelee) this.mainModel).leftPantLeg.showModel = true;
                ((ModelTuskenRaiderMelee) this.mainModel).rightPantLeg.showModel = true;
            }

            // Check for boots
            if (tusken.getEquipmentInSlot(1) != null) {
                ((ModelTuskenRaiderMelee) this.mainModel).leftPantLeg.showModel = false;
                ((ModelTuskenRaiderMelee) this.mainModel).rightPantLeg.showModel = false;
            } else {
                ((ModelTuskenRaiderMelee) this.mainModel).leftPantLeg.showModel = true;
                ((ModelTuskenRaiderMelee) this.mainModel).rightPantLeg.showModel = true;
            }
        }
    }

}
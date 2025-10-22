package com.sown.outerrim.entities;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

public class RenderNightsister
extends RenderBiped {
    public static ResourceLocation z1 = new ResourceLocation("outerrim", "textures/models/species/dathomirian/female/nightsister1.png");
    public static ResourceLocation z2 = new ResourceLocation("outerrim", "textures/models/species/dathomirian/female/nightsister2.png");
    public static ResourceLocation texture = new ResourceLocation("textures/entity/steve.png");

    public RenderNightsister(ModelHuman par1ModelBase, float par2) {
        super(par1ModelBase, par2);
    }

    protected ResourceLocation getEntityTexture(Entity entity) {
        if (entity instanceof EntityNightsister) {
        	EntityNightsister commoner = (EntityNightsister)entity;
            switch (commoner.getDataWatcher().getWatchableObjectInt(25)) {
                case 0: {
                    return z1;
                }
                case 1: {
                    return z2;
                }
            }
        }
        return z1;
    }

    @Override
    protected void preRenderCallback(EntityLivingBase entity, float partialTickTime) {
        if (entity instanceof EntityNightsister) {
        	GL11.glScalef(0.9375f, 0.9375f, 0.9375f);
        }
    }
    
    
}


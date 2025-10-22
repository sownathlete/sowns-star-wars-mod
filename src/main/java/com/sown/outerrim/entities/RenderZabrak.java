package com.sown.outerrim.entities;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

public class RenderZabrak extends RenderBiped {
    public static ResourceLocation z1 = new ResourceLocation("outerrim", "textures/models/species/dathomirian/male/zabrak1.png");
    public static ResourceLocation z2 = new ResourceLocation("outerrim", "textures/models/species/dathomirian/male/zabrak2.png");
    public static ResourceLocation z3 = new ResourceLocation("outerrim", "textures/models/species/dathomirian/male/zabrak3.png");
    public static ResourceLocation texture = new ResourceLocation("textures/entity/steve.png");

    public RenderZabrak(ModelBiped par1ModelBase, float par2) {
        super(par1ModelBase, par2);
    }

    protected ResourceLocation getEntityTexture(Entity entity) {
        if (entity instanceof EntityZabrak) {
            EntityZabrak commoner = (EntityZabrak) entity;
            switch (commoner.getDataWatcher().getWatchableObjectInt(25)) {
                case 0:
                    return z1;
                case 1:
                    return z2;
                case 2:
                    return z3;
                default:
                    return z1;
            }
        }
        return z1;
    }

    @Override
    protected void preRenderCallback(EntityLivingBase entity, float partialTickTime) {
        if (entity instanceof EntityZabrak) {
            GL11.glScalef(0.9375f, 0.9375f, 0.9375f);
        }
    }
}

package com.sown.outerrim.entities;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

@SideOnly(Side.CLIENT)
public class RenderFalumpaset extends RenderLiving {
    private static final ResourceLocation texture =
        new ResourceLocation("outerrim",
                             "textures/models/species/falumpaset/falumpaset.png");

    public RenderFalumpaset(ModelFalumpaset model, float shadowSize) {
        super(model, shadowSize);
    }

    @Override
    protected ResourceLocation getEntityTexture(Entity entity) {
        return texture;
    }

    @Override
    protected void preRenderCallback(EntityLivingBase entity, float partialTickTime) {
        if (entity instanceof EntityFalumpaset) {
            EntityFalumpaset fal = (EntityFalumpaset)entity;
            float scale = fal.isChild() ? 0.5F : 1.0F;
            GL11.glScalef(scale, scale, scale);
        }
    }
}

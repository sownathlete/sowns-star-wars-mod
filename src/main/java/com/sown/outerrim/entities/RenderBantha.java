package com.sown.outerrim.entities;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

@SideOnly(Side.CLIENT)
public class RenderBantha extends RenderLiving {
    private static final ResourceLocation texture = new ResourceLocation("outerrim", "textures/models/bantha.png");

    public RenderBantha() { 
        super(new Bantha(), 0.5F);
    }

    @Override
    protected ResourceLocation getEntityTexture(Entity entity) {
        return texture;
    }

    @Override
    public void doRender(Entity entity, double x, double y, double z, float yaw, float partialTickTime) {
        GL11.glPushMatrix();
        GL11.glTranslated(x, y, z);
        
        if (entity instanceof EntityLivingBase && ((EntityLivingBase) entity).isChild()) {
            GL11.glScalef(0.5F, 0.5F, 0.5F);
        } else {
            GL11.glScalef(1.0F, 1.0F, 1.0F);
        }

        super.doRender(entity, 0, 0, 0, yaw, partialTickTime);
        GL11.glPopMatrix();
    }

}

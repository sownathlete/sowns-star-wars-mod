package com.sown.outerrim.entities;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class RenderKaadu extends RenderLiving {
    private static final ResourceLocation texture =
        new ResourceLocation("outerrim", "textures/models/species/kaadu/kaadu.png");

    public RenderKaadu(ModelKaadu model, float shadowSize) {
        super(model, shadowSize);
    }

    @Override
    protected ResourceLocation getEntityTexture(net.minecraft.entity.Entity entity) {
        return texture;
    }

    @Override
    protected void preRenderCallback(EntityLivingBase entity, float partialTickTime) {
        if (entity instanceof EntityTameable && entity.isChild()) {
            // half-size for babies
            GL11.glScalef(0.5F, 0.5F, 0.5F);
        } else {
            // full size for adults
            GL11.glScalef(1.0F, 1.0F, 1.0F);
        }
    }
}

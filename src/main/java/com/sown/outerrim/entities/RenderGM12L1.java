package com.sown.outerrim.entities;

import org.lwjgl.opengl.GL11;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

/**
 * Renders the GM12L1 entity using ModelGM12L1 and a single texture.
 */
public class RenderGM12L1 extends RenderLiving {
    // Path to the GM12L1 skin texture
    private static final ResourceLocation GM12L1_TEXTURE =
        new ResourceLocation("outerrim", "textures/models/species/gm12-l1/gm12-l1.png");

    /**
     * @param model       an instance of ModelGM12L1
     * @param shadowSize  the shadow radius (e.g. 0.5F)
     */
    public RenderGM12L1(ModelGM12L1 model, float shadowSize) {
        super(model, shadowSize);
    }

    @Override
    protected ResourceLocation getEntityTexture(Entity entity) {
        // We assume the entity is always an EntityGM12L1
        return GM12L1_TEXTURE;
    }

    @Override
    protected void preRenderCallback(EntityLivingBase entity, float partialTickTime) {
        if (entity instanceof EntityGM12L1) {
        	GL11.glScalef(0.9375f, 0.9375f, 0.9375f);
        }
    }
}

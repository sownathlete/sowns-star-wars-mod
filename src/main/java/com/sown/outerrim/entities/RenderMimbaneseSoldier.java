package com.sown.outerrim.entities;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

/**
 * Simple renderer for the Mimbanese soldier.
 * Uses a single texture and the custom {@link ModelMimbaneseSoldier}.
 */
public class RenderMimbaneseSoldier extends RenderLiving {
    /** One-and-only texture for Mimbanese infantry */
    private static final ResourceLocation TEXTURE =
        new ResourceLocation("outerrim", "textures/models/species/mimbanese/mimbanese_soldier.png");

    public RenderMimbaneseSoldier() {
        super(new ModelMimbaneseSoldier(), 0.5F);   // 0.5 = shadow size
    }

    @Override
    protected ResourceLocation getEntityTexture(Entity entity) {
        return TEXTURE;
    }

    /** Slight down-scale so he isnt as tall/broad as a default player model */
    @Override
    protected void preRenderCallback(EntityLivingBase entity, float partialTickTime) {
        GL11.glScalef(0.9375F, 0.9375F, 0.9375F);   // same factor Mojang uses for armor stands
    }
}

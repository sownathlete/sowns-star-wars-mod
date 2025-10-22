package com.sown.outerrim.entities;

import org.lwjgl.opengl.GL11;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

public class RenderDarthVader extends RenderLiving {
    private static final ResourceLocation VADER_TEXTURE =
        new ResourceLocation("outerrim", "textures/models/species/human/darth_vader/darth_vader.png");

    public RenderDarthVader() {
        super(new ModelDarthVader(), 0.5f);
    }

    @Override
    protected ResourceLocation getEntityTexture(Entity entity) {
        return VADER_TEXTURE;
    }

    @Override
    protected void preRenderCallback(EntityLivingBase entity, float partialTickTime) {
        // Slight scaling for Vader
        GL11.glScalef(0.9375f, 0.9375f, 0.9375f);
    }
}
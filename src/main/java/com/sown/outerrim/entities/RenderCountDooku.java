package com.sown.outerrim.entities;

import org.lwjgl.opengl.GL11;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

public class RenderCountDooku extends RenderLiving {
    // Replace with your actual texture path
    private static final ResourceLocation COUNT_DOOKU_TEXTURE =
        new ResourceLocation("outerrim", "textures/models/species/human/count_dooku/count_dooku.png");

    public RenderCountDooku() {
        // new ModelCountDooku(), shadow size (match your other humanoids0.5f is typical)
        super(new ModelCountDooku(), 0.5f);
    }

    @Override
    protected ResourceLocation getEntityTexture(Entity entity) {
        // We assume this renderer is only ever used for EntityCountDooku
        return COUNT_DOOKU_TEXTURE;
    }

    @Override
    protected void preRenderCallback(EntityLivingBase entity, float partialTickTime) {
        // If you want to scale him differently, adjust here.
        // For a slight shrink: GL11.glScalef(0.95f, 0.95f, 0.95f);
        // Default (no change):
    	GL11.glScalef(0.9375f, 0.9375f, 0.9375f);
    }
}

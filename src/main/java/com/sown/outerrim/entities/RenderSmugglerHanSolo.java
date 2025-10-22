package com.sown.outerrim.entities;

import org.lwjgl.opengl.GL11;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

public class RenderSmugglerHanSolo extends RenderLiving {
    private static final ResourceLocation SMUGGLER_HAN_SOLO_TEXTURE =
        new ResourceLocation("outerrim", "textures/models/species/human/han_solo/smuggler_han_solo.png");

    public RenderSmugglerHanSolo(ModelSmugglerHanSolo model, float shadowSize) {
        super(model, shadowSize);
    }

    @Override
    protected ResourceLocation getEntityTexture(Entity entity) {
        return SMUGGLER_HAN_SOLO_TEXTURE;
    }

    @Override
    protected void preRenderCallback(EntityLivingBase entity, float partialTickTime) {
        if (entity instanceof EntitySmugglerHanSolo) {
        	GL11.glScalef(0.9375f, 0.9375f, 0.9375f);
        }
    }
}

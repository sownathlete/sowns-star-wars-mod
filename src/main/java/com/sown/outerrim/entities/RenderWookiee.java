package com.sown.outerrim.entities;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

public class RenderWookiee extends RenderLiving {
    private final ModelWookieeMale   modelMale   = new ModelWookieeMale();
    private final ModelWookieeFemale modelFemale = new ModelWookieeFemale();

    private static final ResourceLocation TEXTURE_MALE   =
        new ResourceLocation("outerrim", "textures/models/species/wookiee/male.png");
    private static final ResourceLocation TEXTURE_FEMALE =
        new ResourceLocation("outerrim", "textures/models/species/wookiee/female.png");

    public RenderWookiee() {
        super(new ModelWookieeMale(), 0.5F);
    }

    @Override
    public void doRender(Entity entity, double x, double y, double z, float entityYaw, float partialTicks) {
        if (entity instanceof EntityWookiee) {
            EntityWookiee wookiee = (EntityWookiee) entity;

            ModelBase chosenModel = wookiee.isMale() ? modelMale : modelFemale;
            this.mainModel = chosenModel;
        }
        super.doRender(entity, x, y, z, entityYaw, partialTicks);
    }

    @Override
    protected ResourceLocation getEntityTexture(Entity entity) {
        if (entity instanceof EntityWookiee) {
            EntityWookiee wookiee = (EntityWookiee) entity;
            return wookiee.isMale() ? TEXTURE_MALE : TEXTURE_FEMALE;
        }
        return TEXTURE_MALE;
    }

    @Override
    protected void preRenderCallback(EntityLivingBase entity, float partialTickTime) {
        if (entity instanceof EntityWookiee) {
        	GL11.glScalef(0.9375f, 0.9375f, 0.9375f);
        }
    }
}

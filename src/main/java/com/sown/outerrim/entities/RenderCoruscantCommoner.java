package com.sown.outerrim.entities;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

public class RenderCoruscantCommoner extends RenderBiped {
    public static ResourceLocation z1 = new ResourceLocation("outerrim", "textures/models/species/human/coruscant/female/coruscant1.png");
    public static ResourceLocation z2 = new ResourceLocation("outerrim", "textures/models/species/human/coruscant/female/coruscant2.png");
    public static ResourceLocation z3 = new ResourceLocation("outerrim", "textures/models/species/human/coruscant/female/coruscant3.png");
    public static ResourceLocation z4 = new ResourceLocation("outerrim", "textures/models/species/human/coruscant/male/coruscant4.png");
    public static ResourceLocation z5 = new ResourceLocation("outerrim", "textures/models/species/human/coruscant/male/coruscant5.png");
    public static ResourceLocation z6 = new ResourceLocation("outerrim", "textures/models/species/human/coruscant/male/coruscant6.png");
    public static ResourceLocation texture = new ResourceLocation("textures/entity/steve.png");

    public RenderCoruscantCommoner(ModelBiped par1ModelBase, float par2) {
        super(par1ModelBase, par2);
    }

    protected ResourceLocation getEntityTexture(Entity entity) {
        if (entity instanceof MobCoruscantCommoner) {
            MobCoruscantCommoner commoner = (MobCoruscantCommoner) entity;
            switch (commoner.getDataWatcher().getWatchableObjectInt(25)) {
                case 0: {
                    return z1;
                }
                case 1: {
                    return z2;
                }
                case 2: {
                    return z3;
                }
                case 3: {
                    return z4;
                }
                case 4: {
                    return z5;
                }
                case 5: {
                    return z6;
                }
            }
        }
        return z4;
    }

    @Override
    protected void preRenderCallback(EntityLivingBase entity, float partialTickTime) {
        if (entity instanceof MobCoruscantCommoner) {
        	GL11.glScalef(0.9375f, 0.9375f, 0.9375f);
        }
    }
    
}
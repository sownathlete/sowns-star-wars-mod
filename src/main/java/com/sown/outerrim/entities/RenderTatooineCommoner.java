package com.sown.outerrim.entities;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

public class RenderTatooineCommoner
extends RenderBiped {
    public static ResourceLocation z1 = new ResourceLocation("outerrim", "textures/models/species/human/tatooine/female/tatooine1.png");
    public static ResourceLocation z2 = new ResourceLocation("outerrim", "textures/models/species/human/tatooine/female/tatooine2.png");
    public static ResourceLocation z3 = new ResourceLocation("outerrim", "textures/models/species/human/tatooine/female/tatooine3.png");
    public static ResourceLocation z4 = new ResourceLocation("outerrim", "textures/models/species/human/tatooine/male/tatooine1.png");
    public static ResourceLocation z5 = new ResourceLocation("outerrim", "textures/models/species/human/tatooine/male/tatooine2.png");
    public static ResourceLocation z6 = new ResourceLocation("outerrim", "textures/models/species/human/tatooine/male/tatooine3.png");
    public static ResourceLocation texture = new ResourceLocation("textures/entity/steve.png");

    public RenderTatooineCommoner(ModelBiped par1ModelBase, float par2) {
        super(par1ModelBase, par2);
    }

    protected ResourceLocation getEntityTexture(Entity entity) {
        if (entity instanceof MobTatooineCommoner) {
            MobTatooineCommoner commoner = (MobTatooineCommoner)entity;
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
        if (entity instanceof MobTatooineCommoner) {
        	GL11.glScalef(0.9375f, 0.9375f, 0.9375f);
        }
    }
}


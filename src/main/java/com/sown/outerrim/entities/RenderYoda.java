package com.sown.outerrim.entities;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

@SideOnly(Side.CLIENT)
public class RenderYoda extends RenderLiving {
    private static final ResourceLocation yodaTexture = new ResourceLocation("outerrim", "textures/models/species/yoda/yoda.png");

    public RenderYoda(ModelYoda model, float shadowSize) {
        super(model, shadowSize);  // Pass the model and shadow size
    }

    @Override
    protected ResourceLocation getEntityTexture(Entity entity) {
        return yodaTexture;  // Provide the texture for Yoda
    }
}

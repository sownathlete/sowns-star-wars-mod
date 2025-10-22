package com.sown.outerrim.entities;

import net.minecraft.client.model.ModelBase;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class RenderReconTrooper501st extends RenderReconTrooper {
    // Texture specific to 501st trooper
    private static final ResourceLocation texture = new ResourceLocation("outerrim", "textures/models/entity/recon_trooper/blue.png");

    public RenderReconTrooper501st(ModelBase model, float shadowSize) {
        super(model, shadowSize);
    }

    @Override
    protected ResourceLocation getEntityTexture(Entity entity) {
        return texture;  // Return 501st trooper texture
    }
}

package com.sown.outerrim.entities;

import net.minecraft.client.model.ModelBase;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class RenderReconTrooper41st extends RenderReconTrooper {
    // Texture specific to 41st trooper
    private static final ResourceLocation texture = new ResourceLocation("outerrim", "textures/models/entity/recon_trooper/green.png");

    public RenderReconTrooper41st(ModelBase model, float shadowSize) {
        super(model, shadowSize);
    }

    @Override
    protected ResourceLocation getEntityTexture(Entity entity) {
        return texture;  // Return 41st trooper texture
    }
}

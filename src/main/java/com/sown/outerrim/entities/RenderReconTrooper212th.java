package com.sown.outerrim.entities;

import net.minecraft.client.model.ModelBase;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class RenderReconTrooper212th extends RenderReconTrooper {
    // Texture specific to 212th trooper
    private static final ResourceLocation texture = new ResourceLocation("outerrim", "textures/models/entity/recon_trooper/orange.png");

    public RenderReconTrooper212th(ModelBase model, float shadowSize) {
        super(model, shadowSize);
    }

    @Override
    protected ResourceLocation getEntityTexture(Entity entity) {
        return texture;  // Return 212th trooper texture
    }
}

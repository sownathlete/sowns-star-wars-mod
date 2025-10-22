package com.sown.outerrim.entities;

import net.minecraft.client.model.ModelBase;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class RenderReconTrooperCoruscantGuard extends RenderReconTrooper {
    // Texture specific to Coruscant Guard trooper
    private static final ResourceLocation texture = new ResourceLocation("outerrim", "textures/models/entity/recon_trooper/red.png");

    public RenderReconTrooperCoruscantGuard(ModelBase model, float shadowSize) {
        super(model, shadowSize);
    }

    @Override
    protected ResourceLocation getEntityTexture(Entity entity) {
        return texture;  // Return Coruscant Guard trooper texture
    }
}

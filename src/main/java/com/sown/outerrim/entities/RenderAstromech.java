package com.sown.outerrim.entities;

import com.sown.outerrim.entities.ModelAstromech;
import com.sown.outerrim.entities.EntityAstromech;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class RenderAstromech extends RenderLiving {
    // Path to the astromech texture
    private static final ResourceLocation TEXTURE = new ResourceLocation(
        "outerrim", "textures/models/entity/astromech/default.png"
    );

    public RenderAstromech() {
        super(new ModelAstromech(), 0.5f); // 0.5f is the shadow size
    }

    @Override
    protected ResourceLocation getEntityTexture(Entity entity) {
        return TEXTURE;
    }

    @Override
    public void doRender(Entity entity, double x, double y, double z, float entityYaw, float partialTicks) {
        super.doRender(entity, x, y, z, entityYaw, partialTicks);
    }
}

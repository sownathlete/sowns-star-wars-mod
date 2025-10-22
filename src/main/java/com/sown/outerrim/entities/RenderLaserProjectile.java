package com.sown.outerrim.entities;

import com.sown.outerrim.entities.EntityLaserProjectile;
import net.minecraft.client.renderer.entity.RenderArrow;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class RenderLaserProjectile extends RenderArrow {
    private static final ResourceLocation laserTexture = new ResourceLocation("outerrim:textures/entities/laserProjectile.png");

    @Override
    protected ResourceLocation getEntityTexture(Entity entity) {
        return laserTexture;
    }
}

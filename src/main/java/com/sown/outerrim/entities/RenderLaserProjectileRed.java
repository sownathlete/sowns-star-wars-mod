package com.sown.outerrim.entities;

import com.sown.outerrim.entities.EntityLaserProjectile;
import net.minecraft.client.renderer.entity.RenderArrow;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class RenderLaserProjectileRed extends RenderArrow {
    private static final ResourceLocation laserTexture = new ResourceLocation("outerrim:textures/entities/laserProjectileRed.png");

    @Override
    protected ResourceLocation getEntityTexture(Entity entity) {
        return laserTexture;
    }
}

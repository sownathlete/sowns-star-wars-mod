package com.sown.outerrim.entities;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class RenderQuadrupedAnimal extends RenderLiving {
    private static final ResourceLocation animalTexture = new ResourceLocation("outerrim", "textures/entity/quadrupedanimal.png");

    public RenderQuadrupedAnimal(ModelQuadrupedAnimal model, float shadowSize) {
        super(model, shadowSize);
    }

    @Override
    protected ResourceLocation getEntityTexture(Entity entity) {
        return animalTexture;
    }
}

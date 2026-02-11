package com.sown.outerrim.entities;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

@SideOnly(Side.CLIENT)
public class RenderN1Starfighter extends RenderLiving {

    private static final ResourceLocation TEXTURE = new ResourceLocation(
            "outerrim", "textures/models/entity/vehicle/n1_starfighter.png");

    public RenderN1Starfighter(float shadowSize) {
        super(new ModelSparkN1Starfighter(), shadowSize);
    }

    @Override
    protected ResourceLocation getEntityTexture(Entity entity) {
        return TEXTURE;
    }
}

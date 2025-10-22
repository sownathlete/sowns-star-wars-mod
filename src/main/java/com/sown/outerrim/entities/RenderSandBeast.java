package com.sown.outerrim.entities;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

@SideOnly(Side.CLIENT)
public class RenderSandBeast extends RenderLiving {

    private static final ResourceLocation texture = new ResourceLocation("outerrim", "textures/models/entity/monster/sand_beast.png");

    public RenderSandBeast(ModelSandBeast model, float shadowSize) {
        super(model, shadowSize);
    }

    @Override
    protected ResourceLocation getEntityTexture(Entity entity) {
            return texture;
        }
}

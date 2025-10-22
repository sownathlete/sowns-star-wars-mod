package com.sown.outerrim.entities;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

@SideOnly(Side.CLIENT)
public class RenderBattleDroid extends RenderLiving {

    private static final ResourceLocation battleDroidTexture = new ResourceLocation("outerrim", "textures/models/entity/battle_droid/b1_battle_droid.png");

    public RenderBattleDroid(ModelBattleDroid model, float shadowSize) {
        super(model, shadowSize);
    }

    @Override
    protected ResourceLocation getEntityTexture(Entity entity) {
            return battleDroidTexture;
        }
}

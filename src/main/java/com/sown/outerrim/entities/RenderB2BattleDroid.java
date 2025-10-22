package com.sown.outerrim.entities;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

@SideOnly(Side.CLIENT)
public class RenderB2BattleDroid extends RenderLiving {
    // Define the texture resource location for the B2 Battle Droid
    private static final ResourceLocation b2BattleDroidTexture = new ResourceLocation("outerrim", "textures/models/entity/b2_battle_droid/b2_battle_droid.png");
    private ModelB2BattleDroid modelB2BattleDroid;

    // Constructor for the render class, passing the model and shadow size
    public RenderB2BattleDroid(ModelB2BattleDroid model, float shadowSize) {
        super(model, shadowSize);
    }

    // Override to provide the correct texture for the B2 Battle Droid
    @Override
    protected ResourceLocation getEntityTexture(Entity entity) {
        return b2BattleDroidTexture;  // Provide the texture for the B2 Battle Droid entity
    }
}

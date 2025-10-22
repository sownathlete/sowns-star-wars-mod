package com.sown.outerrim.entities;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

@SideOnly(Side.CLIENT)
public class RenderPelikki extends RenderLiving {
    // Define the texture resource location for the Kaadu
    private static final ResourceLocation kaaduTexture = new ResourceLocation("outerrim", "textures/models/species/pelikki/pelikki.png");

    public RenderPelikki(ModelPelikki model, float shadowSize) {
        // Call the parent class with the Kaadu model and shadow size
        super(model, shadowSize);
    }

    // Override to provide the correct texture for the Kaadu
    @Override
    protected ResourceLocation getEntityTexture(Entity entity) {
        return kaaduTexture;  // Provide the texture for the Kaadu entity
    }
}

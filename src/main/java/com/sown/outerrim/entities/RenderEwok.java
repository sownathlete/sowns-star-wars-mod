package com.sown.outerrim.entities;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

@SideOnly(Side.CLIENT)
public class RenderEwok extends RenderLiving {
    // Define the texture resource location for the Ewok
    private static final ResourceLocation ewokTexture = new ResourceLocation("outerrim", "textures/models/species/ewok/ewok.png");

    public RenderEwok(ModelEwok model, float shadowSize) {
        // Call the parent class with the Ewok model and shadow size
        super(model, shadowSize);
    }

    // Override to provide the correct texture for the Ewok
    @Override
    protected ResourceLocation getEntityTexture(Entity entity) {
        return ewokTexture;  // Provide the texture for the Ewok entity
    }
}

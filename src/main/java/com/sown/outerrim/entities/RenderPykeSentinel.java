package com.sown.outerrim.entities;

import com.sown.outerrim.entities.EntityPykeSentinel;
import com.sown.outerrim.entities.ModelPykeSentinel;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

@SideOnly(Side.CLIENT)
public class RenderPykeSentinel extends RenderLiving {
    // Change path to your texture!
    private static final ResourceLocation TEXTURE = new ResourceLocation("outerrim:textures/models/species/pyke/pyke_sentinel.png");

    public RenderPykeSentinel() {
        super(new ModelPykeSentinel(), 0.5F); // Model, shadow size
    }

    @Override
    protected ResourceLocation getEntityTexture(Entity entity) {
        return TEXTURE;
    }
    
    // (Optional) If you want to change the scaling or add glow layers, do it here.
    // For most mobs, you do not need to override doRender or preRenderCallback.
}

package com.sown.outerrim.entities.render;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

import com.sown.outerrim.entities.ModelEta2Starfighter;

@SideOnly(Side.CLIENT)
public class RenderEta2Starfighter extends RenderLiving {

    private static final ResourceLocation TEXTURE =
            new ResourceLocation("outerrim", "textures/models/entity/vehicle/eta2_starfighter.png");

    public RenderEta2Starfighter(float shadowSize) {
        super(new ModelEta2Starfighter(), shadowSize);
    }

    @Override
    protected ResourceLocation getEntityTexture(Entity entity) {
        return TEXTURE;
    }

    @Override
    protected void preRenderCallback(EntityLivingBase entity, float partialTickTime) {
        super.preRenderCallback(entity, partialTickTime);
        GL11.glScalef(0.7F, 0.7F, 0.7F);   // smaller than N-1
        GL11.glTranslatef(0.0F, 0.3F, 0.0F);
    }
}

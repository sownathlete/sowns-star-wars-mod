package com.sown.outerrim.entities;

import cpw.mods.fml.common.Loader;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

/**
 * Dooku renderer:
 *  - Uses ModelCountDooku
 *  - Scales like Legends' humanoids
 *  - If Legends is present, reflects its RenderLightsaber layer and draws it
 *    AFTER the base model so the blade appears and follows arm transforms.
 */
public class RenderCountDooku extends RenderBiped {

    private static final ResourceLocation DOOKU_TEX =
            new ResourceLocation("outerrim:textures/models/species/human/count_dooku/count_dooku.png");

    // Optional Legends blade layer (reflected)
    private Object legendsBladeLayer = null;
    private Method legendsDoRenderLayer = null;

    public RenderCountDooku() {
        super((ModelBiped) new ModelCountDooku(), 0.5F);
        tryInitLegendsLayer();
    }

    @Override
    protected ResourceLocation getEntityTexture(Entity entity) {
        return DOOKU_TEX;
    }

    @Override
    protected void preRenderCallback(EntityLivingBase entity, float partialTickTime) {
        // match Legends’ 0.9375 scale so hand offsets line up with their blade layer
        GL11.glScalef(0.9375F, 0.9375F, 0.9375F);
    }

    @Override
    public void doRender(Entity entity, double x, double y, double z, float yaw, float partialTicks) {
        super.doRender(entity, x, y, z, yaw, partialTicks);

        // Draw the blade layer if we have it
        if (legendsBladeLayer != null && legendsDoRenderLayer != null && entity instanceof EntityLivingBase) {
            try {
                GL11.glPushMatrix();
                legendsDoRenderLayer.invoke(
                        legendsBladeLayer,
                        (EntityLivingBase) entity,
                        x, y, z, yaw, partialTicks
                );
            } catch (Throwable ignored) {
                // If Legends signature changes or anything fails, just skip the layer.
            } finally {
                GL11.glPopMatrix();
            }
        }
    }

    private void tryInitLegendsLayer() {
        if (!Loader.isModLoaded("legends")) return;
        try {
            // Legends 1.7.10 usually exposes a RenderLightsaber layer with this ctor & method.
            Class<?> cls = Class.forName("com.tihyo.starwars.client.renders.layers.RenderLightsaber");
            Constructor<?> ctor = cls.getConstructor(RenderManager.class);
            legendsBladeLayer = ctor.newInstance(RenderManager.instance);

            // doRenderLayer(EntityLivingBase,double,double,double,float,float)
            legendsDoRenderLayer = cls.getMethod(
                    "doRenderLayer",
                    EntityLivingBase.class, double.class, double.class, double.class, float.class, float.class
            );
            System.out.println("[OuterRim] RenderCountDooku: Attached Legends RenderLightsaber layer.");
        } catch (Throwable t) {
            legendsBladeLayer = null;
            legendsDoRenderLayer = null;
            System.out.println("[OuterRim] RenderCountDooku: Could not attach Legends blade layer (safe to ignore).");
        }
    }
}

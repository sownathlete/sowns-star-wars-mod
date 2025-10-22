package com.sown.outerrim.entities;

import org.lwjgl.opengl.GL11;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

public class RenderKesselMineWorker extends RenderLiving {
    // Single skin for the KesselMineWorker
    private static final ResourceLocation KESSEL_MINE_WORKER_TEXTURE =
        new ResourceLocation("outerrim", "textures/models/species/human/kessel/kessel_mine_worker.png");

    public RenderKesselMineWorker(ModelKesselMineWorker model, float shadowSize) {
        super(model, shadowSize);
    }

    @Override
    protected ResourceLocation getEntityTexture(Entity entity) {
        // We assume the entity is always a KesselMineWorker
        // (no data watcher index needed since there's only one skin)
        return KESSEL_MINE_WORKER_TEXTURE;
    }

    @Override
    protected void preRenderCallback(EntityLivingBase entity, float partialTickTime) {
        if (entity instanceof EntityKesselMineWorker) {
            // If you want to scale the model slightly smaller/larger, you can adjust here.
            // For example, to scale down to 95% of normal size:
            GL11.glScalef(0.95f, 0.95f, 0.95f);
        }
    }
}

package com.sown.outerrim.entities;

import org.lwjgl.opengl.GL11;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

public class RenderJabba extends RenderJabbaBiped {
    public static final ResourceLocation y1 = new ResourceLocation("outerrim", "textures/models/species/hutt/jabba.png");

    public RenderJabba(ModelJabba par1ModelBase, float par2) {
        super(par1ModelBase, par2);
    }

    @Override
    protected ResourceLocation getEntityTexture(Entity entity) {
        if (entity instanceof EntityJabba) {
            EntityJabba commoner = (EntityJabba) entity;
            switch (commoner.getDataWatcher().getWatchableObjectInt(25)) {
                case 0:
                    return y1;
            }
        }
        return y1;
    }

    @Override
    public void doRender(Entity entity, double x, double y, double z, float yaw, float partialTickTime) {
        GL11.glPushMatrix();
        GL11.glTranslated(x, y, z);
        
        if (entity instanceof EntityLivingBase && ((EntityLivingBase) entity).isChild()) {
            GL11.glScalef(0.75F, 0.75F, 0.75F); // For child entities, half of the 0.75 scaling
        } else {
            GL11.glScalef(1.0F, 1.0F, 1.0F); // For adults, half of the 1.5 scaling
        }

        super.doRender(entity, 0, 0, 0, yaw, partialTickTime);
        GL11.glPopMatrix();
    }
}

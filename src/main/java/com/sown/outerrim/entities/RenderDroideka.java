package com.sown.outerrim.entities;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class RenderDroideka extends RenderLiving {
    private static final ResourceLocation TEXTURE = new ResourceLocation("outerrim:textures/models/entity/droideka/droideka.png");
    private static final ResourceLocation SHIELD_TEXTURE = new ResourceLocation("outerrim:textures/models/entity/droideka/droideka_shield.png");

    private final ModelDroidekaShield shieldModel = new ModelDroidekaShield(); // Shield model

    public RenderDroideka() {
        super(new ModelDroideka(), 0.5F);
    }

    @Override
    protected ResourceLocation getEntityTexture(Entity entity) {
        return TEXTURE;
    }

    @Override
    public void doRender(Entity entity, double x, double y, double z, float entityYaw, float partialTicks) {
        super.doRender(entity, x, y, z, entityYaw, partialTicks);

        // Check if the entity is a Droideka and if the shield should be visible
        if (entity instanceof EntityDroideka) {
            EntityDroideka droideka = (EntityDroideka) entity;

            if (droideka.isShieldActive()) { // Only render shield when active
                this.renderShield(droideka, x, y, z);
            }
        }
    }

    private void renderShield(EntityDroideka entity, double x, double y, double z) {
        GL11.glPushMatrix();
        
        float shieldHeight = 1.5F * 0.55F; // Increase scale factor slightly
        float offsetY = (shieldHeight / 2.0F) + 0.2F; // Adjust position accordingly

        // Translate the shield to the entity's position and adjust its height
        GL11.glTranslated(x, y + 1.5F - offsetY, z);

        // Rotate the shield to always face the player
        GL11.glRotatef(-this.renderManager.playerViewY, 0.0F, 1.0F, 0.0F);
        GL11.glRotatef(this.renderManager.playerViewX, 1.0F, 0.0F, 0.0F);

        // Scale the shield slightly bigger
        GL11.glScalef(0.55F, 0.55F, 0.55F); 

        // Enable blending for translucency
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);

        // Set the shield transparency (adjust alpha as needed)
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 0.75F); // 0.75F = slightly less transparent

        // Bind the shield texture and render it
        this.bindTexture(SHIELD_TEXTURE);
        shieldModel.render(entity, 0, 0, 0, 0, 0, 0.1F);

        // Disable blending after rendering
        GL11.glDisable(GL11.GL_BLEND);

        GL11.glPopMatrix();
    }

}

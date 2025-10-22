package com.sown.outerrim.rendering;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import org.lwjgl.opengl.GL11;

import com.sown.outerrim.models.blocks.ModelHoloProjector;
import com.sown.outerrim.tileentities.TileEntityHoloProjector;
import net.minecraft.util.ResourceLocation;
import net.minecraft.client.Minecraft;

public class RenderHoloProjector extends TileEntitySpecialRenderer {
    public static final ResourceLocation texture = new ResourceLocation("outerrim:textures/models/blocks/holoProjector.png");
    private final ModelHoloProjector model = new ModelHoloProjector();

    @Override
    public void renderTileEntityAt(TileEntity tileEntity, double x, double y, double z, float partialTicks) {
        if (tileEntity instanceof TileEntityHoloProjector) {
            TileEntityHoloProjector projector = (TileEntityHoloProjector) tileEntity;

            GL11.glPushMatrix();
            GL11.glTranslated(x + 0.5, y + 1.5, z + 0.5);  // Move to the correct block position
            GL11.glRotatef(180.0f, 0.0f, 0.0f, 1.0f);      // Rotate to ensure correct orientation
            GL11.glRotatef(90 * projector.getFacing(), 0.0f, 1.0f, 0.0f);

            Minecraft.getMinecraft().renderEngine.bindTexture(texture);

            // Render the main body of the projector (shape3, shape4, shape5)
            this.model.renderBase();

            // Always render shape6
            this.model.renderShape6(); 

            GL11.glPopMatrix();  // Restore the matrix to the default state
        }
    }
}

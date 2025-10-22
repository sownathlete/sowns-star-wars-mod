// RenderCoaxiumRefineryTESR.java
package com.sown.outerrim.rendering;

import org.lwjgl.opengl.GL11;

import com.sown.outerrim.models.blocks.ModelCoaxiumRefineryOld;
import com.sown.outerrim.tileentities.TileEntityCoaxiumRefinery;
import com.sown.util.ui.P3D;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

public class RenderCoaxiumRefineryTESROld extends TileEntitySpecialRenderer {
    private static final ResourceLocation TEX = new ResourceLocation("outerrim:textures/models/blocks/coaxium_refinery.png");
    private final ModelCoaxiumRefineryOld model = new ModelCoaxiumRefineryOld();

    @Override
    public void renderTileEntityAt(TileEntity te, double x, double y, double z, float partialTick) {
        if (!(te instanceof TileEntityCoaxiumRefinery)) return;
        TileEntityCoaxiumRefinery ref = (TileEntityCoaxiumRefinery)te;

        GL11.glPushMatrix();
        // center on block + raise to top
        GL11.glTranslated(x + 0.5, y + 1.5, z + 0.5);
        // flip because model is upside-down
        GL11.glRotatef(180f, 0, 0, 1);
        // rotate by facing
        GL11.glRotatef(90f * ref.getFacing(), 0, 1, 0);
        // optional scale tweak
        P3D.glScalef(1.0);

        Minecraft.getMinecraft().renderEngine.bindTexture(TEX);
        model.render(null, 0, 0, 0, 0, 0, 0.0625f);

        GL11.glPopMatrix();
    }
}

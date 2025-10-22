package com.sown.outerrim.rendering;

import org.lwjgl.opengl.GL11;

import com.sown.outerrim.models.blocks.ModelCoaxiumRack;
import com.sown.outerrim.tileentities.TileEntityCoaxiumContainer;
import com.sown.util.ui.P3D;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

public class RenderCoaxiumContainerTESR extends TileEntitySpecialRenderer {
    private static final ResourceLocation TEX = new ResourceLocation("outerrim:textures/models/blocks/coaxiumRack.png");
    private final ModelCoaxiumRack model = new ModelCoaxiumRack();

    @Override
    public void renderTileEntityAt(TileEntity te, double x, double y, double z, float partialTick) {
        if (!(te instanceof TileEntityCoaxiumContainer)) return;
        TileEntityCoaxiumContainer container = (TileEntityCoaxiumContainer)te;

        GL11.glPushMatrix();
        // move origin to block center + top
        GL11.glTranslated(x + 0.5, y + 1.5, z + 0.5);
        // upside-down because model was authored that way
        GL11.glRotatef(180f, 0f, 0f, 1f);
        // rotate around Y by facing × 90°
        GL11.glRotatef(90f * container.getFacing(), 0f, 1f, 0f);
        // scale if needed
        P3D.glScalef(1.25);

        Minecraft.getMinecraft().renderEngine.bindTexture(TEX);
        // parameters: (Entity, swing, swing2, age, headYaw, headPitch, scale)
        model.render(null, 0f, 0f, 0f, 0f, 0f, 0.05f);

        GL11.glPopMatrix();
    }
}

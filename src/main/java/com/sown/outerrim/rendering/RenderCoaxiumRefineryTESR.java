package com.sown.outerrim.rendering;

import org.lwjgl.opengl.GL11;

import com.sown.outerrim.models.blocks.ModelCoaxiumRefinery;
import com.sown.outerrim.tileentities.TileEntityCoaxiumRefinery;
import com.sown.util.ui.P3D;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

public class RenderCoaxiumRefineryTESR extends TileEntitySpecialRenderer {
    // Use the texture you actually ship; kept your current path/name:
    private static final ResourceLocation TEX =
            new ResourceLocation("outerrim", "textures/models/blocks/coaxium_refinery.png");

    private final ModelCoaxiumRefinery model = new ModelCoaxiumRefinery();

    @Override
    public void renderTileEntityAt(TileEntity te, double x, double y, double z, float partialTick) {
        if (!(te instanceof TileEntityCoaxiumRefinery)) return;
        TileEntityCoaxiumRefinery refinery = (TileEntityCoaxiumRefinery) te;

        GL11.glPushMatrix();

        // Move to block center and raise to model origin
        GL11.glTranslated(x + 0.5, y + 1.5, z + 0.5);

        // Most Techne/Blockbench (old) exports are upside-down in TESR space; fix that first.
        GL11.glRotatef(180f, 0f, 0f, 1f);

        // Your model is "backwards": add +180 yaw, then apply block facing (03)
        GL11.glRotatef(90f * refinery.getFacing() + 180f, 0f, 1f, 0f);

        // Scale if desired (1.0 = authoring scale)
        P3D.glScalef(1.0);

        Minecraft.getMinecraft().getTextureManager().bindTexture(TEX);
        model.render(null, 0f, 0f, 0f, 0f, 0f, 0.0625f);

        GL11.glPopMatrix();
    }
}

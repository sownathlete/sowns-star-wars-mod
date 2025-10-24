package com.sown.outerrim.rendering;

import org.lwjgl.opengl.GL11;

import com.sown.outerrim.models.blocks.ModelCoaxiumExtractor;
import com.sown.outerrim.tileentities.TileEntityCoaxiumPump;
import com.sown.util.ui.P3D;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

public class RenderBlockCoaxiumPump extends TileEntitySpecialRenderer {
    private static final ResourceLocation TEX =
            new ResourceLocation("outerrim", "textures/models/blocks/coaxium_extractor.png");

    private final ModelCoaxiumExtractor model = new ModelCoaxiumExtractor();

    @Override
    public void renderTileEntityAt(TileEntity te, double x, double y, double z, float partialTick) {
        if (!(te instanceof TileEntityCoaxiumPump)) return;
        TileEntityCoaxiumPump pump = (TileEntityCoaxiumPump) te;

        GL11.glPushMatrix();

        GL11.glTranslated(x + 0.5, y + 1.5, z + 0.5);
        GL11.glRotatef(180f, 0f, 0f, 1f); // upright (Blockbench/Techne -> TESR)
        GL11.glRotatef(90f * pump.getFacing() + 180f, 0f, 1f, 0f); // face + fix backward yaw

        P3D.glScalef(1.0);

        Minecraft.getMinecraft().getTextureManager().bindTexture(TEX);

        // crisp pixel look (optional)
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_NEAREST);
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);

        model.render(null, 0f, 0f, 0f, 0f, 0f, 0.0625f);

        GL11.glPopMatrix();
    }
}

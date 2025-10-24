package com.sown.outerrim.rendering;

import org.lwjgl.opengl.GL11;

import com.sown.outerrim.models.blocks.ModelPortableCoaxiumExtractor;
import com.sown.outerrim.tileentities.TileEntityPortableCoaxiumPump;
import com.sown.util.ui.P3D;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

public class RenderPortableCoaxiumExtractorTESR extends TileEntitySpecialRenderer {

    private static final ResourceLocation TEX =
            new ResourceLocation("outerrim", "textures/models/blocks/coaxium_extractor.png");

    private final ModelPortableCoaxiumExtractor model = new ModelPortableCoaxiumExtractor();

    @Override
    public void renderTileEntityAt(TileEntity te, double x, double y, double z, float partialTick) {
        if (!(te instanceof TileEntityPortableCoaxiumPump)) return;
        TileEntityPortableCoaxiumPump pump = (TileEntityPortableCoaxiumPump) te;

        GL11.glPushMatrix();

        GL11.glTranslated(x + 0.5, y + 1.5, z + 0.5);
        GL11.glRotatef(180f, 0f, 0f, 1f);
        GL11.glRotatef(90f * pump.getFacing() + 180f, 0f, 1f, 0f);

        P3D.glScalef(1.0);

        Minecraft.getMinecraft().getTextureManager().bindTexture(TEX);

        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_NEAREST);
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);

        model.render(null, 0f, 0f, 0f, 0f, 0f, 0.0625f);

        GL11.glPopMatrix();
    }
}

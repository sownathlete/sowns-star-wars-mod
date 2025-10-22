package com.sown.outerrim.client.render;

import com.sown.outerrim.models.blocks.ModelPortableCoaxiumPump;
import com.sown.outerrim.tileentities.TileEntityPortableCoaxiumPump;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class RenderPortableCoaxiumPump extends TileEntitySpecialRenderer {
    private static final ResourceLocation TEX = new ResourceLocation("outerrim", "textures/models/blocks/portable_pump.png");
    private final ModelPortableCoaxiumPump model = new ModelPortableCoaxiumPump();

    @Override
    public void renderTileEntityAt(TileEntity te, double x, double y, double z, float partial) {
        if (!(te instanceof TileEntityPortableCoaxiumPump)) return;

        TileEntityPortableCoaxiumPump pump = (TileEntityPortableCoaxiumPump) te;

        GL11.glPushMatrix();
        GL11.glTranslated(x + 0.5, y + 1.5, z + 0.5);
        GL11.glRotatef(pump.getFacing() * 90f, 0f, 1f, 0f);
        GL11.glRotatef(180f, 0f, 0f, 1f);
        bindTexture(TEX);
        model.render(null, 0, 0, 0, 0, 0, 0.0625f);
        GL11.glPopMatrix();
    }
}

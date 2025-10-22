package com.sown.outerrim.rendering;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

import com.sown.outerrim.models.blocks.ModelKaminoDoorSmall;
import com.sown.outerrim.tileentities.TileEntityKaminoDoorSmall;
import com.sown.util.ui.P3D;

public class RenderKaminoDoorSmall extends TileEntitySpecialRenderer {
    public static ResourceLocation defaultTexture = new ResourceLocation("outerrim:textures/models/blocks/kaminoDoorSmall.png");
    private final ModelKaminoDoorSmall model = new ModelKaminoDoorSmall();

    @Override
    public void renderTileEntityAt(TileEntity te, double x, double y, double z, float tickTime) {
        TileEntityKaminoDoorSmall KaminoDoorSmall = (TileEntityKaminoDoorSmall) te;

        GL11.glPushMatrix();
        GL11.glTranslated(x + 0.5, y + 1.5, z + 0.5);
        GL11.glRotatef(180.0f, 0.0f, 0.0f, 1.0f);
        GL11.glRotatef(90 * KaminoDoorSmall.getFacing(), 0.0f, 1.0f, 0.0f);

        Minecraft.getMinecraft().renderEngine.bindTexture(defaultTexture);

        P3D.glScalef(1.25f);
        this.model.render(null, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.05f);

        GL11.glPopMatrix();
    }
}

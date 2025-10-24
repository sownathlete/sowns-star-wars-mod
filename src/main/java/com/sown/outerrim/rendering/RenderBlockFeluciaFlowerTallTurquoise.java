package com.sown.outerrim.rendering;

import org.lwjgl.opengl.GL11;

import com.sown.outerrim.models.blocks.ModelFeluciaFlowerTallTurquoise;
import com.sown.outerrim.tileentities.TileEntityFeluciaFlowerTurquoise;
import com.sown.util.ui.P3D;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

public class RenderBlockFeluciaFlowerTallTurquoise extends TileEntitySpecialRenderer {
    private static final ResourceLocation texture = new ResourceLocation("outerrim:textures/models/blocks/felucia_flower_tall_turquoise.png");
    private final ModelFeluciaFlowerTallTurquoise model = new ModelFeluciaFlowerTallTurquoise();

    @Override
    public void renderTileEntityAt(TileEntity te, double x, double y, double z, float tickTime) {
        if (!(te instanceof TileEntityFeluciaFlowerTurquoise)) return;
        TileEntityFeluciaFlowerTurquoise flower = (TileEntityFeluciaFlowerTurquoise) te;

        GL11.glPushMatrix();
        GL11.glTranslated(x + 0.5, y + 0.75, z + 0.5); // center + lift up 12px
        GL11.glRotatef(180f, 0f, 0f, 1f);              // flip model
        GL11.glRotatef(90f * flower.getFacing(), 0f, 1f, 0f); // rotate based on facing
        P3D.glScalef(0.5f); // downscale to 50%

        Minecraft.getMinecraft().renderEngine.bindTexture(texture);
        model.render(null, 0f, 0f, 0f, 0f, 0f, 0.0625f); // standard render call

        GL11.glPopMatrix();
    }
}

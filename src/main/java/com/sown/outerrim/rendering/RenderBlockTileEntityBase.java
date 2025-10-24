package com.sown.outerrim.rendering;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public abstract class RenderBlockTileEntityBase extends TileEntitySpecialRenderer {
    private final ResourceLocation texture;

    public RenderBlockTileEntityBase(ResourceLocation texture) {
        this.texture = texture;
    }

    @Override
    public void renderTileEntityAt(TileEntity tileEntity, double x, double y, double z, float tickTime) {
        GL11.glPushMatrix();
        GL11.glTranslatef((float) x + 0.5F, (float) y + 1.5F, (float) z + 0.5F);
        Minecraft.getMinecraft().renderEngine.bindTexture(this.texture);
        applyModelTransformations(tileEntity);
        renderModel(tileEntity);
        GL11.glPopMatrix();
    }

    protected abstract void renderModel(TileEntity tileEntity);

    protected abstract void applyModelTransformations(TileEntity tileEntity);
}

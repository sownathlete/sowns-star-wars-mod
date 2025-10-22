package com.sown.outerrim.rendering;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.client.IItemRenderer;
import org.lwjgl.opengl.GL11;

public abstract class RenderBlockBase implements IItemRenderer {
    private final TileEntitySpecialRenderer tileEntityRenderer;
    private final TileEntity tileEntityInstance;

    public RenderBlockBase(TileEntitySpecialRenderer renderer, TileEntity tileEntity) {
        this.tileEntityRenderer = renderer;
        this.tileEntityInstance = tileEntity;
    }

    @Override
    public boolean handleRenderType(ItemStack item, IItemRenderer.ItemRenderType type) {
        return true;
    }

    @Override
    public boolean shouldUseRenderHelper(IItemRenderer.ItemRenderType type, ItemStack item, IItemRenderer.ItemRendererHelper helper) {
        return true;
    }

    @Override
    public void renderItem(IItemRenderer.ItemRenderType type, ItemStack item, Object... data) {
        GL11.glPushMatrix();
        adjustTransformations(type);
        this.tileEntityRenderer.renderTileEntityAt(this.tileEntityInstance, 0.0D, 0.0D, 0.0D, 0.0F);
        GL11.glPopMatrix();
    }

    protected abstract void adjustTransformations(IItemRenderer.ItemRenderType type);
}

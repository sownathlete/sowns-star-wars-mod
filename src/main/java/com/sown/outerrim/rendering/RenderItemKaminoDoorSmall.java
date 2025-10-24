package com.sown.outerrim.rendering;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;
import org.lwjgl.opengl.GL11;

import com.sown.outerrim.tileentities.TileEntityKaminoDoorSmall;

public class RenderItemKaminoDoorSmall implements IItemRenderer {
    private TileEntitySpecialRenderer render;
    private TileEntityKaminoDoorSmall tileEntity;

    public RenderItemKaminoDoorSmall() {
        // Initialize the tileEntity and render objects
        this.tileEntity = new TileEntityKaminoDoorSmall();
        this.render = new RenderBlockKaminoDoorSmall();
    }

    @Override
    public boolean handleRenderType(ItemStack item, IItemRenderer.ItemRenderType type) {
        return true;
    }

    @Override
    public void renderItem(IItemRenderer.ItemRenderType type, ItemStack item, Object... data) {
        GL11.glPushMatrix();
        switch (type) {
            case INVENTORY -> {
                GL11.glPushMatrix();
                GL11.glDisable(GL11.GL_LIGHTING);
                GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F);
                GL11.glScalef(1.0F, 1.0F, -1.0F);
                GL11.glScalef(0.28F, 0.28F, -0.28F);
                GL11.glTranslatef(0.0F, -1.0F, 0.0F);
                GL11.glTranslatef(-0.7F, -1.1F, 0.0F);
                this.render.renderTileEntityAt(this.tileEntity, 0.0D, 0.0D, 0.0D, 0.0F);
                GL11.glEnable(GL11.GL_LIGHTING);
                GL11.glPopMatrix();
            }
            case EQUIPPED -> {
                GL11.glPushMatrix();
                GL11.glDisable(GL11.GL_LIGHTING);
                GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
                GL11.glScalef(0.25F, 0.25F, -0.25F);
                GL11.glTranslatef(-0.5F, 2.0F, -5.0F);
                GL11.glRotatef(45.0F, 1.0F, 0.0F, 0.0F);
                this.render.renderTileEntityAt(this.tileEntity, 0.0D, 0.0D, 0.0D, 0.0F);
                GL11.glEnable(GL11.GL_LIGHTING);
                GL11.glPopMatrix();
            }
            case EQUIPPED_FIRST_PERSON -> {
                GL11.glPushMatrix();
                GL11.glDisable(GL11.GL_LIGHTING);
                GL11.glScalef(0.55F, 0.55F, -0.55F);
                GL11.glTranslatef(3.0F, -1.0F, -0.5F);
                GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F);
                this.render.renderTileEntityAt(this.tileEntity, 0.0D, 0.0D, 0.0D, 0.0F);
                GL11.glEnable(GL11.GL_LIGHTING);
                GL11.glPopMatrix();
            }
            default -> {
                GL11.glPushMatrix();
                GL11.glDisable(GL11.GL_LIGHTING);
                GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F);
                GL11.glScalef(0.45F, 0.45F, -0.45F);
                GL11.glTranslatef(-0.5F, 0.4F, -0.5F);
                this.render.renderTileEntityAt(this.tileEntity, 0.0D, 0.0D, 0.0D, 0.0F);
                GL11.glEnable(GL11.GL_LIGHTING);
                GL11.glPopMatrix();
            }
        }
        GL11.glPopMatrix();
    }

    @Override
    public boolean shouldUseRenderHelper(IItemRenderer.ItemRenderType type, ItemStack item, IItemRenderer.ItemRendererHelper helper) {
        return true;
    }
}
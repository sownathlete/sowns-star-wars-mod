package com.sown.outerrim.rendering;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.client.IItemRenderer;
import org.lwjgl.opengl.GL11;

import com.sown.outerrim.tileentities.TileEntityCoaxiumPump;

public class RenderItemCoaxiumPump implements IItemRenderer {
    private final TileEntitySpecialRenderer tesr = new RenderBlockCoaxiumPump();
    private final TileEntityCoaxiumPump dummyTE = new TileEntityCoaxiumPump();

    public RenderItemCoaxiumPump() {
        World w = Minecraft.getMinecraft().theWorld;
        if (w != null) dummyTE.setWorldObj(w);
    }

    @Override
    public boolean handleRenderType(ItemStack item, ItemRenderType type) {
        return true;
    }

    @Override
    public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper) {
        return true;
    }

    @Override
    public void renderItem(ItemRenderType type, ItemStack item, Object... data) {
        GL11.glPushMatrix();
        GL11.glDisable(GL11.GL_CULL_FACE);

        switch (type) {
            case INVENTORY -> {
                GL11.glScalef(0.2f, 0.2f, 0.2f);
                GL11.glTranslatef(-1.0f, -3.5f, 0.0f);
                GL11.glRotatef(180f, 0f, 1f, 0f);
            }
            case EQUIPPED -> {
                GL11.glScalef(0.5f, 0.5f, 0.5f);
                GL11.glTranslatef(0.5f, 0.5f, -2.0f);
                GL11.glRotatef(45f, 1f, 0f, 0f);
                GL11.glRotatef(45f, 0f, 1f, 0f);
                GL11.glRotatef(180f, 0f, 1f, 0f);
            }
            case EQUIPPED_FIRST_PERSON -> {
                GL11.glScalef(1.5f, 1.5f, 1.5f);
                GL11.glTranslatef(3.0f, -1.0f, 0.5f);
                GL11.glRotatef(135f, 0f, 1f, 0f);
                GL11.glRotatef(180f, 0f, 1f, 0f);
            }
            default -> { // ENTITY
                GL11.glScalef(0.85f, 0.85f, 0.85f);
                GL11.glRotatef(90f, 0f, 1f, 0f);
                GL11.glTranslatef(-0.5f, 0.4f, -0.5f);
                GL11.glRotatef(180f, 0f, 1f, 0f);
            }
        }

        tesr.renderTileEntityAt(dummyTE, 0.0, 0.0, 0.0, 0f);

        GL11.glEnable(GL11.GL_CULL_FACE);
        GL11.glPopMatrix();
    }
}

package com.sown.outerrim.rendering;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.client.IItemRenderer;
import org.lwjgl.opengl.GL11;

import com.sown.outerrim.tileentities.TileEntityCoaxiumContainer;

public class RenderItemCoaxiumContainer implements IItemRenderer {
    // your TESR that knows how to draw the 3D model + texture
    private final TileEntitySpecialRenderer tesr = new RenderBlockCoaxiumContainer();
    // a dummy TE instance to pass into the TESR
    private final TileEntityCoaxiumContainer dummyTE = new TileEntityCoaxiumContainer();

    public RenderItemCoaxiumContainer() {
        // give our dummyTE a world so world dependent code in the TESR won't NPE;
        // we only need to do this once
        World w = Minecraft.getMinecraft().theWorld;
        if (w != null) {
            dummyTE.setWorldObj(w);
            // dummyTE.validate(); // optionalyour TE has no load hooks, so not strictly needed
        }
    }

    @Override
    public boolean handleRenderType(ItemStack item, ItemRenderType type) {
        // we want to render in all contexts (inventory, equipped, etc)
        return true;
    }

    @Override
    public void renderItem(ItemRenderType type, ItemStack item, Object... data) {
        GL11.glPushMatrix();

        // configure transforms per context
        switch (type) {
            case INVENTORY:
                GL11.glDisable(GL11.GL_CULL_FACE);
                GL11.glTranslatef(0.03f, -0.28f, 0f);
                GL11.glRotatef(90f, 0f, 1f, 0f);
                GL11.glScalef(1f, 1f, -1f);
                GL11.glTranslatef(-0.03f, -0.6f, 0f);
                break;

            case EQUIPPED:
                GL11.glDisable(GL11.GL_CULL_FACE);
                GL11.glRotatef(45f, 0f, 1f, 0f);
                GL11.glScalef(0.5f, 0.5f, -0.5f);
                GL11.glTranslatef(0.5f, 0.5f, -2f);
                GL11.glRotatef(45f, 1f, 0f, 0f);
                GL11.glRotatef(-180f, 0f, 1f, 0f);
                break;

            case EQUIPPED_FIRST_PERSON:
                GL11.glDisable(GL11.GL_CULL_FACE);
                GL11.glScalef(1.5f, 1.5f, -1.5f);
                GL11.glTranslatef(3f, -1f, 0.5f);
                GL11.glRotatef(135f, 0f, 1f, 0f);
                break;

            default: // ENTITY / others
                GL11.glDisable(GL11.GL_CULL_FACE);
                GL11.glRotatef(90f, 0f, 1f, 0f);
                GL11.glScalef(0.85f, 0.85f, -0.85f);
                GL11.glTranslatef(-0.5f, 0.4f, -0.5f);
                break;
        }

        // render the TESR at local origin
        tesr.renderTileEntityAt(dummyTE, 0.0, 0.0, 0.0, 0f);

        // restore GL state
        GL11.glEnable(GL11.GL_CULL_FACE);
        GL11.glPopMatrix();
    }

    @Override
    public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper) {
        return true;
    }
}

package com.sown.outerrim.rendering;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.client.IItemRenderer;
import org.lwjgl.opengl.GL11;

import com.sown.outerrim.tileentities.TileEntityCoaxiumRefinery;

public class RenderBlockCoaxiumRefinery implements IItemRenderer {
    private final TileEntitySpecialRenderer tesr = new RenderCoaxiumRefineryTESR();
    private final TileEntityCoaxiumRefinery dummyTE = new TileEntityCoaxiumRefinery();

    public RenderBlockCoaxiumRefinery() {
        World w = Minecraft.getMinecraft().theWorld;
        if (w != null) dummyTE.setWorldObj(w);
    }

    @Override
    public boolean handleRenderType(ItemStack item, ItemRenderType type) {
        return true;
    }

    @Override
    public void renderItem(ItemRenderType type, ItemStack item, Object... data) {
        GL11.glPushMatrix();
        GL11.glDisable(GL11.GL_CULL_FACE); // avoid disappearing faces after transforms

        switch (type) {
            case INVENTORY: {
                // Compact, centered, and rotated to show the front
                GL11.glScalef(0.25f, 0.25f, 0.25f);
                GL11.glTranslatef(1.25f, -0.25f, 0.0f);
                GL11.glRotatef(225f, 0f, 1f, 0f); // <-- fix "backwards" for item views
                GL11.glRotatef(45f, 0f, 1f, 0f);
                break;
            }

            case EQUIPPED: {
                GL11.glScalef(0.5f, 0.5f, 0.5f);
                GL11.glTranslatef(0.5f, 0.5f, -0.5f);
                GL11.glRotatef(45f, 1f, 0f, 0f);
                GL11.glRotatef(45f, 0f, 1f, 0f);
                // GL11.glRotatef(180f, 0f, 1f, 0f); // <-- same yaw correction
                break;
            }

            case EQUIPPED_FIRST_PERSON: {
                GL11.glScalef(1.5f, 1.5f, 1.5f);
                GL11.glTranslatef(3.0f, -1.0f, 0.5f);
                GL11.glRotatef(135f, 0f, 1f, 0f);
                // GL11.glRotatef(180f, 0f, 1f, 0f); // <-- same yaw correction
                break;
            }

            default: { // ENTITY / fallback
                GL11.glScalef(0.85f, 0.85f, 0.85f);
                GL11.glRotatef(90f, 0f, 1f, 0f);
                GL11.glTranslatef(-0.5f, 0.4f, -0.5f);
                // GL11.glRotatef(180f, 0f, 1f, 0f); // <-- same yaw correction
                break;
            }
        }

        // Render the same TESR the block uses so orientation stays consistent
        tesr.renderTileEntityAt(dummyTE, 0.0, 0.0, 0.0, 0f);

        GL11.glEnable(GL11.GL_CULL_FACE);
        GL11.glPopMatrix();
    }

    @Override
    public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper) {
        return true;
    }
}

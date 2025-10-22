package com.sown.outerrim.rendering;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.client.IItemRenderer;
import org.lwjgl.opengl.GL11;

import com.sown.outerrim.tileentities.TileEntityCoaxiumRefinery;

public class RenderItemCoaxiumRefinery implements IItemRenderer {

    private final TileEntitySpecialRenderer tesr = new RenderCoaxiumRefineryTESROld();

    private final TileEntityCoaxiumRefinery dummyTE = new TileEntityCoaxiumRefinery();

    public RenderItemCoaxiumRefinery() {
        World w = Minecraft.getMinecraft().theWorld;
        if (w != null) {
            dummyTE.setWorldObj(w);
        }
    }

    @Override
    public boolean handleRenderType(ItemStack item, ItemRenderType type) {
        return true;
    }

    @Override
    public void renderItem(ItemRenderType type, ItemStack item, Object... data) {
        GL11.glPushMatrix();
        GL11.glDisable(GL11.GL_CULL_FACE);

        switch (type) {
            case INVENTORY: {
                GL11.glTranslatef(0.0f, -0.35f, 0.0f);
                GL11.glRotatef(90f, 0f, 1f, 0f);
                GL11.glScalef(0.8f, 0.8f, -0.8f);
                GL11.glTranslatef(-0.1f, -0.75f, 0.0f);
                break;
            }

            case EQUIPPED: {
                GL11.glRotatef(45f, 0f, 1f, 0f);
                GL11.glScalef(0.45f, 0.45f, -0.45f);
                GL11.glTranslatef(0.6f, 0.55f, -2.2f);
                GL11.glRotatef(35f, 1f, 0f, 0f);
                GL11.glRotatef(-180f, 0f, 1f, 0f);
                break;
            }

            case EQUIPPED_FIRST_PERSON: {
                GL11.glScalef(1.2f, 1.2f, -1.2f);
                GL11.glTranslatef(2.1f, -1.0f, 0.7f);
                GL11.glRotatef(130f, 0f, 1f, 0f);
                GL11.glRotatef(10f, 0f, 0f, 1f);
                break;
            }

            default: {
                GL11.glRotatef(90f, 0f, 1f, 0f);
                GL11.glScalef(0.85f, 0.85f, -0.85f);
                GL11.glTranslatef(-0.5f, 0.45f, -0.5f);
                break;
            }
        }

        tesr.renderTileEntityAt(dummyTE, 0.0, 0.0, 0.0, 0f);

        GL11.glEnable(GL11.GL_CULL_FACE);
        GL11.glPopMatrix();
    }

    @Override
    public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper) {
        return true;
    }
}

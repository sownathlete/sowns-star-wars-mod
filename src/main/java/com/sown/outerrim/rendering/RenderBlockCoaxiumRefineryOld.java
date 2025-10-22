package com.sown.outerrim.rendering;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.client.IItemRenderer;
import org.lwjgl.opengl.GL11;

import com.sown.outerrim.tileentities.TileEntityCoaxiumRefinery;

public class RenderBlockCoaxiumRefineryOld implements IItemRenderer {
    private final TileEntitySpecialRenderer tesr = new RenderCoaxiumRefineryTESROld();
    private final TileEntityCoaxiumRefinery dummyTE = new TileEntityCoaxiumRefinery();

    public RenderBlockCoaxiumRefineryOld() {
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
            case INVENTORY:
            	GL11.glTranslatef(0f, -0.6375f, 0f);
            	GL11.glScalef(0.5f, 0.5f, -0.5f);
            	GL11.glRotatef(-90f, 0f, 1f, 0f);
            	break;

            case EQUIPPED:
                GL11.glScalef(0.7f, 0.7f, -0.7f);
                GL11.glRotatef(45f, 0f, 1f, 0f);
                break;

            case EQUIPPED_FIRST_PERSON:
                GL11.glScalef(1.2f, 1.2f, -1.2f);
                GL11.glRotatef(135f, 0f, 1f, 0f);
                break;

            default: // ENTITY, etc.
                GL11.glScalef(0.8f, 0.8f, -0.8f);
                GL11.glRotatef(90f, 0f, 1f, 0f);
                break;
        }

        tesr.renderTileEntityAt(dummyTE, 0, 0, 0, 0f);
        GL11.glEnable(GL11.GL_CULL_FACE);
        GL11.glPopMatrix();
    }

    @Override
    public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper) {
        return true;
    }
}

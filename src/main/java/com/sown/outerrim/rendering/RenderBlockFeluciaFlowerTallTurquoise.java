package com.sown.outerrim.rendering;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.client.IItemRenderer;
import org.lwjgl.opengl.GL11;

import com.sown.outerrim.tileentities.TileEntityFeluciaFlowerTurquoise;

public class RenderBlockFeluciaFlowerTallTurquoise implements IItemRenderer {
    private final TileEntitySpecialRenderer tesr = new RenderFeluciaFlowerTallTurquoise();
    private final TileEntityFeluciaFlowerTurquoise dummyTE = new TileEntityFeluciaFlowerTurquoise();

    public RenderBlockFeluciaFlowerTallTurquoise() {
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

        switch (type) {
        	case INVENTORY:
        		GL11.glDisable(GL11.GL_CULL_FACE);
        		GL11.glTranslatef(1f, -0.225f, 0f);
                GL11.glRotatef(180f, 0f, 1f, 0f);
                GL11.glScalef(1f, 1f, -1f);
        		break;

            case EQUIPPED:
                GL11.glDisable(GL11.GL_CULL_FACE);
                GL11.glScalef(0.5f, 0.5f, -0.5f);
                GL11.glTranslatef(1f, 1.5f, -2f);
                GL11.glRotatef(90f, 1f, 0f, 0f);
                break;

            case EQUIPPED_FIRST_PERSON:
                GL11.glDisable(GL11.GL_CULL_FACE);
                GL11.glScalef(1.3f, 1.3f, -1.3f);
                GL11.glTranslatef(2f, -0.8f, 0.2f);
                GL11.glRotatef(135f, 0f, 1f, 0f);
                break;

            default: // ENTITY
                GL11.glDisable(GL11.GL_CULL_FACE);
                GL11.glRotatef(90f, 0f, 1f, 0f);
                GL11.glScalef(0.85f, 0.85f, -0.85f);
                GL11.glTranslatef(-0.5f, 0.4f, -0.5f);
                break;
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

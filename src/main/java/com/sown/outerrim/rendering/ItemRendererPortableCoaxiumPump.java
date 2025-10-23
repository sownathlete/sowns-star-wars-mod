package com.sown.outerrim.rendering;

import com.sown.outerrim.client.render.RenderPortableCoaxiumPump;
import com.sown.outerrim.tileentities.TileEntityPortableCoaxiumPump;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.client.IItemRenderer;
import org.lwjgl.opengl.GL11;

public class ItemRendererPortableCoaxiumPump implements IItemRenderer {
    private final RenderPortableCoaxiumPump tesr = new RenderPortableCoaxiumPump();
    private final TileEntityPortableCoaxiumPump dummy = new TileEntityPortableCoaxiumPump();

    public ItemRendererPortableCoaxiumPump() {
        tesr.func_147497_a(TileEntityRendererDispatcher.instance);
        World w = Minecraft.getMinecraft().theWorld;
        if (w != null) {
            dummy.setWorldObj(w);
            dummy.validate();
        }
    }

    @Override
    public boolean handleRenderType(ItemStack s, ItemRenderType t) {
        return true;
    }

    @Override
    public boolean shouldUseRenderHelper(ItemRenderType t, ItemStack s, ItemRendererHelper h) {
        return true;
    }

    @Override
    public void renderItem(ItemRenderType type, ItemStack stack, Object... data) {
        GL11.glPushMatrix();

        switch (type) {
            case INVENTORY:
                GL11.glScalef(1f, 1f, 1f);
                GL11.glTranslatef(0f, -1f, 0f);
                GL11.glRotatef(180, 0, 1, 0);
                break;

            case EQUIPPED:
                GL11.glTranslatef(0.5f, 0f, 0.5f);
                GL11.glRotatef(90, 0, 1, 0);
                break;

            case EQUIPPED_FIRST_PERSON:
                GL11.glTranslatef(0f, 0.5f, 1.25f);
                GL11.glRotatef(90, 0, 1, 0);
                GL11.glScalef(1f, 1f, 1f);
                break;

            default:
                GL11.glRotatef(180, 0, 1, 0);
                GL11.glScalef(0.7f, 0.7f, 0.7f);
                break;
        }

        tesr.renderTileEntityAt(dummy, 0, 0, 0, 0);
        GL11.glPopMatrix();
    }
}

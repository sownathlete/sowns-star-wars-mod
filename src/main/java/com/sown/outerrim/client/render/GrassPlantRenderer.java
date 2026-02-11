package com.sown.outerrim.client.render;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.world.IBlockAccess;

public class GrassPlantRenderer implements ISimpleBlockRenderingHandler {

    private final int renderId;

    public GrassPlantRenderer(int renderId) {
        this.renderId = renderId;
    }

    private static float offsetCoord(int x, int z, int salt) {
        long seed = (long)(x * 3129871) ^ (long)z * 116129781L ^ (long)salt * 4231L;
        seed = seed * seed * 42317861L + seed * 11L;

        // 0..15 -> -0.5..0.5 then scale (~vanilla)
        float r = (((seed >> 16) & 15L) / 15.0F - 0.5F);
        return r * 0.25F;
    }

    @Override
    public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z,
                                    Block block, int modelId, RenderBlocks renderer) {
        if (modelId != renderId) return false;

        float ox = offsetCoord(x, z, 1);
        float oz = offsetCoord(x, z, 2);

        Tessellator tess = Tessellator.instance;

        // Translate the MODEL only (not bounds/hitbox)
        tess.setTranslation(ox, 0.0D, oz);
        renderer.renderCrossedSquares(block, x, y, z);
        tess.setTranslation(0.0D, 0.0D, 0.0D);

        return true;
    }

    @Override
    public void renderInventoryBlock(Block block, int metadata, int modelId, RenderBlocks renderer) {
        Tessellator tess = Tessellator.instance;

        GL11.glPushMatrix();
        GL11.glTranslatef(-0.5F, -0.5F, -0.5F);

        tess.startDrawingQuads();
        renderer.renderCrossedSquares(block, 0, 0, 0);
        tess.draw();

        GL11.glPopMatrix();
    }

    @Override
    public boolean shouldRender3DInInventory(int modelId) {
        return false;
    }

    @Override
    public int getRenderId() {
        return renderId;
    }
}

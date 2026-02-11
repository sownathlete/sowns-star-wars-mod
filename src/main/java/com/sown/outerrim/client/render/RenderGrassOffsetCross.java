package com.sown.outerrim.client.render;

import java.util.Random;

import com.sown.outerrim.blocks.BlockCustomGrassPlant;
import com.sown.outerrim.blocks.BlockCustomTallGrass;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;

public class RenderGrassOffsetCross implements ISimpleBlockRenderingHandler {

    private final int renderId;

    public RenderGrassOffsetCross(int renderId) {
        this.renderId = renderId;
    }

    @Override
    public void renderInventoryBlock(Block block, int metadata, int modelId, RenderBlocks renderer) {
        // vanilla inventory rendering is fine; tint handled by ItemBlock#getColorFromItemStack
        renderer.renderBlockAsItem(block, metadata, 1.0F);
    }

    @Override
    public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
        Tessellator t = Tessellator.instance;

        // Compute a deterministic offset like vanilla uses
        long seed = (long)(x * 3129871) ^ (long)z * 116129781L;
        seed = seed * seed * 42317861L + seed * 11L;

        float ox = (((seed >> 16) & 15L) / 15.0F - 0.5F) * 0.25F;
        float oz = (((seed >> 24) & 15L) / 15.0F - 0.5F) * 0.25F;

        // For tall grass top half, you usually want the same offset as the bottom.
        // Using (x,z) for both halves gives stable alignment.

        int meta = world.getBlockMetadata(x, y, z);
        IIcon icon = block.getIcon(0, meta);

        // Apply brightness + color
        renderer.setRenderBoundsFromBlock(block);
        int color = block.colorMultiplier(world, x, y, z);

        float r = (float)((color >> 16) & 255) / 255.0F;
        float g = (float)((color >> 8) & 255) / 255.0F;
        float b = (float)(color & 255) / 255.0F;

        t.setColorOpaque_F(r, g, b);
        t.setBrightness(block.getMixedBrightnessForBlock(world, x, y, z));

        // Draw the cross manually at (x+0.5+ox, z+0.5+oz)
        renderCrossAt(t, icon, x + 0.5F + ox, y, z + 0.5F + oz, 1.0F);

        return true;
    }

    private void renderCrossAt(Tessellator t, IIcon icon, float cx, float y, float cz, float height) {
        float minU = icon.getMinU();
        float maxU = icon.getMaxU();
        float minV = icon.getMinV();
        float maxV = icon.getMaxV();

        // half-width of the cross plane
        float hw = 0.45F;

        // First plane (NW-SE)
        t.addVertexWithUV(cx - hw, y + height, cz - hw, minU, minV);
        t.addVertexWithUV(cx - hw, y + 0.0F,   cz - hw, minU, maxV);
        t.addVertexWithUV(cx + hw, y + 0.0F,   cz + hw, maxU, maxV);
        t.addVertexWithUV(cx + hw, y + height, cz + hw, maxU, minV);

        t.addVertexWithUV(cx + hw, y + height, cz + hw, minU, minV);
        t.addVertexWithUV(cx + hw, y + 0.0F,   cz + hw, minU, maxV);
        t.addVertexWithUV(cx - hw, y + 0.0F,   cz - hw, maxU, maxV);
        t.addVertexWithUV(cx - hw, y + height, cz - hw, maxU, minV);

        // Second plane (NE-SW)
        t.addVertexWithUV(cx - hw, y + height, cz + hw, minU, minV);
        t.addVertexWithUV(cx - hw, y + 0.0F,   cz + hw, minU, maxV);
        t.addVertexWithUV(cx + hw, y + 0.0F,   cz - hw, maxU, maxV);
        t.addVertexWithUV(cx + hw, y + height, cz - hw, maxU, minV);

        t.addVertexWithUV(cx + hw, y + height, cz - hw, minU, minV);
        t.addVertexWithUV(cx + hw, y + 0.0F,   cz - hw, minU, maxV);
        t.addVertexWithUV(cx - hw, y + 0.0F,   cz + hw, maxU, maxV);
        t.addVertexWithUV(cx - hw, y + height, cz + hw, maxU, minV);
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

/*
 * Decompiled with CFR 0.148.
 *
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockGrass
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.renderer.RenderBlocks
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.client.renderer.texture.ITextureObject
 *  net.minecraft.client.renderer.texture.TextureAtlasSprite
 *  net.minecraft.client.renderer.texture.TextureManager
 *  net.minecraft.client.renderer.texture.TextureMap
 *  net.minecraft.init.Blocks
 *  net.minecraft.util.IIcon
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.opengl.GL11
 */
package com.sown.util.world.creation;

import net.minecraft.block.Block;
import net.minecraft.block.BlockGrass;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.ITextureObject;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.init.Blocks;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

import com.sown.outerrim.OuterRim;
import com.sown.outerrim.registry.BlockRegister;

public class RenderUtils {
    public static int foliageModel = -1;
    public static int plantsModel = -1;
    public static int bonesModel = -1;
    public static int graveModel = -1;
    public static int bambooModel = -1;
    public static int newGrassModel = -1;
    public static int leavesModel = -1;

    public static void renderStandardInvBlock(RenderBlocks renderblocks, Block block, int meta) {
        RenderUtils.renderStandardInvBlock(renderblocks, block, meta, null);
    }

    public static void renderStandardInvBlock(RenderBlocks renderblocks, Block block, int meta, IIcon icon) {
        boolean flag = block == BlockRegister.getRegisteredBlock("boganoGrass");
        GL11.glRotatef(90.0f, 0.0f, 1.0f, 0.0f);
        Tessellator tessellator = Tessellator.instance;
        GL11.glTranslatef(-0.5f, -0.5f, -0.5f);
        tessellator.startDrawingQuads();
        tessellator.setNormal(0.0f, -1.0f, 0.0f);
        renderblocks.renderFaceYNeg(block, 0.0, 0.0, 0.0, icon == null ? block.getIcon(0, meta) : icon);
        tessellator.draw();
        if (flag && renderblocks.useInventoryTint) {
            int colour = 16777215;
            float f1 = (colour >> 16 & 0xFF) / 255.0f;
            float f2 = (colour >> 8 & 0xFF) / 255.0f;
            float f3 = (colour & 0xFF) / 255.0f;
            GL11.glColor4f(f1, f2, f3, 1.0f);
        }
        if (!flag) {
            tessellator.startDrawingQuads();
            tessellator.setNormal(0.0f, 1.0f, 0.0f);
            renderblocks.renderFaceYPos(block, 0.0, 0.0, 0.0, icon == null ? block.getIcon(1, meta) : icon);
            tessellator.draw();
        }
        if (flag || block == Blocks.grass) {
            GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        }
        tessellator.startDrawingQuads();
        tessellator.setNormal(0.0f, 0.0f, -1.0f);
        renderblocks.renderFaceZNeg(block, 0.0, 0.0, 0.0, icon == null ? block.getIcon(2, meta) : icon);
        tessellator.draw();
        tessellator.startDrawingQuads();
        tessellator.setNormal(0.0f, 0.0f, 1.0f);
        renderblocks.renderFaceZPos(block, 0.0, 0.0, 0.0, icon == null ? block.getIcon(3, meta) : icon);
        tessellator.draw();
        tessellator.startDrawingQuads();
        tessellator.setNormal(-1.0f, 0.0f, 0.0f);
        renderblocks.renderFaceXNeg(block, 0.0, 0.0, 0.0, icon == null ? block.getIcon(4, meta) : icon);
        tessellator.draw();
        tessellator.startDrawingQuads();
        tessellator.setNormal(1.0f, 0.0f, 0.0f);
        renderblocks.renderFaceXPos(block, 0.0, 0.0, 0.0, icon == null ? block.getIcon(5, meta) : icon);
        tessellator.draw();
        GL11.glTranslatef(0.5f, 0.5f, 0.5f);
    }

    public static void renderIcon(IIcon icon, double size, double z, float nx, float ny, float nz) {
        RenderUtils.renderIcon(icon, 0.0, 0.0, size, size, z, nx, ny, nz);
    }

    public static void renderIcon(IIcon icon, double xStart, double yStart, double xEnd, double yEnd, double z, float nx, float ny, float nz) {
        if (icon == null) {
            icon = RenderUtils.getMissingIcon(TextureMap.locationItemsTexture);
        }
        Tessellator tessellator = Tessellator.instance;
        tessellator.startDrawingQuads();
        tessellator.setNormal(nx, ny, nz);
        if (nz > 0.0f) {
            tessellator.addVertexWithUV(xStart, yStart, z, icon.getMinU(), icon.getMinV());
            tessellator.addVertexWithUV(xEnd, yStart, z, icon.getMaxU(), icon.getMinV());
            tessellator.addVertexWithUV(xEnd, yEnd, z, icon.getMaxU(), icon.getMaxV());
            tessellator.addVertexWithUV(xStart, yEnd, z, icon.getMinU(), icon.getMaxV());
        } else {
            tessellator.addVertexWithUV(xStart, yEnd, z, icon.getMinU(), icon.getMaxV());
            tessellator.addVertexWithUV(xEnd, yEnd, z, icon.getMaxU(), icon.getMaxV());
            tessellator.addVertexWithUV(xEnd, yStart, z, icon.getMaxU(), icon.getMinV());
            tessellator.addVertexWithUV(xStart, yStart, z, icon.getMinU(), icon.getMinV());
        }
        tessellator.draw();
    }

    public static void renderIcon(int indexX, int indexY, float minU, float maxU, float minV, float maxV, double z) {
        Tessellator tessellator = Tessellator.instance;
        tessellator.startDrawingQuads();
        tessellator.setNormal(0.0f, 0.0f, -1.0f);
        tessellator.addVertexWithUV(0.0, 16.0, z, minU, maxV);
        tessellator.addVertexWithUV(16.0, 16.0, z, maxU, maxV);
        tessellator.addVertexWithUV(16.0, 0.0, z, maxU, minV);
        tessellator.addVertexWithUV(0.0, 0.0, z, minU, minV);
        tessellator.draw();
    }

    public static IIcon getMissingIcon(ResourceLocation textureSheet) {
        return ((TextureMap)Minecraft.getMinecraft().getTextureManager().getTexture(textureSheet)).getAtlasSprite("missingno");
    }
}


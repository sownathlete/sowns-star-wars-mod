/*
 * Decompiled with CFR 0.148.
 *
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.renderer.texture.TextureManager
 *  net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer
 *  net.minecraft.entity.Entity
 *  net.minecraft.init.Blocks
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.world.World
 *  org.lwjgl.opengl.GL11
 */
package com.sown.outerrim.rendering;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;

import com.sown.outerrim.models.blocks.ModelCoaxiumRack;
import com.sown.outerrim.tileentities.TileEntityCoaxiumContainer;
import com.sown.util.ui.P3D;

public class RenderBlockCoaxiumRack extends TileEntitySpecialRenderer {
    public static ResourceLocation texture = new ResourceLocation("outerrim:textures/models/blocks/coaxium_container.png");
    private final ModelCoaxiumRack model = new ModelCoaxiumRack();

    private void adjustRotatePivotViaMeta(World world, int x, int y, int z) {
    }

    @Override
    public void renderTileEntityAt(TileEntity te, double x, double y, double z, float tickTime) {
        GL11.glPushMatrix();
        GL11.glTranslated(x + 0.5, y + 1.5, z + 0.5);
        GL11.glRotatef(180.0f, 0.0f, 0.0f, 1.0f);
        GL11.glRotatef(90 * ((TileEntityCoaxiumContainer)te).getFacing(), 0.0f, 1.0f, 0.0f);
        P3D.glScalef(1.25);
        Minecraft.getMinecraft().renderEngine.bindTexture(texture);
        this.model.render(null, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.05f);
        GL11.glPopMatrix();
    }
}


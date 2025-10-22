package com.sown.outerrim.rendering;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;

import com.sown.outerrim.models.blocks.ModelCoaxiumRefineryOld;
import com.sown.outerrim.tileentities.TileEntityCoaxiumRefinery;
import com.sown.util.ui.P3D;

public class RenderCoaxiumRefinery
extends TileEntitySpecialRenderer {
    public static ResourceLocation texture = new ResourceLocation("outerrim:textures/models/blocks/coaxium_refinery.png");
    private final ModelCoaxiumRefineryOld model = new ModelCoaxiumRefineryOld();

    private void adjustRotatePivotViaMeta(World world, int x, int y, int z) {
    }

    @Override
    public void renderTileEntityAt(TileEntity te, double x, double y, double z, float tickTime) {
        GL11.glPushMatrix();
        GL11.glTranslated(x + 0.5, y + 1.5, z + 0.5);
        GL11.glRotatef(180.0f, 0.0f, 0.0f, 1.0f);
        GL11.glRotatef(90 * ((TileEntityCoaxiumRefinery)te).getFacing(), 0.0f, 1.0f, 0.0f);
        P3D.glScalef(1.25);
        Minecraft.getMinecraft().renderEngine.bindTexture(texture);
        this.model.render(null, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.05f);
        GL11.glPopMatrix();
    }
}
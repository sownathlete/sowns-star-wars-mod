/*
 * Decompiled with CFR 0.148.
 *
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.multiplayer.WorldClient
 *  net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer
 *  net.minecraft.item.ItemStack
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.world.World
 *  net.minecraftforge.client.IItemRenderer
 *  net.minecraftforge.client.IItemRenderer$ItemRenderType
 *  net.minecraftforge.client.IItemRenderer$ItemRendererHelper
 *  org.lwjgl.opengl.GL11
 */
package com.sown.outerrim.rendering;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.client.IItemRenderer;
import org.lwjgl.opengl.GL11;

import com.sown.outerrim.tileentities.TileEntityVenatorBridgeChair;
import com.sown.util.ui.P3D;

public class RenderItemVenatorBridgeChair implements IItemRenderer {
    private TileEntitySpecialRenderer render = new RenderBlockVenatorBridgeChair();
    private TileEntity tile = new TileEntityVenatorBridgeChair();

    public RenderItemVenatorBridgeChair() {
        //this.tile.setWorldObj(OuterRim.mc.theWorld);
    }

    @Override
    public boolean handleRenderType(ItemStack item, IItemRenderer.ItemRenderType type) {
        return true;
    }

    @Override
    public void renderItem(IItemRenderer.ItemRenderType type, ItemStack item, Object ... data) {
        GL11.glPushMatrix();
        //this.tile.setWorldObj(OuterRim.mc.theWorld);
        this.tile.updateEntity();
        switch (type) {
            case INVENTORY -> {
                GL11.glPushMatrix();
                GL11.glDisable(2884);
                GL11.glTranslatef(0.03f, -0.28f, 0.0f);
                GL11.glRotatef(90.0f, 0.0f, 1.0f, 0.0f);
                P3D.glScalef(1.0);
                GL11.glScalef(1.0f, 1.0f, -1.0f);
                GL11.glTranslatef(-0.03f, -0.6f, 0.0f);
                this.render.renderTileEntityAt(this.tile, 0.0, 0.0, 0.0, 0.0f);
                GL11.glEnable(2884);
                GL11.glPopMatrix();
            }
            case EQUIPPED -> {
                GL11.glPushMatrix();
                GL11.glDisable(2884);
                GL11.glRotatef(45.0f, 0.0f, 1.0f, 0.0f);
                GL11.glScalef(0.5f, 0.5f, -0.5f);
                GL11.glTranslatef(0.5f, 0.5f, -2.0f);
                GL11.glRotatef(45.0f, 1.0f, 0.0f, 0.0f);
                GL11.glRotatef(-180.0f, 0.0f, 1.0f, 0.0f);
                this.render.renderTileEntityAt(this.tile, 0.0, 0.0, 0.0, 0.0f);
                GL11.glEnable(2884);
                GL11.glPopMatrix();
            }
            case EQUIPPED_FIRST_PERSON -> {
                GL11.glPushMatrix();
                GL11.glDisable(2884);
                GL11.glScalef(1.5f, 1.5f, -1.5f);
                GL11.glTranslatef(3.0f, -1.0f, 0.5f);
                GL11.glRotatef(135.0f, 0.0f, 1.0f, 0.0f);
                this.render.renderTileEntityAt(this.tile, 0.0, 0.0, 0.0, 0.0f);
                GL11.glEnable(2884);
                GL11.glPopMatrix();
            }
            default -> {
                GL11.glPushMatrix();
                GL11.glDisable(2884);
                GL11.glRotatef(90.0f, 0.0f, 1.0f, 0.0f);
                GL11.glScalef(0.85f, 0.85f, -0.85f);
                GL11.glTranslatef(-0.5f, 0.4f, -0.5f);
                this.render.renderTileEntityAt(this.tile, 0.0, 0.0, 0.0, 0.0f);
                GL11.glEnable(2884);
                GL11.glPopMatrix();
            }
        }
        GL11.glPopMatrix();
    }

    @Override
    public boolean shouldUseRenderHelper(IItemRenderer.ItemRenderType type, ItemStack item, IItemRenderer.ItemRendererHelper helper) {
        return true;
    }

}


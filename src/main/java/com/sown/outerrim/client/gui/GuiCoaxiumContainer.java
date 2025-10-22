package com.sown.outerrim.client.gui;

import com.sown.outerrim.gui.container.ContainerCoaxiumContainer;
import com.sown.outerrim.tileentities.TileEntityCoaxiumContainer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class GuiCoaxiumContainer extends GuiContainer {
    private static final ResourceLocation TEX = new ResourceLocation(
        "outerrim", "textures/gui/coaxium_container.png"
    );
    private final TileEntityCoaxiumContainer tile;

    public GuiCoaxiumContainer(InventoryPlayer inv, TileEntityCoaxiumContainer te) {
        super(new ContainerCoaxiumContainer(inv, te));
        this.tile = te;
        this.xSize = 176;
        this.ySize = 133;
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float pt, int mx, int my) {
        GL11.glColor4f(1, 1, 1, 1);
        Minecraft.getMinecraft().getTextureManager().bindTexture(TEX);

        int left = (width  - xSize) / 2;
        int top  = (height - ySize) / 2;
        drawTexturedModalRect(left, top, 0, 0, xSize, ySize);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mx, int my) {
        fontRendererObj.drawString("Coaxium Container", 8, 6, 0xA5A5A5);
        fontRendererObj.drawString("Inventory", 8, ySize - 96, 0xA5A5A5);
    }
}

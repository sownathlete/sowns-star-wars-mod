package com.sown.outerrim.client.gui;

import com.sown.outerrim.gui.container.ContainerPortableCoaxiumPump;
import com.sown.outerrim.tileentities.TileEntityPortableCoaxiumPump;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

public class GuiPortableCoaxiumPump extends GuiContainer {
    private static final ResourceLocation TEX = new ResourceLocation("outerrim","textures/gui/portable_coaxium_pump.png");
    private final TileEntityPortableCoaxiumPump tile;

    public GuiPortableCoaxiumPump(InventoryPlayer inv, TileEntityPortableCoaxiumPump te) {
        super(new ContainerPortableCoaxiumPump(inv,te));
        tile = te;
        xSize = 176; ySize = 131;
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float pt, int mx, int my) {
        mc.getTextureManager().bindTexture(TEX);
        drawTexturedModalRect(guiLeft,guiTop,0,0,xSize,ySize);
        int p = tile.getProgressScaled(6);
        if (p > 0) {
            mc.getTextureManager().bindTexture(TEX);
            drawTexturedModalRect(guiLeft+85,guiTop+23+(6-p),176,6-p,6,p);
        }
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mx, int my) {
        fontRendererObj.drawString("Portable Coaxium Pump",8,6,0x404040);
        fontRendererObj.drawString("Inventory",8,ySize-96+3,0x404040);
    }
}

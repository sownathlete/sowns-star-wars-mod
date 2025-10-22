package com.sown.outerrim.client.gui;

import com.sown.outerrim.gui.container.ContainerCoaxiumPump;
import com.sown.outerrim.tileentities.TileEntityCoaxiumPump;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

public class GuiCoaxiumPump extends GuiContainer {
    private static final ResourceLocation TEX = new ResourceLocation(
        "outerrim", "textures/gui/coaxium_pump.png"
    );
    private final TileEntityCoaxiumPump tile;

    private static final int SLOT_COUNT    = 9;
    private static final int SLOT_X_START  = 8;
    private static final int SLOT_Y        = 23;
    private static final int SLOT_SPACING  = 18;
    private static final int ARROW_HEIGHT  = 6;

    public GuiCoaxiumPump(InventoryPlayer inv, TileEntityCoaxiumPump te) {
        super(new ContainerCoaxiumPump(inv, te));
        tile = te;
        xSize = 176;
        ySize = 131;
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float pt, int mx, int my) {
        mc.getTextureManager().bindTexture(TEX);
        drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);

        for (int i = 0; i < SLOT_COUNT; i++) {
            int p = tile.getProgressScaled(i, ARROW_HEIGHT);
            if (p > 0) {
                int x = guiLeft + SLOT_X_START + i * SLOT_SPACING;
                int y = guiTop  + SLOT_Y + (ARROW_HEIGHT - p);
                mc.getTextureManager().bindTexture(TEX);
                drawTexturedModalRect(
                    x,               
                    y,               
                    176,             
                    ARROW_HEIGHT - p,
                    6,               
                    p                
                );
            }
        }
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mx, int my) {
        fontRendererObj.drawString("Coaxium Pump", 8, 6, 0x404040);
        fontRendererObj.drawString("Inventory", 8, ySize - 96 + 3, 0x404040);
    }
}

package com.sown.outerrim.client.gui;

import com.sown.outerrim.gui.container.ContainerCoaxiumRefinery;
import com.sown.outerrim.tileentities.TileEntityCoaxiumRefinery;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import org.lwjgl.opengl.GL11;

import java.util.Collections;

public class GuiCoaxiumRefinery extends GuiContainer {

    private static final ResourceLocation TEX =
            new ResourceLocation("outerrim", "textures/gui/coaxium_refinery.png");

    private final TileEntityCoaxiumRefinery tile;

    private static final int TICKER_U = 176;
    private static final int TICKER_V = 0;
    private static final int TICKER_W = 10;
    private static final int TICKER_H = 6;

    private static final int L_BAR_X = 24;
    private static final int L_BAR_Y0 = 25;
    private static final int L_BAR_SPACING = 18;

    private static final int R_BAR_X = 142;
    private static final int R_BAR_Y0 = 25;
    private static final int R_BAR_SPACING = 18;

    private static final int GUI_W = 176;
    private static final int GUI_H = 172;

    private static final int TEXT_COLOR = 0xEF9283;

    public GuiCoaxiumRefinery(InventoryPlayer inv, TileEntityCoaxiumRefinery te) {
        super(new ContainerCoaxiumRefinery(inv, te));
        this.tile = te;
        this.xSize = GUI_W;
        this.ySize = GUI_H;
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        super.drawScreen(mouseX, mouseY, partialTicks);

        final int left = (width - xSize) / 2;
        final int top  = (height - ySize) / 2;

        for (int row = 0; row < 3; row++) {
            int slotIdx = 0 + row;
            int gx = left + L_BAR_X;
            int gy = top  + (L_BAR_Y0 + row * L_BAR_SPACING);
            maybeDrawProgressTooltip(slotIdx, mouseX, mouseY, gx, gy);
        }
        for (int row = 0; row < 3; row++) {
            int slotIdx = 3 + row;
            int gx = left + R_BAR_X;
            int gy = top  + (R_BAR_Y0 + row * R_BAR_SPACING);
            maybeDrawProgressTooltip(slotIdx, mouseX, mouseY, gx, gy);
        }
    }

    private void maybeDrawProgressTooltip(int slotIdx, int mouseX, int mouseY, int gx, int gy) {
        int ticks = tile.getProcessTimeForSlot(slotIdx);
        int maxTicks = tile.getTotalProcessTime();
        if (maxTicks <= 0 || ticks <= 0 || ticks >= maxTicks) return;

        if (mouseX >= gx && mouseX <= gx + TICKER_W && mouseY >= gy && mouseY <= gy + TICKER_H) {
            int ticksLeft = maxTicks - ticks;
            int totalSeconds = ticksLeft / 20;
            int hours = totalSeconds / 3600;
            int minutes = (totalSeconds % 3600) / 60;
            int seconds = totalSeconds % 60;

            String msg = (hours > 0 ? (hours + "h ") : "")
                    + ((minutes > 0 || hours > 0) ? (minutes + "m ") : "")
                    + (seconds + "s left");

            this.drawHoveringText(Collections.singletonList(msg.trim()), mouseX, mouseY, fontRendererObj);
        }
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        GL11.glColor4f(1F, 1F, 1F, 1F);
        Minecraft.getMinecraft().getTextureManager().bindTexture(TEX);

        int left = (width - xSize) / 2;
        int top  = (height - ySize) / 2;

        drawTexturedModalRect(left, top, 0, 0, GUI_W, GUI_H);

        for (int row = 0; row < 3; row++) {
            int slotIdx = 0 + row;
            drawTicker(slotIdx, left + L_BAR_X, top + (L_BAR_Y0 + row * L_BAR_SPACING), true);
        }
        for (int row = 0; row < 3; row++) {
            int slotIdx = 3 + row;
            drawTicker(slotIdx, left + R_BAR_X, top + (R_BAR_Y0 + row * R_BAR_SPACING), false);
        }
    }

    private void drawTicker(int slotIdx, int x, int y, boolean rightToLeft) {
        int ticks = tile.getProcessTimeForSlot(slotIdx);
        int max   = tile.getTotalProcessTime();
        if (max <= 0 || ticks <= 0) return;

        float pct = Math.min(1f, Math.max(0f, (float) ticks / (float) max));
        int w = Math.max(1, Math.round(pct * TICKER_W));

        int drawX = rightToLeft ? (x + (TICKER_W - w)) : x;
        int u     = rightToLeft ? (TICKER_U + (TICKER_W - w)) : TICKER_U;

        drawTexturedModalRect(drawX, y, u, TICKER_V, w, TICKER_H);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        fontRendererObj.drawString("Coaxium Refinery", 8, 6, TEXT_COLOR);
        fontRendererObj.drawString(
                StatCollector.translateToLocal("container.inventory"),
                8, ySize - 94, TEXT_COLOR
        );
    }
}

package com.sown.outerrim.gui;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import org.lwjgl.opengl.GL11;

import com.sown.outerrim.gui.container.MoistureVaporatorContainer;
import com.sown.outerrim.tileentities.TileEntityMoistureVaporator;
import com.sown.util.ui.LangUtils;

@SideOnly(value=Side.CLIENT)
public class GuiMoistureVaporator
extends GuiContainer {
    private static final ResourceLocation guiTexture = new ResourceLocation("outerrim", "textures/gui/mv.png");
    TileEntityMoistureVaporator vaporator;

    public GuiMoistureVaporator(InventoryPlayer player, TileEntityMoistureVaporator vap) {
        super(new MoistureVaporatorContainer(player, vap));
        this.vaporator = vap;
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        this.mc.getTextureManager().bindTexture(guiTexture);
        int k = (this.width - this.xSize) / 2;
        int l = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(k, l, 0, 0, 175, 165);
        int percent = (int)((this.vaporator.progressTicks + 1.0f) / this.vaporator.totalTicks * 30.0f);
        this.drawTexturedModalRect(k + 62, l + 58 - percent, 176, 30 - percent, 9, percent);
        String s = LangUtils.translateKey("moisture.vaporator");
        this.fontRendererObj.drawString(s, k + this.xSize / 2 - this.fontRendererObj.getStringWidth(s) / 2, l + 6, 4210752);
        this.fontRendererObj.drawString(StatCollector.translateToLocal("container.inventory"), k + 8, l + 71, 4210752);
    }
}


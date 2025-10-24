package com.sown.outerrim.rendering;

import com.sown.outerrim.items.ItemInquisitorArmor;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiChat;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import org.lwjgl.opengl.GL11;

public class InquisitorHelmetVisionHandler {

	private final Minecraft mc = Minecraft.getMinecraft();

	@SubscribeEvent
	public void onOverlay(RenderGameOverlayEvent event) {
		if (event.isCancelable() || event.type != RenderGameOverlayEvent.ElementType.ALL)
			return;

		EntityPlayerSP player = mc.thePlayer;
		if (player == null)
			return;

		ItemStack helmet = player.getCurrentArmor(3);
		if (helmet == null || !(helmet.getItem() instanceof ItemInquisitorArmor))
			return;
		if (mc.gameSettings.thirdPersonView != 0)
			return;

		boolean allowedScreen = (mc.currentScreen == null) || (mc.currentScreen instanceof GuiChat)
				|| (mc.currentScreen instanceof GuiContainer);

		if (!allowedScreen)
			return;

		ScaledResolution res = new ScaledResolution(mc, mc.displayWidth, mc.displayHeight);
		int w = res.getScaledWidth();
		int h = res.getScaledHeight();

		GL11.glDisable(GL11.GL_DEPTH_TEST);
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);

		int color = 0x338B2030;

		Gui.drawRect(0, 0, w, h, color);

		GL11.glDisable(GL11.GL_BLEND);
		GL11.glEnable(GL11.GL_DEPTH_TEST);
	}
}

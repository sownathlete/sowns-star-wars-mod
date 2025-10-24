package com.sown.outerrim.rendering;

import com.sown.outerrim.items.ItemDarthVaderArmor;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import org.lwjgl.opengl.GL11;

import java.util.List;

public class DarthVaderHelmetVisionHandler {

	private final Minecraft mc = Minecraft.getMinecraft();

	@SubscribeEvent
	public void onOverlay(RenderGameOverlayEvent event) {
		if (event.isCancelable() || event.type != RenderGameOverlayEvent.ElementType.ALL)
			return;
		EntityPlayerSP player = mc.thePlayer;
		if (player == null)
			return;
		ItemStack helmet = player.getCurrentArmor(3);
		if (helmet == null || !(helmet.getItem() instanceof ItemDarthVaderArmor))
			return;
		if (mc.gameSettings.thirdPersonView != 0)
			return;

		ScaledResolution res = new ScaledResolution(mc, mc.displayWidth, mc.displayHeight);
		int w = res.getScaledWidth();
		int h = res.getScaledHeight();

		GL11.glDisable(GL11.GL_DEPTH_TEST);
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		Gui.drawRect(0, 0, w, h, 0x33A45758);
		GL11.glDisable(GL11.GL_BLEND);
		GL11.glEnable(GL11.GL_DEPTH_TEST);

		drawTargetHealth(player);
	}

	@SubscribeEvent
	public void onRenderWorld(RenderWorldLastEvent event) {
		EntityPlayerSP player = mc.thePlayer;
		if (player == null)
			return;
		ItemStack helmet = player.getCurrentArmor(3);
		if (helmet == null || !(helmet.getItem() instanceof ItemDarthVaderArmor))
			return;
		if (mc.gameSettings.thirdPersonView != 0)
			return;

		float prevGamma = mc.gameSettings.gammaSetting;
		mc.gameSettings.gammaSetting = 3.0F;

		double px = player.lastTickPosX + (player.posX - player.lastTickPosX) * event.partialTicks;
		double py = player.lastTickPosY + (player.posY - player.lastTickPosY) * event.partialTicks;
		double pz = player.lastTickPosZ + (player.posZ - player.lastTickPosZ) * event.partialTicks;

		List<EntityLivingBase> entities = player.worldObj.getEntitiesWithinAABB(EntityLivingBase.class,
				player.boundingBox.expand(64, 32, 64));

		GL11.glPushAttrib(GL11.GL_ALL_ATTRIB_BITS);
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		GL11.glDisable(GL11.GL_LIGHTING);
		GL11.glEnable(GL11.GL_LINE_SMOOTH);

		for (EntityLivingBase e : entities) {
			if (e == player || e.isDead)
				continue;
			if (!player.canEntityBeSeen(e))
				continue;

			float r, g, b;
			if (e instanceof IMob) {
				r = 1.0F;
				g = 0.2F;
				b = 0.2F;
			} else if (e instanceof EntityAnimal) {
				r = 0.3F;
				g = 1.0F;
				b = 0.3F;
			} else {
				r = 0.3F;
				g = 0.8F;
				b = 1.0F;
			}

			double dx = e.lastTickPosX + (e.posX - e.lastTickPosX) * event.partialTicks - px;
			double dy = e.lastTickPosY + (e.posY - e.lastTickPosY) * event.partialTicks - py;
			double dz = e.lastTickPosZ + (e.posZ - e.lastTickPosZ) * event.partialTicks - pz;

			GL11.glPushMatrix();
			GL11.glTranslated(dx, dy, dz);
			GL11.glDepthMask(false);
			GL11.glColor4f(r, g, b, 0.25F);
			OpenGlHelper.glBlendFunc(770, 771, 1, 0);
			RenderManager.instance.renderEntityWithPosYaw(e, 0, 0, 0, 0, event.partialTicks);
			GL11.glDepthMask(true);
			GL11.glPopMatrix();
		}

		GL11.glPopAttrib();
		mc.gameSettings.gammaSetting = prevGamma;
	}

	private void drawTargetHealth(EntityPlayerSP player) {
		if (mc.objectMouseOver == null || mc.objectMouseOver.typeOfHit != MovingObjectPosition.MovingObjectType.ENTITY)
			return;
		Entity hit = mc.objectMouseOver.entityHit;
		if (!(hit instanceof EntityLivingBase))
			return;
		EntityLivingBase target = (EntityLivingBase) hit;

		float hp = target.getHealth();
		float max = (float) target.getMaxHealth();
		if (max <= 0)
			max = 20;

		ScaledResolution res = new ScaledResolution(mc, mc.displayWidth, mc.displayHeight);
		int x = res.getScaledWidth() / 2;
		int y = res.getScaledHeight() / 2 + 20;

		String txt = String.format("HP: %.1f / %.1f", hp, max);
		int color = 0xFF5555;
		if (hp > max * 0.6)
			color = 0x55FF55;
		else if (hp > max * 0.3)
			color = 0xFFFF55;

		mc.fontRenderer.drawStringWithShadow(txt, x - mc.fontRenderer.getStringWidth(txt) / 2, y, color);
	}
}

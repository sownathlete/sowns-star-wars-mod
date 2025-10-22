package com.sown.outerrim.handlers;

import java.util.UUID;

import com.sown.outerrim.OuterRim;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import org.lwjgl.opengl.GL11;

public class FrozenPlayerHandler {

    private final Minecraft mc = Minecraft.getMinecraft();

    @SubscribeEvent
    public void onPlayerTick(TickEvent.PlayerTickEvent ev) {
        if (!(ev.player instanceof EntityPlayerSP)) return;
        EntityPlayerSP p = (EntityPlayerSP) ev.player;
        UUID id = p.getUniqueID();
        if (OuterRim.frozenPlayers.contains(id)) {
            // Stop all motion
            p.motionX = p.motionY = p.motionZ = 0;
            // Cancel any input
            p.movementInput.moveForward = 0;
            p.movementInput.moveStrafe  = 0;
            p.movementInput.jump        = false;
            p.movementInput.sneak       = false;
            p.onGround = true;
        }
    }

    @SubscribeEvent
    public void onOverlay(RenderGameOverlayEvent ev) {
        // only after everything is drawn and only full screen
        if (ev.isCancelable() || ev.type != RenderGameOverlayEvent.ElementType.ALL) return;

        EntityPlayerSP p = mc.thePlayer;
        if (p != null && OuterRim.frozenPlayers.contains(p.getUniqueID())) {
            // draw a full-screen black quad
            ScaledResolution res = new ScaledResolution(mc, mc.displayWidth, mc.displayHeight);
            int w = res.getScaledWidth();
            int h = res.getScaledHeight();

            GL11.glDisable(GL11.GL_DEPTH_TEST);
            GL11.glColor4f(0f, 0f, 0f, 1f);
            Gui.drawRect(0, 0, w, h, 0xFF000000);
            GL11.glEnable(GL11.GL_DEPTH_TEST);
        }
    }
}

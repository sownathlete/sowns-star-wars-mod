package com.sown.outerrim.dimension.deathstar;

import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IRenderHandler;
import org.lwjgl.opengl.GL11;

public class DrawSkybox extends IRenderHandler {

    /*
     * PNG layout:
     * row 0 : [0] south , [1] west , [2] north
     * row 1 : [3] east  , [4] top  , [5] bottom
     *
     * WORLD_TO_ATLAS maps:
     * face 0=top    -> 5
     *      1=bottom -> 4
     *      2=north  -> 2
     *      3=south  -> 0
     *      4=west   -> 3
     *      5=east   -> 1
     */
    private static final int[] WORLD_TO_ATLAS = { 5, 4, 2, 0, 3, 1 };

    private final ResourceLocation[] layers = new ResourceLocation[4]; // dawn/day/dusk/night

    public DrawSkybox(String domain, String skyName) {
        layers[0] = new ResourceLocation(domain, "textures/environment/skybox/" + skyName + "/skybox_" + skyName + "_dawn.png");
        layers[1] = new ResourceLocation(domain, "textures/environment/skybox/" + skyName + "/skybox_" + skyName + "_day.png");
        layers[2] = new ResourceLocation(domain, "textures/environment/skybox/" + skyName + "/skybox_" + skyName + "_dusk.png");
        layers[3] = new ResourceLocation(domain, "textures/environment/skybox/" + skyName + "/skybox_" + skyName + "_night.png");
    }

    @Override
    public void render(float partialTicks, WorldClient world, Minecraft mc) {
        double time = (world.getWorldTime() + partialTicks) % 24000.0;
        int size    = mc.gameSettings.renderDistanceChunks * 16;

        int layerIdx;
        float alpha;
        if (time < 1000.0) {
            layerIdx = 0; alpha = fade(time, 0.0, 200.0);
        } else if (time < 12000.0) {
            layerIdx = 1; alpha = 1f;
        } else if (time < 13000.0) {
            layerIdx = 2; alpha = fade(time - 12000.0, 0.0, 200.0);
        } else {
            layerIdx = 3; alpha = 1f;
        }

        TextureManager tex = mc.renderEngine;
        tex.bindTexture(layers[layerIdx]);

        GL11.glEnable(GL11.GL_BLEND);
        OpenGlHelper.glBlendFunc(770, 771, 1, 0);
        RenderHelper.disableStandardItemLighting();
        GL11.glDepthMask(false);
        GL11.glDisable(GL11.GL_CULL_FACE);
        GL11.glDisable(GL11.GL_LIGHTING);
        GL11.glColor4f(1f, 1f, 1f, alpha);

        Tessellator tess = Tessellator.instance;
        for (int face = 0; face < 6; face++) {
            int atlas = WORLD_TO_ATLAS[face];

            GL11.glPushMatrix();
            // ** RAISE SKYBOX by 25% of its total height **
            float offset = size * 0.25f;
            GL11.glTranslatef(0f, offset, 0f);

            rotateFace(face);
            if (atlas == 0) {
                // rotate atlas cell 0 by 180 degrees
                drawRotated180(tess, size, atlas);
            } else if (face == 4) {
                // west side rotated 90 CW
                drawRotatedCW(tess, size, atlas);
            } else if (face == 5) {
                // east side rotated 90 CCW
                drawRotatedCCW(tess, size, atlas);
            } else {
                // normal
                drawQuad(tess, size, atlas);
            }
            GL11.glPopMatrix();
        }

        GL11.glDepthMask(true);
        GL11.glDisable(GL11.GL_BLEND);
        GL11.glEnable(GL11.GL_CULL_FACE);
        GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glColor4f(1f, 1f, 1f, 1f);
    }

    private static float fade(double t, double start, double dur) {
        if (t <= start) return 0f;
        if (t >= start + dur) return 1f;
        return (float) ((t - start) / dur);
    }

    /* face: 0=top,1=bottom,2=north,3=south,4=west,5=east */
    private static void rotateFace(int face) {
        switch (face) {
            case 1: GL11.glRotatef(180f, 1f, 0f, 0f); break;
            case 2: GL11.glRotatef( 90f, 1f, 0f, 0f); break;
            case 3: GL11.glRotatef(-90f, 1f, 0f, 0f); break;
            case 4: GL11.glRotatef( 90f, 0f, 0f, 1f); break;
            case 5: GL11.glRotatef(-90f, 0f, 0f, 1f); break;
            default: break;
        }
    }

    private static void drawQuad(Tessellator t, int s, int atlas) {
        double du = (atlas % 3) / 3.0;
        double dv = (atlas / 3) / 2.0;

        t.startDrawingQuads();
        t.addVertexWithUV(-s, -s, -s, du,          dv);
        t.addVertexWithUV(-s, -s,  s, du,          dv + 0.5);
        t.addVertexWithUV( s, -s,  s, du + 0.3333, dv + 0.5);
        t.addVertexWithUV( s, -s, -s, du + 0.3333, dv);
        t.draw();
    }

    // rotate 0-cell 180 degrees
    private static void drawRotated180(Tessellator t, int s, int atlas) {
        double du0 = (atlas % 3) / 3.0;
        double dv0 = (atlas / 3) / 2.0;
        double du1 = du0 + 1.0 / 3.0;
        double dv1 = dv0 + 0.5;

        t.startDrawingQuads();
        // new lower-left  = old upper-right
        t.addVertexWithUV(-s, -s, -s, du1, dv1);
        // new upper-left  = old lower-right
        t.addVertexWithUV(-s, -s,  s, du1, dv0);
        // new upper-right = old lower-left
        t.addVertexWithUV( s, -s,  s, du0, dv0);
        // new lower-right = old upper-left
        t.addVertexWithUV( s, -s, -s, du0, dv1);
        t.draw();
    }

    // rotate the cell 90 degrees clockwise
    private static void drawRotatedCW(Tessellator t, int s, int atlas) {
        double du0 = (atlas % 3) / 3.0;
        double dv0 = (atlas / 3) / 2.0;
        double du1 = du0 + 1.0 / 3.0;
        double dv1 = dv0 + 0.5;

        t.startDrawingQuads();
        t.addVertexWithUV(-s, -s, -s, du0, dv1);
        t.addVertexWithUV(-s, -s,  s, du1, dv1);
        t.addVertexWithUV( s, -s,  s, du1, dv0);
        t.addVertexWithUV( s, -s, -s, du0, dv0);
        t.draw();
    }

    // rotate the cell 90 degrees counter-clockwise
    private static void drawRotatedCCW(Tessellator t, int s, int atlas) {
        double du0 = (atlas % 3) / 3.0;
        double dv0 = (atlas / 3) / 2.0;
        double du1 = du0 + 1.0 / 3.0;
        double dv1 = dv0 + 0.5;

        t.startDrawingQuads();
        t.addVertexWithUV(-s, -s, -s, du1, dv0);
        t.addVertexWithUV(-s, -s,  s, du0, dv0);
        t.addVertexWithUV( s, -s,  s, du0, dv1);
        t.addVertexWithUV( s, -s, -s, du1, dv1);
        t.draw();
    }
}

package com.sown.outerrim.dimension.tatooine;

import cpw.mods.fml.client.FMLClientHandler;
import java.util.Random;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.renderer.GLAllocation;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Vec3;
import net.minecraft.world.WorldProvider;
import net.minecraftforge.client.IRenderHandler;
import org.lwjgl.opengl.GL11;

public class DrawTatooineSky
extends IRenderHandler {
    private static ResourceLocation tatoo1Texture = new ResourceLocation("outerrim", "textures/environment/tatoo_i.png");
    private static ResourceLocation tatoo2Texture = new ResourceLocation("outerrim", "textures/environment/tatoo_ii.png");
    private static ResourceLocation ghomrassenTexture = new ResourceLocation("outerrim", "textures/environment/ghomrassen.png");
    private static Vec3 ghomrassenOffset = Vec3.createVectorHelper((double)0.0, (double)0.0, (double)20.0);
    private static float ghomrassenSizeMod = 0.8f;
    private static ResourceLocation guermessaTexture = new ResourceLocation("outerrim", "textures/environment/guermessa.png");
    private static Vec3 guermessaOffset = Vec3.createVectorHelper((double)32.0, (double)0.0, (double)0.0);
    private static float guermessaSizeMod = 1.75f;
    private static ResourceLocation cheniniTexture = new ResourceLocation("outerrim", "textures/environment/chenini.png");
    private static Vec3 cheniniOffset = Vec3.createVectorHelper((double)44.0, (double)0.0, (double)-4.0);
    private static float cheniniSizeMod = 3.0f;
    static int starList = GLAllocation.generateDisplayLists((int)3);
    static int glSkyList = starList + 1;
    static int glSkyList2 = starList + 2;
    private static float sunSize = 32.0f;
    private static float moonSize = 32.0f;
    private static float sun2OffsetX = 60.0f;
    private static float sun2OffsetY = 0.0f;
    private static float sun2OffsetZ = -32.0f;

    public DrawTatooineSky() {
        GL11.glPushMatrix();
        GL11.glNewList((int)starList, (int)4864);
        this.renderStars();
        GL11.glEndList();
        GL11.glPopMatrix();
        Tessellator tessellator = Tessellator.instance;
        GL11.glNewList((int)glSkyList, (int)4864);
        int byte2 = 64;
        int i = 256 / byte2 + 2;
        float f = 16.0f;
        for (int j = -byte2 * i; j <= byte2 * i; j += byte2) {
            for (int l = -byte2 * i; l <= byte2 * i; l += byte2) {
                tessellator.startDrawingQuads();
                tessellator.addVertex((double)(j + 0), (double)f, (double)(l + 0));
                tessellator.addVertex((double)(j + byte2), (double)f, (double)(l + 0));
                tessellator.addVertex((double)(j + byte2), (double)f, (double)(l + byte2));
                tessellator.addVertex((double)(j + 0), (double)f, (double)(l + byte2));
                tessellator.draw();
            }
        }
        GL11.glEndList();
        GL11.glNewList((int)glSkyList2, (int)4864);
        f = -16.0f;
        tessellator.startDrawingQuads();
        for (int k = -byte2 * i; k <= byte2 * i; k += byte2) {
            for (int i1 = -byte2 * i; i1 <= byte2 * i; i1 += byte2) {
                tessellator.addVertex((double)(k + byte2), (double)f, (double)(i1 + 0));
                tessellator.addVertex((double)(k + 0), (double)f, (double)(i1 + 0));
                tessellator.addVertex((double)(k + 0), (double)f, (double)(i1 + byte2));
                tessellator.addVertex((double)(k + byte2), (double)f, (double)(i1 + byte2));
            }
        }
        tessellator.draw();
        GL11.glEndList();
    }

    public float getSkyBrightness(float par1) {
        float var2 = FMLClientHandler.instance().getClient().theWorld.getCelestialAngle(par1);
        float var3 = 1.0f - (MathHelper.sin((float)(var2 * 3.1415927f * 2.0f)) * 2.0f + 0.25f);
        if (var3 < 0.0f) {
            var3 = 0.0f;
        }
        if (var3 > 1.0f) {
            var3 = 1.0f;
        }
        return var3 * var3 * 1.0f;
    }

    public void render(float partialTicks, WorldClient world, Minecraft mc) {
        GL11.glDisable((int)3553);
        Vec3 vec3 = world.getSkyColor((Entity)mc.renderViewEntity, partialTicks);
        float f1 = (float)vec3.xCoord;
        float f2 = (float)vec3.yCoord;
        float f3 = (float)vec3.zCoord;
        if (mc.gameSettings.anaglyph) {
            float f4 = (f1 * 30.0f + f2 * 59.0f + f3 * 11.0f) / 100.0f;
            float f5 = (f1 * 30.0f + f2 * 70.0f) / 100.0f;
            float f6 = (f1 * 30.0f + f3 * 70.0f) / 100.0f;
            f1 = f4;
            f2 = f5;
            f3 = f6;
        }
        GL11.glColor3f((float)f1, (float)f2, (float)f3);
        Tessellator tessellator1 = Tessellator.instance;
        GL11.glDepthMask((boolean)false);
        GL11.glEnable((int)2912);
        GL11.glColor3f((float)f1, (float)f2, (float)f3);
        GL11.glCallList((int)glSkyList);
        GL11.glDisable((int)2912);
        GL11.glDisable((int)3008);
        GL11.glEnable((int)3042);
        OpenGlHelper.glBlendFunc((int)770, (int)771, (int)1, (int)0);
        RenderHelper.disableStandardItemLighting();
        float f18 = world.getStarBrightness(partialTicks);
        if (f18 > 0.0f) {
            GL11.glColor4f((float)f18, (float)f18, (float)f18, (float)f18);
            GL11.glCallList((int)starList);
        }
        float[] afloat = new float[4];
        GL11.glDisable((int)3553);
        GL11.glShadeModel((int)7425);
        GL11.glPushMatrix();
        GL11.glRotatef((float)-90.0f, (float)0.0f, (float)1.0f, (float)0.0f);
        GL11.glRotatef((float)(world.getCelestialAngle(partialTicks) * 360.0f), (float)1.0f, (float)0.0f, (float)0.0f);
        afloat[0] = 1.0f;
        afloat[1] = 0.7607843f;
        afloat[2] = 0.7058824f;
        afloat[3] = 0.3f;
        float f6 = afloat[0];
        float f7 = afloat[1];
        float f8 = afloat[2];
        if (mc.gameSettings.anaglyph) {
            float f9 = (f6 * 30.0f + f7 * 59.0f + f8 * 11.0f) / 100.0f;
            float f10 = (f6 * 30.0f + f7 * 70.0f) / 100.0f;
            float f11 = (f6 * 30.0f + f8 * 70.0f) / 100.0f;
            f6 = f9;
            f7 = f10;
            f8 = f11;
        }
        f18 = 1.0f - f18;
        tessellator1.startDrawing(6);
        tessellator1.setColorRGBA_F(f6 * f18, f7 * f18, f8 * f18, afloat[3] * 2.0f / f18);
        tessellator1.addVertex(0.0, 100.0, 0.0);
        tessellator1.setColorRGBA_F(afloat[0] * f18, afloat[1] * f18, afloat[2] * f18, 0.0f);
        float f10 = 20.0f;
        tessellator1.addVertex((double)(-f10), 100.0, (double)(-f10));
        tessellator1.addVertex(0.0, 100.0, (double)(-f10) * 1.5);
        tessellator1.addVertex((double)f10, 100.0, (double)(-f10));
        tessellator1.addVertex((double)f10 * 1.5, 100.0, 0.0);
        tessellator1.addVertex((double)f10, 100.0, (double)f10);
        tessellator1.addVertex(0.0, 100.0, (double)f10 * 1.5);
        tessellator1.addVertex((double)(-f10), 100.0, (double)f10);
        tessellator1.addVertex((double)(-f10) * 1.5, 100.0, 0.0);
        tessellator1.addVertex((double)(-f10), 100.0, (double)(-f10));
        tessellator1.draw();
        tessellator1.startDrawing(6);
        tessellator1.setColorRGBA_F(f6 * f18, f7 * f18, f8 * f18, afloat[3] * f18);
        tessellator1.addVertex(0.0, 100.0, 0.0);
        tessellator1.setColorRGBA_F(afloat[0] * f18, afloat[1] * f18, afloat[2] * f18, 0.0f);
        f10 = 40.0f;
        tessellator1.addVertex((double)(-f10), 100.0, (double)(-f10));
        tessellator1.addVertex(0.0, 100.0, (double)(-f10) * 1.5);
        tessellator1.addVertex((double)f10, 100.0, (double)(-f10));
        tessellator1.addVertex((double)f10 * 1.5, 100.0, 0.0);
        tessellator1.addVertex((double)f10, 100.0, (double)f10);
        tessellator1.addVertex(0.0, 100.0, (double)f10 * 1.5);
        tessellator1.addVertex((double)(-f10), 100.0, (double)f10);
        tessellator1.addVertex((double)(-f10) * 1.5, 100.0, 0.0);
        tessellator1.addVertex((double)(-f10), 100.0, (double)(-f10));
        tessellator1.draw();
        GL11.glPopMatrix();
        GL11.glShadeModel((int)7424);
        GL11.glEnable((int)3553);
        OpenGlHelper.glBlendFunc((int)770, (int)1, (int)1, (int)0);
        GL11.glPushMatrix();
        f7 = 0.0f;
        f8 = 0.0f;
        float f9 = 0.0f;
        GL11.glTranslatef((float)f7, (float)f8, (float)f9);
        GL11.glRotatef((float)-90.0f, (float)0.0f, (float)1.0f, (float)0.0f);
        GL11.glRotatef((float)(world.getCelestialAngle(partialTicks) * 360.0f), (float)1.0f, (float)0.0f, (float)0.0f);
        GL11.glDisable((int)3553);
        GL11.glColor4f((float)0.0f, (float)0.0f, (float)0.0f, (float)1.0f);
        f10 = sunSize / 3.5f;
        tessellator1.startDrawingQuads();
        tessellator1.addVertex((double)(-f10), 99.9, (double)(-f10));
        tessellator1.addVertex((double)f10, 99.9, (double)(-f10));
        tessellator1.addVertex((double)f10, 99.9, (double)f10);
        tessellator1.addVertex((double)(-f10), 99.9, (double)f10);
        tessellator1.draw();
        GL11.glEnable((int)3553);
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        f10 = sunSize;
        mc.renderEngine.bindTexture(tatoo2Texture);
        tessellator1.startDrawingQuads();
        tessellator1.addVertexWithUV((double)(-f10), 100.0, (double)(-f10), 0.0, 0.0);
        tessellator1.addVertexWithUV((double)f10, 100.0, (double)(-f10), 1.0, 0.0);
        tessellator1.addVertexWithUV((double)f10, 100.0, (double)f10, 1.0, 1.0);
        tessellator1.addVertexWithUV((double)(-f10), 100.0, (double)f10, 0.0, 1.0);
        tessellator1.draw();
        GL11.glDisable((int)3553);
        GL11.glColor4f((float)0.0f, (float)0.0f, (float)0.0f, (float)1.0f);
        f10 = sunSize / 3.5f;
        tessellator1.startDrawingQuads();
        tessellator1.addVertex((double)(-f10 + sun2OffsetX), 99.9 + (double)sun2OffsetY, (double)(-f10 + sun2OffsetZ));
        tessellator1.addVertex((double)(f10 + sun2OffsetX), 99.9 + (double)sun2OffsetY, (double)(-f10 + sun2OffsetZ));
        tessellator1.addVertex((double)(f10 + sun2OffsetX), 99.9 + (double)sun2OffsetY, (double)(f10 + sun2OffsetZ));
        tessellator1.addVertex((double)(-f10 + sun2OffsetX), 99.9 + (double)sun2OffsetY, (double)(f10 + sun2OffsetZ));
        tessellator1.draw();
        GL11.glEnable((int)3553);
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        f10 = sunSize;
        mc.renderEngine.bindTexture(tatoo1Texture);
        tessellator1.startDrawingQuads();
        tessellator1.addVertexWithUV((double)(-f10 + sun2OffsetX), 100.0 + (double)sun2OffsetY, (double)(-f10 + sun2OffsetZ), 0.0, 0.0);
        tessellator1.addVertexWithUV((double)(f10 + sun2OffsetX), 100.0 + (double)sun2OffsetY, (double)(-f10 + sun2OffsetZ), 1.0, 0.0);
        tessellator1.addVertexWithUV((double)(f10 + sun2OffsetX), 100.0 + (double)sun2OffsetY, (double)(f10 + sun2OffsetZ), 1.0, 1.0);
        tessellator1.addVertexWithUV((double)(-f10 + sun2OffsetX), 100.0 + (double)sun2OffsetY, (double)(f10 + sun2OffsetZ), 0.0, 1.0);
        tessellator1.draw();
        GL11.glRotatef((float)180.0f, (float)1.0f, (float)0.0f, (float)0.0f);
        GL11.glDisable((int)3553);
        GL11.glColor4f((float)0.0f, (float)0.0f, (float)0.0f, (float)1.0f);
        f10 = moonSize / 3.5f / cheniniSizeMod;
        tessellator1.startDrawingQuads();
        tessellator1.addVertex((double)(-f10) + DrawTatooineSky.cheniniOffset.xCoord, 99.9 + DrawTatooineSky.cheniniOffset.yCoord, (double)(-f10) + DrawTatooineSky.cheniniOffset.zCoord);
        tessellator1.addVertex((double)f10 + DrawTatooineSky.cheniniOffset.xCoord, 99.9 + DrawTatooineSky.cheniniOffset.yCoord, (double)(-f10) + DrawTatooineSky.cheniniOffset.zCoord);
        tessellator1.addVertex((double)f10 + DrawTatooineSky.cheniniOffset.xCoord, 99.9 + DrawTatooineSky.cheniniOffset.yCoord, (double)f10 + DrawTatooineSky.cheniniOffset.zCoord);
        tessellator1.addVertex((double)(-f10) + DrawTatooineSky.cheniniOffset.xCoord, 99.9 + DrawTatooineSky.cheniniOffset.yCoord, (double)f10 + DrawTatooineSky.cheniniOffset.zCoord);
        tessellator1.draw();
        GL11.glEnable((int)3553);
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        f10 = moonSize / cheniniSizeMod;
        mc.renderEngine.bindTexture(cheniniTexture);
        tessellator1.startDrawingQuads();
        tessellator1.addVertexWithUV((double)(-f10) + DrawTatooineSky.cheniniOffset.xCoord, 100.0 + DrawTatooineSky.cheniniOffset.yCoord, (double)(-f10) + DrawTatooineSky.cheniniOffset.zCoord, 0.0, 0.0);
        tessellator1.addVertexWithUV((double)f10 + DrawTatooineSky.cheniniOffset.xCoord, 100.0 + DrawTatooineSky.cheniniOffset.yCoord, (double)(-f10) + DrawTatooineSky.cheniniOffset.zCoord, 1.0, 0.0);
        tessellator1.addVertexWithUV((double)f10 + DrawTatooineSky.cheniniOffset.xCoord, 100.0 + DrawTatooineSky.cheniniOffset.yCoord, (double)f10 + DrawTatooineSky.cheniniOffset.zCoord, 1.0, 1.0);
        tessellator1.addVertexWithUV((double)(-f10) + DrawTatooineSky.cheniniOffset.xCoord, 100.0 + DrawTatooineSky.cheniniOffset.yCoord, (double)f10 + DrawTatooineSky.cheniniOffset.zCoord, 0.0, 1.0);
        tessellator1.draw();
        GL11.glDisable((int)3553);
        GL11.glColor4f((float)0.0f, (float)0.0f, (float)0.0f, (float)1.0f);
        f10 = moonSize / 3.5f / ghomrassenSizeMod;
        tessellator1.startDrawingQuads();
        tessellator1.addVertex((double)(-f10) + DrawTatooineSky.ghomrassenOffset.xCoord, 99.9 + DrawTatooineSky.ghomrassenOffset.yCoord, (double)(-f10) + DrawTatooineSky.ghomrassenOffset.zCoord);
        tessellator1.addVertex((double)f10 + DrawTatooineSky.ghomrassenOffset.xCoord, 99.9 + DrawTatooineSky.ghomrassenOffset.yCoord, (double)(-f10) + DrawTatooineSky.ghomrassenOffset.zCoord);
        tessellator1.addVertex((double)f10 + DrawTatooineSky.ghomrassenOffset.xCoord, 99.9 + DrawTatooineSky.ghomrassenOffset.yCoord, (double)f10 + DrawTatooineSky.ghomrassenOffset.zCoord);
        tessellator1.addVertex((double)(-f10) + DrawTatooineSky.ghomrassenOffset.xCoord, 99.9 + DrawTatooineSky.ghomrassenOffset.yCoord, (double)f10 + DrawTatooineSky.ghomrassenOffset.zCoord);
        tessellator1.draw();
        GL11.glEnable((int)3553);
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        f10 = moonSize / ghomrassenSizeMod;
        mc.renderEngine.bindTexture(ghomrassenTexture);
        tessellator1.startDrawingQuads();
        tessellator1.addVertexWithUV((double)(-f10) + DrawTatooineSky.ghomrassenOffset.xCoord, 100.0 + DrawTatooineSky.ghomrassenOffset.yCoord, (double)(-f10) + DrawTatooineSky.ghomrassenOffset.zCoord, 0.0, 0.0);
        tessellator1.addVertexWithUV((double)f10 + DrawTatooineSky.ghomrassenOffset.xCoord, 100.0 + DrawTatooineSky.ghomrassenOffset.yCoord, (double)(-f10) + DrawTatooineSky.ghomrassenOffset.zCoord, 1.0, 0.0);
        tessellator1.addVertexWithUV((double)f10 + DrawTatooineSky.ghomrassenOffset.xCoord, 100.0 + DrawTatooineSky.ghomrassenOffset.yCoord, (double)f10 + DrawTatooineSky.ghomrassenOffset.zCoord, 1.0, 1.0);
        tessellator1.addVertexWithUV((double)(-f10) + DrawTatooineSky.ghomrassenOffset.xCoord, 100.0 + DrawTatooineSky.ghomrassenOffset.yCoord, (double)f10 + DrawTatooineSky.ghomrassenOffset.zCoord, 0.0, 1.0);
        tessellator1.draw();
        GL11.glDisable((int)3553);
        GL11.glColor4f((float)0.0f, (float)0.0f, (float)0.0f, (float)1.0f);
        f10 = moonSize / 3.5f / guermessaSizeMod;
        tessellator1.startDrawingQuads();
        tessellator1.addVertex((double)(-f10) + DrawTatooineSky.guermessaOffset.xCoord, 99.9 + DrawTatooineSky.guermessaOffset.yCoord, (double)(-f10) + DrawTatooineSky.guermessaOffset.zCoord);
        tessellator1.addVertex((double)f10 + DrawTatooineSky.guermessaOffset.xCoord, 99.9 + DrawTatooineSky.guermessaOffset.yCoord, (double)(-f10) + DrawTatooineSky.guermessaOffset.zCoord);
        tessellator1.addVertex((double)f10 + DrawTatooineSky.guermessaOffset.xCoord, 99.9 + DrawTatooineSky.guermessaOffset.yCoord, (double)f10 + DrawTatooineSky.guermessaOffset.zCoord);
        tessellator1.addVertex((double)(-f10) + DrawTatooineSky.guermessaOffset.xCoord, 99.9 + DrawTatooineSky.guermessaOffset.yCoord, (double)f10 + DrawTatooineSky.guermessaOffset.zCoord);
        tessellator1.draw();
        GL11.glEnable((int)3553);
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        f10 = moonSize / guermessaSizeMod;
        mc.renderEngine.bindTexture(guermessaTexture);
        tessellator1.startDrawingQuads();
        tessellator1.addVertexWithUV((double)(-f10) + DrawTatooineSky.guermessaOffset.xCoord, 100.0 + DrawTatooineSky.guermessaOffset.yCoord, (double)(-f10) + DrawTatooineSky.guermessaOffset.zCoord, 0.0, 0.0);
        tessellator1.addVertexWithUV((double)f10 + DrawTatooineSky.guermessaOffset.xCoord, 100.0 + DrawTatooineSky.guermessaOffset.yCoord, (double)(-f10) + DrawTatooineSky.guermessaOffset.zCoord, 1.0, 0.0);
        tessellator1.addVertexWithUV((double)f10 + DrawTatooineSky.guermessaOffset.xCoord, 100.0 + DrawTatooineSky.guermessaOffset.yCoord, (double)f10 + DrawTatooineSky.guermessaOffset.zCoord, 1.0, 1.0);
        tessellator1.addVertexWithUV((double)(-f10) + DrawTatooineSky.guermessaOffset.xCoord, 100.0 + DrawTatooineSky.guermessaOffset.yCoord, (double)f10 + DrawTatooineSky.guermessaOffset.zCoord, 0.0, 1.0);
        tessellator1.draw();
        GL11.glDisable((int)3553);
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        GL11.glDisable((int)3042);
        GL11.glEnable((int)3008);
        GL11.glEnable((int)2912);
        GL11.glPopMatrix();
        GL11.glDisable((int)3553);
        GL11.glColor3f((float)0.0f, (float)0.0f, (float)0.0f);
        double d0 = mc.thePlayer.getPosition((float)partialTicks).yCoord - world.getHorizon();
        if (d0 < 0.0) {
            GL11.glPushMatrix();
            GL11.glTranslatef((float)0.0f, (float)12.0f, (float)0.0f);
            GL11.glCallList((int)glSkyList2);
            GL11.glPopMatrix();
            f8 = 1.0f;
            f9 = -((float)(d0 + 65.0));
            f10 = -f8;
            tessellator1.startDrawingQuads();
            tessellator1.setColorRGBA_I(0, 255);
            tessellator1.addVertex((double)(-f8), (double)f9, (double)f8);
            tessellator1.addVertex((double)f8, (double)f9, (double)f8);
            tessellator1.addVertex((double)f8, (double)f10, (double)f8);
            tessellator1.addVertex((double)(-f8), (double)f10, (double)f8);
            tessellator1.addVertex((double)(-f8), (double)f10, (double)(-f8));
            tessellator1.addVertex((double)f8, (double)f10, (double)(-f8));
            tessellator1.addVertex((double)f8, (double)f9, (double)(-f8));
            tessellator1.addVertex((double)(-f8), (double)f9, (double)(-f8));
            tessellator1.addVertex((double)f8, (double)f10, (double)(-f8));
            tessellator1.addVertex((double)f8, (double)f10, (double)f8);
            tessellator1.addVertex((double)f8, (double)f9, (double)f8);
            tessellator1.addVertex((double)f8, (double)f9, (double)(-f8));
            tessellator1.addVertex((double)(-f8), (double)f9, (double)(-f8));
            tessellator1.addVertex((double)(-f8), (double)f9, (double)f8);
            tessellator1.addVertex((double)(-f8), (double)f10, (double)f8);
            tessellator1.addVertex((double)(-f8), (double)f10, (double)(-f8));
            tessellator1.addVertex((double)(-f8), (double)f10, (double)(-f8));
            tessellator1.addVertex((double)(-f8), (double)f10, (double)f8);
            tessellator1.addVertex((double)f8, (double)f10, (double)f8);
            tessellator1.addVertex((double)f8, (double)f10, (double)(-f8));
            tessellator1.draw();
        }
        if (world.provider.isSkyColored()) {
            GL11.glColor3f((float)(f1 * 0.2f + 0.04f), (float)(f2 * 0.2f + 0.04f), (float)(f3 * 0.6f + 0.1f));
        } else {
            GL11.glColor3f((float)f1, (float)f2, (float)f3);
        }
        GL11.glPushMatrix();
        GL11.glTranslatef((float)0.0f, (float)(-((float)(d0 - 16.0))), (float)0.0f);
        GL11.glCallList((int)glSkyList2);
        GL11.glPopMatrix();
        GL11.glEnable((int)3553);
        GL11.glDepthMask((boolean)true);
    }

    private void renderStars() {
        Random rand = new Random(10842L);
        Tessellator var2 = Tessellator.instance;
        var2.startDrawingQuads();
        for (int starIndex = 0; starIndex < 6000; ++starIndex) {
            double var4 = rand.nextFloat() * 2.0f - 1.0f;
            double var6 = rand.nextFloat() * 2.0f - 1.0f;
            double var8 = rand.nextFloat() * 2.0f - 1.0f;
            double var10 = 0.15f + rand.nextFloat() * 0.1f;
            double var12 = var4 * var4 + var6 * var6 + var8 * var8;
            if (!(var12 < 1.0) || !(var12 > 0.01)) continue;
            var12 = 1.0 / Math.sqrt(var12);
            double var14 = (var4 *= var12) * 100.0;
            double var16 = (var6 *= var12) * 100.0;
            double var18 = (var8 *= var12) * 100.0;
            double var20 = Math.atan2(var4, var8);
            double var22 = Math.sin(var20);
            double var24 = Math.cos(var20);
            double var26 = Math.atan2(Math.sqrt(var4 * var4 + var8 * var8), var6);
            double var28 = Math.sin(var26);
            double var30 = Math.cos(var26);
            double var32 = rand.nextDouble() * 3.141592653589793 * 2.0;
            double var34 = Math.sin(var32);
            double var36 = Math.cos(var32);
            for (int var38 = 0; var38 < 4; ++var38) {
                double var39 = 0.0;
                double var41 = (double)((var38 & 2) - 1) * var10;
                double var43 = (double)((var38 + 1 & 2) - 1) * var10;
                double var47 = var41 * var36 - var43 * var34;
                double var49 = var43 * var36 + var41 * var34;
                double var53 = var47 * var28 + var39 * var30;
                double var55 = var39 * var28 - var47 * var30;
                double var57 = var55 * var22 - var49 * var24;
                double var61 = var49 * var22 + var55 * var24;
                var2.addVertex(var14 + var57, var16 + var53, var18 + var61);
            }
        }
        var2.draw();
    }
}


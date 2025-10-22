/*
 * Decompiled with CFR 0.148.
 *
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.FontRenderer
 *  net.minecraft.client.gui.ScaledResolution
 *  net.minecraft.util.MathHelper
 *  org.lwjgl.opengl.GL11
 */
package com.sown.util.ui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.util.MathHelper;
import org.lwjgl.opengl.GL11;

public class P3D {
    private static void startPolygon(boolean filled) {
        if (filled) {
            GL11.glBegin(6);
        } else {
            GL11.glBegin(2);
        }
    }

    public static void setup2D(Minecraft mc) {
        ScaledResolution scaledresolution = new ScaledResolution(mc, mc.displayWidth, mc.displayHeight);
        GL11.glClear(256);
        GL11.glMatrixMode(5889);
        GL11.glLoadIdentity();
        GL11.glOrtho(0.0, scaledresolution.getScaledWidth_double(), scaledresolution.getScaledHeight_double(), 0.0, 1000.0, 3000.0);
        GL11.glMatrixMode(5888);
        GL11.glLoadIdentity();
        GL11.glTranslatef(0.0f, 0.0f, -2000.0f);
    }

    public static void drawRectangle(double x, double y, double w, double h, boolean filled) {
        GL11.glPushMatrix();
        P3D.startPolygon(filled);
        GL11.glVertex2d(x, y);
        GL11.glVertex2d(x, y + h);
        GL11.glVertex2d(x + w, y + h);
        GL11.glVertex2d(x + w, y);
        GL11.glEnd();
        GL11.glPopMatrix();
    }

    public static void drawRectangle(double x, double y, double z, double w, double h, boolean filled) {
        GL11.glPushMatrix();
        P3D.startPolygon(filled);
        GL11.glVertex3d(x, y, z);
        GL11.glVertex3d(x, y + h, z);
        GL11.glVertex3d(x + w, y + h, z);
        GL11.glVertex3d(x + w, y, z);
        GL11.glEnd();
        GL11.glPopMatrix();
    }

    public static void drawTriangle(double x, double y, int scale, boolean filled) {
        P3D.startPolygon(filled);
        GL11.glVertex2d(x, y + scale);
        GL11.glVertex2d(x + scale, y - scale);
        GL11.glVertex2d(x - scale, y - scale);
        GL11.glEnd();
    }

    public static void drawLine(double sx, double sy, double ex, double ey) {
        GL11.glPushMatrix();
        GL11.glBegin(3);
        GL11.glVertex2d(sx, sy);
        GL11.glVertex2d(ex, ey);
        GL11.glEnd();
        GL11.glPopMatrix();
    }

    public static void drawLine(double sx, double sy, double sz, double ex, double ey, double ez) {
        GL11.glPushMatrix();
        GL11.glBegin(3);
        GL11.glVertex3d(sx, sy, sz);
        GL11.glVertex3d(ex, ey, ez);
        GL11.glEnd();
        GL11.glPopMatrix();
    }

    public static void drawCircle(double x, double y, double radius, boolean filled) {
        GL11.glPushMatrix();
        P3D.startPolygon(filled);
        for (int i = 0; i <= 360; ++i) {
            double nx = MathHelper.sin(((float)(i * 3.141592653589793 / 180.0))) * radius;
            double ny = MathHelper.cos(((float)(i * 3.141592653589793 / 180.0))) * radius;
            GL11.glVertex2d(nx + x, ny + y);
        }
        GL11.glEnd();
        GL11.glPopMatrix();
    }

    public static void drawDashedCircle(double x, double y, double radius, int dashLen) {
        GL11.glPushMatrix();
        int i = 0;
        while (i <= 360) {
            GL11.glBegin(3);
            for (int j = 0; j <= dashLen; ++j) {
                double nx = MathHelper.sin(((float)((i + j) * 3.141592653589793 / 180.0))) * radius;
                double ny = MathHelper.cos(((float)((i + j) * 3.141592653589793 / 180.0))) * radius;
                GL11.glVertex2d(nx + x, ny + y);
            }
            GL11.glEnd();
            i = (int)(i + 3.141592653589793 * dashLen);
        }
        GL11.glPopMatrix();
    }

    public static void drawPieCircle(double x, double y, double radius, double percent, boolean filled) {
        GL11.glPushMatrix();
        P3D.startPolygon(filled);
        GL11.glVertex2d(x, y);
        int i = 0;
        while (i <= 360.0 * percent) {
            double nx = MathHelper.sin(((float)(i * 3.141592653589793 / 180.0))) * radius;
            double ny = MathHelper.cos(((float)(i * 3.141592653589793 / 180.0))) * radius;
            GL11.glVertex2d(nx + x, ny + y);
            ++i;
        }
        GL11.glEnd();
        GL11.glPopMatrix();
    }

    public static void drawPieDonut(double x, double y, double radius, double stripSize, double percent, boolean filled) {
        GL11.glPushMatrix();
        P3D.startPolygon(filled);
        int i = 0;
        while (i <= 360.0 * percent) {
            double nx = MathHelper.sin(((float)(i * 3.141592653589793 / 180.0))) * (radius - stripSize);
            double ny = MathHelper.cos(((float)(i * 3.141592653589793 / 180.0))) * (radius - stripSize);
            GL11.glVertex2d(nx + x, ny + y);
            nx = MathHelper.sin(((float)(i * 3.141592653589793 / 180.0))) * radius;
            ny = MathHelper.cos(((float)(i * 3.141592653589793 / 180.0))) * radius;
            GL11.glVertex2d(nx + x, ny + y);
            ++i;
        }
        GL11.glEnd();
        GL11.glPopMatrix();
    }

    public static void drawText(FontRenderer font, String s, double x, double y, double scale, int color, int shadowColor, int shadowDistance) {
        GL11.glPushMatrix();
        GL11.glPushAttrib(8192);
        GL11.glEnable(3553);
        GL11.glScaled(scale, scale, 1.0);
        GL11.glTranslated(x / scale, y / scale, 0.0);
        font.drawString(s, shadowDistance, shadowDistance, shadowColor);
        font.drawString(s, 0, 0, color);
        GL11.glPopAttrib();
        GL11.glPopMatrix();
    }

    public static void drawCenteredText(FontRenderer font, String s, double x, double y, double scale, int color, int shadowColor, int shadowDistance) {
        GL11.glPushMatrix();
        GL11.glPushAttrib(8192);
        GL11.glEnable(3553);
        GL11.glScaled(scale, scale, 1.0);
        GL11.glTranslated(x / scale, y / scale, 0.0);
        font.drawString(s, shadowDistance, shadowDistance, shadowColor);
        font.drawString(s, -font.getStringWidth(s) / 2, 0, color);
        GL11.glPopAttrib();
        GL11.glPopMatrix();
    }

    public static int getRGB(int r, int g, int b) {
        int rgb = r;
        rgb = (rgb << 8) + g;
        rgb = (rgb << 8) + b;
        return rgb;
    }

    public static int getRGBA(int r, int g, int b, int a) {
        int rgba = a;
        rgba = (rgba << 8) + r;
        rgba = (rgba << 8) + g;
        rgba = (rgba << 8) + b;
        return rgba;
    }

    public static void glScalef(double scale) {
        GL11.glScaled(scale, scale, scale);
    }

    public static void glColorGrayscale(double color) {
        GL11.glColor3d(color, color, color);
    }
}


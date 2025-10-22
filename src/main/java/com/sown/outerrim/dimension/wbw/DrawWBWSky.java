package com.sown.outerrim.dimension.wbw;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.renderer.Tessellator;
import net.minecraftforge.client.IRenderHandler;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.Sphere;

public class DrawWBWSky extends IRenderHandler {
    private static final int STAR_COUNT   = 10000;
    private static final float STAR_MIN   = 0.075f;
    private static final float STAR_RANGE = 0.05f;

    // Sky color #0F1418
    private static final float SKY_R = 15f/255f, SKY_G = 20f/255f, SKY_B = 24f/255f;
    // Star base color #4A4F55
    private static final float BASE_R = 74f/255f, BASE_G = 79f/255f, BASE_B = 85f/255f;

    private final Sphere sphere = new Sphere();
    private final List<Star> stars = new ArrayList<>(STAR_COUNT);

    public DrawWBWSky() {
        // Precompute star positions once
        for (int i = 0; i < STAR_COUNT; i++) {
            Random rand = new Random(i * 7919L + 1);

            double x = rand.nextFloat() * 2 - 1;
            double y = rand.nextFloat() * 2 - 1;
            double z = rand.nextFloat() * 2 - 1;
            double m = x*x + y*y + z*z;
            if (m < 1.0 && m > 0.01) {
                double inv = 1.0 / Math.sqrt(m);
                x *= inv * 100;
                y *= inv * 100;
                z *= inv * 100;

                float size = STAR_MIN + rand.nextFloat() * STAR_RANGE;
                float b = (float)Math.pow(rand.nextFloat(), 4);
                float baseFactor = 0.85f + b * (2f - 0.85f);

                // orientation for quad billboarding
                double az = Math.atan2(x, z), sa = Math.sin(az), ca = Math.cos(az);
                double el = Math.atan2(Math.sqrt(x*x + z*z), y), se = Math.sin(el), ce = Math.cos(el);
                double rot = rand.nextDouble() * Math.PI * 2, sr = Math.sin(rot), cr = Math.cos(rot);

                stars.add(new Star(
                    x, y, z,
                    size,
                    baseFactor,
                    sa, ca, se, ce, sr, cr
                ));
            }
        }
    }

    @Override
    public void render(float partialTicks, WorldClient world, Minecraft mc) {
        // draw sky dome
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glDisable(GL11.GL_LIGHTING);
        GL11.glDisable(GL11.GL_CULL_FACE);
        GL11.glDisable(GL11.GL_BLEND);
        GL11.glColor4f(SKY_R, SKY_G, SKY_B, 1f);
        sphere.draw(110f, 50, 50);

        // faint background glow
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE);
        GL11.glColor4f(1f, 1f, 1f, 0.02f);
        sphere.draw(115f, 50, 50);

        // render all stars without twinkling
        renderStars();

        // restore GL state
        GL11.glDisable(GL11.GL_BLEND);
        GL11.glEnable(GL11.GL_CULL_FACE);
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glColor4f(1f, 1f, 1f, 1f);
    }

    private void renderStars() {
        Tessellator t = Tessellator.instance;
        t.startDrawingQuads();
        for (Star s : stars) {
            float f = s.baseFactor;
            t.setColorRGBA_F(BASE_R * f, BASE_G * f, BASE_B * f, 1f);
            drawQuad(s, t);
        }
        t.draw();
    }

    private void drawQuad(Star s, Tessellator t) {
        for (int v = 0; v < 4; v++) {
            double cx = ((v & 2) - 1) * s.size;
            double cz = (((v + 1) & 2) - 1) * s.size;
            double rx = cx * s.cr - cz * s.sr;
            double rz = cz * s.cr + cx * s.sr;
            double dy = rx * s.se;
            double back = -rx * s.ce;
            double dx = back * s.sa - rz * s.ca;
            double dz = rz * s.sa + back * s.ca;
            t.addVertex(s.x + dx, s.y + dy, s.z + dz);
        }
    }

    private static class Star {
        final double x, y, z;
        final float size, baseFactor;
        final double sa, ca, se, ce, sr, cr;

        Star(double x, double y, double z,
             float size, float baseFactor,
             double sa, double ca, double se, double ce, double sr, double cr) {
            this.x = x; this.y = y; this.z = z;
            this.size = size; this.baseFactor = baseFactor;
            this.sa = sa; this.ca = ca;
            this.se = se; this.ce = ce;
            this.sr = sr; this.cr = cr;
        }
    }
}
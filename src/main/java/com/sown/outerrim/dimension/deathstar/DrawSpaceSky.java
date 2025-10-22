package com.sown.outerrim.dimension.deathstar;

import java.awt.Color;
import java.util.Random;

import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.renderer.Tessellator;
import net.minecraftforge.client.IRenderHandler;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.Sphere;

public class DrawSpaceSky extends IRenderHandler {
    private static final int STAR_COUNT   = 10000;
    private static final float STAR_MIN   = 0.075f;
    private static final float STAR_RANGE = 0.05f;

    private final Sphere sphere = new Sphere();

    @Override
    public void render(float partialTicks, WorldClient world, Minecraft mc) {
        double time = world.getTotalWorldTime() + partialTicks;

        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glDisable(GL11.GL_LIGHTING);
        GL11.glDisable(GL11.GL_CULL_FACE);
        GL11.glDisable(GL11.GL_BLEND);

        // prevent sky dome from writing to depth buffer (fixes see-through/fog)
        GL11.glDepthMask(false);
        GL11.glColor4f(0f, 0f, 0f, 1f);
        sphere.draw(220f, 50, 50); // doubled radius
        GL11.glDepthMask(true);

        renderStars(time);

        GL11.glEnable(GL11.GL_CULL_FACE);
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glColor4f(1f, 1f, 1f, 1f);
    }

    private void renderStars(double time) {
        Tessellator t = Tessellator.instance;
        t.startDrawingQuads();

        for (int i = 0; i < STAR_COUNT; i++) {
            Random rand = new Random(i * 7919L + 1);
            double x = rand.nextFloat() * 2 - 1;
            double y = rand.nextFloat() * 2 - 1;
            double z = rand.nextFloat() * 2 - 1;
            double m = x * x + y * y + z * z;

            if (m < 1.0 && m > 0.01) {
                m = 1.0 / Math.sqrt(m);
                x *= m * 200; // also scaled to match bigger dome
                y *= m * 200;
                z *= m * 200;

                double size = STAR_MIN + rand.nextFloat() * STAR_RANGE;

                float baseBrightness = (float) Math.pow(rand.nextFloat(), 4);
                float brightness = 0.85f + baseBrightness * (2f - 0.85f);

                int rgb = getStarColorRGB(rand);
                float r = ((rgb >> 16) & 0xFF) / 255f * brightness;
                float g = ((rgb >> 8) & 0xFF) / 255f * brightness;
                float b = (rgb & 0xFF) / 255f * brightness;

                t.setColorRGBA_F(r, g, b, 1f);
                drawQuad(x, y, z, size, rand, t);
            }
        }

        t.draw();
    }

    private void drawQuad(double x, double y, double z, double s, Random rand, Tessellator t) {
        double az = Math.atan2(x, z), sa = Math.sin(az), ca = Math.cos(az);
        double el = Math.atan2(Math.sqrt(x * x + z * z), y), se = Math.sin(el), ce = Math.cos(el);
        double rot = rand.nextDouble() * Math.PI * 2, sr = Math.sin(rot), cr = Math.cos(rot);

        for (int v = 0; v < 4; v++) {
            double cx = ((v & 2) - 1) * s;
            double cz0 = (((v + 1) & 2) - 1) * s;
            double rx = cx * cr - cz0 * sr;
            double rz = cz0 * cr + cx * sr;
            double dy = rx * se, back = -rx * ce;
            double dx = back * sa - rz * ca, dz = rz * sa + back * ca;
            t.addVertex(x + dx, y + dy, z + dz);
        }
    }

    private static int getStarColorRGB(Random rand) {
        float hue, saturation;
        float brightness = 1.0f - 0.8f * rand.nextFloat();
        double colorType = rand.nextDouble();
        if (colorType <= 0.08) {
            hue = 0.48f + 0.08f * rand.nextFloat();
            saturation = 0.18f + 0.22f * rand.nextFloat();
        } else if (colorType <= 0.24) {
            hue = 0.126f + 0.04f * rand.nextFloat();
            saturation = 0.0f + 0.15f * rand.nextFloat();
            brightness *= 0.95f;
        } else if (colorType <= 0.45) {
            hue = 0.126f + 0.04f * rand.nextFloat();
            saturation = 0.15f + 0.15f * rand.nextFloat();
            brightness *= 0.9f;
        } else if (colorType <= 0.67) {
            hue = 0.126f + 0.04f * rand.nextFloat();
            saturation = 0.8f + 0.15f * rand.nextFloat();
            brightness = rand.nextInt(3) == 1 ? brightness * 0.9f : brightness * 0.85f;
        } else if (colorType <= 0.92) {
            hue = 0.055f + 0.055f * rand.nextFloat();
            saturation = 0.85f + 0.15f * rand.nextFloat();
            brightness = rand.nextInt(3) == 1 ? brightness * 0.9f : brightness * 0.8f;
        } else {
            hue = 0.95f + 0.05f * rand.nextFloat();
            if (rand.nextInt(3) == 1) {
                saturation = 0.8f + 0.2f * rand.nextFloat();
                brightness *= 0.95f;
            } else {
                saturation = 0.7f + 0.2f * rand.nextFloat();
                brightness *= 0.65f;
            }
        }
        return Color.HSBtoRGB(hue, saturation, brightness);
    }
}

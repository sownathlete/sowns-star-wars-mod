package com.sown.outerrim.entities;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * Precise 1.7.10 port of geometry.spark.eta2_starfighter
 * - Correct parent/child pivots (Bedrock absolute -> Java relative)
 * - Y-axis inversion handled (Bedrock Y-up -> Java Y-down)
 * - UVs mapped 1:1 from JSON (top-left origin in Java)
 * - Zero-thickness quads from JSON converted to thickness=1
 */
public class ModelEta2Starfighter extends ModelBase {

    // roots / bones
    private final ModelRenderer ship;
    private final ModelRenderer right_half;
    private final ModelRenderer closed_right;
    private final ModelRenderer open_right;
    private final ModelRenderer left_half;
    private final ModelRenderer closed_left;
    private final ModelRenderer open_left;
    private final ModelRenderer center;

    // --- Bedrock pivots in model space (used for conversion math) ---
    private static final float[] PIVOT_SHIP         = { 0f, 17f, -13f };
    private static final float[] PIVOT_RIGHT_HALF   = { -9f, 17f, -13f };
    private static final float[] PIVOT_LEFT_HALF    = {  9f, 17f, -13f };
    private static final float[] PIVOT_CLOSED_RIGHT = {  0f, 17f,  -5f };
    private static final float[] PIVOT_OPEN_RIGHT   = {  0f, 17f,  -5f };
    private static final float[] PIVOT_CLOSED_LEFT  = {  0f, 17f,  -5f };
    private static final float[] PIVOT_OPEN_LEFT    = {  0f, 17f,  -5f };
    private static final float[] PIVOT_CENTER       = { -9f, 17f, -13f };

    public ModelEta2Starfighter() {
        textureWidth  = 1024;
        textureHeight = 1024;

        // ship is the root; keep rotationPoint at (0,0,0) and do all child
        // rotationPoint deltas relative to its Bedrock pivot.
        ship = new ModelRenderer(this);
        ship.setRotationPoint(0f, 0f, 0f);

        // right_half (pivot relative to ship)
        right_half = new ModelRenderer(this);
        right_half.setRotationPoint(
                PIVOT_RIGHT_HALF[0] - PIVOT_SHIP[0],
                -(PIVOT_RIGHT_HALF[1] - PIVOT_SHIP[1]),
                PIVOT_RIGHT_HALF[2] - PIVOT_SHIP[2]
        );
        ship.addChild(right_half);

        // left_half
        left_half = new ModelRenderer(this);
        left_half.setRotationPoint(
                PIVOT_LEFT_HALF[0] - PIVOT_SHIP[0],
                -(PIVOT_LEFT_HALF[1] - PIVOT_SHIP[1]),
                PIVOT_LEFT_HALF[2] - PIVOT_SHIP[2]
        );
        ship.addChild(left_half);
        left_half.mirror = true;

        // closed_right
        closed_right = new ModelRenderer(this);
        closed_right.setRotationPoint(
                PIVOT_CLOSED_RIGHT[0] - PIVOT_RIGHT_HALF[0],
                -(PIVOT_CLOSED_RIGHT[1] - PIVOT_RIGHT_HALF[1]),
                PIVOT_CLOSED_RIGHT[2] - PIVOT_RIGHT_HALF[2]
        );
        right_half.addChild(closed_right);

        // open_right
        open_right = new ModelRenderer(this);
        open_right.setRotationPoint(
                PIVOT_OPEN_RIGHT[0] - PIVOT_RIGHT_HALF[0],
                -(PIVOT_OPEN_RIGHT[1] - PIVOT_RIGHT_HALF[1]),
                PIVOT_OPEN_RIGHT[2] - PIVOT_RIGHT_HALF[2]
        );
        right_half.addChild(open_right);

        // closed_left
        closed_left = new ModelRenderer(this);
        closed_left.setRotationPoint(
                PIVOT_CLOSED_LEFT[0] - PIVOT_LEFT_HALF[0],
                -(PIVOT_CLOSED_LEFT[1] - PIVOT_LEFT_HALF[1]),
                PIVOT_CLOSED_LEFT[2] - PIVOT_LEFT_HALF[2]
        );
        left_half.addChild(closed_left);
        closed_left.mirror = true;

        // open_left
        open_left = new ModelRenderer(this);
        open_left.setRotationPoint(
                PIVOT_OPEN_LEFT[0] - PIVOT_LEFT_HALF[0],
                -(PIVOT_OPEN_LEFT[1] - PIVOT_LEFT_HALF[1]),
                PIVOT_OPEN_LEFT[2] - PIVOT_LEFT_HALF[2]
        );
        left_half.addChild(open_left);
        open_left.mirror = true;

        // center (child of ship)
        center = new ModelRenderer(this);
        center.setRotationPoint(
                PIVOT_CENTER[0] - PIVOT_SHIP[0],
                -(PIVOT_CENTER[1] - PIVOT_SHIP[1]),
                PIVOT_CENTER[2] - PIVOT_SHIP[2]
        );
        ship.addChild(center);

        // === BOXES =========================================================
        // Helper calls translate Bedrock (origin,size,inflate) + UV to Java.

        // --- right_half boxes ---
        box(right_half, 128,160, -24,10,-108,  8,16,96,   0f, PIVOT_RIGHT_HALF);
        box(right_half, 176,  0, -16,10,-108,  8, 8,88,   0f, PIVOT_RIGHT_HALF);
        box(right_half,   0, 20, -46, 4, -52,  4, 4, 8,   0f, PIVOT_RIGHT_HALF);
        box(right_half,   0,  0, -54, 4, -60,  4, 4,16,   0f, PIVOT_RIGHT_HALF);
        box(right_half, 176, 96, -24, 2,  52, 12,12,24, -0.05f, PIVOT_RIGHT_HALF);
        box(right_half,   0, 80, -24, 2, -12,  8,32,64,   0f, PIVOT_RIGHT_HALF);

        // --- closed_right ---
        box(closed_right, 656,  0, -40,10, -92, 16, 8,144, 0f, PIVOT_CLOSED_RIGHT);
        box(closed_right, 800,152, -56, 2, -44, 16, 8, 96, 0f, PIVOT_CLOSED_RIGHT);
        box(closed_right, 832,  0, -72,-6, -44, 16, 8, 80, 0f, PIVOT_CLOSED_RIGHT);
        box(closed_right, 768,152, -88,-14,-28, 16, 8, 48, 0f, PIVOT_CLOSED_RIGHT);

        // --- open_right (0-thick faces made thickness=1 on X) ---
        box(open_right,  192,272, -88,-14,-28, 16, 8, 48, 0f, PIVOT_OPEN_RIGHT);
        box(open_right,  240, 96, -72, -6,-44, 16, 8, 80, 0f, PIVOT_OPEN_RIGHT);
        box(open_right,    0,152, -56,  2,-44, 16, 8, 96, 0f, PIVOT_OPEN_RIGHT);
        box(open_right,    0,  0, -40, 10,-92, 16, 8,144, 0f, PIVOT_OPEN_RIGHT);
        // decorative “panels” (size[0,48,48]) -> 1px thick quads:
        box(open_right,    0,104, -88, -6,-28, 1,48,48, 0f, PIVOT_OPEN_RIGHT);
        box(open_right,    0,152, -88, -6,-28, 1,48,48, 0f, PIVOT_OPEN_RIGHT);
        box(open_right,  128,104, -88,-62,-28, 1,48,48, 0f, PIVOT_OPEN_RIGHT);
        box(open_right,    0, 48, -88,-62,-28, 1,48,48, 0f, PIVOT_OPEN_RIGHT);

        // --- left_half boxes (mirrored) ---
        left_half.mirror = true;
        box(left_half, 128,160, 16,10,-108,  8,16,96, 0f, PIVOT_LEFT_HALF);
        box(left_half, 176,  0,  8,10,-108,  8, 8,88, 0f, PIVOT_LEFT_HALF);
        box(left_half,   0, 20, 42, 4, -52,  4, 4, 8, 0f, PIVOT_LEFT_HALF);
        box(left_half,   0,  0, 50, 4, -60,  4, 4,16, 0f, PIVOT_LEFT_HALF);
        box(left_half, 176, 96, 12, 2,  52, 12,12,24,-0.05f, PIVOT_LEFT_HALF);
        box(left_half,   0, 80, 16, 2, -12,  8,32,64, 0f, PIVOT_LEFT_HALF);

        // --- closed_left (mirrored) ---
        closed_left.mirror = true;
        box(closed_left, 656,  0, 24,10,-92, 16, 8,144, 0f, PIVOT_CLOSED_LEFT);
        box(closed_left, 800,152, 40, 2,-44, 16, 8, 96, 0f, PIVOT_CLOSED_LEFT);
        box(closed_left, 832,  0, 56,-6,-44, 16, 8, 80, 0f, PIVOT_CLOSED_LEFT);
        box(closed_left, 768,152, 72,-14,-28, 16, 8, 48, 0f, PIVOT_CLOSED_LEFT);

        // --- open_left (mirrored; panels -> thickness=1) ---
        open_left.mirror = true;
        box(open_left, 192,272, 72,-14,-28, 16, 8, 48, 0f, PIVOT_OPEN_LEFT);
        box(open_left, 240, 96, 56, -6,-44, 16, 8, 80, 0f, PIVOT_OPEN_LEFT);
        box(open_left,   0,152, 40,  2,-44, 16, 8, 96, 0f, PIVOT_OPEN_LEFT);
        box(open_left,   0,  0, 24, 10,-92, 16, 8,144, 0f, PIVOT_OPEN_LEFT);
        box(open_left,   0,104, 88, -6,-28, 1,48,48, 0f, PIVOT_OPEN_LEFT);
        box(open_left,   0,152, 88, -6,-28, 1,48,48, 0f, PIVOT_OPEN_LEFT);
        box(open_left, 128,104, 88,-62,-28, 1,48,48, 0f, PIVOT_OPEN_LEFT);
        box(open_left,   0, 48, 88,-62,-28, 1,48,48, 0f, PIVOT_OPEN_LEFT);

        // --- center ---
        box(center,   0,256, -16, -6,-12, 32, 8,64, 0f, PIVOT_CENTER);
        box(center, 240,184, -16, 34,-12, 32, 8,64, 0f, PIVOT_CENTER);
        box(center, 176, 40, -16,  2,-20, 32,32, 8, 0f, PIVOT_CENTER);
        box(center, 176,  0, -16,  2, 52, 32,32, 8, 0f, PIVOT_CENTER);
    }

    /**
     * Convert Bedrock cube to Java box.
     *
     * @param part   target ModelRenderer
     * @param u,v    UV origin from JSON
     * @param ox,oy,oz  Bedrock origin (absolute, Y-up)
     * @param sx,sy,sz  size
     * @param inflate  inflate (can be negative)
     * @param pivot    the ABSOLUTE pivot of the parent bone this box belongs to
     */
    private void box(ModelRenderer part, int u, int v,
                     float ox, float oy, float oz,
                     int sx, int sy, int sz,
                     float inflate,
                     float[] pivot)
    {
        // convert 0-thick to 1-thick so it renders (Bedrock allows planes)
        int fixSx = (sx == 0) ? 1 : sx;
        int fixSy = (sy == 0) ? 1 : sy;
        int fixSz = (sz == 0) ? 1 : sz;

        // Java coords are relative to the bone's rotationPoint.
        // Y is inverted and addBox expects the TOP-left-front corner,
        // so we subtract size in Y after inversion.
        float x = ox - pivot[0];
        float y = -(oy - pivot[1]) - fixSy;
        float z = oz - pivot[2];

        part.setTextureOffset(u, v).addBox(x, y, z, fixSx, fixSy, fixSz, inflate);
    }

    @Override
    public void render(Entity entity, float limbSwing, float limbSwingAmount,
                       float ageInTicks, float netHeadYaw, float headPitch, float scale) {
        // No GL flips needed; all axis corrections are baked into the geometry.
        ship.render(scale);
    }
}

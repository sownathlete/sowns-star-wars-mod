package com.sown.outerrim.entities;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * Auto-converted from Bedrock geometry:
 * identifier: geometry.spark.n1_starfighter
 * texture: 512x512
 *
 * Notes:
 * - Coordinates are passed through as-is from Bedrock "origin" relative to each bone's pivot.
 * - If the model appears mirrored on Y or Z in-game, toggle FLIP_Y / FLIP_Z below.
 * - Bedrock "mirror": true is respected per-cube.
 */
public class ModelSparkN1Starfighter extends ModelBase {

    private static final boolean FLIP_Y = false;  // set true if Y is upside-down in-game
    private static final boolean FLIP_Z = false;  // set true if Z is inverted in-game

    public final ModelRenderer root;
    public final ModelRenderer root_cam;
    public final ModelRenderer root_flight;

    public final ModelRenderer left_half;
    public final ModelRenderer center;
    public final ModelRenderer right_half;

    public ModelSparkN1Starfighter() {
        this.textureWidth = 512;
        this.textureHeight = 512;

        // Bones (pivots in Bedrock units)
        root = new ModelRenderer(this, 0, 0);
        root.setRotationPoint(0.0F, 0.0F, 0.0F);

        root_cam = new ModelRenderer(this, 0, 0);
        setPivot(root_cam, 0.0F, 45.0F, 48.0F);
        root.addChild(root_cam);

        root_flight = new ModelRenderer(this, 0, 0);
        setPivot(root_flight, 0.0F, 3.0F, 0.0F);
        root_cam.addChild(root_flight);

        left_half = new ModelRenderer(this, 0, 0);
        setPivot(left_half, -0.5F, 0.0F, 0.0F);
        root_flight.addChild(left_half);

        center = new ModelRenderer(this, 0, 0);
        setPivot(center, 0.25F, 0.0F, 0.0F);
        root_flight.addChild(center);

        right_half = new ModelRenderer(this, 0, 0);
        setPivot(right_half, 0.5F, 0.0F, 0.0F);
        root_flight.addChild(right_half);

        /* -------------------------
         *  LEFT HALF (mirror: false)
         * ------------------------- */
        add(left_half, 160,144, -0.5F,0F,0F,  -120,-17,-86, 16,3,96, false);
        add(left_half, 160,  0, -0.5F,0F,0F,  -120, 2,-86,  16,3,96, false);
        add(left_half,   0,  0, -0.5F,0F,0F,  -120,-14,-102,16,16,128,false);
        add(left_half, 208,243, -0.5F,0F,0F,  -123,-14,-86, 3,16,96, false);
        add(left_half,  80,  0, -0.5F,0F,0F,  -104,-14,-86, 3,16,16, false);
        add(left_half,   0,144, -0.5F,0F,0F,  -104,-14,-22, 3,16,32, false);
        add(left_half, 280,371, -0.5F,0F,0F,  -114, -8, 26, 4, 4,48, false);
        add(left_half, 160, 99, -0.5F,0F,0F,   -56,-14,-70,32, 8,16, false);
        add(left_half,   0, 96, -0.5F,0F,0F,   -48,-14,-38, 8, 8, 8, false);
        add(left_half, 112,264, -0.5F,0F,0F,   -56, -6,-38,16, 8,16, false);
        add(left_half, 288,179, -0.5F,0F,0F,   -88,-14,-54,64,16,16, false);
        add(left_half,   0,264, -0.5F,0F,0F,   -24,-14,-86,16, 8,16, false);
        add(left_half,   0,  0, -0.5F,0F,0F,   -16,-14, 10, 8, 8,16, false);
        add(left_half,   0,192, -0.5F,0F,0F,   -24, -6, 10,16,16,16, false);
        add(left_half, 342,323, -0.5F,0F,0F,   -24,  2,-54,16, 8,64, false);
        add(left_half,  80, 80, -0.5F,0F,0F,   -16, 10, 18, 8, 8, 8, false);
        add(left_half, 160,  0, -0.5F,0F,0F,   -40, -6,-38,16,16,32, false);
        add(left_half,  80, 64, -0.5F,0F,0F,   -32,-14,-22, 8, 8, 8, false);
        add(left_half, 256, 99, -0.5F,0F,0F,   -40,-14,-38,16, 8,16, false);
        add(left_half,   0, 64, -0.5F,0F,0F,   -11, 18, 90, 3,16,16, false);
        add(left_half,   0,240, -0.5F,0F,0F,   -16,  2, 26, 8,16,96, false);
        add(left_half, 216,355, -0.5F,0F,0F,   -16,-14, 26, 8,16,48, false);
        add(left_half,  80, 32, -0.5F,0F,0F,   -32, -6,-62, 8, 8, 8, false);
        add(left_half,   0, 64, -0.5F,0F,0F,  -104,-14,-70,16,16,48, false);
        add(left_half,   0, 24, -0.5F,0F,0F,  -116,-10,-110,8, 8, 8, false);

        /* -------------
         *  CENTER
         * ------------- */
        add(center, 176,176, 0.25F,0F,0F,   -8,-14,-86,16,16,16, false);
        add(center, 224,243, 0.25F,0F,0F,   -8,-14,-102,16, 8,16, false);
        add(center, 112,243, 0.25F,0F,0F,   -8,-22,-86,16, 8,80, false);
        add(center,   0,144, 0.25F,0F,0F,  -24,-14,-70,48,16,80, false);
        add(center, 310,243, 0.25F,0F,0F,   -8,-14, 10,16,16,64, false);
        add(center, 288, 99, 0.25F,0F,0F,   -8,  2,-70,16, 8,72, false);
        add(center, 176,144, 0.25F,0F,0F,   -8, 10,  2,16, 8,24, false);
        add(center, 288,  0, 0.25F,0F,0F,   -8, 18, 42,16,16,72, false);
        add(center, 112,240, 0.25F,0F,0F,   -8, 34, 90,16, 8,16, false);
        add(center,  88,352, 0.25F,0F,0F,   -8, -6, 74,16, 8,48, false);
        add(center,   0,352, 0.25F,0F,0F,   -8,  2,114,16,24,56, false);
        add(center,   0,  0, 0.25F,0F,0F,   -8, 10,170,16,16,48, false);
        add(center, 160, 48, 0.25F,0F,0F,   -8, 18,218,16, 8,32, false);
        add(center,   0,240, 0.25F,0F,0F,   -8, 18, 26,16, 8,16, false);

        /* --------------------------
         *  RIGHT HALF (mirror:true)
         * -------------------------- */
        add(right_half, 160,144, 0.5F,0F,0F,  104,-17,-86,16,3,96, true);
        add(right_half, 160,  0, 0.5F,0F,0F,  104,  2,-86,16,3,96, true);
        add(right_half,   0,  0, 0.5F,0F,0F,  104,-14,-102,16,16,128,true);
        add(right_half, 208,243, 0.5F,0F,0F,  120,-14,-86, 3,16,96, true);
        add(right_half,  80,  0, 0.5F,0F,0F,  101,-14,-86, 3,16,16, true);
        add(right_half,   0,144, 0.5F,0F,0F,  101,-14,-22, 3,16,32, true);
        add(right_half, 280,371, 0.5F,0F,0F,  110, -8, 26, 4, 4,48, true);
        add(right_half, 160, 99, 0.5F,0F,0F,   24,-14,-70,32, 8,16, true);
        add(right_half,   0, 96, 0.5F,0F,0F,   40,-14,-38, 8, 8, 8, true);
        add(right_half, 112,264, 0.5F,0F,0F,   40, -6,-38,16, 8,16, true);
        add(right_half, 288,179, 0.5F,0F,0F,   24,-14,-54,64,16,16, true);
        add(right_half,   0,264, 0.5F,0F,0F,    8,-14,-86,16, 8,16, true);
        add(right_half,   0,  0, 0.5F,0F,0F,    8,-14, 10, 8, 8,16, true);
        add(right_half,   0,192, 0.5F,0F,0F,    8, -6, 10,16,16,16, true);
        add(right_half, 342,323, 0.5F,0F,0F,    8,  2,-54,16, 8,64, true);
        add(right_half,  80, 80, 0.5F,0F,0F,    8, 10, 18, 8, 8, 8, true);
        add(right_half, 160,  0, 0.5F,0F,0F,   24, -6,-38,16,16,32, true);
        add(right_half,  80, 64, 0.5F,0F,0F,   24,-14,-22, 8, 8, 8, true);
        add(right_half, 256, 99, 0.5F,0F,0F,   24,-14,-38,16, 8,16, true);
        add(right_half,   0, 64, 0.5F,0F,0F,    8, 18, 90, 3,16,16, true);
        add(right_half,   0,240, 0.5F,0F,0F,    8,  2, 26, 8,16,96, true);
        add(right_half, 216,355, 0.5F,0F,0F,    8,-14, 26, 8,16,48, true);
        add(right_half,  80, 32, 0.5F,0F,0F,   24, -6,-62, 8, 8, 8, true);
        add(right_half,   0, 64, 0.5F,0F,0F,   88,-14,-70,16,16,48, true);
        add(right_half,   0, 24, 0.5F,0F,0F,  108,-10,-110,8, 8, 8, true);
    }

    private void setPivot(ModelRenderer part, float px, float py, float pz) {
        part.setRotationPoint(px, adjY(py), adjZ(pz));
    }

    private float adjY(float y) { return FLIP_Y ? -y : y; }
    private float adjZ(float z) { return FLIP_Z ? -z : z; }

    /**
     * Adds a cube using Bedrock-style "origin" and "size", relative to a bone pivot.
     * @param r   target ModelRenderer (already parented)
     * @param u,v texture UV (top-left)
     * @param px,py,pz bone pivot (Bedrock)
     * @param ox,oy,oz cube origin (Bedrock)
     * @param sx,sy,sz cube size
     * @param mirror whether this cube is mirrored
     */
    private void add(ModelRenderer r, int u, int v,
                     float px, float py, float pz,
                     float ox, float oy, float oz,
                     int sx, int sy, int sz,
                     boolean mirror) {

        // offsets relative to pivot
        float dx = ox - px;
        float dy = oy - py;
        float dz = oz - pz;

        // optional flips (if needed)
        dy = adjY(dy);
        dz = adjZ(dz);

        // texture & mirror
        r.setTextureOffset(u, v);
        boolean prevMirror = r.mirror;
        r.mirror = mirror;

        // add box (Forge 1.7.10 expects float offsets & int sizes)
        r.addBox(dx, dy, dz, sx, sy, sz, 0.0F);

        // restore mirror state for safety
        r.mirror = prevMirror;
    }

    @Override
    public void render(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks,
                       float netHeadYaw, float headPitch, float scale) {
        root.render(scale);
    }
}

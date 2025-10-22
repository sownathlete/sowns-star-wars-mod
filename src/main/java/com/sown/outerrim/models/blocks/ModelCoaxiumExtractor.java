package com.sown.outerrim.models.blocks;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ModelCoaxiumExtractor extends ModelBase {

    public ModelRenderer Main;

    public Map<String, ModelRenderer> parts = new HashMap<String, ModelRenderer>();

    public ModelCoaxiumExtractor() {
        this.textureWidth = 512;
        this.textureHeight = 512;

        LayerDefinition ld = createBodyLayer();
        PartDefinition root = ld.mesh.getRoot();
        PartDefinition mainDef = root.getChild("Main");
        if (mainDef == null) {
            mainDef = root;
        }

        this.Main = buildModelRecursive(null, mainDef);
    }

    private ModelRenderer buildModelRecursive(ModelRenderer parent, PartDefinition def) {
        ModelRenderer mr = new ModelRenderer(this);
        mr.setRotationPoint(def.pose.px, def.pose.py, def.pose.pz);
        mr.rotateAngleX = def.pose.rx;
        mr.rotateAngleY = def.pose.ry;
        mr.rotateAngleZ = def.pose.rz;

        for (Box b : def.builder.boxes) {
            mr.setTextureOffset(b.u, b.v);
            mr.mirror = b.mirror;
            mr.addBox(b.x, b.y, b.z, Math.round(b.dx), Math.round(b.dy), Math.round(b.dz));
        }

        if (parent != null) {
            parent.addChild(mr);
        }

        parts.put(def.name, mr);
        if ("Main".equals(def.name)) {
            this.Main = mr;
        }

        for (PartDefinition child : def.children) {
            buildModelRecursive(mr, child);
        }
        return mr;
    }

    @Override
    public void render(Entity entity, float limbSwing, float limbSwingAmount,
                       float ageInTicks, float netHeadYaw, float headPitch, float scale) {
        if (Main != null) {
            Main.render(scale);
        }
    }

    private static class LayerDefinition {
        final MeshDefinition mesh;
        final int texW, texH;
        private LayerDefinition(MeshDefinition mesh, int tw, int th) {
            this.mesh = mesh; this.texW = tw; this.texH = th;
        }
        static LayerDefinition create(MeshDefinition mesh, int tw, int th) { return new LayerDefinition(mesh, tw, th); }
    }

    private static class MeshDefinition {
        final PartDefinition root = new PartDefinition("root", CubeListBuilder.create(), PartPose.offset(0, 24, 0));
        PartDefinition getRoot() { return root; }
    }

    private static class PartDefinition {
        final String name;
        final CubeListBuilder builder;
        final PartPose pose;
        final List<PartDefinition> children = new ArrayList<PartDefinition>();

        PartDefinition(String name, CubeListBuilder builder, PartPose pose) {
            this.name = name;
            this.builder = builder;
            this.pose = pose;
        }

        PartDefinition addOrReplaceChild(String childName, CubeListBuilder b, PartPose p) {
            PartDefinition d = new PartDefinition(childName, b, p);
            // replace if exists
            for (int i = 0; i < children.size(); i++) {
                if (children.get(i).name.equals(childName)) { children.set(i, d); return d; }
            }
            children.add(d);
            return d;
        }

        PartDefinition getChild(String childName) {
            for (PartDefinition c : children) if (c.name.equals(childName)) return c;
            return null;
        }
    }

    private static class PartPose {
        final float px, py, pz;
        final float rx, ry, rz;
        private PartPose(float px, float py, float pz, float rx, float ry, float rz) {
            this.px = px; this.py = py; this.pz = pz; this.rx = rx; this.ry = ry; this.rz = rz;
        }
        static PartPose offset(float x, float y, float z) { return new PartPose(x, y, z, 0f, 0f, 0f); }
        static PartPose offsetAndRotation(float x, float y, float z, float rx, float ry, float rz) {
            return new PartPose(x, y, z, rx, ry, rz);
        }
    }

    private static class CubeListBuilder {
        final List<Box> boxes = new ArrayList<Box>();
        int u = 0, v = 0;
        boolean mirror = false;

        static CubeListBuilder create() { return new CubeListBuilder(); }

        CubeListBuilder texOffs(int u, int v) { this.u = u; this.v = v; return this; }
        CubeListBuilder mirror() { this.mirror = true; return this; }
        CubeListBuilder mirror(boolean m) { this.mirror = m; return this; }

        CubeListBuilder addBox(float x, float y, float z, float dx, float dy, float dz, CubeDeformation def) {
            boxes.add(new Box(u, v, mirror, x, y, z, dx, dy, dz));
            return this;
        }
    }

    private static class CubeDeformation {
        final float inflate;
        CubeDeformation(float f) { this.inflate = f; }
    }

    private static class Box {
        final int u, v;
        final boolean mirror;
        final float x, y, z, dx, dy, dz;
        Box(int u, int v, boolean mirror, float x, float y, float z, float dx, float dy, float dz) {
            this.u = u; this.v = v; this.mirror = mirror;
            this.x = x; this.y = y; this.z = z; this.dx = dx; this.dy = dy; this.dz = dz;
        }
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition Main = partdefinition.addOrReplaceChild("Main", CubeListBuilder.create(), PartPose.offset(0.0F, 19.975F, 0.0F));

        PartDefinition Smallmain = Main.addOrReplaceChild("Smallmain", CubeListBuilder.create(), PartPose.offset(85.0F, 0.0F, -19.0F));

        PartDefinition Tube2 = Smallmain.addOrReplaceChild("Tube2", CubeListBuilder.create(), PartPose.offset(-19.0F, -53.2418F, -10.813F));

        PartDefinition PortableMain = Main.addOrReplaceChild("PortableMain", CubeListBuilder.create(), PartPose.offset(0.0F, 5.025F, -7.0F));

        PartDefinition Tube3 = PortableMain.addOrReplaceChild("Tube3", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition LargeMain = Main.addOrReplaceChild("LargeMain", CubeListBuilder.create(), PartPose.offset(0.0F, 4.025F, 0.0F));

        PartDefinition Tube = LargeMain.addOrReplaceChild("Tube", CubeListBuilder.create().texOffs(170, 369).addBox(-4.0F, -62.975F, -26.0F, 8.0F, 8.0F, 2.0F, new CubeDeformation(0.0F))
        .texOffs(170, 369).addBox(-4.0F, -20.975F, -26.0F, 8.0F, 8.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -4.025F, 0.0F));

        PartDefinition cube_r1 = Tube.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(230, 333).addBox(-2.3F, -4.45F, -4.0F, 10.0F, 10.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.05F, 0.9332F, -76.7879F, 1.5708F, -0.48F, 0.0F));

        PartDefinition cube_r2 = Tube.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(190, 333).addBox(-7.7F, -4.45F, -4.0F, 10.0F, 10.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.95F, 0.9332F, -76.7879F, 1.5708F, 0.48F, 0.0F));

        PartDefinition cube_r3 = Tube.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(376, 229).addBox(-5.0F, -5.0F, -5.975F, 10.0F, 10.0F, 2.0F, new CubeDeformation(0.0F))
        .texOffs(376, 217).addBox(-5.0F, -5.0F, 3.0F, 10.0F, 10.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.075F, -1.1168F, -71.188F, 1.5708F, -0.7854F, 0.0F));

        PartDefinition cube_r4 = Tube.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(176, 313).addBox(-12.425F, -47.375F, -58.125F, 26.0F, 10.0F, 10.0F, new CubeDeformation(0.0F))
        .texOffs(328, 39).addBox(-3.5F, -37.9F, -56.65F, 8.0F, 35.0F, 8.0F, new CubeDeformation(0.0F))
        .texOffs(160, 92).addBox(-3.525F, -3.9F, -50.9F, 8.0F, 8.0F, 52.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.5F, -53.2418F, -29.813F, 1.5708F, 0.0F, 0.0F));

        PartDefinition cube_r5 = Tube.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(310, 336).addBox(-4.0F, -50.9F, -58.15F, 8.0F, 10.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.05F, -53.2418F, -30.888F, 1.5708F, 0.0F, 0.0F));

        PartDefinition cube_r6 = Tube.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(108, 379).addBox(-4.0F, -5.1447F, -2.1464F, 8.0F, 8.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.05F, -3.03F, -31.2858F, -2.0071F, 0.0F, 0.0F));

        PartDefinition cube_r7 = Tube.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(376, 277).addBox(-4.0F, -5.2953F, -1.4159F, 8.0F, 8.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.075F, -3.03F, -31.2858F, -2.618F, 0.0F, 0.0F));

        PartDefinition cube_r8 = Tube.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(392, 158).addBox(-4.0F, -5.795F, 1.1108F, 8.0F, 8.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.025F, -3.03F, -31.3108F, -2.8798F, 0.0F, 0.0F));

        PartDefinition cube_r9 = Tube.addOrReplaceChild("cube_r9", CubeListBuilder.create().texOffs(298, 375).addBox(-4.0F, -3.575F, -2.125F, 8.0F, 8.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.05F, -55.1578F, -29.7291F, 1.309F, 0.0F, 0.0F));

        PartDefinition cube_r10 = Tube.addOrReplaceChild("cube_r10", CubeListBuilder.create().texOffs(376, 301).addBox(-4.0F, -3.875F, -1.975F, 8.0F, 8.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.025F, -57.8898F, -28.1936F, 0.8727F, 0.0F, 0.0F));

        PartDefinition cube_r11 = Tube.addOrReplaceChild("cube_r11", CubeListBuilder.create().texOffs(376, 193).addBox(-4.0F, -4.0F, -4.0F, 8.0F, 8.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.05F, -59.125F, -24.95F, 0.2618F, 0.0F, 0.0F));

        PartDefinition Platform = LargeMain.addOrReplaceChild("Platform", CubeListBuilder.create().texOffs(160, 0).addBox(-21.0F, -4.0F, -21.0F, 42.0F, 4.0F, 42.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition cube_r12 = Platform.addOrReplaceChild("cube_r12", CubeListBuilder.create().texOffs(372, 396).addBox(-2.25F, -0.5F, -2.75F, 5.0F, 1.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-17.5F, -4.5F, -17.0F, 0.0F, -0.6981F, 0.0F));

        PartDefinition cube_r13 = Platform.addOrReplaceChild("cube_r13", CubeListBuilder.create().texOffs(392, 396).addBox(-2.75F, -0.5F, -2.75F, 5.0F, 1.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(17.5F, -4.5F, -17.0F, 0.0F, 0.6981F, 0.0F));

        PartDefinition cube_r14 = Platform.addOrReplaceChild("cube_r14", CubeListBuilder.create().texOffs(334, 396).addBox(-2.75F, -0.5F, -2.25F, 5.0F, 1.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(17.5F, -4.5F, 17.0F, 0.0F, -0.6981F, 0.0F));

        PartDefinition cube_r15 = Platform.addOrReplaceChild("cube_r15", CubeListBuilder.create().texOffs(396, 50).addBox(-2.25F, -0.5F, -2.25F, 5.0F, 1.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-17.5F, -4.5F, 17.0F, 0.0F, 0.6981F, 0.0F));

        PartDefinition Sphere1 = LargeMain.addOrReplaceChild("Sphere1", CubeListBuilder.create().texOffs(272, 194).addBox(-32.0F, 12.0F, 6.0F, 26.0F, 4.0F, 26.0F, new CubeDeformation(0.0F))
        .texOffs(280, 92).addBox(-32.0F, -24.0F, 6.0F, 26.0F, 3.0F, 26.0F, new CubeDeformation(0.0F))
        .texOffs(296, 179).addBox(-31.0F, -25.0F, 32.0F, 24.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
        .texOffs(296, 183).addBox(-31.0F, -25.0F, 4.0F, 24.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(19.0F, -20.025F, -19.0F));

        PartDefinition cube_r16 = Sphere1.addOrReplaceChild("cube_r16", CubeListBuilder.create().texOffs(300, 394).addBox(-2.5F, -3.0F, -2.5F, 4.0F, 6.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.5F, -24.0F, 6.5F, 0.0F, 0.7854F, 0.0F));

        PartDefinition cube_r17 = Sphere1.addOrReplaceChild("cube_r17", CubeListBuilder.create().texOffs(172, 395).addBox(-1.5F, -3.0F, -2.5F, 4.0F, 6.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-31.5F, -24.0F, 6.5F, 0.0F, -0.7854F, 0.0F));

        PartDefinition cube_r18 = Sphere1.addOrReplaceChild("cube_r18", CubeListBuilder.create().texOffs(282, 394).addBox(-1.5F, -3.0F, -2.5F, 4.0F, 6.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-31.5F, -24.0F, 31.5F, 0.0F, 0.7854F, 0.0F));

        PartDefinition cube_r19 = Sphere1.addOrReplaceChild("cube_r19", CubeListBuilder.create().texOffs(206, 394).addBox(-2.5F, -3.0F, -2.5F, 4.0F, 6.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.5F, -24.0F, 31.5F, 0.0F, -0.7854F, 0.0F));

        PartDefinition cube_r20 = Sphere1.addOrReplaceChild("cube_r20", CubeListBuilder.create().texOffs(296, 187).addBox(-12.0F, -1.0F, 13.0F, 24.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
        .texOffs(176, 308).addBox(-12.0F, -1.0F, -15.0F, 24.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-19.0F, -24.0F, 19.0F, 0.0F, 1.5708F, 0.0F));

        PartDefinition Sphere2 = LargeMain.addOrReplaceChild("Sphere2", CubeListBuilder.create().texOffs(272, 224).addBox(-32.0F, 12.0F, 6.0F, 26.0F, 4.0F, 26.0F, new CubeDeformation(0.0F))
        .texOffs(136, 194).addBox(-36.0F, 5.0F, 2.0F, 34.0F, 8.0F, 34.0F, new CubeDeformation(0.0F))
        .texOffs(0, 57).addBox(-39.0F, -12.0F, -1.0F, 40.0F, 17.0F, 40.0F, new CubeDeformation(0.0F))
        .texOffs(0, 213).addBox(-36.0F, -20.0F, 2.0F, 34.0F, 8.0F, 34.0F, new CubeDeformation(0.0F))
        .texOffs(0, 171).addBox(-36.0F, 19.0F, 2.0F, 34.0F, 8.0F, 34.0F, new CubeDeformation(0.0F))
        .texOffs(272, 254).addBox(-32.0F, -24.0F, 6.0F, 26.0F, 4.0F, 26.0F, new CubeDeformation(0.0F)), PartPose.offset(19.0F, -60.025F, -19.0F));

        PartDefinition cube_r21 = Sphere2.addOrReplaceChild("cube_r21", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-20.0F, -8.5F, -20.0F, 40.0F, 17.0F, 40.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-19.0F, 35.5F, 19.0F, 0.0F, 3.1416F, 0.0F));

        PartDefinition cube_r22 = Sphere2.addOrReplaceChild("cube_r22", CubeListBuilder.create().texOffs(160, 152).addBox(-17.0F, 8.5F, -17.0F, 34.0F, 8.0F, 34.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-19.0F, 35.5F, 19.0F, 0.0F, 1.5708F, 0.0F));

        PartDefinition Top = LargeMain.addOrReplaceChild("Top", CubeListBuilder.create().texOffs(0, 320).addBox(-27.5F, -18.0F, 11.0F, 16.0F, 8.0F, 15.0F, new CubeDeformation(0.0F))
        .texOffs(0, 297).addBox(-30.5F, -10.0F, 7.5F, 22.0F, 1.0F, 22.0F, new CubeDeformation(0.0F))
        .texOffs(240, 284).addBox(-31.5F, -9.0F, 6.5F, 24.0F, 5.0F, 24.0F, new CubeDeformation(0.0F)), PartPose.offset(19.5F, -80.025F, -18.5F));

        PartDefinition Pillar4 = LargeMain.addOrReplaceChild("Pillar4", CubeListBuilder.create().texOffs(346, 336).addBox(-23.0F, -77.0F, 32.0F, 8.0F, 9.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offset(19.0F, -4.025F, -19.0F));

        PartDefinition Pillar3 = LargeMain.addOrReplaceChild("Pillar3", CubeListBuilder.create().texOffs(0, 363).addBox(-15.8017F, -0.1F, -41.2358F, 12.0F, 4.0F, 2.0F, new CubeDeformation(0.0F))
        .texOffs(156, 381).addBox(-34.3017F, -0.1F, -41.2358F, 12.0F, 4.0F, 2.0F, new CubeDeformation(0.0F))
        .texOffs(214, 353).addBox(-23.0F, -77.0F, -42.0F, 8.0F, 9.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offset(19.0F, -4.025F, 19.0F));

        PartDefinition cube_r23 = Pillar3.addOrReplaceChild("cube_r23", CubeListBuilder.create().texOffs(0, 363).addBox(3.25F, 28.45F, -0.2358F, 12.0F, 4.0F, 2.0F, new CubeDeformation(0.0F))
        .texOffs(156, 381).addBox(-15.25F, 28.45F, -0.2358F, 12.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-19.0517F, -28.55F, 2.5F, 3.1416F, 0.0F, -3.1416F));

        PartDefinition cube_r24 = Pillar3.addOrReplaceChild("cube_r24", CubeListBuilder.create().texOffs(358, 387).addBox(10.0121F, 24.5714F, -0.2108F, 11.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-19.0517F, -28.55F, 2.5F, -3.1416F, 0.0F, 2.9234F));

        PartDefinition cube_r25 = Pillar3.addOrReplaceChild("cube_r25", CubeListBuilder.create().texOffs(388, 179).addBox(-21.0121F, 24.5714F, -0.2108F, 11.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-19.0517F, -28.55F, 2.5F, 3.1416F, 0.0F, -2.9234F));

        PartDefinition cube_r26 = Pillar3.addOrReplaceChild("cube_r26", CubeListBuilder.create().texOffs(388, 179).addBox(-21.0121F, 24.5714F, -0.2108F, 11.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-19.0517F, -28.55F, -41.0F, 0.0F, 0.0F, -0.2182F));

        PartDefinition cube_r27 = Pillar3.addOrReplaceChild("cube_r27", CubeListBuilder.create().texOffs(358, 387).addBox(10.0121F, 24.5714F, -0.2108F, 11.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-19.0517F, -28.55F, -41.0F, 0.0F, 0.0F, 0.2182F));

        PartDefinition cube_r28 = Pillar3.addOrReplaceChild("cube_r28", CubeListBuilder.create().texOffs(358, 387).addBox(3.5461F, -4.595F, -0.9875F, 11.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-40.025F, 1.3245F, -19.0F, 1.5708F, 1.3526F, 1.5708F));

        PartDefinition cube_r29 = Pillar3.addOrReplaceChild("cube_r29", CubeListBuilder.create().texOffs(0, 363).addBox(3.25F, -1.4245F, -1.0125F, 12.0F, 4.0F, 2.0F, new CubeDeformation(0.0F))
        .texOffs(156, 381).addBox(-15.25F, -1.4245F, -1.0125F, 12.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-40.025F, 1.3245F, -19.0F, 0.0F, 1.5708F, 0.0F));

        PartDefinition cube_r30 = Pillar3.addOrReplaceChild("cube_r30", CubeListBuilder.create().texOffs(388, 179).addBox(-14.5461F, -4.595F, -0.9875F, 11.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-40.025F, 1.3245F, -19.0F, -1.5708F, 1.3526F, -1.5708F));

        PartDefinition cube_r31 = Pillar3.addOrReplaceChild("cube_r31", CubeListBuilder.create().texOffs(358, 387).addBox(11.1484F, 29.6969F, -1.9625F, 11.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, -33.8F, -19.0F, -1.5708F, -1.3526F, 1.5708F));

        PartDefinition cube_r32 = Pillar3.addOrReplaceChild("cube_r32", CubeListBuilder.create().texOffs(0, 363).addBox(3.25F, 33.7F, -1.9875F, 12.0F, 4.0F, 2.0F, new CubeDeformation(0.0F))
        .texOffs(156, 381).addBox(-15.25F, 33.7F, -1.9875F, 12.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, -33.8F, -19.0F, 0.0F, -1.5708F, 0.0F));

        PartDefinition cube_r33 = Pillar3.addOrReplaceChild("cube_r33", CubeListBuilder.create().texOffs(388, 179).addBox(-22.1484F, 29.6969F, -1.9625F, 11.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, -33.8F, -19.0F, 1.5708F, -1.3526F, -1.5708F));

        PartDefinition Pillar2 = LargeMain.addOrReplaceChild("Pillar2", CubeListBuilder.create(), PartPose.offsetAndRotation(21.0F, -45.025F, 0.0F, 0.0F, -1.5708F, 0.0F));

        PartDefinition Pillar1 = LargeMain.addOrReplaceChild("Pillar1", CubeListBuilder.create(), PartPose.offsetAndRotation(-21.0F, -45.025F, 0.0F, 0.0F, 1.5708F, 0.0F));

        PartDefinition cube_r34 = Pillar1.addOrReplaceChild("cube_r34", CubeListBuilder.create().texOffs(400, 272).addBox(3.0F, -12.2F, -42.0F, 2.0F, 7.0F, 2.0F, new CubeDeformation(0.0F))
        .texOffs(384, 137).addBox(-4.0F, 12.8F, -44.0F, 8.0F, 8.0F, 3.0F, new CubeDeformation(0.0F))
        .texOffs(264, 399).addBox(3.0F, -3.2F, -42.0F, 2.0F, 12.0F, 2.0F, new CubeDeformation(0.0F))
        .texOffs(396, 46).addBox(-5.0F, -5.2F, -42.0F, 10.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
        .texOffs(400, 263).addBox(-5.0F, -12.2F, -42.0F, 2.0F, 7.0F, 2.0F, new CubeDeformation(0.0F))
        .texOffs(94, 367).addBox(-5.0F, -37.2F, -42.0F, 10.0F, 3.0F, 9.0F, new CubeDeformation(0.0F))
        .texOffs(384, 93).addBox(-4.0F, -29.2F, -44.0F, 8.0F, 8.0F, 3.0F, new CubeDeformation(0.0F))
        .texOffs(334, 374).addBox(-5.0F, -34.2F, -42.0F, 10.0F, 20.0F, 2.0F, new CubeDeformation(0.0F))
        .texOffs(46, 396).addBox(-5.0F, -14.2F, -42.0F, 10.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
        .texOffs(256, 399).addBox(-5.0F, -3.2F, -42.0F, 2.0F, 12.0F, 2.0F, new CubeDeformation(0.0F))
        .texOffs(274, 353).addBox(-5.0F, 8.8F, -42.0F, 10.0F, 29.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 7.2F, 1.0F, -3.1416F, 0.0F, 3.1416F));

        PartDefinition Pillar5 = LargeMain.addOrReplaceChild("Pillar5", CubeListBuilder.create(), PartPose.offsetAndRotation(-21.0454F, -44.5705F, 0.0F, 0.0F, -1.5708F, 0.0F));

        PartDefinition cube_r35 = Pillar5.addOrReplaceChild("cube_r35", CubeListBuilder.create().texOffs(384, 93).addBox(-4.0F, -34.45F, 10.2108F, 8.0F, 8.0F, 3.0F, new CubeDeformation(0.0F))
        .texOffs(384, 137).addBox(-4.0F, 7.55F, 10.2108F, 8.0F, 8.0F, 3.0F, new CubeDeformation(0.0F))
        .texOffs(274, 353).addBox(-5.0F, 3.55F, 12.2108F, 10.0F, 29.0F, 2.0F, new CubeDeformation(0.0F))
        .texOffs(256, 399).addBox(-5.0F, -8.45F, 12.2108F, 2.0F, 12.0F, 2.0F, new CubeDeformation(0.0F))
        .texOffs(396, 46).addBox(-5.0F, -10.45F, 12.2108F, 10.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
        .texOffs(264, 399).addBox(3.0F, -8.45F, 12.2108F, 2.0F, 12.0F, 2.0F, new CubeDeformation(0.0F))
        .texOffs(400, 272).addBox(3.0F, -17.45F, 12.2108F, 2.0F, 7.0F, 2.0F, new CubeDeformation(0.0F))
        .texOffs(400, 263).addBox(-5.0F, -17.45F, 12.2108F, 2.0F, 7.0F, 2.0F, new CubeDeformation(0.0F))
        .texOffs(46, 396).addBox(-5.0F, -19.45F, 12.2108F, 10.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
        .texOffs(334, 374).addBox(-5.0F, -39.45F, 12.2108F, 10.0F, 20.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(34.0F, 11.9954F, -20.9937F, 0.0F, -1.5708F, 0.0F));

        PartDefinition cube_r36 = Pillar5.addOrReplaceChild("cube_r36", CubeListBuilder.create().texOffs(384, 93).addBox(17.75F, -34.45F, 7.7108F, 8.0F, 8.0F, 3.0F, new CubeDeformation(0.0F))
        .texOffs(46, 396).addBox(16.75F, -19.45F, 9.7108F, 10.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
        .texOffs(334, 374).addBox(16.75F, -39.45F, 9.7108F, 10.0F, 20.0F, 2.0F, new CubeDeformation(0.0F))
        .texOffs(400, 272).addBox(24.75F, -17.45F, 9.7108F, 2.0F, 7.0F, 2.0F, new CubeDeformation(0.0F))
        .texOffs(400, 263).addBox(16.75F, -17.45F, 9.7108F, 2.0F, 7.0F, 2.0F, new CubeDeformation(0.0F))
        .texOffs(396, 46).addBox(16.75F, -10.45F, 9.7108F, 10.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
        .texOffs(256, 399).addBox(16.75F, -8.45F, 9.7108F, 2.0F, 12.0F, 2.0F, new CubeDeformation(0.0F))
        .texOffs(264, 399).addBox(24.75F, -8.45F, 9.7108F, 2.0F, 12.0F, 2.0F, new CubeDeformation(0.0F))
        .texOffs(384, 137).addBox(17.75F, 7.55F, 7.7108F, 8.0F, 8.0F, 3.0F, new CubeDeformation(0.0F))
        .texOffs(274, 353).addBox(16.75F, 3.55F, 9.7108F, 10.0F, 29.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-32.0F, 11.9954F, 0.7562F, 0.0F, 1.5708F, 0.0F));

        PartDefinition cube_r37 = Pillar5.addOrReplaceChild("cube_r37", CubeListBuilder.create().texOffs(400, 272).addBox(3.0F, -12.2F, -42.0F, 2.0F, 7.0F, 2.0F, new CubeDeformation(0.0F))
        .texOffs(384, 137).addBox(-4.0F, 12.8F, -44.0F, 8.0F, 8.0F, 3.0F, new CubeDeformation(0.0F))
        .texOffs(264, 399).addBox(3.0F, -3.2F, -42.0F, 2.0F, 12.0F, 2.0F, new CubeDeformation(0.0F))
        .texOffs(396, 46).addBox(-5.0F, -5.2F, -42.0F, 10.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
        .texOffs(400, 263).addBox(-5.0F, -12.2F, -42.0F, 2.0F, 7.0F, 2.0F, new CubeDeformation(0.0F))
        .texOffs(94, 367).addBox(-5.0F, -37.2F, -42.0F, 10.0F, 3.0F, 9.0F, new CubeDeformation(0.0F))
        .texOffs(384, 93).addBox(-4.0F, -29.2F, -44.0F, 8.0F, 8.0F, 3.0F, new CubeDeformation(0.0F))
        .texOffs(334, 374).addBox(-5.0F, -34.2F, -42.0F, 10.0F, 20.0F, 2.0F, new CubeDeformation(0.0F))
        .texOffs(46, 396).addBox(-5.0F, -14.2F, -42.0F, 10.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
        .texOffs(256, 399).addBox(-5.0F, -3.2F, -42.0F, 2.0F, 12.0F, 2.0F, new CubeDeformation(0.0F))
        .texOffs(274, 353).addBox(-5.0F, 8.8F, -42.0F, 10.0F, 29.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 6.7454F, -40.9545F, -3.1416F, 0.0F, 3.1416F));

        return LayerDefinition.create(meshdefinition, 512, 512);
    }
}
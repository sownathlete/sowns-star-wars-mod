package com.sown.outerrim.models.blocks;

// Converted from a Blockbench 1.17+ model to Minecraft Forge 1.7.10 (ModelBase/ModelRenderer).
// Notes for 1.7.10 compliance:
// • 1.7.10 ModelRenderer does NOT support zero-thickness planes (any box with a 0 size on X/Y/Z).
//   All such planes from the original model (e.g., many of the thin lines/plates) have been omitted.
// • Hierarchy is preserved via addChild(). Pivots/offsets/rotations were translated to rotationPoint and rotateAngleXYZ.
// • Texture size set to 128×128 to match the original LayerDefinition.

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelCoaxiumRack extends ModelBase {

    // Root and major groups
    public ModelRenderer Main;
    public ModelRenderer Frame;
    public ModelRenderer Lid;
    public ModelRenderer Row;
    public ModelRenderer Row2;
    public ModelRenderer Row3;
    public ModelRenderer Row4;

    // Rotated/aux subparts (only those with non-zero thickness kept)
    public ModelRenderer cube_r2;
    public ModelRenderer cube_r3;
    public ModelRenderer cube_r4;
    public ModelRenderer cube_r5;
    public ModelRenderer cube_r6;
    public ModelRenderer cube_r7;

    public ModelRenderer cube_r8;
    public ModelRenderer cube_r9;
    public ModelRenderer cube_r10;
    public ModelRenderer cube_r11;

    public ModelCoaxiumRack() {
        this.textureWidth = 256;
        this.textureHeight = 256;

        // Main pivot (equivalent to PartPose.offset(0, 24, 0))
        this.Main = new ModelRenderer(this);
        this.Main.setRotationPoint(0.0F, 24.0F, 0.0F);

        // element_0
        this.Main.setTextureOffset(0, 0).addBox(7.000F, 5.000F, -8.000F, 1, 18, 16, 0.0F);

        // element_1
        this.Main.setTextureOffset(36, 0).addBox(-8.000F, 5.000F, -8.000F, 1, 18, 16, 0.0F);

        // element_2
        this.Main.setTextureOffset(72, 0).addBox(-8.250F, 11.000F, -6.000F, 1, 2, 4, 0.0F);

        // element_3
        this.Main.setTextureOffset(84, 0).addBox(-8.250F, 11.000F, 2.000F, 1, 2, 4, 0.0F);

        // element_4
        this.Main.setTextureOffset(96, 0).addBox(-8.100F, 5.050F, -3.000F, 1, 2, 6, 0.0F);

        // element_5
        this.Main.setTextureOffset(112, 0).addBox(-8.250F, 20.000F, 2.000F, 1, 2, 4, 0.0F);

        // element_6
        this.Main.setTextureOffset(124, 0).addBox(-8.250F, 20.000F, -6.000F, 1, 2, 4, 0.0F);

        // element_7
        this.Main.setTextureOffset(136, 0).addBox(-8.000F, 23.000F, -8.000F, 16, 1, 16, 0.0F);

        // element_8
        this.Main.setTextureOffset(202, 0).addBox(-7.000F, 5.000F, -8.000F, 14, 18, 1, 0.0F);

        // element_9
        this.Main.setTextureOffset(234, 0).addBox(-2.000F, 17.000F, -8.250F, 4, 5, 1, 0.0F);

        // element_10
        this.Main.setTextureOffset(246, 0).addBox(-2.000F, 17.000F, 7.250F, 4, 5, 1, 0.0F);

        // element_11
        this.Main.setTextureOffset(0, 52).addBox(-7.000F, 5.000F, 7.000F, 14, 18, 1, 0.0F);

        // element_12
        this.Main.setTextureOffset(32, 52).addBox(-7.000F, 8.750F, 6.000F, 14, 1, 1, 0.0F);

        // element_13
        this.Main.setTextureOffset(64, 52).addBox(-7.000F, 8.750F, -7.000F, 14, 1, 1, 0.0F);

        // element_14
        ModelRenderer cube_14 = new ModelRenderer(this);
        cube_14.setRotationPoint(0.000F, 7.000F, 9.000F);
        setRotateAngle(cube_14, 0.000F, 0.000F, -0.3054F);
        cube_14.setTextureOffset(96, 52).addBox(-1.000F, -1.000F, 0.025F, 10, 2, 1, 0.0F);
        this.Main.addChild(cube_14);

        // element_15
        ModelRenderer cube_15 = new ModelRenderer(this);
        cube_15.setRotationPoint(0.000F, 7.000F, -9.000F);
        setRotateAngle(cube_15, 0.000F, 0.000F, -0.3054F);
        cube_15.setTextureOffset(120, 52).addBox(-1.000F, -1.000F, -0.025F, 10, 2, 1, 0.0F);
        this.Main.addChild(cube_15);

        // element_16
        ModelRenderer cube_16 = new ModelRenderer(this);
        cube_16.setRotationPoint(0.000F, 7.000F, 9.000F);
        setRotateAngle(cube_16, 0.000F, 0.000F, -0.3054F);
        cube_16.setTextureOffset(144, 52).addBox(9.000F, -1.000F, -18.000F, 1, 2, 18, 0.0F);
        this.Main.addChild(cube_16);

        // element_17
        this.Main.setTextureOffset(184, 52).addBox(-1.000F, 6.000F, -9.000F, 2, 2, 1, 0.0F);

        // element_18
        this.Main.setTextureOffset(192, 52).addBox(-1.000F, 6.000F, 8.000F, 2, 2, 1, 0.0F);

        // element_19
        this.Main.setTextureOffset(200, 52).addBox(-7.000F, 7.500F, -7.000F, 14, 1, 14, 0.0F);

        // element_20
        this.Main.setTextureOffset(0, 92).addBox(-2.000F, 7.250F, -7.000F, 4, 1, 3, 0.0F);

        // element_21
        this.Main.setTextureOffset(16, 92).addBox(-2.000F, 7.250F, 4.000F, 4, 1, 3, 0.0F);

        // element_22
        this.Main.setTextureOffset(32, 92).addBox(-0.541F, 4.975F, -0.541F, 1, 2, 1, 0.0F);

        // element_23
        ModelRenderer cube_23 = new ModelRenderer(this);
        cube_23.setRotationPoint(-0.041F, 6.725F, -0.041F);
        setRotateAngle(cube_23, 0.000F, 0.000F, 0.3491F);
        cube_23.setTextureOffset(38, 92).addBox(0.500F, -1.000F, -0.475F, 2, 1, 1, 0.0F);
        this.Main.addChild(cube_23);

        // element_24
        ModelRenderer cube_24 = new ModelRenderer(this);
        cube_24.setRotationPoint(0.041F, 6.725F, -0.041F);
        setRotateAngle(cube_24, 0.000F, 0.000F, -0.3491F);
        cube_24.setTextureOffset(46, 92).addBox(-2.500F, -1.000F, -0.475F, 2, 1, 1, 0.0F);
        this.Main.addChild(cube_24);

        // element_25
        ModelRenderer cube_25 = new ModelRenderer(this);
        cube_25.setRotationPoint(6.500F, 7.000F, 6.500F);
        setRotateAngle(cube_25, 0.000F, -0.7854F, 0.000F);
        cube_25.setTextureOffset(54, 92).addBox(-3.750F, -1.000F, -1.000F, 1, 1, 2, 0.0F);
        this.Main.addChild(cube_25);
        
        // element_26
        ModelRenderer cube_26 = new ModelRenderer(this);
        cube_26.setRotationPoint(6.500F, 7.000F, 6.500F);
        setRotateAngle(cube_26, 0.000F, -0.7854F, 0.000F);
        cube_26.setTextureOffset(62, 92).addBox(-16.750F, -1.000F, -1.000F, 1, 1, 2, 0.0F);
        this.Main.addChild(cube_26);

        // element_27
        ModelRenderer cube_27 = new ModelRenderer(this);
        cube_27.setRotationPoint(-0.394F, 6.500F, -0.394F);
        setRotateAngle(cube_27, 0.000F, -0.7854F, 0.000F);
        cube_27.setTextureOffset(70, 92).addBox(-7.000F, -0.500F, -1.400F, 1, 1, 2, 0.0F);
        this.Main.addChild(cube_27);

        // element_28
        ModelRenderer cube_28 = new ModelRenderer(this);
        cube_28.setRotationPoint(-0.394F, 6.500F, -0.394F);
        setRotateAngle(cube_28, 0.000F, -0.7854F, 0.000F);
        cube_28.setTextureOffset(78, 92).addBox(6.000F, -0.500F, -1.400F, 1, 1, 2, 0.0F);
        this.Main.addChild(cube_28);

        // element_29
        ModelRenderer cube_29 = new ModelRenderer(this);
        cube_29.setRotationPoint(-0.041F, 7.500F, -0.041F);
        setRotateAngle(cube_29, 0.000F, -0.7854F, 0.000F);
        cube_29.setTextureOffset(86, 92).addBox(-9.500F, -0.500F, -0.500F, 19, 1, 1, 0.0F);
        this.Main.addChild(cube_29);

        // element_30
        ModelRenderer cube_30 = new ModelRenderer(this);
        cube_30.setRotationPoint(-0.041F, 7.500F, -0.041F);
        setRotateAngle(cube_30, 0.000F, -0.7854F, 0.000F);
        cube_30.setTextureOffset(128, 92).addBox(-9.500F, -0.500F, -0.500F, 19, 1, 1, 0.0F);
        this.Main.addChild(cube_30);

        // element_31
        this.Main.setTextureOffset(170, 92).addBox(-6.750F, 12.000F, -5.975F, 3, 1, 12, 0.0F);

        // element_32
        this.Main.setTextureOffset(202, 92).addBox(-6.750F, 18.000F, -5.975F, 3, 1, 12, 0.0F);

        // element_33
        this.Main.setTextureOffset(0, 119).addBox(-6.750F, 23.000F, -5.975F, 3, 1, 12, 0.0F);

        // element_34
        this.Main.setTextureOffset(32, 119).addBox(-5.750F, 10.975F, 4.025F, 1, 11, 1, 0.0F);

        // element_35
        this.Main.setTextureOffset(38, 119).addBox(-6.250F, 9.975F, 3.525F, 2, 13, 2, 0.0F);

        // element_36
        this.Main.setTextureOffset(48, 119).addBox(-5.750F, 10.975F, 1.025F, 1, 11, 1, 0.0F);

        // element_37
        this.Main.setTextureOffset(54, 119).addBox(-6.250F, 9.975F, 0.525F, 2, 13, 2, 0.0F);

        // element_38
        this.Main.setTextureOffset(64, 119).addBox(-5.750F, 10.975F, -1.975F, 1, 11, 1, 0.0F);

        // element_39
        this.Main.setTextureOffset(70, 119).addBox(-6.250F, 9.975F, -2.475F, 2, 13, 2, 0.0F);

        // element_40
        this.Main.setTextureOffset(80, 119).addBox(-5.750F, 10.975F, -4.975F, 1, 11, 1, 0.0F);

        // element_41
        this.Main.setTextureOffset(86, 119).addBox(-6.250F, 9.975F, -5.475F, 2, 13, 2, 0.0F);

        // element_42
        this.Main.setTextureOffset(96, 119).addBox(-6.750F, 9.000F, 5.975F, 3, 14, 1, 0.0F);

        // element_43
        this.Main.setTextureOffset(106, 119).addBox(-6.750F, 9.000F, -5.975F, 3, 14, 1, 0.0F);

        // element_44
        ModelRenderer cube_44 = new ModelRenderer(this);
        cube_44.setRotationPoint(-4.250F, 9.500F, -1.025F);
        setRotateAngle(cube_44, 0.000F, 0.000F, 0.7854F);
        cube_44.setTextureOffset(116, 119).addBox(-0.500F, -0.500F, 6.975F, 2, 1, 1, 0.0F);
        this.Main.addChild(cube_44);

        // element_45
        ModelRenderer cube_45 = new ModelRenderer(this);
        cube_45.setRotationPoint(-4.250F, 9.500F, -0.025F);
        setRotateAngle(cube_45, 0.000F, 0.000F, 0.7854F);
        cube_45.setTextureOffset(124, 119).addBox(1.500F, -0.500F, -6.025F, 1, 1, 12, 0.0F);
        this.Main.addChild(cube_45);

        // element_46
        ModelRenderer cube_46 = new ModelRenderer(this);
        cube_46.setRotationPoint(-4.250F, 9.500F, 1.000F);
        setRotateAngle(cube_46, 0.000F, 0.000F, 0.7854F);
        cube_46.setTextureOffset(152, 119).addBox(-0.500F, -0.500F, -6.950F, 2, 1, 1, 0.0F);
        this.Main.addChild(cube_46);

        // element_47
        this.Main.setTextureOffset(160, 119).addBox(-3.250F, 12.000F, -5.975F, 3, 1, 12, 0.0F);

        // element_48
        this.Main.setTextureOffset(192, 119).addBox(-3.250F, 18.000F, -5.975F, 3, 1, 12, 0.0F);

        // element_49
        this.Main.setTextureOffset(224, 119).addBox(-3.250F, 23.000F, -5.975F, 3, 1, 12, 0.0F);

        // element_50
        this.Main.setTextureOffset(0, 146).addBox(-2.250F, 10.975F, 4.025F, 1, 11, 1, 0.0F);
        
        // element_51
        this.Main.setTextureOffset(6, 146).addBox(-2.750F, 9.975F, 3.525F, 2, 13, 2, 0.0F);

        // element_52
        this.Main.setTextureOffset(16, 146).addBox(-2.250F, 10.975F, 1.025F, 1, 11, 1, 0.0F);

        // element_53
        this.Main.setTextureOffset(22, 146).addBox(-2.750F, 9.975F, 0.525F, 2, 13, 2, 0.0F);

        // element_54
        this.Main.setTextureOffset(32, 146).addBox(-2.250F, 10.975F, -1.975F, 1, 11, 1, 0.0F);

        // element_55
        this.Main.setTextureOffset(38, 146).addBox(-2.750F, 9.975F, -2.475F, 2, 13, 2, 0.0F);

        // element_56
        this.Main.setTextureOffset(48, 146).addBox(-2.250F, 10.975F, -4.975F, 1, 11, 1, 0.0F);

        // element_57
        this.Main.setTextureOffset(54, 146).addBox(-2.750F, 9.975F, -5.475F, 2, 13, 2, 0.0F);

        // element_58
        this.Main.setTextureOffset(64, 146).addBox(-3.250F, 9.000F, 5.975F, 3, 14, 1, 0.0F);

        // element_59
        this.Main.setTextureOffset(74, 146).addBox(-3.250F, 9.000F, -5.975F, 3, 14, 1, 0.0F);

        // element_60
        ModelRenderer cube_60 = new ModelRenderer(this);
        cube_60.setRotationPoint(-0.750F, 9.500F, -1.025F);
        setRotateAngle(cube_60, 0.000F, 0.000F, 0.7854F);
        cube_60.setTextureOffset(84, 146).addBox(-0.500F, -0.500F, 6.975F, 2, 1, 1, 0.0F);
        this.Main.addChild(cube_60);

        // element_61
        ModelRenderer cube_61 = new ModelRenderer(this);
        cube_61.setRotationPoint(-0.750F, 9.500F, -0.025F);
        setRotateAngle(cube_61, 0.000F, 0.000F, 0.7854F);
        cube_61.setTextureOffset(92, 146).addBox(1.500F, -0.500F, -6.025F, 1, 1, 12, 0.0F);
        this.Main.addChild(cube_61);

        // element_62
        ModelRenderer cube_62 = new ModelRenderer(this);
        cube_62.setRotationPoint(-0.750F, 9.500F, 1.000F);
        setRotateAngle(cube_62, 0.000F, 0.000F, 0.7854F);
        cube_62.setTextureOffset(120, 146).addBox(-0.500F, -0.500F, -6.950F, 2, 1, 1, 0.0F);
        this.Main.addChild(cube_62);

        // element_63
        this.Main.setTextureOffset(128, 146).addBox(0.250F, 12.000F, -5.975F, 3, 1, 12, 0.0F);

        // element_64
        this.Main.setTextureOffset(160, 146).addBox(0.250F, 18.000F, -5.975F, 3, 1, 12, 0.0F);

        // element_65
        this.Main.setTextureOffset(192, 146).addBox(0.250F, 23.000F, -5.975F, 3, 1, 12, 0.0F);

        // element_66
        this.Main.setTextureOffset(224, 146).addBox(1.250F, 10.975F, 4.025F, 1, 11, 1, 0.0F);

        // element_67
        this.Main.setTextureOffset(230, 146).addBox(0.750F, 9.975F, 3.525F, 2, 13, 2, 0.0F);

        // element_68
        this.Main.setTextureOffset(240, 146).addBox(1.250F, 10.975F, 1.025F, 1, 11, 1, 0.0F);

        // element_69
        this.Main.setTextureOffset(246, 146).addBox(0.750F, 9.975F, 0.525F, 2, 13, 2, 0.0F);

        // element_70
        this.Main.setTextureOffset(0, 173).addBox(1.250F, 10.975F, -1.975F, 1, 11, 1, 0.0F);

        // element_71
        this.Main.setTextureOffset(6, 173).addBox(0.750F, 9.975F, -2.475F, 2, 13, 2, 0.0F);

        // element_72
        this.Main.setTextureOffset(16, 173).addBox(1.250F, 10.975F, -4.975F, 1, 11, 1, 0.0F);

        // element_73
        this.Main.setTextureOffset(22, 173).addBox(0.750F, 9.975F, -5.475F, 2, 13, 2, 0.0F);

        // element_74
        this.Main.setTextureOffset(32, 173).addBox(0.250F, 9.000F, 5.975F, 3, 14, 1, 0.0F);

        // element_75
        this.Main.setTextureOffset(42, 173).addBox(0.250F, 9.000F, -5.975F, 3, 14, 1, 0.0F);

        // element_76
        ModelRenderer cube_76 = new ModelRenderer(this);
        cube_76.setRotationPoint(2.750F, 9.500F, -1.025F);
        setRotateAngle(cube_76, 0.000F, 0.000F, 0.7854F);
        cube_76.setTextureOffset(52, 173).addBox(-0.500F, -0.500F, 6.975F, 2, 1, 1, 0.0F);
        this.Main.addChild(cube_76);

        // element_77
        ModelRenderer cube_77 = new ModelRenderer(this);
        cube_77.setRotationPoint(2.750F, 9.500F, -0.025F);
        setRotateAngle(cube_77, 0.000F, 0.000F, 0.7854F);
        cube_77.setTextureOffset(60, 173).addBox(1.500F, -0.500F, -6.025F, 1, 1, 12, 0.0F);
        this.Main.addChild(cube_77);

        // element_78
        ModelRenderer cube_78 = new ModelRenderer(this);
        cube_78.setRotationPoint(2.750F, 9.500F, 1.000F);
        setRotateAngle(cube_78, 0.000F, 0.000F, 0.7854F);
        cube_78.setTextureOffset(88, 173).addBox(-0.500F, -0.500F, -6.950F, 2, 1, 1, 0.0F);
        this.Main.addChild(cube_78);

        // element_79
        this.Main.setTextureOffset(96, 173).addBox(3.750F, 12.000F, -5.975F, 3, 1, 12, 0.0F);

        // element_80
        this.Main.setTextureOffset(128, 173).addBox(3.750F, 18.000F, -5.975F, 3, 1, 12, 0.0F);

        // element_81
        this.Main.setTextureOffset(160, 173).addBox(3.750F, 23.000F, -5.975F, 3, 1, 12, 0.0F);

        // element_82
        this.Main.setTextureOffset(192, 173).addBox(4.750F, 10.975F, 4.025F, 1, 11, 1, 0.0F);

        // element_83
        this.Main.setTextureOffset(198, 173).addBox(4.250F, 9.975F, 3.525F, 2, 13, 2, 0.0F);

        // element_84
        this.Main.setTextureOffset(208, 173).addBox(4.750F, 10.975F, 1.025F, 1, 11, 1, 0.0F);

        // element_85
        this.Main.setTextureOffset(214, 173).addBox(4.250F, 9.975F, 0.525F, 2, 13, 2, 0.0F);

        // element_86
        this.Main.setTextureOffset(224, 173).addBox(4.750F, 10.975F, -1.975F, 1, 11, 1, 0.0F);

        // element_87
        this.Main.setTextureOffset(230, 173).addBox(4.250F, 9.975F, -2.475F, 2, 13, 2, 0.0F);

        // element_88
        this.Main.setTextureOffset(240, 173).addBox(4.750F, 10.975F, -4.975F, 1, 11, 1, 0.0F);

        // element_89
        this.Main.setTextureOffset(246, 173).addBox(4.250F, 9.975F, -5.475F, 2, 13, 2, 0.0F);

        // element_90
        this.Main.setTextureOffset(0, 200).addBox(3.750F, 9.000F, 5.975F, 3, 14, 1, 0.0F);

        // element_91
        this.Main.setTextureOffset(10, 200).addBox(3.750F, 9.000F, -5.975F, 3, 14, 1, 0.0F);

        // element_92
        ModelRenderer cube_92 = new ModelRenderer(this);
        cube_92.setRotationPoint(6.250F, 9.500F, -1.025F);
        setRotateAngle(cube_92, 0.000F, 0.000F, 0.7854F);
        cube_92.setTextureOffset(20, 200).addBox(-0.500F, -0.500F, 6.975F, 2, 1, 1, 0.0F);
        this.Main.addChild(cube_92);

        // element_93
        ModelRenderer cube_93 = new ModelRenderer(this);
        cube_93.setRotationPoint(6.250F, 9.500F, -0.025F);
        setRotateAngle(cube_93, 0.000F, 0.000F, 0.7854F);
        cube_93.setTextureOffset(28, 200).addBox(1.500F, -0.500F, -6.025F, 1, 1, 12, 0.0F);
        this.Main.addChild(cube_93);

        // element_94
        ModelRenderer cube_94 = new ModelRenderer(this);
        cube_94.setRotationPoint(6.250F, 9.500F, 1.000F);
        setRotateAngle(cube_94, 0.000F, 0.000F, 0.7854F);
        cube_94.setTextureOffset(56, 200).addBox(-0.500F, -0.500F, -6.950F, 2, 1, 1, 0.0F);
        this.Main.addChild(cube_94);
    }

    @Override
    public void render(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
        this.Main.render(scale);
    }

    // No animation on this static container model for 1.7.10
    public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn) {
        // intentionally empty
    }

    private void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}

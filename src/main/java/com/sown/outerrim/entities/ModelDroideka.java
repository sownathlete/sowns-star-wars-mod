package com.sown.outerrim.entities;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * Droideka - sown
 * Created using Tabula 4.1.1
 */
public class ModelDroideka extends ModelBase {
    public ModelRenderer body1;
    public ModelRenderer body2;
    public ModelRenderer body3;
    public ModelRenderer body4;
    public ModelRenderer side_armor_1;
    public ModelRenderer side_armor_2;
    public ModelRenderer leg_left;
    public ModelRenderer leg_right;
    public ModelRenderer leg_back;
    public ModelRenderer back_2;
    public ModelRenderer side_armor_1_back;
    public ModelRenderer back_side_armor_2;
    public ModelRenderer back_c;
    public ModelRenderer back_b;
    public ModelRenderer back_a;
    public ModelRenderer arm_left;
    public ModelRenderer arm_right;
    public ModelRenderer arm_left_2;
    public ModelRenderer arm_left_3;
    public ModelRenderer arm_left_4;
    public ModelRenderer arm_left_5;
    public ModelRenderer arm_left_6;
    public ModelRenderer arm_right_2;
    public ModelRenderer arm_right_3;
    public ModelRenderer arm_right_4;
    public ModelRenderer arm_right_5;
    public ModelRenderer arm_right_6;
    public ModelRenderer head1;
    public ModelRenderer head2;
    public ModelRenderer head3;
    public ModelRenderer head4;

    public ModelDroideka() {
        this.textureWidth = 90;
        this.textureHeight = 90;
        this.body2 = new ModelRenderer(this, 20, 45);
        this.body2.setRotationPoint(6.0F, -2.0F, 0.0F);
        this.body2.addBox(0.0F, 0.0F, 0.0F, 2, 2, 8, 0.0F);
        this.arm_left_4 = new ModelRenderer(this, 58, 35);
        this.arm_left_4.setRotationPoint(0.0F, 14.0F, -11.0F);
        this.arm_left_4.addBox(2.1F, -2.0F, 0.0F, 2, 2, 13, 0.0F);
        this.back_c = new ModelRenderer(this, 16, 29);
        this.back_c.setRotationPoint(0.0F, 0.0F, -5.0F);
        this.back_c.addBox(0.0F, 0.0F, 0.0F, 6, 8, 5, 0.0F);
        this.leg_left = new ModelRenderer(this, 49, 50);
        this.leg_left.mirror = true;
        this.leg_left.setRotationPoint(8.0F, 0.0F, 0.0F);
        this.leg_left.addBox(0.0F, 0.0F, 0.0F, 12, 14, 0, 0.0F);
        this.setRotateAngle(leg_left, 0.0F, 0.5235987755982988F, 0.0F);
        this.arm_left = new ModelRenderer(this, 50, 34);
        this.arm_left.setRotationPoint(4.0F, 3.0F, 1.0F);
        this.arm_left.addBox(0.1F, 0.0F, 0.0F, 2, 14, 2, 0.0F);
        this.setRotateAngle(arm_left, 0.0F, 0.0F, -0.7853981633974483F);
        this.side_armor_2 = new ModelRenderer(this, 0, 77);
        this.side_armor_2.mirror = true;
        this.side_armor_2.setRotationPoint(0.0F, -3.0F, 8.0F);
        this.side_armor_2.addBox(-8.0F, 0.0F, 0.0F, 8, 6, 0, 0.0F);
        this.setRotateAngle(side_armor_2, 0.0F, -0.39269908169872414F, 0.0F);
        this.back_b = new ModelRenderer(this, 16, 60);
        this.back_b.setRotationPoint(3.0F, -4.0F, -2.0F);
        this.back_b.addBox(0.0F, 0.0F, 0.0F, 0, 10, 9, 0.0F);
        this.body3 = new ModelRenderer(this, 32, 61);
        this.body3.setRotationPoint(2.0F, -2.0F, 6.0F);
        this.body3.addBox(0.0F, 0.0F, 0.0F, 4, 2, 2, 0.0F);
        this.arm_right_2 = new ModelRenderer(this, 82, 80);
        this.arm_right_2.mirror = true;
        this.arm_right_2.setRotationPoint(0.0F, 2.0F, -1.0F);
        this.arm_right_2.addBox(-1.0F, 0.0F, 0.0F, 0, 5, 4, 0.0F);
        this.side_armor_1_back = new ModelRenderer(this, 0, 70);
        this.side_armor_1_back.setRotationPoint(0.0F, 0.0F, 0.1F);
        this.side_armor_1_back.addBox(0.0F, 0.0F, 0.0F, 8, 6, 0, 0.0F);
        this.arm_left_3 = new ModelRenderer(this, 77, 76);
        this.arm_left_3.setRotationPoint(1.0F, 2.0F, -2.0F);
        this.arm_left_3.addBox(0.1F, 0.0F, 0.0F, 0, 11, 2, 0.0F);
        this.side_armor_1 = new ModelRenderer(this, 0, 77);
        this.side_armor_1.setRotationPoint(8.0F, -3.0F, 8.0F);
        this.side_armor_1.addBox(0.0F, 0.0F, 0.0F, 8, 6, 0, 0.0F);
        this.setRotateAngle(side_armor_1, 0.0F, 0.39269908169872414F, 0.0F);
        this.arm_right = new ModelRenderer(this, 50, 34);
        this.arm_right.mirror = true;
        this.arm_right.setRotationPoint(1.0F, 2.3F, 1.0F);
        this.arm_right.addBox(-0.9F, 0.0F, 0.0F, 2, 14, 2, 0.0F);
        this.setRotateAngle(arm_right, 0.0F, 0.0F, 0.7853981633974483F);
        this.arm_left_5 = new ModelRenderer(this, 60, 82);
        this.arm_left_5.mirror = true;
        this.arm_left_5.setRotationPoint(0.0F, 13.0F, -7.0F);
        this.arm_left_5.addBox(0.1F, 0.0F, 0.0F, 2, 0, 9, 0.0F);
        this.head3 = new ModelRenderer(this, 29, 0);
        this.head3.mirror = true;
        this.head3.setRotationPoint(-4.5F, 0.0F, -5.0F);
        this.head3.addBox(0.0F, 0.0F, 0.0F, 2, 0, 9, 0.0F);
        this.body4 = new ModelRenderer(this, 0, 45);
        this.body4.setRotationPoint(0.0F, -2.0F, 0.0F);
        this.body4.addBox(0.0F, 0.0F, 0.0F, 2, 2, 8, 0.0F);
        this.arm_right_5 = new ModelRenderer(this, 60, 82);
        this.arm_right_5.setRotationPoint(0.0F, 13.0F, -7.0F);
        this.arm_right_5.addBox(-0.9F, 0.0F, 0.0F, 2, 0, 9, 0.0F);
        this.arm_left_6 = new ModelRenderer(this, 58, 35);
        this.arm_left_6.setRotationPoint(-4.0F, 14.0F, -11.0F);
        this.arm_left_6.addBox(2.1F, -2.0F, 0.0F, 2, 2, 13, 0.0F);
        this.leg_right = new ModelRenderer(this, 49, 50);
        this.leg_right.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.leg_right.addBox(-12.0F, 0.0F, 0.0F, 12, 14, 0, 0.0F);
        this.setRotateAngle(leg_right, 0.0F, -0.5235987755982988F, 0.0F);
        this.arm_right_4 = new ModelRenderer(this, 58, 35);
        this.arm_right_4.mirror = true;
        this.arm_right_4.setRotationPoint(-4.0F, 12.0F, -11.0F);
        this.arm_right_4.addBox(1.1F, 0.0F, 0.0F, 2, 2, 13, 0.0F);
        this.head4 = new ModelRenderer(this, -3, 75);
        this.head4.setRotationPoint(0.0F, 1.0F, -5.0F);
        this.head4.addBox(0.0F, 0.0F, 0.0F, 0, 4, 9, 0.0F);
        this.back_side_armor_2 = new ModelRenderer(this, 0, 70);
        this.back_side_armor_2.mirror = true;
        this.back_side_armor_2.setRotationPoint(0.0F, 0.0F, 0.1F);
        this.back_side_armor_2.addBox(-8.0F, 0.0F, 0.0F, 8, 6, 0, 0.0F);
        this.arm_left_2 = new ModelRenderer(this, 82, 80);
        this.arm_left_2.setRotationPoint(2.0F, 2.0F, -1.0F);
        this.arm_left_2.addBox(0.2F, 0.0F, 0.0F, 0, 5, 4, 0.0F);
        this.head1 = new ModelRenderer(this, 0, 0);
        this.head1.setRotationPoint(5.0F, 3.0F, -3.9F);
        this.head1.addBox(-2.5F, -1.0F, -4.0F, 5, 2, 8, 0.0F);
        this.body1 = new ModelRenderer(this, 0, 55);
        this.body1.setRotationPoint(-4.0F, 10.0F, -4.0F);
        this.body1.addBox(0.0F, 0.0F, 0.0F, 8, 6, 8, 0.0F);
        this.back_2 = new ModelRenderer(this, 0, 29);
        this.back_2.setRotationPoint(1.0F, -16.0F, 6.0F);
        this.back_2.addBox(0.0F, 0.0F, 0.0F, 6, 14, 2, 0.0F);
        this.head2 = new ModelRenderer(this, 29, 0);
        this.head2.setRotationPoint(2.5F, 0.0F, -5.0F);
        this.head2.addBox(0.0F, 0.0F, 0.0F, 2, 0, 9, 0.0F);
        this.leg_back = new ModelRenderer(this, 49, 38);
        this.leg_back.setRotationPoint(4.0F, 0.0F, 8.0F);
        this.leg_back.addBox(0.0F, 0.0F, 0.0F, 0, 14, 12, 0.0F);
        this.back_a = new ModelRenderer(this, 38, 0);
        this.back_a.setRotationPoint(-2.0F, -3.0F, -11.0F);
        this.back_a.addBox(0.0F, 0.0F, 0.0F, 10, 17, 16, 0.0F);
        this.arm_right_3 = new ModelRenderer(this, 77, 76);
        this.arm_right_3.setRotationPoint(1.0F, 2.0F, -2.0F);
        this.arm_right_3.addBox(-0.9F, 0.0F, 0.0F, 0, 11, 2, 0.0F);
        this.arm_right_6 = new ModelRenderer(this, 58, 35);
        this.arm_right_6.mirror = true;
        this.arm_right_6.setRotationPoint(2.0F, 12.0F, -11.0F);
        this.arm_right_6.addBox(-0.9F, 0.0F, 0.0F, 2, 2, 13, 0.0F);
        this.body1.addChild(this.body2);
        this.arm_left.addChild(this.arm_left_4);
        this.back_2.addChild(this.back_c);
        this.body1.addChild(this.leg_left);
        this.back_c.addChild(this.arm_left);
        this.body1.addChild(this.side_armor_2);
        this.back_2.addChild(this.back_b);
        this.body1.addChild(this.body3);
        this.arm_right.addChild(this.arm_right_2);
        this.side_armor_1.addChild(this.side_armor_1_back);
        this.arm_left.addChild(this.arm_left_3);
        this.body1.addChild(this.side_armor_1);
        this.back_c.addChild(this.arm_right);
        this.arm_left.addChild(this.arm_left_5);
        this.head1.addChild(this.head3);
        this.body1.addChild(this.body4);
        this.arm_right.addChild(this.arm_right_5);
        this.arm_left.addChild(this.arm_left_6);
        this.body1.addChild(this.leg_right);
        this.arm_right.addChild(this.arm_right_4);
        this.head1.addChild(this.head4);
        this.side_armor_2.addChild(this.back_side_armor_2);
        this.arm_left.addChild(this.arm_left_2);
        this.back_a.addChild(this.head1);
        this.body1.addChild(this.back_2);
        this.head1.addChild(this.head2);
        this.body1.addChild(this.leg_back);
        this.back_2.addChild(this.back_a);
        this.arm_right.addChild(this.arm_right_3);
        this.arm_right.addChild(this.arm_right_6);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { 
        this.setRotateAngle(back_a, 0.0873F, 0.0F, 0.0F);
        this.setRotateAngle(head1, 0.2618F, 0.0F, 0.0F);
        this.body1.render(f5);
    }
    
    @Override
    public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn) {
        super.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor, entityIn);

        // Right Arm: Position (1, 2.5, 1) with 60-degree rotation on Z-axis
        this.arm_right.setRotationPoint(1.0F, 2.5F, 1.0F);
        this.setRotateAngle(arm_right, 0.0F, 0.0F, (float) Math.toRadians(60));

        // Left Arm: Position (4.5, 3.5, 1) with -60-degree rotation on Z-axis
        this.arm_left.setRotationPoint(4.5F, 3.5F, 1.0F);
        this.setRotateAngle(arm_left, 0.0F, 0.0F, (float) Math.toRadians(-60));
    }


    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}

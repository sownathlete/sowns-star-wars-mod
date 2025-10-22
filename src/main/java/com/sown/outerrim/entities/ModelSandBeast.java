package com.sown.outerrim.entities;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class ModelSandBeast extends ModelBase {
    public ModelRenderer body_upper;
    public ModelRenderer body_upper_2;
    public ModelRenderer arm_left;
    public ModelRenderer arm_right;
    public ModelRenderer head;
    public ModelRenderer body_lower;
    public ModelRenderer head_2;
    public ModelRenderer head_3;
    public ModelRenderer head_4;
    public ModelRenderer head_5;
    public ModelRenderer eye_left;
    public ModelRenderer eye_right;
    public ModelRenderer body_lower_2;
    public ModelRenderer leg_left_front;
    public ModelRenderer leg_right_front;
    public ModelRenderer leg_back_right;
    public ModelRenderer leg_back_left;
    public ModelRenderer leg_back_right_2;
    public ModelRenderer leg_back_left_2;

    public ModelSandBeast() {
        this.textureWidth = 128;
        this.textureHeight = 128;
        this.leg_right_front = new ModelRenderer(this, 60, 51);
        this.leg_right_front.mirror = true;
        this.leg_right_front.setRotationPoint(-2.5F, 3.0F, 3.0F);
        this.leg_right_front.addBox(0.0F, 0.0F, 0.0F, 6, 24, 6, 0.0F);
        this.arm_right = new ModelRenderer(this, 60, 18);
        this.arm_right.mirror = true;
        this.arm_right.setRotationPoint(-6.0F, 0.0F, 2.0F);
        this.arm_right.addBox(0.0F, 0.0F, 0.0F, 6, 26, 6, 0.0F);
        this.head_5 = new ModelRenderer(this, 85, -15);
        this.head_5.setRotationPoint(0.0F, -13.0F, 10.0F);
        this.head_5.addBox(0.0F, 0.0F, 0.0F, 0, 12, 15, 0.0F);
        this.setRotateAngle(head_5, 0.0F, 3.141592653589793F, 0.0F);
        this.head_3 = new ModelRenderer(this, 0, 0);
        this.head_3.mirror = true;
        this.head_3.setRotationPoint(5.5F, -4.0F, -4.0F);
        this.head_3.addBox(0.0F, 0.0F, 0.0F, 3, 4, 0, 0.0F);
        this.head_2 = new ModelRenderer(this, 0, 0);
        this.head_2.setRotationPoint(-8.5F, -4.0F, -4.0F);
        this.head_2.addBox(0.0F, 0.0F, 0.0F, 3, 4, 0, 0.0F);
        this.leg_back_left = new ModelRenderer(this, 0, 94);
        this.leg_back_left.setRotationPoint(9.5F, 3.0F, 21.0F);
        this.leg_back_left.addBox(0.0F, 0.0F, 0.0F, 7, 24, 7, 0.0F);
        this.eye_right = new ModelRenderer(this, 32, 0);
        this.eye_right.mirror = true;
        this.eye_right.setRotationPoint(-6.5F, -11.0F, -6.0F);
        this.eye_right.addBox(0.0F, 0.0F, 0.0F, 4, 4, 4, 0.0F);
        this.arm_left = new ModelRenderer(this, 60, 18);
        this.arm_left.setRotationPoint(18.0F, 0.0F, 2.0F);
        this.arm_left.addBox(0.0F, 0.0F, 0.0F, 6, 26, 6, 0.0F);
        this.leg_left_front = new ModelRenderer(this, 60, 51);
        this.leg_left_front.setRotationPoint(11.5F, 3.0F, 3.0F);
        this.leg_left_front.addBox(0.0F, 0.0F, 0.0F, 6, 24, 6, 0.0F);
        this.body_upper = new ModelRenderer(this, 0, 20);
        this.body_upper.setRotationPoint(-9.0F, -26.0F, -14.0F);
        this.body_upper.addBox(0.0F, 0.0F, 0.0F, 18, 24, 12, 0.0F);
        this.body_lower_2 = new ModelRenderer(this, 88, 68);
        this.body_lower_2.setRotationPoint(7.5F, -2.0F, 28.0F);
        this.body_lower_2.addBox(0.0F, 0.0F, 0.0F, 0, 2, 18, 0.0F);
        this.setRotateAngle(body_lower_2, 0.0F, 3.141592653589793F, 0.0F);
        this.body_upper_2 = new ModelRenderer(this, 87, 11);
        this.body_upper_2.setRotationPoint(9.0F, -3.0F, 17.0F);
        this.body_upper_2.addBox(0.0F, 0.0F, 0.0F, 0, 25, 8, 0.0F);
        this.setRotateAngle(body_upper_2, 0.0F, -3.141592653589793F, 0.0F);
        this.head = new ModelRenderer(this, 0, 0);
        this.head.setRotationPoint(9.0F, 0.0F, 3.0F);
        this.head.addBox(-5.5F, -10.0F, -5.0F, 11, 10, 10, 0.0F);
        this.eye_left = new ModelRenderer(this, 32, 0);
        this.eye_left.setRotationPoint(2.5F, -11.0F, -6.0F);
        this.eye_left.addBox(0.0F, 0.0F, 0.0F, 4, 4, 4, 0.0F);
        this.leg_back_right = new ModelRenderer(this, 0, 94);
        this.leg_back_right.mirror = true;
        this.leg_back_right.setRotationPoint(-1.5F, 3.0F, 21.0F);
        this.leg_back_right.addBox(0.0F, 0.0F, 0.0F, 7, 24, 7, 0.0F);
        this.head_4 = new ModelRenderer(this, 42, 10);
        this.head_4.setRotationPoint(-3.5F, -8.0F, -8.0F);
        this.head_4.addBox(0.0F, 0.0F, 0.0F, 7, 5, 3, 0.0F);
        this.leg_back_right_2 = new ModelRenderer(this, 29, 117);
        this.leg_back_right_2.mirror = true;
        this.leg_back_right_2.setRotationPoint(3.5F, 19.0F, 7.0F);
        this.leg_back_right_2.addBox(0.0F, 0.0F, 0.0F, 0, 5, 3, 0.0F);
        this.body_lower = new ModelRenderer(this, 0, 56);
        this.body_lower.setRotationPoint(1.5F, 23.0F, 3.0F);
        this.body_lower.addBox(0.0F, 0.0F, 0.0F, 15, 11, 27, 0.0F);
        this.leg_back_left_2 = new ModelRenderer(this, 29, 117);
        this.leg_back_left_2.setRotationPoint(3.5F, 19.0F, 7.0F);
        this.leg_back_left_2.addBox(0.0F, 0.0F, 0.0F, 0, 5, 3, 0.0F);
        this.body_lower.addChild(this.leg_right_front);
        this.body_upper.addChild(this.arm_right);
        this.head.addChild(this.head_5);
        this.head.addChild(this.head_3);
        this.head.addChild(this.head_2);
        this.body_lower.addChild(this.leg_back_left);
        this.head.addChild(this.eye_right);
        this.body_upper.addChild(this.arm_left);
        this.body_lower.addChild(this.leg_left_front);
        this.body_lower.addChild(this.body_lower_2);
        this.body_upper.addChild(this.body_upper_2);
        this.body_upper.addChild(this.head);
        this.head.addChild(this.eye_left);
        this.body_lower.addChild(this.leg_back_right);
        this.head.addChild(this.head_4);
        this.leg_back_right.addChild(this.leg_back_right_2);
        this.body_upper.addChild(this.body_lower);
        this.leg_back_left.addChild(this.leg_back_left_2);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { 
        this.body_upper.render(f5);
    }
    
    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
    
    @Override
    public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn) {
        // Head animation (rotation based on player's view)
        this.head.rotateAngleY = netHeadYaw * ((float) Math.PI / 180F);
        this.head.rotateAngleX = headPitch * ((float) Math.PI / 180F);

        // Walking animations
        this.leg_left_front.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
        this.leg_right_front.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount;
        this.leg_back_left.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount;
        this.leg_back_right.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;

        // Arm idle animations (e.g., slight movement)
        this.arm_left.rotateAngleX = MathHelper.cos(ageInTicks * 0.1F) * 0.05F;
        this.arm_right.rotateAngleX = MathHelper.cos(ageInTicks * 0.1F) * 0.05F;

        // Optional: Body swaying during walking
        this.body_upper.rotateAngleY = MathHelper.cos(limbSwing * 0.6662F) * 0.05F * limbSwingAmount;
    }

}
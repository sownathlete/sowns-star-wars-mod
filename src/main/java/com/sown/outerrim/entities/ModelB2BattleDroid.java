package com.sown.outerrim.entities;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class ModelB2BattleDroid extends ModelBase {
    public ModelRenderer body;
    public ModelRenderer leg_right;
    public ModelRenderer leg_left;
    public ModelRenderer upper_body;
    public ModelRenderer calf_right;
    public ModelRenderer calf_left;
    public ModelRenderer arm_right;
    public ModelRenderer head;
    public ModelRenderer arm_left;
    public ModelRenderer forearm_right;
    public ModelRenderer forearm_left;
    public ModelRenderer scope_back;
    public ModelRenderer scope_front;
    public ModelRenderer barrel_top;
    public ModelRenderer barrel_bottom;

    private boolean isInCombat = false;  // Flag to track combat state
    private float prevArmLeftRotateX;
    private float prevForearmLeftRotateX;
    private float prevForearmLeftPosY;
    private float prevForearmLeftPosX;

    public ModelB2BattleDroid() {
        this.textureWidth = 80;
        this.textureHeight = 80;
        this.body = new ModelRenderer(this, 0, 37);
        this.body.setRotationPoint(-3.0F, 2.0F, -2.5F);
        this.body.addBox(0.0F, 0.0F, 0.0F, 6, 8, 5, 0.0F);
        this.arm_left = new ModelRenderer(this, 42, 0);
        this.arm_left.setRotationPoint(14.0F, 2.0F, 2.0F);
        this.arm_left.addBox(0.0F, 0.0F, 0.0F, 4, 10, 5, 0.0F);
        this.calf_right = new ModelRenderer(this, 12, 50);
        this.calf_right.setRotationPoint(0.0F, 8.0F, 0.0F);
        this.calf_right.addBox(0.0F, 0.0F, 0.0F, 3, 9, 3, 0.0F);
        this.head = new ModelRenderer(this, 0, 0);
        this.head.setRotationPoint(7.0F, 2.7F, 3.0F);
        this.head.addBox(-2.0F, -4.7F, -5.0F, 4, 8, 8, 0.0F);
        this.leg_left = new ModelRenderer(this, 24, 50);
        this.leg_left.setRotationPoint(5.0F, 5.0F, 1.0F);
        this.leg_left.addBox(0.0F, 0.0F, 0.0F, 3, 8, 3, 0.0F);
        this.forearm_left = new ModelRenderer(this, 42, 15);
        this.forearm_left.setRotationPoint(0.0F, 10.0F, 0.0F);
        this.forearm_left.addBox(0.0F, 0.0F, 0.0F, 4, 4, 5, 0.0F);
        this.leg_right = new ModelRenderer(this, 0, 50);
        this.leg_right.setRotationPoint(-2.0F, 5.0F, 1.0F);
        this.leg_right.addBox(0.0F, 0.0F, 0.0F, 3, 8, 3, 0.0F);
        this.calf_left = new ModelRenderer(this, 36, 50);
        this.calf_left.setRotationPoint(0.0F, 8.0F, 0.0F);
        this.calf_left.addBox(0.0F, 0.0F, 0.0F, 3, 9, 3, 0.0F);
        this.arm_right = new ModelRenderer(this, 24, 0);
        this.arm_right.setRotationPoint(-4.0F, 2.0F, 2.0F);
        this.arm_right.addBox(0.0F, 0.0F, 0.0F, 4, 10, 5, 0.0F);
        this.scope_front = new ModelRenderer(this, 36, 15);
        this.scope_front.mirror = true;
        this.scope_front.setRotationPoint(2.0F, 0.0F, -3.0F);
        this.scope_front.addBox(0.0F, 0.0F, 0.0F, 0, 4, 3, 0.0F);
        this.upper_body = new ModelRenderer(this, 0, 16);
        this.upper_body.setRotationPoint(-4.0F, -13.0F, -2.0F);
        this.upper_body.addBox(0.0F, 0.0F, 0.0F, 14, 13, 8, 0.0F);
        this.barrel_top = new ModelRenderer(this, 44, 24);
        this.barrel_top.setRotationPoint(0.5F, 4.0F, 0.0F);
        this.barrel_top.addBox(0.0F, 0.0F, 0.0F, 3, 10, 3, 0.0F);
        this.forearm_right = new ModelRenderer(this, 46, 37);
        this.forearm_right.setRotationPoint(0.0F, 10.0F, 0.0F);
        this.forearm_right.addBox(0.0F, 0.0F, 0.0F, 4, 11, 5, 0.0F);
        this.barrel_bottom = new ModelRenderer(this, 56, 25);
        this.barrel_bottom.setRotationPoint(1.0F, 4.0F, 3.0F);
        this.barrel_bottom.addBox(0.0F, 0.0F, 0.0F, 2, 10, 2, 0.0F);
        this.scope_back = new ModelRenderer(this, 36, 12);
        this.scope_back.mirror = true;
        this.scope_back.setRotationPoint(2.0F, 7.0F, -3.0F);
        this.scope_back.addBox(0.0F, 0.0F, 0.0F, 0, 3, 3, 0.0F);
        this.upper_body.addChild(this.arm_left);
        this.leg_right.addChild(this.calf_right);
        this.upper_body.addChild(this.head);
        this.body.addChild(this.leg_left);
        this.arm_left.addChild(this.forearm_left);
        this.body.addChild(this.leg_right);
        this.leg_left.addChild(this.calf_left);
        this.upper_body.addChild(this.arm_right);
        this.forearm_left.addChild(this.scope_front);
        this.body.addChild(this.upper_body);
        this.forearm_left.addChild(this.barrel_top);
        this.arm_right.addChild(this.forearm_right);
        this.forearm_left.addChild(this.barrel_bottom);
        this.arm_left.addChild(this.scope_back);

        // Initialize previous rotation/position states for interpolation
        this.prevArmLeftRotateX = this.arm_left.rotateAngleX;
        this.prevForearmLeftRotateX = this.forearm_left.rotateAngleX;
        this.prevForearmLeftPosY = this.forearm_left.rotationPointY;
        this.prevForearmLeftPosX = this.forearm_left.rotationPointX;
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        this.setRotationAngles(f, f1, f2, f3, f4, f5, entity); 
        this.body.render(f5);
    }
    
    public void setInCombat(boolean inCombat) {
        this.isInCombat = inCombat;  // Update the combat state
    }

    @Override
    public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entity) {
        // Set walking animation for legs (not directly relevant but keeping it here for full animation)
        this.leg_right.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * limbSwingAmount;
        this.leg_left.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * limbSwingAmount;
        this.arm_left.rotateAngleX = (float) Math.toRadians(-90);  // Set arm to 90 degrees

        float transitionSpeed = 0.1F;  // Adjust this to control the speed of interpolation
        if (limbSwingAmount > 0.1F) {  // Moving threshold
            float targetArmLeftRotateX = (float) Math.toRadians(-60);  // -60 degrees
            float targetForearmLeftRotateX = (float) Math.toRadians(-30);  // -30 degrees
            float targetForearmLeftPosY = 7.5F;  // Move Y position
            float targetForearmLeftPosX = 0.7F;  // Move X position

            // Interpolate arm rotation
            this.arm_left.rotateAngleX = interpolate(this.prevArmLeftRotateX, targetArmLeftRotateX, transitionSpeed);
            // Interpolate forearm rotation and position
            this.forearm_left.rotateAngleX = interpolate(this.prevForearmLeftRotateX, targetForearmLeftRotateX, transitionSpeed);
            this.forearm_left.rotationPointY = interpolate(this.prevForearmLeftPosY, targetForearmLeftPosY, transitionSpeed);
            this.forearm_left.rotationPointX = interpolate(this.prevForearmLeftPosX, targetForearmLeftPosX, transitionSpeed);
        } else {
            // Interpolate back to default (resting) position when idle
            this.arm_left.rotateAngleX = interpolate(this.arm_left.rotateAngleX, 0.0F, transitionSpeed);
            this.forearm_left.rotateAngleX = interpolate(this.forearm_left.rotateAngleX, 0.0F, transitionSpeed);
            this.forearm_left.rotationPointY = interpolate(this.forearm_left.rotationPointY, 10.0F, transitionSpeed);
            this.forearm_left.rotationPointX = interpolate(this.forearm_left.rotationPointX, 0.0F, transitionSpeed);
        }
        
        this.head.rotateAngleY = netHeadYaw * ((float) Math.PI / 180F);
        this.head.rotateAngleX = headPitch * ((float) Math.PI / 180F);

        this.prevArmLeftRotateX = this.arm_left.rotateAngleX;
        this.prevForearmLeftRotateX = this.forearm_left.rotateAngleX;
        this.prevForearmLeftPosY = this.forearm_left.rotationPointY;
        this.prevForearmLeftPosX = this.forearm_left.rotationPointX;
    }

    private float interpolate(float previousValue, float targetValue, float factor) {
        return previousValue + (targetValue - previousValue) * factor;
    }
}

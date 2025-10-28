package com.sown.outerrim.entities;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MathHelper;

public class ModelYoda extends ModelBase {
    public ModelRenderer head;
    public ModelRenderer rightLeg;
    public ModelRenderer robe;
    public ModelRenderer rightSleeve;
    public ModelRenderer leftSleeve;
    public ModelRenderer leftLeg;
    public ModelRenderer body;
    public ModelRenderer rightArm;
    public ModelRenderer leftArm;
    public ModelRenderer rightEarFront;
    public ModelRenderer leftEarFront;
    public ModelRenderer rightEarBack;
    public ModelRenderer leftEarBack;

    // Make the robe tilt specific to each instance
    private float currentRobeTilt = 0.0F; 

    public ModelYoda() {
        this.textureWidth = 64;
        this.textureHeight = 64;

        // Body
        this.body = new ModelRenderer(this, 0, 12);
        this.body.setRotationPoint(0.0F, 11.0F, 0.0F);
        this.body.addBox(-4.0F, 0.0F, -2.0F, 8, 8, 4, 0.0F);

        // Right Ear Front (position -8, -10, -1)
        this.rightEarFront = new ModelRenderer(this, 51, 13);
        this.rightEarFront.mirror = true;
        this.rightEarFront.setRotationPoint(-8.0F, -10.0F, -1.0F);
        this.rightEarFront.addBox(0.0F, 0.0F, 0.0F, 6, 4, 0, 0.0F);

        // Left Ear Front (position 2, -10, -1)
        this.leftEarFront = new ModelRenderer(this, 51, 13);
        this.leftEarFront.setRotationPoint(2.0F, -10.0F, -1.0F);
        this.leftEarFront.addBox(0.0F, 0.0F, 0.0F, 6, 4, 0, 0.0F);

        // Right Arm
        this.rightArm = new ModelRenderer(this, 24, 14);
        this.rightArm.setRotationPoint(0.3F, 7.7F, 0.0F);
        this.rightArm.addBox(-6.0F, 4.0F, -1.0F, 2, 8, 2, 0.0F);
        this.setRotateAngle(rightArm, 0.0F, 0.0F, 0.045029494701453704F);

        // Head
        this.head = new ModelRenderer(this, 0, 0);
        this.head.setRotationPoint(0.0F, 15.0F, 1.0F);
        this.head.addBox(-3.0F, -10.0F, -4.0F, 6, 6, 6, 0.0F);

        // Left Ear Back
        this.leftEarBack = new ModelRenderer(this, 48, 0);
        this.leftEarBack.setRotationPoint(0.0F, 0.0F, 0.11F);
        this.leftEarBack.addBox(0.0F, 0.0F, 0.0F, 6, 4, 0, 0.0F);

        // Right Ear Back
        this.rightEarBack = new ModelRenderer(this, 48, 0);
        this.rightEarBack.mirror = true;
        this.rightEarBack.setRotationPoint(0.0F, 0.0F, 0.01F);
        this.rightEarBack.addBox(0.0F, 0.0F, 0.0F, 6, 4, 0, 0.0F);

        // Right Sleeve
        this.rightSleeve = new ModelRenderer(this, 32, 14);
        this.rightSleeve.setRotationPoint(0.3F, 7.7F, 0.0F);
        this.rightSleeve.addBox(-6.0F, 4.0F, -1.0F, 2, 8, 2, 0.25F);

        // Robe
        this.robe = new ModelRenderer(this, 40, 29);
        this.robe.setRotationPoint(0.0F, 19.0F, 0.0F);
        this.robe.addBox(-4.0F, 0.0F, -2.0F, 8, 5, 4, 0.0F);

        // Left Arm
        this.leftArm = new ModelRenderer(this, 24, 14);
        this.leftArm.mirror = true;
        this.leftArm.setRotationPoint(-0.3F, 7.7F, 0.0F);
        this.leftArm.addBox(4.0F, 4.0F, -1.0F, 2, 8, 2, 0.0F);

        // Left Leg
        this.leftLeg = new ModelRenderer(this, 24, 24);
        this.leftLeg.setRotationPoint(0.1F, 19.0F, -0.5F);
        this.leftLeg.addBox(0.4F, 0.0F, -1.4F, 3, 5, 3, 0.0F);

        // Right Leg
        this.rightLeg = new ModelRenderer(this, 24, 24);
        this.rightLeg.mirror = true;
        this.rightLeg.setRotationPoint(0.0F, 19.0F, 0.0F);
        this.rightLeg.addBox(-3.5F, 0.0F, -1.9F, 3, 5, 3, 0.0F);

        // Left Sleeve
        this.leftSleeve = new ModelRenderer(this, 32, 14);
        this.leftSleeve.mirror = true;
        this.leftSleeve.setRotationPoint(-0.3F, 7.7F, 0.0F);
        this.leftSleeve.addBox(4.0F, 4.0F, -1.0F, 2, 8, 2, 0.25F);

        // Attach the back parts of the ears
        this.leftEarFront.addChild(this.leftEarBack);
        this.rightEarFront.addChild(this.rightEarBack);

        // Attach the ears to the head
        this.head.addChild(this.rightEarFront);
        this.head.addChild(this.leftEarFront);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { 
        this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        this.body.render(f5);
        this.rightArm.render(f5);
        this.head.render(f5);
        this.rightSleeve.render(f5);
        this.robe.render(f5);
        this.leftArm.render(f5);
        this.leftLeg.render(f5);
        this.leftSleeve.render(f5);
        this.rightLeg.render(f5);
    }

    // Helper method to set rotations
    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }

    @Override
    public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks,
                                  float netHeadYaw, float headPitch, float scaleFactor, Entity entity) {
        final float RAD = (float)Math.PI / 180F;

        // Head aim
        this.head.rotateAngleX = headPitch * RAD;
        this.head.rotateAngleY = netHeadYaw * RAD;
        this.head.rotateAngleZ = 0F;

        // Clamp wild spikes (hurt/knockback sometimes feeds huge limbSwingAmount)
        float swingAmt = MathHelper.clamp_float(limbSwingAmount, 0.0F, 1.2F);

        // Keep idle micro-motion but tone it down slightly if hurt/airborne (no hard freeze)
        float idleMul = 1.0F;
        if (entity instanceof EntityLivingBase) {
            EntityLivingBase elb = (EntityLivingBase) entity;
            if (elb.hurtTime > 0 || !elb.onGround) idleMul = 0.6F; // softer, not frozen
        }

        final float w = limbSwing * 0.6662F;

        // Arms walk + idle
        float armXR = (float)Math.cos(w + Math.PI) * swingAmt * 0.8F;
        float armXL = (float)Math.cos(w)           * swingAmt * 0.8F;

        float idleX = MathHelper.sin(ageInTicks * 0.067F) * 0.05F * idleMul;
        float idleZ = MathHelper.cos(ageInTicks * 0.09F)  * 0.05F * idleMul;

        this.rightArm.rotateAngleX = armXR + idleX;
        this.rightArm.rotateAngleY = 0F;
        this.rightArm.rotateAngleZ =  idleZ;

        this.leftArm.rotateAngleX  = armXL - idleX;
        this.leftArm.rotateAngleY  = 0F;
        this.leftArm.rotateAngleZ  = -idleZ;

        // Sleeves follow arms
        this.rightSleeve.rotateAngleX = this.rightArm.rotateAngleX;
        this.rightSleeve.rotateAngleY = this.rightArm.rotateAngleY;
        this.rightSleeve.rotateAngleZ = this.rightArm.rotateAngleZ;

        this.leftSleeve.rotateAngleX  = this.leftArm.rotateAngleX;
        this.leftSleeve.rotateAngleY  = this.leftArm.rotateAngleY;
        this.leftSleeve.rotateAngleZ  = this.leftArm.rotateAngleZ;

        // Legs walk
        this.rightLeg.rotateAngleX = (float)Math.cos(w)           * swingAmt * 1.2F;
        this.rightLeg.rotateAngleY = 0F;
        this.rightLeg.rotateAngleZ = 0F;

        this.leftLeg.rotateAngleX  = (float)Math.cos(w + Math.PI) * swingAmt * 1.2F;
        this.leftLeg.rotateAngleY  = 0F;
        this.leftLeg.rotateAngleZ  = 0F;

        // Per-entity robe tilt (no cross-bleed between Yodas)
        float robeTilt = 0.0F;
        if (entity instanceof MobYoda) robeTilt = ((MobYoda)entity).getCurrentRobeTilt();
        this.robe.rotateAngleX = robeTilt;
        this.robe.rotateAngleY = 0F;
        this.robe.rotateAngleZ = 0F;
    }
}
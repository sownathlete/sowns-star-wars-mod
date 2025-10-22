package com.sown.outerrim.entities;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class ModelWookieeMale extends ModelBase {
    // Root parts
    private final ModelRenderer head;
    private final ModelRenderer torso;
    private final ModelRenderer leftArm;
    private final ModelRenderer rightArm;
    private final ModelRenderer leftLeg;
    private final ModelRenderer rightLeg;

    // Overlay (child) parts
    private final ModelRenderer helmet;
    private final ModelRenderer scalpLeft;
    private final ModelRenderer scalpRight;
    private final ModelRenderer beardCenter;
    private final ModelRenderer beardLeft;
    private final ModelRenderer beardRight;
    private final ModelRenderer jacket;
    private final ModelRenderer leftSleeve;
    private final ModelRenderer rightSleeve;
    private final ModelRenderer leftPantLeg;
    private final ModelRenderer rightPantLeg;

    public ModelWookieeMale() {
        this.textureWidth = 64;
        this.textureHeight = 64;

        // ----- HEAD and child overlays -----
        head = new ModelRenderer(this, 0, 0);
        head.setRotationPoint(0F, -3F, 0F);
        head.addBox(-4F, -8F, -4F, 8, 8, 8, 0F);

        helmet = new ModelRenderer(this, 32, 0);
        helmet.setRotationPoint(0F, -3F, 0F);
        helmet.addBox(-4F, -8F, -4F, 8, 8, 8, 0.5F);

        scalpLeft = new ModelRenderer(this, 3, 54);
        scalpLeft.mirror = true;
        scalpLeft.setRotationPoint(0F, -9F, -3F);
        scalpLeft.addBox(0F, 0F, 0F, 3, 1, 6, 0F);

        scalpRight = new ModelRenderer(this, 3, 54);
        scalpRight.setRotationPoint(-3F, -9F, -3F);
        scalpRight.addBox(0F, 0F, 0F, 3, 1, 6, 0F);

        beardCenter = new ModelRenderer(this, 0, 5);
        beardCenter.setRotationPoint(-1F, 0F, -4F);
        beardCenter.addBox(0F, 0F, 0F, 2, 2, 1, 0F);

        beardLeft = new ModelRenderer(this, 0, 3);
        beardLeft.setRotationPoint(2F, 0F, -4F);
        beardLeft.addBox(0F, 0F, 0F, 1, 1, 1, 0F);

        beardRight = new ModelRenderer(this, 0, 3);
        beardRight.setRotationPoint(-3F, 0F, -4F);
        beardRight.addBox(0F, 0F, 0F, 1, 1, 1, 0F);

        // ----- TORSO and jacket -----
        torso = new ModelRenderer(this, 16, 16);
        torso.setRotationPoint(0F, -3F, 0F);
        torso.addBox(-4F, 0F, -2F, 8, 13, 4, 0F);

        jacket = new ModelRenderer(this, 16, 32);
        jacket.setRotationPoint(0F, -3F, 0F);
        jacket.addBox(-4F, 0F, -2F, 8, 13, 4, 0.25F);

        // ----- LEFT ARM and sleeve -----
        leftArm = new ModelRenderer(this, 40, 16);
        leftArm.setRotationPoint(5F, -1F, 0F);
        leftArm.addBox(-1F, -2F, -2F, 4, 12, 4, 0F);

        leftSleeve = new ModelRenderer(this, 40, 33);
        leftSleeve.mirror = true;
        leftSleeve.setRotationPoint(5F, -1F, 0F);
        leftSleeve.addBox(-1F, -2F, -2F, 4, 12, 4, 0.25F);

        // ----- RIGHT ARM and sleeve -----
        rightArm = new ModelRenderer(this, 40, 16);
        rightArm.setRotationPoint(-5F, -1F, 0F);
        rightArm.addBox(-3F, -2F, -2F, 4, 12, 4, 0F);

        rightSleeve = new ModelRenderer(this, 40, 33);
        rightSleeve.setRotationPoint(-5F, -1F, 0F);
        rightSleeve.addBox(-3F, -2F, -2F, 4, 12, 4, 0.25F);

        // ----- LEFT LEG and pant leg -----
        leftLeg = new ModelRenderer(this, 0, 16);
        leftLeg.mirror = true;
        leftLeg.setRotationPoint(1.9F, 10F, 0F);
        leftLeg.addBox(-2F, 0F, -2F, 4, 14, 4, 0F);

        leftPantLeg = new ModelRenderer(this, 0, 34);
        leftPantLeg.mirror = true;
        leftPantLeg.setRotationPoint(1.9F, 10F, 0F);
        leftPantLeg.addBox(-2F, 0F, -2F, 4, 14, 4, 0.25F);

        // ----- RIGHT LEG and pant leg -----
        rightLeg = new ModelRenderer(this, 0, 16);
        rightLeg.setRotationPoint(-1.9F, 10F, 0F);
        rightLeg.addBox(-2F, 0F, -2F, 4, 14, 4, 0F);

        rightPantLeg = new ModelRenderer(this, 0, 34);
        rightPantLeg.setRotationPoint(-1.9F, 10F, 0F);
        rightPantLeg.addBox(-2F, 0F, -2F, 4, 14, 4, 0.25F);
        
        this.head.addChild(this.beardRight);
        this.head.addChild(this.scalpLeft);
        this.head.addChild(this.beardLeft);
        this.head.addChild(this.scalpRight);
        this.head.addChild(this.beardCenter);
    }

    @Override
    public void render(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw,
                       float headPitch, float scale) {
        // Apply animations so that children follow correctly
        setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entity);

        // Render root parts; overlays must be rendered explicitly to appear
        head.render(scale);
        helmet.render(scale);

        torso.render(scale);
        jacket.render(scale);

        leftArm.render(scale);
        leftSleeve.render(scale);

        rightArm.render(scale);
        rightSleeve.render(scale);

        leftLeg.render(scale);
        leftPantLeg.render(scale);

        rightLeg.render(scale);
        rightPantLeg.render(scale);
    }

    @Override
    public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw,
                                  float headPitch, float scaleFactor, Entity entityIn) {
        // HEAD: follow yaw & pitch
        head.rotateAngleY = netHeadYaw * 0.017453292F;
        head.rotateAngleX = headPitch * 0.017453292F;

        helmet.rotateAngleY = head.rotateAngleY;
        helmet.rotateAngleX = head.rotateAngleX;

        // SWING: arms & legs during walking
        float swingCos = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
        float swingOpp = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount;

        leftArm.rotateAngleX = swingCos;
        leftSleeve.rotateAngleX = swingCos;

        rightArm.rotateAngleX = swingOpp;
        rightSleeve.rotateAngleX = swingOpp;

        leftLeg.rotateAngleX = swingOpp;
        leftPantLeg.rotateAngleX = swingOpp;

        rightLeg.rotateAngleX = swingCos;
        rightPantLeg.rotateAngleX = swingCos;
    }
}

package com.sown.outerrim.entities;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class ModelEwok extends ModelBase {
    public ModelRenderer head;
    public ModelRenderer torso;
    public ModelRenderer leftArm;
    public ModelRenderer rightArm;
    public ModelRenderer leftLeg;
    public ModelRenderer rightLeg;
    public ModelRenderer nose;
    public ModelRenderer rightEar;
    public ModelRenderer leftEar;

    public ModelEwok() {
        this.textureWidth = 64;
        this.textureHeight = 64;

        // Torso
        this.torso = new ModelRenderer(this, 16, 16);
        this.torso.setRotationPoint(0.0F, 14.0F, 0.0F);
        this.torso.addBox(-4.0F, 0.0F, -2.0F, 8, 5, 4, 0.0F);

        // Nose (child of head)
        this.nose = new ModelRenderer(this, 0, 0);
        this.nose.setRotationPoint(-1.0F, 0.0F, -3.5F);
        this.nose.addBox(0.0F, 0.0F, 0.0F, 2, 1, 1, 0.0F);

        // Left ear (child of head)
        this.leftEar = new ModelRenderer(this, 24, 3);
        this.leftEar.setRotationPoint(2.0F, -4.0F, 0.0F);
        this.leftEar.addBox(0.0F, 0.0F, 0.0F, 2, 2, 1, 0.0F);

        // Right ear (child of head)
        this.rightEar = new ModelRenderer(this, 24, 0);
        this.rightEar.setRotationPoint(-4.0F, -4.0F, 0.0F);
        this.rightEar.addBox(0.0F, 0.0F, 0.0F, 2, 2, 1, 0.0F);

        // Head
        this.head = new ModelRenderer(this, 2, 2);
        this.head.setRotationPoint(0.0F, 11.0F, 0.0F);
        this.head.addBox(-3.0F, -3.0F, -3.0F, 6, 6, 6, 0.0F);
        this.head.addChild(this.nose);
        this.head.addChild(this.leftEar);
        this.head.addChild(this.rightEar);

        // Left arm
        this.leftArm = new ModelRenderer(this, 38, 0);
        this.leftArm.setRotationPoint(5.0F, 15.5F, 0.0F);
        this.leftArm.addBox(-1.0F, -1.0F, -1.0F, 2, 6, 2, 0.0F);

        // Right arm
        this.rightArm = new ModelRenderer(this, 30, 0);
        this.rightArm.setRotationPoint(-5.0F, 15.5F, 0.0F);
        this.rightArm.addBox(-1.0F, -1.0F, -1.0F, 2, 6, 2, 0.0F);

        // Left leg
        this.leftLeg = new ModelRenderer(this, 0, 23);
        this.leftLeg.setRotationPoint(1.9F, 19.0F, 0.0F);
        this.leftLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 5, 4, 0.0F);

        // Right leg
        this.rightLeg = new ModelRenderer(this, 0, 14);
        this.rightLeg.setRotationPoint(-1.9F, 19.0F, 0.0F);
        this.rightLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 5, 4, 0.0F);
    }

    @Override
    public void render(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
        // Animate model parts before rendering
        setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entity);
        // Render each part
        this.head.render(scale);
        this.torso.render(scale);
        this.leftArm.render(scale);
        this.rightArm.render(scale);
        this.leftLeg.render(scale);
        this.rightLeg.render(scale);
    }

    @Override
    public void setRotationAngles(
            float limbSwing,
            float limbSwingAmount,
            float ageInTicks,
            float netHeadYaw,
            float headPitch,
            float scaleFactor,
            Entity entity
    ) {
        // Head follows the look direction
        this.head.rotateAngleY = netHeadYaw * ((float) Math.PI / 180F);
        this.head.rotateAngleX = headPitch * ((float) Math.PI / 180F);

        // Arms swing opposite to each other when walking
        this.rightArm.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 2.0F * limbSwingAmount * 0.5F;
        this.leftArm.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 2.0F * limbSwingAmount * 0.5F;

        // Legs swing opposite to each other when walking
        this.rightLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
        this.leftLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount;
    }

    /**
     * Utility method to set initial rotation angles if needed.
     */
    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }
}

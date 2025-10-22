package com.sown.outerrim.entities;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class ModelPykeSentinel extends ModelBase {
    public ModelRenderer helmet;
    public ModelRenderer head;
    public ModelRenderer jacket;
    public ModelRenderer torso;
    public ModelRenderer leftSleeve;
    public ModelRenderer leftArm;
    public ModelRenderer rightSleeve;
    public ModelRenderer rightArm;
    public ModelRenderer leftPantLeg;
    public ModelRenderer leftLeg;
    public ModelRenderer rightPantLeg;
    public ModelRenderer rightLeg;
    public ModelRenderer headLight;
    public ModelRenderer helmetBack;
    public ModelRenderer helmetRight;
    public ModelRenderer helmetLeft;
    public ModelRenderer nose;
    public ModelRenderer mouthRight;
    public ModelRenderer mouthLeft;
    public ModelRenderer beltRight;
    public ModelRenderer beltLeft;
    public ModelRenderer chestRight;
    public ModelRenderer chestLeft;
    public ModelRenderer back;
    public ModelRenderer chestRightTip;
    public ModelRenderer chestLeftTip;
    public ModelRenderer backBottom;
    public ModelRenderer leftWrist;
    public ModelRenderer rightWrist;

    public ModelPykeSentinel() {
        this.textureWidth = 64;
        this.textureHeight = 64;
        this.rightLeg = new ModelRenderer(this, 0, 16);
        this.rightLeg.setRotationPoint(-1.9F, 11.0F, 0.0F);
        this.rightLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 13, 4, 0.0F);
        this.leftLeg = new ModelRenderer(this, 0, 16);
        this.leftLeg.mirror = true;
        this.leftLeg.setRotationPoint(1.9F, 11.0F, 0.0F);
        this.leftLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 13, 4, 0.0F);
        this.headLight = new ModelRenderer(this, 24, 0);
        this.headLight.setRotationPoint(2.4F, -9.0F, -5.0F);
        this.headLight.addBox(0.0F, 0.0F, 0.0F, 2, 2, 1, 0.0F);
        this.chestLeft = new ModelRenderer(this, 0, 4);
        this.chestLeft.mirror = true;
        this.chestLeft.setRotationPoint(1.5F, 1.5F, -3.0F);
        this.chestLeft.addBox(0.0F, 0.0F, 0.0F, 2, 3, 1, 0.0F);
        this.mouthRight = new ModelRenderer(this, 24, 5);
        this.mouthRight.setRotationPoint(-2.0F, -2.0F, -5.0F);
        this.mouthRight.addBox(0.0F, 0.0F, 0.0F, 1, 2, 1, 0.0F);
        this.leftPantLeg = new ModelRenderer(this, 37, 47);
        this.leftPantLeg.setRotationPoint(1.9F, 11.0F, 0.0F);
        this.leftPantLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 13, 4, 0.26F);
        this.chestRightTip = new ModelRenderer(this, 1, 0);
        this.chestRightTip.setRotationPoint(0.5F, 3.0F, 0.0F);
        this.chestRightTip.addBox(0.0F, 0.0F, 0.0F, 1, 1, 1, 0.0F);
        this.jacket = new ModelRenderer(this, 16, 33);
        this.jacket.setRotationPoint(0.0F, -2.0F, 0.0F);
        this.jacket.addBox(-4.0F, 0.0F, -2.0F, 8, 13, 4, 0.27F);
        this.rightWrist = new ModelRenderer(this, 23, 56);
        this.rightWrist.mirror = true;
        this.rightWrist.setRotationPoint(-3.0F, 5.0F, -1.0F);
        this.rightWrist.addBox(0.0F, 0.0F, 0.0F, 1, 5, 3, 0.0F);
        this.mouthLeft = new ModelRenderer(this, 24, 5);
        this.mouthLeft.mirror = true;
        this.mouthLeft.setRotationPoint(1.0F, -2.0F, -5.0F);
        this.mouthLeft.addBox(0.0F, 0.0F, 0.0F, 1, 2, 1, 0.0F);
        this.chestLeftTip = new ModelRenderer(this, 1, 0);
        this.chestLeftTip.setRotationPoint(0.5F, 3.0F, 0.0F);
        this.chestLeftTip.addBox(0.0F, 0.0F, 0.0F, 1, 1, 1, 0.0F);
        this.rightPantLeg = new ModelRenderer(this, 0, 33);
        this.rightPantLeg.setRotationPoint(-1.9F, 11.0F, 0.0F);
        this.rightPantLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 13, 4, 0.25F);
        this.beltRight = new ModelRenderer(this, 34, 1);
        this.beltRight.setRotationPoint(-4.0F, 6.0F, -3.0F);
        this.beltRight.addBox(0.0F, 0.0F, 0.0F, 2, 3, 1, 0.0F);
        this.helmet = new ModelRenderer(this, 32, 0);
        this.helmet.setRotationPoint(0.0F, -2.0F, 0.0F);
        this.helmet.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, 0.5F);
        this.torso = new ModelRenderer(this, 16, 16);
        this.torso.setRotationPoint(0.0F, -2.0F, 0.0F);
        this.torso.addBox(-4.0F, 0.0F, -2.0F, 8, 13, 4, 0.0F);
        this.helmetRight = new ModelRenderer(this, 40, 37);
        this.helmetRight.mirror = true;
        this.helmetRight.setRotationPoint(-5.5F, -2.5F, -2.0F);
        this.helmetRight.addBox(0.0F, 0.0F, 0.0F, 1, 3, 4, 0.0F);
        this.beltLeft = new ModelRenderer(this, 34, 1);
        this.beltLeft.setRotationPoint(2.0F, 6.0F, -3.0F);
        this.beltLeft.addBox(0.0F, 0.0F, 0.0F, 2, 3, 1, 0.0F);
        this.leftWrist = new ModelRenderer(this, 23, 56);
        this.leftWrist.setRotationPoint(2.0F, 5.0F, -1.0F);
        this.leftWrist.addBox(0.0F, 0.0F, 0.0F, 1, 5, 3, 0.0F);
        this.helmetLeft = new ModelRenderer(this, 40, 37);
        this.helmetLeft.setRotationPoint(4.5F, -2.5F, -2.0F);
        this.helmetLeft.addBox(0.0F, 0.0F, 0.0F, 1, 3, 4, 0.0F);
        this.chestRight = new ModelRenderer(this, 0, 4);
        this.chestRight.setRotationPoint(-3.5F, 1.5F, -3.0F);
        this.chestRight.addBox(0.0F, 0.0F, 0.0F, 2, 3, 1, 0.0F);
        this.rightSleeve = new ModelRenderer(this, 50, 33);
        this.rightSleeve.setRotationPoint(-5.0F, 0.5F, 0.0F);
        this.rightSleeve.addBox(-2.0F, -2.0F, -2.0F, 3, 13, 4, 0.25F);
        this.helmetBack = new ModelRenderer(this, 0, 50);
        this.helmetBack.setRotationPoint(-4.0F, -8.0F, 4.0F);
        this.helmetBack.addBox(0.0F, 0.0F, 0.0F, 8, 2, 2, 0.0F);
        this.back = new ModelRenderer(this, 0, 58);
        this.back.setRotationPoint(-2.5F, 2.0F, 2.0F);
        this.back.addBox(0.0F, 0.0F, 0.0F, 5, 5, 1, 0.0F);
        this.nose = new ModelRenderer(this, 34, 5);
        this.nose.setRotationPoint(-1.0F, -4.0F, -5.0F);
        this.nose.addBox(0.0F, 0.0F, 0.0F, 2, 2, 1, 0.0F);
        this.rightArm = new ModelRenderer(this, 40, 16);
        this.rightArm.setRotationPoint(-5.0F, 0.5F, 0.0F);
        this.rightArm.addBox(-2.0F, -2.0F, -2.0F, 3, 13, 4, 0.0F);
        this.head = new ModelRenderer(this, 0, 0);
        this.head.setRotationPoint(0.0F, -2.0F, 0.0F);
        this.head.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, 0.0F);
        this.leftSleeve = new ModelRenderer(this, 50, 33);
        this.leftSleeve.mirror = true;
        this.leftSleeve.setRotationPoint(5.0F, 0.5F, 0.0F);
        this.leftSleeve.addBox(-1.0F, -2.0F, -2.0F, 3, 13, 4, 0.25F);
        this.leftArm = new ModelRenderer(this, 40, 16);
        this.leftArm.mirror = true;
        this.leftArm.setRotationPoint(5.0F, 0.5F, 0.0F);
        this.leftArm.addBox(-1.0F, -2.0F, -2.0F, 3, 13, 4, 0.0F);
        this.backBottom = new ModelRenderer(this, 13, 62);
        this.backBottom.setRotationPoint(0.0F, 5.0F, 1.0F);
        this.backBottom.addBox(0.0F, 0.0F, 0.0F, 4, 2, 0, 0.0F);
        this.head.addChild(this.headLight);
        this.torso.addChild(this.chestLeft);
        this.head.addChild(this.mouthRight);
        this.chestRight.addChild(this.chestRightTip);
        this.rightArm.addChild(this.rightWrist);
        this.head.addChild(this.mouthLeft);
        this.chestLeft.addChild(this.chestLeftTip);
        this.torso.addChild(this.beltRight);
        this.head.addChild(this.helmetRight);
        this.torso.addChild(this.beltLeft);
        this.leftArm.addChild(this.leftWrist);
        this.head.addChild(this.helmetLeft);
        this.torso.addChild(this.chestRight);
        this.head.addChild(this.helmetBack);
        this.torso.addChild(this.back);
        this.head.addChild(this.nose);
        this.back.addChild(this.backBottom);
    }

	@Override
	public void render(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw,
			float headPitch, float scale) {
		// Update rotations (so children follow appropriately)
		setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entity);

		// Render head and its overlays explicitly (helmet is not a child)
		head.render(scale);
		helmet.rotateAngleY = head.rotateAngleY;
		helmet.rotateAngleX = head.rotateAngleX;
		helmet.render(scale);

		// Render torso and jacket
		torso.render(scale);
		jacket.render(scale);

		// Render left arm and sleeve
		leftArm.render(scale);
		leftSleeve.rotateAngleX = leftArm.rotateAngleX;
		leftSleeve.render(scale);

		// Render right arm and sleeve
		rightArm.render(scale);
		rightSleeve.rotateAngleX = rightArm.rotateAngleX;
		rightSleeve.render(scale);

		// Render left leg and pant leg
		leftLeg.render(scale);
		leftPantLeg.rotateAngleX = leftLeg.rotateAngleX;
		leftPantLeg.render(scale);

		// Render right leg and pant leg
		rightLeg.render(scale);
		rightPantLeg.rotateAngleX = rightLeg.rotateAngleX;
		rightPantLeg.render(scale);
	}

	@Override
	public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw,
	                              float headPitch, float scaleFactor, Entity entityIn) {
	    // HEAD: follow yaw & pitch
	    head.rotateAngleY = netHeadYaw / 57.295776f;
	    head.rotateAngleX = headPitch / 57.295776f;

	    // Classic vanilla Biped walk animation (arms/legs alternate)
	    rightArm.rotateAngleX = MathHelper.cos(limbSwing * 0.6662f + (float)Math.PI) * 2.0f * limbSwingAmount * 0.5f;
	    leftArm.rotateAngleX  = MathHelper.cos(limbSwing * 0.6662f) * 2.0f * limbSwingAmount * 0.5f;
	    rightArm.rotateAngleZ = 0.0f;
	    leftArm.rotateAngleZ  = 0.0f;

	    rightLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662f) * 1.4f * limbSwingAmount;
	    leftLeg.rotateAngleX  = MathHelper.cos(limbSwing * 0.6662f + (float)Math.PI) * 1.4f * limbSwingAmount;
	    rightLeg.rotateAngleY = 0.0f;
	    leftLeg.rotateAngleY  = 0.0f;

	    // --- Sync helmet etc (if you want)
	    helmet.rotateAngleY = head.rotateAngleY;
	    helmet.rotateAngleX = head.rotateAngleX;

	    // --- Idle/living arm sway (matches vanilla ModelBiped and your ModelHuman)
	    rightArm.rotateAngleZ += MathHelper.cos(ageInTicks * 0.09f) * 0.05f + 0.05f;
	    leftArm.rotateAngleZ  -= MathHelper.cos(ageInTicks * 0.09f) * 0.05f + 0.05f;
	    rightArm.rotateAngleX += MathHelper.sin(ageInTicks * 0.067f) * 0.05f;
	    leftArm.rotateAngleX  -= MathHelper.sin(ageInTicks * 0.067f) * 0.05f;
	}
}
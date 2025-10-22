package com.sown.outerrim.entities;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class ModelWookieeFemale extends ModelBase {
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

	public ModelWookieeFemale() {
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

		// Attach beard and scalp to head so they follow head rotations:
		head.addChild(beardLeft);
		head.addChild(scalpLeft);
		head.addChild(beardRight);
		head.addChild(scalpRight);
		head.addChild(beardCenter);

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
		leftArm.addBox(-1F, -2F, -2F, 3, 12, 4, 0F);

		leftSleeve = new ModelRenderer(this, 40, 33);
		leftSleeve.mirror = true;
		leftSleeve.setRotationPoint(5F, -1F, 0F);
		leftSleeve.addBox(-1F, -2F, -2F, 3, 12, 4, 0.25F);

		// ----- RIGHT ARM and sleeve -----
		rightArm = new ModelRenderer(this, 40, 16);
		rightArm.setRotationPoint(-4F, -1F, 0F);
		rightArm.addBox(-3F, -2F, -2F, 3, 12, 4, 0F);

		rightSleeve = new ModelRenderer(this, 40, 33);
		rightSleeve.setRotationPoint(-4F, -1F, 0F);
		rightSleeve.addBox(-3F, -2F, -2F, 3, 12, 4, 0.25F);

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
		head.rotateAngleY = netHeadYaw * 0.017453292F;
		head.rotateAngleX = headPitch * 0.017453292F;

		// ARMS & LEGS SWING during walking
		float swingCos = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
		float swingOpp = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount;

		leftArm.rotateAngleX = swingCos;
		rightArm.rotateAngleX = swingOpp;
		leftLeg.rotateAngleX = swingOpp;
		rightLeg.rotateAngleX = swingCos;
	}
}

package com.sown.outerrim.entities;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

// Made with Blockbench 4.8.3
// Exported for Minecraft version 1.7 - 1.12
// Paste this class into your mod and generate all required imports


public class Bantha extends ModelBase {
	private final ModelRenderer bone;
	private final ModelRenderer body;
	private final ModelRenderer armscore;
	private final ModelRenderer leftFrontLeg;
	private final ModelRenderer leftFrontLeg2;
	private final ModelRenderer rightFrontLeg;
	private final ModelRenderer rightFrontLeg2;
	private final ModelRenderer legscore;
	private final ModelRenderer leftBackLeg;
	private final ModelRenderer leftBackLeg2;
	private final ModelRenderer rightBackLeg;
	private final ModelRenderer rightBackLeg2;
	private final ModelRenderer torso;
	private final ModelRenderer tail1;
	private final ModelRenderer tail2;
	private final ModelRenderer tail3;
	private final ModelRenderer tailFan;
	private final ModelRenderer bodyFur;
	private final ModelRenderer neck;
	private final ModelRenderer head;
	private final ModelRenderer brow;
	private final ModelRenderer furBrow;
	private final ModelRenderer mouth;
	private final ModelRenderer jaw;
	private final ModelRenderer jawFur;
	private final ModelRenderer headFur;
	private final ModelRenderer hornLeft1;
	private final ModelRenderer hornLeft2;
	private final ModelRenderer hornLeft3;
	private final ModelRenderer hornLeft4;
	private final ModelRenderer hornRight1;
	private final ModelRenderer hornRight2;
	private final ModelRenderer hornRight3;
	private final ModelRenderer hornRight4;

	public Bantha() {
		textureWidth = 256;
		textureHeight = 256;

		bone = new ModelRenderer(this);
		bone.setRotationPoint(0.0F, 0.0F, 0.0F);
		

		body = new ModelRenderer(this);
		body.setRotationPoint(0.0F, -8.0F, 0.0F);
		

		armscore = new ModelRenderer(this);
		armscore.setRotationPoint(-7.0F, 14.0F, -17.0F);
		body.addChild(armscore);
		

		leftFrontLeg = new ModelRenderer(this);
		leftFrontLeg.setRotationPoint(0.0F, 0.0F, 0.0F);
		armscore.addChild(leftFrontLeg);
		leftFrontLeg.cubeList.add(new ModelBox(leftFrontLeg, 0, 110, -4.5F, 0.0F, 0.0F, 9, 9, 10, 0.0F));

		leftFrontLeg2 = new ModelRenderer(this);
		leftFrontLeg2.setRotationPoint(0.0F, 9.0F, 0.0F);
		leftFrontLeg.addChild(leftFrontLeg2);
		leftFrontLeg2.cubeList.add(new ModelBox(leftFrontLeg2, 0, 129, -4.5F, 0.0F, 0.0F, 9, 9, 10, -0.01F));

		rightFrontLeg = new ModelRenderer(this);
		rightFrontLeg.setRotationPoint(14.0F, 0.0F, 0.0F);
		armscore.addChild(rightFrontLeg);
		rightFrontLeg.cubeList.add(new ModelBox(rightFrontLeg, 0, 110, -4.5F, 0.0F, 0.0F, 9, 9, 10, 0.0F));

		rightFrontLeg2 = new ModelRenderer(this);
		rightFrontLeg2.setRotationPoint(0.0F, 9.0F, 0.0F);
		rightFrontLeg.addChild(rightFrontLeg2);
		rightFrontLeg2.cubeList.add(new ModelBox(rightFrontLeg2, 0, 129, -4.5F, 0.0F, 0.0F, 9, 9, 10, -0.01F));

		legscore = new ModelRenderer(this);
		legscore.setRotationPoint(-7.0F, 14.0F, 7.0F);
		body.addChild(legscore);
		

		leftBackLeg = new ModelRenderer(this);
		leftBackLeg.setRotationPoint(0.0F, 0.0F, 0.0F);
		legscore.addChild(leftBackLeg);
		leftBackLeg.cubeList.add(new ModelBox(leftBackLeg, 0, 110, -4.5F, 0.0F, 0.0F, 9, 9, 10, 0.0F));

		leftBackLeg2 = new ModelRenderer(this);
		leftBackLeg2.setRotationPoint(0.0F, 9.0F, 0.0F);
		leftBackLeg.addChild(leftBackLeg2);
		leftBackLeg2.cubeList.add(new ModelBox(leftBackLeg2, 0, 129, -4.5F, 0.0F, 0.0F, 9, 9, 10, -0.01F));

		rightBackLeg = new ModelRenderer(this);
		rightBackLeg.setRotationPoint(14.0F, 0.0F, 0.0F);
		legscore.addChild(rightBackLeg);
		rightBackLeg.cubeList.add(new ModelBox(rightBackLeg, 0, 110, -4.5F, 0.0F, 0.0F, 9, 9, 10, 0.0F));

		rightBackLeg2 = new ModelRenderer(this);
		rightBackLeg2.setRotationPoint(0.0F, 9.0F, 0.0F);
		rightBackLeg.addChild(rightBackLeg2);
		rightBackLeg2.cubeList.add(new ModelBox(rightBackLeg2, 0, 129, -4.5F, 0.0F, 0.0F, 9, 9, 10, -0.01F));

		torso = new ModelRenderer(this);
		torso.setRotationPoint(0.0F, 0.0F, 0.0F);
		legscore.addChild(torso);
		torso.cubeList.add(new ModelBox(torso, 0, 0, -5.5F, -28.0F, -25.0F, 25, 28, 36, 0.0F));

		tail1 = new ModelRenderer(this);
		tail1.setRotationPoint(7.0F, -25.0F, 11.5F);
		torso.addChild(tail1);
		setRotationAngle(tail1, 0.3491F, 0.0F, 0.0F);
		tail1.cubeList.add(new ModelBox(tail1, 0, 64, -7.0F, 0.0F, -12.0F, 14, 34, 12, 0.0F));

		tail2 = new ModelRenderer(this);
		tail2.setRotationPoint(0.0F, 34.0F, -12.0F);
		tail1.addChild(tail2);
		setRotationAngle(tail2, 0.9599F, 0.0F, 0.0F);
		tail2.cubeList.add(new ModelBox(tail2, 52, 64, -6.0F, 0.0F, 0.0F, 12, 30, 10, 0.0F));

		tail3 = new ModelRenderer(this);
		tail3.setRotationPoint(0.0F, 30.0F, 0.0F);
		tail2.addChild(tail3);
		setRotationAngle(tail3, 0.2618F, 0.0F, 0.0F);
		tail3.cubeList.add(new ModelBox(tail3, 96, 73, -7.5F, 0.0F, 0.0F, 15, 25, 10, 0.0F));

		tailFan = new ModelRenderer(this);
		tailFan.setRotationPoint(0.0F, 1.0F, 7.0F);
		tail3.addChild(tailFan);
		tailFan.cubeList.add(new ModelBox(tailFan, 90, 0, -11.0F, 0.0F, -6.0F, 22, 29, 6, 0.0F));

		bodyFur = new ModelRenderer(this);
		bodyFur.setRotationPoint(7.0F, -28.0F, -7.0F);
		torso.addChild(bodyFur);
		bodyFur.cubeList.add(new ModelBox(bodyFur, 122, 0, -13.0F, 0.0F, -18.5F, 26, 36, 37, 0.0F));

		neck = new ModelRenderer(this);
		neck.setRotationPoint(7.0F, -14.0F, -16.0F);
		torso.addChild(neck);
		neck.cubeList.add(new ModelBox(neck, 0, 174, -5.0F, -7.0F, -12.0F, 10, 14, 16, 0.0F));

		head = new ModelRenderer(this);
		head.setRotationPoint(0.0F, -1.0F, -11.0F);
		neck.addChild(head);
		head.cubeList.add(new ModelBox(head, 154, 124, -9.0F, -12.0F, -14.0F, 18, 26, 16, 0.0F));

		brow = new ModelRenderer(this);
		brow.setRotationPoint(0.0F, -12.0F, -14.0F);
		head.addChild(brow);
		brow.cubeList.add(new ModelBox(brow, 146, 73, -9.0F, 0.0F, -4.0F, 18, 10, 4, 0.0F));

		furBrow = new ModelRenderer(this);
		furBrow.setRotationPoint(0.0F, 10.0F, -2.0F);
		brow.addChild(furBrow);
		furBrow.cubeList.add(new ModelBox(furBrow, 146, 87, -9.0F, 0.0F, -2.0F, 18, 5, 4, 0.0F));

		mouth = new ModelRenderer(this);
		mouth.setRotationPoint(0.0F, 9.5F, -12.0F);
		head.addChild(mouth);
		mouth.cubeList.add(new ModelBox(mouth, 42, 108, -10.0F, -3.0F, -7.0F, 20, 3, 12, 0.0F));

		jaw = new ModelRenderer(this);
		jaw.setRotationPoint(0.0F, 0.0F, 0.0F);
		mouth.addChild(jaw);
		jaw.cubeList.add(new ModelBox(jaw, 42, 123, -9.5F, 0.0F, -6.0F, 19, 2, 11, 0.0F));

		jawFur = new ModelRenderer(this);
		jawFur.setRotationPoint(0.0F, 2.0F, -2.5F);
		jaw.addChild(jawFur);
		jawFur.cubeList.add(new ModelBox(jawFur, 106, 108, -7.0F, 0.0F, -2.5F, 14, 12, 10, 0.0F));

		headFur = new ModelRenderer(this);
		headFur.setRotationPoint(0.0F, 14.0F, -14.0F);
		head.addChild(headFur);
		headFur.cubeList.add(new ModelBox(headFur, 154, 96, -9.0F, 0.0F, 0.0F, 18, 12, 16, 0.0F));

		hornLeft1 = new ModelRenderer(this);
		hornLeft1.setRotationPoint(-7.0F, -10.0F, -14.0F);
		head.addChild(hornLeft1);
		setRotationAngle(hornLeft1, 0.4363F, -0.6109F, 0.0F);
		hornLeft1.cubeList.add(new ModelBox(hornLeft1, 0, 150, -4.0F, -2.0F, 0.0F, 8, 8, 16, 0.0F));

		hornLeft2 = new ModelRenderer(this);
		hornLeft2.setRotationPoint(0.0F, -2.0F, 16.0F);
		hornLeft1.addChild(hornLeft2);
		setRotationAngle(hornLeft2, 0.2618F, 0.1396F, 0.0F);
		hornLeft2.cubeList.add(new ModelBox(hornLeft2, 48, 150, -3.0F, 0.0F, -7.0F, 6, 18, 7, 0.0F));

		hornLeft3 = new ModelRenderer(this);
		hornLeft3.setRotationPoint(0.0F, 18.0F, 0.0F);
		hornLeft2.addChild(hornLeft3);
		setRotationAngle(hornLeft3, 0.2618F, 0.1745F, 0.0F);
		hornLeft3.cubeList.add(new ModelBox(hornLeft3, 74, 150, -2.0F, -6.0F, -15.0F, 4, 6, 15, 0.0F));

		hornLeft4 = new ModelRenderer(this);
		hornLeft4.setRotationPoint(0.0F, 0.0F, -15.0F);
		hornLeft3.addChild(hornLeft4);
		setRotationAngle(hornLeft4, -1.2217F, 0.0F, -0.1745F);
		hornLeft4.cubeList.add(new ModelBox(hornLeft4, 112, 150, -1.0F, -4.0F, -13.0F, 3, 4, 13, 0.0F));

		hornRight1 = new ModelRenderer(this);
		hornRight1.setRotationPoint(7.0F, -10.0F, -14.0F);
		head.addChild(hornRight1);
		setRotationAngle(hornRight1, 0.4363F, 0.6109F, 0.0F);
		hornRight1.cubeList.add(new ModelBox(hornRight1, 0, 150, -4.0F, -2.0F, 0.0F, 8, 8, 16, 0.0F));

		hornRight2 = new ModelRenderer(this);
		hornRight2.setRotationPoint(0.0F, -2.0F, 16.0F);
		hornRight1.addChild(hornRight2);
		setRotationAngle(hornRight2, 0.2618F, -0.1396F, 0.0F);
		hornRight2.cubeList.add(new ModelBox(hornRight2, 48, 150, -3.0F, 0.0F, -7.0F, 6, 18, 7, 0.0F));

		hornRight3 = new ModelRenderer(this);
		hornRight3.setRotationPoint(0.0F, 18.0F, 0.0F);
		hornRight2.addChild(hornRight3);
		setRotationAngle(hornRight3, 0.2618F, -0.1745F, 0.0F);
		hornRight3.cubeList.add(new ModelBox(hornRight3, 74, 150, -2.0F, -6.0F, -15.0F, 4, 6, 15, 0.0F));

		hornRight4 = new ModelRenderer(this);
		hornRight4.setRotationPoint(0.0F, 0.0F, -15.0F);
		hornRight3.addChild(hornRight4);
		setRotationAngle(hornRight4, -1.2217F, 0.0F, 0.1745F);
		hornRight4.cubeList.add(new ModelBox(hornRight4, 112, 150, -1.0F, -4.0F, -13.0F, 3, 4, 13, 0.0F));
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		bone.render(f5);
		body.render(f5);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}
package com.sown.outerrim.entities;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;

public class ModelSmugglerHanSolo extends ModelBiped {
	public ModelRenderer hair_1;
	public ModelRenderer hair_2;
	public ModelRenderer hair_3;
	public ModelRenderer hair_4;
	public ModelRenderer hair_5;
	public ModelRenderer hair_6;
	public ModelRenderer hair_7;
	public ModelRenderer hair_8;
	public ModelRenderer hair_9;
	public ModelRenderer hair_10;
	public ModelRenderer hair_11;
	public ModelRenderer holster_1;
	public ModelRenderer holster_2;
	public ModelRenderer holster_3;
	public ModelRenderer holster_4;
	public ModelRenderer holster_4_clone;
	public ModelRenderer holster_5;
	public ModelRenderer holster_6;
	public ModelRenderer holster_7;

	public ModelSmugglerHanSolo() {
		this.textureWidth = 64;
		this.textureHeight = 64;
		this.hair_3 = new ModelRenderer(this, 32, 12);
		this.hair_3.setRotationPoint(-4.5F, -8.5F, -4.5F);
		this.hair_3.addBox(0.0F, 0.0F, 0.0F, 4, 1, 3, 0.0F);
		this.bipedHead = new ModelRenderer(this, 0, 0);
		this.bipedHead.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.bipedHead.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, 0.0F);
		this.holster_5 = new ModelRenderer(this, 56, 0);
		this.holster_5.setRotationPoint(1.0F, -1.0F, 0.0F);
		this.holster_5.addBox(0.0F, 0.0F, 0.0F, 3, 3, 0, 0.0F);
		this.hair_8 = new ModelRenderer(this, 50, 11);
		this.hair_8.setRotationPoint(-4.5F, -6.5F, -2.5F);
		this.hair_8.addBox(0.0F, 0.0F, 0.0F, 1, 1, 1, 0.0F);
		this.holster_4 = new ModelRenderer(this, 24, 3);
		this.holster_4.setRotationPoint(-2.01F, 5.0F, -2.01F);
		this.holster_4.addBox(0.0F, 0.0F, 0.0F, 4, 1, 4, 0.0F);
		this.hair_5 = new ModelRenderer(this, 46, 11);
		this.hair_5.setRotationPoint(-4.5F, -7.5F, -4.5F);
		this.hair_5.addBox(0.0F, 0.0F, 0.0F, 2, 1, 4, 0.0F);
		this.hair_2 = new ModelRenderer(this, 45, 5);
		this.hair_2.setRotationPoint(3.5F, -6.5F, -4.5F);
		this.hair_2.addBox(0.0F, 0.0F, 0.0F, 1, 1, 3, 0.0F);
		this.bipedLeftLeg = new ModelRenderer(this, 16, 48);
		this.bipedLeftLeg.setRotationPoint(1.9F, 12.0F, 0.0F);
		this.bipedLeftLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.0F);
		this.hair_10 = new ModelRenderer(this, 50, 11);
		this.hair_10.setRotationPoint(-1.0F, 0.0F, 0.0F);
		this.hair_10.addBox(0.0F, 0.0F, 0.0F, 1, 1, 1, 0.0F);
		this.hair_7 = new ModelRenderer(this, 50, 11);
		this.hair_7.setRotationPoint(-4.5F, -6.5F, -4.5F);
		this.hair_7.addBox(0.0F, 0.0F, 0.0F, 1, 1, 1, 0.0F);
		this.hair_4 = new ModelRenderer(this, 36, 0);
		this.hair_4.setRotationPoint(1.5F, -8.5F, -4.5F);
		this.hair_4.addBox(0.0F, 0.0F, 0.0F, 3, 1, 4, 0.0F);
		this.bipedLeftArm = new ModelRenderer(this, 32, 48);
		this.bipedLeftArm.setRotationPoint(5.0F, 2.0F, 0.0F);
		this.bipedLeftArm.addBox(-1.0F, -2.0F, -2.0F, 4, 12, 4, 0.0F);
		this.holster_2 = new ModelRenderer(this, 36, 16);
		this.holster_2.setRotationPoint(0.0F, 0.0F, 3.0F);
		this.holster_2.addBox(0.0F, 0.0F, 0.0F, 1, 1, 1, 0.0F);
		this.holster_4_clone = new ModelRenderer(this, 24, 3);
		this.holster_4_clone.setRotationPoint(-1.99F, 5.0F, -1.99F);
		this.holster_4_clone.addBox(0.0F, 0.0F, 0.0F, 4, 1, 4, 0.0F);
		this.hair_1 = new ModelRenderer(this, 36, 5);
		this.hair_1.setRotationPoint(2.5F, -7.5F, -4.5F);
		this.hair_1.addBox(0.0F, 0.0F, 0.0F, 2, 1, 5, 0.0F);
		this.hair_11 = new ModelRenderer(this, 50, 11);
		this.hair_11.setRotationPoint(1.0F, 0.0F, 1.0F);
		this.hair_11.addBox(0.0F, 0.0F, 0.0F, 1, 1, 1, 0.0F);
		this.bipedRightLeg = new ModelRenderer(this, 0, 16);
		this.bipedRightLeg.setRotationPoint(-1.9F, 12.0F, 0.0F);
		this.bipedRightLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.0F);
		this.bipedRightArm = new ModelRenderer(this, 40, 16);
		this.bipedRightArm.setRotationPoint(-5.0F, 2.0F, 0.0F);
		this.bipedRightArm.addBox(-3.0F, -2.0F, -2.0F, 4, 12, 4, 0.0F);
		this.holster_6 = new ModelRenderer(this, 58, 4);
		this.holster_6.setRotationPoint(1.0F, -1.0F, 4.2F);
		this.holster_6.addBox(0.0F, 0.0F, 0.0F, 3, 3, 0, 0.0F);
		this.holster_1 = new ModelRenderer(this, 0, 0);
		this.holster_1.setRotationPoint(-3.0F, 1.0F, -2.1F);
		this.holster_1.addBox(0.0F, 0.0F, 0.0F, 1, 3, 3, 0.0F);
		this.hair_6 = new ModelRenderer(this, 50, 11);
		this.hair_6.setRotationPoint(-4.5F, -6.5F, -0.5F);
		this.hair_6.addBox(0.0F, 0.0F, 0.0F, 1, 1, 1, 0.0F);
		this.holster_3 = new ModelRenderer(this, 36, 16);
		this.holster_3.setRotationPoint(0.0F, 3.0F, 0.0F);
		this.holster_3.addBox(0.0F, 0.0F, 0.0F, 1, 2, 1, 0.0F);
		this.bipedBody = new ModelRenderer(this, 16, 16);
		this.bipedBody.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.bipedBody.addBox(-4.0F, 0.0F, -2.0F, 8, 12, 4, 0.0F);
		this.hair_9 = new ModelRenderer(this, 50, 11);
		this.hair_9.setRotationPoint(-1.5F, -8.5F, -1.5F);
		this.hair_9.addBox(0.0F, 0.0F, 0.0F, 1, 1, 1, 0.0F);
		this.holster_7 = new ModelRenderer(this, 56, 6);
		this.holster_7.setRotationPoint(-0.1F, 2.0F, -1.0F);
		this.holster_7.addBox(0.0F, 0.0F, 0.0F, 0, 1, 1, 0.0F);
		this.bipedHead.addChild(this.hair_3);
		this.holster_1.addChild(this.holster_5);
		this.bipedHead.addChild(this.hair_8);
		this.bipedRightLeg.addChild(this.holster_4);
		this.bipedHead.addChild(this.hair_5);
		this.bipedHead.addChild(this.hair_2);
		this.hair_9.addChild(this.hair_10);
		this.bipedHead.addChild(this.hair_7);
		this.bipedHead.addChild(this.hair_4);
		this.holster_1.addChild(this.holster_2);
		this.bipedRightLeg.addChild(this.holster_4_clone);
		this.bipedHead.addChild(this.hair_1);
		this.hair_10.addChild(this.hair_11);
		this.holster_1.addChild(this.holster_6);
		this.bipedRightLeg.addChild(this.holster_1);
		this.bipedHead.addChild(this.hair_6);
		this.holster_1.addChild(this.holster_3);
		this.bipedHead.addChild(this.hair_9);
		this.holster_6.addChild(this.holster_7);
		this.bipedHeadwear.showModel = false;
	}

	protected void adjustEP(EntityPlayer entity, ItemStack stack) {
		this.heldItemRight = (stack != null) ? 1 : 0;
		this.isSneak = entity.isSneaking();

		if (stack != null && entity.getItemInUseCount() > 0) {
			EnumAction enumaction = stack.getItemUseAction();
			if (enumaction == EnumAction.bow) {
				this.aimedBow = true;
			} else if (enumaction == EnumAction.block) {
				this.bipedRightArm.rotateAngleX = -0.63F;
				this.bipedRightArm.rotateAngleY = 0.0F;
				this.bipedRightArm.rotateAngleZ = 0.0F;
			} else {
				this.aimedBow = false;
			}
		} else {
			this.aimedBow = false;
		}
	}

	protected void adjustEL(EntityLivingBase entity, ItemStack stack) {
		this.heldItemRight = (stack != null) ? 1 : 0;
		this.isSneak = entity.isSneaking();
		this.aimedBow = false;
	}

	@Override
	public void render(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw,
			float bipedHeadPitch, float scale) {

		if (entity instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) entity;
			this.adjustEP(player, player.getHeldItem());
		} else if (entity instanceof EntityLivingBase) {
			EntityLivingBase el = (EntityLivingBase) entity;
			this.adjustEL(el, el.getHeldItem());
		}

		super.render(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, bipedHeadPitch, scale);
	}

	@Override
	public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw,
			float bipedHeadPitch, float scaleFactor, Entity entityIn) {
		super.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, bipedHeadPitch, scaleFactor,
				entityIn);
	}
}

package com.sown.outerrim.entities;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;

public class ModelCountDooku extends ModelBiped {
	public ModelRenderer jacket;
	public ModelRenderer leftSleeve;
	public ModelRenderer rightSleeve;
	public ModelRenderer leftPantLeg;
	public ModelRenderer rightPantLeg;
	public ModelRenderer beard;

	public ModelCountDooku() {
		this.textureWidth = 128;
		this.textureHeight = 128;
		this.bipedHead = new ModelRenderer(this, 0, 1);
		this.bipedHead.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.bipedHead.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, 0.0F);
		this.bipedBody = new ModelRenderer(this, 0, 17);
		this.bipedBody.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.bipedBody.addBox(-4.0F, 0.0F, -2.0F, 8, 12, 4, 0.0F);
		this.bipedRightArm = new ModelRenderer(this, 48, 17);
		this.bipedRightArm.setRotationPoint(-5.0F, 2.0F, 0.0F);
		this.bipedRightArm.addBox(-3.0F, -2.0F, -2.0F, 4, 12, 4, 0.0F);
		this.bipedRightLeg = new ModelRenderer(this, 0, 33);
		this.bipedRightLeg.setRotationPoint(-1.9F, 12.0F, 0.0F);
		this.bipedRightLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.0F);
		this.bipedLeftLeg = new ModelRenderer(this, 16, 33);
		this.bipedLeftLeg.setRotationPoint(1.9F, 12.0F, 0.0F);
		this.bipedLeftLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.0F);
		this.rightSleeve = new ModelRenderer(this, 16, 49);
		this.rightSleeve.mirror = true;
		this.rightSleeve.addBox(-3.0F, -2.0F, -2.0F, 4, 12, 4, 0.25F);
		this.bipedRightArm.addChild(rightSleeve);
		this.bipedLeftArm = new ModelRenderer(this, 48, 17);
		this.bipedLeftArm.mirror = true;
		this.bipedLeftArm.setRotationPoint(5.0F, 2.0F, 0.0F);
		this.bipedLeftArm.addBox(-1.0F, -2.0F, -2.0F, 4, 12, 4, 0.0F);
		this.leftPantLeg = new ModelRenderer(this, 0, 49);
		this.leftPantLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.25F);
		this.bipedLeftLeg.addChild(leftPantLeg);
		this.beard = new ModelRenderer(this, 93, 21);
		this.beard.setRotationPoint(-4.0F, 0.0F, -4.0F);
		this.beard.addBox(0.0F, 0.0F, 0.0F, 8, 1, 2, 0.0F);
		this.bipedCloak = new ModelRenderer(this, 42, 33);
		this.bipedCloak.setRotationPoint(-7.0F, 0.0F, 2.0F);
		this.bipedCloak.addBox(0.0F, 0.0F, 0.0F, 14, 22, 1, 0.0F);
		this.setRotateAngle(bipedCloak, 0.17453292519943295F, 0.0F, 0.0F);
		this.leftSleeve = new ModelRenderer(this, 16, 49);
		this.leftSleeve.addBox(-1.0F, -2.0F, -2.0F, 4, 12, 4, 0.25F);
		this.bipedLeftArm.addChild(leftSleeve);
		this.jacket = new ModelRenderer(this, 24, 17);
		this.jacket.addBox(-4.0F, 0.0F, -2.0F, 8, 12, 4, 0.25F);
		this.bipedBody.addChild(jacket);
		this.rightPantLeg = new ModelRenderer(this, 0, 49);
		this.rightPantLeg.mirror = true;
		this.rightPantLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.25F);
		this.bipedRightLeg.addChild(rightPantLeg);
		this.bipedHead.addChild(this.beard);
		this.bipedBody.addChild(this.bipedCloak);
		this.bipedHeadwear.showModel = false;
	}

	private void setRotateAngle(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
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
		this.aimedBow = false; // NPCs never draw a bow automatically
	}

	private void animateCloak(EntityLivingBase entity, float ageInTicks) {
		// how far we moved since last tick
		double dx = entity.posX - entity.prevPosX;
		double dy = entity.posY - entity.prevPosY;
		double dz = entity.posZ - entity.prevPosZ;

		// entity yaw for local-space transform
		float yaw = entity.prevRenderYawOffset + (entity.renderYawOffset - entity.prevRenderYawOffset);
		float sinY = MathHelper.sin(yaw * (float) Math.PI / 180F);
		float cosY = -MathHelper.cos(yaw * (float) Math.PI / 180F);

		// forward & side components
		float forward = (float) (dx * sinY + dz * cosY) * 10F;
		float side = (float) (dx * cosY - dz * sinY) * 10F;

		// clamp so it doesn’t flap through the floor or over the head
		forward = MathHelper.clamp_float(forward, 0F, 40F);
		side = MathHelper.clamp_float(side, -5F, 5F);

		// small idle bob
		float bob = MathHelper.sin(ageInTicks * 0.02F) * 2F;

		// apply
		this.bipedCloak.rotateAngleX = (6F + forward / 2F + (float) dy * 10F + bob) * (float) Math.PI / 180F;
		this.bipedCloak.rotateAngleZ = side * (float) Math.PI / 180F;
		this.bipedCloak.rotateAngleY = 0F;
	}

	@Override
	public void render(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw,
			float headPitch, float scale) {
		if (entity instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) entity;
			this.adjustEP(player, player.getHeldItem());
		} else if (entity instanceof EntityLivingBase) {
			EntityLivingBase el = (EntityLivingBase) entity;
			this.adjustEL(el, el.getHeldItem());
		}

		// animate cloak before the vanilla model render so it's in place
		animateCloak((EntityLivingBase) entity, ageInTicks);

		super.render(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
	}

	@Override
	public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw,
			float headPitch, float scaleFactor, Entity entityIn) {
		super.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor, entityIn);

		if (entityIn instanceof EntityLivingBase) {
			EntityLivingBase e = (EntityLivingBase) entityIn;

			// get horizontal speed
			float speed = (float) Math.hypot(e.motionX, e.motionZ);

			float targetX;
			if (speed > 0.005F) {
				// up to 240° forward swing, ±30° bob
				float sway = MathHelper.clamp_float((speed - 0.005F) / 0.295F * 240F, 0F, 240F);
				float bob = MathHelper.sin((e.ticksExisted + ageInTicks) * 0.1F) * 30F;
				targetX = (15F + sway + bob) * ((float) Math.PI / 180F);
			} else {
				// default hang at 15°
				targetX = 15F * ((float) Math.PI / 180F);
			}

			// smooth toward targetX
			bipedCloak.rotateAngleX += (targetX - bipedCloak.rotateAngleX) * 0.3F;
			bipedCloak.rotateAngleY = 0F;
			bipedCloak.rotateAngleZ = 0F; // no side tilt
		}
	}
}
package com.sown.outerrim.entities;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;

public class ModelGM12L1 extends ModelBiped {
    public ModelRenderer jacket;
    public ModelRenderer leftSleeve;
    public ModelRenderer rightSleeve;
    public ModelRenderer leftPantLeg;
    public ModelRenderer rightPantLeg;
    public ModelRenderer backLeft;
    public ModelRenderer backRight;

    public ModelGM12L1() {
        this.textureWidth = 64;
        this.textureHeight = 64;
        this.bipedLeftLeg = new ModelRenderer(this, 16, 48);
        this.bipedLeftLeg.setRotationPoint(1.9F, 12.0F, 0.0F);
        this.bipedLeftLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.0F);
        this.backLeft = new ModelRenderer(this, 32, 48);
        this.backLeft.setRotationPoint(0.0F, 0.0F, 2.0F);
        this.backLeft.addBox(0.0F, 0.0F, 0.0F, 4, 10, 4, 0.0F);
        this.leftSleeve = new ModelRenderer(this, 48, 48);
        this.leftSleeve.mirror = true;
        this.leftSleeve.setRotationPoint(5.0F, 2.0F, 0.0F);
        this.leftSleeve.addBox(-1.0F, -2.0F, -2.0F, 4, 12, 4, 0.25F);
        this.leftPantLeg = new ModelRenderer(this, 0, 48);
        this.leftPantLeg.setRotationPoint(1.9F, 12.0F, 0.0F);
        this.leftPantLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.25F);
        this.jacket = new ModelRenderer(this, 16, 32);
        this.jacket.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.jacket.addBox(-4.0F, 0.0F, -2.0F, 8, 12, 4, 0.25F);
        this.rightSleeve = new ModelRenderer(this, 40, 32);
        this.rightSleeve.setRotationPoint(-5.0F, 2.0F, 0.0F);
        this.rightSleeve.addBox(-3.0F, -2.0F, -2.0F, 4, 12, 4, 0.25F);
        this.rightPantLeg = new ModelRenderer(this, 0, 32);
        this.rightPantLeg.setRotationPoint(-1.9F, 12.0F, 0.0F);
        this.rightPantLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.25F);
        this.bipedLeftArm = new ModelRenderer(this, 40, 16);
        this.bipedLeftArm.mirror = true;
        this.bipedLeftArm.setRotationPoint(5.0F, 2.0F, 0.0F);
        this.bipedLeftArm.addBox(-1.0F, -2.0F, -2.0F, 4, 12, 4, 0.0F);
        this.bipedRightLeg = new ModelRenderer(this, 0, 16);
        this.bipedRightLeg.setRotationPoint(-1.9F, 12.0F, 0.0F);
        this.bipedRightLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.0F);
        this.bipedHead = new ModelRenderer(this, 0, 0);
        this.bipedHead.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.bipedHead.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, 0.0F);
        this.bipedHeadwear = new ModelRenderer(this, 32, 0);
        this.bipedHeadwear.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.bipedHeadwear.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, 0.5F);
        this.bipedRightArm = new ModelRenderer(this, 40, 16);
        this.bipedRightArm.setRotationPoint(-5.0F, 2.0F, 0.0F);
        this.bipedRightArm.addBox(-3.0F, -2.0F, -2.0F, 4, 12, 4, 0.0F);
        this.backRight = new ModelRenderer(this, 32, 48);
        this.backRight.mirror = true;
        this.backRight.setRotationPoint(-4.0F, 0.0F, 2.0F);
        this.backRight.addBox(0.0F, 0.0F, 0.0F, 4, 10, 4, 0.0F);
        this.bipedBody = new ModelRenderer(this, 16, 16);
        this.bipedBody.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.bipedBody.addBox(-4.0F, 0.0F, -2.0F, 8, 12, 4, 0.0F);
        this.bipedBody.addChild(this.backLeft);
        this.bipedBody.addChild(this.backRight);
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
    public void render(Entity entity, float limbSwing, float limbSwingAmount,
                       float ageInTicks, float netHeadYaw, float headPitch, float scale) {
        // Copy parent rotations into overlays before calling super.render
        // Jacket matches body:
        this.jacket.rotateAngleX = this.bipedBody.rotateAngleX;
        this.jacket.rotateAngleY = this.bipedBody.rotateAngleY;
        this.jacket.rotateAngleZ = this.bipedBody.rotateAngleZ;

        // Sleeves match arms:
        this.leftSleeve.rotateAngleX  = this.bipedLeftArm.rotateAngleX;
        this.leftSleeve.rotateAngleY  = this.bipedLeftArm.rotateAngleY;
        this.leftSleeve.rotateAngleZ  = this.bipedLeftArm.rotateAngleZ;
        this.rightSleeve.rotateAngleX = this.bipedRightArm.rotateAngleX;
        this.rightSleeve.rotateAngleY = this.bipedRightArm.rotateAngleY;
        this.rightSleeve.rotateAngleZ = this.bipedRightArm.rotateAngleZ;

        // Pant legs match legs:
        this.leftPantLeg.rotateAngleX  = this.bipedLeftLeg.rotateAngleX;
        this.leftPantLeg.rotateAngleY  = this.bipedLeftLeg.rotateAngleY;
        this.leftPantLeg.rotateAngleZ  = this.bipedLeftLeg.rotateAngleZ;
        this.rightPantLeg.rotateAngleX = this.bipedRightLeg.rotateAngleX;
        this.rightPantLeg.rotateAngleY = this.bipedRightLeg.rotateAngleY;
        this.rightPantLeg.rotateAngleZ = this.bipedRightLeg.rotateAngleZ;

        // Update aimedBow / isSneak first
        if (entity instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) entity;
            this.adjustEP(player, player.getHeldItem());
        } else if (entity instanceof EntityLivingBase) {
            EntityLivingBase el = (EntityLivingBase) entity;
            this.adjustEL(el, el.getHeldItem());
        }

        // Draw overlays before the main biped, so they line up perfectly
        this.jacket.render(scale);
        this.leftSleeve.render(scale);
        this.rightSleeve.render(scale);
        this.leftPantLeg.render(scale);
        this.rightPantLeg.render(scale);

        // Now render the base biped (head, body, arms, legs)
        super.render(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
    }

    @Override
    public void setRotationAngles(float limbSwing, float limbSwingAmount,
                                  float ageInTicks, float netHeadYaw,
                                  float headPitch, float scaleFactor,
                                  Entity entityIn) {
        // Let ModelBiped set head/arm/leg rotations first
        super.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor, entityIn);

        // Then immediately copy those rotations into the overlays so they remain in sync:
        this.jacket.rotateAngleX = this.bipedBody.rotateAngleX;
        this.jacket.rotateAngleY = this.bipedBody.rotateAngleY;
        this.jacket.rotateAngleZ = this.bipedBody.rotateAngleZ;

        this.leftSleeve.rotateAngleX  = this.bipedLeftArm.rotateAngleX;
        this.leftSleeve.rotateAngleY  = this.bipedLeftArm.rotateAngleY;
        this.leftSleeve.rotateAngleZ  = this.bipedLeftArm.rotateAngleZ;
        this.rightSleeve.rotateAngleX = this.bipedRightArm.rotateAngleX;
        this.rightSleeve.rotateAngleY = this.bipedRightArm.rotateAngleY;
        this.rightSleeve.rotateAngleZ = this.bipedRightArm.rotateAngleZ;

        this.leftPantLeg.rotateAngleX  = this.bipedLeftLeg.rotateAngleX;
        this.leftPantLeg.rotateAngleY  = this.bipedLeftLeg.rotateAngleY;
        this.leftPantLeg.rotateAngleZ  = this.bipedLeftLeg.rotateAngleZ;
        this.rightPantLeg.rotateAngleX = this.bipedRightLeg.rotateAngleX;
        this.rightPantLeg.rotateAngleY = this.bipedRightLeg.rotateAngleY;
        this.rightPantLeg.rotateAngleZ = this.bipedRightLeg.rotateAngleZ;
    }
}
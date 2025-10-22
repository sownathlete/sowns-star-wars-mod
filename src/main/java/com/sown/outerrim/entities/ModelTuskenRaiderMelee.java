package com.sown.outerrim.entities;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class ModelTuskenRaiderMelee extends ModelBiped {
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
    public ModelRenderer gaderffii;
    public ModelRenderer gaderffii_2;
    public ModelRenderer blade;
    public ModelRenderer blade_2;
    public ModelRenderer gaderffii_3;
    public ModelRenderer gaderffii_4;

    public ModelTuskenRaiderMelee() {
        this.textureWidth = 80;
        this.textureHeight = 80;
        this.gaderffii_2 = new ModelRenderer(this, 0, 70);
        this.gaderffii_2.setRotationPoint(-2.0F, -1.0F, 19.0F);
        this.gaderffii_2.addBox(0.0F, 0.0F, 0.0F, 2, 4, 4, 0.0F);
        this.leftSleeve = new ModelRenderer(this, 48, 34);
        this.leftSleeve.mirror = true;
        this.leftSleeve.setRotationPoint(5.0F, 2.0F, 0.0F);
        this.leftSleeve.addBox(-1.0F, -2.0F, -2.0F, 4, 12, 4, 0.25F);
        this.head = new ModelRenderer(this, 0, 0);
        this.head.setRotationPoint(0.0F, -1.0F, 0.0F);
        this.head.addBox(-4.0F, -8.0F, -4.0F, 8, 9, 8, 0.0F);
        this.leftLeg = new ModelRenderer(this, 0, 34);
        this.leftLeg.mirror = true;
        this.leftLeg.setRotationPoint(1.9F, 12.0F, 0.0F);
        this.leftLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.0F);
        this.rightArm = new ModelRenderer(this, 48, 18);
        this.rightArm.setRotationPoint(-5.0F, 2.0F, 0.0F);
        this.rightArm.addBox(-3.0F, -2.0F, -2.0F, 4, 12, 4, 0.0F);
        this.rightLeg = new ModelRenderer(this, 0, 34);
        this.rightLeg.setRotationPoint(-1.9F, 12.0F, 0.0F);
        this.rightLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.0F);
        this.gaderffii = new ModelRenderer(this, 0, 56);
        this.gaderffii.setRotationPoint(-2.0F, 7.0F, -9.0F);
        this.gaderffii.addBox(0.0F, 0.0F, 0.0F, 2, 2, 22, 0.0F);
        this.blade_2 = new ModelRenderer(this, -10, 55);
        this.blade_2.setRotationPoint(-1.85F, -1.8F, -14.0F);
        this.blade_2.addBox(0.0F, 0.0F, 0.0F, 8, 0, 14, 0.0F);
        this.setRotateAngle(blade_2, 0.0F, 0.0F, 0.7853981633974483F);
        this.leftArm = new ModelRenderer(this, 48, 18);
        this.leftArm.mirror = true;
        this.leftArm.setRotationPoint(5.0F, 2.0F, 0.0F);
        this.leftArm.addBox(-1.0F, -2.0F, -2.0F, 4, 12, 4, 0.0F);
        this.blade = new ModelRenderer(this, 27, 40);
        this.blade.setRotationPoint(4.5F, -2.5F, 0.0F);
        this.blade.addBox(0.0F, 0.0F, 0.0F, 0, 8, 14, 0.0F);
        this.setRotateAngle(blade, 0.0F, 3.141592653589793F, 0.7853981633974483F);
        this.gaderffii_3 = new ModelRenderer(this, -2, 67);
        this.gaderffii_3.setRotationPoint(-3.0F, 2.0F, 1.0F);
        this.gaderffii_3.addBox(0.0F, 0.0F, 0.0F, 3, 0, 2, 0.0F);
        this.helmet = new ModelRenderer(this, 32, 0);
        this.helmet.setRotationPoint(0.0F, -2.0F, 0.0F);
        this.helmet.addBox(-4.0F, -9.0F, -4.0F, 8, 10, 8, 0.5F);
        this.torso = new ModelRenderer(this, 0, 18);
        this.torso.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.torso.addBox(-4.0F, 0.0F, -2.0F, 8, 12, 4, 0.0F);
        this.leftPantLeg = new ModelRenderer(this, 16, 34);
        this.leftPantLeg.setRotationPoint(1.9F, 12.0F, 0.0F);
        this.leftPantLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.25F);
        this.gaderffii_4 = new ModelRenderer(this, -3, 67);
        this.gaderffii_4.mirror = true;
        this.gaderffii_4.setRotationPoint(-3.0F, 1.0F, 2.0F);
        this.gaderffii_4.addBox(0.0F, 0.0F, 0.0F, 3, 2, 0, 0.0F);
        this.rightPantLeg = new ModelRenderer(this, 16, 34);
        this.rightPantLeg.setRotationPoint(-1.9F, 12.0F, 0.0F);
        this.rightPantLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.25F);
        this.jacket = new ModelRenderer(this, 24, 18);
        this.jacket.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.jacket.addBox(-4.0F, 0.0F, -2.0F, 8, 12, 4, 0.25F);
        this.rightSleeve = new ModelRenderer(this, 48, 34);
        this.rightSleeve.setRotationPoint(-5.0F, 2.0F, 0.0F);
        this.rightSleeve.addBox(-3.0F, -2.0F, -2.0F, 4, 12, 4, 0.25F);
        this.gaderffii.addChild(this.gaderffii_2);
        this.rightArm.addChild(this.gaderffii);
        this.gaderffii.addChild(this.blade_2);
        this.gaderffii.addChild(this.blade);
        this.gaderffii_2.addChild(this.gaderffii_3);
        this.gaderffii_2.addChild(this.gaderffii_4);
    }

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        this.helmet.render(f5);
        this.head.render(f5);
        this.jacket.render(f5);
        this.torso.render(f5);
        this.leftSleeve.render(f5);
        this.leftArm.render(f5);
        this.rightSleeve.render(f5);
        this.rightArm.render(f5);
        this.leftPantLeg.render(f5);
        this.leftLeg.render(f5);
        this.rightPantLeg.render(f5);
        this.rightLeg.render(f5);
    }

    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
    
	private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }

    public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn) {
        float f7;
        float f6;
        this.head.rotateAngleY = netHeadYaw / 57.295776f;
        this.head.rotateAngleX = headPitch / 57.295776f;
        this.rightArm.rotateAngleX = MathHelper.cos(limbSwing * 0.6662f + 3.1415927f) * 2.0f * limbSwingAmount * 0.5f;
        this.leftArm.rotateAngleX = MathHelper.cos(limbSwing * 0.6662f) * 2.0f * limbSwingAmount * 0.5f;
        this.rightArm.rotateAngleZ = 0.0f;
        this.leftArm.rotateAngleZ = 0.0f;
        this.rightLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662f) * 1.4f * limbSwingAmount;
        this.leftLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662f + 3.1415927f) * 1.4f * limbSwingAmount;
        this.rightLeg.rotateAngleY = 0.0f;
        this.leftLeg.rotateAngleY = 0.0f;
        this.helmet.rotateAngleY = this.head.rotateAngleY;
        this.helmet.rotateAngleX = this.head.rotateAngleX;
        this.rightSleeve.rotateAngleX = this.rightArm.rotateAngleX;
        this.leftSleeve.rotateAngleX = this.leftArm.rotateAngleX;
        this.rightSleeve.rotateAngleZ = this.rightArm.rotateAngleZ;
        this.leftSleeve.rotateAngleZ = this.leftArm.rotateAngleZ;
        this.rightPantLeg.rotateAngleX = this.rightLeg.rotateAngleX;
        this.leftPantLeg.rotateAngleX = this.leftLeg.rotateAngleX;
        this.rightPantLeg.rotateAngleY = this.rightLeg.rotateAngleY;
        this.leftPantLeg.rotateAngleY = this.leftLeg.rotateAngleY;

        if (this.isRiding) {
            this.rightArm.rotateAngleX += -0.62831855f;
            this.leftArm.rotateAngleX += -0.62831855f;
            this.rightLeg.rotateAngleX = -1.2566371f;
            this.leftLeg.rotateAngleX = -1.2566371f;
            this.rightLeg.rotateAngleY = 0.31415927f;
            this.leftLeg.rotateAngleY = -0.31415927f;
            this.rightSleeve.rotateAngleX = this.rightArm.rotateAngleX;
            this.leftSleeve.rotateAngleX = this.leftArm.rotateAngleX;
            this.rightPantLeg.rotateAngleX = this.rightLeg.rotateAngleX;
            this.leftPantLeg.rotateAngleX = this.leftLeg.rotateAngleX;
            this.rightPantLeg.rotateAngleY = this.rightLeg.rotateAngleY;
            this.leftPantLeg.rotateAngleY = this.leftLeg.rotateAngleY;
        }
        if (this.heldItemLeft != 0) {
            this.leftArm.rotateAngleX = this.leftArm.rotateAngleX * 0.5f - 0.31415927f * (float)this.heldItemLeft;
            this.leftArm.rotateAngleY = this.leftArm.rotateAngleY * 0.5f; // Add Y-axis rotation adjustment
            this.leftArm.rotateAngleZ = this.leftArm.rotateAngleZ * 0.5f; // Add Z-axis rotation adjustment
            
            this.leftSleeve.rotateAngleX = this.leftArm.rotateAngleX;
            this.leftSleeve.rotateAngleY = this.leftArm.rotateAngleY; // Ensure sleeve follows arm in Y-axis
            this.leftSleeve.rotateAngleZ = this.leftArm.rotateAngleZ; // Ensure sleeve follows arm in Z-axis
        }

        if (this.heldItemRight != 0) {
            this.rightArm.rotateAngleX = this.rightArm.rotateAngleX * 0.5f - 0.31415927f * (float)this.heldItemRight;
            this.rightArm.rotateAngleY = this.rightArm.rotateAngleY * 0.5f; // Add Y-axis rotation adjustment
            this.rightArm.rotateAngleZ = this.rightArm.rotateAngleZ * 0.5f; // Add Z-axis rotation adjustment
            
            this.rightSleeve.rotateAngleX = this.rightArm.rotateAngleX;
            this.rightSleeve.rotateAngleY = this.rightArm.rotateAngleY; // Ensure sleeve follows arm in Y-axis
            this.rightSleeve.rotateAngleZ = this.rightArm.rotateAngleZ; // Ensure sleeve follows arm in Z-axis
        }
        this.rightArm.rotateAngleY = 0.0f;
        this.leftArm.rotateAngleY = 0.0f;
        this.rightSleeve.rotateAngleY = this.rightArm.rotateAngleY;
        this.leftSleeve.rotateAngleY = this.leftArm.rotateAngleY;
  
        if (this.onGround > -9990.0f) {
            f6 = this.onGround;
            this.torso.rotateAngleY = MathHelper.sin((float)(MathHelper.sqrt_float((float)f6) * 3.1415927f * 2.0f)) * 0.2f;
            this.rightArm.rotationPointZ = MathHelper.sin((float)this.torso.rotateAngleY) * 5.0f;
            this.rightArm.rotationPointX = -MathHelper.cos((float)this.torso.rotateAngleY) * 5.0f;
            this.leftArm.rotationPointZ = -MathHelper.sin((float)this.torso.rotateAngleY) * 5.0f;
            this.leftArm.rotationPointX = MathHelper.cos((float)this.torso.rotateAngleY) * 5.0f;
            this.rightArm.rotateAngleY += this.torso.rotateAngleY;
            this.leftArm.rotateAngleY += this.torso.rotateAngleY;
            this.leftArm.rotateAngleX += this.torso.rotateAngleY;
            this.jacket.rotateAngleY = this.torso.rotateAngleY;
            this.rightSleeve.rotationPointZ = this.rightArm.rotationPointZ;
            this.rightSleeve.rotationPointX = this.rightArm.rotationPointX;
            this.leftSleeve.rotationPointZ = this.leftArm.rotationPointZ;
            this.leftSleeve.rotationPointX = this.leftArm.rotationPointX;
            this.rightSleeve.rotateAngleY = this.rightArm.rotateAngleY;
            this.leftSleeve.rotateAngleY = this.leftArm.rotateAngleY;
            this.leftSleeve.rotateAngleX = this.leftArm.rotateAngleX;
            f6 = 1.0f - this.onGround;
            f6 *= f6;
            f6 *= f6;
            f6 = 1.0f - f6;
            f7 = MathHelper.sin((float)(f6 * 3.1415927f));
            float f8 = MathHelper.sin((float)(this.onGround * 3.1415927f)) * -(this.head.rotateAngleX - 0.7f) * 0.75f;
            this.rightArm.rotateAngleX = (float)((double)this.rightArm.rotateAngleX - ((double)f7 * 1.2 + (double)f8));
            this.rightArm.rotateAngleY += this.torso.rotateAngleY * 2.0f;
            this.rightArm.rotateAngleZ = MathHelper.sin((float)(this.onGround * 3.1415927f)) * -0.4f;
            this.rightSleeve.rotateAngleX = this.rightArm.rotateAngleX;
            this.rightSleeve.rotateAngleY = this.rightArm.rotateAngleY;
            this.rightSleeve.rotateAngleZ = this.rightArm.rotateAngleZ;
            
        }
        if (this.isSneak) {
            this.torso.rotateAngleX = 0.5f;
            this.rightLeg.rotateAngleX -= 0.0f;
            this.leftLeg.rotateAngleX -= 0.0f;
            this.rightArm.rotateAngleX += 0.4f;
            this.leftArm.rotateAngleX += 0.4f;
            this.rightLeg.rotationPointZ = 4.0f;
            this.leftLeg.rotationPointZ = 4.0f;
            this.rightLeg.rotationPointY = 9.0f;
            this.leftLeg.rotationPointY = 9.0f;
            this.head.rotationPointY = 1.0f;
            this.jacket.rotateAngleX = this.torso.rotateAngleX;
            this.rightPantLeg.rotateAngleX = this.rightLeg.rotateAngleX;
            this.leftPantLeg.rotateAngleX = this.leftLeg.rotateAngleX;
            this.rightSleeve.rotateAngleX = this.rightArm.rotateAngleX;
            this.leftSleeve.rotateAngleX = this.leftArm.rotateAngleX;
            this.rightPantLeg.rotationPointZ = this.rightLeg.rotationPointZ;
            this.leftPantLeg.rotationPointZ = this.leftLeg.rotationPointZ;
            this.rightPantLeg.rotationPointY = this.rightLeg.rotationPointY;
            this.leftPantLeg.rotationPointY = this.leftLeg.rotationPointY;
            this.helmet.rotationPointY = this.head.rotationPointY;

        } else {
            this.torso.rotateAngleX = 0.0f;
            this.rightLeg.rotationPointZ = 0.0f;
            this.leftLeg.rotationPointZ = 0.0f;
            this.rightLeg.rotationPointY = 12.0f;
            this.leftLeg.rotationPointY = 12.0f;
            this.head.rotationPointY = 0.0f;
            this.jacket.rotateAngleX = this.torso.rotateAngleX;
            this.rightPantLeg.rotationPointZ = this.rightLeg.rotationPointZ;
            this.leftPantLeg.rotationPointZ = this.leftLeg.rotationPointZ;
            this.rightPantLeg.rotationPointY = this.rightLeg.rotationPointY;
            this.leftPantLeg.rotationPointY = this.leftLeg.rotationPointY;
            this.helmet.rotationPointY = this.head.rotationPointY;

        }
        this.rightArm.rotateAngleZ += MathHelper.cos((float)(ageInTicks * 0.09f)) * 0.05f + 0.05f;
        this.leftArm.rotateAngleZ -= MathHelper.cos((float)(ageInTicks * 0.09f)) * 0.05f + 0.05f;
        this.rightArm.rotateAngleX += MathHelper.sin((float)(ageInTicks * 0.067f)) * 0.05f;
        this.leftArm.rotateAngleX -= MathHelper.sin((float)(ageInTicks * 0.067f)) * 0.05f;
        this.rightSleeve.rotateAngleZ += MathHelper.cos((float)(ageInTicks * 0.09f)) * 0.05f + 0.05f;
        this.leftSleeve.rotateAngleZ -= MathHelper.cos((float)(ageInTicks * 0.09f)) * 0.05f + 0.05f;
        this.rightSleeve.rotateAngleX += MathHelper.sin((float)(ageInTicks * 0.067f)) * 0.05f;
        this.leftSleeve.rotateAngleX -= MathHelper.sin((float)(ageInTicks * 0.067f)) * 0.05f;
        
        if (this.aimedBow) {       	
            float f1 = 0.0f;
            float f3 = 0.0f;
            this.rightArm.rotateAngleZ = 0.0f;
            this.leftArm.rotateAngleZ = 0.0f;
            this.rightArm.rotateAngleY = -(0.1f - f1 * 0.6f) + this.head.rotateAngleY;
            this.leftArm.rotateAngleY = 0.1f - f1 * 0.6f + this.head.rotateAngleY + 0.4f;
            this.rightArm.rotateAngleX = -1.5707964f + this.head.rotateAngleX;
            this.leftArm.rotateAngleX = -1.5707964f + this.head.rotateAngleX;
            this.rightArm.rotateAngleX -= f1 * 1.2f - f3 * 0.4f;
            this.leftArm.rotateAngleX -= f1 * 1.2f - f3 * 0.4f;
            this.rightArm.rotateAngleZ += MathHelper.cos((float)(ageInTicks)) * 0.05f + 0.05f;
            this.leftArm.rotateAngleZ -= MathHelper.cos((float)(ageInTicks * 0.09f)) * 0.05f + 0.05f;
            this.rightArm.rotateAngleX += MathHelper.sin((float)(ageInTicks * 0.067f)) * 0.05f;
            this.leftArm.rotateAngleX -= MathHelper.sin((float)(ageInTicks * 0.067f)) * 0.05f;
            this.rightSleeve.rotateAngleZ = this.rightArm.rotateAngleZ;
            this.leftSleeve.rotateAngleZ = this.leftArm.rotateAngleZ;
            this.rightSleeve.rotateAngleY = this.rightArm.rotateAngleY;
            this.leftSleeve.rotateAngleY = this.leftArm.rotateAngleY;
            this.rightSleeve.rotateAngleX = this.rightArm.rotateAngleX;
            this.leftSleeve.rotateAngleX = this.leftArm.rotateAngleX;
            this.rightSleeve.rotateAngleZ += MathHelper.cos((float)(ageInTicks)) * 0.05f + 0.05f;
            this.leftSleeve.rotateAngleZ -= MathHelper.cos((float)(ageInTicks * 0.09f)) * 0.05f + 0.05f;
            this.rightSleeve.rotateAngleX += MathHelper.sin((float)(ageInTicks * 0.067f)) * 0.05f;
            this.leftSleeve.rotateAngleX -= MathHelper.sin((float)(ageInTicks * 0.067f)) * 0.05f;
        }
    }
}
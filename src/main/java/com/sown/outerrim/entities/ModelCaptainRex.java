package com.sown.outerrim.entities;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MathHelper;

public class ModelCaptainRex extends ModelBase {
    public ModelRenderer torso;
    public ModelRenderer leftSleeve;
    public ModelRenderer rightArm;
    public ModelRenderer jacket;
    public ModelRenderer leftLeg;
    public ModelRenderer leftPantLeg;
    public ModelRenderer head;
    public ModelRenderer rightSleeve;
    public ModelRenderer rightLeg;
    public ModelRenderer helmet;
    public ModelRenderer leftArm;
    public ModelRenderer rightPantLeg;
    public ModelRenderer headChild;
    public ModelRenderer headChild_1;
    public ModelRenderer antenna;
    public ModelRenderer headChildChild;
    public ModelRenderer headChildChild_1;
    public ModelRenderer pauldron;

    public ModelCaptainRex() {
        this.textureWidth = 80;
        this.textureHeight = 80;
        this.leftArm = new ModelRenderer(this, 48, 18);
        this.leftArm.mirror = true;
        this.leftArm.setRotationPoint(5.0F, 0.0F, -0.0F);
        this.leftArm.addBox(-1.0F, -2.0F, -2.0F, 4, 13, 4, 0.0F);
        this.rightPantLeg = new ModelRenderer(this, 16, 35);
        this.rightPantLeg.setRotationPoint(-1.899999976158142F, 11.0F, 0.0F);
        this.rightPantLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 13, 4, 0.25F);
        this.pauldron = new ModelRenderer(this, 42, 52);
        this.pauldron.setRotationPoint(-1.1F, -2.5F, -2.5F);
        this.pauldron.addBox(0.0F, 0.0F, 0.0F, 6, 5, 5, 0.0F);
        this.helmet = new ModelRenderer(this, 48, 62);
        this.helmet.setRotationPoint(0.0F, -2.0F, 0.0F);
        this.helmet.addBox(-4.0F, -10.0F, -4.0F, 8, 10, 8, 0.5F);
        this.rightArm = new ModelRenderer(this, 48, 18);
        this.rightArm.setRotationPoint(-5.0F, 1.0F, 0.0F);
        this.rightArm.addBox(-3.0F, -3.0F, -2.0F, 4, 13, 4, 0.0F);
        this.leftLeg = new ModelRenderer(this, 0, 35);
        this.leftLeg.setRotationPoint(1.899999976158142F, 11.0F, 0.0F);
        this.leftLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 13, 4, 0.0F);
        this.rightLeg = new ModelRenderer(this, 0, 35);
        this.rightLeg.mirror = true;
        this.rightLeg.setRotationPoint(-1.899999976158142F, 11.0F, 0.0F);
        this.rightLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 13, 4, 0.0F);
        this.headChild = new ModelRenderer(this, 24, 0);
        this.headChild.mirror = true;
        this.headChild.setRotationPoint(-5.0F, -7.0F, 0.0F);
        this.headChild.addBox(0.0F, 0.0F, 0.0F, 1, 5, 2, 0.0F);
        this.leftPantLeg = new ModelRenderer(this, 16, 35);
        this.leftPantLeg.mirror = true;
        this.leftPantLeg.setRotationPoint(1.899999976158142F, 11.0F, 0.0F);
        this.leftPantLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 13, 4, 0.25F);
        this.head = new ModelRenderer(this, 0, 0);
        this.head.setRotationPoint(0.0F, -2.0F, 0.0F);
        this.head.addBox(-4.0F, -10.0F, -4.0F, 8, 10, 8, 0.0F);
        this.setRotateAngle(head, 0.0F, 0.0F, -0.008726646192371845F);
        this.headChildChild_1 = new ModelRenderer(this, 23, 43);
        this.headChildChild_1.setRotationPoint(-2.0F, 0.0F, 0.0F);
        this.headChildChild_1.addBox(0.0F, 5.0F, -5.0F, 3, 2, 9, 0.0F);
        this.antenna = new ModelRenderer(this, 0, 0);
        this.antenna.setRotationPoint(-6.0F, -15.0F, 1.0F);
        this.antenna.addBox(0.0F, 0.0F, 0.0F, 4, 8, 0, 0.0F);
        this.leftSleeve = new ModelRenderer(this, 48, 35);
        this.leftSleeve.setRotationPoint(5.0F, 0.0F, -0.0F);
        this.leftSleeve.addBox(-1.0F, -2.0F, -2.0F, 4, 13, 4, 0.25F);
        this.torso = new ModelRenderer(this, 0, 18);
        this.torso.setRotationPoint(0.0F, -2.0F, 0.0F);
        this.torso.addBox(-4.0F, 0.0F, -2.0F, 8, 13, 4, 0.0F);
        this.headChildChild = new ModelRenderer(this, 23, 43);
        this.headChildChild.mirror = true;
        this.headChildChild.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.headChildChild.addBox(0.0F, 5.0F, -5.0F, 3, 2, 9, 0.0F);
        this.rightSleeve = new ModelRenderer(this, 48, 35);
        this.rightSleeve.mirror = true;
        this.rightSleeve.setRotationPoint(-5.0F, 0.0F, 0.0F);
        this.rightSleeve.addBox(-3.0F, -2.0F, -2.0F, 4, 13, 4, 0.25F);
        this.jacket = new ModelRenderer(this, 24, 18);
        this.jacket.setRotationPoint(0.0F, -2.0F, 0.0F);
        this.jacket.addBox(-4.0F, 0.0F, -2.0F, 8, 13, 4, 0.25F);
        this.headChild_1 = new ModelRenderer(this, 24, 0);
        this.headChild_1.mirror = true;
        this.headChild_1.setRotationPoint(4.0F, -7.0F, 0.0F);
        this.headChild_1.addBox(0.0F, 0.0F, 0.0F, 1, 5, 2, 0.0F);
        this.leftArm.addChild(this.pauldron);
        this.head.addChild(this.headChild);
        this.headChild_1.addChild(this.headChildChild_1);
        this.head.addChild(this.antenna);
        this.headChild.addChild(this.headChildChild);
        this.head.addChild(this.headChild_1);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { 
        this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        this.torso.render(f5);
        this.rightLeg.render(f5);
        this.rightArm.render(f5);
        this.helmet.render(f5);
        this.leftPantLeg.render(f5);
        this.leftLeg.render(f5);
        this.rightSleeve.render(f5);
        this.jacket.render(f5);
        this.head.render(f5);
        this.leftArm.render(f5);
        this.rightPantLeg.render(f5);
        this.leftSleeve.render(f5);
    }

    /**
     * This is a helper function from Tabula to set the rotation of model parts
     */
    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }

    @Override
    public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entity) {
        // Copying human animation behavior

        // Head rotation
        this.head.rotateAngleY = netHeadYaw * 0.017453292F;
        this.head.rotateAngleX = headPitch * 0.017453292F;
        
        // Ensure the helmet follows the head's rotation
        this.helmet.rotateAngleY = this.head.rotateAngleY;
        this.helmet.rotateAngleX = this.head.rotateAngleX;

        // Arm swinging while walking
        this.rightArm.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 2.0F * limbSwingAmount * 0.5F;
        this.leftArm.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 2.0F * limbSwingAmount * 0.5F;
        this.rightArm.rotateAngleZ = 0.0F;
        this.leftArm.rotateAngleZ = 0.0F;

        // Overlay: Sleeves follow arms
        this.rightSleeve.rotateAngleX = this.rightArm.rotateAngleX;
        this.leftSleeve.rotateAngleX = this.leftArm.rotateAngleX;
        this.rightSleeve.rotateAngleZ = this.rightArm.rotateAngleZ;
        this.leftSleeve.rotateAngleZ = this.leftArm.rotateAngleZ;

        // Arm swinging when attacking
        if (entity instanceof EntityLivingBase) {
            float swingProgress = ((EntityLivingBase) entity).getSwingProgress(scaleFactor);
            if (swingProgress > 0.0F) {
                float swingAdjusted = MathHelper.sin(swingProgress * (float) Math.PI);
                this.rightArm.rotateAngleX -= swingAdjusted * 1.2F;
                // Ensure the right sleeve follows the arm's attack animation
                this.rightSleeve.rotateAngleX = this.rightArm.rotateAngleX;
            }
        }

        // Leg movement while walking
        this.rightLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
        this.leftLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount;

        // Overlay: Pant legs follow legs
        this.rightPantLeg.rotateAngleX = this.rightLeg.rotateAngleX;
        this.leftPantLeg.rotateAngleX = this.leftLeg.rotateAngleX;

        // Reset leg rotation around Z axis
        this.rightLeg.rotateAngleZ = 0.0F;
        this.leftLeg.rotateAngleZ = 0.0F;

        // Ensure the pant legs follow the legs
        this.rightPantLeg.rotateAngleZ = this.rightLeg.rotateAngleZ;
        this.leftPantLeg.rotateAngleZ = this.leftLeg.rotateAngleZ;
        

        // Subtle breathing animation for realism
        this.rightArm.rotateAngleZ += MathHelper.cos(ageInTicks * 0.09F) * 0.05F + 0.05F;
        this.leftArm.rotateAngleZ -= MathHelper.cos(ageInTicks * 0.09F) * 0.05F + 0.05F;
        this.rightArm.rotateAngleX += MathHelper.sin(ageInTicks * 0.067F) * 0.05F;
        this.leftArm.rotateAngleX -= MathHelper.sin(ageInTicks * 0.067F) * 0.05F;

        // Sync arm overlays
        this.rightSleeve.rotateAngleZ += MathHelper.cos(ageInTicks * 0.09F) * 0.05F + 0.05F;
        this.leftSleeve.rotateAngleZ -= MathHelper.cos(ageInTicks * 0.09F) * 0.05F + 0.05F;
        this.rightSleeve.rotateAngleX += MathHelper.sin(ageInTicks * 0.067F) * 0.05F;
        this.leftSleeve.rotateAngleX -= MathHelper.sin(ageInTicks * 0.067F) * 0.05F;
    }
}

package com.sown.outerrim.entities;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MathHelper;

public class ModelReconTrooper extends ModelBase {
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
    public ModelRenderer blaster5;
    public ModelRenderer blaster1;
    public ModelRenderer blaster2;
    public ModelRenderer blaster3;
    public ModelRenderer blaster6;
    public ModelRenderer blaster7;
    public ModelRenderer blaster4;
    public ModelRenderer head_right;
    public ModelRenderer head_left;
    public ModelRenderer visor;
    public ModelRenderer mouth;

    public ModelReconTrooper() {
        this.textureWidth = 80;
        this.textureHeight = 80;
        this.helmet = new ModelRenderer(this, 32, 0);
        this.helmet.setRotationPoint(0.0F, -2.0F, 0.0F);
        this.helmet.addBox(-4.0F, -10.0F, -4.0F, 8, 10, 8, 0.5F);
        this.head_left = new ModelRenderer(this, 44, 19);
        this.head_left.setRotationPoint(3.0F, -7.0F, 0.0F);
        this.head_left.addBox(0.0F, 5.0F, -5.0F, 2, 2, 9, 0.0F);
        this.rightSleeve = new ModelRenderer(this, 64, 59);
        this.rightSleeve.mirror = true;
        this.rightSleeve.setRotationPoint(-5.0F, 0.25F, 0.0F); // Moved it up slightly by setting Y to 0.25
        this.rightSleeve.addBox(-3.0F, -2.0F, -2.0F, 4, 13, 4, 0.25F);
        this.setRotateAngle(rightSleeve, -1.5708F, 0.0F, 0.0F); // -90 degrees in radians
        this.leftLeg = new ModelRenderer(this, 0, 51);
        this.leftLeg.setRotationPoint(1.9F, 11.0F, 0.0F);
        this.leftLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 13, 4, 0.0F);
        this.leftPantLeg = new ModelRenderer(this, 16, 51);
        this.leftPantLeg.mirror = true;
        this.leftPantLeg.setRotationPoint(1.9F, 11.0F, 0.0F);
        this.leftPantLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 13, 4, 0.25F);
        this.rightLeg = new ModelRenderer(this, 0, 51);
        this.rightLeg.mirror = true;
        this.rightLeg.setRotationPoint(-1.9F, 11.0F, 0.0F);
        this.rightLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 13, 4, 0.0F);
        this.rightArm = new ModelRenderer(this, 64, 42);
        this.rightArm.mirror = true;
        this.rightArm.setRotationPoint(-5.0F, 0.2F, -1.0F);
        this.rightArm.addBox(-3.0F, -3.0F, -2.0F, 4, 13, 4, 0.0F);
        this.setRotateAngle(rightArm, -1.5707963267948966F, 0.0F, 0.0F);
        this.leftArm = new ModelRenderer(this, 64, 42);
        this.leftArm.setRotationPoint(5.0F, 0.0F, -0.0F);
        this.leftArm.addBox(-1.0F, -2.0F, -2.0F, 4, 13, 4, 0.0F);
        this.setRotateAngle(leftArm, 0.0F, 0.0F, -0.10000736613927509F);
        this.mouth = new ModelRenderer(this, 68, 9);
        this.mouth.setRotationPoint(-2.0F, -6.0F, -5.0F);
        this.mouth.addBox(0.0F, 0.0F, 0.0F, 4, 6, 1, 0.0F);
        this.setRotateAngle(mouth, 0.0F, 0.017453292519943295F, 0.0F);
        this.leftSleeve = new ModelRenderer(this, 64, 59);
        this.leftSleeve.setRotationPoint(5.0F, 0.0F, -0.0F);
        this.leftSleeve.addBox(-1.0F, -2.0F, -2.0F, 4, 13, 4, 0.25F);
        this.setRotateAngle(leftSleeve, 0.0F, 0.0F, -0.10000736613927509F);
        this.torso = new ModelRenderer(this, 0, 34);
        this.torso.setRotationPoint(0.0F, -2.0F, 0.0F);
        this.torso.addBox(-4.0F, 0.0F, -2.0F, 8, 13, 4, 0.0F);
        this.head = new ModelRenderer(this, 0, 0);
        this.head.setRotationPoint(0.0F, -2.0F, 0.0F);
        this.head.addBox(-4.0F, -10.0F, -4.0F, 8, 10, 8, 0.0F);
        this.setRotateAngle(head, 0.0F, 0.0F, -0.008726646259971648F);
        this.jacket = new ModelRenderer(this, 24, 34);
        this.jacket.setRotationPoint(0.0F, -2.0F, 0.0F);
        this.jacket.addBox(-4.0F, 0.0F, -2.0F, 8, 13, 4, 0.25F);
        this.rightPantLeg = new ModelRenderer(this, 16, 51);
        this.rightPantLeg.setRotationPoint(-1.9F, 11.0F, 0.0F);
        this.rightPantLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 13, 4, 0.25F);
        this.head_right = new ModelRenderer(this, 44, 19);
        this.head_right.mirror = true;
        this.head_right.setRotationPoint(-5.0F, -7.0F, 0.0F);
        this.head_right.addBox(0.0F, 5.0F, -5.0F, 2, 2, 9, 0.0F);
        this.visor = new ModelRenderer(this, 58, 19);
        this.visor.setRotationPoint(-4.0F, -8.0F, -7.0F);
        this.visor.addBox(0.0F, 0.0F, 0.0F, 8, 5, 3, 0.0F);
        this.head.addChild(this.head_left);
        this.head.addChild(this.mouth);
        this.head.addChild(this.head_right);
        this.head.addChild(this.visor);
        // Blaster Parts
        this.blaster1 = new ModelRenderer(this, 23, 70);
        this.blaster1.setRotationPoint(-2.0F, 9.5F, -3.0F);
        this.blaster1.addBox(0.0F, 0.0F, 0.0F, 2, 3, 2, 0.0F);
        this.setRotateAngle(blaster1, 1.5708F, 0.0F, 0.0F);
        this.blaster2 = new ModelRenderer(this, 40, 68);
        this.blaster2.setRotationPoint(0.0F, -4.0F, -4.0F);
        this.blaster2.addBox(0.0F, 0.0F, 0.0F, 3, 4, 7, 0.0F);
        this.blaster3 = new ModelRenderer(this, 0, 69);
        this.blaster3.setRotationPoint(0.0F, 0.0F, -4.0F);
        this.blaster3.addBox(0.0F, 0.0F, 0.0F, 3, 2, 4, 0.0F);
        this.blaster4 = new ModelRenderer(this, 0, 75);
        this.blaster4.setRotationPoint(0.0F, 3.0F, 0.0F);
        this.blaster4.addBox(0.0F, 0.0F, 0.0F, 3, 1, 4, 0.0F);
        this.blaster5 = new ModelRenderer(this, 10, 68);
        this.blaster5.setRotationPoint(-7.0F, -6.75F, -14.5F);
        this.blaster5.addBox(0.0F, 0.0F, 0.0F, 3, 4, 7, -0.25F);
        this.blaster6 = new ModelRenderer(this, 31, 70);
        this.blaster6.mirror = true;
        this.blaster6.setRotationPoint(-1.0F, 1.0F, 3.0F);
        this.blaster6.addBox(0.0F, 0.0F, 0.0F, 1, 3, 3, 0.0F);
        this.blaster7 = new ModelRenderer(this, 31, 70);
        this.blaster7.setRotationPoint(3.0F, 1.0F, 3.0F);
        this.blaster7.addBox(0.0F, 0.0F, 0.0F, 1, 3, 3, 0.0F);
        this.rightArm.addChild(this.blaster1);
        this.blaster1.addChild(this.blaster2);
        this.blaster2.addChild(this.blaster3);
        this.blaster3.addChild(this.blaster4);
        this.blaster2.addChild(this.blaster6);
        this.blaster2.addChild(this.blaster7);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { 
        this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        this.helmet.render(f5);
        this.rightSleeve.render(f5);
        this.leftLeg.render(f5);
        this.leftPantLeg.render(f5);
        this.rightLeg.render(f5);
        this.rightArm.render(f5);
        this.leftArm.render(f5);
        this.leftSleeve.render(f5);
        this.blaster5.render(f5);
        this.torso.render(f5);
        this.head.render(f5);
        this.jacket.render(f5);
        this.rightPantLeg.render(f5);
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
        //this.rightArm.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 2.0F * limbSwingAmount * 0.5F;
        this.leftArm.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 2.0F * limbSwingAmount * 0.5F;
        //this.rightArm.rotateAngleZ = 0.0F;
        this.leftArm.rotateAngleZ = 0.0F;

        // Overlay: Sleeves follow arms
        //this.rightSleeve.rotateAngleX = this.rightArm.rotateAngleX;
        this.leftSleeve.rotateAngleX = this.leftArm.rotateAngleX;
        //this.rightSleeve.rotateAngleZ = this.rightArm.rotateAngleZ;
        this.leftSleeve.rotateAngleZ = this.leftArm.rotateAngleZ;

        // Arm swinging when attacking
        if (entity instanceof EntityLivingBase) {
            float swingProgress = ((EntityLivingBase) entity).getSwingProgress(scaleFactor);
            if (swingProgress > 0.0F) {
                float swingAdjusted = MathHelper.sin(swingProgress * (float) Math.PI);
                //this.rightArm.rotateAngleX -= swingAdjusted * 1.2F;
                // Ensure the right sleeve follows the arm's attack animation
                //this.rightSleeve.rotateAngleX = this.rightArm.rotateAngleX;
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
        //this.rightArm.rotateAngleZ += MathHelper.cos(ageInTicks * 0.09F) * 0.05F + 0.05F;
        this.leftArm.rotateAngleZ -= MathHelper.cos(ageInTicks * 0.09F) * 0.05F + 0.05F;
        //this.rightArm.rotateAngleX += MathHelper.sin(ageInTicks * 0.067F) * 0.05F;
        this.leftArm.rotateAngleX -= MathHelper.sin(ageInTicks * 0.067F) * 0.05F;

        // Sync arm overlays
        //this.rightSleeve.rotateAngleZ += MathHelper.cos(ageInTicks * 0.09F) * 0.05F + 0.05F;
        this.leftSleeve.rotateAngleZ -= MathHelper.cos(ageInTicks * 0.09F) * 0.05F + 0.05F;
        //this.rightSleeve.rotateAngleX += MathHelper.sin(ageInTicks * 0.067F) * 0.05F;
        this.leftSleeve.rotateAngleX -= MathHelper.sin(ageInTicks * 0.067F) * 0.05F;
    }

    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}

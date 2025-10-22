package com.sown.outerrim.entities;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

/**
 * b1_battle_droid - Undefined
 * Created using Tabula 4.1.1
 */
public class ModelBattleDroid extends ModelBase {
    public ModelRenderer body;
    public ModelRenderer blaster_5;
    public ModelRenderer neck;
    public ModelRenderer backpack;
    public ModelRenderer arm_right;
    public ModelRenderer arm_left;
    public ModelRenderer pelvis;
    public ModelRenderer head;
    public ModelRenderer head_extension;
    public ModelRenderer backpack_antennae;
    public ModelRenderer forearm_right;
    public ModelRenderer blaster;
    public ModelRenderer blaster_2;
    public ModelRenderer blaster_3;
    public ModelRenderer blaster_4;
    public ModelRenderer blaster_6;
    public ModelRenderer blaster_7;
    public ModelRenderer forearm_left;
    public ModelRenderer body_connector;
    public ModelRenderer leg_right;
    public ModelRenderer leg_left;
    public ModelRenderer calf_right;
    public ModelRenderer calf_left;

    public ModelBattleDroid() {
        this.textureWidth = 80;
        this.textureHeight = 80;
        this.arm_left = new ModelRenderer(this, 10, 25);
        this.arm_left.setRotationPoint(8.0F, 0.0F, 2.0F);
        this.arm_left.addBox(0.0F, 0.0F, 0.0F, 2, 7, 2, 0.0F);
        this.blaster = new ModelRenderer(this, 32, 29);
        this.blaster.setRotationPoint(1.0F, 8.5F, -0.5F);
        this.blaster.addBox(0.0F, 0.0F, 0.0F, 2, 3, 2, 0.0F);
        this.setRotateAngle(blaster, 1.5707963267948966F, 0.0F, -0.0F);
        this.pelvis = new ModelRenderer(this, 0, 25);
        this.pelvis.setRotationPoint(3.0F, 7.0F, 2.0F);
        this.pelvis.addBox(0.0F, 0.0F, 0.0F, 2, 7, 3, 0.0F);
        this.head = new ModelRenderer(this, 0, 0);
        this.head.setRotationPoint(1.0F, 0.0F, 0.0F);
        this.head.addBox(-2.0F, -2.0F, -7.0F, 4, 3, 9, 0.0F);
        this.setRotateAngle(head, 0.5672320068981571F, 0.0F, 0.0F);
        this.leg_right = new ModelRenderer(this, 8, 34);
        this.leg_right.setRotationPoint(-2.0F, 5.0F, 0.0F);
        this.leg_right.addBox(0.0F, 0.0F, 0.0F, 2, 7, 2, 0.0F);
        this.blaster_4 = new ModelRenderer(this, 0, 50);
        this.blaster_4.setRotationPoint(0.0F, 3.0F, -4.0F);
        this.blaster_4.addBox(0.0F, 0.0F, 0.0F, 3, 1, 4, 0.0F);
        this.blaster_2 = new ModelRenderer(this, 10, 43);
        this.blaster_2.setRotationPoint(-0.5F, -4.0F, -4.0F);
        this.blaster_2.addBox(0.0F, 0.0F, 0.0F, 3, 4, 7, 0.0F);
        this.backpack = new ModelRenderer(this, 26, 1);
        this.backpack.setRotationPoint(0.0F, -3.0F, 6.0F);
        this.backpack.addBox(0.0F, 0.0F, 0.0F, 8, 9, 3, 0.0F);
        this.body_connector = new ModelRenderer(this, 53, 21);
        this.body_connector.setRotationPoint(-2.0F, 0.0F, 1.0F);
        this.body_connector.addBox(0.0F, 0.0F, 0.0F, 6, 4, 0, 0.0F);
        this.forearm_right = new ModelRenderer(this, 18, 25);
        this.forearm_right.mirror = true;
        this.forearm_right.setRotationPoint(0.0F, 7.0F, 2.0F);
        this.forearm_right.addBox(0.0F, 0.0F, 0.0F, 2, 9, 2, 0.0F);
        this.setRotateAngle(forearm_right, -1.5707963267948966F, 0.0F, 0.0F);
        this.blaster_3 = new ModelRenderer(this, 0, 44);
        this.blaster_3.setRotationPoint(0.0F, 0.0F, -4.0F);
        this.blaster_3.addBox(0.0F, 0.0F, 0.0F, 3, 2, 4, 0.0F);
        this.blaster_5 = new ModelRenderer(this, 19, 29);
        this.blaster_5.setRotationPoint(0.0F, 7.0F, 2.0F); // Keep the rotation point as it is
        this.blaster_5.addBox(-5.5F, -6.5F, -11.5F, 3, 4, 7, -0.25F); // Adjust Y offset by -0.5 to move up
        this.blaster_6 = new ModelRenderer(this, 26, 23);
        this.blaster_6.mirror = true;
        this.blaster_6.setRotationPoint(-1.0F, 1.0F, 3.0F);
        this.blaster_6.addBox(0.0F, 0.0F, 0.0F, 1, 3, 3, 0.0F);
        this.blaster_7 = new ModelRenderer(this, 26, 23);
        this.blaster_7.setRotationPoint(3.0F, 1.0F, 3.0F);
        this.blaster_7.addBox(0.0F, 0.0F, 0.0F, 1, 3, 3, 0.0F);
        this.body = new ModelRenderer(this, 0, 12);
        this.body.setRotationPoint(-4.0F, -2.0F, -1.0F);
        this.body.addBox(0.0F, 0.0F, 0.0F, 8, 7, 6, 0.0F);
        this.leg_left = new ModelRenderer(this, 8, 34);
        this.leg_left.mirror = true;
        this.leg_left.setRotationPoint(2.0F, 5.0F, 0.0F);
        this.leg_left.addBox(0.0F, 0.0F, 0.0F, 2, 7, 2, 0.0F);
        this.backpack_antennae = new ModelRenderer(this, 54, 0);
        this.backpack_antennae.setRotationPoint(4.0F, -8.0F, 2.0F);
        this.backpack_antennae.addBox(0.0F, 0.0F, 0.0F, 3, 8, 0, 0.0F);
        this.calf_left = new ModelRenderer(this, 0, 35);
        this.calf_left.mirror = true;
        this.calf_left.setRotationPoint(0.0F, 7.0F, 0.0F);
        this.calf_left.addBox(0.0F, 0.0F, 0.0F, 2, 7, 2, 0.0F);
        this.arm_right = new ModelRenderer(this, 10, 25);
        this.arm_right.mirror = true;
        this.arm_right.setRotationPoint(-2.0F, 0.0F, 2.0F);
        this.arm_right.addBox(0.0F, 0.0F, 0.0F, 2, 7, 2, 0.0F);
        this.neck = new ModelRenderer(this, 0, 0);
        this.neck.setRotationPoint(3.0F, -8.0F, 3.0F);
        this.neck.addBox(0.0F, 0.0F, 0.0F, 2, 8, 1, 0.0F);
        this.head_extension = new ModelRenderer(this, 17, 0);
        this.head_extension.setRotationPoint(-2.0F, 1.0F, 1.0F);
        this.head_extension.addBox(0.0F, 0.0F, 0.0F, 4, 3, 1, 0.0F);
        this.forearm_left = new ModelRenderer(this, 18, 25);
        this.forearm_left.setRotationPoint(0.0F, 7.0F, 2.0F);
        this.forearm_left.addBox(0.0F, 0.0F, 0.0F, 2, 9, 2, 0.0F);
        this.setRotateAngle(forearm_left, -1.5707963267948966F, 0.0F, 0.0F);
        this.calf_right = new ModelRenderer(this, 0, 35);
        this.calf_right.setRotationPoint(0.0F, 7.0F, 0.0F);
        this.calf_right.addBox(0.0F, 0.0F, 0.0F, 2, 7, 2, 0.0F);
        this.body.addChild(this.arm_left);
        this.forearm_right.addChild(this.blaster);
        this.body.addChild(this.pelvis);
        this.neck.addChild(this.head);
        this.pelvis.addChild(this.leg_right);
        this.blaster_2.addChild(this.blaster_4);
        this.blaster.addChild(this.blaster_2);
        this.body.addChild(this.backpack);
        this.pelvis.addChild(this.body_connector);
        this.arm_right.addChild(this.forearm_right);
        this.blaster_2.addChild(this.blaster_3);
        this.blaster_2.addChild(this.blaster_6);
        this.blaster_2.addChild(this.blaster_7);
        this.pelvis.addChild(this.leg_left);
        this.backpack.addChild(this.backpack_antennae);
        this.leg_left.addChild(this.calf_left);
        this.body.addChild(this.arm_right);
        this.body.addChild(this.neck);
        this.head.addChild(this.head_extension);
        this.arm_left.addChild(this.forearm_left);
        this.leg_right.addChild(this.calf_right);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { 
        this.setRotationAngles(f, f1, f2, f3, f4, f5, entity); 
        this.body.render(f5);
        this.blaster_5.render(f5);
    }
    
    @Override
    public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entity) {
        // Head rotation (yaw for left-right rotation)
        this.head.rotateAngleY = netHeadYaw * ((float)Math.PI / 180F); // Rotates head left-right

        // Arm swing (walking animation)
        //this.arm_right.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * limbSwingAmount;
        //this.blaster_5.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * limbSwingAmount;
        this.arm_left.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * limbSwingAmount;

        // Leg movement (walking animation)
        this.leg_right.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * limbSwingAmount;
        this.leg_left.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * limbSwingAmount;

        // Set interpolation speed factor (higher = faster transition)
        float transitionSpeed = 0.1F;

        // Forearms adjustment with smooth transitions
        if (limbSwingAmount > 0.1F) {  // When walking (i.e., the entity is moving)
            //this.forearm_right.rotateAngleX = lerp(this.forearm_right.rotateAngleX, -((float)Math.PI / 2), transitionSpeed);  // Gradually set to -90 degrees
            this.forearm_left.rotateAngleX = lerp(this.forearm_left.rotateAngleX, -((float)Math.PI / 2), transitionSpeed);   // Gradually set to -90 degrees
            //this.forearm_right.setRotationPoint(lerp(this.forearm_right.rotationPointX, 0.0F, transitionSpeed),
            //                                    lerp(this.forearm_right.rotationPointY, 7.0F, transitionSpeed),
            //                                    lerp(this.forearm_right.rotationPointZ, 2.0F, transitionSpeed));  // Gradually move Z position to 2.0F
            this.forearm_left.setRotationPoint(lerp(this.forearm_left.rotationPointX, 0.0F, transitionSpeed),
                                               lerp(this.forearm_left.rotationPointY, 7.0F, transitionSpeed),
                                               lerp(this.forearm_left.rotationPointZ, 2.0F, transitionSpeed));   // Gradually move Z position to 2.0F
        } else {  // When idle (i.e., the entity is standing still)
            //this.forearm_right.rotateAngleX = lerp(this.forearm_right.rotateAngleX, 0.0F, transitionSpeed);  // Gradually reset forearm rotation to 0
            this.forearm_left.rotateAngleX = lerp(this.forearm_left.rotateAngleX, 0.0F, transitionSpeed);   // Gradually reset forearm rotation to 0
            //this.forearm_right.setRotationPoint(lerp(this.forearm_right.rotationPointX, 0.0F, transitionSpeed),
            //                                    lerp(this.forearm_right.rotationPointY, 7.0F, transitionSpeed),
            //                                    lerp(this.forearm_right.rotationPointZ, 0.0F, transitionSpeed));  // Gradually move Z position to 0
            this.forearm_left.setRotationPoint(lerp(this.forearm_left.rotationPointX, 0.0F, transitionSpeed),
                                               lerp(this.forearm_left.rotationPointY, 7.0F, transitionSpeed),
                                               lerp(this.forearm_left.rotationPointZ, 0.0F, transitionSpeed));   // Gradually move Z position to 0
        }
    }

    private float lerp(float start, float end, float alpha) {
        return start + alpha * (end - start);
    }

    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}
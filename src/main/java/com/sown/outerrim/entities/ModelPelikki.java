package com.sown.outerrim.entities;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class ModelPelikki extends ModelBase {
    public ModelRenderer body;
    public ModelRenderer wing_left;
    public ModelRenderer wing_right;
    public ModelRenderer neck;
    public ModelRenderer tail;
    public ModelRenderer leg_left;
    public ModelRenderer leg_right;
    public ModelRenderer head;
    public ModelRenderer bill;
    public ModelRenderer crest;
    public ModelRenderer left_foot;
    public ModelRenderer right_foot;

    public ModelPelikki() {
        this.textureWidth = 64;
        this.textureHeight = 64;
        this.wing_left = new ModelRenderer(this, 9, 25);
        this.wing_left.setRotationPoint(-1.0F, 0.0F, 2.0F);
        this.wing_left.addBox(0.0F, 0.0F, 0.0F, 1, 5, 7, 0.0F);
        this.wing_right = new ModelRenderer(this, 9, 25);
        this.wing_right.setRotationPoint(7.0F, 0.0F, 2.0F);
        this.wing_right.addBox(0.0F, 0.0F, 0.0F, 1, 5, 7, 0.0F);
        this.crest = new ModelRenderer(this, 0, 17);
        this.crest.setRotationPoint(2.0F, -2.0F, 0.0F);
        this.crest.addBox(-2.0F, 0.0F, -5.0F, 0, 5, 8, 0.0F);
        this.body = new ModelRenderer(this, 0, 0);
        this.body.setRotationPoint(-3.5F, 13.0F, -6.0F);
        this.body.addBox(0.0F, 0.0F, 0.0F, 7, 5, 11, 0.0F);
        this.leg_right = new ModelRenderer(this, 0, 16);
        this.leg_right.mirror = true;
        this.leg_right.setRotationPoint(0.5F, 5.0F, 8.0F);
        this.leg_right.addBox(0.0F, 0.0F, 0.0F, 2, 6, 0, 0.0F);
        this.head = new ModelRenderer(this, 24, 19);
        this.head.setRotationPoint(1.5F, -3.0F, 2.0F);
        this.head.addBox(-2.0F, 0.0F, -5.0F, 4, 3, 6, 0.0F);
        this.left_foot = new ModelRenderer(this, -3, 16);
        this.left_foot.setRotationPoint(0.0F, 5.95F, -6.0F);
        this.left_foot.addBox(0.0F, 0.0F, 0.0F, 4, 0, 8, 0.0F);
        this.neck = new ModelRenderer(this, 0, 0);
        this.neck.setRotationPoint(2.0F, -5.0F, -1.0F);
        this.neck.addBox(0.0F, 0.0F, 0.0F, 3, 8, 2, 0.0F);
        this.bill = new ModelRenderer(this, 8, 16);
        this.bill.setRotationPoint(0.5F, 2.0F, -8.0F);
        this.bill.addBox(-2.0F, 0.0F, -5.0F, 3, 1, 8, 0.0F);
        this.tail = new ModelRenderer(this, 15, 0);
        this.tail.setRotationPoint(0.0F, 1.0F, 11.0F);
        this.tail.addBox(0.0F, 0.0F, 0.0F, 7, 0, 10, 0.0F);
        this.right_foot = new ModelRenderer(this, -3, 16);
        this.right_foot.mirror = true;
        this.right_foot.setRotationPoint(-2.0F, 5.95F, -6.0F);
        this.right_foot.addBox(0.0F, 0.0F, 0.0F, 4, 0, 8, 0.0F);
        this.leg_left = new ModelRenderer(this, 0, 16);
        this.leg_left.setRotationPoint(4.5F, 5.0F, 8.0F);
        this.leg_left.addBox(0.0F, 0.0F, 0.0F, 2, 6, 0, 0.0F);
        this.body.addChild(this.wing_left);
        this.body.addChild(this.wing_right);
        this.head.addChild(this.crest);
        this.body.addChild(this.leg_right);
        this.neck.addChild(this.head);
        this.leg_left.addChild(this.left_foot);
        this.body.addChild(this.neck);
        this.head.addChild(this.bill);
        this.body.addChild(this.tail);
        this.leg_right.addChild(this.right_foot);
        this.body.addChild(this.leg_left);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        this.setRotationAngles(f, f1, f2, f3, f4, f5, entity); 
        this.body.render(f5);
    }

    @Override
    public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn) {
        // Head rotation based on player input
        this.head.rotateAngleY = netHeadYaw * ((float) Math.PI / 180F);
        this.head.rotateAngleX = headPitch * ((float) Math.PI / 180F);

        // Leg walking animation
        this.leg_left.rotateAngleX = MathHelper.cos(limbSwing * 1.0F) * 1.2F * limbSwingAmount;
        this.leg_right.rotateAngleX = MathHelper.cos(limbSwing * 1.0F + (float) Math.PI) * 1.2F * limbSwingAmount;

        // Tail slightly moving up and down
        this.tail.rotateAngleX = MathHelper.cos(ageInTicks * 0.2F) * 0.1F;

        // Wing flapping only if the entity is flying
        if (!entityIn.onGround) {
            this.wing_left.rotateAngleZ = MathHelper.cos(ageInTicks * 0.6F) * (float) Math.PI * 0.25F;
            this.wing_right.rotateAngleZ = -MathHelper.cos(ageInTicks * 0.6F) * (float) Math.PI * 0.25F;
        } else {
            this.wing_left.rotateAngleZ = 0;
            this.wing_right.rotateAngleZ = 0;
        }
    }

    /**
     * This is a helper function from Tabula to set the rotation of model parts
     */
    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}

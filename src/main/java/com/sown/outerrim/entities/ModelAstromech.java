package com.sown.outerrim.entities;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * astromech - sown
 * Created using Tabula 4.1.1
 */
public class ModelAstromech extends ModelBiped {
    public ModelRenderer body;
    public ModelRenderer leg_left;
    public ModelRenderer leg_middle;
    public ModelRenderer leg_right;
    public ModelRenderer head;
    public ModelRenderer leg_left_2;
    public ModelRenderer leg_right_2;

    public ModelAstromech() {
        this.textureWidth = 64;
        this.textureHeight = 64;
        this.body = new ModelRenderer(this, 0, 26);
        this.body.setRotationPoint(-4.0F, 10.0F, -2.6F);
        this.body.addBox(0.0F, 0.0F, 0.0F, 8, 11, 8, 0.0F);
        this.setRotateAngle(body, -0.2792526803190927F, 0.0F, 0.0F);
        this.head = new ModelRenderer(this, 0, 0);
        this.head.setRotationPoint(4.0F, -5.0F, 4.0F);
        this.head.addBox(-4.0F, 0.0F, -4.0F, 8, 5, 8, 0.0F);
        this.leg_left = new ModelRenderer(this, 0, 45);
        this.leg_left.setRotationPoint(4.0F, 11.0F, -1.0F);
        this.leg_left.addBox(0.0F, 0.0F, 0.0F, 2, 10, 4, 0.0F);
        this.leg_right_2 = new ModelRenderer(this, 12, 50);
        this.leg_right_2.mirror = true;
        this.leg_right_2.setRotationPoint(0.0F, 10.0F, -1.0F);
        this.leg_right_2.addBox(0.0F, 0.0F, 0.0F, 2, 3, 6, 0.0F);
        this.leg_middle = new ModelRenderer(this, 28, 52);
        this.leg_middle.setRotationPoint(-1.0F, 21.0F, -4.7F);
        this.leg_middle.addBox(0.0F, 0.0F, 0.0F, 2, 3, 4, 0.0F);
        this.leg_left_2 = new ModelRenderer(this, 12, 50);
        this.leg_left_2.setRotationPoint(0.0F, 10.0F, -1.0F);
        this.leg_left_2.addBox(0.0F, 0.0F, 0.0F, 2, 3, 6, 0.0F);
        this.leg_right = new ModelRenderer(this, 0, 45);
        this.leg_right.mirror = true;
        this.leg_right.setRotationPoint(-6.0F, 11.0F, -1.0F);
        this.leg_right.addBox(0.0F, 0.0F, 0.0F, 2, 10, 4, 0.0F);
        this.body.addChild(this.head);
        this.leg_right.addChild(this.leg_right_2);
        this.leg_left.addChild(this.leg_left_2);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        this.body.render(f5);
        this.leg_middle.render(f5);
        this.leg_right.render(f5);
        this.leg_left.render(f5);
    }

    @Override
    public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entity) {
        super.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor, entity);

        this.head.rotateAngleY = netHeadYaw * ((float)Math.PI / 180F); // Rotates head left-right

    }

    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}

package com.sown.outerrim.entities;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class ModelQuadrupedAnimal extends ModelBase {
    public ModelRenderer head;
    public ModelRenderer leg1;
    public ModelRenderer hornLeft;
    public ModelRenderer leg4;
    public ModelRenderer hornRight;
    public ModelRenderer leg2;
    public ModelRenderer leg3;
    public ModelRenderer body;
    public ModelRenderer shape9;

    public ModelQuadrupedAnimal() {
        this.textureWidth = 64;
        this.textureHeight = 32;
        this.shape9 = new ModelRenderer(this, 0, 0);
        this.shape9.setRotationPoint(-1.0F, -1.0F, -7.9F);
        this.shape9.addBox(0.0F, 0.0F, 0.0F, 2, 2, 2, 0.0F);
        this.head = new ModelRenderer(this, 0, 0);
        this.head.setRotationPoint(0.0F, 10.0F, -8.0F);
        this.head.addBox(-4.0F, -4.0F, -6.0F, 8, 8, 6, 0.0F);
        this.leg2 = new ModelRenderer(this, 0, 16);
        this.leg2.setRotationPoint(4.0F, 18.0F, -6.0F);
        this.leg2.addBox(-2.0F, 0.0F, -2.0F, 4, 6, 4, 0.0F);
        this.body = new ModelRenderer(this, 18, 4);
        this.body.setRotationPoint(0.0F, 18.0F, -1.0F);
        this.body.addBox(-6.0F, -10.0F, -7.0F, 12, 10, 17, 0.0F);
        this.leg1 = new ModelRenderer(this, 0, 16);
        this.leg1.setRotationPoint(-4.0F, 18.0F, -6.0F);
        this.leg1.addBox(-2.0F, 0.0F, -2.0F, 4, 6, 4, 0.0F);
        this.hornLeft = new ModelRenderer(this, 22, 0);
        this.hornLeft.setRotationPoint(0.0F, 10.0F, -8.0F);
        this.hornLeft.addBox(-5.0F, -5.0F, -4.0F, 2, 3, 1, 0.0F);
        this.hornRight = new ModelRenderer(this, 22, 0);
        this.hornRight.setRotationPoint(-1.0F, 10.0F, -8.0F);
        this.hornRight.addBox(4.0F, -5.0F, -4.0F, 2, 3, 1, 0.0F);
        this.leg3 = new ModelRenderer(this, 0, 16);
        this.leg3.setRotationPoint(-4.0F, 18.0F, 7.0F);
        this.leg3.addBox(-2.0F, 0.0F, -2.0F, 4, 6, 4, 0.0F);
        this.leg4 = new ModelRenderer(this, 0, 16);
        this.leg4.setRotationPoint(4.0F, 18.0F, 7.0F);
        this.leg4.addBox(-2.0F, 0.0F, -2.0F, 4, 6, 4, 0.0F);
        this.head.addChild(this.shape9);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { 
        setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        this.head.render(f5);
        this.leg2.render(f5);
        this.body.render(f5);
        this.leg1.render(f5);
        this.hornLeft.render(f5);
        this.hornRight.render(f5);
        this.leg3.render(f5);
        this.leg4.render(f5);
    }

    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
        super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        float baseRotationAngle = +1.05F;
        this.head.rotateAngleX = (f4 / 57.295776f);
        this.head.rotateAngleY = (baseRotationAngle / 3) + (f3 / 57.295776f);
        this.leg1.rotateAngleX = MathHelper.sin((float)((float)((double)(f * 0.6662f) + 3.141592653589793))) * f1;
        this.leg2.rotateAngleX = MathHelper.sin((float)(f * 0.6662f)) * f1;
        this.leg3.rotateAngleX = MathHelper.sin((float)(f * 0.6662f)) * f1;
        this.leg4.rotateAngleX = MathHelper.sin((float)((float)((double)(f * 0.6662f) + 3.141592653589793))) * f1;
    }
}

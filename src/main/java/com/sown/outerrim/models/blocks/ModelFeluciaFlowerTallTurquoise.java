package com.sown.outerrim.models.blocks;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelFeluciaFlowerTallTurquoise extends ModelBase {
    public ModelRenderer bottom_1;
    public ModelRenderer bottom_2;
    public ModelRenderer top_1;
    public ModelRenderer top_2;
    public ModelRenderer top_3;

    public ModelFeluciaFlowerTallTurquoise() {
        this.textureWidth = 77;
        this.textureHeight = 77;
        this.top_1 = new ModelRenderer(this, 0, 0);
        this.top_1.setRotationPoint(-3.0F, -2.0F, -3.0F);
        this.top_1.addBox(0.0F, 0.0F, 0.0F, 14, 6, 14, 0.0F);
        this.bottom_1 = new ModelRenderer(this, 42, 2);
        this.bottom_1.setRotationPoint(-2.0F, 16.0F, -2.0F);
        this.bottom_1.addBox(0.0F, 0.0F, 0.0F, 4, 8, 4, 0.0F);
        this.bottom_2 = new ModelRenderer(this, 0, 20);
        this.bottom_2.setRotationPoint(-2.0F, -15.0F, -2.0F);
        this.bottom_2.addBox(0.0F, 0.0F, 0.0F, 8, 17, 8, 0.0F);
        this.top_2 = new ModelRenderer(this, -20, 45);
        this.top_2.setRotationPoint(-3.0F, 0.0F, -3.0F);
        this.top_2.addBox(0.0F, 0.0F, 0.0F, 20, 0, 20, 0.0F);
        this.top_3 = new ModelRenderer(this, 0, 65);
        this.top_3.mirror = true;
        this.top_3.setRotationPoint(19.0F, -12.0F, 19.0F);
        this.top_3.addBox(0.0F, 0.0F, 0.0F, 14, 12, 0, 0.0F);
        this.setRotateAngle(top_3, 0.0F, 2.356194490192345F, 0.0F);
        this.bottom_2.addChild(this.top_1);
        this.bottom_1.addChild(this.bottom_2);
        this.top_1.addChild(this.top_2);
        this.top_2.addChild(this.top_3);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { 
        this.bottom_1.render(f5);
    }

    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}

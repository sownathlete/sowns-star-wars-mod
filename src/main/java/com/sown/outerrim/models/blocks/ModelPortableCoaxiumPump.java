package com.sown.outerrim.models.blocks;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelPortableCoaxiumPump extends ModelBase {
    public ModelRenderer base;
    public ModelRenderer mid;
    public ModelRenderer top;
    public ModelRenderer handle;

    public ModelPortableCoaxiumPump() {
        this.textureWidth = 64;
        this.textureHeight = 32;
        this.mid = new ModelRenderer(this, 0, 11);
        this.mid.setRotationPoint(1.0F, -9.0F, 1.0F);
        this.mid.addBox(0.0F, 0.0F, 0.0F, 6, 9, 6, 0.0F);
        this.base = new ModelRenderer(this, 0, 0);
        this.base.setRotationPoint(-4.0F, 21.0F, -4.0F);
        this.base.addBox(0.0F, 0.0F, 0.0F, 8, 3, 8, 0.0F);
        this.top = new ModelRenderer(this, 0, 0);
        this.top.setRotationPoint(2.0F, -2.0F, 2.0F);
        this.top.addBox(0.0F, 0.0F, 0.0F, 2, 2, 2, 0.0F);
        this.handle = new ModelRenderer(this, 32, 0);
        this.handle.setRotationPoint(5.0F, -1.0F, 3.0F);
        this.handle.addBox(0.0F, 0.0F, 0.0F, 4, 12, 0, 0.0F);
        this.base.addChild(this.mid);
        this.mid.addChild(this.top);
        this.mid.addChild(this.handle);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { 
        this.base.render(f5);
    }

    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}

package com.sown.outerrim.models.blocks;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * kamino_railing - sown
 * Created using Tabula 4.1.1
 */
public class ModelKaminoRailing extends ModelBase {
    public ModelRenderer shape1;
    public ModelRenderer shape2;
    public ModelRenderer shape3;

    public ModelKaminoRailing() {
        this.textureWidth = 64;
        this.textureHeight = 64;
        this.shape3 = new ModelRenderer(this, 0, 7);
        this.shape3.mirror = true;
        this.shape3.setRotationPoint(6.0F, 11.0F, -6.0F);
        this.shape3.addBox(0.0F, 0.0F, 0.0F, 2, 13, 2, 0.0F);
        this.shape1 = new ModelRenderer(this, 0, 0);
        this.shape1.setRotationPoint(-8.0F, 8.0F, -8.0F);
        this.shape1.addBox(0.0F, 0.0F, 0.0F, 16, 3, 4, 0.0F);
        this.shape2 = new ModelRenderer(this, 0, 7);
        this.shape2.setRotationPoint(-8.0F, 11.0F, -6.0F);
        this.shape2.addBox(0.0F, 0.0F, 0.0F, 2, 13, 2, 0.0F);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { 
        this.shape3.render(f5);
        this.shape1.render(f5);
        this.shape2.render(f5);
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

package com.sown.outerrim.models.blocks;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * kamino_door_small - sown
 * Created using Tabula 4.1.1
 */
public class ModelKaminoDoorSmall extends ModelBase {
    public ModelRenderer door;
    public ModelRenderer frame1;
    public ModelRenderer frame2;
    public ModelRenderer frame3;

    public ModelKaminoDoorSmall() {
        this.textureWidth = 156;
        this.textureHeight = 156;
        this.door = new ModelRenderer(this, 0, 32);
        this.door.setRotationPoint(-16.0F, -24.0F, -3.0F);
        this.door.addBox(0.0F, 0.0F, 0.0F, 32, 48, 6, 0.0F);
        this.frame3 = new ModelRenderer(this, 0, 0);
        this.frame3.setRotationPoint(-24.0F, -39.0F, -8.0F);
        this.frame3.addBox(0.0F, 0.0F, 0.0F, 48, 16, 16, 0.0F);
        this.frame2 = new ModelRenderer(this, 0, 86);
        this.frame2.setRotationPoint(16.0F, -24.0F, -8.0F);
        this.frame2.addBox(0.0F, 0.0F, 0.0F, 8, 48, 16, 0.0F);
        this.frame1 = new ModelRenderer(this, 60, 70);
        this.frame1.setRotationPoint(-24.0F, -24.0F, -8.0F);
        this.frame1.addBox(0.0F, 0.0F, 0.0F, 8, 48, 16, 0.0F);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { 
        this.door.render(f5);
        this.frame3.render(f5);
        this.frame2.render(f5);
        this.frame1.render(f5);
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

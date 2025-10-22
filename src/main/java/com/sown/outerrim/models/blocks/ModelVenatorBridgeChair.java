package com.sown.outerrim.models.blocks;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * venator_bridge_chair - effref
 * Created using Tabula 4.1.1
 */
public class ModelVenatorBridgeChair extends ModelBase {
    public ModelRenderer chair_1;
    public ModelRenderer chair_2;
    public ModelRenderer chair_3;
    public ModelRenderer chair_4;
    public ModelRenderer chair_5;

    public ModelVenatorBridgeChair() {
        this.textureWidth = 64;
        this.textureHeight = 64;
        this.chair_2 = new ModelRenderer(this, 0, 21);
        this.chair_2.setRotationPoint(-3.0F, -3.0F, -2.0F);
        this.chair_2.addBox(0.0F, 0.0F, 0.0F, 12, 3, 9, 0.0F);
        this.chair_1 = new ModelRenderer(this, 0, 33);
        this.chair_1.setRotationPoint(-3.0F, 16.0F, -2.0F);
        this.chair_1.addBox(0.0F, 0.0F, 0.0F, 6, 8, 7, 0.0F);
        this.chair_4 = new ModelRenderer(this, 49, 47);
        this.chair_4.setRotationPoint(0.0F, -3.0F, 2.0F);
        this.chair_4.addBox(0.0F, 0.0F, 0.0F, 0, 3, 7, 0.0F);
        this.chair_5 = new ModelRenderer(this, 49, 47);
        this.chair_5.mirror = true;
        this.chair_5.setRotationPoint(12.0F, -3.0F, 2.0F);
        this.chair_5.addBox(0.0F, 0.0F, 0.0F, 0, 3, 7, 0.0F);
        this.chair_3 = new ModelRenderer(this, 0, 0);
        this.chair_3.setRotationPoint(-3.0F, -18.0F, 7.0F);
        this.chair_3.addBox(0.0F, 0.0F, 0.0F, 12, 18, 3, 0.0F);
        this.chair_1.addChild(this.chair_2);
        this.chair_2.addChild(this.chair_4);
        this.chair_2.addChild(this.chair_5);
        this.chair_1.addChild(this.chair_3);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { 
        this.chair_1.render(f5);
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

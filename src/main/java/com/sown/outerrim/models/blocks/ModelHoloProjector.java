package com.sown.outerrim.models.blocks;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelHoloProjector extends ModelBase {
    public ModelRenderer shape3;
    public ModelRenderer shape4;
    public ModelRenderer shape5;
    public ModelRenderer shape6;

    public ModelHoloProjector() {
        this.textureWidth = 64;
        this.textureHeight = 64;
        this.shape3 = new ModelRenderer(this, 0, 0);
        this.shape3.setRotationPoint(-5.0F, 22.0F, -5.0F);
        this.shape3.addBox(0.0F, 0.0F, 0.0F, 10, 2, 10, 0.0F);
        this.shape4 = new ModelRenderer(this, 35, 16);
        this.shape4.setRotationPoint(3.0F, -5.0F, 3.0F);
        this.shape4.addBox(0.0F, 0.0F, 0.0F, 4, 5, 4, 0.0F);
        this.shape5 = new ModelRenderer(this, 6, 33);
        this.shape5.setRotationPoint(-4.0F, -6.0F, -4.0F);
        this.shape5.addBox(0.0F, 0.0F, 0.0F, 12, 6, 12, 0.0F);
        this.shape6 = new ModelRenderer(this, 0, 19);
        this.shape6.setRotationPoint(2.0F, -3.0F, 2.0F);
        this.shape6.addBox(0.0F, 0.0F, 0.0F, 8, 3, 8, 0.0F);

        // You can still add children as a fallback hierarchy
        this.shape3.addChild(this.shape4);
        this.shape4.addChild(this.shape5);
        this.shape5.addChild(this.shape6);
    }

    // Render base shapes (shape3, shape4, shape5)
    public void renderBase() {
        this.shape3.render(0.0625F); // Scale factor for rendering
    }

    // Render shape6 (only when needed)
    public void renderShape6() {
        // shape6 is still a child of shape5, but this is explicitly called in RenderHoloProjector
        this.shape6.render(0.0625F); // Scale factor for rendering
    }

    // Helper function for setting rotation
    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}

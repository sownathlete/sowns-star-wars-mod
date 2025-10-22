package com.sown.outerrim.models.blocks;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * venator_bridge_mechanical_table - sown
 * Created using Tabula 4.1.1
 */
public class ModelVenatorBridgeMechanicalTable extends ModelBase {
    public ModelRenderer shape1;
    public ModelRenderer shape2;

    public ModelVenatorBridgeMechanicalTable() {
        this.textureWidth = 64;
        this.textureHeight = 64;
        this.shape2 = new ModelRenderer(this, 3, 42);
        this.shape2.setRotationPoint(-8.0F, 9.0F, -10.0F);
        this.shape2.addBox(0.0F, 0.0F, 0.0F, 16, 6, 9, 0.0F);
        this.shape1 = new ModelRenderer(this, 0, 0);
        this.shape1.setRotationPoint(-8.0F, -4.0F, -1.0F);
        this.shape1.addBox(0.0F, 0.0F, 0.0F, 16, 28, 9, 0.0F);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { 
        this.shape2.render(f5);
        this.shape1.render(f5);
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

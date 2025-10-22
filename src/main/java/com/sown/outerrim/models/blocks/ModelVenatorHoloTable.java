package com.sown.outerrim.models.blocks;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * venator_holo_table - sown
 * Created using Tabula 4.1.1
 */
public class ModelVenatorHoloTable extends ModelBase {
    public ModelRenderer table;
    public ModelRenderer table2;

    public ModelVenatorHoloTable() {
        this.textureWidth = 256;
        this.textureHeight = 256;
        this.table2 = new ModelRenderer(this, 0, 0);
        this.table2.setRotationPoint(-8.0F, -9.0F, -8.0F);
        this.table2.addBox(0.0F, 0.0F, 0.0F, 64, 9, 64, 0.0F);
        this.table = new ModelRenderer(this, 0, 74);
        this.table.setRotationPoint(-24.0F, 16.0F, -24.0F);
        this.table.addBox(0.0F, 0.0F, 0.0F, 48, 8, 48, 0.0F);
        this.table.addChild(this.table2);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { 
        this.table.render(f5);
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

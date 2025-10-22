package com.sown.outerrim.models.blocks;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * kamino_door_large - sown
 * Created using Tabula 4.1.1
 */
public class ModelKaminoDoorLarge extends ModelBase {
    public ModelRenderer door_part_1;
    public ModelRenderer door_part_2;

    public ModelKaminoDoorLarge() {
        this.textureWidth = 178;
        this.textureHeight = 178;
        this.door_part_1 = new ModelRenderer(this, 0, 80);
        this.door_part_1.mirror = true;
        this.door_part_1.setRotationPoint(-48.0F, -48.0F, -4.0F);
        this.door_part_1.addBox(0.0F, 0.0F, 0.0F, 48, 72, 8, 0.0F);
        this.door_part_2 = new ModelRenderer(this, 0, 0);
        this.door_part_2.mirror = true;
        this.door_part_2.setRotationPoint(0.0F, -48.0F, -4.0F);
        this.door_part_2.addBox(0.0F, 0.0F, 0.0F, 48, 72, 8, 0.0F);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { 
        this.door_part_1.render(f5);
        this.door_part_2.render(f5);
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

package com.sown.outerrim.models.blocks;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * venator_bridge_door - sown
 * Created using Tabula 4.1.1
 */
public class ModelVenatorBridgeDoor extends ModelBase {
    public ModelRenderer door;

    public ModelVenatorBridgeDoor() {
        this.textureWidth = 350;
        this.textureHeight = 350;
        this.door = new ModelRenderer(this, 0, 0);
        this.door.setRotationPoint(-72.0F, -72.0F, -8.0F);
        this.door.addBox(0.0F, 0.0F, 0.0F, 144, 96, 16, 0.0F);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { 
        this.door.render(f5);
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

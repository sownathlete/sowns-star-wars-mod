package com.sown.outerrim.entities;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelDroidekaShield extends ModelBase {
    public ModelRenderer shield;

    public ModelDroidekaShield() {
        this.textureWidth = 48;
        this.textureHeight = 48;
        this.shield = new ModelRenderer(this, 0, 0);
        this.shield.setRotationPoint(-24.0F, -17.0F, 0.0F);
        this.shield.addBox(0.0F, 0.0F, 0.0F, 48, 48, 0, 0.0F);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { 
        this.shield.render(f5);
    }

    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}

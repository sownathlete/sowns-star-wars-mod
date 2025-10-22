package com.sown.outerrim.models.blocks;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelVenatorScreen extends ModelBase {
    public ModelRenderer screen;
    public ModelRenderer screen_2;

    public ModelVenatorScreen() {
        this.textureWidth = 184;
        this.textureHeight = 184;
        this.screen = new ModelRenderer(this, 0, 0);
        this.screen.setRotationPoint(44.0F, 8.0F, -2.0F);
        this.screen.addBox(0.0F, 0.0F, 0.0F, 4, 16, 88, 0.0F);
        this.setRotateAngle(screen, 0.0F, -1.5707963267948966F, 0.0F);
        this.screen_2 = new ModelRenderer(this, 0, 232);
        this.screen_2.setRotationPoint(2.0F, -48.0F, 0.0F);
        this.screen_2.addBox(0.0F, 0.0F, 0.0F, 0, 48, 88, 0.0F);
        this.screen.addChild(this.screen_2);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { 
        this.screen.render(f5);
    }

    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}

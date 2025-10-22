package com.sown.outerrim.models.blocks;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

// Made with Blockbench 4.2.5
// Exported for Minecraft version 1.7 - 1.12
// Paste this class into your mod and generate all required imports


public class ModelCoaxiumRack extends ModelBase {
    private final ModelRenderer base;
    private final ModelRenderer bars2;
    private final ModelRenderer cube_r1;
    private final ModelRenderer bar_left_r1;
    private final ModelRenderer bars;
    private final ModelRenderer cube_r2;

    public ModelCoaxiumRack() {
        this.textureWidth = 64;
        this.textureHeight = 64;

        this.base = new ModelRenderer(this);
        this.base.setRotationPoint(8.0F, 24.0F, -8.0F);
        this.base.cubeList.add(new ModelBox(this.base, 0, 0, -16.0F, -16.0F, 0.0F, 16, 16, 16, 0.0F));

        this.bars2 = new ModelRenderer(this);
        this.bars2.setRotationPoint(0.0F, -15.0F, 0.0F);
        this.base.addChild(this.bars2);


        this.cube_r1 = new ModelRenderer(this);
        this.cube_r1.setRotationPoint(0.0F, -3.0F, 0.0F);
        this.bars2.addChild(this.cube_r1);
        this.setRotationAngle(this.cube_r1, 0.3927F, 0.0F, 0.0F);
        this.cube_r1.cubeList.add(new ModelBox(this.cube_r1, 30, 29, -16.0F, -1.0F, -1.0F, 16, 1, 1, 0.0F));

        this.bar_left_r1 = new ModelRenderer(this);
        this.bar_left_r1.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.bars2.addChild(this.bar_left_r1);
        this.setRotationAngle(this.bar_left_r1, 0.3927F, 0.0F, 0.0F);
        this.bar_left_r1.cubeList.add(new ModelBox(this.bar_left_r1, 27, 9, -17.0F, -4.0F, 0.0F, 1, 6, 1, 0.0F));
        this.bar_left_r1.cubeList.add(new ModelBox(this.bar_left_r1, 27, 9, 0.0F, -4.0F, 0.0F, 1, 6, 1, 0.0F));

        this.bars = new ModelRenderer(this);
        this.bars.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.bars2.addChild(this.bars);


        this.cube_r2 = new ModelRenderer(this);
        this.cube_r2.setRotationPoint(0.0F, 0.0F, 15.0F);
        this.bars.addChild(this.cube_r2);
        this.setRotationAngle(this.cube_r2, -0.3927F, 0.0F, 0.0F);
        this.cube_r2.cubeList.add(new ModelBox(this.cube_r2, 30, 29, -16.0F, -4.2619F, 0.0399F, 16, 1, 1, 0.0F));
        this.cube_r2.cubeList.add(new ModelBox(this.cube_r2, 27, 9, -17.0F, -4.4903F, -0.1082F, 1, 6, 1, 0.0F));
        this.cube_r2.cubeList.add(new ModelBox(this.cube_r2, 27, 9, 0.0F, -4.4903F, -0.1082F, 1, 6, 1, 0.0F));
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        this.base.render(f5);
    }

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}
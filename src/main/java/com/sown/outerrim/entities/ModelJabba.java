/*
 * Decompiled with CFR 0.148.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelRenderer
 *  net.minecraft.entity.Entity
 */
package com.sown.outerrim.entities;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelJabba
extends ModelBase {
    public ModelRenderer shape1;
    public ModelRenderer shape3;
    public ModelRenderer shape34;
    public ModelRenderer shape4;
    public ModelRenderer shape17;
    public ModelRenderer shape29;
    public ModelRenderer shape41;
    public ModelRenderer shape5;
    public ModelRenderer shape8;
    public ModelRenderer shape6;
    public ModelRenderer shape7;
    public ModelRenderer shape9;
    public ModelRenderer shape15;
    public ModelRenderer shape10;
    public ModelRenderer shape16;
    public ModelRenderer shape11;
    public ModelRenderer shape12;
    public ModelRenderer shape13;
    public ModelRenderer shape14;
    public ModelRenderer shape18;
    public ModelRenderer shape39;
    public ModelRenderer shape19;
    public ModelRenderer shape40;
    public ModelRenderer shape22;
    public ModelRenderer shape23;
    public ModelRenderer shape30;
    public ModelRenderer shape37;
    public ModelRenderer shape31;
    public ModelRenderer shape38;
    public ModelRenderer shape32;
    public ModelRenderer shape33;
    public ModelRenderer shape35;
    public ModelRenderer shape36;

    private float animationTime = 0;

    public ModelJabba() {
        this.textureWidth = 256;
        this.textureHeight = 256;
        this.shape36 = new ModelRenderer((ModelBase)this, 0, 150);
        this.shape36.setRotationPoint(-5.9f, 2.0f, 1.0f);
        this.shape36.addBox(0.0f, 0.0f, 0.0f, 6, 4, 4, 0.0f);
        this.setRotateAngle(this.shape36, 0.0f, -0.02617994f, 0.0f);
        this.shape9 = new ModelRenderer((ModelBase)this, 45, 2);
        this.shape9.setRotationPoint(3.0f, -1.0f, 3.0f);
        this.shape9.addBox(0.0f, 0.0f, 0.0f, 8, 1, 8, 0.0f);
        this.shape11 = new ModelRenderer((ModelBase)this, 220, 35);
        this.shape11.setRotationPoint(-3.0f, 1.0f, 0.0f);
        this.shape11.addBox(0.0f, 0.0f, 0.0f, 3, 4, 1, 0.0f);
        this.shape31 = new ModelRenderer((ModelBase)this, 129, 129);
        this.shape31.setRotationPoint(0.0f, -2.6f, 8.75f);
        this.shape31.addBox(0.0f, 0.0f, 0.0f, 3, 3, 4, 0.0f);
        this.setRotateAngle(this.shape31, -0.2617994f, 0.0f, 0.0f);
        this.shape8 = new ModelRenderer((ModelBase)this, 220, 2);
        this.shape8.setRotationPoint(8.0f, -1.0f, -0.75f);
        this.shape8.addBox(0.0f, 0.0f, 0.0f, 4, 5, 4, 0.0f);
        this.shape34 = new ModelRenderer((ModelBase)this, 0, 104);
        this.shape34.setRotationPoint(-18.4f, 6.0f, 5.0f);
        this.shape34.addBox(0.0f, 0.0f, 0.0f, 20, 14, 14, 0.0f);
        this.setRotateAngle(this.shape34, 0.0f, -0.10471976f, 0.0f);
        this.shape7 = new ModelRenderer((ModelBase)this, 100, 100);
        this.shape7.setRotationPoint(1.0f, -4.0f, 1.0f);
        this.shape7.addBox(0.0f, 0.0f, 0.0f, 14, 4, 14, 0.0f);
        this.shape33 = new ModelRenderer((ModelBase)this, 150, 170);
        this.shape33.setRotationPoint(0.0f, 0.6f, 0.9f);
        this.shape33.addBox(0.0f, 0.0f, 0.0f, 4, 5, 3, 0.0f);
        this.setRotateAngle(this.shape33, -0.18203785f, 0.0f, 0.0f);
        this.shape6 = new ModelRenderer((ModelBase)this, 146, 2);
        this.shape6.setRotationPoint(1.0f, -1.0f, 1.0f);
        this.shape6.addBox(0.0f, 0.0f, 0.0f, 16, 1, 16, 0.0f);
        this.shape22 = new ModelRenderer((ModelBase)this, 150, 190);
        this.shape22.setRotationPoint(0.0f, 0.0f, 1.3f);
        this.shape22.addBox(0.0f, 0.0f, 0.0f, 4, 6, 3, 0.0f);
        this.setRotateAngle(this.shape22, -0.5235988f, 0.0f, 0.0f);
        this.shape30 = new ModelRenderer((ModelBase)this, 0, 200);
        this.shape30.setRotationPoint(8.0f, 0.0f, 3.0f);
        this.shape30.addBox(0.0f, 0.0f, 0.0f, 3, 4, 8, 0.0f);
        this.shape29 = new ModelRenderer((ModelBase)this, 0, 170);
        this.shape29.setRotationPoint(16.0f, 5.5f, -5.0f);
        this.shape29.addBox(0.0f, 0.0f, 0.0f, 11, 4, 4, 0.0f);
        this.setRotateAngle(this.shape29, 0.2617994f, 0.0f, 0.0f);
        this.shape5 = new ModelRenderer((ModelBase)this, 71, 0);
        this.shape5.setRotationPoint(1.0f, -2.0f, 1.0f);
        this.shape5.addBox(0.0f, 0.0f, 0.0f, 18, 2, 18, 0.0f);
        this.shape38 = new ModelRenderer((ModelBase)this, 229, 63);
        this.shape38.setRotationPoint(3.0f, 2.0f, -3.0f);
        this.shape38.addBox(0.0f, 0.0f, 0.0f, 1, 0, 7, 0.0f);
        this.shape14 = new ModelRenderer((ModelBase)this, 220, 26);
        this.shape14.setRotationPoint(3.0f, 1.0f, 0.0f);
        this.shape14.addBox(0.0f, 0.0f, 0.0f, 3, 3, 1, 0.0f);
        this.shape1 = new ModelRenderer((ModelBase)this, 0, 57);
        this.shape1.setRotationPoint(-14.5f, 4.3f, -12.0f);
        this.shape1.addBox(0.0f, 0.0f, 0.0f, 29, 20, 24, 0.0f);
        this.shape15 = new ModelRenderer((ModelBase)this, 150, 150);
        this.shape15.setRotationPoint(5.0f, 2.0f, -1.0f);
        this.shape15.addBox(0.0f, 0.0f, 0.0f, 4, 2, 1, 0.0f);
        this.shape39 = new ModelRenderer((ModelBase)this, 211, 70);
        this.shape39.mirror = true;
        this.shape39.setRotationPoint(0.0f, 2.0f, -1.0f);
        this.shape39.addBox(0.0f, 0.0f, 0.0f, 11, 0, 1, 0.0f);
        this.shape16 = new ModelRenderer((ModelBase)this, 150, 158);
        this.shape16.setRotationPoint(1.0f, -2.0f, 0.0f);
        this.shape16.addBox(0.0f, 0.0f, 0.0f, 2, 2, 1, 0.0f);
        this.shape12 = new ModelRenderer((ModelBase)this, 220, 15);
        this.shape12.setRotationPoint(4.0f, 1.0f, 0.0f);
        this.shape12.addBox(0.0f, 0.0f, 0.0f, 3, 4, 1, 0.0f);
        this.shape35 = new ModelRenderer((ModelBase)this, 0, 135);
        this.shape35.setRotationPoint(-15.7f, 8.0f, 4.0f);
        this.shape35.addBox(0.0f, 0.0f, 0.0f, 16, 6, 6, 0.0f);
        this.setRotateAngle(this.shape35, 0.0f, -0.05235988f, 0.0f);
        this.shape40 = new ModelRenderer((ModelBase)this, 229, 63);
        this.shape40.mirror = true;
        this.shape40.setRotationPoint(-1.0f, 2.0f, 0.0f);
        this.shape40.addBox(0.0f, 0.0f, 0.0f, 1, 0, 7, 0.0f);
        this.shape23 = new ModelRenderer((ModelBase)this, 220, 220);
        this.shape23.setRotationPoint(0.0f, 0.6f, 0.9f);
        this.shape23.addBox(0.0f, 0.0f, 0.0f, 4, 5, 3, 0.0f);
        this.setRotateAngle(this.shape23, -0.18203785f, 0.0f, 0.0f);
        this.shape13 = new ModelRenderer((ModelBase)this, 220, 45);
        this.shape13.setRotationPoint(-3.0f, 1.0f, 0.0f);
        this.shape13.addBox(0.0f, 0.0f, 0.0f, 3, 3, 1, 0.0f);
        this.shape41 = new ModelRenderer((ModelBase)this, 190, 106);
        this.shape41.setRotationPoint(8.0f, 4.0f, 0.0f);
        this.shape41.addBox(0.0f, 0.0f, 0.0f, 8, 5, 1, 0.0f);
        this.setRotateAngle(this.shape41, -0.17453292f, 0.0f, 0.0f);
        this.shape32 = new ModelRenderer((ModelBase)this, 200, 200);
        this.shape32.setRotationPoint(-1.0f, 0.0f, 1.3f);
        this.shape32.addBox(0.0f, 0.0f, 0.0f, 4, 6, 3, 0.0f);
        this.setRotateAngle(this.shape32, -0.5235988f, 0.0f, 0.0f);
        this.shape17 = new ModelRenderer((ModelBase)this, 0, 185);
        this.shape17.setRotationPoint(-3.0f, 5.5f, -5.0f);
        this.shape17.addBox(0.0f, 0.0f, 0.0f, 11, 4, 4, 0.0f);
        this.setRotateAngle(this.shape17, 0.2617994f, 0.0f, 0.0f);
        this.shape19 = new ModelRenderer((ModelBase)this, 110, 129);
        this.shape19.setRotationPoint(0.0f, -2.6f, 8.75f);
        this.shape19.addBox(0.0f, 0.0f, 0.0f, 3, 3, 4, 0.0f);
        this.setRotateAngle(this.shape19, -0.2617994f, 0.0f, 0.0f);
        this.shape37 = new ModelRenderer((ModelBase)this, 211, 70);
        this.shape37.setRotationPoint(0.0f, 2.0f, -1.0f);
        this.shape37.addBox(0.0f, 0.0f, 0.0f, 11, 0, 1, 0.0f);
        this.setRotateAngle(this.shape37, 0.02443461f, 0.0f, 0.0f);
        this.shape3 = new ModelRenderer((ModelBase)this, 110, 57);
        this.shape3.setRotationPoint(2.5f, -9.0f, 1.0f);
        this.shape3.addBox(0.0f, 0.0f, 0.0f, 24, 9, 22, 0.0f);
        this.shape10 = new ModelRenderer((ModelBase)this, 5, 20);
        this.shape10.setRotationPoint(3.0f, -1.0f, 3.0f);
        this.shape10.addBox(0.0f, 0.0f, 0.0f, 2, 1, 2, 0.0f);
        this.shape18 = new ModelRenderer((ModelBase)this, 30, 200);
        this.shape18.setRotationPoint(0.0f, 0.0f, 3.0f);
        this.shape18.addBox(0.0f, 0.0f, 0.0f, 3, 4, 8, 0.0f);
        this.shape4 = new ModelRenderer((ModelBase)this, 0, 29);
        this.shape4.setRotationPoint(2.0f, -4.0f, 1.0f);
        this.shape4.addBox(0.0f, 0.0f, 0.0f, 20, 4, 20, 0.0f);
        this.shape35.addChild(this.shape36);
        this.shape7.addChild(this.shape9);
        this.shape8.addChild(this.shape11);
        this.shape30.addChild(this.shape31);
        this.shape4.addChild(this.shape8);
        this.shape1.addChild(this.shape34);
        this.shape6.addChild(this.shape7);
        this.shape32.addChild(this.shape33);
        this.shape5.addChild(this.shape6);
        this.shape19.addChild(this.shape22);
        this.shape29.addChild(this.shape30);
        this.shape3.addChild(this.shape29);
        this.shape4.addChild(this.shape5);
        this.shape30.addChild(this.shape38);
        this.shape12.addChild(this.shape14);
        this.shape7.addChild(this.shape15);
        this.shape17.addChild(this.shape39);
        this.shape15.addChild(this.shape16);
        this.shape8.addChild(this.shape12);
        this.shape34.addChild(this.shape35);
        this.shape18.addChild(this.shape40);
        this.shape22.addChild(this.shape23);
        this.shape11.addChild(this.shape13);
        this.shape3.addChild(this.shape41);
        this.shape31.addChild(this.shape32);
        this.shape3.addChild(this.shape17);
        this.shape18.addChild(this.shape19);
        this.shape29.addChild(this.shape37);
        this.shape1.addChild(this.shape3);
        this.shape9.addChild(this.shape10);
        this.shape17.addChild(this.shape18);
        this.shape3.addChild(this.shape4);
    }

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        // Use system time or world time to drive the animation
        this.animationTime = (float)entity.ticksExisted + f2;

        // Update tail segments rotation with a slower speed
        float speedFactor = 0.05f; // Adjust the speed factor to slow down the animation
        this.shape34.rotateAngleY = 0.1f * (float)Math.sin(this.animationTime * speedFactor);
        this.shape35.rotateAngleY = 0.1f * (float)Math.sin(this.animationTime * speedFactor + Math.PI / 6);
        this.shape36.rotateAngleY = 0.1f * (float)Math.sin(this.animationTime * speedFactor + Math.PI / 3);

        this.shape1.render(f5);
    }

    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}


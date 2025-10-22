package com.sown.outerrim.models.blocks;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * ModelCarbonite - Either Mojang or a mod author
 * Created using Tabula 4.1.1
 */
public class ModelCarbonite extends ModelBase {
    public ModelRenderer main;
    public ModelRenderer head;
    public ModelRenderer body;
    public ModelRenderer R1;
    public ModelRenderer R2;
    public ModelRenderer R3;
    public ModelRenderer R4;
    public ModelRenderer L1;
    public ModelRenderer L2;
    public ModelRenderer L3;
    public ModelRenderer L4;
    public ModelRenderer rarm;
    public ModelRenderer larm;
    public ModelRenderer rleg;
    public ModelRenderer lleg;
    public ModelRenderer R11;
    public ModelRenderer R12;
    public ModelRenderer R21;
    public ModelRenderer R22;
    public ModelRenderer R31;
    public ModelRenderer R32;
    public ModelRenderer R41;
    public ModelRenderer R42;
    public ModelRenderer L11;
    public ModelRenderer L12;
    public ModelRenderer L21;
    public ModelRenderer L22;
    public ModelRenderer L31;
    public ModelRenderer L32;
    public ModelRenderer L41;
    public ModelRenderer L42;

    public ModelCarbonite() {
        this.textureWidth = 128;
        this.textureHeight = 128;
        this.R3 = new ModelRenderer(this, 18, 85);
        this.R3.setRotationPoint(-1.0F, 19.0F, 1.0F);
        this.R3.addBox(0.0F, 0.0F, 0.0F, 1, 6, 6, 0.0F);
        this.L22 = new ModelRenderer(this, 40, 95);
        this.L22.mirror = true;
        this.L22.setRotationPoint(0.47F, 5.15F, 0.0F);
        this.L22.addBox(0.0F, 0.0F, 0.0F, 1, 2, 6, 0.0F);
        this.setRotateAngle(this.L22, 0.0F, 0.0F, 1.016654289286697F);
        this.R1 = new ModelRenderer(this, 18, 59);
        this.R1.setRotationPoint(-1.0F, 2.0F, 1.0F);
        this.R1.addBox(0.0F, 0.0F, 0.0F, 1, 6, 6, 0.0F);
        this.R2 = new ModelRenderer(this, 18, 72);
        this.R2.setRotationPoint(-1.0F, 11.0F, 1.0F);
        this.R2.addBox(0.0F, 0.0F, 0.0F, 1, 6, 6, 0.0F);
        this.R21 = new ModelRenderer(this, 40, 95);
        this.R21.setRotationPoint(1.8F, -0.9F, 0.0F);
        this.R21.addBox(0.0F, 0.0F, 0.0F, 1, 2, 6, 0.0F);
        this.setRotateAngle(this.R21, 0.0F, 0.0F, 1.104618883587211F);
        this.R41 = new ModelRenderer(this, 40, 77);
        this.R41.setRotationPoint(1.8F, -0.9F, 0.0F);
        this.R41.addBox(0.0F, 0.0F, 0.0F, 1, 2, 6, 0.0F);
        this.setRotateAngle(this.R41, 0.0F, 0.0F, 1.104618883587211F);
        this.R31 = new ModelRenderer(this, 55, 95);
        this.R31.setRotationPoint(1.8F, -0.9F, 0.0F);
        this.R31.addBox(0.0F, 0.0F, 0.0F, 1, 2, 6, 0.0F);
        this.setRotateAngle(this.R31, 0.0F, 0.0F, 1.104618883587211F);
        this.body = new ModelRenderer(this, 88, 80);
        this.body.setRotationPoint(6.0F, 10.0F, -0.5F);
        this.body.addBox(0.0F, 0.0F, 0.0F, 8, 12, 4, 0.0F);
        this.L1 = new ModelRenderer(this, 18, 59);
        this.L1.mirror = true;
        this.L1.setRotationPoint(20.0F, 2.0F, 1.0F);
        this.L1.addBox(0.0F, 0.0F, 0.0F, 1, 6, 6, 0.0F);
        this.R42 = new ModelRenderer(this, 40, 86);
        this.R42.setRotationPoint(0.0F, 5.0F, 0.0F);
        this.R42.addBox(0.0F, 0.0F, 0.0F, 1, 2, 6, 0.0F);
        this.setRotateAngle(this.R42, 0.0F, 0.0F, -1.104618883587211F);
        this.lleg = new ModelRenderer(this, 88, 112);
        this.lleg.setRotationPoint(4.0F, 11.9F, 0.01F);
        this.lleg.addBox(0.0F, 0.0F, 0.0F, 4, 12, 4, 0.0F);
        this.setRotateAngle(this.lleg, 0.0F, 0.0F, -0.05323254218582704F);
        this.R11 = new ModelRenderer(this, 40, 58);
        this.R11.setRotationPoint(1.8F, -0.9F, 0.0F);
        this.R11.addBox(0.0F, 0.0F, 0.0F, 1, 2, 6, 0.0F);
        this.setRotateAngle(this.R11, 0.0F, 0.0F, 1.104618883587211F);
        this.L41 = new ModelRenderer(this, 40, 77);
        this.L41.mirror = true;
        this.L41.setRotationPoint(-1.25F, -0.15F, 0.0F);
        this.L41.addBox(0.0F, 0.0F, 0.0F, 1, 2, 6, 0.0F);
        this.setRotateAngle(this.L41, 0.0F, 0.0F, -1.0407398329642188F);
        this.rleg = new ModelRenderer(this, 72, 80);
        this.rleg.setRotationPoint(0.0F, 11.7F, 0.01F);
        this.rleg.addBox(0.0F, 0.0F, 0.0F, 4, 12, 4, 0.0F);
        this.setRotateAngle(this.rleg, 0.0F, 0.0F, 0.05323254218582704F);
        this.L4 = new ModelRenderer(this, 18, 98);
        this.L4.mirror = true;
        this.L4.setRotationPoint(20.0F, 28.0F, 1.0F);
        this.L4.addBox(0.0F, 0.0F, 0.0F, 1, 5, 6, 0.0F);
        this.R32 = new ModelRenderer(this, 55, 104);
        this.R32.setRotationPoint(0.0F, 6.0F, 0.0F);
        this.R32.addBox(0.0F, 0.0F, 0.0F, 1, 2, 6, 0.0F);
        this.setRotateAngle(this.R32, 0.0F, 0.0F, -1.104618883587211F);
        this.rarm = new ModelRenderer(this, 112, 80);
        this.rarm.setRotationPoint(-3.9F, -0.3F, 0.01F);
        this.rarm.addBox(0.0F, 0.0F, 0.0F, 4, 12, 4, 0.0F);
        this.setRotateAngle(this.rarm, 0.0F, 0.0F, 0.06719517620178168F);
        this.larm = new ModelRenderer(this, 104, 112);
        this.larm.setRotationPoint(7.9F, -0.1F, 0.01F);
        this.larm.addBox(0.0F, 0.0F, 0.0F, 4, 12, 4, 0.0F);
        this.setRotateAngle(this.larm, 0.0F, 0.0F, -0.06719517620178168F);
        this.L31 = new ModelRenderer(this, 55, 95);
        this.L31.mirror = true;
        this.L31.setRotationPoint(-1.25F, -0.15F, 0.0F);
        this.L31.addBox(0.0F, 0.0F, 0.0F, 1, 2, 6, 0.0F);
        this.setRotateAngle(this.L31, 0.0F, 0.0F, -1.0407398329642188F);
        this.L12 = new ModelRenderer(this, 40, 68);
        this.L12.mirror = true;
        this.L12.setRotationPoint(0.47F, 5.15F, 0.0F);
        this.L12.addBox(0.0F, 0.0F, 0.0F, 1, 2, 6, 0.0F);
        this.setRotateAngle(this.L12, 0.0F, 0.0F, 1.016654289286697F);
        this.L42 = new ModelRenderer(this, 40, 86);
        this.L42.mirror = true;
        this.L42.setRotationPoint(0.47F, 4.15F, 0.0F);
        this.L42.addBox(0.0F, 0.0F, 0.0F, 1, 2, 6, 0.0F);
        this.setRotateAngle(this.L42, 0.0F, 0.0F, 1.016654289286697F);
        this.head = new ModelRenderer(this, 72, 64);
        this.head.setRotationPoint(6.0F, 2.0F, -2.5F);
        this.head.addBox(0.0F, 0.0F, 0.0F, 8, 8, 8, 0.0F);
        this.L2 = new ModelRenderer(this, 18, 72);
        this.L2.mirror = true;
        this.L2.setRotationPoint(20.0F, 11.0F, 1.0F);
        this.L2.addBox(0.0F, 0.0F, 0.0F, 1, 6, 6, 0.0F);
        this.R4 = new ModelRenderer(this, 18, 98);
        this.R4.setRotationPoint(-1.0F, 28.0F, 1.0F);
        this.R4.addBox(0.0F, 0.0F, 0.0F, 1, 5, 6, 0.0F);
        this.L3 = new ModelRenderer(this, 18, 85);
        this.L3.mirror = true;
        this.L3.setRotationPoint(20.0F, 19.0F, 1.0F);
        this.L3.addBox(0.0F, 0.0F, 0.0F, 1, 6, 6, 0.0F);
        this.R22 = new ModelRenderer(this, 40, 104);
        this.R22.setRotationPoint(0.0F, 6.0F, 0.0F);
        this.R22.addBox(0.0F, 0.0F, 0.0F, 1, 2, 6, 0.0F);
        this.setRotateAngle(this.R22, 0.0F, 0.0F, -1.104618883587211F);
        this.R12 = new ModelRenderer(this, 40, 68);
        this.R12.setRotationPoint(0.0F, 6.0F, 0.0F);
        this.R12.addBox(0.0F, 0.0F, 0.0F, 1, 2, 6, 0.0F);
        this.setRotateAngle(this.R12, 0.0F, 0.0F, -1.104618883587211F);
        this.L32 = new ModelRenderer(this, 55, 104);
        this.L32.setRotationPoint(0.47F, 5.15F, 0.0F);
        this.L32.addBox(0.0F, 0.0F, 0.0F, 1, 2, 6, 0.0F);
        this.setRotateAngle(this.L32, 0.0F, 0.0F, 1.016654289286697F);
        this.L11 = new ModelRenderer(this, 40, 58);
        this.L11.mirror = true;
        this.L11.setRotationPoint(-1.25F, -0.15F, 0.0F);
        this.L11.addBox(0.0F, 0.0F, 0.0F, 1, 2, 6, 0.0F);
        this.setRotateAngle(this.L11, 0.0F, 0.0F, -1.040765275811005F);
        this.main = new ModelRenderer(this, 5, 0);
        this.main.setRotationPoint(-10.0F, -12.0F, 0.0F);
        this.main.addBox(0.0F, 0.0F, 0.0F, 20, 36, 8, 0.0F);
        this.L21 = new ModelRenderer(this, 40, 95);
        this.L21.mirror = true;
        this.L21.setRotationPoint(-1.25F, -0.15F, 0.0F);
        this.L21.addBox(0.0F, 0.0F, 0.0F, 1, 2, 6, 0.0F);
        this.setRotateAngle(this.L21, 0.0F, 0.0F, -1.0407398329642188F);
        this.main.addChild(this.R3);
        this.L2.addChild(this.L22);
        this.main.addChild(this.R1);
        this.main.addChild(this.R2);
        this.R2.addChild(this.R21);
        this.R4.addChild(this.R41);
        this.R3.addChild(this.R31);
        this.main.addChild(this.body);
        this.main.addChild(this.L1);
        this.R4.addChild(this.R42);
        this.body.addChild(this.lleg);
        this.R1.addChild(this.R11);
        this.L4.addChild(this.L41);
        this.body.addChild(this.rleg);
        this.main.addChild(this.L4);
        this.R3.addChild(this.R32);
        this.body.addChild(this.rarm);
        this.body.addChild(this.larm);
        this.L3.addChild(this.L31);
        this.L1.addChild(this.L12);
        this.L4.addChild(this.L42);
        this.main.addChild(this.head);
        this.main.addChild(this.L2);
        this.main.addChild(this.R4);
        this.main.addChild(this.L3);
        this.R2.addChild(this.R22);
        this.R1.addChild(this.R12);
        this.L3.addChild(this.L32);
        this.L1.addChild(this.L11);
        this.L2.addChild(this.L21);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        this.main.render(f5);
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

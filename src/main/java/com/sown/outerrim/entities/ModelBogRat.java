package com.sown.outerrim.entities;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.util.MathHelper;

import org.lwjgl.opengl.GL11;

/**
 * ModelWolf - Either Mojang or a mod author
 * Created using Tabula 4.1.1
 */
public class ModelBogRat extends ModelBase {
    public ModelRenderer leg4;
    public ModelRenderer scruff;
    public ModelRenderer body;
    public ModelRenderer leg2;
    public ModelRenderer leg1;
    public ModelRenderer tail;
    public ModelRenderer head;
    public ModelRenderer leg3;

    public ModelBogRat() {
        this.textureWidth = 64;
        this.textureHeight = 32;
        this.leg4 = new ModelRenderer(this, 0, 18);
        this.leg4.setRotationPoint(0.5F, 16.0F, -4.0F);
        this.leg4.addBox(-1.0F, 0.0F, -1.0F, 2, 8, 2, 0.0F);
        this.tail = new ModelRenderer(this, 9, 18);
        this.tail.setRotationPoint(-1.0F, 12.0F, 8.0F);
        this.tail.addBox(-1.0F, 0.0F, -1.0F, 2, 8, 2, 0.0F);
        this.setRotateAngle(tail, 0.6981317007977318F, 0.0F, 0.0F);
        this.head = new ModelRenderer(this, 0, 0);
        this.head.setRotationPoint(-1.0F, 13.5F, -8.0F);
        this.head.addBox(-3.0F, -3.0F, -2.0F, 6, 6, 4, 0.0F);
        this.leg1 = new ModelRenderer(this, 0, 18);
        this.leg1.setRotationPoint(-2.5F, 16.0F, 7.0F);
        this.leg1.addBox(-1.0F, 0.0F, -1.0F, 2, 8, 2, 0.0F);
        this.leg2 = new ModelRenderer(this, 0, 18);
        this.leg2.setRotationPoint(0.5F, 16.0F, 7.0F);
        this.leg2.addBox(-1.0F, 0.0F, -1.0F, 2, 8, 2, 0.0F);
        this.leg3 = new ModelRenderer(this, 0, 18);
        this.leg3.setRotationPoint(-2.5F, 16.0F, -4.0F);
        this.leg3.addBox(-1.0F, 0.0F, -1.0F, 2, 8, 2, 0.0F);
        this.body = new ModelRenderer(this, 18, 14);
        this.body.setRotationPoint(0.0F, 14.0F, 2.0F);
        this.body.addBox(-4.0F, -2.0F, -3.4F, 6, 9, 6, 0.0F);
        this.setRotateAngle(body, 1.5707963267948966F, 0.0F, 0.0F);
        this.scruff = new ModelRenderer(this, 21, 0);
        this.scruff.setRotationPoint(-1.0F, 14.8F, -3.0F);
        this.scruff.addBox(-4.0F, -3.0F, -3.0F, 8, 6, 7, 0.0F);
        this.setRotateAngle(scruff, 1.5707963267948966F, 0.0F, 0.0F);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { 
        setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        
        // Check if the entity is a child and apply scaling
        if (((MobBogRat) entity).isChild()) {
            GL11.glPushMatrix();
            GL11.glScalef(0.5F, 0.5F, 0.5F);  // 50% size for babies
            GL11.glTranslatef(0.0F, 24.0F * f5, 0.0F);  // Adjusting the position to keep it on the ground
        }

        this.head.render(f5);
        this.leg2.render(f5);
        this.body.render(f5);
        this.leg1.render(f5);
        this.tail.render(f5);
        this.scruff.render(f5);
        this.leg3.render(f5);
        this.leg4.render(f5);

        if (((MobBogRat) entity).isChild()) {
            GL11.glPopMatrix();
        }
    }

    /**
     * This is a helper function from Tabula to set the rotation of model parts
     */
    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }

    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
        super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        float baseRotationAngle = +1.05F;
        this.head.rotateAngleX = (f4 / 57.295776f);
        this.head.rotateAngleY = (baseRotationAngle / 3) + (f3 / 57.295776f);
        this.leg1.rotateAngleX = MathHelper.sin((float)((float)((double)(f * 0.6662f) + 3.141592653589793))) * f1;
        this.leg2.rotateAngleX = MathHelper.sin((float)(f * 0.6662f)) * f1;
        this.leg3.rotateAngleX = MathHelper.sin((float)(f * 0.6662f)) * f1;
        this.leg4.rotateAngleX = MathHelper.sin((float)((float)((double)(f * 0.6662f) + 3.141592653589793))) * f1;

        // Adding motion to the tail, making it move up and down
        float walkingFactor = f1 > 0.1F ? 1.4F : 0.7F;  // Adjust the tail's movement based on whether the entity is moving or standing still
        this.tail.rotateAngleX = 0.6981317007977318F + (MathHelper.sin(f * 0.6662F * walkingFactor) * 1.05F * f1);
        this.tail.rotateAngleY = MathHelper.sin(f * 0.6662F * walkingFactor) * 0.4F * f1;  // Side-to-side motion
    }


}

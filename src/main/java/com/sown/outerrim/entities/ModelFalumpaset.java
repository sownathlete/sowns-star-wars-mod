package com.sown.outerrim.entities;

import java.util.Random;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MathHelper;

public class ModelFalumpaset extends ModelBase {
    public ModelRenderer body;
    public ModelRenderer leg_back_right;
    public ModelRenderer leg_back_left;
    public ModelRenderer leg_front_right;
    public ModelRenderer leg_front_left;
    public ModelRenderer tail_1;
    public ModelRenderer neck_1;
    public ModelRenderer tail_2;
    public ModelRenderer neck_2_normal;
    public ModelRenderer neck_2_alt;
    public ModelRenderer neck_4;
    public ModelRenderer neck_3;

    private final float bodyBaseY = -23.0F;
    private final float legBackY  = 7.0F;
    private final float legFrontY = 7.0F;

    public ModelFalumpaset() {
        textureWidth  = 128;
        textureHeight = 128;

        // neck2 normal & alt
        neck_2_normal = new ModelRenderer(this, 0, 0);
        neck_2_normal.setRotationPoint(0, 3, -18);
        neck_2_normal.addBox(-4, -5, -18, 8, 10, 18);

        neck_2_alt = new ModelRenderer(this, 0, 90);
        neck_2_alt.setRotationPoint(0, 3, -18);
        neck_2_alt.addBox(-4, -5, -18, 8, 10, 18);

        // ears
        neck_3 = new ModelRenderer(this, 0, 0);
        neck_3.setRotationPoint(-5, -7, -7);
        neck_3.addBox(0, 0, 0, 2, 4, 3);

        neck_4 = new ModelRenderer(this, 0, 0);
        neck_4.mirror = true;
        neck_4.setRotationPoint(3, -7, -7);
        neck_4.addBox(0, 0, 0, 2, 4, 3);

        neck_2_normal.addChild(neck_3);
        neck_2_normal.addChild(neck_4);
        neck_2_alt   .addChild(neck_3);
        neck_2_alt   .addChild(neck_4);

        // neck1
        neck_1 = new ModelRenderer(this, 0, 56);
        neck_1.setRotationPoint(9, 13, 0);
        neck_1.addBox(-5, -8, -18, 10, 16, 18);
        neck_1.addChild(neck_2_normal);
        neck_1.addChild(neck_2_alt);

        // tail
        tail_2 = new ModelRenderer(this, 0, 7);
        tail_2.setRotationPoint(0, 24, 0);
        tail_2.addBox(-1.5F, 0, -1.5F, 3, 6, 3);

        tail_1 = new ModelRenderer(this, 0, 28);
        tail_1.setRotationPoint(9, 3, 35);
        tail_1.addBox(-1, 0, -1, 2, 24, 2);
        tail_1.addChild(tail_2);

        // body
        body = new ModelRenderer(this, 18, 0);
        body.setRotationPoint(-9, bodyBaseY, -17);
        body.addBox(0, 0, 0, 18, 22, 34);
        body.addChild(neck_1);
        body.addChild(tail_1);

        // legs
        leg_back_left = new ModelRenderer(this, 56, 56);
        leg_back_left.mirror = true;
        leg_back_left.setRotationPoint(13, legBackY, 27);
        leg_back_left.addBox(0, 0, 0, 7, 40, 9);

        leg_back_right = new ModelRenderer(this, 56, 56);
        leg_back_right.setRotationPoint(-2, legBackY, 27);
        leg_back_right.addBox(0, 0, 0, 7, 40, 9);

        leg_front_right = new ModelRenderer(this, 88, 56);
        leg_front_right.setRotationPoint(-2, legFrontY, 4);
        leg_front_right.addBox(0, 0, 0, 6, 40, 7);

        leg_front_left = new ModelRenderer(this, 88, 56);
        leg_front_left.mirror = true;
        leg_front_left.setRotationPoint(14, legFrontY, 4);
        leg_front_left.addBox(0, 0, 0, 6, 40, 7);

        body.addChild(leg_back_left);
        body.addChild(leg_back_right);
        body.addChild(leg_front_right);
        body.addChild(leg_front_left);

        // start visible
        neck_2_alt.isHidden = true;
    }

    @Override
    public void setRotationAngles(float limbSwing, float limbSwingAmount,
                                  float ageInTicks, float netHeadYaw,
                                  float headPitch, float scaleFactor,
                                  Entity entity) {
        int tick = (int) ageInTicks;

        // head swivel
        float yawRad   = MathHelper.clamp_float(netHeadYaw  * ((float)Math.PI/180F), -0.785F, 0.785F);
        float pitchRad = MathHelper.clamp_float(headPitch  * ((float)Math.PI/180F), -0.785F, 0.785F);
        neck_1.rotateAngleY        = yawRad * 0.5F;
        neck_2_normal.rotateAngleY = yawRad * 0.5F;
        neck_2_alt   .rotateAngleY = yawRad * 0.5F;

        // split head pitch half/half
        neck_1.rotateAngleX        = pitchRad * 0.5F;
        neck_2_normal.rotateAngleX = pitchRad * 0.5F;
        neck_2_alt   .rotateAngleX = pitchRad * 0.5F;

        // **idle neck bob on top** 
        neck_1.rotateAngleX += MathHelper.cos(ageInTicks * 0.05F) * 0.05F;

        // walk: both rights & both lefts
        float ws = 0.6662F, wd = 1.4F;
        float ra = MathHelper.cos(limbSwing * ws)                 * wd * limbSwingAmount;
        float la = MathHelper.cos(limbSwing * ws + (float)Math.PI) * wd * limbSwingAmount;
        leg_front_right.rotateAngleX = ra;
        leg_back_right .rotateAngleX = ra;
        leg_front_left .rotateAngleX = la;
        leg_back_left  .rotateAngleX = la;

        // tail sway
        tail_1.rotateAngleY = MathHelper.cos(ageInTicks * 0.1F) * 0.1F;
        tail_1.rotateAngleX = MathHelper.cos(ageInTicks * 0.1F + (float)Math.PI/2) * 0.05F;

        // body breathing bob
        float breathe = MathHelper.sin(ageInTicks * 0.1F) * 0.5F;
        body.rotationPointY            = bodyBaseY + breathe;
        leg_front_right.rotationPointY = legFrontY - breathe;
        leg_front_left .rotationPointY = legFrontY - breathe;
        leg_back_right .rotationPointY = legBackY  - breathe;
        leg_back_left  .rotationPointY = legBackY  - breathe;

        // blinking, de-synced per entity
        int id       = entity.getEntityId();
        int period   = 150;                    // full cycle
        int duration = 8 + (id % 3);           // 810 ticks
        int phase    = (int)((id * 37L) % period);

        boolean timedBlink = ((tick + phase) % period) < duration;
        boolean hurtBlink  = entity instanceof EntityLivingBase
                          && ((EntityLivingBase)entity).hurtTime > 0;
        boolean blinking   = timedBlink || hurtBlink;

        neck_2_normal.isHidden = blinking;
        neck_2_alt   .isHidden = !blinking;
    }

    @Override
    public void render(Entity entity, float limbSwing, float limbSwingAmount,
                       float ageInTicks, float netHeadYaw, float headPitch,
                       float scale) {
        setRotationAngles(limbSwing, limbSwingAmount,
                          ageInTicks, netHeadYaw, headPitch,
                          scale, entity);
        body.render(scale);
    }

    public void setRotateAngle(ModelRenderer m, float x, float y, float z) {
        m.rotateAngleX = x;
        m.rotateAngleY = y;
        m.rotateAngleZ = z;
    }
}

package com.sown.outerrim.entities;

import java.util.Random;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MathHelper;

public class ModelKaadu extends ModelBase {
    public ModelRenderer center, body, leftLeg, rightLeg, neck, tail, head, headOverlay;

    private final Random rand = new Random();
    private int blinkStartTick, blinkDuration;

    // static tilt values
    private static final float STATIC_BODY_PITCH = 0.18203784F;
    private static final float STATIC_TAIL_PITCH = -0.22759093F;

    // body bob base
    private final float bodyBaseY = 0F;

    public ModelKaadu() {
        this.textureWidth  = 96;
        this.textureHeight = 96;

        // schedule first blink
        scheduleNextBlink(0);

        // build parts...
        center = new ModelRenderer(this, 0, 0);
        center.setRotationPoint(0F, -13F, 0F);

        body = new ModelRenderer(this, 0, 25);
        body.setRotationPoint(0F, bodyBaseY, 0F);
        body.addBox(-6F, -7F, -12F, 12, 14, 24, 0F);
        setRotateAngle(body, STATIC_BODY_PITCH, 0F, 0F);

        neck = new ModelRenderer(this, 56, 0);
        neck.setRotationPoint(0F, -18F, -10F);
        neck.addBox(-3F, -2F, -6F, 6, 22, 6, 0F);

        head = new ModelRenderer(this, 0, 0);
        head.setRotationPoint(0F, -7F, -3F);
        head.addBox(-4F, 0F, -17F, 8, 5, 20, 0F);

        headOverlay = new ModelRenderer(this, 0, 63);
        headOverlay.setRotationPoint(0F, 0F, 0F);
        headOverlay.addBox(-4F, 0F, -17F, 8, 5, 20, 0F);
        headOverlay.isHidden = true;

        tail = new ModelRenderer(this, 48, 28);
        tail.setRotationPoint(0F, -4F, 11F);
        tail.addBox(-3F, -3F, 0F, 6, 7, 14, 0F);
        setRotateAngle(tail, STATIC_TAIL_PITCH, 0F, 0F);

        rightLeg = new ModelRenderer(this, 72, 49);
        rightLeg.setRotationPoint(0F, 0F, 0F);
        rightLeg.addBox(-7F, 0F, -3F, 4, 37, 6, 0F);

        leftLeg = new ModelRenderer(this, 72, 49);
        leftLeg.mirror = true;
        leftLeg.setRotationPoint(0F, 0F, 0F);
        leftLeg.addBox(3F, 0F, -3F, 4, 37, 6, 0F);

        // assemble
        center.addChild(body);
        body.addChild(neck);
        neck.addChild(head);
        head.addChild(headOverlay);
        body.addChild(tail);
        center.addChild(rightLeg);
        center.addChild(leftLeg);
    }

    /** Schedule the next timed blink */
    private void scheduleNextBlink(int currentTick) {
        // choose next in 100200 ticks (510 s)
        blinkStartTick = currentTick + 100 + rand.nextInt(101);
        // blink lasts 810 ticks (0.40.5 s)
        blinkDuration  = 8 + rand.nextInt(3);
    }

    @Override
    public void setRotationAngles(float limbSwing, float limbSwingAmount,
                                  float ageInTicks, float netHeadYaw,
                                  float headPitch, float scaleFactor,
                                  Entity entity) {
        int tick = (int) ageInTicks;

        // head look
        float yaw   = MathHelper.clamp_float(netHeadYaw  * (float)Math.PI/180F, -0.785F, 0.785F);
        float pitch = MathHelper.clamp_float(headPitch  * (float)Math.PI/180F, -0.785F, 0.785F);
        head.rotateAngleY = yaw;
        head.rotateAngleX = pitch;

        // legs (both right same, both left same)
        float speed  = 0.6662F, degree = 1.4F;
        rightLeg.rotateAngleX = MathHelper.cos(limbSwing * speed)        * degree * limbSwingAmount;
        leftLeg .rotateAngleX = MathHelper.cos(limbSwing * speed + (float)Math.PI) * degree * limbSwingAmount;

        // tail sway
        tail.rotateAngleY = MathHelper.cos(ageInTicks * 0.1F) * 0.15F;

        // neck bob
        neck.rotateAngleX = MathHelper.cos(ageInTicks * 0.05F) * 0.05F;

        // body vertical bob only
        float bob = MathHelper.sin(ageInTicks * 0.1F) * 0.1F;
        body.rotationPointY = bodyBaseY - bob;

        // timed blink?
        if (tick >= blinkStartTick + blinkDuration) {
            scheduleNextBlink(tick);
        }
        boolean timedBlink = tick >= blinkStartTick
                          && tick <  blinkStartTick + blinkDuration;
        boolean hurtBlink  = entity instanceof EntityLivingBase
                          && ((EntityLivingBase)entity).hurtTime > 0;
        boolean blinking   = timedBlink || hurtBlink;
        headOverlay.isHidden = !blinking;
    }

    @Override
    public void render(Entity entity, float swing, float swingAmt,
                       float ticks, float yaw, float pitch, float scale) {
        setRotationAngles(swing, swingAmt, ticks, yaw, pitch, scale, entity);
        center.render(scale);
    }

    // helper
    public void setRotateAngle(ModelRenderer m, float x, float y, float z) {
        m.rotateAngleX = x;
        m.rotateAngleY = y;
        m.rotateAngleZ = z;
    }
}

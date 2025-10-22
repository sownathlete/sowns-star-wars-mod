package com.sown.outerrim.entities;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;

/**
 * ModelKesselMineWorker:
 *  • Extends ModelBiped so we inherit all of the vanilla arm/leg/head animation.
 *  • Replaces each bipedXxx box with exactly the same dimensions & UV offsets your texture expects.
 *  • Attaches overlays (helmet, jacket, sleeves, pant legs, ears) as children of those bipedXxx parts,
 *    so they move in perfect sync automatically.
 */
public class ModelKesselMineWorker extends ModelBiped {
    // Overlays
    private final ModelRenderer leftEar;
    private final ModelRenderer rightEar;
    private final ModelRenderer jacket;
    private final ModelRenderer leftSleeve;
    private final ModelRenderer rightSleeve;
    private final ModelRenderer leftPantLeg;
    private final ModelRenderer rightPantLeg;

    public ModelKesselMineWorker() {
        super(); // Call ModelBiped's constructor

        // Tell ModelBiped how big our texture atlas is:
        this.textureWidth = 64;
        this.textureHeight = 64;

        //
        // STEP 1: Replace bipedHead with a new 8×8×8 box (UV at 0,0)
        //
        this.bipedHead = new ModelRenderer(this, 0, 0);
        this.bipedHead.setRotationPoint(0.0F, 0.0F, 0.0F);
        // Head box: 8×8×8 at (-4, -8, -4)
        this.bipedHead.addBox(-4.0F, -8.0F, -4.0F,
                              8, 8, 8,
                              0.0F);

        //
        // STEP 2: Replace bipedHeadwear (the “hat layer” / helmet) with 8×8×8 at UV (32,0), inflate=0.5F
        //           We will attach it as a child of bipedHead in the code below.
        //
        this.bipedHeadwear = new ModelRenderer(this, 32, 0);
        this.bipedHeadwear.addBox(-4.0F, -8.0F, -4.0F,
                      8, 8, 8,
                      0.5F);
        // We do NOT call 'setRotationPoint' on 'helmet' here; instead, we attach it to bipedHead below.

        //
        // STEP 3: Replace bipedBody with 8×12×4 box (UV at 16,16)
        //
        this.bipedBody = new ModelRenderer(this, 16, 16);
        this.bipedBody.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.bipedBody.addBox(-4.0F, 0.0F, -2.0F,
                              8, 12, 4,
                              0.0F);

        //
        // STEP 4: Replace bipedRightArm with 4×12×4 box (UV at 40,16)
        //
        this.bipedRightArm = new ModelRenderer(this, 32, 48);
        this.bipedRightArm.setRotationPoint(-5.0F, 2.0F, 0.0F);
        this.bipedRightArm.addBox(-3.0F, -2.0F, -2.0F,
                                  4, 12, 4,
                                  0.0F);

        //
        // STEP 5: Replace bipedLeftArm with 4×12×4 box (UV at 32,48), mirror=true
        //
        this.bipedLeftArm = new ModelRenderer(this, 40, 16);
        this.bipedLeftArm.mirror = true;
        this.bipedLeftArm.setRotationPoint(5.0F, 2.0F, 0.0F);
        this.bipedLeftArm.addBox(-1.0F, -2.0F, -2.0F,
                                 4, 12, 4,
                                 0.0F);

        //
        // STEP 6: Replace bipedRightLeg with 4×12×4 box (UV at 0,16)
        //
        this.bipedRightLeg = new ModelRenderer(this, 0, 16);
        this.bipedRightLeg.setRotationPoint(-1.9F, 12.0F, 0.0F);
        this.bipedRightLeg.addBox(-2.0F, 0.0F, -2.0F,
                                  4, 12, 4,
                                  0.0F);

        //
        // STEP 7: Replace bipedLeftLeg with 4×12×4 box (UV at 0,16), mirror=true
        //
        this.bipedLeftLeg = new ModelRenderer(this, 0, 16);
        this.bipedLeftLeg.mirror = true;
        this.bipedLeftLeg.setRotationPoint(1.9F, 12.0F, 0.0F);
        this.bipedLeftLeg.addBox(-2.0F, 0.0F, -2.0F,
                                 4, 12, 4,
                                 0.0F);

        //
        // STEP 8: Create all “overlay” pieces and attach them as children of the matching bipedXxx
        //

        // (A) HEAD OVERLAYS: helmet + ears

        // Left ear (UV 24,2), size 1×3×3, attached to head at x=+4, y=0, z=0:
        leftEar = new ModelRenderer(this, 24, 2);
        leftEar.addBox(0.0F, -6.0F, -1.0F,
                       1, 3, 3,
                       0.0F);
        leftEar.setRotationPoint(4.0F, 0.0F, 0.0F);
        this.bipedHead.addChild(leftEar);

        // Right ear (UV 32,2), size 1×3×3, attached to head at x=-4, y=0, z=0:
        rightEar = new ModelRenderer(this, 32, 2);
        rightEar.addBox(-1.0F, -6.0F, -1.0F,
                        1, 3, 3,
                        0.0F);
        rightEar.setRotationPoint(-4.0F, 0.0F, 0.0F);
        this.bipedHead.addChild(rightEar);

        // (B) BODY OVERLAY: jacket (UV 16,32), size 8×12×4, inflate=0.25
        jacket = new ModelRenderer(this, 16, 32);
        jacket.addBox(-4.0F, 0.0F, -2.0F,
                      8, 12, 4,
                      0.25F);
        this.bipedBody.addChild(jacket);

        // (C) ARM OVERLAYS: sleeves
        // Right sleeve (UV 40,32), size 4×12×4, inflate=0.25, attached to right arm
        rightSleeve = new ModelRenderer(this, 40, 32);
        rightSleeve.addBox(-3.0F, -2.0F, -2.0F,
                            4, 12, 4,
                            0.25F);
        this.bipedRightArm.addChild(rightSleeve);

        // Left sleeve (UV 48,48), size 4×12×4, inflate=0.25, mirror=true, attached to left arm
        leftSleeve = new ModelRenderer(this, 48, 48);
        leftSleeve.mirror = true;
        leftSleeve.addBox(-1.0F, -2.0F, -2.0F,
                          4, 12, 4,
                          0.25F);
        this.bipedLeftArm.addChild(leftSleeve);

        // (D) LEG OVERLAYS: pant legs
        // Right pant leg (UV 0,32), size 4×12×4, inflate=0.25, attached to right leg
        rightPantLeg = new ModelRenderer(this, 0, 32);
        rightPantLeg.addBox(-2.0F, 0.0F, -2.0F,
                             4, 12, 4,
                             0.25F);
        this.bipedRightLeg.addChild(rightPantLeg);

        // Left pant leg (UV 0,48), size 4×12×4, inflate=0.25, mirror=true, attached to left leg
        leftPantLeg = new ModelRenderer(this, 0, 48);
        leftPantLeg.mirror = true;
        leftPantLeg.addBox(-2.0F, 0.0F, -2.0F,
                            4, 12, 4,
                            0.25F);
        this.bipedLeftLeg.addChild(leftPantLeg);
    }

    protected void adjustEP(EntityPlayer entity, ItemStack stack) {
        this.heldItemRight = (stack != null) ? 1 : 0;
        this.isSneak       = entity.isSneaking();

        if (stack != null && entity.getItemInUseCount() > 0) {
            EnumAction enumaction = stack.getItemUseAction();
            if (enumaction == EnumAction.bow) {
                this.aimedBow = true;
            } else if (enumaction == EnumAction.block) {
                this.bipedRightArm.rotateAngleX = -0.63F; 
                this.bipedRightArm.rotateAngleY = 0.0F;
                this.bipedRightArm.rotateAngleZ = 0.0F;
            } else {
                this.aimedBow = false;
            }
        } else {
            this.aimedBow = false;
        }
    }

    protected void adjustEL(EntityLivingBase entity, ItemStack stack) {
        this.heldItemRight = (stack != null) ? 1 : 0;
        this.isSneak       = entity.isSneaking();
        this.aimedBow      = false; // NPCs never draw a bow automatically
    }

    @Override
    public void render(Entity entity, float limbSwing, float limbSwingAmount,
                       float ageInTicks, float netHeadYaw, float headPitch, float scale) {
        // If the entity is a player, call adjustEP so that “aimedBow” reflects
        // getItemInUseCount() > 0. If it’s any other EntityLivingBase, call adjustEL.
        if (entity instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer)entity;
            this.adjustEP(player, player.getHeldItem());
        } else if (entity instanceof EntityLivingBase) {
            EntityLivingBase el = (EntityLivingBase)entity;
            this.adjustEL(el, el.getHeldItem());
        }

        // Now call super.render(), which will read `this.aimedBow` and rotate accordingly.
        super.render(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
    }

    @Override
    public void setRotationAngles(
            float limbSwing,
            float limbSwingAmount,
            float ageInTicks,
            float netHeadYaw,
            float headPitch,
            float scaleFactor,
            Entity entityIn
    ) {
        // Let ModelBiped handle all vanilla rotations (walking, head look, sneaking, bow aim, etc.)
        super.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor, entityIn);

        // Our overlay children (ears, jacket, sleeves, pant legs) are already attached, so they automatically follow.
        //
        // (Optional: add a little sway on the jacket, etc., if desired.)
        // float sway = MathHelper.cos(ageInTicks * 0.05F) * 0.02F;
        // jacket.rotateAngleY = sway;
    }
}
package com.sown.outerrim.entities;

import cpw.mods.fml.common.Loader;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;

import java.lang.reflect.Method;

public class ModelCountDooku extends ModelBiped {
    public ModelRenderer jacket;
    public ModelRenderer leftSleeve;
    public ModelRenderer rightSleeve;
    public ModelRenderer leftPantLeg;
    public ModelRenderer rightPantLeg;
    public ModelRenderer beard;

    /** Alias so code that references either works */
    public ModelRenderer cape; // points to bipedCloak

    // --- Legends reflection (no hard dependency) ---
    private static boolean legendsChecked = false, legendsPresent = false;
    private static Class<?> CLS_Lightsaber;
    private static Method M_isActive; // static ItemLightsaberBase.isActive(ItemStack)

    private static void ensureLegends() {
        if (legendsChecked) return;
        legendsChecked = true;
        try {
            if (!Loader.isModLoaded("legends")) return;
            CLS_Lightsaber = Class.forName("com.tihyo.starwars.items.weapons.ItemLightsaberBase");
            M_isActive = CLS_Lightsaber.getMethod("isActive", ItemStack.class);
            legendsPresent = true;
            System.out.println("[OuterRim] ModelCountDooku: Legends hooks READY.");
        } catch (Throwable t) {
            legendsPresent = false;
            System.out.println("[OuterRim] ModelCountDooku: Legends hooks unavailable.");
        }
    }

    public ModelCountDooku() {
        this.textureWidth = 128;
        this.textureHeight = 128;

        this.bipedHead = new ModelRenderer(this, 0, 1);
        this.bipedHead.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.bipedHead.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, 0.0F);

        this.bipedBody = new ModelRenderer(this, 0, 17);
        this.bipedBody.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.bipedBody.addBox(-4.0F, 0.0F, -2.0F, 8, 12, 4, 0.0F);

        this.bipedRightArm = new ModelRenderer(this, 48, 17);
        this.bipedRightArm.setRotationPoint(-5.0F, 2.0F, 0.0F);
        this.bipedRightArm.addBox(-3.0F, -2.0F, -2.0F, 4, 12, 4, 0.0F);

        this.bipedLeftArm = new ModelRenderer(this, 48, 17);
        this.bipedLeftArm.mirror = true;
        this.bipedLeftArm.setRotationPoint(5.0F, 2.0F, 0.0F);
        this.bipedLeftArm.addBox(-1.0F, -2.0F, -2.0F, 4, 12, 4, 0.0F);

        this.bipedRightLeg = new ModelRenderer(this, 0, 33);
        this.bipedRightLeg.setRotationPoint(-1.9F, 12.0F, 0.0F);
        this.bipedRightLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.0F);

        this.bipedLeftLeg = new ModelRenderer(this, 16, 33);
        this.bipedLeftLeg.setRotationPoint(1.9F, 12.0F, 0.0F);
        this.bipedLeftLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.0F);

        // overlays
        this.rightSleeve = new ModelRenderer(this, 16, 49);
        this.rightSleeve.mirror = true;
        this.rightSleeve.addBox(-3.0F, -2.0F, -2.0F, 4, 12, 4, 0.25F);
        this.bipedRightArm.addChild(rightSleeve);

        this.leftSleeve = new ModelRenderer(this, 16, 49);
        this.leftSleeve.addBox(-1.0F, -2.0F, -2.0F, 4, 12, 4, 0.25F);
        this.bipedLeftArm.addChild(leftSleeve);

        this.leftPantLeg = new ModelRenderer(this, 0, 49);
        this.leftPantLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.25F);
        this.bipedLeftLeg.addChild(leftPantLeg);

        this.rightPantLeg = new ModelRenderer(this, 0, 49);
        this.rightPantLeg.mirror = true;
        this.rightPantLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.25F);
        this.bipedRightLeg.addChild(rightPantLeg);

        this.jacket = new ModelRenderer(this, 24, 17);
        this.jacket.addBox(-4.0F, 0.0F, -2.0F, 8, 12, 4, 0.25F);
        this.bipedBody.addChild(jacket);

        this.beard = new ModelRenderer(this, 93, 21);
        this.beard.setRotationPoint(-4.0F, 0.0F, -4.0F);
        this.beard.addBox(0.0F, 0.0F, 0.0F, 8, 1, 2, 0.0F);
        this.bipedHead.addChild(this.beard);

        // cloak (original behavior)
        this.bipedCloak = new ModelRenderer(this, 42, 33);
        this.bipedCloak.setRotationPoint(-7.0F, 0.0F, 2.0F);
        this.bipedCloak.addBox(0.0F, 0.0F, 0.0F, 14, 22, 1, 0.0F);
        setRotate(this.bipedCloak, 0.1745329F, 0.0F, 0.0F);
        this.bipedBody.addChild(this.bipedCloak);

        // alias so code using `cape` still works
        this.cape = this.bipedCloak;

        this.bipedHeadwear.showModel = false;
    }

    private static void setRotate(ModelRenderer part, float x, float y, float z) {
        part.rotateAngleX = x; part.rotateAngleY = y; part.rotateAngleZ = z;
    }

    // --- Original player/NPC flag setup (so blocking/bow/sneak look right) ---
    private void adjustEP(EntityPlayer entity, ItemStack stack) {
        this.heldItemRight = (stack != null) ? 1 : 0;
        this.isSneak = entity.isSneaking();

        if (stack != null && entity.getItemInUseCount() > 0) {
            EnumAction act = stack.getItemUseAction();
            if (act == EnumAction.bow) {
                this.aimedBow = true;
            } else if (act == EnumAction.block) {
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

    private void adjustEL(EntityLivingBase entity, ItemStack stack) {
        this.heldItemRight = (stack != null) ? 1 : 0;
        this.isSneak = entity.isSneaking();
        this.aimedBow = false;
    }

    // --- Original cloak animation (forward/side sway + idle bob) ---
    private void animateCloak(EntityLivingBase entity, float ageInTicks) {
        double dx = entity.posX - entity.prevPosX;
        double dy = entity.posY - entity.prevPosY;
        double dz = entity.posZ - entity.prevPosZ;

        float yaw = entity.prevRenderYawOffset + (entity.renderYawOffset - entity.prevRenderYawOffset);
        float sinY = MathHelper.sin(yaw * (float) Math.PI / 180F);
        float cosY = -MathHelper.cos(yaw * (float) Math.PI / 180F);

        float forward = (float) (dx * sinY + dz * cosY) * 10F;
        float side    = (float) (dx * cosY - dz * sinY) * 10F;

        forward = MathHelper.clamp_float(forward, 0F, 40F);
        side    = MathHelper.clamp_float(side, -5F, 5F);

        float bob = MathHelper.sin(ageInTicks * 0.02F) * 2F;

        this.bipedCloak.rotateAngleX = (6F + forward / 2F + (float) dy * 10F + bob) * ((float) Math.PI / 180F);
        this.bipedCloak.rotateAngleZ = side * ((float) Math.PI / 180F);
        this.bipedCloak.rotateAngleY = 0F;
    }

    @Override
    public void render(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks,
                       float netHeadYaw, float headPitch, float scale) {
        if (entity instanceof EntityPlayer) {
            adjustEP((EntityPlayer) entity, ((EntityPlayer) entity).getHeldItem());
        } else if (entity instanceof EntityLivingBase) {
            adjustEL((EntityLivingBase) entity, ((EntityLivingBase) entity).getHeldItem());
        }

        if (entity instanceof EntityLivingBase) {
            animateCloak((EntityLivingBase) entity, ageInTicks); // ensure cloak updates pre-render
        }

        super.render(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
    }

    @Override
    public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks,
                                  float netHeadYaw, float headPitch, float scaleFactor,
                                  Entity entityIn) {
        // IMPORTANT: include entityIn in the super call
        super.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks,
                                netHeadYaw, headPitch, scaleFactor, entityIn);

        if (!(entityIn instanceof EntityLivingBase)) return;
        EntityLivingBase e = (EntityLivingBase) entityIn;

        // --- ModelSith-style swing math (body/arms coupling) ---
        float swing = e.getSwingProgress(ageInTicks);
        if (swing > 0.0F) {
            float f6 = swing;
            this.bipedBody.rotateAngleY =
                MathHelper.sin(MathHelper.sqrt_float(f6) * (float)Math.PI * 2.0F) * 0.2F;

            this.bipedRightArm.rotationPointZ = MathHelper.sin(this.bipedBody.rotateAngleY) * 5.0F;
            this.bipedRightArm.rotationPointX = -MathHelper.cos(this.bipedBody.rotateAngleY) * 5.0F;
            this.bipedLeftArm.rotationPointZ  = -MathHelper.sin(this.bipedBody.rotateAngleY) * 5.0F;
            this.bipedLeftArm.rotationPointX  =  MathHelper.cos(this.bipedBody.rotateAngleY) * 5.0F;

            this.bipedRightArm.rotateAngleY += this.bipedBody.rotateAngleY;
            this.bipedLeftArm .rotateAngleY += this.bipedBody.rotateAngleY;

            f6 *= f6; f6 *= f6; f6 = 1.0F - f6;
            float f7 = MathHelper.sin(f6 * (float)Math.PI);
            float f8 = MathHelper.sin(swing * (float)Math.PI) * -(this.bipedHead.rotateAngleX - 0.7F) * 0.75F;

            this.bipedRightArm.rotateAngleX -= (f7 * 1.2F + f8);
            this.bipedRightArm.rotateAngleZ  = MathHelper.sin(swing * (float)Math.PI) * -0.4F;
        }

        // Legends stance tweak (safe via reflection)
        ensureLegends();
        if (legendsPresent && e.getHeldItem() != null &&
            CLS_Lightsaber.isInstance(e.getHeldItem().getItem())) {
            try {
                if ((Boolean) M_isActive.invoke(null, e.getHeldItem())) {
                    this.bipedRightArm.rotateAngleX -= 0.4F;
                }
            } catch (Throwable ignored) {}
        }
    }

}

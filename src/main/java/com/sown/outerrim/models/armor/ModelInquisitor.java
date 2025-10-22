package com.sown.outerrim.models.armor;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;

public class ModelInquisitor extends ModelBiped {
    private final ModelRenderer helmet;
    private final ModelRenderer helmet2;
    private final ModelRenderer chestplate;
    private final ModelRenderer chestplate2;
    private final ModelRenderer rarm;
    private final ModelRenderer larm;
    private final ModelRenderer lleg;
    private final ModelRenderer lfoot;
    private final ModelRenderer rleg;
    private final ModelRenderer rfoot;

    public ModelInquisitor() {
        textureWidth = 128;
        textureHeight = 128;

        helmet = new ModelRenderer(this, 0, 0);
        helmet.addBox(-4.5F, -8.25F, -4.5F, 9, 9, 9);
        helmet2 = new ModelRenderer(this, 43, 47);
        helmet2.addBox(-5.0F, -4.75F, 2.75F, 10, 4, 2);

        ModelRenderer helmet_r2 = new ModelRenderer(this, 27, 0);
        helmet_r2.setRotationPoint(0.0F, 1.0F, 0.0F);
        helmet.addChild(helmet_r2);
        setRotationAngle(helmet_r2, -0.1745F, 0.0F, 0.0F);
        helmet_r2.addBox(-5.0F, -7.0F, -2.5F, 10, 4, 4);
        ModelRenderer helmet_r2_r2 = new ModelRenderer(this, 17, 47);
        helmet_r2_r2.setRotationPoint(0.0F, 1.0F, 0.0F);
        helmet.addChild(helmet_r2_r2);
        setRotationAngle(helmet_r2_r2, -0.1745F, 0.0F, 0.0F);
        helmet_r2_r2.addBox(-5.0F, -7.0F, -6.0F, 10, 2, 3);

        ModelRenderer helmet_r3 = new ModelRenderer(this, 55, 0);
        helmet_r3.setRotationPoint(0.0F, 1.0F, 0.0F);
        helmet.addChild(helmet_r3);
        setRotationAngle(helmet_r3, 0.2182F, 0.0F, 0.0F);
        helmet_r3.addBox(-2.5F, -6.0F, -4.75F, 5, 5, 2);

        chestplate = new ModelRenderer(this, 0, 18);
        chestplate.addBox(-4.5F, -0.25F, -2.5F, 9, 13, 5);
        chestplate2 = new ModelRenderer(this, 36, 53);
        chestplate2.addBox(-4.5F, 0.75F, 2.5F, 9, 6, 1);
        
        ModelRenderer chestplate_r1 = new ModelRenderer(this, 0, 0);
        chestplate_r1.setRotationPoint(0.0F, 8.0F, 0.0F);
        chestplate.addChild(chestplate_r1);
        setRotationAngle(chestplate_r1, 0.0F, 0.0F, 0.0873F);
        chestplate_r1.addBox(1.5F, -8.25F, -3.5F, 3, 7, 1);

        this.rarm = new ModelRenderer(this, 43, 31);
        this.rarm.addBox(-3.0F, -2.0F, -2.0F, 4, 12, 4, 0.25F);
        this.rarm.setRotationPoint(0.0F, 0.0F, 0.0F);

        ModelRenderer rarm_r1 = new ModelRenderer(this, 18, 52);
        rarm_r1.setRotationPoint(0.75F, 0.0F, 0.0F);
        rarm.addChild(rarm_r1);
        setRotationAngle(rarm_r1, 0.0F, 0.0F, 0.2182F);

        ModelRenderer rarm_r1_r1 = new ModelRenderer(this, 18, 52);
        rarm_r1_r1.setRotationPoint(4.0F, 3.0F, 0.0F);
        rarm_r1.addChild(rarm_r1_r1);
        setRotationAngle(rarm_r1_r1, 0.0F, 0.0F, -0.1309F);
        rarm_r1_r1.addBox(-8.0F, -5.75F, -2.5F, 4, 6, 5);


        this.larm = new ModelRenderer(this, 28, 18);
        this.larm.addBox(-1.0F, -2.0F, -2.0F, 4, 12, 4, 0.25F);
        this.larm.setRotationPoint(0.0F, 0.0F, 0.0F);

        ModelRenderer larm_r1 = new ModelRenderer(this, 0, 48);
        larm_r1.setRotationPoint(-1.0F, 0.0F, 0.0F);
        larm.addChild(larm_r1);
        setRotationAngle(larm_r1, 0.0F, 0.0F, -0.2182F);

        ModelRenderer larm_r1_r1 = new ModelRenderer(this, 0, 48);
        larm_r1_r1.setRotationPoint(-4.0F, 3.0F, 0.0F);
        larm_r1.addChild(larm_r1_r1);
        setRotationAngle(larm_r1_r1, 0.0F, 0.0F, 0.1309F);
        larm_r1_r1.addBox(4.0F, -5.75F, -2.5F, 4, 6, 5);

        lleg = new ModelRenderer(this, 44, 19);
        lleg.setRotationPoint(0.0F, 0.0F, 0.0F);
        lleg.addBox(-2.5F, -0.5F, -2.5F, 5, 6, 5);

        lfoot = new ModelRenderer(this, 0, 36);
        lfoot.addBox(-2.4F, 5.5F, -2.5F, 5, 7, 5);

        rleg = new ModelRenderer(this, 40, 8);
        rleg.setRotationPoint(0.0F, 0.0F, 0.0F);
        rleg.addBox(-2.75F, -0.5F, -2.5F, 5, 6, 5);

        rfoot = new ModelRenderer(this, 23, 35);
        rfoot.addBox(-2.85F, 5.5F, -2.5F, 5, 7, 5);

        this.bipedHead.addChild(this.helmet);
        this.bipedHead.addChild(this.helmet2);
        this.bipedBody.addChild(this.chestplate);
        this.bipedBody.addChild(this.chestplate2);
        this.bipedRightArm.addChild(this.rarm);
        this.bipedLeftArm.addChild(this.larm);
        this.bipedRightLeg.addChild(this.rleg);
        this.bipedRightLeg.addChild(this.rfoot);
        this.bipedLeftLeg.addChild(this.lleg);
        this.bipedLeftLeg.addChild(this.lfoot);
    }

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }

    protected void adjustEP(EntityPlayer entity, ItemStack stack) {
        this.heldItemRight = stack != null ? 1 : 0;
        this.isSneak = entity.isSneaking();
        
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
        this.heldItemRight = stack != null ? 1 : 0;
        this.isSneak = entity.isSneaking();
        this.aimedBow = false;
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        if (entity instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer)entity;
            this.adjustEP(player, player.getHeldItem());
        } else if (entity instanceof EntityLivingBase) {
            EntityLivingBase el = (EntityLivingBase)entity;
            this.adjustEL(el, el.getHeldItem());
        }
        
        this.rarm.rotateAngleX = this.bipedRightArm.rotateAngleX;
        this.rarm.rotateAngleY = this.bipedRightArm.rotateAngleY;
        this.rarm.rotateAngleZ = this.bipedRightArm.rotateAngleZ;
        
        this.larm.rotateAngleX = this.bipedLeftArm.rotateAngleX;
        this.larm.rotateAngleY = this.bipedLeftArm.rotateAngleY;
        this.larm.rotateAngleZ = this.bipedLeftArm.rotateAngleZ;

        super.render(entity, f, f1, f2, f3, f4, f5);
    }

}

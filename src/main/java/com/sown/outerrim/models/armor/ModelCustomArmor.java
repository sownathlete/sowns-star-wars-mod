package com.sown.outerrim.models.armor;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;

public class ModelCustomArmor extends ModelBiped {
    public ModelRenderer helmet;
    public ModelRenderer jacket;
    public ModelRenderer leftSleeve;
    public ModelRenderer rightSleeve;
    public ModelRenderer leftPantLeg;
    public ModelRenderer rightPantLeg;

    public ModelCustomArmor() {
        this.textureWidth = 64;
        this.textureHeight = 32;
        this.leftSleeve = new ModelRenderer(this, 40, 16);
        this.leftSleeve.mirror = true; // Add this line
        this.leftSleeve.setRotationPoint(0F, 0F, 0.0F);
        this.leftSleeve.addBox(-1.0F, -2.0F, -2.0F, 4, 12, 4, 0.25F);
        this.leftSleeve.setTextureSize(64, 32);
        this.setRotation(this.leftSleeve, 0.0f, 0.0f, 0.0f);
        this.helmet = new ModelRenderer(this, 32, 0);
        this.helmet.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.helmet.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, 0.5F);
        this.helmet.setTextureSize(64, 32);
        this.setRotation(this.helmet, 0.0f, 0.0f, 0.0f);
        this.jacket = new ModelRenderer(this, 16, 16);
        this.jacket.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.jacket.addBox(-4.0F, 0.0F, -2.0F, 8, 12, 4, 0.25F);
        this.jacket.setTextureSize(64, 32);
        this.setRotation(this.jacket, 0.0f, 0.0f, 0.0f);
        this.rightPantLeg = new ModelRenderer(this, 0, 16);
        this.rightPantLeg.setRotationPoint(0F, 0F, 0.0F);
        this.rightPantLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.25F);
        this.rightPantLeg.setTextureSize(64, 32);
        this.setRotation(this.rightPantLeg, 0.0f, 0.0f, 0.0f);
        this.leftPantLeg = new ModelRenderer(this, 0, 16);
        this.leftPantLeg.mirror = true; // Add this line
        this.leftPantLeg.setRotationPoint(0F,0F, 0.0F);
        this.leftPantLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.25F);
        this.leftPantLeg.setTextureSize(64, 32);
        this.setRotation(this.leftPantLeg, 0.0f, 0.0f, 0.0f);
        this.rightSleeve = new ModelRenderer(this, 40, 16);
        this.rightSleeve.setRotationPoint(0F, 0.0F, 0.0F);
        this.rightSleeve.addBox(-3.0F, -2.0F, -2.0F, 4, 12, 4, 0.25F);
        this.rightSleeve.setTextureSize(64, 32);
        this.setRotation(this.rightSleeve, 0.0f, 0.0f, 0.0f);

        // Attach custom parts to the ModelBiped parts
        this.bipedHead.addChild(this.helmet);
        this.bipedBody.addChild(this.jacket);
        this.bipedRightArm.addChild(this.rightSleeve);
        this.bipedLeftArm.addChild(this.leftSleeve);
        this.bipedRightLeg.addChild(this.rightPantLeg);
        this.bipedLeftLeg.addChild(this.leftPantLeg);
    }

    @SuppressWarnings("unused")
	private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }

    // Add the adjustEP method
protected void adjustEP(EntityPlayer entity, ItemStack stack) {
    this.heldItemRight = stack != null ? 1 : 0;
    this.isSneak = entity.isSneaking();
    
    if (stack != null && entity.getItemInUseCount() > 0) {
        EnumAction enumaction = stack.getItemUseAction();
        if (enumaction == EnumAction.bow) {
            this.aimedBow = true;
        } else {
            this.aimedBow = false;
        }
    } else {
        this.aimedBow = false;
    }
}

    // Add the adjustEL method
    protected void adjustEL(EntityLivingBase entity, ItemStack stack) {
        this.heldItemRight = stack != null ? 1 : 0;
        this.isSneak = entity.isSneaking();
        // For EntityLivingBase, we don't check for bow aiming
        this.aimedBow = false;
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        // Synchronize armor's arm rotations with the base model's arm rotations
        this.rightSleeve.rotateAngleX = this.bipedRightArm.rotateAngleX;
        this.rightSleeve.rotateAngleY = this.bipedRightArm.rotateAngleY;
        this.rightSleeve.rotateAngleZ = this.bipedRightArm.rotateAngleZ;
        
        this.leftSleeve.rotateAngleX = this.bipedLeftArm.rotateAngleX;
        this.leftSleeve.rotateAngleY = this.bipedLeftArm.rotateAngleY;
        this.leftSleeve.rotateAngleZ = this.bipedLeftArm.rotateAngleZ;
        if (entity instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer)entity;
            this.adjustEP(player, player.getHeldItem());
        } else if (entity instanceof EntityLivingBase) {
            EntityLivingBase el = (EntityLivingBase)entity;
            this.adjustEL(el, el.getHeldItem());
        }
        super.render(entity, f, f1, f2, f3, f4, f5);
    }

    

}

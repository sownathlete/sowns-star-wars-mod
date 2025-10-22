package com.sown.outerrim.items;

import com.sown.outerrim.OuterRim;
import com.sown.outerrim.models.armor.ModelInquisitor;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.FMLCommonHandler;

public class ItemInquisitorArmor extends ItemArmor {
    
    private ModelInquisitor customModel;  // Declare here, but don't initialize yet

    public ItemInquisitorArmor(ArmorMaterial material, int armorType, String armorName) {
        super(material, 0, armorType);

        // Check if we are on the client side before initializing client-only classes
        if (FMLCommonHandler.instance().getEffectiveSide() == Side.CLIENT) {
            this.customModel = new ModelInquisitor();  // Initialize only on client side
        }

        this.setUnlocalizedName("outerrim." + armorName);
        this.setTextureName("outerrim:" + armorName);
        this.setCreativeTab(OuterRim.tabCombat);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack itemStack, int armorSlot) {
    	ModelInquisitor model = new ModelInquisitor();
        
        // Set default visibility
    	model.bipedHead.showModel = armorSlot == 0;
        model.bipedHeadwear.showModel = armorSlot == 0;
        model.bipedBody.showModel = armorSlot == 1;
        model.bipedRightArm.showModel = armorSlot == 1;
        model.bipedLeftArm.showModel = armorSlot == 1;
        model.bipedRightLeg.showModel = (armorSlot == 2 || armorSlot == 3);
        model.bipedLeftLeg.showModel = (armorSlot == 2 || armorSlot == 3);
        
        // Handle custom visibility and rotations
        // Example: If the helmet is worn, hide the headwear
        if (armorSlot == 0) {
        	model.bipedHeadwear.showModel = false;
        }
        
        return model;
    }

    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type) {
        if (this.armorType == 2) { // Leggings
            return "outerrim:textures/models/armor/" + this.getArmorMaterial().name().toLowerCase() + "_layer_2.png";
        } else {
            return "outerrim:textures/models/armor/" + this.getArmorMaterial().name().toLowerCase() + "_layer_1.png";
        }
    }
}

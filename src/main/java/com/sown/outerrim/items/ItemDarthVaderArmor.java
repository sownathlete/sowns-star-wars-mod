package com.sown.outerrim.items;

import java.util.List;

import com.sown.outerrim.entities.ModelDarthVader;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;

public class ItemDarthVaderArmor extends ItemArmor {
    @SideOnly(Side.CLIENT)
    private ModelDarthVader armorModel;

    public ItemDarthVaderArmor(ArmorMaterial material, int renderIndex, int armorSlot) {
        super(material, renderIndex, armorSlot);
        if (FMLCommonHandler.instance().getEffectiveSide() == Side.CLIENT) {
            this.armorModel = new ModelDarthVader();
        }
    }
    
    @Override
    public int getMaxDamage(ItemStack stack) {
        return Loader.isModLoaded("legends") ? 1000 : super.getMaxDamage(stack);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack itemStack, int armorSlot) {
        if (this.armorModel == null) this.armorModel = new ModelDarthVader();

        // hide everything
        armorModel.bipedHead.showModel      = false;
        armorModel.bipedHeadwear.showModel  = false;
        armorModel.bipedBody.showModel      = false;
        armorModel.bipedRightArm.showModel  = false;
        armorModel.bipedLeftArm.showModel   = false;
        armorModel.bipedRightLeg.showModel  = false;
        armorModel.bipedLeftLeg.showModel   = false;
        armorModel.bipedCloak.showModel     = false;

        // enable slot-specific parts
        switch (armorSlot) {
            case 0: // helmet
                armorModel.bipedHead.showModel     = true;
                armorModel.bipedHeadwear.showModel = true;
                break;
            case 1: // chest
                armorModel.bipedBody.showModel     = true;
                armorModel.bipedCloak.showModel    = true;
                armorModel.bipedRightArm.showModel = true;
                armorModel.bipedLeftArm.showModel  = true;
                break;
            case 2: // leggings
                armorModel.bipedBody.showModel     = true;
                armorModel.bipedRightLeg.showModel = true;
                armorModel.bipedLeftLeg.showModel  = true;
                break;
            case 3: // boots
                armorModel.bipedRightLeg.showModel = true;
                armorModel.bipedLeftLeg.showModel  = true;
                break;
        }

        // copy poses
        armorModel.isSneak       = entityLiving.isSneaking();
        armorModel.isRiding      = entityLiving.isRiding();
        armorModel.heldItemRight = entityLiving.getHeldItem() != null ? 1 : 0;
        armorModel.aimedBow      = (entityLiving.getHeldItem() != null
            && entityLiving.getHeldItem().getItemUseAction() == EnumAction.bow);

        return armorModel;
    }

    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, int slot, String layer) {
        String suffix = (slot == 2) ? "_layer_2" : "_layer_1";
        return "outerrim:textures/models/armor/darth_vader" + suffix + ".png";
    }
    
    @SideOnly(Side.CLIENT)
    @SuppressWarnings("rawtypes")
    public void addInformation(ItemStack stack, EntityPlayer player, List tooltip, boolean advanced) {
        super.addInformation(stack, player, tooltip, advanced);
        if (Loader.isModLoaded("legends")) {
            tooltip.add(EnumChatFormatting.RESET + "" + EnumChatFormatting.RED + "Tier 5");
        }
    }
}

package com.sown.outerrim.items;

import java.util.List;

import com.sown.outerrim.entities.ModelCountDooku;

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

public class ItemCountDookuArmor extends ItemArmor {
    @SideOnly(Side.CLIENT)
    private ModelCountDooku armorModel;

    public ItemCountDookuArmor(ArmorMaterial material, int renderIndex, int armorSlot) {
        super(material, renderIndex, armorSlot);
        if (FMLCommonHandler.instance().getEffectiveSide() == Side.CLIENT) {
            this.armorModel = new ModelCountDooku();
        }
    }
    
    @Override
    public int getMaxDamage(ItemStack stack) {
        return Loader.isModLoaded("legends") ? 1000 : super.getMaxDamage(stack);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public ModelBiped getArmorModel(
            EntityLivingBase entityLiving,
            ItemStack itemStack,
            int armorSlot
    ) {
        if (this.armorModel == null) {
            this.armorModel = new ModelCountDooku();
        }

        // hide all parts by default
        armorModel.bipedHead.showModel      = false;
        armorModel.bipedHeadwear.showModel  = false;
        armorModel.bipedBody.showModel      = false;
        armorModel.bipedRightArm.showModel  = false;
        armorModel.bipedLeftArm.showModel   = false;
        armorModel.bipedRightLeg.showModel  = false;
        armorModel.bipedLeftLeg.showModel   = false;
        armorModel.bipedCloak.showModel     = false;
        armorModel.jacket.showModel         = false;
        armorModel.leftSleeve.showModel     = false;
        armorModel.rightSleeve.showModel    = false;
        armorModel.leftPantLeg.showModel    = false;
        armorModel.rightPantLeg.showModel   = false;
        armorModel.beard.showModel          = false;

        // show only the relevant parts for this slot
        switch (armorSlot) {
            case 0:  // Helmet
                armorModel.bipedHead.showModel     = true;
                armorModel.beard.showModel         = true;
                break;
            case 1:  // Chestplate
                armorModel.bipedBody.showModel     = true;
                armorModel.bipedCloak.showModel        = true;
                armorModel.jacket.showModel        = true;
                armorModel.bipedRightArm.showModel = true;
                armorModel.bipedLeftArm.showModel  = true;
                armorModel.rightSleeve.showModel   = true;
                armorModel.leftSleeve.showModel    = true;
                break;
            case 2:  // Leggings
                armorModel.bipedBody.showModel     = true;
                armorModel.bipedRightLeg.showModel = true;
                armorModel.bipedLeftLeg.showModel  = true;
                armorModel.rightPantLeg.showModel  = true;
                armorModel.leftPantLeg.showModel   = true;
                break;
            case 3:  // Boots
                armorModel.bipedRightLeg.showModel = true;
                armorModel.bipedLeftLeg.showModel  = true;
                armorModel.rightPantLeg.showModel  = true;
                armorModel.leftPantLeg.showModel   = true;
                break;
        }

        // copy state
        armorModel.isSneak       = entityLiving.isSneaking();
        armorModel.isRiding      = entityLiving.isRiding();
        armorModel.heldItemRight = entityLiving.getHeldItem() != null ? 1 : 0;
        armorModel.aimedBow      = (entityLiving.getHeldItem() != null
                && entityLiving.getHeldItem().getItemUseAction() == EnumAction.bow);

        return armorModel;
    }

    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, int slot, String layer) {
        // layer 2 = leggings
        String suffix = (slot == 2) ? "_layer_2" : "_layer_1";
        return "outerrim:textures/models/armor/count_dooku" + suffix + ".png";
    }
    
    @SideOnly(Side.CLIENT)
    @SuppressWarnings("rawtypes")
    public void addInformation(ItemStack stack, EntityPlayer player, List tooltip, boolean advanced) {
        // first call vanilla so you get durability, etc.
        super.addInformation(stack, player, tooltip, advanced);

        if (Loader.isModLoaded("legends")) {
            // RESET clears the default italic, then RED colors it
            tooltip.add(EnumChatFormatting.RESET + "" + EnumChatFormatting.GOLD + "Tier 4");
        }
    }
}

package com.sown.outerrim.items;

import com.sown.outerrim.entities.ModelKesselMineWorker;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;

public class ItemKesselMineWorkerArmor extends ItemArmor {
    @SideOnly(Side.CLIENT)
    private ModelKesselMineWorker armorModel;

    public ItemKesselMineWorkerArmor(ArmorMaterial material, int renderIndex, int armorSlot) {
        super(material, renderIndex, armorSlot);

        if (FMLCommonHandler.instance().getEffectiveSide() == Side.CLIENT) {
            this.armorModel = new ModelKesselMineWorker();
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public ModelBiped getArmorModel(
            EntityLivingBase entityLiving,
            ItemStack itemStack,
            int armorSlot
    ) {
        if (this.armorModel == null) {
            this.armorModel = new ModelKesselMineWorker();
        }

        armorModel.bipedHead.showModel      = false;
        armorModel.bipedHeadwear.showModel  = false;
        armorModel.bipedBody.showModel      = false;
        armorModel.bipedRightArm.showModel  = false;
        armorModel.bipedLeftArm.showModel   = false;
        armorModel.bipedRightLeg.showModel  = false;
        armorModel.bipedLeftLeg.showModel   = false;

        switch (armorSlot) {
            case 0:  // Helmet slot
                armorModel.bipedHead.showModel     = true;
                armorModel.bipedHeadwear.showModel = true;
                break;

            case 1:  // Chestplate slot
                armorModel.bipedBody.showModel     = true;
                armorModel.bipedRightArm.showModel = true;
                armorModel.bipedLeftArm.showModel  = true;
                break;

            case 2:  // Leggings slot
                armorModel.bipedRightLeg.showModel = true;
                armorModel.bipedLeftLeg.showModel  = true;
                break;

            case 3:  // Boots slot
                armorModel.bipedRightLeg.showModel = true;
                armorModel.bipedLeftLeg.showModel  = true;
                break;
        }

        armorModel.isSneak        = entityLiving.isSneaking();
        armorModel.isRiding       = entityLiving.isRiding();
        armorModel.heldItemRight  = entityLiving.getHeldItem() != null ? 1 : 0;
        armorModel.aimedBow       = (entityLiving.getHeldItem() != null
                                     && entityLiving.getHeldItem().getItem().getItemUseAction(entityLiving.getHeldItem())
                                        == net.minecraft.item.EnumAction.bow);

        // Return our customized model
        return armorModel;
    }

    @Override
    public String getArmorTexture(ItemStack stack, net.minecraft.entity.Entity entity, int slot, String layer) {
        if (slot == 2) {
            return "outerrim:textures/models/armor/kesselworker_layer_2.png";
        } else {
            return "outerrim:textures/models/armor/kesselworker_layer_1.png";
        }
    }
}

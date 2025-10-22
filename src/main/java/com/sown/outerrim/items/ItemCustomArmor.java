package com.sown.outerrim.items;

import com.sown.outerrim.OuterRim;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;

public class ItemCustomArmor extends ItemArmor {

    public ItemCustomArmor(ArmorMaterial material, int armorType, String armorName) {
        super(material, 0, armorType);
        this.setUnlocalizedName("outerrim." + armorName);
        this.setTextureName("outerrim:" + armorName);
        this.setCreativeTab(OuterRim.tabCombat);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type) {
        if (this.armorType == 2) { // Leggings
            return "outerrim:textures/models/armor/" + this.getArmorMaterial().name().toLowerCase() + "_layer_2.png";
        } else {
            return "outerrim:textures/models/armor/" + this.getArmorMaterial().name().toLowerCase() + "_layer_1.png";
        }
    }
}

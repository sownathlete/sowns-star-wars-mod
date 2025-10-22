package com.sown.outerrim.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

public class ItemBlockMetadata extends ItemBlock {
  public ItemBlockMetadata(Block block) {
    super(block);
    setMaxDamage(0);
    setHasSubtypes(true);
  }
  
  @SideOnly(Side.CLIENT)
  public IIcon getIconFromDamage(int i) {
    return this.field_150939_a.getIcon(2, i);
  }
  
  @SideOnly(Side.CLIENT)
  public int getColorFromItemStack(ItemStack itemstack, int pass) {
    return this.field_150939_a.getRenderColor(itemstack.getItemDamage());
  }
  
  public int getMetadata(int i) {
    return i;
  }
  
  public String getUnlocalizedName(ItemStack itemstack) {
    return getUnlocalizedName() + "." + itemstack.getItemDamage();
  }
}

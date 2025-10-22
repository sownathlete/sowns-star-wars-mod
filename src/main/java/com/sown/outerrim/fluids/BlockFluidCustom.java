package com.sown.outerrim.fluids;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;
import java.util.Random;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockFluidCustom extends BlockFluidClassic {
    private static final Random rand = new Random();
    private static final int EFFECT_INTERVAL = 60;

    @SideOnly(Side.CLIENT)
    protected IIcon stillIcon;
    @SideOnly(Side.CLIENT)
    protected IIcon flowingIcon;

    private final FluidCustomLiquid customFluid;
    private PotionEffect potionEffect;

    public BlockFluidCustom(Fluid fluid, Material material) {
        super(fluid, material);
        if (fluid instanceof FluidCustomLiquid) {
            this.customFluid = (FluidCustomLiquid) fluid;
        } else {
            throw new IllegalArgumentException("BlockFluidCustom requires a FluidCustomLiquid instance.");
        }
    }

    public void setPotionEffect(PotionEffect potionEffect) {
        this.potionEffect = potionEffect;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconRegister) {
        stillIcon = iconRegister.registerIcon("outerrim:" + fluidName + "_still");
        flowingIcon = iconRegister.registerIcon("outerrim:" + fluidName + "_flow");
        getFluid().setIcons(stillIcon, flowingIcon);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int meta) {
        return side != 0 && side != 1 ? this.flowingIcon : this.stillIcon;
    }

    @Override
    public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity) {
        if (!world.isRemote) {
            // Acid damage
            if (this.customFluid.getIsAcid() && entity instanceof EntityLivingBase) {
                ((EntityLivingBase) entity).attackEntityFrom(DamageSource.generic, 0.5F);
            }

            // Apply potion effect
            if (this.potionEffect != null && entity instanceof EntityLivingBase) {
                ((EntityLivingBase) entity).addPotionEffect(new PotionEffect(this.potionEffect));
            }

            if (entity instanceof EntityPlayer) {
                EntityPlayer player = (EntityPlayer) entity;
                int tickCount = player.getEntityData().getInteger("effectTickCount");

                tickCount++;

                if (tickCount >= EFFECT_INTERVAL) {
                    // Only repair armor if not acid
                    if (this.customFluid.getRepairsArmor() && !this.customFluid.getIsAcid()) {
                        for (ItemStack armorItem : player.inventory.armorInventory) {
                            if (armorItem != null && armorItem.isItemDamaged()) {
                                armorItem.setItemDamage(Math.max(armorItem.getItemDamage() - 1, 0));
                            }
                        }
                    }

                    // Damage armor if it's acid
                    if (this.customFluid.getIsAcid()) {
                        for (ItemStack armorItem : player.inventory.armorInventory) {
                            if (armorItem != null && armorItem.getMaxDamage() > 0) {
                                armorItem.damageItem(3, player);
                            }
                        }
                    }

                    if (this.customFluid.getHealsPlayer()) {
                        if (player.getHealth() < player.getMaxHealth()) {
                            player.heal(1.0F);
                        }

                        if (player.getFoodStats().getFoodLevel() < 20) {
                            player.getFoodStats().addStats(1, 0.3F);
                        } else {
                            player.getFoodStats().addExhaustion(-2.0F);
                        }
                    }

                    tickCount = 0;
                }

                player.getEntityData().setInteger("effectTickCount", tickCount);

                if (this.customFluid.getPreventsDrowning() && player.getAir() < 300) {
                    player.setAir(300);
                }
            }
        }

        // Subtle drift effect
        entity.motionX += (rand.nextFloat() - rand.nextFloat()) * 0.001F;
        entity.motionY += (rand.nextFloat() - rand.nextFloat()) * 0.001F;
        entity.motionZ += (rand.nextFloat() - rand.nextFloat()) * 0.001F;
    }
}

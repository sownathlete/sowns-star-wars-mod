package com.sown.outerrim.items;

import java.util.List;
import java.util.Objects;

import com.sown.outerrim.OuterRim;
import com.sown.outerrim.OuterRimResources;
import com.sown.outerrim.network.MessageHyperdrive;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;
import net.minecraftforge.common.DimensionManager;

public class ItemCustomHyperdrive extends Item {
    private final String planetName;

    public ItemCustomHyperdrive(String planetName) {
        this.planetName = planetName;
        String name = "hyperdrive" + planetName;
        this.setUnlocalizedName("outerrim." + name);
        this.setTextureName("outerrim:" + name);
        this.maxStackSize = 1;
    }

    @Override
    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
        // if not holding shift or not creative etc, this runs only when sneaking & creative
        if (!player.isSneaking() || !player.capabilities.isCreativeMode || !world.isRemote) {
            return stack;
        }

        // check implemented
        int dimId;
        try {
            dimId = OuterRimResources.ConfigOptions.getDimId(planetName);
        } catch (Exception e) {
            return stack;  // not configured
        }
        boolean implemented = false;
        for (int id : DimensionManager.getIDs()) {
            if (id == dimId) {
                implemented = true;
                break;
            }
        }
        if (!implemented) {
            return stack;  // do nothing if not implemented
        }

        // now perform hyperdrive
        if (player.dimension != dimId) {
            player.timeUntilPortal = 20;
            OuterRim.network.sendToServer(new MessageHyperdrive(player, dimId));
        }
        return stack;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof ItemCustomHyperdrive)) return false;
        ItemCustomHyperdrive other = (ItemCustomHyperdrive) obj;
        return Objects.equals(this.planetName, other.planetName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(planetName);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean advanced) {
        // Usage hint
        list.add(EnumChatFormatting.GRAY + "Usage: Shift + Right-Click");
        // Creative-only notice
        list.add(EnumChatFormatting.RED + "Creative-only item");

        // Check if dimension is registered
        try {
            int dimId = OuterRimResources.ConfigOptions.getDimId(planetName);
            boolean implemented = false;
            for (int id : DimensionManager.getIDs()) {
                if (id == dimId) {
                    implemented = true;
                    break;
                }
            }
            if (implemented) {
                list.add(EnumChatFormatting.GREEN + "Implemented (Dim " + dimId + ")");
            } else {
                list.add(EnumChatFormatting.DARK_RED + "Not implemented");
            }
        } catch (Exception e) {
            list.add(EnumChatFormatting.DARK_RED + "Not implemented");
        }
    }
}

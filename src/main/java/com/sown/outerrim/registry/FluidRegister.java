package com.sown.outerrim.registry;

import com.sown.outerrim.OuterRim;
import com.sown.outerrim.fluids.BlockFluidCustom;
import com.sown.outerrim.fluids.FluidCustomLiquid;
import com.sown.outerrim.items.ItemCustomBucket;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;

import java.util.HashMap;
import java.util.Map;

public class FluidRegister {
    private static Map<String, Fluid> registeredFluids = new HashMap<>();
    private static Map<String, BlockFluidCustom> registeredFluidBlocks = new HashMap<>();

    // Register all fluids
    public static void registerAll() {
        registerFluid("liquid_acid", false, 1000, 1000, 0, 300, true, null, false, false, false);
        registerFluid("liquid_bacta", false, 1000, 1000, 0, 300, false, null, true, true, true);
        registerFluid("liquid_ambori", false, 1000, 5000, 0, 300, false, null, true, true, false);
    }

    // Modified to include the healsPlayer parameter
    public static void registerFluid(String name, boolean gaseous, int density, int viscosity, int luminosity, int temperature, boolean isAcid, PotionEffect potionEffect, boolean repairsArmor, boolean preventsDrowning, boolean healsPlayer) {
        FluidCustomLiquid fluid = new FluidCustomLiquid(name);
        fluid.setDensity(density);
        fluid.setViscosity(viscosity);
        fluid.setLuminosity(luminosity);
        fluid.setTemperature(temperature);
        fluid.setIsAcid(isAcid);
        fluid.setRepairsArmor(repairsArmor);
        fluid.setPreventsDrowning(preventsDrowning);
        fluid.setHealsPlayer(healsPlayer); // Set the new healsPlayer property

        FluidRegistry.registerFluid(fluid);

        // Create and register the corresponding block
        BlockFluidCustom blockFluid = new BlockFluidCustom(fluid, Material.water);
        blockFluid.setBlockName(name);
        blockFluid.setPotionEffect(potionEffect);
        GameRegistry.registerBlock(blockFluid, name);

        // Create and register the bucket
        ItemCustomBucket bucketItem = new ItemCustomBucket(blockFluid);
        bucketItem.setUnlocalizedName(name + "_bucket");
        GameRegistry.registerItem(bucketItem, name + "_bucket");

        bucketItem.setCreativeTab(OuterRim.tabMisc);
        ItemRegister.misc.add(bucketItem);

        // Register the fluid container
        FluidContainerRegistry.registerFluidContainer(new FluidStack(fluid, 1000), new ItemStack(bucketItem), FluidContainerRegistry.EMPTY_BUCKET);

        // Store the fluid and block in maps for later access
        registeredFluids.put(name, fluid);
        registeredFluidBlocks.put(name, blockFluid);
    }

    public static Fluid getRegisteredFluid(String fluidName) {
        return registeredFluids.get(fluidName);
    }

    public static BlockFluidCustom getRegisteredFluidBlock(String fluidName) {
        return registeredFluidBlocks.get(fluidName);
    }

    public static Map<String, Fluid> getRegisteredFluids() {
        return registeredFluids;
    }
}

package com.sown.outerrim.handlers;

import com.sown.outerrim.registry.FluidRegister;

import java.util.Map;

import com.sown.outerrim.items.ItemCustomBucket;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.FillBucketEvent;
import net.minecraftforge.fluids.Fluid;
import cpw.mods.fml.common.eventhandler.Event;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class BucketHandler {

    @SubscribeEvent
    public void onBucketFill(FillBucketEvent event) {
        World world = event.world;
        int x = event.target.blockX;
        int y = event.target.blockY;
        int z = event.target.blockZ;

        if (event.current.getItem() == Items.bucket) {
            for (Map.Entry<String, Fluid> entry : FluidRegister.getRegisteredFluids().entrySet()) {
                Fluid fluid = entry.getValue();
                
                if (world.getBlock(x, y, z) == fluid.getBlock()) {
                    event.result = new ItemStack(ItemCustomBucket.getBucketForFluid(entry.getKey()));
                    world.setBlockToAir(x, y, z);
                    event.setResult(Event.Result.ALLOW);
                    return;
                }
            }
        }
    }
}

package com.sown.outerrim.items;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.common.MinecraftForge;

public class InquisitorHelmetLayerHandler {

    public InquisitorHelmetLayerHandler() {
        // Register only on the CLIENT side
        if (FMLCommonHandler.instance().getEffectiveSide() == Side.CLIENT) {
            MinecraftForge.EVENT_BUS.register(this);
        }
    }

    @SubscribeEvent(priority = EventPriority.NORMAL)
    public void onRenderPlayerPre(RenderPlayerEvent.Pre event) {
        EntityPlayer player = event.entityPlayer;
        ModelBiped playerModel = event.renderer.modelBipedMain;

        // 1) Reset visibility for every part (in case a prior frame hid something)
        playerModel.bipedHead.showModel       = true;
        playerModel.bipedHeadwear.showModel   = true;
        playerModel.bipedBody.showModel       = true;
        playerModel.bipedRightArm.showModel   = true;
        playerModel.bipedLeftArm.showModel    = true;
        playerModel.bipedRightLeg.showModel   = true;
        playerModel.bipedLeftLeg.showModel    = true;

        ItemStack heldItem = player.getHeldItem();

        // 
        // 2) Helmet slot (index 3)
        // 
        ItemStack helmetStack = player.inventory.armorItemInSlot(3);
        boolean isLegendsHelmet = false;
        if (helmetStack != null && helmetStack.getItem() instanceof ItemArmor) {
            // Detect if this helmet belongs to the Legends mod (modId contains legends)
            String modId = GameRegistry.findUniqueIdentifierFor(helmetStack.getItem()).modId;
            if (modId != null && modId.toLowerCase().contains("legends")) {
                isLegendsHelmet = true;
            }

            boolean isInquisitorHelmet   = helmetStack.getItem() instanceof ItemInquisitorArmor;
            boolean isKesselHelmet       = helmetStack.getItem() instanceof ItemKesselMineWorkerArmor;
            boolean isHanSoloHelmet      = helmetStack.getItem() instanceof ItemSmugglerHanSoloArmor;
            boolean isCountDookuHelmet   = helmetStack.getItem() instanceof ItemCountDookuArmor;

            // Check display name for "clone", "storm" + "trooper", or "shadow" + "trooper"
            String name = helmetStack.getDisplayName();
            boolean isSpecialHelmet = false;
            if (name != null) {
                String lower = name.toLowerCase();
                isSpecialHelmet = lower.contains("clone")
                              || (lower.contains("storm") && lower.contains("trooper"))
                              || (lower.contains("shadow") && lower.contains("trooper"));
            }

            // Hide head/headwear only if not a Legends helmet and is one of our custom armors
            if (!isLegendsHelmet &&
                (isInquisitorHelmet
              || isKesselHelmet
              || isHanSoloHelmet
              || isCountDookuHelmet
              || isSpecialHelmet)) {
                playerModel.bipedHead.showModel     = false;
                playerModel.bipedHeadwear.showModel = false;
            }
        }

        // 
        // 3) Chest slot (index 2)
        // 
        ItemStack chestStack = player.inventory.armorItemInSlot(2);
        if (chestStack != null && chestStack.getItem() instanceof ItemArmor) {
            boolean isInquisitorChest   = chestStack.getItem() instanceof ItemInquisitorArmor;
            boolean isKesselChest       = chestStack.getItem() instanceof ItemKesselMineWorkerArmor;
            boolean isHanSoloChest      = chestStack.getItem() instanceof ItemSmugglerHanSoloArmor;
            boolean isCountDookuChest   = chestStack.getItem() instanceof ItemCountDookuArmor;

            if (isInquisitorChest
             || isKesselChest
             || isHanSoloChest
             || isCountDookuChest) {
                // Hide torso and arms
                playerModel.bipedBody.showModel     = false;
                playerModel.bipedRightArm.showModel = false;
                playerModel.bipedLeftArm.showModel  = false;

                // If holding something, keep RIGHT arm visible so the held item renders
                if (heldItem != null) {
                    playerModel.bipedRightArm.showModel = true;
                }
            }
        }

        // 
        // 4) Leggings (index 1) + Boots (index 0)
        // 
        ItemStack legStack  = player.inventory.armorItemInSlot(1);
        ItemStack bootStack = player.inventory.armorItemInSlot(0);

        boolean hideLegs = false;
        if ((legStack  != null && legStack.getItem()  instanceof ItemInquisitorArmor)
         && (bootStack != null && bootStack.getItem() instanceof ItemInquisitorArmor)) {
            hideLegs = true;
        }
        if ((legStack  != null && legStack.getItem()  instanceof ItemKesselMineWorkerArmor)
         && (bootStack != null && bootStack.getItem() instanceof ItemKesselMineWorkerArmor)) {
            hideLegs = true;
        }
        if ((legStack  != null && legStack.getItem()  instanceof ItemSmugglerHanSoloArmor)
         && (bootStack != null && bootStack.getItem() instanceof ItemSmugglerHanSoloArmor)) {
            hideLegs = true;
        }
        if ((legStack  != null && legStack.getItem()  instanceof ItemCountDookuArmor)
         && (bootStack != null && bootStack.getItem() instanceof ItemCountDookuArmor)) {
            hideLegs = true;
        }

        if (hideLegs) {
            playerModel.bipedRightLeg.showModel = false;
            playerModel.bipedLeftLeg.showModel  = false;
        }
    }

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public void onRenderPlayerPost(RenderPlayerEvent.Post event) {
        // Restore every part to visible after rendering
        ModelBiped playerModel = event.renderer.modelBipedMain;
        playerModel.bipedHead.showModel       = true;
        playerModel.bipedHeadwear.showModel   = true;
        playerModel.bipedBody.showModel       = true;
        playerModel.bipedRightArm.showModel   = true;
        playerModel.bipedLeftArm.showModel    = true;
        playerModel.bipedRightLeg.showModel   = true;
        playerModel.bipedLeftLeg.showModel    = true;
    }
}

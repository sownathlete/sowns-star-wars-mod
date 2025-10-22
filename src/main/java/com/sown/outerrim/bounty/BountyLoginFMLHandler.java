package com.sown.outerrim.bounty;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;
import cpw.mods.fml.common.FMLCommonHandler;
import net.minecraft.entity.player.EntityPlayerMP;

public class BountyLoginFMLHandler {

    public static void register() {
        FMLCommonHandler.instance().bus().register(new BountyLoginFMLHandler());
    }

    @SubscribeEvent
    public void onPlayerLogin(PlayerLoggedInEvent event) {
        if (event.player instanceof EntityPlayerMP) {
            BountyLoginHandler.handleLogin((EntityPlayerMP) event.player);
        }
    }
}

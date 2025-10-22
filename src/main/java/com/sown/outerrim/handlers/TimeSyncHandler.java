package com.sown.outerrim.handlers;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent.WorldTickEvent;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.play.server.S03PacketTimeUpdate;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.DimensionManager;

public class TimeSyncHandler {

    private static int syncCooldown = 0;

    @SubscribeEvent
    public void onWorldTick(WorldTickEvent event) {
        if (event.world.isRemote || event.phase != WorldTickEvent.Phase.END)
            return;

        if (++syncCooldown < 100) return; // sync every 5 seconds
        syncCooldown = 0;

        for (int dimId : DimensionManager.getIDs()) {
            WorldServer world = MinecraftServer.getServer().worldServerForDimension(dimId);
            long time = world.getWorldTime();
            boolean doCycle = world.getGameRules().getGameRuleBooleanValue("doDaylightCycle");

            for (Object obj : world.playerEntities) {
                EntityPlayerMP player = (EntityPlayerMP) obj;
                player.playerNetServerHandler.sendPacket(
                    new S03PacketTimeUpdate(time, time, doCycle)
                );
            }
        }
    }
}

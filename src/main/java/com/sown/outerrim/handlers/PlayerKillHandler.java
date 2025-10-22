package com.sown.outerrim.handlers;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.play.server.S29PacketSoundEffect;
import net.minecraft.server.MinecraftServer;
import net.minecraftforge.event.entity.living.LivingDeathEvent;

import java.util.UUID;

public class PlayerKillHandler {

    private static final UUID SOWN_UUID = UUID.fromString("5135dae1-7a0a-4915-85e1-c5a923e2d98a");

    @SubscribeEvent
    public void onEntityKilled(LivingDeathEvent event) {
        Entity source = event.source.getEntity();
        if (!(source instanceof EntityPlayerMP)) return;

        EntityPlayerMP killer = (EntityPlayerMP) source;

        if (!killer.getUniqueID().equals(SOWN_UUID)) return;

        // 50% chance to play the sound
        if (Math.random() >= 0.5) return;

        double x = killer.posX;
        double y = killer.posY;
        double z = killer.posZ;

        float volume = 1.0F;
        double maxDistance = 16.0 * volume;

        for (Object obj : MinecraftServer.getServer().getConfigurationManager().playerEntityList) {
            if (!(obj instanceof EntityPlayerMP)) continue;
            EntityPlayerMP listener = (EntityPlayerMP) obj;

            double dx = listener.posX - x;
            double dy = listener.posY - y;
            double dz = listener.posZ - z;
            double distSq = dx * dx + dy * dy + dz * dz;

            if (distSq <= maxDistance * maxDistance) {
                listener.playerNetServerHandler.sendPacket(
                    new S29PacketSoundEffect(
                        "outerrim:vo.mp.hero.anakin.core.landing_strike",
                        x, y, z,
                        volume,
                        1.0F
                    )
                );
            }
        }
    }
}

package com.sown.outerrim.handlers;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.play.server.S29PacketSoundEffect;

public class DimensionSoundHandler {

    public static void playSoundForPlayer(EntityPlayerMP player, String soundName, float pitch) {
        System.out.println("[DEBUG] Sending sound: " + soundName + " to " + player.getCommandSenderName());
        player.playerNetServerHandler.sendPacket(
            new S29PacketSoundEffect(
                soundName,
                player.posX, player.posY, player.posZ,
                10000.0F, // huge volume, max range
                pitch
            )
        );
    }
}

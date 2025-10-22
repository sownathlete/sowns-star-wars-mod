package com.sown.outerrim.handlers;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.play.server.S29PacketSoundEffect;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PlayerLevelUpHandler {

    // Store each player's last known level
    private final Map<UUID, Integer> previousLevels = new HashMap<>();

    @SubscribeEvent
    public void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (event.phase != TickEvent.Phase.END || !(event.player instanceof EntityPlayerMP)) return;

        EntityPlayerMP player = (EntityPlayerMP) event.player;
        UUID uuid = player.getUniqueID();
        int currentLevel = player.experienceLevel;

        if (previousLevels.containsKey(uuid)) {
            int lastLevel = previousLevels.get(uuid);
            if (currentLevel > lastLevel) {
                // Level up detected
                player.playerNetServerHandler.sendPacket(
                    new S29PacketSoundEffect(
                        "outerrim:ux.rank_up.prestige",
                        player.posX, player.posY, player.posZ,
                        Float.MAX_VALUE, // Max volume so it's always heard
                        1.0F
                    )
                );
            }
        }

        // Update stored level
        previousLevels.put(uuid, currentLevel);
    }
}
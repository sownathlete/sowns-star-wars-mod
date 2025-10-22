package com.sown.outerrim.handlers;

import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.play.server.S29PacketSoundEffect;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.event.entity.living.LivingDeathEvent;

import java.lang.reflect.Method;
import java.util.UUID;

public class PlayerDeathHandler {

    private static final UUID SOWN_UUID = UUID.fromString("5135dae1-7a0a-4915-85e1-c5a923e2d98a");

    @SubscribeEvent
    public void onPlayerDeath(LivingDeathEvent event) {
        if (!(event.entity instanceof EntityPlayerMP)) return;

        EntityPlayerMP player = (EntityPlayerMP) event.entity;
        UUID uuid = player.getUniqueID();

        double deathX = player.posX;
        double deathY = player.posY;
        double deathZ = player.posZ;

        float volume = 1.0F;
        double maxHearingDistance = 16.0 * volume;

        // Sown (Anakin outro global + localized)
        if (uuid.equals(SOWN_UUID)) {
            for (Object obj : MinecraftServer.getServer().getConfigurationManager().playerEntityList) {
                EntityPlayerMP onlinePlayer = (EntityPlayerMP) obj;

                // Global outro sound
                onlinePlayer.playerNetServerHandler.sendPacket(
                    new S29PacketSoundEffect(
                        "outerrim:music.hero.anakin.outro",
                        onlinePlayer.posX, onlinePlayer.posY, onlinePlayer.posZ,
                        10000.0F,
                        1.0F
                    )
                );
            }

            // Localized Anakin death sound
            for (Object obj : MinecraftServer.getServer().getConfigurationManager().playerEntityList) {
                EntityPlayerMP otherPlayer = (EntityPlayerMP) obj;

                double dx = otherPlayer.posX - deathX;
                double dy = otherPlayer.posY - deathY;
                double dz = otherPlayer.posZ - deathZ;
                double distanceSq = dx * dx + dy * dy + dz * dz;

                if (distanceSq <= maxHearingDistance * maxHearingDistance) {
                    otherPlayer.playerNetServerHandler.sendPacket(
                        new S29PacketSoundEffect(
                            "outerrim:death.hero.anakin",
                            deathX, deathY, deathZ,
                            volume,
                            1.0F
                        )
                    );
                }
            }
        }

        // ZugVam
        else if (uuid.toString().equals("e2e62bf7-1f56-4a7a-81c0-e2528b0bffcd")) {
            for (Object obj : MinecraftServer.getServer().getConfigurationManager().playerEntityList) {
                EntityPlayerMP otherPlayer = (EntityPlayerMP) obj;

                double dx = otherPlayer.posX - deathX;
                double dy = otherPlayer.posY - deathY;
                double dz = otherPlayer.posZ - deathZ;
                double distanceSq = dx * dx + dy * dy + dz * dz;

                if (distanceSq <= maxHearingDistance * maxHearingDistance) {
                    otherPlayer.playerNetServerHandler.sendPacket(
                        new S29PacketSoundEffect(
                            "outerrim:music.hero.dooku.outro",
                            deathX, deathY, deathZ,
                            volume,
                            1.0F
                        )
                    );
                }
            }
        }

        // 711Hisroyal
        else if (uuid.toString().equals("44952a43-1177-4b80-9129-e2447a519f16")) {
            for (Object obj : MinecraftServer.getServer().getConfigurationManager().playerEntityList) {
                EntityPlayerMP otherPlayer = (EntityPlayerMP) obj;

                double dx = otherPlayer.posX - deathX;
                double dy = otherPlayer.posY - deathY;
                double dz = otherPlayer.posZ - deathZ;
                double distanceSq = dx * dx + dy * dy + dz * dz;

                if (distanceSq <= maxHearingDistance * maxHearingDistance) {
                    otherPlayer.playerNetServerHandler.sendPacket(
                        new S29PacketSoundEffect(
                            "outerrim:music.hero.obi_wan.intro",
                            deathX, deathY, deathZ,
                            volume,
                            1.0F
                        )
                    );
                }
            }
        }

        // Only reset legacy if the killer is a player
        if (event.source.getEntity() instanceof EntityPlayerMP) {
            // Legacy reset (Legends mod)
            if (Loader.isModLoaded("legends")) {
                try {
                    Class<?> legacyHelper = Class.forName("com.tihyo.legends.legacy.LegacyHelper");

                    Method hasLegacy = legacyHelper.getDeclaredMethod("hasLegacy", net.minecraft.entity.player.EntityPlayer.class);
                    boolean hadLegacy = (Boolean) hasLegacy.invoke(null, player);

                    if (hadLegacy) {
                        Method resetClass = legacyHelper.getDeclaredMethod("resetClass", net.minecraft.entity.player.EntityPlayer.class);
                        resetClass.invoke(null, player);

                        System.out.println("[DEBUG] Successfully reset legacy for: " + player.getCommandSenderName());

                        ChatComponentText msg = new ChatComponentText("Death has severed your bond with the Force.");
                        msg.setChatStyle(new ChatStyle().setColor(EnumChatFormatting.GRAY));
                        player.addChatMessage(msg);
                    }

                } catch (Exception e) {
                    System.err.println("[ERROR] Failed to reset legacy via reflection:");
                    e.printStackTrace();
                }
            } else {
                System.out.println("[INFO] Legends mod not loaded — skipping legacy check.");
            }
        }
    }
}

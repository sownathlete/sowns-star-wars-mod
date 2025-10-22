package com.sown.outerrim.handlers;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.gameevent.PlayerEvent.PlayerChangedDimensionEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraft.entity.player.EntityPlayerMP;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.sown.outerrim.OuterRimResources;

public class DimensionEntrySoundHandler {

    private static final Map<Integer, String> DIMENSION_SOUNDS = new HashMap<>();
    private static final float DEFAULT_PITCH = 1.0F;

    private final Map<String, Integer> lastKnownDimension = new HashMap<>();
    private final Map<EntityPlayerMP, PendingSound> delayedSounds = new HashMap<>();

    private static final int DELAY_TICKS = 20;

    static {
        String[] planets = {
            "AjanKloss", "AhchTo", "Alderaan", "Bespin", "Bogano", "Bracca", "Byss",
            "Corellia", "Coruscant", "Crait", "Dagobah", "Dantooine", "Dathomir", "DeathStar",
            "DQar", "Endor", "Felucia", "Fondor", "Geonosis", "Hoth", "Ilum",
            "Jakku", "Kamino", "Kashyyyk", "Kessel", "Korriban", "Manaan", "Mimban", "Mustafar", "Naboo", "Niamos", "Savareen",
            "Scarif", "Sullust", "Takodana", "Tatooine", "Tython", "Utapau", "Vjun", "WorldBetweenWorlds", "Yavin4"
        };

        for (String planet : planets) {
            String formatted = "DQar".equals(planet)
                ? "dqar"
                : planet.replaceAll("([a-z])([A-Z])", "$1_$2")
                        .replaceAll("([A-Za-z])([0-9])", "$1_$2")
                        .toLowerCase();
            DIMENSION_SOUNDS.put(
                OuterRimResources.ConfigOptions.getDimId(planet),
                "outerrim:planet." + formatted + ".ambient"
            );
        }
    }

    public DimensionEntrySoundHandler() {
        FMLCommonHandler.instance().bus().register(this);
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void onPlayerChangeDimension(PlayerChangedDimensionEvent event) {
        if (event.player instanceof EntityPlayerMP) {
            queueSound((EntityPlayerMP) event.player, event.toDim);
        }
    }

    @SubscribeEvent
    public void onPlayerLogin(PlayerLoggedInEvent event) {
        if (event.player instanceof EntityPlayerMP) {
            queueSound((EntityPlayerMP) event.player, event.player.dimension);
        }
    }

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public void onLivingUpdate(LivingEvent.LivingUpdateEvent event) {
        if (!(event.entityLiving instanceof EntityPlayerMP)) return;
        if (!Loader.isModLoaded("warpdrive")) return;

        EntityPlayerMP player = (EntityPlayerMP) event.entityLiving;
        int currentDim = player.dimension;
        String name = player.getCommandSenderName();

        Integer lastDim = lastKnownDimension.get(name);
        if (lastDim == null || lastDim != currentDim) {
            lastKnownDimension.put(name, currentDim);
            queueSound(player, currentDim);
        }
    }

    @SubscribeEvent
    public void onServerTick(TickEvent.ServerTickEvent event) {
        if (event.phase != TickEvent.Phase.END) return;

        Iterator<Map.Entry<EntityPlayerMP, PendingSound>> it = delayedSounds.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<EntityPlayerMP, PendingSound> entry = it.next();
            PendingSound pending = entry.getValue();
            pending.ticksRemaining--;

            if (pending.ticksRemaining <= 0) {
                if (!entry.getKey().isDead) {
                    DimensionSoundHandler.playSoundForPlayer(entry.getKey(), pending.sound, DEFAULT_PITCH);
                }
                it.remove();
            }
        }
    }

    private void queueSound(EntityPlayerMP player, int dimId) {
        String sound = DIMENSION_SOUNDS.get(dimId);
        if (sound != null) {
            delayedSounds.put(player, new PendingSound(sound, DELAY_TICKS));
        }
    }

    private static class PendingSound {
        String sound;
        int ticksRemaining;

        PendingSound(String sound, int delayTicks) {
            this.sound = sound;
            this.ticksRemaining = delayTicks;
        }
    }
}

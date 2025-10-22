package com.sown.outerrim.bounty;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.event.entity.living.LivingDeathEvent;

import java.util.*;

public class BountyDeathHandler {

    private static final Map<String, List<ItemStack>> pendingRewards = new HashMap<>();

    @SubscribeEvent
    public void onPlayerDeath(LivingDeathEvent event) {
        if (!(event.entity instanceof EntityPlayerMP)) return;
        EntityPlayerMP victim = (EntityPlayerMP) event.entity;

        if (event.source.getEntity() instanceof EntityPlayerMP) {
            EntityPlayerMP killer = (EntityPlayerMP) event.source.getEntity();
            BountyManager.claimBounty(killer, victim);

            BountyManager.Bounty bounty = BountyManager.getBounty(victim);
            if (bounty == null) return;

            Map<String, Integer> contributions = bounty.getContributors();
            int max = 0;
            List<String> topContributors = new ArrayList<>();

            for (Map.Entry<String, Integer> entry : contributions.entrySet()) {
                int value = entry.getValue();
                if (value > max) {
                    max = value;
                    topContributors.clear();
                    topContributors.add(entry.getKey());
                } else if (value == max) {
                    topContributors.add(entry.getKey());
                }
            }

            if (!topContributors.isEmpty()) {
                String winner = topContributors.get(0);
                EntityPlayerMP receiver = MinecraftServer.getServer().getConfigurationManager().func_152612_a(winner);

                ItemStack head = new ItemStack((Item) Item.itemRegistry.getObject("minecraft:skull"), 1, 3);
                head.setStackDisplayName(victim.getCommandSenderName() + "'s Head");

                if (receiver != null) {
                    receiver.inventory.addItemStackToInventory(head);
                    receiver.addChatMessage(new ChatComponentText("You received " + victim.getCommandSenderName() + "'s head as the top bounty contributor.").setChatStyle(new net.minecraft.util.ChatStyle().setColor(EnumChatFormatting.GOLD)));
                } else {
                    pendingRewards.computeIfAbsent(winner, k -> new ArrayList<>()).add(head);
                }
            }
        }
    }

    public static void handleLogin(EntityPlayerMP player) {
        String name = player.getCommandSenderName();
        if (pendingRewards.containsKey(name)) {
            List<ItemStack> rewards = pendingRewards.remove(name);
            for (ItemStack reward : rewards) {
                player.inventory.addItemStackToInventory(reward);
            }
            player.addChatMessage(new ChatComponentText("You have received pending bounty rewards.").setChatStyle(new net.minecraft.util.ChatStyle().setColor(EnumChatFormatting.YELLOW)));
        }
    }
} 

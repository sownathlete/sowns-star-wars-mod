package com.sown.outerrim.commands;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;

import java.util.*;

import com.sown.outerrim.bounty.BountyManager;
import com.sown.outerrim.registry.ItemRegister;

public class CommandLottery extends CommandBase {

    private static final Map<UUID, Integer> tickets = new HashMap<>();
    private static final int TICKET_PRICE = 10;
    private static int pot = 0;

    @Override
    public String getCommandName() {
        return "lottery";
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "/lottery <buy|draw|status|clear> [tickets]";
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) {
        if (!(sender instanceof EntityPlayerMP)) {
            sender.addChatMessage(new ChatComponentText("Only players can use this command."));
            return;
        }

        EntityPlayerMP player = (EntityPlayerMP) sender;

        if (args.length == 0) {
            player.addChatMessage(colored("Usage: /lottery <buy|draw|status|clear> [tickets]", EnumChatFormatting.YELLOW));
            return;
        }

        String sub = args[0].toLowerCase();

        switch (sub) {
            case "buy": {
                int count = 1;
                if (args.length >= 2) {
                    try {
                        count = Integer.parseInt(args[1]);
                        if (count <= 0) throw new NumberFormatException();
                    } catch (NumberFormatException e) {
                        player.addChatMessage(colored("Invalid ticket count.", EnumChatFormatting.RED));
                        return;
                    }
                }

                int totalCost = count * TICKET_PRICE;

                if (!removeCreditsFromInventory(player, totalCost)) {
                    player.addChatMessage(colored("You don't have enough credits!", EnumChatFormatting.RED));
                    return;
                }

                tickets.put(player.getUniqueID(), tickets.getOrDefault(player.getUniqueID(), 0) + count);
                pot += totalCost;
                player.addChatMessage(colored("You bought " + count + " ticket(s) for " + totalCost + " credits!", EnumChatFormatting.GREEN));
                break;
            }
            case "draw": {
                if (!player.canCommandSenderUseCommand(4, getCommandName())) {
                    player.addChatMessage(colored("You don't have permission to draw the lottery.", EnumChatFormatting.RED));
                    return;
                }

                if (tickets.isEmpty()) {
                    player.addChatMessage(colored("No entries in the lottery.", EnumChatFormatting.RED));
                    return;
                }

                UUID winner = drawWinner();
                EntityPlayerMP winnerPlayer = MinecraftServer.getServer().getConfigurationManager().func_152612_a(getNameFromUUID(winner));
                if (winnerPlayer != null) {
                    giveCreditsToPlayer(winnerPlayer, pot);
                    announce("[Lottery] " + winnerPlayer.getCommandSenderName() + " won the pot of " + pot + " credits!");
                } else {
                    announce("[Lottery] A player has won the lottery, but is offline. Their prize will be saved.");
                }
                tickets.clear();
                pot = 0;
                break;
            }
            case "status": {
                player.addChatMessage(colored("Current pot: " + pot + " credits", EnumChatFormatting.GOLD));
                player.addChatMessage(colored("Total tickets: " + getTotalTickets(), EnumChatFormatting.GRAY));
                break;
            }
            case "clear": {
                if (!player.canCommandSenderUseCommand(4, getCommandName())) {
                    player.addChatMessage(colored("You don't have permission to clear the lottery.", EnumChatFormatting.RED));
                    return;
                }
                tickets.clear();
                pot = 0;
                player.addChatMessage(colored("Lottery cleared.", EnumChatFormatting.YELLOW));
                break;
            }
            default:
                player.addChatMessage(colored("Unknown subcommand.", EnumChatFormatting.RED));
                break;
        }
    }

    private UUID drawWinner() {
        List<UUID> pool = new ArrayList<>();
        for (Map.Entry<UUID, Integer> entry : tickets.entrySet()) {
            for (int i = 0; i < entry.getValue(); i++) {
                pool.add(entry.getKey());
            }
        }
        Collections.shuffle(pool);
        return pool.get(new Random().nextInt(pool.size()));
    }

    private int getTotalTickets() {
        return tickets.values().stream().mapToInt(i -> i).sum();
    }

    private String getNameFromUUID(UUID uuid) {
        for (Object o : MinecraftServer.getServer().getConfigurationManager().playerEntityList) {
            EntityPlayerMP p = (EntityPlayerMP) o;
            if (p.getUniqueID().equals(uuid)) return p.getCommandSenderName();
        }
        return uuid.toString();
    }

    private void giveCreditsToPlayer(EntityPlayerMP player, int amount) {
        player.addChatMessage(colored("You received " + amount + " credits from the lottery!", EnumChatFormatting.GREEN));
        int[] values = {1000, 100, 10, 1};
        Item[] items = {
            ItemRegister.getRegisteredItem("galactic_credit_1000"),
            ItemRegister.getRegisteredItem("galactic_credit_100"),
            ItemRegister.getRegisteredItem("galactic_credit_10"),
            ItemRegister.getRegisteredItem("galactic_credit_1")
        };

        for (int i = 0; i < values.length; i++) {
            int count = amount / values[i];
            amount %= values[i];
            if (count > 0) {
                player.inventory.addItemStackToInventory(new ItemStack(items[i], count));
            }
        }
    }

    private boolean removeCreditsFromInventory(EntityPlayerMP player, int amount) {
        return BountyManager.removeCreditsFromInventory(player, amount);
    }

    private ChatComponentText colored(String msg, EnumChatFormatting color) {
        ChatComponentText text = new ChatComponentText(msg);
        text.getChatStyle().setColor(color);
        return text;
    }

    private void announce(String msg) {
        ChatComponentText text = new ChatComponentText(msg);
        text.getChatStyle().setColor(EnumChatFormatting.AQUA);
        for (Object o : MinecraftServer.getServer().getConfigurationManager().playerEntityList) {
            ((EntityPlayerMP) o).addChatMessage(text);
        }
    }

    @Override
    public int getRequiredPermissionLevel() {
        return 0;
    }
}
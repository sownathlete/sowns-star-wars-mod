package com.sown.outerrim.commands;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.event.ClickEvent;
import net.minecraft.event.HoverEvent;
import net.minecraft.util.ChatStyle;

public class CommandDiscord extends CommandBase {

    private static final String DISCORD_LINK = "https://discord.gg/Ec4EurXvQd";

    @Override
    public String getCommandName() {
        return "discord";
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "/discord - Provides a clickable invite link to the Discord server.";
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) {
        if (!(sender instanceof EntityPlayer)) {
            sender.addChatMessage(new ChatComponentText(EnumChatFormatting.RED + "This command can only be used by a player."));
            return;
        }

        EntityPlayer player = (EntityPlayer) sender;

        // Clickable [Discord] prefix
        ChatComponentText prefix = new ChatComponentText("[Discord] ");
        prefix.setChatStyle(new ChatStyle().setColor(EnumChatFormatting.BLUE).setBold(true)
                .setChatClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, DISCORD_LINK))
                .setChatHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
                        new ChatComponentText(EnumChatFormatting.GRAY + "Join our community!"))));

        // Clickable message
        ChatComponentText message = new ChatComponentText("Click here to join!");
        message.setChatStyle(new ChatStyle().setColor(EnumChatFormatting.AQUA).setBold(true)
                .setChatClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, DISCORD_LINK))
                .setChatHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
                        new ChatComponentText(EnumChatFormatting.GRAY + "Join our community!"))));

        player.addChatMessage(prefix.appendSibling(message));
    }

    @Override
    public int getRequiredPermissionLevel() {
        return 0;
    }
    
}

package com.sown.outerrim.commands;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.event.ClickEvent;
import net.minecraft.event.HoverEvent;
import net.minecraft.util.ChatStyle;

public class CommandPatreon extends CommandBase {

    private static final String PATREON_LINK = "https://www.patreon.com/c/sownathlete5923/membership";

    @Override
    public String getCommandName() {
        return "patreon";
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "/patreon - Provides a clickable Patreon link.";
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) {
        if (!(sender instanceof EntityPlayer)) {
            sender.addChatMessage(new ChatComponentText(EnumChatFormatting.RED + "This command can only be used by players."));
            return;
        }

        EntityPlayer player = (EntityPlayer) sender;

        ChatComponentText prefix = new ChatComponentText("[Patreon] ");
        prefix.setChatStyle(new ChatStyle().setColor(EnumChatFormatting.RED).setBold(true)
                .setChatClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, PATREON_LINK))
                .setChatHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
                        new ChatComponentText(EnumChatFormatting.YELLOW + "Support the mod development!"))));

        ChatComponentText message = new ChatComponentText("Click here to support!");
        message.setChatStyle(new ChatStyle().setColor(EnumChatFormatting.GOLD).setBold(true)
                .setChatClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, PATREON_LINK))
                .setChatHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
                        new ChatComponentText(EnumChatFormatting.YELLOW + "Support the mod development!"))));

        player.addChatMessage(prefix.appendSibling(message));
    }

    @Override
    public int getRequiredPermissionLevel() {
        return 0;
    }
    
}

package com.sown.outerrim.commands;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.event.ClickEvent;
import net.minecraft.event.HoverEvent;
import net.minecraft.util.ChatStyle;

public class CommandMap extends CommandBase {

    private static final String MAP_LINK = "https://www.sownathlete.com/galaxy-map";

    @Override
    public String getCommandName() {
        return "map";
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "/map - Opens the galaxy map.";
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) {
        if (!(sender instanceof EntityPlayer)) {
            sender.addChatMessage(new ChatComponentText(EnumChatFormatting.RED + "This command can only be used by players."));
            return;
        }

        EntityPlayer player = (EntityPlayer) sender;

        ChatComponentText prefix = new ChatComponentText("[Map] ");
        prefix.setChatStyle(new ChatStyle().setColor(EnumChatFormatting.DARK_PURPLE).setBold(true)
                .setChatClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, MAP_LINK))
                .setChatHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
                        new ChatComponentText(EnumChatFormatting.LIGHT_PURPLE + "Click to open the galaxy map!"))));

        ChatComponentText message = new ChatComponentText("Click here to view the galaxy map!");
        message.setChatStyle(new ChatStyle().setColor(EnumChatFormatting.LIGHT_PURPLE).setBold(true)
                .setChatClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, MAP_LINK))
                .setChatHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
                        new ChatComponentText(EnumChatFormatting.GRAY + "View planets and territories here!"))));

        player.addChatMessage(prefix.appendSibling(message));
    }

    @Override
    public int getRequiredPermissionLevel() {
        return 0;
    }
    
}

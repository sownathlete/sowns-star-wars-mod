package com.sown.outerrim.commands;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.event.ClickEvent;
import net.minecraft.event.HoverEvent;
import net.minecraft.util.ChatStyle;

public class CommandWiki extends CommandBase {

    private static final String WIKI_LINK = "https://sownathlete.com/wiki/Main_Page";

    @Override
    public String getCommandName() {
        return "wiki";
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "/wiki - Opens the mod's wiki.";
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) {
        if (!(sender instanceof EntityPlayer)) {
            sender.addChatMessage(new ChatComponentText(EnumChatFormatting.RED + "This command can only be used by players."));
            return;
        }

        EntityPlayer player = (EntityPlayer) sender;

        ChatComponentText prefix = new ChatComponentText("[Wiki] ");
        prefix.setChatStyle(new ChatStyle().setColor(EnumChatFormatting.DARK_GREEN).setBold(true)
                .setChatClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, WIKI_LINK))
                .setChatHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
                        new ChatComponentText(EnumChatFormatting.GREEN + "Click to open the mod wiki!"))));

        ChatComponentText message = new ChatComponentText("Click here to visit the wiki!");
        message.setChatStyle(new ChatStyle().setColor(EnumChatFormatting.GREEN).setBold(true)
                .setChatClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, WIKI_LINK))
                .setChatHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
                        new ChatComponentText(EnumChatFormatting.GRAY + "Find guides and information here!"))));

        player.addChatMessage(prefix.appendSibling(message));
    }

    @Override
    public int getRequiredPermissionLevel() {
        return 0;
    }

}

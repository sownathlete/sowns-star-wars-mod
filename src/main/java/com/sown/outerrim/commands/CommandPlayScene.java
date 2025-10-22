package com.sown.outerrim.commands;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.play.server.S29PacketSoundEffect;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;

public class CommandPlayScene extends CommandBase {

    private static final float RANGE = 100.0F;

    @Override
    public String getCommandName() {
        return "playscene";
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "/playscene <sound_name>";
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) {
        if (!(sender instanceof EntityPlayerMP)) return;

        if (args.length < 1) {
            sender.addChatMessage(new ChatComponentText(EnumChatFormatting.RED + "Usage: /playscene <sound_name>"));
            return;
        }

        String soundName = args[0].toLowerCase();
        EntityPlayerMP sourcePlayer = (EntityPlayerMP) sender;
        World world = sourcePlayer.worldObj;

        for (Object obj : world.playerEntities) {
            if (obj instanceof EntityPlayerMP) {
                EntityPlayerMP target = (EntityPlayerMP) obj;

                if (target.dimension == sourcePlayer.dimension &&
                    target.getDistanceToEntity(sourcePlayer) <= RANGE) {

                    target.playerNetServerHandler.sendPacket(
                        new S29PacketSoundEffect(
                            soundName,
                            target.posX,
                            target.posY,
                            target.posZ,
                            10000.0F,
                            1.0F
                        )
                    );
                }
            }
        }

        sender.addChatMessage(new ChatComponentText(EnumChatFormatting.GREEN + "Scene sound played: " + soundName));
    }

    @Override
    public boolean canCommandSenderUseCommand(ICommandSender sender) {
        return true;
    }
}

package com.sown.outerrim.commands;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public class CommandSmite extends CommandBase {

    @Override
    public String getCommandName() {
        return "smite";
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return EnumChatFormatting.RED + "/smite " + EnumChatFormatting.WHITE + "- Strikes lightning at the block you look at." +
               "\n" + EnumChatFormatting.RED + "/smite me " + EnumChatFormatting.WHITE + "- Strikes lightning at yourself." +
               "\n" + EnumChatFormatting.RED + "/smite <player> " + EnumChatFormatting.WHITE + "- Strikes another player (tab-completable)." +
               "\n" + EnumChatFormatting.RED + "/smite nearest " + EnumChatFormatting.WHITE + "- Strikes the nearest entity." +
               "\n" + EnumChatFormatting.RED + "/smite all " + EnumChatFormatting.WHITE + "- Strikes all mobs within 64 blocks.";
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) {
        if (!(sender instanceof EntityPlayerMP)) {
            sender.addChatMessage(new ChatComponentText(EnumChatFormatting.RED + "Only players can use this command."));
            return;
        }

        EntityPlayerMP player = (EntityPlayerMP) sender;
        World world = player.worldObj;

        if (args.length == 0) {
            smiteLookAt(player, world);
        } else {
            String target = args[0].toLowerCase();

            if (target.equals("me")) {
                smiteEntity(player, player, world);
            } else if (target.equals("nearest")) {
                EntityLivingBase nearest = getNearestEntity(player, 64);
                if (nearest != null) {
                    smiteEntity(player, nearest, world);
                } else {
                    player.addChatMessage(new ChatComponentText(EnumChatFormatting.RED + "No mobs found nearby."));
                }
            } else if (target.equals("all")) {
                smiteAllEntities(player, world);
            } else {
                EntityPlayerMP targetPlayer = MinecraftServer.getServer().getConfigurationManager().func_152612_a(target);
                if (targetPlayer != null) {
                    smiteEntity(player, targetPlayer, world);
                } else {
                    player.addChatMessage(new ChatComponentText(EnumChatFormatting.RED + "Player not found."));
                }
            }
        }
    }

    private void smiteLookAt(EntityPlayerMP player, World world) {
        Vec3 start = Vec3.createVectorHelper(player.posX, player.posY + player.getEyeHeight(), player.posZ);
        Vec3 look = player.getLookVec();
        Vec3 end = start.addVector(look.xCoord * 200, look.yCoord * 200, look.zCoord * 200);
        MovingObjectPosition mop = world.rayTraceBlocks(start, end);

        if (mop != null) {
            world.addWeatherEffect(new net.minecraft.entity.effect.EntityLightningBolt(world, mop.blockX, mop.blockY, mop.blockZ));
            player.addChatMessage(new ChatComponentText(EnumChatFormatting.YELLOW + "Smited at (" + mop.blockX + ", " + mop.blockY + ", " + mop.blockZ + ")."));
        } else {
            player.addChatMessage(new ChatComponentText(EnumChatFormatting.RED + "No valid block found to smite."));
        }
    }

    private void smiteEntity(EntityPlayerMP player, Entity target, World world) {
        world.addWeatherEffect(new net.minecraft.entity.effect.EntityLightningBolt(world, target.posX, target.posY, target.posZ));
        player.addChatMessage(new ChatComponentText(EnumChatFormatting.YELLOW + "Smited " + target.getCommandSenderName() + "!"));
    }

    private void smiteAllEntities(EntityPlayerMP player, World world) {
        List<EntityLivingBase> entities = world.getEntitiesWithinAABB(EntityLivingBase.class, player.boundingBox.expand(64, 64, 64));
        if (!entities.isEmpty()) {
            for (EntityLivingBase entity : entities) {
                smiteEntity(player, entity, world);
            }
            player.addChatMessage(new ChatComponentText(EnumChatFormatting.YELLOW + "Smited all entities within 64 blocks!"));
        } else {
            player.addChatMessage(new ChatComponentText(EnumChatFormatting.RED + "No entities found within range."));
        }
    }

    private EntityLivingBase getNearestEntity(EntityPlayerMP player, double range) {
        List<EntityLivingBase> entities = player.worldObj.getEntitiesWithinAABB(EntityLivingBase.class, player.boundingBox.expand(range, range, range));
        EntityLivingBase closest = null;
        double closestDistance = Double.MAX_VALUE;

        for (EntityLivingBase entity : entities) {
            double distance = player.getDistanceSqToEntity(entity);
            if (distance < closestDistance && entity != player) {
                closest = entity;
                closestDistance = distance;
            }
        }
        return closest;
    }

    @Override
    public List<String> addTabCompletionOptions(ICommandSender sender, String[] args) {
        if (args.length == 1) {
            List<String> options = new ArrayList<>();
            options.add("me");
            options.add("nearest");
            options.add("all");

            for (String name : MinecraftServer.getServer().getAllUsernames()) {
                options.add(name);
            }
            
            return getListOfStringsMatchingLastWord(args, options.toArray(new String[0]));
        }
        return null;
    }

    @Override
    public int getRequiredPermissionLevel() {
        return 2;
    }
}

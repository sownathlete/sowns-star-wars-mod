// MessageHyperdrive.java
package com.sown.outerrim.network;

import com.sown.outerrim.world.TransferDim;
import com.sown.util.network.ORMessage;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.WorldServer;

public class MessageHyperdrive extends ORMessage<MessageHyperdrive> {
    // IMPORTANT: keep this as a simple public field so ORMessage can serialize it
    public int destDim;

    public MessageHyperdrive() {
    }

    public MessageHyperdrive(int destDim) {
        this.destDim = destDim;
    }

    @Override
    public IMessage handleMessage(MessageContext context) {
        // NEVER send/deserialize the player. Use the sender from the context.
        final EntityPlayerMP player = context.getServerHandler().playerEntity;

        // Validate dimension exists
        WorldServer target = MinecraftServer.getServer().worldServerForDimension(this.destDim);
        if (target == null) {
            return null;
        }

        // Server-side cooldown (prevents spam)
        if (player.timeUntilPortal > 0) {
            return null;
        }
        player.timeUntilPortal = 20;

        // Teleport mount first (if any), then player
        Entity mount = player.ridingEntity;
        if (mount != null) {
            player.mountEntity(null);
            new TransferDim(target).teleport(mount);
        }

        new TransferDim(target).teleport(player);
        player.setSneaking(false);

        return null;
    }
}

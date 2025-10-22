package com.sown.outerrim.network;

import com.sown.outerrim.world.TransferDim;
import com.sown.util.network.ORMessage;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.WorldServer;

public class MessageHyperdrive extends ORMessage<MessageHyperdrive> {
    public EntityPlayer player;
    public int destDim;

    public MessageHyperdrive() {
    }

    public MessageHyperdrive(EntityPlayer player, int destDim) {
        this.player = player;
        this.destDim = destDim;
    }

    @Override
    public IMessage handleMessage(MessageContext context) {
        if(this.player == null) {
            throw new IllegalStateException("Player must not be null.");
        }

        Entity mount = this.player.ridingEntity;
        this.player.mountEntity(null);
        new TransferDim(MinecraftServer.getServer().worldServerForDimension(this.destDim)).teleport(mount);
        new TransferDim(MinecraftServer.getServer().worldServerForDimension (this.destDim)).teleport(this.player);
        this.player.setSneaking(false);
        return null;
    }
}

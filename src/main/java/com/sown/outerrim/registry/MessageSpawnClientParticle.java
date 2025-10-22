package com.sown.outerrim.registry;

import com.sown.util.network.ORMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;

public class MessageSpawnClientParticle extends ORMessage<MessageSpawnClientParticle> {

    public String particle;
    public double x, y, z;
    public double motionX, motionY, motionZ;

    public MessageSpawnClientParticle() {}

    public MessageSpawnClientParticle(String particle, double x, double y, double z, double motionX, double motionY, double motionZ) {
        this.particle = particle;
        this.x = x;
        this.y = y;
        this.z = z;
        this.motionX = motionX;
        this.motionY = motionY;
        this.motionZ = motionZ;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IMessage handleMessage(MessageContext context) {
        Minecraft mc = Minecraft.getMinecraft();
        WorldClient world = mc.theWorld;
        if (world != null) {
            world.spawnParticle(this.particle, this.x, this.y, this.z, this.motionX, this.motionY, this.motionZ);
        }
        return null;
    }
}

package com.sown.util.block;

import java.util.List;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public interface IDebugProvider {
    List<String> getDebugText(List<String> paramList, EntityPlayer paramEntityPlayer, World paramWorld, int paramInt1, int paramInt2, int paramInt3);
}

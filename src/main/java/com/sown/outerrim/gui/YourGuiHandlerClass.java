package com.sown.outerrim.gui;

import com.sown.outerrim.gui.container.MoistureVaporatorContainer;
import com.sown.outerrim.tileentities.TileEntityMoistureVaporator;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IGuiHandler;

public class YourGuiHandlerClass implements IGuiHandler {

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        TileEntity tileEntity = world.getTileEntity(x, y, z);

        if (tileEntity instanceof TileEntityMoistureVaporator)
            return new MoistureVaporatorContainer(player.inventory, (TileEntityMoistureVaporator) tileEntity);

        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        TileEntity tileEntity = world.getTileEntity(x, y, z);

        if (tileEntity instanceof TileEntityMoistureVaporator)
            return new GuiMoistureVaporator(player.inventory, (TileEntityMoistureVaporator) tileEntity);

        return null;
    }
}
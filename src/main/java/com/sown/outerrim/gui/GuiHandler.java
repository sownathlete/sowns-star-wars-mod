package com.sown.outerrim.gui;

import com.sown.outerrim.OuterRimResources;
import com.sown.outerrim.client.gui.GuiCoaxiumContainer;
import com.sown.outerrim.client.gui.GuiCoaxiumPump;
import com.sown.outerrim.client.gui.GuiCoaxiumRefinery;
import com.sown.outerrim.client.gui.GuiPortableCoaxiumPump;
import com.sown.outerrim.gui.container.ContainerCoaxiumContainer;
import com.sown.outerrim.gui.container.ContainerCoaxiumPump;
import com.sown.outerrim.gui.container.ContainerCoaxiumRefinery;
import com.sown.outerrim.gui.container.MoistureVaporatorContainer;
import com.sown.outerrim.gui.container.ContainerPortableCoaxiumPump;
import com.sown.outerrim.tileentities.TileEntityCoaxiumContainer;
import com.sown.outerrim.tileentities.TileEntityCoaxiumPump;
import com.sown.outerrim.tileentities.TileEntityCoaxiumRefinery;
import com.sown.outerrim.tileentities.TileEntityMoistureVaporator;
import com.sown.outerrim.tileentities.TileEntityPortableCoaxiumPump;
import cpw.mods.fml.common.network.IGuiHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class GuiHandler implements IGuiHandler {

    @Override
    public Object getClientGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
        TileEntity te = world.getTileEntity(x, y, z);
        switch (id) {
            case OuterRimResources.ConfigOptions.GUI_VAPORATOR:
                if (te instanceof TileEntityMoistureVaporator)
                    return new GuiMoistureVaporator(player.inventory, (TileEntityMoistureVaporator) te);
                break;
            case OuterRimResources.ConfigOptions.GUI_REFINERY:
                if (te instanceof TileEntityCoaxiumRefinery)
                    return new GuiCoaxiumRefinery(player.inventory, (TileEntityCoaxiumRefinery) te);
                break;
            case OuterRimResources.ConfigOptions.GUI_COAXIUM_CONTAINER:
                if (te instanceof TileEntityCoaxiumContainer)
                    return new GuiCoaxiumContainer(player.inventory, (TileEntityCoaxiumContainer) te);
                break;
            case OuterRimResources.ConfigOptions.GUI_PORTABLE_PUMP:
                if (te instanceof TileEntityPortableCoaxiumPump)
                    return new GuiPortableCoaxiumPump(player.inventory, (TileEntityPortableCoaxiumPump) te);
                break;
            case OuterRimResources.ConfigOptions.GUI_COAXIUM_PUMP:
                if (te instanceof TileEntityCoaxiumPump)
                    return new GuiCoaxiumPump(player.inventory, (TileEntityCoaxiumPump) te);
                break;
        }
        return null;
    }

    @Override
    public Object getServerGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
        TileEntity te = world.getTileEntity(x, y, z);
        switch (id) {
            case OuterRimResources.ConfigOptions.GUI_VAPORATOR:
                if (te instanceof TileEntityMoistureVaporator)
                    return new MoistureVaporatorContainer(player.inventory, (TileEntityMoistureVaporator) te);
                break;
            case OuterRimResources.ConfigOptions.GUI_REFINERY:
                if (te instanceof TileEntityCoaxiumRefinery)
                    return new ContainerCoaxiumRefinery(player.inventory, (TileEntityCoaxiumRefinery) te);
                break;
            case OuterRimResources.ConfigOptions.GUI_COAXIUM_CONTAINER:
                if (te instanceof TileEntityCoaxiumContainer)
                    return new ContainerCoaxiumContainer(player.inventory, (TileEntityCoaxiumContainer) te);
                break;
            case OuterRimResources.ConfigOptions.GUI_PORTABLE_PUMP:
                if (te instanceof TileEntityPortableCoaxiumPump)
                    return new ContainerPortableCoaxiumPump(player.inventory, (TileEntityPortableCoaxiumPump) te);
                break;
            case OuterRimResources.ConfigOptions.GUI_COAXIUM_PUMP:
                if (te instanceof TileEntityCoaxiumPump)
                    return new ContainerCoaxiumPump(player.inventory, (TileEntityCoaxiumPump) te);
                break;
        }
        return null;
    }
}

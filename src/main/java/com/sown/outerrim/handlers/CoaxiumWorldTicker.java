package com.sown.outerrim.handlers;

import com.sown.outerrim.items.ItemCoaxiumVialRaw;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import net.minecraft.entity.item.EntityItemFrame;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class CoaxiumWorldTicker {

    @SubscribeEvent
    public void onWorldTick(TickEvent.WorldTickEvent evt) {
        if (evt.phase != TickEvent.Phase.END) return;

        World world = evt.world;

        for (Object obj : world.loadedTileEntityList) {
            if (obj instanceof IInventory && obj instanceof TileEntity) {
                safeTickInventory((IInventory) obj, world, (TileEntity) obj);
            }
        }

        for (Object obj : world.loadedEntityList) {

            if (obj instanceof IInventory) {
                safeTickInventory((IInventory) obj, world, null);
            }

            if (obj instanceof EntityItemFrame) {
                EntityItemFrame frame = (EntityItemFrame) obj;
                ItemStack st = frame.getDisplayedItem();
                if (st != null && st.getItem() instanceof ItemCoaxiumVialRaw) {
                    ItemCoaxiumVialRaw.tickInsideInventory(st, world, null);

                    if (st.getTagCompound() == null) {
                        frame.setDisplayedItem(null);
                    } else {
                        frame.setDisplayedItem(st);
                    }
                }
            }
        }
    }

    private void safeTickInventory(IInventory inv, World world, TileEntity te) {
        for (int i = 0; i < inv.getSizeInventory(); i++) {
            ItemStack st = inv.getStackInSlot(i);
            if (st == null || !(st.getItem() instanceof ItemCoaxiumVialRaw)) continue;

            ItemCoaxiumVialRaw.tickInsideInventory(st, world, te);

            if (st.getTagCompound() == null) {
                inv.setInventorySlotContents(i, null);
            } else {
                inv.setInventorySlotContents(i, st);
            }

            if (te != null) {
                te.markDirty();
                world.markBlockForUpdate(te.xCoord, te.yCoord, te.zCoord);
            }
        }
    }
}

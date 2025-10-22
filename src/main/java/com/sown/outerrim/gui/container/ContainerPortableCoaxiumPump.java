package com.sown.outerrim.gui.container;

import com.sown.outerrim.tileentities.TileEntityPortableCoaxiumPump;
import com.sown.outerrim.registry.ItemRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerPortableCoaxiumPump extends Container {
    private final TileEntityPortableCoaxiumPump tile;
    private int lastProg;

    private static final int PUMP_X = 80;
    private static final int PUMP_Y = 18;

    public ContainerPortableCoaxiumPump(InventoryPlayer inv, TileEntityPortableCoaxiumPump te){
        tile = te;

        addSlotToContainer(new Slot(te,0,PUMP_X,PUMP_Y){
            @Override public boolean isItemValid(ItemStack s){
                return s != null && s.getItem() == ItemRegister.getRegisteredItem("vialEmpty");
            }
            @Override public int getSlotStackLimit(){ return 1; }
        });

        int invTop = 49;
        for(int r=0;r<3;r++)
            for(int c=0;c<9;c++)
                addSlotToContainer(new Slot(inv,c+r*9+9,8+c*18,invTop+r*18));

        int hotbarY = invTop + 58;
        for(int c=0;c<9;c++)
            addSlotToContainer(new Slot(inv,c,8+c*18,hotbarY));
    }

    @Override public boolean canInteractWith(EntityPlayer p){ return tile.isUseableByPlayer(p); }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer p,int idx){
        ItemStack res=null; Slot s=(Slot)inventorySlots.get(idx);
        if(s!=null&&s.getHasStack()){
            ItemStack st=s.getStack(); res=st.copy();
            if(idx==0){
                if(!mergeItemStack(st,1,37,true)) return null;
            }else{
                if(st.getItem()!=ItemRegister.getRegisteredItem("vialEmpty")) return null;
                if(!mergeItemStack(st,0,1,false)) return null;
            }
            if(st.stackSize==0) s.putStack(null); else s.onSlotChanged();
        }
        return res;
    }

    @Override public void addCraftingToCrafters(ICrafting c){
        super.addCraftingToCrafters(c);
        c.sendProgressBarUpdate(this,0,tile.getProgress());
    }
    @Override public void detectAndSendChanges(){
        super.detectAndSendChanges();
        for(Object o:crafters){
            ICrafting c=(ICrafting)o;
            if(lastProg!=tile.getProgress())
                c.sendProgressBarUpdate(this,0,tile.getProgress());
        }
        lastProg = tile.getProgress();
    }
    @Override public void updateProgressBar(int id,int v){
        if(id==0) tile.clientProgress = v;
    }
}

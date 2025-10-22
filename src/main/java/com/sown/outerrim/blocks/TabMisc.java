package com.sown.outerrim.blocks;

import com.sown.outerrim.registry.BlockRegister;
import com.sown.outerrim.registry.ItemRegister;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class TabMisc extends CreativeTabs {

    public TabMisc(String label) {
        super(label);
    }

    @Override
    public Item getTabIconItem() {
        return ItemRegister.getRegisteredItem("vialCoaxiumRefined");
    }

    @Override
    public void displayAllReleventItems(List items) {
        items.addAll(getAllMisc());
        
    }

    private List<ItemStack> getAllMisc() {
        List<ItemStack> miscStacks = new ArrayList<>();
        for(Item item : ItemRegister.misc) {
            miscStacks.add(new ItemStack(item));
        }
        return miscStacks;
    }
}

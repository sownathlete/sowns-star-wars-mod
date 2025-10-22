package com.sown.outerrim.blocks;

import com.sown.outerrim.registry.ItemRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class TabTools extends CreativeTabs {

    public TabTools(String label) {
        super(label);
    }

    @Override
    public Item getTabIconItem() {
        return ItemRegister.getRegisteredItem("karnitePickaxe");
    }

    public void displayAllRelevantItems(List items) {
        items.addAll(getAllToolItems());
    }

    private List<ItemStack> getAllToolItems() {
        List<ItemStack> toolItemStacks = new ArrayList<>();
        for(Item item : ItemRegister.tools) {
            toolItemStacks.add(new ItemStack(item));
        }
        return toolItemStacks;
    }
}
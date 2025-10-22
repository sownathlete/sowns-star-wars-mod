package com.sown.outerrim.blocks;

import com.sown.outerrim.registry.BlockRegister;
import com.sown.outerrim.registry.ItemRegister;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class TabStory extends CreativeTabs {

    public TabStory(String label) {
        super(label);
    }

    @Override
    public Item getTabIconItem() {
        return ItemRegister.getRegisteredItem("japor_snippetChestplate");
    }

    @Override
    public void displayAllReleventItems(List items) {
        items.addAll(getAllStory());
    }

    private List<ItemStack> getAllStory() {
        List<ItemStack> foodStacks = new ArrayList<>();
        for(Item item : ItemRegister.story) {
            foodStacks.add(new ItemStack(item));
        }
        return foodStacks;
    }
}
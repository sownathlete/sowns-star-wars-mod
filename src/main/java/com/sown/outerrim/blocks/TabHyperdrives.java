package com.sown.outerrim.blocks;

import com.sown.outerrim.registry.BlockRegister;
import com.sown.outerrim.registry.ItemRegister;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class TabHyperdrives extends CreativeTabs {

    public TabHyperdrives(String label) {
        super(label);
    }

    @Override
    public Item getTabIconItem() {
        return ItemRegister.getRegisteredItem("hyperdriveNaboo");
    }

    @Override
    public void displayAllReleventItems(List items) {
        items.addAll(getAllHyperdrives());
    }

    private List<ItemStack> getAllHyperdrives() {
        List<ItemStack> hyperdriveStacks = new ArrayList<>();
        for (Item item : ItemRegister.hyperdrives) {
            hyperdriveStacks.add(new ItemStack(item));
        }

        Collections.sort(hyperdriveStacks, new Comparator<ItemStack>() {
            @Override
            public int compare(ItemStack stack1, ItemStack stack2) {
                String name1 = stack1.getItem().getUnlocalizedName();
                String name2 = stack2.getItem().getUnlocalizedName();
                return name1.compareToIgnoreCase(name2);
            }
        });

        return hyperdriveStacks;
    }
}

package com.sown.outerrim.blocks;

import com.sown.outerrim.registry.BlockRegister;
import com.sown.outerrim.registry.ItemRegister;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TabMaterials extends CreativeTabs {

    public TabMaterials(String label) {
        super(label);
    }

    @Override
    public Item getTabIconItem() {
        return ItemRegister.getRegisteredItem("alazhi");
    }

    @Override
    public void displayAllReleventItems(List items) {
        items.addAll(getAllMaterials());
    }

    private List<ItemStack> getAllMaterials() {
        List<ItemStack> materialStacks = new ArrayList<>();
        for (Item item : ItemRegister.materials) {
            materialStacks.add(new ItemStack(item));
        }
        Collections.sort(materialStacks, (a, b) -> a.getItem().getUnlocalizedName().compareTo(b.getItem().getUnlocalizedName()));
        return materialStacks;
    }
}

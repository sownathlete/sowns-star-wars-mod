package com.sown.outerrim.blocks;

import com.sown.outerrim.registry.ItemRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class TabWeapons extends CreativeTabs {

    public TabWeapons(String label) {
        super(label);
    }

    @Override
    public Item getTabIconItem() {
        return ItemRegister.getRegisteredItem("inquisitorHelmet");
    }

    public void displayAllRelevantItems(List items) {
        items.addAll(getAllWeaponItems());
    }

    private List<ItemStack> getAllWeaponItems() {
        List<ItemStack> weaponItemStacks = new ArrayList<>();
        for(Item item : ItemRegister.weapons) {
            weaponItemStacks.add(new ItemStack(item));
        }
        return weaponItemStacks;
    }
}
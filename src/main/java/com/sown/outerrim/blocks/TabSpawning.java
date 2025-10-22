package com.sown.outerrim.blocks;

import com.sown.outerrim.registry.ItemRegister;
import com.sown.util.entity.EntityUtils;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import java.util.ArrayList;
import java.util.List;

public class TabSpawning extends CreativeTabs {

    public TabSpawning(String label) {
        super(label);
    }

    @Override
    public Item getTabIconItem() {
        return (ItemRegister.getRegisteredItem("spawn_egg"));
    }

    @Override
    public void displayAllReleventItems(List items) {
        items.addAll(getAllSpawning());
    }

    private List<ItemStack> getAllSpawning() {
        List<ItemStack> spawnStacks = new ArrayList<>();
        for (int entityId : EntityUtils.getRegisteredEntityIds()) {
            Item spawnEgg = (Item) Item.itemRegistry.getObject("spawn_egg");
            if (spawnEgg != null) {
                spawnStacks.add(new ItemStack(spawnEgg, 1, entityId));
            }
        }
        return spawnStacks;
    }
}

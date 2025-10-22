package com.sown.outerrim.blocks;

import com.sown.outerrim.registry.ItemRegister;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class TabFood extends CreativeTabs {

    public TabFood(String label) {
        super(label);
    }

    @Override
    public Item getTabIconItem() {
        return ItemRegister.getRegisteredItem("blueMilkBucket");
    }

    @Override
    public void displayAllReleventItems(List items) {
        items.clear();
        items.addAll(getAllFood());
    }

    private List<ItemStack> getAllFood() {
        List<ItemStack> foodStacks = new ArrayList<>();

        for (Item item : ItemRegister.food) {
            if (!isDrink(item)) {
                foodStacks.add(new ItemStack(item));
            }
        }

        Item glassItem = GameRegistry.findItem("outerrim", "glass");
        if (glassItem != null) {
            foodStacks.add(new ItemStack(glassItem));
        }

        List<Item> glassDrinks = new ArrayList<>();
        List<Item> bottleDrinks = new ArrayList<>();
        List<Item> bucketDrinks = new ArrayList<>();

        for (Item item : ItemRegister.food) {
            if (item == glassItem) continue;

            String itemName = item.getUnlocalizedName().toLowerCase();
            if (itemName.contains("glass")) {
                glassDrinks.add(item);
            } else if (itemName.contains("bottle")) {
                bottleDrinks.add(item);
            } else if (itemName.contains("bucket")) {
                bucketDrinks.add(item);
            }
        }

        for (Item item : glassDrinks) {
            foodStacks.add(new ItemStack(item));
        }
        for (Item item : bottleDrinks) {
            foodStacks.add(new ItemStack(item));
        }
        for (Item item : bucketDrinks) {
            foodStacks.add(new ItemStack(item));
        }

        return foodStacks;
    }

    private boolean isDrink(Item item) {
        String itemName = item.getUnlocalizedName().toLowerCase();
        return itemName.contains("glass") || itemName.contains("bottle") || itemName.contains("bucket");
    }
}

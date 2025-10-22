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

public class TabDeco extends CreativeTabs {

    public TabDeco(String label) {
        super(label);
    }

    @Override
    public Item getTabIconItem() {
        return Item.getItemFromBlock(BlockRegister.getRegisteredBlock("deathStarWallPanel1"));
    }

    @Override
    public void displayAllReleventItems(List items) {
        items.addAll(getSortedItems(getAllLeavesBlocks()));
        items.addAll(getSortedItems(getAllItems()));
        items.addAll(getSortedItems(getAllDecorationBlocks()));
        items.addAll(getSortedItems(getAllFenceBlocks()));
    }

    private List<ItemStack> getAllLeavesBlocks() {
        List<ItemStack> leavesBlockStacks = new ArrayList<>();
        for (Block block : BlockRegister.leavesBlocks) {
            leavesBlockStacks.add(new ItemStack(block));
        }
        return leavesBlockStacks;
    }

    private List<ItemStack> getAllItems() {
        List<ItemStack> decoStacks = new ArrayList<>();
        for (Item item : ItemRegister.deco) {
            decoStacks.add(new ItemStack(item));
        }
        return decoStacks;
    }

    private List<ItemStack> getAllDecorationBlocks() {
        List<ItemStack> decorationBlockStacks = new ArrayList<>();
        for (Block block : BlockRegister.decorationBlocks) {
            decorationBlockStacks.add(new ItemStack(block));
        }
        return decorationBlockStacks;
    }

    private List<ItemStack> getAllFenceBlocks() {
        List<ItemStack> fenceBlockStacks = new ArrayList<>();
        for (Block block : BlockRegister.fenceBlocks) {
            fenceBlockStacks.add(new ItemStack(block));
        }
        return fenceBlockStacks;
    }

    private List<ItemStack> getSortedItems(List<ItemStack> items) {
        List<ItemStack> sortedItems = new ArrayList<>(items);
        Collections.sort(sortedItems, Comparator.comparing(item -> item.getDisplayName().toLowerCase()));
        return sortedItems;
    }
}

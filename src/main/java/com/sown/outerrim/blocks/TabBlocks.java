package com.sown.outerrim.blocks;

import java.util.List;

import com.sown.outerrim.registry.BlockRegister;

import java.util.ArrayList;
import java.util.Collections;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.List;
import java.util.ArrayList;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class TabBlocks extends CreativeTabs {

    public TabBlocks(String label) {
        super(label);
    }

    @Override
    public Item getTabIconItem() {
        return Item.getItemFromBlock(BlockRegister.getRegisteredBlock("boganoGrass"));
    }

    public void displayAllReleventItems(List items) {        
        items.addAll(getAllNormalBlocks());
        items.addAll(getAllSlabs());
        items.addAll(getAllStairs());
        items.addAll(getAllWalls());
    }

    private List<ItemStack> getAllNormalBlocks() {
        List<ItemStack> normalBlockStacks = new ArrayList<>();
        for(Block block : BlockRegister.solidBlocks) {
            normalBlockStacks.add(new ItemStack(block));
        }
        return normalBlockStacks;
    }

    private List<ItemStack> getAllSlabs() {
        List<ItemStack> slabBlockStacks = new ArrayList<>();
        for(Block block : BlockRegister.slabBlocks) {
            slabBlockStacks.add(new ItemStack(block));
        }
        return slabBlockStacks;
    }

    private List<ItemStack> getAllStairs() {
        List<ItemStack> stairsBlockStacks = new ArrayList<>();
        for(Block block : BlockRegister.stairsBlocks) {
            stairsBlockStacks.add(new ItemStack(block));
        }
        return stairsBlockStacks;
    }

    private List<ItemStack> getAllWalls() {
        List<ItemStack> wallBlockStacks = new ArrayList<>();
        for(Block block : BlockRegister.wallBlocks) {
            wallBlockStacks.add(new ItemStack(block));
        }
        return wallBlockStacks;
    }
}

package com.sown.outerrim.blocks;

import com.sown.outerrim.registry.BlockRegister;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class TabUtil extends CreativeTabs {

    public TabUtil(String label) {
        super(label);
    }

    @Override
    public Item getTabIconItem() {
        return Item.getItemFromBlock(BlockRegister.getRegisteredBlock("moisture_vaporator"));
    }

    @Override
    public void displayAllReleventItems(List items) {
        items.addAll(getAllPressurePlates());        
        items.addAll(getAllButtons());       
        items.addAll(getAllTrapdoors());        
        items.addAll(getAllOtherRedstoneComponents());
        items.addAll(getAllUtilBlocks());
    }

    private List<ItemStack> getAllPressurePlates() {
        List<ItemStack> pressurePlateStacks = new ArrayList<>();
        for(Block block : BlockRegister.pressurePlateBlocks) {
            pressurePlateStacks.add(new ItemStack(block));
        }
        return pressurePlateStacks;
    }

    private List<ItemStack> getAllButtons() {
        List<ItemStack> buttonStacks = new ArrayList<>();
        for(Block block : BlockRegister.buttonBlocks) {
            buttonStacks.add(new ItemStack(block));
        }
        return buttonStacks;
    }

    private List<ItemStack> getAllTrapdoors() {
        List<ItemStack> trapdoorStacks = new ArrayList<>();
        for(Block block : BlockRegister.trapdoorBlocks) {
            trapdoorStacks.add(new ItemStack(block));
        }
        return trapdoorStacks;
    }

    private List<ItemStack> getAllUtilBlocks() {
        List<ItemStack> utilBlockStacks = new ArrayList<>();
        for(Block block : BlockRegister.utilBlocks) {
        	utilBlockStacks.add(new ItemStack(block));
        }
        return utilBlockStacks;
    }

    private List<ItemStack> getAllOtherRedstoneComponents() {
        List<ItemStack> redstoneBlockStacks = new ArrayList<>();
        for(Block block : BlockRegister.redstoneComponentsList) {
            if (!BlockRegister.pressurePlateBlocks.contains(block) &&
                !BlockRegister.buttonBlocks.contains(block) &&
                !BlockRegister.trapdoorBlocks.contains(block)) {
                redstoneBlockStacks.add(new ItemStack(block));
            }
        }
        return redstoneBlockStacks;
    }
}
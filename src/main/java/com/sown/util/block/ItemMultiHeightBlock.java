/*
 * Decompiled with CFR 0.148.
 *
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.Block$SoundType
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemBlockWithMetadata
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.world.World
 */
package com.sown.util.block;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlockWithMetadata;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

public class ItemMultiHeightBlock
extends ItemBlockWithMetadata {
    public ItemMultiHeightBlock(Block block) {
        super(block, block);
    }

    @Override
    public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float subX, float subY, float subZ) {
        int i1;
        int j1;
        if (stack.stackSize == 0)
            return false;
        if (!player.canPlayerEdit(x, y, z, side, stack))
            return false;
        Block block = world.getBlock(x, y, z);
        if (block instanceof BlockMultiHeight && (j1 = (i1 = world.getBlockMetadata(x, y, z)) & 0xF) < 15 && world.checkNoEntityCollision(this.field_150939_a.getCollisionBoundingBoxFromPool(world, x, y, z)) && world.setBlockMetadataWithNotify(x, y, z, j1 + 1 | i1 & 0xFFFFFFF8, 2)) {
            world.playSoundEffect(x + 0.5f, y + 0.5f, z + 0.5f, this.field_150939_a.stepSound.func_150496_b(), (this.field_150939_a.stepSound.getVolume() + 1.0f) / 2.0f, this.field_150939_a.stepSound.getPitch() * 0.8f);
            --stack.stackSize;
            return true;
        }
        return super.onItemUse(stack, player, world, x, y, z, side, subX, subY, subZ);
    }
}


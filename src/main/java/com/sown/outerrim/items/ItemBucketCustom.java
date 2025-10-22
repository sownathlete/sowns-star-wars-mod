package com.sown.outerrim.items;

import net.minecraft.item.ItemBucket;
import net.minecraft.block.Block;
import net.minecraft.init.Items;
import com.sown.outerrim.OuterRim; // Import the OuterRim class to access the ItemsTab

public class ItemBucketCustom extends ItemBucket {

    public ItemBucketCustom(Block fluidBlock) {
        super(fluidBlock);
        this.setContainerItem(Items.bucket);
        this.setCreativeTab(OuterRim.tabMisc); // Set the creative tab to OuterRim.ItemsTab
    }

    // Additional methods for behavior, etc. can be added here
}

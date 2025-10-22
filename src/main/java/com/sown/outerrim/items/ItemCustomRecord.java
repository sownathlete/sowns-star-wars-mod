package com.sown.outerrim.items;

import net.minecraft.item.ItemRecord;
import net.minecraft.util.ResourceLocation;

public class ItemCustomRecord extends ItemRecord {

    private final String soundEvent;

    public ItemCustomRecord(String name, String soundEvent) {
        super(name);
        this.soundEvent = soundEvent;
        this.setUnlocalizedName("outerrim." + name);
        this.setTextureName("outerrim:records/record_" + name);
        this.setCreativeTab(com.sown.outerrim.OuterRim.tabMisc); // Replace with your mod's creative tab
    }

    @Override
    public ResourceLocation getRecordResource(String name) {
        return new ResourceLocation(soundEvent);
    }
}

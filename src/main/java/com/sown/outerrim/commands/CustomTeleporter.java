package com.sown.outerrim.commands;

import net.minecraft.entity.Entity;
import net.minecraft.world.Teleporter;
import net.minecraft.world.WorldServer;

public class CustomTeleporter extends Teleporter {

    public CustomTeleporter(WorldServer world) {
        super(world);
    }

    @Override
    public void placeInPortal(Entity entity, double x, double y, double z, float yaw) {
        entity.setPosition(x, y, z);
        entity.worldObj.updateEntityWithOptionalForce(entity, false);
    }

    @Override
    public boolean placeInExistingPortal(Entity entity, double x, double y, double z, float yaw) {
        return false;
    }

    @Override
    public void removeStalePortalLocations(long time) {
    }
}

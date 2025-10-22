package com.sown.outerrim.entities;

import net.minecraft.entity.EntityAgeable;
import net.minecraft.world.World;

public class MobCloneTrooper41stPhase2 extends EntityCloneTrooperBase {

    public MobCloneTrooper41stPhase2(World worldIn) {
        super(worldIn);
    }

    @Override
    public String getTrooperType() {
        return "41st";
    }

    @Override
    public EntityAgeable createChild(EntityAgeable ageable) {
        return null;
    }
}

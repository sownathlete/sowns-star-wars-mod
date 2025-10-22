package com.sown.outerrim.entities;

import net.minecraft.entity.EntityAgeable;
import net.minecraft.world.World;

public class MobCloneTrooper212thPhase2 extends EntityCloneTrooperBase {

    public MobCloneTrooper212thPhase2(World worldIn) {
        super(worldIn);
    }

    @Override
    public String getTrooperType() {
        return "212th";
    }

    @Override
    public EntityAgeable createChild(EntityAgeable ageable) {
        return null;
    }
}

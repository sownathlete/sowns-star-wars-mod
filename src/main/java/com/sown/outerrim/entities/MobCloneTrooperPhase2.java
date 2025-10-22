package com.sown.outerrim.entities;

import net.minecraft.entity.EntityAgeable;
import net.minecraft.world.World;

public class MobCloneTrooperPhase2 extends EntityCloneTrooperBase {

    public MobCloneTrooperPhase2(World worldIn) {
        super(worldIn);
    }

    @Override
    public EntityAgeable createChild(EntityAgeable ageable) {
        return null;
    }

	@Override
	public String getTrooperType() {
		// TODO Auto-generated method stub
		return null;
	}
}

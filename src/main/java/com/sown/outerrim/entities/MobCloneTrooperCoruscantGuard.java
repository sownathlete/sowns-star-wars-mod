package com.sown.outerrim.entities;

import net.minecraft.entity.EntityAgeable;
import net.minecraft.world.World;

public class MobCloneTrooperCoruscantGuard extends EntityCloneTrooperBase {

    public MobCloneTrooperCoruscantGuard(World worldIn) {
        super(worldIn);
    }

    @Override
    public String getTrooperType() {
        return "coruscant_guard";
    }

	@Override
	public EntityAgeable createChild(EntityAgeable p_90011_1_) {
		// TODO Auto-generated method stub
		return null;
	}
}

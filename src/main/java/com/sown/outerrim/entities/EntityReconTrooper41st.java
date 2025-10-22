package com.sown.outerrim.entities;

import net.minecraft.entity.EntityAgeable;
import net.minecraft.world.World;

public class EntityReconTrooper41st extends EntityReconTrooperBase {

    public EntityReconTrooper41st(World worldIn) {
        super(worldIn);
    }

	@Override
	public String getTrooperType() {
        return "41st";
	}

	@Override
	public EntityAgeable createChild(EntityAgeable p_90011_1_) {
		// TODO Auto-generated method stub
		return null;
	}
}
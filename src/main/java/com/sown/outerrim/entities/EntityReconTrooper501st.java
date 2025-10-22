package com.sown.outerrim.entities;

import net.minecraft.entity.EntityAgeable;
import net.minecraft.world.World;

public class EntityReconTrooper501st extends EntityReconTrooperBase {

    public EntityReconTrooper501st(World worldIn) {
        super(worldIn);
    }

	@Override
	public String getTrooperType() {
        return "501st";
	}

	@Override
	public EntityAgeable createChild(EntityAgeable p_90011_1_) {
		// TODO Auto-generated method stub
		return null;
	}
}

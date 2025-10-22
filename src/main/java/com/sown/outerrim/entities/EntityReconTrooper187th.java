package com.sown.outerrim.entities;

import net.minecraft.entity.EntityAgeable;
import net.minecraft.world.World;

public class EntityReconTrooper187th extends EntityReconTrooperBase {

    public EntityReconTrooper187th(World worldIn) {
        super(worldIn);
    }

	@Override
	public String getTrooperType() {
        return "187th";
	}

	@Override
	public EntityAgeable createChild(EntityAgeable p_90011_1_) {
		// TODO Auto-generated method stub
		return null;
	}
}
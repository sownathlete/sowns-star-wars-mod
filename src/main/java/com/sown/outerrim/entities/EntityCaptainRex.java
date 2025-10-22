package com.sown.outerrim.entities;

import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.world.World;

public class EntityCaptainRex extends EntityCloneTrooperBase {

    public EntityCaptainRex(World worldIn) {
        super(worldIn);
    }

    @Override
    public String getTrooperType() {
        return "captain_rex";
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(200.0);  // Increased follow range
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(60.0);  // Standard health
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.3);  // Decent movement speed
    }

	@Override
	public EntityAgeable createChild(EntityAgeable p_90011_1_) {
		// TODO Auto-generated method stub
		return null;
	}
}

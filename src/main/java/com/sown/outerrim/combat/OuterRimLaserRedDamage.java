package com.sown.outerrim.combat;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;

/** Red laser (droids) damage source wrapper. */
public class OuterRimLaserRedDamage extends OuterRimLaserDamage {
    public OuterRimLaserRedDamage(Entity projectile, EntityLivingBase shooter) {
        super("outerrim_laser_red", projectile, shooter);
    }
}

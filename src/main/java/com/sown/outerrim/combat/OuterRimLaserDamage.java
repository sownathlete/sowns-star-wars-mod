package com.sown.outerrim.combat;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.EntityDamageSourceIndirect;
import net.minecraft.util.IChatComponent;

/** Custom indirect projectile damage (green/blue etc.). */
public class OuterRimLaserDamage extends EntityDamageSourceIndirect {
    private final EntityLivingBase shooterRef; // keep our own shooter to avoid obf helpers

    public OuterRimLaserDamage(String damageType, Entity projectile, EntityLivingBase shooter) {
        super(damageType, projectile, shooter);
        this.shooterRef = shooter;
        this.setProjectile();
    }

    // 1.7.10 death message hook
    @Override
    public IChatComponent func_151519_b(EntityLivingBase victim) {
        if (this.shooterRef != null) {
            return new ChatComponentTranslation(
                "death.attack." + this.damageType + ".player",
                victim.func_145748_c_(),
                this.shooterRef.func_145748_c_()
            );
        }
        return new ChatComponentTranslation(
            "death.attack." + this.damageType,
            victim.func_145748_c_()
        );
    }
}

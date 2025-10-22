package com.sown.outerrim.utils;

import net.minecraft.entity.Entity;

public class EntityCooldownEntry {
    public int cooldownLeft;
    public int cooldown;
    public Entity entity;
    public String effect;

    public EntityCooldownEntry(Entity entity, String effect, int cooldown) {
        this.cooldownLeft = cooldown;
        this.cooldown = cooldown;
        this.entity = entity;
        this.effect = effect;
    }
}


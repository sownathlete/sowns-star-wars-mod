package com.sown.outerrim.entities;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.EntityAIBase;

public class AiRandomSneak extends EntityAIBase {
    private EntityLiving entity;
    private int sneakDuration;
    private int currentSneakTime;

    public AiRandomSneak(EntityLiving entity) {
        this.entity = entity;
    }

    @Override
    public boolean shouldExecute() {
        if (this.entity.getRNG().nextInt(100) == 0) {
            this.sneakDuration = 60 * 3; // 60 ticks = 1 second, so 60*3 = 3 seconds
            this.currentSneakTime = 0;
            return true;
        }
        return false;
    }

    @Override
    public boolean continueExecuting() {
        return this.currentSneakTime < this.sneakDuration;
    }

    @Override
    public void updateTask() {
        this.entity.setSneaking(true);
        this.currentSneakTime++;
        if (this.currentSneakTime >= this.sneakDuration) {
            this.entity.setSneaking(false);
        }
    }
}


package com.sown.outerrim.entities;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.IRangedAttackMob;

import java.util.Random;

public class EntityAIAttackWithBow extends EntityAIBase {
    private final EntityCreature entity;
    private final IRangedAttackMob rangedAttackEntity;
    private EntityLivingBase target;
    private int attackCooldown; // Cooldown between individual shots
    private final double moveSpeed;
    private final int attackInterval;
    private final float maxAttackDistance;
    private int seeTime;
    private Random random = new Random();

    private int shotsFired = 0;  // Counter for shots fired
    private int missedShots = 0;  // Counter for missed shots
    private static final int MAX_MISSED_SHOTS = 6;  // Threshold for moving closer
    private static final int MAX_SHOTS_BEFORE_REPOSITION = 6;  // Number of shots before moving closer

    public EntityAIAttackWithBow(IRangedAttackMob rangedAttackEntity, double moveSpeed, int attackInterval, float maxAttackDistance) {
        this.entity = (EntityCreature) rangedAttackEntity;
        this.rangedAttackEntity = rangedAttackEntity;
        this.moveSpeed = moveSpeed;
        this.attackInterval = attackInterval;
        this.maxAttackDistance = maxAttackDistance * maxAttackDistance;
        this.setMutexBits(3);
    }

    @Override
    public boolean shouldExecute() {
        EntityLivingBase target = this.entity.getAttackTarget();
        if (target == null || !target.isEntityAlive()) {
            return false;
        } else {
            this.target = target;
            return true;
        }
    }

    @Override
    public boolean continueExecuting() {
        return this.shouldExecute() || !this.entity.getNavigator().noPath();
    }

    @Override
    public void resetTask() {
        this.target = null;
        this.seeTime = 0;
        this.shotsFired = 0;  // Reset shot counter
        this.missedShots = 0;  // Reset missed shots counter
        this.attackCooldown = 0;  // Reset attack cooldown
    }

    @Override
    public void updateTask() {
        double distanceSq = this.entity.getDistanceSq(this.target.posX, this.target.boundingBox.minY, this.target.posZ);
        boolean canSee = this.entity.getEntitySenses().canSee(this.target);

        if (canSee) {
            ++this.seeTime;
        } else {
            this.seeTime = 0;
        }

        // Move closer if missed 6 shots
        if (this.missedShots >= MAX_MISSED_SHOTS) {
            this.entity.getNavigator().tryMoveToEntityLiving(this.target, this.moveSpeed);
            this.missedShots = 0;  // Reset missed shots after moving
        }

        this.entity.getLookHelper().setLookPositionWithEntity(this.target, 30.0F, 30.0F);

        // Handle attack cooldown (1-second between shots)
        if (--this.attackCooldown <= 0) {
            if (distanceSq > this.maxAttackDistance || !canSee) {
                return;  // Can't shoot if out of range or can't see the target
            }

            float distanceFactor = MathHelper.sqrt_double(distanceSq) / MathHelper.sqrt_float(this.maxAttackDistance);
            boolean hit = this.shootWithSpread(distanceFactor);  // Attempt to shoot with spread

            if (hit) {
                this.missedShots = 0;  // Reset missed shots if hit
            } else {
                this.missedShots++;  // Increment missed shots if missed
            }

            this.shotsFired++;
            this.attackCooldown = 20;  // 1-second cooldown between shots (20 ticks)

            // Check if it's time to reposition after missing too many shots
            if (this.shotsFired >= MAX_SHOTS_BEFORE_REPOSITION) {
                this.entity.getNavigator().tryMoveToEntityLiving(this.target, this.moveSpeed);  // Move closer
                this.shotsFired = 0;  // Reset shots after moving
            }
        }
    }

    // New method to handle shooting with spread and return if it hits
    private boolean shootWithSpread(float distanceFactor) {
        double targetX = this.target.posX + (this.random.nextGaussian() * 0.1);  // Add spread to X-axis
        double targetY = this.target.posY + this.target.getEyeHeight() - 0.2 + (this.random.nextGaussian() * 0.1);  // Add spread to Y-axis
        double targetZ = this.target.posZ + (this.random.nextGaussian() * 0.1);  // Add spread to Z-axis

        // Calculate the direction vector with spread
        double dX = targetX - this.entity.posX;
        double dY = targetY - (this.entity.posY + this.entity.getEyeHeight());
        double dZ = targetZ - this.entity.posZ;
        double distance = MathHelper.sqrt_double(dX * dX + dZ * dZ);

        // Set heading for the ranged attack (with spread and distance adjustment)
        this.rangedAttackEntity.attackEntityWithRangedAttack(this.target, distanceFactor);

        // Simulate if the attack hit or missed (simple random check for this demo, improve based on real conditions)
        return random.nextBoolean();  // 50% chance of hitting for this example (replace with real hit/miss logic)
    }
}

package com.sown.outerrim.entities;

import java.util.List;

import com.sown.outerrim.combat.OuterRimLaserRedDamage;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class EntityLaserProjectileRed extends EntityArrow {

    // Performance tunables
    private static final int    MAX_TICKS_ALIVE  = 40;
    private static final int    MAX_STEPS        = 6;
    private static final double STEP_EPS         = 0.031D;
    private static final double EARLY_BREAK      = 1.0E-4;
    private static final double COLLISION_EXPAND = 0.25D;

    public EntityLaserProjectileRed(World world) {
        super(world);
        this.canBePickedUp = 0;
        this.setIsCritical(false);
        this.setSize(0.1F, 0.1F);
    }

    public EntityLaserProjectileRed(World world, EntityLivingBase shooter, float velocity) {
        super(world, shooter, velocity);
        this.canBePickedUp = 0;
        this.setIsCritical(false);
        this.setSize(0.1F, 0.1F);
    }

    public EntityLaserProjectileRed(World world, EntityLivingBase shooter, EntityLivingBase target, float velocity) {
        super(world, shooter, target, velocity, 0.0F);
        this.canBePickedUp = 0;
        this.setIsCritical(false);
        this.setSize(0.1F, 0.1F);
    }

    public void shootProjectile(EntityLivingBase shooter, float velocity) {
        Vec3 look = shooter.getLookVec();
        this.setLocationAndAngles(shooter.posX, shooter.posY + shooter.getEyeHeight() - 0.1D, shooter.posZ,
                shooter.rotationYaw, shooter.rotationPitch);
        this.setThrowableHeading(look.xCoord, look.yCoord, look.zCoord, velocity, 0.0F);
        this.setIsCritical(false);
    }

    // Emissive render (no world lighting)
    @Override
    public int getBrightnessForRender(float partial) { return 0xF000F0; }
    @Override
    public float getBrightness(float partial) { return 1.0F; }

    @Override
    public void onUpdate() {
        if (!this.worldObj.isRemote && this.ticksExisted > MAX_TICKS_ALIVE) { this.setDead(); return; }

        Vec3 start = Vec3.createVectorHelper(this.posX, this.posY, this.posZ);
        Vec3 end   = Vec3.createVectorHelper(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);

        if (end.subtract(start).lengthVector() < EARLY_BREAK) {
            ++this.ticksExisted;
            return;
        }

        MovingObjectPosition hit = null;
        Vec3 curStart = start;

        // Step through ignorable blocks
        for (int steps = 0; steps < MAX_STEPS; steps++) {
            MovingObjectPosition candidate = this.worldObj.rayTraceBlocks(curStart, end, false);
            if (candidate == null) break;

            Block b = this.worldObj.getBlock(candidate.blockX, candidate.blockY, candidate.blockZ);
            if (shouldIgnoreBlock(b, candidate.blockX, candidate.blockY, candidate.blockZ)) {
                if (candidate.hitVec == null) break;

                Vec3 dir = end.subtract(curStart);
                double len = dir.lengthVector();
                if (len < EARLY_BREAK) break;

                dir = dir.normalize();
                curStart = Vec3.createVectorHelper(
                    candidate.hitVec.xCoord + dir.xCoord * STEP_EPS,
                    candidate.hitVec.yCoord + dir.yCoord * STEP_EPS,
                    candidate.hitVec.zCoord + dir.zCoord * STEP_EPS
                );
                if (end.subtract(curStart).lengthVector() < EARLY_BREAK) break;
                continue;
            }

            hit = candidate;
            break;
        }

        double closest = (hit != null && hit.hitVec != null) ? hit.hitVec.distanceTo(curStart) : Double.MAX_VALUE;

        @SuppressWarnings("rawtypes")
        List list = this.worldObj.getEntitiesWithinAABBExcludingEntity(this,
                this.boundingBox.addCoord(this.motionX, this.motionY, this.motionZ).expand(COLLISION_EXPAND, COLLISION_EXPAND, COLLISION_EXPAND));

        for (int i = 0; i < list.size(); ++i) {
            Entity e = (Entity) list.get(i);
            if (!e.canBeCollidedWith()) continue;
            if (e == this.shootingEntity && this.ticksExisted < 5) continue;

            float border = 0.2F;
            AxisAlignedBB aabb = e.boundingBox.expand(border, border, border);
            boolean inside = aabb.isVecInside(curStart);

            MovingObjectPosition intercept = inside ? new MovingObjectPosition(e) : aabb.calculateIntercept(curStart, end);
            if (intercept != null) {
                double d = inside ? 0.0D : (intercept.hitVec != null ? curStart.distanceTo(intercept.hitVec) : 0.0D);
                if (d < closest) {
                    closest = d;
                    hit = new MovingObjectPosition(e);
                    hit.hitVec = inside ? curStart : intercept.hitVec;
                }
            }
        }

        if (hit != null) handleImpact(hit);

        // Move & slight damping
        this.posX += this.motionX;
        this.posY += this.motionY;
        this.posZ += this.motionZ;

        this.motionX *= 0.995D;
        this.motionY *= 0.995D;
        this.motionZ *= 0.995D;

        this.rotationYaw = (float)(Math.atan2(this.motionX, this.motionZ) * 180.0D / Math.PI);
        double horiz = Math.sqrt(this.motionX * this.motionX + this.motionZ * this.motionZ);
        this.rotationPitch = (float)(Math.atan2(this.motionY, horiz) * 180.0D / Math.PI);

        this.setPosition(this.posX, this.posY, this.posZ);
        ++this.ticksExisted;
    }

    private boolean shouldIgnoreBlock(Block b, int x, int y, int z) {
        if (b == null) return true;
        if (b.isAir(this.worldObj, x, y, z)) return true;
        if (b.isReplaceable(this.worldObj, x, y, z)) return true;
        Material m = b.getMaterial();
        if (m == Material.plants || m == Material.vine || m == Material.leaves) return true;
        if (b.getCollisionBoundingBoxFromPool(this.worldObj, x, y, z) == null) return true;
        return false;
    }

    private void handleImpact(MovingObjectPosition mop) {
        if (mop.entityHit != null) {
            if (!this.worldObj.isRemote) {
                Entity shooter = (this.shootingEntity != null) ? this.shootingEntity : this;
                DamageSource src = new OuterRimLaserRedDamage(this,
                        (shooter instanceof EntityLivingBase) ? (EntityLivingBase) shooter : null);
                mop.entityHit.attackEntityFrom(src, 8.0F);
            }
            this.playSound("outerrim:item.blaster.hit", 1.0F, 1.0F);
            this.setDead();
            return;
        }

        if (mop.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK) {
            this.playSound("outerrim:item.blaster.hit", 1.0F, 1.0F);
            this.setDead();
        }
    }

    @Override
    public void playSound(String sound, float volume, float pitch) {
        if ("random.bowhit".equals(sound) || "random.pop".equals(sound)) sound = "outerrim:item.blaster.hit";
        super.playSound(sound, volume, pitch);
    }
}

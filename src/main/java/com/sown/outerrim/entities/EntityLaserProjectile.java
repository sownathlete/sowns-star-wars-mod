package com.sown.outerrim.entities;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class EntityLaserProjectile extends EntityArrow {

    public EntityLaserProjectile(World world) {
        super(world);
    }

    public EntityLaserProjectile(World world, EntityLivingBase shooter, float velocity) {
        super(world, shooter, velocity);
    }

    public EntityLaserProjectile(World world, EntityLivingBase shooter, EntityLivingBase target, float velocity) {
        super(world, shooter, velocity);
        this.shootingEntity = shooter;
    }

    @Override
    public void onUpdate() {
        super.onUpdate();

        // Handle collision detection manually
        Vec3 startVec = Vec3.createVectorHelper(this.posX, this.posY, this.posZ);
        Vec3 endVec = Vec3.createVectorHelper(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);
        MovingObjectPosition movingObjectPosition = this.worldObj.rayTraceBlocks(startVec, endVec, false);

        // Check if we hit a block
        if (movingObjectPosition != null && movingObjectPosition.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK) {
            int x = movingObjectPosition.blockX;
            int y = movingObjectPosition.blockY;
            int z = movingObjectPosition.blockZ;
            Block hitBlock = this.worldObj.getBlock(x, y, z);

            // If the block is replaceable (like tallgrass, flowers, vines, etc.), ignore it
            if (hitBlock.isReplaceable(this.worldObj, x, y, z)) {
                movingObjectPosition = null; // Ignore replaceable blocks
            }
        }

        // If the projectile has collided with something, handle the impact
        if (movingObjectPosition != null) {
            handleImpact(movingObjectPosition);
        }

        // Update position based on motion
        this.posX += this.motionX;
        this.posY += this.motionY;
        this.posZ += this.motionZ;

        // Apply slight friction
        this.motionX *= 0.99;
        this.motionY *= 0.99;
        this.motionZ *= 0.99;

        // Set position
        this.setPosition(this.posX, this.posY, this.posZ);
    }
 
    public void shootProjectile(EntityLivingBase shooter, float velocity) {
        // Get the direction the shooter is looking at using yaw and pitch
        Vec3 look = shooter.getLookVec();

        // Set the motion of the projectile to the direction the shooter is looking
        this.motionX = look.xCoord * velocity;
        this.motionY = look.yCoord * velocity;
        this.motionZ = look.zCoord * velocity;

        // Set the heading of the projectile (sets the direction and speed)
        this.setThrowableHeading(this.motionX, this.motionY, this.motionZ, velocity, 0.0F);
    }

    private void handleImpact(MovingObjectPosition movingObjectPosition) {
        // Handle impact with an entity
        if (movingObjectPosition.entityHit != null) {
            movingObjectPosition.entityHit.attackEntityFrom(DamageSource.causeArrowDamage(this, this.shootingEntity), 8.0F);

            // **Fix: Stop laser from sticking in entity**
            if (movingObjectPosition.entityHit instanceof EntityLivingBase) {
                ((EntityLivingBase) movingObjectPosition.entityHit).setArrowCountInEntity(0);
            }
        } 
        // Handle impact with a block
        else if (movingObjectPosition.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK) {
            // Spawn smoke particle effect on block hit
            for (int i = 0; i < 5; i++) {
                this.worldObj.spawnParticle("smoke", this.posX, this.posY, this.posZ, 0.0D, 0.0D, 0.0D);
            }
        }

        // Play the "item burn" (random fizz) sound on impact
        this.playSound("outerrim:item.blaster.hit", 1.0F, 1.0F);

        // Mark projectile as dead after impact
        if (!this.worldObj.isRemote) {
            this.setDead();
        }
    }
    
    @Override
    public void playSound(String sound, float volume, float pitch) {
        if ("random.bowhit".equals(sound) || "random.pop".equals(sound)) {
            sound = "outerrim:item.blaster.hit"; // Replace vanilla sounds
        }
        super.playSound(sound, volume, pitch);
    }
}

package com.sown.outerrim.registry;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import com.sown.outerrim.OuterRim;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public abstract class EntityBlasterBoltBase extends EntityThrowable {
    private EntityLivingBase sender;
    private int timeAlive = 0;
    protected float damage = 5.0f;
    protected float speed = 4.5f;
    private Entity target;

    public EntityBlasterBoltBase(World par1World, double par2, double par4, double par6, float damage) {
        super(par1World, par2, par4, par6);
        this.damage = damage;
    }

    public void setTarget(Entity target) {
        this.target = target;
    }

    public Entity getTarget() {
        return this.target;
    }

    private void trackTarget() {
        if (this.target != null) {
            this.renderDistanceWeight = 10.0;
            double d0 = this.target.posX - this.posX;
            double d1 = this.target.boundingBox.minY + (double) (this.target.height / 3.0f) - this.posY;
            double d2 = this.target.posZ - this.posZ;
            double d3 = MathHelper.sqrt_double(d0 * d0 + d2 * d2);
            if (d3 >= 1.0E-7) {
                float f2 = (float) (Math.atan2(d2, d0) * 180.0 / Math.PI) - 90.0f;
                float f3 = (float) (-(Math.atan2(d1, d3) * 180.0 / Math.PI));
                double d4 = d0 / d3;
                double d5 = d2 / d3;
                this.setLocationAndAngles(this.posX + d4, this.posY, this.posZ + d5, f2, f3);
                this.yOffset = 0.0f;
                this.setThrowableHeading(d0, d1, d2, 1.0f, 1.0f);
            }
        }
    }

    public EntityBlasterBoltBase(World par1World, EntityLivingBase par2EntityLivingBase, EntityLivingBase par3EntityLivingBase, float damage) {
        this(par1World, par2EntityLivingBase, damage);
        this.renderDistanceWeight = 10.0;
        this.posY = par2EntityLivingBase.posY + (double) par2EntityLivingBase.getEyeHeight() - 0.10000000149011612;
        
        // Adjust to spawn the bolt closer to the shooter (one-third of the distance)
        double d0 = par3EntityLivingBase.posX - par2EntityLivingBase.posX;
        double d1 = par3EntityLivingBase.boundingBox.minY + (double) (par3EntityLivingBase.height / 3.0f) - this.posY;
        double d2 = par3EntityLivingBase.posZ - par2EntityLivingBase.posZ;
        double d3 = MathHelper.sqrt_double(d0 * d0 + d2 * d2);

        if (d3 >= 1.0E-7) {
            double spawnFactor = 0.33;  // Reduce the spawn distance to one-third
            double d4 = d0 * spawnFactor / d3;
            double d5 = d2 * spawnFactor / d3;
            float f2 = (float) (Math.atan2(d2, d0) * 180.0 / Math.PI) - 90.0f;
            float f3 = (float) (-(Math.atan2(d1, d3) * 180.0 / Math.PI));
            this.setLocationAndAngles(par2EntityLivingBase.posX + d4, this.posY, par2EntityLivingBase.posZ + d5, f2, f3);
            this.yOffset = 0.0f;
            this.setThrowableHeading(d0, d1, d2, 1.0f, 1.0f);
        }
    }

    public EntityBlasterBoltBase(World par1World, EntityLivingBase sender, float damage) {
        super(par1World, sender);
        this.sender = sender;
        this.damage = damage;
        this.setThrowableHeading(sender.getLookVec().xCoord, sender.getLookVec().yCoord, sender.getLookVec().zCoord, 1.0f, 1.0f);
    }

    public EntityBlasterBoltBase(World par1World, float damage) {
        super(par1World);
        this.damage = damage;
    }

    protected float getGravityVelocity() {
        return 0.0f;
    }

    public EntityLivingBase getSender() {
        return this.sender;
    }

    private void hitFX(int blockX, int blockY, int blockZ) {
        Block block = this.worldObj.getBlock(blockX, blockY, blockZ);
        for (int i = 0; i < 10; ++i) {
            double motionX = -this.motionX * 0.07999999821186066;
            double motionY = this.rand.nextDouble() * 0.05000000074505806;
            double motionZ = -this.motionZ * 0.07999999821186066;
            OuterRim.network.sendToDimension(new MessageSpawnClientParticle("smoke", this.posX + (this.rand.nextFloat() - 0.5f) / 3.0f, this.posY + (this.rand.nextFloat() - 0.5f) / 3.0f, this.posZ + (this.rand.nextFloat() - 0.5f) / 3.0f, motionX, motionY, motionZ), this.worldObj.provider.dimensionId);
            motionX = -this.motionX * 0.019999999552965164;
            motionY = this.rand.nextDouble() * 0.019999999552965164;
            motionZ = -this.motionZ * 0.019999999552965164;
            OuterRim.network.sendToDimension(new MessageSpawnClientParticle("blockdust_" + Block.getIdFromBlock(block) + "_" + this.worldObj.getBlockMetadata(blockX, blockY, blockZ), this.posX + (this.rand.nextFloat() - 0.5f) / 3.0f, this.posY + (this.rand.nextFloat() - 0.5f) / 3.0f, this.posZ + (this.rand.nextFloat() - 0.5f) / 3.0f, motionX, motionY, motionZ), this.worldObj.provider.dimensionId);
        }
        this.playSound("OuterRim:fx.bolt.hit", 1.0f, 1.0f);
    }

    protected void onImpact(MovingObjectPosition pos) {
        if (this.sender == null || this.worldObj == null) {
            this.setDead();
            return;
        }
    }

    public static void deflectFX(Entity entity) {
        for (int i = 0; i < 40; ++i) {
            double motionX = -entity.motionX * 0.07999999821186066;
            double motionY = OuterRim.rngGeneral.nextDouble() * 0.05000000074505806;
            double motionZ = -entity.motionZ * 0.07999999821186066;
            OuterRim.network.sendToDimension(new MessageSpawnClientParticle("magicCrit", entity.posX + (OuterRim.rngGeneral.nextFloat() - 0.5f) / 3.0f, entity.posY + (OuterRim.rngGeneral.nextFloat() - 0.5f) / 3.0f, entity.posZ + (OuterRim.rngGeneral.nextFloat() - 0.5f) / 3.0f, motionX, motionY, motionZ), entity.worldObj.provider.dimensionId);
        }
    }

    private void hit(MovingObjectPosition pos) {
        pos.entityHit.attackEntityFrom(OuterRim.blasterDamageSource, this.damage);
        double f4 = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionZ * this.motionZ);
        double k = 1.0;
        if (f4 > 0.0) {
            pos.entityHit.addVelocity(this.motionX * k * 0.6000000238418579 / f4, 0.1, this.motionZ * k * 0.6000000238418579 / f4);
        }
        this.setDead();
    }

    public void onUpdate() {
        super.onUpdate();
        this.trackTarget();
        if (this.timeAlive++ > 100) {
            this.setDead();
        }
    }

    public void setSender(EntityLivingBase sender) {
        this.sender = sender;
    }

    public abstract void recreate(EntityPlayer var1);

    public void setThrowableHeading(double x, double y, double z, float velocity, float inaccuracy) {
        double f2 = MathHelper.sqrt_double(x * x + y * y + z * z);
        this.motionX = (x /= f2) * this.speed;
        this.motionY = (y /= f2) * this.speed;
        this.motionZ = (z /= f2) * this.speed;
        double f3 = MathHelper.sqrt_double(x * x + z * z);
        this.prevRotationYaw = this.rotationYaw = (float) (Math.atan2(x, z) * 180.0 / Math.PI);
        this.prevRotationPitch = this.rotationPitch = (float) (Math.atan2(y, f3) * 180.0 / Math.PI);
    }
}
